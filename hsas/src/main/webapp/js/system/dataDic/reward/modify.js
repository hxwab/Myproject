/*
 * @author fengzheqi
 * @date 2015/09/09
 * @description 添加奖金配置
 */

define(function(require, exports, module){
	require('datePicker');
	require('validation');
	require('validationInit');
	require('form');
	
	/*初始化dataPicker */
	$('#bonusForm .datePicker').each(function(){
		$(this).datepicker({
			language: 'zh-CN',
			startView: 2,
			minViewMode: 2,
			format: 'yyyy',
			autoclose: true
		}).on("hide", function(e) {
			$(e.currentTarget).validationEngine("validate");
	    });
	});
	
	/*初始化验证表单*/
	$('#bonusForm').validationEngine({
		scroll: false,
		isOverflown:true,
		validateNonVisibleFields: true,
		promptPosition: 'centerRight',
		addPromptClass: 'formError-noArrow formError-text'
	}).ajaxForm({
		url: "system/dataDic/reward/modify.action",
		type: "post",
		dataType: "json",
		success: function(data) {
			if (data.tag ==1) {
				dialog({
					title: '提示',
					content: '修改成功!',
					okValue: '确定',
					width: 260,
					ok: function() {
						window.location.href = "system/dataDic/reward/toView.action?entityId="+$("#entityId").val();
					}
				}).showModal();
			} else {
				dialog({
					title: '提示',
					content: '修改失败,请稍后再试!',
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