package ml.satgrie.pl.ue.utils;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import ml.satgrie.pl.ue.model.Budget;
import ml.satgrie.pl.ue.model.Cercle;
import ml.satgrie.pl.ue.model.Chapitre;
import ml.satgrie.pl.ue.model.Contractant;
import ml.satgrie.pl.ue.model.Contractanttype;
import ml.satgrie.pl.ue.model.Instrumentfinancement;
import ml.satgrie.pl.ue.model.Localisation;
import ml.satgrie.pl.ue.model.Projet;
import ml.satgrie.pl.ue.model.Projetchapitre;
import ml.satgrie.pl.ue.model.Region;
import ml.satgrie.pl.ue.model.Secteur;
import ml.satgrie.pl.ue.model.SourceFinancement;
import ml.satgrie.pl.ue.services.BudgetViewService;
import ml.satgrie.pl.ue.services.CercleViewService;
import ml.satgrie.pl.ue.services.ChapitreViewService;
import ml.satgrie.pl.ue.services.ContractantViewService;
import ml.satgrie.pl.ue.services.ContractanttypeViewService;
import ml.satgrie.pl.ue.services.InstrumentfinancementViewService;
import ml.satgrie.pl.ue.services.LocalisationViewService;
import ml.satgrie.pl.ue.services.ProjetViewService;
import ml.satgrie.pl.ue.services.ProjetchapitreViewService;
import ml.satgrie.pl.ue.services.RegionViewService;
import ml.satgrie.pl.ue.services.SecteurViewService;
import ml.satgrie.pl.ue.services.SourceFinancementViewService;
import org.primefaces.PrimeFaces;

@ManagedBean(name = "moteurOld")
@SessionScoped
public class MoteurOld implements Serializable {
	private static final long serialVersionUID = 1L;

	List<Chapitre> chapList;

	private List<Projet> projets;

	private Projet selectedProjet;

	private ProjetViewService projetService;

	private List<String> sourceStrings = new ArrayList<>();

	private List<String> instrumentStrings = new ArrayList<>();

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

	private ProjetchapitreViewService projetchapitreService;

	private List<Localisation> localisations = new ArrayList<>();

	private LocalisationViewService localisationService;

	private Map<Integer, List<Chapitre>> projetChapitres;

	@PostConstruct
	public void init() {
		this.projetService = new ProjetViewService();
		this.projets = this.projetService.findAll();
		this.sourceService = new SourceFinancementViewService();
		this.instrumentService = new InstrumentfinancementViewService();
		this.chapitreService = new ChapitreViewService();
		this.secteurService = new SecteurViewService();
		this.contractantService = new ContractantViewService();
		this.contractanttypeService = new ContractanttypeViewService();
		this.regionService = new RegionViewService();
		this.cercleService = new CercleViewService();
		this.projetchapitreService = new ProjetchapitreViewService();
		this.projetchapitres = this.projetchapitreService.findAll();
		this.sources = this.sourceService.findAll();
		this.instruments = this.instrumentService.findAll();
		this.chapitres = this.chapitreService.findAll();
		this.secteurs = this.secteurService.findAll();
		this.contractants = this.contractantService.findAll();
		this.contractanttypes = this.contractanttypeService.findAll();
		this.regions = this.regionService.findAll();
		this.cercles = this.cercleService.findAll();
		this.budgetService = new BudgetViewService();
		this.budgets = new ArrayList<>();
		this.budgets = this.budgetService.findAll();
		List<Projet> updatedProject = new ArrayList<>();
		for (Projet proj : this.projets) {
			long budTotal = 0L;
			if (proj.getProjetBudget().intValue() == 0) {
				for (Budget budC : this.budgets) {
					if (budC.getProjet().getProjetId() == proj.getProjetId())
						budTotal += budC.getBudgetContribution().longValue();
				}
				proj.setProjetBudget(new BigDecimal(budTotal));
			}
			updatedProject.add(proj);
		}
		for (Projetchapitre procChapC : this.projetchapitres) {
			for (Projet projet : this.projets) {
				this.chapList = new ArrayList<>();
				if (procChapC.getProjetId() == projet.getProjetId().intValue())
					for (Chapitre chapC : this.chapitres) {
						if (procChapC.getType().equals("Chapitre")
								&& chapC.getChapitreId().intValue() == procChapC.getFormerSectorId())
							this.chapList.add(chapC);
					}
			}
		}
		this.projets = updatedProject;
		this.localisationService = new LocalisationViewService();
		this.localisations = this.localisationService.findAll();
	}

	public void reinitialiserFiltre() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
			context.getExternalContext().redirect("moteur.xhtml");
			context.getExternalContext().getFlash().setKeepMessages(true);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	public void filteredProject() throws IOException {
		updatedProjectList();
		System.out.println(this.instrumentStrings);
		if (this.chapitreStrings != null && this.chapitreStrings.size() > 0)
			this.projets = filterProjetByChapitre();
		if (this.instrumentStrings.size() > 0) {
			this.projets = filterProjetByInstrument();
		} else if (this.sourceStrings.size() > 0) {
			for (String source : this.sourceStrings) {
				for (Instrumentfinancement instruC : this.instruments) {
					if (instruC.getSourcefinancement().getSourceFinancementNom().equals(source))
						this.instrumentStrings.add(instruC.getInstrumentFinancementNom());
				}
			}
			this.projets = filterProjetByInstrument();
		}
		if (this.secteurStrings != null && this.secteurStrings.size() > 0)
			this.projets = filterProjetBySecteur();
		if (this.contractantStrings.size() > 0) {
			this.projets = filterProjetByContractant();
		} else if (this.contractanttypeStrings.size() > 0) {
			for (String typecontractant : this.contractanttypeStrings) {
				for (Contractant contractantC : this.contractants) {
					if (contractantC.getContractanttype().getContractantTypeNom().equals(typecontractant))
						this.contractantStrings.add(contractantC.getContractantNom());
				}
			}
		}
		if (this.cercleStrings.size() > 0) {
			this.projets = filterProjetByCercle();
		} else if (this.regionStrings.size() > 0) {
			for (String region : this.regionStrings) {
				for (Cercle cercleC : this.cercles) {
					if (cercleC.getRegion().getRegionNom().equals(region))
						this.cercleStrings.add(cercleC.getCerlceName());
				}
			}
		}
		deleteDoublonFilter();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Filtre effectuer"));
		PrimeFaces.current().ajax().update(new String[] { "form:messages", "form:dt-projet" });
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().redirect("moteur.xhtml");
	}

	private List<Projet> filterProjetByCercle() {
		List<Projet> filterProjet = new ArrayList<>();
		List<Cercle> filteredCercle = new ArrayList<>();
		for (Cercle cercleC : this.cercles) {
			for (String cercleString : this.cercleStrings) {
				if (cercleC.getCerlceName().equals(cercleString))
					filteredCercle.add(cercleC);
			}
		}
		for (Projet projet : this.projets) {
			for (Cercle cercleC : filteredCercle) {
				for (Localisation localisationC : this.localisations) {
					if (localisationC.getProjetId() == projet.getProjetId()
							&& localisationC.getId().getCerlceId() == cercleC.getId().getCerlceId())
						filterProjet.add(projet);
				}
			}
		}
		return filterProjet;
	}

	private void updatedProjectList() {
		this.projets = this.projetService.findAll();
		List<Projet> updatedProject = new ArrayList<>();
		for (Projet proj : this.projets) {
			long budTotal = 0L;
			if (proj.getProjetBudget().intValue() == 0) {
				for (Budget budC : this.budgets) {
					if (budC.getProjet().getProjetId() == proj.getProjetId())
						budTotal += budC.getBudgetContribution().longValue();
				}
				proj.setProjetBudget(new BigDecimal(budTotal));
			}
			updatedProject.add(proj);
		}
		this.projets = updatedProject;
	}

	private List<Projet> filterProjetByContractant() {
		List<Projet> filterProjet = new ArrayList<>();
		List<Contractant> filteredContractant = new ArrayList<>();
		for (Contractant contractantC : this.contractants) {
			for (String contractantString : this.contractantStrings) {
				if (contractantC.getContractantNom().equals(contractantString))
					filteredContractant.add(contractantC);
			}
		}
		for (Projet projet : this.projets) {
			for (Contractant contractantC : filteredContractant) {
				if (contractantC.getProjet().getProjetId() == projet.getProjetId())
					filterProjet.add(projet);
			}
		}
		return filterProjet;
	}

	private void deleteDoublonFilter() {
		List<Projet> newValues = new ArrayList<>();
		for (int i = 0; i < this.projets.size(); i++) {
			if (i == 0) {
				newValues.add(this.projets.get(i));
			} else {
				Boolean exist = Boolean.valueOf(false);
				for (int y = 0; y < newValues.size(); y++) {
					if (((Projet) newValues.get(y)).getProjetId() == ((Projet) this.projets.get(i)).getProjetId()) {
						exist = Boolean.valueOf(true);
						break;
					}
				}
				if (!exist.booleanValue())
					newValues.add(this.projets.get(i));
			}
		}
		this.projets = newValues;
	}

	private List<Projet> filterProjetBySecteur() {
		List<Projet> filterProjet = new ArrayList<>();
		List<Secteur> filteredSecteur = new ArrayList<>();
		for (Secteur secteurC : this.secteurs) {
			for (String secteurString : this.secteurStrings) {
				if (secteurC.getSecteurName().equals(secteurString))
					filteredSecteur.add(secteurC);
			}
		}
		for (Projetchapitre projChapC : this.projetchapitres) {
			if (projChapC.getType().equals("Secteur"))
				for (Secteur secteurC : filteredSecteur) {
					if (projChapC.getFormerSectorId() == secteurC.getId().getSecteurId())
						for (Projet projet : this.projets) {
							if (projChapC.getProjetId() == projet.getProjetId().intValue())
								filterProjet.add(projet);
						}
				}
		}
		return filterProjet;
	}

	private List<Projet> filterProjetByInstrument() {
		List<Projet> filterProjet = new ArrayList<>();
		List<Instrumentfinancement> filteredInstrument = new ArrayList<>();
		for (Instrumentfinancement instruC : this.instruments) {
			for (String instruString : this.instrumentStrings) {
				if (instruC.getInstrumentFinancementNom().equals(instruString))
					filteredInstrument.add(instruC);
			}
		}
		for (Instrumentfinancement instruC : filteredInstrument) {
			for (Budget budgetC : this.budgets) {
				if (budgetC.getInstrumentfinancement().getInstrumentfinancementId() == instruC
						.getInstrumentfinancementId())
					filterProjet.add(budgetC.getProjet());
			}
		}
		return filterProjet;
	}

	private List<Projet> filterProjetByChapitre() {
		List<Projet> filterProjet = new ArrayList<>();
		List<Chapitre> filteredChapitre = new ArrayList<>();
		for (Chapitre chapitreC : this.chapitres) {
			for (String chapitreString : this.chapitreStrings) {
				if (chapitreC.getChapitreName().equals(chapitreString))
					filteredChapitre.add(chapitreC);
			}
		}
		for (Projetchapitre projChapC : this.projetchapitres) {
			if (projChapC.getType().equals("Chapitre"))
				for (Chapitre chapitreC : filteredChapitre) {
					if (projChapC.getFormerSectorId() == chapitreC.getChapitreId().intValue())
						for (Projet projet : this.projets) {
							if (projChapC.getProjetId() == projet.getProjetId().intValue())
								filterProjet.add(projet);
						}
				}
		}
		for (Projet projet : this.projets) {
			for (Chapitre chapitreC : filteredChapitre) {
				if (projet.getChapitre() != null && projet.getChapitre().getChapitreId() == chapitreC.getChapitreId())
					filterProjet.add(projet);
			}
		}
		return filterProjet;
	}

	public void handletest() {
		System.out.println(this.instrumentStrings);
	}

	public void handleSourceChange() {
		this.instrumentsFilter = new ArrayList<>();
		for (String source : this.sourceStrings) {
			for (Instrumentfinancement instruC : this.instruments) {
				if (instruC.getSourcefinancement().getSourceFinancementNom().equals(source))
					this.instrumentsFilter.add(instruC);
			}
		}
		PrimeFaces.current().ajax().update(new String[] { "form:instrument" });
	}

	public void handleRegionChange() {
		this.cerclesFilter = new ArrayList<>();
		for (String region : this.regionStrings) {
			for (Cercle cercleC : this.cercles) {
				if (cercleC.getRegion().getRegionNom().equals(region)) {
					this.cerclesFilter.add(cercleC);
					this.cercleStrings.add(cercleC.getCerlceName());
				}
			}
		}
		PrimeFaces.current().ajax().update(new String[] { "form:cercle" });
	}

	public void handleTypeContractantChange() {
		this.contractantsFilter = new ArrayList<>();
		for (String typecontractant : this.contractanttypeStrings) {
			for (Contractant contractantC : this.contractants) {
				if (contractantC.getContractanttype().getContractantTypeNom().equals(typecontractant))
					this.contractantsFilter.add(contractantC);
			}
		}
		PrimeFaces.current().ajax().update(new String[] { "form:contractant" });
	}

	public List<Projet> getProjets() {
		return this.projets;
	}

	public void setProjets(List<Projet> projets) {
		this.projets = projets;
	}

	public Projet getSelectedProjet() {
		return this.selectedProjet;
	}

	public void setSelectedProjet(Projet selectedProjet) {
		this.selectedProjet = selectedProjet;
	}

	public ProjetViewService getProjetService() {
		return this.projetService;
	}

	public void setProjetService(ProjetViewService projetService) {
		this.projetService = projetService;
	}

	public List<String> getSourceStrings() {
		return this.sourceStrings;
	}

	public void setSourceStrings(List<String> sourceStrings) {
		this.sourceStrings = sourceStrings;
	}

	public List<String> getInstrumentStrings() {
		return this.instrumentStrings;
	}

	public void setInstrumentStrings(List<String> instrumentStrings) {
		this.instrumentStrings = instrumentStrings;
	}

	public List<String> getChapitreStrings() {
		return this.chapitreStrings;
	}

	public void setChapitreStrings(List<String> chapitreStrings) {
		this.chapitreStrings = chapitreStrings;
	}

	public List<String> getSecteurStrings() {
		return this.secteurStrings;
	}

	public void setSecteurStrings(List<String> secteurStrings) {
		this.secteurStrings = secteurStrings;
	}

	public List<String> getContractantStrings() {
		return this.contractantStrings;
	}

	public void setContractantStrings(List<String> contractantStrings) {
		this.contractantStrings = contractantStrings;
	}

	public List<String> getContractanttypeStrings() {
		return this.contractanttypeStrings;
	}

	public void setContractanttypeStrings(List<String> contractanttypeStrings) {
		this.contractanttypeStrings = contractanttypeStrings;
	}

	public List<String> getRegionStrings() {
		return this.regionStrings;
	}

	public void setRegionStrings(List<String> regionStrings) {
		this.regionStrings = regionStrings;
	}

	public List<String> getCercleStrings() {
		return this.cercleStrings;
	}

	public void setCercleStrings(List<String> cercleStrings) {
		this.cercleStrings = cercleStrings;
	}

	public int getBudget() {
		return this.budget;
	}

	public void setBudget(int budget) {
		this.budget = budget;
	}

	public String getStatut() {
		return this.statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}

	public SourceFinancementViewService getSourceService() {
		return this.sourceService;
	}

	public void setSourceService(SourceFinancementViewService sourceService) {
		this.sourceService = sourceService;
	}

	public InstrumentfinancementViewService getInstrumentService() {
		return this.instrumentService;
	}

	public void setInstrumentService(InstrumentfinancementViewService instrumentService) {
		this.instrumentService = instrumentService;
	}

	public ChapitreViewService getChapitreService() {
		return this.chapitreService;
	}

	public void setChapitreService(ChapitreViewService chapitreService) {
		this.chapitreService = chapitreService;
	}

	public SecteurViewService getSecteurService() {
		return this.secteurService;
	}

	public void setSecteurService(SecteurViewService secteurService) {
		this.secteurService = secteurService;
	}

	public ContractantViewService getContractantService() {
		return this.contractantService;
	}

	public void setContractantService(ContractantViewService contractantService) {
		this.contractantService = contractantService;
	}

	public ContractanttypeViewService getContractanttypeService() {
		return this.contractanttypeService;
	}

	public void setContractanttypeService(ContractanttypeViewService contractanttypeService) {
		this.contractanttypeService = contractanttypeService;
	}

	public RegionViewService getRegionService() {
		return this.regionService;
	}

	public void setRegionService(RegionViewService regionService) {
		this.regionService = regionService;
	}

	public CercleViewService getCercleService() {
		return this.cercleService;
	}

	public void setCercleService(CercleViewService cercleService) {
		this.cercleService = cercleService;
	}

	public List<SourceFinancement> getSources() {
		return this.sources;
	}

	public void setSources(List<SourceFinancement> sources) {
		this.sources = sources;
	}

	public List<Instrumentfinancement> getInstruments() {
		return this.instruments;
	}

	public void setInstruments(List<Instrumentfinancement> instruments) {
		this.instruments = instruments;
	}

	public List<Instrumentfinancement> getInstrumentsFilter() {
		return this.instrumentsFilter;
	}

	public void setInstrumentsFilter(List<Instrumentfinancement> instrumentsFilter) {
		this.instrumentsFilter = instrumentsFilter;
	}

	public List<Chapitre> getChapitres() {
		return this.chapitres;
	}

	public void setChapitres(List<Chapitre> chapitres) {
		this.chapitres = chapitres;
	}

	public List<Secteur> getSecteurs() {
		return this.secteurs;
	}

	public void setSecteurs(List<Secteur> secteurs) {
		this.secteurs = secteurs;
	}

	public List<Contractant> getContractants() {
		return this.contractants;
	}

	public void setContractants(List<Contractant> contractants) {
		this.contractants = contractants;
	}

	public List<Contractanttype> getContractanttypes() {
		return this.contractanttypes;
	}

	public void setContractanttypes(List<Contractanttype> contractanttypes) {
		this.contractanttypes = contractanttypes;
	}

	public List<Region> getRegions() {
		return this.regions;
	}

	public void setRegions(List<Region> regions) {
		this.regions = regions;
	}

	public List<Cercle> getCercles() {
		return this.cercles;
	}

	public void setCercles(List<Cercle> cercles) {
		this.cercles = cercles;
	}

	public BudgetViewService getBudgetService() {
		return this.budgetService;
	}

	public void setBudgetService(BudgetViewService budgetService) {
		this.budgetService = budgetService;
	}

	public static long getSerialversionuid() {
		return 1L;
	}

	public List<Contractant> getContractantsFilter() {
		return this.contractantsFilter;
	}

	public void setContractantsFilter(List<Contractant> contractantsFilter) {
		this.contractantsFilter = contractantsFilter;
	}

	public List<Cercle> getCerclesFilter() {
		return this.cerclesFilter;
	}

	public void setCerclesFilter(List<Cercle> cerclesFilter) {
		this.cerclesFilter = cerclesFilter;
	}

	public List<Budget> getBudgets() {
		return this.budgets;
	}

	public void setBudgets(List<Budget> budgets) {
		this.budgets = budgets;
	}

	public List<Projetchapitre> getProjetchapitres() {
		return this.projetchapitres;
	}

	public void setProjetchapitres(List<Projetchapitre> projetchapitres) {
		this.projetchapitres = projetchapitres;
	}

	public ProjetchapitreViewService getProjetchapitreService() {
		return this.projetchapitreService;
	}

	public void setProjetchapitreService(ProjetchapitreViewService projetchapitreService) {
		this.projetchapitreService = projetchapitreService;
	}

	public List<Localisation> getLocalisations() {
		return this.localisations;
	}

	public void setLocalisations(List<Localisation> localisations) {
		this.localisations = localisations;
	}

	public LocalisationViewService getLocalisationService() {
		return this.localisationService;
	}

	public void setLocalisationService(LocalisationViewService localisationService) {
		this.localisationService = localisationService;
	}

	public Map<Integer, List<Chapitre>> getProjetChapitres() {
		return this.projetChapitres;
	}

	public void setProjetChapitres(Map<Integer, List<Chapitre>> projetChapitres) {
		this.projetChapitres = projetChapitres;
	}

	public List<Chapitre> getChapList() {
		return this.chapList;
	}

	public void setChapList(List<Chapitre> chapList) {
		this.chapList = chapList;
	}
}
