<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<s:head/>
		<title>SMAS</title>
		<s:include value="/innerBase.jsp" />
		<link rel="stylesheet" type="text/css" href="css/main/page.css" />
	</head>

	<body>
		<div id="container">
			<div id="top">
				系统管理&nbsp;&gt;&gt;&nbsp;系统配置&nbsp;&gt;&gt;&nbsp;<span class="text_red">配置系统状态</span>
			</div>
			
<%--				<div id="mid"></div>--%>
			
			<div id="bottom">
				<s:form action="configSystemStatus" id="form_sysconfig" namespace="/main" theme="simple">
					<s:include value="/validateError.jsp" />
					<table class=" table_edit2table_edit" cellspacing="0" cellpadding="0">
						<tr>
							<td class="wd12">
								系统状态
							</td>
							<td class="wd13">
								<s:radio list="#{true:'运行中',false:'维护中'}" name="systemStatus"></s:radio>
							</td>
						</tr>
						<tr>
							<td></td>
							<td class="td_sub"><input type="submit" class ="btn" value = "确定" /><input type="button" class="btn" value="取消" onclick="history.back();" /></td>
						</tr>
					</table>
				</s:form>
			</div>
		</div>
		<script type="text/javascript" src="javascript/jquery/jquery.js?ver=<%=application.getAttribute("systemVersion")%>"></script>
		<script type="text/javascript" src="javascript/jquery/jquery.validate.js?ver=<%=application.getAttribute("systemVersion")%>"></script>
		<script type="text/javascript" src="javascript/sysconfig/sysconfig_validate.js?ver=<%=application.getAttribute("systemVersion")%>"></script>
		<s:if test="#session.locale.equals(\"en_US\")">
			<script type="text/javascript" src="javascript/sysconfig/sysconfig_validate_en.js?ver=<%=application.getAttribute("systemVersion")%>"></script>
		</s:if>
	</body>
</html>