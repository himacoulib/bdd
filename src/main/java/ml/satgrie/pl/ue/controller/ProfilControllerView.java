package ml.satgrie.pl.ue.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import org.primefaces.PrimeFaces;

import ml.satgrie.pl.ue.model.Profile;
import ml.satgrie.pl.ue.model.Utilisateur;
import ml.satgrie.pl.ue.services.ProfileViewService;
import ml.satgrie.pl.ue.utils.HibernateUtils;
import ml.satgrie.pl.ue.utils.SessionUtils;
import ml.satgrie.pl.ue.utils.Singleton;

@ManagedBean(name = "profilControllerView")
@ViewScoped
public class ProfilControllerView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Profile> profils;

	private Profile selectedProfil;

	private List<Profile> selectedProfils;

	private ProfileViewService profilService;

	@PostConstruct
	public void init() {
		this.selectedProfil = new Profile();
		this.profilService = new ProfileViewService();
		this.profils = this.profilService.findAll();

	}

	public void openNew() {
		this.selectedProfil = new Profile();

	}

	public void saveProfil() {
		if (this.selectedProfil.getProfileId() == null) {
			try {
				
				this.selectedProfil.setLastModifiedDate(new Date());

				Utilisateur utilisateur = new Utilisateur();
				utilisateur = (Utilisateur) SessionUtils.getRequest().getSession().getAttribute("user");
				this.selectedProfil.setLastModifiedBy(utilisateur.getUtilisateurId());
				int min = 1;
				int max = 999_999;
				int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
				this.selectedProfil.setProfileId(randomNum);
				this.profilService.persist(this.selectedProfil);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Profile Ajouter"));
				this.profils.add(this.selectedProfil);
			} catch (Exception e) {
				System.out.println(e);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Une erreure est survenue"));
			}

		} else {
			try {
				this.selectedProfil.setLastModifiedDate(new Date());

				Utilisateur utilisateur = new Utilisateur();
				utilisateur = (Utilisateur) SessionUtils.getRequest().getSession().getAttribute("user");
				this.selectedProfil.setLastModifiedBy(utilisateur.getUtilisateurId());
				this.profilService.update(this.selectedProfil);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Profile mise à jour"));

			} catch (Exception e) {
				System.out.println(e);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Une erreure est survenue"));
			}

		}

		PrimeFaces.current().executeScript("PF('manageProfilDialog').hide()");
		PrimeFaces.current().ajax().update("form:messages", "form:dt-profils");
	}

	public void deleteSelectedProfil() {
		
		if (this.selectedProfil != null) {
			try{
				this.profilService.delete(this.selectedProfil.getProfileId());
				this.profils.remove(this.selectedProfil);
				this.selectedProfil = null;
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Profil Supprimer"));
			}catch (Exception e) {
				System.out.println(e.getMessage());
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Une erreure est survenue"));
			}
			
			PrimeFaces.current().ajax().update("form:messages", "form:dt-profils");
		}

	}

	public String getDeleteButtonMessage() {
		if (hasSelectedProfils()) {
			int size = this.selectedProfils.size();
			return size > 1 ? size + " profils selected" : "1 region selected";
		}

		return "Delete";
	}

	public boolean hasSelectedProfils() {
		return this.selectedProfils != null && !this.selectedProfils.isEmpty();
	}

	public void deleteSelectedProfils() {
		this.profils.removeAll(this.selectedProfils);
		this.selectedProfils = null;
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Profils Removed"));
		PrimeFaces.current().ajax().update("form:messages", "form:dt-profils");
	}

	public List<Profile> getProfils() {
		return profils;
	}

	public void setProfils(List<Profile> profils) {
		this.profils = profils;
	}

	public Profile getSelectedProfil() {
		return selectedProfil;
	}

	public void setSelectedProfil(Profile selectedProfil) {
		this.selectedProfil = selectedProfil;
	}

	public List<Profile> getSelectedProfils() {
		return selectedProfils;
	}

	public void setSelectedProfils(List<Profile> selectedProfils) {
		this.selectedProfils = selectedProfils;
	}

	public ProfileViewService getProfilService() {
		return profilService;
	}

	public void setProfilService(ProfileViewService profilService) {
		this.profilService = profilService;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}