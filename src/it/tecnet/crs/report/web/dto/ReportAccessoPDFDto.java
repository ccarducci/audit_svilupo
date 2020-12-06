package it.tecnet.crs.report.web.dto;

import java.math.BigDecimal;

/**
 * Classe contentente tutti i dati da visualizzare nel report (PDF)
 * reportAccessoPDF
 * 
 * @author a.malerba
 * 
 */
public class ReportAccessoPDFDto {

	private String sede;

	private String dirigente;

	private long idSessione;

	private long idSSessione;

	private String dataInizio;

	private String dataFine;

	private int numeroPratiche;

	private int numeroPraticheEsaminate;

	private int numeroPraticheND;

	private int minimo;

	private int massimo;

	private int media;

	private int devStandard;

	private String statoEsameSessione;

	private String dataAggiornamentoDatiSessione;

	private String dataInizioOsservazione;

	private String dataFineOsservazione;

	private BigDecimal riepilogoINCC;

	private String descrizioneFase;

	private long idFase;

	private Double INCC;

	private String INCCDescrizione;

	private String descrizioneVarComp;

	private Integer numSVarComp;

	private Integer numSNonConf;

	private BigDecimal percentuale;

	private long idMRischio;

	private long idSRischio;

	private String descrizioneRischio;

	private String descrizioneRisEspr;

	private Integer numSRischio;

	private BigDecimal suPsPerc;

	private BigDecimal importo;

	private String descrizioneFascicolo;

	private Integer quantita;

	private String descrizioneEsito;

	private Integer numeroGiudizi;

	private Integer numeroPrestazioni;

	private BigDecimal importoPrestazioni;

	private BigDecimal speseLegali;

	private BigDecimal speseCtu;

	private String descrizioneTemporale;

	private BigDecimal mediaGG;

	private String descrizioneMNonConf;

	private long idMNonConf;

	private String NC;

	private String tipoDifesa;

	private String colore;

	private BigDecimal ImportoRischio;

	private BigDecimal PercRischio;
	
	private int ordinamento;
	
	private String codifica;
	
	private String codiceVC;

	public long getIdSSessione() {
		return idSSessione;
	}

	public void setIdSSessione(long idSSessione) {
		this.idSSessione = idSSessione;
	}

	public void setDataInizio(String dataInizio) {
		this.dataInizio = dataInizio;
	}

	public String getDataInizio() {
		return dataInizio;
	}

	public void setDataFine(String dataFine) {
		this.dataFine = dataFine;
	}

	public String getDataFine() {
		return dataFine;
	}

	public int getNumeroPratiche() {
		return numeroPratiche;
	}

	public void setNumeroPratiche(int numeroPratiche) {
		this.numeroPratiche = numeroPratiche;
	}

	public int getNumeroPraticheEsaminate() {
		return numeroPraticheEsaminate;
	}

	public void setNumeroPraticheEsaminate(int numeroPraticheEsaminate) {
		this.numeroPraticheEsaminate = numeroPraticheEsaminate;
	}

	public int getNumeroPraticheND() {
		return numeroPraticheND;
	}

	public void setNumeroPraticheND(int numeroPraticheND) {
		this.numeroPraticheND = numeroPraticheND;
	}

	public int getMinimo() {
		return minimo;
	}

	public void setMinimo(int minimo) {
		this.minimo = minimo;
	}

	public int getMassimo() {
		return massimo;
	}

	public void setMassimo(int massimo) {
		this.massimo = massimo;
	}

	public int getMedia() {
		return media;
	}

	public void setMedia(int media) {
		this.media = media;
	}

	public int getDevStandard() {
		return devStandard;
	}

	public void setDevStandard(int devStandard) {
		this.devStandard = devStandard;
	}

	public String getStatoEsameSessione() {
		return statoEsameSessione;
	}

	public void setStatoEsameSessione(String statoEsameSessione) {
		this.statoEsameSessione = statoEsameSessione;
	}

	public String getDataAggiornamentoDatiSessione() {
		return dataAggiornamentoDatiSessione;
	}

	public void setDataAggiornamentoDatiSessione(
			String dataAggiornamentoDatiSessione) {
		this.dataAggiornamentoDatiSessione = dataAggiornamentoDatiSessione;
	}

	public void setSede(String sede) {
		this.sede = sede;
	}

	public String getSede() {
		return sede;
	}

	public void setDirigente(String dirigente) {
		this.dirigente = dirigente;
	}

	public String getDirigente() {
		return dirigente;
	}

	public void setIdSessione(long idSessione) {
		this.idSessione = idSessione;
	}

	public long getIdSessione() {
		return idSessione;
	}

	public void setDataInizioOsservazione(String dataInizioOsservazione) {
		this.dataInizioOsservazione = dataInizioOsservazione;
	}

	public String getDataInizioOsservazione() {
		return dataInizioOsservazione;
	}

	public void setDataFineOsservazione(String dataFineOsservazione) {
		this.dataFineOsservazione = dataFineOsservazione;
	}

	public String getDataFineOsservazione() {
		return dataFineOsservazione;
	}

	public void setRiepilogoINCC(BigDecimal riepilogoINCC) {
		this.riepilogoINCC = riepilogoINCC;
	}

	public BigDecimal getRiepilogoINCC() {
		return riepilogoINCC;
	}

	public void setDescrizioneFase(String descrizioneFase) {
		this.descrizioneFase = descrizioneFase;
	}

	public String getDescrizioneFase() {
		return descrizioneFase;
	}

	public void setIdFase(long idFase) {
		this.idFase = idFase;
	}

	public long getIdFase() {
		return idFase;
	}

	public Double getINCC() {
		return INCC;
	}

	public void setINCC(Double incc) {
		INCC = incc;
	}

	public void setINCCDescrizione(String iNCCDescrizione) {
		INCCDescrizione = iNCCDescrizione;
	}

	public String getINCCDescrizione() {
		return INCCDescrizione;
	}

	public void setDescrizioneVarComp(String descrizioneVarComp) {
		this.descrizioneVarComp = descrizioneVarComp;
	}

	public String getDescrizioneVarComp() {
		return descrizioneVarComp;
	}

	public void setNumSVarComp(Integer numSVarComp) {
		this.numSVarComp = numSVarComp;
	}

	public Integer getNumSVarComp() {
		return numSVarComp;
	}

	public void setNumSNonConf(Integer numSNonConf) {
		this.numSNonConf = numSNonConf;
	}

	public Integer getNumSNonConf() {
		return numSNonConf;
	}

	public void setPercentuale(BigDecimal percentuale) {
		this.percentuale = percentuale;
	}

	public BigDecimal getPercentuale() {
		return percentuale;
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

	public void setDescrizioneRischio(String descrizioneRischio) {
		this.descrizioneRischio = descrizioneRischio;
	}

	public String getDescrizioneRischio() {
		return descrizioneRischio;
	}

	public void setDescrizioneRisEspr(String descrizioneRisEspr) {
		this.descrizioneRisEspr = descrizioneRisEspr;
	}

	public String getDescrizioneRisEspr() {
		return descrizioneRisEspr;
	}

	public void setNumSRischio(Integer numSRischio) {
		this.numSRischio = numSRischio;
	}

	public Integer getNumSRischio() {
		return numSRischio;
	}

	public void setSuPsPerc(BigDecimal suPsPerc) {
		this.suPsPerc = suPsPerc;
	}

	public BigDecimal getSuPsPerc() {
		return suPsPerc;
	}

	public void setImporto(BigDecimal importo) {
		this.importo = importo;
	}

	public BigDecimal getImporto() {
		return importo;
	}

	public void setDescrizioneFascicolo(String descrizioneFascicolo) {
		this.descrizioneFascicolo = descrizioneFascicolo;
	}

	public String getDescrizioneFascicolo() {
		return descrizioneFascicolo;
	}

	public void setQuantita(Integer quantita) {
		this.quantita = quantita;
	}

	public Integer getQuantita() {
		return quantita;
	}

	public void setDescrizioneEsito(String descrizioneEsito) {
		this.descrizioneEsito = descrizioneEsito;
	}

	public String getDescrizioneEsito() {
		return descrizioneEsito;
	}

	public void setNumeroGiudizi(Integer numeroGiudizi) {
		this.numeroGiudizi = numeroGiudizi;
	}

	public Integer getNumeroGiudizi() {
		return numeroGiudizi;
	}

	public void setNumeroPrestazioni(Integer numeroPrestazioni) {
		this.numeroPrestazioni = numeroPrestazioni;
	}

	public Integer getNumeroPrestazioni() {
		return numeroPrestazioni;
	}

	public void setImportoPrestazioni(BigDecimal importoPrestazioni) {
		this.importoPrestazioni = importoPrestazioni;
	}

	public BigDecimal getImportoPrestazioni() {
		return importoPrestazioni;
	}

	public void setSpeseLegali(BigDecimal speseLegali) {
		this.speseLegali = speseLegali;
	}

	public BigDecimal getSpeseLegali() {
		return speseLegali;
	}

	public void setDescrizioneTemporale(String descrizioneTemporale) {
		this.descrizioneTemporale = descrizioneTemporale;
	}

	public String getDescrizioneTemporale() {
		return descrizioneTemporale;
	}

	public void setMediaGG(BigDecimal mediaGG) {
		this.mediaGG = mediaGG;
	}

	public BigDecimal getMediaGG() {
		return mediaGG;
	}

	public void setDescrizioneMNonConf(String descrizioneMNonConf) {
		this.descrizioneMNonConf = descrizioneMNonConf;
	}

	public String getDescrizioneMNonConf() {
		return descrizioneMNonConf;
	}

	public void setIdMNonConf(long idMNonConf) {
		this.idMNonConf = idMNonConf;
	}

	public long getIdMNonConf() {
		return idMNonConf;
	}

	public void setNC(String nC) {
		NC = nC;
	}

	public String getNC() {
		return NC;
	}

	public String getTipoDifesa() {
		return tipoDifesa;
	}

	public void setTipoDifesa(String tipoDifesa) {
		this.tipoDifesa = tipoDifesa;
	}

	public String getColore() {
		return colore;
	}

	public void setColore(String colore) {
		this.colore = colore;
	}

	public BigDecimal getSpeseCtu() {
		return speseCtu;
	}

	public void setSpeseCtu(BigDecimal speseCtu) {
		this.speseCtu = speseCtu;
	}

	public BigDecimal getImportoRischio() {
		return ImportoRischio;
	}

	public void setImportoRischio(BigDecimal importoRischio) {
		ImportoRischio = importoRischio;
	}

	public BigDecimal getPercRischio() {
		return PercRischio;
	}

	public void setPercRischio(BigDecimal percRischio) {
		PercRischio = percRischio;
	}

	public void setOrdinamento(int ordinamento) {
		this.ordinamento = ordinamento;
	}

	public int getOrdinamento() {
		return ordinamento;
	}

	public void setCodifica(String codifica) {
		this.codifica = codifica;
	}

	public String getCodifica() {
		return codifica;
	}

	public String getCodiceVC() {
		return codiceVC;
	}

	public void setCodiceVC(String codiceVC) {
		this.codiceVC = codiceVC;
	}

}
