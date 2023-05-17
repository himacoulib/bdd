package ml.satgrie.pl.ue.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import ml.satgrie.pl.ue.model.Region;
import ml.satgrie.pl.ue.model.RegionId;
import ml.satgrie.pl.ue.services.RegionViewService;
import ml.satgrie.pl.ue.utils.HibernateUtils;

@ManagedBean(name = "rapportRegionControllerView")
@ViewScoped
public class RapportRegionControllerView implements Serializable {

	private static final int ID_PAYS_MALI = 138;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Region> regions;
	
	private Region selectedRegion;
	
	private List<Region> selectedRegions;

	private RegionViewService regionService;


	@PostConstruct
	public void init() {
		this.regionService = new RegionViewService();
		this.regions = this.regionService.findAll();
		
	}


	public List<Region> getRegions() {
		return regions;
	}


	public void setRegions(List<Region> regions) {
		this.regions = regions;
	}


	public Region getSelectedRegion() {
		return selectedRegion;
	}


	public void setSelectedRegion(Region selectedRegion) {
		this.selectedRegion = selectedRegion;
	}


	public List<Region> getSelectedRegions() {
		return selectedRegions;
	}


	public void setSelectedRegions(List<Region> selectedRegions) {
		this.selectedRegions = selectedRegions;
	}


	public RegionViewService getRegionService() {
		return regionService;
	}


	public void setRegionService(RegionViewService regionService) {
		this.regionService = regionService;
	}


	public static int getIdPaysMali() {
		return ID_PAYS_MALI;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}