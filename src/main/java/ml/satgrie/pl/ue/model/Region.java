package ml.satgrie.pl.ue.model;
// Generated 31 mars 2021 � 17:28:15 by Hibernate Tools 5.2.12.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * Region generated by hbm2java
 */
@Entity
@Table(name = "region", catalog = "lastuebdd")

public class Region implements java.io.Serializable {

	private RegionId id;
	private Pays pays;
	private String regionNom;
	private String regionLat;
	private String regionLong;
	private Integer regionLastModifiedBy;
	private Date lastModifiedDate;
	private Set<Cercle> cercles = new HashSet<Cercle>(0);

	public Region() {
	}

	public Region(RegionId id, Pays pays) {
		this.id = id;
		this.pays = pays;
	}

	public Region(RegionId id, Pays pays, String regionNom, String regionLat, String regionLong,
			Integer regionLastModifiedBy, Date lastModifiedDate, Set<Cercle> cercles) {
		this.id = id;
		this.pays = pays;
		this.regionNom = regionNom;
		this.regionLat = regionLat;
		this.regionLong = regionLong;
		this.regionLastModifiedBy = regionLastModifiedBy;
		this.lastModifiedDate = lastModifiedDate;
		this.cercles = cercles;
	}

	@EmbeddedId

	@AttributeOverrides({ @AttributeOverride(name = "paysId", column = @Column(name = "PaysID", nullable = false)),
			@AttributeOverride(name = "regionId", column = @Column(name = "RegionID", nullable = false)) })
	public RegionId getId() {
		return this.id;
	}

	public void setId(RegionId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PaysID", nullable = false, insertable = false, updatable = false)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public Pays getPays() {
		return this.pays;
	}

	public void setPays(Pays pays) {
		this.pays = pays;
	}

	@Column(name = "RegionNom", length = 45)
	public String getRegionNom() {
		return this.regionNom;
	}

	public void setRegionNom(String regionNom) {
		this.regionNom = regionNom;
	}

	@Column(name = "RegionLat", length = 45)
	public String getRegionLat() {
		return this.regionLat;
	}

	public void setRegionLat(String regionLat) {
		this.regionLat = regionLat;
	}

	@Column(name = "RegionLong", length = 45)
	public String getRegionLong() {
		return this.regionLong;
	}

	public void setRegionLong(String regionLong) {
		this.regionLong = regionLong;
	}

	@Column(name = "RegionLastModifiedBy")
	public Integer getRegionLastModifiedBy() {
		return this.regionLastModifiedBy;
	}

	public void setRegionLastModifiedBy(Integer regionLastModifiedBy) {
		this.regionLastModifiedBy = regionLastModifiedBy;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LastModifiedDate", length = 19)
	public Date getLastModifiedDate() {
		return this.lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "region")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public Set<Cercle> getCercles() {
		return this.cercles;
	}

	public void setCercles(Set<Cercle> cercles) {
		this.cercles = cercles;
	}

}
