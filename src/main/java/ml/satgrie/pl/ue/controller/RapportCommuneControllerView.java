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
import javax.inject.Inject;

import org.primefaces.PrimeFaces;
import org.primefaces.diamond.service.OrderService;

import ml.satgrie.pl.ue.model.Cercle;
import ml.satgrie.pl.ue.model.CercleId;
import ml.satgrie.pl.ue.model.Commune;
import ml.satgrie.pl.ue.model.CommuneId;
import ml.satgrie.pl.ue.model.Region;
import ml.satgrie.pl.ue.services.CercleViewService;
import ml.satgrie.pl.ue.services.CommuneViewService;
import ml.satgrie.pl.ue.services.RegionViewService;
import ml.satgrie.pl.ue.utils.HibernateUtils;
import ml.satgrie.pl.ue.utils.Singleton;

@ManagedBean(name = "rapportCommuneControllerView")
@ViewScoped
public class RapportCommuneControllerView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Commune> communes;
	
	private Commune selectedCommune;
	
	private CommuneViewService communeService;

	@PostConstruct
	public void init() {
		
		this.communeService = new CommuneViewService();
		this.communes = this.communeService.findAll();
		
	}

	public List<Commune> getCommunes() {
		return communes;
	}

	public void setCommunes(List<Commune> communes) {
		this.communes = communes;
	}

	public Commune getSelectedCommune() {
		return selectedCommune;
	}

	public void setSelectedCommune(Commune selectedCommune) {
		this.selectedCommune = selectedCommune;
	}

	public CommuneViewService getCommuneService() {
		return communeService;
	}

	public void setCommuneService(CommuneViewService communeService) {
		this.communeService = communeService;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	

}