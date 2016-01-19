<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page isELIgnored ="true"%><%--添加对EL表达式的支持--%>
<%@ taglib prefix="s" uri="/struts-tags"%> <%--添加对struts标签的支持 --%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
	<head>
		<title>湖北省社会科学优秀成果奖申报评审系统</title>
		<%@ include file="/jsp/base.jsp"%>
		<link rel="stylesheet" href="lib/uploadify/css/uploadify.css">
	</head>
	<body>
		<%@ include file="/jsp/innerNav.jsp"%>
		
		<div class="row">
			<ol class="breadcrumb mybreadcrumb">当前位置：
				<li>常用下载</li>
				<li class="active">添加</li>
			</ol>
		</div>
		
		<div class="col-xs-12">
			<div class="col-xs-8 col-xs-offset-2">
				<form id="form_download" class="form-horizontal">
					<div class="form-group">
						<label class="col-xs-2 control-label">附件</label>
						<div class="col-xs-10 position-relative">
							<label class="sr-only" for="inputfile">文件输入</label>
							<input type="file" id="downloadFileUpload"  name="upload.attachmentName" class='validate[required]'>
							<input type="text" class="pull-right validation-file-input" name="upload.attachmentName" value="">
						</div>
					</div>
					<div id="optr" class="btn_bar1 form-group">
						<div class="btn-group">
							<input type="submit" value="提交" class="btn btn-default " id="submit">
							<input type="button" value="取消" class="btn btn-default " id="cancel">
						</div>
					</div>
				</form>
			</div>
		</div>
		
		<div class="row">
		<%@ include file="/jsp/footer.jsp"%>
		<!-- <script src="js/main.js" type="text/javascript"></script> -->
	</body>
	<script>
		seajs.use('js/portal/download/add.js', function(){
		})
	</script>
</html>