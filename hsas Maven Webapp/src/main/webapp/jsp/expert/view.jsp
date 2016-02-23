<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page isELIgnored ="true"%><%--添加对EL表达式的支持--%>
<%@ taglib prefix="s" uri="/struts-tags"%> <%--添加对struts标签的支持 --%>
<!DOCTYPE html>
<html>
	<head>
		<title>湖北省社会科学优秀成果奖励申报评审系统</title>
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
				<li><a href="#">专家管理</a></li>
				<li class="active">专家库</li>
			</ol>
			
			<!-- 详情 -->
			<div class="col-xs-12">
				<div class="btn-group pull-right view-controler" role="group" aria-label="...">
					<button type="button" class="btn btn-sm btn-default" id = "view_add">添加</button>
					<button type="button" class="btn btn-sm btn-default" id = "view_mod">修改</button>
					<button type="button" class="btn btn-sm btn-default" id = "view_del">删除</button>
					<button type="button" class="btn btn-sm btn-default" id = "view_prev">上一条</button>
					<button type="button" class="btn btn-sm btn-default" id = "view_next">下一条</button>
					<button type="button" class="btn btn-sm btn-default" id = "view_back">返回</button>
				</div>
				<span class="clearfix"></span><!-- 重要!! 用于清除按键组浮动 -->
				
				<!-- 详情模板 -->
				<s:hidden id="entityId" name="entityId" />
				<textarea id="view_template" style="display:none;"> 
					<!-- 专家基本信息 -->
					<div class="panel panel-default panel-view">
						<div class="panel-heading"><strong>基本信息</strong></div>
						<div class="panel-body">
							<table class="table table-striped view-table">
								<tbody>
									<tr>
										<td width = "50" class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td width = "80" class = "text-right">中文名：</td><td width = "200" class = "text-left">${person.name}</td>
										<td width = "50" class = "text-right"><span></span></td>
										<td width = "80" class = "text-right" ></td><td width = "200" class = "text-left" ></td>
									</tr>
									<tr>
										<td class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td class = "text-right" >性别：</td><td class = "text-left" >${person.gender}</td>
										<td class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td class = "text-right" >民族：</td><td class = "text-left" >${person.ethnic}</td>
									</tr>
									<tr>
										<td class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td class = "text-right" >出生日期：</td><td class = "text-left" >${person.birthday}</td>
										<td class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td class = "text-right">邮箱：</td><td class = "text-left">${person.email}</td>
									</tr>
									<tr>
										<td class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td class = "text-right">办公电话：</td><td class = "text-left">${person.officePhone }</td>
										<td class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td class = "text-right" >家庭电话：</td><td class = "text-left" >${person.homePhone }</td>
									</tr>
									<tr>
										<td class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td class = "text-right" >手机：</td><td class = "text-left" >${person.mobilePhone }</td>
										<td class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td class = "text-right" >邮编：</td><td class = "text-left" >${person.postCode }</td>
									</tr>
									<tr>
										<td class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td class = "text-right">身份证号：</td><td class = "text-left">${person.idcardNumber }</td>
										<td class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td class = "text-right" >机构名称：</td><td class = "text-left" >${person.agencyName}</td>
									</tr>
									<tr>
										<td class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td class = "text-right" >创建时间：</td><td class = "text-left" >${person.createDate }</td>
										<td class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td class = "text-right">更新时间：</td><td class = "text-left">${person.updateDate }</td>
									</tr>
									<tr>
										<td class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td class = "text-right" >联系地址：</td>
										<td class = "text-left" colspan="4">${person.address }</td>
									</tr>
									<tr>
										<td class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td class = "text-right" >个人简介：</td>
										<td class = "text-left" colspan="4">${person.introduction }</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div> <!-- end <div class="panel panel-default"> -->
					
					<!-- 专家学术信息 -->
					<div class="panel panel-default panel-view">
						<div class="panel-heading"><strong>学术信息</strong></div>
						<div class="panel-body">
							<table class="table table-striped view-table">
								<tbody>
									<tr>
										<td width = "50" class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td width = "80" class = "text-right">专家编号：</td><td width = "200" class = "text-left">${expert.number }</td>
										<td width = "50" class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td width = "80" class = "text-right" >专业职称：</td><td width = "200" class = "text-left" >${expert.specialityTitle }</td>
									</tr>
									<tr>
										<td class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td class = "text-right">学术研究方向：</td><td class = "text-left">${expert.researchField }</td>
										<td class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td class = "text-right" >评审专家类型：</td><td class = "text-left" >${expert.reviewerType }</td>
									</tr>
									<tr>
										<td class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td class = "text-right">学科分组：</td><td class = "text-left">${expert.group }</td>
										<td class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td class = "text-right" >学科门类：</td><td class = "text-left" >${expert.discipline.id }/${expert.discipline.name }</td>
									</tr>
									<tr>
										<td class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td class = "text-right">最后学位：</td><td class = "text-left">${expert.lastDegree }</td>
										<td class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td class = "text-right" >最后学历：</td><td class = "text-left" >${expert.lastEducation }</td>
									</tr>
									<tr>
										<td class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td class = "text-right">是否组长：</td><td class = "text-left">${expert.isGroupLeader }</td>
										<td class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td class = "text-right" >是否参评专家：</td><td class = "text-left" >${expert.isReviewer }</td>
									</tr>
									<tr>
										<td class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td class = "text-right">过往参评年份：</td><td class = "text-left">${expert.reviewerYears }</td>
										<td class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td class = "text-right" >推送专家年份：</td><td class = "text-left" >${expert.addYears }</td>
									</tr>
									<tr>
										<td class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td class = "text-right">备注：</td><td class = "text-left" colspan="4">${expert.note }</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div> <!-- end <div class="panel panel-default"> -->
				</textarea>
			
				<!-- 显示详情 -->
				<div id="view_container" style="display:none;"></div>
			</div>
		</div>
		
		<!-- 页脚 -->
		<div class="row">
		<%@ include file="/jsp/footer.jsp"%>
	</body>
	<script>
		seajs.use("js/expert/view.js", function(view) {
			view.init();
		});
	</script>
</html>