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
	var nameSpace = "product/firstReview";
	var loadReviewStanard = function(){
			var productType = $("#productType").val();
	        $("#form_first_review .wrapper").hide();
	        $("#form_first_review .wrapper").html(TrimPath.processDOMTemplate("view_template_" + productType,{}))
	        $("#form_first_review .wrapper").show();
	        $("#form_first_review").show();
	        $("#form_first_review").validationEngine({ // 校验
	    		scroll: false,
	    		isOverflown:true,
	    		validateNonVisibleFields: true,
	    		promptPosition: 'centerRight',
	    		addPromptClass: 'formError-noArrow formError-text'
	    	});
	}
	var showProductInfo = function(results){
		if (results.errorInfo == null || results.errorInfo == "") {
			$("#product_info").hide();
		    $("#product_info").html(TrimPath.processDOMTemplate("product_info_template",results))
		    $("#product_info").show();
		    $("#product_detail_info").html(TrimPath.processDOMTemplate("product_detail_info_template",results));
		} else {
			alert(results.errorInfo);
		}
		  $.isLoading("hide");//关闭加载信息
		
	};
	
	var showDetail = function(results){
     if (results.errorInfo == null || results.errorInfo == "") {
    	 	 $("#entityId").val(results.entityId);
    	 	 $("#productType").val(results.produtType);
    	 	 if($("#hasSubmit").val() !== "1" && $("#accountType").val() == "2") {
    	 		 loadReviewStanard();
    	 	 } else {
    	 		$('.tab-custom a[href="#product_detail_info"]').tab('show')
    	 	 }
		     view.show("productView", showProductInfo);
		} else {
			alert(results.errorInfo);
		}
		  $.isLoading("hide");//关闭加载信息
	}
	
	
	$("#form_first_review").ajaxForm({
		type: "post",
		dataType: "json",
		beforeSubmit: function(){
		},
		success: function(data) {
			if (data.tag == "1") {
				dialog({
					title: '提示',
					content: '添加成功!',
					okValue: '确定',
					width: 260,
					ok: function() {
						window.location.href = "product/firstReview/toList.action";
					},
					onclose: function(){
						window.location.href = "product/firstReview/toList.action";
					}
				}).showModal();
			} else {
				dialog({
					title: '提示',
					content: '添加失败!',
					width: 260,
					okValue: '确定',
					ok: function() {
						window.location.reload();
					}
				}).showModal();
			}
		}
	});
	$("#confirm").click(function(){
		if($("#form_first_review").validationEngine('validate')) {
			$("#form_first_review").submit();
		} else {
			alert("还有评分项没有完成！");
		}
	});
	exports.init = function(){
		
		view.show(nameSpace,showDetail);
		view.next(nameSpace,showDetail);
    	view.prev(nameSpace,showDetail);
    	view.back(nameSpace);
	}
});