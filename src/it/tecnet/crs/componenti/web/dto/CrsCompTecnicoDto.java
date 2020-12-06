package it.tecnet.crs.componenti.web.dto;

import java.util.Date;

public class CrsCompTecnicoDto {


	private long id;

	private String codice;
	

	private String descrizione;
	

	private Date dataInizio;
	
	
	private Date dataFine;
	

	private Date dataPubblicazione;
	

	private String versione;
	

	private String autore;


	private String  tipoCompTecnico;
	
	private long idTplCompTecnico;
	
	
	
	public Date getDataPubblicazione() {
		return dataPubblicazione;
	}

	public void setDataPubblicazione(Date dataPubblicazione) {
		this.dataPubblicazione = dataPubblicazione;
	}

	public String getVersione() {
		return versione;
	}

	public void setVersione(String versione) {
		this.versione = versione;
	}

	public String getAutore() {
		return autore;
	}

	public void setAutore(String autore) {
		this.autore = autore;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
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

	public void setTipoCompTecnico(String tipoCompTecnico) {
		this.tipoCompTecnico = tipoCompTecnico;
	}

	public String getTipoCompTecnico() {
		return tipoCompTecnico;
	}

	public void setIdTplCompTecnico(long idTplCompTecnico) {
		this.idTplCompTecnico = idTplCompTecnico;
	}

	public long getIdTplCompTecnico() {
		return idTplCompTecnico;
	}
	
	

}
