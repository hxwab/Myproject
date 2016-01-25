package csdc.action.system;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.instrument.classloading.glassfish.GlassFishLoadTimeWeaver;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import csdc.action.BaseAction;
import csdc.model.Account;
import csdc.model.Reward;
import csdc.service.IRewardService;
import csdc.tool.info.GlobalInfo;
import csdc.tool.info.RewardInfo;
/**
 * 
 * @author Yaoyota
 *
 */
@Component
@Scope(value="prototype")
public class RewardAction extends BaseAction{
   
	/**
	 * 
	 */
	private static final long serialVersionUID = -8084750778942833900L;
	private static final String TMP_ENTITY_ID = "accountId";// 缓存与session中，备用的账号ID变量名称
	private static final String DATE_FORMAT = "yyyy-MM-dd";// 列表时间格式
	private static final String PAGE_NAME = "rewardListPage";
	private static final String PAGE_BUFFER_ID = "r.id";// 上下条查看时用于查找缓存的字段名称
	private static final String HQL = 
			"select r.id, r.year, r.paperSpecial, r.paperFirst, r.paperSecond, r.paperThird, r.bookSpecial, r.bookFirst, r.bookSecond, r.bookThird, r.updateDate from Reward r where 1=1";// 上下条查看时用于查找缓存的字段名称HQL
	private static final String[] SORT_COLUMNS = new String[] {
		"r.year",
		"r.paperSpecial",
		"r.paperFirst",
		"r.paperSecond",
		"r.paperThird",
		"r.bookSpecial",
		"r.bookFirst",
		"r.bookSecond",
		"r.bookThird",
		"r.updateDate"
	};
	
	private Reward reward; //奖励对象
	@Autowired
	private IRewardService rewardService;
	
	@Override
	public String toAdd() {
		return SUCCESS;
	}

	@Override
	@Transactional
	public String add() {
		Account creater= loginer.getAccount();
		entityId = rewardService.addReward(reward, creater);
		jsonMap.put("tag", "1");
		jsonMap.put("entityId", entityId);
		return SUCCESS;
	}
	
	public void validateAdd() {
		if(reward.getYear()==null||reward.getYear().isEmpty()) 
			this.addFieldError(GlobalInfo.ERROR_INFO, RewardInfo.ERROR_REWARD_YEAR_NULL);
		if(reward.getPaperSpecial()==null||reward.getPaperSpecial()==0)
			this.addFieldError(GlobalInfo.ERROR_INFO, RewardInfo.ERROR_REWARD_PAPER_SPECIAL_NULL);
		if(reward.getPaperFirst()==null||reward.getPaperFirst()==0)
			this.addFieldError(GlobalInfo.ERROR_INFO, RewardInfo.ERROR_REWARD_PAPER_FIRST_NULL);
		if(reward.getPaperSecond()==null||reward.getPaperSecond()==0)
			this.addFieldError(GlobalInfo.ERROR_INFO, RewardInfo.ERROR_REWARD_PAPER_SECOND_NULL);
		if(reward.getPaperThird()==null||reward.getPaperThird()==0)
			this.addFieldError(GlobalInfo.ERROR_INFO, RewardInfo.ERROR_REWARD_PAPER_THIRD_NULL);
		if(reward.getBookSpecial()==null||reward.getBookSpecial()==0)
			this.addFieldError(GlobalInfo.ERROR_INFO, RewardInfo.ERROR_REWARD_BOOK_SPECIAL_NULL);
		if(reward.getBookFirst()==null||reward.getBookFirst()==0)
			this.addFieldError(GlobalInfo.ERROR_INFO, RewardInfo.ERROR_REWARD_BOOK_FIRST_NULL);
		if(reward.getBookSecond()==null||reward.getBookSecond()==0)
			this.addFieldError(GlobalInfo.ERROR_INFO, RewardInfo.ERROR_REWARD_BOOK_SECOND_NULL);
		if(reward.getBookThird()==null||reward.getBookThird()==0)
			this.addFieldError(GlobalInfo.ERROR_INFO, RewardInfo.ERROR_REWARD_BOOK_THIRD_NULL);
		if(rewardService.checkReward(reward))
			this.addFieldError(GlobalInfo.ERROR_INFO, RewardInfo.ERROR_REWARD_EXIST);
	}

	@Override
	@Transactional
	public String delete() {
		int flag;
		flag = rewardService.deleteReward(entityIds);
		if(flag==1) //是否删除成功
			return SUCCESS;
		else
			return ERROR;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String toModify() {
		reward = (Reward) dao.query(Reward.class, entityId);// 获取奖励
		if (reward == null) {// 奖励不存在，返回错误提示
			this.addFieldError(GlobalInfo.ERROR_INFO, RewardInfo.ERROR_REWARD_NULL);
			return INPUT;
		} else {// 奖励存在，备用奖励ID
			session.put(TMP_ENTITY_ID, entityId);
			return SUCCESS;
		}
	}

	@Override
	@Transactional
	public String modify() {
		entityId = (String)session.get(TMP_ENTITY_ID);//获取备用奖励Id
		Reward oldReward = (Reward)dao.query(Reward.class, entityId);
		entityId = rewardService.modifyReward(oldReward, reward);//修改奖励
		session.remove(TMP_ENTITY_ID);
		jsonMap.clear();
		jsonMap.put("tag", "1");
		return SUCCESS;
	}
	
	public void validateModify() {
		if(reward.getYear()==null||reward.getYear().isEmpty()) 
			this.addFieldError(GlobalInfo.ERROR_INFO, RewardInfo.ERROR_REWARD_YEAR_NULL);
		if(reward.getPaperSpecial()==null||reward.getPaperSpecial()==0)
			this.addFieldError(GlobalInfo.ERROR_INFO, RewardInfo.ERROR_REWARD_PAPER_SPECIAL_NULL);
		if(reward.getPaperFirst()==null||reward.getPaperFirst()==0)
			this.addFieldError(GlobalInfo.ERROR_INFO, RewardInfo.ERROR_REWARD_PAPER_FIRST_NULL);
		if(reward.getPaperSecond()==null||reward.getPaperSecond()==0)
			this.addFieldError(GlobalInfo.ERROR_INFO, RewardInfo.ERROR_REWARD_PAPER_SECOND_NULL);
		if(reward.getPaperThird()==null||reward.getPaperThird()==0)
			this.addFieldError(GlobalInfo.ERROR_INFO, RewardInfo.ERROR_REWARD_PAPER_THIRD_NULL);
		if(reward.getBookSpecial()==null||reward.getBookSpecial()==0)
			this.addFieldError(GlobalInfo.ERROR_INFO, RewardInfo.ERROR_REWARD_BOOK_SPECIAL_NULL);
		if(reward.getBookFirst()==null||reward.getBookFirst()==0)
			this.addFieldError(GlobalInfo.ERROR_INFO, RewardInfo.ERROR_REWARD_BOOK_FIRST_NULL);
		if(reward.getBookSecond()==null||reward.getBookSecond()==0)
			this.addFieldError(GlobalInfo.ERROR_INFO, RewardInfo.ERROR_REWARD_BOOK_SECOND_NULL);
		if(reward.getBookThird()==null||reward.getBookThird()==0)
			this.addFieldError(GlobalInfo.ERROR_INFO, RewardInfo.ERROR_REWARD_BOOK_THIRD_NULL);
		Reward oldReward = (Reward)dao.query(Reward.class, (String)session.get(TMP_ENTITY_ID));
		if(!oldReward.getYear().equals(reward.getYear())) {
			if(rewardService.checkReward(reward))
				this.addFieldError(GlobalInfo.ERROR_INFO, RewardInfo.ERROR_REWARD_EXIST);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public String view() {
		if(entityId!=null) {
			reward = (Reward) rewardService.viewReward(entityId);// 获取奖励
		} else {
			jsonMap.put(GlobalInfo.ERROR_INFO, RewardInfo.ERROR_REWARD_ID_NULL);
			return INPUT;
		}
		if (reward == null) {// 奖励不存在，返回错误提示
			jsonMap.put(GlobalInfo.ERROR_INFO, RewardInfo.ERROR_REWARD_NULL);
			return INPUT;
		} else {// 学科存在，存入jsonMap
			jsonMap.put("reward", reward);
			return SUCCESS;
		}
	}
	
	@Override
	public String toView() {
		return SUCCESS;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Object[] simpleSearchCondition() {
		keyword = (keyword == null) ? "" : keyword.toLowerCase().trim();// 预处理关键字
		StringBuffer hql = new StringBuffer();
		Map map = new HashMap();
		hql.append(HQL);
		if (keyword != null && !keyword.isEmpty()) {
			hql.append(" and ");
			if (searchType == 1) { //初级 检索条件为年份
				hql.append("LOWER(r.year) like :keyword");
				map.put("keyword", "%" + keyword + "%");
			} else if (searchType == 2) { //初级检索条件为奖金
				hql.append(
					"r.paperSpecial like :keyword or " +
					"r.paperFirst like :keyword or "   +
					"r.paperSecond like :keyword or "  +
					"r.paperThird like :keyword or "   +
					"r.bookSpecial like :keyword or "  +
					"r.bookFirst like :keyword or "    +
					"r.bookSecond like :keyword or "   +
					"r.bookThird like :keyword"
				);
				map.put("keyword", "%" + keyword + "%");
			} else {
				hql.append(
					"LOWER(r.year) like :keyword or "  +
					"r.paperSpecial like :keyword or " +
					"r.paperFirst like :keyword or "   +
					"r.paperSecond like :keyword or "  +
					"r.paperThird like :keyword or "   +
					"r.bookSpecial like :keyword or "  +
					"r.bookFirst like :keyword or "    +
					"r.bookSecond like :keyword or "   +
					"r.bookThird like :keyword"
					);
				map.put("keyword", "%" + keyword + "%");
			}
		}
		// 调用初级检索功能
		return new Object[]{
			hql.toString(),
			map,
			0,
			GlobalInfo.ERROR_EXCEPTION_INFO
		};
	}

	@Override
	public Object[] advSearchCondition() {
		return null;
	}

	@Override
	public String pageName() {
		return this.PAGE_NAME;
	}

	@Override
	public String pageBufferId() {
		// TODO Auto-generated method stub
		return this.PAGE_BUFFER_ID;
	}

	@Override
	public String[] sortColumn() {
		return this.SORT_COLUMNS;
	}

	@Override
	public String dateFormat() {
		return this.DATE_FORMAT;
	}

	public Reward getReward() {
		return reward;
	}

	public void setReward(Reward reward) {
		this.reward = reward;
	}
}
