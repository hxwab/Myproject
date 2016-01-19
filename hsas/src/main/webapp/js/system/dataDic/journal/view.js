/*
 * @author: fengzheqi
 * @description: 期刊常看
 * @date:2015/11/28
 */
define(function(require, exports, module){
	var view = require("view");
	require('dhtmlxtree');
	var nameSpace = "system/dataDic/journal";
	var showDetail = function(results){
		if (results.errorInfo == null || results.errorInfo == "") {
			/*$("#entityId").val(results.role.id);*/
	        $("#view_container").hide();
	        $("#view_container").html(TrimPath.processDOMTemplate("view_template",results.role))
	        viewTree();
	        $("#view_container").show();
		} else {
			alert(results.errorInfo);
		}
		  $.isLoading("hide");//关闭加载信息
	}
	
	var viewTree = function() {// 加载树
		$("#treeparent").html("<div id='treeId'></div>");
		tree2 = new dhtmlXTreeObject("treeId", "100%", "100%", 0);
		tree2.setImagePath(basePath+"lib/dhtmlxTree_v44_std/imgs/dhxtree_skyblue/"); // 设定图片的路径
		tree2.enableCheckBoxes(0); // 1：为有复选框(做权限)  0：为没有复选框(做部门管理的时候) 
		tree2.enableItemEditor(true); //打开树的编辑功能
		tree2.enableThreeStateCheckboxes(false); // true: 假如选中了上级下级也全部选中   false:上级与下级的选择状态无关，不关联
		tree2.setOnClickHandler(null);  // 单击树的时候，做的操作  tonclick为点击的方法名 (类似于回调函数)
		tree2.loadXML("system/dataDic/journal/createJournalTree.action");
	};
	
	/**
	 * @desc 返回插入新的子节点的id
	 * @param {string} treeName - 树的名称
	 * @param {string} selectedItemId - 选中的item的id值
	 * @returns {string} - 插入新的子节点的id
	 * */
	var insertNewChild = function(treeName, selectedItemId) {
		var selectedItemId = treeName.getSelectedItemId();
		var childNum = parseInt(treeName.hasChildren(selectedItemId));
		if (childNum >= 99) {
			alert("超过节点上限，添加节点失败！");
			return;
		} else {
			return childNum>8 ? selectedItemId+""+(childNum+1) : selectedItemId+"0"+(childNum+1);
		}
	};
	
	exports.init = function(){
		view.show(nameSpace,showDetail);
    	view.add(nameSpace);
    	view.mod(nameSpace);
    	view.del(nameSpace);
    	view.next(nameSpace,showDetail);
    	view.prev(nameSpace,showDetail);
    	view.back(nameSpace);
    	
    	/**
    	 * 插入子节点
    	 */
    	$('body').on('click', '#insertNewChild', function() {
    		tree2.insertNewChild(tree2.getSelectedItemId()||0, (insertNewChild(tree2, tree2.getSelectedItemId())), '新节点');
    		return false;
    	});
    	
    	/**
    	 * 删除节点
    	 */
    	$('body').on('click', '#deleteItem', function() {
    		var id = tree2.getSelectedItemId(); 
    		if (id == 'all') {
    			alert('不能删除根节点');
    		} else if(id){
    			tree2.deleteItem(id);
    		}
    		return false;
    	});
    	
    	/**
    	 * 检索节点
    	 */
    	$('body').on('click', '#searchItem', function() {
    		tree2.findItem($('#searchInput').val());
    		return false;
    	});
    	
    	/**
    	 * 更新节点树
    	 */
    	$('body').on('click', '#updateItems',function() {
    		$.isLoading();//显示加载信息
    		tree2.stopEdit();
    		var items= [];
    		tree2.getAllSubItems('all').split(',').forEach(function(currentValue){
    			var item = {};
    			/*item+='{' +currentValue + ':' + '' + tree2.getItemText(currentValue) + '}';*/
    			/*item[currentValue] = tree2.getItemText(currentValue);*/
    			item.code = currentValue;
    			item.name = tree2.getItemText(currentValue);
    			items.push(item);
    		});
    		$.ajax({
    			url: nameSpace + "/update.action",
    			type: 'post',
    			data: {
    				'node': JSON.stringify(items)
    			},
    			success: function(data) {
    				if (data.tag ==1) {
    					dialog({
    						title: '提示',
    						content: '更新成功!',
    						okValue: '确定',
    						width: 260,
    						ok: function() {
    							view.show(nameSpace,showDetail);
    						}
    					}).showModal();
    				} else {
    					dialog({
    						title: '提示',
    						content: '更新失败,请稍后再试!',
    						width: 260,
    						okValue: '确定',
    						ok: function() {
    							window.location.reload();
    						}
    					}).showModal();
    				}
    			},
    			complete: function() {
    				$.isLoading("hide");//关闭加载信息
    			}
    		})
    	})
	}
});