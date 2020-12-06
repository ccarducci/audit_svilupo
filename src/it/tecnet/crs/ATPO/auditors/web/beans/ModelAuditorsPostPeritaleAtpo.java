package it.tecnet.crs.ATPO.auditors.web.beans;

import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoFasePostPeritale;
import it.tecnet.crs.ATPO.auditors.web.dto.AuTplTipologicheAtpoDto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ModelAuditorsPostPeritaleAtpo {

	private String praticaModificable;

	public AtpoFasePostPeritale postPeritale;

	private String statoEsamePratica;

	private String giudiziofaseDati;

	private String tipoDiss;
	
	private String type;

	// dropdown

	private List<AuTplTipologicheAtpoDto> optionsCodChiusura = new ArrayList<AuTplTipologicheAtpoDto>();

	private List<AuTplTipologicheAtpoDto> corrispDecrOMGeCtuDef = new ArrayList<AuTplTipologicheAtpoDto>();

	private List<AuTplTipologicheAtpoDto> codPagamentoSpeseLegaliINPS = new ArrayList<AuTplTipologicheAtpoDto>();

	private List<AuTplTipologicheAtpoDto> optionsRecDatiPratica = new ArrayList<AuTplTipologicheAtpoDto>();

	private List<AuTplTipologicheAtpoDto> optionsSiNoPP = new ArrayList<AuTplTipologicheAtpoDto>();

	private List<AuTplTipologicheAtpoDto> optionsCodiceChiusura = new ArrayList<AuTplTipologicheAtpoDto>();

	private List<AuTplTipologicheAtpoDto> comunicatoDepDissUffL = new ArrayList<AuTplTipologicheAtpoDto>();

	private List<AuTplTipologicheAtpoDto> tipoDis = new ArrayList<AuTplTipologicheAtpoDto>();

	private String codificaCodChiusuraCorretto;
	private String codificaCodChiusuraInserito;
	private String codCorrispDecrOmgEctuDef;
	private String codCodPagamentoSpeseLegali;
	private String codCodPagamentoSpeseLegaliCorretto;
	private String codRecDatiPratica;
	private String codOmologaAllegata;

	private String codComDepDissUffLegale;

	private Date dataDissenso = new Date();

	public List<AuTplTipologicheAtpoDto> getOptionsSiNoPP() {
		return optionsSiNoPP;
	}

	public void setOptionsSiNoPP(List<AuTplTipologicheAtpoDto> optionsSiNoPP) {
		this.optionsSiNoPP = optionsSiNoPP;
	}

	public List<AuTplTipologicheAtpoDto> getOptionsRecDatiPratica() {
		return optionsRecDatiPratica;
	}

	public void setOptionsRecDatiPratica(
			List<AuTplTipologicheAtpoDto> optionsRecDatiPratica) {
		this.optionsRecDatiPratica = optionsRecDatiPratica;
	}

	public List<AuTplTipologicheAtpoDto> getCodPagamentoSpeseLegaliINPS() {
		return codPagamentoSpeseLegaliINPS;
	}

	public void setCodPagamentoSpeseLegaliINPS(
			List<AuTplTipologicheAtpoDto> codPagamentoSpeseLegaliINPS) {
		this.codPagamentoSpeseLegaliINPS = codPagamentoSpeseLegaliINPS;
	}

	public List<AuTplTipologicheAtpoDto> getCorrispDecrOMGeCtuDef() {
		return corrispDecrOMGeCtuDef;
	}

	public void setCorrispDecrOMGeCtuDef(
			List<AuTplTipologicheAtpoDto> corrispDecrOMGeCtuDef) {
		this.corrispDecrOMGeCtuDef = corrispDecrOMGeCtuDef;
	}

	public AtpoFasePostPeritale getPostPeritale() {
		return postPeritale;
	}

	public void setPostPeritale(AtpoFasePostPeritale postPeritale) {
		this.postPeritale = postPeritale;
	}

	public List<AuTplTipologicheAtpoDto> getOptionsCodChiusura() {
		return optionsCodChiusura;
	}

	public void setOptionsCodChiusura(
			List<AuTplTipologicheAtpoDto> optionsCodChiusura) {
		this.optionsCodChiusura = optionsCodChiusura;
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

	public void setOptionsCodiceChiusura(
			List<AuTplTipologicheAtpoDto> optionsCodiceChiusura) {
		this.optionsCodiceChiusura = optionsCodiceChiusura;
	}

	public List<AuTplTipologicheAtpoDto> getOptionsCodiceChiusura() {
		return optionsCodiceChiusura;
	}

	public void setGiudiziofaseDati(String giudiziofaseDati) {
		this.giudiziofaseDati = giudiziofaseDati;
	}

	public String getGiudiziofaseDati() {
		return giudiziofaseDati;
	}

	public void setCodificaCodChiusuraCorretto(
			String codificaCodChiusuraCorretto) {
		this.codificaCodChiusuraCorretto = codificaCodChiusuraCorretto;
	}

	public String getCodificaCodChiusuraCorretto() {
		return codificaCodChiusuraCorretto;
	}

	public void setCodificaCodChiusuraInserito(
			String codificaCodChiusuraInserito) {
		this.codificaCodChiusuraInserito = codificaCodChiusuraInserito;
	}

	public String getCodificaCodChiusuraInserito() {
		return codificaCodChiusuraInserito;
	}

	public void setCodCorrispDecrOmgEctuDef(String codCorrispDecrOmgEctuDef) {
		this.codCorrispDecrOmgEctuDef = codCorrispDecrOmgEctuDef;
	}

	public String getCodCorrispDecrOmgEctuDef() {
		return codCorrispDecrOmgEctuDef;
	}

	public void setCodCodPagamentoSpeseLegali(String codCodPagamentoSpeseLegali) {
		this.codCodPagamentoSpeseLegali = codCodPagamentoSpeseLegali;
	}

	public String getCodCodPagamentoSpeseLegali() {
		return codCodPagamentoSpeseLegali;
	}

	public void setCodCodPagamentoSpeseLegaliCorretto(
			String codCodPagamentoSpeseLegaliCorretto) {
		this.codCodPagamentoSpeseLegaliCorretto = codCodPagamentoSpeseLegaliCorretto;
	}

	public String getCodCodPagamentoSpeseLegaliCorretto() {
		return codCodPagamentoSpeseLegaliCorretto;
	}

	public void setCodRecDatiPratica(String codRecDatiPratica) {
		this.codRecDatiPratica = codRecDatiPratica;
	}

	public String getCodRecDatiPratica() {
		return codRecDatiPratica;
	}

	public void setCodOmologaAllegata(String codOmologaAllegata) {
		this.codOmologaAllegata = codOmologaAllegata;
	}

	public String getCodOmologaAllegata() {
		return codOmologaAllegata;
	}

	public void setCodComDepDissUffLegale(String codComDepDissUffLegale) {
		this.codComDepDissUffLegale = codComDepDissUffLegale;
	}

	public String getCodComDepDissUffLegale() {
		return codComDepDissUffLegale;
	}

	public void setComunicatoDepDissUffL(
			List<AuTplTipologicheAtpoDto> comunicatoDepDissUffL) {
		this.comunicatoDepDissUffL = comunicatoDepDissUffL;
	}

	public List<AuTplTipologicheAtpoDto> getComunicatoDepDissUffL() {
		return comunicatoDepDissUffL;
	}

	public void setDataDissenso(Date dataDissenso) {
		this.dataDissenso = dataDissenso;
	}

	public Date getDataDissenso() {
		return dataDissenso;
	}

	public void setStatoEsamePratica(String statoEsamePratica) {
		this.statoEsamePratica = statoEsamePratica;
	}

	public String getStatoEsamePratica() {
		return statoEsamePratica;
	}

	public void setTipoDiss(String tipoDiss) {
		this.tipoDiss = tipoDiss;
	}

	public String getTipoDiss() {
		return tipoDiss;
	}

	public void setTipoDis(List<AuTplTipologicheAtpoDto> tipoDis) {
		this.tipoDis = tipoDis;
	}

	public List<AuTplTipologicheAtpoDto> getTipoDis() {
		return tipoDis;
	}

	public String getPraticaModificable() {
		return praticaModificable;
	}

	public void setPraticaModificable(String praticaModificable) {
		this.praticaModificable = praticaModificable;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	private String codChiusuraCorretto;
	private String codChiusuraInserito;

	
	public String getCodChiusuraCorretto() {
		return codChiusuraCorretto;
	}

	public void setCodChiusuraCorretto(String codChiusuraCorretto) {
		this.codChiusuraCorretto = codChiusuraCorretto;
	}

	public String getCodChiusuraInserito() {
		return codChiusuraInserito;
	}

	public void setCodChiusuraInserito(String codChiusuraInserito) {
		this.codChiusuraInserito = codChiusuraInserito;
	}

}
