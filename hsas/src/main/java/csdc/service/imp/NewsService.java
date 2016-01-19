package csdc.service.imp;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import csdc.model.Article;
import csdc.model.SystemOption;
import csdc.service.INewsService;

@Service
public class NewsService extends BaseService implements INewsService{

	
	@Override
	public Map  getArticles(String type, Integer num) {
		
		SimpleDateFormat  sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS");
		List artlist = new ArrayList();
		Map map = new HashMap();
		map.put("type", type);
		
		List<Article> list = dao.query("select a.id,a.title,a.createDate from Article a left join a.systemOption so where so.name =:type order by a.createDate desc", map, 0, num);
		map.remove("type");
		
		 /* List laData = new ArrayList();// 处理之后的列表数据
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
							item[i] = datestr;
						} else {// 如果字段非时间对象，则直接转化为字符串
							item[i] = o[i].toString();
						}
					}
				}
				laData.add(item);// 将处理好的数据存入laData
			}
		*/
		map.put(type, pageListDealWith(list));
		
		 JSONObject jsonObject = JSONObject.fromObject(map);
				
		return jsonObject;
	}

	@Override
	public Article getArticle(String articleId) {
		
		//hql.append("select a.id, a.title, a.editor, a.source, to_char(a.createdDate,'yyyy-mm-dd hh24:mi:ss') from Article a left join a.systemOption so where so.name = :type order by a.createdDate desc ");

		
		
		return  dao.query(Article.class, articleId);
	}

	@Override
	public String addArticle(Article article) {
		article.setViewCount(0);
		article.setCreateDate(new Date());
		return dao.add(article) ;
	}

	@Override
	public String modify(Article oldArticle, Article newArticle) {
		if(newArticle!=null){
			oldArticle.setAuthor(newArticle.getAuthor());
			oldArticle.setContent(newArticle.getContent());
			oldArticle.setCreateDate(new Date());
			oldArticle.setIsOpen(newArticle.getIsOpen());
			oldArticle.setSource(newArticle.getSource());
			oldArticle.setTitle(newArticle.getTitle());
			oldArticle.setViewCount(0);
		}
		
		dao.modify(oldArticle);
		return oldArticle.getId();
	}

	@Override
	public void delete(List<String> articleIds) {
		for(String id:articleIds){
			dao.delete(Article.class, id);
		}
	}

	@Override
	public SystemOption getSystemOptionById(String typeId) {
		
		return dao.query(SystemOption.class, typeId);
	}

}
