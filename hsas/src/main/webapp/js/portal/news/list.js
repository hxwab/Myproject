/**
 * @author liujia
 * @date 2015/8/7
 * @descrioption 新闻列表模块
 */
define(function(require, exports, module){
	var list = require("list");
	var nameSpace = "portal/news";
	require("form");
	//初始化函数，初始化列表
	exports.init = function(){
		switch(parseUrl(window.location.href).type) {
			case "news":
				$(".breadcrumb .active").text("社科动态");
				break;
			case "notice":
				$(".breadcrumb .active").text("通知公告");
				break;
			case "status":
				$(".breadcrumb .active").text("政策文件");
				break;
			case "rules":
				$(".breadcrumb .active").text("注意事项");
		}
		list.pageShow({
			"nameSpace":nameSpace,
			"sortColumnId":["sortcolumn0","sortcolumn1","sortcolumn2","sortcolumn3","sortcolumn4","sortcolumn5"],
			"sortColumnValue":{"sortcolumn0":0,"sortcolumn1":1,"sortcolumn2":2,"sortcolumn3":3,"sortcolumn4":4,"sortcolumn5":5},
			"dealWith":function(){
				//重新绑定查看事件
				$("body").off("click",".link1");
				$("body").on("click",".link1", function(){// 点击进入查看页面
		            //var url = basePath + nameSpace + "/toView.action?entityId=" + this.id + "&pageNumber=" + $("#list_pagenumber").val();
		            var url = "";	//不再需要页码
		            url = basePath + nameSpace + "/toView.action?entityId=" + this.id + "&type=" + parseUrl(window.location.href).type;	//不再需要页码
		            window.location.href = url;
		            return false;
		        });
			}
		});
		
	};

});

