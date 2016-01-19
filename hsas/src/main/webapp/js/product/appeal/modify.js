define(function(require, exports, module){
	var nameSpace = "product/appeal";
	require("validation");
	require("validationInit");
	require("uploadify");
	require("uploadify-ext");
	require("form");
	
	/* 
	 * 初始化上传控件
	 */
	$("#newsAttachmentUpload").uploadifyExt({
		uploadLimitExt: '1', // 文件上传个数的限制
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
			$("#" + this.settings.id).parents("td").find(".validation-file-input").val(file.name).validationEngine('hide');
		}
	});
	
	/*
	 * 初始化表单验证
	 * */
	$("#form_appeal_modify").validationEngine({
		scroll: false,
		isOverflown:true,
		validateNonVisibleFields: true,
		promptPosition: 'centerRight:2, 2',
		addPromptClass: 'formError-noArrow formError-text'
	}).ajaxForm({
		type: "post",
		dataType: "json",
		success: function(data) {
			if (data.tag == "1") {
				dialog({
					title: '提示',
					content: '修改成功!',
					okValue: '确定',
					width: 260,
					ok: function() {
						window.location.href = nameSpace + "/toView.action?entityId="+$('#entityId').val();
					},
					onclose: function(){
						window.location.href = nameSpace + "/toView.action?entityId="+$('#entityId').val();
					}
				}).showModal();
			} else {
				dialog({
					title: '提示',
					content: '修改失败!',
					width: 260,
					okValue: '确定',
					ok: function() {
						window.location.reload();
					}
				}).showModal();
			}
		}
	});
})