package csdc.tool.info;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import csdc.model.Discipline;
import csdc.model.Groups;
import csdc.service.imp.GroupService;

public class DiscipinlesAndGroups {

	public static Map map = new HashMap();
 
	
	public static Map getDiscipinlesAndGroups(){
		
		GroupService  disciplineService =  new GroupService();
		List<Discipline> disciplines = disciplineService.getAllDiscipline();
		List<Groups> groups = disciplineService.getAllGroup();
		map.put("disciplines", disciplines);
		map.put("groups", groups);
		return map;
	}
	
}
