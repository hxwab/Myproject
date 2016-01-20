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
				<li><a href="javascript:void(0)">统计分析</a></li>
				<li class="active">详情</li>
			</ol>
			
			<div class="col-xs-12">
				<textarea id = "" style = "display:none"><!-- 前台模版 -->
				</textarea>
				<textarea id = "" style = "display:none"><!-- 前台模版 -->
				</textarea>
				<textarea id = "" style = "display:none"><!-- 前台模版 -->
				</textarea>
				<textarea id = "" style = "display:none"><!-- 前台模版 -->
				</textarea>
				<textarea id = "" style = "display:none"><!-- 前台模版 -->
				</textarea>
				<textarea id = "" style = "display:none"><!-- 前台模版 -->
				</textarea>
			</div>
			
		</div>
		<!-- end body -->
		
		<!-- footer -->
		<div class="row">
		<%@ include file="/jsp/footer.jsp"%>
		<!-- end footer -->
		
		<script>
			seajs.use("js/statistic/view.js", function(view) {
				 view.init();
			});
		</script>
	</body>
</html>