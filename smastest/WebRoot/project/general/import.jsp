<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
		<head>
			<base href="<%=basePath%>" />
			<title>导入项目</title>
			<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
			<link rel="stylesheet" type="text/css" href="css/global.css" />
			<link rel="stylesheet" type="text/css" href="css/project/project.css" />
		</head>

		<body>
			<div id="container">
				<div id="top">
					<table class="table_bar">
						<tr>
							<td>
								项目管理&nbsp;&gt;&gt;
								<span class="text_red">导入项目</span>
							</td>
						</tr>
					</table>
				</div>
				
				<div id="mid"></div>
				
				<div id="bottom">
					<s:form id="form1" action="importProject" enctype="multipart/form-data" theme="simple" method="post" namespace="/project/general">
						<s:include value="/validateError.jsp" />
						<table class="table_edit" cellspacing="0" cellpadding="0">
							<tr>
								<td class="thhead" colspan="3">
									数据导入
								</td>
							</tr>
							<tr>
								<td class="wd12">导入文件:</td>
								<td class="wd13" style="border-width:1px 0 1px 1px;" >
									<s:file name="xls" cssClass="input1" />
								</td>
								<td style="border-width:1px 1px 1px 0;">
									<input type="submit" class ="input" value = "导入" />
								</td>
							</tr>
						</table>
					</s:form>
				</div>
			</div>
			<script type="text/javascript" src="javascript/project/general/general.js?ver=<%=application.getAttribute("systemVersion")%>"></script>
		</body>
</html>