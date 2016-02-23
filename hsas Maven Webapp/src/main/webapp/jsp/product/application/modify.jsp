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
				
				<s:debug></s:debug>
			    <div class="col-xs-10">
				   <div class="addTable">
				   
				    	<form action="product/modify.action" method="post" id="form_award_application">
				    	<div id="procedure" class="step_css">
							<ul>
								<li class="proc" name="award_info"><span class="right_step">成果信息</span><span class="triangle"></span></li>
								<li class="proc" name="person_info"><span class="right_step">第一作者信息</span><span class="triangle"></span></li>
								<li class="proc" name="member_info"><span class="right_step">合作者</span><span class="triangle"></span></li>
								<li class="proc step_oo" name="finish"><span class="right_step">完成</span></li>
							</ul>
							<span class="clearfix"></span>
						</div>
						<div id="award_info" style="display:none">
							<table width="100%">
				    			<tbody>

					    			<tr>
					    				<td width="80" class="text-right required-label">成果名称：</td>
					    				<td><s:textfield type="text" name="product.name" class="form-control input-sm validate[required]" placeholder=""/></td>
					    			</tr>
					    			<tr>
					    				<td width="80" class="text-right required-label">第一作者：</td>
					    				<td><s:textfield  type="text" name="product.authorName" class="form-control input-sm validate[required]" placeholder=""/></td>
					    			</tr>
					    			<tr>
					    				<td width="80" class="text-right required-label">成果类型：</td>
					    				<td>
					    					<select name="product.type" class="form-control input-sm validate[required]" placeholder="" data="<s:property value='product.type'/>">
						    					<option value="">--- 请选择 ---</option>
						    					<option value="著作">著作</option>
						    					<option value="单篇论文">单篇论文</option>
						    					<option value="系列论文">系列论文</option>
						    					<option value="调研报告">调研报告</option>
					    					</select>
					    				</td>
					    			</tr>
					    			<tr>
					    				<td width="80" class="text-right required-label">研究类型：</td>
					    				<td>
					    				<select type="text" name="product.researchType" class="form-control input-sm validate[required]" placeholder="" data="<s:property value='product.researchType'/>">
					    					<option value="">--- 请选择 ---</option>
					    					<option value="基础类">基础类</option>
					    					<option value="应用对策类">应用对策类</option>
					    				</select>
					    				</td>
					    			</tr>
					    			<tr>
					    				<td width="80" class="text-right required-label">学科分组：</td>
					    				<td>
					    					<select name="disciplineName" class="form-control input-sm validate[required]" placeholder="" data="<s:property value='disciplineName'/>">
												<option value="">--- 请选择 ---</option>
												<option value="0">马克思主义与党建（社科）</option>
												<option value="1">经济理论学</option>
												<option value="2">应用经济学</option>
												<option value="3">哲学与社会学</option>
												<option value="4">历史学（考古学）</option>
												<option value="5">语言文字（文化研究，新闻学，图书情报学）</option>
												<option value="6">法学（政治学）</option>
												<option value="7">综合一组（教育学，体育学等）</option>
												<option value="8">综合二组（民族学，宗教学，艺术学，交叉学科等）</option>
												<option value="9">综合三组（市州及其他）</option>
					    					</select>
					    				</td>
					    			</tr>
					    			<tr>
					    				<td width="80" class="text-right required-label">依托机构：</td>
					    				<td>
					    					<button class="btn btn-default btn-sm select-agency">选择</button>
					    					<div class="selected">
					    					<s:if test="product.agency">
					    						<div class="selected-link"><span class="label label-primary" id="<s:property value='product.agency.id'/>"><s:property value='product.agency.name'/></span><div class="delete-link"><i class="fa fa-times"></i></div></div>
					    					</s:if>
					    					</div>
					    					<input type="hidden" name="product.agencyId" class="form-control input-sm validate[required]"  placeholder="" value="<s:property value='product.agency.id'/>">
					    				</td>
					    			</tr>
					    			
					    			<tr>
					    				<td width="180" class="text-right ">出版社或发表刊物名称：</td>
					    				<td><s:textfield  type="text" name="product.publishName" class="form-control input-sm " placeholder=""/></td>
					    			</tr>
					    			<tr>
					    				<td width="140" class="text-right ">出版社或发表刊物级别：</td>
					    				<td><s:textfield  type="text" name="product.publishLevel" class="form-control input-sm " placeholder=""/></td>
					    			</tr>
					    			<tr>
					    				<td width="80" class="text-right ">出版或发表时间：</td>
					    				<td><s:textfield type="text" name="product.publishDate"  class="form-control input-sm dataPicker validate[custom[date]]" placeholder=""/></td>
					    			</tr>
					    			<tr>
					    				<td width="80" class="text-right ">成果社会反映：</td>
					    				<td><s:textfield  type="text" name="product.socialEffect" class="form-control input-sm  " placeholder=""/></td>
					    			</tr>
					    			<tr>
					    				<td width="80" class="text-right ">推荐意见：</td>
					    				<td><s:textfield  type="text" name="product.recommendation" class="form-control input-sm " placeholder=""/></td>
					    			</tr>
					    			<tr>
					    				<td width="80" class="text-right " style="vertical-align:top">成果概要：</td>
					    				<td><s:textarea type="text" name="product.abstractStr" class="form-control input-sm" placeholder=""></s:textarea></td>
					    			</tr>
					    			<tr>
					    				<td width="80" class="text-right " style="vertical-align:top">成果简介：</td>
					    				<td><s:textarea type="text" name="product.introduction" class="form-control input-sm" placeholder=""></s:textarea></td>
					    			</tr>
				    			</tbody>
				    		</table>
						</div>
						<div id="person_info" style="display:none">
							<table width="100%">
				    			<tbody>
					    			<tr>
					    				<td width="80" class="text-right required-label">姓名：</td>
					    				<td><s:textfield  type="text" name="person.name" class="form-control input-sm validate[required]" placeholder=""/></td>
					    			</tr>
					    			<tr>
					    				<td width="80" class="text-right required-label">性别：</td>
					    				<td>
						    				<select type="text" name="person.gender" class="form-control input-sm validate[required]" placeholder="" data="<s:property value='person.gender'/>">
							    				<option>---请选择---</option>
							    				<option value="男">男</option>
							    				<option value="女">女</option>
							    			</select>
						    			</td>
					    			</tr>
					    			<tr>
					    				<td width="80" class="text-right required-label">民族：</td>
					    				<td>
						    				<select type="text" name="person.ethnic" class="form-control input-sm validate[required]" placeholder="" data="<s:property value='person.ethnic'/>">
											    <option value="">--请选择民族--</option>
											    <option value="汉族">汉族</option>
											    <option value="蒙古族">蒙古族</option>
											    <option value="回族">回族</option>
											    <option value="藏族">藏族</option>
											    <option value="维吾尔族">维吾尔族</option>
											    <option value="苗族">苗族</option>
											    <option value="彝族">彝族</option>
											    <option value="壮族">壮族</option>
											    <option value="布依族">布依族</option>
											    <option value="朝鲜族">朝鲜族</option>
											    <option value="满族">满族</option>
											    <option value="侗族">侗族</option>
											    <option value="瑶族">瑶族</option>
											    <option value="白族">白族</option>
											    <option value="土家族">土家族</option>
											    <option value="哈尼族">哈尼族</option>
											    <option value="哈萨克族">哈萨克族</option>
											    <option value="傣族">傣族</option>
											    <option value="黎族">黎族</option>
											    <option value="傈僳族">傈僳族</option>
											    <option value="佤族">佤族</option>
											    <option value="畲族">畲族</option>
											    <option value="高山族">高山族</option>
											    <option value="拉祜族">拉祜族</option>
											    <option value="水族">水族</option>
											    <option value="东乡族">东乡族</option>
											    <option value="纳西族">纳西族</option>
											    <option value="景颇族">景颇族</option>
											    <option value="柯尔克孜族">柯尔克孜族</option>
											    <option value="土族">土族</option>
											    <option value="达斡尔族">达斡尔族</option>
											    <option value="仫佬族">仫佬族</option>
											    <option value="羌族">羌族</option>
											    <option value="布朗族">布朗族</option>
											    <option value="撒拉族">撒拉族</option>
											    <option value="毛南族">毛南族</option>
											    <option value="仡佬族">仡佬族</option>
											    <option value="锡伯族">锡伯族</option>
											    <option value="阿昌族">阿昌族</option>
											    <option value="普米族">普米族</option>
											    <option value="塔吉克族">塔吉克族</option>
											    <option value="怒族">怒族</option>
											    <option value="乌孜别克族">乌孜别克族</option>
											    <option value="俄罗斯族">俄罗斯族</option>
											    <option value="鄂温克族">鄂温克族</option>
											    <option value="德昴族">德昴族</option>
											    <option value="保安族">保安族</option>
											    <option value="裕固族">裕固族</option>
											    <option value="京族">京族</option>
											    <option value="塔塔尔族">塔塔尔族</option>
											    <option value="独龙族">独龙族</option>
											    <option value="鄂伦春族">鄂伦春族</option>
											    <option value="赫哲族">赫哲族</option>
											    <option value="门巴族">门巴族</option>
											    <option value="珞巴族">珞巴族</option>
											    <option value="基诺族">基诺族</option>
						    				</select>
					    				</td>
					    			</tr>
					    			<tr>
					    				<td width="80" class="text-right">身份证号：</td>
					    				<td><s:textfield  type="text" name="person.idCardNumber" class="form-control input-sm validate[custom[chinaId]]" placeholder=""/></td>
					    			</tr>
					    			<tr>
					    				<td width="80" class="text-right required-label">出生日期：</td>
					    				<td><s:textfield  type="text" name="person.birthday" class="form-control input-sm dataPicker validate[required,custom[date]]" placeholder=""/></td>
					    			</tr>
					    			<tr>
					    				<td width="80" class="text-right required-label">Email：</td>
					    				<td><s:textfield  type="text" name="person.email" class="form-control input-sm validate[required,custom[email]]" placeholder=""/></td>
					    			</tr>
					    			<tr>
					    				<td width="80" class="text-right required-label">手机：</td>
					    				<td><s:textfield  type="text" name="person.mobilePhone" class="form-control input-sm validate[required,custom[phone]]" placeholder=""/></td>
					    			</tr>
					    			<tr>
					    				<td width="80" class="text-right">联系地址：</td>
					    				<td><s:textfield  type="text" name="person.address" class="form-control input-sm " placeholder=""/></td>
					    			</tr>
					    			<tr>
					    				<td width="80" class="text-right">邮编：</td>
					    				<td><s:textfield  type="text" name="person.postCode" class="form-control input-sm  validate[custom[chinaZip]]" placeholder=""/></td>
					    			</tr>
					    			<tr>
					    				<td width="80" class="text-right ">个人简介：</td>
					    				<td><s:textarea type="text" name="person.introduction" class="form-control input-sm " placeholder=""></s:textarea></td>
					    			</tr>
					    			<tr>
					    				<td width="80" class="text-right ">分工情况：</td>
					    				<td><s:textarea type="text" name="workDivision1" class="form-control input-sm " placeholder=""></s:textarea></td>
					    			</tr>
					    			<tr>
					    				<td width="80" class="text-right ">职务：</td>
					    				<td><s:textfield  type="text" name="position1" class="form-control input-sm " placeholder=""/></td>
					    			</tr>
					    			<tr>
					    				<td width="80" class="text-right ">所属机构：</td>
					    				<td>
					    					<button class = "btn btn-default btn-sm select-agency">选择</button>
					    					<div class="selected">
					    						<s:if test="person.agency.id != null">
					    						<div class="selected-link"><span class="label label-primary" id="<s:property value='%{person.agency.id}'/>"><s:property value='%{person.agency.name}'/></span><div class="delete-link"><i class="fa fa-times"></i></div></div>
					    						</s:if>
					    					</div>
					    					<input type="hidden" name="agencyId1" class="form-control input-sm " placeholder="" value="<s:property value='%{person.agency.id}'/>">
					    				</td>
					    			</tr>
				    			</tbody>
				    		</table>
						</div>
						<div id="member_info" style="display:none">
							<div class="panel panel-default panel-view switch">
							 <div class="panel-heading">
							 	<span class="label label-primary label-custom">合作者 <span class="member-count">1</span></span>
							 	<div class = "btn-group pull-right">
									<button class="btn btn-default btn-sm member-add">添加</button>
								</div>
								<span class="clearfix"></span>
							</div>
							</div>
							<s:iterator value="%{members}" status="stat" var="list1">
								<div class="panel panel-default panel-view member">
								 <div class="panel-heading">
								 	<span class="label label-primary label-custom">合作者 <span class="member-count"><s:property value="#stat.index + 1"/></span></span>
								 	<div class = "btn-group pull-right">
										<button class="btn btn-default btn-sm member-add">添加</button>
										<button class="btn btn-default btn-sm member-delete">删除</button>
									</div>
									<span class="clearfix"></span>
								</div>
							  	<div class="panel-body" style="padding:15px">
							  	
									<table width="100%">
						    			<tbody>
							    			<tr>
							    				<td width="80" class="text-right required-label">姓名：</td>
							    				<td><input type="text" value="<s:property value="%{members[#stat.index].name}"/>"  name="member<s:property value="#stat.index + 2"/>" class="form-control input-sm validate[required]" placeholder=""></td>
							    				<td width="80" class="text-right "></td>
							    				<td width="80" class="text-right required-label">性别：</td>
							    				<td>
							    				<select type="text" name="member<s:property value="#stat.index + 2"/>.gender" class="form-control input-sm validate[required]" placeholder="" data="<s:property value="%{members[#stat.index].gender}"/>">
							    					<option>---请选择---</option>
							    					<option value="男">男</option>
							    					<option value="女">女</option>
							    				</select>
							    				</td>
							    				<td width="80" class="text-right "></td>
							    			</tr>
							    			<tr>
							    				<td width="80" class="text-right required-label">民族：</td>
							    				<td>
								    				<select type="text" name="member<s:property value="#stat.index + 2"/>.ethnic" class="form-control input-sm validate[required]" placeholder="" data="<s:property value="%{members[#stat.index].ethnic}"/>">
													    <option value="">--请选择民族--</option>
													    <option value="汉族">汉族</option>
													    <option value="蒙古族">蒙古族</option>
													    <option value="回族">回族</option>
													    <option value="藏族">藏族</option>
													    <option value="维吾尔族">维吾尔族</option>
													    <option value="苗族">苗族</option>
													    <option value="彝族">彝族</option>
													    <option value="壮族">壮族</option>
													    <option value="布依族">布依族</option>
													    <option value="朝鲜族">朝鲜族</option>
													    <option value="满族">满族</option>
													    <option value="侗族">侗族</option>
													    <option value="瑶族">瑶族</option>
													    <option value="白族">白族</option>
													    <option value="土家族">土家族</option>
													    <option value="哈尼族">哈尼族</option>
													    <option value="哈萨克族">哈萨克族</option>
													    <option value="傣族">傣族</option>
													    <option value="黎族">黎族</option>
													    <option value="傈僳族">傈僳族</option>
													    <option value="佤族">佤族</option>
													    <option value="畲族">畲族</option>
													    <option value="高山族">高山族</option>
													    <option value="拉祜族">拉祜族</option>
													    <option value="水族">水族</option>
													    <option value="东乡族">东乡族</option>
													    <option value="纳西族">纳西族</option>
													    <option value="景颇族">景颇族</option>
													    <option value="柯尔克孜族">柯尔克孜族</option>
													    <option value="土族">土族</option>
													    <option value="达斡尔族">达斡尔族</option>
													    <option value="仫佬族">仫佬族</option>
													    <option value="羌族">羌族</option>
													    <option value="布朗族">布朗族</option>
													    <option value="撒拉族">撒拉族</option>
													    <option value="毛南族">毛南族</option>
													    <option value="仡佬族">仡佬族</option>
													    <option value="锡伯族">锡伯族</option>
													    <option value="阿昌族">阿昌族</option>
													    <option value="普米族">普米族</option>
													    <option value="塔吉克族">塔吉克族</option>
													    <option value="怒族">怒族</option>
													    <option value="乌孜别克族">乌孜别克族</option>
													    <option value="俄罗斯族">俄罗斯族</option>
													    <option value="鄂温克族">鄂温克族</option>
													    <option value="德昴族">德昴族</option>
													    <option value="保安族">保安族</option>
													    <option value="裕固族">裕固族</option>
													    <option value="京族">京族</option>
													    <option value="塔塔尔族">塔塔尔族</option>
													    <option value="独龙族">独龙族</option>
													    <option value="鄂伦春族">鄂伦春族</option>
													    <option value="赫哲族">赫哲族</option>
													    <option value="门巴族">门巴族</option>
													    <option value="珞巴族">珞巴族</option>
													    <option value="基诺族">基诺族</option>
								    				</select>
							    				</td>
							    				<td width="80" class="text-right "></td>
							    				<td width="80" class="text-right ">身份证号：</td>
							    				<td><input type="text" name="member<s:property value="#stat.index + 2"/>.idCardNumber" class="form-control input-sm validate[custom[chinaId]]" placeholder="" value="<s:property value="%{members[#stat.index].idCardNumber}"/>" ></td>
							    				<td width="80" class="text-right "></td>
							    			</tr>
							    			<tr>
							    				<td width="80" class="text-right required-label">出生日期：</td>
							    				<td><input type="text" name="member<s:property value="#stat.index + 2"/>.birthday" class="form-control input-sm dataPicker validate[required,custom[date]]" placeholder=""  value="<s:property value="%{members[#stat.index].birthday}"/>"></td>
							    				<td width="80" class="text-right "></td>
							    				<td width="80" class="text-right required-label">Email：</td>
							    				<td><input type="text" name="member<s:property value="#stat.index + 2"/>.email" class="form-control input-sm validate[required,custom[email]]" placeholder=""  value="<s:property value="%{members[#stat.index].email}"/>"></td>
							    				<td width="80" class="text-right "></td>
							    			</tr>
							    			<tr>
							    				<td width="80" class="text-right required-label">手机：</td>
							    				<td><input type="text" name="member<s:property value="#stat.index + 2"/>.mobilePhone" class="form-control input-sm validate[required,custom[phone]]" placeholder=""  value="<s:property value="%{members[#stat.index].mobilePhone}"/>"></td>
							    				<td width="80" class="text-right "></td>
							    				<td width="80" class="text-right">联系地址：</td>
							    				<td><input type="text" name="member<s:property value="#stat.index + 2"/>.address" class="form-control input-sm " placeholder=""  value="<s:property value="%{members[#stat.index].address}"/>"></td>
							    				<td width="80" class="text-right "></td>
							    			</tr>
							    			<tr>
							    				<td width="80" class="text-right">邮编：</td>
							    				<td><input type="text" name="member<s:property value="#stat.index + 2"/>.postCode" class="form-control input-sm validate[custom[chinaZip]]" placeholder=""  value="<s:property value="%{members[#stat.index].postCode}"/>"></td>
							    				<td width="80" class="text-right "></td>
							    				
							    				<td width="80" class="text-right ">所属机构：</td>
							    				<td>
							    					<button class = "btn btn-default btn-sm select-agency">选择</button>
							    					<div class="selected">
							    						<s:if test="members[#stat.index].agency.id != null">
							    						<div class="selected-link"><span class="label label-primary" id="<s:property value='%{members[#stat.index].agency.id}'/>"><s:property value='%{members[#stat.index].agency.name}'/></span><div class="delete-link"><i class="fa fa-times"></i></div></div>
							    						</s:if>
							    					</div>
					    				
							    					<input type="hidden" name="agencyId<s:property value="#stat.index + 2"/>" class="form-control input-sm " placeholder=""  value="<s:property value='%{members[#stat.index].agency.id}'/>"/>
							    				</td>
							    			</tr>
							    			<tr>
							    				<td width="80" class="text-right ">分工情况：</td>
							    				<td><input type="text" name="workDivision<s:property value="#stat.index + 2"/>" class="form-control input-sm " placeholder=""  value="<s:property value="%{workDivisions[#stat.index]}"/>"/></td>
							    				<td width="80" class="text-right "></td>
							    				<td width="80" class="text-right ">职务：</td>
							    				<td><input type="text" name="position<s:property value="#stat.index + 2"/>" class="form-control input-sm " placeholder=""  value="<s:property value="%{positions[#stat.index]}"/>"/></td>
							    				<td width="80" class="text-right "></td>
							    			</tr>
							    			<tr>
							    				<td width="80" class="text-right ">个人简介：</td>
							    				<td><textarea type="text" name="member<s:property value="#stat.index + 2"/>.introduction" class="form-control input-sm " placeholder=""  value="<s:property value="%{members[#stat.index].introduction}"/>"></textarea></td>
							    			</tr>	
						    			</tbody>
						    		</table>
					    		</div>
					    		</div>
								
							</s:iterator>
					
						</div>
						
				    	<div id="optr" class="text-center">
				    		<div class="btn-group">
								<input id="prev" class="btn btn-sm btn-default" type="button" style="display: none" value="上一步" />
								<input id="next" class="btn btn-sm btn-default" type="button" style="display: none" value="下一步" />
								<input id="finish" class="btn btn-sm btn-default" type="button" style="display: none" value="完成" />
								<input id="retry" class="btn btn-sm btn-default" type="button" style="display: none" value="重填" />
								<input id="confirm" class="btn btn-sm btn-default" type="button" style="display: none" value="确定" />
								<input id="cancel" class="btn btn-sm btn-default" type="button" style="display: none" value="取消" />
							</div>
						</div>	
				   		</form>
				   		<div id="member_template" style="display:none">
							<div class="panel panel-default panel-view member">
							 <div class="panel-heading">
							 	<span class="label label-primary label-custom">合作者 <span class="member-count">${memberCount }</span></span>
							 	<div class = "btn-group pull-right">
									<button class="btn btn-default btn-sm member-add">添加</button>
									<button class="btn btn-default btn-sm member-delete">删除</button>
								</div>
								<span class="clearfix"></span>
							</div>
						  	<div class="panel-body" style="padding:15px">
								<table width="100%">
					    			<tbody>
						    			<tr>
						    				<td width="80" class="text-right required-label">姓名：</td>
						    				<td><input type="text" name="${member}.name" class="form-control input-sm validate[required]" placeholder=""></td>
						    				<td width="80" class="text-right "></td>
						    				<td width="80" class="text-right required-label">性别：</td>
						    				<td>
						    				<select type="text" name="${member}.gender" class="form-control input-sm validate[required]" placeholder="">
						    					<option>---请选择---</option>
						    					<option value="男">男</option>
						    					<option value="女">女</option>
						    				</select>
						    				</td>
						    				<td width="80" class="text-right "></td>
						    			</tr>
						    			<tr>
						    				<td width="80" class="text-right required-label">民族：</td>
						    				<td>
							    				<select type="text" name="${member}.ethnic" class="form-control input-sm validate[required]" placeholder="">
												    <option value="">--请选择民族--</option>
												    <option value="汉族">汉族</option>
												    <option value="蒙古族">蒙古族</option>
												    <option value="回族">回族</option>
												    <option value="藏族">藏族</option>
												    <option value="维吾尔族">维吾尔族</option>
												    <option value="苗族">苗族</option>
												    <option value="彝族">彝族</option>
												    <option value="壮族">壮族</option>
												    <option value="布依族">布依族</option>
												    <option value="朝鲜族">朝鲜族</option>
												    <option value="满族">满族</option>
												    <option value="侗族">侗族</option>
												    <option value="瑶族">瑶族</option>
												    <option value="白族">白族</option>
												    <option value="土家族">土家族</option>
												    <option value="哈尼族">哈尼族</option>
												    <option value="哈萨克族">哈萨克族</option>
												    <option value="傣族">傣族</option>
												    <option value="黎族">黎族</option>
												    <option value="傈僳族">傈僳族</option>
												    <option value="佤族">佤族</option>
												    <option value="畲族">畲族</option>
												    <option value="高山族">高山族</option>
												    <option value="拉祜族">拉祜族</option>
												    <option value="水族">水族</option>
												    <option value="东乡族">东乡族</option>
												    <option value="纳西族">纳西族</option>
												    <option value="景颇族">景颇族</option>
												    <option value="柯尔克孜族">柯尔克孜族</option>
												    <option value="土族">土族</option>
												    <option value="达斡尔族">达斡尔族</option>
												    <option value="仫佬族">仫佬族</option>
												    <option value="羌族">羌族</option>
												    <option value="布朗族">布朗族</option>
												    <option value="撒拉族">撒拉族</option>
												    <option value="毛南族">毛南族</option>
												    <option value="仡佬族">仡佬族</option>
												    <option value="锡伯族">锡伯族</option>
												    <option value="阿昌族">阿昌族</option>
												    <option value="普米族">普米族</option>
												    <option value="塔吉克族">塔吉克族</option>
												    <option value="怒族">怒族</option>
												    <option value="乌孜别克族">乌孜别克族</option>
												    <option value="俄罗斯族">俄罗斯族</option>
												    <option value="鄂温克族">鄂温克族</option>
												    <option value="德昴族">德昴族</option>
												    <option value="保安族">保安族</option>
												    <option value="裕固族">裕固族</option>
												    <option value="京族">京族</option>
												    <option value="塔塔尔族">塔塔尔族</option>
												    <option value="独龙族">独龙族</option>
												    <option value="鄂伦春族">鄂伦春族</option>
												    <option value="赫哲族">赫哲族</option>
												    <option value="门巴族">门巴族</option>
												    <option value="珞巴族">珞巴族</option>
												    <option value="基诺族">基诺族</option>
							    				</select>
						    				</td>
						    				<td width="80" class="text-right "></td>
						    				<td width="80" class="text-right ">身份证号：</td>
						    				<td><input type="text" name="${member}.idCardNumber" class="form-control input-sm validate[custom[chinaId]]" placeholder=""></td>
						    				<td width="80" class="text-right "></td>
						    			</tr>
						    			<tr>
						    				<td width="80" class="text-right required-label">出生日期：</td>
						    				<td><input type="text" name="${member}.birthday" class="form-control input-sm dataPicker validate[required,custom[date]]" placeholder=""></td>
						    				<td width="80" class="text-right "></td>
						    				<td width="80" class="text-right required-label">Email：</td>
						    				<td><input type="text" name="${member}.email" class="form-control input-sm validate[required,custom[email]]" placeholder=""></td>
						    				<td width="80" class="text-right "></td>
						    			</tr>
						    			<tr>
						    				<td width="80" class="text-right required-label">手机：</td>
						    				<td><input type="text" name="${member}.mobilePhone" class="form-control input-sm validate[required,custom[phone]]" placeholder=""></td>
						    				<td width="80" class="text-right "></td>
						    				<td width="80" class="text-right">联系地址：</td>
						    				<td><input type="text" name="${member}.address" class="form-control input-sm " placeholder=""></td>
						    				<td width="80" class="text-right "></td>
						    			</tr>
						    			<tr>
						    				<td width="80" class="text-right">邮编：</td>
						    				<td><input type="text" name="${member}.postCode" class="form-control input-sm validate[custom[chinaZip]]" placeholder=""></td>
						    				<td width="80" class="text-right "></td>
						    				
						    				<td width="80" class="text-right ">所属机构：</td>
						    				<td>
						    					<button class = "btn btn-default btn-sm select-agency">选择</button>
						    					<div class="selected"></div>
						    					<input type="hidden" name="agencyId${parseInt(memberCount) +1}" class="form-control input-sm " placeholder=""/>
						    				</td>
						    			</tr>
						    			<tr>
						    				<td width="80" class="text-right ">分工情况：</td>
						    				<td><input type="text" name="workDivision${parseInt(memberCount)+1 }" class="form-control input-sm " placeholder=""/></td>
						    				<td width="80" class="text-right "></td>
						    				<td width="80" class="text-right ">职务：</td>
						    				<td><input type="text" name="position${parseInt(memberCount) +1}" class="form-control input-sm " placeholder=""/></td>
						    				<td width="80" class="text-right "></td>
						    			</tr>
						    			<tr>
						    				<td width="80" class="text-right ">个人简介：</td>
						    				<td><textarea type="text" name="${member}.introduction" class="form-control input-sm " placeholder=""></textarea></td>
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