<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title></title>
		<s:include value="/innerBase.jsp" />
		<link rel="stylesheet" type="text/css" href="css/index.css" />
		<link rel="stylesheet" type="text/css" href="css/global.css" />
		<link rel="stylesheet" type="text/css" href="css/jquery/jquery-ui-1.8.5.custom.css" />
	</head>

	<body>
		<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-bottom:5px;">
			<tr>
				<td><span>请选择切换的账号：</span></td>
				<td><span id="error" class="errorInfo"></span></td>
			</tr>
		</table>
		
		<s:include value="/validateError.jsp" />
		<div style="height:110px; padding:4px; border:1px solid #A9A9A9;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr height="30">
					<td width="40%"><input type="radio" name="accountId" value="" />&nbsp;系统管理员</td>
					<td><span>系统管理员账号</span>&nbsp;<span>启用</span></td>
				</tr>
				<tr height="30px">
					<td width="40%"><img src="image/menu/activeuser.gif" />&nbsp;<span>用户名</span></td>
					<td><input type="text" name="username" id="username"  /></td>
				</tr>
			</table>
		</div>
		
		<div style="text-align:center;margin-top:10px;">
			<ul>
				<li><input id="confirm_btn" class="btn" type="button" value="确定" /></li>
			</ul>
		</div>
	
		<script type="text/javascript" src="javascript/jquery/jquery.js?ver=<%=application.getAttribute("systemVersion")%>"></script>
		<script type="text/javascript" src="javascript/pop/pop_init.js?ver=<%=application.getAttribute("systemVersion")%>"></script>
		<script type="text/javascript" src="javascript/main/switchAccount.js?ver=<%=application.getAttribute("systemVersion")%>"></script>
	</body>
</html>
