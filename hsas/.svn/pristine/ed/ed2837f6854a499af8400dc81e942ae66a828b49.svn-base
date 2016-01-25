package csdc.action;

import java.util.Date;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import csdc.dao.IHibernateBaseDao;
import csdc.model.Account;
import csdc.service.IAccountService;
import csdc.service.INewsService;
import csdc.tool.bean.LoginInfo;
import csdc.tool.info.GlobalInfo;


/** 
* @Description: 通过security认证后，进入系统登录后首页之前，将用户信息存入session、初始化首页相关信息。 
* @author wangming 
* @date 2015-3-12 
*/
@Component   
@Scope(value="prototype")
public class LoginAction extends ActionSupport {

	private static final long serialVersionUID = 1430425486077928672L;
	
	@Autowired
	protected IHibernateBaseDao dao;
	
	@Autowired
	private INewsService iNewsService;
	@Autowired
	private IAccountService accountService;
	private String username;// 账号用户名

	/** 
	* @Description: 通过spring security认证后，跳转至此接口，初始化信息，记录登入信息。 
	* @return
	*/
	@PreAuthorize("hasRole('ROLE_USER')")
	@Transactional
	public String doLogin() {
		System.out.println("进入doLogin");
		init();
		LoginInfo loginer = new LoginInfo();
		username = accountService.securityUsername();
		Account account = accountService.getAccountByName(username);
		account.setLastLoginDate(new Date());
		Date currenttime = new Date();
		if (!currenttime.before(account.getExpireDate())) {
			account.setStatus(0);
		}
		dao.modify(account);
		loginer.setAccount(account);
		loginer.setIsSuperUser(account.getIsSuperUser());
		ActionContext.getContext().getSession().put(GlobalInfo.LOGINER, loginer);
		String username = loginer.getAccount().getUsername();
		String name = loginer.getAccount().getPerson().getName();
		ActionContext.getContext().getSession().put("username", username);
		ActionContext.getContext().getSession().put("name", name);
		String type = "";
		switch (loginer.getAccount().getType()) {
			case 1: type="高级管理员";break;
			case 2: type="一般管理员";break;
			case 3: type="高校管理员";break;
			case 4: type="复评专家组长";break;
			case 5: type="复评专家";break;
			case 6: type="初评专家";break;
			case 7: type="申请人";break;
			default:type="未分配";break;
		}
		String status = (loginer.getAccount().getStatus()==1) ? "已启用" : "已停用";
		ActionContext.getContext().getSession().put("type", type);
		ActionContext.getContext().getSession().put("status", status);
		
		return SUCCESS;
	}
	
	/**
	 * 初始化首页的新闻界面
	 * @return
	 */
		public void init(){
			
			
			Map appSession = ActionContext.getContext().getApplication();
			
			//appSession.clear();
			appSession.put("news", iNewsService.getArticles("news", 7));
			appSession.put("notice", iNewsService.getArticles("notice", 7));
			appSession.put("status", iNewsService.getArticles("status", 7));
			appSession.put("rules", iNewsService.getArticles("rules", 7));
		}
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public IAccountService getAccountService() {
		return accountService;
	}
	public void setAccountService(IAccountService accountService) {
		this.accountService = accountService;
	}

}