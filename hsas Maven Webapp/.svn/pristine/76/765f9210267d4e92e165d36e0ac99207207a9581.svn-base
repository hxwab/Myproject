package csdc.service;

import java.util.List;

import csdc.model.Discipline;
import csdc.model.Group;
import csdc.model.SystemOption;

/**
 * 学科管理接口
 * @author Yaoyota
 * @version 2015.9.17
 */
public interface IDisciplineService extends IBaseService {
	/**
	 * 判断学科名称是否存在
	 */
	public boolean checkDisciplineName(String disciplineName);
	
	/**
	 * 判断学科代码是否存在
	 */
	public boolean checkDisciplineCode(String disciplineCode);
	/**
	 * 添加学科
	 * @param discipline 学科对象
	 * @return 学科ID
	 */
	public String addDiscipline(SystemOption discipline);
	/**
	 * 删除学科
	 * @param disciplineIds 学科Id集合
	 * @return 1删除成功 0删除失败
	 */
	public int deleDiscipline(List<String> disciplineIds);
	/**
	 * 修改学科
	 * @return 学科ID
	 */
	public String modifyDiscipline(SystemOption oldDiscipline, SystemOption newDiscipline);
	/**
	 * 查看学科
	 */
	
	public SystemOption viewDiscipline(String disciplineId);
	/**
	 * 获取学科分组
	 * @return 学科分组List，List中元素为String[] String[0]为学科分组id String[1]为分组名称
	 */
	
	public List<String[]> getDisciplineGroup();
	
	/**
	 * 通过学科名获取学科门类
	 * @param name
	 * @return
	 */
	public Discipline getDiscipline(String name);
	
	
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
	public List<Group> getAllGroup();

	

}
