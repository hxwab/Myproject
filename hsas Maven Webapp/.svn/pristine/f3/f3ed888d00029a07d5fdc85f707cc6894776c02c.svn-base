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
		<!-- 页头 -->
		<%@ include file="/jsp/innerNav.jsp"%>
		<a name="top" id="top"></a>
		
		<!-- 页面主体 -->
		<div class="row mySlide">
			<!-- 当前位置 -->
			<ol class="breadcrumb mybreadcrumb">当前位置：
				<li><a href="#">系统管理</a></li>
				<li><a href="#">数据字典管理</a></li>
				<li class="active">添加机构</li>
			</ol>
			
			<div class="col-xs-12">
				<form id="addUnitForm">
					<div class="panel panel-default panel-view">
						<div class="panel-heading"><strong>添加机构信息</strong></div>
						<div class="panel-body">
							<table class="table table-striped view-table">
								<tbody>
									<tr>
										<td width = "150" class = "text-right required-label">机构名称：</td>
										<td class = "text-left"><input type="text" name="unit.name" class="form-control input-sm validate[required, custom[number]]" placeholder="请输入"></td>
										<td width = "100"></td>
									</tr>
									<tr>
										<td width = "150" class = "text-right required-label">机构类型：</td>
										<td class = "text-left"><input type="text" name="unit.type" class="form-control input-sm validate[required, custom[number]]" placeholder="请输入"></td>
										<td width = "100"></td>
									</tr>
									<tr>
										<td width = "150" class = "text-right required-label" >上级单位：</td>
										<td class = "text-left"><input type="text" name="unit.superiorUnit" class="form-control input-sm validate[required, custom[number]]" placeholder="请输入"></td>
										<td width = "100"></td>
									</tr>
									<tr>
										<td width = "150" class = "text-right required-label" >联系人：</td>
										<td class = "text-left"><input type="text" name="unit." class="form-control input-sm validate[required, custom[number]]" placeholder="请输入"></td>
										<td width = "100"></td>
									</tr>
									<tr>
										<td width = "150" class = "text-right required-label" >联系电话：</td>
										<td class = "text-left"><input type="text" name="unit.phone" class="form-control input-sm validate[required, custom[number]]" placeholder="请输入"></td>
										<td width = "100"></td>
									</tr>
									<tr>
										<td width = "150" class = "text-right required-label" >传真号码：</td>
										<td class = "text-left"><input type="text" name="unit.fax" class="form-control input-sm validate[required, custom[number]]" placeholder="请输入"></td>
										<td width = "100"></td>
									</tr>
									<tr>
										<td width = "150" class = "text-right" >单位描述：</td>
										<td class = "text-left"><textarea type="text" name="unit.fax" class="form-control input-sm" placeholder="请输入" rows="5"></textarea></td>
										<td width = "100"></td>
									</tr>
								</tbody>
							</table>
						</div>
					</div> <!-- end <div class="panel panel-default"> -->
					
					<div id="optr" class="btn_bar1">
						<input type="submit" value="提交" class="btn btn-default btn-sm" id="submit">
						<input type="button" value="取消" class="btn btn-default btn-sm" id="submit">
					</div>
				</form>
			</div>
		</div>
		
		<!-- 页脚 -->
		<div class="row">
		<%@ include file="/jsp/footer.jsp"%>
	</body>
	<script>
		seajs.use("js/system/dataDic/unit/add", function() {
			
		});
	</script>
</html>