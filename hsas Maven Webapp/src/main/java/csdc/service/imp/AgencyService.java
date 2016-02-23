package csdc.service.imp;

import java.util.List;

import org.springframework.stereotype.Service;

import csdc.model.Agency;
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
}
