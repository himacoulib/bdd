package ml.satgrie.pl.ue.model;
// Generated 31 mars 2021 � 17:28:15 by Hibernate Tools 5.2.12.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * Devise generated by hbm2java
 */
@Entity
@Table(name = "devise", catalog = "lastuebdd")

public class Devise implements java.io.Serializable {

	private Integer deviseId;
	private String deviseNom;
	private Integer lastModifiedBy;
	private Date lastModifiedDate;
	private String deviseSymb;
	private Set<Budget> budgets = new HashSet<Budget>(0);

	public Devise() {
	}

	public Devise(String deviseNom, Integer lastModifiedBy, Date lastModifiedDate, String deviseSymb,
			Set<Budget> budgets) {
		this.deviseNom = deviseNom;
		this.lastModifiedBy = lastModifiedBy;
		this.lastModifiedDate = lastModifiedDate;
		this.deviseSymb = deviseSymb;
		this.budgets = budgets;
	}

	@Id

	@Column(name = "DeviseID", unique = true, nullable = false)
	public Integer getDeviseId() {
		return this.deviseId;
	}

	public void setDeviseId(Integer deviseId) {
		this.deviseId = deviseId;
	}

	@Column(name = "DeviseNom", length = 250)
	public String getDeviseNom() {
		return this.deviseNom;
	}

	public void setDeviseNom(String deviseNom) {
		this.deviseNom = deviseNom;
	}

	@Column(name = "LastModifiedBy")
	public Integer getLastModifiedBy() {
		return this.lastModifiedBy;
	}

	public void setLastModifiedBy(Integer lastModifiedBy) {
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

	@Column(name = "DeviseSymb", length = 250)
	public String getDeviseSymb() {
		return this.deviseSymb;
	}

	public void setDeviseSymb(String deviseSymb) {
		this.deviseSymb = deviseSymb;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "devise")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public Set<Budget> getBudgets() {
		return this.budgets;
	}

	public void setBudgets(Set<Budget> budgets) {
		this.budgets = budgets;
	}

}
