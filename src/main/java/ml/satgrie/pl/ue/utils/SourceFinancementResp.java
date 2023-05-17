package ml.satgrie.pl.ue.utils;
// Generated 30 mars 2021 � 17:13:07 by Hibernate Tools 5.2.12.Final

public class SourceFinancementResp implements java.io.Serializable {

	private Integer sourceFinancementId;
	private String sourceFinancementNom;
	
	private String sourceFinancementDesc;

	public SourceFinancementResp() {
		super();
	}

	public SourceFinancementResp(Integer sourceFinancementId, String sourceFinancementNom,
		 String sourceFinancementDesc) {
		super();
		this.sourceFinancementId = sourceFinancementId;
		this.sourceFinancementNom = sourceFinancementNom;
		this.sourceFinancementDesc = sourceFinancementDesc;
	}

	public Integer getSourceFinancementId() {
		return sourceFinancementId;
	}

	public void setSourceFinancementId(Integer sourceFinancementId) {
		this.sourceFinancementId = sourceFinancementId;
	}

	public String getSourceFinancementNom() {
		return sourceFinancementNom;
	}

	public void setSourceFinancementNom(String sourceFinancementNom) {
		this.sourceFinancementNom = sourceFinancementNom;
	}

	

	public String getSourceFinancementDesc() {
		return sourceFinancementDesc;
	}

	public void setSourceFinancementDesc(String sourceFinancementDesc) {
		this.sourceFinancementDesc = sourceFinancementDesc;
	}

	

}
