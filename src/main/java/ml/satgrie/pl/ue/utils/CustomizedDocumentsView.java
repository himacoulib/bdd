//package ml.satgrie.pl.ue.utils;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//import java.util.TreeMap;
//
//import javax.annotation.PostConstruct;
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.RequestScoped;
//
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.primefaces.model.DualListModel;
//
//import ml.satgrie.pl.ue.model.Budget;
//import ml.satgrie.pl.ue.model.Calendrier;
//import ml.satgrie.pl.ue.model.Chapitre;
//import ml.satgrie.pl.ue.model.Contractant;
//import ml.satgrie.pl.ue.model.Instrumentfinancement;
//import ml.satgrie.pl.ue.model.Localisation;
//import ml.satgrie.pl.ue.model.Projet;
//import ml.satgrie.pl.ue.model.Projetchapitre;
//import ml.satgrie.pl.ue.model.Secteur;
//import ml.satgrie.pl.ue.model.SourceFinancement;
//import ml.satgrie.pl.ue.services.BudgetViewService;
//import ml.satgrie.pl.ue.services.CalendrierViewService;
//import ml.satgrie.pl.ue.services.ChapitreViewService;
//import ml.satgrie.pl.ue.services.ContractantViewService;
//import ml.satgrie.pl.ue.services.InstrumentfinancementViewService;
//import ml.satgrie.pl.ue.services.LocalisationViewService;
//import ml.satgrie.pl.ue.services.ProjetViewService;
//import ml.satgrie.pl.ue.services.ProjetchapitreViewService;
//import ml.satgrie.pl.ue.services.SecteurViewService;
//
//@ManagedBean(name = "customizedDocumentsView")
//@RequestScoped
//public class CustomizedDocumentsView {
//
//	private List<Projet> projets;
//
//	private ProjetViewService projetService;
//	private List<Calendrier> calendriers;
//
//	private List<Budget> budgets;
//	private List<Contractant> contractants;
//	private List<Instrumentfinancement> instruments;
//	private List<Secteur> secteurs;
//	private List<Projetchapitre> projetChapitres;
//	private List<Chapitre> chapitres;
//	private List<Localisation> localisations;
//
//	private CalendrierViewService calendrierService;
//	private BudgetViewService budgetService;
//	private ContractantViewService contractantService;
//	private InstrumentfinancementViewService instrumentService;
//	private SecteurViewService secteurService;
//	private ProjetchapitreViewService projchapitreService;
//	private ChapitreViewService chapitreService;
//	private LocalisationViewService localisationService;
//	private List<SourceFinancement> sourceFinancement;
//
//	private DualListModel<XlsFile> xlsFiles;
//
//	@PostConstruct
//	public void init() {
//		List<XlsFile> xlsFileSource = Arrays.asList(new XlsFile("1", " Numero de contrat"), new XlsFile("2", " Titre"),
//				new XlsFile("3", " Description"), new XlsFile("4", " Objectif General"),
//				new XlsFile("5", " Objectig Specifique"), new XlsFile("6", " Source de Financement"),
//				new XlsFile("7", " Budget"), new XlsFile("8", " Domaines"), new XlsFile("9", " Secteurs"),
//				new XlsFile("10", " Region"), new XlsFile("11", " Cercle"), new XlsFile("12", " Commune"),
//				new XlsFile("13", " Coordonnée GPS"), new XlsFile("14", " Calendier"));
//		List<XlsFile> xlsFileTarget = new ArrayList<>();
//
//		xlsFileTarget.add(new XlsFile("15", " statut"));
//
//		this.xlsFiles = new DualListModel<>(new ArrayList<>(xlsFileSource), xlsFileTarget);
//
//		this.calendrierService = new CalendrierViewService();
//		this.budgetService = new BudgetViewService();
//		this.contractantService = new ContractantViewService();
//		this.instrumentService = new InstrumentfinancementViewService();
//		this.secteurService = new SecteurViewService();
//		this.projchapitreService = new ProjetchapitreViewService();
//		this.chapitreService = new ChapitreViewService();
//		this.localisationService = new LocalisationViewService();
//
//		this.projets = this.projetService.findAll();
//		this.calendriers = this.calendrierService.findAll();
//		this.budgets = this.budgetService.findAll();
//		this.contractants = this.contractantService.findAll();
//		this.instruments = this.instrumentService.findAll();
//		this.secteurs = this.secteurService.findAll();
//		this.projetChapitres = this.projchapitreService.findAll();
//		this.chapitres = this.chapitreService.findAll();
//		this.localisations = this.localisationService.findAll();
//
//	}
//
//	public void readMyAssocImTypVariables() {
//
//		// Blank workbook
//		XSSFWorkbook workbook = new XSSFWorkbook();
//
//		// Create a blank sheet
//		XSSFSheet sheet = workbook.createSheet("Liste des projets");
//
//		// This data needs to be written (Object[])
//		Map<String, Object[]> data = new TreeMap<String, Object[]>();
//		data.put("1", new Object[] { "ID", "NAME", "LASTNAME" });
//		data.put("2", new Object[] { 1, "Amit", "Shukla" });
//		data.put("3", new Object[] { 2, "Lokesh", "Gupta" });
//		data.put("4", new Object[] { 3, "John", "Adwards" });
//		data.put("5", new Object[] { 4, "Brian", "Schultz" });
//
//		// Iterate over data and write to sheet
//		Set<String> keyset = data.keySet();
//		int rownum = 0;
//		for (String key : keyset) {
//			Row row = sheet.createRow(rownum++);
//			Object[] objArr = data.get(key);
//			int cellnum = 0;
//			for (Object obj : objArr) {
//				Cell cell = row.createCell(cellnum++);
//				if (obj instanceof String)
//					cell.setCellValue((String) obj);
//				else if (obj instanceof Integer)
//					cell.setCellValue((Integer) obj);
//			}
//		}
//		try {
//			// Write the workbook in file system
//			FileOutputStream out = new FileOutputStream(new File("howtodoinjava_demo.xlsx"));
//			workbook.write(out);
//			out.close();
//			System.out.println("howtodoinjava_demo.xlsx written successfully on disk.");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		for (XlsFile header : xlsFiles.getTarget()) {
//			if (xlsFiles.getTarget().size() == 15) {
//
//			} else {
//				if (header.getName().equals(" Numero de contrat")) {
//
//				}
//				if (header.getName().equals(" Numero de contrat")) {
//
//				}
//			}
//
//		}
//
//		System.out.println(">>>>>>>>>>>>>>> entered");
//		List<XlsFile> sourceVariables = this.xlsFiles.getSource();
//		List<XlsFile> targetVariables = this.xlsFiles.getTarget();
//
//		for (XlsFile sourceVariable : sourceVariables) {
//			System.out.println(">>>>>>>>>>>>> I am a source variable: " + sourceVariable.getName().toString());
//		}
//		for (XlsFile targetVariable : targetVariables) {
//			System.out.println("------------ I am a target variable: " + targetVariable.getName().toString());
//		}
//	}
//
//	/**
//	 * @return the xlsFiles
//	 */
//	public DualListModel<XlsFile> getXlsFiles() {
//		return xlsFiles;
//	}
//
//	/**
//	 * @param xlsFiles the xlsFiles to set
//	 */
//	public void setXlsFiles(DualListModel<XlsFile> xlsFiles) {
//		this.xlsFiles = xlsFiles;
//	}
//
//	/**
//	 * @return the projets
//	 */
//	public List<Projet> getProjets() {
//		return projets;
//	}
//
//	/**
//	 * @param projets the projets to set
//	 */
//	public void setProjets(List<Projet> projets) {
//		this.projets = projets;
//	}
//
//	/**
//	 * @return the projetService
//	 */
//	public ProjetViewService getProjetService() {
//		return projetService;
//	}
//
//	/**
//	 * @param projetService the projetService to set
//	 */
//	public void setProjetService(ProjetViewService projetService) {
//		this.projetService = projetService;
//	}
//
//	/**
//	 * @return the calendriers
//	 */
//	public List<Calendrier> getCalendriers() {
//		return calendriers;
//	}
//
//	/**
//	 * @param calendriers the calendriers to set
//	 */
//	public void setCalendriers(List<Calendrier> calendriers) {
//		this.calendriers = calendriers;
//	}
//
//	/**
//	 * @return the budgets
//	 */
//	public List<Budget> getBudgets() {
//		return budgets;
//	}
//
//	/**
//	 * @param budgets the budgets to set
//	 */
//	public void setBudgets(List<Budget> budgets) {
//		this.budgets = budgets;
//	}
//
//	/**
//	 * @return the contractants
//	 */
//	public List<Contractant> getContractants() {
//		return contractants;
//	}
//
//	/**
//	 * @param contractants the contractants to set
//	 */
//	public void setContractants(List<Contractant> contractants) {
//		this.contractants = contractants;
//	}
//
//	/**
//	 * @return the instruments
//	 */
//	public List<Instrumentfinancement> getInstruments() {
//		return instruments;
//	}
//
//	/**
//	 * @param instruments the instruments to set
//	 */
//	public void setInstruments(List<Instrumentfinancement> instruments) {
//		this.instruments = instruments;
//	}
//
//	/**
//	 * @return the secteurs
//	 */
//	public List<Secteur> getSecteurs() {
//		return secteurs;
//	}
//
//	/**
//	 * @param secteurs the secteurs to set
//	 */
//	public void setSecteurs(List<Secteur> secteurs) {
//		this.secteurs = secteurs;
//	}
//
//	/**
//	 * @return the projetChapitres
//	 */
//	public List<Projetchapitre> getProjetChapitres() {
//		return projetChapitres;
//	}
//
//	/**
//	 * @param projetChapitres the projetChapitres to set
//	 */
//	public void setProjetChapitres(List<Projetchapitre> projetChapitres) {
//		this.projetChapitres = projetChapitres;
//	}
//
//	/**
//	 * @return the chapitres
//	 */
//	public List<Chapitre> getChapitres() {
//		return chapitres;
//	}
//
//	/**
//	 * @param chapitres the chapitres to set
//	 */
//	public void setChapitres(List<Chapitre> chapitres) {
//		this.chapitres = chapitres;
//	}
//
//	/**
//	 * @return the localisations
//	 */
//	public List<Localisation> getLocalisations() {
//		return localisations;
//	}
//
//	/**
//	 * @param localisations the localisations to set
//	 */
//	public void setLocalisations(List<Localisation> localisations) {
//		this.localisations = localisations;
//	}
//
//	/**
//	 * @return the calendrierService
//	 */
//	public CalendrierViewService getCalendrierService() {
//		return calendrierService;
//	}
//
//	/**
//	 * @param calendrierService the calendrierService to set
//	 */
//	public void setCalendrierService(CalendrierViewService calendrierService) {
//		this.calendrierService = calendrierService;
//	}
//
//	/**
//	 * @return the budgetService
//	 */
//	public BudgetViewService getBudgetService() {
//		return budgetService;
//	}
//
//	/**
//	 * @param budgetService the budgetService to set
//	 */
//	public void setBudgetService(BudgetViewService budgetService) {
//		this.budgetService = budgetService;
//	}
//
//	/**
//	 * @return the contractantService
//	 */
//	public ContractantViewService getContractantService() {
//		return contractantService;
//	}
//
//	/**
//	 * @param contractantService the contractantService to set
//	 */
//	public void setContractantService(ContractantViewService contractantService) {
//		this.contractantService = contractantService;
//	}
//
//	/**
//	 * @return the instrumentService
//	 */
//	public InstrumentfinancementViewService getInstrumentService() {
//		return instrumentService;
//	}
//
//	/**
//	 * @param instrumentService the instrumentService to set
//	 */
//	public void setInstrumentService(InstrumentfinancementViewService instrumentService) {
//		this.instrumentService = instrumentService;
//	}
//
//	/**
//	 * @return the secteurService
//	 */
//	public SecteurViewService getSecteurService() {
//		return secteurService;
//	}
//
//	/**
//	 * @param secteurService the secteurService to set
//	 */
//	public void setSecteurService(SecteurViewService secteurService) {
//		this.secteurService = secteurService;
//	}
//
//	/**
//	 * @return the projchapitreService
//	 */
//	public ProjetchapitreViewService getProjchapitreService() {
//		return projchapitreService;
//	}
//
//	/**
//	 * @param projchapitreService the projchapitreService to set
//	 */
//	public void setProjchapitreService(ProjetchapitreViewService projchapitreService) {
//		this.projchapitreService = projchapitreService;
//	}
//
//	/**
//	 * @return the chapitreService
//	 */
//	public ChapitreViewService getChapitreService() {
//		return chapitreService;
//	}
//
//	/**
//	 * @param chapitreService the chapitreService to set
//	 */
//	public void setChapitreService(ChapitreViewService chapitreService) {
//		this.chapitreService = chapitreService;
//	}
//
//	/**
//	 * @return the localisationService
//	 */
//	public LocalisationViewService getLocalisationService() {
//		return localisationService;
//	}
//
//	/**
//	 * @param localisationService the localisationService to set
//	 */
//	public void setLocalisationService(LocalisationViewService localisationService) {
//		this.localisationService = localisationService;
//	}
//
//	/**
//	 * @return the sourceFinancement
//	 */
//	public List<SourceFinancement> getSourceFinancement() {
//		return sourceFinancement;
//	}
//
//	/**
//	 * @param sourceFinancement the sourceFinancement to set
//	 */
//	public void setSourceFinancement(List<SourceFinancement> sourceFinancement) {
//		this.sourceFinancement = sourceFinancement;
//	}
//
//}
