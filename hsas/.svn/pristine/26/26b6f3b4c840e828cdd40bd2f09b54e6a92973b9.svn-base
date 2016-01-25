/*
 * @author:Liujia
 * @description: 复评
 * @date:2015/11/05
 */
define(function(require, exports, module){
	require("form");
	require("validation");
	require("validationInit");
	require("js/pop/popInit.js");
	var nameSpace = "product/secondReview";
	var view = require("view");
	var reviewResult = {};// 暂存审核意见
	var showReviewResult = function(data){
			reviewResult.level = data.rewardLevel;
			reviewResult.opinion = data.myOpinion && data.myOpinion[0];
			reviewResult.leaderAuditResult = (data.isBacked == 1 ? 1 : null);
			$("#secondReview").html(TrimPath.processDOMTemplate("second-review_template", data));
			$.isLoading("hide");//关闭加载信息
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
		}
	// 组长添加复评意见
	$("body").on("click", ".chiefReviewer-add-second-review", function(){
		var template = $("#add_second_review_template_2").clone();
		dialog({
			title: '添加评审意见',
			content: template.show()[0],
			cancelValue: '取消',
			okValue: '确定',
		    cancel: function () {},
			width: 400,
			ok: function() {
				if($(this.node).find("textarea[name='reviewOpinion']").val() == "" || $(this.node).find("select[name='reviewLevel']").val()==""){
					alert("还有未填写的项！");
					return false;
				}
				$(this.node).find("form").ajaxForm({
					url:nameSpace + "/addChiefReview.action",
					type: "post",
					success: function(data){
						if(data.tag == 1)  {
							alert("添加成功！")
						} else {
							alert("添加失败！")
						}
						view.show(nameSpace,showReviewResult);
					}
				});
				$(this.node).find("form").submit();
			},
			onclose: function(){
			}
		}).showModal();
	})
	// 组长修改复评意见
	$("body").on("click", ".reviewer-add-second-review", function(){
		dialog({
			title: '添加审核意见',
			content: $("#add_second_review_template_1").clone().show()[0],
			cancelValue: '取消',
			okValue: '确定',
			cancel: function () {},
			width: 400,
			ok: function() {
				if($(this.node).find("textarea[name='reviewOpinion']").val() == ""){
					alert("请输入审核意见！");
					return false;
				}
				$(this.node).find("form").ajaxForm({
					url:nameSpace + "/addReview.action",
					type: "post",
					success: function(data){
						if(data.tag == 1)  {
							alert("添加成功！")
						} else {
							alert("添加失败！")
						}
						view.show(nameSpace,showReviewResult);
					}
				});
				$(this.node).find("form").submit();
			},
			onclose: function(){
			}
		}).showModal();
	})
	// 组长修改复评意见
	$("body").on("click", ".chiefReviewer-modify-second-review", function(){
		var template = $("#add_second_review_template_2").clone();
		template.find("select[name='rewardLevel']").val(reviewResult.level);
		template.find("textarea[name='reviewOpinion']").val(reviewResult.opinion);
		dialog({
			title: '修改评审意见',
			content: template.show()[0],
			cancelValue: '取消',
			okValue: '确定',
		    cancel: function () {},
			width: 400,
			ok: function() {
				if($(this.node).find("textarea[name='reviewOpinion']").val() == "" || $(this.node).find("select[name='reviewLevel']").val()==""){
					alert("还有未填写的项！");
					return false;
				}
				$(this.node).find("form").ajaxForm({
					type: "post",
					url:nameSpace + "/modifyChiefReview.action",
					success: function(data){
						if(data.tag == 1)  {
							alert("修改成功！")
						} else {
							alert("修改失败！")
						}
						view.show(nameSpace,showReviewResult);
					}
				});
				$(this.node).find("form").submit();
			},
			onclose: function(){
			}
		}).showModal();
	})
	// 专家修改复评意见
	$("body").on("click", ".reviewer-modify-second-review", function(){
		var template = $("#add_second_review_template_1").clone();
		template.find("textarea[name='reviewOpinion']").val(reviewResult.opinion);
		dialog({
			title: '添加审核意见',
			content: template.show()[0],
			cancelValue: '取消',
			okValue: '确定',
			cancel: function () {},
			width: 400,
			ok: function() {
				if($(this.node).find("textarea[name='reviewOpinion']").val() == ""){
					alert("请输入审核意见！");
					return false;
				}
				$(this.node).find("form").ajaxForm({
					url:nameSpace + "/modifyReview.action",
					type: "post",
					success: function(data){
						if(data.tag == 1)  {
							alert("修改成功！")
						} else {
							alert("修改失败！")
						}
						view.show(nameSpace,showReviewResult);
					}
				});
				$(this.node).find("form").submit();
			},
			onclose: function(){
			}
		}).showModal();
	
	})
	// 组长审核
	$("body").on("click", ".review", function(){
		var template = $("#review_template").clone();
		if (reviewResult.leaderAuditResult)
			template.find("input[value='" + reviewResult.leaderAuditResult +  "']")[0].checked = true;
		dialog({
			title: '复评审核',
			content: template[0],
			cancelValue: '取消',
			okValue: '确定',
		    cancel: function () {},
			width: 400,
			ok: function() {
				if($(this.node).find("input[name='leaderAuditResult']:checked").length < 1){
					alert("请选择！");
					return false;
				}
				$(this.node).find("form").ajaxForm({
					type: "post",
					success: function(data){
						if(data.tag == 1)  {
							alert("审核成功！")
						} else {
							alert("审核失败！")
						}
						view.show(nameSpace,showReviewResult);
					}
				});
				$(this.node).find("form").submit();
			},
			onclose: function(){
			}
		}).showModal();
	})
	exports.init = function(){
		view.show("productView",showProductInfo);
		view.show(nameSpace,showReviewResult);
		view.next(nameSpace,showReviewResult);
		view.prev(nameSpace,showReviewResult);
		view.back(nameSpace);
//		showReviewResult(data);

	}
});
