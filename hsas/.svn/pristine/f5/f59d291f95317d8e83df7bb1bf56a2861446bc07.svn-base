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
		<!-- header -->
		<%@ include file="/jsp/innerNav.jsp"%>
		<s:hidden id="type" name="type" />
		<!-- end header -->
		
		<!-- body -->
		<div class="row mySlide">
			<ol class="breadcrumb mybreadcrumb">当前位置：
				<li><a href="javascript:void(0)">统计分析</a></li>
				<li><a id="current_site" href="javascript:void(0)"></a></li>
				<li class="active">详情</li>
			</ol>
			<form class="form-inline" style="text-align:right;padding:0px 30px 5px;">
				<div class="form-group">
					<s:select id ="year" cssClass="form-control input-sm" name="year" headerKey="" headerValue="--请选择--" list="years" />
					<button id="search" type="button" class="btn btn-sm btn-default">检索</button>
  				</div>
			</form>
			
			<div class="col-xs-12">
				<!-- 成果入围模版 -->	
				<textarea id="apply_template" style="display:none" data-info="成果入围">
					<table class="table table-striped table-bordered view-table">
						<thead>
							<tr>
								<th class="text-center">申报总数</th>
								<th class="text-center">进入初评总数</th>
								<th class="text-center">进入复评总数</th>
								<th class="text-center">进入终审总数</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td class="text-center">{if result[0][0]>0}${result[0][0]}{else}0{/if}</td>
								<td class="text-center">{if result[0][1]>0}${result[0][1]}{else}0{/if}</td>
								<td class="text-center">{if result[0][2]>0}${result[0][2]}{else}0{/if}</td>
								<td class="text-center">{if result[0][3]>0}${result[0][3]}{else}0{/if}</td>
							</tr>
						</tbody>
					</table>
				</textarea>
				
				<!-- 初评信息模版 -->
				<textarea id="firstReview_template" style = "display:none" data-info="初评信息">
					<table class="table table-striped table-bordered view-table">
						<thead>
							<tr>
								<th class="text-center">分组</th>
								<th class="text-center">著作</th>
								<th class="text-center">系列论文<br>（基础类）</th>
								<th class="text-center">系列论文<br>（应用对策类）</th>
								<th class="text-center">单篇论文<br>（基础类）</th>
								<th class="text-center">单篇论文<br>（应用对策类）</th>
								<th class="text-center">调研报告<br>（基础类）</th>
								<th class="text-center">调研报告<br>（应用对策类）</th>
								<th class="text-center">论文合计</th>
							</tr>
						</thead>
						<tbody>
							{for item in result}
							<tr>
								<td class="text-center">${item[0]}</td>
								<td class="text-center">{if item[1]>0}${item[1]}{else}0{/if}</td>
								<td class="text-center">{if item[2]>0}${item[2]}{else}0{/if}</td>
								<td class="text-center">{if item[3]>0}${item[3]}{else}0{/if}</td>
								<td class="text-center">{if item[4]>0}${item[4]}{else}0{/if}</td>
								<td class="text-center">{if item[5]>0}${item[5]}{else}0{/if}</td>
								<td class="text-center">{if item[6]>0}${item[6]}{else}0{/if}</td>
								<td class="text-center">{if item[7]>0}${item[7]}{else}0{/if}</td>
								<td class="text-center">{if item[8]>0}${item[8]}{else}0{/if}</td>
							</tr>
							{/for}
						</tbody>
					</table>
				</textarea>
				
				<!-- 复评信息模版 -->
				<textarea id="secondReview_template" style = "display:none" data-info="复评信息">
					<table class="table table-striped table-bordered view-table">
						<thead>
							<tr>
								<th class="text-center">分组</th>
								<th class="text-center">著作</th>
								<th class="text-center">系列论文<br>（基础类）</th>
								<th class="text-center">系列论文<br>（应用对策类）</th>
								<th class="text-center">单篇论文<br>（基础类）</th>
								<th class="text-center">单篇论文<br>（应用对策类）</th>
								<th class="text-center">调研报告<br>（基础类）</th>
								<th class="text-center">调研报告<br>（应用对策类）</th>
								<th class="text-center">论文合计</th>
							</tr>
						</thead>
						<tbody>
							{for item in result}
							<tr>
								<td class="text-center">${item[0]}</td>
								<td class="text-center">{if item[1]>0}${item[1]}{else}0{/if}</td>
								<td class="text-center">{if item[2]>0}${item[2]}{else}0{/if}</td>
								<td class="text-center">{if item[3]>0}${item[3]}{else}0{/if}</td>
								<td class="text-center">{if item[4]>0}${item[4]}{else}0{/if}</td>
								<td class="text-center">{if item[5]>0}${item[5]}{else}0{/if}</td>
								<td class="text-center">{if item[6]>0}${item[6]}{else}0{/if}</td>
								<td class="text-center">{if item[7]>0}${item[7]}{else}0{/if}</td>
								<td class="text-center">{if item[8]>0}${item[8]}{else}0{/if}</td>
							</tr>
							{/for}
						</tbody>
					</table>
				</textarea>
				
				<!-- 单位申报模版 -->
				<textarea id="agency_template" style = "display:none" data-info="单位申报">
					<table class="table table-striped table-bordered view-table">
						<thead>
							<tr>
								<th class="text-center">序号</th>
								<th class="text-center">单位名称</th>
								<th class="text-center">著作</th>
								<th class="text-center">单篇论文</th>
								<th class="text-center">系列论文</th>
								<th class="text-center">调研报告</th>
								<th class="text-center">总数</th>
							</tr>
						</thead>
						<tbody>
							{for item in result}
							<tr>
								<td class="text-center">${parseInt(item_index)+1}</td>
								<td class="text-center">${item[0]}</td>
								<td class="text-center">{if item[1]>0}${item[1]}{else}0{/if}</td>
								<td class="text-center">{if item[2]>0}${item[2]}{else}0{/if}</td>
								<td class="text-center">{if item[3]>0}${item[3]}{else}0{/if}</td>
								<td class="text-center">{if item[4]>0}${item[4]}{else}0{/if}</td>
								<td class="text-center">{if item[5]>0}${item[5]}{else}0{/if}</td>
							</tr>
							{/for}
						</tbody>
					</table>
				</textarea>
				
				<!-- 作者年龄模版 -->
				<textarea id="authorAge_template" style = "display:none" data-info="作者年龄">
					<table class="table table-striped table-bordered view-table">
						<thead>
							<tr>
								<th class="text-center">总人数<br>（第一作者）</th>
								<th class="text-center">40岁以下人数(获奖)<br>（第一作者）</th>
								<th class="text-center">40岁以下比率<br>（第一作者）</th>
								<th class="text-center">总人数<br>（全部作者）</th>
								<th class="text-center">40岁以下人数(获奖)<br>（全部作者）</th>
								<th class="text-center">40岁以下比率<br>（全部作者）</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td class="text-center">${result[0][0]}</td>
								<td class="text-center">${result[0][1]}</td>
								<td class="text-center">${result[0][2]}</td>
								<td class="text-center">${result[0][3]}</td>
								<td class="text-center">${result[0][4]}</td>
								<td class="text-center">${result[0][5]}</td>
							</tr>
						</tbody>
					</table>
				</textarea>
				
				<!-- 奖项分布模版 -->
				<textarea id="award_template" style = "display:none" data-info="奖项分布">
					<table class="table table-striped table-bordered view-table">
						<thead>
							<tr>
								<th class="text-center">序号</th>
								<th class="text-center">单位</th>
								<th class="text-center">一等奖（著作）</th>
								<th class="text-center">二等奖（著作）</th>
								<th class="text-center">三等奖（著作）</th>
								<th class="text-center">一等奖（论文）</th>
								<th class="text-center">二等奖（论文）</th>
								<th class="text-center">三等奖（论文）</th>
								<th class="text-center">总数</th>
							</tr>
						</thead>
						<tbody>
							{for item in result}
							<tr>
								<td class="text-center">${parseInt(item_index)+1}</td>
								<td class="text-center">${item[0]}</td>
								<td class="text-center">{if item[1]>0}${item[1]}{else}0{/if}</td>
								<td class="text-center">{if item[2]>0}${item[2]}{else}0{/if}</td>
								<td class="text-center">{if item[3]>0}${item[3]}{else}0{/if}</td>
								<td class="text-center">{if item[4]>0}${item[4]}{else}0{/if}</td>
								<td class="text-center">{if item[5]>0}${item[5]}{else}0{/if}</td>
								<td class="text-center">{if item[6]>0}${item[6]}{else}0{/if}</td>
								<td class="text-center">{if item[7]>0}${item[7]}{else}0{/if}</td>
							</tr>
							{/for}
						</tbody>
					</table>
				</textarea>
				
				<div id = "view_container" style = "display:none"></div><!-- 模版解析后的容器 -->
			</div>
			
		</div>
		<!-- end body -->
		
		<!-- footer -->
		<div class="row">
		<%@ include file="/jsp/footer.jsp"%>
		<!-- end footer -->
		
		<script>
			seajs.use("js/statistic/view.js", function(view) {
				 view.init();
			});
		</script>
	</body>
</html>