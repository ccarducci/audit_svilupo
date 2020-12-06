package it.tecnet.crs.ATPO.auditors.web.beans;

import it.tecnet.crs.jpa.model.AuMNonConf;
import it.tecnet.crs.jpa.model.AuMRischio;
import it.tecnet.crs.jpa.model.AuSRischio;
import it.tecnet.crs.web.dto.NonConformitaAccessiDto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class ModelAccessiReport {
	
	
	//se data aggiornamento dati sessione == null -> info
	private Date dataAggiornamentoDatiSessione;
	
	//tab non conformita
	private List<AuMNonConf> nonConformita= new ArrayList<AuMNonConf>();
	private Long idMNonConf;
	private NonConformitaAccessiDto auSNonConf;
	private List<AuSRischio> sRischi= new ArrayList<AuSRischio>();
	
	private long idRischio;
	
	private String criticitaVarComp;
	private String azioniCorrettVarComp;
	private long idSVarComp;
	
	
	private String motivazioni;
	private String azioniCorrett;
	private long idSEsprRischio;
	
	
	//tab rischi
	private List<AuMRischio> rischi= new ArrayList<AuMRischio>();
	public String getCriticitaVarComp() {
		return criticitaVarComp;
	}

	public void setCriticitaVarComp(String criticitaVarComp) {
		this.criticitaVarComp = criticitaVarComp;
	}

	public String getAzioniCorrettVarComp() {
		return azioniCorrettVarComp;
	}

	public void setAzioniCorrettVarComp(String azioniCorrettVarComp) {
		this.azioniCorrettVarComp = azioniCorrettVarComp;
	}


	public long getIdSVarComp() {
		return idSVarComp;
	}

	public void setIdSVarComp(long idSVarComp) {
		this.idSVarComp = idSVarComp;
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
	
	
	
	
	
	
	public List<AuMRischio> getRischi() {
		return rischi;
	}

	public void setRischi(List<AuMRischio> rischi) {
		this.rischi = rischi;
	}

	public long getIdRischio() {
		return idRischio;
	}

	public void setIdRischio(long idRischio) {
		this.idRischio = idRischio;
	}

	public void setNonConformita(List<AuMNonConf> nonConformita) {
		this.nonConformita = nonConformita;
	}

	public List<AuMNonConf> getNonConformita() {
		return nonConformita;
	}

	public void setIdMNonConf(Long idMNonConf) {
		this.idMNonConf = idMNonConf;
	}

	public Long getIdMNonConf() {
		return idMNonConf;
	}

	public void setAuSNonConf(NonConformitaAccessiDto auSNonConf) {
		this.auSNonConf = auSNonConf;
	}

	public NonConformitaAccessiDto getAuSNonConf() {
		return auSNonConf;
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

	public void setSRischi(List<AuSRischio> sRischi) {
		this.sRischi = sRischi;
	}

	public List<AuSRischio> getSRischi() {
		return sRischi;
	}

	public void setDataAggiornamentoDatiSessione(
			Date dataAggiornamentoDatiSessione) {
		this.dataAggiornamentoDatiSessione = dataAggiornamentoDatiSessione;
	}

	public Date getDataAggiornamentoDatiSessione() {
		return dataAggiornamentoDatiSessione;
	}


	public void setMotivazioni(String motivazioni) {
		this.motivazioni = motivazioni;
	}

	public String getMotivazioni() {
		return motivazioni;
	}

	public void setAzioniCorrett(String azioniCorrett) {
		this.azioniCorrett = azioniCorrett;
	}

	public String getAzioniCorrett() {
		return azioniCorrett;
	}

	public void setIdSEsprRischio(long idSEsprRischio) {
		this.idSEsprRischio = idSEsprRischio;
	}

	public long getIdSEsprRischio() {
		return idSEsprRischio;
	}




	

}
