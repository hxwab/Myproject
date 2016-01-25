package csdc.service.imp;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import csdc.model.Discipline;
import csdc.model.Expert;
import csdc.model.Groups;
import csdc.model.Person;
import csdc.model.ProductAuthor;
import csdc.service.IAssignExpertService;
@Service
public class AssignExpertService extends BaseService implements IAssignExpertService {

	@Override
	public List laDataDealWith(List laData, int type) {
		List newlaData = new ArrayList();
		String[] item;
		String[] exterData;
		String groupId;
		for(Object data: laData) {
			String[] oitem = (String[])data;
			item = new String[oitem.length+2];
			groupId = oitem[0];
			int len = oitem.length;
			for(int i=0; i<len; i++) {
				item[i] = oitem[i];
			}
			exterData = getExterData(groupId, type);
			item[len] = exterData[0];
			item[len+1] = exterData[1];
			newlaData.add(item);
		}
		return newlaData;
	}
	
	@Override
	public List expertDataDealWith(List laData) {
		List newlaData = new ArrayList();
		String[] item;
		int isAuthor;
		String expertId;
		for(Object data: laData) {
			String[] oitem = (String[])data;
			item = new String[oitem.length+1];
			expertId = oitem[0];
			int len = oitem.length;
			for(int i=0; i<len; i++) {
				item[i] = oitem[i];
			}
			isAuthor = getIsAuthorByExpertId(expertId);
			item[len] = new Integer(isAuthor).toString();
			newlaData.add(item);
		}
		return newlaData;
	}
	
	private int getIsAuthorByExpertId(String expertId) {
		boolean isAuthor = false;
		boolean isFirstAuthor = false;
		int authorFlag;
		Expert expert = dao.query(Expert.class, expertId);
		Person person = expert.getPerson();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		String currentYear =  sdf.format(new Date());
		//查询当年提交的成果的作者中包含指定Person的成果
		String hql = "from ProductAuthor pa where pa.person = :person and " +
				"DATE_FORMAT(pa.product.submitDate, '%Y')='"+currentYear+"'";
		Map map = new HashMap();
		map.put("person", person);
		List<ProductAuthor> list = dao.query(hql, map);
		if(!list.isEmpty()) {
			isAuthor = true;
			for(ProductAuthor pa: list) {
				if(pa.getIsFirstAuthor()==1) {
					isFirstAuthor = true;
					break;
				}
			}
		}
		if(isAuthor) {
			if(isFirstAuthor)
				authorFlag = 1;
			else
				authorFlag = 2;
		} else {
			authorFlag = 0;
		}
		return authorFlag;
	}

	public String[] getExterData(String groupId, int type){
		String[] data = new String[2];
		String allDiscipline = "";
		int expertNum = 0;
		expertNum = getAssignedExpertNumByGroupId(groupId, type);
		allDiscipline = getDisciplinesByGroupId(groupId);
		data[0] = new Integer(expertNum).toString();
		data[1] = allDiscipline;
		return data;
	}
	
	@Override
	public int getAssignedExpertNumByGroupId(String groupId, int type) {
		int expertNum = 0;
		String hql = "from Expert ex where ex.groups=:groups and ex.isRecommend=1 and ex.isReviewer=1 and ex.reviewerType=";
		if(type==0)
			hql+="0";
		else
			hql+="1";
		Groups group = dao.query(Groups.class, groupId);
		Map map = new HashMap();
		map.put("groups", group);
		expertNum = (int)dao.count(hql, map);
		return expertNum;
	}
	
	@Override
	public int getProductNumByGroupId(String groupId, int reviewType) {
		int productNum = 0;
		String hql = " from Product pr where pr.groups = :groups and ";
		if(reviewType==0)
			hql+="pr.status=4";
		else
			hql+="pr.status=6";
		Groups group = dao.query(Groups.class, groupId);
		Map map = new HashMap();
		map.put("groups", group);
		productNum = (int)dao.count(hql, map);
		return productNum;
	}
	@Override
	public String getDisciplinesByGroupId(String groupId) {
		String allDiscipline = "";
		Groups group = dao.query(Groups.class, groupId);
//		Set<Discipline> disciplines = group.obtainDisciplines();
//		if(!disciplines.isEmpty()) {
//			for(Discipline dis : disciplines) {
//				allDiscipline += "; "+dis.getName();
//			}
//			allDiscipline = allDiscipline.substring(1);
//		}
		allDiscipline = group.getDescription();
		return allDiscipline;
	}
		
    /**
     * 获取已分配专家
     * @param groupId 
     * @param reviewType 评审类型[0:初评 1:复评]
     * @return
     */
	public List<Expert> getAssignedExpertObject(String groupId, int reviewType) {
		List<Object[]> list =  new ArrayList();
		List<Expert> data = new ArrayList<Expert>();
		String hql1 = 
				" from Expert exp"
				+" where exp.groups =:groups and exp.isRecommend=1 and exp.isReviewer=1 and exp.reviewerType=0";
		String hql2 = 
				" from Expert exp"
				+" where exp.groups =:groups and exp.isRecommend=1 and exp.isReviewer=1 and exp.reviewerType=1";
		Groups groups = dao.query(Groups.class, groupId);
		Map map = new HashMap();
		map.put("groups", groups);
		list = dao.query(reviewType==0? hql1:hql2, map);
		if(!list.isEmpty()) {
			for (Object e : list) {
				data.add((Expert)e);
			}
		}
		return data;
	}
	
	@Override
	public List getAssignedExpert(String groupId, int reviewType) {
		List<Object[]> list =  new ArrayList();
		List<String[]> data = new ArrayList<String[]>();
		String[] item;
		//获取初评已分配专家信息
		String hql1 = 
				"select ex.id, ex.person.name, ex.person.agencyName, ex.specialityTitle, ex.discipline.name"
				+" from Expert ex"
				+" where ex.groups =:groups and ex.isRecommend = 1 and ex.isReviewer =1 and ex.reviewerType = 0";
		//获取复评已分配专家信息
		String hql2 = 
				"select ex.id, ex.person.name, ex.person.agencyName, ex.specialityTitle, ex.discipline.name, ex.isGroupLeader"
				+" from Expert ex"
				+" where ex.groups =:groups and ex.isRecommend = 1 and ex.isReviewer =1 and ex.reviewerType = 1";
		Groups group = dao.query(Groups.class, groupId);
		Map map = new HashMap();
		map.put("groups", group);
		list = dao.query(reviewType==0? hql1:hql2, map);
		for (Object[] p : list) {
			int len = p.length;
			String expertId = p[0].toString();
			item = new String[len+1];
			for (int i = 0; i < len; i++) {
				if (p[i] == null) {// 如果字段值为空，则以""替换
					item[i] = "";
				} else {// 如果字段值非空，则做进一步判断
					item[i] = p[i].toString();
				}
			}
			item[len] = new Integer(this.getIsAuthorByExpertId(expertId)).toString();
			data.add(item);// 将处理好的数据存入laData
		}
		return data;
	}
	@Override
	public int setChiefReviewProducts(String expertId, List<String> productId) {
		int flag = 1;
		try {
			Map map = new HashMap();
			Expert expert = dao.query(Expert.class, expertId);
			//先把原来分配的成果全部删除，再重新分配主审成果
			String deleteHql = "update Product pr set pr.chiefReviewer = null where pr.status>=6 and pr.chiefReviewer = :expert";
			String addHql = "update Product pr set pr.chiefReviewer = :expert where pr.status>=6 and pr.id in :productId";
			map.put("expert", expert);
			dao.execute(deleteHql, map);
			map.put("productId", productId);
			dao.execute(addHql, map);
		} catch (Exception e) {
			flag = 0;
		}
		return flag;
	}
	
	@Override
	public List getChiefReviewProducts(String expertId) {
		List<Object[]> list =  new ArrayList();
		List<String[]> data = new ArrayList<String[]>();
		String[] item;
		//当前专家主审成果
		String hql = 
				"select pr.id, pr.name"
				+" from Product pr where pr.status>=6 and pr.chiefReviewer = :expert";
		Expert expert = dao.query(Expert.class, expertId);
		Map map = new HashMap();
		map.put("expert", expert);
		list = dao.query(hql, map);
		for (Object[] ex : list) {
			item = new String[ex.length];
			for (int i = 0; i < ex.length; i++) {
				if (ex[i] == null) {// 如果字段值为空，则以""替换
					item[i] = "";
				} else {// 如果字段值非空，则做进一步判断
					item[i] = ex[i].toString();
				}
			}
			data.add(item);// 将处理好的数据存入laData
		}
		return data;
	}
	
	@Override
	public String getGroupLeaderId(String groupId) {
		List<Object[]> list =  new ArrayList();
		Groups group = dao.query(Groups.class, groupId);
		String groupLeaderId = null;
		String hql = "select ex.id"
				+" from Expert ex"
				+"where ex.groups =:groups and ex.isGroupLeader=1 and ex.isRecommend = 1 and ex.isReviewer =1 and ex.reviewerType = 0";
		Map map = new HashMap();
		map.put("groups", group);
		list = dao.query(hql, map);
		if(!list.isEmpty()) {
			groupLeaderId = (String)list.get(0)[0];
		}
		return groupLeaderId;
	}

	@Override
	public int addExpert(List<String> expertIds, int reviewType) {
		int flag = 1;
		if(expertIds!=null) {
			if(!expertIds.isEmpty()) {
				for(String expertId : expertIds) {
					try {
						Expert expert = dao.query(Expert.class, expertId);
						expert.setReviewer(reviewType);
						dao.modify(expert);
					} catch (Exception e) {
						flag = 0;
						break;
					}
				}
			}
		}
		return flag;
	}

	@Override
	public int deleteExpert(List<String> expertIds, int reviewType) {
		int flag = 1;
		if(expertIds!=null) {
			if(!expertIds.isEmpty()) {
				for(String expertId : expertIds) {
					try {
						Expert expert = dao.query(Expert.class, expertId);
						expert.cancelReviewer();
						dao.modify(expert);
					} catch (Exception e) {
						flag = 0;
						break;
					}
				}
			}
		}
		return flag;
	}

	@Override
	public int updateLeader(String leaderId) {
		int flag = 1;
		try {
			Expert expert = dao.query(Expert.class, leaderId);
			this.revokeLeader(expert.getGroups());
			expert.setIsGroupLeader(1);
			dao.modify(expert);
		} catch (Exception e) {
			flag = 0;
		}
		return flag;
	}
	/**
	 * 撤销该组的组长
	 * @param groups
	 */
	private void revokeLeader(Groups groups) {
		String upodateHql = 
				"update Expert ex set ex.isGroupLeader=0 " +
				"where ex.groups =:groups and ex.isGroupLeader=1 and ex.isRecommend = 1 and ex.isReviewer =1 and ex.reviewerType = 1";
		Map map = new HashMap();
		map.put("groups", groups);
		dao.execute(upodateHql, map);
	}

}
