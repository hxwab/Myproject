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
				<li><a href="#">数据字典管理</a></li>
				<li class="active">奖金管理</li>
			</ol>
			
			<!-- 列表 -->
			<div class="col-xs-12">
				<!-- 初级检索 -->
				<div id="simple_search" style="display: none !important;">
					<form id="search" class="form-inline" name="search" action="system/dataDic/reward/list.action" method="post" >
						<input type="hidden" name="sim_sysOptKeyword" value="" id="sim_sysOptKeyword">
						<table border="0" cellspacing="0" cellpadding="0" class="table_td_padding form-group pull-right">
							<tbody>
							<tr>
							 <td align="right">
							<span class="choose_bar">
							<select name="searchType" id="search_searchType" class="select form-control input-sm">
								<option value="-1" selected="selected">--不限--</option>
								<!-- 名称、作者、发表时间、指标类型、模板类型、备注 -->
								<option value="1">年份</option>
								<option value="2">类型</option>
								<option value="3">等级</option>
								<option value="4">奖金</option>
							</select>
							</span>
									<input id="keyword" class="form-control input-sm keyword" type="text" name="keyword" size="10" value="">
									<input id="list_pagenumber" type="hidden" name="pageNumber" value="" >
									<input id="list_sortcolumn" type="hidden" name="sortColumn" value="0" >
									<input id="list_pagesize" type="hidden" name="pageSize" value="0" >
								</td>
								<td class = "btn-group" style = "display:block">
									 <input id="list_button_query" class="btn btn-default btn-sm" type="button" value="检索" >
								</td>
							</tr>
							</tbody>
						</table>
						<span class="clearfix"></span>
					</form>
				</div>
				<textarea id="list_template" style="display:none;">
					<table id="list_table"  class="table table-striped table-bordered dataTable no-footer" width="100%" border="0" cellspacing="0" cellpadding="0">
						<thead id="list_head">
							<tr class="table_title_tr">
								<td width="20"><input id="check" name="check" type="checkbox" title="点击全选/不选本页所有项目"/></td>
								<td width="50" class="text-center">序号</td>
								<td width="80"><a id="sortcolumn0" href="" class="{if sortColumn == 0}{if sortColumnLabel == 1}up_css{else}down_css{/if}{/if}" title="点击按年份名称排序">年份</a></td>
								<td width="80"><a id="sortcolumn1" href="" class="{if sortColumn == 1}{if sortColumnLabel == 1}up_css{else}down_css{/if}{/if}" title="点击按等级名称排序">等级</a></td>
								<td width="80"><a id="sortcolumn2" href="" class="{if sortColumn == 2}{if sortColumnLabel == 1}up_css{else}down_css{/if}{/if}" title="点击按类型名称排序">类型</a></td>
								<td width="80"><a id="sortcolumn3" href="" class="{if sortColumn == 3}{if sortColumnLabel == 1}up_css{else}down_css{/if}{/if}" title="点击按奖金名称排序">奖金</a></td>
								<td width="80">更新时间</a></td>
							</tr>
						</thead>
						<tbody>
							{for item in root}
							<tr>
								<td><input type="checkbox" name="entityIds" value="${item.laData[0]}"/></td>
								<td class="text-center">${item.num}</td>
								<td class="table_txt_td"><a href="${item.laData[0]}" title="点击查看详细信息" type="1">${item.laData[1]}</a></td>
								<td>
									{if item.laData[2]==0}
										特等奖
									{elseif item.laData[2]==1}
										一等奖
									{elseif item.laData[2]==2}
										二等奖 
									{elseif item.laData[2]==3}
										三等奖
									{/if}
								</td>
								<td>
									{if item.laData[3]==1}
										著作
									{elseif item.laData[3]==2}
										论文
									{/if}
								</td>
								<td>${item.laData[4]}</td>
								<td>${item.laData[5]}</td>
							</tr>
							{/for}
						</tbody>
					</table>
					<table width="100%" border="0" cellspacing="0" cellpadding="0" class="table_td_padding form-inline">
						<tr class="table_main_tr2">
							<td class = "btn-group">
								<button id = "list_add" class = "btn btn-default btn-sm">添加</button>
								<button id = "list_delete" class = "btn btn-default btn-sm">删除</button>
								<button id = "list_expert" class = "btn btn-default btn-sm">导出</button>
							</td>
						</tr>
					</table>
				</textarea>
				<!-- 显示列表 -->
				<s:form id="list" theme="simple" action="delete" namespace="/system/dataDic/reward">
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
		seajs.use("js/system/dataDic/reward/list.js", function(list) {
			list.init();
		});
	</script>
</html>