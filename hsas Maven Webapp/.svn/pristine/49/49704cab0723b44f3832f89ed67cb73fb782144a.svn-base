package csdc.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="T_EXPERT" )
@JsonIgnoreProperties({"password"})
public class Expert implements Serializable {
	private static final long serialVersionUID = 7151871470737916338L;

	private String id; //评审专家id（PK）
	private Person person; //人员id（FK）
	private String number; //专家编号
	private String specialityTitle; //专业职称
	private String researchField; //学术研究方向
	private Group group; //学科分组
	private Discipline discipline; //学科门类
	private String lastDegree; //最后学位
	private String lastEducation; //最后学历[大专、本科、研究生]
	private Integer isGroupLeader; //是否组长[0:否; 1:是]
	private Integer reviewerType; //评审专家类型[0:初评; 1:复评]
	private Integer isReviewer;//是否参评专家
	private String reviewerYears; //过往参评年份
	private String note;//备注
	private String addYears;//推送专家年份

	@Id	
	@Column(name="C_ID", length=40)  
	@GeneratedValue(generator="idGenerator")
    @GenericGenerator(name="idGenerator", strategy="uuid")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@OneToOne
	@JoinColumn(name="C_PERSON_ID", nullable=false)
	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	@Column(name="C_NUMBER", length=40, nullable=false)
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
	@Column(name="C_SPECIALITY_TITLE", length=200)
	public String getSpecialityTitle() {
		return specialityTitle;
	}

	public void setSpecialityTitle(String specialityTitle) {
		this.specialityTitle = specialityTitle;
	}

	@Column(name="C_RESEARCH_FIELD", length=400)  
	public String getResearchField() {
		return researchField;
	}

	public void setResearchField(String researchField) {
		this.researchField = researchField;
	}

	@ManyToOne
	@JoinColumn(name="c_group_id")
	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	
	@ManyToOne
	@JoinColumn(name="c_discipline_id", nullable=false)
	public Discipline getDiscipline() {
		return discipline;
	}

	public void setDiscipline(Discipline discipline) {
		this.discipline = discipline;
	}

	@Column(name="C_LAST_DEGREE", length=40)
	public String getLastDegree() {
		return lastDegree;
	}

	public void setLastDegree(String lastDegree) {
		this.lastDegree = lastDegree;
	}
	
	@Column(name="C_LAST_EDUCATION", length=40)
	public String getLastEducation() {
		return lastEducation;
	}

	public void setLastEducation(String lastEducation) {
		this.lastEducation = lastEducation;
	}

	@Column(name="C_REVIEWER_YEARS", length=200) 
	public String getReviewerYears() {
		return reviewerYears;
	}

	public void setReviewerYears(String reviewerYears) {
		this.reviewerYears = reviewerYears;
	}
	
	@Column(name="C_IS_GROUP_LEADER", scale=1)
	public Integer getIsGroupLeader() {
		return isGroupLeader;
	}

	public void setIsGroupLeader(Integer isGroupLeader) {
		this.isGroupLeader = isGroupLeader;
	}

	@Column(name="C_REVIEWER_TYPE", scale=1)
	public Integer getReviewerType() {
		return reviewerType;
	}

	public void setReviewerType(Integer reviewerType) {
		this.reviewerType = reviewerType;
	}

	@Column(name="C_IS_REVIEWER", scale=1)
	public Integer getIsReviewer() {
		return isReviewer;
	}

	public void setIsReviewer(Integer isReviewer) {
		this.isReviewer = isReviewer;
	}

	@Column(name="C_NOTE", length=1000)
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	@Column(name="C_ADD_YEARS", length=200)
	public String getAddYears() {
		return addYears;
	}

	public void setAddYears(String addYears) {
		this.addYears = addYears;
	}
}
