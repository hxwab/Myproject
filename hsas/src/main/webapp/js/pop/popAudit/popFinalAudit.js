/**
 * @author fengzheqi
 * @date 2015/12/12
 * @descrioption 终审审核模块
 */
define(function(require, exports, module){
	require('form');
	$('#formFinalAudit').ajaxForm({
		url: 'product/finalAudit/audit.action',
		type: 'post',
		beforeSubmit: function() {
			if (!$('input[name=auditResult]:checked').val()){
				alert('请选择审核结果！');
				return false;
			}
		},  
		success: function(result) {
			if (result.tag == 1) {
				dialog({
					title: '提示',
					content: '审核完成!',
					okValue: '确定',
					width: 260,
					ok: function() {
						top.dialog.get(window).close();
					}
				}).showModal();
			} else {
				dialog({
					title: '提示',
					content: '审核失败,请稍后再试!',
					width: 260,
					okValue: '确定',
					ok: function() {
						top.dialog.get(window).close();
					}
				}).showModal();
			}
		}
	})
})