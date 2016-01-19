package csdc.action.system;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import csdc.action.BaseAction;
import csdc.model.Agency;
import csdc.model.Person;
import csdc.service.IAgencyService;
import csdc.tool.info.AgencyInfo;
import csdc.tool.info.GlobalInfo;

@Component
@Scope(value="prototype")

public class AgencyAction extends BaseAction{
	private static final long serialVersionUID = -2633795673223514884L;
	private static final String TMP_ENTITY_ID = "agencyId";// 缓存与session中，备用的账号ID变量名称
	private static final String DATE_FORMAT = "yyyy-MM-dd";// 列表时间格式
	private static final String PAGE_NAME = "unitListPage";
	private static final String PAGE_BUFFER_ID = "ag.id";// 上下条查看时用于查找缓存的字段名称
	private static final String HQL = "select ag.id, ag.name, ag.code, ag.type, ag.abbr, ag.directorName, ag.mobilePhone, ag.officePhone from Agency ag where 1=1";// 上下条查看时用于查找缓存的字段名称HQL
	private static final String[] SORT_COLUMNS = new String[] {
		"ag.name",
		"ag.type",
		"ag.directorName",
		"ag.mobilePhone",
		"ag.officePhone"
		
	};
	private Agency agency;//机构对象
	private String keyword1, keyword2, keyword3, keyword4, keyword5, keyword6, keyword7; //高级检索关键词 [机构名称 机构代码 机构类型 名称缩写 机构负责人姓名 机构电话 机构传真]
	@Autowired
	private IAgencyService agencyService;
	@Override
	public String toAdd() {
		// TODO Auto-generated method stub
		return SUCCESS;
	}

	@Override
	@Transactional
	public String add() {
		//添加机构信息
		String agencyId = agencyService.addAgency(agency);
		if(null==agencyId) {
			System.out.println("添加机构失败......");
			jsonMap.put(GlobalInfo.ERROR_INFO, "机构添加失败");
			return ERROR;
		}
		jsonMap.put("entityId", agencyId);
		jsonMap.put("tag", "1");
		return SUCCESS;
	}
	
	public void validateAdd() {
		if(agency==null) {
			this.addFieldError(GlobalInfo.ERROR_INFO, GlobalInfo.ERROR_EXCEPTION_INFO);
		}
		if(agency.getName()==null||agency.getName().trim().isEmpty()) {
			this.addFieldError(GlobalInfo.ERROR_INFO, AgencyInfo.ERROR_AGENCY_NAME_NULL);
		}
		if(agency.getType()==null) {
			this.addFieldError(GlobalInfo.ERROR_INFO, AgencyInfo.ERROR_AGENCY_TYPE_NULL);
		}
		if(agency.getDirectorName()==null||agency.getDirectorName().trim().isEmpty()) {
			this.addFieldError(GlobalInfo.ERROR_INFO, AgencyInfo.ERROR_AGENCY_DIRECTORNAME_NULL);
		}
		if(agency.getOfficePhone()==null||agency.getOfficePhone().trim().isEmpty()) {
			this.addFieldError(GlobalInfo.ERROR_INFO, AgencyInfo.ERROR_AGENCY_OFFICEPHONE_NULL);
		}
		if(agencyService.checkAgency(agency)) {
			this.addFieldError(GlobalInfo.ERROR_INFO, AgencyInfo.ERROR_AGENCY_EXIST);
		}
	}

	@Override
	public String delete() {
		int flag;
		flag = agencyService.deleteAgency(entityIds);
		if(flag==0)//是否删除成功
			jsonMap.put(GlobalInfo.ERROR_INFO, "删除失败");
		return SUCCESS;
	}

	@Override
	public String toModify() {
		agency = (Agency) dao.query(Agency.class, entityId);// 获取机构
		if (agency == null) {// 奖励不存在，返回错误提示
			this.addFieldError(GlobalInfo.ERROR_INFO, AgencyInfo.ERROR_AGENCY_NULL);
			return INPUT;
		} else {// 奖励存在，备用奖励ID
			session.put(TMP_ENTITY_ID, entityId);
			return SUCCESS;
		}
	}

	@Override
	@Transactional
	public String modify() {
		entityId = (String)session.get(TMP_ENTITY_ID);//获取备用机构Id
		Agency oldAgency = (Agency)dao.query(Agency.class, entityId);
		entityId = agencyService.modifyAgency(oldAgency, agency);//修改机构信息
		session.remove(TMP_ENTITY_ID);
		jsonMap.clear();
		jsonMap.put("entityId", entityId);
		jsonMap.put("tag", 1);
		return SUCCESS;
	}
	
	public void validateModify() {
		if(agency==null) {
			this.addFieldError(GlobalInfo.ERROR_INFO, GlobalInfo.ERROR_EXCEPTION_INFO);
		}
		if(agency.getName()==null||agency.getName().trim().isEmpty()) {
			this.addFieldError(GlobalInfo.ERROR_INFO, AgencyInfo.ERROR_AGENCY_NAME_NULL);
		}
		if(agency.getType()==null) {
			this.addFieldError(GlobalInfo.ERROR_INFO, AgencyInfo.ERROR_AGENCY_TYPE_NULL);
		}
		if(agency.getDirectorName()==null||agency.getDirectorName().trim().isEmpty()) {
			this.addFieldError(GlobalInfo.ERROR_INFO, AgencyInfo.ERROR_AGENCY_DIRECTORNAME_NULL);
		}
		if(agency.getOfficePhone()==null||agency.getOfficePhone().trim().isEmpty()) {
			this.addFieldError(GlobalInfo.ERROR_INFO, AgencyInfo.ERROR_AGENCY_OFFICEPHONE_NULL);
		}
		Agency oldAgency = dao.query(Agency.class, (String)session.get(TMP_ENTITY_ID));
		if(!oldAgency.getName().equals(agency.getName())) {//如果机构名发生变动，则判断变化后的机构是否已存在
			if(agencyService.checkAgency(agency)) {
				this.addFieldError(GlobalInfo.ERROR_INFO, AgencyInfo.ERROR_AGENCY_EXIST);
			}
		}
	}

	@Override
	public String view() {
		if(entityId!=null) {
			agency = (Agency)agencyService.viewAgency(entityId);//获取机构
		} else {
			jsonMap.put(GlobalInfo.ERROR_INFO, AgencyInfo.ERROR_AGENCY_ID_NULL);
			return INPUT;
		}
		if(agency!=null) {
			jsonMap.put("agency", agency);
			return SUCCESS;
		} else {
			jsonMap.put(GlobalInfo.ERROR_INFO, AgencyInfo.ERROR_AGENCY_NULL);
			return INPUT;
		}
	}

	@Override
	public String toView() {
		// TODO Auto-generated method stub
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
			if (searchType == 1) { //初级 检索条件为机构名称
				hql.append("LOWER(ag.name) like :keyword");
				map.put("keyword", "%" + keyword + "%");
			} else if (searchType == 2) { //初级检索条件为联系人
				hql.append("LOWER(ag.directorName) like :keyword");
				map.put("keyword", "%" + keyword + "%");
			} else if(searchType == 3) { //初级检索条件为手机
				hql.append("ag.mobilePhone like :keyword");
				map.put("keyword", "%" + keyword + "%");
			} else if(searchType == 4) { //初级检索条件为办公电话
				hql.append("ag.officePhone like :keyword");
				map.put("keyword", "%" + keyword + "%");
			} else {
				hql.append("(LOWER(ag.name) like :keyword or LOWER(ag.directorName) like :keyword " +
						"or ag.mobilePhone like :keyword or ag.officePhone like :keyword)");
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
	public Object[] advSearchCondition() {
		StringBuffer hql = new StringBuffer();
		Map map = new HashMap();
		hql.append(HQL);
		// 拼接检索条件，当检索关键字非空时，才添加检索条件，忽略大小写
		if (keyword1 != null && !keyword1.isEmpty()) {//按机构名称检索
			keyword1 = keyword1.toLowerCase();
			hql.append(" and LOWER(ag.name) like :keyword1 ");
			map.put("keyword1", "%" + keyword1 + "%");
		}
		if (keyword2 != null && !keyword2.isEmpty()) {//按机构代码检索
			keyword2 = keyword2.toLowerCase();
			hql.append(" and LOWER(ag.code) like :keyword2 ");
			map.put("keyword2", "%" + keyword2 + "%");
		}
		if(keyword3 != null && !keyword3.isEmpty()) {//按机构类型检索
			int type = Integer.valueOf(keyword3);
			hql.append(" and ag.type = :type");
			map.put("type", type);
		}
		if(keyword4 != null && !keyword4.isEmpty()) {//按名称缩写检索
			keyword4 = keyword4.toLowerCase();
			hql.append(" and LOWER(ag.abbr) like :keyword4 ");
			map.put("keyword4", "%" + keyword4 + "%");
		}
		if(keyword5 != null && !keyword5.isEmpty()) {//按机构负责人姓名检索
			keyword5 = keyword5.toLowerCase();
			hql.append(" and LOWER(ag.directorName) like :keyword5 ");
			map.put("keyword5", "%" + keyword5 + "%");
		}
		if(keyword6 != null && !keyword6.isEmpty()) {//按机构电话检索
			keyword6 = keyword6.toLowerCase();
			hql.append(" and LOWER(ag.officePhone) like :keyword6 ");
			map.put("keyword6", "%" + keyword6 + "%");
		}
		if(keyword7 != null && !keyword7.isEmpty()) {//按机构传真检索
			keyword7 = keyword7.toLowerCase();
			hql.append(" and LOWER(ag.officeFax) like :keyword7 ");
			map.put("keyword7", "%" + keyword7 + "%");
		}
		return new Object[] {
			hql.toString(),
			map,
			0,
			GlobalInfo.ERROR_EXCEPTION_INFO
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
		return this.SORT_COLUMNS;
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

	public String getKeyword3() {
		return keyword3;
	}

	public void setKeyword3(String keyword3) {
		this.keyword3 = keyword3;
	}

	public String getKeyword4() {
		return keyword4;
	}

	public void setKeyword4(String keyword4) {
		this.keyword4 = keyword4;
	}

	public String getKeyword5() {
		return keyword5;
	}

	public void setKeyword5(String keyword5) {
		this.keyword5 = keyword5;
	}

	public String getKeyword6() {
		return keyword6;
	}

	public void setKeyword6(String keyword6) {
		this.keyword6 = keyword6;
	}

	public String getKeyword7() {
		return keyword7;
	}

	public void setKeyword7(String keyword7) {
		this.keyword7 = keyword7;
	}

	public Agency getAgency() {
		return agency;
	}

	public void setAgency(Agency agency) {
		this.agency = agency;
	}

}
