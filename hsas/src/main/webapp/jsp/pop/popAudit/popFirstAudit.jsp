<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page isELIgnored ="true"%><%--添加对EL表达式的支持--%>
<%@ taglib prefix="s" uri="/struts-tags"%> <%--添加对struts标签的支持 --%>
<!DOCTYPE html>
<html>
    <head>
        <title>湖北省社会科学优秀成果奖申报评审系统</title>
        <%@ include file="/jsp/base.jsp"%>
    </head>
    <body class="popLayer" style="width:500px;font-size:14px;float:right;" >
		<div class="col-xs-12 ">
			<form id="formFirstAudit">
				<s:hidden id="entityId" name="entityId" />
				<div class="form-group">
					<label class="required-label">审核结果</label>
					<table width="60%">
						<tr>
							<td width="8"></td>
							<td width="30"><input type="radio" class="validate[required]" name="auditResult" id="auditResult1" value="2"> 同意申报</td>
							<td width="30"><input type="radio" class="validate[required]" name="auditResult" id="auditResult3" value="3"> 退回修改</td>
							<td width="30"><input type="radio" class="validate[required]" name="auditResult" id="auditResult2" value="1"> 不同意</td>
						</tr>
					</table>
				</div>
				<div id="div_auditOption" class="form-group">
					<label class="required-label">审核意见</label>
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
							<td colspan="3"><textarea id="auditOption" class="form-control" name="auditOption" rows="3"></textarea></td>
						</tr>
					</table>
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
		    seajs.use("js/pop/popAudit/popFirstAudit.js", function() {
		    });
		</script>
	</body>
</html>