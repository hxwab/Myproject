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
@Table(name="t_product_review" )
@JsonIgnoreProperties({"password"})
public class ProductReview implements Serializable {
	private static final long serialVersionUID = 2468287737120996202L;

	@Id	
	@Column(name="C_ID", unique = true, nullable = false, length=40)
	@GeneratedValue(generator="idGenerator")
    @GenericGenerator(name="idGenerator", strategy="uuid")
	private String id; //评审id(PK)
	
	@ManyToOne
	@JoinColumn(name="c_expert_id")
	private Expert expert; //专家id(FK)
	
	@Column(name="c_type")  
	private Integer type; //评审类型[0:初评; 1:复评]
	
	@Column(name="c_opinion", length=400)
	private String opinion; //评审意见
	
	@ManyToOne
	@JoinColumn(name="c_product_id")
	private Product product; //成果id(FK)
	
	@Column(name="c_creativity_score")  
	private Integer creativityScore; //创新程度得分
	
	@Column(name="c_research_score")  
	private Integer researchScore; //研究方法得分
	
	@Column(name="c_journal_score")  
	private Integer journalScore; //发表报刊级别得分
	
	@Column(name="c_quote_score")  
	private Integer quoteScore; //转载/应用采纳/引用情况得分
	
	@Column(name="c_social_effect_score")  
	private Integer socialEffectScore; //社会评价得分

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Expert getExpert() {
		return expert;
	}

	public void setExpert(Expert expert) {
		this.expert = expert;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getCreativityScore() {
		return creativityScore;
	}

	public void setCreativityScore(Integer creativityScore) {
		this.creativityScore = creativityScore;
	}

	public Integer getResearchScore() {
		return researchScore;
	}

	public void setResearchScore(Integer researchScore) {
		this.researchScore = researchScore;
	}

	public Integer getJournalScore() {
		return journalScore;
	}

	public void setJournalScore(Integer journalScore) {
		this.journalScore = journalScore;
	}

	public Integer getQuoteScore() {
		return quoteScore;
	}

	public void setQuoteScore(Integer quoteScore) {
		this.quoteScore = quoteScore;
	}

	public Integer getSocialEffectScore() {
		return socialEffectScore;
	}

	public void setSocialEffectScore(Integer socialEffectScore) {
		this.socialEffectScore = socialEffectScore;
	}
}
