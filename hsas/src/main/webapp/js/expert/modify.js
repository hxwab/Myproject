/*
 * @author fengzheqi
 * @date 2015/09/22
 * @description 专家添加
 */

define(function(require, exports, module){
	require('step');
	require('datePicker');
	require('validation');
	require('validationInit');
	require('form');
	require("js/pop/popInit.js");
	
	var nameSpace = 'expert';
	
	$("#form_modify_expert").validationEngine({
		scroll: false,
		isOverflown:true,
		validateNonVisibleFields: true,
		promptPosition: 'centerRight',
		addPromptClass: 'formError-noArrow formError-text'
	}).ajaxForm({
		type: "post",
		success: function(data) {
			var entityId = data.entityId;
			if (data.tag == "1") {
				dialog({
					title: '提示',
					content: '修改成功!',
					okValue: '确定',
					width: 260,
					ok: function() {
						window.location.href = nameSpace + "/toView.action?entityId="+ entityId;
					}
				}).showModal();
			} else {
				dialog({
					title: '提示',
					content: '添加失败!',
					width: 260,
					okValue: '确定',
					ok: function() {
						window.location.reload();
					}
				}).showModal();
			}
		}
	});
	
	/*初始化dataPicker */
	$('#form_modify_expert .datePicker').each(function(){
		$(this).datepicker({
			language: "zh-CN",
		    autoclose: true,
		    todayHighlight: true,
		    format: "yyyy-mm-dd",
		    defaultViewDate: {year: '1975', month: '0', day: '1'}
		}).on("hide", function(e) {
			$(e.currentTarget).validationEngine("validate");
	    });
	});
	
	var basic_info_setting = new Setting({
		id: "basic_info",
		buttons: ['next', 'retry', 'cancel'],
		out_check: function() {
			var validationResult = $.makeArray($("#basic_info input, #basic_info select, #basic_info textarea").map(function(previousValue, currentValue, index, array) {
				return ($(currentValue).validationEngine('validate'));
			}));
			
			return ! validationResult.reduce(function(previousValue, currentValue, index, array) {
				  return previousValue || currentValue;
			});
		}
	});
	
	var expert_info_setting = new Setting({
		id: "expert_info",
		buttons: ['prev', 'next', 'retry', 'finish', 'cancel'],
		out_check: function() {
			var validationResult = $.makeArray($("#expert_info input, #expert_info select, #expert_info textarea").map(function(previousValue, currentValue, index, array) {
				return ($(currentValue).validationEngine('validate'));
			}));
			
			return ! validationResult.reduce(function(previousValue, currentValue, index, array) {
				  return previousValue || currentValue;
			});
		}
	});
	
	step_controller = new Step_controller();
	
	step_controller.after_move_opt = function(){
		var flag = false;
		for (step in step_controller.steps){
			var $curLi = $("li[name=" + step_controller.steps[step].id + "]");
			if (step_controller.steps[step].id == step_controller.state){
				flag = true;
				$curLi.attr("class", "proc step_d");
			} else if (!flag){
				$curLi.attr("class", "proc step_e");
			} else {
				$curLi.attr("class", "proc step_f");
			}
		}
	};
	
	step_controller.add_step(basic_info_setting);
	step_controller.add_step(expert_info_setting);
	step_controller.init();
	
///////////////////分割线//////////////////////
	
	
	$("#prev").click(function(){
		step_controller.prev();
	});

	$("#next").click(function(){
		step_controller.next();
	});

	$("#confirm").click(function(){
		step_controller.next();
	});

	$("#retry").click(function(){
		location.reload();
	});

	$("#cancel").click(function(){
		history.go(-1);
	});

	$("#finish").click(function(){
		step_controller.submit();
	});

	$("#info").show();
})