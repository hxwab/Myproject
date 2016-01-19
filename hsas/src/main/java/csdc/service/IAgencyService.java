package csdc.service;

import java.util.List;

import csdc.model.Agency;
import csdc.model.Person;

public interface IAgencyService {

	public Agency findAgencyByName(String agencyName);
	/**
	 * 校验机构是否已存在 以机构名称和机构代码(如果不为空)作为唯一机构的代表
	 * @param agency 待校验的机构
	 * @return
	 */
	public boolean checkAgency(Agency agency);
	
	/**
	 * 校验机构名称是否存在
	 * @param agencyName
	 * @return
	 */
	public boolean  checkAgencyByName(String agencyName);
	
	/**
	 * 添加机构
	 * @param agency 要添加的机构
	 * @return 添加成功后的机构id
	 */
	public String addAgency(Agency agency);
	/**
	 * 修改机构信息
	 * @param oldAgency 旧的机构对象
	 * @param agency 新的机构对象
	 * @return 机构Id
	 */
	public String modifyAgency(Agency oldAgency, Agency agency);
	/**
	 * 删除机构，会连同本机构的负责人信息一起删除
	 * @param entityId
	 * @return 是否删除成功的标志
	 */
	public int deleteAgency(List<String> entityIds);
	/**
	 * 查看机构信息
	 * @param entityId 机构Id
	 * @return 要查看的机构对象
	 */
	public Agency viewAgency(String entityId);

	
}
