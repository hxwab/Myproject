package csdc.service;

import java.util.List;
import java.util.Map;

import csdc.model.Product;
import csdc.model.ProductReview;
import csdc.model.Reward;

public interface IFinalAuditService {
	
	/**
	 * 获取成果
	 * @param productId
	 * @return
	 */
	public Product getProduct(String productId);
	
	/**
	 * 获取成果的复评信息
	 * @param productId
	 * @return
	 */
	public List<ProductReview> getProductReview(String productId,int type);
	
	
	/**
	 * 获取成果的奖励信息
	 * @param prodcutId
	 * @return
	 */
	public Reward getReward(String prodcutId);
	
	/**
	 * 导出选择的成果
	 * @param productId
	 * @return
	 */
	public List<Object[]> getProductInfos(List<String> productId);
	
	
	public String  setFinalResult(String entityId,Map map);
		
	
}
