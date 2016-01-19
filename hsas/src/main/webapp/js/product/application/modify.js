define(function(require, exports, module){
	var nameSpace = "product/application";
	require("validation");
	require("validationInit");
	require("form");
	require("uploadify");
	require("uploadify-ext");
	require('datePicker');
	require("js/pop/popInit.js");
	var memberCount = 0; // 合作者计数器
	
	var award_info_setting = new Setting({
		id: "award_info",
		buttons: ['next', 'tempSubmit', 'cancel'],
		out_check: function() {
			var notSelectedAgency = (!$("#product_agencyId").val());
			
			if(notSelectedAgency) {
				var warning1 = notSelectedAgency ? '<span style="color:red;">* 请选择单位<br /></span>':'';
				dialog({
					title: '提示',
					width: '320px',
					content: warning1
				}).showModal();
				return false;
			}
			
			var validationResult = $.makeArray($("#award_info input, #award_info select, #award_info textarea").map(function(previousValue, currentValue, index, array) {
				return ($(currentValue).validationEngine('validate'));
			}));
			
			return ! validationResult.reduce(function(previousValue, currentValue, index, array) {
				  return previousValue || currentValue;
			});
		}
	});
	var member_info_setting = new Setting({
		id: "member_info",
		buttons:  ['prev', 'next', 'tempSubmit', 'cancel'],
		out_check: function() {
			var validationResult = $.makeArray($("#member_info input, #member_info select, #member_info textarea").map(function(previousValue, currentValue, index, array) {
				return ($(currentValue).validationEngine('validate'));
			}));
			
			if(validationResult.length) {
				return ! validationResult.reduce(function(previousValue, currentValue, index, array) {
					return previousValue || currentValue;
				});
			} else {
				return true;
			}
		}
	});
	var other_info_setting = new Setting({
		id: "other_info",
		buttons:  ['prev', 'finish', 'tempSubmit', 'cancel'],
		out_check: function() {
			if($("#status").val()==2) {
				var notUploadAuditFile = (!$("#auditAttachmentUpload-queue").html()),
					notUploadProductFile = (!$("#productAttachmentUpload-queue").html());
					
				if(notUploadAuditFile || notUploadProductFile) {
					var warning2 = notUploadAuditFile ? ' <span style="color:red;">* 请上传审核材料<br /></span>':'',
						warning3 = notUploadProductFile ? '<span style="color:red;">* 请上传成果附件<br /></span>':'';
					dialog({
						title: '提示',
						width: '320px',
						content: warning2  + warning3
					}).showModal();
					return false;
				}
			}
			
			$("#introduction").addClass("validate[required]");
			$("#abstractStr").addClass("validate[required]");
			$("#socialEffect").addClass("validate[required]");
			
			var validationResult = $.makeArray($("#other_info input, #other_info select, #other_info textarea").map(function(previousValue, currentValue, index, array) {
				return ($(currentValue).validationEngine('validate'));
			}));
			
			if(validationResult.length) {
				return ! validationResult.reduce(function(previousValue, currentValue, index, array) {
					return previousValue || currentValue;
				});
			} else {
				return true;
			}
		}
	});
	
	step_controller = new Step_controller();
	
	step_controller.after_move_opt = function(){
		var flag = false;
		for (step in step_controller.steps){
			var $curLi = $("li[name=" + step_controller.steps[step].id + "]");
			if (step_controller.steps[step].id == step_controller.state){
				flag = true;
				$curLi.attr("class", "proc step_d");
			} else if (!flag){
				$curLi.attr("class", "proc step_e");
			} else {
				$curLi.attr("class", "proc step_f");
			}
		}
	};
	
	step_controller.add_step(award_info_setting);
	step_controller.add_step(member_info_setting);
	step_controller.add_step(other_info_setting);
	step_controller.init();
	
	
	$("#prev").click(function(){
		step_controller.prev();
	});

	$("#next").click(function(){
		step_controller.next();
	});

	$("#tempSubmit").click(function(){
		step_controller.tempSubmit();
	});

	$("#retry").click(function(){
		location.reload();
	});

	$("#cancel").click(function(){
		history.go(-1);
	});

	$("#finish").click(function(){
		step_controller.submit();
	});

	$("#award_info").show();
	
	$("#form_award_application").validationEngine({
		scroll: false,
		isOverflown:true,
		validateNonVisibleFields: true,
		promptPosition: 'centerRight:2, 2',
		addPromptClass: 'formError-noArrow formError-text'
	}).ajaxForm({
		type: "post",
//		dataType: "json",
		beforeSubmit: function(){
		},
		success: function(data) {
			if (data.tag == "1") {
				dialog({
					title: '提示',
					content: '修改成功!',
					okValue: '确定',
					width: 260,
					ok: function() {
						window.location.href = nameSpace + "/toView.action?entityId=" + data.entityId;
					},
					onclose: function(){
						window.location.href = nameSpace + "/toView.action?entityId=" + data.entityId;
					}
				}).showModal();
			} else {
				dialog({
					title: '提示',
					content: '修改失败!',
					width: 260,
					okValue: '确定',
					ok: function() {
						window.location.reload();
					}
				}).showModal();
			}
		}
	});
	
	
	
	/*初始化dataPicker */
	var initDataPicker = function(){
		$("#form_award_application .birthday").each(function(){
			$(this).datepicker({
				language: "zh-CN",
			    autoclose: true,
			    todayHighlight: true,
			    format: "yyyy-mm-dd",
			    defaultViewDate: {year: '1975', month: '0', day: '1'}
			})
			.on("hide", function(e) {
				$(e.currentTarget).validationEngine("validate");
		    });
		});
		$("#form_award_application .publishDate").each(function(){
			$(this).datepicker({
				language: "zh-CN",
			    autoclose: true,
			    todayHighlight: true,
			    format: "yyyy-mm-dd",
			    startDate: (new Date().getFullYear()-3)+"-01-01",
			    endDate: (new Date().getFullYear()-2)+"-12-31",
			    defaultViewDate: {year: new Date().getFullYear()-3, month: '0', day: '1'}
			})
			.on("hide", function(e) {
				$(e.currentTarget).validationEngine("validate");
		    });
		});
	}
	initDataPicker();
	
	/* 完成合作者添加 */
	var reSort = function(){
		$("#member_info .member").each(function(index){
			$(this).find(".member-count").text(index + 1);
			$(this).find("input, textarea, select").attr("name", function(){
				if(/\w+\./.test(this.name)){
					return this.name.replace(/\w+\./,"member" + (index + 2) + ".");
				}
				return this.name.replace(/\d$/,index + 2);
			})
		});
		
	}
	
	/* 初始化上传控件 */
	var entityId = $("#entityId").val();
	$("#file1_" + entityId).uploadifyExt({
		uploadLimitExt: '1', // 文件上传个数的限制
		fileSizeLimit: '100MB', // 文件上传大小的限制
		fileTypeDesc: '附件', // 可以不用管
		fileTypeExts: '*.pdf',// 限制为压缩文件上传格式
		buttonText : '上传附件', // 按钮上的文字
		buttonClass: "btn btn-default",
		hideButton : true,
		buttonImg: " ",
		preventCaching: false,
		multi: false,
		onUploadSuccess:function(file,data,response){
			$("#" + file.id).attr("fileId", $.parseJSON(data).fileId);
			$("#" + file.id + " div.uploadify-progress").remove();
			$("#" + this.settings.id).parents("td").find(".validation-file-input").val(file.name).validationEngine('hide');
		}
	});
	$("#file2_" + entityId).uploadifyExt({
		uploadLimitExt: '1', // 文件上传个数的限制
		fileSizeLimit: '100MB', // 文件上传大小的限制
		fileTypeDesc: '附件', // 可以不用管
		fileTypeExts: '*.rar;*.zip;',// 限制为压缩文件上传格式
		buttonText : '上传附件', // 按钮上的文字
		buttonClass: "btn btn-default",
		hideButton : true,
		buttonImg: " ",
		preventCaching: false,
		multi: false,
		onUploadSuccess:function(file,data,response){
			$("#" + file.id).attr("fileId", $.parseJSON(data).fileId);
			$("#" + file.id + " div.uploadify-progress").remove();
			$("#" + this.settings.id).parents("td").find(".validation-file-input").val(file.name).validationEngine('hide');
		}
	});
	
	
	//添加著作和论文的操作
	$("body").on("change", "#productType",function(){
		if ($(this).val() =="著作" || $(this).val() == ""){
			$("#productResearchType").removeClass("validate[required]");
			$("#productResearchtype").val("");
			$("#tr_research_type").hide(500);
			$("#publishLevel").val("").show().attr("name","product.publishLevel");
			$("#paperLevel").val("").hide().removeAttr("name");
		} else {
			$("#productResearchType").addClass("validate[required]");
			$("#tr_research_type").show(500);
			$("#publishLevel").val("").hide().removeAttr("name");
			$("#paperLevel").val("").show().attr("name","product.publishLevel");
		}
	})
	
	//出版社或发表刊物级别类型
	$("body").on("click", ".j-productPublishLevel", function(){
		if (!$("#productType").val()) {
			alert("请选择成果类型");
		}
	});
	
	//添加合作者
	$("body").on("click", ".member-add", function(){
		if(!memberCount) {
			$("#member_info .switch").hide();
		}
		memberCount ++;
		if(memberCount > 5) {
			alert("最多添加5个合作者！");
			return ;
		}
		var node = $(TrimPath.processDOMTemplate("member_template",{member:"member" + (memberCount + 1), memberCount:memberCount}));
		node.appendTo("#member_info").fadeIn();
		initDataPicker();//重新初始化dataPicker
		$("#form_award_application").validationEngine('detach').validationEngine({
			scroll: false,
			isOverflown:true,
			validateNonVisibleFields: true,
			promptPosition: 'centerRight',
			addPromptClass: 'formError-noArrow formError-text'
		}).validationEngine('hideAll');
		return false;
	})
	
	//删除合作者
	$("body").on("click", ".member-delete", function(){
		memberCount --;
		$(this).parents(".panel").remove();
		reSort();
		if(!memberCount) {
			$("#member_info .switch").show();
		}
		return false;
	});
	
	$(".countWords").bind("propertychange input", function(){
		var idName = $(this).attr("id"), 
		count = $(this).val().length;
		if(idName=="introduction" && count >800){
			$("#introduction").val($("#introduction").val().slice(0,800));
			$(this).parent().parent().find(".count").html(800);
		} else if(idName=="abstractStr" && count >6000){
			$("#abstractStr").val($("#abstractStr").val().slice(0,6000));
			$(this).parent().parent().find(".count").html(6000);
		} else if(idName=="socialEffect" && count >6000){
			$("#socialEffect").val($("#socialEffect").val().slice(0,6000));
			$(this).parent().parent().find(".count").html(6000);
		} else{
			$(this).parent().parent().find(".count").html(count);
		}
	});
	
	
	exports.init = function(){
		$("select").each(function(){
			if($(this).attr("data") && $(this).attr("data").length){
				$(this).val($(this).attr("data"));
			}
		});
		$(".countWords").each(function(){
			$(this).parent().parent().find(".count").html($(this).val().length);
		});
		memberCount = $("#member_info .member").length;
		if(memberCount) {
			$("#member_info .switch").hide();
		}
	}
})
