package csdc.service.imp;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import csdc.model.Account;
import csdc.model.AccountRole;
import csdc.model.Agency;
import csdc.model.Person;
import csdc.model.Role;
import csdc.service.IRegisterService;
import csdc.tool.MD5;

@Service
@Transactional

public class RegisterService extends BaseService implements IRegisterService {

	
	//此处还需完善，要考虑申请人信息已经有的情况
	@Override
	public String addPerson(Person person) {
		
		
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
				if(p.getEmail()!=null&&!p.getEmail().equals(person.getEmail())){
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

	
	/**
	 * 默认情况下账户类型为停用、申请人、用户注册
	 * 
	 * 增加限制，一个邮箱只能申请一个账户
	 */
	@Override
	public String addAccount(Account account, Person person,Map map) {
		
		//先判断该人员是否在库中已经存在,若人员已经存在则进行更新
		Agency agency = dao.query(Agency.class, map.get("agencyId").toString());
		person.setAgency(agency);
		person.setAgencyName(agency.getName());
		person.setEmail(account.getMail());
		
		String personId = addPerson(person);
		
		
	/*	Agency agency = dao.query(Agency.class, map.get("agencyId").toString());
		
		String personId =map.get("personId").toString();*/
		Person p = dao.query(Person.class, personId);
		int currentYear = new Date().getYear();
		account.setPerson(p);
		account.setAgency(agency);
		account.setType(7);
		account.setStatus(1);
		account.setStartDate(new Date());
		account.setExpireDate(new Date( currentYear+1, 11, 30, 23, 59, 59));
		account.setCreateType(1);
		account.setIsSuperUser(0);
		String id =dao.add(account);
		
		account = dao.query(Account.class, id);
		AccountRole accountRole = new AccountRole();
		Role role = getRole("申报人");
		accountRole.setAccount(account);
		accountRole.setRole(role);
		dao.add(accountRole);
		return  id;
		
		
	}

	
	private Role getRole(String roleName){
		HashMap map = new HashMap();
		map.put("name", roleName);
		return (Role) dao.queryUnique("select r from Role r where r.name =:name ",map);
	}
	
	@Override
	public Agency getAgency(String agencyName) {
		Map map = new HashMap();
		map.put("agencyName", agencyName);
		String hql = "select a from Agency a where a.name = : agencyName";
		
		return (Agency) dao.queryUnique(hql, map);
	}


	@Override
	public boolean isExistUsername(String username) {
		Map map = new HashMap();
		map.put("username", username);
		String hql ="select a from Account a where a.username = :username";
		List list = dao.query(hql, map);
		return !list.isEmpty();
	}


	@Override
	public boolean isExistEmail(String email) {
		Map map = new HashMap ();
		map.put("mail", email);
		String hql ="select a from Account a where a.mail = :mail";	
		List list = dao.query(hql, map);
		return !list.isEmpty();
	}


	@Override
	public String resetPassword(Map argmap) {
		String email = argmap.get("email").toString();
		String username = argmap.get("username").toString();
		Map map = new HashMap ();
		map.put("mail", email);
		map.put("username", username);
		String hql ="select a from Account a where a.mail = :mail and a.username =:username";	
		Account account = (Account) dao.queryUnique(hql,map);
		
		String randomPassword = getRandomString(6);
		account.setPassword(MD5.getMD5(randomPassword));
		dao.modify(account);
		return randomPassword;
		
	}

	/**
	 * 产生随机码
	 * @param length 随机码长度
	 * @return
	 */
	private  String getRandomString(int length){
		
		String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuffer s = new StringBuffer();
		int index;
		for(int i=0;i<length;i++){
			 index = random.nextInt(base.length());
			 s.append(base.charAt(index));
		}
		return s.toString();
	}
}
