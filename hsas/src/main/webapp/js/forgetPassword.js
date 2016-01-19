/*
 *@author fengzheqi
 *@date 2016/1/15
 *@description 找回密码 
 */

define(function(require, exports, module){
	require('form');
	
	$('#forgetPasswordForm').ajaxForm({
		url:'register/forgetPassword.action',
		method: 'post',
		success: function(data) {
			if (data.tag==5) {
				alert('用户名或邮箱不存在！');
			} else if(data.tag ==1){
				alert('新的密码已发送至您的邮箱，请你登录邮箱查看！');
			} else {
				alert('操作失败，请稍后再试！')
			}
			window.location.href = basePath + "toIndex.action";
		}
	})
})
