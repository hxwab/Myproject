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
   		<s:hidden id="entityId" name="entityId" value="4028d88e504badea01504bae67460000" />
   		<s:hidden id="productType" name="productType" value="1" />
   		<a name="top" id="top"></a>
			<div class="row mySlide">
			<ol class="breadcrumb mybreadcrumb">当前位置：
				  <li><a href="#">评奖管理</a></li>
				  <li class="active">终审</li>
				</ol>
			</ol>
			    <div class="col-xs-12 main-content">
			    	<textarea id="view_template" style="display:none">
					    <div class="panel panel-default panel-view">
						  	<div class="panel-body">
							    <table class="table table-striped view-table">
							      <tbody>
									<tr>
										<td width = "50" class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td width = "100" class = "text-right">成果名称：</td>
										<td width = "100"></td>
										<td width = "150"></td>
										<td width = "100"></td>
										<td ></td>
									</tr>
									<tr>
										<td width = "50" class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td width = "100" class = "text-right">第一作者：</td>
										<td class = "text-left"  width = "100"></td>
										<td width = "50" class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td width = "100" class = "text-right">合作者：</td>
										<td width ='' class = "text-left" ></td>
									</tr>
									<tr>
										<td width = "50" class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td width = "100" class = "text-right">研究类型：</td>
										<td width ='' class = "text-left" ></td>
										<td width = "50" class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td width = "100" class = "text-right">依托机构：</td>
										<td class = "text-left" ><a href = "4028d89a4ae844f5014ae84659450133" class="pop-view-agency"></a></td>
									</tr>
									<tr>
										<td width = "50" class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
										<td width = "100" class = "text-right">提交状态：</td>
										<td class = "text-left" colspan = "4"></td>
									</tr>
								  </tbody>
					    		</table>
					    	</div>
						</div>
				    	
					    <div class="panel panel-default panel-view member">
							 <div class="panel-heading clearfix">
							 	<span class="label label-primary label-custom">评审意见</span>
							 	<div class = "btn-group pull-right">
									<button class="btn btn-default  add-second-review">添加</button>
								</div>
							</div>
						  	<div class="panel-body" style="padding:15px">
						  		<table class = "table table-bordered">
						  			<thead>
						  				<tr>
						  					<th>序号</th>
						  					<th>专家</th>
						  					<th>复评评审意见</th>
						  					<th>添加时间</th>
						  					<th>操作</th>
						  				</tr>
						  			</thead>
						  			<tbody>
						  			{for item in secondReview}
						  			<tr>
						  				<td width="50">${parseInt(item_index) + 1 }</td>
						  				<td width="80">${item.expert }</td>
						  				<td>${item.opinion }</td>
						  				<td width="150">${item.time }</td>
						  				<td class="text-center" width="150">
						  					<button class="btn btn-default  modify-second-review">修改</button> 
						  					<buton class="btn btn-danger  delete-second-review">删除</buton>
						  				</td>
						  			</tr>
						  			{forelse}
						  			<tr>
						  				<td colspan="5" class="text-center">没有复评审核意见！</td>
						  			</tr>
						  			{/for}
						  			</tbody>
						  		</table>
						  	</div>
						</div>
						<div class="panel panel-default panel-view member">
							 <div class="panel-heading clearfix">
							 	<span class="label label-primary label-custom">推荐意见</span>
							</div>
						  	<div class="panel-body" style="padding:15px">
						  		<table width="100%" class="table table-bordered text-center">
						  			<tr>
						  				<td><label><input type="radio" name="level" value = "1"/>一等奖</label></td>
						  				<td><label><input type="radio" name="level" value = "2"/>二等奖</label></td>
						  				<td><label><input type="radio" name="level" value = "3"/>三等奖</label></td>
						  				<td><label><input type="radio" name="level" value = "0"/>不推荐</label></td>
						  				<td><button class="btn btn-success  submit-level">确定</button></td>
						  			</tr>
						  		</table>
						  	</div>
						  </div>
					</textarea>
					<div id="view_container" style="display:none"></div>
					<table id="add_second_review_template"  style="display:none" width="100%">
						<tr>
							<td width = "80" class="required-label">审核意见：</td>
							<td><textarea class="form-control input-sm validate[required]" name="opinion"></textarea></td>
						</tr>
					</table>
			    </div>
			</div>
			<div class="row">
   		<%@ include file="/jsp/footer.jsp"%>
		<script>
		    seajs.use("js/product/finalAudit/review.js", function(review) {
		    	review.init(); 
		    });
		</script>
	</body>
</html>