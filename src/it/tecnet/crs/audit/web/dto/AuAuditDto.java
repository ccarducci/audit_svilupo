package it.tecnet.crs.audit.web.dto;

import java.util.Date;

public class AuAuditDto {
	
	private long idAudit;
	private String nome;
	private String descrizione;
	private Date dataInizio;
	private Date dataFine;
	private String input;
	private String output;
	private String obiettivo;

	public long getIdAudit() {
		return idAudit;
	}
	public void setIdAudit(long idAudit) {
		this.idAudit = idAudit;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
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
	public String getInput() {
		return input;
	}
	public void setInput(String input) {
		this.input = input;
	}
	public String getOutput() {
		return output;
	}
	public void setOutput(String output) {
		this.output = output;
	}
	public String getObiettivo() {
		return obiettivo;
	}
	public void setObiettivo(String obiettivo) {
		this.obiettivo = obiettivo;
	}

}
