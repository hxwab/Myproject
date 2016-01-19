<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page isELIgnored ="true"%><%--添加对EL表达式的支持--%>
<%@ taglib prefix="s" uri="/struts-tags"%> <%--添加对struts标签的支持 --%>
<!DOCTYPE html>
<html>
	<head>
		<title>湖北省社会科学优秀成果奖申报评审系统</title>
		<%@ include file="/jsp/base.jsp"%>
		<link rel="stylesheet" href="lib/bootstrap-datepicker-1.4.0-dist/css/bootstrap-datepicker3.css">
	</head>
	<body>
		<%@ include file="/jsp/innerNav.jsp"%>
		<s:hidden id="entityId" name="entityId" value="%{entityId}" />
		<div class="row mySlide">
			<ol class="breadcrumb mybreadcrumb">当前位置：
				<li><a href="#">系统管理</a></li>
				<li><a href="#">数据字典管理</a></li>
				<li class="active">奖金修改</li>
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
					<table class="table view-table">
						<tbody>
							<tr>
								<td class = "text-right view-key" width = "100">年份：</td>
								<td class="text-left" width = "300">${reward.year}</td>
								<td></td>
							</tr>
						</tbody>
					</table>
					
					<div class="panel panel-default panel-view">
						<div class="panel-heading"><strong>著作</strong></div>
						<div class="panel-body">
							<table class="table table-striped view-table">
								<tbody>
									<tr>
										<td width = "100" class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td width = "80" class = "text-right view-key">特等奖：</td>
										<td class = "text-left">${reward.bookSpecial}</td>
										<td width = "100"></td>
									</tr>
									<tr>
										<td width = "100" class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td width = "80" class = "text-right view-key">一等奖：</td>
										<td class = "text-left">${reward.bookFirst}</td>
										<td width = "100"></td>
									</tr>
									<tr>
										<td width = "100" class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td width = "80" class = "text-right view-key" >二等奖：</td>
										<td class = "text-left">${reward.bookSecond}</td>
										<td width = "100"></td>
									</tr>
									<tr>
										<td width = "100" class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td width = "80" class = "text-right view-key" >三等奖：</td>
										<td class = "text-left">${reward.bookThird}</td>
										<td width = "100"></td>
									</tr>
								</tbody>
							</table>
						</div>
					</div> <!-- end <div class="panel panel-default"> -->
					
					<div class="panel panel-default panel-view">
						<div class="panel-heading"><strong>论文</strong></div>
						<div class="panel-body">
							<table class="table table-striped view-table">
								<tbody>
									<tr>
										<td width = "100" class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td width = "80" class = "text-right view-key">特等奖：</td>
										<td class = "text-left">${reward.paperSpecial}</td>
										<td width = "100"></td>
									</tr>
									<tr>
										<td width = "100" class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td width = "80" class = "text-right view-key">一等奖：</td>
										<td class = "text-left">${reward.paperFirst}</td>
										<td width = "100"></td>
									</tr>
									<tr>
										<td width = "100" class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td width = "80" class = "text-right view-key" >二等奖：</td>
										<td class = "text-left">${reward.paperSecond}</td>
										<td width = "100"></td>
									</tr>
									<tr>
										<td width = "100" class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td width = "80" class = "text-right view-key" >三等奖：</td>
										<td class = "text-left">${reward.paperThird}</td>
										<td width = "100"></td>
									</tr>
								</tbody>
							</table>
						</div>
					</div> <!-- end <div class="panel panel-default"> -->
				</textarea>
				
				<div id = "view_container" style = "display:none"></div><!-- 模版解析后的容器 -->
			</div>
		</div>
	
		<!-- 页脚 -->
		<div class="row">
		<%@ include file="/jsp/footer.jsp"%>
		<script>
			seajs.use("js/system/dataDic/reward/view", function(view) {
				view.init();
			});
		</script>
	</body>
</html>