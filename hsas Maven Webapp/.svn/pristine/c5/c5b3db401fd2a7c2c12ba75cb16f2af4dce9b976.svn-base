package csdc.service.imp;

import java.util.Date;
import java.util.List;
import java.util.Map;

import csdc.model.Account;
import csdc.model.Product;
import csdc.service.IFirstCheckService;

public class FirstCheckService extends BaseService implements IFirstCheckService {

	@Override
	public Product getProductById(String productId) {
		return dao.query(Product.class, productId);
	}

	

	@Override
	public String setFirstCheckResult(String productId, Map map) {
		Account account = (Account) map.get("account");
		Product product = getProductById(productId);
		String result = map.get("result").toString();
		String opinion = map.get("opinion").toString();
		
		//高校审核
		if(account.getType()==3){
			if(result.equals("1")){
				product.setUniversityAuditResult(1);
				product.setUniversityAuditOpinion("高校初审未通过！");
			} else if( result.equals("2")){
				product.setUniversityAuditResult(2);
				product.setUniversityAuditOpinion("高校初审通过！");

			}else if(result.equals("3")){
				product.setUniversityAuditOpinion(opinion);
				product.setUniversityAuditResult(0);
			}
		} else if(account.getType()==2){//社科联审核
			//能够走到这个流程表明，高校审核通过或为非高校申报成果
			if(result.endsWith("1")){
				product.setHsasApplyAuditResult(1);
				product.setHsasApplyAuditOpinion("省社科联初审未通过！");
			} else if(result.equals("2")){
				product.setHsasApplyAuditResult(2);
				product.setHsasApplyAuditOpinion("省社科联初审通过！");

			}else {
				product.setHsasApplyAuditResult(0);
				
			}
		}
		
		product.setUpdateDate(new Date());
		return dao.add(product);
	}



	@Override
	public String setFirstCheckRestults(List<String> productIds, Map map) {
		
		for(String productId :productIds){
			setFirstCheckResult(productId, map);
		}
		return "success";
	}

}
