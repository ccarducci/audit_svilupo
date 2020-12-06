package it.tecnet.crs.ATPO.auditors.web.beans;

import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoFaseGestioneIstruttoria;
import it.tecnet.crs.ATPO.auditors.web.dto.AuTplTipologicheAtpoDto;

import java.util.ArrayList;
import java.util.List;

public class ModelAuditorsGestioneIstruttoriaATPO {

	private String praticaModificable;

	public String getPraticaModificable() {
		return praticaModificable;
	}

	public void setPraticaModificable(String praticaModificable) {
		this.praticaModificable = praticaModificable;
	}

	public AtpoFaseGestioneIstruttoria gestioneIStruttoria;

	private String statoEsamePratica;

	// dropdown eccezioni non rilevabili (SI/NO)
	public List<AuTplTipologicheAtpoDto> yesNo = new ArrayList<AuTplTipologicheAtpoDto>();

	public List<AuTplTipologicheAtpoDto> getYesNo() {
		return yesNo;
	}

	public void setYesNo(List<AuTplTipologicheAtpoDto> yesNo) {
		this.yesNo = yesNo;
	}

	/*
	 * query per dropdown: - litispendeza, -decadenza, -i ndeterminatezza
	 * dell'oggetto della domanda -Carenza interesse ad agire (art. 100 c.p.c.)
	 * -Altre eccezioni processuali
	 */
	private List<AuTplTipologicheAtpoDto> optionsDropDownCondivisi = new ArrayList<AuTplTipologicheAtpoDto>();

	// dropdown Corrispondenza tra istanza ATP e domanda amministrativa di
	// invalidità
	private List<AuTplTipologicheAtpoDto> corrispondenzaIstanzaATP = new ArrayList<AuTplTipologicheAtpoDto>();

	// query dropdown Verifica della correttezza della dichiarazione di
	// esenzione dal pagamento spese
	private List<AuTplTipologicheAtpoDto> correttezzaDichiarazioneEsenzione = new ArrayList<AuTplTipologicheAtpoDto>();

	/*
	 * codifiche da settare nel value dell'option
	 */
	private String codificaEccNonRilevabili;
	private String codificaLitispendenza;
	private String codificaDecadenza;
	private String codificaCorrisp;
	private String codificaCorrett;
	private String codificaIndet;
	private String codificacar;
	private String codificatel;
	private String codifiAltreC;

	public String getCodificaEccNonRilevabili() {
		return codificaEccNonRilevabili;
	}

	public void setCodificaEccNonRilevabili(String codificaEccNonRilevabili) {
		this.codificaEccNonRilevabili = codificaEccNonRilevabili;
	}

	public String getCodificaLitispendenza() {
		return codificaLitispendenza;
	}

	public void setCodificaLitispendenza(String codificaLitispendenza) {
		this.codificaLitispendenza = codificaLitispendenza;
	}

	public String getCodificaDecadenza() {
		return codificaDecadenza;
	}

	public void setCodificaDecadenza(String codificaDecadenza) {
		this.codificaDecadenza = codificaDecadenza;
	}

	public String getCodificaCorrisp() {
		return codificaCorrisp;
	}

	public void setCodificaCorrisp(String codificaCorrisp) {
		this.codificaCorrisp = codificaCorrisp;
	}

	public String getCodificaCorrett() {
		return codificaCorrett;
	}

	public void setCodificaCorrett(String codificaCorrett) {
		this.codificaCorrett = codificaCorrett;
	}

	public String getCodificaIndet() {
		return codificaIndet;
	}

	public void setCodificaIndet(String codificaIndet) {
		this.codificaIndet = codificaIndet;
	}

	public String getCodificacar() {
		return codificacar;
	}

	public void setCodificacar(String codificacar) {
		this.codificacar = codificacar;
	}

	public String getCodificatel() {
		return codificatel;
	}

	public void setCodificatel(String codificatel) {
		this.codificatel = codificatel;
	}

	public String getCodifiAltreC() {
		return codifiAltreC;
	}

	public void setCodifiAltreC(String codifiAltreC) {
		this.codifiAltreC = codifiAltreC;
	}

	public AtpoFaseGestioneIstruttoria getGestioneIStruttoria() {
		return gestioneIStruttoria;
	}

	public void setGestioneIStruttoria(
			AtpoFaseGestioneIstruttoria gestioneIStruttoria) {
		this.gestioneIStruttoria = gestioneIStruttoria;
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

	public void setOptionsDropDownCondivisi(
			List<AuTplTipologicheAtpoDto> optionsDropDownCondivisi) {
		this.optionsDropDownCondivisi = optionsDropDownCondivisi;
	}

	public List<AuTplTipologicheAtpoDto> getOptionsDropDownCondivisi() {
		return optionsDropDownCondivisi;
	}

	public void setCorrispondenzaIstanzaATP(
			List<AuTplTipologicheAtpoDto> corrispondenzaIstanzaATP) {
		this.corrispondenzaIstanzaATP = corrispondenzaIstanzaATP;
	}

	public List<AuTplTipologicheAtpoDto> getCorrispondenzaIstanzaATP() {
		return corrispondenzaIstanzaATP;
	}

	public void setCorrettezzaDichiarazioneEsenzione(
			List<AuTplTipologicheAtpoDto> correttezzaDichiarazioneEsenzione) {
		this.correttezzaDichiarazioneEsenzione = correttezzaDichiarazioneEsenzione;
	}

	public List<AuTplTipologicheAtpoDto> getCorrettezzaDichiarazioneEsenzione() {
		return correttezzaDichiarazioneEsenzione;
	}

	public void setStatoEsamePratica(String statoEsamePratica) {
		this.statoEsamePratica = statoEsamePratica;
	}

	public String getStatoEsamePratica() {
		return statoEsamePratica;
	}

}
