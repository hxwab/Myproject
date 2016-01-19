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
				<form id="registerForm" class="form-horizontal" action="register/register.action" method="post">
					<div class="form-group">
						<label class="col-xs-2 control-label required-label">用户名</label>
						<div class="col-xs-10">
							<input name="account.username"  class="form-control form-sm validate[required, custom[onlyLetterNumber]]" type="text" placeholder="请输入数字和英文字母">
						</div>
					</div>
					<div class="form-group">
						<label class="col-xs-2 control-label required-label">密码</label>
						<div class="col-xs-10">
							<input name="account.password"  id="password" class="form-control validate[required, minSize[6]]" type="password" placeholder="至少输入6个字符">
						</div>
					</div>
					<div class="form-group">
						<label class="col-xs-2 control-label required-label">确认密码</label>
						<div class="col-xs-10">
							<input name='rePassword' class="form-control validate[required, equals[password]]" type="password" placeholder="确认密码">
						</div>
					</div>
					<div class="form-group">
						<label class="col-xs-2 control-label required-label">中文名</label>
						<div class="col-xs-10">
							<input name="person.name" class="form-control validate[required, custom[chinese]]" type="text" placeholder="中文名">
						</div>
					</div>
					<div class="form-group">
						<label class="col-xs-2 control-label required-label">性别</label>
						<div class="col-xs-10">
							<select name="person.gender" class="form-control input-sm validate[required]" placeholder="请输入性别">
								<option selected="selected" >请选择</option>
								<option value="男">男</option>
								<option value="女">女</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-xs-2 control-label required-label">出生年月日</label>
						<div class="col-xs-10">
							<input name="person.birthday" class="form-control dataPicker validate[required, custom[date]]" type="text" placeholder="生日">
						</div>
					</div>
					<div class="form-group">
						<label class="col-xs-2 control-label required-label">所在单位</label>
						<div class="col-xs-10">
							<input type="hidden" name="agencyId" class="form-control validate[required]" value="default">
							<button class="btn btn-default  select-agency">选择</button>
							<div class="selected"></div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-xs-2 control-label required-label">邮箱</label>
						<div class="col-xs-10">
							<input name="account.mail" class="form-control validate[required, custom[email]]" type="email" placeholder="邮箱">
						</div>
					</div>
					<div class="form-group">
						<label class="col-xs-2 control-label required-label">联系电话</label>
						<div class="col-xs-10">
							<input name="person.mobilePhone" class="form-control validate[required, custom[phone]]" type="text" placeholder="联系电话">
						</div>
					</div>
					<div class="text-center">
						<div class="btn-group">
							<input type="submit" value="注册" class="btn btn-default " id="submit">
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
		seajs.use('js/register.js', function(){
			
		})
	</script>
</html>