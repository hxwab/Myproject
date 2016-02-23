package csdc.action.portal;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionContext;

import csdc.action.BaseAction;
import csdc.model.Article;
import csdc.model.SystemOption;
import csdc.service.INewsService;
import csdc.service.IUploadService;
import csdc.tool.ApplicationContainer;
import csdc.tool.FileTool;
import csdc.tool.bean.FileRecord;
import csdc.tool.bean.UploadPath;


@Component
@Scope(value="prototype")
public class NewsAction  extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2502105311448301085L;
	
	private static final String HQL = "select a.id, a.title, a.editor, a.source,a.createDate from Article a left join a.systemOption so ";
	private static final String PAGE_NAME = "NewsPage";// 列表页面名称
	private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss:SS";// 列表时间格式
	private static final String PAGE_BUFFER_ID = "a.id";// 上下条查看时用于查找缓存的字段
	private static final String TMP_ENTITY_ID = "newsId";// 用于session缓存实体的ID名称
	
	private static final String[] COLUMN = {
		"a.id",
		"a.title",
		"a.editor",
		"a.source" ,
		"a.type",
		"a.createDate"
	};
	
	
	private String type;
	
	private Article article;
	
	@Autowired
	private INewsService iNewsService;
	
	@Autowired
	private IUploadService uploadService;
	
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
			article.setAttachment(newFilePath);
			fileRecord.setDest(new File(ApplicationContainer.sc.getRealPath(article.getAttachment())));//将文件移至新的位置(不立刻执行，而在uploadService.flush时再执行)
		}
		uploadService.flush(fileGroupId);
		
		
		String username = (String) ActionContext.getContext().getSession().get("username");
		SystemOption systemOption = iNewsService.getSystemOptionById(type);
		article.setSystemOption(systemOption);
		article.setEditor(username);
		entityId = iNewsService.addArticle(article);
		jsonMap.put("tag", 1);
		jsonMap.put("entityId", entityId);
		return SUCCESS;
	}

	@Override
	public String delete() {
		iNewsService.delete(entityIds);
		return SUCCESS;
	}

	@Override
	public String toModify() {
	
		article = iNewsService.getArticle(entityId);
		return SUCCESS;
	}

	@Override
	public String modify() {
		
	//	entityId = (String) ActionContext.getContext().getSession().get(TMP_ENTITY_ID);
		Article oldArticle  =iNewsService.getArticle(entityId);
		SystemOption systemOption = iNewsService.getSystemOptionById(type);
		oldArticle.setSystemOption(systemOption);
		entityId=iNewsService.modify(oldArticle, article);
	//ActionContext.getContext().getSession().remove(TMP_ENTITY_ID);
		jsonMap.put("tag", "1");
		return SUCCESS;
	}

	@Override
	public String view() {
		
		Article article =  iNewsService.getArticle(entityId);
		article.setViewCount(article.getViewCount()+1);
		iNewsService.modify(article, null);
		jsonMap.put("article",article);
		return SUCCESS;
	}

	@Override
	public String toView() {
		return SUCCESS ;
	}

	/**
	 * 强制更新页面传参数update=1
	 */
	@Override
	public Object[] simpleSearchCondition() {

		StringBuffer hql = new StringBuffer();
		Map map = new HashMap();
		hql.append(HQL);
		if(type!=null){
			map.put("type", type);
			hql.append("where so.name =:type order by a.createDate desc");
		}else {
			hql.append("order by a.createDate desc");
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

	
	
	public Article getArticle() {

		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	
	
	
	
	
	
}
