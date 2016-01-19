/**
 * @author:Liujia
 * @description:账号常看
 * @date:2015/08/28
 */
define(function(require, exports, module){
	var view = require("view");
	var nameSpace = "portal/news";
	var showDetail = function(results){
		if (results.errorInfo == null || results.errorInfo == "") {
			$("#entityId").val(results.article.id);
	        $("#view_container").hide();
	        $("#view_container").html(TrimPath.processDOMTemplate("view_template",results.article))
	        $("#view_container").show();
		} else {
			alert(results.errorInfo);
		}
		  $.isLoading("hide");//关闭加载信息
	}
	
	
	exports.init = function(){
		switch(parseUrl(window.location.href).type) {
			case "news":
				$(".breadcrumb .active").prev().find("a").text("社科动态").attr("href","portal/news/toList.action?type=news&update=1");
				break;
			case "notice":
				$(".breadcrumb .active").prev().find("a").text("通知公告").attr("href","portal/news/toList.action?type=notice&update=1");
				break;
			case "status":
				$(".breadcrumb .active").prev().find("a").text("政策文件").attr("href","portal/news/toList.action?type=status&update=1");
				break;
			case "rules":
				$(".breadcrumb .active").prev().find("a").text("注意事项").attr("href","portal/news/toList.action?type=rules&update=1");
		}
		view.show(nameSpace,showDetail);
    	view.add(nameSpace);
    	view.del(nameSpace);
    	view.next(nameSpace,showDetail);
    	view.prev(nameSpace,showDetail);

    	/**
    	 * 返回列表
    	 */
		$("body").on("click", "#view_back", function() {
			window.location.href = basePath + nameSpace + "/toList.action?" + "update=" + 1 + "&type=" + parseUrl(window.location.href).type;
			return false;
		});
		/**
		 * 修改按钮绑定事件，点击修改当前查看内容
		 */
		$("body").on("click","#view_mod", function() {
			window.location.href = basePath + nameSpace + "/toModify.action?entityId=" + $("#entityId").val()+ "&type=" + parseUrl(window.location.href).type;;
			return false;
		});
		
	}
});