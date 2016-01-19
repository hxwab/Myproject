/**
 * @author fengzheqi
 * @date 2015/11/02
 * @descrioption 权限列表模块
 */
define(function(require, exports, module){
	var list = require("list");
	var nameSpace = "product/appeal";
	
	//初始化函数，初始化列表
	exports.init = function(){
		//初始化列表
		list.pageShow({
			"nameSpace":nameSpace,
			"sortColumnId":["sortcolumn0","sortcolumn1","sortcolumn2"],
			"sortColumnValue":{"sortcolumn0":0,"sortcolumn1":1,"sortcolumn2":2}
		});
	};
});

