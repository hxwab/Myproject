package csdc.action.system;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;
import com.opensymphony.xwork2.ActionContext;
import csdc.action.BaseAction;
import csdc.model.Account;
import csdc.model.Mail;
import csdc.model.Person;
import csdc.model.Role;
import csdc.service.IAccountService;
import csdc.tool.InputValidate;
import csdc.tool.bean.LoginInfo;
import csdc.tool.info.AccountInfo;
import csdc.tool.info.GlobalInfo;


@Component
@Scope(value="prototype")

public class AccountAction extends BaseAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1883015192752770930L;
	private static final String TMP_ACCOUNT_ID = "accountId";// 缓存与session中，备用的账号ID变量名称
	private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";// 列表时间格式
	private final static String PAGE_NAME = "accountListPage";
	private static final String PAGE_BUFFER_ID = "a.id";// 上下条查看时用于查找缓存的字段名称
	private static final String HQL = "select a.id,a.username ,a.person.name,a.agency.name,a.startDate ,a.expireDate,a.status,a.type from Account a where 1=1 ";// 上下条查看时用于查找缓存的字段名称HQL
	private final static String[] SORT_COLUMNS = new String[] {
		"a.username",
		"a.person.name",
		"a.agency.name",
		"a.startDate",
		"a.expireDate",
		"a.status",
		"a.type"
	};
	
	private Account account;// 账号对象
	private Date validity;// 有效期
	private String assignRoleIds;// 用于分配角色时，拼装账号ID的字符串
	private String roleIds;// 分配角色功能记录已分配角色的ID
	private String pwRetrieveCode;// 密码重置校验码
	private String newPassword, rePassword;// 管理者修改所辖账号密码
	private Date createDate1, createDate2, expireDate1, expireDate2;// 创建时间起始，有效期起始
	private int status;// 账号状态，用于高级检索
	private int type;
	private InputValidate inputValidate = new InputValidate();//校验工具类
	private String term;//自动补全的接收变量
	private Mail mail;
	private List<String[]> userList;// 登录可选的账号信息
	private int flag;
	
	private int expertType;
	private int reviewerType;//评审专家类型[1:初评专家 2:复评专家]
	private String agencyId;
	private Person person;
	
	private  List<Role> toAssignRole ; //未分配角色
	private  List<Role> assignedRole; //已分配角色

	
	@Autowired
	private IAccountService accountService;// 账号管理接口
	/*
	@Autowired
	private MailController mailController;*/
	/**
	 * 使用PROPAGATION_REQUIRES_NEW传播特性的编程式事务模板
	 */
	@Autowired
	private TransactionTemplate txTemplateRequiresNew;
	
	private String accountType;
	
	
	@Override
	public String view() {
		
		jsonMap.clear();
		 account = accountService.getAccountById(entityId);
		 List<String> accountId = new ArrayList<String>();
		 accountId.add(entityId);
		 List<Role>[] list = accountService.getAssignRole(loginer, accountId);
		 assignedRole = list[1];
		 
		 if(account==null){
			 jsonMap.put(GlobalInfo.ERROR_INFO, AccountInfo.ERROR_ACCOUNT_EXIST);
			 return INPUT;
			 
		 } else{
			 
			 jsonMap.put("account",account);
			 jsonMap.put("role", assignedRole);
				return SUCCESS;
		 }
	}

	@Override
	public String toView() {

		return SUCCESS;
		
	}

	
	
	@Override
	public String add() {
		
		if(account==null||person==null||"".equals(agencyId)||type>1||agencyId==null){
			jsonMap.put("tag", "2");
			return INPUT;
		}else if(accountService.checkAccountName(account.getUsername())){
			jsonMap.put("tag","3");
			return INPUT;
		}
		
		Map map = new HashMap();
		map.put("person", person);
		map.put("agencyId", agencyId);
		map.put("type", type);
		//accountService.addAccount(account, map);
		entityId = accountService.addAccount(account, map);
		jsonMap.put("tag", "1");
		jsonMap.put("entityId", entityId);
		return SUCCESS;
		
	}

	@Override
	public String toAdd() {
		
		return SUCCESS;
	}

	@Override
	public String delete() {
		
		accountService.delete(entityIds);
		
		return SUCCESS;
	}

	@Override
	public String modify() {
		
		account = dao.query(Account.class, entityId);
		return null;
	}

	@Override
	public String toModify() {
	
		return SUCCESS;
	}

	public String toEnable(){
		
		return SUCCESS;
	}
	
	public  String enable(){
		
		if(entityIds==null||entityIds.isEmpty()){
			jsonMap.put("tag", "2");
			return INPUT;
		}
		accountService.enable(entityIds, validity);
		
		jsonMap.put("tag","1");
		return SUCCESS;
	}
	
	
	
	public String disable(){
		
		/**
		 * 获取账号
		 * 判断账号是否在当前管理范围
		 * 决定是否停用
		 * 
		 */
		if(entityIds==null||entityIds.isEmpty()){
			jsonMap.put("tag", "2");
			return INPUT;
		}
		accountService.disable(entityIds);
		jsonMap.put("tag", "1");
		return SUCCESS;
	}
	
	
	
	public String toAssignRole(){
		loginer = (LoginInfo) ActionContext.getContext().getSession().get(GlobalInfo.LOGINER);
		String [] str = assignRoleIds.substring(0, assignRoleIds.length()).split(",");
		List<String> accountIds = Arrays.asList(str);
		
		List<Role>[] list = accountService.getAssignRole(loginer, accountIds);
		toAssignRole = list[0];
		assignedRole = list[1];
		return SUCCESS;
	}

	

	public String assignRole() {
		
		
		/*List<String> accountIds = new ArrayList<String>();
		List<String> roleIds = new ArrayList<String>();
		
		
		accountIds.add("test");
		accountIds.add("guest");
		accountIds.add("chbf");
		
		roleIds.add("4028d88e5044dff2015046be51370000");                                                                                                                                                                                                                                                                                                                  
		*/
		
		//String [] str = assignRoleIds.substring(0, assignRoleIds.length()).split(",");
		//List<String> accountIds = Arrays.asList(str);
		String[] ss = roleIds.split(";");
		List<String> toAssignRoleIds =Arrays.asList(ss);
		
		accountService.assignRole(entityIds, toAssignRoleIds);
		jsonMap.put("tag", "1");
		return SUCCESS;
		
	}

	/**
	 * 可考虑添加用户类型限制，每一级管理员只能看到其下属机构的账号
	 */
	@Override
	public Object[] simpleSearchCondition() {
		loginer = (LoginInfo) ActionContext.getContext().getSession().get(GlobalInfo.LOGINER);
		account = loginer.getAccount();
		keyword = (keyword == null) ? "" : keyword.toLowerCase().trim();// 预处理关键字
		StringBuffer hql = new StringBuffer();
		Map map = new HashMap();
		hql.append(HQL);
		
		if (keyword != null && !keyword.isEmpty()) {
			hql.append(" and ");
			if (searchType == 1) {
				hql.append("LOWER(a.username) like :keyword");
				map.put("keyword", "%" + keyword + "%");
			}else if (searchType == 2) {
				hql.append("LOWER(a.person.name) like :keyword");
				map.put("keyword", "%" + keyword + "%");
			}else if (searchType == 3) {
				hql.append("LOWER(a.agency.name) like :keyword");
				map.put("keyword", "%" + keyword + "%");
			} else {
				hql.append("LOWER(a.username) like :keyword or LOWER(a.person.name) like :keyword or LOWER(a.agency.name) like :keyword");
				map.put("keyword", "%" + (keyword == null ? "" : keyword.toLowerCase()) + "%");
			}
		} 
		
		if(account.getIsSuperUser()!=1){
			hql.append(" and a.isSuperUser='0'");
		}
		
		if(type ==0){
			hql.append(" and (a.type='1' or a.type = '2')");
		} else if(type==1){
			hql.append(" and a.type='3'");
		}else if(type==2){
			hql.append(" and (a.type='4' or a.type = '5' or a.type='6')");
		}else if(type==3){
			hql .append(" and a.type ='7'");
		}else {
			hql.append(" and a.type = ''");
		}
		
		
		
		// 调用初级检索功能
		return new Object[]{
			hql.toString(),
			map,
			0,
			null,
			null
		};
	}

	

	@Override
	public Object[] advSearchCondition() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Transactional
	public String addExpertAccount(){
		
//		List<String> list = new ArrayList<String>();
//		list.add("2");
//		list.add("3");
//		list.add("4028d82a50f031a00150f04e301d000b");
//		expertType=5;
		switch (reviewerType) {
		case 1:
			//查找所有初评专家id
			List<String> firstReviewerIds = accountService.getExpertIds(1);
			accountService.addAccounts(firstReviewerIds, 6);
			break;
		case 2:
			//查找所有复评普通专家id
			List<String> secondReviewerIds = accountService.getExpertIds(2);
			//查找所有复评组长id
			List<String> secondReviewerLeaderIds = accountService.getExpertIds(3);
			accountService.addAccounts(secondReviewerIds, 5);
			accountService.addAccounts(secondReviewerLeaderIds, 4);
			break;
		default:
			this.addFieldError(GlobalInfo.ERROR_INFO, "请求参数[reviewerType]错误");
			break;
		}
		jsonMap.put("tag", 1);
		return SUCCESS;
	}
	
	


	@Override
	public String pageName() {
		
		return this.PAGE_NAME;
	}

	@Override
	public String pageBufferId() {
		
		return this.PAGE_BUFFER_ID;
	}

	@Override
	public String[] sortColumn() {
		
		return this.SORT_COLUMNS;
	}

	@Override
	public String dateFormat() {
		
		return this.DATE_FORMAT;
	}


	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Date getValidity() {
		return validity;
	}

	public void setValidity(Date validity) {
		this.validity = validity;
	}

	public String getAssignRoleIds() {
		return assignRoleIds;
	}

	public void setAssignRoleIds(String assignRoleIds) {
		this.assignRoleIds = assignRoleIds;
	}

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

	public String getPwRetrieveCode() {
		return pwRetrieveCode;
	}

	public void setPwRetrieveCode(String pwRetrieveCode) {
		this.pwRetrieveCode = pwRetrieveCode;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getRePassword() {
		return rePassword;
	}

	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
	}

	

	public Date getCreateDate1() {
		return createDate1;
	}

	public void setCreateDate1(Date createDate1) {
		this.createDate1 = createDate1;
	}

	public Date getCreateDate2() {
		return createDate2;
	}

	public void setCreateDate2(Date createDate2) {
		this.createDate2 = createDate2;
	}

	public Date getExpireDate1() {
		return expireDate1;
	}

	public void setExpireDate1(Date expireDate1) {
		this.expireDate1 = expireDate1;
	}

	public Date getExpireDate2() {
		return expireDate2;
	}

	public void setExpireDate2(Date expireDate2) {
		this.expireDate2 = expireDate2;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public Mail getMail() {
		return mail;
	}

	public void setMail(Mail mail) {
		this.mail = mail;
	}

	public List<String[]> getUserList() {
		return userList;
	}

	public void setUserList(List<String[]> userList) {
		this.userList = userList;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public IAccountService getAccountService() {
		return accountService;
	}

	public void setAccountService(IAccountService accountService) {
		this.accountService = accountService;
	}

	/*public MailController getMailController() {
		return mailController;
	}

	public void setMailController(MailController mailController) {
		this.mailController = mailController;
	}*/

	public int getReviewerType() {
		return reviewerType;
	}

	public void setReviewerType(int reviewerType) {
		this.reviewerType = reviewerType;
	}

	public TransactionTemplate getTxTemplateRequiresNew() {
		return txTemplateRequiresNew;
	}

	public void setTxTemplateRequiresNew(TransactionTemplate txTemplateRequiresNew) {
		this.txTemplateRequiresNew = txTemplateRequiresNew;
	}

	public InputValidate getInputValidate() {
		return inputValidate;
	}

	public void setInputValidate(InputValidate inputValidate) {
		this.inputValidate = inputValidate;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public List<Role> getToAssignRole() {
		return toAssignRole;
	}

	public void setToAssignRole(List<Role> toAssignRole) {
		this.toAssignRole = toAssignRole;
	}

	public List<Role> getAssignedRole() {
		return assignedRole;
	}

	public void setAssignedRole(List<Role> assignedRole) {
		this.assignedRole = assignedRole;
	}

	
	public int getExpertType() {
		return expertType;
	}

	public void setExpertType(int expertType) {
		this.expertType = expertType;
	}

	public String getAgencyId() {
		return agencyId;
	}

	public void setAgencyId(String agencyId) {
		this.agencyId = agencyId;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	
	
}