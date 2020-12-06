package it.tecnet.crs.audit.web.beans;

import it.tecnet.crs.jpa.model.AuMNonConf;
import it.tecnet.crs.jpa.model.AuNonConformita;
import it.tecnet.crs.mod.jpa.model.CrsSottoprocesso;

import java.util.ArrayList;
import java.util.List;

public class ModelRischiENonConformitaNonConformita {
	
	
	public List<CrsSottoprocesso> fasiDropDown= new ArrayList<CrsSottoprocesso>();
	
	public String listaIdNonConf;
	public List<AuMNonConf> ncDaNonEliminare = new ArrayList<AuMNonConf>();
	
	private List<AuMNonConf> nc = new ArrayList<AuMNonConf>();


	public AuMNonConf c;
	
	public AuMNonConf getC() {
		return c;
	}


	public void setC(AuMNonConf c) {
		this.c = c;
	}


	public List<CrsSottoprocesso> getFasiDropDown() {
		return fasiDropDown;
	}


	public void setFasiDropDown(List<CrsSottoprocesso> fasiDropDown) {
		this.fasiDropDown = fasiDropDown;
	}
	public List<AuMNonConf> getNcDaNonEliminare() {
		return ncDaNonEliminare;
	}


	public void setNcDaNonEliminare(List<AuMNonConf> ncDaNonEliminare) {
		this.ncDaNonEliminare = ncDaNonEliminare;
	}


	public String getListaIdNonConf() {
		return listaIdNonConf;
	}


	public void setListaIdNonConf(String listaIdNonConf) {
		this.listaIdNonConf = listaIdNonConf;
	}

	public long idAudit;
	
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


	public void setIdAudit(long idAudit) {
		this.idAudit = idAudit;
	}


	public long getIdAudit() {
		return idAudit;
	}


	public void setNc(List<AuMNonConf> nc) {
		this.nc = nc;
	}


	public List<AuMNonConf> getNc() {
		return nc;
	}



	


}



