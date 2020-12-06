package it.tecnet.crs.mod.web.bean;


import it.tecnet.crs.componenti.jpa.model.CrsCircolariInps;
import it.tecnet.crs.componenti.jpa.model.CrsComponenteTecnico;
import it.tecnet.crs.componenti.jpa.model.CrsDescrizioneTipo;
import it.tecnet.crs.componenti.jpa.model.CrsDomini;
import it.tecnet.crs.componenti.jpa.model.CrsLeggiDecreti;
import it.tecnet.crs.componenti.jpa.model.CrsMessaggiInps;
import it.tecnet.crs.componenti.jpa.model.CrsNoteDecreti;
import it.tecnet.crs.componenti.web.bean.TipoNormativa;
import it.tecnet.crs.mod.web.dto.ProcessoDto;
import it.tecnet.crs.web.beans.AssAcClasse;
import it.tecnet.crs.web.beans.AssAdClasse;
import it.tecnet.crs.web.beans.AssProcessoClasse;
import it.tecnet.crs.web.beans.AssSottoProcessoClasse;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Modellazione{
	
	private String tabellaAssociativa;
	private long idAssociativa;
	
	
	private CrsDomini dominio;
	private CrsComponenteTecnico compTecnico;
	private CrsCircolariInps circolariInps;
	private CrsNoteDecreti noteDecreti;
	private CrsMessaggiInps messaggiInps;
	private CrsLeggiDecreti leggiDecreti;
	private String tipoCompTecn;
	private Area area;
	
	private List<Area> areaList;
	
	private ProcessoDto processoDto;
	
	private List<ProcessoDto> processoDtoList;
	
	private Processo processo;
	
	private List<Processo> processoList;
	
	private SottoProcesso sottoProcesso;
	
	private List<SottoProcesso> sottoProcessoList;
	
	private AttivitaComponente attivitaComponente;
	
	private List<AttivitaComponente> attivitaComponenteList;
	
	private AttivitaDettaglio attivitaDettaglio;
	
	public List<TipoNormativa> tipoNormativaList;
	
	public List<CrsDescrizioneTipo> labelList = new ArrayList<CrsDescrizioneTipo>();
	
	// Modello Area
	private long idArea;
	private Date dataInizio;
	private Date dataFine;
	private String descrizione;
	private String stato;

	// Modello Processo
	private long idProcesso;
	private String descrizioneProcesso;
	private Date dataInizioProcesso;
	private Date dataFineProcesso;
	private String statoProcesso;
	private String pubblicazione;
	
	// attributo valorizzato in caso di errore
	private boolean isError;

	// attributi che riguardano le associazioni con la normativa
	public long idClasse;
  	public long idTipo;
  	public AssProcessoClasse assProcessoClasse;
  	public AssSottoProcessoClasse assSottoProcessoClasse;
  	public AssAcClasse assAcClasse;
  	public AssAdClasse assAdClasse;
	
  	private boolean isErrorOrdinamento;
  	
  	private boolean isErrorChiusuraValiditaArea;
  	
  	private String userNameDirigente;
	
	public void setArea(Area area) {
		this.area = area;
	}

	public Area getArea() {
		return area;
	}

	public void setAreaList(List<Area> areaList) {
		this.areaList = areaList;
	}

	public List<Area> getAreaList() {
		return areaList;
	}

	public long getIdArea() {
		return idArea;
	}

	public void setIdArea(long idArea) {
		this.idArea = idArea;
	}

	public Date getDataInizio() {
		return dataInizio;
	}

	public void setDataInizio(Date dataInizio) {
		this.dataInizio = dataInizio;
	}

	public Date getDataFine() {
		return dataFine;
	}

	public void setDataFine(Date dataFine) {
		this.dataFine = dataFine;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public long getIdProcesso() {
		return idProcesso;
	}

	public void setIdProcesso(long idProcesso) {
		this.idProcesso = idProcesso;
	}

	public String getDescrizioneProcesso() {
		return descrizioneProcesso;
	}

	public void setDescrizioneProcesso(String descrizioneProcesso) {
		this.descrizioneProcesso = descrizioneProcesso;
	}

	public Date getDataInizioProcesso() {
		return dataInizioProcesso;
	}

	public void setDataInizioProcesso(Date dataInizioProcesso) {
		this.dataInizioProcesso = dataInizioProcesso;
	}

	public Date getDataFineProcesso() {
		return dataFineProcesso;
	}

	public void setDataFineProcesso(Date dataFineProcesso) {
		this.dataFineProcesso = dataFineProcesso;
	}

	public void setStatoProcesso(String statoProcesso) {
		this.statoProcesso = statoProcesso;
	}

	public String getStatoProcesso() {
		return statoProcesso;
	}

	public void setPubblicazione(String pubblicazione) {
		this.pubblicazione = pubblicazione;
	}

	public String getPubblicazione() {
		return pubblicazione;
	}

	public void setError(boolean isError) {
		this.isError = isError;
	}

	public boolean isError() {
		return isError;
	}

	public void setProcessoDto(ProcessoDto processoDto) {
		this.processoDto = processoDto;
	}

	public ProcessoDto getProcessoDto() {
		return processoDto;
	}

	public void setProcessoDtoList(List<ProcessoDto> processoDtoList) {
		this.processoDtoList = processoDtoList;
	}

	public List<ProcessoDto> getProcessoDtoList() {
		return processoDtoList;
	}

	public void setProcesso(Processo processo) {
		this.processo = processo;
	}

	public Processo getProcesso() {
		return processo;
	}

	public void setProcessoList(List<Processo> processoList) {
		this.processoList = processoList;
	}

	public List<Processo> getProcessoList() {
		return processoList;
	}

	public void setSottoProcesso(SottoProcesso sottoProcesso) {
		this.sottoProcesso = sottoProcesso;
	}

	public SottoProcesso getSottoProcesso() {
		return sottoProcesso;
	}

	public void setSottoProcessoList(List<SottoProcesso> sottoProcessoList) {
		this.sottoProcessoList = sottoProcessoList;
	}

	public List<SottoProcesso> getSottoProcessoList() {
		return sottoProcessoList;
	}

	public void setAttivitaComponente(AttivitaComponente attivitaComponente) {
		this.attivitaComponente = attivitaComponente;
	}

	public AttivitaComponente getAttivitaComponente() {
		return attivitaComponente;
	}

	public void setAttivitaComponenteList(List<AttivitaComponente> attivitaComponenteList) {
		this.attivitaComponenteList = attivitaComponenteList;
	}

	public List<AttivitaComponente> getAttivitaComponenteList() {
		return attivitaComponenteList;
	}

	public void setAttivitaDettaglio(AttivitaDettaglio attivitaDettaglio) {
		this.attivitaDettaglio = attivitaDettaglio;
	}

	public AttivitaDettaglio getAttivitaDettaglio() {
		return attivitaDettaglio;
	}

	public List<TipoNormativa> getTipoNormativaList() {
		return tipoNormativaList;
	}

	public void setTipoNormativaList(List<TipoNormativa> tipoNormativaList) {
		this.tipoNormativaList = tipoNormativaList;
	}

	public List<CrsDescrizioneTipo> getLabelList() {
		return labelList;
	}

	public void setLabelList(List<CrsDescrizioneTipo> labelList) {
		this.labelList = labelList;
	}

	public long getIdClasse() {
		return idClasse;
	}

	public void setIdClasse(long idClasse) {
		this.idClasse = idClasse;
	}

	public long getIdTipo() {
		return idTipo;
	}

	public void setIdTipo(long idTipo) {
		this.idTipo = idTipo;
	}

	public AssProcessoClasse getAssProcessoClasse() {
		return assProcessoClasse;
	}

	public void setAssProcessoClasse(AssProcessoClasse assProcessoClasse) {
		this.assProcessoClasse = assProcessoClasse;
	}

	public AssSottoProcessoClasse getAssSottoProcessoClasse() {
		return assSottoProcessoClasse;
	}

	public void setAssSottoProcessoClasse(
			AssSottoProcessoClasse assSottoProcessoClasse) {
		this.assSottoProcessoClasse = assSottoProcessoClasse;
	}

	public AssAcClasse getAssAcClasse() {
		return assAcClasse;
	}

	public void setAssAcClasse(AssAcClasse assAcClasse) {
		this.assAcClasse = assAcClasse;
	}

	public AssAdClasse getAssAdClasse() {
		return assAdClasse;
	}

	public void setAssAdClasse(AssAdClasse assAdClasse) {
		this.assAdClasse = assAdClasse;
	}

	public void setErrorOrdinamento(boolean isErrorOrdinamento) {
		this.isErrorOrdinamento = isErrorOrdinamento;
	}

	public boolean isErrorOrdinamento() {
		return isErrorOrdinamento;
	}

	public void setErrorChiusuraValiditaArea(boolean isErrorChiusuraValiditaArea) {
		this.isErrorChiusuraValiditaArea = isErrorChiusuraValiditaArea;
	}

	public boolean isErrorChiusuraValiditaArea() {
		return isErrorChiusuraValiditaArea;
	}

	public void setUserNameDirigente(String userNameDirigente) {
		this.userNameDirigente = userNameDirigente;
	}

	public String getUserNameDirigente() {
		return userNameDirigente;
	}

	

	public void setDominio(CrsDomini dominio) {
		this.dominio = dominio;
	}

	public CrsDomini getDominio() {
		return dominio;
	}

	public void setIdAssociativa(long idAssociativa) {
		this.idAssociativa = idAssociativa;
	}

	public long getIdAssociativa() {
		return idAssociativa;
	}

	public void setTabellaAssociativa(String tabellaAssociativa) {
		this.tabellaAssociativa = tabellaAssociativa;
	}

	public String getTabellaAssociativa() {
		return tabellaAssociativa;
	}

	public void setCompTecnico(CrsComponenteTecnico compTecnico) {
		this.compTecnico = compTecnico;
	}

	public CrsComponenteTecnico getCompTecnico() {
		return compTecnico;
	}

	public void setCircolariInps(CrsCircolariInps circolariInps) {
		this.circolariInps = circolariInps;
	}

	public CrsCircolariInps getCircolariInps() {
		return circolariInps;
	}

	public void setNoteDecreti(CrsNoteDecreti noteDecreti) {
		this.noteDecreti = noteDecreti;
	}

	public CrsNoteDecreti getNoteDecreti() {
		return noteDecreti;
	}

	public void setMessaggiInps(CrsMessaggiInps messaggiInps) {
		this.messaggiInps = messaggiInps;
	}

	public CrsMessaggiInps getMessaggiInps() {
		return messaggiInps;
	}

	public void setLeggiDecreti(CrsLeggiDecreti leggiDecreti) {
		this.leggiDecreti = leggiDecreti;
	}

	public CrsLeggiDecreti getLeggiDecreti() {
		return leggiDecreti;
	}

	public void setTipoCompTecn(String tipoCompTecn) {
		this.tipoCompTecn = tipoCompTecn;
	}

	public String getTipoCompTecn() {
		return tipoCompTecn;
	}



	

}
