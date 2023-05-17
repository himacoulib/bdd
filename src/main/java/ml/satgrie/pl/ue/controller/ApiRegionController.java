package ml.satgrie.pl.ue.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

import ml.satgrie.pl.ue.model.Budget;
import ml.satgrie.pl.ue.model.Contractant;
import ml.satgrie.pl.ue.model.Localisation;
import ml.satgrie.pl.ue.model.Projet;
import ml.satgrie.pl.ue.services.BudgetViewService;
import ml.satgrie.pl.ue.services.ContractantViewService;
import ml.satgrie.pl.ue.services.LocalisationViewService;
import ml.satgrie.pl.ue.services.ProjetViewService;
import ml.satgrie.pl.ue.utils.ContractantResp;
import ml.satgrie.pl.ue.utils.InstrumentfinancementResp;
import ml.satgrie.pl.ue.utils.ProjetResp;
import ml.satgrie.pl.ue.utils.SourceFinancementResp;

@Path("/localisation")
public class ApiRegionController {

	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/kayes")
	public String getLocKaye() {

		LocalisationViewService localisationService = new LocalisationViewService();
		List<Localisation> localisations = localisationService.findByRegionId(1);

		return new Gson().toJson(this.getProjets(localisations));
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/koulikoro")
	public String getLocKoulikoro() {
		LocalisationViewService localisationService = new LocalisationViewService();
		List<Localisation> localisations = localisationService.findByRegionId(2);
		return new Gson().toJson(this.getProjets(localisations));
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/sikasso")
	public String getLocSikasso() {
		LocalisationViewService localisationService = new LocalisationViewService();
		List<Localisation> localisations = localisationService.findByRegionId(3);
		return new Gson().toJson(this.getProjets(localisations));
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/segou")
	public String getLocSegou() {
		LocalisationViewService localisationService = new LocalisationViewService();
		List<Localisation> localisations = localisationService.findByRegionId(4);
		return new Gson().toJson(this.getProjets(localisations));
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/tombouctou")
	public String getLocTombouctou() {
		LocalisationViewService localisationService = new LocalisationViewService();
		List<Localisation> localisations = localisationService.findByRegionId(5);
		return new Gson().toJson(this.getProjets(localisations));
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/mopti")
	public String getLocMopti() {
		LocalisationViewService localisationService = new LocalisationViewService();
		List<Localisation> localisations = localisationService.findByRegionId(6);
		return new Gson().toJson(this.getProjets(localisations));
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/bamako")
	public String getLocBamako() {
		LocalisationViewService localisationService = new LocalisationViewService();
		List<Localisation> localisations = localisationService.findByRegionId(7);
		return new Gson().toJson(this.getProjets(localisations));
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/gao")
	public String getLocGao() {
		LocalisationViewService localisationService = new LocalisationViewService();
		List<Localisation> localisations = localisationService.findByRegionId(8);
		return new Gson().toJson(this.getProjets(localisations));
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/kidal")
	public String getLocKidal() {
		LocalisationViewService localisationService = new LocalisationViewService();
		List<Localisation> localisations = localisationService.findByRegionId(9);
		return new Gson().toJson(this.getProjets(localisations));
	}

	private List<ProjetResp> getProjets(List<Localisation> localisations) {
		List<ProjetResp> projets = new ArrayList<ProjetResp>();

		ContractantViewService contractantService = new ContractantViewService();
		List<Contractant> contractants = contractantService.findAll();

		BudgetViewService budgetService = new BudgetViewService();
		List<Budget> budgets = budgetService.findAll();

		ProjetViewService projetService = new ProjetViewService();
		List<Projet> projetsList = projetService.findAll();
		
		for (Localisation loc : localisations) {
			ProjetResp proj = new ProjetResp();
			for (Projet proC : projetsList) {
				if (proC.getProjetId() == loc.getProjetId()) {
					proj.setProjetId(proC.getProjetId());
					proj.setProjetNumContrat(proC.getProjetNumContrat());
					proj.setProjetDescription(proC.getProjetDescription());
					proj.setProjetObjectifGeneraux(proC.getProjetObjectifGeneraux());
					proj.setProjetObjectifSpecifiques(proC.getProjetObjectifSpecifiques());
					proj.setProjetBudget(proC.getProjetBudget());
					break;
				}
			}

			List<ContractantResp> contractantProj = new ArrayList<>();
			for (Contractant contrac : contractants) {
				if (contrac.getProjet().getProjetId() == loc.getProjetId()) {
					ContractantResp contraResp = new ContractantResp(contrac.getContractantId(),
							contrac.getContractantNom(), contrac.getContractantSigle());
					contractantProj.add(contraResp);
				}
			}

			List<InstrumentfinancementResp> instrumentProj = new ArrayList<>();
			List<SourceFinancementResp> sourceProj = new ArrayList<>();

			for (Budget bud : budgets) {
				if (bud.getProjet().getProjetId() == loc.getProjetId()) {
					InstrumentfinancementResp instruResp = new InstrumentfinancementResp(
							bud.getInstrumentfinancement().getInstrumentfinancementId(),
							bud.getInstrumentfinancement().getInstrumentFinancementNom(),
							bud.getInstrumentfinancement().getInstrumentFinancementDesc());
					SourceFinancementResp sourceResp = new SourceFinancementResp(
							bud.getInstrumentfinancement().getSourcefinancement().getSourceFinancementId(),
							bud.getInstrumentfinancement().getSourcefinancement().getSourceFinancementNom(),
							bud.getInstrumentfinancement().getSourcefinancement().getSourceFinancementDesc());
					instrumentProj.add(instruResp);
					sourceProj.add(sourceResp);
				}
			}

			proj.setContractants(contractantProj);
			proj.setInstruments(instrumentProj);
			proj.setSources(sourceProj);
			projets.add(proj);
		}
		return projets;
	}
}
