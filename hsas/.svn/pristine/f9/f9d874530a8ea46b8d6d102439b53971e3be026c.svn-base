/**
 * @author liujia
 * @date 2015/8/7
 * @descrioption 权限列表模块
 */
define(function(require, exports, module){
	require('validation');
	require('validationInit');
	var list = require("list");
	var nameSpace = "product/firstAudit";
	require("form");
	
	exports.init = function(){
		/**
		 * 初始化列表
		 */
		list.pageShow({
			"nameSpace":nameSpace,
			"sortColumnId":["sortcolumn0","sortcolumn1","sortcolumn2","sortcolumn3","sortcolumn4","sortcolumn5","sortcolumn6","sortcolumn7","sortcolumn8","sortcolumn9","sortcolumn10"],
			"sortColumnValue":{"sortcolumn0":0,"sortcolumn1":1,"sortcolumn2":2,"sortcolumn3":3,"sortcolumn4":4,"sortcolumn5":5,"sortcolumn6":6,"sortcolumn7":7,"sortcolumn8":8,"sortcolumn9":9,"sortcolumn10":10}
		});
		
		/**
		 * 批量审核
		 */
		$('body').on('click', '#btn_audit', function(){
			var cnt = count("entityIds");
			if (cnt == 0) {
				alert("请选择要修改的记录！");
			} else {
				dialog({
					title: "申报审核",
					width: 500,
					content: $('#div_pop_firstAudit')
				}).showModal();
			}
		});
		
		/**
		 * 审核选择过程
		 */
		$('body').on('click', 'input[name=auditResultTemp]', function() {
			var auditResult = $("input[name=auditResultTemp]:checked").val();
			if (auditResult==1) {
				$('.j-auditNotAgree').show();
				$('.j-auditBack').hide();
				$('input.j-auditOption:checked').attr("checked", false);
				$('#auditOptionTemp').val("");
			} else if (auditResult  == 2) {
				$('.j-auditNotAgree').hide();
				$('.j-auditBack').hide();
				$('input.j-auditOption:checked').attr("checked", false);
				$('#auditOptionTemp').val('同意申报！');
			} else if (auditResult  == 3){
				$('.j-auditBack').show();
				$('.j-auditNotAgree').hide();
				$('input.j-auditOption:checked').attr("checked", false);
				$('#auditOptionTemp').val("");
			}
		});
		
		/**
		 * 填充审核意见
		 */
		$('body').on('click', '.j-auditOption', function() {
			var options = ""
			var _checked = $('input.j-auditOption:checked');
			if (!this.value) {
				$('#auditOptionTemp').focus();
			} else {
				_checked.each(function(index) {
					if(index != _checked.length-1){
						options += (this.value + "；");
					} else {
						options += this.value;
					}
				});
				$('#auditOptionTemp').val(options);
			}
		})
		
		/**
		 * 批量审核提交
		 */
		$('body').on('click', '#auditSubmit', function() {
			$('#auditResult').val($("input[name=auditResultTemp]:checked").val());
			$('#auditOption').val($('#auditOptionTemp').val());
			$("#list").attr("action", "product/firstAudit/audit.action");
			$("#list").ajaxSubmit({
				dataType: "json",
				beforeSubmit: function(){
					if (!$('input[name=auditResultTemp]:checked').val()){
						alert('请选择审核结果！');
						return false;
					}
				},
				success: function(data) {
					if (data.tag ==1) {
						dialog({
							title: '提示',
							content: '审核成功!',
							okValue: '确定',
							width: 260,
							ok: function() {
								window.location.href = "product/firstAudit/toList.action?update=1";
							}
						}).showModal();
					} else {
						dialog({
							title: '提示',
							content: data.tag,
							width: 260,
							okValue: '确定',
							ok: function() {
								window.location.reload();
							}
						}).showModal();
					}
				}
			});
		});
		
	};
	
	
});

