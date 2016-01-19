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
    <body class="pop-body" style="width:650px" >
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
										<td width = "30" class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td width = "60" class = "text-right view-key">姓名：</td>
										<td width = "120">${name}</td>
										<td width = "30" class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td width = "60" class = "text-right view-key">性别：</td>
										<td width = "120">${gender}</td>
									</tr>
									<tr>
										<td class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td class = "text-right view-key">民族：</td>
										<td>${ethnic}</td>
										<td class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td class = "text-right view-key">email：</td>
										<td >${email }</td>
									</tr>
									<tr>
										<td class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td class = "text-right view-key">出生年月：</td>
										<td>${birthday}</td>
										<td class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td class = "text-right view-key">身份证号：</td>
										<td >${idcardNumber }</td>
									</tr>
									<tr>
										<td class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td class = "text-right view-key">手机号码：</td>
										<td>${mobilePhone}</td>
										<td class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td class = "text-right view-key">所属机构：</td>
										<td>${agencyName}</td>
									</tr>
									<tr>
										<td class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td class = "text-right view-key">职位：</td>
										<td></td>
										<td class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td class = "text-right view-key">邮编：</td>
										<td>${postCode}</td>
										
									</tr>
									<tr>
										<td class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td class = "text-right view-key">联系地址：</td>
										<td colspan="4">${address}</td>
									</tr>
									<tr>
										<td class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td class = "text-right view-key">分工情况：</td>
										<td></td>
									</tr>
									<tr>
										<td class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td class = "text-right view-key">个人简介：</td>
										<td>${introduction}</td>
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
		    seajs.use("js/pop/popView/popViewPerson.js", function(view) {
		         view.init(); 
		    });
		</script>
	</body>
</html>