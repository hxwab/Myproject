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
   		<%@ include file="/jsp/innerNav.jsp"%>
   		<s:hidden id="type" name="type" />
		
		<div class="row mySlide">
			<ol class="breadcrumb mybreadcrumb">当前位置：
			  <li><a href="#">系统管理</a></li>
			  <li><a href="account/toList.action?update=1">账户管理</a></li>
			  <li class="active">社科联管理员账号</li>
			</ol>
			    <div class="col-xs-12 main-content">
					<div id="simple_search" style="display: none !important;">
		                <form id="search" name="search" action="account/list.action" method="post"
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
		                            <option value="1">账户名称</option>
		                            <option value="2">姓名</option>
		                            <option value="3">单位</option>
		                        </select>
								</span>
		                                <input class="form-control input-sm keyword" type="text" name="keyword" size="10" value="" id="keyword">
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
					<!-- 高级检索 -->
				<!-- 显示列表 -->
				<textarea id="list_template" style="display:none;">
						<table id="list_table" width="100%" border="0" cellspacing="0" cellpadding="0" class="table table-striped table-bordered dataTable no-footer">
			                <thead id="list_head">
			                <tr class="table_title_tr">
			                	<td width="20"><input id="check" name="check" type="checkbox" title="点击全选/不选本页所有项目"/></td>
			                    <td width="50" class="text-center">序号</td>
			                    <td width="200"><a id="sortcolumn0" href="" class="{if sortColumn == 0}{if sortColumnLabel == 1}up_css{else}down_css{/if}{/if}" title="点击按账号名称排序">账号名称</a></td>
			                    <td width="200"><a id="sortcolumn1" href="" class="{if sortColumn == 1}{if sortColumnLabel == 1}up_css{else}down_css{/if}{/if}" title="点击按姓名排序">姓名</a></td>
			                    <td width="200"><a id="sortcolumn2" href="" class="{if sortColumn == 2}{if sortColumnLabel == 1}up_css{else}down_css{/if}{/if}" title="点击按单位排序">单位</a></td>
			                    <td width="150" ><a id="sortcolumn3" href="" class="{if sortColumn ==3}{if sortColumnLabel == 1}up_css{else}down_css{/if}{/if}"  title="点击按创建时间排序">创建时间</a></td>
			                    <td width="150" ><a id="sortcolumn4" href="" class="{if sortColumn == 4}{if sortColumnLabel == 1}up_css{else}down_css{/if}{/if}"  title="点击按有效时间排序">有效时间</a></td>
			                    <td width="100"><a id="sortcolumn5" href="" class="{if sortColumn == 5}{if sortColumnLabel == 1}up_css{else}down_css{/if}{/if}"  title="点击按账号状态排序">账号状态</a></td>
			                    <td width="100"><a id="sortcolumn6" href="" class="{if sortColumn == 6}{if sortColumnLabel == 1}up_css{else}down_css{/if}{/if}"  title="点击按角色分配排序">角色分配</a></td>
			                </tr>
			                </thead>
			                <tbody>
			                {for item in root}
			                <tr>
			                	<td><input type="checkbox" name="entityIds" value="${item.laData[0]}"/></td>
			                    <td class="text-center">${item.num}</td>
			                    <td class="table_txt_td"><a id="${item.laData[0]}"  class = "link1" title="点击查看详细信息" type="1">${item.laData[1]}</a></td>
			                    <td >${item.laData[2]}</td>
			                    <td >${item.laData[3]}</td>
			                    <td >${item.laData[4]|toFormat}</td>
			                    <td >${item.laData[5]|toFormat}</td>
			                    <td>{if item.laData[6]==1}启用{else}停用{/if}</td>
			                    <td>
			                    	{if item.laData[7]==1}高级管理员
			                    	{elseif item.laData[7]==2}一般管理员
			                    	{elseif item.laData[7]==3}高校管理员
			                    	{elseif item.laData[7]==4}复评专家组长
			                    	{elseif item.laData[7]==5}复评专家
			                    	{elseif item.laData[7]==6}初评专家
			                    	{else}申请人
			                    	{/if}
			                    </td>
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
			                    	<button id = "list_enable" class = "btn btn-default ">启用</button>
			                    	<button id = "list_disable" class = "btn btn-default ">停用</button>
			                    	<s:if test="%{type==0 }">
			                    	<button id = "list_assign_role" class = "btn btn-default ">分配角色</button>
			                    	</s:if>
			                    	<s:if test="%{type==2}">
			                    	<button id = "list_generate_first_review_account" class = "btn btn-default ">生成初评专家帐号</button>
			                    	<button id = "list_generate_second_review_account" class = "btn btn-default ">生成复评专家帐号</button>
			                    	</s:if>
			                    </td>
			                </tr>
			            </table>
				</textarea>
		       	<s:form id="list" theme="simple" action="delete" namespace="/portal">
				    <s:hidden id="pagenumber" name="pageNumber"/>
				    <s:hidden id="checkedIds" name="checkedIds"/>
				    <s:hidden id="roleIds" name="roleIds" />
				    <div id="list_container"  style="display:none;"></div>
        		</s:form>   
		            
			    </div>
			</div>
			<div style="display:none"> <!--  弹层Content  -->
				<div id="pop_enable" >
					<form action="account/enable.action" method="post" id="form_enable">
						<table width="100%">
			    		 	<tbody>
				    			<tr>
				    				<td width="80" class="text-right required-label">有效期至：</td>
				    				<td><input type="text" name="validity" class="form-control input-sm dataPicker validate[required]" placeholder="请输入有效时间"></td>
				    				<td width="50" ></td>
				    			</tr>
				    		</tbody>
				    	</table>
					</form>
				</div>
				<div id="pop_disable" >
					<form action="account/disable.action" method="post" id="form_disable"></form>
				</div>
			</div>
		<div class="row">
   		<%@ include file="/jsp/footer.jsp"%>
		<script>
		    seajs.use("js/system/account/administrator/list.js", function(list) {
		         list.init(); 
		    });
		</script>
	</body>
</html>