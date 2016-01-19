/*
 * @author:fengzheqi
 * @description: 终审详情
 * @date:2015/09/01
 */
define(function(require, exports, module){
	require("viewer");
	require("fancyBox");
	require("js/pop/popInit.js");
	var view = require("view");
	var nameSpace = "product/finalAudit";

	var showDetail = function(results){
		if (results.errorInfo == null || results.errorInfo == "") {
			$("#entityId").val(results.product.id);
	        $("#view_container").hide();
	        $("#view_container").html(TrimPath.processDOMTemplate("view_template",results))
	        $("#view_container").show();
		} else {
			alert(results.errorInfo);
		}
			$.isLoading("hide");//关闭加载信息
	}
	
	exports.init = function(){
		view.show(nameSpace,showDetail);
    	view.add(nameSpace);
    	view.mod(nameSpace);
    	view.del(nameSpace);
    	view.next(nameSpace,showDetail);
    	view.prev(nameSpace,showDetail);
    	view.back(nameSpace);
    	
    	$('.fancybox').fancybox();
	}
});