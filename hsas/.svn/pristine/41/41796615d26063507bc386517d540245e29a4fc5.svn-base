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
    <body class="pop-body" style="width:700px;" >
   		<div class="no-padding">
			<div class="no-padding clearfix">
					<div id="simple_search" style="display: none !important;margin-top: 3px;">
		                <form id="search" name="search" action="assignExpert/list.action" method="post" class="form-inline">
		                    <input type="hidden" name="sim_sysOptKeyword" value="" id="sim_sysOptKeyword">
		                    <table border="0" cellspacing="0" cellpadding="0" class="table_td_padding pull-right">
		                        <tbody>
		                        <tr>
			                         <td align="right">
										<select name="searchType" id="search_searchType" class="select form-control input-sm">
				                            <option value="-1" selected="selected">--不限--</option>
				                            <!-- 名称、作者、发表时间、指标类型、模板类型、备注 -->
				                            <option value="1">专家姓名</option>
				                            <option value="2">学科门类</option>
				                            <option value="3">研究方向</option>
				                            <option value="2">职称</option>
				                      </td>
				                      <td><input class="form-control input-sm" type="text" name="keyword" size="10" value="" id="keyword" class="keyword"></td>
				                      <td><input id="list_button_query" type="button" value="检索" class="btn btn-default "></td>
	                                <input type="hidden" name="pageNumber" value="" id="list_pagenumber">
	                                <input type="hidden" name="sortColumn" value="0" id="list_sortcolumn">
	                                <input type="hidden" name="pageSize" value="0" id="list_pagesize">
	                                <input type="hidden" name="groupId" value="0" >
	                                <input type="hidden" name="type" value="0" >
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
			                    <td width="100"><a id="sortcolumn0" href="" class="{if sortColumn == 0}{if sortColumnLabel == 1}up_css{else}down_css{/if}{/if}" title="点击按姓名排序">姓名</a></td>
			                    <td width="100"><a id="sortcolumn1" href="" class="{if sortColumn == 1}{if sortColumnLabel == 1}up_css{else}down_css{/if}{/if}" title="点击按学科门类排序">所属机构</a></td>
			                    <td width="80"><a id="sortcolumn2" href="" class="{if sortColumn == 2}{if sortColumnLabel == 1}up_css{else}down_css{/if}{/if}" title="点击按研究方向排序">职称</a></td>
			                    <td width="50"><a id="sortcolumn3" href="" class="{if sortColumn == 3}{if sortColumnLabel == 1}up_css{else}down_css{/if}{/if}" title="点击按职称排序">学科门类</a></td>
			                    <td width="100"><a id="sortcolumn4" href="" class="{if sortColumn == 4}{if sortColumnLabel == 1}up_css{else}down_css{/if}{/if}" title="点击按职称排序">当年是否有成果</a></td>
			                </tr>
			                </thead>
			                <tbody>
			                {for item in root}
			                <tr>
			                	<td><input type="checkbox" name="entityIds" value="${item.laData[0]}" data="${item.laData[1]}"/></td>
			                    <td class="text-center">${item.num}</td>
			                    <td class="table_txt_td">${item.laData[1]}</td>
			                    <td class="table_txt_td">${item.laData[2]}</td>
			                    <td class="table_txt_td">${item.laData[3]}</td>
			                    <td class="table_txt_td">${item.laData[4]}</td>
			                    <td>{if item[5] == 1}<span class="label label-success label-custom">第一作者</span> {elseif item[5] == 2}<span class="label label-info label-custom">第二作者</span> {else}<span class="label label-default label-custom">无</span>{/if}</td>
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
			                </tr>
			            </table>
						
				</textarea>
		         <s:form id="list" theme="simple" action="delete" namespace="/portal">
				    <s:hidden id="pagenumber" name="pageNumber"/>
				    <s:hidden id="checkedIds" name="checkedIds"/>
				    <s:hidden id="roleIds" name="roleIds" />
				    <div id="list_container"  style="display:none;"></div>
				    <div class="selected"></div>
					
        		</s:form>   
			    
			 	<div class="text-center col-xs-12 ">
					<div class="btn-group">
			    		<button class="btn btn-default  confirm" >确定</button>
			    		<button class="btn btn-default  cancel" >取消</button>
					</div>
				</div>
			</div>
		</div>
		<script src="lib/bootstrap-3.3.5/js/bootstrap.js" type="text/javascript"></script>
		<script>
		    seajs.use("js/assignExpert/popAssignExpert.js", function(list) {
		         list.init(); 
		    });
		</script>
	</body>
</html>