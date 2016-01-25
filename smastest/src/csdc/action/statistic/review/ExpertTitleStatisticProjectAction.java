package csdc.action.statistic.review;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.struts2.components.If;

public class ExpertTitleStatisticProjectAction extends ReviewStatisticAction {
	private static final long serialVersionUID = 1L;
	private static final String HQL4General = "select p.id, p.projectName, count(distinct pr.reviewer.id), 'experttitle' from ProjectApplicationReview pr, Expert e left join pr.project p where pr.type = 'general' and e.id = pr.reviewer.id and pr.year = :defaultYear ";
	private static final String HQL4INSTITUTE = "select p.id, p.projectName, count(distinct pr.reviewer.id), 'experttitle' from ProjectApplicationReview pr, Expert e left join pr.project p where pr.type = 'instp' and e.id = pr.reviewer.id and pr.year = :defaultYear ";
	private static final String HQLG = " group by p.id, p.projectName ";
	private static final String PAGENAME = "statisticPETPage";
	private static final String PAGE_BUFFER_ID = "p.id";//缓存id
	private static final String column[] = {
		"p.projectName",
		"count(distinct pr.reviewer.id)"
	};// 排序用的列
	
	/**
	 * 奖励分组匹配中专家职称统计列表 
	 */
	@Override
	public Object[] simpleSearchCondition() {
		StringBuffer hql = new StringBuffer();
		if(listType == 1){//一般项目
			hql.append(HQL4General);
		}else if(listType == 2){//基地项目
			hql.append(HQL4INSTITUTE);
		}
		Map map = new HashMap();
		map.put("defaultYear", session.get("defaultYear"));//从session中获取当前显示年份
		if (keyword == null) {// 预处理关键字
			keyword = "";
		} else {
			keyword = keyword.toLowerCase();
		}
		
		// 处理检索参数
		map.put("keyword", "%" + keyword + "%");
		// 拼接检索条件
		hql.append(" and ");
		if (searchType == 1) {// 按分组名称检索
			hql.append(" LOWER(p.projectName) like :keyword ");
		} else {// 按上述字段检索
			hql.append(" (LOWER(p.projectName) like :keyword) ");
		}			
		initConfig();//为各评审参数赋值
//		hql.append(isContainP == 0 ? " and pr.priority <= " + projectExpertNumber + " ": " ");
		hql.append(HQLG);
		
		// 调用初级检索功能
		return new Object[]{
			hql.toString(),
			map,
			0,
			null,
			null
		};
	}

	/**
	 * 处理列表数据，如有需要，各模块可以重写此方法，本方法默认仅格式化时间。
	 */
	public void pageListDealWith() {
		// 处理之后的列表数据
		laData = new ArrayList();
		Object[] item;// 缓存查询结果一行
		SimpleDateFormat dateformat = new SimpleDateFormat(dateFormat());// 时间格式化对象
		String datestr;// 格式化之后的时间字符串		
		// 遍历初始查询列表数据，按照指定格式，格式化其中的时间字段，其它字段转化为字符串
		for (Object p : pageList) {
			item = (Object[]) p;
			for (int i = 0; i < item.length; i++) {
				if (item[i] == null) {// 如果字段值为空，则以""替换
					item[i] = "";
				} else {// 如果字段值非空，则做进一步判断
					if (item[i] instanceof Date) {// 如果字段为时间对象，则按照子类定义的时间格式格式化
						datestr = dateformat.format((Date) item[i]);
						item[i] = datestr;
					}
				}
			}
			@SuppressWarnings("unchecked")
			List<Object[]> expertTitles = baseService.query("select e.isKeyProjectDirector," +//是否重大项目负责人
					" e.isYangtzeScholar," +//是否长江学者
					" e.awardMoeYangtse," +//长江学者奖励计划
					" e.awardNsfcYoung," +//国家杰出青年科学基金
					" e.awardNationalContribution," +//国家有突出贡献中青年专家
					" e.awardGlobalThousand," +//海外高层次人才引进计划（千人计划）
					" e.awardNationalNcentury," +//国家新世纪百千万人才工程
					" e.awardMoeTcentury," +//教育部跨世纪优秀人才培养计划
					" e.awardMoeNcentury," +//教育部新世纪优秀人才支持计划
					" e.awardNationalDoctor," + //全国百篇优秀博士论文获得者
					" e.awardMoeTeacher," +//教育部高等学校优秀青年教师教学科研奖励计划（高校青年教师奖）
					" e.awardLocalContribution," +//地方有突出贡献中青年专家
					" e.awardMoeSocial," +//教育部社会科学委员会委员
					" e.awardStateDegree" +//国务院学位委员会评议组成员
					" from ProjectApplicationReview pr, Expert e where e.id = pr.reviewer.id and pr.project.id = ?", item[0].toString());
			HashMap<String, Integer> titleStatic = new HashMap<String, Integer>();
			for(int i = 0; i < expertTitles.size(); i++) {
				Object[] exTitle = expertTitles.get(i);
				if(exTitle[0] != null && (Integer) exTitle[0] == 1) {
					if(titleStatic.get("重大项目负责人") == null) {
						titleStatic.put("重大项目负责人", 1);
					} else {
						titleStatic.put("重大项目负责人", titleStatic.get("重大项目负责人") + 1);
					}
				}
				if(exTitle[1] != null && (Integer) exTitle[1] == 1) {
					if(titleStatic.get("长江学者") == null) {
						titleStatic.put("长江学者", 1);
					} else {
						titleStatic.put("长江学者", titleStatic.get("长江学者") + 1);
					}
				}
				for(int j = 2; j < exTitle.length; j++) {
					if(exTitle[j] == null) continue;
					else {
						if(titleStatic.get((String) exTitle[j]) == null) {
							titleStatic.put((String) exTitle[j], 1);
						} else {
							titleStatic.put((String) exTitle[j], titleStatic.get((String) exTitle[j]) + 1);
						}
					}
				}
			}
			StringBuffer tempB = new StringBuffer();
			Set<Entry<String, Integer>> entries = titleStatic.entrySet();
			for(Entry<String, Integer> entry : entries) {
				tempB.append(entry.getKey() + ": " + entry.getValue() + "; ");
			}
			if(tempB.length() > 2) {
				item[3] = tempB.substring(0, tempB.length() - 2);
			} else {
				item[3] = "";
			}
			laData.add(item);// 将处理好的数据存入laData
		}
	}	
	
	@Override
	public String[] column() {
		return column;
	}
	@Override
	public String pageBufferId() {
		return PAGE_BUFFER_ID;
	}
	@Override
	public String pageName() {
		return PAGENAME;
	}
	
}
