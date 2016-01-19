package csdc.action.product;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import csdc.action.BaseAction;
import csdc.model.Account;
import csdc.model.Expert;
import csdc.model.Groups;
import csdc.service.IFirstReviewService;
import csdc.tool.HSSFExport;
import csdc.tool.StringTool;
import csdc.tool.info.FirstReviewInfo;
import csdc.tool.info.GlobalInfo;
/**
 * 初评
 * @author Yaoyota
 *
 */
@Component
@Scope(value="prototype")
@SuppressWarnings("unchecked")
public class FirstReviewAction extends BaseAction {

	private static final long serialVersionUID = -152450308408337180L;
	private static final String TMP_ENTITY_ID = "firstReviewProductId";// 缓存与session中，备用的成果ID变量名称
	private static final String DATE_FORMAT = "yyyy-MM-dd";// 列表时间格式
	private static final String PRODUCT_YEAR = "productYear";//session中成果年份字段的key
	private static final String PAGE_NAME = "firstReviewPage";
	private static final String PAGE_BUFFER_ID = "pr.id";// 上下条查看时用于查找缓存的字段名称
	//成果id 
	private static final String HQL =  
			"select pr.id, pr.reviewNumber, pr.name, pr.authorName, pr.agencyName, pr.groupName, pr.type, pr.publishName, pr.publishDate from Product pr where 1=1";// 上下条查看时用于查找缓存的字段名称HQL
	private static final String[] SORT_COLUMNS = new String[] {
		"pr.reviewNumber",
		"pr.name",
		"pr.authorName",
		"pr.agencyName",
		"pr.groupName",
		"pr.type",
		"pr.publishName",
		"pr.publishDate"
		};
	
	private static final int SEARCH_TYPE_MAX = 7;
	private int productType; //成果的初评类型[1:论文基础类  2：论文应用对策类  3：著作类]
	private int score1; //成果的创新程度得分
	private int score2; //成果的研究方法得分
	private int score3; //成果的发表报刊级别得分
	private int score4; //成果的转载/应用采纳/引用情况得分
	private int score5; //成果的社会评价得分
	private String firstReviewOpinion; //成果的初评意见
	private int hasSubmit; //专家已经提交
	@Autowired
	private IFirstReviewService firstReviewService;
	private int accountType;//账户类型[1:管理员账户 2:专家账户]
	private String year;//年份字段
	private String fileName;//下载文件名
	private int scoreLine;//复评分数线
	private String expertId;//专家Id
	private String keyword1;
	private String keyword2;
	private String keyword3;
	private String keyword4;
	private String keyword5;
	private String keyword6;
	private String keyword7;
	@Override
	public String toList() {
		Account account = loginer.getAccount();
		if(account.getType()<=2||account.getIsSuperUser()==1) {
			accountType = 1;
		} else if(account.getType()==6){//如果是专家账号
			accountType = 2;
			Expert expert = firstReviewService.getExpertByAccount(account);
			hasSubmit = firstReviewService.hasSubmitByExpert(expert)?1:0;
		} else {
			this.addFieldError(GlobalInfo.ERROR_INFO, GlobalInfo.ERROR_NO_RIGHT_INFO);
		}
		return SUCCESS;
	}
	@Override
	public String list() {
		super.list();
		Account account = loginer.getAccount();
		try {
			laData = firstReviewService.addExternalInfo(laData, account);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		jsonMap.remove("laData");
		jsonMap.put("laData", laData);
		return SUCCESS;
	}
	
	/**
	 * 进入列表之前的校验，进行一些权限判断
	 */
	public void validateList() {
		//当前账户类型为专家时，判断该专家是否为当年初评专家
		Account account = loginer.getAccount();
		if(account.getType()==6) {
			if(!firstReviewService.isFirstReviewer(account)) {
				this.addFieldError(GlobalInfo.ERROR_INFO, GlobalInfo.ERROR_NO_RIGHT_INFO);
			}
		}else if (account.getType()<=2||account.getIsSuperUser()==1) {
		}else {
			this.addFieldError(GlobalInfo.ERROR_INFO, GlobalInfo.ERROR_NO_RIGHT_INFO);
		}
	}
	
	public String toFirstReview() {
		return SUCCESS;
	}
	
	public String exportListData() {
		return SUCCESS;
	}
	
	public InputStream getListDataStream() throws UnsupportedEncodingException {
		String header = "初评成果表";//表头
		
		String[] title = new String[]{};//标题
			title = new String[]{
					"序号",
					"评审编号",
					"成果名称",
					"作者",
					"机构名",
					"成果分组名",
					"成果类型",
					"发表刊物",
					"发表时间",
					"合作者",
					"初评类型",
					"是否评完",
					"初评专家数"
				};
		String applyYear = new SimpleDateFormat("yyyy").format(new Date());
		if(session.containsKey(PRODUCT_YEAR))
			applyYear = (String) session.get(PRODUCT_YEAR);
		header = applyYear+"年"+ header;
		fileName =header + ".xls";
		fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
		this.list();
		int index = 1;
		List dataList = new ArrayList();
		for (Object object : laData) {
			String[] o = (String[]) object;
			Object[] data = new Object[13];
			data[0] = index++;
			for(int i=1; i<=9; i++) {
				data[i] = o[i];
			}
			switch (new Integer(o[10])) {
			case 1:
				data[10] = "论文基础类";//成果类型
				break;
			case 2:
				data[10] = "论文应用对策类";
				break;
			case 3:
				data[10] = "著作类";
				break;
			default:
				break;
			}
			data[11] = o[11].equals("1")? "是":"否";//成果是否被评完
			data[12] = new Integer(o[12]);//成果初评专家数
			dataList.add(data);
		}
		return HSSFExport.commonExportExcel(dataList, header, title);
	}
	
	public InputStream getExpertSignStream() throws UnsupportedEncodingException {
		Expert expert = (Expert)dao.query(Expert.class, expertId);
		String expertName = expert.getPerson().getName();
		String groupName = expert.getGroups().getName();
		String currentYear = new SimpleDateFormat("yyyy").format(new Date());
		fileName = currentYear+"年初评 "+groupName+"组 "+expertName+"专家签字表"+ ".xls";
		fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
		return firstReviewService.getExpertSignExcel(expert);
	}
	/**
	 * 生成单个专家签字表
	 */
	public String getExpertSign() {
		Expert expert = (Expert)dao.query(Expert.class, expertId);
		if(!firstReviewService.hasSubmitByExpert(expert)) {
			jsonMap.put(GlobalInfo.ERROR_INFO, "该专家尚未提交初评结果");
			return "nosubmit";
		}
		return SUCCESS;
	}
	/**
	 * 添加初评
	 * @return
	 */
	@Transactional
	public String addFirstReview() {
		Account account = loginer.getAccount();
		Expert expert = null;
	    //添加初评之前先确定该成果是否在初评阶段
		if(firstReviewService.getProductStatusbyId(entityId)!=4) {	
			this.addFieldError(GlobalInfo.ERROR_INFO, FirstReviewInfo.ERROR_FIRSTREVIEW_END_INFO);
		}
		if(account.getType()==6) {
			//当前账户类型为专家时，判断该专家是否为当年初评专家
			if(!firstReviewService.isFirstReviewer(account)) {
				this.addFieldError(GlobalInfo.ERROR_INFO, GlobalInfo.ERROR_NO_RIGHT_INFO);
			}
			expert = firstReviewService.getExpertByAccount(account);
			//判断该成果是否属于该专家评
			if(!firstReviewService.isUnderExpertReview(expert, entityId)) {
				this.addFieldError(GlobalInfo.ERROR_INFO, GlobalInfo.ERROR_NO_RIGHT_INFO);
			}
		} else {
			this.addFieldError(GlobalInfo.ERROR_INFO, GlobalInfo.ERROR_NO_RIGHT_INFO);
		}
		int[] firstReviewScores = this.dealWithScores();
		firstReviewService.addFirstReviewResult(expert, entityId, productType, firstReviewScores, firstReviewOpinion);
//		if(firstReviewService.isReviewedByAllExpert(entityId)) {
//			firstReviewService.submitFirstReview(entityId); //若该成果已被评完，则自动提交初评结果
//		}
		jsonMap.put("tag", 1);
		return SUCCESS;
	}
	/**
	 * 当前专家提交初评结果
	 * @return
	 */
	@Transactional
	public String submitReview() {
		Account account = loginer.getAccount();
		Expert expert = firstReviewService.getExpertByAccount(account);
		if(!firstReviewService.isAllReviewedByExpert(expert)) {
			jsonMap.put(GlobalInfo.ERROR_INFO, "尚有成果未评");
			return SUCCESS;
		}
		if(firstReviewService.hasSubmitByExpert(expert)) {
			jsonMap.put(GlobalInfo.ERROR_INFO, "已提交过，不能重复提交");
			return SUCCESS;
		}
		firstReviewService.submitByExpert(expert);
		Groups group = expert.getGroups();
		if(firstReviewService.isSubmitByAllExpert(group))
			firstReviewService.submitFirstReviewByGroup(group);
		jsonMap.put("tag", 1);
		return SUCCESS;
	}
	
	private int[] dealWithScores() {
		int[] scores = new int[5];
		scores[0] = score1;
		scores[1] = score2;
		scores[2] = score3;
		scores[3] = score4;
		scores[4] = score5;
		return scores;
	}

	public void validateAddFirstReview() {
		if(entityId==null) {
			this.addFieldError(GlobalInfo.ERROR_INFO, FirstReviewInfo.ERROR_PRODUCT_ID_NULL);
		}
		if(productType==0) {
			this.addFieldError(GlobalInfo.ERROR_INFO, FirstReviewInfo.ERROR_PRODUCT_TYPE_NULL);
		}
		if(score1==0||score2==0||score3==0||score4==0) {
			this.addFieldError(GlobalInfo.ERROR_INFO, FirstReviewInfo.ERROR_PRODUCT_SCORE_NULL);
		}
		if(productType==3) {
			if(score5==0) {
				this.addFieldError(GlobalInfo.ERROR_INFO, FirstReviewInfo.ERROR_PRODUCT_SCORE_NULL);
			}
		}
	}
	
	@Transactional
	public String audit() {
		Account account = loginer.getAccount();
		if(account.getType()>2) {
			this.addFieldError(GlobalInfo.ERROR_INFO, GlobalInfo.ERROR_NO_RIGHT_INFO);
		}
		firstReviewService.audit(scoreLine);
		jsonMap.put("tag", 1);
		return SUCCESS;
	}
	
	@Override
	public Object[] simpleSearchCondition() {
		keyword = (keyword == null) ? "" : keyword.toLowerCase().trim();// 预处理关键字
		StringBuffer hql = new StringBuffer();
		Map map = new HashMap();	
		hql.append(HQL());
		Account account = loginer.getAccount();
		//初评专家只能看到本组的成果
		if(account.getType()==6) {
			hql.append(" and pr.status>=4");//专家评完后成果不消失
			Groups group = firstReviewService.getExpertGroupByAccount(account);
			hql.append(" and pr.groups=:groups");
			map.put("groups", group);
		} else if (account.getType()<=2||account.getIsSuperUser()==1) {
			hql.append(" and pr.status>=4");//社科联管理员和超级管理员可以看到进入初评的所有成果
		}
		if (keyword != null && !keyword.isEmpty()) {
			hql.append(" and ");
			if (1<=searchType&&searchType<=SEARCH_TYPE_MAX) {
				hql.append(SORT_COLUMNS[searchType-1]);
				hql.append(" like :keyword");
				map.put("keyword", "%" + keyword + "%");
			} else { 
				hql.append("(");
				String column[] = new String[SEARCH_TYPE_MAX];
				for(int i=0; i<SEARCH_TYPE_MAX; i++) {
					column[i] = SORT_COLUMNS[i]+" like :keyword";
				}
				hql.append(StringTool.joinString(column, " or ")).append(")");
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
	
	public String changeYear() {
		//校验输入的年份参数是否合法
		if(!year.matches("^\\d{4}$"))
			this.addFieldError(GlobalInfo.ERROR_INFO, GlobalInfo.ERROR_PARAM);
		if(session.containsKey(PRODUCT_YEAR))
			session.remove(PRODUCT_YEAR);
		session.put(PRODUCT_YEAR, year);
		simpleSearch();
		return SUCCESS;
	}
	
	protected String HQL() {
		StringBuffer hql = new StringBuffer();
		hql.append(HQL);
		hql.append(" and pr.applyYear = ");
		String applyYear = new SimpleDateFormat("yyyy").format(new Date());
		if(session.containsKey(PRODUCT_YEAR))
			applyYear = (String) session.get(PRODUCT_YEAR);
		hql.append(applyYear);
		return hql.toString();
	}
	
	@Override
	public String toView() {
		Account account = loginer.getAccount();
		if(account.getType()==6){//如果是专家账号
			accountType = 2;
			Expert expert = firstReviewService.getExpertByAccount(account);
			hasSubmit = firstReviewService.hasSubmitByExpert(expert)?1:0;
		} 
		return SUCCESS;
	}
	
	@Override
	public String view() {
		// TODO Auto-generated method stub
		int produtType = firstReviewService.getProductTypeByProductId(entityId);
		jsonMap.put("produtType", produtType);
		jsonMap.put("entityId", entityId);
		return SUCCESS;
	}

	@Override
	public Object[] advSearchCondition() {
		
		StringBuffer hql = new StringBuffer();
		Map map = new HashMap();
		hql.append(HQL());
		// 拼接检索条件，当检索关键字非空时，才添加检索条件，忽略大小写
		if (keyword1 != null && !keyword1.isEmpty()) {// 按评审编号
			keyword1 = keyword1.toLowerCase();
			hql.append(" and LOWER(pr.reviewNumber) like :keyword1 ");
			map.put("keyword1", "%" + keyword1 + "%");
		}
		if (keyword2 != null && !keyword2.isEmpty()) {
			hql.append(" and pr.name like :keyword2 ");
			map.put("keyword2", "%" + keyword2 + "%");
		}
		if (keyword3 != null && !keyword3.isEmpty()) {
			hql.append(" and pr.authorName like :keyword3 ");
			map.put("keyword3", "%" + keyword3 + "%");
		}
		if (keyword4 != null && !keyword4.isEmpty()) {
			hql.append(" and pr.agencyName like :keyword4 ");
			map.put("keyword4", "%" + keyword4 + "%");
		}
		if (keyword5 != null && !keyword5.isEmpty()) {
			hql.append(" and pr.groupName like :keyword5 ");
			map.put("keyword5", "%" + keyword5 + "%");
		}
		if (keyword6 != null && !keyword6.isEmpty()) {
			hql.append(" and pr.type like :keyword6 ");
			map.put("keyword6", "%" + keyword6 + "%");
		}
		if (keyword7 != null && !keyword7.isEmpty()) {
			hql.append(" and pr.publishName like :keyword7 ");
			map.put("keyword4", "%" + keyword7+ "%");
		}
		return new Object[] {
			hql.toString(),
			map,
			0,
			GlobalInfo.ERROR_EXCEPTION_INFO
		};
	}

	@Override
	public String pageName() {
		// TODO Auto-generated method stub
		return this.PAGE_NAME;
	}

	@Override
	public String pageBufferId() {
		// TODO Auto-generated method stub
		return this.PAGE_BUFFER_ID;
	}

	@Override
	public String[] sortColumn() {
		// TODO Auto-generated method stub
		return this.SORT_COLUMNS;
	}

	@Override
	public String dateFormat() {
		// TODO Auto-generated method stub
		return this.DATE_FORMAT;
	}
	
	public int getProductType() {
		return productType;
	}

	public void setProductType(int productType) {
		this.productType = productType;
	}

	public int getScore1() {
		return score1;
	}

	public void setScore1(int score1) {
		this.score1 = score1;
	}

	public int getScore2() {
		return score2;
	}

	public void setScore2(int score2) {
		this.score2 = score2;
	}

	public int getScore3() {
		return score3;
	}

	public void setScore3(int score3) {
		this.score3 = score3;
	}

	public int getScore4() {
		return score4;
	}

	public void setScore4(int score4) {
		this.score4 = score4;
	}

	public int getScore5() {
		return score5;
	}

	public void setScore5(int score5) {
		this.score5 = score5;
	}

	public String getFirstReviewOpinion() {
		return firstReviewOpinion;
	}

	public void setFirstReviewOpinion(String firstReviewOpinion) {
		this.firstReviewOpinion = firstReviewOpinion;
	}

	public int getAccountType() {
		return accountType;
	}

	public void setAccountType(int accountType) {
		this.accountType = accountType;
	}

	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public int getScoreLine() {
		return scoreLine;
	}
	public void setScoreLine(int scoreLine) {
		this.scoreLine = scoreLine;
	}
	public String getExpertId() {
		return expertId;
	}
	public void setExpertId(String expertId) {
		this.expertId = expertId;
	}
	public String getKeyword1() {
		return keyword1;
	}
	public void setKeyword1(String keyword1) {
		this.keyword1 = keyword1;
	}
	public String getKeyword2() {
		return keyword2;
	}
	public void setKeyword2(String keyword2) {
		this.keyword2 = keyword2;
	}
	public String getKeyword3() {
		return keyword3;
	}
	public void setKeyword3(String keyword3) {
		this.keyword3 = keyword3;
	}
	public String getKeyword4() {
		return keyword4;
	}
	public void setKeyword4(String keyword4) {
		this.keyword4 = keyword4;
	}
	public String getKeyword5() {
		return keyword5;
	}
	public void setKeyword5(String keyword5) {
		this.keyword5 = keyword5;
	}
	public String getKeyword6() {
		return keyword6;
	}
	public void setKeyword6(String keyword6) {
		this.keyword6 = keyword6;
	}
	public String getKeyword7() {
		return keyword7;
	}
	public void setKeyword7(String keyword7) {
		this.keyword7 = keyword7;
	}
	public int getHasSubmit() {
		return hasSubmit;
	}
	public void setHasSubmit(int hasSubmit) {
		this.hasSubmit = hasSubmit;
	}
	//***********************以下方法没有用到 不用实现***************************//
	@Override
	public String toAdd() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String add() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delete() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toModify() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String modify() {
		// TODO Auto-generated method stub
		return null;
	}

}
