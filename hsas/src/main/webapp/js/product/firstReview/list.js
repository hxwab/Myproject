/**
 * @author liuj
 * @date 2015/10/20
 * @descrioption 初评列表模块
 */
define(function(require, exports, module){
	var list = require("list");
	var nameSpace = "product/firstReview";
	require("js/pop/popInit.js");
	require("form");
	//初始化函数，初始化列表
	exports.init = function(){
		list.pageShow({
			"nameSpace":nameSpace,
			"sortColumnId":["sortcolumn0","sortcolumn1","sortcolumn2","sortcolumn3","sortcolumn4","sortcolumn5","sortcolumn6","sortcolumn7","sortcolumn8","sortcolumn9","sortcolumn10","sortcolumn11"],
			"sortColumnValue":{"sortcolumn0":0,"sortcolumn1":1,"sortcolumn2":2,"sortcolumn3":3,"sortcolumn4":4,"sortcolumn5":5,"sortcolumn6":6,"sortcolumn7":7,"sortcolumn8":8,"sortcolumn9":9,"sortcolumn10":10,"sortcolumn11":11}
		});
	};

	$("body").on("click", ".first-review", function(){
		var entityId = $(this).attr("id");
		var type = $(this).attr("type");
		window.location.href = "product/firstReview/toView.action?entityId=" + entityId + "&productType=" + type;
		return ;
	})
	$("body").on("click", ".getListData", function(){
		window.location.href = nameSpace + "/exportListData.action";
		return ;
	})
	$("body").on("click", ".submit-all", function(){
		$.ajax({
			url: nameSpace + "/submitReview.action",
			type:"post",
			dataType:"json",
			success: function(data){
				if(data.tag == "1") {
					alert("所有成果已经提交成功！");
				} else {
					alert(data.errorInfo || "出错！");
				}
			}
		});
		return false;
	})
});

