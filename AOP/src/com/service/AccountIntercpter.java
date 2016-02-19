package com.service;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class AccountIntercpter implements MethodInterceptor {

	public Object invoke(MethodInvocation arg0) throws Throwable {
		
		 if (arg0.getMethod().getName().equals("save"))  
	            // ���ط����Ƿ���UserService�ӿڵ�printUser����  
	            {  
	                Object[] args = arg0.getArguments();// �����صĲ���  
	                System.out.println("user:" + args[0]);  
	                arg0.getArguments()[0] = "hello!";// �޸ı����صĲ���  
	  
	            }  
	  
	            System.out.println(arg0.getMethod().getName() + "---!");  
	            return arg0.proceed();// ����UserService�ӿڵ�printUser����  
		
	}

}
