package it.tecnet.crs.ATPO.auditors.web.beans;

import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoFaseAcquisizioneIstanza;
import it.tecnet.crs.ATPO.auditors.web.dto.AuTplTipologicheAtpoDto;

import java.util.ArrayList;
import java.util.List;

public class ModelAuditorsAcquisizioneIstanzaATPO {

	public AtpoFaseAcquisizioneIstanza acquisizioneIstanza;

	private String praticaModificable;

	private String statoEsamePratica;

	// tab acquisizioneIstanza.jsp
	public AtpoFaseAcquisizioneIstanza getAcquisizioneIstanza() {
		return acquisizioneIstanza;
	}

	// dropdown protoclloConImg
	private ArrayList<AuTplTipologicheAtpoDto> siNo = new ArrayList<AuTplTipologicheAtpoDto>();

	// dropdown vocetitolario
	private List<AuTplTipologicheAtpoDto> vocetitolario = new ArrayList<AuTplTipologicheAtpoDto>();

	private String codificaVoceTitolario;
	private String codificaProtImg;

	public String getPraticaModificable() {
		return praticaModificable;
	}

	public void setPraticaModificable(String praticaModificable) {
		this.praticaModificable = praticaModificable;
	}

	public void setAcquisizioneIstanza(
			AtpoFaseAcquisizioneIstanza acquisizioneIstanza) {
		this.acquisizioneIstanza = acquisizioneIstanza;
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

	public void setSiNo(ArrayList<AuTplTipologicheAtpoDto> siNo) {
		this.siNo = siNo;
	}

	public ArrayList<AuTplTipologicheAtpoDto> getSiNo() {
		return siNo;
	}

	public void setVocetitolario(List<AuTplTipologicheAtpoDto> vocetitolario) {
		this.vocetitolario = vocetitolario;
	}

	public List<AuTplTipologicheAtpoDto> getVocetitolario() {
		return vocetitolario;
	}

	public void setCodificaVoceTitolario(String codificaVoceTitolario) {
		this.codificaVoceTitolario = codificaVoceTitolario;
	}

	public String getCodificaVoceTitolario() {
		return codificaVoceTitolario;
	}

	public void setCodificaProtImg(String codificaProtImg) {
		this.codificaProtImg = codificaProtImg;
	}

	public String getCodificaProtImg() {
		return codificaProtImg;
	}

	public void setStatoEsamePratica(String statoEsamePratica) {
		this.statoEsamePratica = statoEsamePratica;
	}

	public String getStatoEsamePratica() {
		return statoEsamePratica;
	}

}
