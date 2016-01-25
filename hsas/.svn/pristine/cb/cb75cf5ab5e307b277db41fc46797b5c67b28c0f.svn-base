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
   		<%@ include file="/jsp/innerNav.jsp"%>
   		<s:hidden id="entityId" name="entityId" value="%{entityId}" />
			<div class="row mySlide">
				<ol class="breadcrumb mybreadcrumb">当前位置：
				  <li><a href="#">系统管理</a></li>
				  <li><a href="#">账户管理</a></li>
				  <li><a href="#">社科联管理员账号</a></li>
				  <li class="active">详情</li>
				</ol>
			    <div class="col-xs-12 main-content">
				     <div class="btn-group pull-right view-controler" role="group" aria-label="...">
			  			<button type="button" class="btn  btn-default" id = "view_add">添加</button>
			  			<%--<button type="button" class="btn  btn-default" id = "view_mod">修改</button>
			  			--%><button type="button" class="btn  btn-default" id = "view_del">删除</button>
			  			<button type="button" class="btn  btn-default" id = "view_prev">上一条</button>
			  			<button type="button" class="btn  btn-default" id = "view_next">下一条</button>
			  			<button type="button" class="btn  btn-default" id = "view_back">返回</button>
					</div>
					<span class="clearfix"></span><!-- 重要!! 用于清除按键组浮动 -->
					
					<textarea id = "view_template" style = "display:none"><!-- 前台模版 -->
						<div class="panel panel-default panel-view">
							<div class="panel-heading">
								<strong>账号信息</strong>
							</div>
							<div class="panel-body">
								<table class="table table-striped view-table">
								<tbody>
									<tr>
										<td width = "20" class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td width = "50" class = "text-right view-key">用户名：</td>
										<td width ="100" class = "text-left">${account.username }</td>
										<td width = "20" class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td width = "50" class = "text-right view-key">账号类型：</td>
										<td width = "100" class = "text-left" >
											{if account.type==1&&account.isSuperUser==1}admin
											{elseif account.type==1}高级管理员
											{elseif account.type==2}一般管理员
											{elseif account.type==3}高校管理员
											{elseif account.type==4}复评专家组长
											{elseif account.type==5}复评专家
											{elseif account.type==6}初评专家
											{elseif account.type==7}申报人
											{else}未知
											{/if}
										</td>
									</tr>
									<tr>
										<td class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td class = "text-right view-key">所属机构：</td>
										<td class = "text-left" ><a id="popAccountAgency" href="javascipt:void(0);">${account.agency.name}</a></td>
										<td class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td class = "text-right view-key">账号状态：</td>
										<td class = "text-left">{if account.status==0}已停用{elseif account.status==1}已启用{else}未知{/if}</td>
									</tr>
									<tr>
										<td class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td class = "text-right view-key">创建日期：</td>
										<td class = "text-left" >${account.startDate|toFormat}</td>
										<td class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td class = "text-right view-key">失效日期：</td>
										<td class = "text-left">${account.expireDate|toFormat}</td>
									</tr>
									<tr>
										<td class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td class = "text-right view-key">创建类型：</td>
										<td class = "text-left" >{if account.createType==0}管理员分配{elseif account.createType==1}用户注册{else}未知{/if}</td>
										<td class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td class = "text-right view-key">上次登录时间：</td>
										<td class = "text-left">${account.lastLoginDate|toFormat}</td>
									</tr>
									<tr>
										<td class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td class = "text-right view-key">账号角色：</td>
										<td class = "text-left" colspan="4">
										{for item in role}
											{if  item_index == role.length-1}
											${item.name}
											{elseif}
											${item.name}；
											{/if}
										{forelse}
				 							暂无角色
										{/for}
											<a id="modifyRole" href="javascript:void(0)">[修改]</a></td>
									</tr>
								</tbody>
								</table>
			    			</div>
			    		</div>
			    		
			    		<div class="panel panel-default panel-view">
							<div class="panel-heading">
								<strong>用户信息</strong>
							</div>
							<div class="panel-body">
								<table class="table table-striped view-table">
								<tbody>
									<tr>
										<td width = "20" class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td width = "50" class = "text-right view-key">姓名：</td>
										<td width ='100' class = "text-left"><a href="">${account.person.name}</a></td>
										<td width = "20" class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td width = "50" class = "text-right view-key">单位：</td>
										<td width ='100' class = "text-left">${account.person.agencyName}</td>
									</tr>
									<tr>
										<td class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td class = "text-right view-key">邮箱：</td>
										<td class = "text-left">${account.person.email}</td>
										<td class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td class = "text-right view-key">手机：</td>
										<td class = "text-left">${account.person.mobilePhone}</td>
									</tr>
								</tbody>
								</table>
			    			</div>
			    		</div>
			    		
			    		<div id="accountAgency" style="display:none">
			    			<table class="table table-striped view-table">
								<tbody>
									<tr>
										<td width = "20" class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td width = "50" class = "text-right view-key">机构名称：</td>
										<td width ='100' class = "text-left">${account.agency.name}</td>
									</tr>
									<tr>
										<td class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td class = "text-right view-key">联系人：</td>
										<td class = "text-left">${account.agency.directorName}</td>
									</tr>
									<tr>
										<td class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td class = "text-right view-key">手机号码：</td>
										<td class = "text-left">${account.agency.mobilePhone}</td>
									</tr>
									<tr>
										<td class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td class = "text-right view-key">电子邮箱：</td>
										<td class = "text-left">${account.agency.email}</td>
									</tr>
									<tr>
										<td class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td class = "text-right view-key">办公电话：</td>
										<td class = "text-left">${account.agency.officePhone}</td>
									</tr>
									<tr>
										<td class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td class = "text-right view-key">办公传真：</td>
										<td class = "text-left">${account.agency.officeFax}</td>
									</tr>
									<tr>
										<td class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td class = "text-right view-key">联系地址：</td>
										<td class = "text-left">${account.agency.address}</td>
									</tr>
									<tr>
										<td class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td class = "text-right view-key">邮政编码：</td>
										<td class = "text-left">${account.agency.postCode}</td>
									</tr>
								</tbody>
							</table>
			    		</div>
			    	</textarea>
			    	<div id = "view_container" style = "display:none"></div><!-- 模版解析后的容器 -->
			    	<s:form id="modifyRoleForm" theme="simple" action="account/assignRole.action">
					    <s:hidden id="roleIds" name="roleIds" />
					    <s:hidden id="entityId" name="entityId" value="%{entityId}" />
        			</s:form>  
			    </div>
			</div>
			<div class="row">
   		<%@ include file="/jsp/footer.jsp"%>
		<script>
		    seajs.use("js/system/account/administrator/view.js", function(view) {
		         view.init(); 
		    });
		</script>
	</body>
</html>