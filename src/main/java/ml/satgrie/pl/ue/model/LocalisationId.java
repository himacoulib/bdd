package ml.satgrie.pl.ue.model;
// Generated 31 mars 2021 � 17:28:15 by Hibernate Tools 5.2.12.Final

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * LocalisationId generated by hbm2java
 */
@Embeddable
public class LocalisationId implements java.io.Serializable {

	private int paysId;
	private int localisationId;
	private int regionId;
	private int cerlceId;
	private int communeId;

	public LocalisationId() {
	}

	public LocalisationId(int paysId, int localisationId, int regionId, int cerlceId, int communeId) {
		this.paysId = paysId;
		this.localisationId = localisationId;
		this.regionId = regionId;
		this.cerlceId = cerlceId;
		this.communeId = communeId;
	}

	@Column(name = "PaysID", nullable = false)
	public int getPaysId() {
		return this.paysId;
	}

	public void setPaysId(int paysId) {
		this.paysId = paysId;
	}

	@Column(name = "LocalisationID", nullable = false)
	public int getLocalisationId() {
		return this.localisationId;
	}

	public void setLocalisationId(int localisationId) {
		this.localisationId = localisationId;
	}

	@Column(name = "RegionID", nullable = false)
	public int getRegionId() {
		return this.regionId;
	}

	public void setRegionId(int regionId) {
		this.regionId = regionId;
	}

	@Column(name = "CerlceID", nullable = false)
	public int getCerlceId() {
		return this.cerlceId;
	}

	public void setCerlceId(int cerlceId) {
		this.cerlceId = cerlceId;
	}

	@Column(name = "CommuneID", nullable = false)
	public int getCommuneId() {
		return this.communeId;
	}

	public void setCommuneId(int communeId) {
		this.communeId = communeId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof LocalisationId))
			return false;
		LocalisationId castOther = (LocalisationId) other;

		return (this.getPaysId() == castOther.getPaysId())
				&& (this.getLocalisationId() == castOther.getLocalisationId())
				&& (this.getRegionId() == castOther.getRegionId()) && (this.getCerlceId() == castOther.getCerlceId())
				&& (this.getCommuneId() == castOther.getCommuneId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getPaysId();
		result = 37 * result + this.getLocalisationId();
		result = 37 * result + this.getRegionId();
		result = 37 * result + this.getCerlceId();
		result = 37 * result + this.getCommuneId();
		return result;
	}

}
