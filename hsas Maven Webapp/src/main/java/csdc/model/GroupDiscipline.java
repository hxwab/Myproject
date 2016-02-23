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
@Table(name="t_group_discipline" )
@JsonIgnoreProperties({"password"})
public class GroupDiscipline implements Serializable {
	private static final long serialVersionUID = -8463079995730285826L;

	@Id	
	@Column(name="C_ID", unique = true, nullable = false, length=40)
	@GeneratedValue(generator="idGenerator")
    @GenericGenerator(name="idGenerator", strategy="uuid")
	private String id; //分组学科门类id(PK)
	
	@ManyToOne
	@JoinColumn(name="c_group_id")
	private Group group; //分组id(FK)
	
	@ManyToOne
	@JoinColumn(name="c_discipline_id")
	private Discipline discipline; //学科门类id(FK)

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public Discipline getDiscipline() {
		return discipline;
	}

	public void setDiscipline(Discipline discipline) {
		this.discipline = discipline;
	}

}
