/* author fengzheqi
 * date 20151105
 * description 机构添加
 * */

define(function(require, exports, module){
	var view = require('view');
	var nameSpace = 'system/dataDic/agency';
	
	exports.init = function() {
		view.show(nameSpace, showDetail);
		view.add(nameSpace, showDetail);
		view.mod(nameSpace, showDetail);
		view.del(nameSpace, showDetail);
		view.prev(nameSpace, showDetail);
		view.next(nameSpace, showDetail);
		view.back(nameSpace, showDetail);
	};

	function showDetail(results) {
		if(results.errorInfo == null || results.errorInfo == '') {
			$("#entityId").val(results.agency.id);
			$('#view_container').hide();
			$('#view_container').html(TrimPath.processDOMTemplate('view_template', results));
			$('#view_container').show();
		} else {
			alert(results.errorInfo);
			}
		$.isLoading("hide");//关闭加载信息
  }
});