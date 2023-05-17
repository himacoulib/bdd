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
 * Contractanttype generated by hbm2java
 */
@Entity
@Table(name = "contractanttype", catalog = "lastuebdd")

public class Contractanttype implements java.io.Serializable {

	private Integer contractantTypeId;
	private String contractantTypeNom;
	private int lastModifiedBy;
	private Date lastModifiedDate;
	private Set<Contractant> contractants = new HashSet<Contractant>(0);

	public Contractanttype() {
	}

	public Contractanttype(int lastModifiedBy, Date lastModifiedDate) {
		this.lastModifiedBy = lastModifiedBy;
		this.lastModifiedDate = lastModifiedDate;
	}

	public Contractanttype(String contractantTypeNom, int lastModifiedBy, Date lastModifiedDate,
			Set<Contractant> contractants) {
		this.contractantTypeNom = contractantTypeNom;
		this.lastModifiedBy = lastModifiedBy;
		this.lastModifiedDate = lastModifiedDate;
		this.contractants = contractants;
	}

	@Id

	@Column(name = "ContractantTypeID", unique = true, nullable = false)
	public Integer getContractantTypeId() {
		return this.contractantTypeId;
	}

	public void setContractantTypeId(Integer contractantTypeId) {
		this.contractantTypeId = contractantTypeId;
	}

	@Column(name = "ContractantTypeNom", length = 250)
	public String getContractantTypeNom() {
		return this.contractantTypeNom;
	}

	public void setContractantTypeNom(String contractantTypeNom) {
		this.contractantTypeNom = contractantTypeNom;
	}

	@Column(name = "LastModifiedBy", nullable = false)
	public int getLastModifiedBy() {
		return this.lastModifiedBy;
	}

	public void setLastModifiedBy(int lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LastModifiedDate", nullable = false, length = 19)
	public Date getLastModifiedDate() {
		return this.lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "contractanttype")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public Set<Contractant> getContractants() {
		return this.contractants;
	}

	public void setContractants(Set<Contractant> contractants) {
		this.contractants = contractants;
	}

}
