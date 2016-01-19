package csdc.action.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import csdc.action.BaseAction;
import csdc.model.Discipline;
import csdc.model.Groups;
import csdc.service.IGroupService;
import csdc.tool.info.GlobalInfo;
import csdc.tool.info.GroupInfo;

@Component
@Scope(value="prototype")

public class GroupAction extends BaseAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8507240264861492582L;
	private static final String HQL = "select gr.id, gr.name, gr.description from Groups gr where 1=1";
	private static final String[] COLUMN = {
		"gr.name",
		"gr.description",
		"gr.index"
	};// 用于拼接的排序列
	private static final String PAGE_NAME = "groupPage";// 列表页面名称
	private static final String DATE_FORMAT = "yyyy-MM-dd";// 列表时间格式
	private static final String PAGE_BUFFER_ID = "gr.id";// 上下条查看时用于查找缓存的字段
	private static final String TMP_ENTITY_ID = "groupId";// 用于session缓存实体的ID名称
	
	private Groups group;//分组对象
	private String srcId;//合并来源分组id
	private String dstId;//合并目的分组id
//	private Set<String> disciplineIds;//学科Id集合
	@Autowired
	private IGroupService groupService;
	@Override
	public String toAdd() {
		// TODO Auto-generated method stub
		return SUCCESS;
	}

	@Override
	@Transactional
	public String add() {
		if (group.getName()==null||group.getName().trim().isEmpty()) {//分组名称为空，返回错误提示
			this.addFieldError(GlobalInfo.ERROR_INFO, GroupInfo.ERROR_GROUP_NAME_NULL);
			return INPUT;
		} else {
			if (groupService.checkGroup(group.getName())) {// 分组存在，返回错误提示
				this.addFieldError(GlobalInfo.ERROR_INFO, GroupInfo.ERROR_GROUP_EXIST);
				return INPUT;
			}
		}
		entityId = groupService.addGroup(group);
		jsonMap.put("tag", 1);
		return SUCCESS;
	}
	
	@Transactional
	public String merge() {
		groupService.merge(srcId, dstId);
		jsonMap.put("tag", 1);
		return SUCCESS;
	}
	@Override
	@Transactional
	public String delete() {
		// TODO Auto-generated method stub
		int flag;
		flag = groupService.deleGroup(entityIds);
		if(flag==1) //是否删除成功
			return SUCCESS;
		else
			return ERROR;
	}

	@Override
	public String toModify() {
		group = (Groups) dao.query(Groups.class, entityId);// 获取学科
		if (group == null) {// 权限不存在，返回错误提示
			this.addFieldError(GlobalInfo.ERROR_INFO, GroupInfo.ERROR_GROUP_NULL);
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
		Groups oldGroup = (Groups)dao.query(Groups.class, entityId);//获取原来学科
		if (group.getName()==null||group.getName().trim().isEmpty()) {//学科名称为空，返回错误提示
			this.addFieldError(GlobalInfo.ERROR_INFO, GroupInfo.ERROR_GROUP_NAME_NULL);
			return INPUT;
		} else {
			// 如果分组名称发生变化，校验分组是否存在
			if(!oldGroup.getName().equals(group.getName())&&groupService.checkGroup(group.getName())) {
				this.addFieldError(GlobalInfo.ERROR_INFO, GroupInfo.ERROR_GROUP_EXIST);
				return INPUT;
			}
		}
		entityId = groupService.modifyGroup(oldGroup, group);//修改学科
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
//			group = (Groups) groupService.viewGroup(entityId);// 获取学科
		} else {
			jsonMap.put(GlobalInfo.ERROR_INFO, GroupInfo.ERROR_GROUP_ID_NULL);
			return INPUT;
		}
		if (group == null) {// 学科不存在，返回错误提示
			jsonMap.put(GlobalInfo.ERROR_INFO, GroupInfo.ERROR_GROUP_NULL);
			return INPUT;
		} else {// 学科存在，存入jsonMap
			jsonMap.put("group", group);
			return SUCCESS;
		}
	}
	/**
	 * 获取所有分组
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String getAllGroup() {
		List<Groups> allGroup = groupService.getAllGroup();
		jsonMap.put("allGroup", allGroup);
		return SUCCESS;
	}
	
	/**
	 * 获取所有学科
	 */
	public String getAllDiscipline() {
		List<Discipline> allDiscipline = groupService.getAllDiscipline();
		jsonMap.put("allDisclipline", allDiscipline);
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
			if(searchType==1) {
				hql.append("LOWER(gr.name) like :keyword");
				map.put("keyword", "%" + keyword + "%");
			}
			if(searchType==2) {
				hql.append("LOWER(gr.description) like :keyword");
				map.put("keyword", "%" + keyword + "%");
			}
		}
		// 调用初级检索功能
		return new Object[]{
			hql.toString(),
			map,
			2,
			GlobalInfo.ERROR_EXCEPTION_INFO
		};
	}


	@Override
	public Object[] advSearchCondition() {
		return simpleSearchCondition();
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

	public Groups getGroup() {
		return group;
	}

	public void setGroup(Groups group) {
		this.group = group;
	}

	public String getSrcId() {
		return srcId;
	}

	public void setSrcId(String srcId) {
		this.srcId = srcId;
	}

	public String getDstId() {
		return dstId;
	}

	public void setDstId(String dstId) {
		this.dstId = dstId;
	}

	
}