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
   		<a name="top" id="top"></a>
			<div class="row mySlide">
			<ol class="breadcrumb mybreadcrumb">当前位置：
			  <li><a href="#">评审管理</a></li>
			  <li><a href="right/toList.action?update=1">初评</a></li>
			  <li class="active">审核</li>
			</ol>
			    <div class="col-xs-12 main-content">
				   
					<div id="simple_search" style="display: none !important;">
		                <form id="search" name="search" action="product/firstReviewAudit/list.action" method="post"
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
		                            <option value="1">成果名称</option>
		                            <option value="2">是否初评</option>
		                            <option value="3">审核时间</option>
		                        </select>
								</span>
		                                <input class="form-control input-sm" type="text" name="keyword" size="10" value="" id="keyword"
		                                       class="keyword">
		                                <input type="hidden" name="pageNumber" value="" id="list_pagenumber">
		                                <input type="hidden" name="sortColumn" value="0" id="list_sortcolumn">
		                                <input type="hidden" name="pageSize" value="0" id="list_pagesize">
		                            </td>
		                            <td class = "btn-group" style = "display:block">
		                                 <input id="list_button_query" type="button" value="检索" class="btn btn-default ">
		                                 <input id="list_search_more" type="button" value="更多条件" class="btn btn-default ">
		                            </td>
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
		                                <td class="adv_td1" width="100" align="right">成果名称：</td>
		                                <td class="adv_td1" width="200"><input type="text" class="form-control input-sm" name="keyword1" id="advSearch_keyword1"></td>
		                                <td class="adv_td1" width="100"></td>
		                                <td class="adv_td1" width="100" align="right">是否初评：</td>
		                                <td class="adv_td1" width="200"><input type="text" class="form-control input-sm" name="keyword2" id="advSearch_keyword2"></td>
		                                <td class="adv_td1"></td>
		                            </tr>
		                            <tr class="adv_tr">
		                                <td class="adv_td1" align="right">初评时间：</td>
		                                <td class="adv_td1"><input type="text" name="keyword3" class="form-control input-sm" id="advSearch_keyword3"></td>
		                                <td class="adv_td1" width="100"></td>
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
				<!-- 显示列表 -->
				
				<textarea id="list_template" style="display:none;">
						<table id="list_table" width="100%" border="0" cellspacing="0" cellpadding="0"
			                   class="table table-striped table-bordered dataTable no-footer">
			                <thead id="list_head">
			               <tr class="table_title_tr">
			                	<td width="20"><input id="check" name="check" type="checkbox" title="点击全选/不选本页所有项目"/></td>
			                    <td width="50" class="text-center">序号</td>
			                    <td width ="85"><a id="sortcolumn0" href="" class="{if sortColumn == 0}{if sortColumnLabel == 1}up_css{else}down_css{/if}{/if}" title="点击按角色名称排序">评审编号</a></td>
			                    <td><a id="sortcolumn1" href="" class="{if sortColumn == 1}{if sortColumnLabel == 1}up_css{else}down_css{/if}{/if}"  title="点击按角色描述排序">成果名称</a></td>
			                    <td width="100" class=""><a id="sortcolumn2" href="" class="{if sortColumn == 2}{if sortColumnLabel == 1}up_css{else}down_css{/if}{/if}"  title="点击按作者姓名排序">作者姓名</a></td>
			                	<td width="120" class=""><a id="sortcolumn3" href="" class="{if sortColumn == 3}{if sortColumnLabel == 1}up_css{else}down_css{/if}{/if}"  title="点击按单位排序">单位 </a></td>
			                  	<td width="150" class=""><a id="sortcolumn4" href="" class="{if sortColumn == 4}{if sortColumnLabel == 1}up_css{else}down_css{/if}{/if}"  title="点击按分组排序">分组</a></td>
			                  	<td width="90" class=""><a id="sortcolumn5" href="" class="{if sortColumn == 5}{if sortColumnLabel == 1}up_css{else}down_css{/if}{/if}"  title="点击按成果类型排序"> 成果类型</a></td>
			                  	<td width="150" class=""><a id="sortcolumn6" href="" class="{if sortColumn == 6}{if sortColumnLabel == 1}up_css{else}down_css{/if}{/if}"  title="点击按发表刊物排序">发表刊物 </a></td>
			                  	<td width="100" class=""><a id="sortcolumn7" href="" class="{if sortColumn == 7}{if sortColumnLabel == 1}up_css{else}down_css{/if}{/if}"  title="点击按发表时间排序">发表时间</a></td>
			                  	<td width="100" class=""><a id="sortcolumn8" href="" class="{if sortColumn == 8}{if sortColumnLabel == 1}up_css{else}down_css{/if}{/if}"  title="点击按初评得分排序">初评得分</a></td>
			                  	<td width="80" class=""><a id="sortcolumn9" href="" class="{if sortColumn == 9}{if sortColumnLabel == 1}up_css{else}down_css{/if}{/if}"  title="点击按审核结果排序">审核结果</a></td>
			                </tr>
			                </thead>
			                <tbody>
			                {for item in root}
			                 <tr>
			                	<td class="text-center"><input type="checkbox" name="entityIds" value="${item.laData[0]}" {if item.laData[2] == 1} disabled {/if}/></td>
			                    <td class="text-center">${item.num}</td>
			                    <td class="">${item.laData[1]}</td>
		                      	<td class=""><a id="${item.laData[0]}" class="link1" type="${ item.laData[10]}">${item.laData[2]}</a></td>
		                      	<td class="">${item.laData[3]}</td>
		                      	<td class="">${item.laData[4]}</td>
		                      	<td class="">${item.laData[5]}</td>
			                    <td class="">${item.laData[6]}</td>
			                    <td class="">${item.laData[7]}</td>
			                    <td class="">${item.laData[8]}</td>
			                    <td class="">${item.laData[9]}</td>
			                    <td class="text-center">{if item.laData[10] == 2} <span class="label label-success label-custom">同意</span> {elseif item.laData[10] == 1}<span class="label label-danger label-custom">不同意</span> {else}<span class="label label-default label-custom">未审核</span> {/if}</td>
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
			                	<button class="firstReviewAudit btn btn-default ">审核</button>
			                	<button class="back btn btn-default ">退回</button>
			                    </td>
			                </tr>
			            </table>
				</textarea>
		         <s:form id="list" theme="simple" action="delete" namespace="/portal">
				    <s:hidden id="pagenumber" name="pageNumber"/>
				    <s:hidden id="checkedIds" name="checkedIds"/>
				    <div id="list_container"  style="display:none;"></div>
        		</s:form>   
        		<div id="firstReviewAudit_tmplate" style="display:none">
        			<table width="100%">
        				<tr>
        					<td><label><input type="radio" name="auditResult" value="2"> <span class="label label-success label-custom">同意</span></label></td>
        					<td><label><input type="radio" name="auditResult" value="1"> <span class="label label-danger label-custom">不同意</span><label></td>
        				</tr>
        			</table>
        		</div>
			    </div>
			</div>
			<div class="row">
   		<%@ include file="/jsp/footer.jsp"%>
		<script>
		    seajs.use("js/product/firstReviewAudit/list.js", function(list) {
		         list.init(); 
		    });
		</script>
	</body>
</html>