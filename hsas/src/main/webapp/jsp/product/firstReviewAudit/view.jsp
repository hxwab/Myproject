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
   		
		<s:hidden id="entityId" name="entityId"/>
        <a name="top" id="top"></a>
            <div class="row mySlide">
            <ol class="breadcrumb mybreadcrumb">当前位置：
                  <li><a href="#">评奖管理</a></li>
                  <li class="active">初评审核</li>
                </ol>
            </ol>
            
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
                                        <td width = "100">${product.name }</td>
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
										<td class = "text-left" >	
										{for item in members} 
											{if item_index != 0}
												<a href = "${item[0].id}" class="pop-view-person">${item[0].name }</a>
												{if item_index != members.length-1}；{/if}
											{else}
											{/if}
										{/for}
									</td>                                    </tr>
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
								<li role="presentation" class="active"><a href="#firstReviewAudit" aria-controls="" role="tab" data-toggle="tab">初评</a></li>
								<li role="presentation" ><a href="#product_detail_info" aria-controls="basic" role="tab" data-toggle="tab">基本信息</a></li>
							</ul>
		                    <div class="tab-content ">
			                     <div role="tabpanel" class="tab-pane fade in active" id="firstReviewAudit"></div>
			                     <div role="tabpanel" class="tab-pane fade " id="product_detail_info"></div>
                     		</div>
                	</div>
                	
                	<textarea id="firstReviewAudit_template" style="display:none">
                		<table class="table table-striped table-bordered" style="font-size:14px">
							<tbody>
								<tr>
									<td colspan="9" class="text-center"><span class="label label-success label-custom">初评信息</span></td>
								</tr>
								<tr>
									<td width="120" class = "text-right view-key">初评得分</td>
									<td width="200" class = "text-left" >${averageScore}</td>
									<td width="120" class = "text-right view-key">评审类型</td>
									<td width="200" class = "text-left" colspan="7">{if reviewType ==1} 基础类 {elseif reviewType == 2} 应用决策类 {else} 著作类 {/if}</td>
								</tr>
								<tr>
									<td colspan="9" class="text-center"><span class="label label-success label-custom">初评得分明细</span></td>
								</tr>
						{if reviewType == 1}
								<tr>
									<th>专家名</th>
									<th>总分</th>
									<th>评审意见</th>
									<th>创新程度得分</th>
									<th>研究方法得分</th>
									<th>发表报刊级别得分</th>
									<th>转载情况得分</th>
								</tr>
							{for item in firstReviewData}
								<tr>
									<td>${item[0]}</td>
									<td>${item[1]}</td>
									<td>${item[2]}</td>
									<td>${item[3]}</td>
									<td>${item[4]}</td>
									<td>${item[5]}</td>
									<td>${item[6]}</td>
								</tr>
							{/for}
						{elseif reviewType == 2}
								<tr>
									<th>专家名</th>
									<th>总分</th>
									<th>评审意见</th>
									<th>创新程度得分</th>
									<th>研究方法得分</th>
									<th>发表报刊级别得分</th>
									<th>应用采纳情况得分</th>
								</tr>
							{for item in firstReviewData}
								<tr>
									<td>${item[0]}</td>
									<td>${item[1]}</td>
									<td>${item[2]}</td>
									<td>${item[3]}</td>
									<td>${item[4]}</td>
									<td>${item[5]}</td>
									<td>${item[6]}</td>
								</tr>
							{/for}
						{else}	
								<tr>
									<th>专家名</th>
									<th>总分</th>
									<th>评审意见</th>
									<th>创新程度得分</th>
									<th>研究方法得分</th>
									<th>出版社级别得分</th>
									<th>社会评价得分</th>
									<th>引用情况得分</th>
								</tr>
							{for item in firstReviewData}
								<tr>
									<td>${item[0]}</td>
									<td>${item[1]}</td>
									<td>${item[2]}</td>
									<td>${item[3]}</td>
									<td>${item[4]}</td>
									<td>${item[5]}</td>
									<td>${item[6]}</td>
									<td>${item[7]}</td>
								</tr>
							{/for}
							</tbody>
						</table>						
						{/if}
                	</textarea>
            </div>
            <div class="row">
        <%@ include file="/jsp/footer.jsp"%>
        <script>
            seajs.use("js/product/firstReviewAudit/view.js", function(ai) {
                ai.init(); 
            });
        </script>
    </body>
</html>