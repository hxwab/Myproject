/**
 * @author liujia
 * @date 2015/10/12
 * @descrioption 统一弹出层注册
 */
define(function(require, exports, module){
	/* 机构选择弹出层*/
	$("body").on("click", ".select-agency", function(){
		var that = $(this).parent();
		var selected = $(".selected .selected-link",that);
		var inData = {};//定义弹出层返回值
		if(selected.length) {
			inData.name = $(".label", selected).text();
			inData.id = $(".label", selected).attr("id");
		} 
		dialog({
			title: '选择机构',
			url:"jsp/pop/popSelect/popSelectAgency.jsp",
			data: {
				selected:inData
			},
			onclose: function () {
				if(this.returnValue) {
					if(this.returnValue.name) {
						$(".selected", that).empty().append(generateLink(this.returnValue.name, this.returnValue.id));
						$("input[name*='agencyId']", that).val(this.returnValue.id);
						$("input[name*='agencyName']", that).val(this.returnValue.name);
					} else {
						$(".selected", that).empty();
						$("input[name*='agencyId']", that).val("");
						$("input[name*='agencyName']", that).val("");
					}
				} 
			}
		}).showModal();
		return false;
	});
	
	/* 删除已选择的机构 */
	$("body").on("click", ".selected-link .delete-link", function(){
		$(this).parent().remove();
		$("input[name*='agencyId']").val("");
	});	
	
	$("body").on("click", ".pop-view-agency",function(){
		dialog({
			title: '查看机构',
			url:"view/toViewAgency.action",
			data: {
				entityId: $(this).attr("href")
			}
		}).showModal();
		return false;
	});
	
	/* 查看人员信息 */
	$("body").on("click", ".pop-view-person",function(){
		dialog({
			title: '查看人员',
			url:"view/toViewPerson.action",
			data: {
				entityId: $(this).attr("href")
			}
		}).showModal();
		return false;
	});
	
	/* 审核弹层 */
	$("body").on("click", ".btn-first-audit",function(){
		dialog({
			title: '审核',
			url: "product/firstAudit/toAudit.action?entityId=" + $('#entityId').val(),
			onclose: function() {
				window.location.reload();
			}
		}).showModal();
		return false;
	});
	
	/* 审核弹层 */
	$("body").on("click", ".btn-final-audit",function(){
		dialog({
			title: '审核',
			url: "product/finalAudit/toAudit.action?entityId=" + $('#entityId').val(),
			onclose: function() {
				window.location.reload();
			}
		}).showModal();
		return false;
	});
	
	/**
	 * 提示成果已审核
	 */
	$("body").on("click", "#view_mod_unable",function(){
		dialog({
			title: '提示',
			content: "成果已提交，不可修改！"
		}).showModal();
		return false;
	});
	
	/**
	 * 上传材料说明
	 */
	$("body").on("click", "#upload_file_Manual",function(){
		dialog({
			title: '提示',
			content: 
				'<p class="red">1.审核材材料包括论文著作，格式pdf</p>' +
				'<p class="red">2.请将论文或者著作的相应文档放入一个文件夹，并打包成压缩包，格式为zip或rar</p>'
		}).showModal();
		return false;
	});
	
});