package csdc.service.imp;


import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import csdc.model.SolicitPapers;
import csdc.service.ISolicitPapersService;


@Service
@Transactional
public class SolicitPapersService extends BaseService implements ISolicitPapersService {

	@Override
	public SolicitPapers getSolicitPapers(String entityId) {
		
		
		return dao.query(SolicitPapers.class, entityId);
	}

	@Override
	public String addDocument(SolicitPapers document) {
		
		document.setCreateDate(new Date());
	    return dao.add(document).toString();
	}

	@Override
	public String modifyDocument(SolicitPapers solicitPapers,SolicitPapers oSolicitPapers) {
		
		if(solicitPapers.getAddress()!=null){
			oSolicitPapers.setAddress(solicitPapers.getAddress());
		}
		if(solicitPapers.getDescription()!=null){
			oSolicitPapers.setDescription(solicitPapers.getDescription());
		}
		if(solicitPapers.getPhone()!=null){
			oSolicitPapers.setPhone(solicitPapers.getPhone());
		}
		
		 dao.modify(oSolicitPapers);
		 return oSolicitPapers.getId();
	}

	@Override
	public void delete(List<String> entityIds) {
		for(String entityId:entityIds){
			
			dao.delete(SolicitPapers.class, entityId);
		}
	}

	

	
	

}
