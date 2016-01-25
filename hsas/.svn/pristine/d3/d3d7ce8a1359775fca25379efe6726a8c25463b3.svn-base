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
   		<s:hidden id="entityId" name="entityId" value="%{entityId}" />
   		<a name="top" id="top"></a>
			<div class="row mySlide">
			<ol class="breadcrumb mybreadcrumb">当前位置：
			  <li><a href="javascript:void(0)">系统管理</a></li>
			  <li><a href="system/dataDic/agency/toList.action?update=1">机构管理</a></li>
			  <li class="active">查看</li>
			</ol>
			    <div class="col-xs-12 main-content">
				     <div class="btn-group pull-right view-controler" role="group" aria-label="...">
			  			<button type="button" class="btn  btn-default" id = "view_add">添加</button>
			  			<button type="button" class="btn  btn-default" id = "view_mod">修改</button>
			  			<button type="button" class="btn  btn-default" id = "view_del">删除</button>
			  			<button type="button" class="btn  btn-default" id = "view_prev">上一条</button>
			  			<button type="button" class="btn  btn-default" id = "view_next">下一条</button>
			  			<button type="button" class="btn  btn-default" id = "view_back">返回</button>
					</div>
					<span class="clearfix"></span><!-- 重要!! 用于清除按键组浮动 -->
					 <textarea id = "view_template" style = "display:none"><!-- 前台模版 -->
					    <table class="table table-striped view-table">
					      <tbody>
							<tr>
								<td width = "50" class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
								<td width = "100" class = "text-right view-key">机构名称：</td>
								<td width ='300' class = "text-left">${agency.name }</td>
								<td width = "50" class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
								<td width = "100" class = "text-right view-key">英文名称：</td>
								<td width ='' class = "text-left" >${agency.englishName}</td>
							</tr>
							<tr>
								<td width = "50" class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
								<td width = "100" class = "text-right view-key">机构类型：</td>
								<td width="300" class = "text-left">{if agency.type == 0}非高校{else}高校{/if}</td>
								<td width = "50" class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
								<td width = "100" class = "text-right view-key">机构简称：</td>
								<td width ='' class = "text-left" >${agency.abbr}</td>
							</tr>
							<tr>
								<td width = "50" class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
								<td width = "100" class = "text-right view-key">邮政编码：</td>
								<td width="300" class = "text-left">${agency.postCode}</td>
								<td width = "50" class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
								<td width = "100" class = "text-right view-key">所在地址:</td>
								<td width ='' class = "text-left" >${agency.address}</td>
							</tr>
							<tr>
								<td width = "50" class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
								<td width = "100" class = "text-right view-key">联系人：</td>
								<td width="300" class = "text-left">${agency.directorName}</td>
								<td width = "50" class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
								<td width = "100" class = "text-right view-key">手机号码：</td>
								<td width ='' class = "text-left" >${agency.mobilePhone}</td>
							</tr>
							<tr>
								<td width = "50" class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
								<td width = "100" class = "text-right view-key">电子邮箱：</td>
								<td width="300" class = "text-left">${agency.email}</td>
								<td width = "50" class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
								<td width = "100" class = "text-right view-key">机构主页：</td>
								<td width ='' class = "text-left" >${agency.homepage}</td>
							</tr>
							<tr>
								<td width = "50" class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
								<td width = "100" class = "text-right view-key">办公电话：</td>
								<td width="300" class = "text-left">${agency.officePhone}</td>
								<td width = "50" class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
								<td width = "100" class = "text-right view-key">办公传真：</td>
								<td width ='' class = "text-left" >${agency.officeFax}</td>
							</tr>
							<tr>
								<td width = "50" class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
								<td width = "100" class = "text-right view-key">机构简介：</td>
								<td width="" class = "text-left" colspan="4">${agency.introduction }</td>
							</tr>
						  </tbody>
			    		</table>
			    	</textarea>
			    	<div id = "view_container" style = "display:none"></div><!-- 模版解析后的容器 -->
			    	
			    </div>
			</div>
			<div class="row">
   		<%@ include file="/jsp/footer.jsp"%>
		<script>
		    seajs.use("js/system/dataDic/agency/view.js", function(view) {
		         view.init(); 
		    });
		</script>
	</body>
</html>