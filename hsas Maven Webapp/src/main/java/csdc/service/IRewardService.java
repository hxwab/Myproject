package csdc.service;

import java.util.List;

import csdc.model.Reward;

/**
 * 奖励管理接口
 * @author Yaoyota 2015.09.30
 *
 */
public interface IRewardService extends IBaseService {
	/**
	 * 判断奖励记录是否已存在
	 */
	public boolean checkReward(Reward reward);
	/**
	 *添加奖励记录
	 *@param reward 要添加的奖励
	 *@return 添加后的奖励id
	 */
	public String addReward(Reward reward);
	/**
	 * 修改奖励记录
	 * @param oldReward 修改前的奖励
	 * @param newReward 修改后的奖励
	 * @return 修改后的奖励id
	 */
	public String modifyReward(Reward oldReward, Reward newReward);
	/**
	 * 查看奖励
	 * @param entityId 奖励id
	 * @return 奖励实体
	 */
	public Reward viewReward(String entityId);
	/**
	 * 批量删除奖励
	 * @param entityIds 奖励id列表
	 * @return 是否成功删除标志位 1：删除成功 0：删除失败
	 */
	public int deleteReward(List<String> entityIds);
}
