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
   		<s:hidden id="entityId" name="entityId" />
   		<a name="top" id="top"></a>
		<div class="row mySlide">
		<ol class="breadcrumb mybreadcrumb">当前位置：
			  <li><a href="#">评奖管理</a></li>
			  <li class="active">复评</li>
			</ol>
		</ol>
		<div class="col-xs-12 main-content">
	     	<div class="btn-group pull-right view-controler" role="group" aria-label="...">
                  <button type="button" class="btn  btn-default" id = "view_prev">上一条</button>
                  <button type="button" class="btn  btn-default" id = "view_next">下一条</button>
                  <button type="button" class="btn  btn-default" id = "view_back">返回</button>
            </div>
            <span class="clearfix"></span><!-- 重要!! 用于清除按键组浮动 -->
            <div class="panel panel-default panel-view" id = "product_info" style = "display:none"> </div>
		    <textarea id = "product_info_template" style = "display:none"><!-- 前台模版 -->
                  <div class="panel-body">
                         <table class="table table-striped view-table">
                           <tbody>
                             <tr>
                                 <td width = "50" class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
                                 <td width = "100" class = "text-right view-key">成果名称：</td>
                                 <td width = "100">${product.name }</td>
                                 <td width = "150"></td>
                                 <td width = "100"></td>
                                 <td ></td>
                             </tr>
                             <tr>
                                 <td width = "50" class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
                                 <td width = "100" class = "text-right view-key">第一作者：</td>
                                 <td class = "text-left"  width = "100">${product.authorName }</td>
                                 <td width = "50" class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
                                 <td width = "100" class = "text-right view-key">合作者：</td>
                                 <td width ='' class = "text-left" >{for item in members} <a href = "${item[0].id}" class="pop-view-person">${item[0].name }</a>；{/for}</td>
                             </tr>
                             <tr>
                                 <td width = "50" class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
                                 <td width = "100" class = "text-right ">研究类型：</td>
                                 <td width ='' class = "text-left" >${product.researchType}</td>
                                 <td width = "50" class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
                                 <td width = "100" class = "text-right view-key">依托机构：</td>
                                 <td class = "text-left" ><a href = "${product.agency.id }" class="pop-view-agency">${product.agencyName }</a></td>
                             </tr>
                             <tr>
                                 <td width = "50" class = "text-right"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></td>
                                 <td width = "100" class = "text-right view-key">提交状态：</td>
                                 <td class = "text-left" colspan = "4">${product.status}</td>
                             </tr>
                           </tbody>
                         </table>
                     </div>
            </textarea>
            
             <textarea id = "product_detail_info_template" style = "display:none"><!-- 前台模版 -->
		          <table class="table table-striped table-bordered">
					<tbody>
						<tr>
							<td width="120" class = "text-right view-key">出版社或发表刊物名称</td>
							<td width="200" class = "text-left" >${ product.publishName}</td>
							<td width="120" class = "text-right view-key">出版社或发表刊物级别</td>
							<td width="200" class = "text-left" >${product.publishLevel }</td>
						</tr>
						<tr>
							<td class = "text-right view-key">出版或发表时间</td>
							<td class = "text-left" >${product.publishDate}</td>
							<td class = "text-right view-key">合作者</td>
							<td class = "text-left" >
								{for item in members} 
									{if item_index != 0}
										<a href = "${item[0].id}" class="pop-view-person">${item[0].name }</a>
										{if item_index != members.length-1}；{/if}
									{else}
									{/if}
								{/for}
							</td>
						</tr>
						<tr>
							<td width="120" class = "text-right view-key">成果内容简介</td>
							<td width="520" class = "text-left" colspan="3">${product.introduction}</td>
						</tr>
						<tr>
							<td width="120" class = "text-right view-key">成果内容概要</td>
							<td width="520" class = "text-left" colspan="3">${product.abstractStr }</td>
						</tr>
						<tr>
							<td width="100" class = "text-right view-key">成果社会反映</td>
							<td width="520" class = "text-left" colspan="3">${product.socialEffect }</td>
						</tr>
					</tbody>
					</table>				
	        </textarea>
	        <div class="tab-custom">
	             <ul class="nav nav-tabs" role="tablist">
					<li role="presentation" class="active"><a href="#secondReview" aria-controls="secondReview" role="tab" data-toggle="tab">复评</a></li>
					<li role="presentation" ><a href="#product_detail_info" aria-controls="basic" role="tab" data-toggle="tab">基本信息</a></li>
				</ul>
                 <div class="tab-content ">
                 	<div role="tabpanel" class="tab-pane fade in active" id="secondReview"></div>
                    <div role="tabpanel" class="tab-pane fade " id="product_detail_info"></div>
                 </div>
		    </div>
            <textarea id="second-review_template" style="display:none">
            <div class="panel panel-default panel-view member">
            {if isSecondReviewer == 1}
					 <div class="panel-heading clearfix">
					 	<span class="label label-primary label-custom">复评</span>
					 	<div class = "btn-group pull-right">
					 		{if isReviewed != 1 && isChiefReviewer == 1}
							<button class="btn btn-primary  chiefReviewer-add-second-review ">添加</button>
							{elseif isReviewed != 1 && isChiefReviewer != 1}
							<button class="btn btn-primary  reviewer-add-second-review ">添加</button>
							{elseif isReviewed == 1 && isChiefReviewer == 1}
							<button class="btn btn-primary  chiefReviewer-modify-second-review ">修改评审意见</button>
							{else}
							<button class="btn btn-primary  reviewer-modify-second-review ">修改</button>
							{/if}
							{if (isReviewed == 1 && auditAble == 1)}<button class="btn btn-success  review">审核</button> {/if}
						</div>
					</div>
					<div class="panel-body" style="padding:15px">
					{if isReviewed == 1}
						<table width="100%" class="table table-bordered text-center">
						<tbody>
						<thead>
							<tr class="table-head">
								<th colspan="2" class="text-center">审核情况</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td width="50%">审核结果</td>
								<td>
									{if isBacked == 1}
									<label class="label label-danger label-custom">退回</label>
									{else}
									
									{/if}
								</td>
							</tr>
						</tbody>
						</tbody>
						</table>
					{/if}
					
						<table width="100%" class="table table-bordered text-center">
						<tbody>
						<thead>
							<tr class="table-head">
								<th colspan="3" class="text-center ">复评意见</th>
							</tr>
						</thead>
						<tbody>
							<tr >
								<td width="100">专家</td>
								<td>复评意见</td>
								<td width="100">是否主审</td>
							</tr>
						{if isReviewed == 1 }
							<tr>
								<td>${myOpinion[1]}</td>
								<td>${myOpinion[0]}</td>
								<td>{if myOpinion[2] == 0}否{else}是{/if}</td>
							</tr>
							{if auditAble == 1 }
							{for item in otherOpinioin}
							<tr>
								<td>${item[1] }</td>
								<td>{if item[0] == ""} 未评 {else}${item[0] }{/if}</td>
								<td>{if item[2] == 1} 是{else}否{/if}</td>
							</tr>
							{/for}
							{/if}
						{else}
							<tr>
								<td colspan="3" class="text-center"><label class="label label-danger label-custom">还未进行复评，请添加！</label></td>
							</tr>
						{/if} 
						</tbody>
						</tbody>
						</table>
				{if (isReviewed == 1 && isChiefReviewer == 1) || auditAble == 1 }
						<table width="100%" class="table table-bordered text-center">
						<tbody>
						<thead>
							<tr class="table-head">
								<th colspan="2" class="text-center">推荐情况</th>
							</tr>
							
						</thead>
						<tbody>
							<tr>
								<td width="50%">推荐等级</td>
								<td>
									<label class="label label-info label-custom">{if rewardLevel == 1} 特等奖 {elseif rewardLevel == 2} 一等奖 {elseif rewardLevel == 3} 二等奖{elseif rewardLevel == 4}三等奖{elseif rewardLevel == 5}不推荐{/if}</label>
								</td>

							</tr>
						</tbody>
						</tbody>
						</table>
				{/if}
						</div>
						
			{elseif isAdmin == 1}
					<div class="panel-heading clearfix">
					 	<span class="label label-primary label-custom">复评</span>
					</div>
					<div class="panel-body" style="padding:15px">
						<table width="100%" class="table table-bordered text-center">
						<tbody>
						<thead>
							<tr class="table-head">
								<th colspan="2" class="text-center">组长审核意见</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td width="50%">审核结果</td>
								<td>
									{if leaderAuditResult == 0}
									<label class="label label-default label-custom">未审核</label>
									{elseif leaderAuditResult == 1}
									<label class="label label-danger label-custom">退回</label>
									{else}
									<label class="label label-success label-custom">通过</label>
									{/if}
								</td>
							</tr>
						</tbody>
						</tbody>
						</table>
					
						<table width="100%" class="table table-bordered text-center">
						<tbody>
						<thead>
							<tr class="table-head">
								<th colspan="3" class="text-center ">复评意见</th>
							</tr>
						</thead>
						<tbody>
							<tr >
								<td width="100">专家</td>
								<td>复评意见</td>
								<td width="100">是否主审</td>
							</tr>
						{if allOpinioin != null }
						{for item in allOpinioin}
							<tr>
								<td>${item[1] }</td>
								<td>{if item[0] == ""} 未评 {else}${item[0] }{/if}</td>
								<td>{if item[2] == 1} 是{else}否{/if}</td>
							</tr>
						{/for}
						{else}
							<tr>
								<td colspan="3" class="text-center"><label class="label label-danger label-custom">还未进行复评！</label></td>
							</tr>
						{/if}
						</tbody>
						</tbody>
						</table>
						
						<table width="100%" class="table table-bordered text-center">
						<tbody>
						<thead>
							<tr class="table-head">
								<th colspan="2" class="text-center">推荐情况</th>
							</tr>
							
						</thead>
						<tbody>
							<tr>
								<td width="50%">推荐等级</td>
								<td>
									<label class="label label-info label-custom">{if rewardLevel == 1} 特等奖 {elseif rewardLevel == 2} 一等奖 {elseif rewardLevel == 3} 二等奖{elseif rewardLevel == 4}三等奖{elseif rewardLevel == 5}不推荐{/if}</label>
								</td>

							</tr>
						</tbody>
						</tbody>
						</table>
					</div>
			{else}
					<div class="panel-heading clearfix">
					 	<span class="label label-primary label-custom">提示</span>
					</div>
				  	<div class="panel-body" style="padding:15px">
				  		<table class = "table table-bordered">
				  			<tbody>
				  				<tr><td>获取信息出错！</td></tr>
				  			</tbody>
				  		</table>
				  	</div>
			{/if}
		  	</div>
		  	</textarea>
			<div id = "add_second_review_template_1" style="display:none">
				<form action = "">
				<s:hidden id="entityId" name="entityId" />
				<table width="100%">
					<tr>
						<td width = "80" class="required-label"s>审核意见：</td>
						<td><textarea class="form-control input-sm validate[required]" name="reviewOpinion"></textarea></td>
					</tr>
					
				</table>
				</form>
			</div>
			<div id="add_second_review_template_2" style="display:none">
				<form action = "product/secondReview/modifyChiefReview.action">
				<s:hidden id="entityId" name="entityId" />
				<table width="100%">
					<tr style="line-height: 50px;">
						<td width = "80" class="required-label">推荐等级：</td>
						<td  >
							<select name="rewardLevel" class="form form-control" style="width:auto;">
							<option value="">---请选择---</option>
							<option value="1">特等奖</option>
							<option value="2">一等奖</option>
							<option value="3">二等奖</option>
							<option value="4">三等奖</option>
							<option value="5">不推荐</option>
							</select>
						</td>
					</tr>
					<tr>
						<td width = "80" class="required-label">审核意见：</td>
						<td><textarea class="form-control input-sm validate[required]" name="reviewOpinion"></textarea></td>
					</tr>
				</table>
			  	</form>
			</div>
			<div id="review_template" style="display:none">
				<form action = "product/secondReview/audit.action">
				<s:hidden id="entityId" name="entityId" />
					<table width="100%">
						<tr style="line-height: 50px;">
							<td width = "80" class=""><label><input name="leaderAuditResult" type="radio" value = "2">&nbsp;&nbsp;<span class="label label-success label-custom">审核通过</span></label></td>
							<td width = "80" class=""><label><input name="leaderAuditResult" type="radio" value = "1">&nbsp;&nbsp;<span class="label label-danger label-custom">退回</span></label></td>
						</tr>
					</table>
				  </form>
			</div>
		</div>
		</div>
		<div class="row">
   		<%@ include file="/jsp/footer.jsp"%>
		<script>
		    seajs.use("js/product/secondReview/review.js", function(review) {
		    	review.init(); 
		    });
		</script>
	</body>
</html>