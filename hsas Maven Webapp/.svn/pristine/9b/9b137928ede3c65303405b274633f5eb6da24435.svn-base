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
			
			<!-- 修改专家 -->
			<div class="col-xs-12">
				<div class="addTable">
					<form action="expert/modify.action" method="post" id="form_expert_application">
						<div id="procedure" class="step_css">
							<ul>
								<li class="proc" name="basic_info"><span class="right_step">基本信息</span><span class="triangle"></span></li>
								<li class="proc" name="expert_info"><span class="right_step">专家信息</span><span class="triangle"></span></li>
								<li class="proc step_oo" name="finish"><span class="right_step">完成</span></li>
							</ul>
						</div>
						
						<s:hidden name="entityId" /> <!-- 专家id -->
						<!-- 基本信息 -->
						<div id="basic_info" style="display:none">
							<table width="100%">
								<tbody>
									<tr>
										<td width="150" class="text-right required-label">姓名：</td>
										<td><s:textfield cssClass="form-control input-sm validate[required, custom[chinese]]" name="person.name" placeholder="请输入"/></td>
										<td width="100"></td>
									</tr>
									<tr>
										<td width="150" class="text-right required-label">性别：</td>
										<td><s:select cssClass="validate[required]" name="person.gender" headerKey="" headerValue="--请选择--" list="#{'男':'男', '女':'女'}" /></td>
										<td width="100"></td>
									</tr>
									
									<tr>
										<td width="150" class="text-right">民族：</td>
										<td><s:select name="person.ethnic" headerKey="" headerValue="--请选择--" list="ethnics"  listKey="name" listValue="name"/></td>
										<td width="100"></td>
									</tr>
									<tr>
										<td width="150" class="text-right required-label">所在机构：</td>
										<td>
											<input class="btn btn-default btn-sm select-agency" type="button" value="选择机构" />
											<div class="selected"></div>
											<s:hidden cssClass="form-control input-sm validate[required]" name="person.agencyName"  value = "1" />
										</td>
										<td width="100"></td>
									</tr>
									<tr>
										<td width="150" class="text-right required-label">出生日期：</td>
										<td>
											<s:textfield cssClass="form-control input-sm datePicker validate[required, custom[date]]" name="person.birthday" placeholder="请输入">
												 <s:param name="value"><s:date name="person.birthday" format="yyyy-MM-dd"/></s:param>
											</s:textfield>
										</td>
									</tr>
									<tr>
										<td width="150" class="text-right">身份证号：</td>
										<td><s:textfield cssClass="form-control input-sm validate[custom[chinaIdLoose]]" name="person.idcardNumber" placeholder="请输入"/></td>
										<td width="100"></td>
									</tr>
									<tr>
										<td width="150" class="text-right required-label">手机：</td>	
										<td><s:textfield cssClass="form-control input-sm validate[required, custom[phone]]" name="person.mobilePhone" placeholder="请输入"/></td>
										<td width="100"></td>
									</tr>
									<tr>
										<td width="150" class="text-right required-label">邮箱：</td>
										<td><s:textfield cssClass="form-control input-sm validate[required, custom[email]]" name="person.email" placeholder="请输入"/></td>
										<td width="100"></td>
									</tr>
									<tr>
										<td width="150" class="text-right">办公电话：</td>
										<td><s:textfield cssClass="form-control input-sm  validate[custom[phone]]" name="person.officePhone" placeholder="请输入"/></td>
										<td width="100"></td>
									</tr>
									<tr>
										<td width="150" class="text-right">家庭电话：</td>
										<td><s:textfield cssClass="form-control input-sm" name="person.homePhone" placeholder="请输入"/></td>
										<td width="100"></td>
									</tr>
									<tr>
										<td width="150" class="text-right">邮编：</td>
										<td><s:textfield cssClass="form-control input-sm validate[custom[chinaZip]]" name="person.postCode" placeholder="请输入"/></td>
										<td width="100"></td>
									</tr>
									<tr>
										<td width="150" class="text-right">联系地址：</td>
										<td><s:textfield cssClass="form-control input-sm" name="person.address" placeholder="请输入"/></td>
										<td width="100"></td>
									</tr>
									<tr>
										<td width="150" class="text-right">个人简介：</td>
										<td><s:textarea cssClass="form-control input-sm" name="person.introduction" rows="5" placeholder="请输入"/></td>
										<td width="100"></td>
									</tr>
								</tbody>
							</table>
						</div>
						
						<!-- 学术信息 -->
						<div id="expert_info" style="display:none">
							<table width="100%">
								<tbody>
									<tr>
										<td width="150" class="text-right required-label">学科门类：</td>
										<td><s:select cssClass="form-control input-sm" name="discipline.name" headerKey="" headerValue="--请选择--" list="disciplines" listKey="name" listValue="name" /></td>
										<td width="100"></td>
									</tr>
									<tr>
										<td width="150" class="text-right required-label">专业职称：</td>
										<td><s:select cssClass="form-control input-sm" name="expert.specialityTitle" headerKey="" headerValue="--请选择--" list="titles" listKey="name" listValue="name"  /></td>
										<td width="100"></td>
									</tr>
									<tr>
										<td width="150" class="text-right">最后学位：</td>
										<td><s:select cssClass="form-control input-sm" name="expert.lastDegree" headerKey="" headerValue="--请选择--" list="#{'学士':'学士', '硕士':'硕士', '博士':'博士'}" /></td>
										<td width="100"></td>
									</tr>
									<tr>
										<td width="150" class="text-right">最后学历：</td>
										<td>
											<s:select cssClass="form-control input-sm" name="expert.lastEducation" headerKey="" headerValue="--请选择--" list="#{'大专':'大专', '本科':'本科', '研究生':'研究生'}" />
										</td>
										<td width="100"></td>
									<tr>
										<td width="150" class="text-right required-label">研究方向：</td>
										<td><s:textfield cssClass="form-control input-sm" name="expert.researchField" placeholder="请输入"/></td>
										<td width="100"></td>
									</tr>
									<tr>
										<td width="150" class="text-right">备注：</td>
										<td><s:textarea cssClass="form-control input-sm " name="expert.note" rows="5" placeholder="请输入" /></td>
										<td width="100"></td>
									</tr>
								</tbody>
							</table>
						</div>
						
						<!-- 按钮组 -->
						<div id="optr" class="text-center">
							<div class="btn-group">
								<input id="prev" class="btn btn-sm btn-default" type="button" style="display: none" value="上一步" />
								<input id="next" class="btn btn-sm btn-default" type="button" style="display: none" value="下一步" />
								<input id="finish" class="btn btn-sm btn-default" type="submit" style="display: none" value="完成" />
								<input id="retry" class="btn btn-sm btn-default" type="button" style="display: none" value="重填" />
								<input id="confirm" class="btn btn-sm btn-default" type="button" style="display: none" value="确定" />
								<input id="cancel" class="btn btn-sm btn-default" type="button" style="display: none" value="取消" />
							</div>
						</div>	
					</form>
				</div>
			</div>
		</div>
		
		<!-- 页脚 -->
		<div class="row">
		<%@ include file="/jsp/footer.jsp"%>
	</body>
	<script>
		seajs.use("js/expert/modify.js", function() {
		});
	</script>
</html>