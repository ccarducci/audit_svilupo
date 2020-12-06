package it.tecnet.crs.ATPO.auditors.web.beans;

import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoFasePeritale;
import it.tecnet.crs.ATPO.auditors.web.dto.AuTplTipologicheAtpoDto;

import java.util.ArrayList;
import java.util.List;

public class ModelAuditorsPeritaleAtpo {

	public AtpoFasePeritale peritale;

	private String statoEsamePratica;
	private String praticaModificable;

	// dropdown Registrazione informazioni operazioni peritali
	private List<AuTplTipologicheAtpoDto> optionsRegInfo = new ArrayList<AuTplTipologicheAtpoDto>();

	// dropdown si no
	private List<AuTplTipologicheAtpoDto> optionsSiNoPeritale = new ArrayList<AuTplTipologicheAtpoDto>();

	// dropdown si no non rilevabile
	private List<AuTplTipologicheAtpoDto> optionsSiNonNonRilevabile = new ArrayList<AuTplTipologicheAtpoDto>();

	// dropdown concorde ecc
	private List<AuTplTipologicheAtpoDto> optionsPareBozzaCtu = new ArrayList<AuTplTipologicheAtpoDto>();

	private String recInfo;
	private String assegnCtuInps;
	private String presenzaCTPINPS;
	private String ctuBozzaImg;
	private String codParereBozzaCtu;
	private String codOssBozza;
	private String codCtuDefImgAtti;
	private String dissensAccett;
	private String tipoDiss;
	private String ossParereDef;
	private String presMedicoInpsDoc;

	public String getRecInfo() {
		return recInfo;
	}

	public void setRecInfo(String recInfo) {
		this.recInfo = recInfo;
	}

	public String getAssegnCtuInps() {
		return assegnCtuInps;
	}

	public void setAssegnCtuInps(String assegnCtuInps) {
		this.assegnCtuInps = assegnCtuInps;
	}

	public String getPresenzaCTPINPS() {
		return presenzaCTPINPS;
	}

	public void setPresenzaCTPINPS(String presenzaCTPINPS) {
		this.presenzaCTPINPS = presenzaCTPINPS;
	}

	public String getCtuBozzaImg() {
		return ctuBozzaImg;
	}

	public void setCtuBozzaImg(String ctuBozzaImg) {
		this.ctuBozzaImg = ctuBozzaImg;
	}

	public String getDissensAccett() {
		return dissensAccett;
	}

	public void setDissensAccett(String dissensAccett) {
		this.dissensAccett = dissensAccett;
	}

	public String getTipoDiss() {
		return tipoDiss;
	}

	public void setTipoDiss(String tipoDiss) {
		this.tipoDiss = tipoDiss;
	}

	public String getPraticaModificable() {
		return praticaModificable;
	}

	public void setPraticaModificable(String praticaModificable) {
		this.praticaModificable = praticaModificable;
	}

	public AtpoFasePeritale getPeritale() {
		return peritale;
	}

	public void setPeritale(AtpoFasePeritale peritale) {
		this.peritale = peritale;
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

	public void setCodOssBozza(String codOssBozza) {
		this.codOssBozza = codOssBozza;
	}

	public String getCodOssBozza() {
		return codOssBozza;
	}

	public void setCodParereBozzaCtu(String codParereBozzaCtu) {
		this.codParereBozzaCtu = codParereBozzaCtu;
	}

	public String getCodParereBozzaCtu() {
		return codParereBozzaCtu;
	}

	public void setCodCtuDefImgAtti(String codCtuDefImgAtti) {
		this.codCtuDefImgAtti = codCtuDefImgAtti;
	}

	public String getCodCtuDefImgAtti() {
		return codCtuDefImgAtti;
	}

	public void setOptionsRegInfo(List<AuTplTipologicheAtpoDto> optionsRegInfo) {
		this.optionsRegInfo = optionsRegInfo;
	}

	public List<AuTplTipologicheAtpoDto> getOptionsRegInfo() {
		return optionsRegInfo;
	}

	public void setOptionsSiNoPeritale(
			List<AuTplTipologicheAtpoDto> optionsSiNoPeritale) {
		this.optionsSiNoPeritale = optionsSiNoPeritale;
	}

	public List<AuTplTipologicheAtpoDto> getOptionsSiNoPeritale() {
		return optionsSiNoPeritale;
	}

	public void setOptionsSiNonNonRilevabile(
			List<AuTplTipologicheAtpoDto> optionsSiNonNonRilevabile) {
		this.optionsSiNonNonRilevabile = optionsSiNonNonRilevabile;
	}

	public List<AuTplTipologicheAtpoDto> getOptionsSiNonNonRilevabile() {
		return optionsSiNonNonRilevabile;
	}

	public void setOptionsPareBozzaCtu(
			List<AuTplTipologicheAtpoDto> optionsPareBozzaCtu) {
		this.optionsPareBozzaCtu = optionsPareBozzaCtu;
	}

	public List<AuTplTipologicheAtpoDto> getOptionsPareBozzaCtu() {
		return optionsPareBozzaCtu;
	}

	public void setOssParereDef(String ossParereDef) {
		this.ossParereDef = ossParereDef;
	}

	public String getOssParereDef() {
		return ossParereDef;
	}

	public void setStatoEsamePratica(String statoEsamePratica) {
		this.statoEsamePratica = statoEsamePratica;
	}

	public String getStatoEsamePratica() {
		return statoEsamePratica;
	}

	public void setPresMedicoInpsDoc(String presMedicoInpsDoc) {
		this.presMedicoInpsDoc = presMedicoInpsDoc;
	}

	public String getPresMedicoInpsDoc() {
		return presMedicoInpsDoc;
	}

}
