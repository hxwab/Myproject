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
    <input id="entityId" type="hidden" name="entityId" />
   		<div class="no-padding">
			<div class="row mySlide">
			    <div class="col-xs-12 ">
					 <textarea id = "view_template" style = "display:none"><!-- 前台模版 -->
						 <div class="panel panel-default panel-view">
						  	<div class="panel-body">
							    <table class="table table-striped view-table">
							      <tbody>
									<tr>
										<td width = "50" class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td width = "100" class = "text-right view-key">机构名称：</td>
										<td width = "110">${name}</td>
										<td width = "100" class = "text-right"></td>
										<td width = "100" class = "text-right"></td>
										<td ></td>
									</tr>
									<tr>
										<td width = "50" class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td width = "100" class = "text-right view-key">电子邮箱：</td>
										<td width = "100">${email}</td>
										<td width = "50" class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td width = "100" class = "text-right view-key">电话：</td>
										<td >${officePhone }</td>
									</tr>
									<tr>
										<td width = "50" class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td width = "100" class = "text-right view-key">传真：</td>
										<td width = "100">${officeFax}</td>
										<td width = "50" class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td width = "100" class = "text-right view-key">地址：</td>
										<td >${address }</td>
									</tr>
									<tr>
										<td width = "50" class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td width = "100" class = "text-right view-key">邮编：</td>
										<td width = "100">${postCode}</td>
										<td width = "50" class = "text-right"></td>
										<td width = "100" class = "text-right"></td>
										<td ></td>
									</tr>
								  </tbody>
					    		</table>
					    	</div>
					    </div>
			    	</textarea>
			    	<div id = "view_container" style = "display:none"></div><!-- 模版解析后的容器 -->
			    	
			    </div>
			</div>
		</div>
		<script src="lib/bootstrap-3.3.5/js/bootstrap.js" type="text/javascript"></script>
		<script>
		    seajs.use("js/pop/popView/popViewAgency.js", function(view) {
		         view.init(); 
		    });
		</script>
	</body>
</html>