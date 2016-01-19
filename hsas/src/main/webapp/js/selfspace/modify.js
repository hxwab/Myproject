/*
 * @author fengzheqi
 * @date 2015/08/31
 * @description 修改个人信息
 */

define(function(require, exports, module){
	require('validation');
	require('validationInit');
	require('form');
	require("js/pop/popInit.js");
	
	/*初始化验证表单*/
	$('#modifyForm').validationEngine({
		scroll: false,
		isOverflown:true,
		validateNonVisibleFields: true,
		promptPosition: 'centerRight',
		addPromptClass: 'formError-noArrow formError-text'
	}).ajaxForm({
		url: "personInfo/modify.action",
		type: "post",
		dataType: "json",
		success: function(data) {
			if (data.tag == "1") {
				dialog({
					title: '提示',
					content: '修改个人信息成功!',
					okValue: '确定',
					width: 260,
					ok: function() {
						window.location.href = "personInfo/toView.action";
					}
				}).showModal();
			} else {
				dialog({
					title: '提示',
					content: '修改个人信息失败,请稍后再试!',
					width: 260,
					okValue: '确定',
					ok: function() {
						window.location.reload();
					}
				}).showModal();
			}
		}
	})
})