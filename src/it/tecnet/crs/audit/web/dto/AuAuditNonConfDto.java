package it.tecnet.crs.audit.web.dto;

import java.util.Date;

public class AuAuditNonConfDto {
	
	private long idNonConform;
	private long idFase;
	private String descrizioneNonConform;
	private String descrizioneAudit;
	private String descrizioneFase;
	private Double peso;
	private Date dataInizio;
	private Date dataFine;
	private String codiceNc;
	public long getIdNonConform() {
		return idNonConform;
	}
	public void setIdNonConform(long idNonConform) {
		this.idNonConform = idNonConform;
	}
	public long getIdFase() {
		return idFase;
	}
	public void setIdFase(long idFase) {
		this.idFase = idFase;
	}
	public String getDescrizioneNonConform() {
		return descrizioneNonConform;
	}
	public void setDescrizioneNonConform(String descrizioneNonConform) {
		this.descrizioneNonConform = descrizioneNonConform;
	}
	public String getDescrizioneAudit() {
		return descrizioneAudit;
	}
	public void setDescrizioneAudit(String descrizioneAudit) {
		this.descrizioneAudit = descrizioneAudit;
	}
	public String getDescrizioneFase() {
		return descrizioneFase;
	}
	public void setDescrizioneFase(String descrizioneFase) {
		this.descrizioneFase = descrizioneFase;
	}
	public Double getPeso() {
		return peso;
	}
	public void setPeso(Double peso) {
		this.peso = peso;
	}
	public Date getDataInizio() {
		return dataInizio;
	}
	public void setDataInizio(Date dataInizio) {
		this.dataInizio = dataInizio;
	}
	public Date getDataFine() {
		return dataFine;
	}
	public void setDataFine(Date dataFine) {
		this.dataFine = dataFine;
	}
	public void setCodiceNc(String codiceNc) {
		this.codiceNc = codiceNc;
	}
	public String getCodiceNc() {
		return codiceNc;
	}
	
	

}
