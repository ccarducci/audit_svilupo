package it.tecnet.crs.mod.web.bean;

import java.util.Date;


public class Processo {
	
	private long idProcesso;

	private String descrizione;

	private Date dataInizio;

	private Date dataFine;
	
	private String stato;

	private Area area;
	
	private String dataInizioAsString;
	
	private String dataFineAsString;
	
	private String input;

	private String output;
	
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

	public void setStato(String stato) {
		this.stato = stato;
	}

	public String getStato() {
		return stato;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
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

	public void setInput(String input) {
		this.input = input;
	}

	public String getInput() {
		return input;
	}

	public void setOutput(String output) {
		this.output = output;
	}

	public String getOutput() {
		return output;
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
