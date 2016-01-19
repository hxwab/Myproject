package csdc.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.struts2.json.annotations.JSON;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.context.annotation.Lazy;

@Entity
@Table(name="t_group" )
@JsonIgnoreProperties({"password"})
public class Groups implements Serializable {
	private static final long serialVersionUID = -2203971275753321562L;
	
//	private Set<Discipline> disciplines;//学科集合
//	private Set<GroupDiscipline> groupDiscipline;//分组-学科关系集合
	
	@Id	
	@Column(name="C_ID", unique = true, nullable = false, length=40)
	@GeneratedValue(generator="idGenerator")
    @GenericGenerator(name="idGenerator", strategy="uuid")
	private String id; //学科分组id(PK)
	
	@Column(name="c_name", length=40)  
	private String name; //分组名
	
	@Column(name="c_description", length=800) 
	private String description; //分组描述
	
	@Column(name="c_index") 
	private Integer index; //分组顺序
	
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
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

//	@Transient
//	public Set<Discipline> getDisciplines() {
//		return disciplines;
//	}
//
//	public void setDisciplines(Set<Discipline> disciplines) {
//		this.disciplines = disciplines;
//	}
//
//	@JSON(serialize=false)
//	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
//	@JoinColumn(name="c_group_id")
//	public Set<GroupDiscipline> getGroupDiscipline() {
//		return groupDiscipline;
//	}
//
//	public void setGroupDiscipline(Set<GroupDiscipline> groupDiscipline) {
//		this.groupDiscipline = groupDiscipline;
//	}
//	
//	public Set<Discipline> obtainDisciplines(){
//		Set<Discipline> disSet = new HashSet<Discipline>();
//		for(GroupDiscipline gd: this.groupDiscipline) {
//			disSet.add(gd.getDiscipline());
//		}
//		return disSet;
//	}
	
}
