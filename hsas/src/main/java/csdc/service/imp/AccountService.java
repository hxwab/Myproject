package csdc.service.imp;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.Vector;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opensymphony.xwork2.ActionContext;

import sun.nio.cs.Surrogate;

import csdc.model.Account;
import csdc.model.AccountRole;
import csdc.model.Agency;
import csdc.model.Mail;
import csdc.model.Person;
import csdc.model.Role;
import csdc.service.IAccountService;
import csdc.tool.MD5;
import csdc.tool.bean.AccountType;
import csdc.tool.bean.LoginInfo;
import csdc.tool.info.GlobalInfo;
import csdc.tool.mail.Mailer;
/**
 * 账号管理，包括账号的增删查改 及相应权限等的查询操作
 * 
 * 批量添加账号操作时人员信息怎么搞（尤其是非空字段）
 * @author huangxw
 *
 */
@Service
@Transactional
public class AccountService extends BaseService implements IAccountService {
	
	public Account getAccountByName(String accountName) {
		Account account = (Account) dao.queryUnique("select acc from Account acc where acc.username =?", accountName);
		return account;
	}
	/**
	 * 获取当前通过认证的账号名称
	 */
	public String securityUsername() {
		// 获得当前通过认证的用户上下文信息
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		// 获得当前通过认证用户的账号名
		String username;
		if (principal instanceof UserDetails) {// 如果上下文信息为UserDails实例，则调用该接口的getUsername方法获取账号名
			username = ((UserDetails)principal).getUsername();
		} else {// 如果上下文信息不是UserDails实例，则它就是账号名
			username = principal.toString();
		}
		return username;
	}
	
	public String addAccount(Account account) {
		//待完善
		if (account.getType().equals("-1")) {
			account.setType(1);
		}
		account.setPassword(MD5.getMD5(account.getPassword()));
		account.setStatus(1);
		return dao.add(account).toString();
	}
	@Override
	public String modifyAccount(Account account, Account oldAccount) {
	
		if (!account.getIsSuperUser().equals(oldAccount.getIsSuperUser())) {
			oldAccount.setIsSuperUser(account.getIsSuperUser());
		}
		if (!account.getUsername().equals(oldAccount.getUsername())) {
			oldAccount.setUsername(account.getUsername());
		}
		if (!account.getType().equals(oldAccount.getType())) {
			oldAccount.setType(account.getType());
		}
		dao.modify(oldAccount);
		return oldAccount.getId();
	
	}
	
	
	@Override
	public void enable(List<String> ids, Date date) {
		
		Account account;
		for(String id :ids){
			
			account = dao.query(Account.class, id);
			account.setStatus(1); //设置为启用
			account.setExpireDate(date);//设置有效期
			dao.modify(account);
			
		}
	}
	
	@Override
	public void disable(List<String> ids) {
		// TODO Auto-generated method stub
		Account account;
		for(String id :ids) {
			
			account = dao.query(Account.class,id);
			account.setStatus(0);
			dao.modify(account);
			
		}
	}
	
	
	@Override
	public void delete(List<String> ids) {

		for(String id :ids){
			dao.delete(Account.class, id);
		
		}
	}
	
	/**
	 * 根据账号名获得账号权限
	 * @param name账号名
	 * @return 如果账号不存在，则返回null；
	 * 如果账号存在，则系统管理员账号返回所有权限；
	 * 其它账号，返回拥有角色所对应的权限。
	 * 返回结果均无重复项。
	 */
	@Override
	public List<String> getRightByAccountName(String accountName) {
		if (accountName == null || accountName.isEmpty()) {// 如果账号名为空，则直接返回null
			return null;
		} else {// 如果账号名非空，则先查找该账号对象，根据账号的类型读取相应的权限。
			Map map = new HashMap();
			map.put("name", accountName);
			Account account = (Account) dao.query("from Account ac where ac.username = :name", map).get(0);
			map.remove("name");
			
			if (account == null) {// 账号不存在，直接返回空
				return null;
			} else {// 账号存在，则根据账号类型查询相应的权限
				List<String> re;// 权限集合
				if (account.getIsSuperUser() == 1 ) {// 系统管理员读取所有权限
					re = dao.query("select distinct r.code from Right r");
				} /*else if (account.getType()==2||account.getType()==3 ||account.getType()==6) {// 测试
					re = dao.query("select distinct r.code from Right r");
				} */
				else {// 其它账号，根据账号、角色、权限关联关系，读取自己拥有的权限
					map.put("accountId", account.getId());
					re = dao.query("select distinct r.code from Right r, RoleRight rr, AccountRole ar where ar.account.id = :accountId and ar.role.id = rr.role.id and rr.right.id = r.id", map);
				}
				return re;
			}
		}
	}
	
	
	@Override
	public Account getAccountById(String accountId) {

		if(accountId==null ||accountId.equals("")){
			
			return null;
		} else {
			
			return dao.query(Account.class, accountId);
		}
	}
	
	
	@Override
	public List<String> getRightByAccountId(String accountId) {
		
		if(accountId==null||accountId.equals("")){
			return null;
		}else {
			Map map = new HashMap();
			Account account = dao.query(Account.class, accountId);
			List<String> list;
			if(account.getType().equals(AccountType.SUPERADMIN)){
				list = dao.query("select r.code from Right r");
			}else {
				
				map.put("accountId", account.getId());
				list = dao.query("select distinct r.code from Right ,RoleRight rr,AccountRole ar where ar.account.id = :accountId and ar.role.id=rr.role.id and rr.right.id = r.id",map );
			}
			
			return list;
		}
		
	}
	
	
	
	
	/**
	 * 批量操作和单个操作
	 * 
	 */
	@Override
	public List<Role>[] getAssignRole(LoginInfo loginer, List<String> accountIds) {
		
		List<Role> toAssignRole;//当前管理账户有权分配的角色
		List<Role> assignedRole;//待分配账户已分配的角色
		List<Role> commonRoles = new ArrayList<Role>();//列表页面选择多个账号时，选择他们已经分配账号的集合
		Account adminAccount = loginer.getAccount();
		
		//String hql = "select r from AccountRole ar left join ar.role r where ar.account.id = :accountId";
		String HQL = "select r from Role r";
		StringBuffer hql = new StringBuffer();
		hql.append(HQL);
		
		//非admin需要过滤掉专家的角色和admin
		if(adminAccount.getIsSuperUser()!=1){
			hql.append(" where r.id NOT IN('4028d88e50d64e4d0150d65399930001','4028d88e50d64e4d0150d65492990003','4028d88e514146f90151415d10cd0005','4028d88e50d64e4d0150d658aa2f0008') order by r.id");
		}
		
		Map map = new HashMap();
		//map.put("accountId", adminAccount.getId());
		toAssignRole = dao.query(hql.toString());
		map.clear();
		
		int size = accountIds.size();
		//单个操作，找出待分配权限账号的已有权限
		if(size==1){
			
			map.put("accountId", accountIds.get(0));
			assignedRole = dao.query("select r from AccountRole ar left join ar.role r where ar.account.id = :accountId", map);
			map.clear();
			//从全集中除去已分配权限得到未分配的权限
			for(Role role : assignedRole){
				
				if(toAssignRole.contains(role))
					toAssignRole.remove(role);
			}
			
			return  new List[] {toAssignRole ,assignedRole};
			
		} else if(size>1){
			
			Map<Role, Integer> roleMap = new HashMap<Role, Integer>();
			//批量操作,找出账号已分配角色的交集
			for(String accountId : accountIds ){
				
				map.put("accountId", accountId);
				assignedRole = dao.query("select r from AccountRole ar left join ar.role r where ar.account.id = :accountId", map);
				for(Role role :assignedRole){
					if(!roleMap.containsKey(role)){
						roleMap.put(role, 1);
					}else {
						roleMap.put(role, roleMap.get(role)+1);
					}
				}
			}
			
			for(Map.Entry<Role, Integer> entry : roleMap.entrySet()){
				
				if(entry.getValue()==size){
					commonRoles.add(entry.getKey());
					toAssignRole.remove(entry.getKey());
				}
				
			}
			
			return new List[] {toAssignRole,commonRoles};
			
		}
		
		return null;
		
	}
	
	
		
		//如果该账户已分配过该角色，则不重新分配
	@Override
	public void assignRole(List<String> accountIds, List<String> roleIds) {
		
		
			for(String accountId :accountIds){
				
				Account  account;
				Role role;
				List<String> roleAccountId ;// 账号角色对应关系ID
				
				account = dao.query(Account.class,accountId);
				Map map =new HashMap();
				map.put("accountId", accountId);
				roleAccountId = dao.query("select r.id from AccountRole ar left join ar.role r where ar.account.id = :accountId",map);
				
				
				
				
				//如果是批量添加，则该账号原有角色不变，只增加新添角色
				if(!roleIds.isEmpty()){
					
					for(String roleId :roleIds){
						
						if(!roleAccountId.contains(roleId)){

							role = dao.query(Role.class, roleId);
							AccountRole accountRole = new AccountRole();
							accountRole.setAccount(account);
							accountRole.setRole(role);
							dao.add(accountRole);
							
						}else {
							roleAccountId.remove(roleId);//原来剩余的角色
						}
					}
				}
				
				//如果是单个添加，则原来剩余的角色要清除
				if(accountIds.size()==1){
					
					if(roleAccountId.size()>0){
						for(String roleId :roleAccountId){
							map.put("roleId", roleId);
							AccountRole accountRole = (AccountRole) dao.queryUnique("select ar from AccountRole ar left join ar.role r where ar.account.id = :accountId and r.id= :roleId",map);

							dao.delete(accountRole);
						}
					}
					
				}
				
			}
	}




	@Override
	public String getRoleName(String accountId) {
		
		return null;
	}
	
	
	@Override
	public boolean checkAccountName(String accountName) {

		if (accountName == null || accountName.isEmpty()) {// 如果账号名为空，则视为已存在
			return true;
		} else {// 如果账号名非空，则查询数据库，判断此账号名是否存在
			Map map = new HashMap();
			map.put("accountName", accountName);
			List<String> re = dao.query("select a.id from Account a  where a.username = :accountName", map);// 获取指定名称的账号
			return !re.isEmpty();
		}
	}
	@Override
	public Map viewAccount(Account account, Map jsonMap, LoginInfo loginer) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 添加账号
	 */
	@Override
	public String addAccount(Account account, Map map) {
		Person person = (Person) map.get("person");
		Agency agency = dao.query(Agency.class,map.get("agencyId").toString());
		int type = (Integer) map.get("type");
		person.setAgency(agency);
		person.setAgencyName(agency.getName());
		//person.setEmail(account.getMail());
		
		String personId = addPerson(person);
		Person p = dao.query(Person.class, personId);
		account.setPerson(p);
		account.setAgency(agency);
		
		
		if(type==0){
			type=2;
		}else if(type==1){
			type=3;
		}
		//Type要区分
		account.setType(type);
		account.setStatus(1);
		account.setStartDate(new Date());
		//account.setExpireDate(new Date(117, 10, 25, 23, 59, 59));
		account.setApproveTime(new Date());
		account.setPassword(MD5.getMD5("123456"));
		account.setCreateType(0);
		account.setIsSuperUser(0);
		account.setMail(person.getEmail());
		 String id=dao.add(account);
		 
		account = getAccountById(id);
		AccountRole accountRole = new AccountRole();
		Role role = getRole(parseAccountType(type));
		accountRole.setAccount(account);
		accountRole.setRole(role);
		dao.add(accountRole);
		return id;
		
	}
	
	
	/**
	 * 批量添加账号（账号名为expert+6位数字，初始密码为666666）
	 * 前台传personId以及账号类型
	 * 
	 */
	@Override
	public List<Person> addAccounts(List<String> entityIds, int accountType) {
		
		int currentYear = new Date().getYear()-100;
		List<Person> failAccount= new ArrayList<Person>();
		Person person;
		Account account;
		Account admin =( (LoginInfo)(ActionContext.getContext().getSession().get(GlobalInfo.LOGINER)) ).getAccount() ;
		MailService mailService ;
		Random random = new Random();
		int randomNumber=0;
		Set<Integer> randoms = new HashSet<Integer>();
		HashMap map = new HashMap();
		
		for(String entityId :entityIds){
			//person = dao.query(Person.class, personId);
			map.put("id",entityId);
			person = (Person) dao.queryUnique("select p from Expert e left join e.person p where e.id=:id", map);
			map.clear();
			StringBuffer username =null;
			
			//生成账号后六位
			boolean isUsed = false;
			  while(!isUsed){
				  randomNumber=	random.nextInt(1000000);
				  
				  if(randoms.add(randomNumber)){
					  username = new StringBuffer();
					  username.append(currentYear).append("expert").append(randomNumber);
					  if(!checkAccountName(username.toString())){
						  isUsed=true;
					  }
				  }
			  }
			  
			account = new Account();
			
			account.setUsername(username.toString());
			account.setPassword(MD5.getMD5("666666"));
			account.setType(accountType);
			account.setStatus(1);
			account.setStartDate(new Date());
			account.setApproveTime(new Date());
			account.setExpireDate(new Date(currentYear+101, 11, 30, 23, 59, 59));
			account.setCreateType(0);
			account.setAgency(person.getAgency());
			account.setPerson(person);
			account.setMail(person.getEmail());
			account.setIsSuperUser(0);
			String id =dao.add(account);
			
			account = getAccountById(id);
			AccountRole accountRole = new AccountRole();
			Role role = getRole(parseAccountType(accountType));
			accountRole.setAccount(account);
			accountRole.setRole(role);
			dao.add(accountRole);
			
			
			Mail mail =  new Mail();
			mail.setAccountId(id);
			mail.setSendTo(person.getEmail());
			mail.setSubject("湖北省社科联系统专家账号分配邮件");
			mail.setBody("您好！您已被选为湖北省社科联系统"+parseAccountType(accountType) +"账号名为："+account.getUsername()+"初始密码为666666。请及时修改密码！");
			mail.setCreateDate(new Date());
			mail.setSendTimes(0);
			mail.setIsHtml(1);
			mailService = new MailService();	
			
			/*try {
				mailService.addMail(mail);
			} catch (Exception e) {
				failAccount.add(person);
				e.printStackTrace();
			}*/
			
			dao.add(mail);
			}
		
		return failAccount;
	}
	
	private String addPerson(Person person) {
		
		StringBuffer str ;
		Map map =new HashMap();
		map.put("name",person.getName());
		map.put("birthday", person.getBirthday());
		map.put("agencyName", person.getAgencyName());
		String hql ="select p from Person p where p.name = :name and p.birthday = :birthday and p.agencyName= :agencyName";
		Person p =(Person) dao.queryUnique(hql, map);
		
		if(p==null){
			person.setCreateDate(new Date());
			return dao.add(person);
		} else {
			
			if(person.getMobilePhone()!=null){
				if(p.getMobilePhone()!=null&&!p.getMobilePhone().equals(person.getMobilePhone())){
					str = new StringBuffer();
					str.append(person.getMobilePhone()+";").append(p.getMobilePhone());
					p.setMobilePhone(str.toString());
				} else {
					p.setMobilePhone(person.getMobilePhone());
				}
			}
			
			if(person.getEmail()!=null){
				if(p.getEmail()!=null && !p.getEmail().equals(person.getEmail())){
					str = new StringBuffer();
					str.append(person.getEmail()+";").append(p.getEmail());
					p.setEmail(str.toString());
				} else {
					p.setEmail(person.getEmail());
				}
			}
			
			p.setUpdateDate(new Date());
			
			 dao.modify(p);
			 return p.getId();
			
		}
		
	}
	
	private String parseAccountType(int type){
		String AccountType="";
		
		switch (type) {
		case 2:
			AccountType = "一般管理员";
			break;
		case 3:
			AccountType = "高校管理员";
			break;
		case 4:
			AccountType = "复评专家组长";
			break;
		case 5:
			AccountType = "复评专家";
			break;
		case 6:
			AccountType = "初评专家";
			break;

		default:
			break;
		}
		
		return AccountType;
		
	}
	
	private Role getRole(String roleName){
		HashMap map = new HashMap();
		map.put("name", roleName);
		return (Role) dao.queryUnique("select r from Role r where r.name =:name ",map);
	}
	@Override
	public List<String> getExpertIds(int type) {
		StringBuffer hql = new StringBuffer();
		hql.append("select e.id from Expert e where e.isRecommend=1 and e.isReviewer=1");
		switch (type) {
		case 1:
			hql.append(" and e.reviewerType=0 and not exists (from Account ac where ac.person.id=e.person.id and ac.type=6 and ac.status=1)");
			break;
		case 2:
			hql.append(" and e.reviewerType=1 and e.isGroupLeader=0 and not exists (from Account ac where ac.person.id=e.person.id and ac.type=5 and ac.status=1)");
			break;
		case 3:
			hql.append(" and e.reviewerType=1 and e.isGroupLeader=1 and not exists (from Account ac where ac.person.id=e.person.id and ac.type=4 and ac.status=1)");
			break;			
		default:
			break;
		}
		List<String> expertIds= dao.query(hql.toString());
		return expertIds;
	}
	
}
