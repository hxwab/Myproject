/**
 * @author fengzheqi
 * @date 2015/12/12
 * @descrioption 初审审核模块
 */
define(function(require, exports, module){
	require('form');
	
	/**
	 * 审核选择过程
	 */
	$('body').on('click', 'input[name=auditResult]', function() {
		var auditResult = $("input[name=auditResult]:checked").val();
		if (auditResult==1) {
			$('.j-auditNotAgree').show();
			$('.j-auditBack').hide();
			$('input.j-auditOption:checked').attr("checked", false);
			$('#auditOption').val("");
		} else if (auditResult  == 2) {
			$('.j-auditNotAgree').hide();
			$('.j-auditBack').hide();
			$('input.j-auditOption:checked').attr("checked", false);
			$('#auditOption').val('同意申报！');
		} else if (auditResult  == 3){
			$('.j-auditBack').show();
			$('.j-auditNotAgree').hide();
			$('input.j-auditOption:checked').attr("checked", false);
			$('#auditOption').val("");
		}
		top.dialog.getCurrent().data.tweakSize();
	});
	
	/**
	 * 填充审核意见
	 */
	$('body').on('click', '.j-auditOption', function() {
		var options = ""
		var _checked = $('input.j-auditOption:checked');
		if (!this.value) {
			$('#auditOption').focus();
		} else {
			_checked.each(function(index) {
				if(index != _checked.length-1){
					options += (this.value + "；");
				} else {
					options += this.value;
				}
			});
			$('#auditOption').val(options);
		}
	})
	
	$('#formFirstAudit').ajaxForm({
		url: 'product/firstAudit/audit.action',
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
					content: result.tag,
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