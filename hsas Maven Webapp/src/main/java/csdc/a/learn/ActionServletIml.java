package csdc.a.learn;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;
@Component
@Scope(value="prototype")
public class ActionServletIml extends ActionSupport implements ServletRequestAware {
	private HttpServletRequest request;
	private HttpSession session;
	private ServletContext application;
	
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;
		this.session=request.getSession();
		this.application=request.getSession().getServletContext();
		
	}

}
