package csdc.service.imp;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import csdc.model.Agency;
import csdc.model.Person;
import csdc.model.ProductAuthor;
import csdc.service.IViewService;

/**
 * 查看人员和机构弹层
 * @author huangxw
 *
 */
@Service
@Transactional
public class ViewService extends BaseService implements IViewService {

	/**
	 * 弹层提取个人信息
	 */
	@Override
	public Map viewPerson(String entityId) {
		Map map = new HashMap();
		map.put("personId", entityId);
		/*String hql ="select pa from ProductAuthor pa left join pa.person p where p.id= :personId";
		ProductAuthor productAuthor = (ProductAuthor) dao.queryUnique(hql, map);
		Person p =productAuthor.getPerson();*/
		
		String hql = "select p from Person p where p.id= :personId";
		Person person = (Person) dao.queryUnique(hql,map);
		map.clear();
		
	/*	map.put("name", p.getName());
		map.put("gender", p.getGender());
		map.put("ethnic", p.getEthnic());
		map.put("idCardNumber", p.getIdcardNumber());
		map.put("birthday", p.getBirthday());
		map.put("email", p.getEmail());
		map.put("mobilePhone", p.getMobilePhone());
		map.put("address", p.getAddress());
		map.put("postCode", p.getPostCode());
		map.put("introduction", p.getIntroduction());
		map.put("agencyName", p.getAgencyName());
		
		map.put("workDivision", productAuthor.getWorkDivision());
		map.put("position", productAuthor.getPosition());*/
		
		map.put("person", person);
		//map.put("productAuthor", productAuthor);
		
		return map;
	}

	@Override
	public Map viewAgency(String entityId) {
		Map map = new HashMap();
		map.put("agencyId", entityId);
		String hql ="select a from  Agency  a  where a.id=:agencyId";
		/*ProductAuthor productAuthor = (ProductAuthor) dao.queryUnique(hql, map);
		Agency agency = productAuthor.getAgency();*/
		
		Agency agency = (Agency) dao.queryUnique(hql,map);
		map.clear();
		
		/*map.put("name", agency.getName());
		map.put("type", agency.getType()==0 ? "非高校":"高校");
		map.put("code", agency.getCode());
		map.put("address", agency.getAddress());
		map.put("directionName", agency.getDirectorName());
		map.put("postCode", agency.getPostCode());
		map.put("mobilePhone", agency.getMobilePhone());
		map.put("email", agency.getEmail());
		map.put("introduction", agency.getIntroduction());
		map.put("createDate", agency.getCreateDate());*/
		
		map.put("agency", agency);
		
		return map;
	}

}
