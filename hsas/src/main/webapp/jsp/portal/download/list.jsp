<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page isELIgnored ="true"%><%--添加对EL表达式的支持--%>
<%@ taglib prefix="s" uri="/struts-tags"%> <%--添加对struts标签的支持 --%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
	<head>
		<title>湖北省社会科学优秀成果奖申报评审系统</title>
		<%@ include file="/jsp/base.jsp"%>
	</head>
	<body>
		<%@ include file="/jsp/innerNav.jsp"%>
		
		<div class="row">
			<ol class="breadcrumb mybreadcrumb">当前位置：
				<li class="active">常用下载</li>
			</ol>
		</div>
		<div class="row mySlide">
			<div class="col-xs-3 sidebar1 view-nav-bar no-padding-right">
				<!-- <div><a href="portal/news/toList.action?type=news&update=1"><i class="fa fa-newspaper-o fa-2"></i>&nbsp;社科动态&nbsp;>></a></div>-->
					<div><a href="portal/news/toList.action?type=notice&update=1"><i class="fa fa-book fa-2"></i>&nbsp;通知通告&nbsp;>></a></div>
					<div><a href="portal/news/toList.action?type=status&update=1"><i class="fa fa-file-text fa-2"></i>&nbsp;政策公告&nbsp;>></a></div>
					<!-- <div><a href="portal/news/toList.action?type=rules&update=1"><i class="fa fa-question-circle fa-2"></i>&nbsp;注意事项&nbsp;>></a></div>-->
			</div>
				
			<div class="col-xs-9 sidebar2 no-padding-left">
				<div id="simple_search" style="display: none !important;">
					<form id="search"  class="form-inline" name="search" action="portal/download/list.action" method="post">
						<input type="hidden" name="sim_sysOptKeyword" value="" id="sim_sysOptKeyword">
						<table border="0" cellspacing="0" cellpadding="0" class="table_td_padding form-group pull-right">
							<tbody>
							<tr>
							 <td align="right">
							<span class="choose_bar">
							<select name="searchType" id="search_searchType" class="select form-control input-sm">
								<option value="-1" selected="selected">--不限--</option>
								<!-- 名称、作者、发表时间、指标类型、模板类型、备注 -->
								<option value="1">文件名称</option>
							</select>
							</span>
									<input id="keyword" class="form-control input-sm keyword" type="text" name="keyword" size="10" value="">
									<input id="list_pagenumber" type="hidden" name="pageNumber" value="" >
									<input id="list_sortcolumn" type="hidden" name="sortColumn" value="0" >
									<input id="list_pagesize" type="hidden" name="pageSize" value="0" >
								</td>
								<td class = "btn-group" style = "display:block">
									 <input id="list_button_query" class="btn btn-default " type="button" value="检索" >
								</td>
							</tr>
							</tbody>
						</table>
						<span class="clearfix"></span>
					</form>
				</div>
			
				<!-- 显示列表 -->
				<textarea id="list_template" style="display:none;">
					<table id="list_table" class="table table-striped table-bordered dataTable no-footer" width="100%" border="0" cellspacing="0" cellpadding="0">
						<thead id="list_head">
							<tr class="table_title_tr">
								<td width="20" class="text-center"><input id="check" name="check" type="checkbox" title="点击全选/不选本页所有项目"/></td>
								<td width="40" class="text-center">序号</td>
								<td width="300"><a id="sortcolumn0" href="" class="{if sortColumn == 0}{if sortColumnLabel == 1}up_css{else}down_css{/if}{/if}" title="点击按文件名称排序">文件名称</a></td>
								<td width="80" ><a id="sortcolumn1" href="" class="{if sortColumn == 1}{if sortColumnLabel == 1}up_css{else}down_css{/if}{/if}"  title="点击按发布日期排序">发布日期</a></td>
							</tr>
						</thead>
						<tbody>
						{for item in root}
						<tr>
							<td class="text-center"><input type="checkbox" name="entityIds" value="${item.laData[0]}"/></td>
							<td class="text-center">${item.num}</td>
							<td class="table_txt_td"><a href="portal/download/download.action?entityId=${item.laData[0]}" title="点击查看详细信息" type="1">${item.laData[1]}</a></td>
							<td >${item.laData[2]|toFormat}</td>
						</tr>
						{forelse}
						<tr>
							<td align="center">暂无符合条件的记录</td>
						</tr>
						{/for}
						</tbody>
					</table>
					
					<table width="100%" border="0" cellspacing="0" cellpadding="0" class="table_td_padding form-inline">
						<tr class="table_main_tr2">
							<td class = "btn-group">
								<sec:authorize ifAllGranted="ROLE_PORT_EDITOR">
								<button id = "list_add" class = "btn btn-default ">添加</button>
								<button id = "list_delete" class = "btn btn-default ">删除</button>
								</sec:authorize>
							</td>
						</tr>
					</table>
					
				</textarea>
				<s:form id="list" theme="simple" action="delete" namespace="/portal">
					<s:hidden id="pagenumber" name="pageNumber"/>
					<s:hidden id="checkedIds" name="checkedIds"/>
					<div id="list_container"  style="display:none;"></div>
				</s:form>
			</div>
		</div>
		
		<div class="row">
		<%@ include file="/jsp/footer.jsp"%>
		<!-- <script src="js/main.js" type="text/javascript"></script> -->
	</body>
	<script>
		seajs.use('js/portal/download/list.js', function(list){
			list.init();
		})
	</script>
</html>