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
   		<s:hidden id="entityId" name="entityId" />
		
		<div class="row mySlide">
			<ol class="breadcrumb mybreadcrumb">当前位置：
				<li>系统管理</li>
				<li>投稿管理</li>
				<li class="active">查看</li>
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
									<tr>
										<td width = "50" class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td width = "100" class = "text-right view-key">名称：</td>
										<td class = "text-left" colspan="4">${solicitPapers.name}</td>
									</tr>
									<tr>
										<td width = "50" class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td width = "100" class = "text-right view-key">申请人：</td>
										<td width = "250" class = "text-left">${solicitPapers.author}</td>
										<td width = "50" class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td width = "100" class = "text-right view-key">性别：</td>
										<td width = "250" class = "text-left">${solicitPapers.gender}</td>
									</tr>
									<tr>
										<td class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td class = "text-right view-key">出生年月：</td>
										<td class = "text-left">${solicitPapers.birthday|toFormat}</td>
										<td class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td class = "text-right view-key">所在单位：</td>
										<td class = "text-left">${solicitPapers.unit}</td>
									</tr>
									<tr>
										<td class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td class = "text-right view-key">联系电话：</td>
										<td class = "text-left">${solicitPapers.phone}</td>
										<td class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td class = "text-right view-key">电子邮件：</td>
										<td class = "text-left">${solicitPapers.email}</td>
									</tr>
									<tr>
										<td class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td class = "text-right view-key">投稿日期：</td>
										<td class = "text-left">${solicitPapers.createDate|toFormat}</td>
										<td class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td class = "text-right view-key">邮政编码：</td>
										<td class = "text-left">${solicitPapers.postcode}</td>
									</tr>
									<tr>
										<td width = "50" class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td width = "100" class = "text-right view-key">联系地址：</td>
										<td class = "text-left" colspan="4">${solicitPapers.address}</td>
									</tr>
									<tr>
										<td width = "50" class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td width = "100" class = "text-right view-key">上传附件：</td>
										<td class = "text-left" colspan="4">
											{if solicitPapers.attachmentName==undefined || solicitPapers.attachmentName==null}
												暂无附件上传
											{else}
												<a href = "portal/download/downloadPaper.action?entityId=${solicitPapers.id}">${solicitPapers.attachmentName}</a>
											{/if}
										</td>
									</tr>
									<tr>
										<td width = "50" class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td width = "100" class = "text-right view-key">内容简介：</td>
										<td class = "text-left" colspan="4">${solicitPapers.note}</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</textarea>
				<div id = "view_container" style = "display:none"></div><!-- 模版解析后的容器 -->
				
			</div>
		</div>
		
		<div class="row">
   		<%@ include file="/jsp/footer.jsp"%>
   		
   		<script>
			seajs.use("js/solicitPapers/view.js", function(view) {
				view.init(); 
			});
		</script>
	</body>
</html>