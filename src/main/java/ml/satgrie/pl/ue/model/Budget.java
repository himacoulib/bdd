package ml.satgrie.pl.ue.model;
// Generated 31 mars 2021 � 17:28:15 by Hibernate Tools 5.2.12.Final

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.log4j.Logger;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import ml.satgrie.pl.ue.controller.LoginController;

/**
 * 
 * @author drTiefariG4f4W
 *
 */
@Entity
@Table(name = "budget", catalog = "lastuebdd")

public class Budget implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8119711985712540673L;
	private static Logger logger = Logger.getLogger(Budget.class);
	private Integer budgetId;
	private Devise devise;
	private Instrumentfinancement instrumentfinancement;
	private Projet projet;
	private BigDecimal budgetContribution;
	private Float budgetTotal;
	private Float budgetContributionUexof;
	private BigDecimal budgetContributionUeautreDev;
	private Short budgetContributionUepct;
	private BigDecimal budgetAutreContributionXof;
	private Float budgetAutreContributionAutreDev;
	private Short budgetAutreContributionPct;
	private Integer idTypeBudget;
	private int lastModifiedBy;
	private Date lastModifiedDate;

	public Budget() {
	}

	public Budget(Devise devise, Instrumentfinancement instrumentfinancement, Projet projet,
			BigDecimal budgetContribution, Float budgetTotal, Float budgetContributionUexof,
			BigDecimal budgetContributionUeautreDev, Short budgetContributionUepct,
			BigDecimal budgetAutreContributionXof, Float budgetAutreContributionAutreDev,
			Short budgetAutreContributionPct, Integer idTypeBudget, int lastModifiedBy, Date lastModifiedDate) {
		this.devise = devise;
		this.instrumentfinancement = instrumentfinancement;
		this.projet = projet;
		this.budgetContribution = budgetContribution;
		this.budgetTotal = budgetTotal;
		this.budgetContributionUexof = budgetContributionUexof;
		this.budgetContributionUeautreDev = budgetContributionUeautreDev;
		this.budgetContributionUepct = budgetContributionUepct;
		this.budgetAutreContributionXof = budgetAutreContributionXof;
		this.budgetAutreContributionAutreDev = budgetAutreContributionAutreDev;
		this.budgetAutreContributionPct = budgetAutreContributionPct;
		this.idTypeBudget = idTypeBudget;
		this.lastModifiedBy = lastModifiedBy;
		this.lastModifiedDate = lastModifiedDate;
	}

	@Id

	@Column(name = "BudgetID", unique = true, nullable = false)
	public Integer getBudgetId() {
		return this.budgetId;
	}

	public void setBudgetId(Integer budgetId) {
		this.budgetId = budgetId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DeviseID")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public Devise getDevise() {
		return this.devise;
	}

	public void setDevise(Devise devise) {
		this.devise = devise;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "instrumentfinancementID")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public Instrumentfinancement getInstrumentfinancement() {
		return this.instrumentfinancement;
	}

	public void setInstrumentfinancement(Instrumentfinancement instrumentfinancement) {
		this.instrumentfinancement = instrumentfinancement;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ProjetID")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public Projet getProjet() {
		return this.projet;
	}

	public void setProjet(Projet projet) {
		this.projet = projet;
	}

	@Column(name = "BudgetContribution", precision = 20, scale = 0)
	public BigDecimal getBudgetContribution() {
		return this.budgetContribution;
	}

	public void setBudgetContribution(BigDecimal budgetContribution) {
		this.budgetContribution = budgetContribution;
	}

	@Column(name = "BudgetTotal", precision = 12, scale = 0)
	public Float getBudgetTotal() {
		return this.budgetTotal;
	}

	public void setBudgetTotal(Float budgetTotal) {
		this.budgetTotal = budgetTotal;
	}

	@Column(name = "BudgetContributionUEXOF", precision = 12, scale = 0)
	public Float getBudgetContributionUexof() {
		return this.budgetContributionUexof;
	}

	public void setBudgetContributionUexof(Float budgetContributionUexof) {
		this.budgetContributionUexof = budgetContributionUexof;
	}

	@Column(name = "BudgetContributionUEAutreDev", precision = 20, scale = 0)
	public BigDecimal getBudgetContributionUeautreDev() {
		return this.budgetContributionUeautreDev;
	}

	public void setBudgetContributionUeautreDev(BigDecimal budgetContributionUeautreDev) {
		this.budgetContributionUeautreDev = budgetContributionUeautreDev;
	}

	@Column(name = "BudgetContributionUEPct", precision = 3, scale = 0)
	public Short getBudgetContributionUepct() {
		return this.budgetContributionUepct;
	}

	public void setBudgetContributionUepct(Short budgetContributionUepct) {
		this.budgetContributionUepct = budgetContributionUepct;
	}

	@Column(name = "BudgetAutreContributionXOF", precision = 20, scale = 0)
	public BigDecimal getBudgetAutreContributionXof() {
		return this.budgetAutreContributionXof;
	}

	public void setBudgetAutreContributionXof(BigDecimal budgetAutreContributionXof) {
		this.budgetAutreContributionXof = budgetAutreContributionXof;
	}

	@Column(name = "BudgetAutreContributionAutreDev", precision = 12, scale = 0)
	public Float getBudgetAutreContributionAutreDev() {
		return this.budgetAutreContributionAutreDev;
	}

	public void setBudgetAutreContributionAutreDev(Float budgetAutreContributionAutreDev) {
		this.budgetAutreContributionAutreDev = budgetAutreContributionAutreDev;
	}

	@Column(name = "BudgetAutreContributionPct", precision = 3, scale = 0)
	public Short getBudgetAutreContributionPct() {
		return this.budgetAutreContributionPct;
	}

	public void setBudgetAutreContributionPct(Short budgetAutreContributionPct) {
		this.budgetAutreContributionPct = budgetAutreContributionPct;
	}

	@Column(name = "idTypeBudget")
	public Integer getIdTypeBudget() {
		return this.idTypeBudget;
	}

	public void setIdTypeBudget(Integer idTypeBudget) {
		this.idTypeBudget = idTypeBudget;
	}

	@Column(name = "LastModifiedBy")
	public int getLastModifiedBy() {
		return this.lastModifiedBy;
	}

	public void setLastModifiedBy(int lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LastModifiedDate", length = 19)
	public Date getLastModifiedDate() {
		return this.lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

}
