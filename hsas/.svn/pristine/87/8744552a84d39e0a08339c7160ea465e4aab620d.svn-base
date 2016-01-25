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
    	<!-- header -->
   		<%@ include file="/jsp/innerNav.jsp"%>
   		<!-- end header -->
		
		<div class="row mySlide">
			<ol class="breadcrumb mybreadcrumb">当前位置：
			  <li><a href="#">系统管理</a></li>
			  <li><a href="account/toList.action?update=1">账户管理</a></li>
			  <li class="active">添加</li>
			</ol>
			
			<div class="col-xs-12 main-content">
				<form id="addAccountForm" action="account/add.action">
					
					<div class="panel panel-default panel-view">
						<s:hidden id="type" name="type" />
						<div class="panel-heading"><strong>账号信息</strong></div>
						<div class="panel-body">
							<table class="table table-striped view-table">
								<tbody>
									<tr>
										<td width = "150" class = "text-right required-label">账号名称：</td>
										<td class = "text-left"><s:textfield type="text" name="account.username" class="form-control input-sm validate[required]" placeholder="请输入" /></td>
										<td width = "100"></td>
									</tr>
									<tr>
										<td width = "150" class = "text-right required-label" >所属机构：</td>
										<td class = "text-left">
											<button class="btn btn-default  select-agency" type="button">选择</button>
					    					<div class="selected" style=""></div>
					    					<input id="agencyId" type="hidden" name="acountAgencyId">
										</td>
										<td width = "100"></td>
									</tr>
									<tr>
										<td width = "150" class = "text-right required-label">有效期：</td>
										<td class = "text-left"><s:textfield type="text" name="account.expireDate" class="form-control input-sm datePicker" placeholder="请输入" /></td>
										<td width = "100"></td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
					<div class="panel panel-default panel-view">
						<div class="panel-heading"><strong>用户信息</strong></div>
						<div class="panel-body">
							<table class="table table-striped view-table">
								<tbody>
									<tr>
										<td width = "150" class = "text-right required-label">用户姓名：</td>
										<td class = "text-left"><s:textfield type="text" name="person.name" class="form-control input-sm validate[required]" placeholder="请输入" /></td>
										<td width = "100"></td>
									</tr>
									<tr>
										<td width = "150" class = "text-right required-label" >单位：</td>
										<td class = "text-left">
											<button class="btn btn-default  select-agency" type="button">选择</button>
					    					<div class="selected" style=""></div>
					    					<input id="agencyId" type="hidden" name="agencyId">
										</td>
										<td width = "100"></td>
									</tr>
									<tr>
										<td width = "150" class = "text-right required-label">出生日期：</td>
										<td class = "text-left"><s:textfield type="text" name="person.birthday" class="form-control input-sm datePicker" placeholder="请输入" /></td>
										<td width = "100"></td>
									</tr>
									<tr>
										<td width = "150" class = "text-right required-label">邮箱：</td>
										<td class = "text-left"><s:textfield type="text" name="person.email" class="form-control input-sm validate[required]" placeholder="请输入" /></td>
										<td width = "100"></td>
									</tr>
									<tr>
										<td width = "150" class = "text-right required-label">联系电话：</td>
										<td class = "text-left"><s:textfield type="text" name="person.mobilePhone" class="form-control input-sm validate[required,phone]" placeholder="请输入" /></td>
										<td width = "100"></td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
					
					<div id="optr" class="btn_bar1">
						<div class="btn-group">
							<button id="submit" class="btn btn-default " type="submit">提交</button>
							<button id="cancel" class="btn btn-default " type="button" onclick="history.back()">取消</button>
						</div>
					</div>
				</form>
			</div>
		</div>
		
		<!-- footer -->
		<div class="row">
   		<%@ include file="/jsp/footer.jsp"%>
   		<!-- end footer -->
		<script>
		    seajs.use("js/system/account/administrator/add.js", function(list) {
		    });
		</script>
	</body>
</html>