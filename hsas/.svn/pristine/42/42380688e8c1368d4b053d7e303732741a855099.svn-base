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
				<li class="active">修改机构信息</li>
			</ol>
			
			<div class="col-xs-12">
				<form id="modifyAgencyForm">
					<div class="panel panel-default panel-view">
						<div class="panel-heading"><strong>修改机构信息</strong></div>
						<div class="panel-body">
							<table class="table table-striped view-table">
								<tbody>
									<tr>
										<td width = "150" class = "text-right required-label">机构名称：</td>
										<td class = "text-left"><s:textfield type="text" name="agency.name" class="form-control input-sm validate[required]" placeholder="请输入" /></td>
										<td width = "100"></td>
									</tr>
									<tr>
										<td width = "150" class = "text-right" >英文名称：</td>
										<td class = "text-left"><s:textfield type="text" name="agency.englishName" class="form-control input-sm" placeholder="请输入" /></td>
										<td width = "100"></td>
									</tr>
									<tr>
										<td width = "150" class = "text-right required-label">机构类型：</td>
										<td class = "text-left"><s:select cssClass="form-control input-sm validate[required]" name="agency.type" headerKey="" headerValue="--请选择--" list="#{'0':'非高校', '1':'高校'}" /></td>
										<td width = "100"></td>
									</tr>
									<tr>
										<td width = "150" class = "text-right">机构简称：</td>
										<td class = "text-left"><s:textfield type="text" name="agency.abbr" class="form-control input-sm" placeholder="请输入" /></td>
										<td width = "100"></td>
									</tr>
									<tr>
										<td width = "150" class = "text-right">邮政编码：</td>
										<td class = "text-left"><s:textfield type="text" name="agency.postCode" class="form-control input-sm validate[custom[chinaZip]]" placeholder="请输入" /></td>
										<td width = "100"></td>
									</tr>
									<tr>
										<td width = "150" class = "text-right">所在地址：</td>
										<td class = "text-left"><s:textfield type="text" name="agency.address" class="form-control input-sm" placeholder="请输入" /></td>
										<td width = "100"></td>
									</tr>
									<tr>
										<td width = "150" class = "text-right required-label" >联系人：</td>
										<td class = "text-left"><s:textfield type="text" name="agency.directorName" class="form-control input-sm validate[required]" placeholder="请输入" /></td>
										<td width = "100"></td>
									</tr>
									<tr>
										<td width = "150" class = "text-right required-label" >手机号码：</td>
										<td class = "text-left"><s:textfield type="text" name="agency.mobilePhone" class="form-control input-sm validate[required,[custom[phone]]]" placeholder="请输入" /></td>
										<td width = "100"></td>
									</tr>
									<tr>
										<td width = "150" class = "text-right required-label" >电子邮箱：</td>
										<td class = "text-left"><s:textfield type="text" name="agency.email" class="form-control input-sm validate[required, [custom[email]]]" placeholder="请输入" /></td>
										<td width = "100"></td>
									</tr>
									<tr>
										<td width = "150" class = "text-right required-label" >办公电话：</td>
										<td class = "text-left"><s:textfield type="text" name="agency.officePhone" cssClass="form-control input-sm validate[required, [custom[phone]]]" placeholder="请输入" /></td>
										<td width = "100"></td>
									</tr>
									<tr>
										<td width = "150" class = "text-right" >传真号码：</td>
										<td class = "text-left"><s:textfield type="text" name="agency.officeFax" class="form-control input-sm" placeholder="请输入" /></td>
										<td width = "100"></td>
									</tr>
									<tr>
										<td width = "150" class = "text-right" >机构主页：</td>
										<td class = "text-left"><s:textfield type="text" name="agency.homepage" class="form-control input-sm" placeholder="请输入" /></td>
										<td width = "100"></td>
									</tr>
									<tr>
										<td width = "150" class = "text-right" >机构简介：</td>
										<td class = "text-left"><s:textarea type="text" name="agency.introduction" class="form-control input-sm" placeholder="请输入" rows="5"></s:textarea></td>
										<td width = "100"></td>
									</tr>
								</tbody>
							</table>
						</div>
					</div> <!-- end <div class="panel panel-default"> -->
					
					<div id="optr" class="btn_bar1">
						<div class="btn-group">
							<button id="submit" class="btn btn-default " type="submit">提交</button>
							<button id="cancel" class="btn btn-default " type="button" onclick="history.back()">取消</button>
						</div>
					</div>
				</form>
			</div>
		</div>
		
		<!-- 页脚 -->
		<div class="row">
		<%@ include file="/jsp/footer.jsp"%>
	</body>
	<script>
		seajs.use("js/system/dataDic/agency/modify", function() {
			
		});
	</script>
</html>