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

import ml.satgrie.pl.ue.model.Instrumentfinancement;
import ml.satgrie.pl.ue.model.SourceFinancement;
import ml.satgrie.pl.ue.model.Utilisateur;
import ml.satgrie.pl.ue.services.InstrumentfinancementViewService;
import ml.satgrie.pl.ue.services.SourceFinancementViewService;
import ml.satgrie.pl.ue.utils.HibernateUtils;
import ml.satgrie.pl.ue.utils.SessionUtils;
import ml.satgrie.pl.ue.utils.Singleton;

@ManagedBean(name = "instrumentControllerView")
@ViewScoped
public class InstrumentControllerView implements Serializable {

	private static final int ID_PAYS_MALI = 138;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Instrumentfinancement> instruments;

	private Instrumentfinancement selectedInstrument;

	private List<Instrumentfinancement> selectedInstruments;

	private boolean editSelected = false;

	private String selectedSource;

	private List<String> listSource;

	private SourceFinancementViewService sourceService;

	private InstrumentfinancementViewService instrumentService;

	private List<SourceFinancement> sources;

	@PostConstruct
	public void init() {
		this.selectedInstrument = new Instrumentfinancement();
		this.instrumentService = new InstrumentfinancementViewService();
		this.instruments = this.instrumentService.findAll();
		this.sourceService = new SourceFinancementViewService();
		this.sources = this.sourceService.findAll();
		this.listSource = new ArrayList<String>();

		for (SourceFinancement sourceC : this.sources) {
			this.listSource.add(sourceC.getSourceFinancementNom());
		}

	}

	public void openNew() {
		this.selectedInstrument = new Instrumentfinancement();
		this.editSelected = false;
	//	PrimeFaces.current().ajax().update("form:manage-product-content");
	}

	public void saveInstrument() {
		
		if (this.selectedInstrument.getInstrumentfinancementId() == null) {
			SourceFinancement source = null;
			for (SourceFinancement sourceC : this.sources) {
				if (sourceC.getSourceFinancementNom().equals(this.selectedSource)) {
					source = sourceC;
					break;
				}
			}
			if (source != null) {

				try {
					this.selectedInstrument.setSourcefinancement(source);
					this.selectedInstrument.setLastModifiedDate(new Date());

					Utilisateur utilisateur = new Utilisateur();
					utilisateur = (Utilisateur) SessionUtils.getRequest().getSession().getAttribute("user");
					this.selectedInstrument
							.setLastModifiedBy(utilisateur.getUtilisateurId());
					int min = 1;
					int max = 999_999;
					int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
					this.selectedInstrument.setInstrumentfinancementId(randomNum);
					this.instrumentService.persist(this.selectedInstrument);
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage("Instrument financement Ajouter"));

					this.instruments.add(this.selectedInstrument);
				} catch (Exception e) {
					System.out.println(e);
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Une erreure est survenue"));
				}

			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Source financement ne peut être vide"));
			}

		} else {
			SourceFinancement source = null;
			for (SourceFinancement sourceC : this.sources) {
				if (sourceC.getSourceFinancementNom().equals(this.selectedSource)) {
					source = sourceC;
					break;
				}
			}
			if (source != null) {
				try {

					Utilisateur utilisateur = new Utilisateur();
					utilisateur = (Utilisateur) SessionUtils.getRequest().getSession().getAttribute("user");
					this.selectedInstrument.setSourcefinancement(source);
					this.selectedInstrument.setLastModifiedDate(new Date());
					this.selectedInstrument
							.setLastModifiedBy(utilisateur.getUtilisateurId());
					this.instrumentService.update(this.selectedInstrument);
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage("Instrument financement mise à jour"));

				} catch (Exception e) {
					System.out.println(e);
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Une erreure est survenue"));
				}
			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Source financement ne peut être vide"));

			}

		}

		PrimeFaces.current().executeScript("PF('manageInstrumentDialog').hide()");
		PrimeFaces.current().ajax().update("form:messages", "form:dt-instrument");
	}

	public void deleteSelectedInstrument() {
		try {
			
			if (this.selectedInstrument != null) {
				this.instrumentService.delete(this.selectedInstrument.getInstrumentfinancementId());
				this.instruments.remove(this.selectedInstrument);
				this.selectedInstrument = null;
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Instrument Supprimer"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Une erreure est survenue"));
		}
		PrimeFaces.current().ajax().update("form:messages", "form:dt-instrument");
	}

	public String getDeleteButtonMessage() {
		if (hasSelectedInstruments()) {
			int size = this.selectedInstruments.size();
			return size > 1 ? size + " regions selected" : "1 region selected";
		}

		return "Delete";
	}

	public boolean hasSelectedInstruments() {
		return this.selectedInstruments != null && !this.selectedInstruments.isEmpty();
	}

	public void deleteSelectedInstruments() {
		this.instruments.removeAll(this.selectedInstruments);
		this.selectedInstruments = null;
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("instruments Removed"));
		PrimeFaces.current().ajax().update("form:messages", "form:dt-instruments");
	}

	public List<Instrumentfinancement> getInstruments() {
		return instruments;
	}

	public void setInstruments(List<Instrumentfinancement> instruments) {
		this.instruments = instruments;
	}

	public Instrumentfinancement getSelectedInstrument() {
		return selectedInstrument;
	}

	public void setSelectedInstrument(Instrumentfinancement selectedInstrument) {
		this.selectedInstrument = selectedInstrument;
	}

	public List<Instrumentfinancement> getSelectedInstruments() {
		return selectedInstruments;
	}

	public void setSelectedInstruments(List<Instrumentfinancement> selectedInstruments) {
		this.selectedInstruments = selectedInstruments;
	}

	public boolean isEditSelected() {
		return editSelected;
	}

	public void setEditSelected(boolean editSelected) {
		this.editSelected = editSelected;
	}

	public String getSelectedSource() {
		return selectedSource;
	}

	public void setSelectedSource(String selectedSource) {
		this.selectedSource = selectedSource;
	}

	public List<String> getListSource() {
		return listSource;
	}

	public void setListSource(List<String> listSource) {
		this.listSource = listSource;
	}

	public SourceFinancementViewService getSourceService() {
		return sourceService;
	}

	public void setSourceService(SourceFinancementViewService sourceService) {
		this.sourceService = sourceService;
	}

	public InstrumentfinancementViewService getInstrumentService() {
		return instrumentService;
	}

	public void setInstrumentService(InstrumentfinancementViewService instrumentService) {
		this.instrumentService = instrumentService;
	}

	public List<SourceFinancement> getSources() {
		return sources;
	}

	public void setSources(List<SourceFinancement> sources) {
		this.sources = sources;
	}

	public static int getIdPaysMali() {
		return ID_PAYS_MALI;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}