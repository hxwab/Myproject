package csdc.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import csdc.model.Account;
import csdc.model.Agency;
import csdc.model.Person;
import csdc.model.SolicitPapers;
/**
 * 
 * @author huangxw
 * @since 15-9
 *
 */
public interface IPersonInfoService {
	
	/**
	 * 由username查出个人信息
	 * @param username
	 * @return
	 */
	public List getPersonByAccount(Account account);
	
	/**
	 * 获取该账号所属人员的默认信息
	 * @return
	 */
	public Person getPersonByDefaultAccount();
	
	/**
	 * 根据账号名找到对应的申报成果
	 * @param username
	 * @return
	 */
	public List getProductByAccount(Account account);
	
	
	public String modifyPersonInfo(Person newPerson,Person oldPerson);
	
	public String modifyAccountInfo(Account account);
	
	public Agency getAgency(String agenctId);
	
	public Boolean checkPassword(String password ,String username);
	
	public String modifyPassword(String oldPassword ,String newPassword,String username);

	public Person findPersonByIdcard(String idcard);

	public Object[] checkIfPersonExists(String idcardNumber, String name);

	public String addPerson(Person person);

	public Person findPerson(String name, String agencyName, Date birthday);
	
	public String  generateProductNumber();
	
}
