package ml.satgrie.pl.ue.utils;

import java.util.List;

import ml.satgrie.pl.ue.model.Budget;
import ml.satgrie.pl.ue.model.Calendrier;
import ml.satgrie.pl.ue.model.Cercle;
import ml.satgrie.pl.ue.model.Chapitre;
import ml.satgrie.pl.ue.model.Commune;
import ml.satgrie.pl.ue.model.Contractant;
import ml.satgrie.pl.ue.model.Instrumentfinancement;
import ml.satgrie.pl.ue.model.Localisation;
import ml.satgrie.pl.ue.model.LocalisationSecteur;
import ml.satgrie.pl.ue.model.Projet;
import ml.satgrie.pl.ue.model.Projetchapitre;
import ml.satgrie.pl.ue.model.Region;
import ml.satgrie.pl.ue.model.Secteur;
import ml.satgrie.pl.ue.model.SecteurHierar;
import ml.satgrie.pl.ue.services.BudgetViewService;
import ml.satgrie.pl.ue.services.CalendrierViewService;
import ml.satgrie.pl.ue.services.CercleViewService;
import ml.satgrie.pl.ue.services.ChapitreViewService;
import ml.satgrie.pl.ue.services.CommuneViewService;
import ml.satgrie.pl.ue.services.ContractantViewService;
import ml.satgrie.pl.ue.services.InstrumentfinancementViewService;
import ml.satgrie.pl.ue.services.LocalisationSecteurViewService;
import ml.satgrie.pl.ue.services.LocalisationViewService;
import ml.satgrie.pl.ue.services.ProjetViewService;
import ml.satgrie.pl.ue.services.ProjetchapitreViewService;
import ml.satgrie.pl.ue.services.RegionViewService;
import ml.satgrie.pl.ue.services.SecteurHierarViewService;
import ml.satgrie.pl.ue.services.SecteurViewService;

public class InitProjectInfo {
	
	private CalendrierViewService calendrierService;
	private CommuneViewService communeService;
	private SecteurHierarViewService secteurHierarService;
	private CercleViewService cercleCervice;
	private BudgetViewService budgetService;
	private ContractantViewService contractantService;
	private RegionViewService regionViewService;
	private InstrumentfinancementViewService instrumentService;
	private SecteurViewService secteurService;
	private ProjetchapitreViewService projchapitreService;
	private ChapitreViewService chapitreService;
	private LocalisationViewService localisationService;
	private ProjetViewService projetService;
	private LocalisationSecteurViewService localisationSecteurViewService;
	
	public void initProjet(List<SecteurHierar> secteurHierars, List<Commune> communes, List<Cercle> cercles, List<Region> regions, List<Projet> projets, List<Calendrier> calendriers, List<Budget> budgets, List<Contractant> contractants, List<Instrumentfinancement> instruments, List<Secteur> secteurs, List<Projetchapitre> projetChapitres, List<Chapitre> chapitres, List<Localisation> localisations, List<LocalisationSecteur> localisationSecteurs) {
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

		secteurHierars = secteurHierarService.findAll();
		communes = communeService.findAll();
		cercles = cercleCervice.findAll();
		regions = regionViewService.findAll();
		projets = projetService.findAll();
		calendriers = calendrierService.findAll();
		budgets = budgetService.findAll();
		contractants = contractantService.findAll();
		instruments = instrumentService.findAll();
		secteurs = secteurService.findAll();
		projetChapitres = projchapitreService.findAll();
		chapitres = chapitreService.findAll();
		localisations = localisationService.findAll();
		localisationSecteurs = localisationSecteurViewService.findAll();
	}
}
