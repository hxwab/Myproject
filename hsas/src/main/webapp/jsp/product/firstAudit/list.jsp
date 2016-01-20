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
		<a name="top" id="top"></a>
		
		<!-- 页面主体 -->
		<div class="row mySlide">
			<!-- 当前位置 -->
			<ol class="breadcrumb mybreadcrumb">当前位置：
				<li><a href="javascript:void(0);">评审管理</a></li>
				<li><a href="javascript:void(0);">初审</a></li>
				<li class="active">列表</li>
			</ol>
			
			<!-- 列表 -->
			<div class="col-xs-12">
				<!-- 初级检索 -->
				<div id="simple_search" style="display: none !important;">
					<form id="search" class="form-inline" name="search" action="product/firstAudit/list.action" method="post">
						<input id="sim_sysOptKeyword" name="sim_sysOptKeyword" type="hidden" value="" >
						<table class="table_td_padding form-group pull-right" border="0" cellspacing="0" cellpadding="0" >
							<tbody>
								<tr>
									<td align="right">
										<span class="choose_bar">
										<select id="search_searchType" class="select form-control input-sm" name="searchType">
											<option value="-1" selected="selected">--不限--</option>
											<option value="0">申报编号</option>
											<option value="1">成果名称</option>
											<option value="2">作者</option>
											<option value="3">单位</option>
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
								<td width="30"><input id="check" name="check" type="checkbox" title="点击全选/不选本页所有项目"/></td>
								<td width="40">序号</td>
								<td width="250"><a id="sortcolumn0" href="" class="{if sortColumn == 0}{if sortColumnLabel == 1}up_css{else}down_css{/if}{/if}" title="点击按成果名称排序">成果名称</a></td>
								<td width="60"><a id="sortcolumn1" href="" class="{if sortColumn == 1}{if sortColumnLabel == 1}up_css{else}down_css{/if}{/if}"  title="点击按作者排序">作者</a></td>
								<td width="100"><a id="sortcolumn2" href="" class="{if sortColumn == 2}{if sortColumnLabel == 1}up_css{else}down_css{/if}{/if}"  title="点击按单位排序">单位</a></td>
								<td width="100"><a id="sortcolumn3" href="" class="{if sortColumn == 3}{if sortColumnLabel == 1}up_css{else}down_css{/if}{/if}"  title="点击按成果类型排序">成果类型</a></td>
								<td width="120"><a id="sortcolumn4" href="" class="{if sortColumn == 4}{if sortColumnLabel == 1}up_css{else}down_css{/if}{/if}"  title="点击按分组排序">分组</a></td>
								<td width="80"><a id="sortcolumn5" href="" class="{if sortColumn == 5}{if sortColumnLabel == 1}up_css{else}down_css{/if}{/if}"  title="点击按出版社/刊物排序">出版社/刊物</a></td>
								<td width="100"><a id="sortcolumn6" href="" class="{if sortColumn == 6}{if sortColumnLabel == 1}up_css{else}down_css{/if}{/if}"  title="点击按出版/发表时间排序">出版/发表时间</a></td>
								<td width="80"><a id="sortcolumn7" href="" class="{if sortColumn == 7}{if sortColumnLabel == 1}up_css{else}down_css{/if}{/if}"  title="点击按校级审核排序">校级审核</a></td>
								<td width="80"><a id="sortcolumn8" href="" class="{if sortColumn == 8}{if sortColumnLabel == 1}up_css{else}down_css{/if}{/if}"  title="点击按社科联审核排序">社科联审核</a></td>
								<td width="80"><a id="sortcolumn9" href="" class="{if sortColumn == 9}{if sortColumnLabel == 1}up_css{else}down_css{/if}{/if}"  title="点击按提交状态排序">提交状态</td>
							</tr>
						</thead>
						<tbody>
						{for item in root}
							<tr>	
								<td><input type="checkbox" name="entityIds" value="${item.laData[0]}"/></td>
								<td class="text-center">${item.num}</td>
								<td class="text-center"><a id="${item.laData[0]}" class ="link1" title="点击查看详细信息">${item.laData[1]}</a></td>
								<td class="text-center">${item.laData[2]}</td>
								<td class="text-center">${item.laData[3]}</td>
								<td class="text-center">${item.laData[4]}</td>
								<td class="text-center">${item.laData[5]}</td>
								<td class="text-center">${item.laData[6]}</td>
								<td class="text-center">${item.laData[7]|toFormat}</td>
								<td class="text-center">
								{if item.laData[8]==1}
									<span class="red"><i class="fa fa-exclamation-triangle"></i>&nbsp;不同意</span>
								{elseif item.laData[8]==2}
									<span class="green"><i class="fa fa-check-circle"></i>&nbsp;同意</span>
								{elseif item.laData[8]==3}退回
								{elseif item.laData[8]==4} — 
								{else}未审核
								{/if}
								</td>
								<td class="text-center">
								{if item.laData[9]==1}
									<span class="red"><i class="fa fa-exclamation-triangle"></i>&nbsp;不同意</span>
								{elseif item.laData[9]==2}
									<span class="green"><i class="fa fa-check-circle"></i>&nbsp;同意</span>
								{elseif item.laData[9]==3}退回
								{else}未审核
								{/if}
								</td>
								<td class="text-center">
								{if item.laData[10]==1}暂存
								{elseif item.laData[10]==2}已提交
								{elseif item.laData[10]==3}退回修改
								{else}未知
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
								<!-- <button id = "list_add" class = "btn btn-default " type="button">添加</button> -->
								<button id = "list_delete" class = "btn btn-default " type="button">删除</button>
								<button id = "btn_audit" class = "btn btn-default " type="button">审核</button>
								<button id = "list_export" class = "btn btn-default " type="button">导出</button>
							</td>
						</tr>
					</table>
				</textarea>
			
				<!-- 显示列表 -->
				<s:form id="list" theme="simple" action="product/application/delete.action">
					<s:hidden id="pagenumber" name="pageNumber"/>
					<s:hidden id="checkedIds" name="checkedIds"/>
					<s:hidden id="auditResult" name="auditResult"/>
					<s:hidden id="auditOption" name="auditOption"/>
					
					<div id="list_container"  style="display:none;"></div>
				</s:form> 
			</div>
		</div>
		
		<div id="div_pop_firstAudit" style="width:500px;display:none;">
			<form id="form_first_audit">
				<div class="form-group">
					<label class="required-label">审核结果:</label>
					<table width="60%">
						<tr>
							<td width="8"></td>
							<td width="30"><input type="radio" class="validate[required]" name="auditResultTemp" value="2"> 同意申报</td>
							<td width="30"><input type="radio" class="validate[required]" name="auditResultTemp" value="3"> 退回修改</td>
							<td width="30"><input type="radio" class="validate[required]" name="auditResultTemp" value="1"> 不同意</td>
						</tr>
					</table>
				</div>
				<div id="div_auditOption" class="form-group">
					<label>审核意见</label>
					<table width="100%">
						<tr class="j-auditBack" style="display: none;">
							<td width="10"></td>
							<td width="45"><input type="checkbox" class="j-auditOption" value="成果无合作者署名">成果无合作者署名</td>
							<td width="45"><input type="checkbox" class="j-auditOption" value="作者或合作者姓名不符">作者或合作者姓名不符</td>
						</tr>
						<tr class="j-auditBack" style="display: none;">
							<td></td>
							<td><input type="checkbox" class="j-auditOption" value="出版社或刊物名称不符">出版社或刊物名称不符</td>
							<td><input type="checkbox" class="j-auditOption" value="成果形式不符">成果形式不符</td>
						</tr>
						<tr class="j-auditBack" style="display: none;">
							<td></td>
							<td><input type="checkbox" class="j-auditOption" value="外文成果无中文简介">外文成果无中文简介</td>
							<td><input type="checkbox" class="j-auditOption" value="">其他</td>
						</tr>
						<tr class="j-auditNotAgree" style="display: none;">
							<td width="10"></td>
							<td width="45"><input type="checkbox" class="j-auditOption" value="成果发表时间不符申报要求">成果发表时间不符申报要求</td>
							<td width="45"><input type="checkbox" class="j-auditOption" value="未发表成果不能参评">未发表成果不能参评</td>
						</tr>
						<tr class="j-auditNotAgree" style="display: none;">
							<td></td>
							<td><input type="checkbox" class="j-auditOption" value="涉密成果不受理">涉密成果不受理</td>
							<td><input type="checkbox" class="j-auditOption" value="">其他</td>
						</tr>
						<tr>
							<td colspan="3"><textarea id="auditOptionTemp" class="form-control" name="auditOptionTemp" rows="3"></textarea></td>
						</tr>
					</table>
					
				</div>
				<div id="optr" class="btn_bar1">
					<div class="btn-group">
						<button id="auditSubmit" class="btn btn-default " type="button">提交</button>
						<button id="auditCancel" class="btn btn-default " type="button" onclick="top.dialog.getCurrent().close();">取消</button>
					</div>
				</div>
			</form>
		</div>
		<!-- 页脚 -->
		<div class="row">
		<%@ include file="/jsp/footer.jsp"%>
	</body>
	<script>
		seajs.use("js/product/firstAudit/list.js", function(list) {
			list.init();
		});
	</script>
</html>