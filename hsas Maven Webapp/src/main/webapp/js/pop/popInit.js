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
					} else {
						$(".selected", that).empty();
						$("input[name*='agencyId']", that).val("");
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
});