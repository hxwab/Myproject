package csdc.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface IBaseService {
	/**
	 * 添加实体
	 * @param entity 实体的类
	 */
	public String add(Object entity);
	
	/**
	 * 添加/修改实体集合
	 * @param entity 实体的类
	 */
	@SuppressWarnings("unchecked")
	public void addOrModify(Collection entity);
	
	/**
	 * 
	 * @param entity
	 */
	public void delete(Object entity);
	
	/**
	 * 
	 * @param entityClass
	 * @param id
	 */
	@SuppressWarnings("unchecked")
	public void delete(Class entityClass, Serializable id);
	
	/**
	 * 
	 * @param entity
	 */
	public void modify(Object entity);
	

	@SuppressWarnings("unchecked")
	public Object query(Class entityClass, Serializable id);
	

	@SuppressWarnings("unchecked")
	public List query(String queryString);
	@SuppressWarnings("unchecked")
	public List query(String queryString, Map parMap);
	
	public String accquireFileSize(long fileLength);
	
}
