package csdc.action.assignExpert;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import csdc.action.BaseAction;
import csdc.model.Groups;
import csdc.service.IAssignExpertService;
import csdc.tool.info.AssignExpertInfo;
import csdc.tool.info.GlobalInfo;

@Component
@Scope(value="prototype")
@SuppressWarnings("unchecked")
public class ChiefReviewProductAction extends BaseAction {
	
	private static final long serialVersionUID = -3588557972413185512L;
	private static final String DATE_FORMAT = "yyyy-MM-dd";// 列表时间格式
	private static final String PAGE_NAME = "chiefReviewProductListPage";
	private static final String PAGE_BUFFER_ID = "pr.id";// 上下条查看时用于查找缓存的字段名称
	private static final String HQL = 
			"select pr.id, pr.name, pr.type, pr.researchType, pr.authorName, pr.publishName, pr.publishLevel, pr.averageScore"
			+" from Product pr where pr.status=6 and pr.chiefReviewer is null";
	private static final String[] SORT_COLUMNS = new String[] {
		"pr.name",
		"pr.type",
		"pr.researchType",
		"pr.authorName",
		"pr.publishName",
		"pr.publishLevel",
		"pr.averageScore"
	};
	@Autowired
	private IAssignExpertService assignExpertService;
	private String groupId;//分组Id
	private String expertId;//专家Id
	private List<String> productId;//主审成果Id
	
	@Override
	public String list() {
		update = 1;
		super.toList();
		super.list();
		return SUCCESS;
	};
	
	public String getChiefReviewProducts() {
		if(expertId==null) {
			this.addFieldError(GlobalInfo.ERROR_INFO, AssignExpertInfo.ERROR_EXPERT_ID_NULL);
			jsonMap.put(GlobalInfo.ERROR_INFO, AssignExpertInfo.ERROR_EXPERT_ID_NULL);
		}
		List list = assignExpertService.getChiefReviewProducts(expertId);
		jsonMap.put("chiefProducts", list);
		return SUCCESS;
	}
	
	public String assignChiefProducts() {
		if(expertId==null) {
			this.addFieldError(GlobalInfo.ERROR_INFO, AssignExpertInfo.ERROR_EXPERT_ID_NULL);
			jsonMap.put(GlobalInfo.ERROR_INFO, AssignExpertInfo.ERROR_EXPERT_ID_NULL);
		}
		if(productId==null||productId.isEmpty()) {
			this.addFieldError(GlobalInfo.ERROR_INFO, AssignExpertInfo.ERROR_PRODUCT_ID_NULL);
			jsonMap.put(GlobalInfo.ERROR_INFO, AssignExpertInfo.ERROR_PRODUCT_ID_NULL);
		}
		int i = assignExpertService.setChiefReviewProducts(expertId, productId);
		if(i==0) {
			jsonMap.put(GlobalInfo.ERROR_INFO, "主审成果分配失败");
			return INPUT;
		}else {
			jsonMap.put("tag", "1");//表示分配专家成功
		}
		return SUCCESS;
	}
	
	@Override
	public Object[] simpleSearchCondition() {
		keyword = (keyword == null) ? "" : keyword.toLowerCase().trim();// 预处理关键字
		StringBuffer hql = new StringBuffer();
		Map map = new HashMap();
		hql.append(HQL);
		String currentYear = new SimpleDateFormat("yyyy").format(new Date());
		hql.append(" and pr.applyYear = :applyYear");
		map.put("applyYear", currentYear);
		Groups group = (Groups)dao.query(Groups.class, groupId);
		hql.append(" and pr.groups = :groups");
		map.put("groups", group);
		if (keyword != null && !keyword.isEmpty()) {
			hql.append(" and ");
			if (1<=searchType&&searchType<=6) {
				hql.append(SORT_COLUMNS[searchType-1]);
				hql.append(" like :keyword");
				map.put("keyword", "%" + keyword + "%");
			} else { 
				String appenSql = "(pr.name like :keyword or pr.type like :keyword or pr.researchType like :keyword or " +
						"pr.authorName like :keyword or pr.publishName like :keyword or pr.publishLevel like :keyword)";
				hql.append(appenSql);
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
		return this.PAGE_NAME;
	}

	@Override
	public String pageBufferId() {
		return this.PAGE_BUFFER_ID;
	}

	@Override
	public String[] sortColumn() {
		return this.SORT_COLUMNS;
	}

	@Override
	public String dateFormat() {
		return this.DATE_FORMAT;
	}
	
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getExpertId() {
		return expertId;
	}
	public void setExpertId(String expertId) {
		this.expertId = expertId;
	}
	
	public List<String> getProductId() {
		return productId;
	}

	public void setProductId(List<String> productId) {
		this.productId = productId;
	}

	//*******************以下方法没有用到，不用实现**********************//
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
	public Object[] advSearchCondition() {
		// TODO Auto-generated method stub
		return null;
	}

}
