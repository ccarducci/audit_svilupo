package it.tecnet.crs.componenti.web.bean;

import it.tecnet.crs.componenti.jpa.model.CrsDominiValori;
import it.tecnet.crs.util.datatables.DataTablesModel;

public class DominiValoriPaginator extends DataTablesModel {
	
	private Long idDominiValori;
	
	private String codiceValore;
	
	private String codiceDominio;
	
	private Boolean codEsistente;
	
	private CrsDominiValori dominiValori;
	
	
	
	
	public Long getIdDominiValori() {
		return idDominiValori;
	}

	public void setIdDominiValori(Long idDominiValori) {
		this.idDominiValori = idDominiValori;
	}

	public String getCodiceValore() {
		return codiceValore;
	}

	public void setCodiceValore(String codiceValore) {
		this.codiceValore = codiceValore;
	}

	public String getCodiceDominio() {
		return codiceDominio;
	}

	public void setCodiceDominio(String codiceDominio) {
		this.codiceDominio = codiceDominio;
	}

	public Boolean getCodEsistente() {
		return codEsistente;
	}

	public void setCodEsistente(Boolean codEsistente) {
		this.codEsistente = codEsistente;
	}

	public CrsDominiValori getDominiValori() {
		return dominiValori;
	}

	public void setDominiValori(CrsDominiValori dominiValori) {
		this.dominiValori = dominiValori;
	}

	public String getListId() {
		return listId;
	}

	public void setListId(String listId) {
		this.listId = listId;
	}

	private String listId;
	
}
