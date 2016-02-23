package csdc.service.imp;

import java.io.Serializable;
import java.text.DecimalFormat;

import java.util.Collection;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import csdc.dao.HibernateBaseDao;
import csdc.dao.IBaseDao;
import csdc.dao.SystemOptionDao;
import csdc.model.SystemOption;
import csdc.service.IBaseService;

@Service
	
public class BaseService implements IBaseService {
	@Autowired
	private IBaseDao baseDao;
	
	@Autowired
	protected HibernateBaseDao dao;
	
	@Autowired
	protected SystemOptionDao soDao;
	
	public String add(Object entity) {
		return this.baseDao.add(entity);
	}
	
	@SuppressWarnings("unchecked")
	public void addOrModify(Collection entity) {
		this.baseDao.addOrModify(entity);
	}

	public void delete(Object entity) {
		this.baseDao.delete(entity);
	}

	@SuppressWarnings("unchecked")
	public void delete(Class entityClass, Serializable id) {
		this.baseDao.delete(entityClass, id);
	}

	public void modify(Object entity) {
		this.baseDao.modify(entity);
	}

	@SuppressWarnings("unchecked")
	public Object query(Class entityClass, Serializable id) {
		return this.baseDao.query(entityClass, id);
	}

	@SuppressWarnings("unchecked")
	public List query(String queryString) {
		return this.baseDao.query(queryString);
	}
	
	
	@SuppressWarnings("unchecked")
	public List query(String queryString, Map parMap) {
		return this.baseDao.query(queryString, parMap);
	}
	public String accquireFileSize(long fileLength) {
		DecimalFormat df = new DecimalFormat("#.00");
		String fileSizeString = "";
		if (fileLength < 1024) {
			fileSizeString = df.format((double) fileLength) + "B";
		} else if (fileLength < 1048576) {
			fileSizeString = df.format((double) fileLength / 1024) + "K";
		} else if (fileLength < 1073741824) {
			fileSizeString = df.format((double) fileLength / 1048576) + "M";
		} else {
			fileSizeString = df.format((double) fileLength / 1073741824) + "G";
		}
		return fileSizeString;
	}

	
	/**
	 * 将DATE格式化后转化为string 类型
	 * @param date
	 * @return
	 */
	public String Date2String(Date date){
		
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS");// 时间格式化对象
		String datestr = dateformat.format(date);
		
		return datestr;
	}
	
	
	public List  pageListDealWith(List  list) {
	    List laData = new ArrayList();// 处理之后的列表数据
		Object[] o;// 缓存查询结果的一行
		String[] item;// 缓存查询结果一行中的每一字段
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS");// 时间格式化对象
		String datestr;// 格式化之后的时间字符串
		
		// 遍历初始查询列表数据，按照指定格式，格式化其中的时间字段，其它字段转化为字符串
		for (Object p : list) {
			o = (Object[]) p;
			item = new String[o.length];
			for (int i = 0; i < o.length; i++) {
				if (o[i] == null) {// 如果字段值为空，则以""替换
					item[i] = "";
				} else {// 如果字段值非空，则做进一步判断
					if (o[i] instanceof Date) {// 如果字段为时间对象，则按照子类定义的时间格式格式化
						datestr = dateformat.format((Date) o[i]);
						item[i] = datestr.substring(0, datestr.length()-3);
					} else {// 如果字段非时间对象，则直接转化为字符串
						item[i] = o[i].toString();
					}
				}
			}
			laData.add(item);// 将处理好的数据存入laData
		}
		return laData;
	}
	
	/**
	 * 根据指定standard, code获取其直接子节点, map的key为id
	 * @param standard
	 * @param code
	 * @return	map (id与name的映射)
	 */
	public Map<String,String> getSystemOptionMap(String standard, String code){
		Map<String,String> map = new HashMap<String, String>();
		SystemOption father = soDao.query(standard, code);
		if (father != null) {
			List<SystemOption> systemOptionList = soDao.queryChildren(father);
			if(systemOptionList.size() > 0){
				map = new LinkedHashMap<String, String>();
				for (SystemOption systemOption : systemOptionList) {
					map.put(systemOption.getId(), systemOption.getName());
				}
			}
		}
		return map;
	}


}
