/**
 * @author liujia
 * @date 2015/10/12
 * @descrioption 机构选择模块
 */
define(function(require, exports, module){
	
	require("form");
	var list = require("list");
	var nameSpace = "selectAgency";
	require("form");
	$("body").on("click", "input[name='entityId']", function(){
		var self = this;
		if($(this).attr("alt") == "其它") {
			var d = dialog({
			    title: '请输入您所在单位的名称：',
			    content: '<input class="form form-control form-sm" name="agencyName">',
			    width:300,
			    okValue: '确定',
			    ok: function () {
			        if($(this.node).find("input").val() == ""){
			        	alert("请输入！");
			        	return false;
			        } 
			        else {
			        	var _self = this;
			        	$.ajax({
			        		url:nameSpace + "/checkName.action",
			        		data:{agencyName:$(this.node).find("input").val()},
			        		dateType:"json",
			        		type:"post",
			        		success: function(result){
				        		if(!result.isExist) {
				        			$(".selected").empty().append(generateLink($(_self.node).find("input").val(), $(self).val()));
				        			top.dialog.getCurrent().data.tweakSize();
				        			_self.close();
				        		} else {
				        			alert("机构名已存在，请重新输入！");
				        		}
				        	}
			        	});
			        	return false;
			        }
			    },
			    cancelValue: '取消',
			    cancel: function () {
			    	self.checked = false;
			    }
			});
			d.showModal();
		} else {
			$(".selected").empty().append(generateLink($(this).attr("alt"), $(this).val()));
		}
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

