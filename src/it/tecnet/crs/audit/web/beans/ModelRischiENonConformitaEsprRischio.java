package it.tecnet.crs.audit.web.beans;

import it.tecnet.crs.jpa.model.AuMRischio;
import it.tecnet.crs.jpa.model.AuMRisepr;

import java.util.ArrayList;
import java.util.List;

public class ModelRischiENonConformitaEsprRischio {

	public long idAudit;
	public AuMRisepr esprRischio = new AuMRisepr();
	public long idEsprRischio;
	public AuMRischio rischio;
	public List<AuMRischio> rischiDropDown= new  ArrayList<AuMRischio>();
	public List<AuMRisepr> auMRisepr= new  ArrayList<AuMRisepr>();
	
	
	String listaIdEsprRischio;
	
	
	


	public String getListaIdEsprRischio() {
		return listaIdEsprRischio;
	}


	public void setListaIdEsprRischio(String listaIdEsprRischio) {
		this.listaIdEsprRischio = listaIdEsprRischio;
	}


	public AuMRischio getRischio() {
		return rischio;
	}


	public void setRischio(AuMRischio rischio) {
		this.rischio = rischio;
	}
	public long getIdEsprRischio() {
		return idEsprRischio;
	}


	public void setIdEsprRischio(long idEsprRischio) {
		this.idEsprRischio = idEsprRischio;
	}

	public List<AuMRisepr> getAuMRisepr() {
		return auMRisepr;
	}


	public void setAuMRisepr(List<AuMRisepr> auMRisepr) {
		this.auMRisepr = auMRisepr;
	}


	public AuMRisepr getEsprRischio() {
		return esprRischio;
	}


	public void setEsprRischio(AuMRisepr esprRischio) {
		this.esprRischio = esprRischio;
	}
	
	public List<AuMRischio> getRischiDropDown() {
		return rischiDropDown;
	}


	public void setRischiDropDown(List<AuMRischio> rischiDropDown) {
		this.rischiDropDown = rischiDropDown;
	}


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
	


}
