package csdc.service.imp;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import csdc.model.Account;
import csdc.model.Product;
import csdc.model.ProductReview;
import csdc.model.Reward;
import csdc.service.IFinalAuditService;

@Service
public class FinalAuditService extends BaseService implements IFinalAuditService{

	
	@Override
	public Product getProduct(String productId) {
		return dao.query(Product.class, productId);
	}

	@Override
	public List<ProductReview> getProductReview(String productId,int type) {
		Map map = new HashMap();
		map.put("id", productId);
		map.put("type", type);
		String hql = "select pr from ProductReview pr left join pr.product p where p.id=:id and pr.type=:type";
		return dao.query(hql,map);
	}

	@Override
	public Reward getReward(String productId) {
		Map map = new HashMap();
		map.put("id", productId);
		String hql = "select r from Product p left join p.reward r where p.id=:id ";
		return (Reward) dao.queryUnique(hql, map);
	}

	@Override
	public List<Object[]> getProductInfos(List<String> productId) {
		
		Map map = new HashMap();
		List<Object[]> list= new ArrayList<Object[]>();
		Object[] o;
		String hql="select p.id,p.number,p.name,p.authorName,p.agencyName,p.researchType,r.year,r.level,r.type ,r.bonus from Product p left join p.reward r where p.id=:id order by p.number" ;

		for(String id:productId){
			map.put("id", id);
			list.addAll(dao.query(hql, map));
			map.clear();
		}
		
		return list;
	}

	@Override
	public String setFinalResult(String entityId, Map map) {
		
		Account account = (Account) map.get("account");
		Product product = getProductById(entityId);
		String result = map.get("result").toString();
		String opinion = map.get("opinion").toString();
				
		//高级管理员终审
		if(account.getType()==1){
			if(result.equals("1")){
				product.setHsasFinalAuditResult(1);
				product.setHsasFinalAuditOpinion("不同意！"+opinion);
			} else if( result.equals("2")){
				product.setHsasFinalAuditResult(2);
				product.setHsasFinalAuditOpinion("同意！"+opinion);

			}else{
				product.setHsasFinalAuditResult(0);
		
			}
			product.setStatus(7);
		} 
			
		
		product.setUpdateDate(new Date());
		dao.modify(product);
		return "success";
		
		
	}

	public Product getProductById(String productId) {
		return dao.query(Product.class, productId);
	}
}
