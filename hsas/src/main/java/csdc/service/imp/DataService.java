package csdc.service.imp;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.stereotype.Service;

import csdc.service.IDataService;

@Service
public class DataService extends BaseService implements IDataService {

	
	
	/*
	 
//String hql = "select count(distinct p.author)  from ProductAuthor pa left join pa.product p left join p.author person where p.applyYear=2016";

		//String hql = "select count(distinct p.author) ,sum(case when p.status=2 THEN 1 else null end) ,sum(case when date_add(person.birthday,INTERVAL 40 YEAR)>=p.createDate THEN 1 else null end) from ProductAuthor pa left join pa.product p left join p.author person where p.applyYear=2016";
		
		
		// hql = "select count(distinct p.author) ,sum(case when p.status=2 THEN 1 else null end) ,sum(case when timestampdiff(year,person.birthday,p.createDate)<=40 AND (p.rewardLevel=1 or p.rewardLevel=2 or p.rewardLevel=3 or p.rewardLevel=4 ) THEN 1 else null end),sum(case when timestampdiff(year,pa.person.birthday,p.createDate)<=40 THEN 1 else null end) from ProductAuthor pa left join pa.product p left join p.author person where p.applyYear=2016 group by p.id ";
		
		//统计一作人数及40以上获奖人数
		//String hql = "select count(distinct p.author) ,sum(case when p.status=2 THEN 1 else null end),sum(case when timestampdiff(year,person.birthday,p.createDate)<=40 AND (p.rewardLevel=1 or p.rewardLevel=2 or p.rewardLevel=3 or p.rewardLevel=4 ) THEN 1 else null end)  from Product p left join p.author person where p.applyYear=2016";

		//统计所有作者及40岁以上获奖人数
		//String hql = "select count(distinct pa.person) ,sum(case when p.status=2 THEN 1 else null end),sum(case when timestampdiff(year,pa.person.birthday,p.createDate)<=40 AND (p.rewardLevel=1 or p.rewardLevel=2 or p.rewardLevel=3 or p.rewardLevel=4 ) THEN 1 else null end)  from ProductAuthor pa left join pa.product p  where p.applyYear=2016";

		//分开查
		//String hql = "select p.id,count(distinct p.author) ,sum(case when p.status=2 THEN 1 else null end) ,sum(case when timestampdiff(year,person.birthday,p.createDate)<=40 AND (p.rewardLevel=1 or p.rewardLevel=2 or p.rewardLevel=3 or p.rewardLevel=4 ) THEN 1 else null end),sum(case when timestampdiff(year,pa.person.birthday,p.createDate)<=40 THEN 1 else null end) from Product p left join p.author person,ProductAuthor pa  where p.id=pa.product.id and  p.applyYear=2016";
		//String hql = "select count(distinct p.author) ,sum(case when p.status=2 THEN 1 else null end) ,sum(case when timestampdiff(year,person.birthday,p.createDate)<=40 AND (p.rewardLevel=1 or p.rewardLevel=2 or p.rewardLevel=3 or p.rewardLevel=4 ) THEN 1 else null end),sum(case when timestampdiff(year,pa.person.birthday,p.createDate)<=40 THEN 1 else null end) from Product p left join p.author person,ProductAuthor pa  where p.id=pa.product.id and  p.applyYear=2016 group by p.id";

		
		//初评情况
				String hql = "select p.groupName , sum(case when p.type='著作' THEN 1 else null end),sum(case when p.type='系列论文' and p.researchType ='基础类'THEN 1 else null end),sum(case when p.type='系列论文' and p.researchType ='应用对策类'THEN 1 else null end)," +
						"sum(case when p.type='单篇论文' and p.researchType ='基础类'THEN 1 else null end),sum(case when p.type='单篇论文' and p.researchType ='应用对策类'THEN 1 else null end)," +
						"sum(case when p.type='调研报告' and p.researchType ='基础类'THEN 1 else null end),sum(case when p.type='调研报告' and p.researchType ='应用对策类'THEN 1 else null end) ," +
						"count(p.id)"+
						"from Product p  where p.status >=4 and p.applyYear=2016  group by p.groupName ";

		/*
		 * 
		//统计一作人数及40以上获奖人数
		String hql = "select count(distinct p.author) ,sum(case when p.status=2 THEN 1 else null end),sum(case when timestampdiff(year,person.birthday,p.createDate)<=40 AND (p.rewardLevel=1 or p.rewardLevel=2 or p.rewardLevel=3 or p.rewardLevel=4 ) THEN 1 else null end)  from Product p left join p.author person where p.applyYear=2016";
		
		//统计所有作者及40岁以上获奖人数
		String hql = "select count(distinct pa.person) ,sum(case when p.status=2 THEN 1 else null end),sum(case when timestampdiff(year,pa.person.birthday,p.createDate)<=40 AND (p.rewardLevel=1 or p.rewardLevel=2 or p.rewardLevel=3 or p.rewardLevel=4 ) THEN 1 else null end)  from ProductAuthor pa left join pa.product p  where p.applyYear=2016";

		
		 //申报情况
		//String hql = "select sum(case when p.status=4 THEN 1 else null end),sum(case when p.status=6 THEN 1 else null end),sum(case when p.status=7 THEN 1 else null end) from Product p  where p.applyYear=2016  ";
		
		//复评情况
		String hql = "select p.groupName , sum(case when p.type='著作' THEN 1 else null end),sum(case when p.type='系列论文' and p.researchType ='基础类'THEN 1 else null end),sum(case when p.type='系列论文' and p.researchType ='应用对策类'THEN 1 else null end)," +
				"sum(case when p.type='单篇论文' and p.researchType ='基础类'THEN 1 else null end),sum(case when p.type='单篇论文' and p.researchType ='应用对策类'THEN 1 else null end)," +
				"sum(case when p.type='调研报告' and p.researchType ='基础类'THEN 1 else null end),sum(case when p.type='调研报告' and p.researchType ='应用对策类'THEN 1 else null end) " +
				"from Product p  where p.status =6 and p.applyYear=2016  group by p.groupName ";
				
				//合计
		String hql = "select '合计' , sum(case when p.type='著作' THEN 1 else null end),sum(case when p.type='系列论文' and p.researchType ='基础类'THEN 1 else null end),sum(case when p.type='系列论文' and p.researchType ='应用对策类'THEN 1 else null end)," +
				"sum(case when p.type='单篇论文' and p.researchType ='基础类'THEN 1 else null end),sum(case when p.type='单篇论文' and p.researchType ='应用对策类'THEN 1 else null end)," +
				"sum(case when p.type='调研报告' and p.researchType ='基础类'THEN 1 else null end),sum(case when p.type='调研报告' and p.researchType ='应用对策类'THEN 1 else null end) " +
				"from Product p  where p.status =6 and p.applyYear=2016  ";
		//初评情况
		String hql = "select p.groupName , sum(case when p.type='著作' THEN 1 else null end),sum(case when p.type='系列论文' and p.researchType ='基础类'THEN 1 else null end),sum(case when p.type='系列论文' and p.researchType ='应用对策类'THEN 1 else null end)," +
				"sum(case when p.type='单篇论文' and p.researchType ='基础类'THEN 1 else null end),sum(case when p.type='单篇论文' and p.researchType ='应用对策类'THEN 1 else null end)," +
				"sum(case when p.type='调研报告' and p.researchType ='基础类'THEN 1 else null end),sum(case when p.type='调研报告' and p.researchType ='应用对策类'THEN 1 else null end) " +
				"from Product p  where p.status =4 and p.applyYear=2016  group by p.groupName ";

		//合计
		String hql = "select '合计' , sum(case when p.type='著作' THEN 1 else null end),sum(case when p.type='系列论文' and p.researchType ='基础类'THEN 1 else null end),sum(case when p.type='系列论文' and p.researchType ='应用对策类'THEN 1 else null end)," +
				"sum(case when p.type='单篇论文' and p.researchType ='基础类'THEN 1 else null end),sum(case when p.type='单篇论文' and p.researchType ='应用对策类'THEN 1 else null end)," +
				"sum(case when p.type='调研报告' and p.researchType ='基础类'THEN 1 else null end),sum(case when p.type='调研报告' and p.researchType ='应用对策类'THEN 1 else null end) " +
				"from Product p  where p.status =4 and p.applyYear=2016  ";
		//单位申报总数
		String hql = "select  p.agencyName,sum(case when p.type='著作' THEN 1 else null end),sum(case when p.type='系列论文' THEN 1 else null end),"+
				"sum(case when p.type='单篇论文' THEN 1 else null end),sum(case when p.type='调研报告' THEN 1 else null end),count(p.id)"+
				"from Product p  where p.applyYear=2016 group by p.agencyName";
		//奖项分布
		String hql = "select  p.agencyName,sum(case when p.type='著作' and p.rewardLevel=2 THEN 1 else null end),sum(case when p.type='著作' and p.rewardLevel=3 THEN 1 else null end),sum(case when p.type='著作' and p.rewardLevel=4 THEN 1 else null end)," +
				"sum(case when p.type='系列论文' and p.rewardLevel=2 THEN 1 else null end),sum(case when p.type='系列论文' and p.rewardLevel=3 THEN 1 else null end),sum(case when p.type='系列论文' and p.rewardLevel=4 THEN 1 else null end),"+
				"sum(case when p.type='调研报告' and  p.rewardLevel=2 THEN 1 else null end),sum(case when p.type='调研报告' and p.rewardLevel=3 THEN 1 else null end),sum(case when p.type='调研报告' and  p.rewardLevel=4 THEN 1 else null end)," +
				"sum(case when p.rewardLevel=2  or p.rewardLevel=3 or p.rewardLevel=4 THEN 1 else null end)"+
				"from Product p  where p.applyYear=2016 group by p.agencyName";

	 */
	
	
	
	
	@Override
	public List calcApplyAuthor(String year) {
		Map map = new HashMap();
		map.put("applyYear", year);
		
		//统计一作人数及40以上获奖人数
		String hql = "select count(distinct p.author) ,sum(case when timestampdiff(year,person.birthday,p.createDate)<=40 AND (p.rewardLevel=1 or p.rewardLevel=2 or p.rewardLevel=3 or p.rewardLevel=4 ) THEN 1 else null end)  from Product p left join p.author person where p.applyYear=:applyYear";

		//统计所有作者及40岁以上获奖人数
		String hql1= "select count(distinct pa.person) ,sum(case when timestampdiff(year,pa.person.birthday,p.createDate)<=40 AND (p.rewardLevel=1 or p.rewardLevel=2 or p.rewardLevel=3 or p.rewardLevel=4 ) THEN 1 else null end)  from ProductAuthor pa left join pa.product p  where p.applyYear=:applyYear";

		List list =dao.query(hql);
		list.addAll(dao.query(hql1, map));

		return  list;
	}

	@Override
	public List calcApplyProduct(String year) {
		
		String hql = "select sum(case when p.status>=4 THEN 1 else null end),sum(case when p.status>=6 THEN 1 else null end),sum(case when p.status>=7 THEN 1 else null end) from Product p  where p.applyYear=:applyYear  ";
		Map map = new HashMap();
		map.put("applyYear", year);
		return dao.query(hql, map);
	}

	@Override
	public List calc1ReviewProduct(String year) {
		Map map = new HashMap();
		map.put("applyYear", year);
		//初评情况
		String hql = "select p.groupName , sum(case when p.type='著作' THEN 1 else null end),sum(case when p.type='系列论文' and p.researchType ='基础类'THEN 1 else null end),sum(case when p.type='系列论文' and p.researchType ='应用对策类'THEN 1 else null end)," +
						"sum(case when p.type='单篇论文' and p.researchType ='基础类'THEN 1 else null end),sum(case when p.type='单篇论文' and p.researchType ='应用对策类'THEN 1 else null end)," +
						"sum(case when p.type='调研报告' and p.researchType ='基础类'THEN 1 else null end),sum(case when p.type='调研报告' and p.researchType ='应用对策类'THEN 1 else null end), " +
						"sum(case when p.type='单篇论文' or p.type ='系列论文' THEN 1 else null end),sum(case when p.type='调研报告' THEN 1 else null end) " +
						"from Product p  where p.status >=4 and p.applyYear=:applyYear  group by p.groupName ";

		//合计
		String hql1 = "select '合计' , sum(case when p.type='著作' THEN 1 else null end),sum(case when p.type='系列论文' and p.researchType ='基础类'THEN 1 else null end),sum(case when p.type='系列论文' and p.researchType ='应用对策类'THEN 1 else null end)," +
						"sum(case when p.type='单篇论文' and p.researchType ='基础类'THEN 1 else null end),sum(case when p.type='单篇论文' and p.researchType ='应用对策类'THEN 1 else null end)," +
						"sum(case when p.type='调研报告' and p.researchType ='基础类'THEN 1 else null end),sum(case when p.type='调研报告' and p.researchType ='应用对策类'THEN 1 else null end) ," +
						"sum(case when p.type='单篇论文' or p.type ='系列论文' THEN 1 else null end),sum(case when p.type='调研报告' THEN 1 else null end) " +
						"from Product p  where p.status >=4 and p.applyYear=:applyYear ";
		List  list = dao.query(hql, map);
		list.addAll(dao.query(hql1, map));
		
		return list;
	}

	@Override
	public List calc2ReviewProduct(String year) {
		Map map = new HashMap();
		map.put("applyYear", year);
		String hql = "select p.groupName , sum(case when p.type='著作' THEN 1 else null end),sum(case when p.type='系列论文' and p.researchType ='基础类'THEN 1 else null end),sum(case when p.type='系列论文' and p.researchType ='应用对策类'THEN 1 else null end)," +
				"sum(case when p.type='单篇论文' and p.researchType ='基础类'THEN 1 else null end),sum(case when p.type='单篇论文' and p.researchType ='应用对策类'THEN 1 else null end)," +
				"sum(case when p.type='调研报告' and p.researchType ='基础类'THEN 1 else null end),sum(case when p.type='调研报告' and p.researchType ='应用对策类'THEN 1 else null end) ," +
				"sum(case when p.type='单篇论文' or p.type ='系列论文' THEN 1 else null end),sum(case when p.type='调研报告' THEN 1 else null end) " +
				"from Product p  where p.status >=6 and p.applyYear=:applyYear group by p.groupName ";
				
				//合计
		String hql1 = "select '合计' , sum(case when p.type='著作' THEN 1 else null end),sum(case when p.type='系列论文' and p.researchType ='基础类'THEN 1 else null end),sum(case when p.type='系列论文' and p.researchType ='应用对策类'THEN 1 else null end)," +
				"sum(case when p.type='单篇论文' and p.researchType ='基础类'THEN 1 else null end),sum(case when p.type='单篇论文' and p.researchType ='应用对策类'THEN 1 else null end)," +
				"sum(case when p.type='调研报告' and p.researchType ='基础类'THEN 1 else null end),sum(case when p.type='调研报告' and p.researchType ='应用对策类'THEN 1 else null end), " +
				"sum(case when p.type='单篇论文' or p.type ='系列论文' THEN 1 else null end),sum(case when p.type='调研报告' THEN 1 else null end) " +
				"from Product p  where p.status >=6 and p.applyYear=:applyYear  ";
		List  list = dao.query(hql, map);
		list.addAll(dao.query(hql1, map));
		
		return list;
	}

	
	@Override
	public List calcReward4Agency(String year) {
		Map map = new HashMap();
		map.put("applyYear", year);
		//奖项分布
		String hql = "select  p.agencyName,sum(case when p.type='著作' and p.rewardLevel=2 THEN 1 else null end),sum(case when p.type='著作' and p.rewardLevel=3 THEN 1 else null end),sum(case when p.type='著作' and p.rewardLevel=4 THEN 1 else null end)," +
				"sum(case when p.type='系列论文' or p.type='单篇论文'  and p.rewardLevel=2 THEN 1 else null end),sum(case when p.type='系列论文' or p.type='单篇论文' and p.rewardLevel=3 THEN 1 else null end),sum(case when p.type='系列论文' or p.type='单篇论文' and p.rewardLevel=4 THEN 1 else null end),"+
				"sum(case when p.type='调研报告' and  p.rewardLevel=2 THEN 1 else null end),sum(case when p.type='调研报告' and p.rewardLevel=3 THEN 1 else null end),sum(case when p.type='调研报告' and  p.rewardLevel=4 THEN 1 else null end)," +
				"sum(case when p.rewardLevel=2  or p.rewardLevel=3 or p.rewardLevel=4 THEN 1 else null end)"+
				"from Product p  where p.applyYear=:applyYear group by p.agencyName";
		return dao.query(hql, map);
	}

	@Override
	public List calcProduct4Agency(String year) {
		Map map = new HashMap();
		map.put("applyYear", year);
		//单位申报总数
		String hql = "select  p.agencyName,sum(case when p.type='著作' THEN 1 else null end),sum(case when p.type='系列论文' THEN 1 else null end),"+
		"sum(case when p.type='单篇论文' THEN 1 else null end),sum(case when p.type='调研报告' THEN 1 else null end),count(p.id)"+
		"from Product p  where p.applyYear=:applyYear group by p.agencyName";
		return dao.query(hql, map);
	}
	
	
	@Override
	public List calcService(int type, String year) {
		
		List list = null;
		
		switch (type) {
		
		case 1 :
			list = calcApplyProduct(year);
			break;
		case 2 :
			list = calc1ReviewProduct(year);
			break;
		case 3 :
			list = calc2ReviewProduct(year);
			break;
		case 4 :
			list = calcProduct4Agency(year);
			break;

		case 5 :
			list = addExternalInfo(calcApplyAuthor(year));
			break;
		case 6 :
			list = calcReward4Agency(year);
			break;

		default:
			break;
		}
		
		return list;
	}

	
	
	//处理作者统计信息
	private List addExternalInfo(List laData) {
		
		
		DecimalFormat df = (DecimalFormat)NumberFormat.getInstance(Locale.CHINESE); 
	    df.applyPattern("00.00%"); 
	    
		List newLaData = new ArrayList();
		String[] nItem;
		
		Object data1 = laData.get(0);
		Object data2 = laData.get(1);
		String[] item1 = (String[]) data1;
		String[] item2 = (String[]) data2;
		String [] item = new String[item1.length+item2.length+2];
		
		item[0] = item1[0];
		item[1] = item1[1];
		item[2] = df.format(Double.parseDouble(item1[1])/Double.parseDouble(item1[0]));
		item[3] = item2[0];
		item[4] = item2[1];
		item[5] = df.format(Double.parseDouble(item2[1])/Double.parseDouble(item2[0]));	
		newLaData.add(item);
		return newLaData;
	}
	

}
