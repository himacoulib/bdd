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
import ml.satgrie.pl.ue.model.Region;
import ml.satgrie.pl.ue.services.CercleViewService;
import ml.satgrie.pl.ue.services.RegionViewService;
import ml.satgrie.pl.ue.utils.HibernateUtils;
import ml.satgrie.pl.ue.utils.Singleton;

@ManagedBean(name = "rapportCercleControllerView")
@ViewScoped
public class RapportCercleControllerView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Cercle> cercles;

	private CercleViewService cercleService;

	private Cercle selectedCercle;

	
	@PostConstruct
	public void init() {
		this.cercleService = new CercleViewService();
		this.cercles = this.cercleService.findAll();
		
	}


	public List<Cercle> getCercles() {
		return cercles;
	}


	public void setCercles(List<Cercle> cercles) {
		this.cercles = cercles;
	}


	public CercleViewService getCercleService() {
		return cercleService;
	}


	public void setCercleService(CercleViewService cercleService) {
		this.cercleService = cercleService;
	}


	public Cercle getSelectedCercle() {
		return selectedCercle;
	}


	public void setSelectedCercle(Cercle selectedCercle) {
		this.selectedCercle = selectedCercle;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}