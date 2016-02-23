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
   		<s:hidden id="entityId" name="entityId" value="%{entityId}" />
   		<a name="top" id="top"></a>
			<div class="row mySlide">
			<ol class="breadcrumb mybreadcrumb">当前位置：
			  <li><a href="#">新闻</a></li>
			  <li class="active">添加</li>
			</ol>
			    <div class="col-xs-10 main-content">
				   <div class="addTable">
				    	<form id="form_news" name="form_news" action="portal/news/add.action" method="post">
				    		<table width="100%">
				    			<!-- 从前台向后台传数据两种方法1：直接写入name=对象名.属性值；2：加上required，在后台逐个访问标签 -->
				    			<tbody><tr>
				    				<td width="80" class="text-right required-label">新闻标题：</td>
				    				<td><input type="text" name="article.title" id="" class="form-control input-sm validate[required]" placeholder=""></td>
				    			</tr>
				    			<tr>
				    				<td width="80" class="text-right required-label">新闻来源：</td>
				    				<td><input  type="text" name="article.source" id="" class="form-control input-sm validate[required]" placeholder=""/></td>
				    			</tr>
				    			<tr>
				    				<td width="80" class="text-right required-label">新闻类型：</td>
				    				<td>
				    					<select name="type" class="form-control input-sm validate[required]">
					    					<option value="news">社科动态</option>
					    					<option value="notice">通知公告</option>
					    					<option value="status">政策文件</option>
					    					<option value="rules">注意事项</option>
				    					</select>
				    				</td>
				    			</tr>
				    			<tr>
				    				<td width="80" class="text-right required-label" style="vertical-align: top;">是否公开：</td>
				    				<td>
				    					<label>公开<input  type="radio" value="1" name="article.isOpen" id="" class="validate[required]" placeholder=""/></label><br/>
										<label>非公开<input  type="radio" value="0" name="article.isOpen" id="" class="validate[required]" placeholder=""/></label>
				    				</td>
				    			</tr>
				    			<tr>
				    				<td width="100" class="text-right required-label" style="vertical-align: top;">新闻正文：</td>
				    				<td><textarea name="article.content" class = "form-control input-sm" id="news_content"></textarea></td>
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
		    seajs.use("js/portal/news/add.js", function(add) {
		         add.init(); 
		    });
		</script>
	</body>
</html>