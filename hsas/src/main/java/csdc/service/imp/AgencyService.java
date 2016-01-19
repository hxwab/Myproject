package csdc.service.imp;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import csdc.model.Agency;
import csdc.model.Person;
import csdc.service.IAgencyService;

@Service
public class AgencyService extends BaseService implements IAgencyService {

	/**
	 * 机构名  -> 机构
	 */
	@Override
	public Agency findAgencyByName(String agencyName) {
		List list = dao.query("select agency from Agency agency where agency.name = '" + agencyName + "'");
		return list.isEmpty() ? null : (Agency)list.get(0);
	}

	@Override
	public boolean checkAgency(Agency agency) {
		StringBuffer hql = new StringBuffer("from Agency ag where ag.name=:name");
		Map map = new HashMap();
		map.put("name", agency.getName());
		if(agency.getCode()!=null) {
			hql.append("and ag.code=:code");
			map.put("code", agency.getCode());
		}
		List list = dao.query(hql.toString(), map);
		return list.isEmpty()? false:true;
	}
	
	@Override
	public boolean checkAgencyByName(String agencyName) {
		Map map = new HashMap();
		map.put("agencyName", agencyName);
		List list = dao.query("select agency from Agency agency where agency.name =:agencyName ",map);
		return !list.isEmpty();
	}

	@Override
	public String addAgency(Agency agency) {
		agency.setCreateDate(new Date());
		return dao.add(agency);
	}

	@Override
	public String modifyAgency(Agency oldAgency, Agency agency) {
		oldAgency.setName(agency.getName());
		oldAgency.setEnglishName(agency.getEnglishName());
		oldAgency.setType(agency.getType());
		oldAgency.setCode(agency.getPostCode());
		oldAgency.setAbbr(agency.getAbbr());
		oldAgency.setAddress(agency.getAddress());
		oldAgency.setPostCode(agency.getPostCode());
		oldAgency.setMobilePhone(agency.getMobilePhone());
		oldAgency.setOfficePhone(agency.getOfficePhone());
		oldAgency.setOfficeFax(agency.getOfficeFax());
		oldAgency.setEmail(agency.getEmail());
		oldAgency.setHomepage(agency.getHomepage());
		oldAgency.setIntroduction(agency.getIntroduction());
		oldAgency.setUpdateDate(new Date());
		dao.modify(oldAgency);
		return oldAgency.getId();
	}

	@Override
	public int deleteAgency(List<String> entityIds) {
		try {
			for(String entityId: entityIds) {
				dao.delete(Agency.class, entityId);
			}
		} catch (Exception e) {
			return 0;//删除失败
		}
		return 1;
	}

	@Override
	public Agency viewAgency(String entityId) {
		return (Agency)dao.query(Agency.class, entityId);
	}

}
