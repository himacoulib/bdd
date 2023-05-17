package ml.satgrie.pl.ue.utils;
// Generated 31 mars 2021 � 17:28:15 by Hibernate Tools 5.2.12.Final

public class InstrumentfinancementResp implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer instrumentfinancementId;
	private String instrumentFinancementNom;
	private String instrumentFinancementDesc;
	
	
	public InstrumentfinancementResp() {
		super();
	}


	public InstrumentfinancementResp(Integer instrumentfinancementId, String instrumentFinancementNom,
			String instrumentFinancementDesc) {
		super();
		this.instrumentfinancementId = instrumentfinancementId;
		this.instrumentFinancementNom = instrumentFinancementNom;
		this.instrumentFinancementDesc = instrumentFinancementDesc;
	}


	public Integer getInstrumentfinancementId() {
		return instrumentfinancementId;
	}


	public void setInstrumentfinancementId(Integer instrumentfinancementId) {
		this.instrumentfinancementId = instrumentfinancementId;
	}


	public String getInstrumentFinancementNom() {
		return instrumentFinancementNom;
	}


	public void setInstrumentFinancementNom(String instrumentFinancementNom) {
		this.instrumentFinancementNom = instrumentFinancementNom;
	}


	public String getInstrumentFinancementDesc() {
		return instrumentFinancementDesc;
	}


	public void setInstrumentFinancementDesc(String instrumentFinancementDesc) {
		this.instrumentFinancementDesc = instrumentFinancementDesc;
	}


}
