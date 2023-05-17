package ml.satgrie.pl.ue.model;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import ml.satgrie.pl.ue.utils.WrapperCommune;

public class GetProjectData implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6919691507932972569L;
	private Integer projetId;
	private List<Budget> budgets;
	private List<SecteurHierar> chapitres;
	private Utilisateur utilisateur;
	private String projetTitre;
	private String projetNumContrat;
	private List<SourceFinancement> projetSourceFinancements;
	private List<Instrumentfinancement> projetInstrumentFinancements;
	private String projetObjectifGeneraux;
	private String projetObjectifSpecifiques;
	private String projetResultatsPrevus;
	private List<SecteurHierar> projetSecteurs;
	private List<SousSecteur> projetSousSecteurs;
	private String projetDescription;
	private long projetBudget;
	private Integer lastModifiedBy;
	private Date lastModifiedDate;
	private List<Calendrier> calendriers;
	private List<Region> regions;
	private List<Cercle> cercles;
	private List<Commune> communes;
	private List<Pointfocal> pointfocals;
	private Set<Photo> photos = new HashSet<Photo>(0);
	private List<Contractant> contractants;
	private List<Etatavancement> etatavancements;
	private Map<BigDecimal, String> budgetSourceFinancement;
	private List<Contractanttype> contractantTypes;
	private String status;
	private List<String> chapIcon;
	private List<Localisation> localisations;

	public GetProjectData() {

	}

	/**
	 * 
	 * @param projetId
	 * @param chapitres
	 * @param utilisateur
	 * @param projetTitre
	 * @param projetNumContrat
	 * @param projetSourceFinancements
	 * @param projetInstrumentFinancements
	 * @param projetObjectifGeneraux
	 * @param projetObjectifSpecifiques
	 * @param projetResultatsPrevus
	 * @param projetSecteurs
	 * @param projetSousSecteurs
	 * @param projetDescription
	 * @param projetBudget
	 * @param lastModifiedBy
	 * @param lastModifiedDate
	 * @param calendriers
	 * @param regions
	 * @param cercles
	 * @param communes
	 * @param pointfocals
	 * @param photos
	 * @param contractants
	 * @param etatavancements
	 * @param status
	 */
	public GetProjectData(Integer projetId, List<SecteurHierar> chapitres, Utilisateur utilisateur, String projetTitre,
			String projetNumContrat, List<SourceFinancement> projetSourceFinancements,
			List<Instrumentfinancement> projetInstrumentFinancements, String projetObjectifGeneraux,
			String projetObjectifSpecifiques, String projetResultatsPrevus, List<SecteurHierar> projetSecteurs,
			List<SousSecteur> projetSousSecteurs, String projetDescription, long projetBudget, Integer lastModifiedBy,
			Date lastModifiedDate, List<Calendrier> calendriers, List<Region> regions, List<Cercle> cercles,
			List<Commune> communes, List<Pointfocal> pointfocals, Set<Photo> photos, List<Contractant> contractants,
			List<Etatavancement> etatavancements, String status, Map<BigDecimal, String> budgetSourceFinancement,
			List<Contractanttype> contractantTypes, List<Localisation> localisations, List<Budget> budgets,  List<String> chapIcon) {
		super();
		this.projetId = projetId;
		this.chapitres = chapitres;
		this.utilisateur = utilisateur;
		this.projetTitre = projetTitre;
		this.projetNumContrat = projetNumContrat;
		this.projetSourceFinancements = projetSourceFinancements;
		this.projetInstrumentFinancements = projetInstrumentFinancements;
		this.projetObjectifGeneraux = projetObjectifGeneraux;
		this.projetObjectifSpecifiques = projetObjectifSpecifiques;
		this.projetResultatsPrevus = projetResultatsPrevus;
		this.projetSecteurs = projetSecteurs;
		this.projetSousSecteurs = projetSousSecteurs;
		this.projetDescription = projetDescription;
		this.projetBudget = projetBudget;
		this.lastModifiedBy = lastModifiedBy;
		this.lastModifiedDate = lastModifiedDate;
		this.calendriers = calendriers;
		this.regions = regions;
		this.cercles = cercles;
		this.communes = communes;
		this.pointfocals = pointfocals;
		this.photos = photos;
		this.contractants = contractants;
		this.etatavancements = etatavancements;
		this.status = status;
		this.budgetSourceFinancement = budgetSourceFinancement;
		this.contractantTypes = contractantTypes;
		this.localisations = localisations;
		this.budgets = budgets;
		this.chapIcon =  chapIcon;
	}

	public List<GetProjectData> getProjectAllInfo(List<Projet> projets, List<Budget> budgets,
			List<LocalisationSecteur> localisationSecteurs, List<SecteurHierar> secteurHierars,
			List<Localisation> localisations, List<GetProjectData> getProjectDatas, List<Region> regions,
			List<Cercle> cercles, List<Commune> communes, List<Contractant> contractants, List<Calendrier> calendriers,
			List<Contractanttype> contractantTypes, List<Etatavancement> etatavancements, List<Chapitre> chapitresIconFinder) {
		for (Projet projet : projets) {
			GetProjectData projetData = new GetProjectData();
			projetData.setProjetId(projet.getProjetId());
			projetData.setProjetTitre(projet.getProjetTitre());
			projetData.setProjetNumContrat(projet.getProjetNumContrat());
			projetData.setProjetDescription(projet.getProjetDescription());
			projetData.setProjetObjectifGeneraux(projet.getProjetObjectifGeneraux());
			projetData.setProjetObjectifSpecifiques(projet.getProjetObjectifSpecifiques());
			projetData.setProjetResultatsPrevus(projet.getProjetResultatsPrevus());
			projetData.setProjetSourceFinancements(new ArrayList<SourceFinancement>());
			projet.setProjetId(projet.getProjetId());

			List<Instrumentfinancement> instrumentfinancementsTmp = new ArrayList<Instrumentfinancement>();
			for (Budget budgetC : budgets) {
				if (budgetC.getProjet().getProjetId().equals(projet.getProjetId())) {
					instrumentfinancementsTmp.add(budgetC.getInstrumentfinancement());

				}

			}
			/**************************/
			projetData.setProjetSourceFinancements(new ArrayList<SourceFinancement>());
			for (Instrumentfinancement instru : instrumentfinancementsTmp) {
				projetData.getProjetSourceFinancements().add(instru.getSourcefinancement());
			}

			long montatTotal = 0;
			for (Budget budC : budgets) {

				if (budC.getProjet().getProjetId().toString().equals(projet.getProjetId().toString())) {
					montatTotal = montatTotal + budC.getBudgetContribution().longValue();
				}
			}
			projetData.setProjetBudget(montatTotal);
			projetData.setChapitres(new ArrayList<SecteurHierar>());

			for (LocalisationSecteur localisationSecteur : localisationSecteurs) {
				if (localisationSecteur.getIdProjet() == projet.getProjetId().intValue()) {
					for (SecteurHierar secteurHierar : secteurHierars) {
						if (localisationSecteur.getIdSecteur() == secteurHierar.getSecteurHierarId()
								&& secteurHierar.getSecteurParentId() == 0) {
							projetData.getChapitres().add(secteurHierar);
						}
					}
				}

			}
			
			projetData.setChapitres(projetData.getChapitres().stream().distinct().collect(Collectors.toList()));
			projetData.setChapIcon(new ArrayList<String>());
			for (Chapitre chapitre : chapitresIconFinder) {
				for (SecteurHierar projChap : projetData.getChapitres()) {
					if (chapitre.getChapitreName().equals(projChap.getSecteurNom())) {
						projetData.getChapIcon().add(chapitre.getIconeUrl());
					}
				}
			}
			
			/*******************/
			projetData.setProjetSecteurs(new ArrayList<SecteurHierar>());
			for (LocalisationSecteur localisationSecteur : localisationSecteurs) {
				if (localisationSecteur.getIdProjet() == projet.getProjetId().intValue()) {
					for (SecteurHierar secteurHierar : secteurHierars) {
						if (localisationSecteur.getIdSecteur() == secteurHierar.getSecteurHierarId()
								&& secteurHierar.getSecteurParentId() != 0
								&& secteurHierar.getFormerSectorType().equals("Secteur")) {
							projetData.getProjetSecteurs().add(secteurHierar);
						}
					}
				}

			}
			
			projetData.setProjetSecteurs(projetData.getProjetSecteurs().stream().distinct().collect(Collectors.toList()));
			
			projetData.setEtatavancements(new ArrayList<Etatavancement>());
			for (Etatavancement etatavancement : etatavancements) {
				if (etatavancement.getProjet().getProjetId().equals(projet.getProjetId())) {
					projetData.getEtatavancements().add(etatavancement);
				}
			}

			projetData.setRegions(new ArrayList<Region>());
			for (Localisation localisationTmp : localisations) {
				if (projet.getProjetId().toString().equals(localisationTmp.getProjetId().toString())) {

					for (Region regionTmp : regions) {
						if (regionTmp.getId().getRegionId() == localisationTmp.getId().getRegionId()) {

							projetData.getRegions().add(regionTmp);
						}
					}
				}

			}

			projetData.setCercles(new ArrayList<Cercle>());
			for (Localisation localisationTmp : localisations) {
				if (projet.getProjetId().toString().equals(localisationTmp.getProjetId().toString())) {
					for (Cercle cercleTmp : cercles) {
						if (cercleTmp.getId().getCerlceId() == localisationTmp.getId().getCerlceId()) {
							projetData.getCercles().add(cercleTmp);
						}
					}
				}

			}

			projetData.setCommunes(new ArrayList<Commune>());
			for (Localisation localisationTmp : localisations) {
				if (projet.getProjetId().toString().equals(localisationTmp.getProjetId().toString())) {
					for (Commune communeTmp : communes) {
						if (communeTmp.getId().getCommuneId() == localisationTmp.getId().getCommuneId()) {
							projetData.getCommunes().add(communeTmp);
						}
					}
				}

			}
			
			projetData.setCommunes(projetData.getCommunes().stream().map(WrapperCommune::new).distinct().map(WrapperCommune::unwrap).collect(Collectors.toList()));

			projetData.setContractants(new ArrayList<Contractant>());
			for (Contractant contractantTmp : contractants) {
				if (projet.getProjetId().toString().equals(contractantTmp.getProjet().getProjetId().toString())) {
					projetData.getContractants().add(contractantTmp);
				}

			}

			projetData.setCalendriers(new ArrayList<Calendrier>());
			for (Calendrier calendrierTmp : calendriers) {
				if (projet.getProjetId().toString().equals(calendrierTmp.getProjet().getProjetId().toString())) {
					projetData.getCalendriers().add(calendrierTmp);
				}
			}

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.ENGLISH);
			Date date = new Date();
			// Date date1 = sdf.parse("2009-12-31");

			for (Calendrier calendrierTmp : calendriers) {
				if (projet.getProjetId().toString().equals(calendrierTmp.getProjet().getProjetId().toString())
						&& calendrierTmp.getCalendrierTypeId() == 2 && !calendrierTmp.getCalendrierDate().after(date)) {
					projetData.setStatus("Clôturé");
				}
				if (projet.getProjetId().toString().equals(calendrierTmp.getProjet().getProjetId().toString())
						&& calendrierTmp.getCalendrierTypeId() == 3 && !calendrierTmp.getCalendrierDate().after(date)) {
					projetData.setStatus("Clôturé");
				}
				if (projet.getProjetId().toString().equals(calendrierTmp.getProjet().getProjetId().toString())
						&& calendrierTmp.getCalendrierTypeId() == 2 && calendrierTmp.getCalendrierDate().after(date)) {
					projetData.setStatus("En cours");
				}
				if (projet.getProjetId().toString().equals(calendrierTmp.getProjet().getProjetId().toString())
						&& calendrierTmp.getCalendrierTypeId() == 3 && calendrierTmp.getCalendrierDate().after(date)) {
					projetData.setStatus("En cours");
				}

			}
			projetData.setBudgetSourceFinancement(new HashMap<BigDecimal, String>());
			projetData.setBudgets(new ArrayList<Budget>());
			for (Budget budgetC : budgets) {
				if (budgetC.getProjet().getProjetId().toString().equals(projet.getProjetId().toString())) {
					projetData.getBudgets().add(budgetC);
					projetData.getBudgetSourceFinancement().put(budgetC.getBudgetContribution(),
							budgetC.getInstrumentfinancement().getSourcefinancement().getSourceFinancementNom());

				}
			}
			
			projetData.setLocalisations(new ArrayList<Localisation>());
			for (Localisation localisation : localisations) {
				if (localisation.getProjetId().toString().equals(projet.getProjetId().toString())) {
					projetData.getLocalisations().add(localisation);
				}
			}
			projetData.setLocalisations(projetData.getLocalisations().stream().distinct().collect(Collectors.toList()));
			projetData.setContractantTypes(new ArrayList<Contractanttype>());
			for (Contractanttype contractanttype : contractantTypes) {
				for (Contractant contractantTmp : contractants) {
					if (projet.getProjetId().toString().equals(contractantTmp.getProjet().getProjetId().toString())) {
						if (contractanttype.getContractantTypeNom()
								.equals(contractantTmp.getContractanttype().getContractantTypeNom())) {
							projetData.getContractantTypes().add(contractanttype);
						}
					}

				}
			}

			getProjectDatas.add(projetData);
		}

		List<SecteurHierar> secteurHierarsTmp = new ArrayList<SecteurHierar>();
		for (GetProjectData projectData : getProjectDatas) {
			secteurHierarsTmp = projectData.getChapitres().stream().distinct().collect(Collectors.toList());
			projectData.setChapitres(secteurHierarsTmp);
		}
		List<Region> regionsUnique = new ArrayList<Region>();
		for (GetProjectData projectData : getProjectDatas) {
			regionsUnique = projectData.getRegions().stream().distinct().collect(Collectors.toList());
			projectData.setRegions(regionsUnique);
		}
		return getProjectDatas;

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
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the budgetSourceFinancement
	 */
	public Map<BigDecimal, String> getBudgetSourceFinancement() {
		return budgetSourceFinancement;
	}

	/**
	 * @param budgetSourceFinancement the budgetSourceFinancement to set
	 */
	public void setBudgetSourceFinancement(Map<BigDecimal, String> budgetSourceFinancement) {
		this.budgetSourceFinancement = budgetSourceFinancement;
	}

	/**
	 * @return the projetId
	 */
	public Integer getProjetId() {
		return projetId;
	}

	/**
	 * @param projetId the projetId to set
	 */
	public void setProjetId(Integer projetId) {
		this.projetId = projetId;
	}

	/**
	 * @return the chapitres
	 */
	public List<SecteurHierar> getChapitres() {
		return chapitres;
	}

	/**
	 * @param chapitres the chapitres to set
	 */
	public void setChapitres(List<SecteurHierar> chapitres) {
		this.chapitres = chapitres;
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
	 * @return the projetTitre
	 */
	public String getProjetTitre() {
		return projetTitre;
	}

	/**
	 * @param projetTitre the projetTitre to set
	 */
	public void setProjetTitre(String projetTitre) {
		this.projetTitre = projetTitre;
	}

	/**
	 * @return the projetNumContrat
	 */
	public String getProjetNumContrat() {
		return projetNumContrat;
	}

	/**
	 * @param projetNumContrat the projetNumContrat to set
	 */
	public void setProjetNumContrat(String projetNumContrat) {
		this.projetNumContrat = projetNumContrat;
	}

	/**
	 * @return the projetSourceFinancements
	 */
	public List<SourceFinancement> getProjetSourceFinancements() {
		return projetSourceFinancements;
	}

	/**
	 * @param projetSourceFinancements the projetSourceFinancements to set
	 */
	public void setProjetSourceFinancements(List<SourceFinancement> projetSourceFinancements) {
		this.projetSourceFinancements = projetSourceFinancements;
	}

	/**
	 * @return the projetInstrumentFinancements
	 */
	public List<Instrumentfinancement> getProjetInstrumentFinancements() {
		return projetInstrumentFinancements;
	}

	/**
	 * @param projetInstrumentFinancements the projetInstrumentFinancements to set
	 */
	public void setProjetInstrumentFinancements(List<Instrumentfinancement> projetInstrumentFinancements) {
		this.projetInstrumentFinancements = projetInstrumentFinancements;
	}

	/**
	 * @return the projetObjectifGeneraux
	 */
	public String getProjetObjectifGeneraux() {
		return projetObjectifGeneraux;
	}

	/**
	 * @param projetObjectifGeneraux the projetObjectifGeneraux to set
	 */
	public void setProjetObjectifGeneraux(String projetObjectifGeneraux) {
		this.projetObjectifGeneraux = projetObjectifGeneraux;
	}

	/**
	 * @return the projetObjectifSpecifiques
	 */
	public String getProjetObjectifSpecifiques() {
		return projetObjectifSpecifiques;
	}

	/**
	 * @param projetObjectifSpecifiques the projetObjectifSpecifiques to set
	 */
	public void setProjetObjectifSpecifiques(String projetObjectifSpecifiques) {
		this.projetObjectifSpecifiques = projetObjectifSpecifiques;
	}

	/**
	 * @return the projetResultatsPrevus
	 */
	public String getProjetResultatsPrevus() {
		return projetResultatsPrevus;
	}

	/**
	 * @param projetResultatsPrevus the projetResultatsPrevus to set
	 */
	public void setProjetResultatsPrevus(String projetResultatsPrevus) {
		this.projetResultatsPrevus = projetResultatsPrevus;
	}

	/**
	 * @return the projetSecteurs
	 */
	public List<SecteurHierar> getProjetSecteurs() {
		return projetSecteurs;
	}

	/**
	 * @param projetSecteurs the projetSecteurs to set
	 */
	public void setProjetSecteurs(List<SecteurHierar> projetSecteurs) {
		this.projetSecteurs = projetSecteurs;
	}

	/**
	 * @return the projetSousSecteurs
	 */
	public List<SousSecteur> getProjetSousSecteurs() {
		return projetSousSecteurs;
	}

	/**
	 * @param projetSousSecteurs the projetSousSecteurs to set
	 */
	public void setProjetSousSecteurs(List<SousSecteur> projetSousSecteurs) {
		this.projetSousSecteurs = projetSousSecteurs;
	}

	/**
	 * @return the projetDescription
	 */
	public String getProjetDescription() {
		return projetDescription;
	}

	/**
	 * @param projetDescription the projetDescription to set
	 */
	public void setProjetDescription(String projetDescription) {
		this.projetDescription = projetDescription;
	}

	/**
	 * @return the projetBudget
	 */
	public long getProjetBudget() {
		return projetBudget;
	}

	/**
	 * @param montatTotal the projetBudget to set
	 */
	public void setProjetBudget(long montatTotal) {
		this.projetBudget = montatTotal;
	}

	/**
	 * @return the lastModifiedBy
	 */
	public Integer getLastModifiedBy() {
		return lastModifiedBy;
	}

	/**
	 * @param lastModifiedBy the lastModifiedBy to set
	 */
	public void setLastModifiedBy(Integer lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	/**
	 * @return the lastModifiedDate
	 */
	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	/**
	 * @param lastModifiedDate the lastModifiedDate to set
	 */
	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
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
	 * @return the pointfocals
	 */
	public List<Pointfocal> getPointfocals() {
		return pointfocals;
	}

	/**
	 * @param pointfocals the pointfocals to set
	 */
	public void setPointfocals(List<Pointfocal> pointfocals) {
		this.pointfocals = pointfocals;
	}

	/**
	 * @return the photos
	 */
	public Set<Photo> getPhotos() {
		return photos;
	}

	/**
	 * @param photos the photos to set
	 */
	public void setPhotos(Set<Photo> photos) {
		this.photos = photos;
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
	 * @return the contractantTypes
	 */
	public List<Contractanttype> getContractantTypes() {
		return contractantTypes;
	}

	/**
	 * @param contractantTypes the contractantTypes to set
	 */
	public void setContractantTypes(List<Contractanttype> contractantTypes) {
		this.contractantTypes = contractantTypes;
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
	 * @return the chapIcon
	 */
	public List<String> getChapIcon() {
		return chapIcon;
	}

	/**
	 * @param chapIcon the chapIcon to set
	 */
	public void setChapIcon(List<String> chapIcon) {
		this.chapIcon = chapIcon;
	}
	
	
}
