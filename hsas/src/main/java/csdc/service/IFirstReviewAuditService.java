package csdc.service;

import java.util.List;

public interface IFirstReviewAuditService {
	/**
	 * 批量审核
	 * @param entityIds 成果id
	 * @param auditResult  审核结果
	 */
	void addAuditRsult(List<String> entityIds, int auditResult);
	/**
	 * 自动审核，设置进入复评的分数线，此分数线以上的初评成果自动进入复评
	 * @param scoreLine
	 */
	void audit(int scoreLine);
	/**
	 * 获取成果状态字段
	 * @param entityId
	 * @return
	 */
	int getProductStatus(String entityId);
	/**
	 * 获取成果初评平均分
	 * @param entityId
	 * @return
	 */
	int getAverageScore(String entityId);
	/**
	 * 获取成果的初评类型  [1:论文基础类  2：论文应用对策类  3：著作类]
	 * @param entityId
	 * @return
	 */
	int getReviewType(String entityId);
	/**
	 * 获取成果初评得分数据 列表字段包含:专家名、5项得分、评审意见、总分
	 * @param entityId
	 * @return
	 */
	List<String[]> getReviewData(String entityId);

}
