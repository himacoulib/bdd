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
import ml.satgrie.pl.ue.model.Utilisateur;
import ml.satgrie.pl.ue.services.CercleViewService;
import ml.satgrie.pl.ue.services.RegionViewService;
import ml.satgrie.pl.ue.utils.HibernateUtils;
import ml.satgrie.pl.ue.utils.SessionUtils;
import ml.satgrie.pl.ue.utils.Singleton;

@ManagedBean(name = "cercleControllerView")
@ViewScoped
public class CercleControllerView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Cercle> cercles;

	private List<Region> regions;

	private String selectedRegion;

	private List<String> regionsListFilter;

	private Cercle selectedCercle;

	private List<Cercle> selectedCercles;

	private CercleViewService cercleService;

	private RegionViewService regionService;

	private boolean editSelected = false;
	
	@PostConstruct
	public void init() {
		this.selectedCercle = new Cercle();
		this.selectedCercle.setId(new CercleId());
		this.cercleService = new CercleViewService();
		this.cercles = this.cercleService.findAll();
		this.regionService = new RegionViewService();
		this.regions = this.regionService.findAll();

		this.regionsListFilter = new ArrayList<>();

		for (Region region : this.regions) {
			this.regionsListFilter.add(region.getRegionNom());
		}
	}

	public void openNew() {
		this.selectedCercle = new Cercle();
		this.selectedCercle.setId(null);
		this.editSelected = false;
	}

	public void saveCercle() {
		if (this.selectedCercle.getId().getCerlceId() == 0) {
			Region region = null;
			for(Region regionC: this.regions){
				if(regionC.getRegionNom().equals(this.selectedRegion)){
					region = regionC;
					break;
				}
			}
			if(region != null) {
				CercleId cercleId = new CercleId();
				cercleId.setPaysId(region.getPays().getPaysId());
				cercleId.setRegionId(region.getId().getRegionId());
				int min = 1;
				int max = 999_999;
				int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
				cercleId.setCerlceId(randomNum);

				Utilisateur utilisateur = new Utilisateur();
				utilisateur = (Utilisateur) SessionUtils.getRequest().getSession().getAttribute("user");
				this.selectedCercle.setCerlceModifiedBy(utilisateur.getUtilisateurId());
				this.selectedCercle.setCerlceModifiedDate(new Date());
				this.selectedCercle.setId(cercleId);

				try {
					this.cercleService.persist(this.selectedCercle);
					this.cercles.add(this.selectedCercle);
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cercle Ajouter"));
				} catch (Exception e) {
					System.out.println(e);
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Une erreure est survenue"));
				}
			}else{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Region ne peut être vide"));
			}
			

		} else {
			try {

				Utilisateur utilisateur = new Utilisateur();
				utilisateur = (Utilisateur) SessionUtils.getRequest().getSession().getAttribute("user");
				this.selectedCercle.setCerlceModifiedBy(utilisateur.getUtilisateurId());
				this.selectedCercle.setCerlceModifiedDate(new Date());

				this.cercleService.update(this.selectedCercle);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cercle mise à jour"));
			} catch (Exception e) {
				System.out.println(e);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Une erreure est survenue"));
			}

		}

		PrimeFaces.current().executeScript("PF('manageCercleDialog').hide()");
		PrimeFaces.current().ajax().update("form:messages", "form:dt-cercles");
	}

	public void deleteCercle() {
		
		if (this.selectedCercle != null) {
			try{
				this.cercleService.delete(this.selectedCercle.getId().getCerlceId());
				this.cercles.remove(this.selectedCercle);
				this.selectedCercle = null;
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cercle Supprimer"));
				
			}catch (Exception e) {
				System.out.println(e.getMessage());
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Une erreure est survenue"));
			}
		}else
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Aucun cercle selectionné."));

		
		PrimeFaces.current().ajax().update("form:messages", "form:dt-cercles");

	}

	public String getDeleteButtonMessage() {
		if (hasSelectedCercles()) {
			int size = this.selectedCercles.size();
			return size > 1 ? size + " Cercles selected" : "1 cercle selected";
		}

		return "Delete";
	}

	public boolean hasSelectedCercles() {
		return this.selectedCercles != null && !this.selectedCercles.isEmpty();
	}

	public void deleteSelectedCercles() {
		this.cercles.removeAll(this.selectedCercles);
		this.selectedCercles = null;
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cercles Removed"));
		PrimeFaces.current().ajax().update("form:messages", "form:dt-cercles");
	}

	public List<Cercle> getCercles() {
		return cercles;
	}


	public void setCercles(List<Cercle> cercles) {
		this.cercles = cercles;
	}

	public Cercle getSelectedCercle() {
		
		return selectedCercle;
	}

	public void setSelectedCercle(Cercle selectedCercle) {
		this.selectedCercle = selectedCercle;
		this.editSelected = true;
	}

	public CercleViewService getCercleService() {
		return cercleService;
	}

	public void setCercleService(CercleViewService cercleService) {
		this.cercleService = cercleService;
	}

	

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<Cercle> getSelectedCercles() {
		return selectedCercles;
	}

	public void setSelectedCercles(List<Cercle> selectedCercles) {
		this.selectedCercles = selectedCercles;
	}

	public List<Region> getRegions() {
		return regions;
	}

	public void setRegions(List<Region> regions) {
		this.regions = regions;
	}

	public String getSelectedRegion() {
		return selectedRegion;
	}

	public void setSelectedRegion(String selectedRegion) {
		this.selectedRegion = selectedRegion;
	}

	public List<String> getRegionsListFilter() {
		return regionsListFilter;
	}

	public void setRegionsListFilter(List<String> regionsListFilter) {
		this.regionsListFilter = regionsListFilter;
	}

	public RegionViewService getRegionService() {
		return regionService;
	}

	public void setRegionService(RegionViewService regionService) {
		this.regionService = regionService;
	}

	public boolean isEditSelected() {
		return editSelected;
	}

	public void setEditSelected(boolean editSelected) {
		this.editSelected = editSelected;
	}

}