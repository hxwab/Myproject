package csdc.service;

import java.util.List;

import csdc.model.Expert;

public interface IExpertService {

	/**
	 * 添加专家
	 * @param expert待添加的专家对象
	 */
	public String addExpert(Expert expert);
	
	/**
	 * 根据ID查找专家
	 * @param entityId
	 * @return
	 */
	public Expert findExpert(String entityId);

	/**
	 * 根据身份证号找专家
	 * @param idcardNumber
	 * @return
	 */
	public Expert findExpertByIdCard(String idcardNumber);
	
	/**
	 * 根据身份证号和专家姓名找专家
	 * @param idcardNumber
	 * @param name
	 * @return
	 */
	public Expert findExpertByIdCardAndName(String idcardNumber, String name);	
	
	/**
	 * 根据personId找专家
	 * @param personId
	 * @return
	 */
	public Expert findExpertByPersonId(String personId);

	/**
	 * 根据专家编号找专家
	 * @param number
	 * @return
	 */
	public Expert findExpertByNumber(String number);

	/**
	 * 群删专家
	 * @param expertIds
	 */
	public void deleteExperts(List<String> expertIds);
	
	/**
	 * 修改专家
	 * @param expert
	 */
	public void modifyExpert(Expert newExpert, Expert oldExpert);

	/**
	 * 生成新专家的专家编号
	 * 格式：Ennnnnnn，第1位是"E"，后面7位是数字
	 * @return
	 */
	public String generateNewNumber();

	/**
	 * 授予专家参评资格
	 * @param entityIds
	 */
	public void authorizeExperts(List<String> entityIds);

	/**
	 * 回收专家参评资格
	 * @return
	 */
	public void unauthorizeExperts(List<String> entityIds);

}
