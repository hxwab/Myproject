/*
 * @author fengzheqi
 * @date 2015/09/14
 * @describe 专家列表
 */

define(function(require, exports, module){
	require('form');
	var list = require('list');
	var nameSpace = 'expert';
	
	exports.init = function(){
		//列表初始化
		list.pageShow({
			'nameSpace': nameSpace,
			'sortColumnId': ["sortcolumn0","sortcolumn1","sortcolumn2","sortcolumn3","sortcolumn4","sortcolumn5"],
			'sortColumnValue': {"sortcolumn0":0,"sortcolumn1":1,"sortcolumn2":2,"sortcolumn3":3,"sortcolumn4":4,"sortcolumn5":5}
		});
		
		//推荐专家操作
		$('body').on('click', '#list_add_recommend', function(){
			var cnt = count("entityIds");
			if (cnt == 0) {
				alert("请选择要推荐的专家！");
			} else {
				if (confirm("您确定要推荐所选专家吗？")) {
					$('#list').attr('action', nameSpace + '/authorize.action');
					$('#list').submit();
					window.location.reload();
				}
			}
		});
		
		//撤销推荐专家操作
		$('body').on('click', '#list_delete_recommend', function(){
			var cnt = count("entityIds");
			var entityIds = "";
			if (cnt == 0) {
				alert("请选择撤销推荐的专家");
			} else {
				if (confirm("您确定要撤销所选的推荐专家吗？")) {
					$('#list').attr('action', nameSpace + '/unauthorize.action');
					$('#list').submit();
					window.location.reload();
				}
			}
		});
		
		//导出操作
		$('body').on('click', '#list_export', function(){
			if (confirm("您确定要导出所列专家的信息吗？")) {
				document.forms[0].action = "expert/exportExpert.action";
				document.forms[0].submit();
			}
		});
	}
})