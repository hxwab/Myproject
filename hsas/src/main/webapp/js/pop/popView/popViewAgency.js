/**
 * @author liujia
 * @description 查看人员弹层
 * @date 2015/10/14
 */
define(function(require, exports, module){
	require('loading');
	var nameSpace = "view";
	var showDetail = function(results){
		if (results.errorInfo == null || results.errorInfo == "") {
			$("#entityId").val(results.agency.id);
	        $("#view_container").hide();
	        $("#view_container").html(TrimPath.processDOMTemplate("view_template",results.agency))
	        $("#view_container").show();
	        top.dialog.getCurrent().data.tweakSize();
		} else {
			alert(results.errorInfo);
		}
		  $.isLoading("hide");//关闭加载信息
	}
	var show = function(nameSpace, showDetails, viewType){
		viewType = viewType || "";
		$.isLoading(); //显示加载信息
		$.ajax({
			url: nameSpace + "/viewAgency.action",
			type: "post",
			data: "entityId=" + $("#entityId").val() + "&viewType=" + viewType,//type选填
			dataType: "json",
			success: showDetails//在具体的查看页面js实现
		});
	};
	exports.init = function(){
		$("#entityId").val(top.dialog.getCurrent().data.entityId);
		show(nameSpace,showDetail);
	}
});