/**
 * @author liujia
 * @date 2015/11/03
 * @descrioption 专家分配成果模块
 */
define(function(require, exports, module){
	
	require("form");
	var list = require("list");
	var nameSpace = "chiefReviewProduct";
	require("form");
	var isInit = false;
	var selectProducts =[];// 定义已选专家集合
	var selectedProductsFromServer = []; // 定义从服务器获取的已选专家列表
	var selecteBackup = [];//从服务器获取的已选专家列表备份
	var poplayer = top.dialog.getCurrent();
	var isInit = false;
	
	$("body").on("click", ".confirm",function(){
		var productId = [];
		for(var i = 0; i < selectProducts.length; i ++) {
			productId.push(selectProducts[i].id);
		}
		for(var i = 0; i < selectedProductsFromServer.length; i ++) {
			productId.push(selectedProductsFromServer[i].id);
		}
		$.ajax({
			url:nameSpace + "/assignChiefProducts.action",
			traditional: true,
			data: {
				expertId:poplayer.data.expertId,
				productId:productId
			},
			dataType:"json",
			type:"post",
			success: function(data){
				if(!data.errorInfo) {
					alert("添加成功!");
				} else {
					alert("添加失败!");
				}
				poplayer.close({experts:selectProducts}).remove();
			}
		});
		
	});
	$(".cancel").click(function(){
		var productId = [];
		for(var i = 0; i < selecteBackup.length; i ++) {
			productId.push(selecteBackup[i].id);
		}
		$.ajax({
			url:nameSpace + "/assignChiefProducts.action",
			traditional: true,
			data: {
				expertId:poplayer.data.expertId,
				productId:productId
			},
			dataType:"json",
			type:"post",
			success: function(data){
				poplayer.close().remove();
			}
		});
	});

	function updateSelected(experts, expertFromServer){ // 更新已选专家
		$(".selected").empty();
		for(var i = 0; i < experts.length; i ++){
			$(".selected").append(generateLink(experts[i].name, experts[i].id, 0));
		}
		for(var i = 0; i < expertFromServer.length; i ++){
			$(".selected").append(generateLink(expertFromServer[i].name, expertFromServer[i].id, 1));
		}
		poplayer.data.tweakSize();// 调整弹出层高度
	}
	
	$("body").on("click", "input[name='entityIds']", function(){
		if(this.checked){
			selectProducts.push({id:$(this).val(), name: $(this).attr("data")});
		} else {
			for(var i = 0 ; i < selectProducts.length; i ++) {
				if(selectProducts[i].id == $(this).val())
					selectProducts.splice(i, 1);
			}
		}
		updateSelected(selectProducts, selectedProductsFromServer);
	});
	
	$("body").on("click", ".selected-link .delete-link", function(){ 
		var type = $(this).parents(".selected-link").attr("type");
		var id = $(this).siblings("span").attr("id");
		if(type == "1") {
			for(var i = 0 ; i < selectedProductsFromServer.length; i ++) {
				if(selectedProductsFromServer[i].id == id)
					selectedProductsFromServer.splice(i, 1);
			}

			var productId = [];
			for(var i = 0; i < selectProducts.length; i ++) {
				productId.push(selectProducts[i].id);
			}
			for(var i = 0; i < selectedProductsFromServer.length; i ++) {
				productId.push(selectedProductsFromServer[i].id);
			}
			$.ajax({
				url:nameSpace + "/assignChiefProducts.action",
				traditional: true,
				data: {
					expertId:poplayer.data.expertId,
					productId:productId
				},
				dataType:"json",
				type:"post",
				success: function(){
					list.getData();
				}
			});
			
		
			
		} else {
			for(var i = 0 ; i < selectProducts.length; i ++) {
				if(selectProducts[i].id == id)
					selectProducts.splice(i, 1);
			}
			$("input:checkbox[value='" + id + "']")[0].checked = false;
		}
		updateSelected(selectProducts, selectedProductsFromServer);
	});
	
	exports.init = function(){
		$("#search input[name='groupId']").val(poplayer.data.groupId );
		list.pageShow({
			"nameSpace":nameSpace,
			"sortColumnId":["sortcolumn0","sortcolumn1","sortcolumn2","sortcolumn3","sortcolumn4","sortcolumn5"],
			"sortColumnValue":{"sortcolumn0":0,"sortcolumn1":1,"sortcolumn2":2,"sortcolumn3":3,"sortcolumn4":4,"sortcolumn5":5},
			dealWith: function(){
				if(!isInit) {
					$("body").off("click","#check").on("click","#check", function() {// 全选
						checkAll(this.checked, "entityIds");
						selectProducts.length = 0;
						$("input:checked[name='entityIds']").each(function(){
							selectProducts.push({id:$(this).val(), name: $(this).attr("data")});
						});
						updateSelected(selectProducts, selectedProductsFromServer);
					});
				}
				var dialog = top.dialog.getCurrent();
				dialog.data.tweakSize();
				dialog.data.InitSize();
				isInit = true;
			}
		});
		$.getJSON(nameSpace + "/getChiefReviewProducts.action", {expertId: poplayer.data.expertId}, function(data){
			for(var i  = 0; i < data.chiefProducts.length; i ++ ){
				selectedProductsFromServer.push({
					id:data.chiefProducts[i][0],
					name:data.chiefProducts[i][1]
				});
				selecteBackup.push({
					id:data.chiefProducts[i][0],
					name:data.chiefProducts[i][1]
				});
			}
			updateSelected(selectProducts, selectedProductsFromServer);//显示从服务器获取的已选专家列表
		})
	};
	
});

