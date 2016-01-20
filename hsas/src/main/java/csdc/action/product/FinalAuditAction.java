package csdc.action.product;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ognl.ListPropertyAccessor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionContext;

import csdc.action.BaseAction;
import csdc.model.Account;
import csdc.model.Product;
import csdc.model.ProductReview;
import csdc.model.Reward;
import csdc.service.IFinalAuditService;
import csdc.tool.HSSFExport;
import csdc.tool.bean.LoginInfo;
import csdc.tool.info.GlobalInfo;

@Component
@Scope(value = "prototype")
public class FinalAuditAction extends BaseAction {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -7118166128512170277L;
	
	private  static final String HQL = "select p.id, p.name,p.authorName,p.agencyName, p.type,p.groupName, p.publishName, p.publishDate, p.rewardLevel, p.hsasFinalAuditResult from Product p where 1=1 ";
	private static final String[] COLUMN = {
		"p.name",
		"p.authorName",
		"p.agencyName",
		"p.type",
		"p.groupName",
		"p.publishName", 
		"p.publishDate",
		"p.rewardLevel",
		"p.hsasFinalAuditResult"
	};// 用于拼接的排序列
	
	private static final String PAGE_NAME = "finalAuditPage";// 列表页面名称
	private static final String DATE_FORMAT = "yyyy-MM-dd";// 列表时间格式
	private static final String PAGE_BUFFER_ID = "p.id";// 上下条查看时用于查找缓存的字段
	
	private Product product;
	private List<ProductReview> productReview;
	private Reward reward;
	private String fileFileName;
	private Account account;
	@Autowired
	private  IFinalAuditService finalAuditService;
	
	private int auditResult;
	private String  auditOption;
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
	
	public String toAudit(){
		return SUCCESS;
	}
	
	public String audit(){
		LoginInfo loginer =  (LoginInfo) ActionContext.getContext().getSession().get(GlobalInfo.LOGINER);
		Map map = new HashMap();
		account = loginer.getAccount();
		map.put("account", account);
		map.put("result", auditResult);
		map.put("opinion", auditOption);
		
	    String result=	finalAuditService.setFinalResult(entityId, map);
		
	    if(result.equals("error")){
	    	jsonMap.put("tag", "2");
	    	return INPUT;
	    }else{
	    	jsonMap.put("tag", "1");
	    }
	    return SUCCESS;
	}
	

	@Override
	public String delete() {
		// TODO Auto-generated method stub
		return null;
	}

	//只能修改奖励等级信息
	@Override
	public String toModify() {
		product = finalAuditService.getProduct(entityId);		
		return SUCCESS;
	}

	@Override
	public String modify() {
		return SUCCESS ;
	}

	@Override
	public String view() {
		product = finalAuditService.getProduct(entityId);
		jsonMap.put("product", product);
		productReview = finalAuditService.getProductReview(entityId,0);
		jsonMap.put("FirstReview", productReview);
		productReview = finalAuditService.getProductReview(entityId, 1);
		jsonMap.put("SecondReview", productReview);
		
		return SUCCESS;
	}

	@Override
	public String toView() {
		return SUCCESS;
	}

	//账户类型为一般管理员及以上用户可用
	@Override
	public Object[] simpleSearchCondition() {
		
		LoginInfo loginer =  (LoginInfo) ActionContext.getContext().getSession().get(GlobalInfo.LOGINER);
		account = loginer.getAccount();
		int currentYear = new Date().getYear()+1900;
		
		keyword = (keyword == null) ? "" : keyword.toLowerCase().trim();// 预处理关键字
		StringBuffer hql = new StringBuffer();
		Map map = new HashMap();
		hql.append(HQL);
		
		hql.append(" and(p.rewardLevel in(1,2,3,4)) and p.status in(7)");
			
		if(account.getType()==1||account.getType()==2){
			
			//超级管理员所有的都可查询出来，不做任何限制
		}else {
			//非一般管理员用查询出来的为空
			hql.append(" and p.id = :id");
			map.put("id", "");
		}
		
		
		if (keyword != null && !keyword.isEmpty()) {
			if (searchType == 1) {
				hql.append(" and LOWER(p.agencyName) like :keyword");
				map.put(" keyword", "%" + keyword + "%");
			} else if (searchType == 2) {
				hql.append(" and LOWER(p.researchType) like :keyword");
				map.put("keyword", "%" + keyword + "%");
			} else if (searchType == 3) {
				hql.append(" and (LOWER(p.rewardLevel) like :keyword ");
				map.put("and keyword", "%" + keyword + "%");
			}
		}
		//默认为当前年份
		keyword = searchType==4 ? keyword :Integer.toString(currentYear);
		hql.append(" and p.applyYear = :keyword ");
		map.put("keyword", keyword);
		
		// 调用初级检索功能
		return new Object[]{
			hql.toString(),
			map,
			0,
			null
		};
	}

	@Override
	public Object[] advSearchCondition() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	/**
	 * 导出成果获奖一览表,
	 * @return
	 * @author xn
	 */
	public String confirmExportOverView(){
		
		return SUCCESS;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public InputStream getDownloadFile() throws UnsupportedEncodingException{
		String header = "成果获奖一览表";//表头
		
		StringBuffer hql4Export = new StringBuffer();
		String[] title = new String[]{};//标题
			title = new String[]{
					"序号",
					"成果编号",
					"成果名称",
					"第一作者",
					"依托机构",
					"成果类型",
					"申请年份",
					"获奖等级",
					"获奖类型",
					"获奖金额"
					
				};
			hql4Export.append("select p.id,p.reviewNumber,p.name,p.authorName,p.agencyName,p.researchType ,p.groupName,p.rewardLevel from Product p where p.applyYear=:year order by p.groupName,p.reviewNumber" );
		
		int currentYear = new Date().getYear()+1900;
		Map map = new HashMap();
		map.put("year", Integer.toString(currentYear));
		
		fileFileName =currentYear+"年"+ header + ".xls";
		fileFileName = new String(fileFileName.getBytes("UTF-8"), "ISO8859-1");
		List<Object[]> list;
		
		//默认为导出当前年份的获奖成果，也可以导出选中的获奖成果
		if(entityIds==null){
			
			 list = dao.query(hql4Export.toString(),map);
		} else {
			 list = finalAuditService.getProductInfos(entityIds);
		}
		List dataList = new ArrayList();
		int index = 1;
	
			for (Object object : list) {
				Object[] o = (Object[]) object;
				Object[] data = new Object[8];
				data[0] = index++;
				data[1] = o[1];//成果编号
				data[2] = o[2];//成果名称
				data[3] = o[3];//第一作者
				data[4] = o[4];//依托机构
				data[5] = o[5];//成果类型
				data[6] = o[6];//groupName
				data[7] = rewardLevel(Integer.parseInt(o[7].toString()));//获奖等
				
				dataList.add(data);
			}
			 
		return HSSFExport.commonExportExcel(dataList, header, title);
	}
	
	/**
	 * 获奖等级转换
	 * @param type
	 * @return
	 */
	private String rewardLevel(int type){
		String level="";
		switch (type) {
		case 0:
			level="特等奖";
			break;
		case 1:
			level="一等奖";
			break;
		case 2:
			level="二等奖";
			break;
		case 3:
			level="三等奖";
			break;

		default:
			break;
		}
		
		return level;
		
	}
	
	private String  rewardType(int type){
		String  rewardType = "";
		switch (type) {
		
		case 1:
			rewardType="著作";
			break;
		case 2:
			rewardType="论文";
			break;
	
		default:
			break;
		}
		return rewardType;
		
	}

	@Override
	public String pageName() {
		return PAGE_NAME;
	}

	@Override
	public String pageBufferId() {
		return PAGE_BUFFER_ID;
	}

	@Override
	public String[] sortColumn() {
		return COLUMN;
	}

	@Override
	public String dateFormat() {
		return DATE_FORMAT;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	
	public List<ProductReview> getProductReview() {
		return productReview;
	}

	public void setProductReview(List<ProductReview> productReview) {
		this.productReview = productReview;
	}

	public Reward getReward() {
		return reward;
	}

	public void setReward(Reward reward) {
		this.reward = reward;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public int getAuditResult() {
		return auditResult;
	}

	public void setAuditResult(int auditResult) {
		this.auditResult = auditResult;
	}

	public String getAuditOption() {
		return auditOption;
	}

	public void setAuditOption(String auditOption) {
		this.auditOption = auditOption;
	}
	
	

}
