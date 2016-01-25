package csdc.service.imp;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import csdc.model.Account;
import csdc.model.Expert;
import csdc.model.Groups;
import csdc.model.Person;
import csdc.model.Product;
import csdc.model.ProductReview;
import csdc.service.ISecondReviewService;
import csdc.tool.StringTool;
@Service
public class SecondReviewService extends BaseService implements
		ISecondReviewService {

	@Override
	public Groups getExpertGroupByAccount(Account account) {
		Groups group = null;
		if(account.getType()==4||account.getType()==5) {
			Person person = account.getPerson();
			String hql = "from Expert ex where ex.person=:person";
			Map map = new HashMap();
			map.put("person", person);
			List list = dao.query(hql,map);
			Expert expert = (Expert)list.get(0);
			group = expert.getGroups();
		}
		return group;
	}

	@Override
	public Expert getExpertByAccount(Account account) {
		Expert expert = null;
		if(account.getType()==4||account.getType()==5) {
			Person person = account.getPerson();
			String hql = "from Expert ex where ex.person=:person";
			Map map = new HashMap();
			map.put("person", person);
			List list = dao.query(hql,map);
			expert = (Expert)list.get(0);
		}
		return expert;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List addChiefReviewInfo(List laData, Expert expert) {
		List newlaData = new ArrayList();
		List chifData = new ArrayList();
		List otherData = new ArrayList();
		String[] item;
		int isChief;//是否为主审
		String productId;
		for(Object data: laData) {
			String[] oitem = (String[])data;
			item = new String[oitem.length+3];
			productId = oitem[0];
			int len = oitem.length;
			for(int i=0; i<len; i++) {
				item[i] = oitem[i];
			}
			item[len] = this.getCoAuthor(productId);
			item[len+1] = this.isReviewed(expert, productId)? "1":"0";
			if(this.isChiefReviewer(productId, expert)) {
				isChief = 1;
				item[len+2] = new Integer(isChief).toString();
				chifData.add(item);
			} else {
				isChief = 0;
				item[len+2] = new Integer(isChief).toString();
				otherData.add(item);
			}
		}
		for(Object ob : chifData) {
			newlaData.add(ob);
		}
		for(Object ob : otherData) {
			newlaData.add(ob);
		}
		return newlaData;
	}
	
	@Override
	public List addReviewInfo(List laData) {
		List newlaData = new ArrayList();
		String[] item;
		for(Object data: laData) {
			String[] oitem = (String[])data;
			item = new String[oitem.length+3];
			int len = oitem.length;
			String productId = oitem[0];
			for(int i=0; i<len; i++) {
				item[i] = oitem[i];
			}
			item[len] = this.getCoAuthor(productId);
			int reviewedExpertNum = this.getReviewedExpertNum(productId);
			item[len+1] = new Integer(reviewedExpertNum).toString();
			int allExpertNum = this.getAllExpertNum(productId);
			item[len+2] = new Integer(allExpertNum).toString();
			newlaData.add(item);
		}
		return newlaData;
	}
	
	//判断该成果的主审专家是否为该专家
	private boolean isChiefReviewer(String productId, Expert expert) {
		boolean isChief = false ;
		Product product = dao.query(Product.class, productId);
		if(product.getChiefReviewer().getId().equals(expert.getId()))
			isChief = true;
		return isChief;
	}
	
	/**
	 * 获取成果合作者姓名
	 */
	private String getCoAuthor(String productId) {
		String coAuthor = "";
		String hql = "select pa.person.name from ProductAuthor pa where pa.product.id = :productId and pa.isFirstAuthor=0";
		Map map = new HashMap();
		map.put("productId", productId);
		List<String> author = dao.query(hql, map);
		coAuthor = StringTool.join(author, "; ");
		return coAuthor;
	}
	
	@Override
	public boolean isSecondReviwer(Account account) {
		boolean flag = true;
		if(!(account.getType()==4||account.getType()==5)) {
			return false;
		}
		Expert expert = this.getExpertByAccount(account);
		if(expert.getIsRecommend()!=1) {
			flag = false;
		}
		if(expert.getIsReviewer()!=1) {
			flag = false;
		}
		if(expert.getReviewerType()!=1) {
			flag = false;
		}
		if(expert.getReviewerYears()==null) {
			flag = false;
		} else {
			String currentYear = new SimpleDateFormat("yyyy").format(new Date());
			if(!expert.getReviewerYears().contains(currentYear)) {
				flag = false;
			}
		}
		return flag;
	}

	@Override
	public boolean isChiefReviewer(Expert expert, String productId) {
		boolean isChief = false;
		Product product = dao.query(Product.class, productId);
		Expert chiefReviewer = product.getChiefReviewer();
		if(chiefReviewer!=null) {
			if(chiefReviewer.getId().equals(expert.getId()))
				isChief = true;
		}
		return isChief;
	}
	
	@Override
	public Expert getChiefReviewer(String productId) {
		Product product = dao.query(Product.class, productId);
		Expert chiefReviewer = product.getChiefReviewer();
		return chiefReviewer;
	}

	@Override
	public boolean isReviewed(Expert expert, String productId) {
		boolean isReviewed = false;
		Product product = dao.query(Product.class, productId);
		String  hql = "from ProductReview pr where pr.product = :product and pr.expert =:expert and pr.type=1";
		Map map = new HashMap();
		map.put("product", product);
		map.put("expert", expert);
		List list = dao.query(hql, map);
		if(!list.isEmpty()) {
			if(product.getChiefReviewer().getId().equals(expert.getId())) {
				if(product.getRewardLevel()!=null) {
					isReviewed = true;
				}
			} else {
				isReviewed = true;
			}
		}
		return isReviewed;
	}

	public int getReviewedExpertNum(String productId) {
		int num = 0;
		String hql = "from ProductReview pr where pr.product.id = :productId and pr.type=1";
		Map map = new HashMap();
		map.put("productId", productId);
		num = (int)dao.count(hql, map);
		return num;
		
	}
	public int getAllExpertNum(String produtId) {
		int num;
		Product product = dao.query(Product.class, produtId);
		Groups groups = product.getGroups();
		String hql = "from Expert exp where exp.groups =:groups and exp.isRecommend=1 and exp.isReviewer=1 and exp.reviewerType=1";
		Map map = new HashMap();
		map.put("groups", groups);
		num = (int)dao.count(hql, map);
		return num;
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<String[]> getReviewOpinionByExpertId(String productId, List<String> expertId) {
		List<String[]> opinion = new ArrayList<String[]>();
		Product product = dao.query(Product.class, productId);
		String chiefExpertId = product.getChiefReviewer()!=null?product.getChiefReviewer().getId(): null;
		String hql = "select pr.opinion, pr.expert.person.name, pr.expert.id from ProductReview pr where pr.product = :product and pr.type = 1 and pr.expert.id in (:expertId)";
		Map map = new HashMap();
		map.put("product", product);
		map.put("expertId", expertId);
		List<Object[]> list = dao.query(hql, map);
		List<String> reviewedExpertId = new ArrayList<String>();
		for(Object[] ob : list) {
			String[] opin = new String[ob.length];
			for(int i=0; i<ob.length; i++) {
				if(ob[i]==null) {
					opin[i] = "";
				} else {
					if(i==ob.length-1) {
						if(chiefExpertId!=null) {
							opin[i] = ob[i].toString().equals(chiefExpertId)? "1":"0";
						} else {
							opin[i] = "0";
						}
						reviewedExpertId.add(ob[i].toString());//将已评过专家的id放进已评专家id列表
					}else {
						opin[i] = ob[i].toString();
					}
				}
			}
			opinion.add(opin);
		}
		for(String id : expertId) {
			//找出未评专家
			if(!reviewedExpertId.contains(id)) {
				String[] opin = new String[3];
				opin[0] = "";
				opin[1] = ((Expert)dao.query(Expert.class, id)).getPerson().getName();
				if(chiefExpertId!=null) {
					opin[2] = id.equals(chiefExpertId)? "1":"0";
				} else {
					opin[2] = "0";
				}
				opinion.add(opin);
			}
		}
		
		return opinion;
	}

	@Override
	public int getRewardLevel(String productId) {
		int rewardLevel = 0;
		Product product = dao.query(Product.class, productId);
		if(product.getRewardLevel()!=null)
			rewardLevel = product.getRewardLevel();
		return rewardLevel;
	}

	@Override
	public boolean isBacked(String productId) {
		Product product = dao.query(Product.class, productId);
		int leaderAuditResult = 0;
		if(product.getLeaderAuditResult()!=null)
			leaderAuditResult = product.getLeaderAuditResult();
		return leaderAuditResult==1? true : false;
	}
	
	@Override
	public int getLeaderAuditResult(String productId) {
		Product product = dao.query(Product.class, productId);
		int leaderAuditResult = product.getLeaderAuditResult()!=null? product.getLeaderAuditResult():0;
		return leaderAuditResult;
	}

	@Override
	public boolean isReviewedByAllExpert(String productId) {
		boolean isAllReviewed =  true;
		Product product = dao.query(Product.class, productId);
		Groups groups = product.getGroups();
		List<Expert> reviewer = this.getReviewerByGroups(groups);
		for(Expert expert : reviewer) {
			if(!this.isReviewed(expert, productId)) {
				isAllReviewed = false;
				break;
			}
		}
		return isAllReviewed;
	}
	
	public List<Expert> getReviewerByGroups(Groups groups) {
		List<Object[]> list =  new ArrayList();
		List<Expert> data = new ArrayList<Expert>();
		String hql = 
				"from Expert exp"
				+" where exp.groups =:groups and exp.isRecommend=1 and exp.isReviewer=1 and exp.reviewerType=1";
		Map map = new HashMap();
		map.put("groups", groups);
		list = dao.query(hql, map);
		if(!list.isEmpty()) {
			for (Object e : list) {
				data.add((Expert)e);
			}
		}
		return data;
	}
	
	@Override
	public List<String> getReviewerIdByProductId(String	productId) {
		List<String> reviewerId = new ArrayList<String>();
		Product product = dao.query(Product.class, productId);
		List<Expert> reviewer = this.getReviewerByGroups(product.getGroups());
		for(Expert expert : reviewer) {
			reviewerId.add(expert.getId());
		}
		return reviewerId;
	}

	@Override
	public int getProductStatusbyId(String productId) {
		Product product = dao.query(Product.class, productId);
		return product.getStatus();
	}

	@Override
	public void addChiefReview(String productId, Expert expert, int rewardLevel,
			String reviewOpinion) {
		Product product = dao.query(Product.class, productId);
		product.setRewardLevel(rewardLevel);
		dao.modify(product);
		ProductReview review = new ProductReview();
		review.setType(1);
		review.setProduct(product);
		review.setGroupName(product.getGroupName());
		review.setExpert(expert);
		review.setOpinion(reviewOpinion);
		dao.add(review);
	}

	@Override
	public void modifyChiefReview(String productId, Expert expert,
			int rewardLevel, String reviewOpinion) {
		Product product = dao.query(Product.class, productId);
		product.setRewardLevel(rewardLevel);
		dao.modify(product);
		String updateHql = 
				"update ProductReview pr set pr.opinion = :reviewOpinion where pr.product = :product and pr.expert = :expert and pr.type = 1";
		Map map = new HashMap();
		map.put("reviewOpinion", reviewOpinion);
		map.put("product", product);
		map.put("expert", expert);
		dao.execute(updateHql, map);
	}

	@Override
	public boolean isProductReviewer(Expert expert, String productId) {
		boolean flag = true;
		Product product = dao.query(Product.class, productId);
		if(expert.getIsRecommend()!=1)
			flag = false;
		if(expert.getIsReviewer()!=1)
			flag = false;
		if(expert.getReviewerType()!=1)
			flag = false;
		if(!product.getGroups().getName().equals(expert.getGroups().getName()))
			flag = false;
		return flag;
	}

	@Override
	public void addReview(String productId, Expert expert, String reviewOpinion) {
		Product product = dao.query(Product.class, productId);
		ProductReview review = new ProductReview();
		review.setType(1);
		review.setProduct(product);
		review.setGroupName(product.getGroupName());
		review.setExpert(expert);
		review.setOpinion(reviewOpinion);
		dao.add(review);
	}

	@Override
	public void modifyReview(String entityId, Expert expert,
			String reviewOpinion) {
		Product product = dao.query(Product.class, entityId);
		String updateHql = 
				"update ProductReview pr set pr.opinion = :reviewOpinion where pr.product = :product and pr.expert = :expert and pr.type = 1";
		Map map = new HashMap();
		map.put("reviewOpinion", reviewOpinion);
		map.put("product", product);
		map.put("expert", expert);
		dao.execute(updateHql, map);
	}

	@Override
	public boolean isGroupLeader(Expert expert, String entityId) {
		boolean flag = true;
		if(!this.isProductReviewer(expert, entityId))
			flag = false;
		if(expert.getIsGroupLeader()!=1)
			flag = false;
		return flag;
	}

	@Override
	public void audit(String entityId, int leaderAuditResult) {
		Product product = dao.query(Product.class, entityId);
		product.setLeaderAuditResult(leaderAuditResult);
		dao.modify(product);
	}

	@Override
	public void submit(String entityId) {
		Product product = dao.query(Product.class, entityId);
		int rewardLevel = product.getRewardLevel();
		if(rewardLevel>0&&rewardLevel<5)
			product.setStatus(7);
		dao.modify(product);
	}

	@Override
	public boolean startReview(String productId) {
		boolean start = false;
		String hql = "from ProductReview pr where pr.type=1 and pr.product.id=:productId";
		Map map = new HashMap();
		map.put("productId", productId);
		List<ProductReview> list = dao.query(hql, map);
		if(!list.isEmpty())
			start = true;
		Product product = dao.query(Product.class, productId);
		if(product.getRewardLevel()!=null&&product.getRewardLevel()>0)
			start = true;
		return start;
	}

	@Override
	public void back(List<String> productIds) {
		StringBuffer hql = new StringBuffer();
		hql.append("update Product pr set pr.hsasReviewAuditResult=3, pr.updateDate = :updateDate, pr.status=5 where pr.id in :productIds");
		Map map = new HashMap();
		map.put("updateDate", new Date());
		map.put("productIds", productIds);
		dao.execute(hql.toString(), map);
	}
}
