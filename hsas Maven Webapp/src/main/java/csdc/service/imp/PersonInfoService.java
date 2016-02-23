package csdc.service.imp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import csdc.model.Account;
import csdc.model.Agency;
import csdc.model.Person;
import csdc.service.IPersonInfoService;
import csdc.tool.MD5;

@Service
public class PersonInfoService extends BaseService implements
		IPersonInfoService {
	
	/**
	 * 根据证件号找到人员
	 * @param idcard 身份证号码
	 * @return 人
	 */
	@Override
	public Person findPersonByIdcard(String idcard){
		List list = dao.query("select p from Person p where LOWER(p.idcardNumber) = '" + idcard.trim().toLowerCase() + "' ");
		return list.isEmpty() ? null : (Person)list.get(0);
	}
	
	/**
	 * 根据姓名、机构名和出生日期找人
	 * @return
	 */
	@Override
	public Person findPerson(String name, String agencyName, Date birthday) {
		Map<String, String> paraMap = new HashMap<String, String>();
		paraMap.put("name", name);
		paraMap.put("agencyName", agencyName);
		paraMap.put("birthday", new SimpleDateFormat("yyyy-MM-dd").format(birthday));
		List list = dao.query("select p from Person p where p.name = :name and p.agencyName = :agencyName and date_format(p.birthday,'%Y-%m-%d')=:birthday", paraMap);
		return list.isEmpty() ? null : (Person)list.get(0);
	}
	

	/**
	 * 根据证件号和姓名判断人员是否存在
	 * @param idcardNumber 身份证号码
	 * @param name 姓名
	 * @return 	第一个元素 -- 0:不存在 1:存在且匹配 2:存在且不匹配
	 * 			第二个元素 -- person对象
	 */
	public Object[] checkIfPersonExists(String idcardNumber, String name){
		List list = dao.query("select p from Person p where LOWER(p.idcardNumber) = '" + idcardNumber.trim().toLowerCase() + "' ");
		Object[] result = new Object[2];
		if (list.isEmpty()){
			result[0] = 0;
		} else {
			Person person = (Person) list.get(0);
			result[0] = person.getName().trim().equals(name.trim()) ? 1 : 2;
			if (result[0].equals(1)){
				result[1] = person;
			}
		}
		return result;
	}
	
	
	@Override
	public List getPersonByAccount(Account account) {
		Map map = new HashMap();
		map.put("username", account.getUsername());
		map.put("password", account.getPassword());
		String hql = "select a from Account a left join a.person p where a.username=:username and a.password=:password";
		//String hql = "select  ac.username,ac.type,ac.status,ac.expireDate from Account ac ,Award  aw left aw.product p where ac.username = :username and ac.password = :password and p.id=ac.productId";
		List list = dao.query(hql, map);
		return  list;
	}
	
	
	@Override
	public Person getPersonByDefaultAccount() {
		
		return null;
	}

	/**
	 * 1、中找到账号对应的person_id
	 * 2、applicant找到person对应的applicant_id
	 * 3、award 中找到applicant_id对应的award
	 * 
	 */
	@Override
	public List getProductByAccount(Account account) {
		Map map = new HashMap();
		map.put("username", account.getUsername());
		map.put("password", account.getPassword());
		String hql = "select pd.id ,pd.name,pd.authorName,pd.type,pd.researchType,pd.submitDate from Account ac left join ac.person acp ,Product pd left join pd.author p  where ac.username=:username and ac.password=:password  and p.id = acp.id";
		
		
		List awardList = dao.query(hql, map);
		
		return  pageListDealWith(awardList);
		
		
	}

	@Override
	public String addPerson(Person person) {
		person.setCreateDate(new Date());
		String personId = dao.add(person);
		return personId;
	}

	@Override
	public String modifyPersonInfo(Person newPerson,Person oldPerson) {
		oldPerson.setName(newPerson.getName());
		oldPerson.setGender(newPerson.getGender());
		oldPerson.setBirthday(newPerson.getBirthday());
		oldPerson.setEthnic(newPerson.getEthnic());
		oldPerson.setMobilePhone(newPerson.getMobilePhone());
		oldPerson.setOfficePhone(newPerson.getOfficePhone());
		oldPerson.setHomePhone(newPerson.getHomePhone());
		oldPerson.setAddress(newPerson.getAddress());
		oldPerson.setEmail(newPerson.getEmail());
		oldPerson.setIdcardNumber(newPerson.getIdcardNumber());
		oldPerson.setIntroduction(newPerson.getIntroduction());
		oldPerson.setAgencyName(newPerson.getAgencyName());
		oldPerson.setAgency((Agency)dao.queryUnique("select a from Agency a where a.name='" + newPerson.getAgencyName() + "'"));
		oldPerson.setUpdateDate(new Date());
		dao.modify(oldPerson);
		return oldPerson.getId();
	}

	@Override
	public Boolean checkPassword(String password,String username) {
		Account account = getAccountByUsernameAndPassword(password, username);
		
		return account!=null ;
	}

	@Override
	public String modifyPassword(String oldPassword, String newPassword,
			String username) {
		
		
		Account account =getAccountByUsernameAndPassword(oldPassword, username);
		account.setPassword(MD5.getMD5(newPassword));
		dao.modify(account);
		return account.getId();
	}
	
	private Account getAccountByUsernameAndPassword(String password,String username){
		
		Map map =new HashMap();
		map.put("username", username);
		String md5Password = MD5.getMD5(password);
		map.put("password", md5Password);
		String hql = "select a from Account a where a.username=:username and a.password =:password";
		
		return (Account) dao.queryUnique(hql, map);
		
	}

	
	
	
}
