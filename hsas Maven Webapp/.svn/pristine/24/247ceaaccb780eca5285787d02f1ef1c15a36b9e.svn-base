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
    <body class="pop-body" style="width:600px" >
   		<div class="no-padding">
			<div class="row mySlide">
			    <div class="col-xs-12">
					<div id="simple_search" style="display: none !important;">
		                <form id="search" name="search" action="selectAgency/list.action" method="post" class="form-inline">
		                    <input type="hidden" name="sim_sysOptKeyword" value="" id="sim_sysOptKeyword">
		                    <table border="0" cellspacing="0" cellpadding="0" class="table_td_padding pull-right">
		                        <tbody>
		                        <tr>
			                         <td align="right">
										<select name="searchType" id="search_searchType" class="select form-control input-sm">
				                            <option value="-1" selected="selected">--不限--</option>
				                            <!-- 名称、作者、发表时间、指标类型、模板类型、备注 -->
				                            <option value="1">机构名称</option>
				                      </td>
				                      <td><input class="form-control input-sm" type="text" name="keyword" size="10" value="" id="keyword" class="keyword"></td>
				                      <td><input id="list_button_query" type="button" value="检索" class="btn btn-default btn-sm"></td>
	                                <input type="hidden" name="pageNumber" value="" id="list_pagenumber">
	                                <input type="hidden" name="sortColumn" value="0" id="list_sortcolumn">
	                                <input type="hidden" name="pageSize" value="0" id="list_pagesize">
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
			                	<td width="50">选择</td>
			                    <td width="50" class="text-center">序号</td>
			                    <td width=""><a id="sortcolumn0" href="" class="{if sortColumn == 0}{if sortColumnLabel == 1}up_css{else}down_css{/if}{/if}" title="点击按机构名称排序">机构名称</a></td>
			                </tr>
			                </thead>
			                <tbody>
			                {for item in root}
			                <tr>
			                	<td class="text-center"><input type="radio" name="entityId" value="${item.laData[0]}" alt="${item.laData[1]}" class="radio_select" /></td>
			                    <td class="text-center">${item.num}</td>
			                    <td class="table_txt_td"><a id="${item.laData[0]}"  class = "link1" title="点击查看详细信息" type="1">${item.laData[1]}</a></td>
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
					<div class="text-center">
							<div class="btn-group">
					    		<button class="btn btn-default btn-sm confirm" >确定</button>
					    		<button class="btn btn-default btn-sm cancel" >取消</button>
							</div>
					</div>
				    
        		</s:form>   
			    </div>
			</div>
		</div>
		<script src="lib/bootstrap-3.3.5/js/bootstrap.js" type="text/javascript"></script>
		<script>
		    seajs.use("js/pop/popSelect/selectAgency.js", function(list) {
		         list.init(); 
		    });
		</script>
	</body>
</html>