package csdc.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import csdc.model.Article;
import csdc.model.SolicitPapers;
import csdc.service.IDownloadService;

@Service
public class DownloadService extends BaseService implements IDownloadService {

	
	public SolicitPapers getPaperAttachment(String id) {
		
		return dao.query(SolicitPapers.class, id);
	}

	
	public Article  getArticleAttachment(String id) {
		
		return dao.query(Article.class, id);
	}


	@Override
	public List<Article> getArticles(List<String> entityIds) {
		
		List<Article> articles = new ArrayList<Article>();
		Article article;
		for(String id:entityIds){
			article=dao.query(Article.class,id);
			articles.add(article);
		}
		
		
		return articles;
	}


	@Override
	public List<SolicitPapers> getSolicitPapers(List<String> entityIds) {
		
		List<SolicitPapers> solicitPapers = new ArrayList<SolicitPapers>();
		SolicitPapers solictPaper;
		for(String id:entityIds){
			solictPaper = dao.query(SolicitPapers.class,id);
			solicitPapers.add(solictPaper);
		}
		return solicitPapers;
	}

}
