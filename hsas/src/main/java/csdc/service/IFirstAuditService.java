package csdc.service;

import java.util.List;
import java.util.Map;

import csdc.model.Account;
import csdc.model.Product;

public interface IFirstAuditService {
	
	public  Product  getProductById(String productId);
	
	public List getProductInfos(List<String> productId);	
	public String setFirstCheckResult(String productId,Account account, String result,String opnioin);

	public String setFirstCheckRestults(List<String> productIds ,Map map);
	
	public void delete(List<String> entityIds);
	
	/**
	 * 列表中添加额外信息
	 * @param laData
	 * @return
	 */
	public List addExternalInfo(List laData);
	
	/**
	 * 生成评审编号
	 * @param entityId
	 */
	public String generateAuditNumber(String entityId);
	
	public String getFilePath(String entityId);
	
	public String getFileName(String entityId);

	public String zipFile(List<String> entityIds);
	
	public boolean canApply(List<String> entityIds);
}
