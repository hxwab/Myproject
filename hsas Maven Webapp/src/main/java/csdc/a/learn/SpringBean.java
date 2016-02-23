package csdc.a.learn;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

@Service
public class SpringBean implements ApplicationContextAware {
	
	public static ApplicationContext applicationContext ;
	
	public static Object getBean (String beanName) {
		return applicationContext.getBean(beanName);
	}
	
	//此处需要依赖注入
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		SpringBean.applicationContext =applicationContext;
		
	}

}
