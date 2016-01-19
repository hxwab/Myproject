<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page isELIgnored="true"%><%--添加对EL表达式的支持--%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%--添加对struts标签的支持 --%>
<!DOCTYPE html>
<html>
<head>
	<title>湖北省社会科学优秀成果奖申报评审系统</title>
	<%@ include file="/jsp/base.jsp"%>
</head>
<body>
	<%@ include file="/jsp/innerNav.jsp"%>
	<a name="top" id="top"></a>
	<div class="row mySlide">
		<ol class="breadcrumb mybreadcrumb">
			当前位置：
			<li><a href="#">个人空间</a>
			</li>
			<li class="active">账号信息</li>
		</ol>
		<div class="col-xs-12">
			<div class="panel panel-default panel-view">
				<div class="panel-heading">
					<strong>基本信息</strong>
					<div class="btn-group">
						<a href="personInfo/toModify.action" class="btn btn-sm btn-default">修改个人信息</a>
						<a href="personInfo/toModifyPassword.action" class="btn btn-sm btn-default">修改密码</a>
					</div>
				</div>
				<div class="panel-body">
					<textarea id="view_template_basicInfo" style="display:none"><!-- 前台模版 -->
						<table class="table table-striped view-table">
							<tbody>
								<tr>
									<td width="30" class="text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
									<td width="80" class="text-right view-key">用户名：</td>
									<td width="180" class="text-left">${account.username}</td>
									<td width="30" class="text-right"><span></span></td>
									<td width="80" class="text-right"></td>
									<td width="180" class="text-left"></td>
								</tr>
								<tr>
									<td class="text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
									<td class="text-right view-key">中文名：</td>
									<td class="text-left">${account.person.name}</td>
									<td class="text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
									<td class="text-right view-key">性别：</td>
									<td class="text-left">${account.person.gender}</td>
								</tr>
								<tr>
									<td class="text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
									<td class="text-right view-key">账号状态：</td>
									<td class="text-left">{if account.status == 1}启用{else}停用{/if}</td>
									<td class="text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
									<td class="text-right view-key">所在单位：</td>
									<td class="text-left">${account.person.agencyName}</td>
								</tr>
								<tr>
									<td class="text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
									<td class="text-right view-key">常用邮箱：</td>
									<td class="text-left">${account.person.email}</td>
									<td class="text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
									<td class="text-right view-key">联系电话：</td>
									<td class="text-left">${account.person.mobilePhone}</td>
								</tr>
							</tbody>
						</table>
					</textarea>
					<div id = "view_container_basicInfo" style = "display:none"></div><!-- 模版解析后的容器 -->
				</div>
			</div>
			<!-- end <div class="panel panel-default"> -->

			<div class="panel panel-default panel-view">
				<div class="panel-heading">
					<strong>申报信息</strong>
				</div>
				<div class="panel-body">
					<textarea id="view_template_product" style="display:none"><!-- 前台模版 -->
						<table class="table table-bordered">
							<thead>
								<tr>
									<th width="20" class="text-center">序号</th>
									<th width="150">成果名称</th>
									<th width="20">作者</th>
									<th width="20">成果类型</th>
									<th width="60">分组</th>
									<th width="70">社科联审核状态</th>
									<th width="40">申请年份</th>
									<th width="40">状态</th>
									<th width="50">下载申报表</th>
								</tr>
							</thead>
							{for item in products}
								<tr>
									<td class="text-center">${parseInt(item_index)+1}</td>
									<td><a href="product/application/toView.action?entityId=${item[0]}">${item[1]}</a></td>
									<td>${item[2]}</td>
									<td>${item[3]}</td>
									<td>${item[4]}</td>
									<td>
									{if item[5]==1}
										<span class="red"><i class="fa fa-exclamation-triangle"></i>&nbsp;不同意</span>
									{elseif item[5]==2}
										<span class="green"><i class="fa fa-check-circle"></i>&nbsp;同意</span>
									{elseif item[5]==3}退回
									{else}未审核
									{/if}
									</td>
									<td>${item[6]}</td>
									<td>
									{if item[7]==1}暂存
									{elseif item[7]==2}提交
									{elseif item[7]==3}修改中
									{elseif item[7]}默认
									{/if}
									</td>
									<td>
									{if item[5] == 2}
									<a class="btn btn-sm btn-primary" href="productView/exportProduct.action?entityId=${item[0]}">下载</a>
									{/if}
									</td>
								</tr>
							{/for}
						</table>
					</textarea>
					<div id = "view_container_product" style = "display:none"></div><!-- 模版解析后的容器 -->
				</div>
			</div>
			<!-- end <div class="panel panel-default"> -->
		</div>
	</div>
	
	<!--页脚 -->
	<div class="row">
	<%@ include file="/jsp/footer.jsp"%>
</body>
<script>
	seajs.use('js/selfspace/view', function(view){
		view.init();
	})
</script>
</html>