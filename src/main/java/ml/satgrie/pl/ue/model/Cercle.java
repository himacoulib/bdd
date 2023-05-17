package ml.satgrie.pl.ue.model;
// Generated 31 mars 2021 � 17:28:15 by Hibernate Tools 5.2.12.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * Cercle generated by hbm2java
 */
@Entity
@Table(name = "cercle", catalog = "lastuebdd")

public class Cercle implements java.io.Serializable {

	private CercleId id;
	private Region region;
	private String cerlceName;
	private String cerlceLat;
	private String cerlcelong;
	private Integer cerlceModifiedBy;
	private Date cerlceModifiedDate;
	private Set<Commune> communes = new HashSet<Commune>(0);

	@PostConstruct
	public void init() {
		this.id = new CercleId();
	}

	public Cercle() {
	}

	public Cercle(CercleId id, Region region) {
		this.id = id;
		this.region = region;
	}

	public Cercle(CercleId id, Region region, String cerlceName, String cerlceLat, String cerlcelong,
			Integer cerlceModifiedBy, Date cerlceModifiedDate, Set<Commune> communes) {
		this.id = id;
		this.region = region;
		this.cerlceName = cerlceName;
		this.cerlceLat = cerlceLat;
		this.cerlcelong = cerlcelong;
		this.cerlceModifiedBy = cerlceModifiedBy;
		this.cerlceModifiedDate = cerlceModifiedDate;
		this.communes = communes;
	}

	@EmbeddedId

	@AttributeOverrides({ @AttributeOverride(name = "paysId", column = @Column(name = "PaysID", nullable = false)),
			@AttributeOverride(name = "regionId", column = @Column(name = "RegionID", nullable = false)),
			@AttributeOverride(name = "cerlceId", column = @Column(name = "CerlceID", nullable = false)) })
	public CercleId getId() {
		return this.id;
	}

	public void setId(CercleId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "PaysID", referencedColumnName = "PaysID", nullable = false, insertable = false, updatable = false),
			@JoinColumn(name = "RegionID", referencedColumnName = "RegionID", nullable = false, insertable = false, updatable = false) })

	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public Region getRegion() {
		return this.region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	@Column(name = "CerlceName", length = 100)
	public String getCerlceName() {
		return this.cerlceName;
	}

	public void setCerlceName(String cerlceName) {
		this.cerlceName = cerlceName;
	}

	@Column(name = "CerlceLat", length = 45)
	public String getCerlceLat() {
		return this.cerlceLat;
	}

	public void setCerlceLat(String cerlceLat) {
		this.cerlceLat = cerlceLat;
	}

	@Column(name = "Cerlcelong", length = 45)
	public String getCerlcelong() {
		return this.cerlcelong;
	}

	public void setCerlcelong(String cerlcelong) {
		this.cerlcelong = cerlcelong;
	}

	@Column(name = "CerlceModifiedBy")
	public Integer getCerlceModifiedBy() {
		return this.cerlceModifiedBy;
	}

	public void setCerlceModifiedBy(Integer cerlceModifiedBy) {
		this.cerlceModifiedBy = cerlceModifiedBy;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CerlceModifiedDate", length = 19)
	public Date getCerlceModifiedDate() {
		return this.cerlceModifiedDate;
	}

	public void setCerlceModifiedDate(Date cerlceModifiedDate) {
		this.cerlceModifiedDate = cerlceModifiedDate;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cercle")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public Set<Commune> getCommunes() {
		return this.communes;
	}

	public void setCommunes(Set<Commune> communes) {
		this.communes = communes;
	}

}
