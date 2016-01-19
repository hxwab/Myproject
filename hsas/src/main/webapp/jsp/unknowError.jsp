<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>错误页面</title>
		<s:if test="#session.loginer == null">
			<script type="text/javascript">
		    	$(document).ready(function() {
		    		$("#back").bind("click", function() {
		    			window.location.href = basePath + "toIndex.action";
		    			return false;
		    		});
		    	});
		    </script>
		</s:if>
		<s:else>
			<script type="text/javascript" src="javascript/lib/jquery/jquery.js"></script>
	    </s:else>
	</head>

	<body>
		<s:if test="#session.loginer == null">
			<div class="login_box">
				<s:include value="/outerHead.jsp" />
				<div class="login_input_box">
					<div class="login_input_area">
						<div class="login_error_title">信息提示</div>
						<div class="login_find_pw_txt">
							<s:if test="#request.errorInfo == null">
								有非法数据产生，请求终止。
							</s:if>
							<s:else>
								<s:property value="#request.errorInfo" />
							</s:else>
						</div>
						<s:debug />
						<div class="login_btn_box1"><input id="back" class="login_btn" type="button" value="返回首页" onclick="history.back();" /></div>
					</div>
					<s:include value="/messageIco.jsp" />
				</div>
			</div>
			<s:include value="/outerFoot.jsp" />
		</s:if>
		<s:else>
			<div class="link_bar">
				当前位置：错误页面
			</div>

			<div class="main">
				<div class="main_content" style="text-align:center;">
						<s:if test="hasActionErrors()">
							<s:actionerror/>
						</s:if>
						<s:else>
							有非法数据产生，请求终止。
						</s:else>
				</div>
			</div>
		</s:else>
		<s:debug/>
	</body>
</html>