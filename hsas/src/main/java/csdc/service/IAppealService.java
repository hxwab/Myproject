package csdc.service;

import java.util.List;
import java.util.Map;

import csdc.model.Article;
import csdc.model.SystemOption;

public interface IAppealService {
	
	/**
	 * 添加申诉
	 * @param appeal
	 * @return
	 */
	public String addAppleal(Article appeal ,Map map);

	/**
	 * 编辑申诉
	 * @param oldAppeal
	 * @param newAppeal
	 * @return
	 */
	public String modify(Article oldAppeal , Article newAppeal);
	
	/**
	 * 删除申诉
	 * @param appealIds
	 */
	public void delete(List<String> appealIds);
	
	/**
	 * 根据id取出相应的申诉
	 * @param articleId
	 * @return
	 */
	public Article getAppeal(String articleId);
	
	/**
	 * 获取相应的系统选项表类型
	 * 
	 * @param typeId
	 * @return
	 */
	public SystemOption getSystemOptionById(String typeId);
}
