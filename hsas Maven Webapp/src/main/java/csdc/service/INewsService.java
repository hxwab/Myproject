package csdc.service;

import java.util.List;
import java.util.Map;

import csdc.model.Article;
import csdc.model.SystemOption;

public interface INewsService {
	
	/**
	 * 取出相应的新闻类型并封装为json
	 * @param type  新闻类型
	 * @param num   取出条数
	 * @return
	 */
	public Map  getArticles(String type,Integer num);
	
	/**
	 * 根据id取出相应的新闻
	 * @param articleId
	 * @return
	 */
	public Article getArticle(String articleId);
	
	/**
	 * 添加新闻
	 * @param article
	 * @return
	 */
	public String addArticle(Article article);
	
	
	/**
	 * 编辑新闻
	 * @param oldArticle
	 * @param newArticle
	 * @return
	 */
	public String modify(Article oldArticle , Article newArticle);
	
	/**
	 * 删除新闻
	 * @param articleId
	 */
	public void delete(List<String> articleIds);
	
	/**
	 * 获取相应的系统选项表类型
	 * 
	 * @param typeId
	 * @return
	 */
	public SystemOption  getSystemOptionById(String typeId);
	
	
	
}
