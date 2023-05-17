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
import javax.inject.Inject;

import org.primefaces.PrimeFaces;
import org.primefaces.diamond.service.OrderService;

import ml.satgrie.pl.ue.model.Pays;
import ml.satgrie.pl.ue.model.Utilisateur;
import ml.satgrie.pl.ue.services.PaysViewService;
import ml.satgrie.pl.ue.utils.HibernateUtils;
import ml.satgrie.pl.ue.utils.SessionUtils;
import ml.satgrie.pl.ue.utils.Singleton;

@ManagedBean(name = "paysControllerView")
@ViewScoped
public class PaysControllerView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Pays> payss;
	
	private List<String> regionsListFilter;

	private Pays selectedPays;

	private List<Pays> selectedPayss;

	private PaysViewService paysService;

	@PostConstruct
	public void init() {
		this.selectedPays= new Pays();
		this.paysService = new PaysViewService();
		this.payss= this.paysService.findAll();
	
	}

	public void openNew() {
		this.selectedPays = new Pays();
	}

	public void savePays() {
		
		if (this.selectedPays.getPaysId() == null) {
			
			try {

				Utilisateur utilisateur = new Utilisateur();
				utilisateur = (Utilisateur) SessionUtils.getRequest().getSession().getAttribute("user");
				this.selectedPays.setPaysLastModifiedBy(utilisateur.getUtilisateurId());
				this.selectedPays.setLastMidifiedDate(new Date());
				int min = 1 ;
				int max = 999_999 ;
				int randomNum = ThreadLocalRandom.current().nextInt( min , max + 1 );
				this.selectedPays.setPaysId(randomNum);
				this.paysService.persist(this.selectedPays);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Pays Ajouter"));
				this.payss.add(this.selectedPays);
				this.selectedPays.setPaysId(null);
			} catch (Exception e) {
				System.out.println(e);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Une erreure est survenue"));
			}

		} else {
			
			try {

				Utilisateur utilisateur = new Utilisateur();
				utilisateur = (Utilisateur) SessionUtils.getRequest().getSession().getAttribute("user");
				this.selectedPays.setPaysLastModifiedBy(utilisateur.getUtilisateurId());
				this.selectedPays.setLastMidifiedDate(new Date());
				this.selectedPays.setPaysId(this.selectedPays.getPaysId());
				this.paysService.update(this.selectedPays);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Pays mise à jour"));
			} catch (Exception e) {
				System.out.println(e);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Une erreure est survenue"));
			}

		}

		PrimeFaces.current().executeScript("PF('managePaysDialog').hide()");
		PrimeFaces.current().ajax().update("form:messages", "form:dt-pays");
	}

	public void deletePays() {
		try{
			if (this.selectedPays != null) {
				this.paysService.delete(this.selectedPays.getPaysId());
				this.payss.remove(this.selectedPays);
				this.selectedPays = null;
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Pays Supprimer"));
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Une erreure est survenue"));

		}
		PrimeFaces.current().ajax().update("form:messages", "form:dt-pays");


	}

	public String getDeleteButtonMessage() {
		if (hasSelectedPayss()) {
			int size = this.selectedPayss.size();
			return size > 1 ? size + " Payss selected" : "1 Pays selected";
		}

		return "Delete";
	}

	public boolean hasSelectedPayss() {
		return this.selectedPayss != null && !this.selectedPayss.isEmpty();
	}

	public void deleteSelectedPayss() {
		this.payss.removeAll(this.selectedPayss);
		this.selectedPayss = null;
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Payss Removed"));
		PrimeFaces.current().ajax().update("form:messages", "form:dt-pays");
	}

	public List<Pays> getPayss() {
		return payss;
	}

	public void setPayss(List<Pays> payss) {
		this.payss = payss;
	}

	public List<String> getRegionsListFilter() {
		return regionsListFilter;
	}

	public void setRegionsListFilter(List<String> regionsListFilter) {
		this.regionsListFilter = regionsListFilter;
	}

	public Pays getSelectedPays() {
		return selectedPays;
	}

	public void setSelectedPays(Pays selectedPays) {
		this.selectedPays = selectedPays;
	}

	public List<Pays> getSelectedPayss() {
		return selectedPayss;
	}

	public void setSelectedPayss(List<Pays> selectedPayss) {
		this.selectedPayss = selectedPayss;
	}

	public PaysViewService getPaysService() {
		return paysService;
	}

	public void setPaysService(PaysViewService paysService) {
		this.paysService = paysService;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}