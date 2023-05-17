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
import ml.satgrie.pl.ue.services.SourceFinancementViewService;
import ml.satgrie.pl.ue.utils.HibernateUtils;
import ml.satgrie.pl.ue.utils.Singleton;

@ManagedBean(name = "rapportSourceControllerView")
@ViewScoped
public class RapportSourceControllerView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<SourceFinancement> sources;
	
	private SourceFinancement selectedSource;

	private SourceFinancementViewService sourceService;

	@PostConstruct
	public void init() {
		this.selectedSource = new SourceFinancement();
		this.sourceService = new SourceFinancementViewService();
		this.sources= this.sourceService.findAll();
	
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