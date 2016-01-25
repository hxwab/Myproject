/**
 * @author liujia
 * @date 2015/8/7
 * @descrioption 新闻列表模块
 */
define(function(require, exports, module){
	require("validation");
	require("validationInit");
	require("form");
	require('datePicker');
	var list = require("list");
	var nameSpace = "account";
	require("form");
	var districtAccount = function() {
		var cnt = count("entityIds");// 判断是否有账号被选中
		if (cnt == 0) {
			alert("请选择要进行角色分配的账号！");
		} else {
			var assignRoleIds = "";// 读取待分配账号的ID，并拼成以","分隔的字符串
			$("input[name='entityIds']").each(function() {
				if (this.checked) {
					assignRoleIds += $(this).val() + ",";
				}
			});
			
			/**
			 * 弹出分配角色层，由于校级账号存在两类，为了避免同时给两类校级账号分配角色，
			 * 需判断某次操作的账号为同一类账号。
			 * @param {String} entityId 待分配角色的账号ID集合
			 */
			/*popAccountDistrict({
				src : nameSpace + "/toAssignRole.action?assignRoleIds=" + assignRoleIds + "&type=1",
				callBack : function(result){
					
				}
			});*/
			top.dialog({
				title: '分配角色',
				url: nameSpace + "/toAssignRole.action?assignRoleIds=" + assignRoleIds,
				width:700,
				onclose: function () {
					var role = this.returnValue.role;
					if(role !== undefined) {
						$("#pagenumber").val($("#list_pagenumber").val());// 记录当前页码，以便回到此页面
						$("#roleIds").val(role);
						$("#list").attr("action", nameSpace + "/assignRole.action");
						$("#list").submit();
					}
				}
			}).showModal();
		}
		return false;
	};
	
	$("body").on("click", "#list_enable", function(){
		var cnt = count("entityIds");
        if (cnt == 0) {
            alert("请选择要启用的账号！");
        } else {
                if($("#checkedIds").length > 0){
                    $("#checkedIds").attr("value", "");
                };
                $("#list_container input[name='entityIds']:checked").clone().hide().appendTo("#form_enable");//加入已选entityIds
                dialog({
					title: '请选择有效时间',
					content: $("#pop_enable")[0],
					okValue: '确定',
					width:300,
					ok: function() {
						$("#form_enable").submit();
						if($("#form_enable").validationEngine("validate")) {
							return true;
						}
						return false;
					},
					cancelValue:"取消",
					cancel: function () {}
				}).showModal();
            }
        return false;
    });
	
	$("body").on("click", "#list_disable", function(){
		var cnt = count("entityIds");
        if (cnt == 0) {
            alert("请选择停用的账号！");
        } else {
                if($("#checkedIds").length > 0){
                    $("#checkedIds").attr("value", "");
                };
                if(confirm("确定要停用账号")){
                	$("#list_container input[name='entityIds']:checked").clone().hide().appendTo("#form_disable");
                	$("#form_disable").submit();
                }
        }
        return false;
        
    });

	$("body").on("click", "#list_generate_first_review_account", function(){
		$.ajax({
			url: nameSpace + "/addExpertAccount.action",
			data:{
				reviewerType:1
			},
			type:"post",
			dataType:"json",
			success: function(data){
				if(data.tag == "1") {
					alert("帐号生成成功!");
					list.getData();
				} else {
					alert("帐号生成失败!");
				}
			}
		});
        return false;
        
    });

	$("body").on("click", "#list_generate_second_review_account", function(){
		$.ajax({
			url: nameSpace + "/addExpertAccount.action",
			data:{
				reviewerType:2
			},
			type:"post",
			dataType:"json",
			success: function(data){
				if(data.tag == "1") {
					alert("帐号生成成功!");
					list.getData();
				} else {
					alert("帐号生成失败!");
				}
			}
		});
        return false;
        
    });
	
	//配置启用/停用表单
	$("#form_enable").validationEngine({
		scroll: false,
		isOverflown:true,
		validateNonVisibleFields: true,
		promptPosition: 'centerRight',
		addPromptClass: 'formError-noArrow formError-text',
	}).ajaxForm({
		type: "post",
		dataType: "json",
		success: function(data) {
			$("#form_enable input[name='entityIds']").remove(); // 清除entityIds
			if (data.tag == "1") {
			} else {
				alert("账号启动失败！");
			}
			list.getData();
		}
	});
	
	$("#form_disable").ajaxForm({
		type: "post",
		dataType: "json",
		success: function(data) {
			$("#form_disable input[name='entityIds']").remove(); // 清除entityIds
			if (data.tag == "1") {
			} else {
				alert("账号停用失败！");
			}
			list.getData();
		}
	});
	
	/*初始化dataPicker */
	$("#form_enable .dataPicker").each(function(){
		$(this).datepicker({
			language: "zh-CN",
		    autoclose: true,
		    todayHighlight: true,
		    format: "yyyy-mm-dd"
		}).on("hide", function(e) {
			$(e.currentTarget).validationEngine("validate");
	    });
	});
	
	$("body").on("click", "#list_assign_role", districtAccount);
	//初始化函数，初始化列表
	exports.init = function(){
		list.pageShow({
			"nameSpace":nameSpace,
			"sortColumnId":["sortcolumn0","sortcolumn1","sortcolumn2","sortcolumn3","sortcolumn4","sortcolumn5","sortcolumn6"],
			"sortColumnValue":{"sortcolumn0":0,"sortcolumn1":1,"sortcolumn2":2,"sortcolumn3":3,"sortcolumn4":4,"sortcolumn5":5,"sortcolumn6":6}
		});
	};
	
});

