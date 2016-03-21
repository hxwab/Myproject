<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>湖北省社会科学优秀成果奖申报评审系统</title>
        <%@ include file="/jsp/base.jsp"%>
    </head>
    <body>
    		<%@ include file="/jsp/innerNav.jsp"%>
			<div class="row">
				<div class="col-xs-12">
					<div class="row">
						<div class="col-xs-6 ">
							<div class="myPanel myPanel-notice">
								<div class="panel-heading">
									<h3 class="panel-title"><i class="fa fa-book fa-2"></i>&nbsp;社科动态
										<a href="portal/news/toList.action?type=news&update=1" title='更多' class="pull-right">更多>></a>
									</h3>
								</div>
								<div class="panel-body myNews">
								</div>
							</div>
						</div>
						<div class="col-xs-6">
							<div class="myPanel myPanel-notice">
								<div class="panel-heading">
									<h3 class="panel-title"><i class="fa fa-newspaper-o fa-2"></i>&nbsp;通知公告
										<a href="portal/news/toList.action?type=notice&update=1" title='更多' class="pull-right">更多>></a>
									</h3>
								</div>
									<div class="panel-body myNotice">
								</div>
							</div>
						</div>
						<div class="col-xs-6">
							
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-12">
					<div class="row">
						<div class="col-xs-6 ">
							<div class="myPanel myPanel-notice">
								<div class="panel-heading">
									<h3 class="panel-title"><i class="fa fa-file-text fa-2"></i>&nbsp;政策文件
										<a href="portal/news/toList.action?type=status&update=1" title='更多' class="pull-right">更多>></a>
									</h3>
								</div>
								<div class="panel-body myStatus">
								</div>
							</div>
						</div>
						<div class=" col-xs-6 ">
							<div class = "myPanel myPanel-news">
								<div class="panel-heading">
									<h3 class="panel-title"><i class="fa fa-question-circle fa-2"></i>&nbsp;注意事项
										<a href="portal/news/toList.action?type=rules&update=1" title='更多' class="pull-right">更多>></a>
									</h3>
								</div>
								<div class="panel-body myRules">
								</div>
							</div>
						</div>
					</div>
			    </div>
			
			<%@ include file="/jsp/footer.jsp"%>
		<script>
			var json_news =${applicationScope.news};
			var json_notice = ${applicationScope.notice};
			var json_status = ${applicationScope.status};
			var json_rules = ${applicationScope.rules};
		</script>
		<script>
		    seajs.use("js/index.js", function(index) {
		         index.init(); 
		    });
		</script>
	</body>
</html>