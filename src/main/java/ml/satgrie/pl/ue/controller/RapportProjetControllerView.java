package ml.satgrie.pl.ue.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.servlet.ServletContext;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.primefaces.PrimeFaces;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.DualListModel;
import org.primefaces.model.StreamedContent;

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
import ml.satgrie.pl.ue.utils.SessionUtils;
import ml.satgrie.pl.ue.utils.XlsFile;

@ManagedBean(name = "rapportProjetControllerView")
@ViewScoped
public class RapportProjetControllerView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Etatavancement> etatavancements;
	EtatavancementViewService etService;

	private StreamedContent file;
	private DefaultStreamedContent download;

	private List<Projet> projets;

	private GetProjectData selectedProjet;

	private ProjetViewService projetService;
	private List<Calendrier> calendriers;

	private Boolean fileIsReady;

	private List<Budget> budgets;
	private List<Commune> communes;
	private List<Region> regions;
	private List<Cercle> cercles;
	private List<Contractant> contractants;
	private List<Instrumentfinancement> instruments;
	private List<Secteur> secteurs;
	private List<Projetchapitre> projetChapitres;
	private List<Chapitre> chapitres;
	private List<Localisation> localisations;

	private List<Calendrier> calendriersFilter;
	private List<Budget> budgetsFilter;
	private List<Contractant> contractantsFilter;
	private List<Instrumentfinancement> instrumentsFilter;
	private List<Secteur> secteursFilter;
	private List<Chapitre> chapitresFilter;
	private List<Localisation> localisationsFilter;
	private List<Contractanttype> contractanttypes;

	private CalendrierViewService calendrierService;
	private ContractanttypeViewService contractTypeService;
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
	private LocalisationSecteurViewService localisationSecteurViewService;
	private List<SourceFinancement> sourceFinancement;
	private List<LocalisationSecteur> localisationSecteurs;
	private List<GetProjectData> getProjectDatas;

	private List<SecteurHierar> secteurHierars;
	List<String> getChapNom;

	private DualListModel<XlsFile> xlsFiles;

	@PostConstruct
	public void init() {

		List<XlsFile> xlsFileSource = Arrays.asList(new XlsFile("1", " Numero de contrat"), new XlsFile("2", " Titre"),
				new XlsFile("3", " Description"), new XlsFile("4", " Objectif General"),
				new XlsFile("5", " Objectif Specifique"), new XlsFile("6", " Source de Financement"),
				new XlsFile("7", " Budget"), new XlsFile("8", " Domaines"), new XlsFile("9", " Secteurs"),
				new XlsFile("10", " Region"), new XlsFile("11", " Cercle"), new XlsFile("12", " Commune"),
				new XlsFile("14", " Type Contractant"), new XlsFile("15", " Contractant"), new XlsFile("16", " statut"),
				new XlsFile("17", " Date de Debut"), new XlsFile("18", " Date de Fin"));
		List<XlsFile> xlsFileTarget = new ArrayList<>();

		this.xlsFiles = new DualListModel<>(new ArrayList<>(xlsFileSource), xlsFileTarget);

		this.etService = new EtatavancementViewService();
		this.communeService = new CommuneViewService();
		this.contractTypeService = new ContractanttypeViewService();
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
		this.calendrierService = new CalendrierViewService();
		this.etService = new EtatavancementViewService();

		this.etatavancements = etService.findAll();
		this.contractanttypes = contractTypeService.findAll();
		this.calendriers = calendrierService.findAll();
		this.secteurHierars = secteurHierarService.findAll();
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

		Utilisateur utilisateur = new Utilisateur();
		utilisateur = (Utilisateur) SessionUtils.getRequest().getSession().getAttribute("user");
		List<Projet> updatedProject = new ArrayList<>();
		List<Budget> budgetstmp = new ArrayList<Budget>();
		Set<Instrumentfinancement> instrumentfinancements = new HashSet<Instrumentfinancement>();
		for (Projet proj : this.projets) {
			long budTotal = 0;
			if (proj.getProjetBudget().intValue() == 0) {

				for (Budget budC : budgets) {
					if (budC.getProjet().getProjetId().toString().equals(proj.getProjetId().toString())) {
						budTotal = budTotal + budC.getBudgetContribution().longValue();
						budgetstmp.add(budC);
						budC.getBudgetId();
						for (Instrumentfinancement instrumentfinancement : instruments) {
							if (budC.getInstrumentfinancement().getInstrumentfinancementId() == instrumentfinancement
									.getInstrumentfinancementId()) {
								instrumentfinancements.add(instrumentfinancement);
							}
						}
					}
				}
				proj.setProjetBudget(new BigDecimal(budTotal));
				proj.setBudgets(budgetstmp);
			}
			updatedProject.add(proj);
		}
		GetProjectData getProjectDataTmp = new GetProjectData();

		this.getProjectDatas = new ArrayList<GetProjectData>();
		this.getProjectDatas = getProjectDataTmp.getProjectAllInfo(this.projets, this.budgets, localisationSecteurs,
				secteurHierars, localisations, getProjectDatas, regions, cercles, communes, contractants, calendriers,
				contractanttypes, this.etatavancements, this.chapitres);

		// this.projets = updatedProject;
	}

	public void setSelectedProjet(GetProjectData selectedProjet) {
		this.selectedProjet = selectedProjet;
		PrimeFaces.current().ajax().update("form:manage-product-content", "manage-product-content");
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

	public ProjetViewService getProjetService() {
		return projetService;
	}

	public void setProjetService(ProjetViewService projetService) {
		this.projetService = projetService;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<Calendrier> getCalendriers() {
		return calendriers;
	}

	public void setCalendriers(List<Calendrier> calendriers) {
		this.calendriers = calendriers;
	}

	public List<Budget> getBudgets() {
		return budgets;
	}

	public void setBudgets(List<Budget> budgets) {
		this.budgets = budgets;
	}

	public List<Contractant> getContractants() {
		return contractants;
	}

	public void setContractants(List<Contractant> contractants) {
		this.contractants = contractants;
	}

	public List<Instrumentfinancement> getInstruments() {
		return instruments;
	}

	public void setInstruments(List<Instrumentfinancement> instruments) {
		this.instruments = instruments;
	}

	public List<Secteur> getSecteurs() {
		return secteurs;
	}

	public void setSecteurs(List<Secteur> secteurs) {
		this.secteurs = secteurs;
	}

	public List<Calendrier> getCalendriersFilter() {
		return calendriersFilter;
	}

	public void setCalendriersFilter(List<Calendrier> calendriersFilter) {
		this.calendriersFilter = calendriersFilter;
	}

	public List<Budget> getBudgetsFilter() {
		return budgetsFilter;
	}

	public void setBudgetsFilter(List<Budget> budgetsFilter) {
		this.budgetsFilter = budgetsFilter;
	}

	public List<Contractant> getContractantsFilter() {
		return contractantsFilter;
	}

	public void setContractantsFilter(List<Contractant> contractantsFilter) {
		this.contractantsFilter = contractantsFilter;
	}

	public List<Instrumentfinancement> getInstrumentsFilter() {
		return instrumentsFilter;
	}

	public void setInstrumentsFilter(List<Instrumentfinancement> instrumentsFilter) {
		this.instrumentsFilter = instrumentsFilter;
	}

	public List<Secteur> getSecteursFilter() {
		return secteursFilter;
	}

	public void setSecteursFilter(List<Secteur> secteursFilter) {
		this.secteursFilter = secteursFilter;
	}

	public List<Projetchapitre> getProjetChapitres() {
		return projetChapitres;
	}

	public void setProjetChapitres(List<Projetchapitre> projetChapitres) {
		this.projetChapitres = projetChapitres;
	}

	public List<Chapitre> getChapitres() {
		return chapitres;
	}

	public void setChapitres(List<Chapitre> chapitres) {
		this.chapitres = chapitres;
	}

	public List<Chapitre> getChapitresFilter() {
		return chapitresFilter;
	}

	public void setChapitresFilter(List<Chapitre> chapitresFilter) {
		this.chapitresFilter = chapitresFilter;
	}

	public CalendrierViewService getCalendrierService() {
		return calendrierService;
	}

	public void setCalendrierService(CalendrierViewService calendrierService) {
		this.calendrierService = calendrierService;
	}

	public BudgetViewService getBudgetService() {
		return budgetService;
	}

	public void setBudgetService(BudgetViewService budgetService) {
		this.budgetService = budgetService;
	}

	public ContractantViewService getContractantService() {
		return contractantService;
	}

	public void setContractantService(ContractantViewService contractantService) {
		this.contractantService = contractantService;
	}

	public InstrumentfinancementViewService getInstrumentService() {
		return instrumentService;
	}

	public void setInstrumentService(InstrumentfinancementViewService instrumentService) {
		this.instrumentService = instrumentService;
	}

	public SecteurViewService getSecteurService() {
		return secteurService;
	}

	public void setSecteurService(SecteurViewService secteurService) {
		this.secteurService = secteurService;
	}

	public ProjetchapitreViewService getProjchapitreService() {
		return projchapitreService;
	}

	public void setProjchapitreService(ProjetchapitreViewService projchapitreService) {
		this.projchapitreService = projchapitreService;
	}

	public ChapitreViewService getChapitreService() {
		return chapitreService;
	}

	public void setChapitreService(ChapitreViewService chapitreService) {
		this.chapitreService = chapitreService;
	}

	public List<Localisation> getLocalisations() {
		return localisations;
	}

	public void setLocalisations(List<Localisation> localisations) {
		this.localisations = localisations;
	}

	public List<Localisation> getLocalisationsFilter() {
		return localisationsFilter;
	}

	public void setLocalisationsFilter(List<Localisation> localisationsFilter) {
		this.localisationsFilter = localisationsFilter;
	}

	public LocalisationViewService getLocalisationService() {
		return localisationService;
	}

	public void setLocalisationService(LocalisationViewService localisationService) {
		this.localisationService = localisationService;
	}

	public void readMyAssocImTypVariables() throws ParseException {
		System.out.println(">>>>>>>>>>>>>>> entered");
		List<XlsFile> sourceVariables = this.xlsFiles.getSource();
		List<XlsFile> targetVariables = this.xlsFiles.getTarget();

		Map<String, Object[]> map = new HashMap<String, Object[]>();
		List<String> headerNames = new ArrayList<String>();
		for (XlsFile sourceVariable : sourceVariables) {
			System.out.println(">>>>>>>>>>>>> I am a source variable: " + sourceVariable.getName().toString());
		}
		for (XlsFile targetVariable : targetVariables) {
			System.out.println("------------ I am a target variable: " + targetVariable.getName().toString());
			headerNames.add(targetVariable.getName());
		}

		// Create a Workbook
		Workbook workbook = new XSSFWorkbook(); // new HSSFWorkbook() for generating `.xls` file

		/*
		 * CreationHelper helps us create instances of various things like DataFormat,
		 * Hyperlink, RichTextString etc, in a format (HSSF, XSSF) independent way
		 */
		CreationHelper createHelper = workbook.getCreationHelper();

		// Create a Sheet
		Sheet sheet = workbook.createSheet("ProjetListe");
		// Create a Font for styling header cells
		Font headerFont = workbook.createFont();
		headerFont.setBold(true);
		headerFont.setFontHeightInPoints((short) 14);
		headerFont.setColor(IndexedColors.RED.getIndex());

		// Create a CellStyle with the font
		CellStyle headerCellStyle = workbook.createCellStyle();
		headerCellStyle.setFont(headerFont);

		// Create a Row
		Row headerRow = sheet.createRow(0);

		// Create cells
		for (int i = 0; i < headerNames.size(); i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(headerNames.get(i));
			cell.setCellStyle(headerCellStyle);
		}

		// Create Cell Style for formatting Date
		CellStyle dateCellStyle = workbook.createCellStyle();
		dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));
		int rowNum = 1;

		getProjectData(headerNames, sheet, rowNum);

		// Resize all columns to fit the content size
		for (int i = 0; i < headerNames.size(); i++) {
			sheet.autoSizeColumn(i);
		}

		// Write the output to a file
		FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream("D:\\project-report.xlsx");

			FacesContext facesContext = FacesContext.getCurrentInstance();
			ExternalContext externalContext = facesContext.getExternalContext();
			externalContext.setResponseContentType("application/vnd.ms-excel");
			externalContext.setResponseHeader("Content-Disposition", "attachment; filename=\"project-report.xlsx\"");

			workbook.write(externalContext.getResponseOutputStream());
			facesContext.responseComplete();
			// workbook.write(fileOut);

			fileOut.close();
			// Closing the workbook
			workbook.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @param headerNames
	 * @param sheet
	 * @param rowNum
	 * @throws ParseException
	 */
	private void getProjectData(List<String> headerNames, Sheet sheet, int rowNum) throws ParseException {
		for (GetProjectData projetData : this.getProjectDatas) {
			Row row = sheet.createRow(rowNum++);
			int cellRow = 0;
			for (String nom : headerNames) {

				if (nom.equals(" Numero de contrat")) {
					row.createCell(cellRow).setCellValue(projetData.getProjetNumContrat());
					cellRow++;
				}
				if (nom.equals(" Titre")) {
					row.createCell(cellRow).setCellValue(projetData.getProjetTitre());
					cellRow++;
				}
				if (nom.equals(" Description")) {
					row.createCell(cellRow).setCellValue(projetData.getProjetDescription());
					cellRow++;
				}
				if (nom.equals(" Objectif General")) {
					row.createCell(cellRow).setCellValue(projetData.getProjetObjectifGeneraux());
					cellRow++;
				}
				if (nom.equals(" Objectig Specifique")) {
					row.createCell(cellRow).setCellValue(projetData.getProjetObjectifSpecifiques());
					cellRow++;
				}
				if (nom.equals(" Source de Financement")) {
					List<String> sourceFinancementNom = new ArrayList<String>();

					/***************************/
					List<Instrumentfinancement> instrumentfinancementsTmp = new ArrayList<Instrumentfinancement>();
					for (SourceFinancement sourceFin : projetData.getProjetSourceFinancements()) {
						sourceFinancementNom.add(sourceFin.getSourceFinancementNom());
					}

					row.createCell(cellRow).setCellValue(removeDuplicates(sourceFinancementNom));
					cellRow++;
				}
				if (nom.equals(" Budget")) {
					row.createCell(cellRow).setCellValue(projetData.getProjetBudget());
					cellRow++;
				}
				if (nom.equals(" Domaines")) {
					List<String> chapitresName = new ArrayList<String>();

					for (SecteurHierar secteurHierar : projetData.getChapitres()) {
						chapitresName.add(secteurHierar.getCodeSecteur()+"-"+ secteurHierar.getSecteurNom() + "/ ");
					}

					row.createCell(cellRow).setCellValue(removeDuplicates(chapitresName));
					cellRow++;
				}
				if (nom.equals(" Secteurs")) {
					List<String> secteurNameTmp = new ArrayList<String>();
					for (SecteurHierar localisationSecteur : projetData.getProjetSecteurs()) {
						secteurNameTmp.add(localisationSecteur.getCodeSecteur()+"-"+localisationSecteur.getSecteurNom()+ "/ ");
					}

					row.createCell(cellRow).setCellValue(removeDuplicates(secteurNameTmp));
					cellRow++;
				}
				if (nom.equals(" Region")) {
					List<String> regionProjName = new ArrayList<String>();
					for (Region regionTmp : projetData.getRegions()) {
						regionProjName.add(regionTmp.getRegionNom());
					}

					row.createCell(cellRow).setCellValue(removeDuplicates(regionProjName));
					cellRow++;
				}
				if (nom.equals(" Cercle")) {
					List<String> cercleName = new ArrayList<String>();
					for (Cercle cercleTmp : projetData.getCercles()) {
						cercleName.add(cercleTmp.getCerlceName());
					}
					row.createCell(cellRow).setCellValue(removeDuplicates(cercleName));
					cellRow++;
				}

				if (nom.equals(" Commune")) {
					List<String> communeName = new ArrayList<String>();
					for (Commune communeTmp : projetData.getCommunes()) {
						communeName.add(communeTmp.getCommuneNom());
					}
					
					row.createCell(cellRow).setCellValue(removeDuplicates(communeName));
					cellRow++;
				}
				
				if (nom.equals(" Type Contractant")) {
					List<String> contractantTypeName = new ArrayList<String>();
					for (Contractanttype contractantTypeTmp : projetData.getContractantTypes()) {
						contractantTypeName.add(contractantTypeTmp.getContractantTypeNom());
					}
					row.createCell(cellRow).setCellValue(removeDuplicates(contractantTypeName));
					cellRow++;
				}

				if (nom.equals(" Contractant")) {
					List<String> contractantName = new ArrayList<String>();
					for (Contractant contractantTmp : projetData.getContractants()) {
						contractantName.add(contractantTmp.getContractantNom());
					}
					row.createCell(cellRow).setCellValue(removeDuplicates(contractantName));
					cellRow++;
				}

				if (nom.equals(" Date de Debut")) {
					List<String> calendierNom = new ArrayList<String>();
					for (Calendrier calendrierTmp : projetData.getCalendriers()) {
						if (calendrierTmp.getCalendrierTypeId() == 1 ) {
							calendierNom.add(calendrierTmp.getCalendrierDate().toString());
						}
					}
					row.createCell(cellRow).setCellValue(removeDuplicates(calendierNom));
					cellRow++;
				}
				
				if (nom.equals(" Date de Fin")) {
					List<String> calendierNom = new ArrayList<String>();
					for (Calendrier calendrierTmp : projetData.getCalendriers()) {
						if (calendrierTmp.getCalendrierTypeId() == 2 ) {
							calendierNom.add(calendrierTmp.getCalendrierDate().toString());
						} if (calendrierTmp.getCalendrierTypeId() == 3 ) {
							calendierNom.add(calendrierTmp.getCalendrierDate().toString());
						}		
					}
					row.createCell(cellRow).setCellValue(removeDuplicates(calendierNom));
					cellRow++;
				}


				if (nom.equals(" Status")) {
					row.createCell(cellRow).setCellValue(projetData.getStatus());
					cellRow++;
				}
			}
		}

	}

	/**
	 * @return the fileIsReady
	 */
	public Boolean getFileIsReady() {
		return fileIsReady;
	}

	/**
	 * @param fileIsReady the fileIsReady to set
	 */
	public void setFileIsReady(Boolean fileIsReady) {
		this.fileIsReady = fileIsReady;
	}

	public static String removeDuplicates(List<String> regionProjName) {

		// Create a new ArrayList
		List<String> newList = new ArrayList<String>();

		// Traverse through the first list
		for (String element : regionProjName) {

			// If this element is not present in newList
			// then add it
			if (!newList.contains(element)) {

				newList.add(element);
			}
		}

		String delDup = (String) newList.toString();
		String remBrak = delDup.substring(1, delDup.length() - 1);
		// return the new list
		
		return remBrak;
	}

	/**
	 * @return the file
	 */
	public StreamedContent getFile() {
		return file;
	}

	/**
	 * @param file the file to set
	 */
	public void setFile(StreamedContent file) {
		this.file = file;
	}

	/**
	 * @return the xlsFiles
	 */
	public DualListModel<XlsFile> getXlsFiles() {
		return xlsFiles;
	}

	/**
	 * @param xlsFiles the xlsFiles to set
	 */
	public void setXlsFiles(DualListModel<XlsFile> xlsFiles) {
		this.xlsFiles = xlsFiles;
	}

	/**
	 * @return the sourceFinancement
	 */
	public List<SourceFinancement> getSourceFinancement() {
		return sourceFinancement;
	}

	/**
	 * @param sourceFinancement the sourceFinancement to set
	 */
	public void setSourceFinancement(List<SourceFinancement> sourceFinancement) {
		this.sourceFinancement = sourceFinancement;
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
	 * @return the download
	 */
	public DefaultStreamedContent getDownload() {
		return download;
	}

	/**
	 * @param download the download to set
	 */
	public void setDownload(DefaultStreamedContent download) {
		this.download = download;
	}

	/**
	 * @return the getChapNom
	 */
	public List<String> getGetChapNom() {
		return getChapNom;
	}

	/**
	 * @param getChapNom the getChapNom to set
	 */
	public void setGetChapNom(List<String> getChapNom) {
		this.getChapNom = getChapNom;
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

}