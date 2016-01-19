/*
 * author fengzheqi 
 * date 20151029
 * description 添加下载
 */

define(function(require, exports, module){
	var nameSpace = "portal/download";
	require("validation");
	require("validationInit");
	require("uploadify");
	require("uploadify-ext");
	require("form");
	
	$("#cancel").click(function(){
		window.history.go(-1);
	});
	
	$("#form_download").validationEngine({
		scroll: false,
		isOverflown:true,
		validateNonVisibleFields: true,
		promptPosition: 'centerRight',
		addPromptClass: 'formError-noArrow formError-text'
	}).ajaxForm({
		url: nameSpace + "/add.action",
		type: "post",
		success: function(data) {
			if (data.tag == "1") {
				dialog({
					title: '提示',
					content: '附件上传成功!',
					okValue: '确定',
					width: 260,
					ok: function() {
						window.location.href = basePath +nameSpace+ "/toList.action?type=article&update=1";
					}
				}).showModal();
			} else {
				dialog({
					title: '提示',
					content: '附件上传失败!',
					width: 260,
					okValue: '确定',
					ok: function() {
						window.location.reload();
					}
				}).showModal();
			}
		}
	});
	
	/* 初始化上传控件 */
	$("#downloadFileUpload").uploadifyExt({
		uploadLimitExt: '2', // 文件上传个数的限制
		fileSizeLimit: '100MB', // 文件上传大小的限制
		fileTypeDesc: '附件', // 可以不用管
		fileTypeExts: '*.rar;*.zip;*.doc;*.xls;*.pdf;*.*',// 限制为压缩文件上传格式
		buttonText : '上传附件', // 按钮上的文字
		buttonClass: "btn btn-default",
		hideButton : true,
		buttonImg: " ",
		preventCaching: false,
		multi: false,
		onUploadSuccess:function(file,data,response){
			$("#" + file.id).attr("fileId", $.parseJSON(data).fileId);
			$("#" + file.id + " div.uploadify-progress").remove();
			$("#" + this.settings.id).parents("div").find(".validation-file-input").val(file.name).validationEngine('hide');
		}
	});
})