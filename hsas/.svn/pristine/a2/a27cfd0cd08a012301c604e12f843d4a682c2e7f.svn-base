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
		<!-- header -->
		<%@ include file="/jsp/innerNav.jsp"%>
		<!-- end header -->
		
		<!-- body -->
		<div class="row mySlide">
			<ol class="breadcrumb mybreadcrumb">当前位置：
				<li><a href="javascript:void(0)">评审管理</a></li>
				<li><a href="javascript:void(0)">申诉</a></li>
				<li class="active">列表</li>
			</ol>
			
			<div class="col-xs-12 main-content">
				<!-- simpleSearch -->
				<div id="simple_search" style="display: none !important;">
					<form id="search" name="search" action="product/appeal/list.action" method="post" class="form-inline">
						<input type="hidden" name="sim_sysOptKeyword" value="" id="sim_sysOptKeyword">
						<table border="0" cellspacing="0" cellpadding="0" class="table_td_padding form-group pull-right">
							<tbody>
							<tr>
								<td align="right">
									<span class="choose_bar">
										<select name="searchType" id="search_searchType" class="select form-control input-sm">
											<option value="-1" selected="selected">--不限--</option>
											<option value="1">申诉主题</option>
											<option value="2">申诉人</option>
											<option value="3">更新时间</option>
										</select>
									</span>
									<input class="form-control input-sm" type="text" name="keyword" size="10" value="" id="keyword" class="keyword">
									<input type="hidden" name="pageNumber" value="" id="list_pagenumber">
									<input type="hidden" name="sortColumn" value="0" id="list_sortcolumn">
									<input type="hidden" name="pageSize" value="0" id="list_pagesize">
								</td>
								<td class = "btn-group" style = "display:block">
									<input id="list_button_query" type="button" value="检索" class="btn btn-default ">
								</td>
							</tr>
							</tbody>
						</table>
						<span class="clearfix"></span>
					</form>
				</div>
				<!-- end simpleSearch -->
				
				<!-- dataTable -->
				<textarea id="list_template" style="display:none;">
					<table id="list_table" width="100%" border="0" cellspacing="0" cellpadding="0" class="table table-striped table-bordered dataTable no-footer">
						<thead id="list_head">
							<tr class="table_title_tr">
								<td width="20" class="text-center"><input id="check" name="check" type="checkbox" title="点击全选/不选本页所有项目"/></td>
								<td width="50" class="text-center">序号</td>
								<td width="300" class="text-center"><a id="sortcolumn0" href="" class="{if sortColumn == 0}{if sortColumnLabel == 1}up_css{else}down_css{/if}{/if}" title="点击按申诉主题排序">申诉主题</a></td>
								<td width="100" class="text-center"><a id="sortcolumn1" href="" class="{if sortColumn == 1}{if sortColumnLabel == 1}up_css{else}down_css{/if}{/if}"  title="点击按申诉人排序">申诉人</a></td>
								<td width="100" class="text-center"><a id="sortcolumn2" href="" class="{if sortColumn == 2}{if sortColumnLabel == 1}up_css{else}down_css{/if}{/if}"  title="点击按申诉时间排序">申诉时间</a></td>
							</tr>
						</thead>
						<tbody>
						{for item in root}
							<tr>
								<td class="text-center"><input type="checkbox" name="entityIds" value="${item.laData[0]}" /></td>
								<td class="text-center">${item.num}</td>
								<td class="table_txt_td"><a id="${item.laData[0]}" class="link1" title="点击查看详细信息" >${item.laData[1]}</a></td>
								<td class="text-center">${item.laData[2]}</td>
							  	<td class="text-center">${item.laData[3]}</td>
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
								<button id = "list_add" class = "btn btn-default " type="button">添加</button>
								<button id = "list_delete" class = "btn btn-default " type="button">删除</button>
								<%--<button id = "list_export" class = "btn btn-default " type="button">导出</button>
							--%></td>
						</tr>
					</table>
				</textarea>
				<s:form id="list" theme="simple" action="product/appeal/delete.action">
					<s:hidden id="pagenumber" name="pageNumber"/>
					<s:hidden id="checkedIds" name="checkedIds"/>
					<div id="list_container"  style="display:none;"></div>
				</s:form> 
			</div>
		</div>
		<!-- end body -->

		<!-- footer -->
		<div class="row">
		<%@ include file="/jsp/footer.jsp"%>
		<!-- end footer -->
		
		<script>
			seajs.use("js/product/appeal/list.js", function(list) {
				list.init(); 
			});
		</script>
	</body>
</html>