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
   		

        <a name="top" id="top"></a>
            <div class="row mySlide">
            <ol class="breadcrumb mybreadcrumb">当前位置：
                  <li><a href="#">评奖管理</a></li>
                  <li class="active">初评</li>
                </ol>
            </ol>
            	<s:hidden id="hasSubmit" name="hasSubmit" />
            	<s:hidden id="accountType" name="accountType" />
                <div class="col-xs-12 main-content" style="margin-bottom: 10px;">
                <div class="btn-group pull-right view-controler" role="group" aria-label="...">
                    <button type="button" class="btn  btn-default" id = "view_prev">上一条</button>
                    <button type="button" class="btn  btn-default" id = "view_next">下一条</button>
                    <button type="button" class="btn  btn-default" id = "view_back">返回</button>
                </div>
                <span class="clearfix"></span><!-- 重要!! 用于清除按键组浮动 -->
                    <div class="panel panel-default panel-view" id = "product_info" style = "display:none">
                        
                    </div>
                     <textarea id = "product_info_template" style = "display:none"><!-- 前台模版 -->
                         <div class="panel-body">
                                <table class="table table-striped view-table">
                                  <tbody>
                                    <tr>
                                        <td width = "50" class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
                                        <td width = "100" class = "text-right view-key">成果名称：</td>
                                        <td width = "400">${product.name }</td>
                                        <td width = "150"></td>
                                        <td width = "100"></td>
                                        <td ></td>
                                    </tr>
                                    <tr>
                                        <td width = "50" class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
                                        <td width = "100" class = "text-right view-key">第一作者：</td>
                                        <td class = "text-left"  width = "100">${product.authorName }</td>
                                        <td width = "50" class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
                                        <td width = "100" class = "text-right view-key">合作者：</td>
                                        <td width ='' class = "text-left" >{for item in members} <a href = "${item[0].id}" class="pop-view-person">${item[0].name }</a>；{/for}</td>
                                    </tr>
                                    <tr>
                                        <td width = "50" class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
                                        <td width = "100" class = "text-right view-key">研究类型：</td>
                                        <td width ='' class = "text-left" >${product.researchType}</td>
                                        <td width = "50" class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
                                        <td width = "100" class = "text-right view-key">依托机构：</td>
                                        <td class = "text-left" ><a href = "${product.agency.id }" class="pop-view-agency">${product.agencyName }</a></td>
                                    </tr>
                                    <tr>
                                        <td width = "50" class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
                                        <td width = "100" class = "text-right view-key">提交状态：</td>
                                        <td class = "text-left" colspan = "4">${product.status}</td>
                                    </tr>
                                  </tbody>
                                </table>
                            </div>
                     </textarea>
                      <textarea id = "product_detail_info_template" style = "display:none"><!-- 前台模版 -->
                      	<table class="table table-striped table-bordered">
							<tbody>
								<tr>
									<td width="120" class = "text-right view-key">出版社或发表刊物名称</td>
									<td width="200" class = "text-left" >${ product.publishName}</td>
									<td width="120" class = "text-right view-key">出版社或发表刊物级别</td>
									<td width="200" class = "text-left" >${product.publishLevel }</td>
								</tr>
								<tr>
									<td class = "text-right view-key">出版或发表时间</td>
									<td class = "text-left" >${product.publishDate}</td>
									<td class = "text-right view-key">合作者</td>
									<td class = "text-left" >
										{for item in members} 
											{if item_index != 0}
												<a href = "${item[0].id}" class="pop-view-person">${item[0].name }</a>
												{if item_index != members.length-1}；{/if}
											{else}
											{/if}
										{/for}
									</td>
								</tr>
								<tr>
									<td width="120" class = "text-right view-key">成果内容简介</td>
									<td width="520" class = "text-left" colspan="3">${product.introduction}</td>
								</tr>
								<tr>
									<td width="120" class = "text-right view-key">成果内容概要</td>
									<td width="520" class = "text-left" colspan="3">${product.abstractStr }</td>
								</tr>
								<tr>
									<td width="100" class = "text-right view-key">成果社会反映</td>
									<td width="520" class = "text-left" colspan="3">${product.socialEffect }</td>
								</tr>
							</tbody>
						</table>
                      </textarea>
                     <div class="tab-custom">
                     		<ul class="nav nav-tabs" role="tablist">
                     		<s:if test = 'accountType == "2"'> <!--  expert -->
                     			<s:if test = 'hasSubmit == "0"'> <!--  未初评 -->
								<li role="presentation" class="active"><a href="#firstReview" aria-controls="firstReview" role="tab" data-toggle="tab">初评</a></li>
								</s:if>
							</s:if>
								<li role="presentation" ><a href="#product_detail_info" aria-controls="product_detail_info" role="tab" data-toggle="tab">基本信息</a></li>
							</ul>
		                    <div class="tab-content ">
			                     <div role="tabpanel" class="tab-pane fade " id="product_detail_info">
			                     	
			                     </div>
			                      <div role="tabpanel" class="tab-pane fade in active" id="firstReview">
				                       <form id = "form_first_review"   action="product/firstReview/addFirstReview.action" method="post" style = "display:none" ><!-- 模版解析后的容器 -->
				                    	<s:hidden id="productType" name="productType"/>
				                    	<s:hidden id="entityId" name="entityId" />
				                    	<div class = "wrapper"></div>
				                        <div class="panel panel-default panel-first-review">
				                            <div class="panel-heading">
				                                <span class="label label-success label-custom">评语</span>
				                            </div>
				                            <div class="panel-body">
				                                <textarea class = "form-control" name = "firstReviewOpinion"></textarea>
				                            </div>
				                        </div>
				                        <div id="optr" class="text-center " >
				                        <div class="btn-group">
				                            <input id="confirm" class="btn  btn-default" type="button" value="确定" />
				                            <input id="cancel" class="btn  btn-default" type="button"  value="取消" />
				                        </div>
				                        </div>  
				                    </form>
			                     </div>
	                     	</div>
                     </div>
                     <textarea id = "view_template_1" style = "display:none"><!-- 前台模版 -->
                         <div class="panel panel-default panel-first-review">
                             <div class="panel-heading">
                                    <span class="label label-primary label-custom">1. 创新程度（以下为参考标准）</span>
                                    <span class="label label-success label-custom"> 得分：</span>
                                    <input class="form form-control review-input validate[required, custom[integer],min[0], max[30]]" name="score1" >
                                </div>
                                <div class="panel-body">
                                    <div class="list-group">
                                    <ul>
                                      <li class="list-group-item" >
                                        <i class="fa fa-hand-o-right"></i>
                                        <span class="first-review-stanard">理论上有创新性突破，对学科建设有重大贡献；选题有重大理论和实践意义</span>
                                        <span class="label label-success label-custom">25-30 分</span>
                                      </li>
                                      <li class="list-group-item">
                                            <i class="fa fa-hand-o-right"></i>
                                            <span class="first-review-stanard">理论上有重要创建，提出了新观点、新结论，对学科建设有较大贡献 ；选题有重大学术价值</span>
                                            <span class="label label-success label-custom">20-25 分</span>
                                      </li>
                                      <li class="list-group-item">
                                            <i class="fa fa-hand-o-right"></i>
                                            <span class="first-review-stanard">对原有理论观点有深化和补充，得出了某些新结论，对学科建设有一定贡献；选题有重要学术价值</span>
                                            <span class="label label-success label-custom">15-20 分</span>
                                        </li>
                                      <li class="list-group-item">
                                            <i class="fa fa-hand-o-right"></i>
                                            <span class="first-review-stanard">对原有理论观点有所深入、有新补充，内容有一定新意，对学科建设有所贡献；选题有一定的学术价值</span>
                                            <span class="label label-success label-custom">0-15 分</span>
                                      </li>
                                    </ul>
                                    </div>
                                </div>
                            </div>
                            
                            <div class="panel panel-default panel-first-review">
                                <div class="panel-heading">
                                    <span class="label label-primary label-custom">2. 研究方法（以下为参考标准）</span>
                                    <span class="label label-success label-custom"> 得分：</span>
                                    <input class="form form-control review-input validate[required, custom[integer],min[0], max[20]]" name="score2" >
                                </div>
                                <div class="panel-body">
                                    <div class="list-group">
                                      <li class="list-group-item" >
                                            <i class="fa fa-hand-o-right"></i>
                                            <span class="first-review-stanard">方法上有重大创新，资料翔实，结构严谨，逻辑十分严密，论证很充分，文字精炼</span>
                                            <span class="label label-success label-custom">15-20 分</span>
                                      </li>
                                      <li class="list-group-item">
                                            <i class="fa fa-hand-o-right"></i>
                                            <span class="first-review-stanard">方法上有较大创新,资料翔实，逻辑严密，论证充分，文字通达</span>
                                            <span class="label label-success label-custom">10-15 分</span>
                                      </li>
                                      <li class="list-group-item">
                                            <i class="fa fa-hand-o-right"></i>
                                            <span class="first-review-stanard">方法上有所创新，资料较翔实，逻辑较严密，论证较充分，文字通顺</span>
                                            <span class="label label-success label-custom">0-10 分</span>
                                        </li>
                                    </ul>
                                    </div>
                                </div>
                            </div>
                            
                            <div class="panel panel-default panel-first-review">
                                <div class="panel-heading">
                                    <span class="label label-primary label-custom">3. 发表报刊级别（以下为参考标准）</span>
                                    <span class="label label-success label-custom"> 得分：</span>
                                    <input class="form form-control review-input validate[required, custom[integer],min[0], max[30]]" name="score3" >
                                </div>
                                <div class="panel-body">
                                    <div class="list-group">
                                      <li class="list-group-item" >
                                            <i class="fa fa-hand-o-right"></i>
                                            <span class="first-review-stanard">权威报刊</span>
                                            <span class="label label-success label-custom">25-30 分</span>
                                      </li>
                                      <li class="list-group-item">
                                            <i class="fa fa-hand-o-right"></i>
                                            <span class="first-review-stanard">核心报刊</span>
                                            <span class="label label-success label-custom">20-25 分</span>
                                      </li>
                                      <li class="list-group-item">
                                            <i class="fa fa-hand-o-right"></i>
                                            <span class="first-review-stanard">其他报刊</span>
                                            <span class="label label-success label-custom">0-20 分</span>
                                        </li>
                                    </ul>
                                    </div>
                                </div>
                            </div>
                            
                            <div class="panel panel-default panel-first-review">
                                <div class="panel-heading">
                                    <span class="label label-primary label-custom">4. 转载情况（以下为参考标准）</span>
                                    <span class="label label-success label-custom"> 得分：</span>
                                    <input class="form form-control review-input validate[required, custom[integer],min[0], max[20]]" name="score4" >
                                </div>
                                <div class="panel-body">
                                    <div class="list-group">
                                      <li class="list-group-item" >
                                            <i class="fa fa-hand-o-right"></i>
                                            <span class="first-review-stanard">新华文摘及权威报刊转载或入选国际性学术性会议并作大会发言</span>
                                            <span class="label label-success label-custom">15-20 分</span>
                                      </li>
                                      <li class="list-group-item">
                                            <i class="fa fa-hand-o-right"></i>
                                            <span class="first-review-stanard">书稿、讲稿等中采用或入选全国性学术性会议并作大会发言</span>
                                            <span class="label label-success label-custom">10-15 分</span>
                                      </li>
                                      <li class="list-group-item">
                                            <i class="fa fa-hand-o-right"></i>
                                            <span class="first-review-stanard">人大资料复印中心转载</span>
                                            <span class="label label-success label-custom">0-10 分</span>
                                        </li>
                                    </ul>
                                    </div>
                                </div>
                            </div>
                    </textarea>
                    
                     <textarea id = "view_template_2" style = "display:none"><!-- 前台模版 -->
                         <div class="panel panel-default panel-first-review">
                             <div class="panel-heading">
                                    <span class="label label-primary label-custom">1. 创新程度（以下为参考标准）</span>
                                    <span class="label label-success label-custom"> 得分：</span>
                                    <input class="form form-control review-input validate[required, custom[integer],min[0], max[30]] " name="score1" >
                                </div>
                                <div class="panel-body">
                                    <div class="list-group">
                                      <li class="list-group-item" >
                                            <i class="fa fa-hand-o-right"></i>
                                            <span class="first-review-stanard">理论上有创新性突破，成果对实践有重大指导作用；选题是国家和省经济社会发展急需解决的重大问题</span>
                                            <span class="label label-success label-custom">25-30 分</span>
                                      </li>
                                      <li class="list-group-item">
                                            <i class="fa fa-hand-o-right"></i>
                                            <span class="first-review-stanard">理论上有重要创建，提出了新观点、新结论，对实践有指导意义；选题是国家和省经济社会发展需要解决的问题</span>
                                            <span class="label label-success label-custom">20-25 分</span>
                                      </li>
                                      <li class="list-group-item">
                                            <i class="fa fa-hand-o-right"></i>
                                            <span class="first-review-stanard">对原有理论观点有深化和补充，得出了某些新结论，对实践有一定指导意义；选题是部门或省内某地区经济社会发展中迫切需要解决的问题</span>
                                            <span class="label label-success label-custom">15-20 分</span>
                                        </li>
                                      <li class="list-group-item">
                                            <i class="fa fa-hand-o-right"></i>
                                            <span class="first-review-stanard">对原有理论观点有所深入、有新补充，内容有一定新意，对实践指导有一定价值；选题有现实意义，研究有针对性</span>
                                            <span class="label label-success label-custom">0-15 分</span>
                                      </li>
                                    </ul>
                                    </div>
                                </div>
                            </div>
                            
                            <div class="panel panel-default panel-first-review">
                                <div class="panel-heading">
                                    <span class="label label-primary label-custom">2. 研究方法（以下为参考标准）</span>
                                    <span class="label label-success label-custom"> 得分：</span>
                                    <input class="form form-control review-input validate[required, custom[integer],min[0], max[10]]" name="score2" >
                                </div>
                                <div class="panel-body">
                                    <div class="list-group">
                                      <li class="list-group-item" >
                                            <i class="fa fa-hand-o-right"></i>
                                            <span class="first-review-stanard">方法上有很大创新，资料翔实，结构严谨，逻辑十分严密，论证很充分，文字精炼 </span>
                                            <span class="label label-success label-custom">8-10 分</span>
                                      </li>
                                      <li class="list-group-item">
                                            <i class="fa fa-hand-o-right"></i>
                                            <span class="first-review-stanard">方法上有创新，资料翔实，逻辑严密，论证充分，文字通达</span>
                                            <span class="label label-success label-custom">5-8 分</span>
                                      </li>
                                      <li class="list-group-item">
                                            <i class="fa fa-hand-o-right"></i>
                                            <span class="first-review-stanard">方法上有所创新，资料较翔实，逻辑较严密，论证较充分，文字通顺 </span>
                                            <span class="label label-success label-custom">0-5 分</span>
                                        </li>
                                    </ul>
                                    </div>
                                </div>
                            </div>
                            
                            <div class="panel panel-default panel-first-review">
                                <div class="panel-heading">
                                    <span class="label label-primary label-custom">3. 发表报刊级别（以下为参考标准）</span>
                                    <span class="label label-success label-custom"> 得分：</span>
                                    <input class="form form-control review-input validate[required, custom[integer],min[0], max[30]]" name="score3" >
                                </div>
                                <div class="panel-body">
                                    <div class="list-group">
                                    <table width = "100%">
                                    	<tr>
	                                    	<td rowspan="2" width = "200" class="border-bottom"> 
		                                    	<li class="list-group-item" style="border:0">
		                                            <i class="fa fa-hand-o-right"></i>
		                                            <span class="first-review-stanard">权威报刊</span>
		                                      	</li>
	                                     	 </td>
                                      		<td>
                                      		 <li class="list-group-item no-border-bottom" >
	                                            <span class="first-review-stanard">公开发表</span>
	                                            <span class="label label-success label-custom">25-30 分</span>
                                      		</li>
                                      		</td>
                                    	</tr>
                                    	<tr>
                                    	<td>
                                      		 <li class="list-group-item no-border-bottom">
	                                            <span class="first-review-stanard">内部资料</span>
	                                             <span class="label label-success label-custom">20-25 分</span>
                                      		</li>
                                      		</td>
                                    	</tr>
                                    	
                                    	<tr>
	                                    	<td rowspan="2" width = "200" class="border-bottom"> 
		                                    	<li class="list-group-item" style="border:0">
		                                            <i class="fa fa-hand-o-right"></i>
		                                            <span class="first-review-stanard">核心报刊</span>
		                                      	</li>
	                                     	 </td>
                                      		<td>
                                      		 <li class="list-group-item no-border-bottom" >
	                                            <span class="first-review-stanard">公开发表</span>
	                                           <span class="label label-success label-custom">20-25 分</span>
                                      		</li>
                                      		</td>
                                    	</tr>
                                    	<tr>
                                    	<td>
                                      		 <li class="list-group-item no-border-bottom" >
	                                            <span class="first-review-stanard">内部资料</span>
	                                             <span class="label label-success label-custom">15-20 分</span>
                                      		</li>
                                      		</td>
                                    	</tr>
                                    	
                                    	<tr>
	                                    	<td rowspan="2" width = "200" class="border-bottom"> 
		                                    	<li class="list-group-item" style="border:0">
		                                            <i class="fa fa-hand-o-right"></i>
		                                            <span class="first-review-stanard">其他报刊</span>
		                                      	</li>
	                                     	 </td>
                                      		<td>
                                      		 <li class="list-group-item no-border-bottom" >
	                                            <span class="first-review-stanard">公开发表</span>
	                                            <span class="label label-success label-custom">15-20 分</span>
                                      		</li>
                                      		</td>
                                    	</tr>
                                    	<tr>
                                    	<td>
                                      		 <li class="list-group-item no-border-bottom" >
	                                            <span class="first-review-stanard">内部资料</span>
	                                            <span class="label label-success label-custom">0-15 分</span>
                                      		</li>
                                      		</td>
                                    	</tr>
                                    </table>
                                    </ul>
                                    </div>
                                </div>
                            </div>
                            
                            <div class="panel panel-default panel-first-review">
                                <div class="panel-heading">
                                    <span class="label label-primary label-custom">4. 应用采纳情况（以下为参考标准）</span>
                                    <span class="label label-success label-custom"> 得分：</span>
                                    <input class="form form-control review-input validate[required, custom[integer],min[0], max[30]]" name="score4" >
                                </div>
                                <div class="panel-body">
                                    <div class="list-group">
                                      <li class="list-group-item" >
                                            <i class="fa fa-hand-o-right"></i>
                                            <span class="first-review-stanard">提出了重大可行性建议，被中央所采纳，在国内外有重大影响</span>
                                            <span class="label label-success label-custom">28-30 分</span>
                                      </li>
                                      <li class="list-group-item">
                                            <i class="fa fa-hand-o-right"></i>
                                            <span class="first-review-stanard">对实际工作有较大指导作用，被省委省政府或中央部委所采纳，在省内外有一定影响</span>
                                            <span class="label label-success label-custom">25-28 分</span>
                                      </li>
                                      <li class="list-group-item">
                                            <i class="fa fa-hand-o-right"></i>
                                            <span class="first-review-stanard">对实际工作有一定指导作用，被省属部、委、厅、局及市州委、市州政府采纳，在省内有一定影响</span>
                                            <span class="label label-success label-custom">20-25 分</span>
                                        </li>
                                      <li class="list-group-item">
                                            <i class="fa fa-hand-o-right"></i>
                                            <span class="first-review-stanard">对实际工作有一定指导作用，被实际工作部门采纳，产生一定社会影响</span>
                                            <span class="label label-success label-custom">0-20 分</span>
                                      </li>
                                    </ul>
                                    </div>
                                </div>
                            </div>
                    </textarea>
                    
                     <textarea id = "view_template_3" style = "display:none"><!-- 前台模版 -->
                         <div class="panel panel-default panel-first-review">
                             <div class="panel-heading">
                                    <span class="label label-primary label-custom">1. 创新程度（以下为参考标准）</span>
                                    <span class="label label-success label-custom"> 得分：</span>
                                    <input class="form form-control review-input validate[required, custom[integer],min[0], max[30]]" name="score1" >
                                </div>
                                <div class="panel-body">
                                    <div class="list-group">
                                      <li class="list-group-item" >
                                            <i class="fa fa-hand-o-right"></i>
                                            <span class="first-review-stanard">选题是重大的理论和实际问题，成果有重大创新，在理论上或实践中产生重要影响，对学科建设有重要贡献</span>
                                            <span class="label label-success label-custom">20-30 分</span>
                                      </li>
                                      <li class="list-group-item">
                                            <i class="fa fa-hand-o-right"></i>
                                            <span class="first-review-stanard">选题是重要的理论和实际问题，成果有重要创建，在理论上或实践中产生较大影响，对学科建设有较大贡献</span>
                                            <span class="label label-success label-custom">20-25 分</span>
                                      </li>
                                      <li class="list-group-item">
                                            <i class="fa fa-hand-o-right"></i>
                                            <span class="first-review-stanard">选题有重要的理论意义和学术价值，成果有新意，对原有理论观点有所深化和补充，对学科建设有一定贡献</span>
                                            <span class="label label-success label-custom">15-20 分</span>
                                        </li>
                                      <li class="list-group-item">
                                            <i class="fa fa-hand-o-right"></i>
                                            <span class="first-review-stanard">选题有一定的理论意义和学术价值，成果有一定新意，对原有理论观点有所深入和补充，对学科建设有所贡献</span>
                                            <span class="label label-success label-custom">0-15 分</span>
                                      </li>
                                    </ul>
                                    </div>
                                </div>
                            </div>
                            
                            <div class="panel panel-default panel-first-review">
                                <div class="panel-heading">
                                    <span class="label label-primary label-custom">2. 研究方法（以下为参考标准）</span>
                                    <span class="label label-success label-custom"> 得分：</span>
                                    <input class="form form-control review-input validate[required, custom[integer],min[0], max[20]]" name="score2" >
                                </div>
                                <div class="panel-body">
                                    <div class="list-group">
                                      <li class="list-group-item" >
                                            <i class="fa fa-hand-o-right"></i>
                                            <span class="first-review-stanard">方法上有重要创新，论证科学和充分，结构严谨，逻辑严密，资料翔实，文字精炼流畅</span>
                                            <span class="label label-success label-custom">15-20 分</span>
                                      </li>
                                      <li class="list-group-item">
                                            <i class="fa fa-hand-o-right"></i>
                                        <span class="first-review-stanard">方法上有创新，论证充分和科学，结构严谨，逻辑性强，资料丰实，文字比较精炼流畅</span>
                                        <span class="label label-success label-custom">10-15 分</span>
                                      </li>
                                      <li class="list-group-item">
                                            <i class="fa fa-hand-o-right"></i>
                                            <span class="first-review-stanard">方法上有一定创新，论证比较科学和充分，结构合理，逻辑性较强，资料较丰实，文字流畅</span>
                                            <span class="label label-success label-custom">0-10 分</span>
                                        </li>
                                    </ul>
                                    </div>
                                </div>
                            </div>
                            
                            <div class="panel panel-default panel-first-review">
                                <div class="panel-heading">
                                    <span class="label label-primary label-custom">3. 出版社级别（以下为参考标准）</span>
                                    <span class="label label-success label-custom"> 得分：</span>
                                    <input class="form form-control review-input validate[required, custom[integer],min[0], max[20]]" name="score3" >
                                </div>
                                <div class="panel-body">
                                    <div class="list-group">
                                      <li class="list-group-item" >
                                            <i class="fa fa-hand-o-right"></i>
                                        <span class="first-review-stanard">国家级出版社</span>
                                        <span class="label label-success label-custom">15-20 分</span>
                                      </li>
                                      <li class="list-group-item">
                                            <i class="fa fa-hand-o-right"></i>
                                            <span class="first-review-stanard">省级出版社</span>
                                            <span class="label label-success label-custom">0-15 分</span>
                                      </li>
                                    </ul>
                                    </div>
                                </div>
                            </div>
                            
                            <div class="panel panel-default panel-first-review">
                                <div class="panel-heading">
                                    <span class="label label-primary label-custom">4. 社会评介（以下为参考标准）</span>
                                    <span class="label label-success label-custom"> 得分：</span>
                                    <input class="form form-control review-input validate[required, custom[integer],min[0], max[20]]" name="score4" >
                                </div>
                                <div class="panel-body">
                                    <div class="list-group">
                                      <li class="list-group-item" >
                                            <i class="fa fa-hand-o-right"></i>
                                            <span class="first-review-stanard">全国性报刊评介</span>
                                            <span class="label label-success label-custom">15-20 分</span>
                                      </li>
                                      <li class="list-group-item">
                                            <i class="fa fa-hand-o-right"></i>
                                            <span class="first-review-stanard">省内报刊评介</span>
                                            <span class="label label-success label-custom">0-15 分</span>
                                      </li>
                                    </ul>
                                    </div>
                                </div>
                            </div>
                            <div class="panel panel-default panel-first-review">
                                <div class="panel-heading">
                                    <span class="label label-primary label-custom">5. 引用情况（以下为参考标准）</span>
                                    <span class="label label-success label-custom"> 得分：</span>
                                   <input class="form form-control review-input validate[required, custom[integer],min[0], max[10]]" name="score5" >
                            </div>
                                <div class="panel-body">
                                    <div class="list-group">
                                      <li class="list-group-item" >
                                            <i class="fa fa-hand-o-right"></i>
                                            <span class="first-review-stanard">被多次引用</span>
                                            <span class="label label-success label-custom">5-10 分</span>
                                      </li>
                                      <li class="list-group-item">
                                            <i class="fa fa-hand-o-right"></i>
                                            <span class="first-review-stanard">被引用三次以下</span>
                                            <span class="label label-success label-custom">0-5分 </span>
                                      </li>
                                    </ul>
                                    </div>
                                </div>
                            </div>
                            
                    </textarea>
                   
                </div>
            </div>
            <div class="row">
        <%@ include file="/jsp/footer.jsp"%>
        <script>
            seajs.use("js/product/firstReview/review.js", function(review) {
                review.init(); 
            });
        </script>
    </body>
</html>