package csdc.action.assignExpert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import csdc.action.BaseAction;
import csdc.model.Groups;
import csdc.service.IAssignExpertService;
import csdc.tool.info.AssignExpertInfo;
import csdc.tool.info.GlobalInfo;
/**
 * 专家分配
 * @author Yaoyota
 *
 */
@Component
@Scope(value="prototype")
@SuppressWarnings("unchecked")
public class AssignExpertAction extends BaseAction {
	
	private static final long serialVersionUID = -4461250361730504769L;
	private static final String TMP_ENTITY_ID = "expertId";// 缓存与session中，备用的账号ID变量名称
	private static final String DATE_FORMAT = "yyyy-MM-dd";// 列表时间格式
	private static final String PAGE_NAME_1 = "firstReviewExpertListPage";
	private static final String PAGE_NAME_2 = "secondReviewExpertListPage";
	private static final String PAGE_BUFFER_ID = "ex.id";// 上下条查看时用于查找缓存的字段名称
	//初评待选专家库HQL 专家名字 学科门类 研究方向 专家职称
	private static final String HQL1 = 
			 "select ex.id, ex.person.name, ex.person.agencyName, ex.specialityTitle, ex.discipline.name" 
			+" from Expert ex"
			+" where ex.isRecommend=1 and ex.isReviewer!=1";
	//复评待选专家库HQL
	private static final String HQL2 = 
			"select ex.id, ex.person.name, ex.person.agencyName, ex.specialityTitle, ex.discipline.name" 
			+" from Expert ex"
			+" where ex.reviewerType!=0 ex.isRecommend=1 and ex.isReviewer!=1";
	private static final String[] SORT_COLUMNS = new String[] {
		"ex.person.name",
		"ex.person.agencyName",
		"ex.specialityTitle",
		"ex.discipline.name" 
	};
	@Autowired
	private IAssignExpertService assignExpertService;
	private List<String> expertId; //已分配专家id
	private String groupId; //分组id
	private String leaderId; //组长id
	private int reviewType; //评审类型[0:初评 1:复评]
	
	@Override
	public String list() {
		update = 1;
		super.toList();
		super.list();
		laData = assignExpertService.expertDataDealWith(laData);
		jsonMap.remove("laData");
		jsonMap.put("laData", laData);
		return SUCCESS;
	}
	
	public String toAssignExpertList() {
		return SUCCESS;
	}
	
	public String getAssignedExpert() {
		if(groupId==null) {
			this.addFieldError(GlobalInfo.ERROR_INFO, AssignExpertInfo.ERROR_GROUP_ID_NULL);
			jsonMap.put(GlobalInfo.ERROR_INFO, AssignExpertInfo.ERROR_GROUP_ID_NULL);
			return INPUT;
		}
		List list = assignExpertService.getAssignedExpert(groupId, reviewType);
		jsonMap.put("assignedExpert", list);
		return SUCCESS;
	}
	/**
	 * 添加专家
	 * @return
	 */
	@Transactional
	public String addExpert() {
		if(expertId==null||expertId.isEmpty()) {
			this.addFieldError(GlobalInfo.ERROR_INFO, AssignExpertInfo.ERROR_EXPERT_LIST_NULL);
		}
		int i = assignExpertService.addExpert(expertId, reviewType);
		if(i==0) {
			jsonMap.put(GlobalInfo.ERROR_INFO, "专家添加失败");
			return INPUT;
		}else {
			jsonMap.put("tag", "1");//表示添加专家成功
		}
		return SUCCESS;
	}
	/**
	 * 删除专家
	 * @return
	 */
	@Transactional
	public String deleteExpert() {
		if(expertId==null||expertId.isEmpty()) {
			this.addFieldError(GlobalInfo.ERROR_INFO, AssignExpertInfo.ERROR_EXPERT_LIST_NULL);
		}
		int i = assignExpertService.deleteExpert(expertId, reviewType);
		if(i==0) {
			jsonMap.put(GlobalInfo.ERROR_INFO, "专家删除失败");
			return INPUT;
		}else {
			jsonMap.put("tag", "1");//表示删除专家成功
		}
		return SUCCESS;
	}
	/**
	 * 复评分配组长
	 * @return
	 */
	public String assignLeader() {
		if(groupId==null) {
			this.addFieldError(GlobalInfo.ERROR_INFO, AssignExpertInfo.ERROR_GROUP_ID_NULL);
		}
		if(leaderId==null||leaderId.trim().isEmpty()) {
			this.addFieldError(GlobalInfo.ERROR_INFO, AssignExpertInfo.ERROR_EXPERT_LIST_NULL);
		}
		int i = assignExpertService.updateLeader(leaderId);
		if(i==0) {
			jsonMap.put(GlobalInfo.ERROR_INFO, "分配组长失败");
			return INPUT;
		}else {
			jsonMap.put("tag", "1");//表示分配组长成功
		}
		return SUCCESS;
	}
	@Override
	public Object[] simpleSearchCondition() {
		keyword = (keyword == null) ? "" : keyword.toLowerCase().trim();// 预处理关键字
		StringBuffer hql = new StringBuffer();
		Map map = new HashMap();
		if(reviewType==0) {
			hql.append(HQL1);
		} else if(reviewType==1) {
			hql.append(HQL2);
		}
		hql.append(" and ex.groups = :groups");
		Groups groups = dao.query(Groups.class, groupId);
		map.put("groups", groups);
		if (keyword != null && !keyword.isEmpty()) {
			hql.append(" and ");
			if(searchType==1) {
				hql.append("LOWER(ex.person.name) like :keyword");
				map.put("keyword", "%" + keyword + "%");
			}
			if(searchType==2) {
				hql.append("LOWER(ex.discipline.name) like :keyword");
				map.put("keyword", "%" + keyword + "%");
			}
			if(searchType==3) {
				hql.append("LOWER(ex.researchField) like :keyword");
				map.put("keyword", "%" + keyword + "%");
			}
			if(searchType==4) {
				hql.append("LOWER(ex.specialityTitle) like :keyword");
				map.put("keyword", "%" + keyword + "%");
			}
		}
		// 调用初级检索功能
		return new Object[]{
			hql.toString(),
			map,
			0,
			GlobalInfo.ERROR_EXCEPTION_INFO
		};
	}
	
	@Override
	public String pageName() {
		return reviewType==0? this.PAGE_NAME_1:this.PAGE_NAME_2;
	}

	@Override
	public String pageBufferId() {
		// TODO Auto-generated method stub
		return PAGE_BUFFER_ID;
	}

	@Override
	public String[] sortColumn() {
		// TODO Auto-generated method stub
		return SORT_COLUMNS;
	}

	@Override
	public String dateFormat() {
		// TODO Auto-generated method stub
		return DATE_FORMAT;
	}
	
	
	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public List<String> getExpertId() {
		return expertId;
	}

	public void setExpertId(List<String> expertId) {
		this.expertId = expertId;
	}
	

	public String getLeaderId() {
		return leaderId;
	}

	public void setLeaderId(String leaderId) {
		this.leaderId = leaderId;
	}
    
	public int getReviewType() {
		return reviewType;
	}

	public void setReviewType(int reviewType) {
		this.reviewType = reviewType;
	}
	
	public String toSecondView(){
		return SUCCESS;
	}
	
	public String toFirstView(){
		return SUCCESS;
	}
	
	//*****************以下方法没有用到，没有实现*******************//
	@Override
	public String toAdd() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String add() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delete() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toModify() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String modify() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Object[] advSearchCondition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String view() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String toView() {
		// TODO Auto-generated method stub
		return null;
	}

	public String toSecondReviewList(){
		return SUCCESS;
	}
	
	public String toFirstReviewList(){
		return SUCCESS;
	}
}
