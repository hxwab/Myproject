package csdc.action.portal;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.config.StrutsXmlConfigurationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import csdc.action.BaseAction;
import csdc.model.Article;
import csdc.model.SolicitPapers;
import csdc.service.IDownloadService;
import csdc.service.INewsService;
import csdc.service.imp.DownloadService;
import csdc.service.imp.NewsService;
import csdc.tool.ApplicationContainer;
import csdc.tool.info.GlobalInfo;

@Component
@Scope(value="prototype")

public class DownloadAction extends BaseAction{

	/**
	 * 遍历文件，链接下载（提供详情么？）
	 * 表格下载和文章下载
	 */
	private static final long serialVersionUID = 6312354941195229315L;
	
	private InputStream fileStream;
	private String  fileName;
	private String  fileFileName;
	
	private Article attachment;
	//private Object attachment;
	
	@Autowired
	private IDownloadService downloadService;

	private String type;
	
	public String toDownload() {
		return SUCCESS;
	}
	
	/**
	 * 提供论文或表格的批量下载
	 * @return
	 */
	public String download(){
		
		/*if(type.equals("0")){
			artcile = downloadService.getArticleAttachment(entityId);
					
		}else {
			paper = downloadService.getPaperAttachment(entityId);
		}*/
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
			fileName = new String(attachment.getAttachment().getBytes(), "ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		jsonMap.put("tag", "1");
		return SUCCESS;
		
	}
	
	
	
	public String MultiDownload(){
		
		
		
		
		
		
		return SUCCESS;
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
		 if(type.equals("paper")){
			 
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
			 
			 hql.append("select a.id ,a.attachmentName from Article a where 1=1 and a.systemOption = 'download'");
			 if(keyword!=null&&!keyword.isEmpty()){
					if (searchType == 1) {
						hql.append("and LOWER(a.attachmentName) like :keyword");
						map.put("keyword", "%" + keyword + "%");
					}
			 }
		 }
		
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String pageBufferId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] sortColumn() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String dateFormat() {
		// TODO Auto-generated method stub
		return null;
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



}
