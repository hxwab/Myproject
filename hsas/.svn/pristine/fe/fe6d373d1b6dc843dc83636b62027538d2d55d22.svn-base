<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>湖北省社会科学优秀成果奖申报评审系统</title>
        <%@ include file="/jsp/base.jsp"%>
    </head>
    <body>
    	<!-- 页头 -->
    	<%@ include file="/jsp/innerNav.jsp"%>
    
    	<!-- 页面主体 -->
		<div class="row mySlide">
			<div class="col-xs-9 slide-pic">
				<div class="row">
					<!--  
					<div class="col-xs-6 ">
						<div class="myPanel myPanel-notice">
							<div class="panel-heading">
								<h3 class="panel-title"><i class="fa fa-book fa-2"></i>&nbsp;社科动态
									<a href="portal/news/toList.action?type=news&update=1" title='更多' class="pull-right">更多>></a>
								</h3>
							</div>
							<div class="panel-body myNews">
							</div>
						</div>
					</div>
					-->
					<div class="col-xs-12">
						<div class="myPanel myPanel-notice">
							<div class="panel-heading">
								<h3 class="panel-title"><i class="fa fa-newspaper-o fa-2"></i>&nbsp;通知通告
									<a href="portal/news/toList.action?type=notice&update=1" title='更多' class="pull-right">更多>></a>
								</h3>
							</div>
							<div class="panel-body myNotice">
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-xs-3 myIcon quick-channels">
				<h4 class="text-center login">登陆入口</h4>
				<form class="form-signin" role="form" action="login" method="post">
					<div class="form-group">
						<div class="input-group">
							<span class="input-group-addon">
								<span class="glyphicon glyphicon-user"></span>
							</span>
							<input type="text" class="form-control" name="j_username" placeholder="用户名" required autofocus>
						</div>
					</div>
					<div class="form-group">
						<div class="input-group">
							<span class="input-group-addon">
								<span class="glyphicon glyphicon-lock"></span>
							</span>
							<input type="password" class="form-control" name="j_password" placeholder="密码" required>
						</div>
					</div>
					<div class="myBtn">
						<button class="btn  btn-success btn-submit" type="submit" data-loading-text="登录中...">登录</button>
						<button class="btn  btn-primary" type="reset">重置</button>
					</div>
					<p id="errorInfo" class="error-info">&nbsp;</p>
					<div class="signup">
					<p>
						没有账号？ &nbsp;&nbsp;
						<a href="register/toRegister.action" class="btn btn-primary">立即注册</a> &nbsp;&nbsp;
						<a href="register/toForgetPassword.action">忘记密码</a>
					</p>
					</div>
				</form>
		    </div>
		</div>
		<div class="row">
			<div class="col-xs-9 slide-pic ">
				<div class="row">
					<div class="col-xs-12 ">
						<div class="myPanel myPanel-notice">
							<div class="panel-heading">
								<h3 class="panel-title"><i class="fa fa-file-text fa-2"></i>&nbsp;政策公告
									<a href="portal/news/toList.action?type=status&update=1" title='更多' class="pull-right">更多>></a>
								</h3>
							</div>
							<div class="panel-body myStatus">
							</div>
						</div>
					</div>
					<!-- 
					<div class=" col-xs-6 ">
						<div class = "myPanel myPanel-news">
							<div class="panel-heading">
								<h3 class="panel-title"><i class="fa fa-question-circle fa-2"></i>&nbsp;注意事项
									<a href="portal/news/toList.action?type=rules&update=1" title='更多' class="pull-right">更多>></a>
								</h3>
							</div>
							<div class="panel-body myRules">
							</div>
						</div>
					</div>
					 -->
				</div>
			</div>
			<div class="col-xs-3 quick-channels" style="margin-top:10px">
				<div class="row">
					<div class="col-xs-12 list-group no-padding-right">
						<h3 class="panel-title-sidebar">快速通道</h3>
						<a id="apply_interface" class="list-group-item text-center" href="javascript:void(0)">申报成果评奖入口</a>
						<a class="list-group-item text-center" href="solicitPapers/toAdd.action?type=1" >论坛投稿入口</a>
						<a class="list-group-item text-center" href="solicitPapers/toAdd.action?type=2" >课题投稿入口</a>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-12 list-group no-padding-right">
						<h3 class="panel-title-sidebar">友情链接</h3>
						<a href="http://www.hbskw.com/index.jsp" class="list-group-item text-center">湖北省社科联</a>
						<a href="http://www.hust.edu.cn/" class="list-group-item text-center">华中科技大学</a>
						<a href="http://www.whu.edu.cn/" class="list-group-item text-center">武汉大学</a>
					</div>
				</div>
			</div>
		</div>
			
		<!-- 页脚 -->
		<div class="row">
		<%@ include file="/jsp/footer.jsp"%>
		<input id="login_error" type="hidden" value="${param.error}" />
	</body>
	<script>
		var json_news =${applicationScope.news};
		var json_notice = ${applicationScope.notice};
		var json_status = ${applicationScope.status};
		var json_rules = ${applicationScope.rules};
	</script>
	<script>
	    seajs.use("js/index.js", function(index) {
	         index.init(); 
	    });
	</script>
</html>