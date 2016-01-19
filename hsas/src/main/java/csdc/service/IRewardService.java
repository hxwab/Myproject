package csdc.service;

import java.util.List;

import csdc.model.Account;
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
	 *@param rewards 要添加的奖励
	 *@param account 奖励添加人
	 *@return entityId 奖励id
	 */
	public String addReward(Reward reward, Account creater);
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
	/**
	 * 批量生成某年的所有奖金对象
	 * @param bouns 每年的奖金信息 {0:年份；1：著作特等奖； 2:著作一等奖； 3：著作二等奖； 4：著作三等奖； 5：论文特等奖； 6：论文一等奖 ；7：论文二等奖； 8：论文三等奖 }
	 * @param creater 添加者的账户信息
	 * @return 生成的8个奖金对象
	 */
//	public List<Reward> generateRewards(List<String> bouns, Account creater);
}
