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
				var isNew = ((new Date()).getTime() - (new Date(obj[i][2])).getTime())>518400000 ? false : true;
				genHtml += "<li><i class='fa fa-bookmark-o fa-2'></i>&nbsp;&nbsp;<a href='" + "portal/news/toView.action?entityId=" + obj[i][0] + "&type=" + blockName +  "'>" + (obj[i][1].length > length ? (obj[i][1].substr(0,length)
						+ "..."):(obj[i][1])) + (isNew ? "&nbsp;<span class='label label-warning label-twinkling'>新 !</span>" : "") + "<span style='float:right;margin-right:20px;'>" + obj[i][2] + "</span>" + "</li>";
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
	
	$('body').on('click', '#apply_interface', function(){
		dialog({
			title: '提示',
			content: "请先登录或注册！"
		}).showModal();
	});
	
	exports.init = function(){
		$(".myNews").html(initBlock(json_news,50,"news"));
		$(".myNotice").html(initBlock(json_notice,50,"notice"));
		$(".myStatus").html(initBlock(json_status,50,"status"));
		$(".myRules").html(initBlock(json_rules,50,"rules"));
		
		showLoginError();
	}
})