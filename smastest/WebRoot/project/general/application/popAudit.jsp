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
			<title>一般项目申请审核</title>
			<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
			<link rel="stylesheet" type="text/css" href="css/global.css" />
			<link rel="stylesheet" type="text/css" href="tool/jquery-ui-1.8.16.custom/css/redmond/jquery-ui-1.8.16.custom.css" />
			<link rel="stylesheet" type="text/css" href="css/flora.datepick.css" />
		</head>

		<body>
			<div style="width: 360px;height:260px;">
				<s:form>
					<table width="100%" border="0" cellspacing="2" cellpadding="0">
						<tr class="table_tr1">
							<td class="table_td1" style="text-align:right;width:40%;">是否同意立项：</td>
							<td class="table_td1" style="width:30%;"><input type="radio" name="reviewAuditResult" value="2" checked="true"/>同意</td>
							<td class="table_td1" style="width:30%;"><input type="radio" name="reviewAuditResult" value="1"/>不同意</td>
						</tr>
						<tr class="table_tr1">
							<td class="table_td1" style="text-align:right;">批准时间：</td>
							<td class="table_td1" colspan="2"><input id="reviewAuditDate" name="reviewAuditDate" style="width:100%;"/></td>
						</tr>
						<tr class="table_tr1" id="approveFeeDiv">
							<td class="table_td1" style="text-align:right;">批准经费（万）：</td>
							<td class="table_td1" colspan="2"><input id="approveFee" name="approveFee" style="width:100%;" value="${approveFee}"/></td>
						</tr>
						<tr class="table_tr1" id="approveNumDiv">
							<td class="table_td1" style="text-align:right;">批准号：</td>
							<td class="table_td1" colspan="2"><input id="number" name="number" style="width:100%;" value="${number}" /></td>
						</tr>
						<tr class="table_tr1">
							<td class="table_td1" style="text-align:right;">审核意见：</td>
							<td class="table_td1" colspan="2"><textarea id="reviewAuditOpinion" name="reviewAuditOpinion" id="reviewAuditOpinion" rows="3" style="width:100%;"></textarea></td>
						</tr>
						<tr class="table_tr1">
							<td class="table_td1" style="text-align:right;">审核意见：<br />（反馈给负责人）</td>
							<td class="table_td1" colspan="2"><textarea id="reviewAuditOpinionFeedback" name="reviewAuditOpinionFeedback" id="reviewAuditOpinionFeedback" rows="3" style="width:100%;"></textarea></td>
						</tr>
					</table>
					<div class="btn_div">
						<input type="button" class="confirm btn" value="提交" />
						<input type="button" class="cancel btn" value="取消" />
					</div>
				</s:form>
			</div>
			<script type="text/javascript" src="javascript/jquery/jquery.js?ver=<%=application.getAttribute("systemVersion")%>"></script>
			<script type="text/javascript" src="tool/jquery-ui-1.8.16.custom/js/jquery-ui-1.8.16.custom.min.js?ver=<%=application.getAttribute("systemVersion")%>"></script>
			<script type="text/javascript" src="javascript/jquery/jquery-ui-timepicker-addon.js?ver=<%=application.getAttribute("systemVersion")%>"></script>
			<script type="text/javascript" src="javascript/jquery/jquery.datepick.js?ver=<%=application.getAttribute("systemVersion")%>"></script>
			<script type="text/javascript" src="javascript/jquery/jquery.datepick.zh_cn.js?ver=<%=application.getAttribute("systemVersion")%>"></script>		
			<script type="text/javascript" src="javascript/jquery/datepick.mine.js?ver=<%=application.getAttribute("systemVersion")%>"></script>
			<script type="text/javascript" src="javascript/common.js?ver=<%=application.getAttribute("systemVersion")%>"></script>
			<script type="text/javascript" src="javascript/pop/pop_init.js?ver=<%=application.getAttribute("systemVersion")%>"></script>
			<script type="text/javascript" src="javascript/project/general/application/popAudit.js?ver=<%=application.getAttribute("systemVersion")%>"></script>
		</body>
</html>