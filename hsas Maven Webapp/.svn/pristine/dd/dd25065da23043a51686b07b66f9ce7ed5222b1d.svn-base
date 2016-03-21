package csdc.action.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import csdc.action.BaseAction;
import csdc.model.SystemOption;
import csdc.service.IDisciplineService;
import csdc.tool.info.DisciplineInfo;
import csdc.tool.info.GlobalInfo;

@Component
@Scope(value="prototype")

public class DisciplineAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8507240264861492582L;
	private static final String HQL = "select dis.id, dis.name, dis.code from SystemOption dis  where 1=1";
	private static final String[] COLUMN = {
		"dis.name",
		"dis.code, dis.name",
	};// 用于拼接的排序列
	private static final String PAGE_NAME = "disciplinePage";// 列表页面名称
	private static final String DATE_FORMAT = "yyyy-MM-dd";// 列表时间格式
	private static final String PAGE_BUFFER_ID = "discipline.id";// 上下条查看时用于查找缓存的字段
	private static final String TMP_ENTITY_ID = "disciplineId";// 用于session缓存实体的ID名称
	
	private String keyword1, keyword2;// 高级检索关键字
	private SystemOption discipline;//学科对象
	@Autowired
	private IDisciplineService disciplineService;
	@Override
	public String toAdd() {
		// TODO Auto-generated method stub
		return SUCCESS;
	}

	@Override
	@Transactional
	public String add() {
		if (discipline.getName()==null||discipline.getName().trim().isEmpty()) {//学科名称为空，返回错误提示
			this.addFieldError(GlobalInfo.ERROR_INFO, DisciplineInfo.ERROR_DISCIPLINE_NAME_NULL);
			return INPUT;
		} else {
			if (disciplineService.checkDisciplineName(discipline.getName())) {// 学科名称存在，返回错误提示
				this.addFieldError(GlobalInfo.ERROR_INFO, DisciplineInfo.ERROR_DISCIPLINE_NAME_NULL);
				return INPUT;
			}
		}
		
		if(discipline.getCode()==null||discipline.getCode().trim().isEmpty()) {//学科代码为空，返回错误提示
			this.addFieldError(GlobalInfo.ERROR_INFO, DisciplineInfo.ERROR_DISCIPLINE_CODE_NULL);
			return INPUT;
		} else {
			if(disciplineService.checkDisciplineCode(discipline.getCode())) {//学科代码存在，返回错误提示
				this.addFieldError(GlobalInfo.ERROR_INFO, DisciplineInfo.ERROR_DISCIPLINE_CODE_EXIST);
				return INPUT;
			}
		}
		
		entityId = disciplineService.addDiscipline(discipline);
		jsonMap.put("tag", 1);
		return SUCCESS;
	}
	
	@Override
	@Transactional
	public String delete() {
		// TODO Auto-generated method stub
		int flag;
		flag = disciplineService.deleDiscipline(entityIds);
		if(flag==1) //是否删除成功
			return SUCCESS;
		else
			return ERROR;
	}

	@Override
	public String toModify() {
		discipline = (SystemOption) dao.query(SystemOption.class, entityId);// 获取学科
		if (discipline == null) {// 权限不存在，返回错误提示
			this.addFieldError(GlobalInfo.ERROR_INFO, DisciplineInfo.ERROR_DISCIPLINE_NULL);
			return INPUT;
		} else {// 权限存在，备用权限ID
			session.put(TMP_ENTITY_ID, entityId);
			return SUCCESS;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public String modify() {
		entityId = (String)session.get(TMP_ENTITY_ID);//获取备用学科Id
		SystemOption oldDiscipline = (SystemOption)dao.query(SystemOption.class, entityId);//获取原来学科
		if (discipline.getName()==null||discipline.getName().trim().isEmpty()) {//学科名称为空，返回错误提示
			this.addFieldError(GlobalInfo.ERROR_INFO, DisciplineInfo.ERROR_DISCIPLINE_NAME_NULL);
			return INPUT;
		} else {
			// 如果学科名称发生变化，校验学科名称是否存在
			if(!oldDiscipline.getName().equals(discipline.getName())&&disciplineService.checkDisciplineName(discipline.getName())) {
				this.addFieldError(GlobalInfo.ERROR_INFO, DisciplineInfo.ERROR_DISCIPLINE_NAME_NULL);
				return INPUT;
			}
		}
		
		if(discipline.getCode()==null||discipline.getCode().trim().isEmpty()) {//学科代码为空，返回错误提示
			this.addFieldError(GlobalInfo.ERROR_INFO, DisciplineInfo.ERROR_DISCIPLINE_CODE_NULL);
			return INPUT;
		} else {
			// 如果学科代码发生变化，校验学科名称是否存在
			if(!oldDiscipline.getCode().equals(discipline.getCode())&&disciplineService.checkDisciplineCode(discipline.getCode())) {
				this.addFieldError(GlobalInfo.ERROR_INFO, DisciplineInfo.ERROR_DISCIPLINE_CODE_EXIST);
				return INPUT;
			}
		}
		entityId = disciplineService.modifyDiscipline(oldDiscipline, discipline);//修改学科
		session.remove(TMP_ENTITY_ID);
		jsonMap.clear();
		jsonMap.put("tag", 2);
		return SUCCESS;
	}
	
	@Override
	public String toView() {
		// TODO Auto-generated method stub
		return SUCCESS;
	}

	@Override
	public String view() {
		if(entityId!=null) {
			discipline = (SystemOption) disciplineService.viewDiscipline(entityId);// 获取学科
		} else {
			jsonMap.put(GlobalInfo.ERROR_INFO, DisciplineInfo.ERROR_DISCIPLINE_ID_NULL);
			return INPUT;
		}
		if (discipline == null) {// 学科不存在，返回错误提示
			jsonMap.put(GlobalInfo.ERROR_INFO, DisciplineInfo.ERROR_DISCIPLINE_NULL);
			return INPUT;
		} else {// 学科存在，存入jsonMap
			jsonMap.put("discipline", discipline);
			return SUCCESS;
		}
	}
	/**
	 * 获取学科分组列表，返回根学科和一级学科id 和 name
	 * @return
	 */
	public String getDisciplineGroup() {
		List disGroup = disciplineService.getDisciplineGroup();
		jsonMap.put("disciplineGroup", disGroup);
		return SUCCESS;
	}

	@Override
	public Object[] simpleSearchCondition() {
		keyword = (keyword == null) ? "" : keyword.toLowerCase().trim();// 预处理关键字
		StringBuffer hql = new StringBuffer();
		Map map = new HashMap();
		hql.append(HQL);
		if (keyword != null && !keyword.isEmpty()) {
			hql.append(" and ");
			if (searchType == 1) {
				hql.append("LOWER(dis.name) like :keyword");
				map.put("keyword", "%" + keyword + "%");
			} else if (searchType == 2) {
				hql.append("LOWER(dis.code) like :keyword");
				map.put("keyword", "%" + keyword + "%");
			} else {
				hql.append("(LOWER(dis.name) like :keyword or LOWER(dis.code) like :keyword)");
				map.put("keyword", "%" + keyword + "%");
			}
		}
		// 调用初级检索功能
		return new Object[]{
			hql.toString(),
			map,
			0,
			null
		};
	}


	@Override
	public Object[] advSearchCondition() {
		StringBuffer hql = new StringBuffer();
		Map map = new HashMap();
		hql.append(HQL);
		
		// 拼接检索条件，当检索关键字非空时，才添加检索条件，忽略大小写
		if (keyword1 != null && !keyword1.isEmpty()) {// 按学科名称检索
			keyword1 = keyword1.toLowerCase();
			hql.append(" and LOWER(dis.name) like :keyword1 ");
			map.put("keyword1", "%" + keyword1 + "%");
		}
		if (keyword2 != null && !keyword2.isEmpty()) {// 按学科代码检索
			keyword2 = keyword2.toLowerCase();
			hql.append(" and LOWER(dis.code) like :keyword2 ");
			map.put("keyword2", "%" + keyword2 + "%");
		}
		return new Object[] {
			hql.toString(),
			map,
			0,
			null
		};
	}

	@Override
	public String pageName() {
		// TODO Auto-generated method stub
		return this.PAGE_NAME;
	}

	@Override
	public String pageBufferId() {
		// TODO Auto-generated method stub
		return this.PAGE_BUFFER_ID;
	}

	@Override
	public String[] sortColumn() {
		// TODO Auto-generated method stub
		return this.COLUMN;
	}

	@Override
	public String dateFormat() {
		// TODO Auto-generated method stub
		return this.DATE_FORMAT;
	}

	public String getKeyword1() {
		return keyword1;
	}

	public void setKeyword1(String keyword1) {
		this.keyword1 = keyword1;
	}

	public String getKeyword2() {
		return keyword2;
	}

	public void setKeyword2(String keyword2) {
		this.keyword2 = keyword2;
	}

	public SystemOption getDiscipline() {
		return discipline;
	}

	public void setDiscipline(SystemOption discipline) {
		this.discipline = discipline;
	}

}
