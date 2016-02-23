/*
 * @author fengzheqi
 * @date 2015/09/14
 * @describe 专家详情
 */

define(function(require, exports, module){
	var view = require('view');
	var nameSpace = "expert";
	var showDetail = function(results) {
		if (results.errorInfo == null || results.errorInfo == "") {
			$("#entityId").val(results.expert.id);
			$("#view_container").hide();
			$("#view_container").html(TrimPath.processDOMTemplate("view_template",results));
			$("#view_container").show();
		} else {
			alert(results.errorInfo);
		}
		
		$.isLoading("hide");//关闭加载信息
	}
	
	/*详情页面初始化*/
	exports.init = function(){
		view.show(nameSpace,showDetail);
    	view.add(nameSpace);
    	view.mod(nameSpace);
    	view.del(nameSpace);
    	view.next(nameSpace,showDetail);
    	view.prev(nameSpace,showDetail);
    	view.back(nameSpace);
	}
})