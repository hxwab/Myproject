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
   		<s:hidden id="entityId" name="entityId" value="4028d88e504badea01504bae67460000" />
   		<a name="top" id="top"></a>
			<div class="row mySlide">
			<ol class="breadcrumb mybreadcrumb">当前位置：
				  <li><a href="#">评奖管理</a></li>
				   <li><a href="#">成果申报</a></li>
				  <li class="active">查看</li>
				</ol>
			</ol>
			    <div class="col-xs-12 main-content">
				     <div class="btn-group pull-right view-controler" role="group" aria-label="...">
			  			<button type="button" class="btn btn-sm btn-default" id = "view_add">添加</button>
			  			<button type="button" class="btn btn-sm btn-default" id = "view_mod">修改</button>
			  			<button type="button" class="btn btn-sm btn-default" id = "view_del">删除</button>
			  			<button type="button" class="btn btn-sm btn-default" id = "view_prev">上一条</button>
			  			<button type="button" class="btn btn-sm btn-default" id = "view_next">下一条</button>
			  			<button type="button" class="btn btn-sm btn-default" id = "view_back">返回</button>
					</div>
					<span class="clearfix"></span><!-- 重要!! 用于清除按键组浮动 -->
					 <textarea id = "view_template" style = "display:none"><!-- 前台模版 -->
						 <div class="panel panel-default panel-view">
						  	<div class="panel-body">
							    <table class="table table-striped view-table">
							      <tbody>
									<tr>
										<td width = "50" class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td width = "100" class = "text-right">成果名称：</td>
										<td width = "100">${product.name}</td>
										<td width = "150"></td>
										<td width = "100"></td>
										<td ></td>
									</tr>
									<tr>
										<td width = "50" class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td width = "100" class = "text-right">第一作者：</td>
										<td class = "text-left"  width = "100">${product.authorName }</td>
										<td width = "50" class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td width = "100" class = "text-right">合作者：</td>
										<td width ='' class = "text-left" >{for item in members} <a href = "${item[0].id}" class="pop-view-person">${item[0].name }</a>；{/for}</td>
									</tr>
									<tr>
										<td width = "50" class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td width = "100" class = "text-right">研究类型：</td>
										<td width ='' class = "text-left" >${product.researchType}</td>
										<td width = "50" class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td width = "100" class = "text-right">依托机构：</td>
										<td class = "text-left" ><a href = "4028d89a4ae844f5014ae84659450133" class="pop-view-agency">${product.agencyName }</a></td>
									</tr>
									<tr>
										<td width = "50" class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td width = "100" class = "text-right">提交状态：</td>
										<td class = "text-left" colspan = "4">${product.status}</td>
									</tr>
								  </tbody>
					    		</table>
					    	</div>
					    </div>
					    
					    <div class="tab-custom">
						  <!-- Nav tabs -->
						  <ul class="nav nav-tabs" role="tablist">
						    <li role="presentation" class="active"><a href="#basic" aria-controls="basic" role="tab" data-toggle="tab">基本信息</a></li>
						    <li role="presentation"><a href="#audit" aria-controls="audit" role="tab" data-toggle="tab">审核信息</a></li>
						    <li role="presentation"><a href="#award" aria-controls="award" role="tab" data-toggle="tab">奖励信息</a></li>
						  </ul>
						
						  <!-- Tab panes -->
						  <div class="tab-content ">
						    <div role="tabpanel" class="tab-pane fade in active" id="basic">
						    <table class="table table-striped table-bordered">
							      <tbody>
									<tr>
										<td width = "160" class = "text-right">成果形式</td>
										<td class = "text-left" >${product.type }</td>
										<td width = "160" class = "text-right">研究类型</td>
										<td class = "text-left" >${product.researchType }</td>
									</tr>
									<tr>
										<td width = "100" class = "text-right">学科分组</td>
										<td class = "text-left" >${product.discipline.name }</td>
										<td width = "100" class = "text-right">出版社或发表刊物名称</td>
										<td width ='' class = "text-left" >${ product.publishName}</td>
									</tr>
									<tr>
										<td width = "100" class = "text-right">出版社或发表刊物级别</td>
										<td width ='' class = "text-left" >${product.publishLevel }</td>
										<td width = "100" class = "text-right">出版或发表时间</td>
										<td class = "text-left" >${product.publishDate}</td>
									</tr>
									<tr>
										<td width = "100" class = "text-right">成果社会反映</td>
										<td width ='' class = "text-left" >${product.socialEffect }</td>
										<td width = "100" class = "text-right">推荐意见</td>
										<td class = "text-left" >${product.recommendation }</td>
									</tr>
									<tr>
										<td width = "100" class = "text-right">成果概要</td>
										<td width ='' class = "text-left" >${product.abstractStr }</td>
										<td width = "100" class = "text-right">成果简介</td>
										<td class = "text-left" >${product.introduction }</td>
									</tr>
								  </tbody>
					    		</table>
						    </div>
						    <div role="tabpanel" class="tab-pane fade " id="audit">
						    <table class="table table-striped table-bordered">
							      <tbody>
									<tr>
										<td width = "150" class = "text-right">评审状态</td>
										<td class = "text-left" colspan="3"></td>
									</tr>
									<tr>
										<td width = "100" class = "text-right">高校审核结果</td>
										<td class = "text-left" >${product.universityAuditResult}</td>
										<td width = "150" class = "text-right">高校审核意见</td>
										<td width ='' class = "text-left" >${ product.universityAuditOpinion }</td>
									</tr>
									<tr>
										<td width = "100" class = "text-right">社科联申报审核结果</td>
										<td width ='' class = "text-left" >${product.hsasApplyAuditResult }</td>
										<td width = "100" class = "text-right">社科联申报审核意见</td>
										<td class = "text-left" >${product.hsasApplyAuditOpinion }</td>
									</tr>
									<tr>
										<td width = "100" class = "text-right">社科联初评审核结果</td>
										<td width ='' class = "text-left" >${product.hsasReviewAuditResult }</td>
										<td width = "100" class = "text-right">社科联初评审核意见</td>
										<td class = "text-left" ></td>
									</tr>
									<tr>
										<td width = "100" class = "text-right">社科联复评审核结果</td>
										<td width ='' class = "text-left" >${product.hsasFinalAuditResult }</td>
										<td width = "100" class = "text-right">社科联复评审核意见</td>
										<td class = "text-left" >${product.hsasFinalAuditOpinion }</td>
									</tr>
									<tr>
										<td width = "100" class = "text-right">社科联终审审核结果</td>
										<td width ='' class = "text-left" ></td>
										<td width = "100" class = "text-right">社科联终审审核意见</td>
										<td class = "text-left" ></td>
									</tr>
								  </tbody>
					    		</table>
							</div>
						    <div role="tabpanel" class="tab-pane fade " id="award">
						    <table class="table table-striped table-bordered">
							      <tbody>
							    {if product.award == null}
									<tr>
										<td class = "text-center">没有奖励信息。</td>
									</tr>
								{elseif}
								{/if}
								  </tbody>
					    		</table>
							</div>
						  </div>
						</div>
			    	</textarea>
			    	<div id = "view_container" style = "display:none"></div><!-- 模版解析后的容器 -->
			    	
			    </div>
			</div>
			<div class="row">
   		<%@ include file="/jsp/footer.jsp"%>
		<script>
		    seajs.use("js/product/application/view.js", function(view) {
		         view.init(); 
		    });
		</script>
	</body>
</html>