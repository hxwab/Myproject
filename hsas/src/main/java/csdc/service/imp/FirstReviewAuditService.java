package csdc.service.imp;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

import csdc.model.Product;
import csdc.model.ProductReview;
import csdc.service.IFirstReviewAuditService;

@Service
public class FirstReviewAuditService extends BaseService implements
		IFirstReviewAuditService {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void addAuditRsult(List<String> entityIds, int auditResult) {
		StringBuffer hql = new StringBuffer();
		hql.append("update Product pr set pr.hsasReviewAuditResult = :auditResult, pr.updateDate = :updateDate");
		if(auditResult==2) {
			hql.append(", pr.status = 6");
		}
		hql.append(" where pr.id in :productIds");
		Map map = new HashMap();
		map.put("auditResult", auditResult);
		map.put("updateDate", new Date());
		map.put("productIds", entityIds);
		dao.execute(hql.toString(), map);
	}

	@Override
	public void audit(int scoreLine) {
		String hql = "update Product pr set pr.hsasReviewAuditResult=2, pr.status=6, pr.updateDate = :updateDate" +
				" where pr.status=5 and pr.applyYear=:currentYear and pr.averageScore>=:scoreLine";
		Map map = new HashMap();
		map.put("updateDate", new Date());
		String currentYear = new SimpleDateFormat("yyyy").format(new Date());
		map.put("currentYear", currentYear);
		map.put("scoreLine",scoreLine);
		dao.execute(hql, map);
	}

	@Override
	public int getProductStatus(String entityId) {
		Product product = dao.query(Product.class, entityId);
		if(product==null) {
			throw new RuntimeException("成果不存在");
		}
		return product.getStatus();
	}

	@Override
	public int getAverageScore(String entityId) {
		Product product = dao.query(Product.class, entityId);
		if(product==null) {
			throw new RuntimeException("成果不存在");
		}
		int score = product.getAverageScore();
		return score;
	}

	@Override
	public int getReviewType(String productId) {
		int productType = 0;
		Product product = dao.query(Product.class, productId);
		String type = product.getType();
		String researchType = product.getResearchType();
		if(type.equals("著作")) {
			productType = 3;
		} else {
			if(researchType.equals("基础类"))
				productType = 1;
			else
				productType = 2;
		}
		return productType;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String[]> getReviewData(String productId) {
		List<String[]> reviewData = new ArrayList<String[]>();
		String hql = "from ProductReview pr where pr.status=2 and pr.type=0 and pr.product.id=:productId";
		Map map = new HashMap();
		map.put("productId", productId);
		List<ProductReview> reviews = dao.query(hql, map);
		for(ProductReview review: reviews) {
			String[] item = new String[8];
			item[0] = review.getExpert().getPerson().getName();
			item[2] = review.getOpinion();
			Integer score1 = review.getCreativityScore();
			Integer score2 = review.getResearchScore();
			Integer score3 = review.getJournalScore();
			Integer score4 = review.getQuoteScore();
			Integer score5 = review.getSocialEffectScore()!=null?review.getSocialEffectScore():0;
			Integer totalScore = score1+score2+score3+score4+score5;
			item[1] = totalScore.toString();
			item[3] = score1.toString();
			item[4] = score2.toString();
			item[5] = score3.toString();
			item[6] = score4.toString();
			item[7] = score5.toString();
			reviewData.add(item);
		}
		return reviewData;
	}

}