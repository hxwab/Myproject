package csdc.action.product;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.util.HSSFColor.GOLD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionContext;

import csdc.action.BaseAction;
import csdc.model.Account;
import csdc.model.Article;
import csdc.model.SystemOption;
import csdc.service.IAppealService;
import csdc.service.IUploadService;
import csdc.service.imp.AppealService;
import csdc.tool.ApplicationContainer;
import csdc.tool.FileTool;
import csdc.tool.bean.FileRecord;
import csdc.tool.bean.LoginInfo;
import csdc.tool.bean.UploadPath;
import csdc.tool.info.GlobalInfo;

@Component
@Scope(value = "prototype")
public class AppealAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -821014937551687334L;
	
	private  static final String HQL = "select a.id ,a.title, a.editor, a.createDate from Article a where a.systemOption.id='appeal'";
	private static final String[]	 COLUMN = {
		"a.title",
		"a.editor",
		"a.createDate"
	};// 用于拼接的排序列
	
	private static final String PAGE_NAME = "appealPage";// 列表页面名称
	private static final String DATE_FORMAT = "yyyy-MM-dd";// 列表时间格式
	private static final String PAGE_BUFFER_ID = "a.id";// 上下条查看时用于查找缓存的字段
	
	private Article appeal;
	private String type;
	
	@Autowired
	private IUploadService uploadService;
	
	@Autowired
	private IAppealService appealService;

	@Override
	public String toAdd() {
		return SUCCESS;
	}

	@Override
	public String add() {
		
		String fileGroupId = "newsAttachmentUpload";
		String fileSavePath = null;
       
        fileSavePath = (String) ApplicationContainer.sc.getAttribute(UploadPath.ARTICEL_ATTACHMENT);
		
		for (FileRecord fileRecord : uploadService.getGroupFiles(fileGroupId)) {
			String newFilePath = FileTool.getAvailableFilename(fileSavePath, fileRecord.getOriginal());
			appeal.setAttachment(newFilePath);
			fileRecord.setDest(new File(ApplicationContainer.sc.getRealPath(appeal.getAttachment())));//将文件移至新的位置(不立刻执行，而在uploadService.flush时再执行)
		}
		uploadService.flush(fileGroupId);
		
		Map map = new HashMap();
		Account account = ((LoginInfo)ActionContext.getContext().getSession().get(GlobalInfo.LOGINER)).getAccount();
		map.put("account", account);
		map.put("type", type);
		entityId=appealService.addAppleal(appeal, map);
		jsonMap.put("entityId", entityId);
		jsonMap.put("tag", "1");
		return SUCCESS;
	}

	@Override
	public String delete() {
		appealService.delete(entityIds);
		return SUCCESS;
	}

	@Override
	public String toModify() {
		appeal = appealService.getAppeal(entityId);
		return SUCCESS;
	}

	@Override
	public String modify() {

		if(appeal.getAttachmentName()!=null){

			String fileGroupId = "newsAttachmentUpload";
			String fileSavePath = null;
	       
	        fileSavePath = (String) ApplicationContainer.sc.getAttribute(UploadPath.ARTICEL_ATTACHMENT);
			
			for (FileRecord fileRecord : uploadService.getGroupFiles(fileGroupId)) {
				String newFilePath = FileTool.getAvailableFilename(fileSavePath, fileRecord.getOriginal());
				appeal.setAttachment(newFilePath);
				fileRecord.setDest(new File(ApplicationContainer.sc.getRealPath(appeal.getAttachment())));//将文件移至新的位置(不立刻执行，而在uploadService.flush时再执行)
			}
			uploadService.flush(fileGroupId);
		}


		
		Article oldAppeal  = appealService.getAppeal(entityId);
		SystemOption systemOption = appealService.getSystemOptionById(type);
		oldAppeal.setSystemOption(systemOption);
		entityId = appealService.modify(oldAppeal, appeal);
		jsonMap.put("tag", "1");
		return SUCCESS;
	}

	@Override
	public String view() {
		appeal =appealService.getAppeal(entityId);
		if(appeal == null){
			return INPUT;
		}
		jsonMap.put("appeal", appeal);
		return SUCCESS;
	}

	@Override
	public String toView() {
		return SUCCESS;
	}

	@Override
	public Object[] simpleSearchCondition() {

		keyword = (keyword == null) ? "" : keyword.toLowerCase().trim();// 预处理关键字
		StringBuffer hql = new StringBuffer();
		Map map = new HashMap();
		hql.append(HQL);
		if (keyword != null && !keyword.isEmpty()) {
			if (searchType == 1) {
				hql.append("and LOWER(a.title) like :keyword");
				map.put("keyword", "%" + keyword + "%");
			} else if (searchType == 2) {
				hql.append("and LOWER(a.editor) like :keyword ");
				map.put("keyword", "%" + keyword + "%");
			}else{
				hql.append("and (LOWER(a.title) like :keyword or " +
						"LOWER(a.editor) like :keyword )");
				map.put("keyword", "%" + keyword + "%");
			}
		}
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

	public Article getAppeal() {
		return appeal;
	}

	public void setAppeal(Article article) {
		this.appeal = article;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	
	
}
