package csdc.action.assignExpert;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import csdc.action.BaseAction;
import csdc.model.Groups;
import csdc.tool.StringTool;
import csdc.tool.info.GlobalInfo;
/**
 * 评审分组成果清单列表
 * @author Yaoyota
 *
 */
@Component
@Scope(value="prototype")
@SuppressWarnings("unchecked")
public class GroupProductsAction extends BaseAction {

	private static final long serialVersionUID = -4491544137045640728L;
	private static final String DATE_FORMAT = "yyyy-MM-dd";// 列表时间格式
	private static final String PAGE_NAME_1 = "firstReviewGroupProductsListPage";
	private static final String PAGE_NAME_2 = "secondReviewGroupProductsListPage";
	private static final String PAGE_BUFFER_ID = "pr.id";// 上下条查看时用于查找缓存的字段名称
	//初评成果清单
	private static final String HQL1 = 
			"select pr.id, pr.number, pr.name, pr.authorName, pr.agencyName, pr.type, pr.publishName, pr.publishDate"
			+" from Product pr where pr.status>=4";
	//复评成果清单
	private static final String HQL2 = 
			"select pr.id, pr.number, pr.name, pr.authorName, pr.agencyName, pr.type, pr.publishName, pr.publishDate, pr.averageScore"
					+" from Product pr where pr.status>=6";
	
	private static final String[] SORT_COLUMNS = new String[] {
		"pr.number",
		"pr.name",
		"pr.authorName",
		"pr.agencyName",
		"pr.type",
		"pr.publishName",
		"pr.publishDate",
		"pr.averageScore"
	};
	private static final int SEARCH_TYPE_MAX = 6;
	private String groupId;//分组id
	private int reviewType;//评审类型[0:初评 1:复评]
	
	@Override
	public String list() {
		update = 1;
		super.toList();
		super.list();
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
		String currentYear = new SimpleDateFormat("yyyy").format(new Date());
		hql.append(" and pr.applyYear = :currentYear");
		map.put("currentYear", currentYear);
		Groups group = (Groups)dao.query(Groups.class, groupId);
		hql.append(" and pr.groups = :groups");
		map.put("groups", group);
		if (keyword != null && !keyword.isEmpty()) {
			hql.append(" and ");
			if (1<=searchType&&searchType<=SEARCH_TYPE_MAX) {
				hql.append(SORT_COLUMNS[searchType-1]);
				hql.append(" like :keyword");
				map.put("keyword", "%" + keyword + "%");
			} else { 
				hql.append("(");
				String column[] = new String[SEARCH_TYPE_MAX];
				for(int i=0; i<SEARCH_TYPE_MAX; i++) {
					column[i] = SORT_COLUMNS[i]+" like :keyword";
				}
				hql.append(StringTool.joinString(column, " or ")).append(")");
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
		return this.PAGE_BUFFER_ID;
	}

	@Override
	public String[] sortColumn() {
		// TODO Auto-generated method stub
		return this.SORT_COLUMNS;
	}

	@Override
	public String dateFormat() {
		// TODO Auto-generated method stub
		return this.DATE_FORMAT;
	}
	
	
    public String getGroupId() {
		return groupId;
	}


	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public int getReviewType() {
		return reviewType;
	}


	public void setReviewType(int reviewType) {
		this.reviewType = reviewType;
	}


	//**********************以下方法没有用到，故不用实现************************//
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
