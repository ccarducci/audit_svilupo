package it.tecnet.crs.audit.web.beans;

import it.tecnet.crs.audit.web.dto.AuAuditDto;
import it.tecnet.crs.audit.web.dto.CampagnaDto;
import it.tecnet.crs.jpa.model.AuCampagna;

import java.util.ArrayList;
import java.util.List;

public class ModelCampagna {
	
	public List<AuAuditDto> listAudit = new ArrayList<AuAuditDto>();
	public int numeroSessioniAssociate;
	public long idCampagna;
	public long idAuditSelezionato;
	public AuCampagna campagna;
	public CampagnaDto campagnaDto;
	//public List<AuReportSediLabel> labelList = new ArrayList<AuReportSediLabel>();
	
	public String tipoCmp;

	public int numColonneSediReport;

	public int getNumColonneSediReport() {
		return numColonneSediReport;
	}


	public void setNumColonneSediReport(int numColonneSediReport) {
		this.numColonneSediReport = numColonneSediReport;
	}


//	public List<AuReportSediLabel> getLabelList() {
//		return labelList;
//	}
//
//
//	public void setLabelList(List<AuReportSediLabel> labelList) {
//		this.labelList = labelList;
//	}
	
	
	public long getIdAuditSelezionato() {
		return idAuditSelezionato;
	}


	public void setIdAuditSelezionato(long idAuditSelezionato) {
		this.idAuditSelezionato = idAuditSelezionato;
	}




	public String idFaseToAdd;
  	
	public CampagnaDto getCampagnaDto() {
		return campagnaDto;
	}


	public void setCampagnaDto(CampagnaDto campagnaDto) {
		this.campagnaDto = campagnaDto;
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
  	
  	public long getIdCampagna() {
		return idCampagna;
	}


	public void setIdCampagna(long idCampagna) {
		this.idCampagna = idCampagna;
	}


	
	
	public AuCampagna getCampagna() {
		return campagna;
	}


	public void setCampagna(AuCampagna campagna) {
		this.campagna = campagna;
	}
  	
  	
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
	
	public List<AuAuditDto> getListAudit() {
		return listAudit;
	}


	public void setListAudit(List<AuAuditDto> listAudit) {
		this.listAudit = listAudit;
	}


	public int getNumeroSessioniAssociate() {
		return numeroSessioniAssociate;
	}


	public void setNumeroSessioniAssociate(int numeroSessioniAssociate) {
		this.numeroSessioniAssociate = numeroSessioniAssociate;
	}


	public String getTipoCmp() {
		return tipoCmp;
	}


	public void setTipoCmp(String tipoCmp) {
		this.tipoCmp = tipoCmp;
	}
	
	

}
