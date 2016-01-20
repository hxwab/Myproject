/*
 * author fengzheqi
 * date 20151119
 * describle 申诉详情
 * */
define(function(require, exports, module){
	var view = require("view");
	var nameSpace = "system/mail";
	require("js/pop/popInit.js");
	var showDetail = function(results){
		if (results.errorInfo == null || results.errorInfo == "") {
			$("#entityId").val(results.mail.id);
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
	}
});