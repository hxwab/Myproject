<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page isELIgnored ="true"%><%--添加对EL表达式的支持--%>
<%@ taglib prefix="s" uri="/struts-tags"%> <%--添加对struts标签的支持 --%>
<!DOCTYPE html>
<html>
    <head>
        <title>湖北省社会科学优秀成果奖申报评审系统</title>
        <%@ include file="/jsp/base.jsp"%>
        <link rel="stylesheet" href="lib/uploadify/css/uploadify.css">
    </head>
    <body>
   		<%@ include file="/jsp/nav.jsp"%>
			<div class="row mySlide">
			<ol class="breadcrumb mybreadcrumb">当前位置：
			  <li><a href="#">新闻</a></li>
			  <li class="active">修改</li>
			</ol>
			    <div class="col-xs-10 main-content">
				   <div class="addTable">
				    	<form id="form_news" name="form_news" action="portal/news/modify.action" method="post">
				    	<s:hidden id="entityId" name="entityId" value="%{entityId}" />
				    		<table width="100%">
				    			<!-- 从前台向后台传数据两种方法1：直接写入name=对象名.属性值；2：加上required，在后台逐个访问标签 -->
				    			<tbody><tr>
				    				<td width="80" class="text-right required-label">新闻标题：</td>
				    				<td><s:textfield type="text" name="article.title" id="" class="form-control input-sm validate[required]" placeholder=""/></td>
				    			</tr>
				    			<tr>
				    				<td width="80" class="text-right required-label">新闻来源：</td>
				    				<td><s:textfield  type="text" name="article.source" id="" class="form-control input-sm validate[required]" placeholder=""/></td>
				    			</tr>
				    			<tr>
				    				<td width="80" class="text-right required-label">新闻类型：</td>
				    				<td>
				    					<select name="type" class="form-control input-sm validate[required]" value=<s:property value='article.systemOption.id'/>>
					    					<option value="news">社科动态</option>
					    					<option value="notice">通知公告</option>
					    					<option value="status">政策文件</option>
					    					<option value="rules">注意事项</option>
				    					</select>
				    					<s:textfield  type="hidden" name="article.systemOption.id" id="article_type"/>
				    				</td>
				    			</tr>
				    			<tr>
				    				<td width="80" class="text-right required-label" style="vertical-align: top;">是否公开：</td>
				    				<td>
				    					<s:radio list="#{'0':'不公开','1':'公开'}" name="article.isOpen" value="article.isOpen"/>
				    				</td>
				    			</tr>
				    			<tr>
				    				<td width="100" class="text-right required-label" style="vertical-align: top;">新闻正文：</td>
				    				<td><s:textarea name="article.content" class = "form-control input-sm" id="news_content"></s:textarea></td>
				    			</tr>
				    			<tr>
				    				<td width="100" class="text-right ">附件：</td>
				    				<td class='position-relative' >
				    					<label class="sr-only" for="inputfile">文件输入</label>
				    					<input type="file" id="newsAttachmentUpload"  name="article.attachment" class='validate[required]'>
				    					<input type="text" class=' pull-right validation-file-input' name="article.attachmentName" value="">
				    				</td>
				    			</tr>
				    		</tbody></table>
				    		<div class="text-center">
				            	<div class="btn-group">
				            		<input type="button" value="确定" class="btn btn-default btn-sm" id="submit">
				            		<input type="button" value="取消" class="btn btn-default btn-sm" id="cancel">
				            	</div>
				            </div>
				   		</form>
				   		
				    </div>
			    </div>
			</div>
			<div class="row">
   		<%@ include file="/jsp/footer.jsp"%>
		<script>
		    seajs.use("js/portal/news/modify.js", function(modify) {
		    	modify.init(); 
		    });
		</script>
	</body>
</html>