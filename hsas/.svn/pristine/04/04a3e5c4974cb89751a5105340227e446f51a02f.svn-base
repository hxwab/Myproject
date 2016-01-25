<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page isELIgnored ="true"%><%--添加对EL表达式的支持--%>
<%@ taglib prefix="s" uri="/struts-tags"%> <%--添加对struts标签的支持 --%>
<!DOCTYPE html>
<html>
	<head>
		<title>湖北省社会科学优秀成果奖励申报评审系统</title>
		<%@ include file="/jsp/base.jsp"%>
	</head>
	<body>
		<!-- 页头 -->
		<%@ include file="/jsp/innerNav.jsp"%>
		
		<!-- 页面主体 -->
		<div class="row mySlide">
			<!-- 当前位置 -->
			<ol class="breadcrumb mybreadcrumb">当前位置：
				<li><a href="#">专家管理</a></li>
				<s:if test="type == 2"><li class="active">参评专家</li></s:if>
				<s:if test="type == 1"><li class="active">推荐专家</li></s:if>
				<s:else><li class="active">专家库</li></s:else>
			</ol>
			
			<!-- 列表 -->
			<div class="col-xs-12">
				<!-- 初级检索 -->
				<div id="simple_search" style="display: none !important;">
					<form id="search" class="form-inline" name="search" action="expert/list.action" method="post">
						<input id="sim_sysOptKeyword" name="sim_sysOptKeyword" type="hidden" value="" >
						<s:hidden id="isReviewable" name="isReviewable" />
						<table class="table_td_padding form-group pull-right" border="0" cellspacing="0" cellpadding="0" >
							<tbody>
								<tr>
									<td align="right">
										<span class="choose_bar">
										<select id="search_searchType" class="select form-control input-sm" name="searchType">
											<option value="0" selected="selected">--不限--</option>
											<option value="1">姓名</option>
											<option value="2">所在机构</option>
											<option value="3">职称</option>
											<option value="4">学科门类</option>
										</select>
										</span>
										<input id="keyword" class="form-control input-sm keyword" name="keyword" type="text" size="10" value="">
										<input id="list_pagenumber" name="pageNumber" type="hidden" value="">
										<input id="list_sortcolumn" name="sortColumn" type="hidden" value="0">
										<input id="list_pagesize" name="pageSize" type="hidden"  value="0">
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
								<td width="50" class="text-center">序号</td>
								<td width="50"><a id="sortcolumn0" href="" class="{if sortColumn == 0}{if sortColumnLabel == 1}up_css{else}down_css{/if}{/if}" title="点击按姓名排序">姓名</a></td>
								<td width="100"><a id="sortcolumn1" href="" class="{if sortColumn == 1}{if sortColumnLabel == 1}up_css{else}down_css{/if}{/if}"  title="点击按所在单位排序">所在单位</a></td>
								<td width="60"><a id="sortcolumn2" href="" class="{if sortColumn == 2}{if sortColumnLabel == 1}up_css{else}down_css{/if}{/if}"  title="点击按职称排序">职称</a></td>
								<td width="60"><a id="sortcolumn3" href="" class="{if sortColumn == 3}{if sortColumnLabel == 1}up_css{else}down_css{/if}{/if}"  title="点击按职务排序">职务</a></td>
								<td width="100"><a id="sortcolumn4" href="" class="{if sortColumn == 4}{if sortColumnLabel == 1}up_css{else}down_css{/if}{/if}"  title="点击按分组排序">分组</a></td>
								<td width="100"><a id="sortcolumn5" href="" class="{if sortColumn == 5}{if sortColumnLabel == 1}up_css{else}down_css{/if}{/if}"  title="点击按学科门类排序">学科门类</a></td>
								<td width="80"><a id="sortcolumn6" href="" class="{if sortColumn == 6}{if sortColumnLabel == 1}up_css{else}down_css{/if}{/if}"  title="点击按当年是否已申报排序">当年是否已申报</a></td>
								<s:if test="%{type==0}">
								<td width="80"><a id="sortcolumn7" href="" class="{if sortColumn == 7}{if sortColumnLabel == 1}up_css{else}down_css{/if}{/if}"  title="点击按是否已推荐排序">是否已推荐</a></td>
								</s:if>
								<s:if test="%{type==2}">
								<td width="80"><a id="sortcolumn8" href="" class="{if sortColumn == 8}{if sortColumnLabel == 1}up_css{else}down_css{/if}{/if}"  title="点击按评审专家类型排序">评审专家类型</a></td>
								</s:if>
							</tr>
						</thead>
						<tbody>
						{for item in root}
							<tr>	
								<td class="text-center"><input type="checkbox" name="entityIds" value="${item.laData[0]}" /></td>
								<td class="text-center">${item.num}</td>
								<td class="text-center"><a id="${item.laData[0]}" class = "link1" title="点击查看详细信息">${item.laData[1]}</a></td>
								<td class="text-center">${item.laData[2]}</td>
								<td class="text-center">${item.laData[3]}</td>
								<td class="text-center">${item.laData[4]}</td>
								<td class="text-center">${item.laData[5]}</td>
								<td class="text-center">${item.laData[6]}</td>
								<td class="text-center">{if item.laData[7]==1}<span class="red""><i class="fa fa-exclamation-triangle"></i>&nbsp;已申报</span>{else}否{/if}</td>
								<s:if test="%{type==0}">
									<td class="text-center">{if item.laData[8]==1}<span class="green"><i class="fa fa-check-circle"></i>&nbsp;已推荐</span>{else}{/if}</td>
								</s:if>
								<s:if test="%{type==2}">
									<td class="text-center">
									{if item.laData[9]==0}
										<span>初评专家</span>
									{elseif item.laData[9]==1}
										<span>复评专家</span>
									{else}未记录
									{/if}
									</td>
								</s:if>
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
								<s:if test="type == 0">
								<button id = "list_add_recommend" class = "btn btn-default " type="button">推荐</button>
								</s:if>
								<s:if test="type==1">
								<button id = "list_delete_recommend" class = "btn btn-default " type="button">撤销</button>
								</s:if>
								<button id = "list_export" class = "btn btn-default " type="button">导出</button>
							</td>
						</tr>
					</table>
				</textarea>
			
				<!-- 显示列表 -->
				<s:form id="list" theme="simple" action="delete" namespace="/system/dataDic/bonus">
					<s:hidden id="pagenumber" name="pageNumber"/>
					<s:hidden id="checkedIds" name="checkedIds"/>
					<div id="list_container"  style="display:none;"></div>
				</s:form> 
			</div>
		</div>
		
		<!-- 授权评审资格弹层 -->
		<div id="div_pop_authorize" style="display:none;">
			<form id="form-authorize" action="expert/authorize.action" mothod="post">
				<p>您确定授权所选专家具有评审资格？</p>
			</form>
		</div>
		
		<!-- 撤销评审资格弹层 -->
		<div id="div_pop_unauthorize" style="display:none;">
			<form id="form-unauthorize" action="expert/unauthorize.action" mothod="post">
				<p>您确定撤销所选专家具有评审资格？</p>
			</form>
		</div>
		
		<!-- 页脚 -->
		<div class="row">
		<%@ include file="/jsp/footer.jsp"%>
	</body>
	<script>
		seajs.use("js/expert/list.js", function(list) {
			list.init();
		});
	</script>
</html>