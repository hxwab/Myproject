package csdc.service;

import java.util.List;
import java.util.Set;

import csdc.model.Discipline;
import csdc.model.Groups;

/**
 * 学科管理接口
 * @author Yaoyota
 * @version 2015.9.17
 */
public interface IGroupService extends IBaseService {
	/**
	 * 判断分组是否存在
	 * @param 分组名
	 */
	public boolean checkGroup(String groupName);
	/**
	 * 添加分组
	 * @param group 分组对象
	 * @return 分组ID
	 */
	public String addGroup(Groups group);
	/**
	 * 删除分组
	 * @param groupIds 分组Id集合
	 * @return 1删除成功 0删除失败
	 */
	public int deleGroup(List<String> groupIds);
	/**
	 * 修改分组
	 * @return 分组ID
	 */
	public String modifyGroup(Groups oldGroup, Groups newGroup);
	/**
	 * 查看分组
	 */
	
//	public Groups viewGroup(String groupId);
	/**
	 * 获取学科
	 * @param name 学科名字
	 * @return 要获取的学科
	 */
	public Discipline getDiscipline(String name);
	
	/**
	 * 获取分组
	 * @param groupName 分组名
	 * @return
	 */
	public Groups getGroupByName(String groupName);
	
	
	//*****************以下两方法不要轻易改动，在baseAction中静态调用*******************//
	
	/**
	 * 获取所有学科
	 * @return 
	 */
	public  List<Discipline> getAllDiscipline();
	
	/**
	 * 获取所有分组
	 * @return
	 */
	public List<Groups> getAllGroup();
	/**
	 * 合并分组，把源分组合并到目的分组中
	 * @param srcId 源分组id
	 * @param dstId 目的分组id
	 */
	public void merge(String srcId, String dstId);
	
	

}