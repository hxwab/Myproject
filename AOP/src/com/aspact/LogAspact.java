package com.aspact;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * aop����֯�뷨
 * @author csdc
 *
 */
public class LogAspact {
	
	public void before(JoinPoint call){
		String className = call.getClass().getName();
		String classNames =  call.getTarget().getClass().getName();
		String methodName =call.getSignature().getName();
		System.out.println("ǰ��֪ͨ"+classNames+"���"+methodName+"������ʼ��");
		
		if(methodName.equals("save")){
			
		}
		call.getSignature();
		call.getThis();
		call.getSourceLocation();
		System.out.println(call.getThis().getClass().getName()+"*"+
				call.getArgs()+call.getSignature().getName()
		
				);
	}
	
	public void afterReturn() {       
		System.out.println("����֪ͨ:��������������");       
	}   
	
	public void after(){
		  System.out.println("����֪ͨ:���ܷ�����û������ִ����ɣ�һ���᷵�ص�");     
	}
	
	public void afterThrowing() {      
		System.out.println("�쳣�׳���֪ͨ:����ִ��ʱ���쳣��");     
	} 
	
    //����������֪ͨ�ķ������Ե�һ����������Ϊorg.aspectj.lang.ProceedingJoinPoint����    
	public Object doAround(ProceedingJoinPoint call) throws Throwable {   
		Object result = null;           
		this.before(call);//�൱��ǰ��֪ͨ          
		try {               
			result = call.proceed();    
			this.afterReturn(); //�൱�ں���֪ͨ      
			} catch (Throwable e) {     
				this.afterThrowing();  //�൱���쳣�׳���֪ͨ             
				throw e;           
				}finally{            
					this.after();  
					//�൱������֪ͨ          
					}            
		return result;       
		}     

}
