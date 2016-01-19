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
@Table(name="t_reward" )
@JsonIgnoreProperties({"password"})
public class Reward implements Serializable {
	private static final long serialVersionUID = 6590501447685135096L;

	@Id	
	@Column(name="C_ID", unique = true, nullable = false, length=40)
	@GeneratedValue(generator="idGenerator")
    @GenericGenerator(name="idGenerator", strategy="uuid")
	private String id; //奖励id（PK）
	
	@Column(name="c_year", length=40)
	private String year; //年份
	
	@Column(name="c_book_special")
	private Integer bookSpecial; //著作特等奖金额
	
	@Column(name="c_book_first", length=40)
	private Integer bookFirst; //著作一等奖金额
	
	@Column(name="c_book_second", length=40)
	private Integer bookSecond; //著作二等奖金额
	
	@Column(name="c_book_third", length=40)
	private Integer bookThird; //著作三等奖金额
	
	@Column(name="c_paper_special", length=40)
	private Integer paperSpecial; //论文特等奖金额
	
	@Column(name="c_paper_first", length=40)
	private Integer paperFirst; //论文一等奖金额
	
	@Column(name="c_paper_second", length=40)
	private Integer paperSecond; //论文二等奖金额
	
	@Column(name="c_paper_third", length=40)
	private Integer paperThird; //论文三等奖金额
	
	@ManyToOne
	@JoinColumn(name="c_creater_id")
	private Account creater; //创建人id（FK）
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="c_create_date")
	private Date createDate; //创建时间
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="c_update_date")
	private Date updateDate; //最近修改时间

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Account getCreater() {
		return creater;
	}

	public void setCreater(Account creater) {
		this.creater = creater;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Integer getBookSpecial() {
		return bookSpecial;
	}

	public void setBookSpecial(Integer bookSpecial) {
		this.bookSpecial = bookSpecial;
	}

	public Integer getBookFirst() {
		return bookFirst;
	}

	public void setBookFirst(Integer bookFirst) {
		this.bookFirst = bookFirst;
	}

	public Integer getBookSecond() {
		return bookSecond;
	}

	public void setBookSecond(Integer bookSecond) {
		this.bookSecond = bookSecond;
	}

	public Integer getBookThird() {
		return bookThird;
	}

	public void setBookThird(Integer bookThird) {
		this.bookThird = bookThird;
	}

	public Integer getPaperSpecial() {
		return paperSpecial;
	}

	public void setPaperSpecial(Integer paperSpecial) {
		this.paperSpecial = paperSpecial;
	}

	public Integer getPaperFirst() {
		return paperFirst;
	}

	public void setPaperFirst(Integer paperFirst) {
		this.paperFirst = paperFirst;
	}

	public Integer getPaperSecond() {
		return paperSecond;
	}

	public void setPaperSecond(Integer paperSecond) {
		this.paperSecond = paperSecond;
	}

	public Integer getPaperThird() {
		return paperThird;
	}

	public void setPaperThird(Integer paperThird) {
		this.paperThird = paperThird;
	}
}
