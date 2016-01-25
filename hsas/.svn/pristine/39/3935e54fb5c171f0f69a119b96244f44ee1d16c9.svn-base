package csdc.action.solicitPapers;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import csdc.action.BaseAction;
import csdc.model.SolicitPapers;
import csdc.service.imp.UploadService;
import csdc.tool.ApplicationContainer;
import csdc.tool.FileTool;
import csdc.tool.bean.FileRecord;
import csdc.tool.bean.UploadPath;

import csdc.service.ISolicitPapersService;
import csdc.service.IUploadService;
import freemarker.core.ReturnInstruction.Return;


@Component
@Scope(value = "prototype")
public class SolicitPapersAction extends BaseAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5907082392532922869L;
	
	private  static final String HQL = "select s.id ,s.name, s.author, s.unit, s.phone, s.createDate from SolicitPapers s where 1=1 ";
	
	private static final String PAGE_NAME = "solicitPapersPage";// 列表页面名称
	private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss:SS";// 列表时间格式
	private static final String PAGE_BUFFER_ID = "s.id";// 上下条查看时用于查找缓存的字段
	private static final String TMP_ENTITY_ID = "paperId";// 用于session缓存实体的ID名称
	
	private static final String[] COLUMN = {
		"s.id",
		"s.createDate"
	};

	private int type;
	
	private SolicitPapers solicitPapers;
	
	@Autowired
	private IUploadService uploadService;
	
	@Autowired
	private ISolicitPapersService  solicitPapersService ;
	
	//private String entityId;
	
	
	public String toAdd() {
		/*
		if(flag.equals("0")){
			
			return "forum";
		} else if(flag.equals("1")){
			
			return "topic";
		}else {
			
			return INPUT;
		}*/
		return SUCCESS;
	}

	public String add() {
		String fileGroupId = "solicitPapersUpload";
		//int type = Integer.valueOf(solicitPapers.getType());
		String fileSavePath = null;
        if (type == 1) {
        	fileSavePath = (String) ApplicationContainer.sc.getAttribute(UploadPath.PAPER_FORUM);//设置论坛附件的路径
		}else {
			fileSavePath = (String) ApplicationContainer.sc.getAttribute(UploadPath.PAPER_TOPIC);//设置课题附件的路径
		}
	//	String fileSavePath1 = (String) ApplicationContainer.sc.getAttribute(UploadPath.ARTICEL_ATTACHMENT);//设置附件的路径
		for (FileRecord fileRecord : uploadService.getGroupFiles(fileGroupId)) {
			String newFilePath = FileTool.getAvailableFilename(fileSavePath, fileRecord.getOriginal());
			getSolicitPapers().setAttachment(newFilePath);
			fileRecord.setDest(new File(ApplicationContainer.sc.getRealPath(getSolicitPapers().getAttachment())));//将文件移至新的位置(不立刻执行，而在uploadService.flush时再执行)
		}
		uploadService.flush(fileGroupId);
		solicitPapers.setType(Integer.toString(type));
		entityId = solicitPapersService.addDocument(solicitPapers);
		jsonMap.put("tag", "1");
		jsonMap.put("entityId", entityId);
		return SUCCESS;
	}
	
	
	@Override
	public String delete() {
		solicitPapersService.delete(entityIds);
		return SUCCESS;
	}



	//附件怎么修改？
	@Override
	public String modify() {
		entityId = (String) ActionContext.getContext().getSession().get(TMP_ENTITY_ID);
		SolicitPapers  oPapers = solicitPapersService.getSolicitPapers(entityId);
		String fileGroupId = "solicitPapersUpload";
		//int type = Integer.valueOf(solicitPapers.getType());
		String fileSavePath = null;
        if (type == 1) {
        	fileSavePath = (String) ApplicationContainer.sc.getAttribute(UploadPath.PAPER_FORUM);//设置论坛附件的路径
		}else {
			fileSavePath = (String) ApplicationContainer.sc.getAttribute(UploadPath.PAPER_TOPIC);//设置课题附件的路径
		}
	//	String fileSavePath1 = (String) ApplicationContainer.sc.getAttribute(UploadPath.ARTICEL_ATTACHMENT);//设置附件的路径
		if(solicitPapers.getAttachmentName()!=null){
			
			for (FileRecord fileRecord : uploadService.getGroupFiles(fileGroupId)) {
				String newFilePath = FileTool.getAvailableFilename(fileSavePath, fileRecord.getOriginal());
				getSolicitPapers().setAttachment(newFilePath);
				fileRecord.setDest(new File(ApplicationContainer.sc.getRealPath(getSolicitPapers().getAttachment())));//将文件移至新的位置(不立刻执行，而在uploadService.flush时再执行)
			}
			uploadService.flush(fileGroupId);
			
			oPapers.setAttachment(solicitPapers.getAttachment());
			oPapers.setAttachmentName(solicitPapers.getAttachmentName());
		}

		solicitPapersService.modifyDocument(solicitPapers,oPapers);
		jsonMap.put("tag", "1");
		jsonMap.put("entityId", entityId);
		ActionContext.getContext().getSession().remove(TMP_ENTITY_ID);
		
		return SUCCESS;
	}


	@Override
	public String toModify() {
		solicitPapers =solicitPapersService.getSolicitPapers(entityId);
		ActionContext.getContext().getSession().put(TMP_ENTITY_ID, entityId);
		return SUCCESS;
	}

	@Override
	public String view() {
		
		solicitPapers =solicitPapersService.getSolicitPapers(entityId);
		jsonMap.put("solicitPapers", solicitPapers);
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
		
		hql.append(" and s.type = :type ");
		map.put("type", Integer.toString(type));
		
		if (keyword != null && !keyword.isEmpty()) {
			if (searchType == 1) {
				hql.append("and LOWER(s.name) like :keyword");
				map.put("keyword", "%" + keyword + "%");
			} else if(searchType == 2){
				hql.append("and (LOWER(s.author) like :keyword or " +
						"LOWER(s.author) like :keyword )");
				map.put("keyword", "%" + keyword + "%");
			}else {
				hql.append("and (LOWER(s.unit) like :keyword or " +
						"LOWER(s.unit) like :keyword )");
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


	public SolicitPapers getSolicitPapers() {
		return solicitPapers;
	}


	public void setSolicitPapers(SolicitPapers solicitPapers) {
		this.solicitPapers = solicitPapers;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public IUploadService getUploadService() {
		return uploadService;
	}
	public void setUploadService(IUploadService uploadService) {
		this.uploadService = uploadService;
	}
	
}
