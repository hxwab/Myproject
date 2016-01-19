package csdc.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;
import com.sun.org.apache.bcel.internal.generic.SIPUSH;

import csdc.model.Agency;
import csdc.model.Person;
import csdc.model.Product;
import csdc.service.IApplyService;
import csdc.tool.ApplicationContainer;
import csdc.tool.FileTool;
import csdc.tool.WordExport;
import csdc.tool.ZipUtil;


@Component
@Scope(value = "prototype")
public class ProductViewAction  extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1831252075262968644L;
	

		
		//作者相应分工情况
		private String workDivision1;
		private String workDivision2;
		private String workDivision3;
		private String workDivision4;
		private String workDivision5;
		
		//作者相应职位
		private String position1;
		private String position2;
		private String position3;
		private String position4;
		private String position5;
		
		//作者所属机构名称
		private String product_agencyId; //项目依托机构
		private String agencyId1;
		private String agencyId2;
		private String agencyId3;
		private String agencyId4;
		private String agencyId5;
		
		private Product product;
		
		private Person person;//负责人
		
		private Person member1;//项目成员
		
		private Person member2;
		
		private Person member3;
		
		private Person member4;
		
		private Agency agency;
		
		private String entityId;
		private List<String> entityIds;
		private String groupName;
		
		private HashMap jsonMap = new HashMap();
		
		
	//	private InputStream fileStream;
		
		private String fileName;
		
		@Autowired
		private IApplyService applyService;
		
		private int numbers;
	
		private static Queue<Long> queue = new PriorityQueue<Long>();
		

		//前台要显示机构名，此处agencyId赋值为机构名
		public String view() {
			List<Object[]> memberInfos = applyService.getMemberInfo(entityId);
			numbers =memberInfos.size();
			Object[] o;
			switch (numbers) {
			case 5:
				o = memberInfos.get(4);
				member4 = (Person) o[0];
				workDivision5 = (String) o[1];
				position5 = (String) o[2];
				agencyId5 = applyService.getAgencyById((String) o[3]).getName();
				

			case 4:
				 o = memberInfos.get(3);
				member3 = (Person) o[0];
				workDivision4 = (String) o[1];
				position4 = (String) o[2];
				agencyId4 = applyService.getAgencyById((String) o[3]).getName();
				
			case 3:
				 o = memberInfos.get(2);
				member2 = (Person) o[0];
				workDivision3 = (String) o[1];
				position3 = (String) o[2];
				agencyId3 = applyService.getAgencyById((String) o[3]).getName();
				
				
			case 2:
				 o = memberInfos.get(1);
				member1 = (Person) o[0];
				workDivision2 = (String) o[1];
				position2 = (String) o[2];
				agencyId2 = applyService.getAgencyById((String) o[3]).getName();
			
			case 1:
				o = memberInfos.get(0);
				person = (Person) o[0];
				workDivision1 = (String) o[1];
				position1 = (String) o[2];
				agencyId1 = applyService.getAgencyById((String) o[3]).getName();
				break;
				
			default:
				break;
			}
			
			product = applyService.getProductById(entityId);
			
			jsonMap.put("product", product);
			jsonMap.put("members", memberInfos);
			return SUCCESS;
		}

		public String toExportPorduct() {
			return  SUCCESS;
		}
	
		public String exportProduct(){
			/*entityIds = new ArrayList<String>();
			entityIds.add("4028d8c0520b382101520f3eec18000a");
			entityIds.add("4028d8c0520b382101520f3eec18000a");*/
			
			
			if(entityId!=null&&!"".equals(entityId)){
				entityIds = new ArrayList<String>();
				entityIds.add(entityId);
		}
			
			
			long timer = new Date().getTime();
			queue.add(timer);
			WordExport word = new WordExport(timer);
			
	
			
			for(String productId :entityIds){
				entityId =productId;
				//this.view();
				setMemberInfos(applyService.getMemberInfo(productId));
				Map<String, Object> dataMap = new HashMap<String, Object>();
				
				dataMap.put("productType", product.getType());
				System.out.println(product.getGroups().getName());
				dataMap.put("group",  changeStr(product.getGroups().getName()));
				dataMap.put("researchType", product.getResearchType());
				//todo 究竟是审核后的还是审核前的
				dataMap.put("number", product.getReviewNumber());
				dataMap.put("productName", product.getName());
				dataMap.put("author", product.getAuthorName());
				dataMap.put("agency", product.getAgencyName());
				//dataMap.put("date",product.getCreateDate());
				dataMap.put("date",date2string(product.getCreateDate()) );
				
				
				/*dataMap.put("year", product.getSubmitDate().getYear());
				dataMap.put("month", product.getSubmitDate().getMonth()+1);
				dataMap.put("day", product.getSubmitDate().getDay());*/
				
				
				dataMap.put("publishName", product.getPublishName());
				dataMap.put("publishLevel", product.getPublishLevel());
				dataMap.put("publishDate",date2string(product.getPublishDate()));
				dataMap.put("gender", product.getAuthor().getGender());
				dataMap.put("nation", product.getAuthor().getEthnic());
				dataMap.put("birthday", date2string(product.getAuthor().getBirthday()));
				dataMap.put("job", position1);
				dataMap.put("major", "");//数据库添加
				
				dataMap.put("address", person.getAddress());
				dataMap.put("post", person.getPostCode()==null?"":person.getPostCode());
				dataMap.put("officePhone", person.getOfficePhone()==null?"":person.getOfficePhone());
				dataMap.put("homePhone", person.getHomePhone()==null?"":person.getHomePhone());
				dataMap.put("mobliePhone", person.getMobilePhone()==null?"":person.getMobilePhone());
				
				dataMap.put("introduction", product.getIntroduction());
				dataMap.put("abstractstr", product.getAbstractStr());
				dataMap.put("socialEffect", product.getSocialEffect());
				
				//合作者信息
				
				if(member1==null){
					dataMap.put("name1", "");
					dataMap.put("age1", "");
					dataMap.put("agency1", "");
					dataMap.put("job1", "");
					dataMap.put("workDivision1", "");
				}else{
					dataMap.put("name1", member1.getName());
					dataMap.put("age1", new Date().getYear()-member1.getBirthday().getYear());
					dataMap.put("agency1", member1.getAgency().getName());
					dataMap.put("job1", position2);
					dataMap.put("workDivision1", workDivision2);
				}
				
				if(member2==null){
					dataMap.put("name2", "");
					dataMap.put("age2", "");
					dataMap.put("agency2", "");
					dataMap.put("job2", "");
					dataMap.put("workDivision2", "");
				}else{
					dataMap.put("name2", member2.getName());
					dataMap.put("age2", new Date().getYear()-member2.getBirthday().getYear());
					dataMap.put("agency2", member2.getAgency().getName());
					dataMap.put("job2", position3);
					dataMap.put("workDivision2", workDivision3);
				}
				
				if(member3==null){
					dataMap.put("name3", "");
					dataMap.put("age3", "");
					dataMap.put("agency3", "");
					dataMap.put("job3", "");
					dataMap.put("workDivision3", "");
				}else{
					dataMap.put("name3", member3.getName());
					dataMap.put("age3", new Date().getYear()-member3.getBirthday().getYear());
					dataMap.put("agency3", member3.getAgency().getName());
					dataMap.put("job3", position4);
					dataMap.put("workDivision3", workDivision4);
				}
				
				
				if(member4==null){
					dataMap.put("name4", "");
					dataMap.put("age4", "");
					dataMap.put("agency4", "");
					dataMap.put("job4", "");
					dataMap.put("workDivision4", "");
				}else{
					dataMap.put("name4", member4.getName());
					dataMap.put("age4", new Date().getYear()-member4.getBirthday().getYear());
					dataMap.put("agency4", member4.getAgency().getName());
					dataMap.put("job4", position5);
					dataMap.put("workDivision4", workDivision5);
				}
				
				word.createWord(product.getName(), dataMap);
				
			}
			
			
			String realpath = word.getRealPath();
			String zipPath = WordExport.getZipPath()+"/"+timer+".zip";
			try {
				
				ZipUtil.zip(realpath,zipPath );
				Thread.sleep(500);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//jsonMap.put("tag", "1");
			
			return SUCCESS;
		}
		
		
		public String validateGetFileName(){
			long timer = queue.peek();
			fileName = timer+".zip";
			//jsonMap.put("tag", "1");
			return SUCCESS;
		}
		
		public String download(){
			return SUCCESS;
		}
		
		public InputStream getFileStream() {
			long timer = queue.poll();
			fileName = timer+".zip";
			String zipPath = "upload/temp/zip/"+timer+".zip";
			String realPath =ApplicationContainer.sc.getRealPath("upload/temp");
			InputStream downloadInputStream = null;
			downloadInputStream=ApplicationContainer.sc.getResourceAsStream(zipPath);
			FileTool.fileDelete(zipPath);
			
			realPath = realPath.replace('\\', '/');
			FileTool.rm_fr(realPath+"/"+timer);
			return downloadInputStream;
		}
		
		
		private void setMemberInfos(List<Object[]> memberInfos){
			numbers =memberInfos.size();
			Object[] o;
			switch (numbers) {
			case 5:
				o = memberInfos.get(4);
				member4 = (Person) o[0];
				workDivision5 = (String) o[1];
				position5 = (String) o[2];
				agencyId5 = applyService.getAgencyById((String) o[3]).getName();
				

			case 4:
				 o = memberInfos.get(3);
				member3 = (Person) o[0];
				workDivision4 = (String) o[1];
				position4 = (String) o[2];
				agencyId4 = applyService.getAgencyById((String) o[3]).getName();
				
			case 3:
				 o = memberInfos.get(2);
				member2 = (Person) o[0];
				workDivision3 = (String) o[1];
				position3 = (String) o[2];
				agencyId3 = applyService.getAgencyById((String) o[3]).getName();
				
				
			case 2:
				 o = memberInfos.get(1);
				member1 = (Person) o[0];
				workDivision2 = (String) o[1];
				position2 = (String) o[2];
				agencyId2 = applyService.getAgencyById((String) o[3]).getName();
			
			case 1:
				o = memberInfos.get(0);
				person = (Person) o[0];
				workDivision1 = (String) o[1];
				position1 = (String) o[2];
				agencyId1 = applyService.getAgencyById((String) o[3]).getName();
				break;
				
			default:
				break;
			}
			product = applyService.getProductById(entityId);
		}
		
		
		public static boolean deleteFile(String sPath) {
	        Boolean flag = false;
	        File file = new File(sPath);
	        // 路径为文件且不为空则进行删除
	        if (file.isFile() && file.exists()) {
	            file.delete();
	            flag = true;
	        }
	        return flag;
	    }
	 
	    /**
	     * 删除目录（文件夹）以及目录下的文件
	     * 
	     * @param sPath
	     *            被删除目录的文件路径
	     * @return 目录删除成功返回true，否则返回false
	     */
	    public static boolean deleteDirectory(String sPath) {
	        // 如果sPath不以文件分隔符结尾，自动添加文件分隔符
	        if (!sPath.endsWith(File.separator)) {
	            sPath = sPath + File.separator;
	        }
	        File dirFile = new File(sPath);
	        // 如果dir对应的文件不存在，或者不是一个目录，则退出
	        if (!dirFile.exists() || !dirFile.isDirectory()) {
	            return false;
	        }
	        Boolean flag = true;
	        // 删除文件夹下的所有文件(包括子目录)
	        File[] files = dirFile.listFiles();
	        for (int i = 0; i < files.length; i++) {
	            // 删除子文件
	            if (files[i].isFile()) {
	                flag = deleteFile(files[i].getAbsolutePath());
	                if (!flag)
	                    break;
	            } // 删除子目录
	            else {
	                flag = deleteDirectory(files[i].getAbsolutePath());
	                if (!flag)
	                    break;
	            }
	        }
	        if (!flag)
	            return false;
	        // 删除当前目录
	        if (dirFile.delete()) {
	            return true;
	        } else {
	            return false;
	        }
	    }
		
		
	    private String  changeStr(String str){
	    	
	    	int index = str.indexOf("（");
	    	if(index!=-1){
	    		str = str.substring(0, index);
	    	}
	    	
	    	return str;
	    }
	    
		private String date2string(Date date){
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String d = sdf.format(date);
			return d;
		}

		public Product getProduct() {
			return product;
		}

		public void setProduct(Product product) {
			this.product = product;
		}

		public Person getPerson() {
			return person;
		}

		public void setPerson(Person person) {
			this.person = person;
		}

		public Person getMember1() {
			return member1;
		}

		public void setMember1(Person member1) {
			this.member1 = member1;
		}

		public Person getMember2() {
			return member2;
		}

		public void setMember2(Person member2) {
			this.member2 = member2;
		}

		public Person getMember3() {
			return member3;
		}

		public void setMember3(Person member3) {
			this.member3 = member3;
		}

		public Person getMember4() {
			return member4;
		}

		public void setMember4(Person member4) {
			this.member4 = member4;
		}


		public Agency getAgency() {
			return agency;
		}

		public void setAgency(Agency agency) {
			this.agency = agency;
		}

		public String getWorkDivision1() {
			return workDivision1;
		}

		public void setWorkDivision1(String workDivision1) {
			this.workDivision1 = workDivision1;
		}

		public String getWorkDivision2() {
			return workDivision2;
		}

		public void setWorkDivision2(String workDivision2) {
			this.workDivision2 = workDivision2;
		}

		public String getWorkDivision3() {
			return workDivision3;
		}

		public void setWorkDivision3(String workDivision3) {
			this.workDivision3 = workDivision3;
		}

		public String getWorkDivision4() {
			return workDivision4;
		}

		public void setWorkDivision4(String workDivision4) {
			this.workDivision4 = workDivision4;
		}

		public String getWorkDivision5() {
			return workDivision5;
		}

		public void setWorkDivision5(String workDivision5) {
			this.workDivision5 = workDivision5;
		}

		public String getPosition1() {
			return position1;
		}

		public void setPosition1(String position1) {
			this.position1 = position1;
		}

		public String getPosition2() {
			return position2;
		}

		public void setPosition2(String position2) {
			this.position2 = position2;
		}

		public String getPosition3() {
			return position3;
		}

		public void setPosition3(String position3) {
			this.position3 = position3;
		}

		public String getPosition4() {
			return position4;
		}

		public void setPosition4(String position4) {
			this.position4 = position4;
		}

		public String getPosition5() {
			return position5;
		}

		public void setPosition5(String position5) {
			this.position5 = position5;
		}

		
		
		
		public String getProduct_agencyId() {
			return product_agencyId;
		}

		public void setProduct_agencyId(String product_agencyId) {
			this.product_agencyId = product_agencyId;
		}


		public String getAgencyId1() {
			return agencyId1;
		}

		public void setAgencyId1(String agencyId1) {
			this.agencyId1 = agencyId1;
		}

		public String getAgencyId2() {
			return agencyId2;
		}

		public void setAgencyId2(String agencyId2) {
			this.agencyId2 = agencyId2;
		}

		public String getAgencyId3() {
			return agencyId3;
		}

		public void setAgencyId3(String agencyId3) {
			this.agencyId3 = agencyId3;
		}

		public String getAgencyId4() {
			return agencyId4;
		}

		public void setAgencyId4(String agencyId4) {
			this.agencyId4 = agencyId4;
		}

		public String getAgencyId5() {
			return agencyId5;
		}

		public void setAgencyId5(String agencyId5) {
			this.agencyId5 = agencyId5;
		}

		

		public String getGroupName() {
			return groupName;
		}

		public void setGroupName(String groupName) {
			this.groupName = groupName;
		}


		public String getEntityId() {
			return entityId;
		}

		public void setEntityId(String entityId) {
			this.entityId = entityId;
		}


		public HashMap getJsonMap() {
			return jsonMap;
		}

		public void setJsonMap(HashMap jsonMap) {
			this.jsonMap = jsonMap;
		}


		public List<String> getEntityIds() {
			return entityIds;
		}


		public void setEntityIds(List<String> entityIds) {
			this.entityIds = entityIds;
		}


		


	/*	public void setFileStream(InputStream fileStream) {
			this.fileStream = fileStream;
		}
*/

		public String getFileName() {
			return fileName;
		}


		public void setFileName(String fileName) {
			this.fileName = fileName;
		}

		

}
