package csdc.action.portal;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.security.RolesAllowed;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.config.StrutsXmlConfigurationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import csdc.action.BaseAction;
import csdc.model.Article;
import csdc.model.SolicitPapers;
import csdc.service.IDownloadService;
import csdc.service.INewsService;
import csdc.service.IUploadService;
import csdc.service.imp.DownloadService;
import csdc.service.imp.NewsService;
import csdc.tool.ApplicationContainer;
import csdc.tool.FileTool;
import csdc.tool.StringTool;
import csdc.tool.bean.FileRecord;
import csdc.tool.bean.UploadPath;
import csdc.tool.info.GlobalInfo;

@Component
@Scope(value="prototype")

public class DownloadAction extends BaseAction{

	/**
	 * 遍历文件，链接下载（提供详情么？）
	 * 表格下载和文章下载
	 */
	private static final long serialVersionUID = 6312354941195229315L;
	
	private static final String PAGE_NAME = "downloadPage";// 列表页面名称
	private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss:SS";// 列表时间格式
	private static final String PAGE_BUFFER_ID = "a.id";// 上下条查看时用于查找缓存的字段
	private static final String TMP_ENTITY_ID = "downloadId";// 用于session缓存实体的ID名称
	
	private static final String[] COLUMN = {
		"a.id",
		"a.attachmentName",
		"a.createDate"
	};
	
	
	private InputStream fileStream;
	private String  fileName;
	private String  fileFileName;
	
	private Article attachment;
	private Article  upload;
	private SolicitPapers solicitPapers;
	//private Object attachment;
	
	@Autowired
	private IDownloadService downloadService;
	
	@Autowired
	private IUploadService uploadService;

	private String type;
	/**
	 * 提供论文或表格的批量下载
	 * @return
	 */
	public String download(){
		
		attachment = downloadService.getArticleAttachment(entityId);
		
		
		if (attachment == null) {
			jsonMap.put("tag", "2");
			return INPUT;
		}
		if (attachment.getAttachment() == null) {
			jsonMap.put("tag", "2");
			return INPUT;
		}
		//文件名后缀
		//String suffix = attachment.getAttachment().substring(attachment.getAttachment().lastIndexOf("."), attachment.getAttachment().length());
		//完整文件名
	//	fileFileName = attachment.getName()+suffix;
		fileStream = ApplicationContainer.sc.getResourceAsStream(attachment.getAttachment());
		//fileStream =ServletActionContext.getServletContext().getResourceAsStream(attachment.getAttachment());
		System.out.println(fileStream);
		if (fileStream == null) {
			jsonMap.put("tag", "2");
			return INPUT;
		}
		try {
			fileName = new String(attachment.getAttachmentName().getBytes(), "ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		jsonMap.put("tag", "1");
		return SUCCESS;
		
	}
	
	
	
	public String downloadPaper(){
		
		solicitPapers = downloadService.getPaperAttachment(entityId);
		
		
		if (solicitPapers == null) {
			jsonMap.put("tag", "2");
			return INPUT;
		}
		if (solicitPapers.getAttachment() == null) {
			jsonMap.put("tag", "2");
			return INPUT;
		}
		
		fileStream = ApplicationContainer.sc.getResourceAsStream(solicitPapers.getAttachment());
		if (fileStream == null) {
			jsonMap.put("tag", "2");
			return INPUT;
		}
		try {
			fileName = new String(solicitPapers.getAttachmentName().getBytes(), "ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		jsonMap.put("tag", "1");
		return SUCCESS;
		
	}
	
	
	/**
	 * 提供论文或表格的批量下载
	 * @return
	 */
	public String MultiDownload(){
		//TODO
		return SUCCESS;
	}
	
	
	
	
	/**
	 * 添加上传附件或表格
	 */
	@RolesAllowed("ROLE_ADMIN")
	//@Secured
	//@PreAuthorize
	@Override
	public String toAdd() {
		
		return SUCCESS;
	}

	/*@Override
	public String add() {
		String fileGroupId = "downloadFileUpload";
		
		String fileSavePath = null;
		
		if(upload==null){
			jsonMap.put("tag", "2");
			return INPUT;
		}
       
		fileSavePath = (String) ApplicationContainer.sc.getAttribute(UploadPath.ARTICEL_ATTACHMENT);//设置课题附件的路径
		
		for (FileRecord fileRecord : uploadService.getGroupFiles(fileGroupId)) {
			String newFilePath = FileTool.getTrueFilename(fileSavePath, fileRecord.getOriginal());
			upload.setAttachment(newFilePath);
			fileRecord.setDest(new File(ApplicationContainer.sc.getRealPath(upload.getAttachment())));//将文件移至新的位置(不立刻执行，而在uploadService.flush时再执行)
		}
		uploadService.flush(fileGroupId);
		entityId = downloadService.addAttachment(upload);
		jsonMap.put("tag", "1");
		
		return SUCCESS;
	}
*/
	
	@Override
	public String add() {
		String fileGroupId = "downloadFileUpload";
		
		String fileSavePath = null;
		
		if(upload==null){
			jsonMap.put("tag", "2");
			return INPUT;
		}
       
		fileSavePath = (String) ApplicationContainer.sc.getAttribute(UploadPath.ARTICEL_ATTACHMENT);//设置课题附件的路径
		List<String> attachments = new ArrayList<String>();
		List<String> attachmentNames = new ArrayList<String>();
		for (FileRecord fileRecord : uploadService.getGroupFiles(fileGroupId)) {
			//String newFilePath = FileTool.getTrueFilename(fileSavePath, fileRecord.getOriginal());
			String newFilePath = FileTool.getTrueFilename(fileSavePath,fileRecord.getOriginal());//文件在数据库中的存储地址（包括存储路径和新的文件名）
			String orignalFileName = fileRecord.getOriginal().getName();//原始文件名
			attachments.add(newFilePath);
			attachmentNames.add(orignalFileName);
			//upload.setAttachment(newFilePath);
			fileRecord.setDest(new File(ApplicationContainer.sc.getRealPath(newFilePath)));//将文件移至新的位置(不立刻执行，而在uploadService.flush时再执行)
		}
		
		upload.setAttachment(StringTool.joinString(attachments.toArray(new String[0]), "; "));
		upload.setAttachmentName(StringTool.joinString(attachmentNames.toArray(new String[0]), "; "));
		uploadService.flush(fileGroupId);
		entityId = downloadService.addAttachment(upload);
		jsonMap.put("tag", "1");
		
		return SUCCESS;
	}

	@Override
	public String delete() {
		downloadService.deleteAttachments(entityIds);
		return SUCCESS ;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toView() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 此处复用该方法，传参数辨别列表是表格下载还是文章下载
	 * 
	 */
	
	
	@Override
	public Object[] simpleSearchCondition() {
		 StringBuffer hql = new StringBuffer();
		 Map map = new HashMap();
		 /*if(type.equals("paper")){
			hql.append("select sp.id,sp.name,sp.author,sp.createDate,sp.type from SolicitPapers sp where 1=1");
			if(keyword!=null&&!keyword.isEmpty()){
				 hql.append(" and ");
					if (searchType == 1) {
						hql.append("LOWER(sp.name) like :keyword");
						map.put("keyword", "%" + keyword + "%");
					} else if (searchType == 2) {
						hql.append("LOWER(sp.author) like :keyword");
						map.put("keyword", "%" + keyword + "%");
					} else if(searchType==3){
						hql.append("LOWER(sp.type) like :keyword");
						map.put("keyword", "%" + keyword + "%");
					} 
			 }
			 
		 }else if(type.equals("article")){
			 */
			 hql.append("select a.id ,a.attachmentName ,a.createDate from Article a where 1=1 and a.systemOption = 'download'");
			 if(keyword!=null&&!keyword.isEmpty()){
					if (searchType == 1) {
						hql.append("and LOWER(a.attachmentName) like :keyword");
						map.put("keyword", "%" + keyword + "%");
					}
			 }
		// }
		
		return new Object[]{
				hql.toString(),
				map,
				0,
				null,
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


	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileFileName() {
		return fileFileName;
	}


	public void setFileStream(InputStream fileStream) {
		this.fileStream = fileStream;
	}


	public InputStream getFileStream() {
		return fileStream;
	}


	public Article getUpload() {
		return upload;
	}


	public void setUpload(Article upload) {
		this.upload = upload;
	}


	


	

}
