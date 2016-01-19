package csdc.action.expert;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.opensymphony.xwork2.ActionContext;

import csdc.action.BaseAction;
import csdc.dao.SystemOptionDao;
import csdc.model.Discipline;
import csdc.model.Expert;
import csdc.model.Groups;
import csdc.model.Person;
import csdc.model.SystemOption;
import csdc.service.IExpertService;
import csdc.service.IPersonInfoService;
import csdc.service.imp.AgencyService;
import csdc.service.imp.GroupService;
import csdc.tool.HSSFExport;
import csdc.tool.InputValidate;
import csdc.tool.bean.LoginInfo;
import csdc.tool.bean.Pager;
import csdc.tool.info.GlobalInfo;
import csdc.tool.info.RightInfo;

@Component
@Scope(value="prototype")
@SuppressWarnings("unchecked")
public class ExpertAction extends BaseAction {

	private static final long serialVersionUID = -7305780982528326793L;
	protected static final String DATE_FORMAT = "yyyy-MM-dd";// 列表时间格式
	private static final String HQL = "select e.id, p.name, p.agencyName, e.specialityTitle, e.position, g.name, dis.name, e.isApplier, e.isRecommend, e.reviewerType from Expert e left join e.person p left join e.discipline dis left join e.groups g where 1=1 ";
	private static final String PAGE_NAME = "expertPage";
	private static final String PAGE_BUFFER_ID = "e.id";//缓存id
	private static final String column[] = {
		"p.name",
		"p.agencyName",
		"e.specialityTitle",
		"e.position",
		"g.name",
		"dis.name",
		"e.isApplier",
		"e.isRecommend"
	};// 排序用的列
	
	private final static String[] SEARCH_CONDITIONS = new String[]{
		"LOWER(p.name) like :keyword or LOWER(p.agencyName) like :keyword or LOWER(e.specialityTitle) like :keyword or LOWER(dis.name) like :keyword ",
		"LOWER(p.name) like :keyword",
		"LOWER(p.agencyName) like :keyword",
		"LOWER(e.specialityTitle) like :keyword",
		"LOWER(e.position) like :keyword",
		"LOWER(g.name) like :keyword",
		"LOWER(dis.name) like :keyword",
		"LOWER(e.isApplier) like :keyword"
	};//初级检索所用条件范围
	
	private int type; //列表类型
	private Expert expert;
	private Person person;
	private Discipline discipline;
	private Groups group;
	private IExpertService expertService;
	private IPersonInfoService personInfoService;
	private GroupService groupService;
	private AgencyService agencyService;
	private SystemOptionDao soDao;
	private List<SystemOption> ethnics;
	private List<Discipline> disciplines;
	private List<Groups> groups;
	private List<SystemOption> titles;
	
	private String fileFileName;//导出文件名字

	private InputValidate inputValidate = new InputValidate();//校验工具类
	private int accountType; //账号类型。[1：高级管理员；2：一般管理员；3：高校管理员；4：复评专家组长；5：复评专家；6：初评专家；7：申请人]
	private String agencyName;
	
	@Override
	public String toAdd() {
		LoginInfo loginer = (LoginInfo) session.get(GlobalInfo.LOGINER);
		accountType = loginer.getAccount().getType();
		if (accountType == 3) {
			agencyName = loginer.getAccount().getAgency().getName();
		} else if (accountType > 3) {
			jsonMap.put(GlobalInfo.ERROR_NO_RIGHT_INFO, "您无权进行此操作");
			return INPUT;
		}
		ethnics = soDao.queryLeafNodes("GB3304-91");
		disciplines = groupService.getAllDiscipline();
		groups = groupService.getAllGroup();
		titles = soDao.queryLeafNodes("GBT8561-2001");
		return SUCCESS;
	}

	public void validateAdd() {
		validateBasicInfo(person);
		validateExpertInfo(expert);
		validateContactInfo(person);
		validateAddress(person);
	}
	
	@Transactional
	@Override
	public String add() {
		Person existPerson = personInfoService.findPerson(person.getName(), person.getAgencyName(), person.getBirthday());
		if (existPerson != null) {
			Expert existExpert = expertService.findExpertByPersonId(existPerson.getId());
			if (existExpert != null) {
				this.addFieldError("expert", "专家已存在");
				return ERROR;
			}
			expert.setPerson(existPerson);
		} else {
			person.setAgency(agencyService.findAgencyByName(person.getAgencyName()));
			personInfoService.addPerson(person);
			expert.setPerson(person);
		}
		expert.setIsGroupLeader(0);
		expert.setIsReviewer(0);
		expert.setIsApplier(0);
		expert.setDiscipline(groupService.getDiscipline(discipline.getName()));
		expert.setGroups(groupService.getGroupByName(group.getName()));
		entityId = expertService.addExpert(expert);
		jsonMap.put("entityId", entityId);
		jsonMap.put("tag", "1");
		return SUCCESS;
	}

	@Transactional
	@Override
	public String delete() {
		LoginInfo loginer = (LoginInfo) session.get(GlobalInfo.LOGINER);
		accountType = loginer.getAccount().getType();
		if (accountType > 3) {
			jsonMap.put(GlobalInfo.ERROR_NO_RIGHT_INFO, "您无权进行此操作");
			return INPUT;
		}
		expertService.deleteExperts(entityIds);
		return SUCCESS;
	}

	
	
	@Override
	public String toModify() {
		LoginInfo loginer = (LoginInfo) session.get(GlobalInfo.LOGINER);
		accountType = loginer.getAccount().getType();
		if (accountType > 3) {
			jsonMap.put(GlobalInfo.ERROR_NO_RIGHT_INFO, "您无权进行此操作");
			return INPUT;
		}
		expert = expertService.findExpert(entityId);
		if (expert == null) {
			jsonMap.put(GlobalInfo.ERROR_INFO, "该专家已不存在");
			return INPUT;
		} else { 
			person = expert.getPerson();
		}
		ethnics = soDao.queryLeafNodes("GB3304-91");
		disciplines = groupService.getAllDiscipline();
		groups = groupService.getAllGroup();
		titles = soDao.queryLeafNodes("GBT8561-2001");
		discipline = expert.getDiscipline();
		return SUCCESS;
	}

	public void validateModify() {
		validateBasicInfo(person);
		validateExpertInfo(expert);
		validateContactInfo(person);
		validateAddress(person);
		System.out.println();
	}
	
	@Transactional
	@Override
	public String modify() {
		Expert orginExpert = expertService.findExpert(entityId);
		if (orginExpert == null) {
			jsonMap.put(GlobalInfo.ERROR_INFO, "该专家已不存在");
			return INPUT;
		}
		
		personInfoService.modifyPersonInfo(person, orginExpert.getPerson());
		if (group.getName() != null && !group.getName().trim().equals("")) {
			orginExpert.setGroups(groupService.getGroupByName(group.getName()));
		}
		if (discipline.getName() != null && !discipline.getName().trim().equals("")) {
			orginExpert.setDiscipline(groupService.getDiscipline(discipline.getName()));
		}
		expertService.modifyExpert(expert, orginExpert);
		jsonMap.put("tag", "1");
		jsonMap.put("entityId", entityId);
		return SUCCESS;
	}

	@Override
	public String view() {
		expert = expertService.findExpert(entityId);
		if (expert == null) {
			jsonMap.put(GlobalInfo.ERROR_INFO, "该专家已不存在");
			return INPUT;
		} else { 
			person = expert.getPerson();
		}
		
		jsonMap.put("expert", expert);
		jsonMap.put("person", person);
		return SUCCESS;
	}

	@Override
	public String toView() {
		LoginInfo loginer = (LoginInfo) session.get(GlobalInfo.LOGINER);
		accountType = loginer.getAccount().getType();
		if (accountType > 3) {
			jsonMap.put(GlobalInfo.ERROR_NO_RIGHT_INFO, "您无权进行此操作");
			return INPUT;
		}
		return SUCCESS;
	}
	
	public void validateToView() {
		if (entityId == null || entityId.trim().isEmpty()) {
			this.addFieldError(GlobalInfo.ERROR_INFO, RightInfo.ERROR_VIEW_NULL);
		}
	}
	
	/**
	 * 查看信息时校验ID不得为空
	 */
	public void validateView() {
		if (entityId == null || entityId.trim().isEmpty()) {
			this.addFieldError(GlobalInfo.ERROR_INFO, RightInfo.ERROR_VIEW_NULL);
			jsonMap.put(GlobalInfo.ERROR_INFO, RightInfo.ERROR_VIEW_NULL);
		}
	}

	@Override
	public Object[] simpleSearchCondition() {
		StringBuffer hql = new StringBuffer();
		Map<String, String> map = new HashMap<String, String>();
		hql.append(HQL);
		if (type ==0) {
			
		} else if (type == 1) {
			hql.append(" and e.isRecommend =1");
		} else if (type == 2) {
			hql.append(" and e.isReviewer =1");
		} else {
			hql.append(" and 0=1");
		}

		LoginInfo loginer = (LoginInfo) session.get(GlobalInfo.LOGINER);
		if(loginer.getAccount().getType() == 3) {
			hql.append(" and p.agencyName = '" + loginer.getAccount().getAgency().getName() + "'");
		}

		if (keyword != null && !keyword.trim().isEmpty()) {
			boolean flag = false;
			String[] sc = searchConditions();
			for (int i = 0; !flag && i < sc.length; i++) {
				if (searchType == i) {
					hql.append(" and ").append(sc[i]);
					flag = true;
				}
			}
			map.put("keyword", "%" + (keyword == null ? "" : keyword.toLowerCase()) + "%");
		}
		return new Object[]{
			hql.toString(),
			map,
			0,
			null
		};
	}
	
	/**
	 * 校验人员基本信息
	 * @param person 人员
	 */
	public void validateBasicInfo(Person person) {
		if (person.getName() == null || person.getName().trim().isEmpty()){
			this.addFieldError("person.name", "基本信息 —— 姓名不应为空");
		} else if(!Pattern.matches("^.{1,50}$", person.getName().trim())){
			this.addFieldError("person.name", "基本信息 —— 中文名不合法");
		}
		if (person.getIdcardNumber().trim().length() > 18){
			this.addFieldError("person.idcardNumber", "基本信息 —— 证件号过长");
		}		
		if (person.getEthnic() != null && person.getEthnic().trim().length() > 20){
			this.addFieldError("person.ethnic", "基本信息 —— 民族过长");
		}		
		if (person.getBirthday() == null) {
			this.addFieldError("person.birthday", "基本信息 —— 出生日期不能为空");
		}
		if (person.getBirthday() != null && person.getBirthday().compareTo(new Date()) > 0) {
			this.addFieldError("person.birthday", "基本信息 —— 不合理的出生日期");
		}
		if (person.getAgencyName() == null || person.getAgencyName().trim().isEmpty()){
			this.addFieldError("person.agencyName", "所在单位名称不应为空");
		}
	}
	
	/**
	 * 校验人员联系信息
	 */
	public void validateContactInfo(Person person) {
		if (person.getHomePhone() != null && !inputValidate.checkPhone(person.getHomePhone().trim())){
			this.addFieldError("person.homePhone", "联系信息 —— 住宅电话不合法");
		}
		if (person.getOfficePhone() != null && !inputValidate.checkPhone(person.getOfficePhone().trim())){
			this.addFieldError("person.officePhone", "联系信息 —— 办公电话不合法");
		}
		if (person.getEmail() == null || person.getEmail().trim().isEmpty()){
			this.addFieldError("person.email", "联系信息 —— 邮箱不应为空");
		} else {
			String[] mail = person.getEmail().split(";");
			for (int i = 0; i < mail.length; i++) {
				String	email = mail[i];
				if(!inputValidate.checkEmail(email.trim())){
					this.addFieldError("person.email", "联系信息 —— 邮箱不合法");
				}
			}
		}
		if (person.getMobilePhone() != null && !inputValidate.checkCellphone(person.getMobilePhone().trim())){
			this.addFieldError("person.mobilePhone", "联系信息 —— 移动电话不合法");
		}
	}
	
	/**
	 * 校验人员地址信息
	 * @param person
	 */
	public void validateAddress(Person person) {
		if (person.getPostCode() != null && !inputValidate.checkPostcode(person.getPostCode())){
			this.addFieldError("person.postCode", "联系信息 —— 邮编不合法");
		}
		if (person.getAddress() != null && person.getAddress().trim().length() > 400){
			this.addFieldError("person.address", "联系信息 —— 地址过长");
		}
	}
	
	/**
	 * 校验学科分组与学科门类信息
	 * @param expert
	 */
	public void validateExpertInfo(Expert expert) {
		if (group == null || "".equals(group.getName())) {
			this.addFieldError("expert.group", "专家信息 —— 学科分组不能为空");
		} else if (groupService.getGroupByName(group.getName()) == null) {
			this.addFieldError("expert.group", "专家信息 —— 学科分组不合法");
		}
		if (discipline != null && groupService.getDiscipline(discipline.getName()) == null) {
			this.addFieldError("expert.discipline", "专家信息 —— 学科门类不合法");
		}
	}
	
	/**
	 * 推荐专家
	 * @return
	 */
	@Transactional
	public String authorize() {
		expertService.authorizeExperts(entityIds);
		return SUCCESS;
	}
	
	/**
	 * 撤消专家推荐
	 * @return
	 */
	@Transactional
	public String unauthorize() {
		expertService.unauthorizeExperts(entityIds);
		return SUCCESS;
	}
	
	/**
	 * 导出专家信息
	 */
	public String exportExpert() {
		return SUCCESS;
	}
	
	/**
	 * 导出专家信息
	 * @return 输入流
	 */
	public InputStream getDownloadFile() throws Exception{
		fileFileName = "湖北省社会科学优秀成果奖评审专家一览表.xls";
		fileFileName = new String(fileFileName.getBytes(), "ISO8859-1");

		List dataList = new ArrayList();
		Pager pager = fetchPager(false);
		if(pager != null) {
			dataList=dao.query(pager.getHql(), pager.getParaMap());
		} else {
			String exportHQL = "select p.id, p.name, p.agencyName, e.specialityTitle, dis.name from Expert e left join e.person p left join e.discipline dis ";
			dataList = dao.query(exportHQL);
		}
		
		//添加序号
		for (int i=0, size = dataList.size(); i < size; i++) {
			Object[] obj = (Object[]) dataList.get(i);
			obj[0] = i + 1;
		}
		
		dataList = expertListDealWith(dataList);
		
		//导出配置
		String header = "湖北省社会科学优秀成果奖评审专家一览表";
		String[] title = {"序号", "专家姓名", "所在机构", "职称", "学科门类"};
		String tail = "";
		return HSSFExport.commonExportExcel(dataList, header, title, tail, 0.0f);
	}
	
	/**
	 * 格式化集合中日期类型数据
	 * @param dataList
	 * @return
	 */
	public List expertListDealWith(List dataList) {
		laData = new ArrayList();
		Object[] o;
		String[] item;
		SimpleDateFormat dateformat = new SimpleDateFormat(dateFormat());// 时间格式化对象
		String datestr;
		
		for (Object p : dataList) {
			o = (Object[]) p;
			item = new String[o.length];
			for (int i = 0; i < o.length; i++) {
				if (o[i] == null) {
					item[i] = "";
				} else {
					if (o[i] instanceof Date) {
						datestr = dateformat.format((Date) o[i]);
						item[i] = datestr;
					} else {
						item[i] = o[i].toString();
					}
				}
			}
			laData.add(item);
		}
		return laData;
	}

	@Override
	public Object[] advSearchCondition() {
		return null;
	}

	@Override
	public String pageName() {
		return PAGE_NAME;
	}

	@Override
	public String pageBufferId() {
		return ExpertAction.PAGE_BUFFER_ID;
	}

	@Override
	public String[] sortColumn() {
		return column;
	}

	@Override
	public String dateFormat() {
		return DATE_FORMAT;
	}
	
	public String[] searchConditions() {
		return SEARCH_CONDITIONS;
	}
	
	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	public Expert getExpert() {
		return expert;
	}

	public void setExpert(Expert expert) {
		this.expert = expert;
	}
	
	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
	
	public Discipline getDiscipline() {
		return discipline;
	}

	public void setDiscipline(Discipline discipline) {
		this.discipline = discipline;
	}
	
	public Groups getGroup() {
		return group;
	}

	public void setGroup(Groups group) {
		this.group = group;
	}
	
	public List<SystemOption> getEthnics() {
		return ethnics;
	}

	public void setEthnics(List<SystemOption> ethnics) {
		this.ethnics = ethnics;
	}
	
	public List<Discipline> getDisciplines() {
		return disciplines;
	}

	public void setDisciplines(List<Discipline> disciplines) {
		this.disciplines = disciplines;
	}

	public List<Groups> getGroups() {
		return groups;
	}

	public void setGroups(List<Groups> groups) {
		this.groups = groups;
	}
	
	public List<SystemOption> getTitles() {
		return titles;
	}

	public void setTitles(List<SystemOption> titles) {
		this.titles = titles;
	}

	public IExpertService getExpertService() {
		return expertService;
	}

	@Resource
	public void setExpertService(IExpertService expertService) {
		this.expertService = expertService;
	}
	
	public IPersonInfoService getPersonInfoService() {
		return personInfoService;
	}

	@Resource
	public void setPersonInfoService(IPersonInfoService personInfoService) {
		this.personInfoService = personInfoService;
	}
	
	public GroupService getGroupService() {
		return groupService;
	}

	@Resource
	public void setGroupService(GroupService groupService) {
		this.groupService = groupService;
	}
	
	public AgencyService getAgencyService() {
		return agencyService;
	}

	@Resource
	public void setAgencyService(AgencyService agencyService) {
		this.agencyService = agencyService;
	}
	
	public SystemOptionDao getSoDao() {
		return soDao;
	}

	@Resource
	public void setSoDao(SystemOptionDao soDao) {
		this.soDao = soDao;
	}

	public int getAccountType() {
		return accountType;
	}

	public void setAccountType(int accountType) {
		this.accountType = accountType;
	}

	public String getAgencyName() {
		return agencyName;
	}

	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}

}
