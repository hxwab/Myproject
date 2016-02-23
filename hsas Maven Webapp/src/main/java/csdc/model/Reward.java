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
	
	@Column(name="c_level")  
	private Integer level; //等级[0：特等奖；1：一等奖；2：二等奖；3：三等奖]
	
	@Column(name="c_type")  
	private Integer type; //类别[0：著作；2：论文]
	
	@Column(name="c_bonus", length=40)
	private Integer bonus; //金额
	
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

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getBonus() {
		return bonus;
	}

	public void setBonus(Integer bonus) {
		this.bonus = bonus;
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
}
