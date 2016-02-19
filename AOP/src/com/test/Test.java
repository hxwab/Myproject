package com.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.service.AccountServiceImp;
import com.service.IAccountService;

public class Test {

	public static void main(String[] args) {
		ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
		//IAccountService a=  (IAccountService) app.getBean("accountServiceImp");//ֻ��ǿ��ת��Ϊ�ӿ�
		IAccountService a=  (IAccountService) app.getBean("iAccountService");//ֻ��ǿ��ת��Ϊ�ӿ�
		a.save("hxw", "123456");
	}
}
