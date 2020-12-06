package it.tecnet.crs.web.beans;

import it.tecnet.crs.ATPO.auditors.web.dto.AtpoTipologicheDto;
import it.tecnet.crs.jpa.model.AuMediaFase;
import it.tecnet.crs.jpa.model.AuSPratica;
import it.tecnet.crs.web.dto.AuTplTipologicheDto;
import it.tecnet.crs.web.dto.NonConformitaDto;
import it.tecnet.crs.web.dto.NonConformitaVerbaleDto;
import it.tecnet.crs.web.dto.QuestionarioDto;

import java.util.ArrayList;
import java.util.List;


public class Accesso {

	private String titleGrafico;
	private int charts;//numero di grafici da disegnare
	private String toReturn;
	private String toReturnTwo;
	private String toReturnThree;
	private String toReturnFour;
	private int thisFase=0; 
	
	
	
	private int idSessione;
	private int idPratica;
	private int idRisposta;
	private int idDomanda;
	private Long idVerbale;
	
	private String nomeSede;
	
	//NonConformitaVerbale
	private long idNCV; 
	private String vComp;
	private String rischio;
	private java.math.BigDecimal valoreCalcolato;
	private java.math.BigDecimal valoreReale;
	private String note;	
	
	private List<QuestionarioDto> questionarioList;	
	private AuMediaFase mediaFase;
	
	
	private AuSPratica pratica;
	private String statoEsamePratica;
	
	
	
	//media per ogni non conformita fase N
	List<NonConformitaDto> mediaFaseNonConformitaN;
	//media per ogni non conformita fase D
	List<NonConformitaDto> mediaFaseNonConformitaD;
	
	//indicatori fase notifica
	List<NonConformitaVerbaleDto> listNonConformitaNotifica;
	
	//indicatori fase definzione
	List<NonConformitaVerbaleDto> listNonConformitaDefinizione;
	
	private List<AuTplTipologicheDto>tipologiaVerbaleIspettivo = new ArrayList<AuTplTipologicheDto>();
	
	//TAB NOTE
	private String notaSessione;
	
	//DROPDOWN
	
	//destinatario notifica e inserimento dataNotifica
	List<AtpoTipologicheDto> correttoErrato= new ArrayList<AtpoTipologicheDto>();
	
	//modalitaNotifica
	List<AuTplTipologicheDto> modalitaNotifica = new ArrayList<AuTplTipologicheDto>();
	
	//chi ha effettuato la notifica
	List<AuTplTipologicheDto> chiNotifica = new ArrayList<AuTplTipologicheDto>();
	
	//si no
	List<AtpoTipologicheDto> siNoDropd = new ArrayList<AtpoTipologicheDto>();
	
	//esito regolarizzazione
	List<AuTplTipologicheDto> esitoReg = new ArrayList<AuTplTipologicheDto>();
	
	//esito regolarizzazione corretto
	List<AuTplTipologicheDto> esitoRegCorretto = new ArrayList<AuTplTipologicheDto>();

	

	//errore esito
	List<AuTplTipologicheDto> erroreEsito = new ArrayList<AuTplTipologicheDto>();

	//Modalita Comunicazione Disconoscimento RDL
	List<AuTplTipologicheDto> comDiscRdl = new ArrayList<AuTplTipologicheDto>();
	
	//credito prescritto
	List<AuTplTipologicheDto> creditoPrescritto = new ArrayList<AuTplTipologicheDto>();
	

	public List<AuTplTipologicheDto> getCreditoPrescritto() {
		return creditoPrescritto;
	}

	public void setCreditoPrescritto(List<AuTplTipologicheDto> creditoPrescritto) {
		this.creditoPrescritto = creditoPrescritto;
	}

	//dataProtocollo
	String dataProtocollo;

	//TABLE
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
	
	public long getIdNCV() {
		return idNCV;
	}

	public void setIdNCV(long idNCV) {
		this.idNCV = idNCV;
	}

	public String getVComp() {
		return vComp;
	}

	public void setVComp(String comp) {
		vComp = comp;
	}

	public String getRischio() {
		return rischio;
	}

	public void setRischio(String rischio) {
		this.rischio = rischio;
	}

	public java.math.BigDecimal getValoreCalcolato() {
		return valoreCalcolato;
	}

	public void setValoreCalcolato(java.math.BigDecimal valoreCalcolato) {
		this.valoreCalcolato = valoreCalcolato;
	}

	public java.math.BigDecimal getValoreReale() {
		return valoreReale;
	}

	public void setValoreReale(java.math.BigDecimal valoreReale) {
		this.valoreReale = valoreReale;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}


	public int getIdSessione() {
		return idSessione;
	}

	public void setIdSessione(int idSessione) {
		this.idSessione = idSessione;
	}

	public int getIdPratica() {
		return idPratica;
	}

	public void setIdPratica(int idPratica) {
		this.idPratica = idPratica;
	}

	public void setIdRisposta(int idRisposta) {
		this.idRisposta = idRisposta;
	}

	public int getIdRisposta() {
		return idRisposta;
	}

	public void setIdDomanda(int idDomanda) {
		this.idDomanda = idDomanda;
	}

	public int getIdDomanda() {
		return idDomanda;
	}

	public void setIdVerbale(Long idVerbale) {
		this.idVerbale = idVerbale;
	}

	public Long getIdVerbale() {
		return idVerbale;
	}
	

	public List<QuestionarioDto> getQuestionarioList() {
		return questionarioList;
	}

	public void setQuestionarioList(List<QuestionarioDto> questionarioList) {
		this.questionarioList = questionarioList;
	}

	

	public AuSPratica getPratica() {
		return pratica;
	}

	public void setPratica(AuSPratica pratica) {
		this.pratica = pratica;
	}

	public String getStatoEsamePratica() {
		return statoEsamePratica;
	}

	public void setStatoEsamePratica(String statoEsamePratica) {
		this.statoEsamePratica = statoEsamePratica;
	}

	public String sEcho;
  	public String getSEcho() {
		return sEcho;
	}

	public void setSEcho(String echo) {
		sEcho = echo;
	}

	public String getSSearch() {
		return sSearch;
	}

	public void setSSearch(String search) {
		sSearch = search;
	}

	public int getIDisplayLength() {
		return iDisplayLength;
	}

	public void setIDisplayLength(int displayLength) {
		iDisplayLength = displayLength;
	}

	public int getIDisplayStart() {
		return iDisplayStart;
	}

	public void setIDisplayStart(int displayStart) {
		iDisplayStart = displayStart;
	}

	public int getIColumns() {
		return iColumns;
	}

	public void setIColumns(int columns) {
		iColumns = columns;
	}

	public int getISortingCols() {
		return iSortingCols;
	}

	public void setISortingCols(int sortingCols) {
		iSortingCols = sortingCols;
	}

	public String getSColumns() {
		return sColumns;
	}

	public void setSColumns(String columns) {
		sColumns = columns;
	}

	public int getITotalRecords() {
		return iTotalRecords;
	}

	public void setITotalRecords(int totalRecords) {
		iTotalRecords = totalRecords;
	}

	public int getITotalDisplayRecords() {
		return iTotalDisplayRecords;
	}

	public void setITotalDisplayRecords(int totalDisplayRecords) {
		iTotalDisplayRecords = totalDisplayRecords;
	}

	public List<Object> getAaData() {
		return aaData;
	}

	public void setAaData(List<Object> aaData) {
		this.aaData = aaData;
	}

	public int getISortCol_0() {
		return iSortCol_0;
	}

	public void setISortCol_0(int sortCol_0) {
		iSortCol_0 = sortCol_0;
	}

	public String getSSortDir_0() {
		return sSortDir_0;
	}

	public void setSSortDir_0(String sortDir_0) {
		sSortDir_0 = sortDir_0;
	}

	public List<NonConformitaVerbaleDto> getListNonConformitaNotifica() {
		return listNonConformitaNotifica;
	}

	public void setListNonConformitaNotifica(
			List<NonConformitaVerbaleDto> listNonConformitaNotifica) {
		this.listNonConformitaNotifica = listNonConformitaNotifica;
	}
	
	public List<NonConformitaVerbaleDto> getListNonConformitaDefinizione() {
		return listNonConformitaDefinizione;
	}

	public void setListNonConformitaDefinizione(
			List<NonConformitaVerbaleDto> listNonConformitaDefinizione) {
		this.listNonConformitaDefinizione = listNonConformitaDefinizione;
	}

	public void setMediaFase(AuMediaFase mediaFase) {
		this.mediaFase = mediaFase;
	}

	public AuMediaFase getMediaFase() {
		return mediaFase;
	}

	public void setNotaSessione(String notaSessione) {
		this.notaSessione = notaSessione;
	}

	public String getNotaSessione() {
		return notaSessione;
	}

	public List<NonConformitaDto> getMediaFaseNonConformitaN() {
		return mediaFaseNonConformitaN;
	}

	public void setMediaFaseNonConformitaN(
			List<NonConformitaDto> mediaFaseNonConformitaN) {
		this.mediaFaseNonConformitaN = mediaFaseNonConformitaN;
	}

	public List<NonConformitaDto> getMediaFaseNonConformitaD() {
		return mediaFaseNonConformitaD;
	}

	public void setMediaFaseNonConformitaD(
			List<NonConformitaDto> mediaFaseNonConformitaD) {
		this.mediaFaseNonConformitaD = mediaFaseNonConformitaD;
	}
	
	public List<AtpoTipologicheDto> getCorrettoErrato() {
		return correttoErrato;
	}

	public void setCorrettoErrato(List<AtpoTipologicheDto> correttoErrato) {
		this.correttoErrato = correttoErrato;
	}

	public List<AuTplTipologicheDto> getModalitaNotifica() {
		return modalitaNotifica;
	}

	public void setModalitaNotifica(List<AuTplTipologicheDto> modalitaNotifica) {
		this.modalitaNotifica = modalitaNotifica;
	}

	public List<AuTplTipologicheDto> getChiNotifica() {
		return chiNotifica;
	}

	public void setChiNotifica(List<AuTplTipologicheDto> chiNotifica) {
		this.chiNotifica = chiNotifica;
	}
	public String getDataProtocollo() {
		return dataProtocollo;
	}

	public void setDataProtocollo(String dataProtocollo) {
		this.dataProtocollo = dataProtocollo;
	}
	
	public List<AtpoTipologicheDto> getSiNoDropd() {
		return siNoDropd;
	}

	public void setSiNoDropd(List<AtpoTipologicheDto> siNoDropd) {
		this.siNoDropd = siNoDropd;
	}
	public List<AuTplTipologicheDto> getEsitoReg() {
		return esitoReg;
	}

	public void setEsitoReg(List<AuTplTipologicheDto> esitoReg) {
		this.esitoReg = esitoReg;
	}
	
	public List<AuTplTipologicheDto> getErroreEsito() {
		return erroreEsito;
	}

	public void setErroreEsito(List<AuTplTipologicheDto> erroreEsito) {
		this.erroreEsito = erroreEsito;
	}

	public List<AuTplTipologicheDto> getComDiscRdl() {
		return comDiscRdl;
	}

	public void setComDiscRdl(List<AuTplTipologicheDto> comDiscRdl) {
		this.comDiscRdl = comDiscRdl;
	}

	public void setNomeSede(String nomeSede) {
		this.nomeSede = nomeSede;
	}

	public String getNomeSede() {
		return nomeSede;
	}

	public void setTipologiaVerbaleIspettivo(
			List<AuTplTipologicheDto> tipologiaVerbaleIspettivo) {
		this.tipologiaVerbaleIspettivo = tipologiaVerbaleIspettivo;
	}

	public List<AuTplTipologicheDto> getTipologiaVerbaleIspettivo() {
		return tipologiaVerbaleIspettivo;
	}
	public List<AuTplTipologicheDto> getEsitoRegCorretto() {
		return esitoRegCorretto;
	}

	public void setEsitoRegCorretto(List<AuTplTipologicheDto> esitoRegCorretto) {
		this.esitoRegCorretto = esitoRegCorretto;
	}

	public void setTitleGrafico(String titleGrafico) {
		this.titleGrafico = titleGrafico;
	}

	public String getTitleGrafico() {
		return titleGrafico;
	}

	public void setToReturn(String toReturn) {
		this.toReturn = toReturn;
	}

	public String getToReturn() {
		return toReturn;
	}
	
	public int getCharts() {
		return charts;
	}

	public void setCharts(int charts) {
		this.charts = charts;
	}

	public void setThisFase(int thisFase) {
		this.thisFase = thisFase;
	}

	public int getThisFase() {
		return thisFase;
	}

	public void setToReturnTwo(String toReturnTwo) {
		this.toReturnTwo = toReturnTwo;
	}

	public String getToReturnTwo() {
		return toReturnTwo;
	}

	public void setToReturnThree(String toReturnThree) {
		this.toReturnThree = toReturnThree;
	}

	public String getToReturnThree() {
		return toReturnThree;
	}

	public void setToReturnFour(String toReturnFour) {
		this.toReturnFour = toReturnFour;
	}

	public String getToReturnFour() {
		return toReturnFour;
	}
}
