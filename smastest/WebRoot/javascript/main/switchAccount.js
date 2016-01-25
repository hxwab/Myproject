$(function(){
	$("#confirm_btn").live("click", function(){
		$.ajax({
			url: "user/checkAccount.action",
			type: "post",
			data: {username: $("#username").val()},
			success: function(result){
				if (result.errorInfo == null || result.errorInfo == "") {
					/*thisPopLayer.callBack({
						"accountId" : accountId,
						"username" : username
					});*/
					thisPopLayer.callBack();
					thisPopLayer.destroy();
				} else {
					$("#error").html(result.errorInfo);
					return false;
				}
			}
		})  
	})
})