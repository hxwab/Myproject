<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title></title>
		<s:include value="/innerBase.jsp" />
		<link rel="stylesheet" type="text/css" href="css/index.css" />
		<link rel="stylesheet" type="text/css" href="css/global.css" />
		<link rel="stylesheet" type="text/css" href="css/jquery/jquery-ui-1.8.5.custom.css" />
	</head>

	<body id="right_body">
		<div>
			<s:if test="#session.visitor.user.issuperuser != 1">
				<div style="height:10px;"></div>
			</s:if>
			<s:else>
				<div style="height:18px;margin-bottom:-10px;"><center><s:if test="(#session.SystemStatus==0)"><font color="#FF0000">系统维护中</font></s:if>
				<s:else>系统运行中</s:else></center></div>
			</s:else>
			
			<!-- 专家信息 -->
			<sec:authorize ifAllGranted='ROLE_EXPERT'>
				<div class="static">
					<table class="static_table" cellspaning="0" cellpadding="0">
						<tr>
							<td class="staticwd0" colspan="6">专家统计信息</td>
						</tr>
						<tr>
							<td class="staticwd1" rowspan="4">专家总数：</td>
							<td class="staticwd2" rowspan="4"><s:property value="#request.enall" />人</td>
							<td class="staticwd1" rowspan="2">参加评审的专家：</td>
							<td class="staticwd2" rowspan="2"><s:property value="#request.enReview" />人</td>
							<td class="staticwd1"><a href="expert/toList.action?update=1&expertType=0&isReviewer=1" title="点击进入参加评审的内部专家列表">内部专家</a>：</td>
							<td class="staticwd2"><s:property value="#request.enReviewI" />人</td>
						</tr>
						<tr>
							<td class="staticwd1"><a href="expert/toList.action?update=1&expertType=1&isReviewer=1" title="点击进入参加评审的外部专家列表">外部专家</a>：</td>
							<td class="staticwd2"><s:property value="#request.enReviewO" />人</td>
						</tr>
						<tr>
							<td class="staticwd1" rowspan="2">退出评审的专家：</td>
							<td class="staticwd2" rowspan="2"><s:property value="#request.ennReview" />人</td>
							<td class="staticwd1"><a href="expert/toList.action?update=1&expertType=0&isReviewer=0" title="点击进入退出评审的内部专家列表">内部专家</a>：</td>
							<td class="staticwd2"><s:property value="#request.ennReviewI" />人</td>
						</tr>
						<tr>
							<td class="staticwd1"><a href="expert/toList.action?update=1&expertType=1&isReviewer=0" title="点击进入退出评审的外部专家列表">外部专家</a>：</td>
							<td class="staticwd2"><s:property value="#request.ennReviewO" />人</td>
						</tr>
					</table>
				</div>
			</sec:authorize>
			
			<!-- 项目匹配相关信息 -->
			<sec:authorize ifAnyGranted="ROLE_PROJECT_GENERAL,ROLE_PROJECT_INSTP">
				<div class="main_content" id="tabcontent">
						<div id="tabs" class="p_box_bar">
							<ul>
								<sec:authorize ifAllGranted='ROLE_PROJECT_GENERAL'>
									<li><a href="#general">一般项目</a></li>
								</sec:authorize>
								<sec:authorize ifAllGranted='ROLE_PROJECT_INSTP'>
									<li><a href="#instp">基地项目</a></li>
								</sec:authorize>
								<!-- TODO -->
								<sec:authorize ifAllGranted="ROLE_AWARD_MOESOCIAL">
									<li><a href="#award">社科奖励</a></li>
								</sec:authorize>
							</ul>

						</div>
	
						<div class="p_box">
							<sec:authorize ifAllGranted='ROLE_PROJECT_GENERAL'>
								<div id="general">
									<div id="view_container_project" style="clear:both;">
										<s:include value="/main/rightGeneral.jsp"/>
									</div>
								</div>
							</sec:authorize>
							<sec:authorize ifAllGranted='ROLE_PROJECT_INSTP'>
								<div id="instp">
									<s:include value="/main/rightInstp.jsp"/>
								</div>
							</sec:authorize>
							<!-- TODO -->
							<sec:authorize ifAllGranted="ROLE_AWARD_MOESOCIAL">
								<div id="award">
									<s:include value="/main/rightAward.jsp"/>
								</div>
							</sec:authorize>
						</div>
				</div>
			</sec:authorize>
		</div>
		<script type="text/javascript" src="javascript/jquery/jquery.js?ver=<%=application.getAttribute("systemVersion")%>"></script>
		<script type="text/javascript" src="javascript/jquery/jquery-ui-1.8.5.custom.min.js?ver=<%=application.getAttribute("systemVersion")%>"></script>
		<script type="text/javascript" src="javascript/template_tool.js?ver=<%=application.getAttribute("systemVersion")%>"></script>
		<script type="text/javascript" src="javascript/common.js?ver=<%=application.getAttribute("systemVersion")%>"></script>
		<script type="text/javascript" src="javascript/main/right.js?ver=<%=application.getAttribute("systemVersion")%>"></script>
	</body>
</html>
