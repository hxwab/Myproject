package csdc.service;

import java.util.List;

public interface IStatisticService {
	
	/**
	 * 统计申报作者及获奖作者
	 * @return
	 */
	public List calcApplyAuthor(String year);
	
	
	/**
	 * 统计申报情况
	 * @return
	 */
	public List calcApplyProduct(String year);
	
	
	/**
	 *  统计初评成果
	 * @return
	 */
	public List calc1ReviewProduct(String year);
	
	/**
	 * 统计复评成果
	 * @return
	 */
	public List calc2ReviewProduct(String year);
	
	
	/**
	 * 统计单位申报总数
	 * @return
	 */
	public List calcProduct4Agency(String year);
	
	
	/**
	 * 统计奖项分布
	 * @return
	 */
	public List calcReward4Agency(String year);
	
	
	/**
	 * 封装类
	 * @param type 调用服务类型 ：1统计申报情况、2初评成果、3复评、4单位申报5、作者6、奖项
	 * @param year
	 * @return
	 */
	public List calcService(int  type, String year);

}
