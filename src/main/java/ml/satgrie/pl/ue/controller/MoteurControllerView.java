package ml.satgrie.pl.ue.controller;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import org.bouncycastle.jcajce.provider.asymmetric.ec.SignatureSpi.ecCVCDSA;
import org.primefaces.PrimeFaces;

import ml.satgrie.pl.ue.model.Budget;
import ml.satgrie.pl.ue.model.Calendrier;
import ml.satgrie.pl.ue.model.Cercle;
import ml.satgrie.pl.ue.model.Chapitre;
import ml.satgrie.pl.ue.model.Commune;
import ml.satgrie.pl.ue.model.Contractant;
import ml.satgrie.pl.ue.model.Contractanttype;
import ml.satgrie.pl.ue.model.Devise;
import ml.satgrie.pl.ue.model.Etatavancement;
import ml.satgrie.pl.ue.model.GetProjectData;
import ml.satgrie.pl.ue.model.Instrumentfinancement;
import ml.satgrie.pl.ue.model.Localisation;
import ml.satgrie.pl.ue.model.LocalisationSecteur;
import ml.satgrie.pl.ue.model.Photo;
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
import ml.satgrie.pl.ue.services.DeviseViewService;
import ml.satgrie.pl.ue.services.EtatavancementViewService;
import ml.satgrie.pl.ue.services.InstrumentfinancementViewService;
import ml.satgrie.pl.ue.services.LocalisationSecteurViewService;
import ml.satgrie.pl.ue.services.LocalisationViewService;
import ml.satgrie.pl.ue.services.PhotoViewService;
import ml.satgrie.pl.ue.services.ProjetViewService;
import ml.satgrie.pl.ue.services.ProjetchapitreViewService;
import ml.satgrie.pl.ue.services.RegionViewService;
import ml.satgrie.pl.ue.services.SecteurHierarViewService;
import ml.satgrie.pl.ue.services.SecteurViewService;
import ml.satgrie.pl.ue.services.SourceFinancementViewService;
import ml.satgrie.pl.ue.utils.HibernateUtils;
import ml.satgrie.pl.ue.utils.SessionUtils;

@ManagedBean(name = "moteurControllerView")
@SessionScoped
public class MoteurControllerView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	List<Chapitre> chapList;

	private List<Projet> projets;

	private Projet selectedProjet;

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

	private ProjetchapitreViewService projetchapitreService;

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

	private List<LocalisationSecteur> localisationSecteurs;
	private List<GetProjectData> getProjectDatas;
	private Utilisateur utilisateur;
	private List<Etatavancement> etatAvancements;
	private EtatavancementViewService etService;

	private List<Secteur> secteursGetByChap;

	private List<Secteur>  secteursGetByChapTmp;

	@PostConstruct
	public void init() {

		this.etService = new EtatavancementViewService();
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

		this.etatAvancements = etService.findAll();
		this.calendriers = calendrierService.findAll();
		this.secteurHierars = secteurHierarService.findAll();
		this.sources = sourceService.findAll();
		this.communes = communeService.findAll();
		this.cercles = cercleCervice.findAll();
		this.regions = this.regionViewService.findAll();
		this.projets = this.projetService.findAll();
		this.calendriers = this.calendrierService.findAll();
		this.budgets = this.budgetService.findAll();
		this.contractants = this.contractantService.findAll();
		this.instruments = this.instrumentService.findAll();
		this.secteurs = this.secteurService.findAll();
		this.projetChapitres = this.projchapitreService.findAll();
		this.chapitres = this.chapitreService.findAll();
		this.localisations = this.localisationService.findAll();
		this.localisationSecteurs = this.localisationSecteurViewService.findAll();
		this.contractanttypes = this.contractanttypeService.findAll();
 this.secteursGetByChap = secteurs;

		GetProjectData getProjectDataTmp = new GetProjectData();
		this.getProjectDatas = new ArrayList<GetProjectData>();
		this.getProjectDatas = getProjectDataTmp.getProjectAllInfo(this.projets, this.budgets, localisationSecteurs,
				secteurHierars, localisations, getProjectDatas, regions, cercles, communes, contractants,
				calendriers, contractanttypes, this.etatAvancements, this.chapitres);
		 this.utilisateur = (Utilisateur)SessionUtils.getRequest().getSession().getAttribute("user");
	
		statusProjet = new ArrayList<String>();
		statusProjet.add("Clôturé");
		statusProjet.add("En cours");
	}

	public void reinitialiserFiltre() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			GetProjectData getProjectDataTmp = new GetProjectData();
			this.getProjectDatas = getProjectDataTmp.getProjectAllInfo(this.projets, this.budgets, localisationSecteurs,
					secteurHierars, localisations, getProjectDatas, regions, cercles, communes, contractants,
					calendriers, contractanttypes, this.etatAvancements, this.chapitres);
			statusString = new ArrayList<String>();
			chapitreStrings = new ArrayList<String>();
			sourceStrings = new ArrayList<String>();
			secteurStrings = new ArrayList<String>();
			contractanttypeStrings = new ArrayList<String>();
			regionStrings = new ArrayList<String>();
			cercleStrings = new ArrayList<String>();
			PrimeFaces.current().ajax().update("form:messages", "form:searchTab");
			context.getExternalContext().redirect("moteur.xhtml");
			context.getExternalContext().getFlash().setKeepMessages(true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}



	public void filteredProject() throws IOException {

		this.getProjectDatas = new ArrayList<GetProjectData>();
		GetProjectData getProjectDataTmp = new GetProjectData();
		this.getProjectDatas = getProjectDataTmp.getProjectAllInfo(this.projets, this.budgets, localisationSecteurs,
				secteurHierars, localisations, getProjectDatas, regions, cercles, communes, contractants,
				calendriers, contractanttypes, this.etatAvancements, this.chapitres);

		List<GetProjectData> searchProjectDatas = new ArrayList<GetProjectData>();
		if (this.statusString.size() > 0 && !this.statusString.isEmpty() && this.statusString != null) {
			for (GetProjectData getProjectData : this.getProjectDatas) {

				for (String status : statusString) {
					if (status != null && getProjectData.getStatus() != null
							&& getProjectData.getStatus().equals(status)) {
						searchProjectDatas.add(getProjectData);
					}
				}
			}
		}
		if (!this.chapitreStrings.isEmpty()) {
			if (searchProjectDatas != null && searchProjectDatas.isEmpty()) {
				for (GetProjectData getProjectData : this.getProjectDatas) {

					for (String chapitreString : chapitreStrings) {
						for (SecteurHierar chapToSearch : getProjectData.getChapitres()) {
							if (chapToSearch.getSecteurNom().equals(chapitreString)) {
								searchProjectDatas.add(getProjectData);
							}
						}
					}
				}
			} else {
				if (searchProjectDatas != null && !searchProjectDatas.isEmpty()) {

					List<GetProjectData> filteredByChapitre = new ArrayList<GetProjectData>();

					if (!this.chapitreStrings.isEmpty()) {
						for (String chapitreString : chapitreStrings) {
							for (GetProjectData getProjectData : searchProjectDatas) {
								for (SecteurHierar chapToSearch : getProjectData.getChapitres()) {
									if (chapToSearch.getSecteurNom().equals(chapitreString)) {
										filteredByChapitre.add(getProjectData);
									}
								}
							}
						}
					}
					if (filteredByChapitre.isEmpty()) {
						searchProjectDatas = null;
					} else {
						searchProjectDatas = filteredByChapitre;
					}
				}

			}
		}
		if (!this.sourceStrings.isEmpty()) {
			if (searchProjectDatas != null && searchProjectDatas.isEmpty()) {
				for (GetProjectData getProjectData : this.getProjectDatas) {

					for (String finToSearch : sourceStrings) {
						for (SourceFinancement sourceFin : getProjectData.getProjetSourceFinancements()) {
							if (finToSearch.equals(sourceFin.getSourceFinancementNom())) {
								searchProjectDatas.add(getProjectData);
							}
						}
					}
				}
			} else {
				List<GetProjectData> filteredBySource = new ArrayList<GetProjectData>();
				if (!this.sourceStrings.isEmpty()) {
					for (String finToSearch : sourceStrings) {
						if (searchProjectDatas != null && !searchProjectDatas.isEmpty()) {
							for (GetProjectData getProjectData : searchProjectDatas) {

								for (SourceFinancement sourceFin : getProjectData.getProjetSourceFinancements()) {
									if (finToSearch.equals(sourceFin.getSourceFinancementNom())) {
										filteredBySource.add(getProjectData);
									}
								}
							}
						}
					}
					if (filteredBySource.isEmpty()) {
						searchProjectDatas = null;
					} else {
						searchProjectDatas = filteredBySource;
					}
				}

			}
		}

		if (!this.secteurStrings.isEmpty()) {
			if (searchProjectDatas != null && searchProjectDatas.isEmpty()) {
				for (GetProjectData getProjectData : this.getProjectDatas) {
					for (String secteurToFind : secteurStrings) {
						for (SecteurHierar secteur : getProjectData.getProjetSecteurs()) {
							if (secteur.getSecteurNom().equals(secteurToFind)) {
								searchProjectDatas.add(getProjectData);
							}
						}
					}
				}
			} else {
				List<GetProjectData> filteredBySecteur = new ArrayList<GetProjectData>();

				if (!this.secteurStrings.isEmpty()) {
					for (String secteurToFind : secteurStrings) {
						if (searchProjectDatas != null && !searchProjectDatas.isEmpty()) {
							for (GetProjectData getProjectData : searchProjectDatas) {
								for (SecteurHierar secteur : getProjectData.getProjetSecteurs()) {
									if (secteur.getSecteurNom().equals(secteurToFind)) {
										filteredBySecteur.add(getProjectData);
									}
								}
							}
						}
					}

					if (filteredBySecteur.isEmpty()) {
						searchProjectDatas = null;
					} else {
						searchProjectDatas = filteredBySecteur;
					}
				}

			}

		}
		if (!this.contractanttypeStrings.isEmpty()) {
			if (searchProjectDatas != null && searchProjectDatas.isEmpty()) {
				for (GetProjectData getProjectData : this.getProjectDatas) {

					for (String contractantType : contractanttypeStrings) {
						for (Contractanttype contractanttype : getProjectData.getContractantTypes()) {
							if (contractanttype.getContractantTypeNom().equals(contractantType)) {
								searchProjectDatas.add(getProjectData);
							}
						}
					}

				}
			} else {
				List<GetProjectData> filteredByContrat = new ArrayList<GetProjectData>();

				if (!this.contractanttypeStrings.isEmpty()) {
					for (String contractantType : contractanttypeStrings) {
						if (searchProjectDatas != null && !searchProjectDatas.isEmpty()) {
							for (GetProjectData getProjectData : searchProjectDatas) {
								for (Contractanttype contractanttype : getProjectData.getContractantTypes()) {
									if (contractanttype.getContractantTypeNom().equals(contractantType)) {
										filteredByContrat.add(getProjectData);
									}
								}
							}

						}
					}
					if (filteredByContrat.isEmpty()) {
						searchProjectDatas = null;
					} else {
						searchProjectDatas = filteredByContrat;
					}

				}
			}
		}
		if (!this.cercleStrings.isEmpty()) {
			if (searchProjectDatas != null && searchProjectDatas.isEmpty()) {
				for (GetProjectData getProjectData : this.getProjectDatas) {

					for (String cercleString : cercleStrings) {
						for (Cercle cercleToFind : getProjectData.getCercles()) {
							if (cercleString.equals(cercleToFind.getCerlceName())) {
								searchProjectDatas.add(getProjectData);
							}
						}

					}
				}
			} else {

				List<GetProjectData> filteredByCercle = new ArrayList<GetProjectData>();
				if (!this.cercleStrings.isEmpty()) {
					for (String cercleString : cercleStrings) {
						if (searchProjectDatas != null && !searchProjectDatas.isEmpty()) {
							for (GetProjectData getProjectData : searchProjectDatas) {

								for (Cercle cercleToFind : getProjectData.getCercles()) {
									if (cercleString.equals(cercleToFind.getCerlceName())) {
										filteredByCercle.add(getProjectData);
									}
								}

							}
						}
					}
					if (filteredByCercle.isEmpty()) {
						searchProjectDatas = null;
					} else {
						searchProjectDatas = filteredByCercle;
					}
				}
			}
		}

		if (!this.regionStrings.isEmpty()) {
			if (searchProjectDatas != null && searchProjectDatas.isEmpty()) {
				for (GetProjectData getProjectData : this.getProjectDatas) {

					for (String regionString : regionStrings) {
						for (Region regionToFind : getProjectData.getRegions()) {
							if (regionString.equals(regionToFind.getRegionNom())) {
								searchProjectDatas.add(getProjectData);
							}
						}

					}
				}

			} else {
				List<GetProjectData> filteredByRegion = new ArrayList<GetProjectData>();
				if (!this.regionStrings.isEmpty()) {
					for (String regionString : regionStrings) {

						if (searchProjectDatas != null && !searchProjectDatas.isEmpty()) {
							for (GetProjectData getProjectData : searchProjectDatas) {
								for (Region regionToFind : getProjectData.getRegions()) {
									if (regionString.equals(regionToFind.getRegionNom())) {
										filteredByRegion.add(getProjectData);
									}
								}

							}
						}

					}
					if (filteredByRegion.isEmpty()) {
						searchProjectDatas = null;
					} else {
						searchProjectDatas = filteredByRegion;
					}
				}

			}
		}

		if (searchProjectDatas != null && !searchProjectDatas.isEmpty()) {

			this.getProjectDatas = searchProjectDatas.stream().distinct().collect(Collectors.toList());
		} else {
			this.getProjectDatas = new ArrayList<GetProjectData>();
		}

		// On supprime les eventuelles doublons
		this.deleteDoublonFilter();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Filtre effectuer"));
		PrimeFaces.current().ajax().update("form:messages", "form:dt-projet");
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
					if (localisationC.getProjetId() == projet.getProjetId()) {
						if (localisationC.getId().getCerlceId() == cercleC.getId().getCerlceId())
							filterProjet.add(projet);
					}
				}
			}
		}

		return filterProjet;
	}

	private void updatedProjectList() {
		this.projets = this.projetService.findAll();
		List<Projet> updatedProject = new ArrayList<>();
		for (Projet proj : this.projets) {
			long budTotal = 0;
			if (proj.getProjetBudget().intValue() == 0) {

				for (Budget budC : this.budgets) {
					if (budC.getProjet().getProjetId() == proj.getProjetId()) {
						budTotal = budTotal + budC.getBudgetContribution().longValue();
					}
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
			if (i == 0)
				newValues.add(this.projets.get(i));
			else {
				Boolean exist = false;
				for (int y = 0; y < newValues.size(); y++) {
					if (newValues.get(y).getProjetId() == this.projets.get(i).getProjetId()) {
						exist = true;
						break;
					}
				}
				if (exist == false)
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
			if (projChapC.getType().equals("Secteur")) {
				for (Secteur secteurC : filteredSecteur) {
					if (projChapC.getFormerSectorId() == secteurC.getId().getSecteurId()) {
						for (Projet projet : this.projets) {
							if (projChapC.getProjetId() == projet.getProjetId()) {
								filterProjet.add(projet);
							}
						}
					}
				}
			}
		}

		return filterProjet;
	}

	private List<Projet> filterProjetByInstrument() { // Not working yet
		List<Projet> filterProjet = new ArrayList<>();
		List<Instrumentfinancement> filteredInstrument = new ArrayList<>();

		for (Instrumentfinancement instruC : this.instruments) {
			for (String instruString : this.statusString) {
				if (instruC.getInstrumentFinancementNom().equals(instruString)) {
					filteredInstrument.add(instruC);
				}
			}
		}

		for (Instrumentfinancement instruC : filteredInstrument) {
			for (Budget budgetC : this.budgets) {
				if (budgetC.getInstrumentfinancement().getInstrumentfinancementId() == instruC
						.getInstrumentfinancementId()) {
					filterProjet.add(budgetC.getProjet());
				}
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
			if (projChapC.getType().equals("Chapitre")) {
				for (Chapitre chapitreC : filteredChapitre) {
					if (projChapC.getFormerSectorId() == chapitreC.getChapitreId()) {
						for (Projet projet : this.projets) {
							if (projChapC.getProjetId() == projet.getProjetId()) {
								filterProjet.add(projet);
							}
						}
					}
				}
			}
		}
		for (Projet projet : this.projets) {
			for (Chapitre chapitreC : filteredChapitre) {
				if (projet.getChapitre() != null) {
					if (projet.getChapitre().getChapitreId() == chapitreC.getChapitreId())
						filterProjet.add(projet);
				}
			}
		}

		return filterProjet;
	}

	public void handletest() {
		System.out.println(this.statusString);
	}

	public void handleSourceChange() {
		this.instrumentsFilter = new ArrayList<>();
		String sourceSelected = sources.toString();

		for (String source : this.sourceStrings) {
			for (Instrumentfinancement instruC : this.instruments) {
				if (instruC.getSourcefinancement().getSourceFinancementNom().equals(source)) {
					this.instrumentsFilter.add(instruC);
				}
			}
		}

		PrimeFaces.current().ajax().update("form:instrument");
	}
	public void handleChapChange() {
		secteursGetByChapTmp = new ArrayList<Secteur>();
		this.secteurStrings = new ArrayList<String>();
		for (String chapSet : this.chapitreStrings) {
			for (SecteurHierar secteurHierarToFind : this.secteurHierars) {
				if (secteurHierarToFind.getSecteurParentId() == 0) {
					for (Secteur sect : this.secteursGetByChap) {
						if (chapSet.equals(secteurHierarToFind.getSecteurNom())) {
							if (secteurHierarToFind.getFormerSectorId() == sect.getId().getChapitreId()) {
								//this.secteurStrings.add(sect.getSecteurName());
								this.secteursGetByChapTmp.add(sect);
							}
						}

					}
				}
			}
		}
		this.secteurs = secteursGetByChapTmp;

		PrimeFaces.current().ajax().update("form:secteur");
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

		PrimeFaces.current().ajax().update("form:cercle");
	}

	public void handleTypeContractantChange() {
		this.contractantsFilter = new ArrayList<>();

		for (String typecontractant : this.contractanttypeStrings) {
			for (Contractant contractantC : this.contractants) {
				if (contractantC.getContractanttype().getContractantTypeNom().equals(typecontractant)) {
					this.contractantsFilter.add(contractantC);
				}
			}
		}

		PrimeFaces.current().ajax().update("form:contractant");

	}

	public List<Projet> getProjets() {
		return projets;
	}

	public void setProjets(List<Projet> projets) {
		this.projets = projets;
	}

	public Projet getSelectedProjet() {
		return selectedProjet;
	}

	public void setSelectedProjet(Projet selectedProjet) {
		this.selectedProjet = selectedProjet;
	}

	public ProjetViewService getProjetService() {
		return projetService;
	}

	public void setProjetService(ProjetViewService projetService) {
		this.projetService = projetService;
	}

	public List<String> getSourceStrings() {
		return sourceStrings;
	}

	public void setSourceStrings(List<String> sourceStrings) {
		this.sourceStrings = sourceStrings;
	}

	public List<String> getStatusString() {
		return statusString;
	}

	public void setStatusString(List<String> statusString) {
		this.statusString = statusString;
	}

	public List<String> getChapitreStrings() {
		return chapitreStrings;
	}

	public void setChapitreStrings(List<String> chapitreStrings) {
		this.chapitreStrings = chapitreStrings;
	}

	public List<String> getSecteurStrings() {
		return secteurStrings;
	}

	public void setSecteurStrings(List<String> secteurStrings) {
		this.secteurStrings = secteurStrings;
	}

	public List<String> getContractantStrings() {
		return contractantStrings;
	}

	public void setContractantStrings(List<String> contractantStrings) {
		this.contractantStrings = contractantStrings;
	}

	public List<String> getContractanttypeStrings() {
		return contractanttypeStrings;
	}

	public void setContractanttypeStrings(List<String> contractanttypeStrings) {
		this.contractanttypeStrings = contractanttypeStrings;
	}

	public List<String> getRegionStrings() {
		return regionStrings;
	}

	public void setRegionStrings(List<String> regionStrings) {
		this.regionStrings = regionStrings;
	}

	public List<String> getCercleStrings() {
		return cercleStrings;
	}

	public void setCercleStrings(List<String> cercleStrings) {
		this.cercleStrings = cercleStrings;
	}

	public int getBudget() {
		return budget;
	}

	public void setBudget(int budget) {
		this.budget = budget;
	}

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
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

	public ChapitreViewService getChapitreService() {
		return chapitreService;
	}

	public void setChapitreService(ChapitreViewService chapitreService) {
		this.chapitreService = chapitreService;
	}

	public SecteurViewService getSecteurService() {
		return secteurService;
	}

	public void setSecteurService(SecteurViewService secteurService) {
		this.secteurService = secteurService;
	}

	public ContractantViewService getContractantService() {
		return contractantService;
	}

	public void setContractantService(ContractantViewService contractantService) {
		this.contractantService = contractantService;
	}

	public ContractanttypeViewService getContractanttypeService() {
		return contractanttypeService;
	}

	public void setContractanttypeService(ContractanttypeViewService contractanttypeService) {
		this.contractanttypeService = contractanttypeService;
	}

	public RegionViewService getRegionService() {
		return regionService;
	}

	public void setRegionService(RegionViewService regionService) {
		this.regionService = regionService;
	}

	public CercleViewService getCercleService() {
		return cercleService;
	}

	public void setCercleService(CercleViewService cercleService) {
		this.cercleService = cercleService;
	}

	public List<SourceFinancement> getSources() {
		return sources;
	}

	public void setSources(List<SourceFinancement> sources) {
		this.sources = sources;
	}

	public List<Instrumentfinancement> getInstruments() {
		return instruments;
	}

	public void setInstruments(List<Instrumentfinancement> instruments) {
		this.instruments = instruments;
	}

	public List<Instrumentfinancement> getInstrumentsFilter() {
		return instrumentsFilter;
	}

	public void setInstrumentsFilter(List<Instrumentfinancement> instrumentsFilter) {
		this.instrumentsFilter = instrumentsFilter;
	}

	public List<Chapitre> getChapitres() {
		return chapitres;
	}

	public void setChapitres(List<Chapitre> chapitres) {
		this.chapitres = chapitres;
	}

	public List<Secteur> getSecteurs() {
		return secteurs;
	}

	public void setSecteurs(List<Secteur> secteurs) {
		this.secteurs = secteurs;
	}

	public List<Contractant> getContractants() {
		return contractants;
	}

	public void setContractants(List<Contractant> contractants) {
		this.contractants = contractants;
	}

	public List<Contractanttype> getContractanttypes() {
		return contractanttypes;
	}

	public void setContractanttypes(List<Contractanttype> contractanttypes) {
		this.contractanttypes = contractanttypes;
	}

	public List<Region> getRegions() {
		return regions;
	}

	public void setRegions(List<Region> regions) {
		this.regions = regions;
	}

	public List<Cercle> getCercles() {
		return cercles;
	}

	public void setCercles(List<Cercle> cercles) {
		this.cercles = cercles;
	}

	public BudgetViewService getBudgetService() {
		return budgetService;
	}

	public void setBudgetService(BudgetViewService budgetService) {
		this.budgetService = budgetService;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<Contractant> getContractantsFilter() {
		return contractantsFilter;
	}

	public void setContractantsFilter(List<Contractant> contractantsFilter) {
		this.contractantsFilter = contractantsFilter;
	}

	public List<Cercle> getCerclesFilter() {
		return cerclesFilter;
	}

	public void setCerclesFilter(List<Cercle> cerclesFilter) {
		this.cerclesFilter = cerclesFilter;
	}

	public List<Budget> getBudgets() {
		return budgets;
	}

	public void setBudgets(List<Budget> budgets) {
		this.budgets = budgets;
	}

	public List<Projetchapitre> getProjetchapitres() {
		return projetchapitres;
	}

	public void setProjetchapitres(List<Projetchapitre> projetchapitres) {
		this.projetchapitres = projetchapitres;
	}

	public ProjetchapitreViewService getProjetchapitreService() {
		return projetchapitreService;
	}

	public void setProjetchapitreService(ProjetchapitreViewService projetchapitreService) {
		this.projetchapitreService = projetchapitreService;
	}

	public List<Localisation> getLocalisations() {
		return localisations;
	}

	public void setLocalisations(List<Localisation> localisations) {
		this.localisations = localisations;
	}

	public LocalisationViewService getLocalisationService() {
		return localisationService;
	}

	public void setLocalisationService(LocalisationViewService localisationService) {
		this.localisationService = localisationService;
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
	 * @param localisationSecteurViewService the localisationSecteurViewService to
	 *                                       set
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

}