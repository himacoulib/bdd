package ml.satgrie.pl.ue.utils;
// Generated 31 mars 2021 � 17:28:15 by Hibernate Tools 5.2.12.Final

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


public class ContractantResp implements java.io.Serializable {

	private Integer contractantId;
	private String contractantNom;
	private String contractantSigle;
	public ContractantResp(Integer contractantId, String contractantNom, String contractantSigle) {
		super();
		this.contractantId = contractantId;
		this.contractantNom = contractantNom;
		this.contractantSigle = contractantSigle;
	}
	public Integer getContractantId() {
		return contractantId;
	}
	public void setContractantId(Integer contractantId) {
		this.contractantId = contractantId;
	}
	public String getContractantNom() {
		return contractantNom;
	}
	public void setContractantNom(String contractantNom) {
		this.contractantNom = contractantNom;
	}
	public String getContractantSigle() {
		return contractantSigle;
	}
	public void setContractantSigle(String contractantSigle) {
		this.contractantSigle = contractantSigle;
	}

}
