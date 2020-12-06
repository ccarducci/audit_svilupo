package it.tecnet.crs.componenti.web.bean;

import java.util.Date;

public class TipoNormativaCommon {
	
	
	private Date dataInizio;

	private Date dataFine;

	private String descSintetica;

	private String descDettaglio;

	private Date dataEmissione;
	
	private String codice;

	private String oggetto;
	
	private String dataInizioAsString;
	
	private String dataFineAsString;
	
	private String dataEmissioneAsString;

	
	
	
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

	public String getDescSintetica() {
		return descSintetica;
	}

	public void setDescSintetica(String descSintetica) {
		this.descSintetica = descSintetica;
	}

	public String getDescDettaglio() {
		return descDettaglio;
	}

	public void setDescDettaglio(String descDettaglio) {
		this.descDettaglio = descDettaglio;
	}

	public Date getDataEmissione() {
		return dataEmissione;
	}

	public void setDataEmissione(Date dataEmissione) {
		this.dataEmissione = dataEmissione;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getCodice() {
		return codice;
	}

	public void setOggetto(String oggetto) {
		this.oggetto = oggetto;
	}

	public String getOggetto() {
		return oggetto;
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

	public void setDataEmissioneAsString(String dataEmissioneAsString) {
		this.dataEmissioneAsString = dataEmissioneAsString;
	}

	public String getDataEmissioneAsString() {
		return dataEmissioneAsString;
	}
	
	

}
