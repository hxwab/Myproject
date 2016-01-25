<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
    <head>
        <title>湖北省社会科学优秀成果奖申报评审系统</title>
        <meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">   
        <%@ include file="/jsp/base.jsp"%>
    </head>
    
    <body>
   		<%@ include file="/jsp/innerNav.jsp"%>
		<div class="row">
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
				<h4 class="text-center login">欢迎您</h4>
				<div class=" signup form-signin">
					<div class="form-group">
						<label>用户名：</label>
						<label><a href="personInfo/toView.action" style = "color:#449d44"><%= request.getSession().getAttribute("username")%></a></label>
					</div>
					<div class="form-group">
						<label>姓名：<span style = "color:#449d44"><%= request.getSession().getAttribute("name")%></span></label>
					</div>
					<div class="form-group">
						<label>账号角色：<span style = "color:#449d44"><%= request.getSession().getAttribute("type")%></span></label>
					</div>
					<div class="form-group">
						<label>账号状态：<span style = "color:#449d44"><%= request.getSession().getAttribute("status")%></span></label>
					</div>
					<div class="form-group myBtn">
						<a href="personInfo/toModifyPassword.action" class="btn btn-default ">修改密码</a>
						<a href="logout" class="btn btn-success ">退出</a>
					</div>
					<%--<div class="signup myBtn">
						<p><a href="product/application/toAdd.action" style="font-size:16px;font-weight:bold;">申报成果评奖入口 >></a></p>
					</div>
				--%></div>
		    </div>
		</div>
		<div class="row">
			<div class="col-xs-9 slide-pic">
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
						<a class="list-group-item text-center" href="product/application/toAdd.action">申报成果评奖入口</a>
						<a class="list-group-item text-center" href="solicitPapers/toAdd.action?flag=1" >论坛投稿入口</a>
						<a class="list-group-item text-center" href="solicitPapers/toAdd.action?flag=2" >课题投稿入口</a>
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
		<div class="row">
		<%@ include file="/jsp/footer.jsp"%>
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
	</body>
</html>