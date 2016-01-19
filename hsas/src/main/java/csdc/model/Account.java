package csdc.model;


import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.struts2.json.annotations.JSON;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="T_ACCOUNT")
@JsonIgnoreProperties({"password"})
public class Account implements java.io.Serializable {
	private static final long serialVersionUID = -4428555607314534636L;

	@Id	
	@Column(name="c_id", unique = true, nullable = false, length=40)
	@GeneratedValue(generator="idGenerator")
    @GenericGenerator(name="idGenerator", strategy="uuid")
	private String id; //账号id（PK）
	
	@Column(name="c_username", length=200, nullable = false)  
	private String username; //用户名
	
	@Column(name="c_password", length=40, nullable = false)  
	private String password; //密码[密文]
	
	@Column(name="c_type", nullable = false)
	private Integer type; //是否超级管理员[1：是，0：否]账号类型。[1：高级管理员；2：一般管理员；3：高校管理员；4：评审专家组长；5：评审专家；6：申请人]
	
	@Column(name="c_status", nullable = false)
	private Integer status; //账号状态[0：停用；1：启用]
	
	@Column(name="c_pw_retrieve_code", length=40)  
	private String pwRetrieveCode; //密码找回码
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="c_start_date", nullable = false)
	private Date startDate; //开始（注册或创建）时间
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="c_approve_time")
	private Date approveTime; //批准时间
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="c_expire_date", nullable = false)
	private Date expireDate; //有效期限
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="c_last_login_date")
	private Date lastLoginDate; //上次登录时间
	
	@Column(name="c_create_type")
	private Integer createType; //创建类型[0：管理员分配；1：用户注册]
	
	@ManyToOne()
	@JoinColumn(name="c_agency_id")
	private Agency agency; //机构id
	
	@ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
	@JoinColumn(name="c_person_id")
	private Person person; //账号申请成果id
	
	@Column(name="c_email", length=40)  
	private String mail; //邮件地址
	
	@Column(name = "c_is_super_user", nullable = false)
	private Integer isSuperUser; //是否超级管理员[1：是，0：否]
	

	
	
	@OneToMany(mappedBy="account",cascade=CascadeType.ALL)
	private Set<AccountRole> accountRoles;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPwRetrieveCode() {
		return pwRetrieveCode;
	}

	public void setPwRetrieveCode(String pwRetrieveCode) {
		this.pwRetrieveCode = pwRetrieveCode;
	}
	
	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getApproveTime() {
		return approveTime;
	}

	public void setApproveTime(Date approveTime) {
		this.approveTime = approveTime;
	}
	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getCreateType() {
		return createType;
	}

	public void setCreateType(Integer createType) {
		this.createType = createType;
	}

	public Integer getIsSuperUser() {
		return isSuperUser;
	}

	public void setIsSuperUser(Integer isSuperUser) {
		this.isSuperUser = isSuperUser;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Agency getAgency() {
		return agency;
	}

	public void setAgency(Agency agency) {
		this.agency = agency;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	@JSON(serialize=false)
	public Set<AccountRole> getAccountRoles() {
		return accountRoles;
	}

	public void setAccountRoles(Set<AccountRole> accountRoles) {
		this.accountRoles = accountRoles;
	}

	
	
}
