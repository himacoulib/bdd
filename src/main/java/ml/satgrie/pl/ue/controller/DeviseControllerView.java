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

import ml.satgrie.pl.ue.model.Devise;
import ml.satgrie.pl.ue.model.Utilisateur;
import ml.satgrie.pl.ue.services.DeviseViewService;
import ml.satgrie.pl.ue.utils.HibernateUtils;
import ml.satgrie.pl.ue.utils.SessionUtils;
import ml.satgrie.pl.ue.utils.Singleton;

@ManagedBean(name = "deviseControllerView")
@ViewScoped
public class DeviseControllerView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Devise> devises;

	private Devise selectedDevise;

	private List<Devise> selectedDevises;

	private DeviseViewService deviseService;

	@PostConstruct
	public void init() {
		this.selectedDevise = new Devise();
		this.deviseService = new DeviseViewService();
		this.devises = this.deviseService.findAll();

	}

	public void openNew() {
		this.selectedDevise = new Devise();
	}

	public void saveDevise() {

		Utilisateur utilisateur = new Utilisateur();
		utilisateur = (Utilisateur) SessionUtils.getRequest().getSession().getAttribute("user");
		if (this.selectedDevise.getDeviseId() == null) {
			try {

				this.selectedDevise.setLastModifiedBy(utilisateur.getUtilisateurId());
				this.selectedDevise.setLastModifiedDate(new Date());
				int min = 1;
				int max = 999_999;
				int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
				this.selectedDevise.setDeviseId(randomNum);
				this.deviseService.persist(this.selectedDevise);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Devise Ajouter"));
				this.devises.add(this.selectedDevise);
			} catch (Exception e) {
				System.out.println(e);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Une erreure est survenue"));
			}

		} else {

			try {
				this.selectedDevise.setLastModifiedBy(utilisateur.getUtilisateurId());
				this.selectedDevise.setLastModifiedDate(new Date());
				this.deviseService.update(this.selectedDevise);
				this.devises.add(this.selectedDevise);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Devise mise à jour"));
			} catch (Exception e) {
				System.out.println(e);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Une erreure est survenue"));
			}

		}

		PrimeFaces.current().executeScript("PF('manageDeviseDialog').hide()");
		PrimeFaces.current().ajax().update("form:messages", "form:dt-devises");
	}

	public void deleteDevise() {
		try {
			if (this.selectedDevise != null) {
				this.deviseService.delete(this.selectedDevise.getDeviseId());
				this.devises.remove(this.selectedDevise);
				this.selectedDevise = null;
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Devise Supprimer"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Une erreure est survenue"));
		}
		PrimeFaces.current().ajax().update("form:messages", "form:dt-devises");

	}

	public String getDeleteButtonMessage() {
		if (hasSelectedDevises()) {
			int size = this.selectedDevises.size();
			return size > 1 ? size + " devises selected" : "1 devise selected";
		}

		return "Delete";
	}

	public boolean hasSelectedDevises() {
		return this.selectedDevises != null && !this.selectedDevises.isEmpty();
	}

	public void deleteSelectedDevises() {
		this.devises.removeAll(this.selectedDevises);
		this.selectedDevises = null;
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("devises Removed"));
		PrimeFaces.current().ajax().update("form:messages", "form:dt-devises");
	}

	public List<Devise> getDevises() {
		return devises;
	}

	public void setDevises(List<Devise> devises) {
		this.devises = devises;
	}

	public Devise getSelectedDevise() {
		return selectedDevise;
	}

	public void setSelectedDevise(Devise selectedDevise) {
		this.selectedDevise = selectedDevise;
	}

	public List<Devise> getSelectedDevises() {
		return selectedDevises;
	}

	public void setSelectedDevises(List<Devise> selectedDevises) {
		this.selectedDevises = selectedDevises;
	}

	public DeviseViewService getDeviseService() {
		return deviseService;
	}

	public void setDeviseService(DeviseViewService deviseService) {
		this.deviseService = deviseService;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}