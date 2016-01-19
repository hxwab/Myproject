package csdc.action.assignExpert;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import csdc.action.BaseAction;
import csdc.model.Groups;
import csdc.service.IAssignExpertService;
import csdc.tool.info.GlobalInfo;
/**
 * 评审分组列表
 * @author Yaoyota
 *
 */
@Component
@Scope(value="prototype")
@SuppressWarnings("unchecked")
public class ReviewGroupAction extends BaseAction{
	
	private static final long serialVersionUID = -8145463765230075372L;
	private static final String TMP_ENTITY_ID = "groupId";// 缓存与session中，备用的账号ID变量名称
	private static final String DATE_FORMAT = "yyyy-MM-dd";// 列表时间格式
	private static final String PAGE_NAME = "reviewGroupListPage";
	private static final String PAGE_BUFFER_ID = "gr.id";// 上下条查看时用于查找缓存的字段名称
	//分组id 分组名称  分组已分专家数  分组下学科
	private static final String HQL = "select gr.id, gr.name from Groups gr where 1=1";// 上下条查看时用于查找缓存的字段名称HQL
	private static final String[] SORT_COLUMNS = new String[] {
		"gr.name",
		"gr.index"
	};
	@Autowired
	private IAssignExpertService assignExpertService;
	private int reviewType;//list.action参数 评审类型[0:初评 1:复评]
	private int viewType;//view.action参数 评审类型[0:初评 1:复评]
	
	public String toFirstReviewList() {
		return SUCCESS;
	}
	
	public String toSecondReviewList() {
		return SUCCESS;
	}
	@Override
	@Transactional
	public String list() {
		super.list();
		laData = (List) jsonMap.get("laData");
		jsonMap.remove("laData");
		laData = assignExpertService.laDataDealWith(laData, reviewType);
		jsonMap.put("laData", laData);
		return SUCCESS;
	}
	
	@Override
	public String toView() {
		return SUCCESS;
	}

	@Override
	public String view() {
		Groups group = dao.query(Groups.class, entityId);
		String groupName = group.getName();
		String allDisciplines = assignExpertService.getDisciplinesByGroupId(entityId);
		int assignedExpertNum = assignExpertService.getAssignedExpertNumByGroupId(entityId, viewType);
		int productNum = assignExpertService.getProductNumByGroupId(entityId, viewType);
		jsonMap.put("entityId", entityId);
		jsonMap.put("groupName", groupName);
		jsonMap.put("allDisciplines", allDisciplines);
		jsonMap.put("assignedExpertNum", new Integer(assignedExpertNum).toString());
		jsonMap.put("productNum", new Integer(productNum).toString());
		return SUCCESS;
	}
	@Override
	public Object[] simpleSearchCondition() {
		// 调用初级检索功能
		return new Object[]{
			HQL,
			null,
			1,
			GlobalInfo.ERROR_EXCEPTION_INFO
		};
	}
	
	@Override
	public String pageName() {
		// TODO Auto-generated method stub
		return PAGE_NAME;
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
	
	public int getReviewType() {
		return reviewType;
	}

	public void setReviewType(int reviewType) {
		this.reviewType = reviewType;
	}

	public int getViewType() {
		return viewType;
	}

	public void setViewType(int viewType) {
		this.viewType = viewType;
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

}
