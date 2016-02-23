package csdc.service;


import java.util.Date;
import java.util.List;
import java.util.Map;

import csdc.model.Account;
import csdc.model.Agency;
import csdc.model.Discipline;
import csdc.model.Person;
import csdc.model.Product;

public interface IApplyService {
	
	/**
	 * 获取所有的申报成果
	 * @return
	 */
	public Map getProducts();
	
	/**
	 * 根据成果id获取项的成果
	 * @param id
	 * @return
	 */
	public Product getProductById(String id);
	
	/**
	 * 获取账户所申报的成果
	 * @param account
	 * @return
	 */
	public Product getProductByAccount(Account account);
	

	/**
	 * 添加人员信息
	 * @param person
	 * @return
	 */
	public String addPerson(Person person);
	
	/**
	 * 批量添加人员（合作者）
	 * @param person
	 */
	public List<String> addPersons(List<Person> person,Map map);
	
	
	/**
	 * 添加成果
	 * @param product
	 * @return
	 */
	public String addProduct(Product product);
	
	
	
	/**
	 * 修改
	 * @param oldAward
	 * @param newAward
	 * @return
	 */
	public String modify(Product oldProduct , Product newProduct);
	
	/**
	 * 删除
	 * @param award
	 * @return
	 */
	public void delete(List<String> productIds);
	
	/**
	 * 添加个人信息、成果信息和机构信息
	 * @param person
	 * @param product
	 * @param agency
	 * @return
	 */
	public String addApplyInfo(Person person,Product product,Agency agency,Map map);
	
	/**
	 * 批量添加个人信息、成果信息和机构信息
	 * @param person
	 * @param product
	 * @param agency
	 * @return
	 */
	public String addApplyInfos(List<Person>person ,Product product,Agency agency,Map map);

	
	
	public List getProductMembersByProductId(String entityId);
	
	
	
	public Agency getAgency(String agencyName);
	
	public Agency getAgencyById(String agencyId);
	public Discipline getDiscipline(String disciplineName);
	
	/**
	 * 封装参数形式为  ArgsName+i,为number个
	 * @param ArgsName 待封装参数的基本名称
	 * @param number 封装个数
	 * @return
	 */
	public List<String> getArgsList(String ArgsName,int number);
	
	//获取成果参与者的信息
	public List<Object[]>  getMemberInfo(String productId);
	
	
	public String modifyInfos(List<Person>person ,Product product,Map map);
	
	/**
	 * 封装参数形式为  ArgsName+i,为number个
	 * @param ArgsName 待封装参数的基本名称
	 * @param number 封装个数
	 * @return
	 */
	public List<Object> getObjectList(Object obj1, Object obj2,Object obj3, Object obj4,Object obj5);

	/**
	 * date格式化为Date yyyy-MM-dd
	 * @param date
	 * @return
	 */
	public Date dateformat2Date(Date date);
}
