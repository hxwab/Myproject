/*
 * @author fengzheqi 
 * @date 2015/09/10
 * @description 添加机构
 */

define(function(require, exports, module) {
	require('validation');
	require('validationInit');
	require('form');
	
	$('#addAgencyForm').validationEngine({
		scroll: false,
		isOverflown:true,
		validateNonVisibleFields: true,
		promptPosition: 'centerRight',
		addPromptClass: 'formError-noArrow formError-text'
	}).ajaxForm({
		url: "system/dataDic/agency/add.action",
		type: "post",
		dataType: "json",
		success: function(data) {
			if (data.tag ==1) {
				dialog({
					title: '提示',
					content: '添加成功!',
					okValue: '确定',
					width: 260,
					ok: function() {
						window.location.href = "system/dataDic/agency/toView.action?entityId="+data.entityId;
					}
				}).showModal();
			} else {
				dialog({
					title: '提示',
					content: '添加失败,请稍后再试!',
					width: 260,
					okValue: '确定',
					ok: function() {
						window.location.reload();
					}
				}).showModal();
			}
		}
	});
	
})