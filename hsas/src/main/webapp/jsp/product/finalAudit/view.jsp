<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page isELIgnored ="true"%><%--添加对EL表达式的支持--%>
<%@ taglib prefix="s" uri="/struts-tags"%> <%--添加对struts标签的支持 --%>
<!DOCTYPE html>
<html>
    <head>
        <title>湖北省社会科学优秀成果奖申报评审系统</title>
        <%@ include file="/jsp/base.jsp"%>
        <link rel="stylesheet" type="text/css" href="lib/fancyBox-2.1.5/jquery.fancybox.css?v=2.1.5" />
    </head>
    <body>
   		<%@ include file="/jsp/innerNav.jsp"%>
   		<s:hidden id="entityId" name="entityId" />
   		<a name="top" id="top"></a>
			<div class="row mySlide">
			<ol class="breadcrumb mybreadcrumb">当前位置：
				  <li><a href="#">评奖管理</a></li>
				  <li class="active">查看</li>
				</ol>
			</ol>
			    <div class="col-xs-12 main-content">
				     <div class="btn-group pull-right view-controler" role="group" aria-label="...">
			  			<button type="button" class="btn  btn-default" id = "view_add">添加</button>
			  			<button type="button" class="btn  btn-default" id = "view_mod">修改</button>
			  			<button type="button" class="btn  btn-default" id = "view_del">删除</button>
			  			<button type="button" class="btn  btn-default" id = "view_prev">上一条</button>
			  			<button type="button" class="btn  btn-default" id = "view_next">下一条</button>
			  			<button type="button" class="btn  btn-default" id = "view_back">返回</button>
					</div>
					<span class="clearfix"></span><!-- 重要!! 用于清除按键组浮动 -->
					 <textarea id = "view_template" style = "display:none"><!-- 前台模版 -->
						 <div class="panel panel-default panel-view">
						  	<div class="panel-body">
							    <table class="table table-striped view-table">
							      <tbody>
							     	<tr style="height:0;">
										<td width = "50"></td>
										<td width = "120"></td>
										<td width = "300"></td>
										<td width = "50"></td>
										<td width = "120"></td>
										<td width = "300"></td>
									</tr>
							      	<tr>
										<td class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td class = "text-right view-key">成果名称：</td>
										<td class = "text-left" colspan="4">${product.name}</td>
										
									</tr>
									<tr>
										<td class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td class = "text-right view-key">申报编号：</td>
										<td class = "text-left">${product.number}</td>
										<td class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td class = "text-right view-key">作者：</td>
										<td class = "text-left"><a href = "${product.author.id}" class="pop-view-person">${product.author.name}</a></td>
									</tr>
									<tr>
										<td class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td class = "text-right view-key">成果形式：</td>
										<td class = "text-left"  width = "100">${product.type }</td>
										<td class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td class = "text-right view-key">研究类型：</td>
										<td class = "text-left" >${product.researchType}</td>
									</tr>
									<tr>
										<td class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td class = "text-right view-key">学科分类：</td>
										<td class = "text-left" >${product.groups.name }</td>
										<td class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td class = "text-right view-key">单位：</td>
										<td class = "text-left" ><a href = "${product.agency.id }" class="pop-view-agency">${product.agencyName }</a></td>
									</tr>
									<tr>
										<td class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td class = "text-right view-key">资格审核材料：</td>
										<td class = "text-left" >
											{if !!product.fileName}
											${product.fileName|pdfFile}&nbsp;&nbsp;
											<a class="fancybox fancybox.iframe" href="${product.file|pdfFile}">预览</a>
											<a href="${product.file|pdfFile}">下载</a>
											{else} 暂无附件
											{/if}
										</td>
										<td class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td class = "text-right view-key" >成果附件：</td>
										<td class = "text-left" >
											{if !!product.fileName}
											${product.fileName|zipOrRar}&nbsp;&nbsp;<a href="${product.file|zipOrRar}">下载</a>
											{else} 暂无附件
											{/if}
										</td>
									</tr>
								  </tbody>
					    		</table>
					    	</div>
					    </div>
					    
					    <div class="tab-custom">
							<!-- Nav tabs -->
							<ul class="nav nav-tabs" role="tablist">
								<li role="presentation"><a href="#basic" aria-controls="basic" role="tab" data-toggle="tab">基本信息</a></li>
								<li role="presentation"><a href="#firstAudit" aria-controls="firstAudit" role="tab" data-toggle="tab">初审信息</a></li>
								<li role="presentation"><a href="#firstReview" aria-controls="firstReview" role="tab" data-toggle="tab">初评信息</a></li>
								<li role="presentation"><a href="#secondReview" aria-controls="secondReview" role="tab" data-toggle="tab">复评信息</a></li>
								<li role="presentation" class="active"><a href="#finalAudit" aria-controls="finalAudit" role="tab" data-toggle="tab">终审信息</a></li>
							</ul>
							<!-- end Nav tabs -->
						
							<!-- Tab panes -->
							<div class="tab-content ">
								<!-- 基本信息 -->
								<div role="tabpanel" class="tab-pane fade" id="basic">
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
							    </div>
							
								<!-- 初审信息 -->
								<div role="tabpanel" class="tab-pane fade" id="firstAudit">
									<table class="table table-bordered">
										
										<tbody>
											<tr>
												<td width="120" class = "text-center table-head-color ">审核机构</td>
												<td width="200" class = "text-center table-head-color ">提交状态</td>
												<td width="120" class = "text-center table-head-color ">初审结果</td>
												<td width="200" class = "text-center table-head-color ">初审意见</td>
											</tr>
											<tr>
												<td class = "text-center view-key">高校机构</td>
												<td class = "text-center">
													{if product.universityAuditResult == 4} —
													{elseif product.submitStatus == 2}已提交
													{elseif product.submitStatus == 1}暂存
													{else}未知
													{/if}
												</td>
												<td class = "text-center">
													{if product.universityAuditResult == 4} —
													{elseif product.universityAuditResult == 0}
														<button class="btn  btn-primary btn-first-audit" type="button">审核</button>
													{elseif product.universityAuditResult == 1}不同意
													{elseif product.universityAuditResult == 2}同意
													{elseif product.universityAuditResult == 3}退回
													{else}未知
													{/if}
												</td>
												<td class = "text-center" >
													{if product.universityAuditResult == 4} —
													{else}${product.universityAuditOpinion}
													{/if}
												</td>
											</tr>
											<tr>
												<td class = "text-center view-key">社科联</td>
												<td class = "text-center">
													{if product.submitStatus == 2}已提交{elseif product.submitStatus == 1}暂存{else}未知{/if}
												</td>
												<td class = "text-center">
													{if product.hsasApplyAuditResult == 0}
														<button class="btn  btn-primary btn-first-audit" type="button">审核</button>
													{elseif product.hsasApplyAuditResult == 1}不同意
													{elseif product.hsasApplyAuditResult == 2}同意
													{elseif product.hsasApplyAuditResult == 3}退回
													{/if}
												</td>
												<td class = "text-center" >
													${product.hsasApplyAuditOpinion}
												</td>
											</tr>
										</tbody>
									</table>
							    </div>
								
								<!-- 初评信息 -->
								<div role="tabpanel" class="tab-pane fade " id="firstReview">
									<table class="table table-striped table-bordered">
										<tbody>
											<tr>
												<td width = "100" class = "text-right view-key">初评平均分</td>
												<td width = "500"class = "text-left" >${product.averageScore}</td>
											</tr>
											<tr>
												<td class = "text-right view-key">初评意见</td>
												<td class = "text-left" ></td>
											</tr>
											<tr>
												<td class = "text-right view-key">是否进入复评</td>
												<td class = "text-left" >
													{if product.hsasReviewAuditResult=1}否
													{elseif product.hsasReviewAuditResult=2}是
													{else}待确定
													{/if}
												</td>
											</tr>
										</tbody>
							    	</table>
								</div>
								
								<!-- 复评信息 -->
								<div role="tabpanel" class="tab-pane fade " id="secondReview">
									<table class="table table-striped table-bordered">
										<tbody>
											<tr>
												<td width = "100" class = "text-right view-key">推荐等级</td>
												<td width = "500"class = "text-left" >${product.rewardLevel}</td>
											</tr>
											<tr>
												<td class = "text-right view-key">专家组意见</td>
												<td class = "text-left" >${product.rewardLevel}</td>
											</tr>
											<tr>
												<td class = "text-right view-key">专家组审核结果</td>
												<td class = "text-left" >
													{if product.leaderAuditResult==1}退回
													{elseif product.leaderAuditResult==2}同意
													{else}待审
													{/if}
												</td>
											</tr>
										</tbody>
						    		</table>
								</div>
								
								<!-- 终审信息 -->
								<div role="tabpanel" class="tab-pane fade in active" id="finalAudit">
									<table class="table table-striped table-bordered">
										<tbody>
											<tr>
												<td width = "100" class = "text-right view-key">终审审核结果</td>
												<td width = "500" class = "text-left" >
													{if product.hsasFinalAuditResult == 1}不同意
													{elseif product.hsasFinalAuditResult == 2}同意
													{else}
														<button class="btn  btn-primary btn-final-audit" type="button">审核</button>
													{/if}
												</td>
											</tr>
											<tr>
												<td class = "text-right view-key">终审审核意见</td>
												<td class = "text-left" >${product.hsasFinalAuditOpinion}</td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
							<!-- end Tab panes -->
						</div>
			    	</textarea>
			    	<div id = "view_container" style = "display:none"></div><!-- 模版解析后的容器 -->
			    	
			    </div>
			</div>
			<div class="row">
   		<%@ include file="/jsp/footer.jsp"%>
		<script>
		    seajs.use("js/product/finalAudit/view.js", function(view) {
		         view.init(); 
		    });
		</script>
	</body>
</html>