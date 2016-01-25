<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
	<div class="container">
	<div id="header">
		<!-- 头部图片 -->
		<div class="row myHeader">
			<div class="col-xs-7">
				<div class="headImg">
					<p>湖北省社会科学优秀成果奖申报评审系统</p>
				</div>
			</div>
			<sec:authorize ifAllGranted='ROLE_USER'>
			<div class="col-xs-5" style="position:relative;top:64px;">
				<div class="login" style="text-align: right;">
						欢迎您，<a href="personInfo/toView.action" style = "color:#449d44"><%= request.getSession().getAttribute("username")%>  </a> | <a href="logout" class="btn btn-success ">退出</a>
				    
				</div>
			</div>
			</sec:authorize>
		</div>
		<!-- 头部导航 --> 
		<div class="header">
			<ul class="nav nav-pills myNav">
				<li><a class="fnav" href="login/doLogin.action">网站首页</a></li>
				<li>
					<a class="fnav" href="javasript:void(0);">新闻公告</a>
					<ul class="dropdown-menu">
						<li><a href="portal/news/toList.action?type=news&update=1">社科动态</a></li>
						<li><a href="portal/news/toList.action?type=notice&update=1">通知公告</a></li>
						<li><a href="portal/news/toList.action?type=status&update=1">政策文件</a></li>
						<li><a href="portal/news/toList.action?type=rules&update=1">注意事项</a></li>
					</ul>
				</li>
				
				<!--评审管理-->
				<sec:authorize ifAllGranted='ROLE_USER, ROLE_AUDIT'>
				<li>
					<a class="fnav" href="javasript:void(0);">评审管理</a>
					<ul class="dropdown-menu">
						<sec:authorize ifAllGranted='ROLE_FIRST_AUDIT'>
						<li><a href="product/firstAudit/toList.action?update=1">初审</a></li>
						</sec:authorize>
						
						
						<li class="dropdown-submenu">
							<a href="javascript:void(0);">分组</a>
							<ul class="dropdown-menu">
								<sec:authorize ifAllGranted='ROLE_FIRST_REVIEW_EXPERT_ALLOCATE'>
								<li><a href="assignExpert/toFirstReviewList.action">初评分组</a></li>
								</sec:authorize>
								<sec:authorize ifAllGranted='ROLE_SECOND_REVIEW_EXPERT_ALLOCATE'>
								<li><a href="assignExpert/toSecondReviewList.action">复评分组</a></li>
								</sec:authorize>
							</ul>
						</li>
						
						<sec:authorize ifAllGranted='ROLE_FIRST_REVIEW'>
						<li><a href="product/firstReview/toList.action?update=1">初评</a></li>
						</sec:authorize>
						
						<sec:authorize ifAllGranted='ROLE_FIRST_REVIEW_AUDIT'>
						<li><a href="product/firstReviewAudit/toList.action?update=1">初评审核</a></li>
						</sec:authorize>
						
						
						<sec:authorize ifAllGranted='ROLE_SECOND_REVIEW'>
						<li><a href="product/secondReview/toList.action?update=1">复评</a></li>
						</sec:authorize>
						
						<sec:authorize ifAllGranted='ROLE_FINAL'>
						<li><a href="product/finalAudit/toList.action?update=1">终审</a></li>
						</sec:authorize>
						
						<sec:authorize ifAllGranted='ROLE_APPEAL'>
						<li><a href="product/appeal/toList.action?update=1">申诉</a></li>
						</sec:authorize>
					</ul>
				</li>
				</sec:authorize>
				
				<!--专家管理-->
				<sec:authorize ifAllGranted='ROLE_USER,ROLE_EXPERT'>
				<li>
					<a class="fnav" href="javasript:void(0);">专家管理</a>
					<ul class="dropdown-menu">
						<sec:authorize ifAllGranted='ROLE_EXPERT_EDITOR'>
						<li><a href="expert/toList.action?type=2&update=1">参评专家</a></li>
						</sec:authorize>
						<li><a href="expert/toList.action?type=1&update=1">推荐专家</a></li>
						<li><a href="expert/toList.action?type=0&update=1">专家库</a></li>
					</ul>
				</li>
				</sec:authorize>
				
				<!--统计分析-->
				<sec:authorize ifAllGranted='ROLE_USER'>
				<li>
					<a class="fnav" href="javasript:void(0);">统计分析</a>
					<ul class="dropdown-menu">
						<li><a href="statistic/toCaclResult.action?type=1&update=1">成果入围</a></li>
						<li><a href="statistic/toCaclResult.action?type=2&update=1">初评信息</a></li>
						<li><a href="statistic/toCaclResult.action?type=3&update=1">复评信息</a></li>
						<li><a href="statistic/toCaclResult.action?type=4&update=1">单位申报</a></li>
						<li><a href="statistic/toCaclResult.action?type=5&update=1">作者年龄</a></li>
						<li><a href="statistic/toCaclResult.action?type=6&update=1">奖项分布</a></li>
					</ul>
				</li>
				</sec:authorize>
				
				<!--个人空间-->
				<sec:authorize ifAllGranted='ROLE_USER'>
				<li>
					<a class="fnav" href="javasript:void(0);">个人空间</a>
					<ul class="dropdown-menu">
						<li><a href="personInfo/toView.action">账号信息</a></li>
						<li><a href="personInfo/toModifyPassword.action">密码修改</a></li>
					</ul>
				</li>
				</sec:authorize>
				
				<!--系统管理-->
				<sec:authorize ifAllGranted='ROLE_USER,ROLE_SYSTEM'>
				<li>
					<a class="fnav" href="javasript:void(0);">系统管理</a>
					<ul class="dropdown-menu">
						<sec:authorize ifAllGranted='ROLE_ACCOUNT'>
						<li class="dropdown-submenu">
							<a href="javascript:void(0);">账号管理</a>
							<ul class="dropdown-menu">
								<li><a href="account/toList.action?type=0&update=1">社科联账号</a></li>
								<li><a href="account/toList.action?type=1&update=1">高校账号</a></li>
								<li><a href="account/toList.action?type=2&update=1">专家账号</a></li>
								<li><a href="account/toList.action?type=3&update=1">申报人账号</a></li>
							</ul>
						</li>
						</sec:authorize>
						
						<sec:authorize ifAllGranted='ROLE_ROLE'>
						<li><a href="role/toList.action?update=1">角色管理</a></li>
						</sec:authorize>
						
						<sec:authorize ifAllGranted='ROLE_RIGHT'>
						<li><a href="right/toList.action?update=1">权限管理</a></li>
						</sec:authorize>
						
						<sec:authorize ifAllGranted='ROLE_CONTRIBUTE'>
						<li class="dropdown-submenu"><a href="javascript:void(0);">投稿管理</a>
							<ul class="dropdown-menu">
								<li><a href="solicitPapers/toList.action?type=1&update=1">论坛投稿</a></li>
								<li><a href="solicitPapers/toList.action?type=2&update=1">课题申报</a></li>
							</ul>
						</li>
						</sec:authorize>
						
						<sec:authorize ifAllGranted='ROLE_USER_DICTIONARY'>
						<li class="dropdown-submenu"><a href="javascript:void(0);">数据字典管理</a>
							<ul class="dropdown-menu">
								<li><a href="system/dataDic/reward/toList.action?update=1">奖金管理</a></li>
								<li><a href="system/dataDic/group/toList.action?update=1">分组管理</a></li>
								<li><a href="system/dataDic/agency/toList.action?update=1">机构管理</a></li>
								<li><a href="system/dataDic/journal/toView.action?update=1">期刊管理</a></li>
							</ul>
						</li>
						</sec:authorize>
						
						<sec:authorize ifAllGranted='ROLE_EMAIL'>
						<li><a href="system/mail/toList.action?update=1">邮件管理</a></li>
						</sec:authorize>
					</ul>
				</li>
				</sec:authorize>
				
				<li><a class="fnav" href="portal/download/toList.action?type=article&update=1">常用下载</a></li>
				<li><a class="fnav" href="portal/aboutUs/toView.action?update=1">关于我们</a></li>
			</ul>
		</div>
	</div>
 <div id="content">