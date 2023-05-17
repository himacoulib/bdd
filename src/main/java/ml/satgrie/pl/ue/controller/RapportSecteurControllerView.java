package ml.satgrie.pl.ue.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import org.primefaces.PrimeFaces;

import ml.satgrie.pl.ue.model.Cercle;
import ml.satgrie.pl.ue.model.Chapitre;
import ml.satgrie.pl.ue.model.Region;
import ml.satgrie.pl.ue.model.Secteur;
import ml.satgrie.pl.ue.model.SecteurId;
import ml.satgrie.pl.ue.services.ChapitreViewService;
import ml.satgrie.pl.ue.services.SecteurViewService;
import ml.satgrie.pl.ue.utils.HibernateUtils;
import ml.satgrie.pl.ue.utils.Singleton;

@ManagedBean(name = "rapportSecteurControllerView")
@ViewScoped
public class RapportSecteurControllerView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Secteur> secteurs;
	
	
	private Secteur selectedSecteur;


	private SecteurViewService secteurService;


	
	@PostConstruct
	public void init() {
		this.secteurService = new SecteurViewService();
		this.secteurs = this.secteurService.findAll();
		
	}



	public List<Secteur> getSecteurs() {
		return secteurs;
	}



	public void setSecteurs(List<Secteur> secteurs) {
		this.secteurs = secteurs;
	}



	public Secteur getSelectedSecteur() {
		return selectedSecteur;
	}



	public void setSelectedSecteur(Secteur selectedSecteur) {
		this.selectedSecteur = selectedSecteur;
	}



	public SecteurViewService getSecteurService() {
		return secteurService;
	}



	public void setSecteurService(SecteurViewService secteurService) {
		this.secteurService = secteurService;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}