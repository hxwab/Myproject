/*
 * @author:Liujia
 * @description: 分组查看
 * @date:2015/11/12
 */
define(function(require, exports, module){
	var list = require("list");
	var view = require("view");
	var nameSpace = "reviewGroup";
	var showDetail = function(results){
		if (results.errorInfo == null || results.errorInfo == "") {
			 $("#entityId").val(results.entityId);
			 $("#groupId").val(results.entityId);
			 $("#view_container").hide();
			 $("#view_container").html(TrimPath.processDOMTemplate("view_template",results))
		     $("#view_container").show();
		} else {
			alert(results.errorInfo);
		}
		  $.isLoading("hide");//关闭加载信息
	}
	
	var showDetail_next_prev = function(results){
		if (results.errorInfo == null || results.errorInfo == "") {
			 $("#entityId").val(results.entityId);
			 $("#groupId").val(results.entityId);
			 $("#view_container").hide();
			 $("#view_container").html(TrimPath.processDOMTemplate("view_template",results))
		     $("#view_container").show();
		} else {
			alert(results.errorInfo);
		}
		  $.isLoading("hide");//关闭加载信息
		  
		  list.getData();
		  getExpertList();
	}
	
	function getProductList(){//显示成果列表
		var nameSpace = "groupProducts";
		list.pageShow({
			"nameSpace":nameSpace,
			"sortColumnId":["sortcolumn0","sortcolumn1","sortcolumn2","sortcolumn3","sortcolumn4","sortcolumn5"],
			"sortColumnValue":{"sortcolumn0":0,"sortcolumn1":1,"sortcolumn2":2,"sortcolumn3":3,"sortcolumn4":4,"sortcolumn5":5}
		});
	}
	
	function getExpertList(){// 显示专家列表
		$.ajax({
			url:"assignExpert/getAssignedExpert.action",
			data: {
				groupId: $("#entityId").val() ,
				reviewType: 0
			},
			dataType:"json",
			type:"post",
			success: function(data){
				$("#expert").html(TrimPath.processDOMTemplate("expert_list_template",data))
			}
		});
		
	}
	
	function initTabs(){//初始化标签
		getProductList();
		getExpertList();
		$("#tab_container").show();
	}
	$("body").on("click", ".check-all", function(){//绑定全选操作
		var value = this.checked;
		$(this).parents("table").find("input[name='entityId']").each(function(){
			this.checked = value;
		});
	})
	$("body").on("click", ".add-expert", function(){//添加已选专家
		dialog({
			title: '添加初评专家',
			url: "assignExpert/toAssignExpertList.action",
			data: {
				groupId: $("#entityId").val(),
				type:0
			},
			onclose: function(){
				getExpertList();// 刷新已选专家
			}
		}).showModal();
	})
	$("body").on("click", ".delete-expert", function(){//删除已选专家
		var $experts = $("#expert input:checked[name='entityId']");
		if(!$experts.length){
			alert("请选择专家");
			return;
		}
		var expertId = [];
		 $experts.each(function(){
			 expertId.push($(this).val())
		 });
		$.ajax({
			url: "assignExpert/deleteExpert.action",
			traditional: true,
			data: {
				expertId:expertId,
				reviewType:0
			},
			dataType:"json",
			type:"post",
			success: function(data){
				if(data.tag == 1) {
					getExpertList();
				} else {
					alert("删除失败!");
				}
			}
		});
	})
	
	exports.init = function(){
		view.show(nameSpace,showDetail, "0");
    	view.next(nameSpace,showDetail_next_prev);
    	view.prev(nameSpace,showDetail_next_prev);
    	$("#view_back").click(function(){
    		location.href = nameSpace + "/toFirstReviewList.action";
    	});
    	initTabs()
	}
});