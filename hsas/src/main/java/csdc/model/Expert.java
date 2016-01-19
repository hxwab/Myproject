package csdc.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="T_EXPERT")
@JsonIgnoreProperties({"password"})
public class Expert implements Serializable {
	private static final long serialVersionUID = 7151871470737916338L;

	private String id; //评审专家id（PK）
	private Person person; //人员id（FK）
	private String number; //专家编号
	private String specialityTitle; //专业职称
	private String position; //职务
	private String researchField; //学术研究方向
	private Groups groups; //学科分组
	private Discipline discipline; //学科门类
	private String lastDegree; //最后学位
	private String lastEducation; //最后学历[大专、本科、研究生]
	private Integer isGroupLeader; //是否组长[0:否; 1:是]
	private Integer reviewerType; //评审专家类型[0:初评; 1:复评]
	private Integer isReviewer;//是否参评专家
	private String reviewerYears; //过往参评年份
	private String note;//备注
	private Integer isRecommend;//是否为当年推荐专家[0:否; 1:是]
	private Integer isApplier;//是否当年申报专家[0:否; 1:是]
	
	/**
	 * 将专家设为当年的初评专家
	 */
	@Transient
	public void setReviewer(int reviewType){
		this.isReviewer = 1;
		if(reviewType==0)
			this.reviewerType = 0;
		else if(reviewType==1)
			this.reviewerType = 1;
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy");
		String currentYear = dateformat.format(new Date());
		dealWithReviewerYear(currentYear, 1);
	}
	/**
	 * 将当前专家取消评审资格
	 */
	public void cancelReviewer(){
		this.isReviewer = 0;
		this.reviewerType = null;
		this.isGroupLeader = 0;
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy");
		String currentYear = dateformat.format(new Date());
		dealWithReviewerYear(currentYear, 0);
	}
	
	/**
	 * 参评年份处理方法，往参评年份中添加一个年份 或者删除一个年份
	 * @param year 年份
	 * @param action 要进行的动作 1：添加  0：删除
	 */
	public void dealWithReviewerYear(String year, int action) {
		int num = 0;
		String[] reviewerYear = null;
		if(this.reviewerYears!=null) {
			if(!this.reviewerYears.trim().isEmpty()) {
				reviewerYear = this.reviewerYears.split(";");
				num = reviewerYear.length;
			}
		}
		String[] newreviewerYear = null; 
		if(action==1) {
			newreviewerYear =  new String[num+1];
			for(int i=0; i<num; i++) {
				newreviewerYear[i] = reviewerYear[i];
			}
			newreviewerYear[num] = year;
		} else {
			if(num>0) {
				newreviewerYear =  new String[num];
				for(int i=0; i<num; i++) {
					if(reviewerYear[i].trim().equals(year)){
						newreviewerYear[i] = "";
						continue;
					}
					newreviewerYear[i] = reviewerYear[i];
				}
			}
		}
		StringBuffer sb = new StringBuffer();
		if(newreviewerYear!=null) {
			for(int i=0; i<newreviewerYear.length; i++) {
				if(newreviewerYear[i]!=null||!newreviewerYear[i].trim().isEmpty()) {
					sb.append(";"+newreviewerYear[i]);
				}
			}
			String newyear = sb.toString().substring(1);
			this.reviewerYears =  newyear;
		} else {
			this.reviewerYears = null;
		}
		
	}

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
	
	@Column(name="C_POSITION", length=200)
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}

	@Column(name="C_RESEARCH_FIELD", length=400)  
	public String getResearchField() {
		return researchField;
	}

	public void setResearchField(String researchField) {
		this.researchField = researchField;
	}

	@ManyToOne
	@JoinColumn(name="c_group_id", nullable=false)
	public Groups getGroups() {
		return groups;
	}

	public void setGroups(Groups groups) {
		this.groups = groups;
	}

	
	@ManyToOne
	@JoinColumn(name="c_discipline_id")
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
	
	@Column(name="C_IS_RECOMMEND", scale=1)
	public Integer getIsRecommend() {
		return isRecommend;
	}

	public void setIsRecommend(Integer isRecommend) {
		this.isRecommend = isRecommend;
	}

	@Column(name="C_IS_APPLIER", scale=1)
	public Integer getIsApplier() {
		return isApplier;
	}
	public void setIsApplier(Integer isApplier) {
		this.isApplier = isApplier;
	}
}
