<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page isELIgnored ="true"%><%--添加对EL表达式的支持--%>
<%@ taglib prefix="s" uri="/struts-tags"%> <%--添加对struts标签的支持 --%>
<!DOCTYPE html>
<html>
	<head>
		<title>湖北省社会科学优秀成果奖申报评审系统</title>
		<%@ include file="/jsp/base.jsp"%>
		<link rel="stylesheet" href="lib/uploadify/css/uploadify.css">
	</head>
	<body>
		<!-- header -->
		<%@ include file="/jsp/innerNav.jsp"%>
		<!-- end header -->
		
		<!-- body -->
		<div class="row mySlide">
			<ol class="breadcrumb mybreadcrumb">当前位置：
				<li><a href="javascript:void(0)">评审管理</a></li>
				<li><a href="product/appeal/toList.action?update=1">申诉</a></li>
				<li class="active">添加</li>
			</ol>
			
			<div class="col-xs-12">
				<div class="addTable">
					<form id="form_appeal_add" action="product/appeal/add.action" method="post" >
						<table width="100%" class="col-xs-11">
							<s:hidden name="type" value="appeal" />
							<tr>
								<td width="150" class="text-right required-label">申诉主题：</td>
								<td><input type="text" name="appeal.title" class="form-control input-sm validate[required]" placeholder=""></td>
							</tr>
							<tr>
								<td class="text-right required-label">申诉人：</td>
								<td><input type="text" name="appeal.editor" class="form-control input-sm validate[required]" placeholder=""></td>
							</tr>
							<tr>
			    				<td class="text-right ">附件：</td>
			    				<td class='position-relative' >
			    					<label class="sr-only" for="inputfile">文件输入</label>
			    					<input type="file" id="newsAttachmentUpload"  name="appeal.attachment" class='validate[required]'>
			    					<input type="text" class=' pull-right validation-file-input' name="appeal.attachmentName" value="">
			    				</td>
			    			</tr>
							<tr>
								<td width="150" class="text-right required-label text-top">申诉内容：</td>
								<td><textarea name="appeal.content" class="form-control input-sm validate[required]" placeholder="" rows="15"></textarea></td>
							</tr>
						</table>
						<div id="optr" class="btn_bar1">
							<div class="btn-group">
								<button id="submit" class="btn btn-default " type="submit">提交</button>
								<button id="cancel" class="btn btn-default " type="button">取消</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
		<!-- end body -->

		<!-- footer -->
		<div class="row">
		<%@ include file="/jsp/footer.jsp"%>
		<!-- end footer -->
		
		<script>
			seajs.use("js/product/appeal/add.js", function(list) {
				 
			});
		</script>
	</body>
</html>