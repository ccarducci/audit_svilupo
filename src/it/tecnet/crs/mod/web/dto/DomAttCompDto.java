package it.tecnet.crs.mod.web.dto;

import java.util.Date;

public class DomAttCompDto {
	
	private long idAssociazione;
	private long idDominio;
	private String descrizione;
	private Date dataInizio;
	public long getIdAssociazione() {
		return idAssociazione;
	}
	public void setIdAssociazione(long idAssociazione) {
		this.idAssociazione = idAssociazione;
	}
	public long getIdDominio() {
		return idDominio;
	}
	public void setIdDominio(long idDominio) {
		this.idDominio = idDominio;
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
