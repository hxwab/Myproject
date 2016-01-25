<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page isELIgnored ="true"%><%--添加对EL表达式的支持--%>
<%@ taglib prefix="s" uri="/struts-tags"%> <%--添加对struts标签的支持 --%>
<!DOCTYPE html>
<html>
	<head>
		<title>湖北省社会科学优秀成果奖申报评审系统</title>
		<%@ include file="/jsp/base.jsp"%>
		<link rel="stylesheet" href="lib/bootstrap-datepicker-1.4.0-dist/css/bootstrap-datepicker3.css">
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
											<option value="2">奖金</option>
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
				
				<!-- 列表模板 -->
				<textarea id="list_template" style="display:none;">
					<table id="list_table"  class="table table-striped table-bordered dataTable no-footer" width="100%" border="0" cellspacing="0" cellpadding="0">
						<thead id="list_head">
							<tr class="table_title_tr">
								<td width="20" class="text-center"><input id="check" name="check" type="checkbox" title="点击全选/不选本页所有项目"/></td>
								<td width="40" class="text-center">序号</td>
								<td width="60" class="text-center"><a id="sortcolumn0" href="" class="{if sortColumn == 0}{if sortColumnLabel == 1}up_css{else}down_css{/if}{/if}" title="点击按年份排序">年份</a></td>
								<td width="80" class="text-center"><a id="sortcolumn1" href="" class="{if sortColumn == 1}{if sortColumnLabel == 1}up_css{else}down_css{/if}{/if}" title="点击按特等奖（论文）排序">特等奖<br>（论文）</a></td>
								<td width="80" class="text-center"><a id="sortcolumn2" href="" class="{if sortColumn == 2}{if sortColumnLabel == 1}up_css{else}down_css{/if}{/if}" title="点击按一等奖（论文）排序">一等奖<br>（论文）</a></td>
								<td width="80" class="text-center"><a id="sortcolumn3" href="" class="{if sortColumn == 3}{if sortColumnLabel == 1}up_css{else}down_css{/if}{/if}" title="点击按二等奖（论文）排序">二等奖<br>（论文）</a></td>
								<td width="80" class="text-center"><a id="sortcolumn4" href="" class="{if sortColumn == 4}{if sortColumnLabel == 1}up_css{else}down_css{/if}{/if}" title="点击按三等奖（论文）排序">三等奖<br>（论文）</a></td>
								<td width="80" class="text-center"><a id="sortcolumn5" href="" class="{if sortColumn == 5}{if sortColumnLabel == 1}up_css{else}down_css{/if}{/if}" title="点击按特等奖（著作）排序">特等奖<br>（著作）</a></td>
								<td width="80" class="text-center"><a id="sortcolumn6" href="" class="{if sortColumn == 6}{if sortColumnLabel == 1}up_css{else}down_css{/if}{/if}" title="点击按一等奖（著作）排序">一等奖<br>（著作）</a></td>
								<td width="80" class="text-center"><a id="sortcolumn7" href="" class="{if sortColumn == 7}{if sortColumnLabel == 1}up_css{else}down_css{/if}{/if}" title="点击按二等奖（著作）排序">二等奖<br>（著作）</a></td>
								<td width="80" class="text-center"><a id="sortcolumn8" href="" class="{if sortColumn == 8}{if sortColumnLabel == 1}up_css{else}down_css{/if}{/if}" title="点击按三等奖（著作）排序">三等奖<br>（著作）</a></td>
								<td width="100" class="text-center"><a id="sortcolumn9" href="" class="{if sortColumn == 9}{if sortColumnLabel == 1}up_css{else}down_css{/if}{/if}" title="点击按更新时间排序">更新<br>时间</a></td>
							</tr>
						</thead>
						<tbody>
							{for item in root}
							<tr>
								<td class="text-center"><input type="checkbox" name="entityIds" value="${item.laData[0]}"/></td>
								<td class="text-center">${item.num}</td>
								<td class="text-center table_txt_td"><a id="${item.laData[0]}" class="link1" href="javascript:void(0)" title="点击查看详细信息">${item.laData[1]}</a></td>
								<td class="text-center">${item.laData[2]}</td>
								<td class="text-center">${item.laData[3]}</td>
								<td class="text-center">${item.laData[4]}</td>
								<td class="text-center">${item.laData[5]}</td>
								<td class="text-center">${item.laData[6]}</td>
								<td class="text-center">${item.laData[7]}</td>
								<td class="text-center">${item.laData[8]}</td>
								<td class="text-center">${item.laData[9]}</td>
								<td class="text-center">${item.laData[10]|toFormat}</td>
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
								<button id="list_add" class="btn btn-default " type="button">添加</button>
								<button id="list_delete" class="btn btn-default " type="button">删除</button>
								<button id="list_export" class="btn btn-default " type="button">导出</button>
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
		
		<%--<div id="div_modify" style="display:none;">
			<s:form id="form_modify" cssClass="form-horizontal" theme="simple" action="modify" namespace="/system/dataDic/reward">
				<s:hidden id="modifyIds" name="modifyIds"/>
				<div class="form-group">
					<label class="col-sm-3 control-label required-label">金额</label>
					<div class="col-sm-9">
						<input id="modify_bonus" type="text" class="form-control validate[required, custom[number]" name="modify_bonus" placeholder="请输入金额">
					</div>
				</div>
			</s:form>
		</div>
		
		--%><!-- 页脚 -->
		<div class="row">
		<%@ include file="/jsp/footer.jsp"%>
	</body>
	<script>
		seajs.use("js/system/dataDic/reward/list.js", function(list) {
			list.init();
		});
	</script>
</html>