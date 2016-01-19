package csdc.service;

import java.util.List;

public interface IAssignExpertService extends IBaseService {
	/**
	 * 处理原始laData,在String[] item 数组后追加分组学科信息和已分配专家数
	 * @param type 评审类型的标志[0：初评， 1：复评]
	 */
	public List laDataDealWith(List laData, int type);
	/**
	 * 专家列表数据处理，加入专家是否为成果作者的字段[0:非成果作者 1:为成果第一作者 2:为成果第二作者]
	 * @param laData 原始列表数据
	 * @return 处理后的列表数据
	 */
	public List expertDataDealWith(List laData);
	/**
	 *  根据分组id获取分组下学科门类
	 *  @param groupId 分组id
	 *  @return 拼接后的学科门类
	 */
	public String getDisciplinesByGroupId(String groupId);
	/**
	 * 根据分组id获取已分配专家个数
	 * @param type 评审类型的标志[0：初评， 1：复评]
	 */
	public int getAssignedExpertNumByGroupId(String groupId, int type);
	/**
	 * 获取本分组已分配的专家信息
	 * @param groupId 分组Id
	 * @param reviewType 评审类型[0:初评 1:复评]
	 * @return 专家信息列表
	 */
	public List getAssignedExpert(String groupId, int reviewType);
	/**
	 * 获取 本分组组长id
	 * @param groupId 分组id
	 * @return 组长id
	 */
	public String getGroupLeaderId(String groupId);
	/**
	 * 
	 * 向初评分组中添加专家
	 * @param expertIds 待添加的专家列表
	 * @return 添加专家是否成功的标志 1 表示添加成功 0表示添加失败
	 */
	public int addExpert(List<String> expertId, int reviewType);
	/**
	 * 
	 * 从初评分组专家中删除专家，与添加专家为互逆操作
	 * @param expertIds 待删除的专家列表
	 * @return 删除专家是否成功的标志 1 表示删除成功 0表示删除失败
	 */
	public int deleteExpert(List<String> expertId, int reviewType);
	/**
	 * 根据分组id获取分组内成果数
	 * @param groupId 分组id
	 * @param reviewType 评审类型[0:初评 1:复评]
	 * @return 分组内成果数
	 */
	public int getProductNumByGroupId(String groupId, int reviewType);
	/**
	 * 更新复评组长，将该组之前的组长撤销，重新分配组长
	 * @param leaderId 组长的专家id
	 * @return 更新组长是否成功的标志 1 表示更新成功 0表示更新失败
	 */
	public int updateLeader(String leaderId);
	/**
	 * 获取当前专家主审成果列表
	 * @param expertId 专家id
	 * @return
	 */
	public List getChiefReviewProducts(String expertId);
	/**
	 * 设置当前专家主审成果
	 * @param expertId 专家id
	 * @param productId 成果id列表
	 * @return 操作是否成功的标志 1 表示设置成功 0表示设置失败
	 */
	public int setChiefReviewProducts(String expertId, List<String> productId);
}
