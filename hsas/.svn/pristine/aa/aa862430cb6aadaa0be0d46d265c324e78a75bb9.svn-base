package csdc.service.imp;

import java.util.List;

import org.springframework.stereotype.Service;

import csdc.service.ISystemOptionService;

@Service
public class SystemOptionService extends BaseService implements ISystemOptionService {

	@Override
	public List getGroupInfo() {
		String hql = "select g.name from Groups g order by g.index";
		return dao.query(hql);
	}

	@Override
	public List getEthnicsInfo() {
		
		return dao.query("select so.name from SystemOption so left join so.systemOptions children where so.standard = ? and children is null order by so.code", "GB3304-91");
	}

	@Override
	public List getDisplineInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List getPublishNameInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List getPublishLevelInfo() {
		// TODO Auto-generated method stub
		return null;
	}

}
