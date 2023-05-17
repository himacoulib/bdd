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
import ml.satgrie.pl.ue.services.InstrumentfinancementViewService;
import ml.satgrie.pl.ue.services.SourceFinancementViewService;
import ml.satgrie.pl.ue.utils.HibernateUtils;
import ml.satgrie.pl.ue.utils.Singleton;

@ManagedBean(name = "rapportInstrumentControllerView")
@ViewScoped
public class RapportInstrumentControllerView implements Serializable {

	private static final int ID_PAYS_MALI = 138;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Instrumentfinancement> instruments;

	private Instrumentfinancement selectedInstrument;


	private InstrumentfinancementViewService instrumentService;


	@PostConstruct
	public void init() {
		this.selectedInstrument = new Instrumentfinancement();
		this.instrumentService = new InstrumentfinancementViewService();
		this.instruments = this.instrumentService.findAll();
		

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


	public InstrumentfinancementViewService getInstrumentService() {
		return instrumentService;
	}


	public void setInstrumentService(InstrumentfinancementViewService instrumentService) {
		this.instrumentService = instrumentService;
	}


	public static int getIdPaysMali() {
		return ID_PAYS_MALI;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}