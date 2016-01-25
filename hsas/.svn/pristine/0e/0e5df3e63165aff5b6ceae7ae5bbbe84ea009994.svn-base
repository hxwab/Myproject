package csdc.action.pop.select;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import csdc.action.BaseAction;
import csdc.service.IAgencyService;


public class AgencyAction extends BaseAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4288686161982485183L;
	
	private static final String HQL = "select a.id,a.name from Agency a where 1=1";
	private static final String[] COLUMN = {
			"a.id",
			"a.name",
		};// 用于拼接的排序列
	private static final String PAGE_NAME = "selectAgencyPage";// 列表页面名称
	private static final String DATE_FORMAT = "yyyy-MM-dd";// 列表时间格式
	private static final String PAGE_BUFFER_ID = "a.id";// 上下条查看时用于查找缓存的字段
	
	@Autowired
	private IAgencyService agencyService;
	
	private  String agencyName;
	private boolean isExist;

	@Override
	public Object[] simpleSearchCondition() {
		
		keyword = (keyword == null) ? "" : keyword.toLowerCase().trim();// 预处理关键字
		StringBuffer hql = new StringBuffer();
		Map map = new HashMap();
		hql.append(HQL);
		if (keyword != null && !keyword.isEmpty()) {
			hql.append(" and ");
			if (searchType == 1) {
				hql.append("LOWER(a.name) like :keyword");
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
	
	public String checkName(){
		
		isExist = agencyService.checkAgencyByName(agencyName);
		jsonMap.put("isExist", isExist);
		return SUCCESS;
	}
	
	public void validateCheckName(){
		if(agencyName==null || "".equals(agencyName)){
			this.addFieldError("输入错误","请输入机构名称");
		}
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

	@Override
	public String pageName() {
		return this.PAGE_NAME;
	}

	@Override
	public String pageBufferId() {
	//	return this.PAGE_BUFFER_ID;
		return null;
	}

	@Override
	public String[] sortColumn() {
		return this.COLUMN;
	}

	@Override
	public String dateFormat() {
		return this.DATE_FORMAT;
	}

	public String getAgencyName() {
		return agencyName;
	}

	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}

	public boolean isExist() {
		return isExist;
	}

	public void setExist(boolean isExist) {
		this.isExist = isExist;
	}

	
	
}
