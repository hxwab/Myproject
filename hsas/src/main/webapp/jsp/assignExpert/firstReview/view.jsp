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
   		<s:hidden id="entityId" name="entityId" />
   		<s:hidden id="productType" name="productType" value="1" />
   		<a name="top" id="top"></a>
			<div class="row mySlide">
			<ol class="breadcrumb mybreadcrumb">当前位置：
				<li><a href="#">专家分配</a></li>
				<li class="active">详情</li>
			</ol>
			    <div class="col-xs-12 main-content">
				   	<div class="btn-group pull-right view-controler" role="group" aria-label="...">
						<button type="button" class="btn  btn-default" id = "view_prev">上一条</button>
						<button type="button" class="btn  btn-default" id = "view_next">下一条</button>
						<button type="button" class="btn  btn-default" id = "view_back">返回</button>
					</div>
					<span class="clearfix"></span><!-- 重要!! 用于清除按键组浮动 -->
					<textarea id = "view_template" style="display:none">
					    <div class="panel panel-default panel-view">
					    <div class="panel-heading"><strong>基本信息</strong></div>
						  	<div class="panel-body">
							    <table class="table table-striped view-table">
							      <tbody>
									<tr>
										<td width = "50" class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td width = "100" class = "text-right view-key">分组名称：</td>
										<td width = "350">${groupName }</td>
										<td width = "150"></td>
										<td width = "100"></td>
										<td ></td>
									</tr>
									<tr>
										<td width = "50" class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td width = "100" class = "text-right view-key">专家数：</td>
										<td class = "text-left"  width = "100">${assignedExpertNum }</td>
										<td width = "50" class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td width = "100" class = "text-right view-key">成果数：</td>
										<td width ='' class = "text-left" >${productNum }</td>
									</tr>
									<tr>
										<td width = "50" class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td width = "100" class = "text-right view-key">学科门类：</td>
										<td class = "text-left" colspan = "4">${allDisciplines }</td>
									</tr>
								  </tbody>
					    		</table>
					    	</div>
						</div>
						 	
						</textarea>
						
						<textarea id = "expert_list_template" style="display:none">
						 	<div class="btn-group pull-right view-controler" role="group" aria-label="...">
								<button type="button" class="btn  btn-default add-expert">添加</button>
								<button type="button" class="btn  btn-danger delete-expert">删除</button>
							</div>
							<table class="table table-bordered" style="font-size: 14px;">
						      <thead>
						        <tr>
						          <th width = "30"><input class = "check-all" type="checkbox" title="点击全选/不选本页所有项目"/></th>
						          <th width = "50">序号</th>
						          <th width = "100">姓名</th>
						          <th>所属机构</th>
						     	  <th width = "150">职称</th>
						     	  <th width = "100">学科门类</th>
						     	  <th width = "150">当年是否有成果</th>
						        </tr>
						      </thead>
						      <tbody>
						      {for item in assignedExpert}
						        <tr>
						          <td><input type="checkbox" name="entityId" value="${item[0] }"/></td>
						          <td class="text-center">${parseInt(item_index) + 1}</td>
						          <td>${item[1] }</td>
						          <td>${item[2] }</td>
						          <td>${item[3] }</td>
						          <td>${item[4] }</td>
						          <td class="text-center">{if item[5] == 1}<span class="label label-success label-custom">第一作者</span> {elseif item[5] == 2}<span class="label label-info label-custom">第二作者</span> {else}<span class="label label-default label-custom">无</span>{/if}</td>
						        </tr>
						       {forelse}
						          <td colspan = "7">还未分配专家！</td>
						        {/for}
						      </tbody>
						    </table>
						</textarea>
						<div id = "view_container"></div>
						<div class="tab-custom" id="tab_container" style="display:none">
						  <!-- Nav tabs -->
						  <ul class="nav nav-tabs" role="tablist">
						    <li role="presentation" class="active"><a href="#expert" aria-controls="audit" role="expert" data-toggle="tab">已选专家</a></li>
						    <li role="presentation" ><a href="#product" aria-controls="product" role="tab" data-toggle="tab">成果清单</a></li>
						  </ul>
						
						  <!-- Tab panes -->
						  <div class="tab-content ">
						  	<div role="tabpanel" class="tab-pane fade in active" id="expert"></div>
						    <div role="tabpanel" class="tab-pane fade " id="product">
						    <!-- 初级检索 -->
								<div id="simple_search" style="display: none !important;">
									<form id="search" class="form-inline" name="search" action="groupProducts/list.action" method="post">
										<input id="sim_sysOptKeyword" name="sim_sysOptKeyword" type="hidden" value="" >
										<table class="table_td_padding form-group pull-right" border="0" cellspacing="0" cellpadding="0" >
											<tbody>
												<tr>
													<td align="right">
														<span class="choose_bar">
														<select id="search_searchType" class="select form-control input-sm" name="searchType">
															<option value="-1" selected="selected">--不限--</option>
															<option value="0">分组名称</option>
														</select>
														</span>
														<input id="keyword" class="form-control input-sm keyword" name="keyword" type="text" size="10" value="">
														<input id="list_pagenumber" name="pageNumber" type="hidden" value="">
														<input id="list_sortcolumn" name="sortColumn" type="hidden" value="0">
														<input id="list_pagesize" name="pageSize" type="hidden"  value="0">
														<input id="groupId" name="groupId" type="hidden"  value='<s:property value="entityId"/>'>
													
														<input  name="reviewType" type="hidden"  value="0">
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
													<td width="20"><input id="check" name="check" type="checkbox" title="点击全选/不选本页所有项目"/></td>
													<td width="50" class="text-center">序号</td>
													<td width="150" ><a id="sortcolumn0" href="" class="{if sortColumn == 0}{if sortColumnLabel == 1}up_css{else}down_css{/if}{/if}" title="点击按账号名称排序">申报编号</a> </td>
													<td> <a id="sortcolumn1" href="" class="{if sortColumn == 1}{if sortColumnLabel == 1}up_css{else}down_css{/if}{/if}" title="点击按账号名称排序">成果名称</a></td>
													<td width="150" ><a id="sortcolumn2" href="" class="{if sortColumn == 2}{if sortColumnLabel == 1}up_css{else}down_css{/if}{/if}" title="点击按账号名称排序">作者姓名</a></td>
													<td width="150" ><a id="sortcolumn3" href="" class="{if sortColumn == 3}{if sortColumnLabel == 1}up_css{else}down_css{/if}{/if}" title="点击按账号名称排序">依托机构</a></td>
													<td width="150" ><a id="sortcolumn4" href="" class="{if sortColumn == 4}{if sortColumnLabel == 1}up_css{else}down_css{/if}{/if}" title="点击按账号名称排序">成果类型</a> </td>
													<td width="150" ><a id="sortcolumn5" href="" class="{if sortColumn == 5}{if sortColumnLabel == 1}up_css{else}down_css{/if}{/if}" title="点击按账号名称排序">发表刊物</a></td>
													<td width="100" ><a id="sortcolumn6" href="" class="{if sortColumn == 6}{if sortColumnLabel == 1}up_css{else}down_css{/if}{/if}" title="点击按账号名称排序">发表时间</a></td>
												</tr>
											</thead>
											<tbody>
												{for item in root}
												<tr>
													<td><input type="checkbox" name="entityIds" value="${item.laData[0]}"/></td>
													<td class="text-center">${item.num}</td>
													<td >${item.laData[1]}</td>
													<td class="table_txt_td">${item.laData[2]}</td>
													<td >${item.laData[3]}</td>
													<td >${item.laData[4]}</td>
													<td >${item.laData[5]}</td>
													<td >${item.laData[6]}</td>
													<td >${item.laData[7]}</td>
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
						  </div>
						</div>
			    </div>
			<div class="row">
   		<%@ include file="/jsp/footer.jsp"%>
		<script>
		    seajs.use("js/assignExpert/firstReview/view.js", function(view) {
		    	view.init(); 
		    });
		</script>
	</body>
</html>