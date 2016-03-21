package csdc.service.imp;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import csdc.model.Account;
import csdc.model.Agency;
import csdc.model.Person;
import csdc.service.IRegisterService;

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
		account.setPerson(p);
		account.setAgency(agency);
		account.setType(6);
		account.setStatus(0);
		account.setStartDate(new Date());
		account.setExpireDate(new Date(117, 10, 25, 23, 59, 59));
		account.setCreateType(1);
		account.setIsSuperUser(0);
		return dao.add(account);
		
		
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
	public String resetPassword(String email) {
		
		return null;
	}

	
}
