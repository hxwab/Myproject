package csdc.action.portal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionContext;

import csdc.action.BaseAction;
import csdc.model.Article;
import csdc.service.IAboubUsService;


@Component
@Scope(value="prototype")
public class AboutUsAction  extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3830045732723694154L;
	private static final String TMP_ENTITY_ID = "aboutUsId";// 用于session缓存实体的ID名称
	
	private Article article;
	
	@Autowired
	private IAboubUsService aboutUsService;
	
	

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

	
	//加权限控制
	@Override
	public String toModify() {
		
		ActionContext.getContext().getSession().put(TMP_ENTITY_ID, entityId);
		return SUCCESS;
	}

	@Override
	public String modify() {
		Article old = aboutUsService.getAboutUs();
		entityId=aboutUsService.edit(article, old);
		return SUCCESS;
	}

	@Override
	public String view() {
		
		article = aboutUsService.getAboutUs();
		jsonMap.put("article", article);
		return SUCCESS;
	}

	@Override
	public String toView() {
		
		return SUCCESS;
	}

	@Override
	public Object[] simpleSearchCondition() {
		// TODO Auto-generated method stub
		return null;
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

	
	
	
	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	

}
