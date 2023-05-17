package ml.satgrie.pl.ue.model;
// Generated 31 mars 2021 � 17:28:15 by Hibernate Tools 5.2.12.Final

import java.util.Date;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * Document created by HamayeLAH
 */
@Entity
@Table(name = "document", catalog = "lastuebdd")

public class Document implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer documentId;
	private Projet projet;
	private String documentNom;
	private Integer lastModifiedBy;
	private Date lastModifiedDate;

	public Document() {
	}

	

	public Document(Integer documentId, Projet projet, String documentNom, Integer lastModifiedBy,
			Date lastModifiedDate) {
		super();
		this.documentId = documentId;
		this.projet = projet;
		this.documentNom = documentNom;
		this.lastModifiedBy = lastModifiedBy;
		this.lastModifiedDate = lastModifiedDate;
	}



	@Id

	@Column(name = "DocumentID", unique = true, nullable = false)
	public Integer getDocumentId() {
		return this.documentId;
	}

	public void setDocumentId(Integer documentId) {
		this.documentId = documentId;
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


	@Column(name = "DocumentNom", length = 250)
	public String getDocumentNom() {
		return this.documentNom;
	}

	public void setDocumentNom(String documentNom) {
		this.documentNom = documentNom;
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

}
