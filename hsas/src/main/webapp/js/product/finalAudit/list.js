/**
 * @author liujia
 * @date 2015/11/05
 * @descrioption 复评列表模块
 */
define(function(require, exports, module){
	var list = require("list");
	var nameSpace = "product/finalAudit";
	require("js/pop/popInit.js");
	require("form");
	//初始化函数，初始化列表
	exports.init = function(){
		list.pageShow({
			"nameSpace":nameSpace,
			"sortColumnId":["sortcolumn0","sortcolumn1","sortcolumn2"],
			"sortColumnValue":{"sortcolumn0":0,"sortcolumn1":1,"sortcolumn2":2}
		});
	};

	$("body").on("click", ".second-review", function(){
		var entityId = $(this).attr("id");
		var type = $(this).attr("type");
		window.location.href = "jsp/product/finalAudit/audit.jsp?entityId=" + entityId + "&type=" + type;
		return ;
	})
});

