/**
 * @author liujia
 * @date 2015/11/03
 * @descrioption 专家分组模块
 */
define(function(require, exports, module){
	
	require("form");
	var list = require("list");
	var nameSpace = "assignExpert";
	require("form");
	var isInit = false;
	var selectExperts =[];// 定义已选专家集合
	var poplayer = top.dialog.getCurrent();
	function appendToSelected(data){// 将已选专添加进已选列表
		$("#selected_expert_container").hide();
        $("#selected_expert_container").html(TrimPath.processDOMTemplate("selected-experts",selectExperts));
        $("#selected_expert_container").show();
	}
	
	$("body").on("click", ".confirm",function(){
		var expertId = [];
		for(var i = 0; i < selectExperts.length; i ++) {
			expertId.push(selectExperts[i].id);
		}
		$.ajax({
			url:nameSpace + "/addExpert.action",
			traditional: true,
			data: {
				reviewType:poplayer.data.type,
				expertId:expertId
			},
			dataType:"json",
			type:"post",
			success: function(data){
//				if(!data.errorInfo) {
//					alert("添加成功!");
//				} else {
//					alert("添加失败!");
//				}
				poplayer.close({experts:selectExperts}).remove();
			}
		});
		
	});
	$(".cancel").click(function(){
		poplayer.close().remove();
	});

	function updateSelected(experts){ // 更新已选专家
		$(".selected").empty();
		for(var i = 0; i < experts.length; i ++){
			$(".selected").append(generateLink(experts[i].name, experts[i].id));
		}
		poplayer.data.tweakSize();// 调整弹出层高度
	}
	
	$("body").on("click", "input[name='entityIds']", function(){
		if(this.checked){
			selectExperts.push({id:$(this).val(), name: $(this).attr("data")});
		} else {
			for(var i = 0 ; i < selectExperts.length; i ++) {
				if(selectExperts[i].id == $(this).val())
					selectExperts.splice(i, 1);
			}
		}
		updateSelected(selectExperts);
	});
	$("body").on("click", ".selected-link .delete-link", function(){
		var id = $(this).siblings("span").attr("id");
		for(var i = 0 ; i < selectExperts.length; i ++) {
			if(selectExperts[i].id == id)
				selectExperts.splice(i, 1);
		}
		$("input:checkbox[value='" + id + "']")[0].checked = false;
		updateSelected(selectExperts);
	});
	exports.init = function(){
		$("#search input[name='type']").val(poplayer.data.type );
		$("#search input[name='groupId']").val(poplayer.data.groupId );
		list.pageShow({
			"nameSpace":nameSpace,
			"sortColumnId":["sortcolumn0","sortcolumn1","sortcolumn2","sortcolumn3","sortcolumn4","sortcolumn5"],
			"sortColumnValue":{"sortcolumn0":0,"sortcolumn1":1,"sortcolumn2":2,"sortcolumn3":3,"sortcolumn4":4,"sortcolumn5":5},
			dealWith: function(){
				 $("body").off("click","#check").on("click","#check", function() {// 全选
			           checkAll(this.checked, "entityIds");
			           selectExperts.length = 0;
			           $("input:checked[name='entityIds']").each(function(){
			        	   selectExperts.push({id:$(this).val(), name: $(this).attr("data")});
			           });
			           updateSelected(selectExperts);
			     });
				var dialog = top.dialog.getCurrent();
				dialog.data.tweakSize();
				dialog.data.InitSize();
				
			}
		});
		
	};
	
});

