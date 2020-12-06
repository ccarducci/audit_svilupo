package it.tecnet.crs.audit.web.dto;

import java.math.BigDecimal;
import java.util.Date;

public class AuAuditDomandaDto {
	
	
	private long idDomanda;
	private long idRischio;
	private long idQuestionario;
	
	private String descrizione;
	private long peso;
	private long valMaxRsp;
	private String descrizioneRischio;
	private String codiceRischio;
	private String dataInizio;
	private String dataFine;
	
	private BigDecimal pesoPErcentuale;
	private String controlloProcesso;
	public long getIdDomanda() {
		return idDomanda;
	}
	public void setIdDomanda(long idDomanda) {
		this.idDomanda = idDomanda;
	}
	public long getIdRischio() {
		return idRischio;
	}
	public void setIdRischio(long idRischio) {
		this.idRischio = idRischio;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public long getPeso() {
		return peso;
	}
	public void setPeso(long peso) {
		this.peso = peso;
	}
	public long getValMaxRsp() {
		return valMaxRsp;
	}
	public void setValMaxRsp(long valMaxRsp) {
		this.valMaxRsp = valMaxRsp;
	}
	public String getDescrizioneRischio() {
		return descrizioneRischio;
	}
	public void setDescrizioneRischio(String descrizioneRischio) {
		this.descrizioneRischio = descrizioneRischio;
	}
	public BigDecimal getPesoPErcentuale() {
		return pesoPErcentuale;
	}
	public void setPesoPErcentuale(BigDecimal pesoPErcentuale) {
		this.pesoPErcentuale = pesoPErcentuale;
	}
	public String getControlloProcesso() {
		return controlloProcesso;
	}
	public void setControlloProcesso(String controlloProcesso) {
		this.controlloProcesso = controlloProcesso;
	}
	public void setIdQuestionario(long idQuestionario) {
		this.idQuestionario = idQuestionario;
	}
	public long getIdQuestionario() {
		return idQuestionario;
	}
	public String getCodiceRischio() {
		return codiceRischio;
	}
	public void setCodiceRischio(String codiceRischio) {
		this.codiceRischio = codiceRischio;
	}
	public void setDataInizio(String dataInizio) {
		this.dataInizio = dataInizio;
	}
	public String getDataInizio() {
		return dataInizio;
	}
	public void setDataFine(String dataFine) {
		this.dataFine = dataFine;
	}
	public String getDataFine() {
		return dataFine;
	}
	
}
