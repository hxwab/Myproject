/*
 *@author fengzheqi
 *@date 2015/08/31
 *@description 登录界面 
 */
define(function(require, exports, require){
	var initBlock = function (obj,length,blockName) {
		for(var item in obj) {
			if(obj.hasOwnProperty(item))
				obj = obj[item];
		}
		var genHtml = "<ul class='list-unstyled'>";
		if(obj,length,blockName) {
			$.each(obj,function (i) {
				genHtml += "<li><a href='" + "portal/news/toView.action?entityId=" + obj[i][0] + "&type=" + blockName +  "'>" + (obj[i][1].length > length ? (obj[i][1].substr(0,length)
						+ "..."):(obj[i][1])) + (obj[i][3] ? "&nbsp;<span class='label label-warning label-twinkling'>新 !</span>" : "") + "</li>";
			});
		}
		
		genHtml += "</ul>";
		return genHtml;
	};
	var showLoginError = function(){
		var errorInfo = [
			'*用户名为空',
			'*密码为空',
			'*验证码为空',
			'*验证码错误',
			'*用户名或者密码错误',
			'*账号未启用'
		];
		var errorInfoIndex = parseInt($("#login_error").val())-1;
		$("#errorInfo").html(errorInfo[errorInfoIndex]);
	};
	exports.init = function(){
		$(".myNews").html(initBlock(json_news,22,"news"));
		$(".myNotice").html(initBlock(json_notice,22,"notice"));
		$(".myStatus").html(initBlock(json_status,22,"status"));
		$(".myRules").html(initBlock(json_rules,22,"rules"));
		
		showLoginError();
	}
})