/*
 * @author fengzheqi
 * @date 2015/08/25
 * @description 个人信息
 */
define(function(require, exports, module){
	var view = require('view');
	var nameSpace = 'personInfo';
	
	exports.init = function() {
		view.show(nameSpace, showDetail);
	};

  function showDetail(results) {
    if(results.errorInfo == null || results.errorInfo == '') {
      $('#view_container_basicInfo').hide();
      $('#view_container_basicInfo').html(TrimPath.processDOMTemplate('view_template_basicInfo', results));
      $('#view_container_basicInfo').show();
      
      $('#view_container_product').hide();
      $('#view_container_product').html(TrimPath.processDOMTemplate('view_template_product', results));
      $('#view_container_product').show();
    } else {
      alert(results.errorInfo);
    }
    $.isLoading("hide");//关闭加载信息
  }
})