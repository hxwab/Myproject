/**
 * @author fengzheqi
 * @date 2015/12/7
 * @descrioption 投稿管理模块
 */
define(function(require, exports, module){
	var list = require("list");
	var nameSpace = "solicitPapers";
	
	//初始化函数，初始化列表
	exports.init = function(){
		//初始化列表
		list.pageShow({
			"nameSpace":nameSpace,
			"sortColumnId":["sortcolumn0","sortcolumn1","sortcolumn2","sortcolumn3","sortcolumn4"],
			"sortColumnValue":{"sortcolumn0":0,"sortcolumn1":1,"sortcolumn2":2,"sortcolumn3":3,"sortcolumn4":4}
		});
	};
});

