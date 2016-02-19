package com.service;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class AccountIntercpter implements MethodInterceptor {

	public Object invoke(MethodInvocation arg0) throws Throwable {
		
		 if (arg0.getMethod().getName().equals("save"))  
	            // 拦截方法是否是UserService接口的printUser方法  
	            {  
	                Object[] args = arg0.getArguments();// 被拦截的参数  
	                System.out.println("user:" + args[0]);  
	                arg0.getArguments()[0] = "hello!";// 修改被拦截的参数  
	  
	            }  
	  
	            System.out.println(arg0.getMethod().getName() + "---!");  
	            return arg0.proceed();// 运行UserService接口的printUser方法  
		
	}

}
