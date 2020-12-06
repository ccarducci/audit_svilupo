package it.tecnet.crs.mod.web.bean;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Area{
	
	private static final long serialVersionUID = -4565033661824385730L;

	private long idArea;

	private Date dataInizio;

	private Date dataFine;

	private String descrizione;
	
	private String stato;
	
	private String dataInizioAsString;
	
	private String dataFineAsString;
	

	public long getIdArea() {
		return idArea;
	}

	public void setIdArea(long idArea) {
		this.idArea = idArea;
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

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
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

}
