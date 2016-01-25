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
		<!-- end header -->
		
		<!-- body -->
		<div class="row mySlide">
			<ol class="breadcrumb mybreadcrumb">当前位置：
			  <li><a href="#">系统管理</a></li>
			  <li><a href="#">期刊管理 </a></li>
			  <li class="active">查看</li>
			</ol>
			    <div class="col-xs-12 main-content">
					 <textarea id = "view_template" style = "display:none"><!-- 前台模版 -->
					 	<div class="panel panel-default panel-view">
							<div class="panel-heading">
								<strong>出版社或发表刊物名称</strong>
							</div>
							<div class="panel-body">
								<div class="row">
									<div class="col-xs-3">
										<div class="span-margin"><i class="fa fa-plus-circle"></i>&nbsp;&nbsp;<a id="insertNewChild" href="javascript:void(0)">添加子节点</a></div>
										<div class="span-margin"><i class="fa fa-minus-circle"></i>&nbsp;&nbsp;<a id="deleteItem" href="javascript:void(0)">删除所选节点</a></div>
										<div id="optr" class="btn_bar1">
											<div class="btn-group">
												<button id="updateItems" class="btn btn-default " type="button">更新</button>
												<button id="cancel" class="btn btn-default " type="button" onclick="window.location.reload()">取消</button>
											</div>
										</div>
									</div>
									<div class="col-xs-9 border-left" style="min-height:120px;">
										<div class="input-group col-xs-9" style="margin:10px;">
											<input id="searchInput" type="text" class="form-control" placeholder="检索期刊/报刊">
											<span class="input-group-btn">
												<button id="searchItem" class="btn btn-default" type="button">检索</button>
											</span>
										</div>
										<div id="treeparent" class="tree"></div>
									</div>
								</div>
					    	</div>
			    		</div>
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
		    seajs.use("js/system/dataDic/journal/view.js", function(view) {
		         view.init(); 
		    });
		</script>
	</body>
</html>