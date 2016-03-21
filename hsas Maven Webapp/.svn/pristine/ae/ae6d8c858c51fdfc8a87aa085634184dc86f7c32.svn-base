define(function(require, exports, module){
	var nameSpace = "product";
	require("validation");
	require("validationInit");
	require("form");
	require('datePicker');
	require("js/pop/popInit.js");
	var memberCount = 0; // 合作者计数器
	var award_info_setting = new Setting({
		id: "award_info",
		buttons: ['next', 'retry', 'cancel'],
		out_check: function() {
			var validationResult = $.makeArray($("#award_info input, #award_info select, #award_info textarea").map(function(previousValue, currentValue, index, array) {
				return ($(currentValue).validationEngine('validate'));
			}));
			
			return ! validationResult.reduce(function(previousValue, currentValue, index, array) {
				  return previousValue || currentValue;
			});
		}
	});
	var person_info_setting = new Setting({
		id: "person_info",
		buttons:   ['prev','next', 'retry', 'cancel'],
		out_check: function() {
			var validationResult = $.makeArray($("#person_info input, #person_info select, #person_info textarea").map(function(previousValue, currentValue, index, array) {
				return ($(currentValue).validationEngine('validate'));
			}));
			return ! validationResult.reduce(function(previousValue, currentValue, index, array) {
				return previousValue || currentValue;
			});
		}
	});
	var member_info_setting = new Setting({
		id: "member_info",
		buttons:  ['prev', 'finish', 'retry', 'cancel'],
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
	step_controller.add_step(person_info_setting);
	step_controller.add_step(member_info_setting);
	step_controller.init();
	
	
	$("#prev").click(function(){
		step_controller.prev();
	});

	$("#next").click(function(){
		step_controller.next();
	});

	$("#confirm").click(function(){
		step_controller.next();
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
		promptPosition: 'centerRight',
		addPromptClass: 'formError-noArrow formError-text'
	}).ajaxForm({
		type: "post",
		dataType: "json",
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
						window.location.href = nameSpace + "toView.action?entityId=" + $("#entityId");
					},
					onclose: function(){
						window.location.href = nameSpace + "toView.action?entityId=" + $("#entityId");
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
		$("#form_award_application .dataPicker").each(function(){
			$(this).datepicker({
				language: "zh-CN",
			    autoclose: true,
			    todayHighlight: true,
			    format: "yyyy-mm-dd"
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
	$("body").on("click", ".member-delete", function(){
		memberCount --;
		$(this).parents(".panel").remove();
		reSort();
		if(!memberCount) {
			$("#member_info .switch").show();
		}
		return false;
	})
	
	
	exports.init = function(){
		$("select").each(function(){
			if($(this).attr("data") && $(this).attr("data").length){
				$(this).val($(this).attr("data"));
			}
		});
		memberCount = $("#member_info .member").length;
		if(memberCount) {
			$("#member_info .switch").hide();
		}
	}
})
