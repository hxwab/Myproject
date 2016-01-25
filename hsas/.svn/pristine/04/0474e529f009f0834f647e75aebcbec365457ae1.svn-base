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
   		<s:hidden id="entityId" name="entityId" value="%{entityId}" />
   		<a name="top" id="top"></a>
			<div class="row mySlide">
				<ol class="breadcrumb mybreadcrumb">当前位置：
				  <li><a href="#">系统管理</a></li>
				  <li><a href="#">邮件管理</a></li>
				  <li class="active">详情</li>
				</ol>
			    <div class="col-xs-12">
					<div class="btn-group pull-right view-controler" role="group" aria-label="...">
						<button type="button" class="btn btn-default" id = "view_add">添加</button>
						<button type="button" class="btn btn-default" id = "view_cancel">取消</button>
						<button type="button" class="btn btn-default" id = "view_send">发送</button>
						<button type="button" class="btn btn-default" id = "view_mod">修改</button>
			  			<button type="button" class="btn btn-default" id = "view_del">删除</button>
			  			<button type="button" class="btn btn-default" id = "view_prev">上一条</button>
			  			<button type="button" class="btn btn-default" id = "view_next">下一条</button>
			  			<button type="button" class="btn btn-default" id = "view_back">返回</button>
					</div>
			    </div>
			</div>
			<span class="clearfix"></span><!-- 重要!! 用于清除按键组浮动 -->
			
			<textarea id = "view_template" style = "display:none"><!-- 前台模版 -->
				<div class="panel panel-default panel-view">
					<div class="panel-heading"><strong>基本信息</strong></div>
					  <div class="panel-body">
					    <table class="table table-striped view-table">
					      <tbody>
							<tr>
								<td width = "50" class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
								<td class = "text-right view-key" width = "100">邮件主题：</td>
								<td class = "text-left">${mail.subject}</td>
								<td width = "50" class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
								<td class = "text-right view-key" width = "100">收件人：</td>
								<td class = "text-left">{if mail.sendTo!=null}${mail.sendTo}{else}${mail.sended}{/if}</td>
							</tr>
							<tr>
								<td width = "50" class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
								<td width = "100" class = "text-right view-key">创建账号：</td>
								<td class = "text-left" width='200'>${mail.subject}</td>
								<td width = "50" class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
								<td width = "130" class = "text-right view-key" >创建者：</td>
								<td class = "text-left" >${mail.subject} </td>
							</tr>
							<tr>
								<td width = "50" class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
								<td width = "100" class = "text-right view-key">创建时间：</td>
								<td class = "text-left" width='200'>${mail.createDate}</td>
								<td width = "50" class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
								<td width = "130" class = "text-right view-key" >尝试次数：</td>
								<td class = "text-left" >${mail.sendTimes}</td>
							</tr>
						  </tbody>
			    		</table>
					  </div>
					</div> <!-- end <div class="panel panel-default"> -->
					
					<div class="panel panel-default panel-view">
						<div class="panel-heading"><strong>邮件内容</strong></div>
						<div class="panel-body">
							<div style="min-height:200px;">${mail.body}</div>
						</div>
					</div>
			</textarea>
			<div id = "view_container" style = "display:none"></div><!-- 模版解析后的容器 -->
			
		<div class="row">
   		<%@ include file="/jsp/footer.jsp"%>
		<script>
			seajs.use("js/system/mail/view.js", function(view) {
		         view.init(); 
		    });
		</script>
	</body>
</html>