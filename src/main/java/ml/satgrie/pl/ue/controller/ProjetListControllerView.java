package ml.satgrie.pl.ue.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.bean.ViewScoped;

import org.primefaces.PrimeFaces;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.SelectEvent;
import org.primefaces.util.ComponentUtils;

import ml.satgrie.pl.ue.model.Budget;
import ml.satgrie.pl.ue.model.Calendrier;
import ml.satgrie.pl.ue.model.Cercle;
import ml.satgrie.pl.ue.model.Chapitre;
import ml.satgrie.pl.ue.model.Commune;
import ml.satgrie.pl.ue.model.Contractant;
import ml.satgrie.pl.ue.model.Contractanttype;
import ml.satgrie.pl.ue.model.Etatavancement;
import ml.satgrie.pl.ue.model.GetProjectData;
import ml.satgrie.pl.ue.model.Instrumentfinancement;
import ml.satgrie.pl.ue.model.Localisation;
import ml.satgrie.pl.ue.model.LocalisationSecteur;
import ml.satgrie.pl.ue.model.Projet;
import ml.satgrie.pl.ue.model.Projetchapitre;
import ml.satgrie.pl.ue.model.Region;
import ml.satgrie.pl.ue.model.Secteur;
import ml.satgrie.pl.ue.model.SecteurHierar;
import ml.satgrie.pl.ue.model.SourceFinancement;
import ml.satgrie.pl.ue.model.Utilisateur;
import ml.satgrie.pl.ue.services.BudgetViewService;
import ml.satgrie.pl.ue.services.CalendrierViewService;
import ml.satgrie.pl.ue.services.CercleViewService;
import ml.satgrie.pl.ue.services.ChapitreViewService;
import ml.satgrie.pl.ue.services.CommuneViewService;
import ml.satgrie.pl.ue.services.ContractantViewService;
import ml.satgrie.pl.ue.services.ContractanttypeViewService;
import ml.satgrie.pl.ue.services.EtatavancementViewService;
import ml.satgrie.pl.ue.services.InstrumentfinancementViewService;
import ml.satgrie.pl.ue.services.LocalisationSecteurViewService;
import ml.satgrie.pl.ue.services.LocalisationViewService;
import ml.satgrie.pl.ue.services.ProjetViewService;
import ml.satgrie.pl.ue.services.ProjetchapitreViewService;
import ml.satgrie.pl.ue.services.RegionViewService;
import ml.satgrie.pl.ue.services.SecteurHierarViewService;
import ml.satgrie.pl.ue.services.SecteurViewService;
import ml.satgrie.pl.ue.services.SourceFinancementViewService;
import ml.satgrie.pl.ue.utils.SessionUtils;
import ml.satgrie.pl.ue.utils.Singleton;

@ManagedBean(name = "projetListControllerView")
@ViewScoped
public class ProjetListControllerView implements Serializable {

	/**
	 * 
	 */


	private static final long serialVersionUID = 1L;

	private List<Chapitre> chapList;

	private List<Etatavancement> etatavancements;
	private EtatavancementViewService etViewService;
	private List<Projet> projets;

	private GetProjectData selectedProjet;

	private ProjetViewService projetService;

	private List<String> sourceStrings = new ArrayList<>();

	private List<String> statusString = new ArrayList<>();

	private List<String> chapitreStrings = new ArrayList<>();

	private List<String> secteurStrings = new ArrayList<>();

	private List<String> contractantStrings = new ArrayList<>();

	private List<String> contractanttypeStrings = new ArrayList<>();

	private List<String> regionStrings = new ArrayList<>();

	private List<String> cercleStrings = new ArrayList<>();

	private int budget;

	private String statut;

	private SourceFinancementViewService sourceService;

	private InstrumentfinancementViewService instrumentService;

	private ChapitreViewService chapitreService;

	private SecteurViewService secteurService;

	private ContractantViewService contractantService;

	private ContractanttypeViewService contractanttypeService;

	private RegionViewService regionService;

	private CercleViewService cercleService;

	private List<SourceFinancement> sources = new ArrayList<>();

	private List<Instrumentfinancement> instruments = new ArrayList<>();

	private List<Instrumentfinancement> instrumentsFilter = new ArrayList<>();

	private List<Chapitre> chapitres = new ArrayList<>();

	private List<Secteur> secteurs = new ArrayList<>();

	private List<Contractant> contractants = new ArrayList<>();

	private List<Contractant> contractantsFilter = new ArrayList<>();

	private List<Contractanttype> contractanttypes = new ArrayList<>();

	private List<Region> regions = new ArrayList<>();

	private List<Cercle> cercles = new ArrayList<>();

	private List<Cercle> cerclesFilter = new ArrayList<>();

	private List<Budget> budgets = new ArrayList<>();

	private BudgetViewService budgetService;

	private List<Projetchapitre> projetchapitres;

	private List<Localisation> localisations = new ArrayList<>();

	private LocalisationViewService localisationService;
	private List<Projetchapitre> projetChapitres;

	private CalendrierViewService calendrierService;

	private RegionViewService regionViewService;

	private CommuneViewService communeService;

	private CercleViewService cercleCervice;

	private ProjetchapitreViewService projchapitreService;

	private LocalisationSecteurViewService localisationSecteurViewService;

	private SecteurHierarViewService secteurHierarService;

	private List<SecteurHierar> secteurHierars;

	private List<Commune> communes;

	private List<Calendrier> calendriers;
	private List<String> statusProjet;

    private String selectedAutoProjets;
    private List<GetProjectData> filterProjectDatas;


	private List<LocalisationSecteur> localisationSecteurs;
	private List<GetProjectData> getProjectDatas;
	private Utilisateur utilisateur;

	private Object selectedAutoGlobalProjets;
	@PostConstruct
	public void init() {
		Singleton.getInstance().setSelectedProjet(null);
		this.etViewService = new EtatavancementViewService();
		this.communeService = new CommuneViewService();
		this.regionViewService = new RegionViewService();
		this.cercleCervice = new CercleViewService();
		this.projetService = new ProjetViewService();
		this.calendrierService = new CalendrierViewService();
		this.budgetService = new BudgetViewService();
		this.contractantService = new ContractantViewService();
		this.instrumentService = new InstrumentfinancementViewService();
		this.secteurService = new SecteurViewService();
		this.projchapitreService = new ProjetchapitreViewService();
		this.chapitreService = new ChapitreViewService();
		this.localisationService = new LocalisationViewService();
		this.localisationSecteurViewService = new LocalisationSecteurViewService();
		this.secteurHierarService = new SecteurHierarViewService();
		this.sourceService = new SourceFinancementViewService();
		this.contractanttypeService = new ContractanttypeViewService();
		this.calendrierService = new CalendrierViewService();

		this.etatavancements = etViewService.findAll();
		this.calendriers = calendrierService.findAll();
		this.secteurHierars = secteurHierarService.findAll();
		this.sources = sourceService.findAll();
		this.communes = communeService.findAll();
		this.cercles = cercleCervice.findAll();
		this.regions = this.regionViewService.findAll();
		this.projets = this.projetService.findAll();
		this.budgets = this.budgetService.findAll();
		this.contractants = this.contractantService.findAll();
		this.instruments = this.instrumentService.findAll();
		this.secteurs = this.secteurService.findAll();
		this.projetChapitres = this.projchapitreService.findAll();
		this.chapitres = this.chapitreService.findAll();
		this.localisations = this.localisationService.findAll();
		this.localisationSecteurs = this.localisationSecteurViewService.findAll();
		this.contractanttypes = this.contractanttypeService.findAll();

		this.getProjectDatas = getUserProjet();
		
	}
	
	public List<GetProjectData> getUserProjet() {
		GetProjectData getProjectDataTmp = new GetProjectData();
		this.utilisateur = new Utilisateur();
		this.utilisateur = (Utilisateur) SessionUtils.getRequest().getSession().getAttribute("user");
		List<GetProjectData> userProjects = new ArrayList<GetProjectData>();
		if (this.utilisateur.getSourcefinancement().getSourceFinancementNom().equals("UE")) {
			this.getProjectDatas = new ArrayList<GetProjectData>();
			this.getProjectDatas = getProjectDataTmp.getProjectAllInfo(this.projets, this.budgets, localisationSecteurs,
					secteurHierars, localisations, getProjectDatas, regions, cercles, communes, contractants,
					calendriers, contractanttypes, this.etatavancements, chapitres);
			return getProjectDatas;
		} else {
			this.getProjectDatas = new ArrayList<GetProjectData>();
			this.getProjectDatas = getProjectDataTmp.getProjectAllInfo(this.projets, this.budgets, localisationSecteurs,
					secteurHierars, localisations, getProjectDatas, regions, cercles, communes, contractants,
					calendriers, contractanttypes, this.etatavancements, chapitres);
			for (GetProjectData getProjectDataForUser : getProjectDatas) {
				for (SourceFinancement sourceFinancementByUser : getProjectDataForUser.getProjetSourceFinancements()) {
					if (this.utilisateur.getSourcefinancement().getSourceFinancementNom()
							.equals(sourceFinancementByUser.getSourceFinancementNom())) {
						userProjects.add(getProjectDataForUser);
					}
				}

			}
			 return userProjects;
		}
	}
	
	  public List<String> completeNumContrat(String query) {
	        String queryLowerCase = query.toLowerCase();
	        List<String> projectNumContrat = new ArrayList<String>();
	        for (GetProjectData data : this.getProjectDatas) {
	        	projectNumContrat.add(data.getProjetNumContrat());
			}
	        
	        return projectNumContrat.stream().filter(t -> t.toLowerCase().startsWith(queryLowerCase)).collect(Collectors.toList());
	    }
	  
	  public List<String> completeTitleGlobal(String query) {
	        String queryLowerCase = query.toLowerCase();
	        List<String> projectNumContrat = new ArrayList<String>();
	        for (GetProjectData data : this.getProjectDatas) {
	        	projectNumContrat.add(data.getProjetTitre());
			}
	        
	        return projectNumContrat.stream().filter(t -> t.toLowerCase().contains(queryLowerCase)).collect(Collectors.toList());
	    }

	  public void onItemNumContratSelect(SelectEvent<String> event) throws IOException {
	        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Numero de contrat: ", event.getObject()));
	        FacesContext context = FacesContext.getCurrentInstance();

	        DataTable table = (DataTable)FacesContext.getCurrentInstance().getViewRoot().findComponent("form:dt-projet");
	        table.clearInitialState();
	        table.clearLazyCache();
	        table.reset();
	        table.resetRows();
	        table.resetValue();
	        table.getAttributes();
	        table.setRowStatePreserved(false);
	        if (selectedAutoProjets != null) {
	        	GetProjectData  projToShow = new GetProjectData();
	        	 for (GetProjectData projetData : this.getProjectDatas) {
	  				if (projetData.getProjetNumContrat().equals(selectedAutoProjets)) {
	  					projToShow = projetData;
	  				}
	  			}
	        	this.getProjectDatas = new ArrayList<GetProjectData>();
				this.getProjectDatas.add(projToShow);
			}else {
				this.getProjectDatas = getUserProjet();
			}
	        PrimeFaces.current().ajax().update("form");
			context.getExternalContext().getFlash().setKeepMessages(true);
	    }
	  
	  public void onItemTitleGlobalSelect(SelectEvent<String> event) throws IOException {
	        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Projet: ", event.getObject()));
	        FacesContext context = FacesContext.getCurrentInstance();

	        DataTable table = (DataTable)FacesContext.getCurrentInstance().getViewRoot().findComponent("form:dt-projet");
	        table.clearInitialState();
	        table.clearLazyCache();
	        table.reset();
	        table.resetRows();
	        table.resetValue();
	        table.getAttributes();
	        table.setRowStatePreserved(false);
	        if (selectedAutoGlobalProjets != null) {
	        	GetProjectData  projToShow = new GetProjectData();
	        	 for (GetProjectData projetData : this.getProjectDatas) {
	  				if (projetData.getProjetTitre().equals(selectedAutoGlobalProjets)) {
	  					projToShow = projetData;
	  				}
	  			}
	        	this.getProjectDatas = new ArrayList<GetProjectData>();
				this.getProjectDatas.add(projToShow);
			}else {
				this.getProjectDatas = getUserProjet();
			}
	        PrimeFaces.current().ajax().update("form");
			context.getExternalContext().getFlash().setKeepMessages(true);
	    }
	  
	  
	  
	  
	/**
	 * @return the selectedAutoGlobalProjets
	 */
	public Object getSelectedAutoGlobalProjets() {
		return selectedAutoGlobalProjets;
	}

	/**
	 * @param selectedAutoGlobalProjets the selectedAutoGlobalProjets to set
	 */
	public void setSelectedAutoGlobalProjets(Object selectedAutoGlobalProjets) {
		this.selectedAutoGlobalProjets = selectedAutoGlobalProjets;
	}

	/**
	 * @return the filterProjectDatas
	 */
	public List<GetProjectData> getFilterProjectDatas() {
		return filterProjectDatas;
	}

	/**
	 * @param filterProjectDatas the filterProjectDatas to set
	 */
	public void setFilterProjectDatas(List<GetProjectData> filterProjectDatas) {
		this.filterProjectDatas = filterProjectDatas;
	}

	/**
	 * @return the selectedAutoProjets
	 */
	public String getSelectedAutoProjets() {
		return selectedAutoProjets;
	}

	/**
	 * @param selectedAutoProjets the selectedAutoProjets to set
	 */
	public void setSelectedAutoProjets(String selectedAutoProjets) {
		this.selectedAutoProjets = selectedAutoProjets;
	}

	public List<Projet> getProjets() {
		return projets;
	}

	public void setProjets(List<Projet> projets) {
		this.projets = projets;
	}

	public GetProjectData getSelectedProjet() {
		return selectedProjet;
	}

	public void change() throws IOException {
		this.selectedProjet = new GetProjectData();
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("selectedProjet", this.selectedProjet);
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().redirect("projetForm.xhtml");
	}
	public void setSelectedProjet(GetProjectData selectedProjet) {
		this.selectedProjet = selectedProjet;
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("selectedProjet", this.selectedProjet);
		//Singleton.getInstance().setSelectedProjet(selectedProjet);
	}

	public ProjetViewService getProjetService() {
		return projetService;
	}

	public void setProjetService(ProjetViewService projetService) {
		this.projetService = projetService;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public BudgetViewService getBudgetService() {
		return budgetService;
	}

	public void setBudgetService(BudgetViewService budgetService) {
		this.budgetService = budgetService;
	}

	/**
	 * @return the chapList
	 */
	public List<Chapitre> getChapList() {
		return chapList;
	}

	/**
	 * @param chapList the chapList to set
	 */
	public void setChapList(List<Chapitre> chapList) {
		this.chapList = chapList;
	}

	/**
	 * @return the sourceStrings
	 */
	public List<String> getSourceStrings() {
		return sourceStrings;
	}

	/**
	 * @param sourceStrings the sourceStrings to set
	 */
	public void setSourceStrings(List<String> sourceStrings) {
		this.sourceStrings = sourceStrings;
	}

	/**
	 * @return the statusString
	 */
	public List<String> getStatusString() {
		return statusString;
	}

	/**
	 * @param statusString the statusString to set
	 */
	public void setStatusString(List<String> statusString) {
		this.statusString = statusString;
	}

	/**
	 * @return the chapitreStrings
	 */
	public List<String> getChapitreStrings() {
		return chapitreStrings;
	}

	/**
	 * @param chapitreStrings the chapitreStrings to set
	 */
	public void setChapitreStrings(List<String> chapitreStrings) {
		this.chapitreStrings = chapitreStrings;
	}

	/**
	 * @return the secteurStrings
	 */
	public List<String> getSecteurStrings() {
		return secteurStrings;
	}

	/**
	 * @param secteurStrings the secteurStrings to set
	 */
	public void setSecteurStrings(List<String> secteurStrings) {
		this.secteurStrings = secteurStrings;
	}

	/**
	 * @return the contractantStrings
	 */
	public List<String> getContractantStrings() {
		return contractantStrings;
	}

	/**
	 * @param contractantStrings the contractantStrings to set
	 */
	public void setContractantStrings(List<String> contractantStrings) {
		this.contractantStrings = contractantStrings;
	}

	/**
	 * @return the contractanttypeStrings
	 */
	public List<String> getContractanttypeStrings() {
		return contractanttypeStrings;
	}

	/**
	 * @param contractanttypeStrings the contractanttypeStrings to set
	 */
	public void setContractanttypeStrings(List<String> contractanttypeStrings) {
		this.contractanttypeStrings = contractanttypeStrings;
	}

	/**
	 * @return the regionStrings
	 */
	public List<String> getRegionStrings() {
		return regionStrings;
	}

	/**
	 * @param regionStrings the regionStrings to set
	 */
	public void setRegionStrings(List<String> regionStrings) {
		this.regionStrings = regionStrings;
	}

	/**
	 * @return the cercleStrings
	 */
	public List<String> getCercleStrings() {
		return cercleStrings;
	}

	/**
	 * @param cercleStrings the cercleStrings to set
	 */
	public void setCercleStrings(List<String> cercleStrings) {
		this.cercleStrings = cercleStrings;
	}

	/**
	 * @return the budget
	 */
	public int getBudget() {
		return budget;
	}

	/**
	 * @param budget the budget to set
	 */
	public void setBudget(int budget) {
		this.budget = budget;
	}

	/**
	 * @return the statut
	 */
	public String getStatut() {
		return statut;
	}

	/**
	 * @param statut the statut to set
	 */
	public void setStatut(String statut) {
		this.statut = statut;
	}

	/**
	 * @return the sourceService
	 */
	public SourceFinancementViewService getSourceService() {
		return sourceService;
	}

	/**
	 * @param sourceService the sourceService to set
	 */
	public void setSourceService(SourceFinancementViewService sourceService) {
		this.sourceService = sourceService;
	}

	/**
	 * @return the instrumentService
	 */
	public InstrumentfinancementViewService getInstrumentService() {
		return instrumentService;
	}

	/**
	 * @param instrumentService the instrumentService to set
	 */
	public void setInstrumentService(InstrumentfinancementViewService instrumentService) {
		this.instrumentService = instrumentService;
	}

	/**
	 * @return the chapitreService
	 */
	public ChapitreViewService getChapitreService() {
		return chapitreService;
	}

	/**
	 * @param chapitreService the chapitreService to set
	 */
	public void setChapitreService(ChapitreViewService chapitreService) {
		this.chapitreService = chapitreService;
	}

	/**
	 * @return the secteurService
	 */
	public SecteurViewService getSecteurService() {
		return secteurService;
	}

	/**
	 * @param secteurService the secteurService to set
	 */
	public void setSecteurService(SecteurViewService secteurService) {
		this.secteurService = secteurService;
	}

	/**
	 * @return the contractantService
	 */
	public ContractantViewService getContractantService() {
		return contractantService;
	}

	/**
	 * @param contractantService the contractantService to set
	 */
	public void setContractantService(ContractantViewService contractantService) {
		this.contractantService = contractantService;
	}

	/**
	 * @return the contractanttypeService
	 */
	public ContractanttypeViewService getContractanttypeService() {
		return contractanttypeService;
	}

	/**
	 * @param contractanttypeService the contractanttypeService to set
	 */
	public void setContractanttypeService(ContractanttypeViewService contractanttypeService) {
		this.contractanttypeService = contractanttypeService;
	}

	/**
	 * @return the regionService
	 */
	public RegionViewService getRegionService() {
		return regionService;
	}

	/**
	 * @param regionService the regionService to set
	 */
	public void setRegionService(RegionViewService regionService) {
		this.regionService = regionService;
	}

	/**
	 * @return the cercleService
	 */
	public CercleViewService getCercleService() {
		return cercleService;
	}

	/**
	 * @param cercleService the cercleService to set
	 */
	public void setCercleService(CercleViewService cercleService) {
		this.cercleService = cercleService;
	}

	/**
	 * @return the sources
	 */
	public List<SourceFinancement> getSources() {
		return sources;
	}

	/**
	 * @param sources the sources to set
	 */
	public void setSources(List<SourceFinancement> sources) {
		this.sources = sources;
	}

	/**
	 * @return the instruments
	 */
	public List<Instrumentfinancement> getInstruments() {
		return instruments;
	}

	/**
	 * @param instruments the instruments to set
	 */
	public void setInstruments(List<Instrumentfinancement> instruments) {
		this.instruments = instruments;
	}

	/**
	 * @return the instrumentsFilter
	 */
	public List<Instrumentfinancement> getInstrumentsFilter() {
		return instrumentsFilter;
	}

	/**
	 * @param instrumentsFilter the instrumentsFilter to set
	 */
	public void setInstrumentsFilter(List<Instrumentfinancement> instrumentsFilter) {
		this.instrumentsFilter = instrumentsFilter;
	}

	/**
	 * @return the chapitres
	 */
	public List<Chapitre> getChapitres() {
		return chapitres;
	}

	/**
	 * @param chapitres the chapitres to set
	 */
	public void setChapitres(List<Chapitre> chapitres) {
		this.chapitres = chapitres;
	}

	/**
	 * @return the secteurs
	 */
	public List<Secteur> getSecteurs() {
		return secteurs;
	}

	/**
	 * @param secteurs the secteurs to set
	 */
	public void setSecteurs(List<Secteur> secteurs) {
		this.secteurs = secteurs;
	}

	/**
	 * @return the contractants
	 */
	public List<Contractant> getContractants() {
		return contractants;
	}

	/**
	 * @param contractants the contractants to set
	 */
	public void setContractants(List<Contractant> contractants) {
		this.contractants = contractants;
	}

	/**
	 * @return the contractantsFilter
	 */
	public List<Contractant> getContractantsFilter() {
		return contractantsFilter;
	}

	/**
	 * @param contractantsFilter the contractantsFilter to set
	 */
	public void setContractantsFilter(List<Contractant> contractantsFilter) {
		this.contractantsFilter = contractantsFilter;
	}

	/**
	 * @return the contractanttypes
	 */
	public List<Contractanttype> getContractanttypes() {
		return contractanttypes;
	}

	/**
	 * @param contractanttypes the contractanttypes to set
	 */
	public void setContractanttypes(List<Contractanttype> contractanttypes) {
		this.contractanttypes = contractanttypes;
	}

	/**
	 * @return the regions
	 */
	public List<Region> getRegions() {
		return regions;
	}

	/**
	 * @param regions the regions to set
	 */
	public void setRegions(List<Region> regions) {
		this.regions = regions;
	}

	/**
	 * @return the cercles
	 */
	public List<Cercle> getCercles() {
		return cercles;
	}

	/**
	 * @param cercles the cercles to set
	 */
	public void setCercles(List<Cercle> cercles) {
		this.cercles = cercles;
	}

	/**
	 * @return the cerclesFilter
	 */
	public List<Cercle> getCerclesFilter() {
		return cerclesFilter;
	}

	/**
	 * @param cerclesFilter the cerclesFilter to set
	 */
	public void setCerclesFilter(List<Cercle> cerclesFilter) {
		this.cerclesFilter = cerclesFilter;
	}

	/**
	 * @return the budgets
	 */
	public List<Budget> getBudgets() {
		return budgets;
	}

	/**
	 * @param budgets the budgets to set
	 */
	public void setBudgets(List<Budget> budgets) {
		this.budgets = budgets;
	}

	/**
	 * @return the projetchapitres
	 */
	public List<Projetchapitre> getProjetchapitres() {
		return projetchapitres;
	}

	/**
	 * @param projetchapitres the projetchapitres to set
	 */
	public void setProjetchapitres(List<Projetchapitre> projetchapitres) {
		this.projetchapitres = projetchapitres;
	}

	/**
	 * @return the localisations
	 */
	public List<Localisation> getLocalisations() {
		return localisations;
	}

	/**
	 * @param localisations the localisations to set
	 */
	public void setLocalisations(List<Localisation> localisations) {
		this.localisations = localisations;
	}

	/**
	 * @return the localisationService
	 */
	public LocalisationViewService getLocalisationService() {
		return localisationService;
	}

	/**
	 * @param localisationService the localisationService to set
	 */
	public void setLocalisationService(LocalisationViewService localisationService) {
		this.localisationService = localisationService;
	}

	/**
	 * @return the projetChapitres
	 */
	public List<Projetchapitre> getProjetChapitres() {
		return projetChapitres;
	}

	/**
	 * @param projetChapitres the projetChapitres to set
	 */
	public void setProjetChapitres(List<Projetchapitre> projetChapitres) {
		this.projetChapitres = projetChapitres;
	}

	/**
	 * @return the calendrierService
	 */
	public CalendrierViewService getCalendrierService() {
		return calendrierService;
	}

	/**
	 * @param calendrierService the calendrierService to set
	 */
	public void setCalendrierService(CalendrierViewService calendrierService) {
		this.calendrierService = calendrierService;
	}

	/**
	 * @return the regionViewService
	 */
	public RegionViewService getRegionViewService() {
		return regionViewService;
	}

	/**
	 * @param regionViewService the regionViewService to set
	 */
	public void setRegionViewService(RegionViewService regionViewService) {
		this.regionViewService = regionViewService;
	}

	/**
	 * @return the communeService
	 */
	public CommuneViewService getCommuneService() {
		return communeService;
	}

	/**
	 * @param communeService the communeService to set
	 */
	public void setCommuneService(CommuneViewService communeService) {
		this.communeService = communeService;
	}

	/**
	 * @return the cercleCervice
	 */
	public CercleViewService getCercleCervice() {
		return cercleCervice;
	}

	/**
	 * @param cercleCervice the cercleCervice to set
	 */
	public void setCercleCervice(CercleViewService cercleCervice) {
		this.cercleCervice = cercleCervice;
	}

	/**
	 * @return the projchapitreService
	 */
	public ProjetchapitreViewService getProjchapitreService() {
		return projchapitreService;
	}

	/**
	 * @param projchapitreService the projchapitreService to set
	 */
	public void setProjchapitreService(ProjetchapitreViewService projchapitreService) {
		this.projchapitreService = projchapitreService;
	}

	/**
	 * @return the localisationSecteurViewService
	 */
	public LocalisationSecteurViewService getLocalisationSecteurViewService() {
		return localisationSecteurViewService;
	}

	/**
	 * @param localisationSecteurViewService the localisationSecteurViewService to set
	 */
	public void setLocalisationSecteurViewService(LocalisationSecteurViewService localisationSecteurViewService) {
		this.localisationSecteurViewService = localisationSecteurViewService;
	}

	/**
	 * @return the secteurHierarService
	 */
	public SecteurHierarViewService getSecteurHierarService() {
		return secteurHierarService;
	}

	/**
	 * @param secteurHierarService the secteurHierarService to set
	 */
	public void setSecteurHierarService(SecteurHierarViewService secteurHierarService) {
		this.secteurHierarService = secteurHierarService;
	}

	/**
	 * @return the secteurHierars
	 */
	public List<SecteurHierar> getSecteurHierars() {
		return secteurHierars;
	}

	/**
	 * @param secteurHierars the secteurHierars to set
	 */
	public void setSecteurHierars(List<SecteurHierar> secteurHierars) {
		this.secteurHierars = secteurHierars;
	}

	/**
	 * @return the communes
	 */
	public List<Commune> getCommunes() {
		return communes;
	}

	/**
	 * @param communes the communes to set
	 */
	public void setCommunes(List<Commune> communes) {
		this.communes = communes;
	}

	/**
	 * @return the calendriers
	 */
	public List<Calendrier> getCalendriers() {
		return calendriers;
	}

	/**
	 * @param calendriers the calendriers to set
	 */
	public void setCalendriers(List<Calendrier> calendriers) {
		this.calendriers = calendriers;
	}

	/**
	 * @return the statusProjet
	 */
	public List<String> getStatusProjet() {
		return statusProjet;
	}

	/**
	 * @param statusProjet the statusProjet to set
	 */
	public void setStatusProjet(List<String> statusProjet) {
		this.statusProjet = statusProjet;
	}

	/**
	 * @return the localisationSecteurs
	 */
	public List<LocalisationSecteur> getLocalisationSecteurs() {
		return localisationSecteurs;
	}

	/**
	 * @param localisationSecteurs the localisationSecteurs to set
	 */
	public void setLocalisationSecteurs(List<LocalisationSecteur> localisationSecteurs) {
		this.localisationSecteurs = localisationSecteurs;
	}

	/**
	 * @return the getProjectDatas
	 */
	public List<GetProjectData> getGetProjectDatas() {
		return getProjectDatas;
	}

	/**
	 * @param getProjectDatas the getProjectDatas to set
	 */
	public void setGetProjectDatas(List<GetProjectData> getProjectDatas) {
		this.getProjectDatas = getProjectDatas;
	}

	/**
	 * @return the utilisateur
	 */
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	/**
	 * @param utilisateur the utilisateur to set
	 */
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	/**
	 * @return the etatavancements
	 */
	public List<Etatavancement> getEtatavancements() {
		return etatavancements;
	}

	/**
	 * @param etatavancements the etatavancements to set
	 */
	public void setEtatavancements(List<Etatavancement> etatavancements) {
		this.etatavancements = etatavancements;
	}

	/**
	 * @return the etViewService
	 */
	public EtatavancementViewService getEtViewService() {
		return etViewService;
	}

	/**
	 * @param etViewService the etViewService to set
	 */
	public void setEtViewService(EtatavancementViewService etViewService) {
		this.etViewService = etViewService;
	}
	

}
