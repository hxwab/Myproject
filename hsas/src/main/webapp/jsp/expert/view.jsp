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
					<button type="button" class="btn  btn-default" id = "view_add">添加</button>
					<button type="button" class="btn  btn-default" id = "view_mod">修改</button>
					<button type="button" class="btn  btn-default" id = "view_del">删除</button>
					<button type="button" class="btn  btn-default" id = "view_prev">上一条</button>
					<button type="button" class="btn  btn-default" id = "view_next">下一条</button>
					<button type="button" class="btn  btn-default" id = "view_back">返回</button>
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
										<td width = "80" class = "text-right view-key">中文名：</td><td width = "200" class = "text-left">${person.name}</td>
										<td width = "50" class = "text-right"><span></span></td>
										<td width = "80" class = "text-right" ></td><td width = "200" class = "text-left" ></td>
									</tr>
									<tr>
										<td class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td class = "text-right view-key" >性别：</td><td class = "text-left" >${person.gender}</td>
										<td class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td class = "text-right view-key" >民族：</td><td class = "text-left" >${person.ethnic}</td>
									</tr>
									<tr>
										<td class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td class = "text-right view-key" >出生日期：</td><td class = "text-left" >${person.birthday}</td>
										<td class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td class = "text-right view-key">邮箱：</td><td class = "text-left">${person.email}</td>
									</tr>
									<tr>
										<td class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td class = "text-right view-key">办公电话：</td><td class = "text-left">${person.officePhone }</td>
										<td class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td class = "text-right view-key" >家庭电话：</td><td class = "text-left" >${person.homePhone }</td>
									</tr>
									<tr>
										<td class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td class = "text-right view-key" >手机：</td><td class = "text-left" >${person.mobilePhone }</td>
										<td class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td class = "text-right view-key" >邮编：</td><td class = "text-left" >${person.postCode }</td>
									</tr>
									<tr>
										<td class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td class = "text-right view-key">身份证号：</td><td class = "text-left">${person.idcardNumber }</td>
										<td class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td class = "text-right view-key" >单位：</td><td class = "text-left" >${person.agencyName}</td>
									</tr>
									<tr>
										<td class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td class = "text-right view-key" >创建时间：</td><td class = "text-left" >${person.createDate }</td>
										<td class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td class = "text-right view-key">更新时间：</td><td class = "text-left">${person.updateDate }</td>
									</tr>
									<tr>
										<td class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td class = "text-right view-key" >联系地址：</td>
										<td class = "text-left" colspan="4">${person.address }</td>
									</tr>
									<tr>
										<td class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td class = "text-right view-key" >个人简介：</td>
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
										<td width = "80" class = "text-right view-key">专家编号：</td><td width = "200" class = "text-left">${expert.number }</td>
										<td width = "50" class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td width = "80" class = "text-right view-key" >专业职称：</td><td width = "200" class = "text-left" >${expert.specialityTitle }</td>
									</tr>
									<tr>
										<td class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td class = "text-right view-key">学术研究方向：</td><td class = "text-left">${expert.researchField }</td>
										<td class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td class = "text-right view-key" >录入年份：</td><td class = "text-left" >${person.createDate}</td>
									</tr>
									<tr>
										<td class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td class = "text-right view-key">学科分组：</td><td class = "text-left">${expert.groups.name }</td>
										<td class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td class = "text-right view-key" >学科门类：</td><td class = "text-left" >${expert.discipline.name }</td>
									</tr>
									<tr>
										<td class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td class = "text-right view-key">最后学位：</td><td class = "text-left">${expert.lastDegree }</td>
										<td class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td class = "text-right view-key" >最后学历：</td><td class = "text-left" >${expert.lastEducation }</td>
									</tr>
									<tr>
										<td class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td class = "text-right view-key" >参评资格：</td><td class = "text-left" >{if expert.isReviewable==1}是{else}否{/if}</td>
										<td class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td class = "text-right view-key">过往参评年份：</td><td class = "text-left">${expert.reviewerYears }</td>
									</tr>
									<tr>
										<td class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td class = "text-right view-key">备注：</td><td class = "text-left" colspan="4">${expert.note }</td>
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