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
import csdc.service.ISecondReviewService;
import csdc.tool.HSSFExport;
import csdc.tool.StringTool;
import csdc.tool.info.GlobalInfo;
import csdc.tool.info.SecondReviewInfo;
/**
 * 复评
 * @author Yaoyota
 *
 */
@Component
@Scope(value="prototype")
public class SecondReviewAction extends BaseAction {

	private static final long serialVersionUID = -8433343977136537525L;
	private static final String TMP_ENTITY_ID = "secondReviewProductId";// 缓存与session中，备用的成果ID变量名称
	private static final String DATE_FORMAT = "yyyy-MM-dd";// 列表时间格式
	private static final String PAGE_NAME = "secondReviewPage";
	private static final String PAGE_BUFFER_ID = "pr.id";// 上下条s查看时用于查找缓存的字段名称
	private static final String PRODUCT_YEAR = "productYear";//session中成果年份字段的key
	//成果id 
	private static final String HQL =  
			"select pr.id, pr.reviewNumber, pr.name, pr.authorName, pr.agencyName, pr.groupName, pr.type, pr.publishName, pr.publishDate, pr.averageScore from Product pr where 1=1";// 上下条查看时用于查找缓存的字段名称HQL
	private static final String[] SORT_COLUMNS = new String[] {
		"pr.reviewNumber",
		"pr.name",
		"pr.authorName",
		"pr.agencyName",
		"pr.groupName",
		"pr.type",
		"pr.publishName",
		"pr.publishDate",
		"pr.averageScore"
	};
	private static final int SEARCH_TYPE_MAX = 8;
	@Autowired
	private ISecondReviewService secondReviewService;
	private String reviewOpinion;//复评意见
	private int rewardLevel;//复评推荐获奖等级
	private int leaderAuditResult;//组长审核结果
	private String year;//年份字段
	private int accountType;//账户类型[1:管理员账户 2:专家账户]
	private String fileName;//下载文件名
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
		if(account.getType()==4||account.getType()==5) {
			accountType = 2;
		}else if(account.getType()<=2||account.getIsSuperUser()==1) {
			accountType = 1;
		}else {
			this.addFieldError(GlobalInfo.ERROR_INFO, GlobalInfo.ERROR_NO_RIGHT_INFO);
		}
		return SUCCESS;
	}
	
	@Override
	public String list() {
		super.list();
		Account account = loginer.getAccount();
		//如果是专家账户，则在laData中加入合作者信息和是否已评信息(0:否1:是)和主审信息(最后一个字段为“是否是主审成果”["0":否 "1":是])，并将主审成果排在列表最前
		if(account.getType()==4||account.getType()==5) {
			Expert expert = secondReviewService.getExpertByAccount(account);
			laData = secondReviewService.addChiefReviewInfo(laData, expert);
			jsonMap.remove("laData");
			jsonMap.put("laData", laData);
		}
		//如果是管理员账户，则在列表中加入合作者信息、已评专家个数和应评专家个数
		if(account.getType()<=2||account.getIsSuperUser()==1) {
			laData = secondReviewService.addReviewInfo(laData);
			jsonMap.remove("laData");
			jsonMap.put("laData", laData);
		}
		return SUCCESS;
	}
	
	/**
	 * 进入列表之前的校验，进行一些权限判断
	 */
	public void validateList() {
		//当前账户类型为专家时，判断该专家是否为当年复评专家
		Account account = loginer.getAccount();
		if(account.getType()==4||account.getType()==5) {
			if(!secondReviewService.isSecondReviwer(account)) {
				this.addFieldError(GlobalInfo.ERROR_INFO, GlobalInfo.ERROR_NO_RIGHT_INFO);
			}
		}else if (account.getType()<=2||account.getIsSuperUser()==1) {
		}else {
			this.addFieldError(GlobalInfo.ERROR_INFO, GlobalInfo.ERROR_NO_RIGHT_INFO);
		}
	}
	
	public String exportListData() {
		return SUCCESS;
	}
	
	public InputStream getListDataStream() throws UnsupportedEncodingException {
		String header = "复评成果表";//表头
		
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
					"初评得分",
					"合作者",
					"已评专家数",
					"所有专家数"
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
			for(int i = 1; i<=8; i++) {
				data[i] = o[i];
			}
			data[9] = o[9].equals("")? 0:new Integer(o[9]);//成果初评得分
			data[10] = o[10];//合作者信息
			data[11] = new Integer(o[11]);//已评专家数
			data[12] = new Integer(o[12]);//所有专家数
			dataList.add(data);
		}
		return HSSFExport.commonExportExcel(dataList, header, title);
	}
	
	@Override
	public String toView() {
		// TODO Auto-generated method stub
		return SUCCESS;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public String view() {
		Account account = loginer.getAccount();
		int isSecondReviewer = 0;
		int isAdmin = 0;
		int isChiefReviewer = 0;
		int isReviewed = 0;
		int isBacked = 0;
		int auditAble = 0;
		int accountType = 0;//局部变量 表示当前的账户类型[1:当前的复评专家 2:社科联管理人员和超级管理员]
		if(secondReviewService.isSecondReviwer(account)) {
			accountType = 1;
			isSecondReviewer = 1;
		}else if(account.getType()<=2||account.getIsSuperUser()==1) {
			accountType = 2;
			isAdmin = 1;
		}
		switch (accountType) {
		//复评专家
		case 1:
			Expert expert = secondReviewService.getExpertByAccount(account);
			isChiefReviewer = secondReviewService.isChiefReviewer(expert, entityId)? 1:0;
			isReviewed = secondReviewService.isReviewed(expert, entityId)? 1:0;
			isBacked = secondReviewService.isBacked(entityId)? 1:0;
			auditAble = (secondReviewService.isGroupLeader(expert, entityId)
					&&secondReviewService.isReviewedByAllExpert(entityId))? 1:0;
			if((isReviewed==1&&isChiefReviewer==1)||auditAble==1) {
				//显示复评获奖等级
				int rewardLevel = secondReviewService.getRewardLevel(entityId);
				jsonMap.put("rewardLevel", rewardLevel);
			}
			if(isReviewed==1) {
				//显示当前专家复评意见: String[] myOpinion {专家复评意见, 专家姓名, 是否主审}
				List<String> expertId = new ArrayList<String>();
				expertId.add(expert.getId());
				List<String[]> reviewOpinion = secondReviewService.getReviewOpinionByExpertId(entityId, expertId);
				String[] myOpinion = reviewOpinion.get(0);
				jsonMap.put("myOpinion", myOpinion);
		        if(auditAble==1) {
		        //显示“审核”按钮;
		        //显示其他专家复评意见: String[] otherOpinioin {专家复评意见, 专家姓名, 是否主审}
		    		List<String> allReviewers = secondReviewService.getReviewerIdByProductId(entityId);
					allReviewers.remove(expert.getId());
					List<String[]> otherOpinioin = new ArrayList<String[]>();
					if(!allReviewers.isEmpty())
						otherOpinioin = secondReviewService.getReviewOpinionByExpertId(entityId, allReviewers);
					jsonMap.put("otherOpinioin", otherOpinioin);
		        }
		    }
			break;
		case 2:
			int rewardLevel = secondReviewService.getRewardLevel(entityId);
			jsonMap.put("rewardLevel", rewardLevel);
    		List<String> allReviewers = secondReviewService.getReviewerIdByProductId(entityId);
			List<String[]> allOpinioin = secondReviewService.getReviewOpinionByExpertId(entityId, allReviewers);
			jsonMap.put("allOpinioin", allOpinioin);
			break;
		default:
			this.addFieldError(GlobalInfo.ERROR_INFO, GlobalInfo.ERROR_NO_RIGHT_INFO);
			break;
		}
		int leaderAuditResult = secondReviewService.getLeaderAuditResult(entityId);
		jsonMap.put("leaderAuditResult", leaderAuditResult);
		jsonMap.put("isSecondReviewer", isSecondReviewer);
		jsonMap.put("isAdmin", isAdmin);
		jsonMap.put("isChiefReviewer", isChiefReviewer);
		jsonMap.put("isReviewed", isReviewed);
		jsonMap.put("isBacked", isBacked);
		jsonMap.put("auditAble", auditAble);
		jsonMap.put("entityId", entityId);
		return SUCCESS;
		/**
		if(secondReviewService.isSecondReviwer(account)) {
			isSecondReviewer = 1;
			Expert expert = secondReviewService.getExpertByAccount(account);
			if(secondReviewService.isChiefReviewer(expert, entityId)) {
				isChiefReviewer = 1;
				if(secondReviewService.isReviewed(expert, entityId)) {
					isReviewed = 1;
					List<String> expertId = new ArrayList<String>();
					expertId.add(expert.getId());
					List<String[]> reviewOpinion = secondReviewService.getReviewOpinionByExpertId(entityId, expertId);
					int rewardLevel = secondReviewService.getRewardLevel(entityId);
					String[] myOpinion = reviewOpinion.get(0);
					jsonMap.put("myOpinion", myOpinion);
					jsonMap.put("rewardLevel", rewardLevel);
					if(secondReviewService.isBacked(entityId)) {
						isBacked = 1;
					}
					if(expert.getIsGroupLeader()==1&&secondReviewService.isReviewedByAllExpert(entityId)) {
						auditAble = 1;
						List<String> allReviewers = secondReviewService.getReviewerIdByProductId(entityId);
						allReviewers.remove(expert.getId());
						List<String[]> otherOpinioin = secondReviewService.getReviewOpinionByExpertId(entityId, allReviewers);
						jsonMap.put("otherOpinioin", otherOpinioin);
					}
				}
			} else {
				if(secondReviewService.isReviewed(expert, entityId)) {
					isReviewed = 1;
					List<String> expertId = new ArrayList<String>();
					expertId.add(expert.getId());
					List<String[]> reviewOpinion = secondReviewService.getReviewOpinionByExpertId(entityId, expertId);
					String[] myOpinion = reviewOpinion.get(0);
					jsonMap.put("myOpinion", myOpinion);
				}
				if(expert.getIsGroupLeader()==1&&secondReviewService.isReviewedByAllExpert(entityId)) {
					auditAble = 1;
					Expert chiefReviewer = secondReviewService.getChiefReviewer(entityId);
					List<String> cheifId = new ArrayList<String>();
					cheifId.add(chiefReviewer.getId());
					List<String[]> reviewOpinion = secondReviewService.getReviewOpinionByExpertId(entityId, cheifId);
					String[] chiefOpinion = reviewOpinion.get(0);
					int rewardLevel = secondReviewService.getRewardLevel(entityId);
					jsonMap.put("chiefOpinion", chiefOpinion);
					jsonMap.put("rewardLevel", rewardLevel);
					List<String> allReviewers = secondReviewService.getReviewerIdByProductId(entityId);
					allReviewers.remove(expert.getId());
					allReviewers.remove(chiefReviewer.getId());
					List<String[]> otherOpinioin = secondReviewService.getReviewOpinionByExpertId(entityId, allReviewers);
					jsonMap.put("otherOpinioin", otherOpinioin);
				}
			}
		} else if(account.getType()<=2||account.getIsSuperUser()==1) {
			isAdmin = 1;
			Expert chiefReviewer = secondReviewService.getChiefReviewer(entityId);
			List<String> cheifId = new ArrayList<String>();
			cheifId.add(chiefReviewer.getId());
			List<String[]> reviewOpinion = secondReviewService.getReviewOpinionByExpertId(entityId, cheifId);
			String[] chiefOpinion = reviewOpinion.get(0);
			int rewardLevel = secondReviewService.getRewardLevel(entityId);
			jsonMap.put("chiefOpinion", chiefOpinion);
			jsonMap.put("rewardLevel", rewardLevel);
			List<String> otherReviewers = secondReviewService.getReviewerIdByProductId(entityId);
			otherReviewers.remove(chiefReviewer.getId());
			List<String[]> otherOpinioin = secondReviewService.getReviewOpinionByExpertId(entityId, otherReviewers);
			jsonMap.put("otherOpinioin", otherOpinioin);
			int leaderAuditResult = secondReviewService.getLeaderAuditResult(entityId);
			jsonMap.put("leaderAuditResult", leaderAuditResult);
		} else {
			this.addFieldError(GlobalInfo.ERROR_INFO, GlobalInfo.ERROR_NO_RIGHT_INFO);
		}
		**/
	}
	
	public void validateAddChiefReview() {
		if(rewardLevel==0||rewardLevel>5)
			this.addFieldError(GlobalInfo.ERROR_INFO, SecondReviewInfo.ERROR_REWARDLEVEL_NULL);
		if(reviewOpinion==null||reviewOpinion.isEmpty())
			this.addFieldError(GlobalInfo.ERROR_INFO, SecondReviewInfo.ERROR_REVIEW_OPINION_NULL);
		if(secondReviewService.getProductStatusbyId(entityId)!=6)
			this.addFieldError(GlobalInfo.ERROR_INFO, SecondReviewInfo.ERROR_SECONDREVIEW_END_INFO);
		Account account = loginer.getAccount();
		Expert expert = secondReviewService.getExpertByAccount(account);
		if(!secondReviewService.isSecondReviwer(account))
			this.addFieldError(GlobalInfo.ERROR_INFO, SecondReviewInfo.ERROR_ACCOUNT_NOT_REVIEWER);
		if(!secondReviewService.isChiefReviewer(expert, entityId))
			this.addFieldError(GlobalInfo.ERROR_INFO, SecondReviewInfo.ERROR_ACCOUNT_NOT_CHIEF);
		if(secondReviewService.isReviewed(expert, entityId))
			this.addFieldError(GlobalInfo.ERROR_INFO, SecondReviewInfo.ERROR_IS_REVIEWED);
	}
	/**
	 * 添加复评主审结果
	 * @return
	 */
	@Transactional
	public String addChiefReview() {
		Account account = loginer.getAccount();
		Expert expert = secondReviewService.getExpertByAccount(account);
		secondReviewService.addChiefReview(entityId, expert, rewardLevel, reviewOpinion);
		jsonMap.put("tag", 1);
		return SUCCESS;
	}
	
	public void validateModifyChiefReview() {
		if(rewardLevel==0||rewardLevel>5)
			this.addFieldError(GlobalInfo.ERROR_INFO, SecondReviewInfo.ERROR_REWARDLEVEL_NULL);
		if(reviewOpinion==null||reviewOpinion.isEmpty())
			this.addFieldError(GlobalInfo.ERROR_INFO, SecondReviewInfo.ERROR_REVIEW_OPINION_NULL);
		if(secondReviewService.getProductStatusbyId(entityId)!=6)
			this.addFieldError(GlobalInfo.ERROR_INFO, SecondReviewInfo.ERROR_SECONDREVIEW_END_INFO);
		Account account = loginer.getAccount();
		Expert expert = secondReviewService.getExpertByAccount(account);
		if(!secondReviewService.isSecondReviwer(account))
			this.addFieldError(GlobalInfo.ERROR_INFO, SecondReviewInfo.ERROR_ACCOUNT_NOT_REVIEWER);
		if(!secondReviewService.isChiefReviewer(expert, entityId))
			this.addFieldError(GlobalInfo.ERROR_INFO, SecondReviewInfo.ERROR_ACCOUNT_NOT_CHIEF);
		if(!secondReviewService.isReviewed(expert, entityId))
			this.addFieldError(GlobalInfo.ERROR_INFO, SecondReviewInfo.ERROR_NOT_REVIEW);
	}
	/**
	 * 修改复评主审结果
	 */
	@Transactional
	public String modifyChiefReview() {
		Account account = loginer.getAccount();
		Expert expert = secondReviewService.getExpertByAccount(account);
		secondReviewService.modifyChiefReview(entityId, expert, rewardLevel, reviewOpinion);
		jsonMap.put("tag", 1);
		return SUCCESS;
	}

	public void validateAddReview() {
		if(reviewOpinion==null||reviewOpinion.isEmpty())
			this.addFieldError(GlobalInfo.ERROR_INFO, SecondReviewInfo.ERROR_REVIEW_OPINION_NULL);
		Account account = loginer.getAccount();
		Expert expert = secondReviewService.getExpertByAccount(account);
		if(secondReviewService.getProductStatusbyId(entityId)!=6)
			this.addFieldError(GlobalInfo.ERROR_INFO, SecondReviewInfo.ERROR_SECONDREVIEW_END_INFO);
		if(!secondReviewService.isSecondReviwer(account))
			this.addFieldError(GlobalInfo.ERROR_INFO, SecondReviewInfo.ERROR_ACCOUNT_NOT_REVIEWER);
		if(!secondReviewService.isProductReviewer(expert, entityId))
			this.addFieldError(GlobalInfo.ERROR_INFO, SecondReviewInfo.ERROR_ACCOUNT_NOT_UNDER_CONTROL);
		if(secondReviewService.isReviewed(expert, entityId))
			this.addFieldError(GlobalInfo.ERROR_INFO, SecondReviewInfo.ERROR_IS_REVIEWED);
	}
	/**
	 * 添加复评普通评价
	 */
	@Transactional
	public String addReview() {
		Account account = loginer.getAccount();
		Expert expert = secondReviewService.getExpertByAccount(account);
		secondReviewService.addReview(entityId, expert, reviewOpinion);
		jsonMap.put("tag", 1);
		return SUCCESS;
	}
	
	public void validateModifyReview() {
		if(reviewOpinion==null||reviewOpinion.isEmpty())
			this.addFieldError(GlobalInfo.ERROR_INFO, SecondReviewInfo.ERROR_REVIEW_OPINION_NULL);
		Account account = loginer.getAccount();
		Expert expert = secondReviewService.getExpertByAccount(account);
		if(secondReviewService.getProductStatusbyId(entityId)!=6)
			this.addFieldError(GlobalInfo.ERROR_INFO, SecondReviewInfo.ERROR_SECONDREVIEW_END_INFO);
		if(!secondReviewService.isSecondReviwer(account))
			this.addFieldError(GlobalInfo.ERROR_INFO, SecondReviewInfo.ERROR_ACCOUNT_NOT_REVIEWER);
		if(!secondReviewService.isProductReviewer(expert, entityId))
			this.addFieldError(GlobalInfo.ERROR_INFO, SecondReviewInfo.ERROR_ACCOUNT_NOT_UNDER_CONTROL);
		if(!secondReviewService.isReviewed(expert, entityId))
			this.addFieldError(GlobalInfo.ERROR_INFO, SecondReviewInfo.ERROR_NOT_REVIEW);
	}
	
	/**
	 * 修改复评普通评价
	 */
	@Transactional
	public String modifyReview() {
		Account account = loginer.getAccount();
		Expert expert = secondReviewService.getExpertByAccount(account);
		secondReviewService.modifyReview(entityId, expert, reviewOpinion);
		jsonMap.put("tag", 1);
		return SUCCESS;
	}
	
	public void validateAudit() {
		if(leaderAuditResult==0||leaderAuditResult>2)
			this.addFieldError(GlobalInfo.ERROR_INFO, SecondReviewInfo.ERROR_AUDIT_RESULT_NULL);
		Account account = loginer.getAccount();
		Expert expert = secondReviewService.getExpertByAccount(account);
		if(secondReviewService.getProductStatusbyId(entityId)!=6)
			this.addFieldError(GlobalInfo.ERROR_INFO, SecondReviewInfo.ERROR_SECONDREVIEW_END_INFO);
		if(!secondReviewService.isSecondReviwer(account))
			this.addFieldError(GlobalInfo.ERROR_INFO, SecondReviewInfo.ERROR_ACCOUNT_NOT_REVIEWER);
		if(!secondReviewService.isGroupLeader(expert, entityId))
			this.addFieldError(GlobalInfo.ERROR_INFO, SecondReviewInfo.ERROR_ACCOUNT_NOT_GROUP_LEADER);
		if(!secondReviewService.isReviewedByAllExpert(entityId))
			this.addFieldError(GlobalInfo.ERROR_INFO, SecondReviewInfo.ERROR_NOT_REVIEWED_BY_ALL);
	}
	/**
	 *组长审核
	 */
	@Transactional
	public String audit() {
		secondReviewService.audit(entityId, leaderAuditResult);
		if(leaderAuditResult==2)
			secondReviewService.submit(entityId);
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
		boolean isAdmin = false;
		//复评专家只能看到本组的成果
		if(account.getType()==4||account.getType()==5) {
			hql.append(" and pr.status>=6");//专家也可以看到复评后的成果
			Groups group = secondReviewService.getExpertGroupByAccount(account);
			hql.append(" and pr.groups=:groups");
			map.put("groups", group);
		}else if (account.getType()<=2||account.getIsSuperUser()==1) {
			//社科联管理员和超级管理员可以看到复评和复评阶段之后的所有成果
			isAdmin = true;
			hql.append(" and pr.status>=6");
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
			isAdmin? 4:1,
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
		return this.PAGE_NAME;
	}

	@Override
	public String pageBufferId() {
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
	
	
	public String getReviewOpinion() {
		return reviewOpinion;
	}

	public void setReviewOpinion(String reviewOpinion) {
		this.reviewOpinion = reviewOpinion;
	}

	public int getRewardLevel() {
		return rewardLevel;
	}

	public void setRewardLevel(int rewardLevel) {
		this.rewardLevel = rewardLevel;
	}

	public int getLeaderAuditResult() {
		return leaderAuditResult;
	}

	public void setLeaderAuditResult(int leaderAuditResult) {
		this.leaderAuditResult = leaderAuditResult;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public int getAccountType() {
		return accountType;
	}

	public void setAccountType(int accountType) {
		this.accountType = accountType;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
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

	//*************************//
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
