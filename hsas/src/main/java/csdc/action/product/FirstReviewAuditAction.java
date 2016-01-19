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
import csdc.model.Groups;
import csdc.service.IFirstReviewAuditService;
import csdc.service.IFirstReviewService;
import csdc.service.ISecondReviewService;
import csdc.tool.HSSFExport;
import csdc.tool.StringTool;
import csdc.tool.info.GlobalInfo;
import csdc.tool.info.SecondReviewInfo;
/**
 * 初评审核类
 * @author Yaoyota
 *
 */
@Component
@Scope(value="prototype")
@SuppressWarnings("unchecked") 
public class FirstReviewAuditAction extends BaseAction {

	private static final long serialVersionUID = -4765355666649345670L;
	private static final String TMP_ENTITY_ID = "productId";// 缓存与session中，备用的成果ID变量名称
	private static final String DATE_FORMAT = "yyyy-MM-dd";// 列表时间格式
	private static final String PAGE_NAME = "firstReviewAuditPage";
	private static final String PAGE_BUFFER_ID = "pr.id";// 上下条查看时用于查找缓存的字段名称
	private static final String PRODUCT_YEAR = "productYear";//session中成果年份字段的key
	//成果id 
	private static final String HQL =  
			"select pr.id, pr.reviewNumber, pr.name, pr.authorName, pr.agencyName, pr.groupName, pr.type, pr.publishName, pr.publishDate, pr.averageScore, pr.hsasReviewAuditResult from Product pr where pr.status>=5";// 上下条查看时用于查找缓存的字段名称HQL
	private static final String[] SORT_COLUMNS = new String[] {
		"pr.reviewNumber",
		"pr.name",
		"pr.authorName",
		"pr.agencyName",
		"pr.groupName",
		"pr.type",
		"pr.publishName",
		"pr.publishDate",
		"pr.averageScore",
		"pr.hsasReviewAuditResult"
	};
	private static final int SEARCH_TYPE_MAX = 6;
	@Autowired
	private IFirstReviewService firstReviewService;
	@Autowired
	private IFirstReviewAuditService firstReviewAuditService;
	@Autowired
	private ISecondReviewService secondReviewService;
	private int auditResult;
	private String year;//年份字段
	private String fileName;//下载文件名
	private int scoreLine;//初评分数线
	private String keyword1;
	private String keyword2;
	private String keyword3;
	private String keyword4;
	private String keyword5;
	private String keyword6;
	private String keyword7;
	/**
	 * 批量审核
	 * @return
	 */
	@Transactional
	public String addAuditResult() {
		Account account = loginer.getAccount();
		if(account.getType()>2) {
			this.addFieldError(GlobalInfo.ERROR_INFO, GlobalInfo.ERROR_NO_RIGHT_INFO);
			jsonMap.put(GlobalInfo.ERROR_INFO, GlobalInfo.ERROR_NO_RIGHT_INFO);
			return SUCCESS;
		}
		for(String id: entityIds) {
			if(firstReviewService.getProductStatusbyId(id)!=5) {
				this.addFieldError(GlobalInfo.ERROR_INFO, "有成果已审核通过");
				jsonMap.put(GlobalInfo.ERROR_INFO, "有成果已审核通过");
				return SUCCESS;
			}
		}
		firstReviewAuditService.addAuditRsult(entityIds, auditResult);
		jsonMap.put("tag", 1);
		return SUCCESS;
	}
	
	/**
	 * 管理员退回操作
	 * 将成果从复评退回到初评审核
	 */
	@Transactional
	public String back() {
		Account account = loginer.getAccount();
		if(!(account.getType()<=2||account.getIsSuperUser()==1)) {
			this.addFieldError(GlobalInfo.ERROR_INFO, SecondReviewInfo.ERROR_NO_RIGHT);
			jsonMap.put(GlobalInfo.ERROR_INFO, SecondReviewInfo.ERROR_NO_RIGHT);
			return SUCCESS;
		}
		for(String id: entityIds) {
			if(firstReviewService.getProductStatusbyId(id)!=6) {
				this.addFieldError(GlobalInfo.ERROR_INFO, "有成果不在复评阶段，不能退回");
				jsonMap.put(GlobalInfo.ERROR_INFO, "有成果不在复评阶段，不能退回");
				return SUCCESS;
			}
			if(secondReviewService.startReview(id)) {
				this.addFieldError(GlobalInfo.ERROR_INFO, SecondReviewInfo.ERROR_REVIEW_START);
				jsonMap.put(GlobalInfo.ERROR_INFO, SecondReviewInfo.ERROR_REVIEW_START);
				return SUCCESS;
			}
		}
		secondReviewService.back(entityIds);
		jsonMap.put("tag", 1);
		return SUCCESS;
	}
	
	@Transactional
	public String audit() {
		Account account = loginer.getAccount();
		if(account.getType()>2) {
			this.addFieldError(GlobalInfo.ERROR_INFO, GlobalInfo.ERROR_NO_RIGHT_INFO);
		}
		firstReviewAuditService.audit(scoreLine);
		jsonMap.put("tag", 1);
		return SUCCESS;
	}
	
	@Override
	public String toView() {
		// TODO Auto-generated method stub
		return SUCCESS;
	}
	
	@Override
	public String view() {
		if(firstReviewAuditService.getProductStatus(entityId)<5) {
			jsonMap.put(GlobalInfo.ERROR_INFO, "初评未结束");
			return SUCCESS;
		}
		int averageScore = firstReviewAuditService.getAverageScore(entityId);
		int reviewType = firstReviewAuditService.getReviewType(entityId);
		List<String[]> firstReviewData = firstReviewAuditService.getReviewData(entityId);
		jsonMap.put("averageScore", averageScore);
		jsonMap.put("reviewType", reviewType);
		jsonMap.put("firstReviewData", firstReviewData);
		return SUCCESS;
	}

	@Override
	public Object[] simpleSearchCondition() {
		keyword = (keyword == null) ? "" : keyword.toLowerCase().trim();// 预处理关键字
		StringBuffer hql = new StringBuffer();
		Map map = new HashMap();	
		hql.append(HQL());
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
			8,
			GlobalInfo.ERROR_EXCEPTION_INFO
		};
	}
	
	public String exportListData() {
		return SUCCESS;
	}
	
	public InputStream getListDataStream() throws UnsupportedEncodingException {
		String header = "初评审核成果表";//表头
		String[] title = new String[]{};//标题
			title = new String[]{
					"序号",
					"评审编号",
					"成果名称",
					"作者名",
					"机构名",
					"成果分组",
					"成果类型",
					"发表刊物",
					"发表时间",
					"初评得分",
					"审核结果"
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
			Object[] data = new Object[11];
			data[0] = index++;
			for(int i=1; i<=8; i++){
				data[i] = o[i];
			}
			data[9] = o[9].equals("")? 0:new Integer(o[9]);//成果初评得分
			switch (new Integer(o[10])) {
			case 0:
				data[10] = "未审";//成果类型
				break;
			case 1:
				data[10] = "不同意";
				break;
			case 2:
				data[10] = "同意";
				break;
			case 3:
				data[10] = "退回";
				break;
			default:
				break;
			}
			dataList.add(data);
		}
		return HSSFExport.commonExportExcel(dataList, header, title);
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
			8,
			GlobalInfo.ERROR_EXCEPTION_INFO
		};
	}

	@Override
	public String pageName() {
		// TODO Auto-generated method stub
		return PAGE_NAME;
	}

	@Override
	public String pageBufferId() {
		// TODO Auto-generated method stub
		return PAGE_BUFFER_ID;
	}

	@Override
	public String[] sortColumn() {
		// TODO Auto-generated method stub
		return SORT_COLUMNS;
	}

	@Override
	public String dateFormat() {
		// TODO Auto-generated method stub
		return DATE_FORMAT;
	}

	public int getAuditResult() {
		return auditResult;
	}
	public void setAuditResult(int auditResult) {
		this.auditResult = auditResult;
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

	//*********************以下方法没有用到，故不用实现**************************//
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