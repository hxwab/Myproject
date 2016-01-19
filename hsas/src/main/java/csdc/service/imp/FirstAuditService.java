package csdc.service.imp;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mysql.fabric.xmlrpc.base.Array;
import com.sun.org.apache.xerces.internal.impl.dv.dtd.NMTOKENDatatypeValidator;

import csdc.model.Account;
import csdc.model.Product;
import csdc.service.IFirstAuditService;
import csdc.tool.ApplicationContainer;
import csdc.tool.ZipUtil;

@Service
public class FirstAuditService extends BaseService implements IFirstAuditService {

	@Override
	public Product getProductById(String productId) {
		return dao.query(Product.class, productId);
	}

	
	@Transactional
	@Override
	public String setFirstCheckResult(String productId, Account account,String result,String opinion) {
		//Account account = (Account) map.get("account");
		Product product = getProductById(productId);
		//String result = map.get("result").toString();
		//String opinion = map.get("opinion").toString();
		
		if(product.getSubmitStatus()!=2){
			
			return "成果未提交！";
		}
		
		//高校审核
		if(account.getType()==3){
			if(result.equals("1")){
				product.setUniversityAuditResult(1);
				product.setUniversityAuditOpinion("高校初审未通过！"+opinion);
			} else if( result.equals("2")){
				product.setUniversityAuditResult(2);
				product.setUniversityAuditOpinion("高校初审通过！"+opinion);

			}else if(result.equals("3")){
				product.setUniversityAuditOpinion("退回"+opinion);
				product.setUniversityAuditResult(3);
				product.setSubmitStatus(3);
			}else{
				product.setUniversityAuditResult(0);
		
			}
			product.setStatus(3);
		} else if(account.getType()==2 || account.getType() ==1){//社科联审核
			//能够走到这个流程表明，高校审核通过或为非高校申报成果
			if(result.equals("1")){
				product.setHsasApplyAuditResult(1);
				product.setHsasApplyAuditOpinion("省社科联初审未通过！"+opinion);
			} else if(result.equals("2")){
				product.setHsasApplyAuditResult(2);
				product.setHsasApplyAuditOpinion("省社科联初审通过！"+opinion);
				product.setReviewNumber(generateAuditNumber(productId));

			}else if(result.equals("3")){
				product.setHsasApplyAuditOpinion("退回!"+opinion);
				product.setHsasApplyAuditResult(3);
				product.setSubmitStatus(3);
			}
			else {
				product.setHsasApplyAuditResult(0);
				
			}
			product.setStatus(4);
		}else{
			return "审核失败！";
		}
		
		product.setUpdateDate(new Date());
		dao.modify(product);
		return "success";
	}


	public boolean canApply(List<String> entityIds){
		boolean flag=true;
		for(String id : entityIds){
			Product product = getProductById(id);
			
			if(product.getSubmitStatus()!=2){
				flag = false;
				break;
			}
			
			if(!(product.getUniversityAuditResult()==2||product.getUniversityAuditResult()==4)){
				flag =false;
				break;
			}
		}
		return flag;
	}

	@Override
	public String setFirstCheckRestults(List<String> productIds, Map map) {
		Account account = (Account) map.get("account");
		String result = map.get("result").toString();
		String opinions = map.get("opinion").toString();
		String re="";
		for(String productId :productIds){
			re=	setFirstCheckResult(productId, account,result,opinions);
		}
		return re;
	}



	@Override
	public List getProductInfos(List<String> productId) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void delete(List<String> entityIds) {

		for(String id:entityIds){
			try {
				dao.delete(Product.class,id);
			} catch (Exception e) {
				e.printStackTrace()	;		
				}
			
		}
	}



	@Override
	public List addExternalInfo(List laData) {
		
		List newLaData = new ArrayList();
		String[] nItem;
		for(Object data : laData){
			if(data!=null){
				StringBuffer s = new StringBuffer();
				String[] item = (String[]) data;
				
				nItem = new String[item.length+2];
				
				for(int i =0; i<item.length;i++){
					nItem[i]=item[i];
				}
				
				List members = getProductMemberInfo(item[0]);
				for(Object name:members){
					s.append(name+" ");
				}
				nItem[item.length]=s.toString();
				nItem[item.length+1] = getFileName(item[0])==null?"":getFileName(item[0]);
				
				newLaData.add(nItem);
			}
			
		}
		
		return newLaData;
	}



	private  List getProductMemberInfo(String id){
		Map map= new HashMap();
		map.put("productId", id);
		String hql = "select pa.person.name from ProductAuthor pa where pa.product.id= :productId order by pa.order";
		return dao.query(hql, map);
	}

	
	@Override
	public String getFileName(String entityId){
		Map map =new HashMap();
		map.put("id", entityId);
		String hql = "select p.fileName from Product p where p.id =:id";
		Object obj = dao.queryUnique(hql, map);
		if(obj == null||obj.equals("")){
			return null;
		}
		String fileName = obj.toString();
		String[] fileNames = fileName.split(";");
		
		if(fileNames.length==1){
			if(fileNames[0].endsWith(".pdf")){
				fileName=fileNames[0];
			}else {
				fileName=null;
			}
		}else {
			
			if(fileNames[0].endsWith(".pdf")){
				fileName =fileNames[0];
			}else {
				fileName = fileNames[1];
			}
		}
		
		return fileName;
	}
	

	@Transactional
	@Override
	public String generateAuditNumber(String entityId) {

		int size =3;//编码为三位数
		/*String hql ="select max(p.reviewNumber) from Product p where  (p.groupName,p.applyYear) in (select pd.groupName,pd.applyYear from Product pd where pd.id=:id)";
		
		
		Map map = new HashMap();
		map.put("id", entityId);
		String  number=null;
		try {
			
			  number = dao.query(hql).toString();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}*/
		String number  = null;
		try {
			String hql1 = "select pd.groupName,pd.applyYear from Product pd where pd.id=:id ";

			String hql2 = "select max(p.reviewNumber) from Product p where p.groupName =:groupName and p.applyYear=:applyYear";
			Map map = new HashMap();
			map.put("id", entityId);
			List list = dao.query(hql1,map);
			Object[] item = null;
			for(Object data :list){
	
				item = (Object[]) data;
			}
			
			
			map.clear();
			map.put("groupName", item[0].toString());
			map.put("applyYear", item[1].toString());
			 number = dao.queryUnique(hql2,map).toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		if(number==null||"".equals(number)||number.equals("NULL")||number.equals("null")){
			number ="0";
		}
		
		int  max = Integer.parseInt(number);
		int next = max+1;
		int len = Integer.toString(next).length();
		StringBuffer s = new StringBuffer();
		for(int i=0;i<size-len;i++){
			s.append(0);
		}
		s.append(next);
		
		return s.toString();
	}


	@Override
	public String getFilePath(String entityId) {
		Map map =new HashMap();
		map.put("id", entityId);
		String hql = "select p.file from Product p where p.id =:id";
		Object obj = dao.queryUnique(hql, map);
		if(obj == null){
			return null;
		}
		String filePath = obj.toString();
		String[] filePaths = filePath.split("; ");
		if(filePaths[0].endsWith(".pdf")){
			filePath =filePaths[0];
		}else {
			filePath = filePaths[1];
		}
		return filePath;
		
	}


	@Override
	public String zipFile(List<String> entityIds) {
		long timer = new Date().getTime();
		String zipPath=ApplicationContainer.sc.getRealPath("upload/temp/zip")+"/"+timer+".zip";
		List<File> files = new ArrayList<File>();
		for(String id :entityIds){
			
			String filePath = getFilePath(id);
			String realPath = ApplicationContainer.sc.getRealPath(filePath);
			File file= new File(realPath);
			files.add(file);
		}
		
		try {
			ZipUtil.zip(zipPath, files.toArray(new File[0]));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return zipPath;
	}

}
