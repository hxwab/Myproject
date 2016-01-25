package csdc.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.io.filefilter.FalseFileFilter;
import org.apache.struts2.json.annotations.JSON;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="T_PERSON")
@JsonIgnoreProperties({"password"})
public class Person implements java.io.Serializable {
	private static final long serialVersionUID = -4428555607314534636L;

	@Id	
	@Column(name="C_ID", unique = true, nullable = false, length=40)
	@GeneratedValue(generator="idGenerator")
    @GenericGenerator(name="idGenerator", strategy="uuid")
	private String id; //人员信息id（PK）
	
	@Column(name="C_NAME", length=200)
	private String name; //中文名[汉字]
	
	@ManyToOne
	@JoinColumn(name="C_AGENCY_ID", nullable = false)
	private Agency agency; //机构（高校）代码（FK）
	
	@Column(name="C_AGENCY_NAME", length=80)
	private String agencyName; //机构（高校）名称
	
	@Column(name="C_GENDER", length=40)
	private String gender; //性别[男、女]
	
	@Column(name="C_ETHNIC", length=80)
	private String ethnic; //民族[略]
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="C_BIRTHDAY", nullable=false)
	private Date birthday; //出生日期
	
	@Column(name="C_OFFICE_PHONE", length=400)
	private String officePhone; //办公电话
	
	@Column(name="c_home_phone", length=400)
	private String homePhone; //家庭电话
	
	@Column(name="c_post_code", length=40)
	private String postCode; //邮编
	
	@Column(name="C_ADDRESS", length=400)
	private String address; //联系地址
	
	@Column(name="C_EMAIL", nullable=false, length=400)
	private String email; //邮箱
	
	@Column(name="C_MOBILE_PHONE", length=400)
	private String mobilePhone; //手机
	
	@Column(name="C_IDCARD_NUMBER", length=80)
	private String idcardNumber; //证件号码[身份证号可包含数字、大写字母]
	
	@Column(name="C_INTRODUCTION")
	private String introduction; //个人简介

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="C_CREATE_DATE")
	private Date createDate; //创建时间
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="C_UPDATE_DATE")
	private Date updateDate; //数据更新时间

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Agency getAgency() {
		return agency;
	}

	public void setAgency(Agency agency) {
		this.agency = agency;
	}

	public String getAgencyName() {
		return agencyName;
	}

	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEthnic() {
		return ethnic;
	}

	public void setEthnic(String ethnic) {
		this.ethnic = ethnic;
	}

	@JSON(format="yyyy-MM-dd")
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getOfficePhone() {
		return officePhone;
	}

	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getIdcardNumber() {
		return idcardNumber;
	}

	public void setIdcardNumber(String idcardNumber) {
		this.idcardNumber = idcardNumber;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	@JSON(format="yyyy-MM-dd")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@JSON(format="yyyy-MM-dd")
	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
