package csdc.a.learn;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Component
@Scope(value="prototype")

public class ActionTest extends ActionSupport {
	
	
	public  void test(){
		
		
		Map req =(Map) ActionContext.getContext().get("request");
		Map application = ActionContext.getContext().getApplication();
		Map  session = ActionContext.getContext().getSession();
		
	}
	
	public void set(){
		
		 HttpServletRequest request =ServletActionContext.getRequest();
		 HttpSession session = request.getSession();
		 ServletContext application = ServletActionContext.getServletContext();
	}

}
