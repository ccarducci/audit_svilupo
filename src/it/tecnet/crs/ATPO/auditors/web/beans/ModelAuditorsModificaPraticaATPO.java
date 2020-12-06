package it.tecnet.crs.ATPO.auditors.web.beans;

import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoFaseDati;
import it.tecnet.crs.ATPO.auditors.web.dto.AuTplTipologicheAtpoDto;
import it.tecnet.crs.ATPO.auditors.web.dto.PraticheAtpoDto;

import java.util.ArrayList;
import java.util.List;

public class ModelAuditorsModificaPraticaATPO {
	
	private String fascicolo;
	private String filtroFase;
	private String codificaGiudizio="ND";
	public String sEcho;
	
	private List<String> descrizioneFase= new ArrayList<String>();
	
	

	public long idPraticaATPO;
    
	//TAB DATI GENERALI
	public PraticheAtpoDto praticheAtpoDto;

	//TAB DATI FASI
	public AtpoFaseDati faseDati;

	//TAB FASI
//---> da definire ;all'inizio sarà statico
	
	private List<AuTplTipologicheAtpoDto> radioButtons = new ArrayList<AuTplTipologicheAtpoDto>();
	
	public AtpoFaseDati getFaseDati() {
		return faseDati;
	}


	public void setFaseDati(AtpoFaseDati faseDati) {
		this.faseDati = faseDati;
	}


	public PraticheAtpoDto getPraticheAtpoDto() {
		return praticheAtpoDto;
	}


	public void setPraticheAtpoDto(PraticheAtpoDto praticheAtpoDto) {
		this.praticheAtpoDto = praticheAtpoDto;
	}


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
	public long getIdPraticaATPO() {
		return idPraticaATPO;
	}


	public void setIdPraticaATPO(long idPraticaATPO) {
		this.idPraticaATPO = idPraticaATPO;
	}




	public void setFascicolo(String fascicolo) {
		this.fascicolo = fascicolo;
	}


	public String getFascicolo() {
		return fascicolo;
	}


	public void setRadioButtons(List<AuTplTipologicheAtpoDto> radioButtons) {
		this.radioButtons = radioButtons;
	}


	public List<AuTplTipologicheAtpoDto> getRadioButtons() {
		return radioButtons;
	}


	public void setCodificaGiudizio(String codificaGiudizio) {
		this.codificaGiudizio = codificaGiudizio;
	}


	public String getCodificaGiudizio() {
		return codificaGiudizio;
	}


	public void setFiltroFase(String filtroFase) {
		this.filtroFase = filtroFase;
	}


	public String getFiltroFase() {
		return filtroFase;
	}


	public void setDescrizioneFase(List<String> descrizioneFase) {
		this.descrizioneFase = descrizioneFase;
	}


	public List<String> getDescrizioneFase() {
		return descrizioneFase;
	}
}
