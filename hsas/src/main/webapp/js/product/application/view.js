/*
 * author fengzheqi
 * date 20151119
 * describle 申报详情
 * */
define(function(require, exports, module){
	require("viewer");
	require("fancyBox");
	require("js/pop/popInit.js");
	var view = require("view");
	var nameSpace = "product/application";

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
    	view.mod(nameSpace);
    	view.del(nameSpace);
    	   
    	$('.fancybox').fancybox();
    	 
    	$('body').on('click', '#back', function(){
    		window.location.href= "personInfo/toView.action"
    	});
	}
});