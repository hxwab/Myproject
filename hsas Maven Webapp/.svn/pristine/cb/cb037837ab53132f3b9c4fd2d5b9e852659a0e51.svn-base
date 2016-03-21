package csdc.action.pop.select;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import csdc.action.BaseAction;


@Component
@Scope(value = "prototype")
public class GroupAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4387056670562128565L;

	
	
	private static final String HQL = "select g.id,g.name from Group g where 1=1";
	private static final String[] COLUMN = {
			"g.id",
			"g.name",
		};// 用于拼接的排序列
	private static final String PAGE_NAME = "selectGroupPage";// 列表页面名称
	private static final String DATE_FORMAT = "yyyy-MM-dd";// 列表时间格式
	private static final String PAGE_BUFFER_ID = "g.id";// 上下条查看时用于查找缓存的字段
	
	
	

	@Override
	public Object[] simpleSearchCondition() {
		
		keyword = (keyword == null) ? "" : keyword.toLowerCase().trim();// 预处理关键字
		StringBuffer hql = new StringBuffer();
		Map map = new HashMap();
		hql.append(HQL);
		if (keyword != null && !keyword.isEmpty()) {
			hql.append(" and ");
			if (searchType == 1) {
				hql.append("LOWER(g.name) like :keyword");
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
		return PAGE_NAME;
	}

	@Override
	public String pageBufferId() {
		return PAGE_BUFFER_ID;
	}

	@Override
	public String[] sortColumn() {
		return COLUMN;
	}

	@Override
	public String dateFormat() {
		// TODO Auto-generated method stub
		return null;
	}

}
