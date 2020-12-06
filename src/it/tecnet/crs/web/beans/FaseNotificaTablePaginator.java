package it.tecnet.crs.web.beans;

import java.util.ArrayList;
import java.util.List;


public class FaseNotificaTablePaginator {
	
	//NonConformitaVerbale
	private long idNCV; 
	private String vComp;
	public String getVComp() {
		return vComp;
	}


	public void setVComp(String comp) {
		vComp = comp;
	}


	public String getRischio() {
		return rischio;
	}


	public void setRischio(String rischio) {
		this.rischio = rischio;
	}


	public java.math.BigDecimal getValoreCalcolato() {
		return valoreCalcolato;
	}


	public void setValoreCalcolato(java.math.BigDecimal valoreCalcolato) {
		this.valoreCalcolato = valoreCalcolato;
	}


	public java.math.BigDecimal getValoreReale() {
		return valoreReale;
	}


	public void setValoreReale(java.math.BigDecimal valoreReale) {
		this.valoreReale = valoreReale;
	}


	public String getNote() {
		return note;
	}


	public void setNote(String note) {
		this.note = note;
	}


	private String rischio;
	private java.math.BigDecimal valoreCalcolato;
	private java.math.BigDecimal valoreReale;
	private String note;
	
	public int sessionId;
	
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
  

	public int getSessionId() {
		return sessionId;
	}


	public void setSessionId(int sessionId) {
		this.sessionId = sessionId;
	}


	public void setIdNCV(long idNCV) {
		this.idNCV = idNCV;
	}


	public long getIdNCV() {
		return idNCV;
	}

}
