package it.tecnet.crs.session;

import it.tecnet.crs.web.dto.QuestionarioDto;

import java.util.ArrayList;
import java.util.List;

public class DatiUtente {

	private long idSessione;
	private long idSSessione;
	private Long idVerbale;
	private long idAudit;
	private long idFase;
	private long idCampagna;
	private long idCampione;
	private String tipo;
	private long idVerbaleManualeNotifica;
	private long idVerbaleManualeDefinizione;
	private List<QuestionarioDto> listDomande = new ArrayList<QuestionarioDto>();

	// ID FASI ATPO
	private long idFaseDati;
	private String fascicolo;
	private long idAcquisizioneIstanza;
	private long idAutotutelaResGiudizio;
	private long idGestioneIstruttoria;
	private long idPraticaAtpo;
	private long idPeritale;
	private long idPostPeritale;
	private long idEsecuzioneProvvedimenti;
	private long idPrecetto;
	private long idPignoramento;
	private long idFascicolo;
	private String giudizioFaseDati;
	private long idRischioReportPianoMiglioramento;
	private long idMNonConf;
	private long idQuestionario;
	private long idDomanda;
	private String nomeSede;
	private String codSede;
	private long idDominio;
	private long idComponenteTecnico;
	private String statoEsamePratica = "";
	private Long idSPratica;
	
	private long idSNonConf;

	private long idAttivitaComponente;
	private long idNormativa;
	private long idNoteDecreti;
	private long idMessaggiNormativa;
	private long idLeggDecreti;
	
	private long idMRischio;
	private long idSRischio;
	private String nomeGrafico;
	
	private String postPeritaleType;
	private String esProvvedimentiType;
	
	public long getIdPrecetto() {
		return idPrecetto;
	}

	public void setIdPrecetto(long idPrecetto) {
		this.idPrecetto = idPrecetto;
	}

	public long getIdPignoramento() {
		return idPignoramento;
	}

	public void setIdPignoramento(long idPignoramento) {
		this.idPignoramento = idPignoramento;
	}

	public long getIdEsecuzioneProvvedimenti() {
		return idEsecuzioneProvvedimenti;
	}

	public void setIdEsecuzioneProvvedimenti(long idEsecuzioneProvvedimenti) {
		this.idEsecuzioneProvvedimenti = idEsecuzioneProvvedimenti;
	}

	public long getIdPeritale() {
		return idPeritale;
	}

	public void setIdPeritale(long idPeritale) {
		this.idPeritale = idPeritale;
	}

	public long getIdPraticaAtpo() {
		return idPraticaAtpo;
	}

	public void setIdPraticaAtpo(long idPraticaAtpo) {
		this.idPraticaAtpo = idPraticaAtpo;
	}

	public long getIdGestioneIstruttoria() {
		return idGestioneIstruttoria;
	}

	public void setIdGestioneIstruttoria(long idGestioneIstruttoria) {
		this.idGestioneIstruttoria = idGestioneIstruttoria;
	}

	public long getIdAutotutelaResGiudizio() {
		return idAutotutelaResGiudizio;
	}

	public void setIdAutotutelaResGiudizio(long idAutotutelaResGiudizio) {
		this.idAutotutelaResGiudizio = idAutotutelaResGiudizio;
	}

	public void setIdVerbale(Long idVerbale) {
		this.idVerbale = idVerbale;
	}

	public Long getIdVerbale() {
		return idVerbale;
	}

	public void setIdSessione(long idSessione) {
		this.idSessione = idSessione;
	}

	public long getIdSessione() {
		return idSessione;
	}

	public void setIdAudit(long idAudit) {
		this.idAudit = idAudit;
	}

	public long getIdAudit() {
		return idAudit;
	}

	public void setListDomande(List<QuestionarioDto> listDomande) {
		this.listDomande = listDomande;
	}

	public List<QuestionarioDto> getListDomande() {
		return listDomande;
	}

	public void setIdFase(long idFase) {
		this.idFase = idFase;
	}

	public long getIdFase() {
		return idFase;
	}

	public void setIdCampagna(long idCampagna) {
		this.idCampagna = idCampagna;
	}

	public long getIdCampagna() {
		return idCampagna;
	}

	public long getIdCampione() {
		return idCampione;
	}

	public void setIdCampione(long idCampione) {
		this.idCampione = idCampione;
	}

	public long getIdVerbaleManualeNotifica() {
		return idVerbaleManualeNotifica;
	}

	public void setIdVerbaleManualeNotifica(long idVerbaleManualeNotifica) {
		this.idVerbaleManualeNotifica = idVerbaleManualeNotifica;
	}

	public long getIdVerbaleManualeDefinizione() {
		return idVerbaleManualeDefinizione;
	}

	public void setIdVerbaleManualeDefinizione(long idVerbaleManualeDefinizione) {
		this.idVerbaleManualeDefinizione = idVerbaleManualeDefinizione;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public void setIdFaseDati(long idFaseDati) {
		this.idFaseDati = idFaseDati;
	}

	public long getIdFaseDati() {
		return idFaseDati;
	}

	public void setFascicolo(String fascicolo) {
		this.fascicolo = fascicolo;
	}

	public String getFascicolo() {
		return fascicolo;
	}

	public void setIdAcquisizioneIstanza(long idAcquisizioneIstanza) {
		this.idAcquisizioneIstanza = idAcquisizioneIstanza;
	}

	public long getIdAcquisizioneIstanza() {
		return idAcquisizioneIstanza;
	}

	public void setIdPostPeritale(long idPostPeritale) {
		this.idPostPeritale = idPostPeritale;
	}

	public long getIdPostPeritale() {
		return idPostPeritale;
	}

	public void setIdFascicolo(long idFascicolo) {
		this.idFascicolo = idFascicolo;
	}

	public long getIdFascicolo() {
		return idFascicolo;
	}

	public void setIdQuestionario(long idQuestionario) {
		this.idQuestionario = idQuestionario;
	}

	public long getIdQuestionario() {
		return idQuestionario;
	}

	public void setIdDomanda(long idDomanda) {
		this.idDomanda = idDomanda;
	}

	public long getIdDomanda() {
		return idDomanda;
	}

	public void setNomeSede(String nomeSede) {
		this.nomeSede = nomeSede;
	}

	public String getNomeSede() {
		return nomeSede;
	}

	public void setStatoEsamePratica(String statoEsamePratica) {
		this.statoEsamePratica = statoEsamePratica;
	}

	public String getStatoEsamePratica() {
		return statoEsamePratica;
	}

	public void setGiudizioFaseDati(String giudizioFaseDati) {
		this.giudizioFaseDati = giudizioFaseDati;
	}

	public String getGiudizioFaseDati() {
		return giudizioFaseDati;
	}

	public long getIdSSessione() {
		return idSSessione;
	}

	public void setIdSSessione(long idSSessione) {
		this.idSSessione = idSSessione;
	}

	public void setIdMNonConf(long idMNonConf) {
		this.idMNonConf = idMNonConf;
	}

	public long getIdMNonConf() {
		return idMNonConf;
	}

	public void setIdRischioReportPianoMiglioramento(
			long idRischioReportPianoMiglioramento) {
		this.idRischioReportPianoMiglioramento = idRischioReportPianoMiglioramento;
	}

	public long getIdRischioReportPianoMiglioramento() {
		return idRischioReportPianoMiglioramento;
	}

	public void setCodSede(String codSede) {
		this.codSede = codSede;
	}

	public String getCodSede() {
		return codSede;
	}

	public void setIdDominio(long idDominio) {
		this.idDominio = idDominio;
	}

	public long getIdDominio() {
		return idDominio;
	}

	public Long getIdSPratica() {
		return idSPratica;
	}

	public void setIdSPratica(Long idSPratica) {
		this.idSPratica = idSPratica;
	}

	public void setIdComponenteTecnico(long idComponenteTecnico) {
		this.idComponenteTecnico = idComponenteTecnico;
	}

	public long getIdComponenteTecnico() {
		return idComponenteTecnico;
	}

	public void setIdAttivitaComponente(long idAttivitaComponente) {
		this.idAttivitaComponente = idAttivitaComponente;
	}

	public long getIdAttivitaComponente() {
		return idAttivitaComponente;
	}

	public void setIdSNonConf(long idSNonConf) {
		this.idSNonConf = idSNonConf;
	}

	public long getIdSNonConf() {
		return idSNonConf;
	}

	public void setIdMRischio(long idMRischio) {
		this.idMRischio = idMRischio;
	}

	public long getIdMRischio() {
		return idMRischio;
	}

	public void setIdSRischio(long idSRischio) {
		this.idSRischio = idSRischio;
	}

	public long getIdSRischio() {
		return idSRischio;
	}

	public void setIdNormativa(long idNormativa) {
		this.idNormativa = idNormativa;
	}

	public long getIdNormativa() {
		return idNormativa;
	}

	public void setIdNoteDecreti(long idNoteDecreti) {
		this.idNoteDecreti = idNoteDecreti;
	}

	public long getIdNoteDecreti() {
		return idNoteDecreti;
	}

	public void setIdMessaggiNormativa(long idMessaggiNormativa) {
		this.idMessaggiNormativa = idMessaggiNormativa;
	}

	public long getIdMessaggiNormativa() {
		return idMessaggiNormativa;
	}

	public void setIdLeggDecreti(long idLeggDecreti) {
		this.idLeggDecreti = idLeggDecreti;
	}

	public long getIdLeggDecreti() {
		return idLeggDecreti;
	}

	public void setNomeGrafico(String nomeGrafico) {
		this.nomeGrafico = nomeGrafico;
	}

	public String getNomeGrafico() {
		return nomeGrafico;
	}

	public void setPostPeritaleType(String postPeritaleType) {
		this.postPeritaleType = postPeritaleType;
	}

	public String getPostPeritaleType() {
		return postPeritaleType;
	}

	public void setEsProvvedimentiType(String esProvvedimentiType) {
		this.esProvvedimentiType = esProvvedimentiType;
	}

	public String getEsProvvedimentiType() {
		return esProvvedimentiType;
	}



}
