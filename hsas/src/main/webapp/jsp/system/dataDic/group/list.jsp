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
		<!-- 页头 -->
   		<%@ include file="/jsp/innerNav.jsp"%>
   		<a name="top" id="top"></a>
		
		<!-- 页面主体 -->
		<div class="row mySlide">
			<!-- 当前位置 -->
			<ol class="breadcrumb mybreadcrumb">当前位置：
			  <li><a href="#">系统管理</a></li>
			  <li><a href="#">学科分组</a></li>
			  <li class="active">列表</li>
			</ol>
			
			<!-- 列表 -->
			<div class="col-xs-12">
				<!-- 初级检索 -->
				<div id="simple_search" style="display: none !important;">
					<form id="search" name="search" action="system/dataDic/group/list.action" method="post"
						  class="form-inline">
						<input type="hidden" name="sim_sysOptKeyword" value="" id="sim_sysOptKeyword">
						<table border="0" cellspacing="0" cellpadding="0" class="table_td_padding form-group pull-right">
							<tbody>
							<tr>
							 <td align="right">
							<span class="choose_bar">
							<select name="searchType" id="search_searchType" class="select form-control input-sm">
								<option value="-1" selected="selected">--不限--</option>
								<!-- 名称、作者、发表时间、指标类型、模板类型、备注 -->
								<option value="1">分组名称</option>
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
		
				<!-- 列表数据模板 -->
				<textarea id="list_template" style="display:none;">
					<table id="list_table" class="table table-striped table-bordered dataTable no-footer" width="100%" border="0" cellspacing="0" cellpadding="0">
						<thead id="list_head">
							<tr class="table_title_tr">
								<td width="20" class="text-center"><input id="check" name="check" type="checkbox" title="点击全选/不选本页所有项目"/></td>
								<td width="40" class="text-center">序号</td>
								<td width="100"><a id="sortcolumn0" href="" class="{if sortColumn == 0}{if sortColumnLabel == 1}up_css{else}down_css{/if}{/if}" title="点击按账号名称排序">分组名称</a></td>
								<td width="300">分组描述</td>
							</tr>
						</thead>
						<tbody>
							{for item in root}
							<tr>
								<td class="text-center"><input type="checkbox" name="entityIds" value="${item.laData[0]}" alt="${item.laData[1]}"/></td>
								<td class="text-center">${item.num}</td>
								<td class="table_txt_td">${item.laData[1]}</td>
								<td class="table_txt_td">${item.laData[2]}</td>
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
									<button id = "list_add_displineGroup" class = "btn btn-default " type="button">添加</button>
									<button id = "list_delete" class = "btn btn-default " type="button">删除</button>
									<button id = "list_delete" class = "btn btn-default " type="button">导出</button>
									<button id = "group_merge" class = "btn btn-default " type="button">分组合并</button>
								</td>
							</tr>
						</table>
				</textarea>
			
				<!-- 显示列表 -->
				<s:form id="list" theme="simple" action="delete" namespace="/portal">
					<s:hidden id="pagenumber" name="pageNumber"/>
					<s:hidden id="checkedIds" name="checkedIds"/>
					<div id="list_container"  style="display:none;"></div>
				</s:form>
				
				<!-- 添加弹层 -->
				<div class="hidden">
					<form  method="post" class="form_addDisplineGroup" action="system/dataDic/group/add.action">
						<table width="100%" class='addTable'>
							<!-- 从前台向后台传数据两种方法1：直接写入name=对象名.属性值；2：加上required，在后台逐个访问标签 -->
							<tbody>
								<tr>
									<td width="80" class="text-right">学科名称：</td>
									<td><input type="text" name="group.name" class="form-control input-sm validate[required]" placeholder="请输入名称"></td>
								</tr>
								<tr>
									<td width="80" class="text-right">说明：</td>
									<td><textarea class="form-control input-sm " name="group.description" rows="3" placeholder="请输入说明"></textarea></td>
								</tr>
							</tbody>
						</table>
					</form>
					
					
					<%--<form  method="post" class="form_reGrouping">
						<table width="100%" class='addTable'>
							<!-- 从前台向后台传数据两种方法1：直接写入name=对象名.属性值；2：加上required，在后台逐个访问标签 -->
							<tbody>
								<tr>
									<td width="80" class="text-right">分组名称：</td>
									<td>
									<select type="text" name="groupName" class="form-control input-sm validate[required]" placeholder="请输入名称">
										<option>分组1</option>
										<option>分组2</option>
										<option>分组3</option>
										<option>分组4</option>
									</select>
									</td>
								</tr>
							</tbody>
						</table>
					</form>
				--%></div>
				
				<div id="group_merge-template" style="display:none">
					<table class="table-merge">
						<tr>
							<td><select class="src_group form-control"></select></td>
						</tr>
						<tr>
							<td align="center"><button class="btn btn-info">合并到 <br /><i class="fa fa-angle-double-down"></i></button></td>
						</tr>
						<tr>
							<td><select class="dst_group form-control"></select></td>
						</tr>
					</table>
				</div>
			</div>
		</div>
		
		<!-- 页脚 -->
		<div class="row">
		<%@ include file="/jsp/footer.jsp"%>
	</body>
	<script>
		seajs.use("js/system/dataDic/group/list.js", function(list) {
			list.init(); 
		});
	</script>
</html>