package it.tecnet.crs.ATPO.auditors.web.beans;

import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoFaseEsecuzioneProvvedimenti;
import it.tecnet.crs.ATPO.auditors.web.dto.AuTplTipologicheAtpoDto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ModelAuditorsEsecuzioneProvvedimentiAtpo {

	private String praticaModificable;

	// model

	public String getPraticaModificable() {
		return praticaModificable;
	}

	public void setPraticaModificable(String praticaModificable) {
		this.praticaModificable = praticaModificable;
	}

	private AtpoFaseEsecuzioneProvvedimenti esecuzioneProvvedimenti;
	private String statoEsamePratica;

	// dropdown

	private List<AuTplTipologicheAtpoDto> opzCondPagSpeseLegali = new ArrayList<AuTplTipologicheAtpoDto>();
	private List<AuTplTipologicheAtpoDto> opzSoggRichPagamento = new ArrayList<AuTplTipologicheAtpoDto>();
	private List<AuTplTipologicheAtpoDto> optionsSiNoEsProvv = new ArrayList<AuTplTipologicheAtpoDto>();
	private List<AuTplTipologicheAtpoDto> optionsVerPagCtuEff = new ArrayList<AuTplTipologicheAtpoDto>();

	private String giudiziofaseDati;

	private String codPresDecrOmgFasc;
	private String codPrestCorrisp;
	private String codCondannaPagCtuAtpo;
	private String codAntSpeseCtu;
	private String codCondannaPagSpeseLegali;
	private String codSoggRichPagamento;
	private String codDresDecrSentMancPagPrest;
	private String codCondannaPagCtuPg;
	private String codVerPagCtuEff;

	private Date dataNotificaDecretoOmologa;
	private Date dataDepositoDecretoOmologa;
	private Date dataTrasmissioneLps;

	public List<AuTplTipologicheAtpoDto> getOpzSoggRichPagamento() {
		return opzSoggRichPagamento;
	}

	public void setOpzSoggRichPagamento(
			List<AuTplTipologicheAtpoDto> opzSoggRichPagamento) {
		this.opzSoggRichPagamento = opzSoggRichPagamento;
	}

	public List<AuTplTipologicheAtpoDto> getOpzCondPagSpeseLegali() {
		return opzCondPagSpeseLegali;
	}

	public void setOpzCondPagSpeseLegali(
			List<AuTplTipologicheAtpoDto> opzCondPagSpeseLegali) {
		this.opzCondPagSpeseLegali = opzCondPagSpeseLegali;
	}

	public List<AuTplTipologicheAtpoDto> getOptionsSiNoEsProvv() {
		return optionsSiNoEsProvv;
	}

	public void setOptionsSiNoEsProvv(
			List<AuTplTipologicheAtpoDto> optionsSiNoEsProvv) {
		this.optionsSiNoEsProvv = optionsSiNoEsProvv;
	}

	public AtpoFaseEsecuzioneProvvedimenti getEsecuzioneProvvedimenti() {
		return esecuzioneProvvedimenti;
	}

	public void setEsecuzioneProvvedimenti(
			AtpoFaseEsecuzioneProvvedimenti esecuzioneProvvedimenti) {
		this.esecuzioneProvvedimenti = esecuzioneProvvedimenti;
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

	public void setGiudiziofaseDati(String giudiziofaseDati) {
		this.giudiziofaseDati = giudiziofaseDati;
	}

	public String getGiudiziofaseDati() {
		return giudiziofaseDati;
	}

	public void setCodPresDecrOmgFasc(String codPresDecrOmgFasc) {
		this.codPresDecrOmgFasc = codPresDecrOmgFasc;
	}

	public String getCodPresDecrOmgFasc() {
		return codPresDecrOmgFasc;
	}

	public void setCodPrestCorrisp(String codPrestCorrisp) {
		this.codPrestCorrisp = codPrestCorrisp;
	}

	public String getCodPrestCorrisp() {
		return codPrestCorrisp;
	}

	public void setCodCondannaPagCtuAtpo(String codCondannaPagCtuAtpo) {
		this.codCondannaPagCtuAtpo = codCondannaPagCtuAtpo;
	}

	public String getCodCondannaPagCtuAtpo() {
		return codCondannaPagCtuAtpo;
	}

	public void setCodAntSpeseCtu(String codAntSpeseCtu) {
		this.codAntSpeseCtu = codAntSpeseCtu;
	}

	public String getCodAntSpeseCtu() {
		return codAntSpeseCtu;
	}

	public void setCodCondannaPagSpeseLegali(String codCondannaPagSpeseLegali) {
		this.codCondannaPagSpeseLegali = codCondannaPagSpeseLegali;
	}

	public String getCodCondannaPagSpeseLegali() {
		return codCondannaPagSpeseLegali;
	}

	public void setCodSoggRichPagamento(String codSoggRichPagamento) {
		this.codSoggRichPagamento = codSoggRichPagamento;
	}

	public String getCodSoggRichPagamento() {
		return codSoggRichPagamento;
	}

	public void setCodDresDecrSentMancPagPrest(
			String codDresDecrSentMancPagPrest) {
		this.codDresDecrSentMancPagPrest = codDresDecrSentMancPagPrest;
	}

	public String getCodDresDecrSentMancPagPrest() {
		return codDresDecrSentMancPagPrest;
	}

	public void setDataNotificaDecretoOmologa(Date dataNotificaDecretoOmologa) {
		this.dataNotificaDecretoOmologa = dataNotificaDecretoOmologa;
	}

	public Date getDataNotificaDecretoOmologa() {
		return dataNotificaDecretoOmologa;
	}

	public void setDataDepositoDecretoOmologa(Date dataDepositoDecretoOmologa) {
		this.dataDepositoDecretoOmologa = dataDepositoDecretoOmologa;
	}

	public Date getDataDepositoDecretoOmologa() {
		return dataDepositoDecretoOmologa;
	}

	public void setDataTrasmissioneLps(Date dataTrasmissioneLps) {
		this.dataTrasmissioneLps = dataTrasmissioneLps;
	}

	public Date getDataTrasmissioneLps() {
		return dataTrasmissioneLps;
	}

	public void setCodCondannaPagCtuPg(String codCondannaPagCtuPg) {
		this.codCondannaPagCtuPg = codCondannaPagCtuPg;
	}

	public String getCodCondannaPagCtuPg() {
		return codCondannaPagCtuPg;
	}

	public void setOptionsVerPagCtuEff(
			List<AuTplTipologicheAtpoDto> optionsVerPagCtuEff) {
		this.optionsVerPagCtuEff = optionsVerPagCtuEff;
	}

	public List<AuTplTipologicheAtpoDto> getOptionsVerPagCtuEff() {
		return optionsVerPagCtuEff;
	}

	public void setCodVerPagCtuEff(String codVerPagCtuEff) {
		this.codVerPagCtuEff = codVerPagCtuEff;
	}

	public String getCodVerPagCtuEff() {
		return codVerPagCtuEff;
	}

	public void setStatoEsamePratica(String statoEsamePratica) {
		this.statoEsamePratica = statoEsamePratica;
	}

	public String getStatoEsamePratica() {
		return statoEsamePratica;
	}
	
	private Date dataAccesso;

	public Date getDataAccesso() {
		return dataAccesso;
	}

	public void setDataAccesso(Date dataAccesso) {
		this.dataAccesso = dataAccesso;
	}
	
}
