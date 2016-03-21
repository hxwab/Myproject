/**
 * @author liujia
 * @date 2015/10/12
 * @descrioption 机构选择模块
 */
define(function(require, exports, module){
	
	require("form");
	var list = require("list");
	var nameSpace = "/selectAgency";
	require("form");

	$("body").on("click", "input[name='entityId']", function(){
		$(".selected").empty().append(generateLink($(this).attr("alt"), $(this).val()));
		top.dialog.getCurrent().data.tweakSize();
	});
	$("body").on("click", ".selected-link .delete-link", function(){
		$(this).parent().remove();
		$("#list_container input:checked")[0].checked = false;
		top.dialog.getCurrent().data.tweakSize();
	});
	$("body").on("click", ".cancel", function(){
		top.dialog.getCurrent().close(top.dialog.getCurrent().data.selected);
		return false;
	});
	$("body").on("click", ".confirm", function(){
		var selected = $(".selected .selected-link");
		var result = {};//定义弹出层返回值
		if(selected.length) {
			result.name = $(".label", selected).text();
			result.id = $(".label", selected).attr("id");
			top.dialog.getCurrent().close(result);
		} else {
			if(confirm("没有选择任何机构，确定返回？")){
				top.dialog.getCurrent().close(result);
			}
		}
		return false;
	});
	
	exports.init = function(){
		list.pageShow({
			"nameSpace":nameSpace,
			"sortColumnId":["sortcolumn0","sortcolumn1","sortcolumn2","sortcolumn3","sortcolumn4","sortcolumn5"],
			"sortColumnValue":{"sortcolumn0":0,"sortcolumn1":1,"sortcolumn2":2,"sortcolumn3":3,"sortcolumn4":4,"sortcolumn5":5},
			dealWith: function(){
				var dialog = top.dialog.getCurrent();
				dialog.data.tweakSize();
				if(dialog.data.selected.name){ // 处理从父页面传过来的agency
					$(".selected").empty().append(generateLink(dialog.data.selected.name, dialog.data.selected.id));
					$("input[name='entityId']").each(function(){
						if(this.value == dialog.data.selected.id)
							this.checked = true;//选中已选择的机构
					});
					top.dialog.getCurrent().data.tweakSize();
				}
			}
		});
		
		
	};
	
});

