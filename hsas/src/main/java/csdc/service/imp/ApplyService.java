package csdc.service.imp;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sun.reflect.ReflectionFactory.GetReflectionFactoryAction;

import csdc.model.Account;
import csdc.model.Agency;
import csdc.model.Discipline;
import csdc.model.Expert;
import csdc.model.Groups;
import csdc.model.Person;
import csdc.model.Product;
import csdc.model.ProductAuthor;
import csdc.service.IApplyService;
/**
 * 
 * 需要操作的表有 Person、Product、Agency、product_member
 * 
 * 首先添加Person，因为无任何外键关联（在添加人员信息时，需要检测数据库中是否已经存在；不存在直接添加，否则更新）
 * 其次添加Agency,跟person有外键关联
 * 再添加Product,跟person/agency/systemoption有关 
 * 最后添加中间表product_member，跟person/agency/product有关
 * 
 * @author huangxw
 *
 */
@Service
public class ApplyService extends BaseService implements IApplyService {

	
	
	

	@Override
	public void delete(List<String> awardIds) {
		
	}

	/**
	 * 添加person,若数据库中存在该人员，则更新信息，否则直接添加
	 */
	@Override
	public String addPerson(Person person) {
		
		StringBuffer str ;
		Map map =new HashMap();
		map.put("birthday", person.getBirthday());
		/*map.put("agencyName", person.getAgencyName());
		map.put("name", person.getName());
		String hql ="select p from Person p where p.name = :name and p.birthday = :birthday and p.agencyName= :agencyName";
		*/
		map.put("agencyId", person.getAgency().getId());
		map.put("name", person.getName());
		String hql ="select p from Person p where p.name = :name and p.birthday = :birthday and p.agency.id= :agencyId";
		Person p =(Person) dao.queryUnique(hql, map);
		
		if(p==null){
			person.setCreateDate(new Date());
			return dao.add(person);
		} else {
			
			
			if(person.getOfficePhone()!=null){
					p.setOfficePhone(person.getOfficePhone());
			}
			
			if(person.getMobilePhone()!=null){
					p.setMobilePhone(person.getMobilePhone());
			}
			
	
			
			if(person.getEmail()!=null){
					p.setEmail(person.getEmail());
			}
			
			if(person.getAddress()!=null){
					p.setAddress(person.getAddress());
			}
		
			p.setIntroduction(person.getIntroduction());
			p.setUpdateDate(new Date());
			
			 dao.modify(p);
			 
			String hql1 = "select e from Expert e where e.person=:person";
			map.clear();
			map.put("person", p);
			Expert expert = (Expert) dao.queryUnique(hql1, map);
			if(expert!=null){
				expert.setIsApplier(1);
				dao.modify(expert);
			}
			
			 
			 return p.getId();
			
		}
		
		
	}

	@Override
	public List<String> addPersons(List<Person> person,Map map) {
		
		List<String> list = new ArrayList<String>();
		//List<String> agencyNames = (List<String>) map.get("agencyName");
		List<String> agencyIds =(List<String>) map.get("agencyId");
		List<String> agencyNames = (List<String>) map.get("agencyName");
		
		int i=0;
		for(Person p : person){
			if(p!=null){
				
				if(!agencyIds.isEmpty()){

					Agency agency = dao.query(Agency.class, agencyIds.get(i++));
					p.setAgency(agency);
					p.setAgencyName(agencyNames.get(i-1));
				}
			
			String id =addPerson(p);
			list.add(id);
			}
		}
		return list;
	}

	/**
	 * 要添加机构、人员和学科代码（均为外键关联）
	 * 
	 */
	@Override
	public String addProduct(Product product) {
		product.setCreateDate(new Date());
		return dao.add(product);
	}

	/**
	 * 要添加到product_member表中去
	 */
	@Override
	public String addApplyInfo(Person person, Product product, Agency agency,Map map) {
		
		String personId = addPerson(person);
		/*
		 * 先找机构负责人，再将机构负责人设置为agency的属性
		 * Person agencyDirector = dao.query(***);
		 * agency.setDirector(agencyDirector);
		 */
		String agencyId = dao.add(agency);
		
		/**
		 * 
		 * 必须是先存入再取出，因为外键关联存的是对象（该对象必须具有主键ID）
		 * 
		 * 1、找到作者 author = dao.query(Person.Class, personId);
		 * 2、找到机构 agency = dao.query(Agency.Class , agencyId);
		 * 3、找到学习代码  systemOptin = dao.query("***",map);
		 * 
		 * 然后是set属性
		 */
		String prouctId = addProduct(product);
		
		//***
		/**
		 * 待添加bean文件再完善
		 * 判断是否负责人，将负责人信息装入map中
		 * 取出person/agency/product，然后设置属性
		 */
		/*ProduceMember productMember = new 	ProduceMember();
		
		productMember.setProduct(**);
		productMember.setAgency(**);
		productMember.setPerson(**);
		productMember.setXX(**);*/
		
		
		
		return null;
	}

	
	/**
	 * 批量添加成员信息
	 * 此处机构信息未确定
	 */
	@Transactional
	@Override
	public String addApplyInfos(List<Person> person, Product product,Agency agency,Map map) {
	
		//Map argMap = new HashMap();
		List<Agency> agencys = new ArrayList<Agency>();
		
		List<String> personIds =addPersons(person,map);//实际添加的人数，List<Person>里为空的元素不添加
		List<String> workDivisions = (List<String>) map.get("workDivision");
		List<String> positions = (List<String>) map.get("position");
		List<String> agencyIds = (List<String>) map.get("agencyId");
		List<String> agencyNames = (List<String>) map.get("agencyName");
		//String productAgencyId = (String) map.get("productAgencyId");
		int sumitStatus = (Integer) map.get("submitStatus");
		
		
		for(int i =0;i<personIds.size();i++){
			agency = dao.query(Agency.class, agencyIds.get(i));
			agencys.add(agency);
		}
		
		
		/*
		 * 先找机构负责人，再将机构负责人设置为agency的属性(机构信息和机构负责人信息是直接录入的)
		 * Person agencyDirector = dao.query(***);
		 * agency.setDirector(agencyDirector);
		 */
		
		//有可能前台直接传agencyName或agency过来
		//String agencyName = agencys.get(0);
		
		/**
		 * 
		 * 必须是先存入再取出，因为外键关联存的是对象（该对象必须具有主键ID）
		 * 
		 * 1、找到作者 author = dao.query(Person.Class, personIds.get(0));
		 * 2、找到机构 agency = dao.query(Agency.Class , agencyId);
		 * 3、找到学科代码  systemOptin = dao.query("***",map);
		 * 
		 * 然后是set属性
		 */
		Person author = dao.query(Person.class,personIds.get(0));
		//Agency agen = dao.query(Agency.class, productAgencyId);
		Agency agen =agencys.get(0);
		//String  disciplineName = (String) map.get("disciplineName");
		//Discipline discipline = getDiscipline(parseDisciplineName(disciplineName));
		String  groupName = (String) map.get("groupName");
		Groups group = getGroupByName(groupName);
		int currentYear = new Date().getYear() +1900;
		
		product.setApplyYear(Integer.toString(currentYear));
		product.setAuthor(author);
		product.setAuthorName(author.getName());
		//初次提交时设置为暂存状态
		product.setSubmitStatus(sumitStatus);
		product.setAgency(agen);
		/*if(agen.getName().equals("其他")){
			product.setAgencyName(agencyNames.get(0));
		}else{
			product.setAgencyName(agen.getName());
		}*/
		product.setAgencyName(agencyNames.get(0));
		product.setGroups(group);
		product.setGroupName(group.getName());
		product.setNumber(generateNumber());
		product.setStatus(sumitStatus);//sumitStatus状态跟成果状态一致
		
		
		if(agen.getType()==0){
			product.setUniversityAuditResult(4);
		}
		
		String prouctId = addProduct(product);
		
		//***
		/**
		 * 待添加bean文件再完善
		 * 判断是否负责人，将负责人信息装入map中
		 * 取出person/agency/product，然后设置属性
		 * 
		 * 怎么设置非一作的信息
		 */
		
		for(int i =0;i<personIds.size();i++ ){
			
		Person p = dao.query(Person.class, personIds.get(i));
		
		ProductAuthor productAuthor = new ProductAuthor();
		
		productAuthor.setProduct(product);
		productAuthor.setPerson(p);
		if(i==0){
			productAuthor.setIsFirstAuthor(1);
		}else {
			productAuthor.setIsFirstAuthor(0);
		}
		productAuthor.setOrder(i+1);
		
		productAuthor.setAgency(agencys.get(i));
		productAuthor.setAgencyName(agencyNames.get(i));
		productAuthor.setPosition(positions.get(i));
		productAuthor.setWorkDivision(workDivisions.get(i));
		
		dao.add(productAuthor);
			
		}
		
		return prouctId;
		
	}

	@Override
	public Map getProducts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product getProductById(String id) {
		
		return dao.query(Product.class, id);
	}

	@Override
	public Product getProductByAccount(Account account) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String modify(Product oldProduct, Product newProduct) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 获取项目成员，不包括负责人
	 */
	@Override
	public List getProductMembersByProductId(String entityId) {
		Map map = new HashMap();
		map.put("id", entityId);
		String hql = "select pm from ProductMember pm left join pm.product p where p.id = :id and isdirector=0";
		return dao.query(hql, map);
	}

	@Override
	public Agency getAgency(String agencyName) {
		Map map = new HashMap();
		map.put("name", agencyName);
		String hql = "select a from Agency a where a.name = :name";
		return (Agency) dao.queryUnique(hql,map);
	}

	@Override
	public Discipline getDiscipline(String disciplineName) {
		Map map = new HashMap();
		map.put("name", disciplineName);
		String hql = "select d from Discipline d where d.name = :name";
		return (Discipline) dao.queryUnique(hql,map);
	}

	@Override
	public List<String> getArgsList(String ArgsName, int number) {
		
		return null;
	}

	@Override
	public List<Object[]> getMemberInfo(String productId) {
		
		Map map = new HashMap();
		map.put("productId", productId);
		//String hql = "select pa.person,pa.workDivision,pa.position,pa.agencyName from ProductAuthor pa where pa.product.id= :productId order by pa.order";
		String hql = "select pa.person,pa.workDivision,pa.position,pa.agency.id from ProductAuthor pa where pa.product.id= :productId order by pa.order";
		return  dao.query(hql,map);
	}

	
	@Transactional
	@Override
	public String modifyInfos(List<Person> person, Product product, Map map) {
		Map argMap = new HashMap();
		List<Agency> agencys = new ArrayList<Agency>();
		Agency agency;
		List<String> workDivisions = (List<String>) map.get("workDivision");
		List<String> personIds =addPersons(person,map);//实际添加的人数，List<Person>里为空的元素不添加
		List<String> positions = (List<String>) map.get("position");
		List<String> agencyIds = (List<String>) map.get("agencyId");
		List<String> agencyName = (List<String>) map.get("agencyName");
		//String agencyName = map.get("agencyName").toString();
		String entityId = map.get("entityId").toString();
		Product oProduct = getProductById(entityId);
		
		//String productAgencyId = (String) map.get("productAgencyId");
		int sumitStatus = (Integer) map.get("submitStatus");
		
		for(int i =0;i<personIds.size();i++){
			agency = dao.query(Agency.class, agencyIds.get(i));
			agencys.add(agency);
		}
		
		
		/*
		 * 先找机构负责人，再将机构负责人设置为agency的属性(机构信息和机构负责人信息是直接录入的)
		 * Person agencyDirector = dao.query(***);
		 * agency.setDirector(agencyDirector);
		 */
		
		//有可能前台直接传agencyName或agency过来
		//String agencyName = agencys.get(0);
		
		/**
		 * 
		 * 必须是先存入再取出，因为外键关联存的是对象（该对象必须具有主键ID）
		 * 
		 * 1、找到作者 author = dao.query(Person.Class, personIds.get(0));
		 * 2、找到机构 agency = dao.query(Agency.Class , agencyId);
		 * 3、找到学科代码  systemOptin = dao.query("***",map);
		 * 
		 * 然后是set属性
		 */
		Person author = dao.query(Person.class,personIds.get(0));
		//Agency agen = dao.query(Agency.class, productAgencyId);
		Agency agen =agencys.get(0);
		//String  disciplineName = (String) map.get("disciplineName");
		//Discipline discipline = getDiscipline(parseDisciplineName(disciplineName));
		String  groupName = (String) map.get("groupName");
		Groups group = getGroupByName(groupName);
		int currentYear = new Date().getYear() +1900;
		
		product.setApplyYear(Integer.toString(currentYear));
		product.setAuthor(author);
		product.setAuthorName(author.getName());
		//初次提交时设置为暂存状态
		product.setSubmitStatus(sumitStatus);
		product.setAgency(agen);
		
		product.setAgencyName(agencyName.get(0));
		product.setGroups(group);
		product.setGroupName(group.getName());
		//product.setNumber(generateNumber());
		product.setStatus(sumitStatus);
		
		product.setCreateDate(oProduct.getCreateDate());
		product.setUpdateDate(new Date());
		product.setNumber(oProduct.getNumber());
		product.setAccountId(oProduct.getAccountId());
		
		if(agen.getType()==0){
			product.setUniversityAuditResult(4);
		}
		product.setId(entityId);
		//String prouctId = addProduct(product);
		 dao.modify(product);
		
		
		for(int i =0;i<personIds.size();i++ ){
			
			Person p = dao.query(Person.class, personIds.get(i));
			HashMap hMap =new HashMap();
			hMap.put("id", p.getId());
			ProductAuthor productAuthor= null;
			try {
				
				 productAuthor = (ProductAuthor) dao.queryUnique("select pa from ProductAuthor pa where pa.person.id =:id ",hMap);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			boolean productAuthorIsNull = false;
			if(productAuthor==null){
				productAuthor = new ProductAuthor();
				productAuthorIsNull=true;
			}
			
			productAuthor.setProduct(product);
			productAuthor.setPerson(p);
			if(i==0){
				productAuthor.setIsFirstAuthor(1);
			}else {
				productAuthor.setIsFirstAuthor(0);
			}
			productAuthor.setOrder(i+1);
			
			productAuthor.setAgency(agencys.get(i));
			productAuthor.setAgencyName(agencyName.get(i));
			productAuthor.setPosition(positions.get(i));
			productAuthor.setWorkDivision(workDivisions.get(i));
			
			
			if(productAuthorIsNull){
				dao.add(productAuthor);
			}else {
				dao.modify(productAuthor);
			}
		}
			
		return entityId;
		
	}
	

	private Groups getGroupByName(String groupName){
		Map map = new HashMap();
		map.put("name", groupName);
		String hql = "select g from Groups g where g.name =:name";
		return (Groups) dao.queryUnique(hql,map);
		
	}
	
	public Discipline getDisciplineByGroupName(String groupName){
		Map map = new HashMap();
		map.put("name", groupName);
		String hql = "select gd.discipline from GroupDiscipline gd left join gd.group gdg where gdg.name=:name";
		return  (Discipline) dao.queryUnique(hql,map);
	}

	private String parseGroupName(String groupName){
		
		String str="";
		switch (Integer.parseInt(groupName)) {
		case 0:
			str="马克思主义与党建（社科）";
			break;
		case 1:
			str = "经济理论学";
			break;
		case 2:
			str="应用经济学";
			break;
		case 3:
			str="哲学与社会学";
			break;	
			
		case 4:
			str="历史学（考古学）";
			break;
		case 5:
			str="语言文字（文化研究、新闻学、图书情报学）";
			break;	
			
		case 6:
			str="法学（政治学）";
			break;	
			
		case 7:
			str="综合一组（教育学、体育学等）";
			break;
		case 8:
			str="综合二组（民族学、宗教学、艺术学、交叉学科等）";
			break;	
		case 9:
			str="综合三组（市州及其他）";
			break;
	
		default:
			break;
		}
		
		return str;
	}

	@Override
	public Agency getAgencyById(String agencyId) {
		
		return dao.query(Agency.class, agencyId);
	}

	@Override
	public List<Object> getObjectList(Object obj1, Object obj2, Object obj3,
			Object obj4, Object obj5) {
		
		List<Object> list = new ArrayList<Object>();
		
		if(obj1!=null){
			
			list.add(obj1);
		}
		if(obj2!=null){
			
			list.add(obj2);
		}
		if(obj3!=null){
			
			list.add(obj3);
		}
		if(obj4!=null){
			
			list.add(obj4);
		}
		if(obj5!=null){
			
			list.add(obj5);
		}
		
		return list;
	}
	

	public Date dateformat2Date(Date date){
		
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 String  str = sdf.format(date);
		 date = java.sql.Date.valueOf(str);
		 return date;
	}
	
	
	private String generateNumber(){
		
		int random = new Random().nextInt(3);
		try {
			Thread.sleep(random*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR)-2000;//获取年份
        int month=cal.get(Calendar.MONTH)+1;//获取月份
        int day=cal.get(Calendar.DATE);//获取日
        int hour=cal.get(Calendar.HOUR_OF_DAY);//小时
        int minute=cal.get(Calendar.MINUTE);//分           
        int second=cal.get(Calendar.SECOND);//秒
		StringBuffer sb = new StringBuffer();
		sb.append(year);
		sb.append(month);
		sb.append(day);
		sb.append(hour);
		sb.append(minute);
		sb.append(second);
		
		return "P"+sb.toString();
		
	}

	@Override
	public boolean isCanApply(Person person,String agencyId) {
	
		String name = person.getName();
		Map<String,String> map = new HashMap<String, String>();
		map.put("name", name);
		map.put("agencyId", agencyId);
		String hql = "select p from Product  p left join p.author pp left join pp.agency a where pp.name=:name and a.id=:agencyId and p.submitStatus=2";
		List list = dao.query(hql, map);
		return list.isEmpty()||list.size()<2;
	}
	
}
