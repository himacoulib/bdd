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

import ml.satgrie.pl.ue.model.SourceFinancement;
import ml.satgrie.pl.ue.model.Utilisateur;
import ml.satgrie.pl.ue.services.SourceFinancementViewService;
import ml.satgrie.pl.ue.utils.HibernateUtils;
import ml.satgrie.pl.ue.utils.SessionUtils;
import ml.satgrie.pl.ue.utils.Singleton;

@ManagedBean(name = "sourcefinancementControllerView")
@ViewScoped
public class SourceFinancementControllerView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<SourceFinancement> sources;
	
	private SourceFinancement selectedSource;

	private List<SourceFinancement> selectedSources;

	private SourceFinancementViewService sourceService;

	@PostConstruct
	public void init() {
		this.selectedSource = new SourceFinancement();
		this.sourceService = new SourceFinancementViewService();
		this.sources= this.sourceService.findAll();
	
	}

	public void openNew() {
		this.selectedSource= new SourceFinancement();
	}

	public void saveSource() {
		if (this.selectedSource.getSourceFinancementId() == null) {
			
			
			try {

				Utilisateur utilisateur = new Utilisateur();
				utilisateur = (Utilisateur) SessionUtils.getRequest().getSession().getAttribute("user");
				this.selectedSource.setLastModifiedBy(utilisateur.getUtilisateurId());
				this.selectedSource.setLastModifiedDate(new Date());
				int min = 1;
				int max = 999_999;
				int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
				this.selectedSource.setSourceFinancementId(randomNum);
				this.sourceService.persist(this.selectedSource);
				this.sources.add(this.selectedSource);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Source financement Ajouter"));
			} catch (Exception e) {
				System.out.println(e);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Une erreure est survenue"));
			}

		} else {
			
			try {

				Utilisateur utilisateur = new Utilisateur();
				utilisateur = (Utilisateur) SessionUtils.getRequest().getSession().getAttribute("user");
				this.selectedSource.setLastModifiedBy(utilisateur.getUtilisateurId());
				this.selectedSource.setLastModifiedDate(new Date());
				
				this.sourceService.update(this.selectedSource);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Source financement mise à jour"));
			} catch (Exception e) {
				System.out.println(e);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Une erreure est survenue"));
			}

		}

		PrimeFaces.current().executeScript("PF('manageSourceDialog').hide()");
		PrimeFaces.current().ajax().update("form:messages", "form:dt-sources");
	}

	public void deleteSources() {
		try{
			if (this.selectedSource != null) {
				this.sourceService.delete(this.selectedSource.getSourceFinancementId());
				this.sources.remove(this.selectedSource);
				this.selectedSource = null;	
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Source financement Supprimer"));				
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Une erreure est survenue"));
		}
		PrimeFaces.current().ajax().update("form:messages", "form:dt-source");

	}

	public String getDeleteButtonMessage() {
		if (hasSelectedSources()) {
			int size = this.selectedSources.size();
			return size > 1 ? size + " Payss selected" : "1 Source financement selected";
		}

		return "Delete";
	}

	public boolean hasSelectedSources() {
		return this.selectedSources != null && !this.selectedSources.isEmpty();
	}

	public void deleteSelectedSources() {
		this.sources.removeAll(this.selectedSources);
		this.selectedSources = null;
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Sources financement Removed"));
		PrimeFaces.current().ajax().update("form:messages", "form:dt-sources");
	}

	public List<SourceFinancement> getSources() {
		return sources;
	}

	public void setSources(List<SourceFinancement> sources) {
		this.sources = sources;
	}

	public SourceFinancement getSelectedSource() {
		return selectedSource;
	}

	public void setSelectedSource(SourceFinancement selectedSource) {
		this.selectedSource = selectedSource;
	}

	public List<SourceFinancement> getSelectedSources() {
		return selectedSources;
	}

	public void setSelectedSources(List<SourceFinancement> selectedSources) {
		this.selectedSources = selectedSources;
	}

	public SourceFinancementViewService getSourceService() {
		return sourceService;
	}

	public void setSourceService(SourceFinancementViewService sourceService) {
		this.sourceService = sourceService;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	

}