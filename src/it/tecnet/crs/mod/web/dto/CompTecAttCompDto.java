package it.tecnet.crs.mod.web.dto;

import java.util.Date;

public class CompTecAttCompDto {
	
	private long idAssociazione;
	private long idCompTec;
	private String codice;
	

	public String getCodice() {
		return codice;
	}
	public void setCodice(String codice) {
		this.codice = codice;
	}
	public Date getDataFine() {
		return dataFine;
	}
	public void setDataFine(Date dataFine) {
		this.dataFine = dataFine;
	}
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
	public String getTipoCompTecnico() {
		return tipoCompTecnico;
	}
	public void setTipoCompTecnico(String tipoCompTecnico) {
		this.tipoCompTecnico = tipoCompTecnico;
	}
	public long getIdTplCompTecnico() {
		return idTplCompTecnico;
	}
	public void setIdTplCompTecnico(long idTplCompTecnico) {
		this.idTplCompTecnico = idTplCompTecnico;
	}
	private String descrizione;
	

	private Date dataInizio;
	
	
	private Date dataFine;
	

	private Date dataPubblicazione;
	

	private String versione;
	

	private String autore;


	private String  tipoCompTecnico;
	
	private long idTplCompTecnico;
	
	
	public long getIdAssociazione() {
		return idAssociazione;
	}
	public void setIdAssociazione(long idAssociazione) {
		this.idAssociazione = idAssociazione;
	}
	public long getIdCompTec() {
		return idCompTec;
	}
	public void setIdCompTec(long idCompTec) {
		this.idCompTec = idCompTec;
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
	
	

}
