/*
 * author fengzheqi
 * date 20151119
 * describle 统计分析
 * */
define(function(require, exports, module){
	require('loading');
	var nameSpace = "statistic";
	var templates = [];
	var current_site = [];
	
	var showDetail = function(results){
		if (results.errorInfo == null || results.errorInfo == "") {
			var type = $("#type").val();
			$("textarea").each(function(index, element){
				templates.push($(element).attr("id"));
				current_site.push($(element).attr("data-info"));
			});
			$("#current_site").html(current_site[type-1]);
			$("#view_container").hide();
		    $("#view_container").html(TrimPath.processDOMTemplate(templates[type-1],results))
		    $("#view_container").show();
		} else {
			alert(results.errorInfo);
		}
		  $.isLoading("hide");//关闭加载信息
	}
	
	var show = function(nameSpace, showDetails){
		$.isLoading(); //显示加载信息
		$.ajax({
			url: nameSpace + "/caclResult.action",
			type: "post",
			data: "type=" + $("#type").val() + "&year=" + $("#year").val() ,//type选填
			dataType: "json",
			success: showDetails//在具体的查看页面js实现
		});
	};
	
	exports.init = function(){
		show(nameSpace,showDetail);
		
		$('body').on('click','#search', function(){
			show(nameSpace, showDetail);
		})
	}
});