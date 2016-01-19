package csdc.action.product;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.hssf.util.HSSFColor.GOLD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionContext;

import csdc.action.BaseAction;
import csdc.model.Account;
import csdc.model.Agency;
import csdc.model.Person;
import csdc.model.Product;
import csdc.service.IApplyService;
import csdc.service.IFirstAuditService;
import csdc.service.IUploadService;
import csdc.service.imp.SystemOptionService;
import csdc.tool.ApplicationContainer;
import csdc.tool.FileTool;
import csdc.tool.StringTool;
import csdc.tool.bean.FileRecord;
import csdc.tool.bean.LoginInfo;
import csdc.tool.bean.UploadPath;
import csdc.tool.info.GlobalInfo;
import csdc.tool.info.LogInfo;

/**
 * 
 * @author huangxw
 * 上传、详情列表，增、删、改 ，查、下载（仅限管理员）
 * 
 * 申请人只能看到自己的申报信息，管理员可以看到所有或部分的申报信息
 *
 */

@Component
@Scope(value="prototype")

public class ApplyAction extends BaseAction {

	
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
	//private String product_agencyId; //项目依托机构
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
	
	private String agencyName1;//非高校的机构名称
	private String agencyName2;//非高校的机构名称
	private String agencyName3;//非高校的机构名称
	private String agencyName4;//非高校的机构名称
	private String agencyName5;//非高校的机构名称
	
	private List  members ;
	private List  positions ;
	private List  workDivisions ;
	
	
	private List groupNames =  new ArrayList();
	private List ethnics =  new ArrayList();

	
	private String groupName;
	
	private HashMap map = new HashMap();
	
	private int status;
	
	@Autowired
	private IApplyService applyService;
	
	@Autowired
	private IFirstAuditService auditService;
	
	@Autowired
	private IUploadService uploadService;
	
	@Autowired
	private SystemOptionService systemOptionService;
	
	/*添加申报失败控制信息
	 */
	@Override
	public String toAdd() {
		LoginInfo logger=   (LoginInfo) ActionContext.getContext().getSession().get(GlobalInfo.LOGINER);
		Account account = logger.getAccount();
		if(logger==null ||account==null){
			jsonMap.put("tag", "未登录");
			return INPUT;
		}
		 
		groupNames = systemOptionService.getGroupInfo();
		ethnics = systemOptionService.getEthnicsInfo();
		return SUCCESS;
	}

	@Override
	public String add() {
		String fileGroupId = "auditAttachmentUpload";
		String fileGroupId1 = "productAttachmentUpload";
		
		if(!applyService.isCanApply(person, agencyId1)){
			jsonMap.put("tag", "该作者已经申报了两个成果，不得申报多于两个成果！");
			return INPUT;
		}
		
		LoginInfo logger=   (LoginInfo) ActionContext.getContext().getSession().get(GlobalInfo.LOGINER);
		Account account = logger.getAccount();
		
		
		String fileSavePath = null;
       
        fileSavePath = (String) ApplicationContainer.sc.getAttribute(UploadPath.AWARD_APPLY);
		
		List<String> attachments = new ArrayList<String>();
		List<String> attachmentNames = new ArrayList<String>();
		for (FileRecord fileRecord : uploadService.getGroupFiles(fileGroupId)) {
			String newFilePath = FileTool.getTrueFilename(fileSavePath,fileRecord.getOriginal());//文件在数据库中的存储地址（包括存储路径和新的文件名）
			String orignalFileName = fileRecord.getOriginal().getName();//原始文件名
			attachments.add(newFilePath);
			attachmentNames.add(orignalFileName);
			fileRecord.setDest(new File(ApplicationContainer.sc.getRealPath(newFilePath)));//将文件移至新的位置(不立刻执行，而在uploadService.flush时再执行)
		}
		
		for (FileRecord fileRecord : uploadService.getGroupFiles(fileGroupId1)) {
			String newFilePath = FileTool.getTrueFilename(fileSavePath,fileRecord.getOriginal());//文件在数据库中的存储地址（包括存储路径和新的文件名）
			String orignalFileName = fileRecord.getOriginal().getName();//原始文件名
			attachments.add(newFilePath);
			attachmentNames.add(orignalFileName);
			fileRecord.setDest(new File(ApplicationContainer.sc.getRealPath(newFilePath)));//将文件移至新的位置(不立刻执行，而在uploadService.flush时再执行)
		}
		
		product.setFile(StringTool.joinString(attachments.toArray(new String[0]), "; "));
		product.setFileName(StringTool.joinString(attachmentNames.toArray(new String[0]), "; "));
		uploadService.flush(fileGroupId);
		uploadService.flush(fileGroupId1);

		
		
		List<Person> persons = new ArrayList<Person>();
		persons.add(person);
		persons.add(member1);
		persons.add(member2);
		persons.add(member3);
		persons.add(member4);
		
		//可考虑单独提取方法进行封装
		List<String> workDivision = new ArrayList<String>();
		workDivision.add(workDivision1);
		workDivision.add(workDivision2);
		workDivision.add(workDivision3);
		workDivision.add(workDivision4);
		workDivision.add(workDivision5);
		
		List<String> position = new ArrayList<String>();
		position.add(position1);
		position.add(position2);
		position.add(position3);
		position.add(position4);
		position.add(position5);
		
		List<String> agencyId = new ArrayList<String>();
		agencyId.add(agencyId1);
		agencyId.add(agencyId2);
		agencyId.add(agencyId3);
		agencyId.add(agencyId4);
		agencyId.add(agencyId5);
		
		List<String> agencyName = new ArrayList<String>();
		agencyName.add(agencyName1);
		agencyName.add(agencyName2);
		agencyName.add(agencyName3);
		agencyName.add(agencyName4);
		agencyName.add(agencyName5);

		
		/**
		 * map封装其他参数
		 */
		map.put("workDivision", workDivision);
		map.put("position", position);
		map.put("agencyId", agencyId);
		map.put("groupName", groupName);
		map.put("submitStatus", status);
		map.put("agencyName", agencyName);
		//map.put("productAgencyId",product_agencyId);
		product.setAccountId(account.getId());
		String productId = null;
		try {
			productId =applyService.addApplyInfos(persons, product, null, map);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	
		
		if(productId==null){
			jsonMap.put("tag", "成果申报失败！");
			return INPUT;
		}
		jsonMap.put("tag", "1");
		jsonMap.put("entityId", productId);
		return SUCCESS;
	}
	
	public void validateAdd(){
		
		if(person.getName()==null){
			this.addFieldError(GlobalInfo.ERROR_INFO, "作者名称为空");
		}
		if(person.getBirthday()==null){
			this.addFieldError(GlobalInfo.ERROR_INFO, "生日为空");
		}
		if(agencyId1 == null){
			this.addFieldError(GlobalInfo.ERROR_INFO, "机构为空");
		}
		
	}

	@Override
	public String delete() {
		applyService.delete(entityIds);
		return SUCCESS;
	}

	//传一个productId过来
	@Override
	public String toModify() {
		List<Object[]> memberInfos = applyService.getMemberInfo(entityId);
		int numbers =memberInfos.size();
		Object[] o;
		switch (numbers) {
		case 5:
			o = memberInfos.get(4);
			member4 = (Person) o[0];
			workDivision5 = (String) o[1];
			position5 = (String) o[2];
			agencyId5 = (String) o[3];
			

		case 4:
			 o = memberInfos.get(3);
			member3 = (Person) o[0];
			workDivision4 = (String) o[1];
			position4 = (String) o[2];
			agencyId4 = (String) o[3];
			
		case 3:
			 o = memberInfos.get(2);
			member2 = (Person) o[0];
			workDivision3 = (String) o[1];
			position3 = (String) o[2];
			agencyId3 = (String) o[3];
			
			
		case 2:
			 o = memberInfos.get(1);
			member1 = (Person) o[0];
			workDivision2 = (String) o[1];
			position2 = (String) o[2];
			agencyId2 = (String) o[3];
		
		case 1:
			o = memberInfos.get(0);
			person = (Person) o[0];
			workDivision1 = (String) o[1];
			position1 = (String) o[2];
			agencyId1 = (String) o[3];
			agencyName1 = person.getAgencyName();
			break;
			
		default:
			break;
		}
		
		product = applyService.getProductById(entityId);
		members = applyService.getObjectList(null, member1, member2, member3, member4);
		workDivisions = applyService.getObjectList(null, workDivision2, workDivision3, workDivision4, workDivision5);
		positions = applyService.getObjectList(null, position2, position3, position4, position5);
		groupNames = systemOptionService.getGroupInfo();
		ethnics = systemOptionService.getEthnicsInfo();
		groupName = product.getGroupName();
		
		
		
		String groupId1 = "file1_" + product.getId();
		String groupId2 = "file2_" + product.getId();
		uploadService.resetGroup(groupId1);
		uploadService.resetGroup(groupId2);
		String auditFile =null ;
		String auditAttachment=null;
		String  reviewFile=null;
		String reviewAttachment = null;
		
		if ((product.getFileName() != null&&!"".equals(product.getFileName())) || (product.getFile() != null&&!"".equals(product.getFile()))) {
			String[] fileRealpath = product.getFile().split("; ");
			String[] attchmentNames = product.getFileName().split("; ");
			
			if(fileRealpath.length==2){
				if(fileRealpath[0].endsWith(".pdf")){
					auditFile =fileRealpath[0];
					auditAttachment = attchmentNames[0];
					reviewFile = fileRealpath[1];
					reviewAttachment =attchmentNames[1];
				}else {
					auditFile = fileRealpath[1];
					auditAttachment = attchmentNames[1];
					reviewFile = fileRealpath[0];
					reviewAttachment =attchmentNames[0];
				}
			} else if(fileRealpath.length==1){
				
				if(fileRealpath[0].endsWith(".pdf")){
					auditFile =fileRealpath[0];
					auditAttachment = attchmentNames[0];
				} else {
						reviewFile = fileRealpath[0];
						reviewAttachment =attchmentNames[0];
				}
			}
			
			String auditFilePath=null;
			String reviewFilePath = null;
			
			if(auditFile!=null&&!"".equals(auditFile)){
				auditFilePath = ApplicationContainer.sc.getRealPath(auditFile);
			}
			
			if(reviewFile!=null&&!"".equals(reviewFile)){
				reviewFilePath = ApplicationContainer.sc.getRealPath(reviewFile);
			}

			if(auditFilePath!=null&&new File(auditFilePath).exists()){
				uploadService.addFile(groupId1, new File(auditFilePath), auditAttachment);
			}
			
			if(reviewFilePath!=null&&new File(reviewFilePath).exists()){
				uploadService.addFile(groupId2, new File(reviewFilePath), reviewAttachment);
			}
			
		}
		
		
		
		
		if(product.getSubmitStatus()==2){
			jsonMap.put("tag", "3");
			return INPUT;
		}
		ActionContext.getContext().getSession().put("entityId", entityId);
		return SUCCESS;
	}

	@Override
	public String modify() {
		
		entityId=ActionContext.getContext().getSession().get("entityId").toString();
		
		String fileGroupId = "file1_"+entityId;
		String fileGroupId1 = "file2_"+entityId;
		
		String fileSavePath = null;
        fileSavePath = (String) ApplicationContainer.sc.getAttribute(UploadPath.AWARD_APPLY);
		List<String> attachments = new ArrayList<String>();
		List<String> attachmentNames = new ArrayList<String>();
		for (FileRecord fileRecord : uploadService.getGroupFiles(fileGroupId)) {
			String newFilePath = FileTool.getTrueFilename(fileSavePath,fileRecord.getOriginal());//文件在数据库中的存储地址（包括存储路径和新的文件名）
			String orignalFileName = fileRecord.getOriginal().getName();//原始文件名
			attachments.add(newFilePath);
			attachmentNames.add(orignalFileName);
			fileRecord.setDest(new File(ApplicationContainer.sc.getRealPath(newFilePath)));//将文件移至新的位置(不立刻执行，而在uploadService.flush时再执行)
		}
		
		for (FileRecord fileRecord : uploadService.getGroupFiles(fileGroupId1)) {
			String newFilePath = FileTool.getTrueFilename(fileSavePath,fileRecord.getOriginal());//文件在数据库中的存储地址（包括存储路径和新的文件名）
			String orignalFileName = fileRecord.getOriginal().getName();//原始文件名
			attachments.add(newFilePath);
			attachmentNames.add(orignalFileName);
			fileRecord.setDest(new File(ApplicationContainer.sc.getRealPath(newFilePath)));//将文件移至新的位置(不立刻执行，而在uploadService.flush时再执行)
		}
		
		product.setFile(StringTool.joinString(attachments.toArray(new String[0]), "; "));
		product.setFileName(StringTool.joinString(attachmentNames.toArray(new String[0]), "; "));
		uploadService.flush(fileGroupId);
		uploadService.flush(fileGroupId1);
		
		
		
		
		List<Person> persons = new ArrayList<Person>();
		persons.add(person);
		persons.add(member1);
		persons.add(member2);
		persons.add(member3);
		persons.add(member4);
		
		//可考虑单独提取方法进行封装
		List<String> workDivision = new ArrayList<String>();
		workDivision.add(workDivision1);
		workDivision.add(workDivision2);
		workDivision.add(workDivision3);
		workDivision.add(workDivision4);
		workDivision.add(workDivision5);
		
		List<String> position = new ArrayList<String>();
		position.add(position1);
		position.add(position2);
		position.add(position3);
		position.add(position4);
		position.add(position5);
		
		List<String> agencyId = new ArrayList<String>();
		agencyId.add(agencyId1);
		agencyId.add(agencyId2);
		agencyId.add(agencyId3);
		agencyId.add(agencyId4);
		agencyId.add(agencyId5);
		
		List<String> agencyName = new ArrayList<String>();
		agencyName.add(agencyName1);
		agencyName.add(agencyName2);
		agencyName.add(agencyName3);
		agencyName.add(agencyName4);
		agencyName.add(agencyName5);
		
		/**
		 * map封装其他参数
		 */
		map.put("workDivision", workDivision);
		map.put("position", position);
		map.put("agencyId", agencyId);
		map.put("groupName", groupName);
		map.put("submitStatus", status);
		map.put("entityId", entityId);
		map.put("agencyName", agencyName);
		//TODO:此处服务处要修改
		applyService.modifyInfos(persons, product, map);
		jsonMap.put("tag", "1");
		jsonMap.put("entityId", entityId);
		ActionContext.getContext().getSession().remove("entityId");
		return SUCCESS;
	}

	//前台要显示机构名，此处agencyId赋值为机构名
	@Override
	public String view() {
		List<Object[]> memberInfos = applyService.getMemberInfo(entityId);
		int numbers =memberInfos.size();
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
			agencyId4 = applyService.getAgencyById((String) o[3]).getName();;
			
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
		agencyName1 = product.getAgencyName();
		jsonMap.put("product", product);
		jsonMap.put("members", memberInfos);
		return SUCCESS;
	}

	@Override
	public String toView() {
		return SUCCESS;
	}

	@Override
	public Object[] simpleSearchCondition() {
		
		return null;
	}

	@Override
	public Object[] advSearchCondition() {
		// TODO Auto-generated method stub
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

	public List getMembers() {
		return members;
	}

	public void setMembers(List members) {
		this.members = members;
	}

	public List getPositions() {
		return positions;
	}

	public void setPositions(List positions) {
		this.positions = positions;
	}

	public List getWorkDivisions() {
		return workDivisions;
	}

	public void setWorkDivisions(List workDivisions) {
		this.workDivisions = workDivisions;
	}

	public List getGroupNames() {
		return groupNames;
	}

	public void setGroupNames(List groupNames) {
		this.groupNames = groupNames;
	}

	public List getEthnics() {
		return ethnics;
	}

	public void setEthnics(List ethnics) {
		this.ethnics = ethnics;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getAgencyName1() {
		return agencyName1;
	}

	public void setAgencyName1(String agencyName1) {
		this.agencyName1 = agencyName1;
	}

	public String getAgencyName2() {
		return agencyName2;
	}

	public void setAgencyName2(String agencyName2) {
		this.agencyName2 = agencyName2;
	}

	public String getAgencyName3() {
		return agencyName3;
	}

	public void setAgencyName3(String agencyName3) {
		this.agencyName3 = agencyName3;
	}

	public String getAgencyName4() {
		return agencyName4;
	}

	public void setAgencyName4(String agencyName4) {
		this.agencyName4 = agencyName4;
	}

	public String getAgencyName5() {
		return agencyName5;
	}

	public void setAgencyName5(String agencyName5) {
		this.agencyName5 = agencyName5;
	}

	
	
}