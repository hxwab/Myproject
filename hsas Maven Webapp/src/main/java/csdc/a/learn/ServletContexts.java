package csdc.a.learn;

import javax.servlet.ServletContext;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class ServletContexts {

	public static ServletContext sc;
	
	public static Object getBean (String beanName) {
		Object bean;
		WebApplicationContext wc = WebApplicationContextUtils.getWebApplicationContext(sc);
		bean = wc.getBean(beanName);
		return bean;
	}

}
