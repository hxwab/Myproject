/*
 * @author:Liujia
 * @description:账号常看
 * @date:2015/08/28
 */
define(function(require, exports, module){
	var view = require("view");
	var nameSpace = "account";
	var showDetail = function(results){
		if (results.errorInfo == null || results.errorInfo == "") {
			$("#entityId").val(results.account.id);
	        $("#view_container").hide();
	        $("#view_container").html(TrimPath.processDOMTemplate("view_template",results))
	        $("#view_container").show();
		} else {
			alert(results.errorInfo);
		}
		  $.isLoading("hide");//关闭加载信息
	}
	var districtAccount = function() {
		/**
		 * 弹出分配角色层，由于校级账号存在两类，为了避免同时给两类校级账号分配角色，
		 * 需判断某次操作的账号为同一类账号。
		 * @param {String} entityId 待分配角色的账号ID集合
		 */
		top.dialog({
			title: '分配角色',
			url: nameSpace + "/toAssignRole.action?assignRoleIds="+$('#entityId').val(),
			width:700,
			onclose: function () {
				var role = this.returnValue.role;
				if(role !== undefined) {
					$("#roleIds").val(role);
					$.ajax({
						url: "account/assignRole.action",
						type: "post",
						data: {
								entityIds: $('#entityId').val(),
								roleIds: role
							},
						dataType: "json",
						success: function() {
							view.show(nameSpace,showDetail);
						}
					})
				}
			}
		}).showModal();
		return false;
	};
	
	/**
	 * 账号所属机构弹出层
	 */
	var popAccountAgency = function() {
		dialog({
			title: '所属机构',
			content: $('#accountAgency'),
			width: 320
		}).showModal();
		return false;
	}
	exports.init = function(){
		view.show(nameSpace,showDetail);
    	view.add(nameSpace);
    	view.mod(nameSpace);
    	view.del(nameSpace);
    	view.next(nameSpace,showDetail);
    	view.prev(nameSpace,showDetail);
    	view.back(nameSpace);
    	
    	$('body').on("click","#modifyRole", districtAccount);
    	
    	$('body').on('click', '#popAccountAgency', popAccountAgency)
	}
});