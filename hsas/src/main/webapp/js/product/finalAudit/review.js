/*
 * @author:Liujia
 * @description: 复评
 * @date:2015/11/05
 */
define(function(require, exports, module){
	require("form");
	require("validation");
	require("validationInit");
	var nameSpace = "product";
	var data = {
			secondReview:[{
				expert:"张三",
				opinion:"一等奖！！不解释！！",
				time:"2015-11-05 15:18"
			}]
	}
	var showDetail = function(data){
			$("#view_container").hide();
			$("#view_container").html(TrimPath.processDOMTemplate("view_template", data));
			$("#view_container").show();
	}
	
	$("body").on("click", ".add-second-review", function(){
		dialog({
			title: '添加审核意见',
			content: $("#add_second_review_template").clone().show()[0],
			cancelValue: '取消',
			okValue: '确定',
		    cancel: function () {},
			width: 400,
			ok: function() {
				if($(this.node).find("textarea[name='opinion']").val() == ""){
					alert("请输入审核意见！");
					return false;
				}
				$.ajax({});// TODO
			},
			onclose: function(){
			}
		}).showModal();
	})
	$("body").on("click", ".modify-second-review", function(){
		dialog({
			title: '修改审核意见',
			content: $("#add_second_review_template").clone().show()[0],
			cancelValue: '取消',
			okValue: '确定',
		    cancel: function () {},
			width: 400,
			ok: function() {
				if($(this.node).find("textarea[name='opinion']").val() == ""){
					alert("请输入审核意见！");
					return false;
				}
				$.ajax({});// TODO
			},
			onclose: function(){
			}
		}).showModal();
	})
	$("body").on("click", ".delete-second-review", function(){
		if(confirm("确定删除审核意见？")){
			//TODO
		}
	})
	$("body").on("click", ".submit-level", function(){
		if(!$(this).parents("table").find("input[name='level']:checked").val()){
			alert("请选择");
			return ;
			// TODO
		}
		$(this).text("修改").removeClass("btn-success").addClass("btn-default");
		
	})
	
	exports.init = function(){
		showDetail(data);
		
	}
});