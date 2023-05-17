package ml.satgrie.pl.ue.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import org.primefaces.PrimeFaces;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.ToggleEvent;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.file.UploadedFiles;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import ml.satgrie.pl.ue.model.Budget;
import ml.satgrie.pl.ue.model.Calendrier;
import ml.satgrie.pl.ue.model.Cercle;
import ml.satgrie.pl.ue.model.Chapitre;
import ml.satgrie.pl.ue.model.Commune;
import ml.satgrie.pl.ue.model.Contractant;
import ml.satgrie.pl.ue.model.Contractanttype;
import ml.satgrie.pl.ue.model.Devise;
import ml.satgrie.pl.ue.model.Document;
import ml.satgrie.pl.ue.model.Etatavancement;
import ml.satgrie.pl.ue.model.GetProjectData;
import ml.satgrie.pl.ue.model.Instrumentfinancement;
import ml.satgrie.pl.ue.model.Localisation;
import ml.satgrie.pl.ue.model.LocalisationId;
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
import ml.satgrie.pl.ue.services.DocumentViewService;
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
import ml.satgrie.pl.ue.utils.SessionUtils;
import ml.satgrie.pl.ue.utils.Singleton;

@ManagedBean(name = "projetFormControllerView")
@ViewScoped
public class ProjetFormControllerView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Boolean projInfo;
	private Boolean proChap;
	private Boolean projCalend;
	private Boolean projPart;
	private Boolean projBudget;
	private Boolean projEtat;
	private Boolean projLoc;
	private Boolean projDoc;

	private static final String FOLDER_TEMP_FILE = "D://images//";

	private static final String FOLDER_TEMP_DOCUMENT = "D://documents//";

	private static final String MAIL_SATGRIE = "b.coulibaly@satgrie.com";

	private List<Projet> projets;

	private GetProjectData selectedProjet;

	private LocalisationSecteur localisationSecteur;

	private List<LocalisationSecteur> localisationSecteurs;

	LocalisationSecteurViewService localisationSecteurService;

	private ProjetViewService projetService;

	private String chapitreSelected;

	private ChapitreViewService chapitreService;

	private List<Chapitre> chapitres;

	private List<String> chapitresListString;

	private LocalDate dateCalendrier;

	private Calendrier calendrier;

	private CalendrierViewService calendrierService;

	private Contractant contractant;

	private ContractantViewService contractantService;

	private ContractanttypeViewService typecontractantService;

	private List<Contractanttype> typecontractants;

	private String selectedTypeContractant;

	private MapModel advancedModel;

	private Marker marker;

	private Budget budget;

	private BudgetViewService budgetService;

	private DeviseViewService deviseService;

	private Etatavancement etatavancement;

	private List<Etatavancement> etatavancements;

	private Etatavancement etatavancementSelected;

	private List<Devise> devises;

	private Devise devise;

	private List<SourceFinancement> sources;

	private List<Instrumentfinancement> instruments;

	private List<Instrumentfinancement> instrumentsFilter;
	private List<Secteur> secteursGetByChap;

	private String selectedInstrument;

	private String selectedSource;

	private SourceFinancementViewService sourceService;

	private EtatavancementViewService etatService;

	private InstrumentfinancementViewService instrumentService;

	private Calendrier selectedCalendrier;

	private Contractant selectedContractant;

	private Budget selectedBudget;

	private Instrumentfinancement instruAutre;

	private String selectedRegion;

	private String selectedCercle;

	private String selectedCommune;

	private List<Localisation> localisations;

	private Localisation selectedLocalisation;

	private List<Region> regions;

	private List<Cercle> cercles;

	private List<Commune> communes;

	private List<Commune> communesFilter;

	private List<Cercle> cerclesFilter;

	private RegionViewService regionService;

	private CercleViewService cercleService;

	private CommuneViewService communeService;

	private LocalisationViewService localisationService;

	private List<Secteur> secteurs;

	private List<Secteur> selectedSecteurs;

	private SecteurViewService secteurService;

	private List<String> selectedSecteursString;

	private ProjetchapitreViewService projetchapitreService;

	private MapModel simpleModel;

	private List<SecteurHierar> secteurHierars;

	private SecteurHierarViewService secteurHierarService;

	private List<Projetchapitre> projetchapitres;

	private UploadedFiles files;

	private List<String> regionStrings;

	private List<String> cercleStrings;

	private List<String> communeStrings;
	private GetProjectData gmapProjectData;

	private List<Budget> budgets;

	private List<GetProjectData> getProjectDatas;

	private List<Contractant> contractants;

	private List<Calendrier> calendriers;

	private List<Contractanttype> contractanttypes;
	private ContractanttypeViewService contractantTypeService;

	@SuppressWarnings("unchecked")
	@PostConstruct
	public void init() {

		this.sources = new ArrayList<>();
		this.contractantTypeService = new ContractanttypeViewService();
		this.sourceService = new SourceFinancementViewService();
		this.localisationSecteurService = new LocalisationSecteurViewService();
		this.secteurHierarService = new SecteurHierarViewService();
		this.instrumentService = new InstrumentfinancementViewService();
		this.regionService = new RegionViewService();
		this.cercleService = new CercleViewService();
		this.calendrierService = new CalendrierViewService();
		this.contractantService = new ContractantViewService();
		this.typecontractantService = new ContractanttypeViewService();
		this.communeService = new CommuneViewService();
		this.chapitreService = new ChapitreViewService();
		this.projetService = new ProjetViewService();
		this.budgetService = new BudgetViewService();
		this.deviseService = new DeviseViewService();
		this.secteurService = new SecteurViewService();
		this.localisationService = new LocalisationViewService();
		this.projetchapitreService = new ProjetchapitreViewService();
		this.etatService = new EtatavancementViewService();

		this.contractants = this.contractantService.findAll();
		this.budgets = this.budgetService.findAll();
		this.localisationSecteurs = this.localisationSecteurService.findAll();
		this.secteurHierars = this.secteurHierarService.findAll();
		this.sources = this.sourceService.findAll();
		this.instruments = this.instrumentService.findAll();
		this.instruAutre = this.instrumentService.findByName("Autres");
		this.regions = this.regionService.findAll();
		this.typecontractants = this.typecontractantService.findAll();
		this.devises = this.deviseService.findAll();
		this.projets = this.projetService.findAll();
		this.cercles = this.cercleService.findAll();
		this.communes = this.communeService.findAll();
		this.chapitres = this.chapitreService.findAll();
		this.chapitresListString = new ArrayList<String>();
		this.secteurs = this.secteurService.findAll();
		this.etatavancements = this.etatService.findAll();
		this.calendriers = this.calendrierService.findAll();
		this.localisations = localisationService.findAll();
		this.contractanttypes = contractantTypeService.findAll();

		this.calendrier = new Calendrier();

		this.contractant = new Contractant();

		this.budget = new Budget();

		this.etatavancement = new Etatavancement();

		this.selectedProjet = (GetProjectData) FacesContext.getCurrentInstance().getExternalContext().getFlash()
				.get("selectedProjet");// this.selectedProjet;

		if (this.selectedProjet.getProjetId() == null) {
			this.selectedProjet = new GetProjectData();
			this.selectedProjet.setChapitres(new ArrayList<SecteurHierar>());

		} else {

			if (selectedProjet.getLocalisations() != null) {
				this.simpleModel = new DefaultMapModel();
				int i = 0;
				for (Localisation loc : selectedProjet.getLocalisations()) {
					

					// Basic marker
					// "D:/Projects/JavaProject/PlateformeBdd/src/main/resources/mapstyle/mapIcons/chapIcon/105a84ed-6dd5-4447-b57b-da8ca3a80664.png"
					LatLng coord1 = new LatLng(Double.parseDouble(loc.getLocalisationLat()) + (Math.random() / 2000),
							Double.parseDouble(loc.getLocalisationLong()) + (Math.random() / 2000));

					simpleModel
							.addOverlay(new Marker(coord1, this.selectedProjet.getProjetTitre(), this.selectedProjet));
					i++;
				}

			}
			this.selectedSecteursString = new ArrayList<>();

			if (this.selectedProjet.getChapitres() != null && !this.selectedProjet.getChapitres().isEmpty()) {
				for (SecteurHierar projchapC : selectedProjet.getProjetSecteurs()) {

					this.selectedSecteursString.add(projchapC.getSecteurNom());
				}
			}
			if (this.selectedProjet.getProjetSecteurs() != null && !this.selectedProjet.getProjetSecteurs().isEmpty()) {
				for (SecteurHierar chap : selectedProjet.getChapitres()) {
					this.chapitresListString.add(chap.getSecteurNom());
				}
			}

			this.selectedSecteursString = this.selectedSecteursString.stream().distinct().collect(Collectors.toList());
			this.chapitresListString = this.chapitresListString.stream().distinct().collect(Collectors.toList());

			this.regionStrings = new ArrayList<>();
			this.cercleStrings = new ArrayList<>();
			this.communeStrings = new ArrayList<>();

			if (this.selectedProjet.getRegions() != null && !this.selectedProjet.getRegions().isEmpty()) {
				for (Region region : selectedProjet.getRegions()) {
					this.regionStrings.add(region.getRegionNom());
				}
			}

			if (this.selectedProjet.getCercles() != null && !this.selectedProjet.getCercles().isEmpty()) {
				for (Cercle cercle : selectedProjet.getCercles()) {
					this.cercleStrings.add(cercle.getCerlceName());
				}
			}

			if (this.selectedProjet.getCommunes() != null && !this.selectedProjet.getCommunes().isEmpty()) {
				for (Commune commune : selectedProjet.getCommunes()) {
					this.communeStrings.add(commune.getCommuneNom());
				}
			}

			this.regionStrings = (List<String>) this.regionStrings.stream().distinct().collect(Collectors.toList());
			this.cercleStrings = (List<String>) this.cercleStrings.stream().distinct().collect(Collectors.toList());
			this.communeStrings = (List<String>) this.communeStrings.stream().distinct().collect(Collectors.toList());

			for (Devise deviseC : this.devises) {
				if (deviseC.getDeviseNom().equals("EURO")) {
					this.devise = deviseC;
				}
			}

//			Singleton.getInstance().setCercleFilter(cercleStringsInit);
//			Singleton.getInstance().setCommuneFilter(communeStringsInit);
			this.cerclesFilter = (List<Cercle>) FacesContext.getCurrentInstance().getExternalContext().getFlash()
					.get("cerclesFilter");

			this.communesFilter = (List<Commune>) FacesContext.getCurrentInstance().getExternalContext().getFlash()
					.get("communesFilter");

			this.instrumentsFilter = (List<Instrumentfinancement>) FacesContext.getCurrentInstance()
					.getExternalContext().getFlash().get("instrumentsFilter");
//			GetProjectData getProjectDataTmp = new GetProjectData();
//			this.getProjectDatas = new ArrayList<GetProjectData>();
//			this.getProjectDatas = getProjectDataTmp.getProjectAllInfo(this.projets, this.budgets, localisationSecteurs,
//					secteurHierars, localisations, getProjectDatas, regions, cercles, communes, contractants, calendriers,
//					contractanttypes, this.etatavancements, this.chapitres);
		}

	}

	public void handleChapChange() {

		this.secteursGetByChap = new ArrayList<Secteur>();
		for (String chapSet : this.chapitresListString) {
			for (SecteurHierar secteurHierarToFind : this.secteurHierars) {
				if (secteurHierarToFind.getSecteurParentId() == 0) {
					for (Secteur sect : this.secteurs) {
						if (chapSet.equals(secteurHierarToFind.getSecteurNom())) {
							if (secteurHierarToFind.getFormerSectorId() == sect.getId().getChapitreId()) {
								this.secteursGetByChap.add(sect);
							}
						}

					}
				}
			}
		}
		this.secteurs = secteursGetByChap;

		PrimeFaces.current().ajax().update("formSecteur:sect");
	}

	public void handleFileUpload(FileUploadEvent event) {
		try {
			this.copyFile(event.getFile().getFileName(), event.getFile().getInputStream());
			Photo photo = new Photo();
			for (Projet projet : this.projets) {
				if (selectedProjet.getProjetId().toString().equals(projet.getProjetId().toString())) {
					photo.setProjet(projet);
				}
			}

			photo.setPhotoNom(event.getFile().getFileName());
			int min = 1;
			int max = 999_999;
			int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
			photo.setPhotoId(randomNum);

			Utilisateur utilisateur = new Utilisateur();
			utilisateur = (Utilisateur) SessionUtils.getRequest().getSession().getAttribute("user");
			photo.setLastModifiedBy(utilisateur.getUtilisateurId());
			photo.setLastModifiedDate(new Date());
			photo.setPhotoType(1);
			photo.setPhotoUrl(FOLDER_TEMP_FILE + event.getFile().getFileName());
			PhotoViewService photoService = new PhotoViewService();
			photoService.persist(photo);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Photo ", event.getFile().getFileName() + " enregistrer."));

		} catch (IOException e) {
			System.out.println(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Une erreure est survenue"));
		}
		PrimeFaces.current().ajax().update("formPhoto:messagesPhoto", "formPhoto");
	}

	public void handleDocumentUpload(FileUploadEvent event) {
		try {
			this.copyDocument(event.getFile().getFileName(), event.getFile().getInputStream());
			Document document = new Document();
			for (Projet projet : this.projets) {
				if (selectedProjet.getProjetId().toString().equals(projet.getProjetId().toString())) {
					document.setProjet(projet);
				}
			}
			document.setDocumentNom(event.getFile().getFileName());
			int min = 1;
			int max = 999_999;
			int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
			document.setDocumentId(randomNum);

			Utilisateur utilisateur = new Utilisateur();
			utilisateur = (Utilisateur) SessionUtils.getRequest().getSession().getAttribute("user");
			document.setLastModifiedBy(utilisateur.getUtilisateurId());
			document.setLastModifiedDate(new Date());
			DocumentViewService documentService = new DocumentViewService();
			documentService.persist(document);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Document ", event.getFile().getFileName() + " enregistrer."));

		} catch (IOException e) {
			System.out.println(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Une erreure est survenue"));
		}
		PrimeFaces.current().ajax().update("formDocument:messagesDocument", "formDocument");
	}

	private void copyDocument(String fileName, InputStream in) {
		try {

			// write the inputStream to a FileOutputStream

			OutputStream out = new FileOutputStream(new File(FOLDER_TEMP_DOCUMENT + fileName));

			int read = 0;
			byte[] bytes = new byte[1024];

			while ((read = in.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}

			in.close();
			out.flush();
			out.close();

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	private void copyFile(String fileName, InputStream in) {
		try {

			// write the inputStream to a FileOutputStream

			OutputStream out = new FileOutputStream(new File(FOLDER_TEMP_FILE + fileName));

			int read = 0;
			byte[] bytes = new byte[1024];

			while ((read = in.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}

			in.close();
			out.flush();
			out.close();

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public void saveEtatavancement() {
		try {
			for (Projet projet : this.projets) {
				if (selectedProjet.getProjetId().toString().equals(projet.getProjetId().toString())) {
					this.etatavancement.setProjet(projet);
				}
			}
			this.etatavancement.setEtatAvancementProjetId(this.selectedProjet.getProjetId());
			this.etatavancement.setLastModifiedBy(new Date());
			this.etatavancement.setLastModifiedDate(new Date());
			int min = 1;
			int max = 999_999;
			int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
			this.etatavancement.setEtatAvancementId(randomNum);
			this.etatService.persist(this.etatavancement);
			if (selectedProjet.getEtatavancements() == null) {
				selectedProjet.setEtatavancements(new ArrayList<Etatavancement>());
				selectedProjet.getEtatavancements().add(etatavancement);
			} else {
				selectedProjet.getEtatavancements().add(etatavancement);
			}
			FacesContext.getCurrentInstance().getExternalContext().getFlash().put("selectedProjet",
					this.selectedProjet);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Etat avancement ajouté "));
			FacesContext context = FacesContext.getCurrentInstance();
			this.projEtat = true;
			context.getExternalContext().redirect("projetForm.xhtml");
			context.getExternalContext().getFlash().setKeepMessages(true);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Une erreure est survenue"));
		}
		PrimeFaces.current().ajax().update("formEtat:messagesEtat", "formEtat");

	}

	public void deleteLocalisation() {
		try {
			this.localisationService.delete(this.selectedLocalisation);
			this.selectedProjet.getLocalisations().remove(this.selectedLocalisation);
			FacesContext.getCurrentInstance().getExternalContext().getFlash().put("cercleFilter", null);
			FacesContext.getCurrentInstance().getExternalContext().getFlash().put("communeFilter", null);

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Localisation supprimer"));

		} catch (Exception e) {
			System.out.println(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Une erreure est survenue"));
		}
		PrimeFaces.current().ajax().update("formLocalisation:messagesLocalisation", "formLocalisation");

	}

	public void deleteEtatavancement() {
		try {
			this.etatService.delete(this.etatavancementSelected.getEtatAvancementId());
			this.selectedProjet.getEtatavancements().remove(this.etatavancementSelected);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Etat avancement supprimer"));

		} catch (Exception e) {
			System.out.println(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Une erreure est survenue"));
		}
		PrimeFaces.current().ajax().update("formEtat:messagesEtat", "formEtat");

	}

	public void saveSecteur() {

		if (this.selectedProjet.getChapitres().size() == 0) {
			if (!this.chapitresListString.isEmpty() && !this.selectedSecteursString.isEmpty()) {
				List<SecteurHierar> chapitresAndSecteurToSave = new ArrayList<SecteurHierar>();
				List<SecteurHierar> secteurToSave = new ArrayList<SecteurHierar>();
				List<SecteurHierar> chapitresToSave = new ArrayList<SecteurHierar>();
				if (this.chapitresListString != null && !this.chapitresListString.isEmpty()) {
					for (String chapSelected : chapitresListString) {
						for (SecteurHierar chapitreC : this.secteurHierars) {
							if (chapitreC.getSecteurParentId() == 0 && chapitreC.getSecteurNom().equals(chapSelected)) {
								chapitresAndSecteurToSave.add(chapitreC);
								chapitresToSave.add(chapitreC);
							}
						}
					}
				}

				for (String secteurNameC : this.selectedSecteursString) {
					for (SecteurHierar secteurC : this.secteurHierars) {
						if (secteurNameC.equals(secteurC.getSecteurNom())) {
							chapitresAndSecteurToSave.add(secteurC);
							secteurToSave.add(secteurC);
						}
					}
				}

				try {
					for (SecteurHierar secteurHierarFinded : chapitresAndSecteurToSave) {
						int min = 1;
						int max = 999_999;
						int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
						LocalisationSecteur localisationSecteurSaved = new LocalisationSecteur();
						localisationSecteurSaved.setIdSecteur(secteurHierarFinded.getSecteurHierarId());
						localisationSecteurSaved.setIdLocalisationSecteur(randomNum);
						localisationSecteurSaved.setIdProjet(this.selectedProjet.getProjetId());
						// localisationSecteurSaved.setIdLocalisation(randomNum + 1);
						this.localisationSecteurService.persist(localisationSecteurSaved);
					}

					this.selectedProjet.setChapitres(chapitresToSave);
					this.selectedProjet.setProjetSecteurs(secteurToSave);
					FacesContext.getCurrentInstance().getExternalContext().getFlash().put("selectedProjet",
							this.selectedProjet);
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Secteur(s) ajouter"));
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Projet ajouter avec succès"));
					FacesContext context = FacesContext.getCurrentInstance();
					this.proChap = true;
					context.getExternalContext().redirect("projetForm.xhtml");
					context.getExternalContext().getFlash().setKeepMessages(true);
				} catch (Exception e) {
					System.out.println(e.getMessage());
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Une erreure est survenue"));
				}
			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Aucun Domaines ou secteur selectionner"));
			}
		} else {
			try {
				// this.projetchapitreService.deleteMultiple(this.projetchapitres);

				this.selectedSecteurs = new ArrayList<>();

				List<SecteurHierar> chapitresAndSecteurToSave = new ArrayList<SecteurHierar>();
				List<SecteurHierar> secteurToSave = new ArrayList<SecteurHierar>();
				List<SecteurHierar> chapitresToSave = new ArrayList<SecteurHierar>();

				if (this.chapitresListString != null && !this.chapitresListString.isEmpty()) {
					for (String chapSelected : chapitresListString) {
						for (SecteurHierar chapitreC : this.secteurHierars) {
							if (chapitreC.getSecteurParentId() == 0 && chapitreC.getSecteurNom().equals(chapSelected)) {
								chapitresAndSecteurToSave.add(chapitreC);
								chapitresToSave.add(chapitreC);
							}
						}
					}
				}

				for (String secteurNameC : this.selectedSecteursString) {
					for (SecteurHierar secteurC : this.secteurHierars) {
						if (secteurNameC.equals(secteurC.getSecteurNom())) {
							chapitresAndSecteurToSave.add(secteurC);
							secteurToSave.add(secteurC);
						}
					}
				}
//				for (String secteurNameC : this.selectedSecteursString) {
//					for (Secteur secteurC : this.secteurs) {
//						if (secteurNameC.equals(secteurC.getSecteurName())) {
//							this.selectedSecteurs.add(secteurC);
//							break;
//						}
//					}
//				}
				if (!chapitresAndSecteurToSave.isEmpty() && chapitresAndSecteurToSave.size() > 0) {
					int min = 1;
					int max = 999_999;
					int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
					List<LocalisationSecteur> localisationSecteursToUp = new ArrayList<LocalisationSecteur>();
					LocalisationSecteur localisationSecteurSaved = new LocalisationSecteur();
					for (LocalisationSecteur loSecteur : this.localisationSecteurs) {
						if (loSecteur.getIdProjet() == this.selectedProjet.getProjetId().intValue()) {
							localisationSecteursToUp.add(loSecteur);
						}
					}
					for (LocalisationSecteur localisationSecteur : localisationSecteursToUp) {
						this.localisationSecteurService.delete(localisationSecteur.getIdLocalisationSecteur());
					}
					for (SecteurHierar secteurHierarFinded : chapitresAndSecteurToSave) {

						localisationSecteurSaved
								.setIdLocalisationSecteur(ThreadLocalRandom.current().nextInt(min, max + 1));
						// localisationSecteurService.findById(id)
						// localisationSecteurSaved =
						// localisationSecteurService.findById(this.selectedProjet.getL);
						localisationSecteurSaved.setIdSecteur(secteurHierarFinded.getSecteurHierarId());
						// localisationSecteurSaved.setIdLocalisationSecteur(randomNum);
						localisationSecteurSaved.setIdProjet(this.selectedProjet.getProjetId());
						localisationSecteurSaved.setIdLocalisation(randomNum + 1);

						this.localisationSecteurService.persist(localisationSecteurSaved);
					}

					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Secteur(s) mise à jour"));
					FacesContext context = FacesContext.getCurrentInstance();
					this.selectedProjet.setChapitres(chapitresToSave);
					this.selectedProjet.setProjetSecteurs(secteurToSave);
					FacesContext.getCurrentInstance().getExternalContext().getFlash().put("selectedProjet",
							this.selectedProjet);
					context.getExternalContext().redirect("projetForm.xhtml");
					context.getExternalContext().getFlash().setKeepMessages(true);
				} else {
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Aucun secteur selectionner"));
				}

			} catch (Exception e) {
				System.out.println(e.getMessage());
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Une erreure est survenue"));
			}

		}

		PrimeFaces.current().ajax().update("formSecteur:messagesSecteur", "formSecteur");

	}

	public void saveLocalisation() {

		List<Commune> communesToSave = new ArrayList<Commune>();
		List<Cercle> cerclesToSave = new ArrayList<Cercle>();
		List<Region> regionToSave = new ArrayList<Region>();

		if (this.communeStrings.size() > 0) {
			for (String communeString : this.communeStrings) {
				for (Commune communeC : this.communes) {
					if (communeC.getCommuneNom().equals(communeString)) {
						communesToSave.add(communeC);
					}
				}
				
			}
		}
		if (this.cercleStrings.size() > 0) {
			for (String cercleString : this.cercleStrings) {
				for (Cercle cercle : this.cercles) {
					if (cercle.getCerlceName().equals(cercleString)) {
						cerclesToSave.add(cercle);
					}
				}
			}
		}
		if (this.regionStrings.size() > 0) {
			for (String regionString : this.regionStrings) {
				for (Region region : this.regions) {
					if (region.getRegionNom().equals(regionString)) {
						regionToSave.add(region);
					}
				}
			}
		}
		List<Localisation> localitions = new ArrayList<Localisation>();
		List<Integer> idS = new ArrayList<Integer>();
		if (this.selectedProjet.getLocalisations() != null) {
			if (!this.selectedProjet.getLocalisations().isEmpty()) {

				for (Localisation localisation : this.selectedProjet.getLocalisations()) {
					idS.add(localisation.getId().getLocalisationId());
					this.localisationService.delete(localisation);
				}
			}
		}


//		for (Commune comP : this.selectedProjet.getCommunes()) {
//			if (this.communeStrings.size() > 0) {
//				for (String communeString : this.communeStrings) {
//					if (!communeString.equals(comP.getCommuneNom())) {
//						for (Localisation localisation2 : this.localisations) {
//							if (localisation2.getId().getCommuneId() == comP.getId().getCommuneId()) {
//								if (localisation2 !=null) {
//									this.localisationService.delete(localisation2.getId());
//								}
//								
//							}
//						}
//					}
//
//				}
//			}
//		}
		this.selectedProjet.setLocalisations(new ArrayList<Localisation>());
		this.selectedProjet.setRegions(new ArrayList<Region>());
		this.selectedProjet.setCercles(new ArrayList<Cercle>());
		this.selectedProjet.setCommunes(new ArrayList<Commune>());

		this.communeStrings = this.communeStrings.stream().distinct().collect(Collectors.toList());
		if (this.communeStrings.size() > 0) {
			for (String communeString : this.communeStrings) {
				for (Commune communeC : this.communes) {
					if (communeC.getCommuneNom().equals(communeString)) {
						LocalisationId locId = new LocalisationId();
						locId.setCommuneId(communeC.getId().getCommuneId());
						locId.setCerlceId(communeC.getCercle().getId().getCerlceId());
						locId.setRegionId(communeC.getId().getRegionId());
						locId.setPaysId(communeC.getId().getPaysId());
						int min = 1;
						int max = 999_999;
						int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
						locId.setLocalisationId(randomNum);

						Localisation localisation = new Localisation();
						localisation.setId(locId);

						Utilisateur utilisateur = new Utilisateur();
						utilisateur = (Utilisateur) SessionUtils.getRequest().getSession().getAttribute("user");
						localisation.setLocalisationLastModifiedBy(utilisateur.getUtilisateurId());
						localisation.setLocalisationLastModifiedDate(new Date());
						localisation.setProjetId(this.selectedProjet.getProjetId());
						localisation.setLocalisationLat(communeC.getCercle().getCerlceLat());
						localisation.setLocalisationLong(communeC.getCercle().getCerlcelong());
						localisation.setLocalisationDescActifs("Region: "
								+ communeC.getCercle().getRegion().getRegionNom() + " Cercle: "
								+ communeC.getCercle().getCerlceName() + " Commune: " + communeC.getCommuneNom());
						// localisations.add(localisation);
						try {
							this.localisationService.persist(localisation);
							this.selectedProjet.getLocalisations().add(localisation);
							this.selectedProjet.setLocalisations(this.selectedProjet.getLocalisations().stream()
									.distinct().collect(Collectors.toList()));
							this.selectedProjet.getRegions().addAll(regionToSave);
							this.selectedProjet.getCercles().addAll(cerclesToSave);
							this.selectedProjet.getCommunes().addAll(communesToSave);
							FacesContext.getCurrentInstance().getExternalContext().getFlash().put("selectedProjet",
									this.selectedProjet);
						} catch (Exception e) {
							System.out.println(e);
							FacesContext.getCurrentInstance().addMessage(null,
									new FacesMessage("Une erreure est survenue"));
						}
					}
				}
			}
		}

		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Localisation ajouter"));
		try {
			FacesContext.getCurrentInstance().getExternalContext().getFlash().put("cercleFilter", null);
			FacesContext.getCurrentInstance().getExternalContext().getFlash().put("communeFilter", null);
			FacesContext context = FacesContext.getCurrentInstance();
			this.projLoc = true;
//			if (this.selectedProjet.getLocalisations() == null) {
//				this.selectedProjet.setLocalisations(new ArrayList<Localisation>());
//				this.selectedProjet.setLocalisations(localitions);
//			} else {
//				this.selectedProjet.setLocalisations(localitions);
//			}

			this.selectedProjet
					.setRegions(this.selectedProjet.getRegions().stream().distinct().collect(Collectors.toList()));
			context.getExternalContext().redirect("projetForm.xhtml");
			context.getExternalContext().getFlash().setKeepMessages(true);
		} catch (Exception e) {
			System.out.println(e);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Une erreure est survenue"));
		}
		PrimeFaces.current().ajax().update("formLocalisation:messagesLocalisation", "formLocalisation");
	}

	public void handleRegionChange() {
		this.cerclesFilter = new ArrayList<>();
		if (this.regionStrings.size() > 0) {
			String val = this.regionStrings.get(0);
			if (val.equals("Toutes les regions")) {
				this.cerclesFilter = this.cercles;
			} else {
				for (String region : this.regionStrings) {
					for (Cercle cercleC : this.cercles) {
						if (cercleC.getRegion().getRegionNom().equals(region))
							this.cerclesFilter.add(cercleC);
					}
				}
			}
		}

		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("cerclesFilter", this.cerclesFilter);
		PrimeFaces.current().ajax().update("formLocalisation:cercle");
	}

	public void handleCercleChange() {
		this.communesFilter = new ArrayList<>();
		if (this.cercleStrings.size() > 0) {
			String val = this.cercleStrings.get(0);
			if (val.equals("Tous les cercles")) {
				this.communesFilter = this.communes;
			} else {
				for (String cercle : this.cercleStrings) {
					for (Commune communeC : this.communes) {
						if (communeC.getCercle().getCerlceName().equals(cercle))
							this.communesFilter.add(communeC);
					}
				}
			}
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Veuillez remplir le champ cercle"));
		}
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("communesFilter", this.communesFilter);
		PrimeFaces.current().ajax().update("formLocalisation:commune");
	}

	public void saveBudget() throws IOException {
		if (this.selectedProjet.getProjetId() != 0) {
			Instrumentfinancement defaultInstrumentfinancement = new Instrumentfinancement();
			for (Instrumentfinancement instruC : this.instruments) {
				if (instruC.getSourcefinancement().getSourceFinancementNom().equals(this.selectedSource)) {
					this.budget.setInstrumentfinancement(instruC);
				}
			}
			if (this.budget.getInstrumentfinancement() != null) {

			}
			this.budget.setDevise(this.devise);
			for (Projet projet : this.projets) {
				if (selectedProjet.getProjetId().toString().equals(projet.getProjetId().toString())) {
					this.budget.setProjet(projet);
				}
			}

			int min = 1;
			int max = 999_999;
			int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);

			this.budget.setBudgetId(randomNum);

			Utilisateur utilisateur = new Utilisateur();
			utilisateur = (Utilisateur) SessionUtils.getRequest().getSession().getAttribute("user");
			this.budget.setLastModifiedBy(utilisateur.getUtilisateurId());
			this.budget.setLastModifiedDate(new Date());

			try {
				Projet projForVerif = new Projet();
				projForVerif = this.projetService.findById(this.selectedProjet.getProjetId());
				int total = 0;
				if (!projForVerif.getBudgets().isEmpty()) {
					Budget autreOldBudget = new Budget();
					for (Budget budget : projForVerif.getBudgets()) {
						if (!budget.getInstrumentfinancement().getInstrumentFinancementNom().equals("Autres")) {
							total = total + budget.getBudgetContribution().intValue();
						}
						if (budget.getInstrumentfinancement().getInstrumentfinancementId()
								.equals(instruAutre.getInstrumentfinancementId())) {
							autreOldBudget = budget;

						}
					}

					if (autreOldBudget.getBudgetId() == null) {
						total = total + this.budget.getBudgetContribution().intValue();
					}

					if (total < this.selectedProjet.getProjetBudget() && autreOldBudget.getBudgetId() != null) {
						if (this.selectedProjet.getProjetBudget() > this.budget.getBudgetContribution().intValue()) {
							if (autreOldBudget.getBudgetId() != null) {
								autreOldBudget.setBudgetContribution(
										new BigDecimal(autreOldBudget.getBudgetContribution().intValue()
												- this.budget.getBudgetContribution().intValue()));
								this.budgetService.persist(this.budget);
								if (autreOldBudget.getBudgetContribution().intValue() != 0) {
									this.budgetService.update(autreOldBudget);
									FacesContext.getCurrentInstance().getExternalContext().getFlash()
											.put("instrumentFilter", null);
									//// this.updateSingletonProject();
									FacesContext.getCurrentInstance().addMessage(null,
											new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Budget ajouter"));
									updateBudgetList();
									PrimeFaces.current().ajax().update("formBudget:messagesBudget", "formBudget");
								} else {
									this.budgetService.delete(autreOldBudget.getBudgetId());
									updateBudgetList();
									PrimeFaces.current().ajax().update("formBudget:messagesBudget", "formBudget");
									FacesContext.getCurrentInstance().getExternalContext().getFlash()
											.put("instrumentFilter", null);
									//// this.updateSingletonProject();
									FacesContext.getCurrentInstance().addMessage(null,
											new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Budget ajouter"));
								}

							} else {
								Budget budCompl = new Budget();
								budCompl.setDevise(this.devise);
								for (Projet projet : this.projets) {
									if (selectedProjet.getProjetId().toString()
											.equals(projet.getProjetId().toString())) {
										budCompl.setProjet(projet);
									}
								}
								randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
								budCompl.setBudgetId(randomNum);
								budCompl.setLastModifiedBy(utilisateur.getUtilisateurId());
								budCompl.setLastModifiedDate(new Date());
								budCompl.setInstrumentfinancement(this.instruAutre);
								budCompl.setBudgetContribution(new BigDecimal(this.selectedProjet.getProjetBudget()
										- (this.budget.getBudgetContribution().intValue()
												+ (total - this.budget.getBudgetContribution().intValue()))));
								this.budgetService.persist2(this.budget, budCompl);
								FacesContext.getCurrentInstance().getExternalContext().getFlash()
										.put("instrumentFilter", null);
								//// this.updateSingletonProject();
								FacesContext.getCurrentInstance().addMessage(null,
										new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Budget ajouter"));
								updateBudgetList();
								PrimeFaces.current().ajax().update("formBudget:messagesBudget", "formBudget");
							}

							FacesContext context = FacesContext.getCurrentInstance();
							context.getExternalContext().redirect("projetForm.xhtml");
							context.getExternalContext().getFlash().setKeepMessages(true);

						} else {

							this.budgetService.persist(this.budget);
							updateBudgetList();
							PrimeFaces.current().ajax().update("formBudget:messagesBudget", "formBudget");
							FacesContext.getCurrentInstance().getExternalContext().getFlash().put("instrumentFilter",
									null);
							//// this.updateSingletonProject();
							FacesContext.getCurrentInstance().addMessage(null,
									new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Budget ajouter"));
							FacesContext context = FacesContext.getCurrentInstance();
							context.getExternalContext().redirect("projetForm.xhtml");
							context.getExternalContext().getFlash().setKeepMessages(true);
						}

					} else if (total < this.selectedProjet.getProjetBudget()) {
						Budget budCompl = new Budget();
						budCompl.setDevise(this.devise);
						for (Projet projet : this.projets) {
							if (selectedProjet.getProjetId().toString().equals(projet.getProjetId().toString())) {
								budCompl.setProjet(projet);
							}
						}
						randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
						budCompl.setBudgetId(randomNum);
						budCompl.setLastModifiedBy(utilisateur.getUtilisateurId());
						budCompl.setLastModifiedDate(new Date());
						budCompl.setInstrumentfinancement(this.instruAutre);
						budCompl.setBudgetContribution(new BigDecimal(
								this.selectedProjet.getProjetBudget() - (this.budget.getBudgetContribution().intValue()
										+ (total - this.budget.getBudgetContribution().intValue()))));
						this.budgetService.persist2(this.budget, budCompl);
						updateBudgetList();
						PrimeFaces.current().ajax().update("formBudget:messagesBudget", "formBudget");
					} else {
						FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Erreur",
								"Budget total du projet dépassé, merci d'augmenter le montant total du projet"));
					}

				} else {
					if (this.budget.getBudgetContribution().intValue() < this.selectedProjet.getProjetBudget()) {
						Budget budCompl = new Budget();
						budCompl.setDevise(this.devise);
						for (Projet projet : this.projets) {
							if (selectedProjet.getProjetId().toString().equals(projet.getProjetId().toString())) {
								budCompl.setProjet(projet);
							}
						}
						randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
						budCompl.setBudgetId(randomNum);
						budCompl.setLastModifiedBy(utilisateur.getUtilisateurId());
						budCompl.setLastModifiedDate(new Date());
						budCompl.setInstrumentfinancement(this.instruAutre);
						budCompl.setBudgetContribution(new BigDecimal(this.selectedProjet.getProjetBudget()
								- (this.budget.getBudgetContribution().intValue() + total)));
						this.budgetService.persist2(this.budget, budCompl);
						FacesContext.getCurrentInstance().getExternalContext().getFlash().put("instrumentFilter", null);
						//// this.updateSingletonProject();
						FacesContext.getCurrentInstance().addMessage(null,
								new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Budget ajouter"));
						updateBudgetList();
						PrimeFaces.current().ajax().update("formBudget:messagesBudget", "formBudget");
					} else if (this.budget.getBudgetContribution().intValue() == this.selectedProjet
							.getProjetBudget()) {
						this.budgetService.persist(this.budget);
						FacesContext.getCurrentInstance().getExternalContext().getFlash().put("instrumentFilter", null);
						//// this.updateSingletonProject();
						FacesContext.getCurrentInstance().addMessage(null,
								new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Budget ajouter"));
						updateBudgetList();
						PrimeFaces.current().ajax().update("formBudget:messagesBudget", "formBudget");
					} else {
						FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Erreur",
								"Budget total du projet dépassé, merci d'augmenter le montant total du projet"));
					}

					FacesContext context = FacesContext.getCurrentInstance();
					context.getExternalContext().redirect("projetForm.xhtml");
					context.getExternalContext().getFlash().setKeepMessages(true);
				}

			} catch (Exception e) {
				System.out.println(e.getMessage());
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur", "Une erreure est survenue "));
				FacesContext.getCurrentInstance().getExternalContext().redirect("projetForm.xhtml");
				FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
			}

		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur", "Aucun projet selectionner"));
		}
		PrimeFaces.current().ajax().update("formBudget:messagesBudget", "formBudget");

	}

	public void onRowEditCalendrier(RowEditEvent<Calendrier> event) {
		this.selectedCalendrier = event.getObject();
		ZoneId defaultZoneId = ZoneId.systemDefault();

		Date dateC = Date.from(this.dateCalendrier.atStartOfDay(defaultZoneId).toInstant());
		this.selectedCalendrier.setCalendrierDate(dateC);

		Utilisateur utilisateur = new Utilisateur();
		utilisateur = (Utilisateur) SessionUtils.getRequest().getSession().getAttribute("user");

		this.selectedCalendrier.setLastModifiedBy(utilisateur.getUtilisateurId());
		this.selectedCalendrier.setLastModifiedDate(new Date());

		try {
			this.calendrierService.update(this.selectedCalendrier);
			//// this.updateSingletonProject();
			this.selectedProjet.getCalendriers().add(selectedCalendrier);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Calendrier mise à jour"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Une erreure est survenue "));
		}

	}

	public void onRowCancelCalendrier(RowEditEvent<Calendrier> event) {
		FacesMessage msg = new FacesMessage("Modification annuler",
				String.valueOf(event.getObject().getCalendrierCommmentaires()));
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowEditContractant(RowEditEvent<Contractant> event) {
		this.selectedContractant = event.getObject();
		for (Contractanttype con : this.typecontractants) {
			if (this.selectedContractant.getContractanttype().getContractantTypeNom()
					.equals(con.getContractantTypeNom())) {
				this.selectedContractant.setContractanttype(con);
				break;
			}
		}

		Utilisateur utilisateur = new Utilisateur();
		utilisateur = (Utilisateur) SessionUtils.getRequest().getSession().getAttribute("user");
		this.selectedContractant.setLastModifiedBy(utilisateur.getUtilisateurId());
		this.selectedContractant.setLastModifiedDate(new Date());

		try {
			this.contractantService.update(this.selectedContractant);
			this.selectedProjet.getContractants().add(selectedContractant);
			// this.updateSingletonProject();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Partenaire mise à jour"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Une erreure est survenue "));
		}

	}

	public void onRowCancelContractant(RowEditEvent<Contractant> event) {
		FacesMessage msg = new FacesMessage("Modification annuler",
				String.valueOf(event.getObject().getContractantNom()));
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowEditBudget(RowEditEvent<Budget> event) {
		this.selectedBudget = event.getObject();

		Utilisateur utilisateur = new Utilisateur();
		utilisateur = (Utilisateur) SessionUtils.getRequest().getSession().getAttribute("user");
		this.selectedBudget.setLastModifiedBy(utilisateur.getUtilisateurId());
		this.selectedBudget.setLastModifiedDate(new Date());

		Projet projForVerif = new Projet();
		projForVerif = this.projetService.findById(this.selectedProjet.getProjetId());
		int total = 0;
		if (!projForVerif.getBudgets().isEmpty()) {
			Budget autreOldBudget = new Budget();
			Budget oldBudget = new Budget();
			for (Budget budget : projForVerif.getBudgets()) {
				if (!budget.getInstrumentfinancement().getInstrumentFinancementNom().equals("Autres")) {
					total = total + budget.getBudgetContribution().intValue();
				}
				if (budget.getInstrumentfinancement().getInstrumentFinancementNom()
						.equals(this.selectedBudget.getInstrumentfinancement().getInstrumentFinancementNom())) {
					oldBudget = budget;
				}
				if (budget.getInstrumentfinancement().getInstrumentfinancementId()
						.equals(instruAutre.getInstrumentfinancementId())) {
					autreOldBudget = budget;

				}
			}

			total = total + (this.selectedBudget.getBudgetContribution().intValue()- oldBudget.getBudgetContribution().intValue());
			if ((total <= this.selectedProjet.getProjetBudget()) && autreOldBudget.getBudgetId() != null) {
				int budgetTotal =  (int) this.selectedProjet.getProjetBudget();
				if (budgetTotal >= this.selectedBudget.getBudgetContribution().intValue()) {
					if (autreOldBudget.getBudgetId() != null) {
						autreOldBudget
								.setBudgetContribution(new BigDecimal(autreOldBudget.getBudgetContribution().intValue()
										- (this.selectedBudget.getBudgetContribution().intValue()
												- oldBudget.getBudgetContribution().intValue())));
						this.budgetService.update(this.selectedBudget);
						if (autreOldBudget.getBudgetContribution().intValue() != 0) {
							this.budgetService.update(autreOldBudget);
							FacesContext.getCurrentInstance().getExternalContext().getFlash().put("instrumentFilter",
									null);

							FacesContext.getCurrentInstance().addMessage(null,
									new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Budget mises à jour"));
							updateBudgetList();

						} else if(autreOldBudget.getBudgetContribution().intValue() < 0){
							FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
									"Erreur", "Budget total du projet dépassé, merci d'augmenter le montant total du projet"));
						}else {
							this.budgetService.delete(autreOldBudget.getBudgetId());
							updateBudgetList();
							FacesContext.getCurrentInstance().getExternalContext().getFlash().put("instrumentFilter",
									null);
							updateBudgetList();
						}

					} else {
						Budget budCompl = new Budget();
						budCompl.setDevise(this.devise);
						for (Projet projet : this.projets) {
							if (selectedProjet.getProjetId().toString()
									.equals(projet.getProjetId().toString())) {
								budCompl.setProjet(projet);
							}
						}
						int min = 1;
						int max = 999_999;
						int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);

						randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
						budCompl.setBudgetId(randomNum);
						budCompl.setLastModifiedBy(utilisateur.getUtilisateurId());
						budCompl.setLastModifiedDate(new Date());
						budCompl.setInstrumentfinancement(this.instruAutre);
						budCompl.setBudgetContribution(new BigDecimal(this.selectedProjet.getProjetBudget()
								- (this.selectedBudget.getBudgetContribution().intValue()
										+ (total - this.selectedBudget.getBudgetContribution().intValue()))));
						this.budgetService.update(this.selectedBudget);
						this.budgetService.persist(budCompl);
						FacesContext.getCurrentInstance().getExternalContext().getFlash()
								.put("instrumentFilter", null);
						//// this.updateSingletonProject();
						FacesContext.getCurrentInstance().addMessage(null,
								new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Budget mises à jour"));
						updateBudgetList();
						
					}

				}
			} else if((total <= this.selectedProjet.getProjetBudget()) ) {
				Budget budCompl = new Budget();
				budCompl.setDevise(this.devise);
				for (Projet projet : this.projets) {
					if (selectedProjet.getProjetId().toString()
							.equals(projet.getProjetId().toString())) {
						budCompl.setProjet(projet);
					}
				}
				int min = 1;
				int max = 999_999;
				int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);

				randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
				budCompl.setBudgetId(randomNum);
				budCompl.setLastModifiedBy(utilisateur.getUtilisateurId());
				budCompl.setLastModifiedDate(new Date());
				budCompl.setInstrumentfinancement(this.instruAutre);
				budCompl.setBudgetContribution(new BigDecimal(this.selectedProjet.getProjetBudget()
						- (this.selectedBudget.getBudgetContribution().intValue()
								+ (total - this.selectedBudget.getBudgetContribution().intValue()))));
				this.budgetService.update(this.selectedBudget);
				this.budgetService.persist(budCompl);
				FacesContext.getCurrentInstance().getExternalContext().getFlash()
						.put("instrumentFilter", null);
				//// this.updateSingletonProject();
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Budget mises à jour"));
				updateBudgetList();
				
			}else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Erreur", "Budget total du projet dépassé, merci d'augmenter le montant total du projet"));
			}

		}

		/*
		 * if (this.selectedProjet.getProjetBudget() >
		 * this.selectedBudget.getBudgetContribution().intValue()) { // Mise à jour du
		 * budget Autres
		 * 
		 * List<Budget> budgets =
		 * this.budgetService.findByProject(this.selectedProjet.getProjetId());
		 * 
		 * Budget budCompl = null;
		 * 
		 * for (Budget budC : budgets) { if
		 * (budC.getInstrumentfinancement().getInstrumentfinancementId() ==
		 * this.instruAutre .getInstrumentfinancementId()) { budCompl = budC; break; } }
		 * try { if (budCompl != null) { budCompl.setBudgetContribution(new
		 * BigDecimal(this.selectedProjet.getProjetBudget() -
		 * this.selectedBudget.getBudgetContribution().intValue()));
		 * 
		 * budCompl.setLastModifiedBy(utilisateur.getUtilisateurId());
		 * budCompl.setLastModifiedDate(new Date());
		 * this.budgetService.update2(this.selectedBudget, budCompl); } else {
		 * this.budgetService.update(this.selectedBudget); } //
		 * this.updateSingletonProject();
		 * this.selectedProjet.getBudgets().add(selectedBudget);
		 * FacesContext.getCurrentInstance().addMessage(null, new
		 * FacesMessage("Budget mise à jour")); } catch (Exception e) {
		 * System.out.println(e.getMessage());
		 * FacesContext.getCurrentInstance().addMessage(null, new
		 * FacesMessage("Une erreure est survenue ")); }
		 * 
		 * }
		 */
		PrimeFaces.current().ajax().update("formBudget:messagesBudget", "formBudget:dd");
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("projetForm.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void onRowCancelBudget(RowEditEvent<Budget> event) {
		FacesMessage msg = new FacesMessage("Modification annuler",
				String.valueOf(event.getObject().getBudgetContribution()));
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void saveContractant() {
		if (this.selectedProjet.getProjetId() != 0) {

			for (Contractanttype con : this.typecontractants) {
				if (this.selectedTypeContractant.equals(con.getContractantTypeNom())) {
					this.contractant.setContractanttype(con);
					break;
				}
			}
			for (Projet projet : this.projets) {
				if (selectedProjet.getProjetId().toString().equals(projet.getProjetId().toString())) {
					this.contractant.setProjet(projet);
				}
			}

			Utilisateur utilisateur = new Utilisateur();
			utilisateur = (Utilisateur) SessionUtils.getRequest().getSession().getAttribute("user");
			this.contractant.setLastModifiedBy(utilisateur.getUtilisateurId());
			this.contractant.setLastModifiedDate(new Date());
			int min = 1;
			int max = 999_999;
			int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
			this.contractant.setContractantId(randomNum);
			try {
				this.contractantService.persist(this.contractant);
				if (this.selectedProjet.getContractants() == null) {
					this.selectedProjet.setContractants(new ArrayList<Contractant>());
					this.selectedProjet.getContractants().add(contractant);
				} else {
					this.selectedProjet.getContractants().add(contractant);
				}
				// this.selectedProjet.getContractants().add(contractant);
				// this.updateSingletonProject();
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Contractant ajouter"));
				FacesContext context = FacesContext.getCurrentInstance();
				this.projPart = true;
				context.getExternalContext().redirect("projetForm.xhtml");
				context.getExternalContext().getFlash().setKeepMessages(true);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Une erreure est survenue "));
			}
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Aucun projet selectionner"));
		}

		PrimeFaces.current().ajax().update("form:messagesPartenaire", "formPartenaire");

	}

	public void saveCalendar() {
		if (this.selectedProjet.getProjetId() != 0) {
			ZoneId defaultZoneId = ZoneId.systemDefault();

			Date dateC = Date.from(this.dateCalendrier.atStartOfDay(defaultZoneId).toInstant());
			this.calendrier.setCalendrierDate(dateC);
			for (Projet projet : this.projets) {
				if (selectedProjet.getProjetId().toString().equals(projet.getProjetId().toString())) {
					this.calendrier.setProjet(projet);
				}
			}

			Utilisateur utilisateur = new Utilisateur();
			utilisateur = (Utilisateur) SessionUtils.getRequest().getSession().getAttribute("user");
			this.calendrier.setLastModifiedBy(utilisateur.getUtilisateurId());
			this.calendrier.setLastModifiedDate(new Date());
			int min = 1;
			int max = 999_999;
			int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);

			this.calendrier.setCalendrierId(randomNum);

			try {
				this.calendrierService.persist(this.calendrier);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Calendrier ajouter"));
				if (this.selectedProjet.getCalendriers() == null) {
					this.selectedProjet.setCalendriers(new ArrayList<Calendrier>());
					this.selectedProjet.getCalendriers().add(calendrier);
				} else {
					this.selectedProjet.getCalendriers().add(calendrier);
					FacesContext context = FacesContext.getCurrentInstance();
					context.getExternalContext().redirect("projetForm.xhtml");
					context.getExternalContext().getFlash().setKeepMessages(true);
				}
				// this.selectedProjet.getCalendriers().add(calendrier);
				// this.updateSingletonProject();
				this.projCalend = true;
			} catch (Exception e) {
				System.out.println(e.getMessage());
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Une erreure est survenue "));
			}
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Aucun projet selectionner"));

		}

		PrimeFaces.current().ajax().update("form:messagesCalendrier", "formCalendrier");
	}

	public void handleSourceChange() {
		this.instrumentsFilter = new ArrayList<>();
		for (Instrumentfinancement instruC : this.instruments) {
			if (instruC.getSourcefinancement().getSourceFinancementNom().equals(this.selectedSource)) {
				this.instrumentsFilter.add(instruC);
			}
		}

		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("instrumentFilter",
				this.instrumentsFilter);
		PrimeFaces.current().ajax().update("formBudget:instrument");

	}

	public void saveProjet() {
		if (this.selectedProjet.getProjetId() == null) {
			Projet proj = new Projet();

			updateProjToSaveInfo(proj);

			Utilisateur utilisateur = new Utilisateur();
			utilisateur = (Utilisateur) SessionUtils.getRequest().getSession().getAttribute("user");
			proj.setLastModifiedBy(utilisateur.getUtilisateurId());
			proj.setUtilisateur(utilisateur);
			int min = 1;
			int max = 999_999;
			int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
			this.selectedProjet.setProjetId(randomNum);
			proj.setProjetId(randomNum);
			try {
				this.projetService.persist(proj);
//				String msgsSend = "BONJOUR, Brehima un nouveau projet vient d'être creer, le nom est: "
//						+ this.selectedProjet.getProjetTitre() + " et le numero de contrat est :"
//						+ this.selectedProjet.getProjetNumContrat() + " Merci";
//				SendMail.send(MAIL_SATGRIE, "CREATION DE PROJET ", msgsSend);
				// Projet projToSave = this.projetService.findById(randomNum);
				// this.selectedProjet.setProjetId(projToSave.getProjetId());
				FacesContext.getCurrentInstance().getExternalContext().getFlash().put("selectedProjet",
						this.selectedProjet);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Projet ajouter"));
				FacesContext context = FacesContext.getCurrentInstance();
				this.projInfo = true;

				context.getExternalContext().redirect("projetForm.xhtml");
				context.getExternalContext().getFlash().setKeepMessages(true);

			} catch (Exception e) {
				System.out.println(e.getMessage());
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Une erreure est survenue "));
			}

		} else {
			Projet projetToGet = new Projet();
			projetToGet = projetService.findById(this.selectedProjet.getProjetId());

			updateProjToSaveInfo(projetToGet);

			projetToGet.setLastModifiedDate(new Date());

			Utilisateur utilisateur = new Utilisateur();
			utilisateur = (Utilisateur) SessionUtils.getRequest().getSession().getAttribute("user");
			projetToGet.setLastModifiedBy(utilisateur.getUtilisateurId());
			try {
				this.projetService.update(projetToGet);
				// this.updateSingletonProject();
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Projet mise à jour"));
			} catch (Exception e) {
				System.out.println(e.getMessage());
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Une erreure est survenue "));
			}

		}
		PrimeFaces.current().ajax().update("form:messages", "form");
	}

	/**
	 * @param proj
	 */
	public void updateProjToSaveInfo(Projet proj) {
		proj.setLastModifiedDate(new Date());
		proj.setProjetBudget(new BigDecimal(this.selectedProjet.getProjetBudget()));
		proj.setProjetTitre(this.selectedProjet.getProjetTitre());
		proj.setProjetNumContrat(this.selectedProjet.getProjetNumContrat());
		proj.setProjetDescription(this.selectedProjet.getProjetDescription());
		proj.setProjetObjectifGeneraux(this.selectedProjet.getProjetObjectifGeneraux());
		proj.setProjetObjectifSpecifiques(this.selectedProjet.getProjetObjectifSpecifiques());
		proj.setProjetResultatsPrevus(this.selectedProjet.getProjetResultatsPrevus());
	}

	public void deleteBudget() {
		if (this.selectedBudget != null) {
			try {

				int min = 1;
				int max = 999_999;
				int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
				Utilisateur utilisateur = new Utilisateur();
				utilisateur = (Utilisateur) SessionUtils.getRequest().getSession().getAttribute("user");
				Projet projForVerif = new Projet();
				projForVerif = this.projetService.findById(this.selectedProjet.getProjetId());
				int total = 0;
				Budget autreOldBudget = new Budget();

				if (!projForVerif.getBudgets().isEmpty()) {

					for (Budget budget : projForVerif.getBudgets()) {
						if (!budget.getInstrumentfinancement().getInstrumentFinancementNom()
								.equals(this.selectedBudget.getInstrumentfinancement().getInstrumentFinancementNom())) {
							total = total + this.selectedBudget.getBudgetContribution().intValue();
						}
						if (budget.getInstrumentfinancement().getInstrumentfinancementId()
								.equals(instruAutre.getInstrumentfinancementId())) {
							autreOldBudget = budget;

						}
					}
					if (autreOldBudget.getBudgetId() != null) {
						autreOldBudget
								.setBudgetContribution(new BigDecimal(autreOldBudget.getBudgetContribution().intValue()
										+ this.selectedBudget.getBudgetContribution().intValue()));
						this.budgetService.update(autreOldBudget);
					} else {
						autreOldBudget.setDevise(this.devise);
						autreOldBudget.setDevise(this.devise);
						for (Projet projet : this.projets) {
							if (selectedProjet.getProjetId().toString().equals(projet.getProjetId().toString())) {
								autreOldBudget.setProjet(projet);
							}
						}
						randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
						autreOldBudget.setBudgetId(randomNum);
						autreOldBudget.setLastModifiedBy(utilisateur.getUtilisateurId());
						autreOldBudget.setLastModifiedDate(new Date());
						autreOldBudget.setInstrumentfinancement(this.instruAutre);
						this.budgetService.delete(this.selectedBudget.getBudgetId());
						this.selectedProjet.getBudgets().remove(selectedBudget);
						autreOldBudget.setBudgetContribution(this.selectedBudget.getBudgetContribution());
						this.budgetService.persist(autreOldBudget);
						this.selectedProjet.setBudgets(new ArrayList<Budget>());
					}
				}

				updateBudgetList();
				// this.updateSingletonProject();
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Budget supprimer"));
				PrimeFaces.current().ajax().update("formBudget:messagesBudget", "formBudget");
			} catch (Exception e) {
				System.out.println(e.getMessage());
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Une erreure est survenue"));
			}
		} else
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Une erreure est survenue"));
		PrimeFaces.current().ajax().update("formBudget:messagesBudget", "formBudget");
	}

	/**
	 * 
	 */
	public void updateBudgetList() {
		List<Budget> budgetToUpdate = new ArrayList<Budget>();
		this.selectedProjet.setBudgets(new ArrayList<Budget>());
		budgetToUpdate = this.budgetService.findByProject(this.getSelectedProjet().getProjetId()).stream().distinct()
				.collect(Collectors.toList());
		this.selectedProjet.getBudgets().addAll(budgetToUpdate);
	}

	public void deleteCalendrier() {
		if (this.selectedCalendrier != null) {
			try {
				this.calendrierService.delete(this.selectedCalendrier.getCalendrierId());
				this.selectedProjet.getCalendriers().remove(this.selectedCalendrier);
				// this.updateSingletonProject();
				FacesContext context = FacesContext.getCurrentInstance();
				context.getExternalContext().redirect("projetForm.xhtml");
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Calendrier supprimer"));
			} catch (Exception e) {
				System.out.println(e.getMessage());
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Une erreure est survenue"));
			}
		} else
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Une erreure est survenue"));
		PrimeFaces.current().ajax().update("formCalendrier:messagesCalendrier");
	}

	public void deleteContractant() {
		if (this.selectedContractant != null) {
			try {
				this.contractantService.delete(this.selectedContractant.getContractantId());
				this.selectedProjet.getContractants().remove(this.selectedContractant);
				// this.updateSingletonProject();
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Contractant supprimer"));
			} catch (Exception e) {
				System.out.println(e.getMessage());
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Une erreure est survenue"));
			}
		} else
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Une erreure est survenue"));
		PrimeFaces.current().ajax().update("formPartenaire:messagesPartenaire", "formPartenaire");
	}

	private void updateSingletonProject() {
		GetProjectData projToSave = new GetProjectData();
		for (GetProjectData proj : this.getProjectDatas) {
			if (this.selectedProjet.getProjetId().toString().equals(proj.getProjetId().toString())) {
				projToSave = proj;
			}

		}

		// projToSave = this.projetService.findById(projToSave.getProjetId());
		if (projToSave != null)
			FacesContext.getCurrentInstance().getExternalContext().getFlash().put("selectedProjet", projToSave);
	}

	public void onMarkerSelect(OverlaySelectEvent event) {
		marker = (Marker) event.getOverlay();
		this.gmapProjectData = (GetProjectData) marker.getData();
	}

	public void onClose(CloseEvent event) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Panel Closed",
				"Closed panel id:'" + event.getComponent().getId() + "'");
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void onToggle(ToggleEvent event) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, event.getComponent().getId() + " toggled",
				"Status:" + event.getVisibility().name());
		FacesContext.getCurrentInstance().addMessage(null, message);
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

	public void setSelectedProjet(GetProjectData selectedProjet) {
		this.selectedProjet = selectedProjet;

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

	public String getChapitreSelected() {
		return chapitreSelected;
	}

	public void setChapitreSelected(String chapitreSelected) {
		this.chapitreSelected = chapitreSelected;
	}

	public ChapitreViewService getChapitreService() {
		return chapitreService;
	}

	public void setChapitreService(ChapitreViewService chapitreService) {
		this.chapitreService = chapitreService;
	}

	public List<Chapitre> getChapitres() {
		return chapitres;
	}

	public void setChapitres(List<Chapitre> chapitres) {
		this.chapitres = chapitres;
	}

	public List<String> getChapitresListString() {
		return chapitresListString;
	}

	public void setChapitresListString(List<String> chapitresListString) {
		this.chapitresListString = chapitresListString;
	}

	public LocalDate getDateCalendrier() {
		return dateCalendrier;
	}

	public void setDateCalendrier(LocalDate dateCalendrier) {
		this.dateCalendrier = dateCalendrier;
	}

	public Calendrier getCalendrier() {
		return calendrier;
	}

	public void setCalendrier(Calendrier calendrier) {
		this.calendrier = calendrier;
	}

	public CalendrierViewService getCalendrierService() {
		return calendrierService;
	}

	public void setCalendrierService(CalendrierViewService calendrierService) {
		this.calendrierService = calendrierService;
	}

	public Contractant getContractant() {
		return contractant;
	}

	public void setContractant(Contractant contractant) {
		this.contractant = contractant;
	}

	public ContractantViewService getContractantService() {
		return contractantService;
	}

	public void setContractantService(ContractantViewService contractantService) {
		this.contractantService = contractantService;
	}

	public ContractanttypeViewService getTypecontractantService() {
		return typecontractantService;
	}

	public void setTypecontractantService(ContractanttypeViewService typecontractantService) {
		this.typecontractantService = typecontractantService;
	}

	public List<Contractanttype> getTypecontractants() {
		return typecontractants;
	}

	public void setTypecontractants(List<Contractanttype> typecontractants) {
		this.typecontractants = typecontractants;
	}

	public String getSelectedTypeContractant() {
		return selectedTypeContractant;
	}

	public void setSelectedTypeContractant(String selectedTypeContractant) {
		this.selectedTypeContractant = selectedTypeContractant;
	}

	public Budget getBudget() {
		return budget;
	}

	public void setBudget(Budget budget) {
		this.budget = budget;
	}

	public BudgetViewService getBudgetService() {
		return budgetService;
	}

	public void setBudgetService(BudgetViewService budgetService) {
		this.budgetService = budgetService;
	}

	public DeviseViewService getDeviseService() {
		return deviseService;
	}

	public void setDeviseService(DeviseViewService deviseService) {
		this.deviseService = deviseService;
	}

	public List<Devise> getDevises() {
		return devises;
	}

	public void setDevises(List<Devise> devises) {
		this.devises = devises;
	}

	public Devise getDevise() {
		return devise;
	}

	public void setDevise(Devise devise) {
		this.devise = devise;
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

	public String getSelectedInstrument() {
		return selectedInstrument;
	}

	public void setSelectedInstrument(String selectedInstrument) {
		this.selectedInstrument = selectedInstrument;
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

	public String getSelectedSource() {
		return selectedSource;
	}

	public void setSelectedSource(String selectedSource) {
		this.selectedSource = selectedSource;
	}

	public Calendrier getSelectedCalendrier() {
		return selectedCalendrier;
	}

	public void setSelectedCalendrier(Calendrier selectedCalendrier) {
		this.selectedCalendrier = selectedCalendrier;
	}

	public Contractant getSelectedContractant() {
		return selectedContractant;
	}

	public void setSelectedContractant(Contractant selectedContractant) {
		this.selectedContractant = selectedContractant;
	}

	public Budget getSelectedBudget() {
		return selectedBudget;
	}

	public void setSelectedBudget(Budget selectedBudget) {
		this.selectedBudget = selectedBudget;
	}

	public Instrumentfinancement getInstruAutre() {
		return instruAutre;
	}

	public void setInstruAutre(Instrumentfinancement instruAutre) {
		this.instruAutre = instruAutre;
	}

	public String getSelectedRegion() {
		return selectedRegion;
	}

	public void setSelectedRegion(String selectedRegion) {
		this.selectedRegion = selectedRegion;
	}

	public String getSelectedCercle() {
		return selectedCercle;
	}

	public void setSelectedCercle(String selectedCercle) {
		this.selectedCercle = selectedCercle;
	}

	public String getSelectedCommune() {
		return selectedCommune;
	}

	public void setSelectedCommune(String selectedCommune) {
		this.selectedCommune = selectedCommune;
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

	public List<Commune> getCommunes() {
		return communes;
	}

	public void setCommunes(List<Commune> communes) {
		this.communes = communes;
	}

	public List<Cercle> getCerclesFilter() {
		return cerclesFilter;
	}

	public void setCerclesFilter(List<Cercle> cerclesFilter) {
		this.cerclesFilter = cerclesFilter;
	}

	/**
	 * @return the advancedModel
	 */
	public MapModel getAdvancedModel() {
		return advancedModel;
	}

	/**
	 * @param advancedModel the advancedModel to set
	 */
	public void setAdvancedModel(MapModel advancedModel) {
		this.advancedModel = advancedModel;
	}

	/**
	 * @return the marker
	 */
	public Marker getMarker() {
		return marker;
	}

	/**
	 * @param marker the marker to set
	 */
	public void setMarker(Marker marker) {
		this.marker = marker;
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

	public CommuneViewService getCommuneService() {
		return communeService;
	}

	public void setCommuneService(CommuneViewService communeService) {
		this.communeService = communeService;
	}

	public List<Commune> getCommunesFilter() {
		return communesFilter;
	}

	public void setCommunesFilter(List<Commune> communesFilter) {
		this.communesFilter = communesFilter;
	}

	public LocalisationViewService getLocalisationService() {
		return localisationService;
	}

	public void setLocalisationService(LocalisationViewService localisationService) {
		this.localisationService = localisationService;
	}

	public List<Secteur> getSecteurs() {
		return secteurs;
	}

	public void setSecteurs(List<Secteur> secteurs) {
		this.secteurs = secteurs;
	}

	public List<Secteur> getSelectedSecteurs() {
		return selectedSecteurs;
	}

	public void setSelectedSecteurs(List<Secteur> selectedSecteurs) {
		this.selectedSecteurs = selectedSecteurs;
	}

	public SecteurViewService getSecteurService() {
		return secteurService;
	}

	public void setSecteurService(SecteurViewService secteurService) {
		this.secteurService = secteurService;
	}

	public List<String> getSelectedSecteursString() {
		return selectedSecteursString;
	}

	public void setSelectedSecteursString(List<String> selectedSecteursString) {
		this.selectedSecteursString = selectedSecteursString;
	}

	public ProjetchapitreViewService getProjetchapitreService() {
		return projetchapitreService;
	}

	public void setProjetchapitreService(ProjetchapitreViewService projetchapitreService) {
		this.projetchapitreService = projetchapitreService;
	}

	public MapModel getSimpleModel() {
		return simpleModel;
	}

	public void setSimpleModel(MapModel simpleModel) {
//		this.simpleModel = simpleModel;
	}

	public List<Projetchapitre> getProjetchapitres() {
		return projetchapitres;
	}

	public void setProjetchapitres(List<Projetchapitre> projetchapitres) {
		this.projetchapitres = projetchapitres;
	}

	public UploadedFiles getFiles() {
		return files;
	}

	public void setFiles(UploadedFiles files) {
		this.files = files;
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

	public static String getFolderTempFile() {
		return FOLDER_TEMP_FILE;
	}

	public static String getFolderTempDocument() {
		return FOLDER_TEMP_DOCUMENT;
	}

	public List<String> getCommuneStrings() {
		return communeStrings;
	}

	public void setCommuneStrings(List<String> communeStrings) {
		this.communeStrings = communeStrings;
	}

	public List<Localisation> getLocalisations() {
		return localisations;
	}

	public void setLocalisations(List<Localisation> localisations) {
		this.localisations = localisations;
	}

	public Localisation getSelectedLocalisation() {
		return selectedLocalisation;
	}

	public void setSelectedLocalisation(Localisation selectedLocalisation) {
		this.selectedLocalisation = selectedLocalisation;
	}

	public Etatavancement getEtatavancement() {
		return etatavancement;
	}

	public void setEtatavancement(Etatavancement etatavancement) {
		this.etatavancement = etatavancement;
	}

	public List<Etatavancement> getEtatavancements() {
		return etatavancements;
	}

	public void setEtatavancements(List<Etatavancement> etatavancements) {
		this.etatavancements = etatavancements;
	}

	public Etatavancement getEtatavancementSelected() {
		return etatavancementSelected;
	}

	public void setEtatavancementSelected(Etatavancement etatavancementSelected) {
		this.etatavancementSelected = etatavancementSelected;
	}

	public EtatavancementViewService getEtatService() {
		return etatService;
	}

	public void setEtatService(EtatavancementViewService etatService) {
		this.etatService = etatService;
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
	 * @return the secteursGetByChap
	 */
	public List<Secteur> getSecteursGetByChap() {
		return secteursGetByChap;
	}

	/**
	 * @param secteursGetByChap the secteursGetByChap to set
	 */
	public void setSecteursGetByChap(List<Secteur> secteursGetByChap) {
		this.secteursGetByChap = secteursGetByChap;
	}

	/**
	 * @return the projInfo
	 */
	public Boolean getProjInfo() {
		return projInfo;
	}

	/**
	 * @param projInfo the projInfo to set
	 */
	public void setProjInfo(Boolean projInfo) {
		this.projInfo = projInfo;
	}

	/**
	 * @return the proChap
	 */
	public Boolean getProChap() {
		return proChap;
	}

	/**
	 * @param proChap the proChap to set
	 */
	public void setProChap(Boolean proChap) {
		this.proChap = proChap;
	}

	/**
	 * @return the projCalend
	 */
	public Boolean getProjCalend() {
		return projCalend;
	}

	/**
	 * @param projCalend the projCalend to set
	 */
	public void setProjCalend(Boolean projCalend) {
		this.projCalend = projCalend;
	}

	/**
	 * @return the projPart
	 */
	public Boolean getProjPart() {
		return projPart;
	}

	/**
	 * @param projPart the projPart to set
	 */
	public void setProjPart(Boolean projPart) {
		this.projPart = projPart;
	}

	/**
	 * @return the projBudget
	 */
	public Boolean getProjBudget() {
		return projBudget;
	}

	/**
	 * @param projBudget the projBudget to set
	 */
	public void setProjBudget(Boolean projBudget) {
		this.projBudget = projBudget;
	}

	/**
	 * @return the projEtat
	 */
	public Boolean getProjEtat() {
		return projEtat;
	}

	/**
	 * @param projEtat the projEtat to set
	 */
	public void setProjEtat(Boolean projEtat) {
		this.projEtat = projEtat;
	}

	/**
	 * @return the projLoc
	 */
	public Boolean getProjLoc() {
		return projLoc;
	}

	/**
	 * @param projLoc the projLoc to set
	 */
	public void setProjLoc(Boolean projLoc) {
		this.projLoc = projLoc;
	}

	/**
	 * @return the projDoc
	 */
	public Boolean getProjDoc() {
		return projDoc;
	}

	/**
	 * @param projDoc the projDoc to set
	 */
	public void setProjDoc(Boolean projDoc) {
		this.projDoc = projDoc;
	}

	/**
	 * @return the localisationSecteur
	 */
	public LocalisationSecteur getLocalisationSecteur() {
		return localisationSecteur;
	}

	/**
	 * @param localisationSecteur the localisationSecteur to set
	 */
	public void setLocalisationSecteur(LocalisationSecteur localisationSecteur) {
		this.localisationSecteur = localisationSecteur;
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
	 * @return the localisationSecteurService
	 */
	public LocalisationSecteurViewService getLocalisationSecteurService() {
		return localisationSecteurService;
	}

	/**
	 * @param localisationSecteurService the localisationSecteurService to set
	 */
	public void setLocalisationSecteurService(LocalisationSecteurViewService localisationSecteurService) {
		this.localisationSecteurService = localisationSecteurService;
	}

	/**
	 * @return the mailSatgrie
	 */
	public static String getMailSatgrie() {
		return MAIL_SATGRIE;
	}

	/**
	 * @return the gmapProjectData
	 */
	public GetProjectData getGmapProjectData() {
		return gmapProjectData;
	}

	/**
	 * @param gmapProjectData the gmapProjectData to set
	 */
	public void setGmapProjectData(GetProjectData gmapProjectData) {
		this.gmapProjectData = gmapProjectData;
	}

}
