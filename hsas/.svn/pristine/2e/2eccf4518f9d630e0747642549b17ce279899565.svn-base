/*
 * @author fengzheqi
 * @date 2015/08/26
 * @description 奖励管理
 * */

define(function(require, exports, module){
	require('form');
	require('validation');
	require('validationInit');
	var list = require('list');
	var nameSpace = "system/dataDic/reward";
	
	exports.init = function() {
		list.pageShow({
			"nameSpace":nameSpace,
			"sortColumnId":["sortcolumn0","sortcolumn1","sortcolumn2","sortcolumn3","sortcolumn4","sortcolumn5","sortcolumn6","sortcolumn7","sortcolumn8"],
			"sortColumnValue":{"sortcolumn0":0,"sortcolumn1":1,"sortcolumn2":2,"sortcolumn3":3,"sortcolumn4":4,"sortcolumn5":5,"sortcolumn6":6,"sortcolumn7":7,"sortcolumn8":8}
		});
		
		/*$('#form_modify').validationEngine({
			scroll: false,
			isOverflown:true,
			validateNonVisibleFields: true,
			promptPosition: 'bottomLeft: -20, 5',
			addPromptClass: 'formError-noArrow formError-text'
		}).ajaxForm({
			url: "system/dataDic/reward/modify.action",
			type: "post",
			dataType: "json",
			success: function(data) {
				if (data.tag == "1") {
					dialog({
						title: '提示',
						content: '修改奖金成功!',
						okValue: '确定',
						width: 260,
						ok: function() {
							window.location.href = "system/dataDic/reward/toList.action";
						}
					}).showModal();
				} else {
					dialog({
						title: '提示',
						content: '修改奖金失败,请稍后再试!',
						width: 260,
						okValue: '确定',
						ok: function() {
							window.location.href = "system/dataDic/reward/toList.action";
						}
					}).showModal();
				}
			}
		});*/
		/*
		$('body').on('click', '#list_modify', function(){
			var cnt = count("entityIds");
			var entityIds = "";
			if (cnt == 0) {
				alert("请选择要修改的记录！");
			} else{
				var collect = $("input[type=checkbox]:checked");
				for(var i= 0; i<collect.length; i++){
					entityIds += (i==collect.length-1)?(collect[i].value):(collect[i].value+';');
				}
				$('#modifyIds').val(entityIds);
				dialog({
					title: '修改金额',
					content: $('#div_modify'),
					width: 320,
					okValue: '确定',
					ok: function() {
							$('#form_modify').submit();
					},
					cancelValue: '取消',
					cancel: function () {
						this.close();
					}
				}).showModal();
			}
			
		});*/
		
		
	}
	
})