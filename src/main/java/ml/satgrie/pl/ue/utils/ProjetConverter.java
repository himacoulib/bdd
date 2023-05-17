package ml.satgrie.pl.ue.utils;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;

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

@Named
@FacesConverter(value = "projetConverter", managed = true)
public class ProjetConverter implements Converter<GetProjectData> {
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

    private List<GetProjectData> selectedAutoProjets;


	private List<LocalisationSecteur> localisationSecteurs;
	private List<GetProjectData> getProjectDatas;
	private Utilisateur utilisateur;
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
	

	@Override
	public GetProjectData getAsObject(FacesContext context, UIComponent component, String value) {
		
		GetProjectData getProjectData = new GetProjectData();
		if (value != null && value.trim().length() > 0) {
            try {
            	for (GetProjectData projectData : getProjectDatas) {
					if (projectData.getProjetId().toString().equals(value)) {
						getProjectData = projectData;
						
					}
				}
            }
            catch (NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid country."));
            }
        }
        else {
            return getProjectData;
        }
		return getProjectData;
		
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, GetProjectData value) {
		// TODO Auto-generated method stub
		return null;
	}

    
}