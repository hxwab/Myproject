<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>湖北省社会科学优秀成果奖申报评审系统</title>
        <%@ include file="/jsp/base.jsp"%>
         <link rel="stylesheet" href="lib/bootstrap-datepicker-1.4.0-dist/css/bootstrap-datepicker3.css">
    </head>
    <body>
    	<%@ include file="/jsp/innerNav.jsp"%>
		<div class="row">
			<ol class="breadcrumb mybreadcrumb">当前位置：
				<li><a href="#">首页</a></li>
				<li class="active">注册</li>
			</ol>
			<div class="col-xs-8 col-xs-offset-2" style="margin-bottom:10px">
				<form id="forgetPasswordForm" class="form-horizontal">
					<div class="form-group">
						<label class="col-xs-2 control-label required-label">用户名</label>
						<div class="col-xs-10">
							<input name="username"  class="form-control form-sm validate[required, custom[onlyLetterNumber]]" type="text" placeholder="请输入数字和英文字母">
						</div>
					</div>
					<div class="form-group">
						<label class="col-xs-2 control-label required-label">邮箱</label>
						<div class="col-xs-10">
							<input name="email" class="form-control validate[required, custom[email]]" type="email" placeholder="邮箱">
						</div>
					</div>
					<div class="text-center">
						<div class="btn-group">
							<input type="submit" value="提交" class="btn btn-default " id="submit">
							<input type="button" value="取消" class="btn btn-default " id="cancel">
						</div>
					</div>
				</form>
			</div>
		</div>
		<div class="row">
		<%@ include file="/jsp/footer.jsp"%>
	</body>
	<script>
		seajs.use('js/forgetPassword.js', function(){
			
		})
	</script>
</html>