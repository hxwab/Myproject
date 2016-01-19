package csdc.service.imp;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import csdc.model.Account;
import csdc.model.Expert;
import csdc.model.Groups;
import csdc.model.Person;
import csdc.model.Product;
import csdc.model.ProductReview;
import csdc.service.IFirstReviewService;
import csdc.tool.HSSFExport;
import csdc.tool.StringTool;
@Service
public class FirstReviewService extends BaseService implements
		IFirstReviewService {
	//初评选项得分多维矩阵 3*5*4
	private static final int[][][] scoresMartix = 
		{
			{
				{30, 25, 20, 15},
				{20,15,10},
				{30, 25,20},
				{20, 15, 10}
			},
			{
				{30, 25, 20, 15},
				{10, 8, 5},
				{30, 25, 20, 15},
				{30, 28, 25, 20}
			},
			{
				{30, 25, 20, 15},
				{20, 15, 10},
				{20, 15},
				{20, 15},
				{10, 5}
			}
		};

	@Override
	public Groups getExpertGroupByAccount(Account account) {
		Groups group = null;
		if(account.getType()==6) {
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
	@Transactional
	public List addExternalInfo(List laData, Account account) {
		List newlaData = new ArrayList();
		String[] item;
		int reviewedExpertNum;//已评专家个数
		int productType; //初评成果类型 [1:论文基础类  2：论文应用对策类  3：著作类]
		int accountType=0;//账户类型[1:管理员账户 2:专家账户]
		if(account.getType()<=2||account.getIsSuperUser()==1) {
			accountType = 1;
		} else if(account.getType()==6){//如果是专家账号
			accountType = 2;
		}
		String productId;
		for(Object data: laData) {
			if(data!=null) {
				String[] oitem = (String[])data;
				item = new String[oitem.length+4];
				productId = oitem[0];
				int len = oitem.length;
				for(int i=0; i<len; i++) {
					item[i] = oitem[i];
				}
				item[len] = this.getCoAuthor(productId);
				productType = getProductTypeByProductId(productId);
				item[len+1] = new Integer(productType).toString();
				if(accountType==1) {
					item[len+2] = isReviewedByAllExpert(item[0])? "1":"0";
					reviewedExpertNum = getReviewedExpertNumByProductId(productId);
					item[len+3] = new Integer(reviewedExpertNum).toString();
				}else if(accountType==2) {
					item[len+2] = isReviewedByExpert(item[0], getExpertByAccount(account))? "1":"0";
				}
				newlaData.add(item);
			}
		}
		return newlaData;
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
	/**
	 * 判断成果是否被该专家评过
	 * @param productId
	 * @param expert
	 * @return
	 */
	private boolean isReviewedByExpert(String productId, Expert expert) {
		boolean flag = true;
		String hql = "from ProductReview pr where pr.product.id=:productId and pr.expert=:expert and pr.type=0 and pr.status>=1";
		Map map = new HashMap();
		map.put("productId", productId);
		map.put("expert", expert);
		List list = dao.query(hql, map);
		if(list.isEmpty())
			flag = false;
		return flag;
	}

	/**
	 * 根据成果id获得成果的初评类型  [1:论文基础类  2：论文应用对策类  3：著作类]
	 * @param productId
	 * @return
	 */
	public int getProductTypeByProductId(String productId) {
		int productType = 0;
		Product product = dao.query(Product.class, productId);
		String type = product.getType();
		String researchType = product.getResearchType();
		if(type.equals("著作")) {
			productType = 3;
		} else {
			if(researchType.equals("基础类"))
				productType = 1;
			else
				productType = 2;
		}
		return productType;
	}

	/**
	 *根据成果id获得已初评专家数目
	 */
	public int getReviewedExpertNumByProductId(String productId) {
		Product product = dao.query(Product.class, productId);
		String hql = "from ProductReview pr where pr.product=:product and pr.type=0 and pr.status=2";
		Map map = new HashMap();
		map.put("product", product);
		int number = (int) dao.count(hql, map);
		return number;
	}

	@Override
	public boolean isFirstReviewer(Account account) {
		boolean flag = true;
		if(account.getType()!=6) {
			return false;
		}
		Expert expert = this.getExpertByAccount(account);
		if(expert.getIsRecommend()!=1) {
			flag = false;
		}
		if(expert.getIsReviewer()!=1) {
			flag = false;
		}
		if(expert.getReviewerType()!=0) {
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
	public Expert getExpertByAccount(Account account) {
		Expert expert = null;
		if(account.getType()==6) {
			Person person = account.getPerson();
			String hql = "from Expert exp where exp.person=:person";
			Map map = new HashMap();
			map.put("person", person);
			List list = dao.query(hql,map);
			expert = (Expert)list.get(0);
		}
		return expert;
	}

	@Override
	public boolean isUnderExpertReview(Expert expert, String entityId) {
		boolean flag = false;
		Product product = dao.query(Product.class, entityId);
		Groups expertGroup = expert.getGroups();
		Groups productGroup = product.getGroups();
		if(expertGroup.getName().equals(productGroup.getName()))
			flag = true;
		return flag;
	}

	@Override
	public boolean isReviewedByAllExpert(String entityId) {
		boolean flag = true;
		Product product = dao.query(Product.class, entityId);
		if(product==null) {//如果成果不存在，也返回是
			return true;
		}
		Groups group = product.getGroups();
		List<String> allReviewedExpertId = getAllReviewedExpertId(product);
		List<String> allShouldReviewExpertId = getAllFirstReviewExpertIdByGroup(group);
		for(String shouldId : allShouldReviewExpertId) {
			if(!allReviewedExpertId.contains(shouldId)) {
				flag = false;
				break;
			}
		}
		return flag;
	}

	private List<String> getAllFirstReviewExpertIdByGroup(Groups group) {
		List<Object[]> list = new ArrayList();
		List<String> ids = new ArrayList<String>();
		String hql = 
				"select ex.id"
				+" from Expert ex"
				+" where ex.groups=:groups and ex.isRecommend=1 and ex.isReviewer=1 and ex.reviewerType=0";
		Map map = new HashMap();
		map.put("groups", group);
		list = dao.query(hql, map);
		for (Object p : list) {
			String id = (String)p;
			ids.add(id);
		}
		return ids;
	}

	private List<String> getAllReviewedExpertId(Product product) {
		List<Object[]> list = new ArrayList();
		List<String> ids = new ArrayList<String>();
		String hql = 
				"select pr.expert.id"
				+" from ProductReview pr"
				+" where pr.product=:product and pr.type=0 and pr.status=2";
		Map map = new HashMap();
		map.put("product", product);
		list = dao.query(hql, map);
		for (Object p : list) {
			String id = (String)p;
			ids.add(id);
		}
		return ids;
	}

	@Override
	public int getProductStatusbyId(String entityId) {
		Product product = dao.query(Product.class, entityId);
		return product.getStatus();
	}

	@Override
	public void addFirstReviewResult(Expert expert, String entityId, int type,
			int[] firstReviewScores, String firstReviewOpinion) {
		ProductReview pr;
//		int[] scores = new int[5]; //初评结果各项得分
//		int index = type==3? 5: 4;
//		for(int i = 0; i<index; i++) {
//			scores[i] = scoresMartix[type-1][i][firstReviewScores[i]-1];
//		}
		Product product = dao.query(Product.class, entityId);
		String hql = "from ProductReview pr where pr.product=:product and pr.expert=:expert and pr.type=0";
		Map map = new HashMap();
		map.put("product", product);
		map.put("expert", expert);
		List list = dao.query(hql, map);
		if(!list.isEmpty()) {
			pr = (ProductReview)list.get(0);
		} else {
			pr = new ProductReview();
		}
		pr.setProduct(product);
		pr.setGroupName(product.getGroupName());
		pr.setExpert(expert);
		pr.setType(0);
		pr.setOpinion(firstReviewOpinion);
		pr.setCreativityScore(firstReviewScores[0]);
		pr.setResearchScore(firstReviewScores[1]);
		pr.setJournalScore(firstReviewScores[2]);
		pr.setQuoteScore(firstReviewScores[3]);
		pr.setStatus(1);
		if(type==3)
			pr.setSocialEffectScore(firstReviewScores[4]);
		dao.modify(pr);
	}

	@Override
	public void submitFirstReview(String entityId) {
		Product product = dao.query(Product.class, entityId);
		int averageScore = caculateFirstReviewAverageScore(product);
		product.setAverageScore(averageScore);
		product.setStatus(5);
		product.setUpdateDate(new Date());
		dao.modify(product);
	}

	private int caculateFirstReviewAverageScore(Product product) {
		int averageScore =0;
		int totalScores = 0;
		String hql = "select pr.creativityScore, pr.researchScore, pr.journalScore, pr.quoteScore, pr.socialEffectScore"
				+" from ProductReview pr"
				+" where pr.type=0 and pr.product=:product";
		Map map = new HashMap();
		map.put("product", product);
		List<Object[]> list = dao.query(hql, map);
		int size = list.size();
		for(Object[] scores : list) {
			int score1 = (Integer)scores[0];
			int score2 = (Integer)scores[1];
			int score3 = (Integer)scores[2];
			int score4 = (Integer)scores[3];
			int score5 = scores[4]!=null? (Integer)scores[4]:0;
			int singleRevciewScore = score1 + score2 +score3 +score4 +score5;
			totalScores += singleRevciewScore;
		}
		averageScore = totalScores/size;
		return averageScore;
	}

	@Override
	public void audit(int scoreLine) {
		String hql = "update Product pr set pr.hsasReviewAuditResult=2, pr.status=6, pr.updateDate = :updateDate" +
				" where pr.status=5 and pr.applyYear=:currentYear and pr.averageScore>=:scoreLine";
		Map map = new HashMap();
		map.put("updateDate", new Date());
		String currentYear = new SimpleDateFormat("yyyy").format(new Date());
		map.put("currentYear", currentYear);
		map.put("scoreLine",scoreLine);
		try {
			dao.execute(hql, map);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	@Override
	public boolean isAllReviewedByExpert(Expert expert) {
		boolean flag = true;
		String hql = "select pr.id from Product pr where pr.status=4 and pr.groups.id = :groupsId and pr.applyYear = :currentYear";
		String currentYear = new SimpleDateFormat("yyyy").format(new Date());
		Map map = new HashMap();
		map.put("groupsId", expert.getGroups().getId());
		map.put("currentYear", currentYear);
		List<String> productIds = dao.query(hql, map);
		for(String produtId: productIds) {
			if(!this.isReviewedByExpert(produtId, expert)) {
				flag = false;
				break;
			}
		}
		return flag;
	}
	
	@Override
	public void submitByExpert(Expert expert) {
		String selectHql = "select pr.id from Product pr where pr.status=4 and pr.groups.id = :groupsId and pr.applyYear = :currentYear";
		String currentYear = new SimpleDateFormat("yyyy").format(new Date());
		Map map = new HashMap();
		map.put("groupsId", expert.getGroups().getId());
		map.put("currentYear", currentYear);
		List<String> productIds = dao.query(selectHql, map);
		String udpateHql = "update ProductReview pr set pr.status=2 where pr.product.id in :productId and pr.expert.id=:expertId and pr.type=0";
		map.clear();
		map.put("productId", productIds);
		map.put("expertId", expert.getId());
		dao.execute(udpateHql, map);
	}

	@Override
	public boolean isSubmitByAllExpert(Groups group) {
		boolean flag = true;
		String hql = "select pr.id from Product pr where pr.status=4 and pr.groups = :groups and pr.applyYear = :currentYear";
		String currentYear = new SimpleDateFormat("yyyy").format(new Date());
		Map map = new HashMap();
		map.put("groups", group);
		map.put("currentYear", currentYear);
		List<String> productIds = dao.query(hql, map);
		String hql2 = "from Expert ex where ex.groups =:groups and ex.isRecommend = 1 and ex.isReviewer =1 and ex.reviewerType = 0";
		map.remove("currentYear");
		List<Expert> experts = dao.query(hql2, map);
		loop1: for(Expert expert:experts) {
			String hql3 = "select pr.product.id from ProductReview pr where pr.product.id in :productIds and pr.expert=:expert and pr.type=0 and pr.status=2";
			map.clear();
			map.put("productIds", productIds);
			map.put("expert", expert);
			List<String> submitProductIds = dao.query(hql3, map);
			if(submitProductIds.isEmpty()) {
				flag = false;
				break loop1;
			}
			for(String productId : productIds) {
				if(!submitProductIds.contains(productId)) {
					flag = false;
					break loop1;
				}
			}
		}
		return flag;
	}

	@Override
	public void submitFirstReviewByGroup(Groups group) {
		String hql = "select pr.id from Product pr where pr.status=4 and pr.groups = :groups and pr.applyYear = :currentYear";
		String currentYear = new SimpleDateFormat("yyyy").format(new Date());
		Map map = new HashMap();
		map.put("groups", group);
		map.put("currentYear", currentYear);
		List<String> productIds = dao.query(hql, map);
		for(String productId : productIds) {
			this.submitFirstReview(productId);
		}
	}

	@Override
	public boolean hasSubmitByExpert(Expert expert) {
		boolean flag = false;
		String hql = "select pr.id from Product pr where pr.status>=4 and pr.groups = :groups and pr.applyYear = :currentYear";
		String currentYear = new SimpleDateFormat("yyyy").format(new Date());
		Map map = new HashMap();
		map.put("groups", expert.getGroups());
		map.put("currentYear", currentYear);
		List<String> productIds = dao.query(hql, map);
		String hql2 = "select pr.id from ProductReview pr where pr.product.id in :productIds and pr.expert=:expert and pr.type=0 and pr.status=2";
		map.clear();
		map.put("productIds", productIds);
		map.put("expert", expert);
		List<String> reviewIds = dao.query(hql2, map);
		if(!reviewIds.isEmpty())
			flag = true;
		return flag;
	}

	@Override
	public InputStream getExpertSignExcel(Expert expert) {
		String header = "";//表头
		List dataList = new ArrayList();
		String tail = "专家签字：___________";
		String expertName = expert.getPerson().getName();
		String groupName = expert.getGroups().getName();
		header = "初评 "+groupName+"组 "+expertName+"专家签字表";
		String[] title = new String[]{};//标题
			title = new String[]{
					"序号",
					"评审编号",
					"成果名称",
					"类型",
					"打分"
				};
		String hql = "select pr.id, pr.reviewNumber, pr.name, pr.type " +
				"from Product pr where pr.groups=:groups and pr.applyYear=:currentYear and pr.status>=4";
		Map map = new HashMap();
		map.put("groups", expert.getGroups());
		String currentYear = new SimpleDateFormat("yyyy").format(new Date());
		map.put("currentYear", currentYear);
		List<Object[]> data = dao.query(hql, map);
		int index = 0;
		for(Object[] product : data) {
			Object[] row = new Object[5];
			row[0] = ++index;
			row[1] = product[1]!=null?product[1].toString():"";
			row[2] = product[2].toString();
			row[3] = product[3].toString();
			row[4] = this.getScore(expert, product[0].toString());
			dataList.add(row);
		}
		int[] columWith = {10, 12, 30, 12, 8};
		return HSSFExport.ExportExpertExcel(dataList, header, title, tail, columWith);
	}
	
	private int getScore(Expert expert, String productId) {
		int score = 0;
		String hql = "select pr.creativityScore, pr.researchScore, pr.journalScore, pr.quoteScore, pr.socialEffectScore"
				+" from ProductReview pr"
				+" where pr.type=0 and pr.product.id=:productId and pr.expert=:expert";
		Map map = new HashMap();
		map.put("productId", productId);
		map.put("expert", expert);
		List<Object[]> list = dao.query(hql, map);
		Object[] scor = list.get(0);
		for(int i=0; i<scor.length; i++) {
			if(scor[i]!=null)
				score +=(Integer)scor[i];
		}
		return score;
	}
}
