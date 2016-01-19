<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page isELIgnored ="true"%><%--添加对EL表达式的支持--%>
<%@ taglib prefix="s" uri="/struts-tags"%> <%--添加对struts标签的支持 --%>
<!DOCTYPE html>
<html>
	<head>
		<title>湖北省社会科学优秀成果奖申报评审系统</title>
		<%@ include file="/jsp/base.jsp"%>
	</head>
	<body>
		<!-- header -->
		<%@ include file="/jsp/innerNav.jsp"%>
		<s:hidden id="entityId" name="entityId" value="%{entityId}" />
		<!-- end header -->
		
		<!-- body -->
		<div class="row mySlide">
			<ol class="breadcrumb mybreadcrumb">当前位置：
				<li><a href="javascript:void(0)">评审管理</a></li>
				<li><a href="product/appeal/toList.action?update=1">申诉</a></li>
				<li class="active">详情</li>
			</ol>
			
			<div class="col-xs-12">
				<div class="btn-group pull-right view-controler" role="group" aria-label="...">
		  			<button type="button" class="btn  btn-default" id = "view_add">添加</button>
		  			<button type="button" class="btn  btn-default" id = "view_mod">修改</button>
		  			<button type="button" class="btn  btn-default" id = "view_del">删除</button>
		  			<button type="button" class="btn  btn-default" id = "view_prev">上一条</button>
		  			<button type="button" class="btn  btn-default" id = "view_next">下一条</button>
		  			<button type="button" class="btn  btn-default" id = "view_back">返回</button>
				</div>
				<span class="clearfix"></span><!-- 重要!! 用于清除按键组浮动 -->
				 <textarea id = "view_template" style = "display:none"><!-- 前台模版 -->
				    <table class="table table-striped view-table">
				      <tbody>
						<tr>
							<td width = "20" class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
							<td width = "50" class = "text-right view-key">申诉主题：</td>
							<td width ='300' class = "text-left">${appeal.title}</td>
						</tr>
						<tr>
							<td width = "50" class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
							<td width = "100" class = "text-right view-key">申诉人：</td>
							<td width="300" class = "text-left">${appeal.editor}</td>
						</tr>
						<tr>
							<td width = "50" class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
							<td width = "100" class = "text-right view-key">附件材料：</td>
							<td width="300" class = "text-left"><a href="product/appeal/download.action?entityId=${appeal.id}">${appeal.attachmentName}</a></td>
						</tr>
						<tr>
							<td width = "50" class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
							<td width = "100" class = "text-right view-key">记录人：</td>
							<td width="300" class = "text-left">${appeal.author}</td>
						</tr>
						<tr>
							<td width = "50" class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
							<td width = "100" class = "text-right view-key">更新时间：</td>
							<td width="300" class = "text-left">${appeal.createDate|toFormat}</td>
						</tr>
						<tr>
							<td width = "50" class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
							<td width = "100" class = "text-right">申报内容：</td>
							<td width="300" class = "text-left">${appeal.content}</td>
						</tr>
					  </tbody>
		    		</table>
		    	</textarea>
		    	<div id = "view_container" style = "display:none"></div><!-- 模版解析后的容器 -->
			</div>
		</div>
		<!-- end body -->
		
		<!-- footer -->
		<div class="row">
		<%@ include file="/jsp/footer.jsp"%>
		<!-- end footer -->
		
		<script>
			seajs.use("js/product/appeal/view.js", function(view) {
				 view.init();
			});
		</script>
	</body>
</html>
		