package csdc.service.imp;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import csdc.model.Discipline;
import csdc.model.Groups;
import csdc.model.GroupDiscipline;
import csdc.service.IGroupService;
/**
 * 学科管理接口实现
 * @author Yaoyota
 *
 */
@Service
public class GroupService extends BaseService implements IGroupService {
	
	@Override
	public Groups getGroupByName(String groupName) {
		Map map = new HashMap();
		map.put("groupName", groupName);
		List list = dao.query("from Groups gr where gr.name = :groupName", map);
		return (Groups) (list.size() !=0 ? list.iterator().next() : null);
	}
	
	@Override
	public boolean checkGroup(String groupName) {
		if(groupName == null) {
			return true; //学科名称为空，视为存在
		} else {
			Map map = new HashMap();
			map.put("groupName", groupName);
			List list = dao.query("from Groups gr where gr.name = :groupName", map);
			return list.isEmpty()? false:true;  
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public String addGroup(Groups group) {
//		String hql = "from Discipline dis where dis.id in :disciplineIds";
//		Map map = new HashMap();
//		map.put("disciplineIds", disciplineIds);
//		List<Discipline> disciplines = dao.query(hql, map);
//		Set<GroupDiscipline> gds = new HashSet<GroupDiscipline>();
//		for(Discipline dis: disciplines) {
//			GroupDiscipline gd = new GroupDiscipline();
//			gd.setDiscipline(dis);
//			gds.add(gd);
//		}
//		group.setGroupDiscipline(gds);
		int oldIndex = 0;
		String hql1 = "from Groups gr";
		String hql2 = "select max(gr.index) from Groups gr";
		List<Groups> groups = dao.query(hql1);
		if(!groups.isEmpty()) {
			List<Integer> list = dao.query(hql2);
			oldIndex = list.get(0);
		}
		group.setIndex(++oldIndex);
		String id = dao.add(group);
		return id;
	}

	@Override
	public int deleGroup(List<String> groupIds) {
		try {
			for(String id: groupIds) {
				dao.delete(Groups.class, id);
			}
		} catch (Exception e) {
			// TODO: handle exception
			return 0;//删除失败，返回0
		}
		return 1;//删除成功，返回1
	}

	@Override
	public String modifyGroup(Groups oldGroup, Groups newGroup){
//		String hql = "from Discipline dis where dis.id in :disciplineIds";
//		Map map = new HashMap();
//		map.put("disciplineIds", disciplineIds);
//		List<Discipline> disciplines = dao.query(hql, map);
//		Set<GroupDiscipline> gds = new HashSet<GroupDiscipline>();
//		for(Discipline dis: disciplines) {
//			GroupDiscipline gd = new GroupDiscipline();
//			gd.setDiscipline(dis);
//			gds.add(gd);
//		}
		oldGroup.setName(newGroup.getName());
		oldGroup.setDescription(newGroup.getDescription());
//		oldGroup.setGroupDiscipline(gds);
		dao.modify(oldGroup);
		return oldGroup.getId();
	}

//	@Override
//	public Groups viewGroup(String groupId) {
//		Groups group = dao.query(Groups.class, groupId);
//		Set<Discipline> disSet = new HashSet<Discipline>();
//		Set<GroupDiscipline> gds = group.getGroupDiscipline();
//		for(GroupDiscipline gd: gds) {
//			disSet.add(gd.getDiscipline());
//		}
//		group.setDisciplines(disSet);
//		return group;
//	}

	//*****************以下两方法不要轻易改动，在baseAction中静态调用*******************//
	
	public  List<Discipline> getAllDiscipline() {
		String hql = "select d from Discipline d where 1=1 order by d.id";
		List<Discipline> disciplines =dao.query(hql);
		return disciplines;
	}

	
	public  List<Groups> getAllGroup() {
		Map map = null;
		String hql = "select g from Groups g where 1=1 order by g.index";
		List<Groups> groups = dao.query(hql);
		return groups;
	}

	public Discipline getDiscipline(String name) {
		Discipline dis = null;
		String hql = "from Discipline dis where dis.name = :name";
		Map map = new HashMap();
		map.put("name", name);
		dis = (Discipline) dao.queryUnique(hql, map);
		return dis;
	}

	@Override
	public void merge(String srcId, String dstId) {
		Groups dstGroup = (Groups)dao.query(Groups.class, dstId);
		Map map = new HashMap();
		map.put("dstGroup", dstGroup);
		map.put("srcId", srcId);
		String update1 = "update Product pr set pr.groups = :dstGroup where pr.groups.id=:srcId";
		String update2 = "update Expert ex set ex.groups = :dstGroup where ex.groups.id = :srcId";
		dao.execute(update1, map);
		dao.execute(update2, map);
		dao.delete(Groups.class, srcId);
	}

}