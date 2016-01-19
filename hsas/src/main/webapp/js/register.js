/*
 * @author fengzheqi
 * @date 2015/08/24
 * @description 用户注册
 */

define(function(require, exports, module){
	require('validation');
	require('validationInit');
	require('datePicker');
	require('form');
	require("js/pop/popInit.js");
	
	/*初始化验证表单*/
	$('#registerForm').validationEngine({
		scroll: false,
		isOverflown:true,
		validateNonVisibleFields: true,
		promptPosition: 'centerRight',
		addPromptClass: 'formError-noArrow formError-text'
	}).ajaxForm({
		type: "post",
		dataType: "json",
		success: function(data) {
			if (data.tag == "4") {
				dialog({
					title: '提示',
					content: '注册成功!',
					okValue: '确定',
					width: 260,
					ok: function() {
						window.location.href = basePath+"toIndex.action";
					}
				}).showModal();
			} else if(data.tag == "2"){
				dialog({
					title: '提示',
					content: '两次输入密码不正确！',
					width: 260,
					okValue: '确定'
				}).showModal();
			} else if(data.tag == "3"){
				dialog({
					title: '提示',
					content: '该邮箱已注册！',
					width: 260,
					okValue: '确定'
				}).showModal();
			}else if(data.tag == "1"){
				dialog({
					title: '提示',
					content: '用户名已存在！',
					width: 260,
					okValue: '确定'
				}).showModal();
			}else if(data.tag == "5"){/* 5/6用于找回密码时判断用户名和邮箱是否合法  */
				dialog({
					title: '提示',
					content: '用户名或邮箱错误！',
					width: 260,
					okValue: '确定'
				}).showModal();
			}else if(data.tag == "6"){/* 5/6用于找回密码时判断用户名和邮箱是否合法  */
				dialog({
					title: '提示',
					content: '密码找回成功，请登录邮箱查看详细信息！',
					width: 260,
					okValue: '确定'
				}).showModal();
			}
		}
	});
	/*初始化dataPicker */
	var initDataPicker = function(){
		$("#registerForm .dataPicker").each(function(){
			$(this).datepicker({
				language: "zh-CN",
			    autoclose: true,
			    todayHighlight: true,
			    format: "yyyy-mm-dd"
			}).on("hide", function(e) {
				$(e.currentTarget).validationEngine("validate");
		    });
		});
	}
	initDataPicker();
	$("#cancel").click(function(){
		history.back();
	});
})