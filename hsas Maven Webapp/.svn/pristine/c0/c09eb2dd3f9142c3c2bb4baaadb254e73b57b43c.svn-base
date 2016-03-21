package csdc.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="t_product_author" )
@JsonIgnoreProperties({"password"})
public class ProductAuthor implements Serializable {
	private static final long serialVersionUID = 8736788879213457385L;

	@Id	
	@Column(name="C_ID", unique = true, nullable = false, length=40)
	@GeneratedValue(generator="idGenerator")
    @GenericGenerator(name="idGenerator", strategy="uuid")
	private String id; //作者id(PK)
	
	@ManyToOne
	@JoinColumn(name="c_person_id", nullable = false)
	private Person person; //人员id(FK)
	
	@ManyToOne
	@JoinColumn(name="c_agency_id")
	private Agency agency; //机构id(FK)
	
	@ManyToOne
	@JoinColumn(name="c_product_id")
	private Product product; //成果id(FK)
	
	@Column(name="c_position", length=40)
	private String position; //职称或职务
	
	@Column(name="c_agency_name", length=200)
	private String agencyName; //机构名称
	
	@Column(name="c_is_first_author")  
	private Integer isFirstAuthor; //是否第一作者[0：否；1：是]
	
	@Column(name="c_order")  
	private Integer order; //成员序号
	
	@Column(name="c_work_division", length=800)
	private String workDivision; //分工情况

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Agency getAgency() {
		return agency;
	}

	public void setAgency(Agency agency) {
		this.agency = agency;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getAgencyName() {
		return agencyName;
	}

	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}

	public Integer getIsFirstAuthor() {
		return isFirstAuthor;
	}

	public void setIsFirstAuthor(Integer isFirstAuthor) {
		this.isFirstAuthor = isFirstAuthor;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public String getWorkDivision() {
		return workDivision;
	}

	public void setWorkDivision(String workDivision) {
		this.workDivision = workDivision;
	}
	
}
