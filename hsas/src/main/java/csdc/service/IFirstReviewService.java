package csdc.service;

import java.io.InputStream;
import java.util.List;

import csdc.model.Account;
import csdc.model.Expert;
import csdc.model.Groups;

public interface IFirstReviewService {
	/**
	 * 根据初评专家账户获得初评专家对应的分组
	 * @param account 专家账户
	 * @return 分组
	 */
	Groups getExpertGroupByAccount(Account account);
	/**
	 * 在laData中增加初评成果类型和已评专家个数信息
	 * 初评成果类型: 1:论文基础类  2：论文应用对策类  3：著作类
	 * @param account 当前账户
	 * @return laData 处理后的laData
	 */
	List addExternalInfo(List laData, Account account);
	/**
	 * 判断当前专家账户对应的专家是否为当前初评专家
	 * @param account 账户 
	 * @return
	 */
	boolean isFirstReviewer(Account account);
	/**
	 * 根据当前专家账户获得对应的专家对象
	 */
	Expert getExpertByAccount(Account account);
	/**
	 * 判断该成果是否属于该专家评(判断成果分组是否和专家分组相同)
	 * @param expert
	 * @param entityId
	 * @return
	 */
	boolean isUnderExpertReview(Expert expert, String entityId);
	/**
	 * 判断该成果是否已被所有专家评过
	 * @param entityId 成果id
	 * @return 是/否
	 */
	boolean isReviewedByAllExpert(String entityId);
	/**
	 * 获得该成果的状态
	 * @param entityId
	 * @return
	 */
	int getProductStatusbyId(String entityId);
	/**
	 * 添加初评结果，若存在同一条结果(专家和成果相同)，则覆盖之
	 * @param expert 初评专家
	 * @param entityId 成果id
	 * @param type 初评成果类型
	 * @param firstReviewScores 初评得分情况
	 * @param firstReviewOpinion  初评意见
	 */
	void addFirstReviewResult(Expert expert, String entityId,
			int type, int[] firstReviewScores, String firstReviewOpinion);
	/**
	 * 提交初评结果
	 * @param entityId 成果id
	 */
	void submitFirstReview(String entityId);
	/**
	 * 设置复评分数线，并自动审核，在此分数线之上（含）的初评成果进入复评
	 * @param scoreLine
	 */
	void audit(int scoreLine);
	/**
	 * 判断当前专家的成果是否被自己评完
	 * @param expert 专家
	 * @return
	 */
	boolean isAllReviewedByExpert(Expert expert);
	/**
	 * 提交当前专家所有初评的结果
	 * @param expert
	 */
	void submitByExpert(Expert expert);
	/**
	 * 判断当前组的成果是否被所有初评专家提交
	 * @param group
	 * @return
	 */
	boolean isSubmitByAllExpert(Groups group);
	/**
	 * 提交本组内的所有初评成果
	 * @param group
	 */
	void submitFirstReviewByGroup(Groups group);
	/**
	 * 根据成果id获得成果的初评类型  [1:论文基础类  2：论文应用对策类  3：著作类]
	 * @param productId
	 * @return
	 */
	public int getProductTypeByProductId(String productId);
	/**
	 * 判断当前初评专家是否提交过初评结果
	 * @param expert
	 * @return
	 */
	boolean hasSubmitByExpert(Expert expert);
	/**
	 * 生成单个专家的初评签字表
	 */
	InputStream getExpertSignExcel(Expert expert);
}