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
				<li><a href="#">机构管理</a></li>
				<li class="active">列表</li>
			</ol>
			
			<!-- 列表 -->
			<div class="col-xs-12">
				<!-- 初级检索 -->
				<div id="simple_search" style="display: none !important;">
					<form id="search" name="search" action="system/dataDic/agency/list.action" method="post" class="form-inline">
						<input type="hidden" name="sim_sysOptKeyword" value="" id="sim_sysOptKeyword">
						<table border="0" cellspacing="0" cellpadding="0" class="table_td_padding form-group pull-right">
							<tbody>
							<tr>
								<td align="right">
									<span class="choose_bar">
										<select name="searchType" id="search_searchType" class="select form-control input-sm">
											<option value="-1" selected="selected">--不限--</option>
											<option value="1">机构名称</option>
											<option value="2">联系人</option>
											<option value="3">手机号码</option>
											<option value="4">办公电话</option>
										</select>
									</span>
									<input id="keyword" class="form-control input-sm keyword" type="text" name="keyword" size="10" value="">
									<input id="list_pagenumber" type="hidden" name="pageNumber" value="" >
									<input id="list_sortcolumn" type="hidden" name="sortColumn" value="0" >
									<input id="list_pagesize" type="hidden" name="pageSize" value="0" >
								</td>
								<td class = "btn-group" style = "display:block">
									 <input id="list_button_query" class="btn btn-default " type="button" value="检索" ><%--
									 <input id="list_search_more" type="button" value="更多条件" class="btn btn-default ">
								--%></td>
							</tr>
							</tbody>
						</table>
						<span class="clearfix"></span>
					</form>
				</div>
				
				<!-- 高级检索 -->
				<div id="adv_search"  style="display:none;">
					<form id="advSearch" action="advSearch" theme="simple" namespace="" method="post">
						<input type="hidden" name="adv_sysOptKeyword" value="" id="adv_sysOptKeyword">
						<div class="adv_content">
							<table class="adv_table" width="100%" border="0" cellspacing="2" cellpadding="0">
								<tr class="adv_tr">
									<td class="adv_td1" width="100" align="right">机构名称：</td>
									<td class="adv_td1" width="200"><input type="text" class="form-control input-sm" name="adv_name"></td>
									<td class="adv_td1" width="100"></td>
									<td class="adv_td1" width="100" align="right"></td>
									<td class="adv_td1" width="200"></td>
									<td class="adv_td1"></td>
								</tr>
								<tr class="adv_tr">
									<td class="adv_td1" width="100" align="right">机构类型：</td>
									<td class="adv_td1" width="200"><input type="text" class="form-control input-sm" name="adv_name"></td>
									<td class="adv_td1" width="100"></td>
									<td class="adv_td1" width="100" align="right">负责人：</td>
									<td class="adv_td1" width="200"><input type="text" class="form-control input-sm" name="adv_author"></td>
									<td class="adv_td1"></td>
								</tr>
							</table>
							 <table border="0" cellspacing="0" cellpadding="0" class="table-search form-group pull-right">
								<tbody>
									<tr>
										<td align="right"></td>
										<td class = "btn-group" style = "display:block">
											<input id="list_button_advSearch" type="button" value="检索" class="btn btn-default ">
											<input id="list_search_hide" type="button" value="隐藏条件" class="btn btn-default ">
										</td>
									</tr>
								</tbody>
							</table>
							<!-- 清除浮动 -->
							<span class="clearfix"></span>
						</div>
					</form>
				</div>
				
				<!-- 列表数据模板 -->
				<textarea id="list_template" style="display:none;">
					<table id="list_table"  class="table table-striped table-bordered dataTable no-footer" width="100%" border="0" cellspacing="0" cellpadding="0">
						<thead id="list_head">
							<tr class="table_title_tr">
								<td width="20"><input id="check" name="check" type="checkbox" title="点击全选/不选本页所有项目"/></td>
								<td width="50" class="text-center">序号</td>
								<td width="300" class="text-center"><a id="sortcolumn0" href="" class="{if sortColumn == 0}{if sortColumnLabel == 1}up_css{else}down_css{/if}{/if}" title="点击按机构名称排序">机构名称</a></td>
								<td width="150" class="text-center"><a id="sortcolumn1" href="" class="{if sortColumn == 1}{if sortColumnLabel == 1}up_css{else}down_css{/if}{/if}"  title="点击按机构类型排序">机构类型</a></td>
								<td width="150" class="text-center"><a id="sortcolumn2" href="" class="{if sortColumn == 2}{if sortColumnLabel == 1}up_css{else}down_css{/if}{/if}"  title="点击按联系人排序">联系人</a></td>
								<td width="200" class="text-center"><a id="sortcolumn3" href="" class="{if sortColumn == 3}{if sortColumnLabel == 1}up_css{else}down_css{/if}{/if}"  title="点击按手机号码排序">手机号码</a></td>
								<td width="200" class="text-center"><a id="sortcolumn4" href="" class="{if sortColumn == 4}{if sortColumnLabel == 1}up_css{else}down_css{/if}{/if}"  title="点击按办公电话排序">办公电话</a></td>
							</tr>
						</thead>
						<tbody>
							{for item in root}
							<tr>
								<td><input type="checkbox" name="entityIds" value="${item.laData[0]}"/></td>
								<td class="text-center">${item.num}</td>
								<td class="table_txt_td text-center"><a id="${item.laData[0]}" href="javascript:void(0)" class="link1" title="点击查看详细信息" type="1">${item.laData[1]}</a></td>
								<td class="text-center">{if item.laData[3]==0}非高校{else}高校{/if}</td>
								<td class="text-center">${item.laData[5]}</td>
								<td class="text-center">${item.laData[6]}</td>
								<td class="text-center">${item.laData[7]}</td>
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
								<button id = "list_add" class = "btn btn-default ">添加</button>
								<button id = "list_delete" class = "btn btn-default ">删除</button>
								<button id = "list_expert" class = "btn btn-default ">导出</button>
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
			</div>
		</div>
		
		<!-- 页脚 -->
		<div class="row">
   		<%@ include file="/jsp/footer.jsp"%>
	</body>
	<script>
		seajs.use("js/system/dataDic/agency/list.js", function(list) {
			 list.init(); 
		});
	</script>
</html>