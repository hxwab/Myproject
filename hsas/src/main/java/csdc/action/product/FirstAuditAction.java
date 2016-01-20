package csdc.action.product;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.mysql.fabric.xmlrpc.base.Data;
import com.opensymphony.xwork2.ActionContext;
import csdc.action.BaseAction;
import csdc.model.Account;
import csdc.model.Product;
import csdc.service.IStatisticService;
import csdc.service.IFirstAuditService;
import csdc.tool.ApplicationContainer;
import csdc.tool.FileTool;
import csdc.tool.HSSFExport;
import csdc.tool.bean.LoginInfo;
import csdc.tool.info.GlobalInfo;

/**
 * 初审（非高校直接由省社科联审核；高校先由高校审核，高校审核通过后再由社科联审核）
 * 1、高校只能审核本校的申报成果（根据高校管理员的账户所属机构找出该机构的所有申报成果）
 * 2、社科联管理员可以审核所有申报成果（非高校+高校审核通过部分）
 * 3、高校管理员3，社科联管理员2
 * 
 * @author huangxw
 *
 */

@Component
@Scope(value="prototype")
public class FirstAuditAction extends BaseAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5543945160710526586L;

	//p.submitStatus  p.status
	private  static final String HQL = "select p.id,p.name,p.authorName,p.agencyName, p.type,p.groupName, p.publishName,p.publishDate,p.universityAuditResult,p.hsasApplyAuditResult, p.submitStatus, a.type from Product p left join p.agency a where 1=1";
	private static final String[] COLUMN = {
		"p.name",
		"p.authorName",
		"p.agencyName",
		"p.type",
		"p.groupName",
		"p.publishName",
		"p.publishDate",
		"p.universityAuditResult",
		"p.hsasApplyAuditResult",
		"p.submitStatus"
	};// 用于拼接的排序列
	
	private static final String PAGE_NAME = "firstAuditPage";// 列表页面名称
	private static final String DATE_FORMAT = "yyyy-MM-dd";// 列表时间格式
	private static final String PAGE_BUFFER_ID = "p.id";// 上下条查看时用于查找缓存的字段
	
	private Account account;

	private Product product;
	
	private String auditResult;
	
	private String auditOption;
	
	private List<String> auditIds;
	
	
	private String  fileFileName;
	@Autowired
	private IFirstAuditService firstAuditService;
	
	@Autowired
	private IStatisticService dataService;
	
	private String fileName;
	
	@Override
	public String toList() {
		 super.toList();
		 return SUCCESS;
	}

	@Override
	public String list() {
		super.list();
		List list =null;
		try {
			
			laData=firstAuditService.addExternalInfo(laData);
			//list = dataService.calcApplyAuthor("2016");
			list = dataService.calcService(5, "2016");
		} catch (Exception e) {
			// TODO: handle exception 
			e.printStackTrace();
		}
		jsonMap.put("laData", laData);
		jsonMap.put("author", list);
		return SUCCESS;
		
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
		if(entityId!=null){
			entityIds = new ArrayList<String>();
			entityIds.add(entityId);
		}
		
		//若为高级管理员，则需判断该成果是否提交或经过初级审核。因为高级管理员所有成果都可见
		if(account.getType()==1){
			boolean canApply = false;
			try {
				canApply =firstAuditService.canApply(entityIds);

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			if(!canApply){
				jsonMap.put("tag", "该成果未提交或校级未审核！");
				return INPUT;
			}
		}
		
	    String result=	firstAuditService.setFirstCheckRestults(entityIds, map);
		
	    if(result.equals("success")){
	    	jsonMap.put("tag", "1");
	    }else{
	    	jsonMap.put("tag", result);
	    	return INPUT;
	    }
	    return SUCCESS;
	}
	
	public void validateAudit(){
		
		if(entityIds==null&&entityId==null){
			this.addFieldError(GlobalInfo.ERROR_INFO, "未选任何成果");
		}
		
	}
	
	
	
	public String download(){
		return SUCCESS;
	}
	
	public InputStream getFileStream(){
		String path = firstAuditService.getFilePath(entityId);
		fileName = firstAuditService.getFileName(entityId);
		InputStream downloadInputStream=ApplicationContainer.sc.getResourceAsStream(path);
		return downloadInputStream;
	}
	
	
	public String mutiDownload() {
		return SUCCESS;
	}
	public InputStream getFilesStream(){
		fileName = "初审-"+new Date().getTime()+".zip";
		String zipPath = firstAuditService.zipFile(entityIds);
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(zipPath);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			FileTool.fileDelete(zipPath);
		}
		return inputStream;
	}
	

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
		
		firstAuditService.delete(entityIds);
		
		return SUCCESS;
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

	@Override
	public String view() {
		product = firstAuditService.getProductById(entityId);
		jsonMap.put("product", product);
		return SUCCESS;
	}

	@Override
	public String toView() {
		return SUCCESS;
	}

	
	
	
	/**
	 * 导出成果获奖一览表
	 * @return
	 * @author xn
	 */
	public String confirmExportOverView(){
		
		return SUCCESS;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public InputStream getDownloadFile() throws UnsupportedEncodingException{
		String header = "成果申报一览表";//表头
		
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
					"校级审核状态",
					"社科联审核状态"
				};
			hql4Export.append("select p.id,p.number,p.name,p.authorName,p.agencyName,p.researchType,r.year,r.level,r.type ,r.bonus from Product p left join p.reward r where r.year=:year order by p.number" );
		
		int currentYear = new Date().getYear()+1900;
		Map map = new HashMap();
		map.put("year", Integer.toString(currentYear));
		
		fileFileName =currentYear+"年"+ header + ".xls";
		fileFileName = new String(fileFileName.getBytes("UTF-8"), "ISO8859-1");
		List<Object[]> list;
		
		//默认为导出当前年份的获奖成果，也可以导出选中的获奖成果
		if(entityIds.isEmpty()){
			
			 list = dao.query(hql4Export.toString(),map);
		} else {
			 list = firstAuditService.getProductInfos(entityIds);
		}
		List dataList = new ArrayList();
		int index = 1;
	
			for (Object object : list) {
				Object[] o = (Object[]) object;
				Object[] data = new Object[10];
				data[0] = index++;
				data[1] = o[1];//成果编号
				data[2] = o[2];//成果名称
				data[3] = o[3];//第一作者
				data[4] = o[4];//依托机构
				data[5] = o[5];//成果类型
				data[6] = o[6];//申请年份
				data[7] = o[5];//校级审核状态
				data[8] = o[6];//社科联审核状态
				
				dataList.add(data);
			}
			 
		return HSSFExport.commonExportExcel(dataList, header, title);
	}
	
	
	
	@Override
	public Object[] simpleSearchCondition() {
		LoginInfo loginer =  (LoginInfo) ActionContext.getContext().getSession().get(GlobalInfo.LOGINER);
		account = loginer.getAccount();
		int currentYear = new Date().getYear()+1900;
		StringBuffer hql = new StringBuffer();
		Map map = new HashMap();
		hql.append(HQL);
		//高校管理员
		if(account.getType()==3){
			hql.append("  and (p.submitStatus =2 or p.submitStatus=3) ");
			hql.append(" and p.agencyName = :agencyName");
			map.put("agencyName", account.getAgency().getName());
			
		}else if(account.getType()==2){
			hql.append("  and ( p.submitStatus =2 or p.submitStatus=3) ");
			hql.append(" and (p.universityAuditResult='2' or p.agency.type='0')");
			
		}else if(account.getType()==1){
			
			//超级管理员所有的都可查询出来，不做任何限制
		}else {
			//非管理员用查询出来的为空
			hql.append(" and p.id = :id");
			map.put("id", "");
		}
		
		if (keyword != null && !keyword.isEmpty()) {
			hql.append(" and ");
			if (searchType == 0) {
				hql.append("LOWER(p.name) like :keyword");
				map.put("keyword", "%" + keyword + "%");
			} else if (searchType == 1) {
				hql.append("LOWER(p.authorName) like :keyword");
				map.put("keyword", "%" + keyword + "%");
			} else if(searchType==2) {
				hql.append("LOWER(p.agencyName) like :keyword ");
				map.put("keyword", "%" + keyword + "%");
			}else if(searchType==3) {
				hql.append("LOWER(p.type) like :keyword ");
				map.put("keyword",  keyword );
			}else if(searchType==4) {
				hql.append("LOWER(p.groupName) like :keyword ");
				map.put("keyword",  keyword );
			}
		}
		
		hql.append("  and p.applyYear = :applyYear");
		map.put("applyYear", Integer.toString(currentYear));
		
		
		return new Object[]{
				hql.toString(),
				map,
				0,
				null
			};		
	}

	@Override
	public Object[] advSearchCondition() {
		
		
		return null;
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

	
	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	

	public String getAuditResult() {
		return auditResult;
	}

	public void setAuditResult(String auditResult) {
		this.auditResult = auditResult;
	}

	public String getAuditOption() {
		return auditOption;
	}

	public void setAuditOption(String auditOption) {
		this.auditOption = auditOption;
	}

	public List<String> getAuditIds() {
		return auditIds;
	}

	public void setAuditIds(List<String> auditIds) {
		this.auditIds = auditIds;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	

}
