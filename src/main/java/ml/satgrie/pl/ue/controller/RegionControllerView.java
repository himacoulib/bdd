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

import ml.satgrie.pl.ue.model.Pays;
import ml.satgrie.pl.ue.model.Region;
import ml.satgrie.pl.ue.model.RegionId;
import ml.satgrie.pl.ue.model.Utilisateur;
import ml.satgrie.pl.ue.services.PaysViewService;
import ml.satgrie.pl.ue.services.RegionViewService;
import ml.satgrie.pl.ue.utils.HibernateUtils;
import ml.satgrie.pl.ue.utils.SessionUtils;
import ml.satgrie.pl.ue.utils.Singleton;

@ManagedBean(name = "regionControllerView")
@ViewScoped
public class RegionControllerView implements Serializable {

	private static final int ID_PAYS_MALI = 138;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Region> regions;
	
	private Region selectedRegion;
	
	private List<Region> selectedRegions;

	private RegionViewService regionService;

	private PaysViewService paysService;

	@PostConstruct
	public void init() {
		this.selectedRegion = new Region();
		this.selectedRegion.setId(new RegionId());
		this.regionService = new RegionViewService();
		this.regions = this.regionService.findAll();
		this.paysService = new PaysViewService();
		
	}

	public void openNew() {
		this.selectedRegion= new Region();
	}

	public void saveRegion() {
		if (this.selectedRegion.getId().getRegionId() == 0) {
			Pays pays = paysService.findById(ID_PAYS_MALI);
			if (pays != null) {
				this.selectedRegion.setPays(pays);
				
				try {

					Utilisateur utilisateur = new Utilisateur();
					utilisateur = (Utilisateur) SessionUtils.getRequest().getSession().getAttribute("user");
					this.selectedRegion.setRegionLastModifiedBy(utilisateur.getUtilisateurId());
					this.selectedRegion.setLastModifiedDate(new Date());
					this.selectedRegion.setPays(pays);
					RegionId regionId = new RegionId();
					regionId.setPaysId(pays.getPaysId());
					int min = 1;
					int max = 999_999;
					int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
					regionId.setRegionId(randomNum);
					this.selectedRegion.setId(regionId);
					this.regionService.persist(this.selectedRegion);
					this.regions.add(this.selectedRegion);
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Region Ajouter"));
				} catch (Exception e) {
					System.out.println(e);
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Une erreure est survenue"));
				}

			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Pays ne peut être vide"));
			}

		} else {
			
			try {

				Utilisateur utilisateur = new Utilisateur();
				utilisateur = (Utilisateur) SessionUtils.getRequest().getSession().getAttribute("user");
				this.selectedRegion.setRegionLastModifiedBy(utilisateur.getUtilisateurId());
				this.selectedRegion.setLastModifiedDate(new Date());

				this.regionService.update(this.selectedRegion);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Region mise à jour"));
			} catch (Exception e) {
				System.out.println(e);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Une erreure est survenue"));
			}

		}

		PrimeFaces.current().executeScript("PF('manageRegionDialog').hide()");
		PrimeFaces.current().ajax().update("form:messages", "form:dt-regions");
	}

	public void deleteRegion() {
		
		if (this.selectedRegion != null) {
			try{
				this.regionService.delete(this.selectedRegion.getId().getRegionId());
				this.regions.remove(this.selectedRegion);
				this.selectedRegion = null;
			}catch (Exception e) {
				System.out.println(e.getMessage());
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Une erreure est survenue."));
			}
			
			
			PrimeFaces.current().ajax().update("form:messages", "form:dt-regions");
		}

	}

	public String getDeleteButtonMessage() {
		if (hasSelectedRegions()) {
			int size = this.selectedRegions.size();
			return size > 1 ? size + " regions selected" : "1 region selected";
		}

		return "Delete";
	}

	public boolean hasSelectedRegions() {
		return this.selectedRegions != null && !this.selectedRegions.isEmpty();
	}

	public void deleteSelectedRegions() {
		this.regions.removeAll(this.selectedRegions);
		this.selectedRegions = null;
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("regions Removed"));
		PrimeFaces.current().ajax().update("form:messages", "form:dt-regions");
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

	public PaysViewService getPaysService() {
		return paysService;
	}

	public void setPaysService(PaysViewService paysService) {
		this.paysService = paysService;
	}

	public static int getIdPaysMali() {
		return ID_PAYS_MALI;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
}