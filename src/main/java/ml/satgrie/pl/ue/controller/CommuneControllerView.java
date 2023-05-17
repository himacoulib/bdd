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
import ml.satgrie.pl.ue.model.Utilisateur;
import ml.satgrie.pl.ue.services.CercleViewService;
import ml.satgrie.pl.ue.services.CommuneViewService;
import ml.satgrie.pl.ue.services.RegionViewService;
import ml.satgrie.pl.ue.utils.HibernateUtils;
import ml.satgrie.pl.ue.utils.SessionUtils;
import ml.satgrie.pl.ue.utils.Singleton;

@ManagedBean(name = "communeControllerView")
@ViewScoped
public class CommuneControllerView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Commune> communes;

	private Commune selectedCommune;

	private List<Region> regions;

	private String selectedRegion;

	private List<Cercle> cerclesListFilter;

	private List<Cercle> cercles;

	private Cercle selectedCercle;

	private String selectedCercleString;

	private List<String> regionsListFilter;

	private List<Commune> selectedCommunes;

	private CommuneViewService communeService;

	private CercleViewService cercleService;

	private RegionViewService regionService;

	private boolean editSelected = false;

	@PostConstruct
	public void init() {
		this.selectedCommune = new Commune();
		this.selectedCommune.setId(new CommuneId());

		this.communeService = new CommuneViewService();
		this.communes = this.communeService.findAll();

		this.cercleService = new CercleViewService();
		this.cercles = cercleService.findAll();
		this.regionService = new RegionViewService();
		this.regions = this.regionService.findAll();

		this.regionsListFilter = new ArrayList<>();

		for (Region region : this.regions) {
			this.regionsListFilter.add(region.getRegionNom());
		}
		this.cerclesListFilter = Singleton.getInstance().getCercleFilter();
	}

	public void openNew() {
		this.selectedCommune = new Commune();
		this.editSelected = false;
	}

	public void saveCommune() {
		if (this.selectedCommune.getId().getCommuneId() == 0) {
			Cercle cercle = null;
			for (Cercle cercleC : this.cercles) {
				if (cercleC.getCerlceName().equals(this.selectedCercleString)) {
					cercle = cercleC;
					break;
				}
			}
			if (cercle != null) {
				CommuneId communeId = new CommuneId();
				communeId.setPaysId(cercle.getRegion().getPays().getPaysId());
				communeId.setRegionId(cercle.getRegion().getId().getRegionId());
				communeId.setCerlceId(cercle.getId().getCerlceId());
				int min = 1;
				int max = 999_999;
				int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
				communeId.setCommuneId(randomNum);
				this.selectedCommune.setId(communeId);

				Utilisateur utilisateur = new Utilisateur();
				utilisateur = (Utilisateur) SessionUtils.getRequest().getSession().getAttribute("user");
				this.selectedCommune
						.setCommuneLastModifiedBy(utilisateur.getUtilisateurId());
				this.selectedCommune.setCommuneLastModifiedDate(new Date());
				this.selectedCommune.setCercle(cercle);
				try {

					this.communeService.persist(this.selectedCommune);
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Commune Ajouter"));
					this.communes.add(this.selectedCommune);
					Singleton.getInstance().setCercleFilter(null);

				} catch (Exception e) {
					System.out.println(e);
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Une erreure est survenue"));
				}

			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cercle ne peut être vide"));
			}

		} else {
			try {

				Utilisateur utilisateur = new Utilisateur();
				utilisateur = (Utilisateur) SessionUtils.getRequest().getSession().getAttribute("user");
				this.selectedCommune
						.setCommuneLastModifiedBy(utilisateur.getUtilisateurId());
				this.selectedCommune.setCommuneLastModifiedDate(new Date());
				this.communeService.update(this.selectedCommune);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Commune Mise à jour"));
			} catch (Exception e) {
				System.out.println(e);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Une erreure est survenue"));
			}

		}

		PrimeFaces.current().executeScript("PF('manageCommuneDialog').hide()");
		PrimeFaces.current().ajax().update("form:messages", "form:dt-communes");
	}

	public void deleteCommunes() {
		try{
			
			if (this.selectedCommune != null) {
				this.communeService.delete(this.selectedCommune.getId().getCommuneId());				
				this.communes.remove(this.selectedCommune);
				this.selectedCommune = null;
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Commune Supprimer"));
			}
		}catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Une erreure est survenue"));
		}
		
		PrimeFaces.current().ajax().update("form:messages", "form:dt-communes");

	}

	public void handleRegionChange() {
		this.cerclesListFilter = new ArrayList<>();
		for (Cercle cercleC : this.cercles) {
			if (cercleC.getRegion().getRegionNom().equals(this.selectedRegion)) {
				this.cerclesListFilter.add(cercleC);
			}
		}
		Singleton.getInstance().setCercleFilter(this.cerclesListFilter);
		PrimeFaces.current().ajax().update("form:cercle");

	}

	public String getDeleteButtonMessage() {
		if (hasSelectedCommunes()) {
			int size = this.selectedCommunes.size();
			return size > 1 ? size + " Communes selected" : "1 commune selected";
		}

		return "Delete";
	}

	public boolean hasSelectedCommunes() {
		return this.selectedCommunes != null && !this.selectedCommunes.isEmpty();
	}

	public void deleteSelectedCommunes() {
		this.cercles.removeAll(this.selectedCommunes);
		this.selectedCommunes = null;
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Communes Removed"));
		PrimeFaces.current().ajax().update("form:messages", "form:dt-communes");
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
		this.editSelected = true;
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

	public List<Cercle> getCerclesListFilter() {
		return cerclesListFilter;
	}

	public void setCerclesListFilter(List<Cercle> cerclesListFilter) {
		this.cerclesListFilter = cerclesListFilter;
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

	public List<String> getRegionsListFilter() {
		return regionsListFilter;
	}

	public void setRegionsListFilter(List<String> regionsListFilter) {
		this.regionsListFilter = regionsListFilter;
	}

	public List<Commune> getSelectedCommunes() {
		return selectedCommunes;
	}

	public void setSelectedCommunes(List<Commune> selectedCommunes) {
		this.selectedCommunes = selectedCommunes;
	}

	public CommuneViewService getCommuneService() {
		return communeService;
	}

	public void setCommuneService(CommuneViewService communeService) {
		this.communeService = communeService;
	}

	public CercleViewService getCercleService() {
		return cercleService;
	}

	public void setCercleService(CercleViewService cercleService) {
		this.cercleService = cercleService;
	}

	public RegionViewService getRegionService() {
		return regionService;
	}

	public void setRegionService(RegionViewService regionService) {
		this.regionService = regionService;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public boolean isEditSelected() {
		return editSelected;
	}

	public void setEditSelected(boolean editSelected) {
		this.editSelected = editSelected;
	}

	public String getSelectedCercleString() {
		return selectedCercleString;
	}

	public void setSelectedCercleString(String selectedCercleString) {
		this.selectedCercleString = selectedCercleString;
	}

}