<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page isELIgnored ="true"%><%--添加对EL表达式的支持--%>
<%@ taglib prefix="s" uri="/struts-tags"%> <%--添加对struts标签的支持 --%>
<!DOCTYPE html>
<html>
    <head>
        <title>湖北省社会科学优秀成果奖申报评审系统</title>
        <%@ include file="/jsp/base.jsp"%>
    </head>
    <body class="popLayer" >
   		<div class="div_assign_role">
				<table width="100%" border="0" cellspacing="0" cellpadding="2" class="table_css1">
					<tr>
						<td></td>
						<td valign="top" width="300">
							<div class="panel panel-default">
								 <div class="panel-heading table_s_title"><strong>未分配角色</strong></div>
								 <div class="panel-body table_select">
								 <select name="disroleIds" id="leftselect" class="select" multiple="multiple"></select>
								</div>
							</div>
						</td>
						<td width="120" align="center" class = "action">
						<div class="table_s_title"> &nbsp;</div>
							<button class="btn btn-default" id = "add"><i class="fa fa-angle-right" ></i></button>
							<button class="btn btn-default" id="addall"><i class="fa fa-angle-double-right" ></i></button>
							<button class="btn btn-default" id="remove"><i class="fa fa-angle-left" ></i></button>
							<button class="btn btn-default" id="removeall"><i class="fa fa-angle-double-left" ></i></button>
						<td valign="top" width="300">
						<div class="panel panel-default">
						  <div class="panel-heading table_s_title"><strong>已分配角色</strong></div>
						  <div class="panel-body table_select">
						  	<select name="roleIds" id="rightselect" class="select list-group"  multiple="multiple">
								    <option value="f31c4a822fd8c4a2012ffbebe8e509a0" class="list-group-item">基本角色</option>
								    <option value="4028d8872edb6617012edb8774e800b4" class="list-group-item">教育部子管理员</option>
								    <option value="4028d89a42501fb30142509208d00056" class="list-group-item">无敌角色</option>
								    <option value="4028d89a42501fb30142509208d00056" class="list-group-item">无敌角色</option>
								    <option value="4028d89a42501fb30142509208d00056" class="list-group-item">无敌角色</option>
								</select>
						  </div>
						</div>
						</td>
					</tr>
				</table>
			</div>
			
			<div class="text-center btn-bar">
	           	<div class="btn-group">
	           		<input id="confirm" class="btn btn-default" data-id="ok" type="button" value="确定" />
					<input id="cancel" class="btn btn-default" type="button" value="取消" />
	           	</div>
	         </div>
		<script>
		    seajs.use("js/system/account/popAssignRole.js", function(popAssignRole) {
		    	popAssignRole.init(); 
		    });
		</script>
	</body>
</html>