package ml.satgrie.pl.ue.controller;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.chart.HorizontalBarChartModel;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearTicks;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.bubble.BubbleChartModel;
import org.primefaces.model.charts.donut.DonutChartModel;
import org.primefaces.model.charts.line.LineChartModel;
import org.primefaces.model.charts.optionconfig.legend.Legend;
import org.primefaces.model.charts.optionconfig.legend.LegendLabel;
import org.primefaces.model.charts.optionconfig.title.Title;
import org.primefaces.model.charts.pie.PieChartDataSet;
import org.primefaces.model.charts.pie.PieChartModel;
import org.primefaces.model.charts.pie.PieChartOptions;
import org.primefaces.model.charts.polar.PolarAreaChartDataSet;
import org.primefaces.model.charts.polar.PolarAreaChartModel;
import org.primefaces.model.charts.radar.RadarChartModel;
import org.primefaces.model.charts.scatter.ScatterChartModel;

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

@Named
@ViewScoped
public class ChartJsView implements Serializable {

	private PieChartModel pieModel;

	private List<Region> regions;

	private List<Localisation> localisations;

	private List<Budget> budgets;

	private List<Projet> projets;

	private PolarAreaChartModel polarAreaModel;

	private LineChartModel lineModel;

	private LineChartModel cartesianLinerModel;

	private BarChartModel barModel;

	private BarChartModel barModel2;

	private HorizontalBarChartModel hbarModel;

	private BarChartModel stackedBarModel;

	private BarChartModel stackedGroupBarModel;

	private RadarChartModel radarModel;

	private RadarChartModel radarModel2;

	private List<SourceFinancement> sources;
	private BubbleChartModel bubbleModel;

	private BarChartModel mixedModel;

	private DonutChartModel donutModel;

	private ScatterChartModel scatterModel;
	private ProjetViewService projetService;

	private RegionViewService regionService;

	private InstrumentfinancementViewService instrumentService;

	private SourceFinancementViewService sourceService;

	private LocalisationViewService localisationService;

	private SecteurViewService secteurViewService;

	private BudgetViewService budgetService;

	private PolarAreaChartModel pieContractantTypeModel;

	private List<Contractanttype> contractantTypes;

	private List<Contractant> contractants;

	private List<Secteur> secteurs;

	private ContractanttypeViewService contractantTypeService;

	private ContractantViewService contractantService;

	private BarChartModel  instrumentNombreModel;
	private List<Instrumentfinancement> instruments;

	private PieChartModel sourceNombreModel;

	private float contributionTotal = 0;

	private float contributionUe = 0;
	

	private List<LocalisationSecteur> localisationSecteurs;
	private SecteurHierarViewService secteurHierarService;
	private LocalisationSecteurViewService localisationSecteurViewService;
	private List<SecteurHierar> secteurHierars;

	private List<GetProjectData> getProjectDatas;

	private List<Cercle> cercles;
	private CercleViewService cercleService;

	private List<Commune> communes;
	private CommuneViewService communeService;

	private List<Calendrier> calendriers;
	private CalendrierViewService calendrierService;

	private List<Contractanttype> contractanttypes;
	private ContractanttypeViewService cTypeService;

	private List<Etatavancement> etatavancements;
	private EtatavancementViewService etatService;

	private List<Chapitre> chapitres;

	private ChapitreViewService chapitreService;
	
	
	@PostConstruct
	public void init() {
		cercleService = new CercleViewService();
		communeService = new CommuneViewService();
		calendrierService = new CalendrierViewService();
		cTypeService = new ContractanttypeViewService();
		etatService = new EtatavancementViewService();
		chapitreService = new ChapitreViewService();
		
		this.secteurViewService = new SecteurViewService();
		this.secteurHierarService = new SecteurHierarViewService();
		this.localisationSecteurViewService = new LocalisationSecteurViewService();
		this.projetService = new ProjetViewService();
		this.regionService = new RegionViewService();
		this.localisationService = new LocalisationViewService();
		this.budgetService = new BudgetViewService();
		this.contractantService = new ContractantViewService();
		this.contractantTypeService = new ContractanttypeViewService();
		this.instrumentService = new InstrumentfinancementViewService();

		this.sourceService = new SourceFinancementViewService();
		this.projets = this.projetService.findAll();
		this.regions = this.regionService.findAll();
		this.localisations = this.localisationService.findAll();
		this.budgets = this.budgetService.findAll();
		this.contractants = this.contractantService.findAll();
		this.contractantTypes = this.contractantTypeService.findAll();

		this.instruments = this.instrumentService.findAll();
		this.sources = this.sourceService.findAll();
		this.secteurs = secteurViewService.findAll();
		this.localisationSecteurs = localisationSecteurViewService.findAll();
		this.secteurHierars = secteurHierarService.findAll();
		this.cercles = cercleService.findAll();
		this.communes = communeService.findAll();
		this.calendriers = calendrierService.findAll();
		contractanttypes = cTypeService.findAll();
		etatavancements = etatService.findAll();
		chapitres = chapitreService.findAll();
	
		Projet projet = new Projet();
		projet = this.projetService.findById(2);

		for (Budget budgetC : this.budgets) {
			this.contributionTotal = budgetC.getBudgetContribution().floatValue() + this.contributionTotal;
			if (budgetC.getInstrumentfinancement().getSourcefinancement().getSourceFinancementNom().equals("UE"))
				this.contributionUe = budgetC.getBudgetContribution().floatValue() + this.contributionUe;
		}
		
		GetProjectData getProjectDataTmp = new GetProjectData();

		this.getProjectDatas = new ArrayList<GetProjectData>();
		this.getProjectDatas = getProjectDataTmp.getProjectAllInfo(this.projets, this.budgets, localisationSecteurs,
				secteurHierars, localisations, getProjectDatas, regions, cercles, communes, contractants, calendriers,
				contractanttypes, this.etatavancements, this.chapitres);
		
//		IMap<String, List<GetProjectData>> projetData = HazelCastSingleton.getInstance().getMap(GetProjectData.class.getCanonicalName());
//		IMap<String, Float> totalContrib = HazelCastSingleton.getInstance().getMap("contributionTotal");
//		
//		IMap<String, Float> contributionUes = HazelCastSingleton.getInstance().getMap("contributionUe");
//		this.contributionUe = contributionUes.get("contributionUe");
//		this.contributionTotal = totalContrib.get("contributionTotal");
//		this.getProjectDatas = new ArrayList<>();
//		this.getProjectDatas = projetData.get(GetProjectData.class.getCanonicalName());
//		IMap<String, List<Region>> regionData = HazelCastSingleton.getInstance().getMap(Region.class.getCanonicalName());
//		this.regions = regionData.get("Region");
//		IMap<String, List<Projet>> projData = HazelCastSingleton.getInstance().getMap(Projet.class.getCanonicalName());
//		this.projets = projData.get("Projet");
//		IMap<String, List<Localisation>> locData = HazelCastSingleton.getInstance().getMap(Localisation.class.getCanonicalName());
//		this.localisations = locData.get("localisations");
//		IMap<String, List<Budget>> budgetsData = HazelCastSingleton.getInstance().getMap(Budget.class.getCanonicalName());
//	 	this.budgets =  budgetsData.get("budgets");
//		IMap<String, List<Contractant>> contractantsData = HazelCastSingleton.getInstance().getMap(Contractant.class.getCanonicalName());
//		this.contractants =  contractantsData.get("contractants");
//		IMap<String, List<Contractanttype>> contractantTypesData = HazelCastSingleton.getInstance().getMap(Contractanttype.class.getCanonicalName());
//		this.contractanttypes = contractantTypesData.get("contractantTypes");
//		IMap<String, List<Instrumentfinancement>> instruData = HazelCastSingleton.getInstance().getMap(Instrumentfinancement.class.getCanonicalName());
//		this.instruments =  instruData.get("instruments");
//		IMap<String, List<SourceFinancement>> sourcesData = HazelCastSingleton.getInstance().getMap(SourceFinancement.class.getCanonicalName());
//		this.sources = sourcesData.get("sources");
//		IMap<String, List<Secteur>> secteurData = HazelCastSingleton.getInstance().getMap(Secteur.class.getCanonicalName());
//		this.secteurs = secteurData.get("secteurs");
//		IMap<String, List<Commune>> communeData = HazelCastSingleton.getInstance().getMap(Commune.class.getCanonicalName());
//		this.communes = communeData.get("communes");
//		IMap<String, List<Calendrier>> calendrierData = HazelCastSingleton.getInstance().getMap(Calendrier.class.getCanonicalName());
//		this.calendriers = calendrierData.get("calendriers");
//		IMap<String, List<Etatavancement>> etData = HazelCastSingleton.getInstance().getMap(Etatavancement.class.getCanonicalName());
//		this.etatavancements = etData.get("etatavancements");
//		IMap<String, List<Chapitre>> chapData = HazelCastSingleton.getInstance().getMap(Chapitre.class.getCanonicalName());
//		this.chapitres = chapData.get("chapitres");
		

		createPieModel();
		createContractantPieModel();
		createSourcePieModel();
		this.createPieNombreByInstrument2();
		System.out.println("Total Memory (in bytes): " + Runtime.getRuntime().totalMemory());
		System.out.println("Free Memory (in bytes): " + Runtime.getRuntime().freeMemory());
		System.out.println("Max Memory (in bytes): " + Runtime.getRuntime().maxMemory());

	}
	

	private void createPieModel() {
		pieModel = new PieChartModel();

		ChartData data = new ChartData();

		PieChartDataSet dataSet = new PieChartDataSet();
		List<Number> values = new ArrayList<>();

		List<String> labels = new ArrayList<>();

		this.pieModel = new PieChartModel();
		for (Region regionC : this.regions) {

			int nbrProjet = 0;
			float montant = 0;
			for (Projet projetC : this.projets) {
				for (Localisation localisationC : this.localisations) {
					if (localisationC.getId().getRegionId() == regionC.getId().getRegionId()
							&& localisationC.getProjetId() == projetC.getProjetId()) {
						nbrProjet++;
						for (Budget budgetC : this.budgets) {
							if (budgetC.getProjet().getProjetId() == projetC.getProjetId()) {
								montant = budgetC.getBudgetContribution().floatValue() + montant;
							}
						}
					}
				}
			}
			int pourcentage = (nbrProjet * 100) / this.projets.size();
			// this.pieModel.set(regionC.getRegionNom() + ": " + pourcentage + "%",
			// nbrProjet);
			values.add(nbrProjet);
			labels.add(regionC.getRegionNom());
		}
		dataSet.setData(values);
		List<String> bgColors = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			
		}
		for (Number string : values) {
			Random random = new Random();
		    int red = random.nextInt(256);
		    int green = random.nextInt(256);
		    int blue = random.nextInt(256);

		    
		        red = (red + Color.red.getRed()) / 2;
		        green = (green + Color.green.getGreen()) / 2;
		        blue = (blue + Color.BLUE.getBlue()) / 2;
		        
				bgColors.add("rgb("+String.valueOf(red)+","+String.valueOf(green)+","+String.valueOf(blue)+")");

		}
		dataSet.setBackgroundColor(bgColors);
		data.addChartDataSet(dataSet);
		data.setLabels(labels);
		PieChartOptions options = new PieChartOptions();

		Legend legend = new Legend();
		legend.setDisplay(true);
		legend.setFullWidth(false);

		LegendLabel legendLabel = new LegendLabel();
		legendLabel.setUsePointStyle(true);
		legend.setLabels(legendLabel);

		options.setLegend(legend);
		options.setCutoutPercentage(10);

		pieModel.setOptions(options);
		pieModel.setExtender("pieExtender");
		pieModel.setData(data);
	}


	public String generateRandomColor() {
		 Random rand = new Random(System.currentTimeMillis());
		 float r = rand.nextFloat();
		 float g = rand.nextFloat();
		 float b = rand.nextFloat();
		 String rgb = "rgb("+r+","+g+","+b+")";
	    return rgb;
	}
	
	public void itemSelect(ItemSelectEvent event) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item selected",
				"Item Index: " + event.getItemIndex() + ", Series Index:" + event.getSeriesIndex());

		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	private void createContractantPieModel() {
		pieContractantTypeModel = new PolarAreaChartModel();
		ChartData data = new ChartData();
		PolarAreaChartDataSet dataSet = new PolarAreaChartDataSet();
		
		List<Number> values = new ArrayList<>();
		List<String> labels = new ArrayList<>();
		for (Contractanttype typeC : this.contractantTypes) {

			int nbrProjet = 0;
			for (Projet projetC : this.projets) {
				for (Contractant contractantC : this.contractants) {
					if (contractantC.getContractanttype().getContractantTypeId() == typeC.getContractantTypeId()
							&& contractantC.getProjet().getProjetId() == projetC.getProjetId())
						nbrProjet++;
				}
			}

			int pourcentage = (nbrProjet * 100) / this.projets.size();
			values.add(nbrProjet);
			labels.add(typeC.getContractantTypeNom() + ": " + nbrProjet);
		}

		dataSet.setData(values);
		List<String> bgColors = new ArrayList<>();

		for (Number string : values) {
			Random random = new Random();
		    int red = random.nextInt(256);
		    int green = random.nextInt(256);
		    int blue = random.nextInt(256);

		    
		        red = (red + Color.red.getRed()) / 2;
		        green = (green + Color.green.getGreen()) / 2;
		        blue = (blue + Color.BLUE.getBlue()) / 2;
		        
				bgColors.add("rgb("+String.valueOf(red)+","+String.valueOf(green)+","+String.valueOf(blue)+")");

		}
		dataSet.setBackgroundColor(bgColors);
		data.addChartDataSet(dataSet);
		data.setLabels(labels);
		pieContractantTypeModel.setExtender("ext1");
		pieContractantTypeModel.setData(data);

	}

	private void createSourcePieModel() {

		this.sourceNombreModel = new PieChartModel();
		ChartData data = new ChartData();

		PieChartDataSet dataSet = new PieChartDataSet();
		List<Number> values = new ArrayList<>();
		List<String> labels = new ArrayList<>();

		for (SourceFinancement sourceC : this.sources) {

			int nbrProjet = 0;
			float montant = 0;
			for (Budget budgetC : this.budgets) {
				if (sourceC.getSourceFinancementNom()
						.equals(budgetC.getInstrumentfinancement().getSourcefinancement().getSourceFinancementNom())) {
					nbrProjet++;
					montant = budgetC.getBudgetContribution().floatValue() + montant;
				}
			}
			if (nbrProjet != 0 && !sourceC.getSourceFinancementNom().equals("Autre")) {
				// this.sourceNombreModel.set(sourceC.getSourceFinancementNom(), nbrProjet);
				int pourcentage = (nbrProjet * 100) / this.projets.size();

				values.add(nbrProjet);
				labels.add(sourceC.getSourceFinancementNom() + ": " + nbrProjet);
			}

		}

		dataSet.setData(values);
		List<String> bgColors = new ArrayList<>();
		for (Number string : values) {
			Random random = new Random();
		    int red = random.nextInt(256);
		    int green = random.nextInt(256);
		    int blue = random.nextInt(256);

		    
		        red = (red + Color.red.getRed()) / 2;
		        green = (green + Color.green.getGreen()) / 2;
		        blue = (blue + Color.BLUE.getBlue()) / 2;
		        
				bgColors.add("rgb("+String.valueOf(red)+","+String.valueOf(green)+","+String.valueOf(blue)+")");

		}
		dataSet.setBackgroundColor(bgColors);
		data.addChartDataSet(dataSet);
		data.setLabels(labels);
		sourceNombreModel.setExtender("ext3");
		sourceNombreModel.setData(data);

	}

	private void createPieNombreByInstrument2() {
		this.instrumentNombreModel = new BarChartModel();
		ChartData data = new ChartData();


		BarChartDataSet   dataSet = new BarChartDataSet();
		List<Number> values = new ArrayList<>();
		List<String> labels = new ArrayList<>();
		
		List<Chapitre> chapitres = new ArrayList<Chapitre>();
		ChapitreViewService chapitreService = new ChapitreViewService();
		chapitres = chapitreService.findAll();

		Map<String, Integer> nbrProjBySect = new ConcurrentHashMap<String, Integer>();

		for (GetProjectData projectData : this.getProjectDatas) {
			for (SecteurHierar secteurHierar : projectData.getChapitres()) {
				if (!nbrProjBySect.isEmpty()) {
					for (String key : nbrProjBySect.keySet()) {
						if (nbrProjBySect.get(secteurHierar.getSecteurNom()) != null) {
							if (nbrProjBySect.get(key)!= null && key.equals(secteurHierar.getSecteurNom())) {
								nbrProjBySect.put(key, nbrProjBySect.get(key)+1);
							} 
						}else {
							nbrProjBySect.put(secteurHierar.getSecteurNom(), 1);
						}
						
					}
				}else {
					nbrProjBySect.put(secteurHierar.getSecteurNom(), 1);
				}
			}
		}
		
		
		
		for (String key : nbrProjBySect.keySet()) {
			if (nbrProjBySect.get(key) >= 1) {
				values.add(nbrProjBySect.get(key));
				labels.add(key);
			}
		}

		

		dataSet.setData(values);
		List<String> bgColors = new ArrayList<>();
		
		for (Number string : values) {
			Random random = new Random();
		    int red = random.nextInt(256);
		    int green = random.nextInt(256);
		    int blue = random.nextInt(256);

		    
		        red = (red + Color.red.getRed()) / 2;
		        green = (green + Color.green.getGreen()) / 2;
		        blue = (blue + Color.BLUE.getBlue()) / 2;
		        
				bgColors.add("rgb("+String.valueOf(red)+","+String.valueOf(green)+","+String.valueOf(blue)+")");

		}
		
		dataSet.setBackgroundColor(bgColors);
		data.addChartDataSet(dataSet);
		data.setLabels(labels);

		 //Options
        BarChartOptions options = new BarChartOptions();
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        linearAxes.setOffset(true);
        CartesianLinearTicks ticks = new CartesianLinearTicks();
        ticks.setBeginAtZero(true);
        linearAxes.setTicks(ticks);
        cScales.addYAxesData(linearAxes);
        options.setScales(cScales);

        Title title = new Title();
        title.setDisplay(true);
        title.setText("Répartitions des projets par Domaines");
        options.setTitle(title);

        Legend legend = new Legend();
        legend.setDisplay(true);
        legend.setPosition("top");
        LegendLabel legendLabels = new LegendLabel();
        legendLabels.setFontStyle("bold");
        legendLabels.setFontColor("#2980B9");
        legendLabels.setFontSize(24);
        legend.setLabels(legendLabels);
        options.setLegend(legend);


		instrumentNombreModel.setExtender("ext4");
		instrumentNombreModel.setData(data);
		instrumentNombreModel.setOptions(options);

		
	}
	
	public Color generateRandomColor(Color mix) {
	    Random random = new Random();
	    int red = random.nextInt(256);
	    int green = random.nextInt(256);
	    int blue = random.nextInt(256);

	    // mix the color
	    if (mix != null) {
	        red = (red + mix.getRed()) / 2;
	        green = (green + mix.getGreen()) / 2;
	        blue = (blue + mix.getBlue()) / 2;
	    }

	    Color color = new Color(red, green, blue);
	    return color;
	}

	public void onValueChanged(ActionEvent ev) {
		System.out.println("TEST");
	}

	public BarChartModel getInstrumentNombreModel() {
		return instrumentNombreModel;
	}

	public void setInstrumentNombreModel(BarChartModel instrumentNombreModel) {
		this.instrumentNombreModel = instrumentNombreModel;
	}

	public List<Instrumentfinancement> getInstruments() {
		return instruments;
	}

	public void setInstruments(List<Instrumentfinancement> instruments) {
		this.instruments = instruments;
	}

	public List<SourceFinancement> getSources() {
		return sources;
	}

	public void setSources(List<SourceFinancement> sources) {
		this.sources = sources;
	}

	public PieChartModel getSourceNombreModel() {
		return sourceNombreModel;
	}

	public void setSourceNombreModel(PieChartModel sourceNombreModel) {
		this.sourceNombreModel = sourceNombreModel;
	}

	public float getContributionTotal() {
		return contributionTotal;
	}

	public void setContributionTotal(float contributionTotal) {
		this.contributionTotal = contributionTotal;
	}

	public float getContributionUe() {
		return contributionUe;
	}

	public void setContributionUe(float contributionUe) {
		this.contributionUe = contributionUe;
	}

	public List<Region> getRegions() {
		return regions;
	}

	public void setRegions(List<Region> regions) {
		this.regions = regions;
	}

	public List<Localisation> getLocalisations() {
		return localisations;
	}

	public void setLocalisations(List<Localisation> localisations) {
		this.localisations = localisations;
	}

	public List<Budget> getBudgets() {
		return budgets;
	}

	public void setBudgets(List<Budget> budgets) {
		this.budgets = budgets;
	}

	public List<Projet> getProjets() {
		return projets;
	}

	public void setProjets(List<Projet> projets) {
		this.projets = projets;
	}

	public ProjetViewService getProjetService() {
		return projetService;
	}

	public void setProjetService(ProjetViewService projetService) {
		this.projetService = projetService;
	}

	public RegionViewService getRegionService() {
		return regionService;
	}

	public void setRegionService(RegionViewService regionService) {
		this.regionService = regionService;
	}

	public InstrumentfinancementViewService getInstrumentService() {
		return instrumentService;
	}

	public void setInstrumentService(InstrumentfinancementViewService instrumentService) {
		this.instrumentService = instrumentService;
	}

	public SourceFinancementViewService getSourceService() {
		return sourceService;
	}

	public void setSourceService(SourceFinancementViewService sourceService) {
		this.sourceService = sourceService;
	}

	public LocalisationViewService getLocalisationService() {
		return localisationService;
	}

	public void setLocalisationService(LocalisationViewService localisationService) {
		this.localisationService = localisationService;
	}

	public BudgetViewService getBudgetService() {
		return budgetService;
	}

	public void setBudgetService(BudgetViewService budgetService) {
		this.budgetService = budgetService;
	}

	public PolarAreaChartModel getPieContractantTypeModel() {
		return pieContractantTypeModel;
	}

	public void setPieContractantTypeModel(PolarAreaChartModel pieContractantTypeModel) {
		this.pieContractantTypeModel = pieContractantTypeModel;
	}

	public List<Contractanttype> getContractantTypes() {
		return contractantTypes;
	}

	public void setContractantTypes(List<Contractanttype> contractantTypes) {
		this.contractantTypes = contractantTypes;
	}

	public List<Contractant> getContractants() {
		return contractants;
	}

	public void setContractants(List<Contractant> contractants) {
		this.contractants = contractants;
	}

	public ContractanttypeViewService getContractantTypeService() {
		return contractantTypeService;
	}

	public void setContractantTypeService(ContractanttypeViewService contractantTypeService) {
		this.contractantTypeService = contractantTypeService;
	}

	public ContractantViewService getContractantService() {
		return contractantService;
	}

	public void setContractantService(ContractantViewService contractantService) {
		this.contractantService = contractantService;
	}

	public PieChartModel getPieModel() {
		return pieModel;
	}

	public void setPieModel(PieChartModel pieModel) {
		this.pieModel = pieModel;
	}

	public PolarAreaChartModel getPolarAreaModel() {
		return polarAreaModel;
	}

	public void setPolarAreaModel(PolarAreaChartModel polarAreaModel) {
		this.polarAreaModel = polarAreaModel;
	}

	public LineChartModel getLineModel() {
		return lineModel;
	}

	public void setLineModel(LineChartModel lineModel) {
		this.lineModel = lineModel;
	}

	public LineChartModel getCartesianLinerModel() {
		return cartesianLinerModel;
	}

	public void setCartesianLinerModel(LineChartModel cartesianLinerModel) {
		this.cartesianLinerModel = cartesianLinerModel;
	}

	public BarChartModel getBarModel() {
		return barModel;
	}

	public void setBarModel(BarChartModel barModel) {
		this.barModel = barModel;
	}

	public BarChartModel getBarModel2() {
		return barModel2;
	}

	public void setBarModel2(BarChartModel barModel2) {
		this.barModel2 = barModel2;
	}

	public HorizontalBarChartModel getHbarModel() {
		return hbarModel;
	}

	public void setHbarModel(HorizontalBarChartModel hbarModel) {
		this.hbarModel = hbarModel;
	}

	public BarChartModel getStackedBarModel() {
		return stackedBarModel;
	}

	public void setStackedBarModel(BarChartModel stackedBarModel) {
		this.stackedBarModel = stackedBarModel;
	}

	public BarChartModel getStackedGroupBarModel() {
		return stackedGroupBarModel;
	}

	public void setStackedGroupBarModel(BarChartModel stackedGroupBarModel) {
		this.stackedGroupBarModel = stackedGroupBarModel;
	}

	public RadarChartModel getRadarModel() {
		return radarModel;
	}

	public void setRadarModel(RadarChartModel radarModel) {
		this.radarModel = radarModel;
	}

	public RadarChartModel getRadarModel2() {
		return radarModel2;
	}

	public void setRadarModel2(RadarChartModel radarModel2) {
		this.radarModel2 = radarModel2;
	}

	public BubbleChartModel getBubbleModel() {
		return bubbleModel;
	}

	public void setBubbleModel(BubbleChartModel bubbleModel) {
		this.bubbleModel = bubbleModel;
	}

	public BarChartModel getMixedModel() {
		return mixedModel;
	}

	public void setMixedModel(BarChartModel mixedModel) {
		this.mixedModel = mixedModel;
	}

	public DonutChartModel getDonutModel() {
		return donutModel;
	}

	public void setDonutModel(DonutChartModel donutModel) {
		this.donutModel = donutModel;
	}

	public ScatterChartModel getScatterModel() {
		return scatterModel;
	}

	public void setScatterModel(ScatterChartModel scatterModel) {
		this.scatterModel = scatterModel;
	}
}