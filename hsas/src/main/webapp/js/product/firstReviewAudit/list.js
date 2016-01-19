/**
 * @author liuj
 * @date 2016/1/13
 * @descrioption 初评列表模块
 */
define(function(require, exports, module){
	var list = require("list");
	var nameSpace = "product/firstReviewAudit";
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
// 初评审核
	$("body").on("click", ".firstReviewAudit", function(){
		var $selected = $("input[name='entityIds']:checked");
		if(!$selected.length) alert("请选择成果！");
		else {
			var entityIds = [];
			$selected.each(function(){
				entityIds.push(this.value);
			});
			dialog({
				title: '请选择审核结果',
			    content: $("#firstReviewAudit_tmplate").clone().show(),
			    width: 240,
			    okValue: "确定",
			    cancelValue: "取消",
			    ok: function () {
			    	var $selected = $(this.node).find("input:checked")
			    	if($selected.length) {
			    		$.ajax({
							url: nameSpace + "/addAuditResult.action",
							traditional: true,
							data:{
								entityIds: entityIds,
								auditResult: $selected.val()
							},
							type:"post",
							dataType:"json",
							success: function(data){
								if(data.tag == "1") {
									alert("审核成功！");
									list.getData(); // 刷新列表
								} else {
									alert(data.errorInfo || "出错！");
								}
							}
						});
			    		return true;
			    	} else {
			    		alert("请选择审核结果！");
			    		return false;
			    	}
			    },
			    cancel: function(){}
			}).showModal();
			
		}
		return false;
	});
// 退回
	$("body").on("click", ".back", function(){
		var $selected = $("input[name='entityIds']:checked");
		if(!$selected.length) alert("请选择成果！");
		else {
			var entityIds = [];
			$selected.each(function(){
				entityIds.push(this.value);
			});
			$.ajax({
				url: nameSpace + "/back.action",
				traditional: true,
				data:{
					entityIds: entityIds
				},
				type:"post",
				dataType:"json",
				success: function(data){
					if(data.tag == "1") {
						alert("退回成功！");
						list.getData();
					} else {
						alert(data.errorInfo || "出错！");
					}
				}
			});
		}
		return false;
	});
});

