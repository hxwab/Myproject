/*
 * @author:Liujia
 * @description: 初评
 * @date:2015/11/02
 */
define(function(require, exports, module){
	require("form");
	require("validation");
	require("validationInit");
	require("js/pop/popInit.js");
	var view = require("view");
	var nameSpace = "product/firstReviewAudit";
	
	var showfirstReview = function(results){
		if (results.errorInfo == null || results.errorInfo == "") {
		     $("#firstReviewAudit").html(TrimPath.processDOMTemplate("firstReviewAudit_template",results))
		} else {
			alert(results.errorInfo);
		}
		  $.isLoading("hide");//关闭加载信息
	}
	var showDetail = function(results){
     if (results.errorInfo == null || results.errorInfo == "") {
			 $("#product_info").hide();
		     $("#product_info").html(TrimPath.processDOMTemplate("product_info_template",results))
		     $("#product_info").show();
		     $("#product_detail_info").html(TrimPath.processDOMTemplate("product_detail_info_template",results));
		} else {
			alert(results.errorInfo);
		}
		  $.ajax({
			  url: nameSpace + "/view.action",
			  data:{
				  entityId:$("#entityId").val()
			  },
			  type: "POST",
			  dataType: "json",
			  success: showfirstReview
		  });
	}
	
	
	exports.init = function(){
		view.show("productView",showDetail);
		view.next("productView",showDetail);
    	view.prev("productView",showDetail);
    	view.back("product/firstReviewAudit");
	}
});