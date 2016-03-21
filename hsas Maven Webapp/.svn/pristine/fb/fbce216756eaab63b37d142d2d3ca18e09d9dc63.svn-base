package csdc.service.imp;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import csdc.model.Reward;
import csdc.model.SystemOption;
import csdc.service.IRewardService;
/**
 * 奖励管理接口实现
 * @author Yaoyota 2015.09.30
 *
 */
@Service
public class RewardService extends BaseService implements IRewardService {

	/**
	 * 判断奖励记录是否存在,根据年份、类型、等级三个字段唯一确定一条奖励记录
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public boolean checkReward(Reward reward) {
		if(reward==null) {
			return true;
		} else {
			Map map = new HashMap();
			map.put("year", reward.getYear());
			map.put("type", reward.getType());
			map.put("level", reward.getLevel());
			List list = dao.query("from Reward r where r.year=:year and r.type=:type and r.level=:level", map);
			return list.isEmpty()? false:true;
		}
	}

	@Override
	public String accquireFileSize(long fileLength) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String addReward(Reward reward) {
		reward.setCreateDate(new Date());
		return (String)dao.add(reward);
	}

	@Override
	public String modifyReward(Reward oldReward, Reward newReward) {
		oldReward.setYear(newReward.getYear());
		oldReward.setType(newReward.getType());
		oldReward.setLevel(newReward.getLevel());
		oldReward.setUpdateDate(new Date());
		dao.modify(oldReward);//修改奖励
		return oldReward.getId();
	}

	@Override
	public Reward viewReward(String entityId) {
		return (Reward)dao.query(Reward.class, entityId);
	}

	@Override
	public int deleteReward(List<String> entityIds) {
		try {
			for(String id: entityIds) {
				dao.delete(Reward.class, id);
			}
		} catch (Exception e) {
			// TODO: handle exception
			return 0;//删除失败，返回0
		}
		return 1;//删除成功，返回1
	}

}
