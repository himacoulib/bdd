package ml.satgrie.pl.ue.utils;

import java.io.Serializable;
import java.util.List;

import ml.satgrie.pl.ue.model.Cercle;
import ml.satgrie.pl.ue.model.Commune;
import ml.satgrie.pl.ue.model.Contractant;
import ml.satgrie.pl.ue.model.GetProjectData;
import ml.satgrie.pl.ue.model.Instrumentfinancement;
import ml.satgrie.pl.ue.model.Profile;
import ml.satgrie.pl.ue.model.Projet;
import ml.satgrie.pl.ue.model.Utilisateur;

public class Singleton implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** L'instance statique */
	private Utilisateur utilisateur = null;
	private boolean loggedIn = false;
	private static Singleton instance = null;

	private GetProjectData selectedProjet = null;
	
	private Contractant selectedContractant = null;
	
	private List<Instrumentfinancement> instrumentFilter;
	
	
	private List<Contractant> contractantFilter;
		
	private List<Cercle> cercleFilter;
	
	private List<Commune> communeFilter;
	
	private List<String> instrumentStrings;
	/**
	 * Redinition du constructeur
	 */
	private Singleton() {

	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}


	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}


	/**
	 * @return the instance
	 */
	public static Singleton getInstance() {
		if (instance == null) {
			instance = new Singleton();
		}
		return instance;
	}

	/**
	 * @param instance
	 *            the instance to set
	 */
	public static void setInstance(Singleton instance) {
		Singleton.instance = instance;
	}

	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public GetProjectData getSelectedProjet() {
		return selectedProjet;
	}

	public void setSelectedProjet(GetProjectData selectedProjet) {
		this.selectedProjet = selectedProjet;
	}

	public List<Instrumentfinancement> getInstrumentFilter() {
		return instrumentFilter;
	}

	public void setInstrumentFilter(List<Instrumentfinancement> instrumentFilter) {
		this.instrumentFilter = instrumentFilter;
	}

	public List<Cercle> getCercleFilter() {
		return cercleFilter;
	}

	public void setCercleFilter(List<Cercle> cercleFilter) {
		this.cercleFilter = cercleFilter;
	}

	public Contractant getSelectedContractant() {
		return selectedContractant;
	}

	public void setSelectedContractant(Contractant selectedContractant) {
		this.selectedContractant = selectedContractant;
	}

	public List<Commune> getCommuneFilter() {
		return communeFilter;
	}

	public void setCommuneFilter(List<Commune> communeFilter) {
		this.communeFilter = communeFilter;
	}

	public List<Contractant> getContractantFilter() {
		return contractantFilter;
	}

	public void setContractantFilter(List<Contractant> contractantFilter) {
		this.contractantFilter = contractantFilter;
	}

	public List<String> getInstrumentStrings() {
		return instrumentStrings;
	}

	public void setInstrumentStrings(List<String> instrumentStrings) {
		this.instrumentStrings = instrumentStrings;
	}

	
}
