/*
 * @author:Liujia
 * @description: 权限添加/修改
 * @date:2015/09/01
 */
define(function(require, exports, module){
	var nameSpace = "portal/news";
	require("validation");
	require("validationInit");
	require("uploadify");
	require("uploadify-ext");
	require("form");
	require("ckeditor");
	$("#cancel").click(function(){
		window.history.go(-1);
	});
	exports.init = function(){
		var editor = CKEDITOR.replace('news_content',{
			language:"zh-CN"
		});
		$("#submit").click(function(){
			editor.updateElement();
			$("#form_news").submit();
		});
		$("#form_news").validationEngine({
			scroll: false,
			isOverflown:true,
			validateNonVisibleFields: true,
			promptPosition: 'centerRight',
			addPromptClass: 'formError-noArrow formError-text'
		}).ajaxForm({
			type: "post",
//			dataType: "json",
			beforeSubmit: function(){
				
			},
			success: function(data) {
				if (data.tag == "1") {
					dialog({
						title: '提示',
						content: '新闻添加成功!',
						okValue: '确定',
						width: 260,
						ok: function() {
							window.location.href = nameSpace + "/toView.action?entityId=" + data.entityId +
							"&type=" + $("select[name='type']").val();
						}
					}).showModal();
				} else {
					dialog({
						title: '提示',
						content: '新闻添加失败!',
						width: 260,
						okValue: '确定',
						ok: function() {
//							window.location.reload();
						}
					}).showModal();
				}
			}
		});
		
		/* 初始化上传控件 */
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
	}
});