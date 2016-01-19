/*
 * @author fengzheqi
 * @date 2015/09/22
 * @description 专家添加
 */

define(function(require, exports, module){
	require('datePicker');
	require('validation');
	require('validationInit');
	require('form');
	require("js/pop/popInit.js");
	
	var nameSpace = 'account';
	
	/*初始化dataPicker */
	$('#addAccountForm .datePicker').each(function(){
		$(this).datepicker({
			language: "zh-CN",
		    autoclose: true,
		    todayHighlight: true,
		    format: "yyyy-mm-dd",
		    defaultViewDate: {year: '1975', month: '0', day: '1'}
		}).on("hide", function(e) {
			$(e.currentTarget).validationEngine("validate");
	    });
	});
	
	//初始化表单
	$("#addAccountForm").validationEngine({
		scroll: false,
		isOverflown:true,
		validateNonVisibleFields: true,
		promptPosition: 'centerRight',
		addPromptClass: 'formError-noArrow formError-text'
	}).ajaxForm({
		type: "post",
		success: function(data) {
			var entityId = data.entityId;
			if (data.tag == "1") {
				dialog({
					title: '提示',
					content: '添加成功!',
					okValue: '确定',
					width: 260,
					ok: function() {
						window.location.href = nameSpace + "/toView.action?entityId="+ entityId;
					}
				}).showModal();
			} else {
				dialog({
					title: '提示',
					content: '用户名已存在，添加失败!',
					width: 260,
					okValue: '确定',
					ok: function() {
//						window.location.reload();
					}
				}).showModal();
			}
		}
	});
	
	//选择角色
	$('body').on('click', '#chooseRole', function(){
		top.dialog({
			title: '分配角色',
			url: nameSpace + "/toAssignRole.action?assignRoleIds="+$('#entityId').val(),
			width:700,
			onclose: function () {
				var role = this.returnValue.role;
				if(role !== undefined) {
					$("#roleIds").val(role);
					/*$("#modifyRoleForm").submit();*/
				}
			}
		}).showModal();
		return false;
	})
	
})