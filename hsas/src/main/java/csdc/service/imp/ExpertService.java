package csdc.service.imp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.sun.org.apache.bcel.internal.generic.NEW;

import csdc.model.Expert;
import csdc.service.IExpertService;

@Service
public class ExpertService extends BaseService implements IExpertService {
	
	@Override
	public String addExpert(Expert expert) {
		expert.setNumber(generateNewNumber());
		String expertId = (String) dao.add(expert);
		return expertId;
	}
	
	@Override
	public Expert findExpert(String entityId) {
		return dao.query(Expert.class, entityId);
	}
	
	@Override
	public Expert findExpertByPersonId(String personId) {
		return (Expert)dao.queryUnique("select e from Expert e where e.person.id='" + personId + "'");
	}
	
	@Override
	public Expert findExpertByNumber(String number) {
		return (Expert)dao.queryUnique("select e from Expert e where e.number='" + number + "'");
	}

	@Override
	public Expert findExpertByIdCard(String idcardNumber) {
		return (Expert) dao.queryUnique("select e from Expert e left join e.person p where p.idcardNumber= '" + idcardNumber + "'");
	}
	
	@Override
	public Expert findExpertByIdCardAndName(String idcardNumber, String name) {
		Map<String, String> paraMap = new HashMap<String, String>();
		paraMap.put("idcardNumber", idcardNumber);
		paraMap.put("name", name);
		return (Expert) dao.queryUnique("select e from Expert e left join e.person p where p.idcardNumber=:idcardNumber and p.name=:name", paraMap);
	}
	
	@Override
	public void deleteExperts(List<String> expertIds) {
		if (expertIds != null) {
			for (int i = 0; i < expertIds.size(); i++) {
				dao.delete(Expert.class, expertIds.get(i));
			}
		}
	}
	
	@Override
	public void modifyExpert(Expert newExpert, Expert oldExpert) {
		oldExpert.setSpecialityTitle(newExpert.getSpecialityTitle());
		oldExpert.setResearchField(newExpert.getResearchField());
		oldExpert.setLastDegree(newExpert.getLastDegree());
		oldExpert.setLastEducation(newExpert.getLastEducation());
		oldExpert.setNote(newExpert.getNote());
		oldExpert.setIsRecommend(newExpert.getIsRecommend());
		dao.modify(oldExpert);
	}
	
	@Override
	public String generateNewNumber() {
		String maxNumber = dao.query("select max(e.number) from Expert e").toString();
		if(maxNumber == null || "[null]".equals(maxNumber) || "null".equals(maxNumber) || "NULL".equals(maxNumber) || "".equals(maxNumber)) {
			maxNumber ="0";
		}
		maxNumber = maxNumber.substring(maxNumber.indexOf('E') + 1).replaceAll("[^0-9]", "");
		int nextNumber = Integer.parseInt(maxNumber) + 1;
		int len=0;
		for (int tmp = nextNumber; tmp > 0 ; len++) {
			tmp = tmp / 10;
		}
		StringBuilder sb = new StringBuilder();
		sb.append(new SimpleDateFormat("yyyy").format(new Date()));
		sb.append("E");
		for (int i=0; i<5-len ; i++) {
			sb.append("0");
		}
		return sb.append(nextNumber).toString();
	}

	@Override
	public void authorizeExperts(List<String> expertIds) {
		if (expertIds != null) {
			for (int i = 0; i < expertIds.size(); i++) {
				Expert expert = dao.query(Expert.class, expertIds.get(i));
				expert.setIsRecommend(1);
				dao.modify(expert);
			}
		}
	}

	@Override
	public void unauthorizeExperts(List<String> expertIds) {
		if (expertIds != null) {
			for (int i = 0; i < expertIds.size(); i++) {
				Expert expert = dao.query(Expert.class, expertIds.get(i));
				expert.setIsRecommend(0);
				dao.modify(expert);
			}
		}
	}
	
}
