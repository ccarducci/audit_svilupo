package it.tecnet.crs.ATPO.auditors.web.beans;

import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoFaseAutotutelaResistenzaGiudizio;
import it.tecnet.crs.ATPO.auditors.web.dto.AuTplTipologicheAtpoDto;

import java.util.ArrayList;
import java.util.List;

public class ModelAuditorsAutotutelaResGiudizioATPO {

	private String praticaModificable;

	public AtpoFaseAutotutelaResistenzaGiudizio autotuResGiudizio;

	private List<AuTplTipologicheAtpoDto> optionsParere = new ArrayList<AuTplTipologicheAtpoDto>();

	private List<AuTplTipologicheAtpoDto> optionsTerminiUdienza = new ArrayList<AuTplTipologicheAtpoDto>();

	private String codifParere;
	private String defTerminiEntroPudienza;

	private String statoEsamePratica;

	public String getPraticaModificable() {
		return praticaModificable;
	}

	public void setPraticaModificable(String praticaModificable) {
		this.praticaModificable = praticaModificable;
	}

	public List<AuTplTipologicheAtpoDto> getOptionsTerminiUdienza() {
		return optionsTerminiUdienza;
	}

	public void setOptionsTerminiUdienza(
			List<AuTplTipologicheAtpoDto> optionsTerminiUdienza) {
		this.optionsTerminiUdienza = optionsTerminiUdienza;
	}

	public List<AuTplTipologicheAtpoDto> getOptionsParere() {
		return optionsParere;
	}

	public void setOptionsParere(List<AuTplTipologicheAtpoDto> optionsParere) {
		this.optionsParere = optionsParere;
	}

	public AtpoFaseAutotutelaResistenzaGiudizio getAutotuResGiudizio() {
		return autotuResGiudizio;
	}

	public void setAutotuResGiudizio(
			AtpoFaseAutotutelaResistenzaGiudizio autotuResGiudizio) {
		this.autotuResGiudizio = autotuResGiudizio;
	}

	public String sEcho;

	public String sSearch;

	public int iDisplayLength;

	public int iDisplayStart;

	public int iColumns;

	public int iSortingCols;

	public String sColumns;

	public int iTotalRecords;

	public int iTotalDisplayRecords;

	public List<Object> aaData = new ArrayList<Object>();

	public int iSortCol_0 = 0;

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

	public void setCodifParere(String codifParere) {
		this.codifParere = codifParere;
	}

	public String getCodifParere() {
		return codifParere;
	}

	public void setDefTerminiEntroPudienza(String defTerminiEntroPudienza) {
		this.defTerminiEntroPudienza = defTerminiEntroPudienza;
	}

	public String getDefTerminiEntroPudienza() {
		return defTerminiEntroPudienza;
	}

	public void setStatoEsamePratica(String statoEsamePratica) {
		this.statoEsamePratica = statoEsamePratica;
	}

	public String getStatoEsamePratica() {
		return statoEsamePratica;
	}

}
