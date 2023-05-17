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
import ml.satgrie.pl.ue.model.Utilisateur;
import ml.satgrie.pl.ue.services.ChapitreViewService;
import ml.satgrie.pl.ue.utils.HibernateUtils;
import ml.satgrie.pl.ue.utils.SessionUtils;
import ml.satgrie.pl.ue.utils.Singleton;

@ManagedBean(name = "chapitreControllerView")
@ViewScoped
public class ChapitreControllerView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Chapitre> chapitres;

	private Chapitre selectedChapitre;

	private List<Chapitre> selectedChapitres;

	private ChapitreViewService chapitreService;

	@PostConstruct
	public void init() {
		this.selectedChapitre = new Chapitre();
		this.chapitreService = new ChapitreViewService();
		this.chapitres = this.chapitreService.findAll();

	}

	public void openNew() {
		this.selectedChapitre = new Chapitre();
	}

	public void saveChapitre() {
		Utilisateur utilisateur = new Utilisateur();
		utilisateur = (Utilisateur) SessionUtils.getRequest().getSession().getAttribute("user");
		
		if (this.selectedChapitre.getChapitreId() == null) {
			this.chapitres.add(this.selectedChapitre);
			try {

				
				this.selectedChapitre.setLastModifiedBy(utilisateur.getUtilisateurId());
				this.selectedChapitre.setLastModifiedDate(new Date());
				int min = 1;
				int max = 999_999;
				int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
				this.selectedChapitre.setChapitreId(randomNum);
				this.chapitreService.persist(this.selectedChapitre);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Chapitre Ajouter"));
			} catch (Exception e) {
				System.out.println(e);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Une erreure est survenue"));
			}

		} else {
			try {
				this.selectedChapitre.setLastModifiedBy(utilisateur.getUtilisateurId());
				this.selectedChapitre.setLastModifiedDate(new Date());
				this.chapitreService.update(this.selectedChapitre);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Chapitre mise à jour"));
			} catch (Exception e) {
				System.out.println(e);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Une erreure est survenue"));
			}

		}

		PrimeFaces.current().executeScript("PF('manageChapitreDialog').hide()");
		PrimeFaces.current().ajax().update("form:messages", "form:dt-chapitres");
	}

	public void deleteChapitre() {
		this.chapitres.remove(this.selectedChapitre);
		if (this.selectedChapitre != null) {
			this.chapitreService.delete(this.selectedChapitre.getChapitreId());
			this.chapitreService = null;
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Chapitre Supprimer"));
			PrimeFaces.current().ajax().update("form:messages", "form:dt-chapitres");
		}

	}

	public String getDeleteButtonMessage() {
		if (hasSelectedChapitres()) {
			int size = this.selectedChapitres.size();
			return size > 1 ? size + " Chapitre selected" : "1 Pays selected";
		}

		return "Delete";
	}

	public boolean hasSelectedChapitres() {
		return this.selectedChapitres != null && !this.selectedChapitres.isEmpty();
	}

	public void deleteSelectedChapitres() {
		this.chapitres.removeAll(this.selectedChapitres);
		this.selectedChapitres = null;
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Chapitres Removed"));
		PrimeFaces.current().ajax().update("form:messages", "form:dt-chapitres");
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

	public List<Chapitre> getSelectedChapitres() {
		return selectedChapitres;
	}

	public void setSelectedChapitres(List<Chapitre> selectedChapitres) {
		this.selectedChapitres = selectedChapitres;
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