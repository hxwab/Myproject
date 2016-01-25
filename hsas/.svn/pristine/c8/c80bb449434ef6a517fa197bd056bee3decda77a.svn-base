<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page isELIgnored ="true"%><%--添加对EL表达式的支持--%>
<%@ taglib prefix="s" uri="/struts-tags"%> <%--添加对struts标签的支持 --%>
<!DOCTYPE html>
<html>
	<head>
		<title>湖北省社会科学优秀成果奖申报评审系统</title>
		<%@ include file="/jsp/base.jsp"%>
		<link rel="stylesheet" href="lib/uploadify/css/uploadify.css">
		<link rel="stylesheet" href="lib/bootstrap-datepicker-1.4.0-dist/css/bootstrap-datepicker3.css">
	</head>
	<body>
   		<%@ include file="/jsp/innerNav.jsp"%>
   		<a name="top" id="top"></a>
			<div class="row main-content">
				<ol class="breadcrumb mybreadcrumb">当前位置：
					<li><a href="#">投稿</a></li>
					<li class="active">论坛投稿</li>
				</ol>
				<div class="col-xs-10 col-xs-offset-1">
				   <div class="survey_add addTable">
						<form id="form_solicitPapers" action="solicitPapers/modify.action" method="post">
							<input type="hidden" name="solicitPapers.type" value="1">
							<table width="100%">
								<!-- 从前台向后台传数据两种方法1：直接写入name=对象名.属性值；2：加上required，在后台逐个访问标签 -->
								<tbody>
									<tr>
										<td width="80" class="text-right required-label">名称：</td>
										<td><s:textfield name="solicitPapers.name" cssClass="form-control input-sm validate[required]" placeholder="请输入名称" /></td>
									</tr>
									<tr>
										<td width="80" class="text-right required-label">申请人：</td>
										<td><s:textfield name="solicitPapers.author" cssClass="form-control input-sm validate[required]" placeholder="请输入申请人" /></td>
									</tr>
									<tr>
										<td width="80" class="text-right required-label">性别：</td>
										<td><s:select name="solicitPapers.gender" cssClass="form-control input-sm validate[required]" headerKey="" headerValue="--请选择--" list="#{'男':'男', '女':'女'}"></s:select></td>
									</tr>
									<tr>
										<td width="80" class="text-right required-label">出生日期：</td>
										<td>
											<s:textfield cssClass="form-control input-sm datePicker validate[required, custom[date]]" name="solicitPapers.birthday" placeholder="请输入出生日期">
												 <s:param name="value"><s:date name="solicitPapers.birthday" format="yyyy-MM-dd"/></s:param>
											</s:textfield>
										</td>	
									</tr>
									<tr>
										<td width="80" class="text-right required-label">所在单位：</td>
										<td><s:textfield name="solicitPapers.unit" cssClass="form-control input-sm validate[required]" placeholder="请输入所在单位" /></td>
									</tr>
									<tr>
										<td width="80" class="text-right required-label">联系电话：</td>
										<td><s:textfield name="solicitPapers.phone" cssClass="form-control input-sm validate[required, custom[phone]]" placeholder="请输入联系电话" /></td>
									</tr>
									<tr>
										<td width="80" class="text-right required-label">电子邮件：</td>
										<td><s:textfield name="solicitPapers.email" cssClass="form-control input-sm validate[required, custom[email]]" placeholder="请输入电子邮件" /></td>
									</tr>
									<tr>
										<td width="80" class="text-right">联系地址：</td>
										<td><s:textfield name="solicitPapers.address" cssClass="form-control input-sm validate[required]" placeholder="请输入联系地址" /></td>
									</tr>
									<tr>
										<td width="80" class="text-right">邮政编码：</td>
										<td><s:textfield name="solicitPapers.postcode" cssClass="form-control input-sm validate[custom[chinaZip]]" placeholder="请输入邮政编码" /></td>
									</tr>
									<tr>
										<td width="80"  class = "text-right required-label">上传附件：</td>
										<td class='position-relative'>
											<label class="sr-only" for="inputfile">文件输入</label>
											<input type="file" id="solicitPapersUpload"  name="solicitPapers.attachment" class='validate[required]'>
											<input type="text" class='validate[required] pull-right validation-file-input' name="solicitPapers.attachmentName" value="">
										</td>
									</tr> 
									<tr>
										<td width="80" class="text-right text-top">内容简介：</td>
										<td><s:textarea cssClass="form-control input-sm" name="solicitPapers.note" rows="5" placeholder="请输入简介"/></td>
									</tr>
								</tbody>
							</table>
							<div class="text-center">
								<div class="btn-group">
									<input type="submit" value="提交" class="btn btn-default " id="submit">
									<input type="button" value="取消" class="btn btn-default " id="cancel">
								</div>
							</div>
				   		</form>
					</div>
				</div>
			</div>
			<div class="row">
   		<%@ include file="/jsp/footer.jsp"%>
		<script>
			seajs.use("js/solicitPapers/modify.js", function() {
			});
		</script>
	</body>
</html>