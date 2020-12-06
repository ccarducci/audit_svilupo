package it.tecnet.crs.audit.web.dto;

import java.math.BigDecimal;
import java.util.Date;

public class AuAuditVarCompDto {
	
	private long idVarC;
	private String descrizioneVC;
	
	private long idNC;
	private String descrizioneNC;
	
	private String codiceVC;
	private BigDecimal pesoVC;
	private Date dataInizioVC;
	private Date dataFineVC;
	
	
	public long getIdVarC() {
		return idVarC;
	}
	public void setIdVarC(long idVarC) {
		this.idVarC = idVarC;
	}
	public String getDescrizioneVC() {
		return descrizioneVC;
	}
	public void setDescrizioneVC(String descrizioneVC) {
		this.descrizioneVC = descrizioneVC;
	}
	public long getIdNC() {
		return idNC;
	}
	public void setIdNC(Long long1) {
		this.idNC = long1;
	}
	public String getDescrizioneNC() {
		return descrizioneNC;
	}
	public void setDescrizioneNC(String descrizioneNC) {
		this.descrizioneNC = descrizioneNC;
	}
	public String getCodiceVC() {
		return codiceVC;
	}
	public void setCodiceVC(String codiceVC) {
		this.codiceVC = codiceVC;
	}
	public BigDecimal getPesoVC() {
		return pesoVC;
	}
	public void setPesoVC(BigDecimal pesoVC) {
		this.pesoVC = pesoVC;
	}
	public Date getDataInizioVC() {
		return dataInizioVC;
	}
	public void setDataInizioVC(Date dataInizioVC) {
		this.dataInizioVC = dataInizioVC;
	}
	public Date getDataFineVC() {
		return dataFineVC;
	}
	public void setDataFineVC(Date dataFineVC) {
		this.dataFineVC = dataFineVC;
	}
	
	
}
