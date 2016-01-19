/**
 * @author liujia
 * @date 2015/8/14
 * @descrioption 学科分组列表
 */
define(function(require, exports, module){
	var list = require("list");
	var nameSpace = "system/dataDic/group";
	require("form");
	//初始化函数，初始化列表
	exports.init = function(){
		list.pageShow({
			"nameSpace":nameSpace,
			"sortColumnId":["sortcolumn0","sortcolumn1","sortcolumn2","sortcolumn3","sortcolumn4","sortcolumn5"],
			"sortColumnValue":{"sortcolumn0":0,"sortcolumn1":1,"sortcolumn2":2,"sortcolumn3":3,"sortcolumn4":4,"sortcolumn5":5}
		});
	};
	
	//添加学科代码
	$("body").on("click", "#list_add_displineGroup", function(){
		dialog({
			title: '添加学科分组',
			width:400,
		    content: $(".form_addDisplineGroup")[0],
		    okValue: '确定',
		    ok: function () {
		        this.title('提交中…');
		        $(".form_addDisplineGroup")[0].submit();
		    },
		    cancelValue: '取消',
		    cancel: function () {}
		}).showModal();
	})
	/*//重新分组
	$("body").on("click", "#list_reGrouping", function(){
		dialog({
			title: '重新分组',
			width:400,
		    content: $(".form_reGrouping")[0],
		    okValue: '确定',
		    ok: function () {
		        this.title('提交中…');
		        return false;
		    },
		    cancelValue: '取消',
		    cancel: function () {}
		}).showModal();
	})*/
	
	$("body").on("click", "#group_merge", function(){
		var $selected = $("input[name='entityIds']:checked");
		if(!$selected.length) {
			alert("请选择分组!")
		} else {
			if($selected.length != 2) {
				alert("合并分组时，只能选择2个分组！")
			} else {
				var $node = $("#group_merge-template").clone();
					$selected.each(function(){
						$(".src_group", $node).append($("<option>", {
							value: this.value,
							text: this.getAttribute("alt")
						}));
						
						$(".dst_group", $node).append($("<option>", {
							value: this.value,
							text: this.getAttribute("alt")
						}));
						
					});
					
					$(".src_group", $node).val($selected[0].value);
					$(".dst_group", $node).val($selected[1].value);
					dialog({
						title: '合并分组',
					    content: $node.show(),
					    okValue: '确定',
					    ok: function () {
					       if($(this.node).find(".src_group").val() == $(this.node).find(".dst_group").val()) {
					    	   alert("<span class='label label-success label-custom'>待合并的分组</span>和<span class='label label-success label-custom'>目地分组</span>不能是同一个分组！")
					    	   return false;
					       } else {
					    	   $.ajax({
					    		   url: nameSpace + "/merge.action",
					    		   data:{
					    			   srcId: $(this.node).find(".src_group").val(),
					    			   dstId: $(this.node).find(".dst_group").val()
					    		   },
					    		   dataType:"json",
					    		   type: "POST",
					    		   success: function(result){
					    			   if(result.tag == "1") {
					    				   alert("分组合并成功！");
					    				   list.getDate();
					    			   } else {
					    				   alert(result.errorInfo);
					    			   }
					    		   }
					    	   });
					    	   return true;
					       }
					    },
					    cancelValue: '取消',
					    cancel: function () {}
					}).showModal();
			}
		}
		
	})
});

