package csdc.service.imp;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import csdc.model.Account;
import csdc.model.Article;
import csdc.model.SystemOption;
import csdc.service.IAppealService;

@Service
public class AppealService extends BaseService implements IAppealService {

	@Override
	public String addAppleal(Article article, Map map) {
		Account account = (Account) map.get("account");
		String typeId = map.get("type").toString();
		String author = account.getPerson().getName();
		SystemOption systemOption = getSystemOptionById(typeId);
		
		article.setAccount(account);
		article.setAuthor(author);
		article.setCreateDate(new Date());
		article.setViewCount(0);
		article.setSystemOption(systemOption);
		article.setIsOpen(0);
		return dao.add(article);
	}

	@Override
	public String modify(Article oldArticle, Article newArticle) {
		if(newArticle!=null){
			oldArticle.setEditor(newArticle.getEditor());
			oldArticle.setContent(newArticle.getContent());
			oldArticle.setTitle(newArticle.getTitle());
		}
		if(oldArticle.getAttachmentName()!=newArticle.getAttachmentName()){
			oldArticle.setAttachment(newArticle.getAttachment());
			oldArticle.setAttachmentName(newArticle.getAttachmentName());
		}
		
		oldArticle.setCreateDate(new Date());
		dao.modify(oldArticle);
		return oldArticle.getId();
	}

	@Override
	public void delete(List<String> articleIds) {
		for(String id:articleIds){
			dao.delete(Article.class, id);
		}
	}
	
	@Override
	public SystemOption getSystemOptionById(String typeId) {
		return dao.query(SystemOption.class, typeId);
	}

	@Override
	public Article getAppeal(String entityId) {
		return dao.query(Article.class, entityId);
	}
}
