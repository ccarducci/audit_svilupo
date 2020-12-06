package it.tecnet.crs.componenti.web.bean;

import it.tecnet.crs.componenti.jpa.model.CrsDomini;

import java.util.ArrayList;
import java.util.List;

public class DominiPaginator {
	
	
	private String codiceDominio;
	private Long idDominio;
	private Boolean codEsistente;
	private CrsDomini dominio;
	private String listId;
	private long idFile;
	
	public String sEcho;

	public String sSearch;

  	public int iDisplayLength;

  	public int iDisplayStart;

  	public int iColumns;

  	public int iSortingCols;
    
  	public String sColumns;
    
  	public int  iTotalRecords;
    
  	public int  iTotalDisplayRecords;

  	public List<Object> aaData = new ArrayList<Object>();
  
  	public int iSortCol_0=0;
  	
  	public String sSortDir_0; 
  	
  	
	public String getsEcho() {
		return sEcho;
	}


	public void setsEcho(String sEcho) {
		this.sEcho = sEcho;
	}


	public String getsSearch() {
		return sSearch;
	}


	public void setsSearch(String sSearch) {
		this.sSearch = sSearch;
	}


	public int getiDisplayLength() {
		return iDisplayLength;
	}


	public void setiDisplayLength(int iDisplayLength) {
		this.iDisplayLength = iDisplayLength;
	}


	public int getiDisplayStart() {
		return iDisplayStart;
	}


	public void setiDisplayStart(int iDisplayStart) {
		this.iDisplayStart = iDisplayStart;
	}


	public int getiColumns() {
		return iColumns;
	}


	public void setiColumns(int iColumns) {
		this.iColumns = iColumns;
	}


	public int getiSortingCols() {
		return iSortingCols;
	}


	public void setiSortingCols(int iSortingCols) {
		this.iSortingCols = iSortingCols;
	}


	public String getsColumns() {
		return sColumns;
	}


	public void setsColumns(String sColumns) {
		this.sColumns = sColumns;
	}	

	public int getiTotalRecords() {
		return iTotalRecords;
	}


	public void setiTotalRecords(int iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}


	public int getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}


	public void setiTotalDisplayRecords(int iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}


	public List<Object> getAaData() {
		return aaData;
	}


	public void setAaData(List<Object> aaData) {
		this.aaData = aaData;
	}


	public int getiSortCol_0() {
		return iSortCol_0;
	}


	public void setiSortCol_0(int iSortCol_0) {
		this.iSortCol_0 = iSortCol_0;
	}


	public String getsSortDir_0() {
		return sSortDir_0;
	}


	public void setsSortDir_0(String sSortDir_0) {
		this.sSortDir_0 = sSortDir_0;
	}


	public void setCodiceDominio(String codiceDominio) {
		this.codiceDominio = codiceDominio;
	}


	public String getCodiceDominio() {
		return codiceDominio;
	}


	public void setIdDominio(Long idDominio) {
		this.idDominio = idDominio;
	}


	public Long getIdDominio() {
		return idDominio;
	}


	public void setCodEsistente(Boolean codEsistente) {
		this.codEsistente = codEsistente;
	}


	public Boolean getCodEsistente() {
		return codEsistente;
	}


	public void setDominio(CrsDomini dominio) {
		this.dominio = dominio;
	}


	public CrsDomini getDominio() {
		return dominio;
	}


	public void setListId(String listId) {
		this.listId = listId;
	}


	public String getListId() {
		return listId;
	}


	public void setIdFile(long idFile) {
		this.idFile = idFile;
	}


	public long getIdFile() {
		return idFile;
	}
}
