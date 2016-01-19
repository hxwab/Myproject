<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page isELIgnored="true"%><%--添加对EL表达式的支持--%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
    <head>
        <title>湖北省社会科学优秀成果奖申报评审系统</title>
        <%@ include file="/jsp/base.jsp"%>
    </head>
    <body>
    		<%@ include file="/jsp/innerNav.jsp"%>
			<div class="row">
				<ol class="breadcrumb mybreadcrumb">当前位置：
					<li><a href="#">个人信息</a></li>
					<li class="active">修改</li>
				</ol>
				
				<div class="col-xs-8 col-xs-offset-2">
					<form id="modifyForm" class="form-horizontal">
						<div class="form-group">
							<label class="col-xs-2 control-label required-label">中文名</label>
							<div class="col-xs-10">
								<s:textfield id="chineseName" class="form-control validate[required, custom[chinese]]" name="account.person.name" placeholder="中文名" />
							</div>
						</div>
						<div class="form-group"> 
							<label class="col-xs-2 control-label required-label">性别</label>
							<div class="col-xs-10">
								<s:select cssClass="form-control input-sm validate[required]" name="account.person.gender" headerKey="" headerValue="--请选择--" list="#{'男':'男', '女':'女'}" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-xs-2 control-label required-label">所在单位</label>
							<div class="col-xs-10">
								<input class="btn btn-default  select-agency" type="button" value="选择机构" />
								<div class="selected">
									<div class="selected-link">
										<span class="label label-primary" id=""><s:property value="account.person.agencyName" /></span>
										<div class="delete-link"><i class="fa fa-times"></i></div>
									</div>
								</div>
					    		<s:hidden cssClass="form-control input-sm validate[required]" name="account.agency.id" />
					    		<s:hidden cssClass="form-control input-sm validate[required]" name="account.agency.name" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-xs-2 control-label">联系电话</label>
							<div class="col-xs-10">
								<s:textfield id="phone" class="form-control validate[required, custom[phone]]" name="account.person.mobilePhone" placeholder="联系电话" />
							</div>
						</div>
						<div id="optr" class="btn_bar1 form-group">
							<div class="btn-group">
								<input id="submit" class="btn btn-default " type="submit" value="确定">
								<input id="cancel" class="btn btn-default " type="button" value="取消">
							</div>
						</div>
					</form>
				</div>
			<%@ include file="/jsp/footer.jsp"%>
	<script>
		seajs.use('js/selfspace/modify.js', function(){
			
		})
	</script>
	</body>
</html>