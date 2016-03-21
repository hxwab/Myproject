package csdc.tool.info;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import csdc.model.Discipline;
import csdc.model.Group;
import csdc.service.imp.DisciplineService;

public class DiscipinlesAndGroups {

	public static Map map = new HashMap();
 
	
	public static Map getDiscipinlesAndGroups(){
		
		DisciplineService  disciplineService =  new DisciplineService();
		List<Discipline> disciplines = disciplineService.getAllDiscipline();
		List<Group> groups = disciplineService.getAllGroup();
		map.put("disciplines", disciplines);
		map.put("groups", groups);
		return map;
	}
	
	
	
	
	
	
	
	
}
