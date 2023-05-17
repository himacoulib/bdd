package ml.satgrie.pl.ue.hazelcast;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

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
import ml.satgrie.pl.ue.model.Region;
import ml.satgrie.pl.ue.model.Secteur;
import ml.satgrie.pl.ue.model.SecteurHierar;
import ml.satgrie.pl.ue.model.SourceFinancement;
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
import ml.satgrie.pl.ue.services.RegionViewService;
import ml.satgrie.pl.ue.services.SecteurHierarViewService;
import ml.satgrie.pl.ue.services.SecteurViewService;
import ml.satgrie.pl.ue.services.SourceFinancementViewService;
import ml.satgrie.pl.ue.services.UtilisateurViewService;

/**
 * Servlet implementation class InitHazelcast
 */
@WebServlet("/InitHazelcast")
public class InitHazelcast extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(InitHazelcast.class);
	private CercleViewService cercleService;
	private UtilisateurViewService utilisateurViewService;
	private CommuneViewService communeService;
	private CalendrierViewService calendrierService;
	private ContractanttypeViewService cTypeService;
	private EtatavancementViewService etatService;
	private ChapitreViewService chapitreService;
	private SecteurViewService secteurViewService;
	private SecteurHierarViewService secteurHierarService;
	private LocalisationSecteurViewService localisationSecteurViewService;
	private ProjetViewService projetService;
	private RegionViewService regionService;
	private LocalisationViewService localisationService;
	private BudgetViewService budgetService;
	private ContractantViewService contractantService;
	private ContractanttypeViewService contractantTypeService;
	private InstrumentfinancementViewService instrumentService;
	private SourceFinancementViewService sourceService;
	private List<Projet> projets;
	private List<Region> regions;
	private List<Localisation> localisations;
	private List<Budget> budgets;
	private List<Contractant> contractants;
	private List<Contractanttype> contractantTypes;
	private List<Instrumentfinancement> instruments;
	private List<SourceFinancement> sources;
	private List<Secteur> secteurs;
	private List<LocalisationSecteur> localisationSecteurs;
	private List<SecteurHierar> secteurHierars;
	private List<Cercle> cercles;
	private List<Commune> communes;
	private List<Calendrier> calendriers;
	private List<Etatavancement> etatavancements;
	private List<Chapitre> chapitres;
	private float contributionTotal = 0;

	private float contributionUe = 0;
	private List<GetProjectData> getProjectDatas;
	private List<Contractanttype> contractanttypes;

	public void init() throws ServletException {
		System.setProperty("hazelcast.ignoreXxeProtectionFailures", "true");
//		Config config = new Config();
//		config.setInstanceName("TestName");
//		HazelcastInstance hzInstance = Hazelcast.newHazelcastInstance(config);
	
	}

	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InitHazelcast() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
