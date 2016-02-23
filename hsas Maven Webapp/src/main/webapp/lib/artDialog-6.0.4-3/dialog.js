window.dialog = function(args, ok, cancel){
	var defaults = {
			data: {
				InitSize:function(){
					var pop = dialog.getCurrent();
					// 重新计算iframe高度和宽度
					pop.height($(pop.content().iframeNode.contentDocument)[0].body.scrollHeight);
					pop.width($(pop.content().iframeNode.contentDocument)[0].body.scrollWidth);
				},
				tweakSize: function(){
					var pop = dialog.getCurrent();
					// 重新计算iframe高度和宽度
					pop.height($("body",pop.content().iframeNode.contentDocument).height());
					pop.width($("body",pop.content().iframeNode.contentDocument).width());
				}
			}
	};
	if(! args.data) args.data = {};
	$.extend(args.data, args.data);
	return window._dialog(args, ok, cancel);
}