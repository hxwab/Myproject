package csdc.model;

import java.io.Serializable;
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

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="T_MAIL")
@JsonIgnoreProperties({"password"})
public class Mail implements Serializable {
	private static final long serialVersionUID = -5309693962229322770L;

	@Id	
	@Column(name="C_ID", unique = true, nullable = false, length=40)
	@GeneratedValue(generator="idGenerator")
    @GenericGenerator(name="idGenerator", strategy="uuid")
	private String id; //邮件id（PK）
	
	@Column(name="C_SEND_TO")
	private String sendTo; //收件人列表
	
	@Column(name="C_SENDED")
	private String sended; //已发送成功的收件人列表
	
	@Column(name="C_SUBJECT", length=200)
	private String subject; //邮件主题
	
	@Column(name="C_BODY")
	private String body; //邮件正文（若包含插入文件，如：插图等，其文件名命名规范参考C_ATTACHMENT字段）
	
	@Column(name="C_IS_HTML", nullable = false)  
	private Integer isHtml; //是否HTML[1：是；0：否]
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="c_create_datetime", nullable = false)
	private Date createDate; //创建时间
	
//	@Column(name="c_sended")
//	private String sended; //收件人列表
	
	@Column(name="C_SEND_TIMES")  
	private Integer sendTimes; //尝试发送次数
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="c_finish_datetime")
	private Date finishDate; //成功时间
	
	@Column(name="C_ACCOUNT_ID")  
	private String accountId; //邮件创建者账号id（FK）

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSendTo() {
		return sendTo;
	}

	public void setSendTo(String sendTo) {
		this.sendTo = sendTo;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Integer getIsHtml() {
		return isHtml;
	}

	public void setIsHtml(Integer isHtml) {
		this.isHtml = isHtml;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getSended() {
		return sended;
	}

	public void setSended(String sended) {
		this.sended = sended;
	}

	public Integer getSendTimes() {
		return sendTimes;
	}

	public void setSendTimes(Integer sendTimes) {
		this.sendTimes = sendTimes;
	}

	public Date getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	

}
