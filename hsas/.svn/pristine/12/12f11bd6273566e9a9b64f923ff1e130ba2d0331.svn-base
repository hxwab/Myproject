define(function(require, exports, module){
	require("js/optionTransferSelect.js");
	exports.init = function() {
		$("#confirm").unbind("click").click(function(){
			var roleIds = "";
			var dialog = top.dialog.get(window);
			var len = $("#rightselect option").length;
			$("#rightselect option").each(function (index) {
				roleIds += (index !== len-1)?($(this).val() + ";"):($(this).val());
			});
			dialog.close({"role":roleIds});
			dialog.remove();
		});
		$("#cancel").click(function(){
			top.dialog.get(window).close();
		});
	};
})