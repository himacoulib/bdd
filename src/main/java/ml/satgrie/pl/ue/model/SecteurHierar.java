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
@Table(name = "sectorhierar", catalog = "lastuebdd")

public class SecteurHierar implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6380761954396947598L;
	/**
	 * 
	 */
	private static Logger logger = Logger.getLogger(SecteurHierar.class);
	private int secteurHierarId;
	private String codeSecteur;
	private String secteurNom;
	private int secteurParentId;
	private String formerSectorType;
	private int formerSectorId;
	
	public SecteurHierar() {
	}

	public SecteurHierar(int secteurHierarId, String codeSecteur, String secteurNom, int secteurParentId,
			String formerSectorType, int formerSectorId) {
		super();
		this.secteurHierarId = secteurHierarId;
		this.codeSecteur = codeSecteur;
		this.secteurNom = secteurNom;
		this.secteurParentId = secteurParentId;
		this.formerSectorType = formerSectorType;
		this.formerSectorId = formerSectorId;
	}

	/**
	 * @return the logger
	 */
	public static Logger getLogger() {
		return logger;
	}

	/**
	 * @param logger the logger to set
	 */
	public static void setLogger(Logger logger) {
		SecteurHierar.logger = logger;
	}

	/**
	 * @return the secteurHierarId
	 */
	@Id
	@Column(name = "idSectorHierar", unique = true, nullable = false)
	public int getSecteurHierarId() {
		return secteurHierarId;
	}

	/**
	 * @param secteurHierarId the secteurHierarId to set
	 */
	public void setSecteurHierarId(int secteurHierarId) {
		this.secteurHierarId = secteurHierarId;
	}

	/**
	 * @return the codeSecteur
	 */

	@Column(name = "codeSector", length = 45)
	public String getCodeSecteur() {
		return codeSecteur;
	}

	/**
	 * @param codeSecteur the codeSecteur to set
	 */
	public void setCodeSecteur(String codeSecteur) {
		this.codeSecteur = codeSecteur;
	}

	/**
	 * @return the secteurNom
	 */

	@Column(name = "nameSector", length = 2500)
	public String getSecteurNom() {
		return secteurNom;
	}

	/**
	 * @param secteurNom the secteurNom to set
	 */
	public void setSecteurNom(String secteurNom) {
		this.secteurNom = secteurNom;
	}

	/**
	 * @return the secteurParentId
	 */
	@Column(name = "sectorParentId")
	public int getSecteurParentId() {
		return secteurParentId;
	}

	/**
	 * @param secteurParentId the secteurParentId to set
	 */
	public void setSecteurParentId(int secteurParentId) {
		this.secteurParentId = secteurParentId;
	}

	/**
	 * @return the formerSectorType
	 */
	@Column(name = "formerSectorType")
	public String getFormerSectorType() {
		return formerSectorType;
	}

	/**
	 * @param formerSectorType the formerSectorType to set
	 */
	public void setFormerSectorType(String formerSectorType) {
		this.formerSectorType = formerSectorType;
	}

	/**
	 * @return the formerSectorId
	 */
	@Column(name = "formerSectorId")
	public int getFormerSectorId() {
		return formerSectorId;
	}

	/**
	 * @param formerSectorId the formerSectorId to set
	 */
	public void setFormerSectorId(int formerSectorId) {
		this.formerSectorId = formerSectorId;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
