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
@Table(name = "localisationsector", catalog = "lastuebdd")

public class LocalisationSecteur implements java.io.Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9069173342381057953L;
	/**
	 * 
	 */
	private static Logger logger = Logger.getLogger(LocalisationSecteur.class);
	private int idLocalisationSecteur;
	private int idProjet;
	private int idLocalisation;
	private int idSecteur;
	
	public LocalisationSecteur() {
	}

	
	public LocalisationSecteur(int idLocalisationSecteur, int idProjet, int idLocalisation, int idSecteur) {
		super();
		this.idLocalisationSecteur = idLocalisationSecteur;
		this.idProjet = idProjet;
		this.idLocalisation = idLocalisation;
		this.idSecteur = idSecteur;
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
		LocalisationSecteur.logger = logger;
	}

	/**
	 * @return the idLocalisationSecteur
	 */
	@Id
	@Column(name = "idLocalisationSecteur", unique = true, nullable = false)
	public int getIdLocalisationSecteur() {
		return idLocalisationSecteur;
	}


	/**
	 * @param idLocalisationSecteur the idLocalisationSecteur to set
	 */
	public void setIdLocalisationSecteur(int idLocalisationSecteur) {
		this.idLocalisationSecteur = idLocalisationSecteur;
	}


	/**
	 * @return the idProjet
	 */
	@Column(name = "idProjetFK")
	public int getIdProjet() {
		return idProjet;
	}


	/**
	 * @param idProjet the idProjet to set
	 */
	public void setIdProjet(int idProjet) {
		this.idProjet = idProjet;
	}


	/**
	 * @return the idLocalisation
	 */
	@Column(name = "idLocalisationFK")
	public int getIdLocalisation() {
		return idLocalisation;
	}


	/**
	 * @param idLocalisation the idLocalisation to set
	 */
	public void setIdLocalisation(int idLocalisation) {
		this.idLocalisation = idLocalisation;
	}


	/**
	 * @return the idSecteur
	 */
	@Column(name = "idSectorFK")
	public int getIdSecteur() {
		return idSecteur;
	}


	/**
	 * @param idSecteur the idSecteur to set
	 */
	public void setIdSecteur(int idSecteur) {
		this.idSecteur = idSecteur;
	}


	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
