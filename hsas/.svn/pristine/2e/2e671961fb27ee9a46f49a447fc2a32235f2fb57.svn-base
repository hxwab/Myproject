package csdc.service.imp;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import csdc.model.Account;
import org.springframework.stereotype.Service;
import csdc.model.Reward;
import csdc.service.IRewardService;
/**
 * 奖励管理接口实现
 * @author Yaoyota 2015.09.30
 *
 */
@Service
public class RewardService extends BaseService implements IRewardService {

	/**
	 * 判断奖励记录是否已经存在，以年份为唯一判断标准
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public boolean checkReward(Reward reward) {
		if(reward==null) {
			return true;
		} else {
			Map map = new HashMap();
			map.put("year", reward.getYear());
			List list = dao.query("from Reward r where r.year=:year", map);
			return list.isEmpty()? false:true;
		}
	}

	@Override
	public String accquireFileSize(long fileLength) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String addReward(Reward reward, Account creater) {
		reward.setCreater(creater);
		reward.setCreateDate(new Date());
		reward.setUpdateDate(new Date());
		return dao.add(reward);
	}

	@Override
	public String modifyReward(Reward oldReward, Reward newReward) {
		oldReward.setYear(newReward.getYear());
		oldReward.setPaperSpecial(newReward.getPaperSpecial());
		oldReward.setPaperFirst(newReward.getPaperFirst());
		oldReward.setPaperSecond(newReward.getPaperSecond());
		oldReward.setPaperThird(newReward.getPaperThird());
		oldReward.setBookSpecial(newReward.getBookSpecial());
		oldReward.setBookFirst(newReward.getBookFirst());
		oldReward.setBookSecond(newReward.getBookSecond());
		oldReward.setBookThird(newReward.getBookThird());
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
	/**
	@Override
	public List<Reward> generateRewards(List<String> bouns, Account creater) {
		List<Reward> rewards = new ArrayList<Reward>();
		int i;
		String year = bouns.get(0);//获得奖励年份
		Date createDate = new Date();//创建时间
		if(bouns.size()==9) {
			for(i=0; i<8; i++) {
				int type = i/4 + 1;//奖励类型
				int level = i%4;//奖励等级
				int jine = Integer.valueOf(bouns.get(i+1));//获得奖励金额
				Reward reward = new Reward();
				reward.setYear(year);
				reward.setLevel(level);
				reward.setType(type);
				reward.setBonus(jine);
				reward.setCreater(creater);
				reward.setCreateDate(createDate);
				reward.setUpdateDate(createDate);
				rewards.add(reward);
			}
		}
		return rewards;
	}
  **/
}
