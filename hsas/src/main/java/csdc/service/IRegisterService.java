package csdc.service;

import java.util.Map;

import csdc.model.Account;
import csdc.model.Agency;
import csdc.model.Person;

public interface IRegisterService {
	
	/**
	 * 添加账号信息
	 * @param account 账户信息
	 * @param person  注册者信息
	 * @param map  封装的其他信息
	 * @return
	 */
	public String addAccount(Account account,Person person,Map map);
	
	/**
	 * 添加注册者信息
	 * @param person
	 * @return
	 */
	
	public String addPerson(Person person);
	
	
	/**
	 * 根据机构名获取机构
	 * @param agencyName
	 * @return
	 */
	public Agency getAgency(String agencyName);
	
	/**
	 * 判断账号名是否可用（用户名不可重复）
	 * @param username
	 * @return
	 */
	public boolean isExistUsername(String username);
	
	
	/**
	 * 判断邮箱是否可用（一个邮箱只允许申请一个账号）
	 * @param email
	 * @return
	 */
	public boolean isExistEmail(String email);
	
	
	public String resetPassword(Map argmap);
	
}
