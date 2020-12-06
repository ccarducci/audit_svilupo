package it.tecnet.crs.audit.web.beans;

import it.tecnet.crs.audit.web.dto.CampagnaDto;
import it.tecnet.crs.audit.web.dto.SessioniDto;
import it.tecnet.crs.jpa.model.AuAudit;
import it.tecnet.crs.jpa.model.AuCampagna;
import it.tecnet.crs.jpa.model.AuSede;
import it.tecnet.crs.jpa.model.AuSessioni;

import java.util.ArrayList;
import java.util.List;

public class ModelAccesso {
	
	public List<CampagnaDto> campagnaDtoList= new ArrayList<CampagnaDto>();
	
	public List<AuSede> listSede= new ArrayList<AuSede>();
	
	public String nomeSede;
	
	public SessioniDto sessioneDto;
	
	public long idAudit;
	public String nomeAudit;
	public List<AuAudit> listAudit= new ArrayList<AuAudit>();
	

	public int idSessione;
	public AuSessioni accesso;
	public AuCampagna campagna;
	
	public String getNomeSede() {
		return nomeSede;
	}


	public void setNomeSede(String nomeSede) {
		this.nomeSede = nomeSede;
	}

	
	public List<AuSede> getListSede() {
		return listSede;
	}


	public void setListSede(List<AuSede> listSede) {
		this.listSede = listSede;
	}

	
	public List<AuAudit> getListAudit() {
		return listAudit;
	}


	public void setListAudit(List<AuAudit> listAudit) {
		this.listAudit = listAudit;
	}

	
	
	public String getNomeAudit() {
		return nomeAudit;
	}


	public void setNomeAudit(String nomeAudit) {
		this.nomeAudit = nomeAudit;
	}


	
	public long getIdAudit() {
		return idAudit;
	}


	public void setIdAudit(long idAudit) {
		this.idAudit = idAudit;
	}

	public AuCampagna getCampagna() {
		return campagna;
	}


	public void setCampagna(AuCampagna campagna) {
		this.campagna = campagna;
	}



	public SessioniDto getSessioneDto() {
		return sessioneDto;
	}


	public void setSessioneDto(SessioniDto sessioneDto) {
		this.sessioneDto = sessioneDto;
	}
	public int getIdSessione() {
		return idSessione;
	}


	public void setIdSessione(int idSessione) {
		this.idSessione = idSessione;
	}


	public AuSessioni getAccesso() {
		return accesso;
	}


	public void setAccesso(AuSessioni accesso) {
		this.accesso = accesso;
	}


	public List<CampagnaDto> getCampagnaDtoList() {
		return campagnaDtoList;
	}


	public void setCampagnaDtoList(List<CampagnaDto> campagnaDtoList) {
		this.campagnaDtoList = campagnaDtoList;
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

}
