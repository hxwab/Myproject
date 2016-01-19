<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page isELIgnored ="true"%><%--添加对EL表达式的支持--%>
<%@ taglib prefix="s" uri="/struts-tags"%> <%--添加对struts标签的支持 --%>
<!DOCTYPE html>
<html>
    <head>
        <title>成果申报</title>
        <%@ include file="/jsp/base.jsp"%>
        <link rel="stylesheet" href="lib/uploadify/css/uploadify.css">
        <link rel="stylesheet" href="lib/bootstrap-datepicker-1.4.0-dist/css/bootstrap-datepicker3.css">
    </head>
    <body>
   		<%@ include file="/jsp/innerNav.jsp"%>
   		<a name="top" id="top"></a>
			<div class="row main-content">
				<ol class="breadcrumb mybreadcrumb">当前位置：
				  <li><a href="#">评奖管理</a></li>
				  <li class="active">成果申报</li>
				</ol>
				
			    <div class="col-xs-12">
				   <div class="addTable">
				    <form action="product/application/modify.action" method="post" id="form_award_application">
				    	<div id="procedure" class="step_css">
							<ul style="left:50%">
								<li class="proc" name="award_info"><span class="right_step">基本信息</span><span class="triangle"></span></li>
								<li class="proc" name="member_info"><span class="right_step">合作者</span><span class="triangle"></span></li>
								<li class="proc" name="other_info"><span class="right_step">成果信息</span><span class="triangle"></span></li>
								<li class="proc step_oo" name="finish"><span class="right_step">完成</span></li>
							</ul>
							<span class="clearfix"></span>
						</div>
						<s:hidden name="entityId" />
						<div id="award_info" style="display:none">
							<table width="100%">
				    			<tbody>
					    			<tr>
					    				<td width="120" class="text-right required-label">成果名称：</td>
					    				<td width="500"><s:textfield type="text" name="product.name" class="form-control input-sm validate[required]" placeholder=""/></td>
					    				<td width="80"></td>
					    			</tr>
					    			<tr id="tr_product_type">
					    				<td class="text-right required-label">成果类型：</td>
					    				<td><s:select id="productType" cssClass="form-control input-sm validate[required]" name="product.type" headerKey="" headerValue="--请选择--" list="#{'著作':'著作', '单篇论文':'单篇论文', '系列论文':'系列论文', '调研报告':'调研报告'}" /></td>
					    				<td></td>
					    			</tr>
					    			<s:if test="product.type =='著作'">
					    			<tr id="tr_research_type" style="display:none;">
					    			</s:if>
					    			<s:else>
					    			<tr id="tr_research_type">
					    			</s:else>
					    				<td class="text-right required-label">研究类型：</td>
					    				<td><s:select id="productResearchType" cssClass="form-control input-sm" name="product.researchType" headerKey="" headerValue="--请选择--" list="#{'基础类':'基础类', '应用对策类':'应用对策类'}" /></td>
					    				<td></td>
					    			</tr>
					    			<tr>
					    				<td class="text-right required-label">学科分组：</td>
					    				<td><s:select cssClass="form-control input-sm validate[required]" name="groupName" headerKey="" headerValue="--请选择--" list="groupNames" /></td>
					    				<td></td>
					    			</tr>
					    			<tr>
					    				<td class="text-right ">出版社或发表刊物名称：</td>
					    				<td><s:textfield  type="text" name="product.publishName" class="form-control input-sm " placeholder=""/></td>
					    				<td></td>
					    			</tr>
					    			<tr>
					    				<td class="text-right ">出版社或发表刊物级别：</td>
					    				<td>
					    					<s:if test="product.type =='著作'">
					    					<s:select id="publishLevel" cssClass="form-control input-sm j-productPublishLevel" name="product.publishLevel" headerKey="" headerValue="--请选择--" list="#{'国家级':'国家级', '省级':'省级','一般':'一般'}" />
					    					<s:select id="paperLevel" cssClass="form-control input-sm j-productPublishLevel display-none"  headerKey="" headerValue="--请选择--" list="#{'权威':'权威', '核心':'核心','一般':'一般'}" />
					    					</s:if>
					    					<s:else>
					    					<s:select id="publishLevel" cssClass="form-control input-sm j-productPublishLevel display-none"  headerKey="" headerValue="--请选择--" list="#{'国家级':'国家级', '省级':'省级','一般':'一般'}" />
					    					<s:select id="paperLevel" cssClass="form-control input-sm j-productPublishLevel" name="product.publishLevel" headerKey="" headerValue="--请选择--" list="#{'权威':'权威', '核心':'核心','一般':'一般'}" />
					    					</s:else>
					    				</td>
					    				<td></td>
					    			</tr>
					    			<tr>
					    				<td class="text-right ">出版或发表时间：</td>
					    				<td>
					    					<s:textfield type="text" name="product.publishDate"  class="form-control input-sm publishDate validate[custom[date]]" placeholder="">
					    						<s:param name="value"><s:date name="person.publishDate" format="yyyy-MM-dd"/></s:param>
					    					</s:textfield>
					    				</td>
					    				<td></td>
					    			</tr>
					    			<tr>
					    				<td class="text-right required-label">作者姓名：</td>
					    				<td><s:textfield  type="text" name="person.name" class="form-control input-sm validate[required]" placeholder=""/></td>
					    				<td></td>
					    			</tr>
					    			<tr>
					    				<td class="text-right required-label">性别：</td>
					    				<td><s:select cssClass="form-control input-sm validate[required]" name="person.gender" headerKey="" headerValue="--请选择--" list="#{'男':'男', '女':'女'}" /></td>
					    				<td></td>
					    			</tr>
					    			<tr>
					    				<td class="text-right required-label">出生日期：</td>
					    				<td>
					    					<s:textfield  type="text" name="person.birthday" class="form-control input-sm birthday validate[required,custom[date]]" placeholder="">
					    						<s:param name="value"><s:date name="person.birthday" format="yyyy-MM-dd"/></s:param>
					    					</s:textfield>
					    				</td>
					    				<td></td>
					    			</tr>
					    			<tr>
					    				<td class="text-right required-label">单位：</td>
					    				<td>
					    					<button class="btn btn-default  select-agency">选择</button>
					    					<div class="selected">
					    						<div class="selected-link">
													<span class="label label-primary" id=""><s:property value="agencyName1" /></span>
													<div class="delete-link"><i class="fa fa-times"></i></div>
												</div>
					    					</div>
					    					<s:hidden id="product_agencyId" name="agencyId1" />
					    					<s:hidden id="product_agencyName" name="agencyName1" />
					    					<span style="margin-left:20px;">院系：</span><s:textfield type="text" name="person.department" cssClass="input-sm" size="30" placeholder="高校机构需填写" />
					    				</td>
					    				<td></td>
					    			</tr>
					    			<tr>
					    				<td class="text-right required-label">邮箱：</td>
					    				<td><s:textfield  type="text" name="person.email" class="form-control input-sm validate[required,custom[email]]" placeholder=""/></td>
					    				<td></td>
					    			</tr>
					    			<tr>
					    				<td class="text-right required-label">手机：</td>
					    				<td><s:textfield  type="text" name="person.mobilePhone" class="form-control input-sm validate[required,custom[cellphone]]" placeholder=""/></td>
					    				<td></td>
					    			</tr>
					    			<tr>
					    				<td class="text-right">民族：</td>
					    				<td><s:select cssClass="form-control input-sm" name="person.ethnic" headerKey="" headerValue="--请选择--" list="ethnics"  /></td>
					    				<td></td>
					    			</tr>
					    			<tr>
					    				<td class="text-right ">职务：</td>
					    				<td><s:textfield  type="text" name="position1" class="form-control input-sm " placeholder=""/></td>
					    				<td></td>
					    			</tr>
					    			<tr>
					    				<td class="text-right">身份证号：</td>
					    				<td><s:textfield  type="text" name="person.idCardNumber" class="form-control input-sm validate[custom[chinaId]]" placeholder=""/></td>
					    				<td></td>
					    			</tr>
					    			<tr>
					    				<td class="text-right">联系地址：</td>
					    				<td><s:textfield  type="text" name="person.address" class="form-control input-sm " placeholder=""/></td>
					    				<td></td>
					    			</tr>
					    			<tr>
					    				<td class="text-right">邮编：</td>
					    				<td><s:textfield  type="text" name="person.postCode" class="form-control input-sm  validate[custom[chinaZip]]" placeholder=""/></td>
					    				<td></td>
					    			</tr>
					    			<tr>
					    				<td class="text-right ">参与工作：</td>
					    				<td><s:textarea type="text" name="workDivision1" class="form-control input-sm " placeholder=""></s:textarea></td>
					    				<td></td>
					    			</tr>
					    			<tr>
					    				<td class="text-right ">个人简介：</td>
					    				<td><s:textarea type="text" name="person.introduction" class="form-control input-sm " placeholder=""></s:textarea></td>
					    				<td></td>
					    			</tr>
								</tbody>
				    		</table>
						</div>
						
						<div id="member_info" style="display:none">
							<div class="panel panel-default panel-view switch">
								<div class="panel-heading">
									<span class="label label-primary label-custom">合作者 <span class="member-count">1</span></span>
									<div class = "btn-group pull-right">
										<button class="btn btn-default  member-add">添加</button>
									</div>
									<span class="clearfix"></span>
								</div>
							</div>
							<s:iterator value="%{members}" status="stat" var="list1">
								<div class="panel panel-default panel-view member">
								 <div class="panel-heading">
								 	<span class="label label-primary label-custom">合作者 <span class="member-count"><s:property value="#stat.index + 1"/></span></span>
								 	<div class = "btn-group pull-right">
										<button class="btn btn-default  member-add">添加</button>
										<button class="btn btn-default  member-delete">删除</button>
									</div>
									<span class="clearfix"></span>
								</div>
							  	<div class="panel-body" style="padding:15px">
							  	
									<table width="100%">
						    			<tbody>
							    			<tr>
							    				<td width="120" class="text-right required-label">姓名：</td>
							    				<td>
							    					<s:textfield cssClass="form-control input-sm validate[required]" placeholder="">
							    						<s:param name="name">member<s:property value="#stat.index + 2"/>.name</s:param>
							    						<s:param name="value"><s:property value="%{members[#stat.index].name}"/></s:param>
							    					</s:textfield>
							    				</td>
							    				<td width="80" class="text-right "></td>
							    				<td width="120" class="text-right required-label">性别：</td>
							    				<td>
							    					<s:select cssClass="form-control input-sm validate[required]" headerKey="" headerValue="--请选择--" list="#{'男':'男', '女':'女'}">
							    						<s:param name="name">member<s:property value="#stat.index + 2"/>.gender</s:param>
							    						<s:param name="value"><s:property value="%{members[#stat.index].gender}"/></s:param>
							    					</s:select> 
							    				</td>
							    				<td width="80" class="text-right "></td>
							    			</tr>
							    			<tr>
							    				<td class="text-right">民族：</td>
							    				<td>
							    					<s:select cssClass="form-control input-sm" name="" headerKey="" headerValue="--请选择--" list="ethnics">
							    						<s:param name="name">member<s:property value="#stat.index + 2"/>.ethnic</s:param>
							    						<s:param name="value"><s:property value="%{members[#stat.index].ethnic}"/></s:param>
							    					</s:select>
							    				</td>
							    				<td class="text-right "></td>
							    				<td class="text-right ">身份证号：</td>
							    				<td>
							    					<s:textfield cssClass="form-control input-sm validate[custom[chinaId]]" placeholder="">
							    						<s:param name="name">member<s:property value="#stat.index + 2"/>.idCardNumber</s:param>
							    						<s:param name="value"><s:property value="%{members[#stat.index].idCardNumber}"/></s:param>
							    					</s:textfield>
							    				</td>
							    				<td class="text-right "></td>
							    			</tr>
							    			<tr>
							    				<td class="text-right required-label">出生日期：</td>
							    				<td>
							    					<s:textfield cssClass="form-control input-sm birthday validate[required,custom[date]]" placeholder="">
							    						<s:param name="name">member<s:property value="#stat.index + 2"/>.birthday</s:param>
							    						<s:param name="value"><s:date name="%{members[#stat.index].birthday}" format="yyyy-MM-dd"/></s:param>
							    					</s:textfield>
							    				</td>
							    				<td class="text-right "></td>
							    				<td class="text-right required-label">Email：</td>
							    				<td>
							    					<s:textfield cssClass="form-control input-sm validate[required,custom[email]]" placeholder="">
							    						<s:param name="name">member<s:property value="#stat.index + 2"/>.email</s:param>
							    						<s:param name="value"><s:property value="%{members[#stat.index].email}"/></s:param>
							    					</s:textfield>
							    				</td>
							    				<td class="text-right "></td>
							    			</tr>
							    			<tr>
							    				<td class="text-right">手机：</td>
							    				<td>
							    					<s:textfield cssClass="form-control input-sm validate[custom[cellphone]]" placeholder="">
							    						<s:param name="name">member<s:property value="#stat.index + 2"/>.mobilePhone</s:param>
							    						<s:param name="value"><s:property value="%{members[#stat.index].mobilePhone}"/></s:param>
							    					</s:textfield>
							    				</td>
							    				<td class="text-right "></td>
							    				<td class="text-right">联系地址：</td>
							    				<td>
							    					<s:textfield cssClass="form-control input-sm" placeholder="">
							    						<s:param name="name">member<s:property value="#stat.index + 2"/>.address</s:param>
							    						<s:param name="value"><s:property value="%{members[#stat.index].address}"/></s:param>
							    					</s:textfield>
							    				</td>
							    				<td class="text-right "></td>
							    			</tr>
							    			<tr>
							    				<td class="text-right">邮编：</td>
							    				<td>
							    					<s:textfield cssClass="form-control input-sm validate[custom[chinaZip]]" placeholder="">
							    						<s:param name="name">member<s:property value="#stat.index + 2"/>.postCode</s:param>
							    						<s:param name="value"><s:property value="%{members[#stat.index].postCode}"/></s:param>
							    					</s:textfield>
							    				</td>
							    				<td class="text-right "></td>
							    				<td class="text-right required-label">所属机构：</td>
							    				<td>
							    					<button class = "btn btn-default  select-agency">选择</button>
							    					<div class="selected">
							    						<s:if test="members[#stat.index].agency.id != null">
							    						<div class="selected-link"><span class="label label-primary" id="<s:property value='%{members[#stat.index].agency.id}'/>"><s:property value='%{members[#stat.index].agencyName}'/></span><div class="delete-link"><i class="fa fa-times"></i></div></div>
							    						</s:if>
							    					</div>
							    					<input type="hidden" name="agencyId<s:property value="#stat.index + 2"/>" class="form-control input-sm " placeholder=""  value="<s:property value='%{members[#stat.index].agency.id}'/>"/>
							    					<input type="hidden" name="agencyName<s:property value="#stat.index + 2"/>" class="form-control input-sm " placeholder=""  value="<s:property value='%{members[#stat.index].agencyName}'/>"/>
							    				</td>
							    				<td class="text-right "></td>
							    			</tr>
							    			<tr>
							    				<td class="text-right ">分工情况：</td>
							    				<td>
							    					<s:textfield cssClass="form-control input-sm" placeholder="">
							    						<s:param name="name">member<s:property value="#stat.index + 2"/>.workDivision</s:param>
							    						<s:param name="value"><s:property value="%{members[#stat.index].workDivision}"/></s:param>
							    					</s:textfield>
							    				</td>
							    				<td class="text-right "></td>
							    				<td class="text-right ">职务：</td>
							    				<td>
							    					<s:textfield cssClass="form-control input-sm" placeholder="">
							    						<s:param name="name">member<s:property value="#stat.index + 2"/>.position</s:param>
							    						<s:param name="value"><s:property value="%{members[#stat.index].position}"/></s:param>
							    					</s:textfield>
							    				</td>
							    				<td class="text-right "></td>
							    			</tr>
							    		</tbody>
						    		</table>
					    		</div>
					    		</div>
							</s:iterator>
						</div>
						
						<div id="other_info" style="display:none">
							<table width="100%">
								<tbody>
									<tr>
					    				<td width="120" class="text-right required-label">资格审核材料上传：</td>
				    					<td width="500" class='position-relative' >
				    						<label class="sr-only" for="inputfile">文件输入</label>
				    						<input type="file" id="file1_<s:property value='entityId' />" name="product.file" class='validate[required]'>
				    						<span style="font-size:12px;color:#FD5B78;padding-left:10px;"><a id="upload_file_Manual" href="javascript:void();">上传说明</a></span>
				    					</td>
				    					<td width="80"></td>
					    			</tr>
					    			<tr>
					    				<td width="120" class="text-right required-label">成果附件上传：</td>
				    					<td width="500" class='position-relative' >
				    						<label class="sr-only" for="inputfile">文件输入</label>
				    						<input type="file" id="file2_<s:property value='entityId' />" name="product.file" class='validate[required]'>
				    						<span style="font-size:12px;color:#FD5B78;padding-left:10px;"><a id="upload_file_Manual" href="javascript:void();">上传说明</a></span>
				    					</td>
				    					<td width="80"></td>
					    			</tr>
					    			<tr>
					    				<td width="120" class="text-right required-label" style="vertical-align:top">成果内容简介：<br><span style="font-size:10px;">（已输入<span class="count" style="color:red;">0</span>个字符）<span></td>
					    				<td width="500"><s:textarea id="introduction" type="text" name="product.introduction" cssClass="form-control input-sm countWords" rows="10" placeholder="（要点提示：300-400字，便于成果介绍和推广）"></s:textarea></td>
					    				<td width="80"></td>
					    			</tr>
									<tr>
					    				<td class="text-right required-label" style="vertical-align:top">成果内容提要：<br><span style="font-size:10px;">（已输入<span class="count" style="color:red;">0</span>个字符）<span></td>
					    				<td><s:textarea id="abstractStr" type="text" name="product.abstractStr" cssClass="form-control input-sm countWords" rows="10" placeholder="（要点提示：1000字左右，成果的主要观点、理论价值、应用价值、学术创新等）"></s:textarea></td>
					    				<td></td>
					    			</tr>
					    			<tr>
					    				<td class="text-right required-label" style="vertical-align:top">成果社会反映：<br><span style="font-size:10px;">（已输入<span class="count" style="color:red;">0</span>个字符）<span></td>
					    				<td><s:textarea id="socialEffect" type="text" name="product.socialEffect" cssClass="form-control input-sm countWords" rows="10" placeholder="（要点提示：书评情况、全文转载、论点摘编情况、引用情况、成果获奖情况等，附相关材料复印件）"></s:textarea></td>
					    				<td></td>
					    			</tr>
								</tbody>
							</table>
						</div>
						
				    	<div id="optr" class="text-center">
				    		<div class="btn-group">
								<input id="prev" class="btn  btn-default" type="button" style="display: none" value="上一步" />
								<input id="next" class="btn  btn-default" type="button" style="display: none" value="下一步" />
								<input id="finish" class="btn  btn-default" type="button" style="display: none" value="提交" />
								<input id="retry" class="btn  btn-default" type="button" style="display: none" value="重填" />
								<input id="tempSubmit" class="btn  btn-default" type="button" style="display: none" value="暂存" />
								<input id="cancel" class="btn  btn-default" type="button" style="display: none" value="取消" />
							</div>
						</div>	
				   		</form>
				   		<div id="member_template" style="display:none">
							<div class="panel panel-default panel-view member">
							 <div class="panel-heading">
							 	<span class="label label-primary label-custom">合作者 <span class="member-count">${memberCount }</span></span>
							 	<div class = "btn-group pull-right">
									<button class="btn btn-default  member-add">添加</button>
									<button class="btn btn-default  member-delete">删除</button>
								</div>
								<span class="clearfix"></span>
							</div>
						  	<div class="panel-body" style="padding:15px">
								<table width="100%">
					    			<tbody>
						    			<tr>
						    				<td width="120" class="text-right required-label">姓名：</td>
						    				<td><input type="text" name="${member}.name" class="form-control input-sm validate[required]" placeholder=""></td>
						    				<td width="80" class="text-right "></td>
						    				<td width="120" class="text-right required-label">性别：</td>
						    				<td><s:select cssClass="form-control input-sm validate[required]" name="${member}.gender" headerKey="" headerValue="--请选择--" list="#{'男':'男', '女':'女'}" /></td>
						    				<td width="80" class="text-right "></td>
						    			</tr>
						    			<tr>
						    				<td class="text-right">民族：</td>
						    				<td><s:select cssClass="form-control input-sm" name="${member}.ethnic" headerKey="" headerValue="--请选择--" list="ethnics" /></td>
						    				<td class="text-right "></td>
						    				<td class="text-right ">身份证号：</td>
						    				<td><input type="text" name="${member}.idCardNumber" class="form-control input-sm validate[custom[chinaId]]" placeholder=""></td>
						    				<td class="text-right "></td>
						    			</tr>
						    			<tr>
						    				<td class="text-right required-label">出生日期：</td>
						    				<td><input type="text" name="${member}.birthday" class="form-control input-sm birthday validate[required,custom[date]]" placeholder=""></td>
						    				<td class="text-right "></td>
						    				<td class="text-right required-label">Email：</td>
						    				<td><input type="text" name="${member}.email" class="form-control input-sm validate[required,custom[email]]" placeholder=""></td>
						    				<td class="text-right "></td>
						    			</tr>
						    			<tr>
						    				<td class="text-right">手机：</td>
						    				<td><input type="text" name="${member}.mobilePhone" class="form-control input-sm validate[custom[cellphone]]" placeholder=""></td>
						    				<td class="text-right "></td>
						    				<td class="text-right">联系地址：</td>
						    				<td><input type="text" name="${member}.address" class="form-control input-sm " placeholder=""></td>
						    				<td class="text-right "></td>
						    			</tr>
						    			<tr>
						    				<td class="text-right">邮编：</td>
						    				<td><input type="text" name="${member}.postCode" class="form-control input-sm validate[custom[chinaZip]]" placeholder=""></td>
						    				<td class="text-right "></td>
						    				<td class="text-right required-label">所属机构：</td>
						    				<td>
						    					<button class = "btn btn-default  select-agency">选择</button>
						    					<div class="selected"></div>
						    					<input type="hidden" name="agencyId${parseInt(memberCount) +1}" class="form-control input-sm " placeholder=""/>
						    					<input type="hidden" name="agencyName${parseInt(memberCount) +1}" class="form-control input-sm " placeholder=""/>
						    				</td>
						    				<td class="text-right "></td>
						    			</tr>
						    			<tr>
						    				<td class="text-right ">分工情况：</td>
						    				<td><input type="text" name="workDivision${parseInt(memberCount)+1 }" class="form-control input-sm " placeholder=""/></td>
						    				<td class="text-right "></td>
						    				<td class="text-right ">职务：</td>
						    				<td><input type="text" name="position${parseInt(memberCount) +1}" class="form-control input-sm " placeholder=""/></td>
						    				<td class="text-right "></td>
						    			</tr>
					    			</tbody>
					    		</table>
				    		</div>
				    		</div>
						</div>
				    </div>
			    </div>
			</div>
			<div class="row">
   		<%@ include file="/jsp/footer.jsp"%>
   		
		<script>
		    seajs.use("js/product/application/modify.js", function(modify) {
		    	modify.init();
		    });
		</script>
	</body>
</html>