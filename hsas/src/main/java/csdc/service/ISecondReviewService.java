package csdc.service;

import java.util.List;

import csdc.model.Account;
import csdc.model.Expert;
import csdc.model.Groups;

/**
 * 复评审核接口
 * @author Yaoyota
 *
 */
public interface ISecondReviewService {
	/**
	 * 根据专家账户获得专家对应的分组
	 * @param account 专家账户
	 * @return 分组
	 */
	Groups getExpertGroupByAccount(Account account);
    /**
     * 根据专家账户获得专家对象
     * @param account 专家账户
     * @return 账户对应的专家
     */
	Expert getExpertByAccount(Account account);
	/**
	 * 处理laData 加上是否已评和是否为主审信息，并将主审成果放在数据列表最前
	 * @param laData 原始laData
	 * @param expert 主审专家
	 * @return 处理后的laData
	 */
	List addChiefReviewInfo(List laData, Expert expert);
	/**
	 * 在列表中加入评审信息:已评专家数和应评专家数
	 * @param laData
	 * @return
	 */
	List addReviewInfo(List laData);
	/**
	 * 判断当前账户是否为当年复评专家
	 * @param account
	 * @return
	 */
	boolean isSecondReviwer(Account account);
	/**
	 * 判断该成果是否由当前专家主审
	 * @param expert 专家
	 * @param entityId 成果id
	 * @return
	 */
	boolean isChiefReviewer(Expert expert, String productId);
	/**
	 * 判断该成果是否由当前普通专家评
	 * @param expert 专家
	 * @param entityId 成果id
	 * @return
	 */
	boolean isProductReviewer(Expert expert, String productId);
	/**
	 * 判断该成果是否由当前专家评过
	 * @param expert 专家 
	 * @param entityId 成果id
	 * @return 
	 */
	boolean isReviewed(Expert expert, String productId);
	/**
	 * 根据成果id和专家id获取复评评价意见(包括未评价的专家)
	 * @param expertId 专家id
	 * @param productId 成果id
	 * @return
	 */
	List<String[]> getReviewOpinionByExpertId(String productId, List<String> expertId);
	/**
	 * 获取当前成果奖励等级
	 * @param entityId
	 * @return
	 */
	int getRewardLevel(String productId);
	/**
	 * 判断当前成果复评审核是否被组长退回
	 * @param productId 成果id
	 * @return
	 */
	boolean isBacked(String productId);
	/**
	 * 判断当前成果是否已被所有专家评完
	 * @param productId
	 * @return
	 */
	boolean isReviewedByAllExpert(String productId);
	/**
	 * 获取当前成果的主审专家
	 * @param productId
	 * @return
	 */
	Expert getChiefReviewer(String productId);
	/**
	 * 获取当前成果的所有复评专家id
	 * @param productId 成果id
	 * @return
	 */
	List<String> getReviewerIdByProductId(String productId);
	/**
	 * 获取组长审核结果
	 * @param productId 成果Id
	 * @return
	 */
	int getLeaderAuditResult(String productId);
	/**
	 * 根据成果id获取当前成果状态
	 * @return
	 */
	int getProductStatusbyId(String productId);
	/**
	 * 添加主审评价
	 * @param productId
	 * @param expert 
	 * @param rewardLevel
	 * @param reviewOpinion
	 */
	void addChiefReview(String productId, Expert expert, int rewardLevel, String reviewOpinion);
	/**
	 * 修改主审评价
	 * @param productId
	 * @param expert
	 * @param rewardLevel
	 * @param reviewOpinion
	 */
	void modifyChiefReview(String productId, Expert expert, int rewardLevel,
			String reviewOpinion);
	/**
	 * 添加普通专家评价
	 * @param productId
	 * @param expert
	 * @param reviewOpinion
	 */
	void addReview(String productId, Expert expert, String reviewOpinion);
	/**
	 * 修改普通专家评价
	 * @param entityId
	 * @param expert
	 * @param reviewOpinion
	 */
	void modifyReview(String entityId, Expert expert, String reviewOpinion);
	/**
	 * 判断当前专家是否为该组的组长
	 * @param expert
	 * @param entityId
	 * @return
	 */
	boolean isGroupLeader(Expert expert, String entityId);
	/**
	 * 组长复评审核
	 * @param entityId
	 * @param leaderAuditResult
	 */
	void audit(String entityId, int leaderAuditResult);
	/**
	 * 提交成果进入终审
	 * @param entityId
	 */
	void submit(String entityId);
	/**
	 * 判断当前成果是否开始了复评，即有 专家评过
	 * @param entityId
	 * @return
	 */
	boolean startReview(String entityId);
	/**
	 * 将成果从复评退回到初评审核阶段
	 * @param entityIds
	 */
	void back(List<String> entityIds);
}
