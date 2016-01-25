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
		<title>变更人员详情</title>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
		<link rel="stylesheet" type="text/css" href="css/global.css" />
		<link rel="stylesheet" type="text/css" href="tool/jquery-ui-1.8.16.custom/css/redmond/jquery-ui-1.8.16.custom.css" />
		<link rel="stylesheet" type="text/css" href="css/flora.datepick.css" />
		<link rel="stylesheet" type="text/css" href="tool/uploadify/css/uploadify.css" />
		<script type="text/javascript">
			var basePath = "<%=basePath%>";
		</script>
	</head>
  
  <body style="width:500px;">
	 <form id="form_memberDetail">
	 	<table  width="100%" cellspacing="0" cellpadding="2" style="border-collapse:collapse;table-layout:fixed;">
	 		<tr>
	 			<td>人员类型：</td>
	 			<td><s:select name="memberType" headerKey='-1' headerValue="--请选择--" list="#{'1':'教师','2':'外部专家','3':'学生'}" theme="simple" /></td>
	 			<td>成员姓名：</td>
	 			<td><s:textfield name="memberName" size="12" theme="simple" /></td>
	 		</tr>
	 		<tr>
	 			<td>性别：</td>
	 			<td><s:select name="gender" headerKey='-1' headerValue="--请选择--" list="#{'男':'男','女':'女'}" theme="simple" /></td>
	 			<td>是否负责人：</td>
	 			<td><s:select name="isDirector" headerKey='-1' headerValue="--请选择--" list="#{'是':'是','否':'否'}" theme="simple" /></td>
	 		</tr>
	 		<tr>
	 			<td>生日：</td>
	 			<td><s:textfield name="birthday" size="12" theme="simple" /></td>
	 			<td>外语语种：</td>
	 			<td><s:textfield name="foreign" size="12" theme="simple" /></td>
	 		</tr>
	 		<tr>
	 			<td>证件类型：</td>
	 			<td><s:select name="idcardType" headerKey='-1' headerValue="--请选择--" list="#{'身份证':'身份证','军官证':'军官证','护照':'护照'}" theme="simple" /></td>
	 			<td>证件号：</td>
	 			<td><s:textfield name="idcardNumber" size="12" theme="simple" /></td>
	 		</tr>
	 		<tr>
	 			<td>最后学历：</td>
	 			<td><s:select name="education" headerKey='-1' headerValue="--请选择--" list="#{'大专':'大专','本科':'本科','研究生':'研究生'}" theme="simple" /></td>
	 			<td>学位：</td>
	 			<td><s:select name="degree" headerKey='-1' headerValue="--请选择--" list="#{'学士':'学士','硕士':'硕士','博士':'博士'}" theme="simple" /></td>
	 		</tr>
	 		<tr>
	 			<td>职称：</td>
	 			<td><s:textfield name="title" size="12" theme="simple" /></td>
	 			<td>职务：</td>
	 			<td><s:textfield name="job" size="12" theme="simple" /></td>
	 		</tr>
	 		<tr>
	 			<td>单位名称：</td>
	 			<td><s:textfield name="agencyName" size="12" theme="simple" /></td>
	 			<td>部门名称：</td>
	 			<td><s:textfield name="divisionName" size="12" theme="simple" /></td>
	 		</tr>
	 		<tr>
	 			<td>移动电话：</td>
	 			<td><s:textfield name="mobile" size="12" theme="simple" /></td>
	 			<td>固定电话：</td>
	 			<td><s:textfield name="phone" size="12" theme="simple" /></td>
	 		</tr>
	 		<tr>
	 			<td>邮箱：</td>
	 			<td><s:textfield name="email" size="12" theme="simple" /></td>
	 			<td>邮编：</td>
	 			<td><s:textfield name="postcode" size="12" theme="simple" /></td>
	 		</tr>
	 		<tr>
	 			<td>地址：</td>
	 			<td colspan="3"><s:textfield name="address" size="12" theme="simple" /></td>
	 		</tr>
	 	</table>
	 	<div class="btn_div">
			<input type="button" class="confirm btn" value="提交" />
			<input type="button" class="cancel btn" value="取消" />
		</div>
	 </form>
	<script type="text/javascript" src="javascript/jquery/jquery.js?ver=<%=application.getAttribute("systemVersion")%>"></script>
	<script type="text/javascript" src="javascript/jquery/jquery.form.js?ver=<%=application.getAttribute("systemVersion")%>"></script>
	<script type="text/javascript" src="tool/jquery-ui-1.8.16.custom/js/jquery-ui-1.8.16.custom.min.js?ver=<%=application.getAttribute("systemVersion")%>"></script>
	<script type="text/javascript" src="javascript/jquery/jquery-ui-timepicker-addon.js?ver=<%=application.getAttribute("systemVersion")%>"></script>
	<script type="text/javascript" src="javascript/jquery/jquery.datepick.js?ver=<%=application.getAttribute("systemVersion")%>"></script>
	<script type="text/javascript" src="javascript/jquery/jquery.datepick.zh_cn.js?ver=<%=application.getAttribute("systemVersion")%>"></script>		
	<script type="text/javascript" src="javascript/jquery/datepick.mine.js?ver=<%=application.getAttribute("systemVersion")%>"></script>
	<script type="text/javascript" src="javascript/common.js?ver=<%=application.getAttribute("systemVersion")%>"></script>
	<script type="text/javascript" src="javascript/popLayer.js?ver=<%=application.getAttribute("systemVersion")%>"></script>
	<script type="text/javascript" src="javascript/popLayer.self.js?ver=<%=application.getAttribute("systemVersion")%>"></script>
	<script type="text/javascript" src="javascript/pop/pop_init.js?ver=<%=application.getAttribute("systemVersion")%>"></script>
	<script type="text/javascript" src="javascript/project/general/variation/popMemberDetail.js?ver=<%=application.getAttribute("systemVersion")%>"></script>
  </body>
</html>
