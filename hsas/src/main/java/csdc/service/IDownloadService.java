package csdc.service;

import java.util.List;

import csdc.model.Article;
import csdc.model.SolicitPapers;

public interface IDownloadService {
	
	public SolicitPapers getPaperAttachment(String id);
	
	public Article getArticleAttachment(String id);
	
	public List<Article> getArticles(List<String> entityIds);
	
	public List<SolicitPapers> getSolicitPapers(List<String> entityIds);

	public String addAttachment(Article article);
	
	public String deleteAttachments(List<String> articleIds);
}
