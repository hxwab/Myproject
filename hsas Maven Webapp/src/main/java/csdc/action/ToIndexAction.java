package csdc.action;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import csdc.service.INewsService;
import csdc.tool.RandomNumUtil;
import csdc.tool.bean.LoginInfo;
import csdc.tool.info.GlobalInfo;
import csdc.tool.info.LogInfo;

/** 
* @Description: 主要用于跳转到系统首页时读取首页相关信息 
* @author wangming 
* @date 2015-3-12 
*/
@Component   
@Scope(value="prototype")
public class ToIndexAction extends ActionSupport {

	private static final long serialVersionUID = 5079312703841157332L;
	
	private ByteArrayInputStream inputStream; //验证码图片
	private int error;// 记录登录时的错误类别
	private Map jsonNews = new HashMap();
	private  LoginInfo loginer;
	
	@Autowired
	private INewsService iNewService;

	/**
	 * @Description: 首页跳转
	 * @return：notLoggedIn：未登入跳转至登入页面；loggedIn：已登入跳转至home页面
	 */
	public String toIndex() {
		init();
		Map session = ActionContext.getContext().getSession();
		loginer = (LoginInfo) session.get(GlobalInfo.LOGINER);
		if (loginer == null) {
			return "notLoggedIn";
		} else {
			return "loggedIn";
		}
	}

	/** 
	* @Description: 获取验证码 
	* @return inputStream：验证码图片流
	*/ 
	public String rand() {
		RandomNumUtil rdnu = null;
		rdnu = RandomNumUtil.Instance();
		// 取得带有随机字符串的图片
		this.setInputStream(rdnu.getImage());
		// 取得随机字符串放入HttpSession
		ActionContext.getContext().getSession().put("random", rdnu.getString());
		return SUCCESS;
	}
	
	
	/**
	 * 初始化首页的新闻界面
	 * @return
	 */
	public void init(){
		
		
		Map appSession = ActionContext.getContext().getApplication();
		
		//appSession.clear();
		appSession.put("news", iNewService.getArticles("news", 7));
		appSession.put("notice", iNewService.getArticles("notice", 7));
		appSession.put("status", iNewService.getArticles("status", 7));
		appSession.put("rules", iNewService.getArticles("rules", 7));
	}
	
	
	
	

	/**
	 * @Description: 进入超时访问页面
	 * @return
	 */
	public String timeout() {
			return SUCCESS;
	}

	public ByteArrayInputStream getInputStream() {
		return inputStream;
	}
	public void setInputStream(ByteArrayInputStream inputStream) {
		this.inputStream = inputStream;
	}
	public int getError() {
		return error;
	}
	public void setError(int error) {
		this.error = error;
	}
	
}