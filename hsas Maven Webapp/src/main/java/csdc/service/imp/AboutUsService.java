package csdc.service.imp;

import org.springframework.stereotype.Service;

import csdc.model.Article;
import csdc.service.IAboubUsService;


@Service
public class AboutUsService extends BaseService implements IAboubUsService {

	
	@Override
	public Article getAboutUs() {
		
		return (Article) dao.query("select a from Article a left join a.systemOption so  where so.name='aboutUs'"); 
	}


	@Override
	public String edit(Article newArticle, Article oldArticle) {
		
		oldArticle.setContent(newArticle.getContent());
		dao.modify(oldArticle);
		return oldArticle.getId();
	}

}
