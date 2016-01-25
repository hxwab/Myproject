/*
 * 编辑人员详情
 * author：fengzheqi
 * */
$(function(){
	/*
	 * 填充人员详细信息
	 * */
	var data = thisPopLayer.inData;
	$(":input[name='memberType']").val(data.memberType);
	$(":input[name='memberName']").val(data.memberName);
	$(":input[name='gender']").val(data.gender);
	$(":input[name='birthday']").val(data.birthday);
	$(":input[name='idcardType']").val(data.idcardType);
	$(":input[name='idcardNumber']").val(data.idcardNumber);
	$(":input[name='foreign']").val(data.foreign);
	$(":input[name='education']").val(data.education);
	$(":input[name='degree']").val(data.degree);
	$(":input[name='title']").val(data.title);
	$(":input[name='job']").val(data.job);
	$(":input[name='email']").val(data.email);
	$(":input[name='mobile']").val(data.mobile);
	$(":input[name='phone']").val(data.phone);
	$(":input[name='postcode']").val(data.postcode);
	$(":input[name='address']").val(data.postcode);
	$(":input[name='agencyName']").val(data.agencyName);
	$(":input[name='divisionName']").val(data.divisionName);
	$(":input[name='isDirector']").val(data.isDirector);
	
	/*
	 * 点击确定后，将人员信息返回给父页面
	 * */
	$(".confirm").click(function(){
		thisPopLayer.outData = {
			memberType: $(":input[name='memberType']").val(),//成员类型:1教师;2专家;3学生
			memberName: $(":input[name='memberName']").val(),//成员姓名
			gender: $(":input[name='gender']").val(),//性别
			birthday: $(":input[name='birthday']").val(), //生日
			idcardType: $(":input[name='idcardType']").val(),// 证件类型
			idcardNumber: $(":input[name='idcardNumber']").val(),// 证件号
			foreign: $(":input[name='foreign']").val(),//外语
			education: $(":input[name='education']").val(),//最后学历
			degree: $(":input[name='degree']").val(),//学位
			title: $(":input[name='title']").val(),//职称
			agencyName: $(":input[name='agencyName']").val(),//单位名称
			divisionName: $(":input[name='divisionName']").val(),//部门名称
			job: $(":input[name='job']").val(),//职务
			email: $(":input[name='email']").val(),//邮箱
			mobile: $(":input[name='mobile']").val(),//移动电话
			phone: $(":input[name='phone']").val(),//固定电话
			postcode: $(":input[name='postcode']").val(),//邮编
			address: $(":input[name='address']").val(),//地址
			isDirector: $(":input[name='isDirector']").val(),//是否负责人:1是；0否
		}
		thisPopLayer.callBack(thisPopLayer.outData);
		thisPopLayer.destroy();
	});
})