<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page isELIgnored ="true"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>Execution</title>
		<s:include value="/innerBase.jsp" />
	</head>

	<body>
		<div id="bottom">
			<span style="weitght:bold;font-size:20px;color:blue"><s:property value="executionBeanName" /></span>
			<span> executed <font color="red">failed</font>, costing <span style="weitght:bold;font-size:20px;color:blue"><s:property value="costTime" /></span> ms !</span>
			<br /><br />
			<s:debug />
		</div>
	</body>
</html>
