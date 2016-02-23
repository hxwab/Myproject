package csdc.action.product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionContext;

import csdc.action.BaseAction;
import csdc.model.Account;
import csdc.model.Product;
import csdc.service.IFirstCheckService;
import csdc.tool.bean.LoginInfo;
import csdc.tool.info.GlobalInfo;

/**
 * 初审（非高校直接由省社科联审核；高校先由高校审核，高校审核通过后再由社科联审核）
 * 1、高校只能审核本校的申报成果（根据高校管理员的账户所属机构找出该机构的所有申报成果）
 * 2、社科联管理员可以审核所有申报成果（非高校+高校审核通过部分）
 * 3、高校管理员3，社科联管理员2
 * 
 * 
 * @author huangxw
 *
 */

@Component
@Scope(value="prototype")
public class FirstCheck extends BaseAction {
	
	private Account account;
	
	private  static final String HQL = "select p.id ,p.name,p.authorName,p.agencyName from Product p where 1=1";
	
	private Product product;
	
	private String result;
	
	private String opinion;
	
	private List<String> opinions;
	
	@Autowired
	private IFirstCheckService firstCheckService;
	
	
	public String toFirstCheck(){
		
		return SUCCESS;
	}
	
	public String firstCheck(){
		LoginInfo loginer =  (LoginInfo) ActionContext.getContext().getSession().get(GlobalInfo.LOGINER);
		Map map = new HashMap();
		account = loginer.getAccount();
		map.put("account", account);
		map.put("result", result);
		map.put("opinion", opinion);
		firstCheckService.setFirstCheckRestults(entityIds, map);
		return SUCCESS;
	}
	
	

	@Override
	public String toAdd() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String add() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delete() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toModify() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String modify() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String view() {
		
		return null;
	}

	@Override
	public String toView() {
		return SUCCESS;
	}

	@Override
	public Object[] simpleSearchCondition() {
		LoginInfo loginer =  (LoginInfo) ActionContext.getContext().getSession().get(GlobalInfo.LOGINER);
		account = loginer.getAccount();
		StringBuffer hql = new StringBuffer();
		Map map = new HashMap();
		hql.append(HQL);
		//高校管理员
		if(account.getType()==3){
			hql.append(" p.agencyName = :agencyName");
			map.put("agencyName", account.getAgency().getName());
			
		}else if(account.getType()==2){
			hql.append(" p.universityAuditResult='2' or p.agency.type='1'");
			
		}
		
		return new Object[]{
				hql.toString(),
				map,
				0,
				null
			};		
	}

	@Override
	public Object[] advSearchCondition() {
		
		
		return null;
	}

	@Override
	public String pageName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String pageBufferId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] sortColumn() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String dateFormat() {
		// TODO Auto-generated method stub
		return null;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	public List<String> getOpinions() {
		return opinions;
	}

	public void setOpinions(List<String> opinions) {
		this.opinions = opinions;
	}
	

}
