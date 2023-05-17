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
import ml.satgrie.pl.ue.model.Chapitre;
import ml.satgrie.pl.ue.model.Region;
import ml.satgrie.pl.ue.model.Secteur;
import ml.satgrie.pl.ue.model.SousSecteur;
import ml.satgrie.pl.ue.model.SousSecteurId;
import ml.satgrie.pl.ue.model.Utilisateur;
import ml.satgrie.pl.ue.services.CercleViewService;
import ml.satgrie.pl.ue.services.ChapitreViewService;
import ml.satgrie.pl.ue.services.RegionViewService;
import ml.satgrie.pl.ue.services.SecteurViewService;
import ml.satgrie.pl.ue.services.SousSecteurViewService;
import ml.satgrie.pl.ue.utils.HibernateUtils;
import ml.satgrie.pl.ue.utils.SessionUtils;
import ml.satgrie.pl.ue.utils.Singleton;

@ManagedBean(name = "soussecteurControllerView")
@ViewScoped
public class SousSecteurControllerView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<SousSecteur> soussecteurs;
	
	private SousSecteur selectedSousSecteur;

	private List<Secteur> secteurs;
	
	private List<Chapitre> chapitres;

	private String selectedSecteurString;

	private String selectedChapitreString;

	private SousSecteurViewService soussecteurService;

	private SecteurViewService secteurService;
	
	private ChapitreViewService chapitreService;

	private boolean editSelected;

	
	@PostConstruct
	public void init() {
		this.selectedSousSecteur = new SousSecteur();
		this.selectedSousSecteur.setId(new SousSecteurId());
		
		this.secteurService = new SecteurViewService();
		this.chapitreService = new ChapitreViewService();
		this.soussecteurService = new SousSecteurViewService();
		
		this.secteurs = this.secteurService.findAll();
		this.soussecteurs = this.soussecteurService.findAll();
		this.chapitres = this.chapitreService.findAll();
		
		
	}

	public void openNew() {
		this.selectedSousSecteur = new SousSecteur();
		this.selectedSousSecteur.setId(null);
		this.editSelected = false;
	}

	public void saveSousSecteur() {
		if (this.selectedSousSecteur.getId().getSousSecteurId() == 0) {
			Secteur secteur = null;
			for(Secteur secteurC: this.secteurs){
				if(secteurC.getSecteurName().equals(this.selectedSecteurString)){
					secteur = secteurC;
					break;
				}
			}
			Chapitre chapitre = null;
			for(Chapitre chapitreC: this.chapitres){
				if(chapitreC.getChapitreName().equals(this.selectedChapitreString)){
					chapitre = chapitreC;
					break;
				}
			}
			
			if(secteur != null && chapitre != null) {
				SousSecteurId soussecteurId = new SousSecteurId();
				soussecteurId.setSecteurId(secteur.getId().getSecteurId());
				soussecteurId.setChapitreId(chapitre.getChapitreId());
				
				int min = 1;
				int max = 999_999;
				int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
				soussecteurId.setSousSecteurId(randomNum);
				this.selectedSousSecteur.setId(soussecteurId);

				Utilisateur utilisateur = new Utilisateur();
				utilisateur = (Utilisateur) SessionUtils.getRequest().getSession().getAttribute("user");
				this.selectedSousSecteur.setLastModifiedBy(utilisateur.getUtilisateurId());
				this.selectedSousSecteur.setLastModifiedDate(new Date());

				try {
					this.soussecteurService.persist(this.selectedSousSecteur);
					this.soussecteurs.add(this.selectedSousSecteur);
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Sous secteur Ajouter"));
				} catch (Exception e) {
					System.out.println(e);
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Une erreure est survenue"));
				}
			}else{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Verifier le champ secteur et chapitre."));
			}
			
		} else {
			try {

				Utilisateur utilisateur = new Utilisateur();
				utilisateur = (Utilisateur) SessionUtils.getRequest().getSession().getAttribute("user");
				this.selectedSousSecteur.setLastModifiedBy(utilisateur.getUtilisateurId());
				this.selectedSousSecteur.setLastModifiedDate(new Date());

				this.soussecteurService.update(this.selectedSousSecteur);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Sous secteur mise à jour"));
			} catch (Exception e) {
				System.out.println(e);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Une erreure est survenue"));
			}

		}

		PrimeFaces.current().executeScript("PF('manageSousSecteurDialog').hide()");
		PrimeFaces.current().ajax().update("form:messages", "form:dt-soussecteurs");
	}

	public void deleteSousSecteur() {
		
		if (this.selectedSousSecteur != null) {
			try{
				this.soussecteurService.delete(this.selectedSousSecteur.getId().getSousSecteurId());
				this.soussecteurs.remove(this.selectedSousSecteur);
				this.selectedSousSecteur = null;
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Sous secteur Supprimer"));
				
			}catch (Exception e) {
				System.out.println(e.getMessage());
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Une erreure est survenue"));
			}
		}else
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Aucun sous secteur selectionné."));

		
		PrimeFaces.current().ajax().update("form:messages", "form:dt-cercles");

	}

	public List<SousSecteur> getSoussecteurs() {
		return soussecteurs;
	}

	public void setSoussecteurs(List<SousSecteur> soussecteurs) {
		this.soussecteurs = soussecteurs;
	}

	public SousSecteur getSelectedSousSecteur() {
		return selectedSousSecteur;
	}

	public void setSelectedSousSecteur(SousSecteur selectedSousSecteur) {
		this.selectedSousSecteur = selectedSousSecteur;
		this.editSelected = true;
	}

	public List<Secteur> getSecteurs() {
		return secteurs;
	}

	public void setSecteurs(List<Secteur> secteurs) {
		this.secteurs = secteurs;
	}

	public List<Chapitre> getChapitres() {
		return chapitres;
	}

	public void setChapitres(List<Chapitre> chapitres) {
		this.chapitres = chapitres;
	}

	public String getSelectedSecteurString() {
		return selectedSecteurString;
	}

	public void setSelectedSecteurString(String selectedSecteurString) {
		this.selectedSecteurString = selectedSecteurString;
	}

	public String getSelectedChapitreString() {
		return selectedChapitreString;
	}

	public void setSelectedChapitreString(String selectedChapitreString) {
		this.selectedChapitreString = selectedChapitreString;
	}

	public SousSecteurViewService getSoussecteurService() {
		return soussecteurService;
	}

	public void setSoussecteurService(SousSecteurViewService soussecteurService) {
		this.soussecteurService = soussecteurService;
	}

	public SecteurViewService getSecteurService() {
		return secteurService;
	}

	public void setSecteurService(SecteurViewService secteurService) {
		this.secteurService = secteurService;
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

	public boolean isEditSelected() {
		return editSelected;
	}

	public void setEditSelected(boolean editSelected) {
		this.editSelected = editSelected;
	}

	


}