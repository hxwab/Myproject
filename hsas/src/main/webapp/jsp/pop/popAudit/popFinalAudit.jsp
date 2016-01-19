<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page isELIgnored ="true"%><%--添加对EL表达式的支持--%>
<%@ taglib prefix="s" uri="/struts-tags"%> <%--添加对struts标签的支持 --%>
<!DOCTYPE html>
<html>
    <head>
        <title>湖北省社会科学优秀成果奖申报评审系统</title>
        <%@ include file="/jsp/base.jsp"%>
    </head>
    <body class="popLayer" style="width:500px" >
		<div class="col-xs-12 ">
			<form id="formFinalAudit">
				<s:hidden id="entityId" name="entityId" />
				<div class="form-group">
					<label class="required-label">审核结果</label>
					<div class="radio">
						<label class="radio-inline">
							<input type="radio" class="validate[required]" name="auditResult" id="auditResult1" value="2"> 同意
						</label>
						<label class="radio-inline">
							<input type="radio" class="validate[required]" name="auditResult" id="auditResult2" value="1"> 不同意
						</label>
					</div>
				</div>
				<div id="div_auditOption" class="form-group">
					<label>审核意见</label>
					<textarea id="auditOption1" class="form-control" name="auditOption" rows="3"></textarea>
				</div>
				<div id="optr" class="btn_bar1">
					<div class="btn-group">
						<button id="submit" class="btn btn-default " type="submit">提交</button>
						<button id="cancel" class="btn btn-default " type="button" onclick="top.dialog.getCurrent().close();">取消</button>
					</div>
				</div>
			</form>
		</div>
		<script>
		    seajs.use("js/pop/popAudit/popFinalAudit.js", function() {
		    });
		</script>
	</body>
</html>