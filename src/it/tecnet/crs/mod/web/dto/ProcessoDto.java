package it.tecnet.crs.mod.web.dto;

import java.util.Date;



public class ProcessoDto {
	
	private long idProcesso;
	
	private long idArea;

	private String descrizione;

	private Date dataInizio;

	private Date dataFine;
	
	private String dataInizioAsString;

	private String dataFineAsString;

	private String descrizioneArea;
	
	private String dataInizioArea;

	private String dataFineArea;
	
	private String stato;
	
	private String statoArea;
	
	private int ordinamento;
	
	private String owner;
	
	private String pubblicazione;


	public long getIdProcesso() {
		return idProcesso;
	}

	public void setIdProcesso(long idProcesso) {
		this.idProcesso = idProcesso;
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

	public void setDataInizioAsString(String dataInizioAsString) {
		this.dataInizioAsString = dataInizioAsString;
	}

	public String getDataInizioAsString() {
		return dataInizioAsString;
	}

	public void setDataFineAsString(String dataFineAsString) {
		this.dataFineAsString = dataFineAsString;
	}

	public String getDataFineAsString() {
		return dataFineAsString;
	}

	public long getIdArea() {
		return idArea;
	}

	public void setIdArea(long idArea) {
		this.idArea = idArea;
	}

	public String getDescrizioneArea() {
		return descrizioneArea;
	}

	public void setDescrizioneArea(String descrizioneArea) {
		this.descrizioneArea = descrizioneArea;
	}

	public String getDataInizioArea() {
		return dataInizioArea;
	}

	public void setDataInizioArea(String dataInizioArea) {
		this.dataInizioArea = dataInizioArea;
	}

	public String getDataFineArea() {
		return dataFineArea;
	}

	public void setDataFineArea(String dataFineArea) {
		this.dataFineArea = dataFineArea;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public void setStatoArea(String statoArea) {
		this.statoArea = statoArea;
	}

	public String getStatoArea() {
		return statoArea;
	}

	public void setOrdinamento(int ordinamento) {
		this.ordinamento = ordinamento;
	}

	public int getOrdinamento() {
		return ordinamento;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getOwner() {
		return owner;
	}

	public void setPubblicazione(String pubblicazione) {
		this.pubblicazione = pubblicazione;
	}

	public String getPubblicazione() {
		return pubblicazione;
	}
}