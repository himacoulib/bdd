package ml.satgrie.pl.ue.utils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class ProjetResp implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*
	 * Merci de me faire une API pour chaque région capable de retourné les suivants : 
	 * ProjetID, ProjetNumContrat, ProjetDescription, ProjetObjectifGeneraux ; 
	 * ProjetObjectifSpecifiques, RegionNom, CerlceName , CommuneNom, 
	 * BudgetTOtal, SourceFinancement, InstrumentFinancement, ContractantNon, 
	 * DateDebut, DateFin, Ainsi que les localisation (Long, Lat) des regions, des communes, des cercles
	*/
	private Integer projetId;
	private String projetTitre;
	private String projetNumContrat;
	private Integer projetSourceFinancementId;
	private Integer projetInstrumentFinancementId;
	private String projetObjectifGeneraux;
	private String projetObjectifSpecifiques;
	private String projetResultatsPrevus;
	private String projetDescription;
	private BigDecimal projetBudget;
	private List<SourceFinancementResp> sources;
	private List<InstrumentfinancementResp> instruments;
	private List<ContractantResp> contractants;
	
	
	public ProjetResp() {
		super();
	}


	public ProjetResp(Integer projetId, String projetTitre, String projetNumContrat, Integer projetSourceFinancementId,
			Integer projetInstrumentFinancementId, String projetObjectifGeneraux, String projetObjectifSpecifiques,
			String projetResultatsPrevus, String projetDescription, BigDecimal projetBudget,
			List<SourceFinancementResp> sources, List<InstrumentfinancementResp> instruments, List<ContractantResp> contractants) {
		super();
		this.projetId = projetId;
		this.projetTitre = projetTitre;
		this.projetNumContrat = projetNumContrat;
		this.projetSourceFinancementId = projetSourceFinancementId;
		this.projetInstrumentFinancementId = projetInstrumentFinancementId;
		this.projetObjectifGeneraux = projetObjectifGeneraux;
		this.projetObjectifSpecifiques = projetObjectifSpecifiques;
		this.projetResultatsPrevus = projetResultatsPrevus;
		this.projetDescription = projetDescription;
		this.projetBudget = projetBudget;
		this.sources = sources;
		this.instruments = instruments;
		this.contractants = contractants;
	}


	public Integer getProjetId() {
		return projetId;
	}


	public void setProjetId(Integer projetId) {
		this.projetId = projetId;
	}


	public String getProjetTitre() {
		return projetTitre;
	}


	public void setProjetTitre(String projetTitre) {
		this.projetTitre = projetTitre;
	}


	public String getProjetNumContrat() {
		return projetNumContrat;
	}


	public void setProjetNumContrat(String projetNumContrat) {
		this.projetNumContrat = projetNumContrat;
	}


	public Integer getProjetSourceFinancementId() {
		return projetSourceFinancementId;
	}


	public void setProjetSourceFinancementId(Integer projetSourceFinancementId) {
		this.projetSourceFinancementId = projetSourceFinancementId;
	}


	public Integer getProjetInstrumentFinancementId() {
		return projetInstrumentFinancementId;
	}


	public void setProjetInstrumentFinancementId(Integer projetInstrumentFinancementId) {
		this.projetInstrumentFinancementId = projetInstrumentFinancementId;
	}


	public String getProjetObjectifGeneraux() {
		return projetObjectifGeneraux;
	}


	public void setProjetObjectifGeneraux(String projetObjectifGeneraux) {
		this.projetObjectifGeneraux = projetObjectifGeneraux;
	}


	public String getProjetObjectifSpecifiques() {
		return projetObjectifSpecifiques;
	}


	public void setProjetObjectifSpecifiques(String projetObjectifSpecifiques) {
		this.projetObjectifSpecifiques = projetObjectifSpecifiques;
	}


	public String getProjetResultatsPrevus() {
		return projetResultatsPrevus;
	}


	public void setProjetResultatsPrevus(String projetResultatsPrevus) {
		this.projetResultatsPrevus = projetResultatsPrevus;
	}


	public String getProjetDescription() {
		return projetDescription;
	}


	public void setProjetDescription(String projetDescription) {
		this.projetDescription = projetDescription;
	}


	public BigDecimal getProjetBudget() {
		return projetBudget;
	}


	public void setProjetBudget(BigDecimal projetBudget) {
		this.projetBudget = projetBudget;
	}


	

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public List<SourceFinancementResp> getSources() {
		return sources;
	}


	public void setSources(List<SourceFinancementResp> sources) {
		this.sources = sources;
	}


	public List<InstrumentfinancementResp> getInstruments() {
		return instruments;
	}


	public void setInstruments(List<InstrumentfinancementResp> instruments) {
		this.instruments = instruments;
	}


	public List<ContractantResp> getContractants() {
		return contractants;
	}


	public void setContractants(List<ContractantResp> contractants) {
		this.contractants = contractants;
	}
	
	
	
}
