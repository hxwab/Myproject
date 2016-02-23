package csdc.a.learn;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class Applicationar {
	
   public static WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();    
   public  static ServletContext servletContext = webApplicationContext.getServletContext();  

   public WebApplicationContext web = WebApplicationContextUtils.getWebApplicationContext(servletContext);
   
    public static Object getBean (String beanName) {
		Object bean;
		bean =webApplicationContext.getBean(beanName);
		return bean;
	}
	
}
