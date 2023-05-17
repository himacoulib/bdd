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
import ml.satgrie.pl.ue.model.Utilisateur;
import ml.satgrie.pl.ue.services.ChapitreViewService;
import ml.satgrie.pl.ue.services.SecteurViewService;
import ml.satgrie.pl.ue.utils.HibernateUtils;
import ml.satgrie.pl.ue.utils.SessionUtils;
import ml.satgrie.pl.ue.utils.Singleton;

@ManagedBean(name = "secteurControllerView")
@ViewScoped
public class SecteurControllerView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Secteur> secteurs;
	
	private List<Chapitre> chapitres;
	
	private boolean editSelected = false;
	
	private String selectedChapitreOption;
	
	private List<String> chapitresListFilter;

	private Secteur selectedSecteur;

	private List<Secteur> selectedSecteurs;

	private SecteurViewService secteurService;

	private ChapitreViewService chapitreService;

	
	@PostConstruct
	public void init() {
		this.selectedSecteur = new Secteur();
		this.selectedSecteur.setId(new SecteurId());
		this.secteurService = new SecteurViewService();
		this.secteurs = this.secteurService.findAll();
		this.chapitreService = new ChapitreViewService();
		this.chapitres = this.chapitreService.findAll();

		this.chapitresListFilter = new ArrayList<String>();

		for (Chapitre chapitre : this.chapitres) {
			this.chapitresListFilter.add(chapitre.getChapitreName());
		}
	}

	public void handleChange() {
		

	}
	
	public void openNew() {
		this.selectedSecteur = new Secteur();
		this.editSelected = false;
	}

	public void saveSecteur() {
		SecteurId secteurIdd = this.selectedSecteur.getId();
		if (secteurIdd.getSecteurId() == 0) {
			Chapitre chapitre = null;
			for (Chapitre chapitreC : this.chapitres) {
				if (chapitreC.getChapitreName().equals(this.selectedChapitreOption)) {
					chapitre = chapitreC;
					break;
				}
			}
			if (chapitre != null) {
				this.selectedSecteur.setChapitre(chapitre);
				
				try {
					SecteurId secteurId = new SecteurId();
					
					int min = 1;
					int max = 999_999;
					int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
					secteurId.setSecteurId(randomNum);
					this.selectedSecteur.setId(secteurId);

					Utilisateur utilisateur = new Utilisateur();
					utilisateur = (Utilisateur) SessionUtils.getRequest().getSession().getAttribute("user");
					this.selectedSecteur.setLastModifiedBy(utilisateur.getUtilisateurId());
					this.selectedSecteur.setLastModifiedDate(new Date());
					
					this.secteurService.persist(this.selectedSecteur);
					this.secteurs.add(this.selectedSecteur);
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Secteur Ajouter"));
				} catch (Exception e) {
					System.out.println(e);
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Une erreure est survenue"));
				}

			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Chapitre ne peut être vide"));
			}

		} else {
			
			try {

				Utilisateur utilisateur = new Utilisateur();
				utilisateur = (Utilisateur) SessionUtils.getRequest().getSession().getAttribute("user");
				this.selectedSecteur.setLastModifiedBy(utilisateur.getUtilisateurId());
				this.selectedSecteur.setLastModifiedDate(new Date());
				
				this.secteurService.update(this.selectedSecteur);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Secteur Mise à jour"));
			} catch (Exception e) {
				System.out.println(e);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Une erreure est survenue"));
			}

		}

		PrimeFaces.current().executeScript("PF('manageSecteurDialog').hide()");
		PrimeFaces.current().ajax().update("form:messages", "form:dt-secteurs");
	}

	public void deleteSecteur() {
		try{
			if (this.selectedSecteur != null) {
				this.secteurService.delete(this.selectedSecteur.getId().getSecteurId());
				this.secteurs.remove(this.selectedSecteur);
				this.selectedSecteur = null;
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Secteur Supprimer"));
				
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Une erreure est survenue."));

		}
		PrimeFaces.current().ajax().update("form:messages", "form:dt-secteurs");

	}

	public String getDeleteButtonMessage() {
		if (hasSelectedSecteurs()) {
			int size = this.selectedSecteurs.size();
			return size > 1 ? size + " Cercles selected" : "1 cercle selected";
		}

		return "Delete";
	}

	public boolean hasSelectedSecteurs() {
		return this.selectedSecteurs != null && !this.selectedSecteurs.isEmpty();
	}

	public void deleteSelectedSecteurs() {
		this.secteurs.removeAll(this.selectedSecteurs);
		this.selectedSecteurs = null;
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Secteurs Removed"));
		PrimeFaces.current().ajax().update("form:messages", "form:dt-secteurs");
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

	public boolean isEditSelected() {
		return editSelected;
	}

	public void setEditSelected(boolean editSelected) {
		this.editSelected = editSelected;
	}

	
	public String getSelectedChapitreOption() {
		return selectedChapitreOption;
	}

	public void setSelectedChapitreOption(String selectedChapitreOption) {
		this.selectedChapitreOption = selectedChapitreOption;
	}

	public List<String> getChapitresListFilter() {
		return chapitresListFilter;
	}

	public void setChapitresListFilter(List<String> chapitresListFilter) {
		this.chapitresListFilter = chapitresListFilter;
	}

	public Secteur getSelectedSecteur() {
		return selectedSecteur;
	}

	public void setSelectedSecteur(Secteur selectedSecteur) {
		this.selectedSecteur = selectedSecteur;
		this.editSelected = true;
	}

	public List<Secteur> getSelectedSecteurs() {
		return selectedSecteurs;
	}

	public void setSelectedSecteurs(List<Secteur> selectedSecteurs) {
		this.selectedSecteurs = selectedSecteurs;
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

	

}