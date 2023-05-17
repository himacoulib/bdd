package ml.satgrie.pl.ue.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import org.hibernate.id.UUIDGenerator;
import org.primefaces.PrimeFaces;

import ml.satgrie.pl.ue.model.Chapitre;
import ml.satgrie.pl.ue.services.ChapitreViewService;
import ml.satgrie.pl.ue.utils.HibernateUtils;
import ml.satgrie.pl.ue.utils.Singleton;

@ManagedBean(name = "rapportChapitreControllerView")
@ViewScoped
public class RapportChapitreControllerView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Chapitre> chapitres;

	private Chapitre selectedChapitre;

	private ChapitreViewService chapitreService;

	@PostConstruct
	public void init() {
		this.chapitreService = new ChapitreViewService();
		this.chapitres = this.chapitreService.findAll();

	}

	public List<Chapitre> getChapitres() {
		return chapitres;
	}

	public void setChapitres(List<Chapitre> chapitres) {
		this.chapitres = chapitres;
	}

	public Chapitre getSelectedChapitre() {
		return selectedChapitre;
	}

	public void setSelectedChapitre(Chapitre selectedChapitre) {
		this.selectedChapitre = selectedChapitre;
	}

	public ChapitreViewService getChapitreService() {
		return chapitreService;
	}

	public void setChapitreService(ChapitreViewService chapitreService) {
		this.chapitreService = chapitreService;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}