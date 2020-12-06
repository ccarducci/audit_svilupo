package it.tecnet.crs.ATPO.auditors.jpa.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "ATPO_PRATICHE") 
@NamedQueries( {
	@NamedQuery(name = AtpoPratiche.QUERY_PRATICA_BY_SISCO, query = "SELECT t FROM AtpoPratiche t WHERE t.idPraticheSisco = :"
		+ AtpoPratiche.QUERY_PARAM_IDSISCO) ,
	@NamedQuery(name = AtpoPratiche.QUERY_PRATICA_BY_SEDE, query = "SELECT t FROM AtpoPratiche t WHERE t.Sede = :"
			+ AtpoPratiche.QUERY_PARAM_SEDE) 		
		
}) 
public class AtpoPratiche implements Serializable {
	public static final String QUERY_PRATICA_BY_SEDE = "AtpoPratiche.findBySede";
	public static final String QUERY_PARAM_SEDE= "Sede";

	
	public static final String QUERY_PRATICA_BY_SISCO = "AtpoPratiche.findBySisco";
	public static final String QUERY_PARAM_IDSISCO= "idPraticheSisco";

	private static final long serialVersionUID = 1L;
 
	@Id
	@SequenceGenerator(name = "ATPO_PRATICHE_GENERATOR", sequenceName = "ATPO_PRATICHE_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ATPO_PRATICHE_GENERATOR")
	@Column(name = "ID_PRATICHE")
	private long idPratiche;
	
	
	@Column(name = "ID_PRATICHE_SISCO")
	private long idPraticheSisco;
	
	
	
	@Column(name = "Sede")
	private String Sede;

	@Column(name = "CodiceSede")
	private String CodiceSede;
	
	@Column(name = "Anno")
	private long Anno;
	
	@Column(name = "NumeroPratica")
	private long NumeroPratica;
	
	@Column(name = "Settore")
	private String Settore;
	
	@Column(name = "PIUP")
	private long PIUP;
	
	
	
	@Column(name = "Intestatario")
	private String Intestatario;
	
	@Column(name = "CodiceFiscaleIntestatario")
	private String CodiceFiscaleIntestatario;
	
	@Column(name = "RichiestaRevocaODomanda")
	private String RichiestaRevocaODomanda;
	
	@Column(name = "Funzionario")
	private String Funzionario;
	
	@Column(name = "MedicoINPS")
	private String MedicoINPS;
	
	@Column(name = "TempoAperturaPratica")
	private long TempoAperturaPratica;
	
	@Column(name = "TempoChiusuraPratica")
	private long TempoChiusuraPratica;

	@Column(name = "Esito")
	private String Esito;
	
	@Column(name = "PeriodoRiferimento")
	private long PeriodoRiferimento;

	@Column(name = "PresenzaCTU")
	private String PresenzaCTU;

	@Column(name = "PresenzaVisitePeritali")
	private String PresenzaVisitePeritali;
	
	@Column(name = "ConPresenzaAttoCostituzione")
	private String ConPresenzaAttoCostituzione;

	@Column(name = "PresenzaParereAutotutela")
	private String PresenzaParereAutotutela;

	@Column(name = "ImportoSpeseLegali")
	private BigDecimal ImportoSpeseLegali;
	 
	@Column(name = "ImportoSpeseCTU")
	private BigDecimal ImportoSpeseCTU;

	@Column(name = "DataDepositoAtto")
	private Date DataDepositoAtto;

	@Column(name = "DataNotificaAtto")
	private Date DataNotificaAtto;

	@Column(name = "DataProtocollo")
	private Date DataProtocollo;
	
	@Column(name = "Protocollo")
	private String Protocollo;

	@Column(name = "DataAcquisizioneSISCO")
	private Date DataAcquisizioneSISCO;

	@Column(name = "Parere")
	private String Parere;
	
	@Column(name = "AutotutelaEntroPrimaUdienza")
	private String AutotutelaEntroPrimaUdienza;
	
	@Column(name = "DataCostituzioneInGiudizio")
	private Date DataCostituzioneInGiudizio;
	
	@Column(name = "CostituzioneInGiudizioTelematica")
	private long CostituzioneInGiudizioTelematica;
	
	@Column(name = "DataPrimaUdienza")
	private Date DataPrimaUdienza;
	
	@Column(name = "DataRegistrazioneVisitaPeritaleSISCO")
	private Date DataRegistrazioneVisitaPeritaleSISCO;
	
	@Column(name = "AssegnazioneCTUMedicoINPS")
	private String AssegnazioneCTUMedicoINPS;
	
	@Column(name = "PresenzaCTPINPSOperazioniPeritali")
	private String PresenzaCTPINPSOperazioniPeritali;
	
	@Column(name = "DataArrivoBozza")
	private Date DataArrivoBozza;
	
	@Column(name = "DataProtocolloBozza")
	private Date DataProtocolloBozza;
	
	@Column(name = "ProtocolloBozza")
	private String ProtocolloBozza;
	
	@Column(name = "ParereSuBozzaCTU")
	private String ParereSuBozzaCTU;

	@Column(name = "OsservazioniSuBozza")
	private String OsservazioniSuBozza;

	@Column(name = "DataProtocolloCTUDefinitiva")
	private Date DataProtocolloCTUDefinitiva;

	@Column(name = "ProtocolloCTUDefinitiva")
	private String ProtocolloCTUDefinitiva;

	@Column(name = "DataTermineDissenso")
	private Date DataTermineDissenso;
	
	@Column(name = "ParereDissensoAccettazione")
	private String ParereDissensoAccettazione;
	
	@Column(name = "OsservazioniSuParereDefinitivo")
	private String OsservazioniSuParereDefinitivo;
	
	@Column(name = "DataDepositoDecretoOmologa")
	private Date DataDepositoDecretoOmologa;
	
	@Column(name = "DataNotificaDecretoOmologa")
	private Date DataNotificaDecretoOmologa;
	
	@Column(name = "DataProtocolloDecretoOmologa")
	private Date DataProtocolloDecretoOmologa;
	
	@Column(name = "CodiceEsitoCausa")
	private long CodiceEsitoCausa;
		
	@Column(name = "EsitoCausa")
	private String EsitoCausa;
	
	@Column(name = "SpesePagate")
	private BigDecimal SpesePagate;	
	
	@Column(name = "DataRevisioneSanitaria")
	private Date DataRevisioneSanitaria;
	
	@Column(name = "DataDefinizioneGiudizio")
	private Date DataDefinizioneGiudizio;

	@Column(name = "CodicePagamentoSpeseLegaliInserite")
	private long CodicePagamentoSpeseLegaliInserite;	
	
	@Column(name = "PresenzaDecretoOmologa")
	private String PresenzaDecretoOmologa;	
	
	@Column(name = "DataLiquidazionePrestazione")
	private Date DataLiquidazionePrestazione;
	
	@Column(name = "CondannaPagamentoSpeseLegali")
	private String CondannaPagamentoSpeseLegali;

	@Column(name = "Precetto")
	private String Precetto;

	@Column(name = "PrecettoSpeseLegali")
	private String PrecettoSpeseLegali;
	
	@Column(name = "Pignoramento")
	private String Pignoramento;

	@Column(name = "PignoramentoSpeseLegali")
	private String PignoramentoSpeseLegali;
	
	@Column(name = "DataComunicazioneUffAmmPerEsecuzione")
	private Date DataComunicazioneUffAmmPerEsecuzione;
	
	@Column(name = "DataPresaInCaricoEsecuzione")
	private Date DataPresaInCaricoEsecuzione;
	
	@Column(name = "InteressiRivalutazioni")
	private BigDecimal InteressiRivalutazioni;
	
	@Column(name = "ImportoPrestazioneEconomica")
	private BigDecimal ImportoPrestazioneEconomica;
	
	@Column(name = "DataLiquidazioneCTU")
	private Date DataLiquidazioneCTU;
	
	@Column(name = "ImportoSpeseCTUPagate")
	private BigDecimal ImportoSpeseCTUPagate;
	
	@Column(name = "DataPagamentoSpeseLegali")
	private Date DataPagamentoSpeseLegali;
	
	@Column(name = "DataDepositoDissensoInps")
	private Date DataDepositoDissensoInps;

	@Column(name = "DataDepositoDissensoCNP")
	private Date DataDepositoDissensoCNP;

	public long getIdPratiche() {
		return idPratiche;
	}

	public void setIdPratiche(long idPratiche) {
		this.idPratiche = idPratiche;
	}

	public String getSede() {
		return Sede;
	}

	public void setSede(String sede) {
		Sede = sede;
	}

	public String getCodiceSede() {
		return CodiceSede;
	}

	public void setCodiceSede(String codiceSede) {
		CodiceSede = codiceSede;
	}

	public long getAnno() {
		return Anno;
	}

	public void setAnno(long anno) {
		Anno = anno;
	}

	

	public long getNumeroPratica() {
		return NumeroPratica;
	}

	public void setNumeroPratica(long numeroPratica) {
		NumeroPratica = numeroPratica;
	}

	public String getSettore() {
		return Settore;
	}

	public void setSettore(String settore) {
		Settore = settore;
	}

	public long getPIUP() {
		return PIUP;
	}

	public void setPIUP(long piup) {
		PIUP = piup;
	}


	public String getIntestatario() {
		return Intestatario;
	}

	public void setIntestatario(String intestatario) {
		Intestatario = intestatario;
	}

	public String getCodiceFiscaleIntestatario() {
		return CodiceFiscaleIntestatario;
	}

	public void setCodiceFiscaleIntestatario(String codiceFiscaleIntestatario) {
		CodiceFiscaleIntestatario = codiceFiscaleIntestatario;
	}

	public String getRichiestaRevocaODomanda() {
		return RichiestaRevocaODomanda;
	}

	public void setRichiestaRevocaODomanda(String richiestaRevocaODomanda) {
		RichiestaRevocaODomanda = richiestaRevocaODomanda;
	}

	public String getFunzionario() {
		return Funzionario;
	}

	public void setFunzionario(String funzionario) {
		Funzionario = funzionario;
	}

	public String getMedicoINPS() {
		return MedicoINPS;
	}

	public void setMedicoINPS(String medicoINPS) {
		MedicoINPS = medicoINPS;
	}

	public long getTempoAperturaPratica() {
		return TempoAperturaPratica;
	}

	public void setTempoAperturaPratica(long tempoAperturaPratica) {
		TempoAperturaPratica = tempoAperturaPratica;
	}

	public long getTempoChiusuraPratica() {
		return TempoChiusuraPratica;
	}

	public void setTempoChiusuraPratica(long tempoChiusuraPratica) {
		TempoChiusuraPratica = tempoChiusuraPratica;
	}

	public String getEsito() {
		return Esito;
	}

	public void setEsito(String esito) {
		Esito = esito;
	}

	public long getPeriodoRiferimento() {
		return PeriodoRiferimento;
	}

	public void setPeriodoRiferimento(long periodoRiferimento) {
		PeriodoRiferimento = periodoRiferimento;
	}

	public String getPresenzaCTU() {
		return PresenzaCTU;
	}

	public void setPresenzaCTU(String presenzaCTU) {
		PresenzaCTU = presenzaCTU;
	}

	public String getPresenzaVisitePeritali() {
		return PresenzaVisitePeritali;
	}

	public void setPresenzaVisitePeritali(String presenzaVisitePeritali) {
		PresenzaVisitePeritali = presenzaVisitePeritali;
	}

	public String getConPresenzaAttoCostituzione() {
		return ConPresenzaAttoCostituzione;
	}

	public void setConPresenzaAttoCostituzione(String conPresenzaAttoCostituzione) {
		ConPresenzaAttoCostituzione = conPresenzaAttoCostituzione;
	}

	public String getPresenzaParereAutotutela() {
		return PresenzaParereAutotutela;
	}

	public void setPresenzaParereAutotutela(String presenzaParereAutotutela) {
		PresenzaParereAutotutela = presenzaParereAutotutela;
	}

	public BigDecimal getImportoSpeseLegali() {
		return ImportoSpeseLegali;
	}

	public void setImportoSpeseLegali(BigDecimal importoSpeseLegali) {
		ImportoSpeseLegali = importoSpeseLegali;
	}

	public BigDecimal getImportoSpeseCTU() {
		return ImportoSpeseCTU;
	}

	public void setImportoSpeseCTU(BigDecimal importoSpeseCTU) {
		ImportoSpeseCTU = importoSpeseCTU;
	}

	public Date getDataDepositoAtto() {
		return DataDepositoAtto;
	}

	public void setDataDepositoAtto(Date dataDepositoAtto) {
		DataDepositoAtto = dataDepositoAtto;
	}

	public Date getDataNotificaAtto() {
		return DataNotificaAtto;
	}

	public void setDataNotificaAtto(Date dataNotificaAtto) {
		DataNotificaAtto = dataNotificaAtto;
	}

	public Date getDataProtocollo() {
		return DataProtocollo;
	}

	public void setDataProtocollo(Date dataProtocollo) {
		DataProtocollo = dataProtocollo;
	}

	public String getProtocollo() {
		return Protocollo;
	}

	public void setProtocollo(String protocollo) {
		Protocollo = protocollo;
	}

	public Date getDataAcquisizioneSISCO() {
		return DataAcquisizioneSISCO;
	}

	public void setDataAcquisizioneSISCO(Date dataAcquisizioneSISCO) {
		DataAcquisizioneSISCO = dataAcquisizioneSISCO;
	}

	public String getParere() {
		return Parere;
	}

	public void setParere(String parere) {
		Parere = parere;
	}

	public String getAutotutelaEntroPrimaUdienza() {
		return AutotutelaEntroPrimaUdienza;
	}

	public void setAutotutelaEntroPrimaUdienza(String autotutelaEntroPrimaUdienza) {
		AutotutelaEntroPrimaUdienza = autotutelaEntroPrimaUdienza;
	}

	public Date getDataCostituzioneInGiudizio() {
		return DataCostituzioneInGiudizio;
	}

	public void setDataCostituzioneInGiudizio(Date dataCostituzioneInGiudizio) {
		DataCostituzioneInGiudizio = dataCostituzioneInGiudizio;
	}

	public long getCostituzioneInGiudizioTelematica() {
		return CostituzioneInGiudizioTelematica;
	}

	public void setCostituzioneInGiudizioTelematica(
			long costituzioneInGiudizioTelematica) {
		CostituzioneInGiudizioTelematica = costituzioneInGiudizioTelematica;
	}

	public Date getDataPrimaUdienza() {
		return DataPrimaUdienza;
	}

	public void setDataPrimaUdienza(Date dataPrimaUdienza) {
		DataPrimaUdienza = dataPrimaUdienza;
	}

	public Date getDataRegistrazioneVisitaPeritaleSISCO() {
		return DataRegistrazioneVisitaPeritaleSISCO;
	}

	public void setDataRegistrazioneVisitaPeritaleSISCO(
			Date dataRegistrazioneVisitaPeritaleSISCO) {
		DataRegistrazioneVisitaPeritaleSISCO = dataRegistrazioneVisitaPeritaleSISCO;
	}

	public String getAssegnazioneCTUMedicoINPS() {
		return AssegnazioneCTUMedicoINPS;
	}

	public void setAssegnazioneCTUMedicoINPS(String assegnazioneCTUMedicoINPS) {
		AssegnazioneCTUMedicoINPS = assegnazioneCTUMedicoINPS;
	}

	public String getPresenzaCTPINPSOperazioniPeritali() {
		return PresenzaCTPINPSOperazioniPeritali;
	}

	public void setPresenzaCTPINPSOperazioniPeritali(
			String presenzaCTPINPSOperazioniPeritali) {
		PresenzaCTPINPSOperazioniPeritali = presenzaCTPINPSOperazioniPeritali;
	}

	public Date getDataArrivoBozza() {
		return DataArrivoBozza;
	}

	public void setDataArrivoBozza(Date dataArrivoBozza) {
		DataArrivoBozza = dataArrivoBozza;
	}

	public Date getDataProtocolloBozza() {
		return DataProtocolloBozza;
	}

	public void setDataProtocolloBozza(Date dataProtocolloBozza) {
		DataProtocolloBozza = dataProtocolloBozza;
	}

	public String getProtocolloBozza() {
		return ProtocolloBozza;
	}

	public void setProtocolloBozza(String protocolloBozza) {
		ProtocolloBozza = protocolloBozza;
	}

	public String getParereSuBozzaCTU() {
		return ParereSuBozzaCTU;
	}

	public void setParereSuBozzaCTU(String parereSuBozzaCTU) {
		ParereSuBozzaCTU = parereSuBozzaCTU;
	}

	public String getOsservazioniSuBozza() {
		return OsservazioniSuBozza;
	}

	public void setOsservazioniSuBozza(String osservazioniSuBozza) {
		OsservazioniSuBozza = osservazioniSuBozza;
	}

	public Date getDataProtocolloCTUDefinitiva() {
		return DataProtocolloCTUDefinitiva;
	}

	public void setDataProtocolloCTUDefinitiva(Date dataProtocolloCTUDefinitiva) {
		DataProtocolloCTUDefinitiva = dataProtocolloCTUDefinitiva;
	}

	public String getProtocolloCTUDefinitiva() {
		return ProtocolloCTUDefinitiva;
	}

	public void setProtocolloCTUDefinitiva(String protocolloCTUDefinitiva) {
		ProtocolloCTUDefinitiva = protocolloCTUDefinitiva;
	}

	public Date getDataTermineDissenso() {
		return DataTermineDissenso;
	}

	public void setDataTermineDissenso(Date dataTermineDissenso) {
		DataTermineDissenso = dataTermineDissenso;
	}

	public String getParereDissensoAccettazione() {
		return ParereDissensoAccettazione;
	}

	public void setParereDissensoAccettazione(String parereDissensoAccettazione) {
		ParereDissensoAccettazione = parereDissensoAccettazione;
	}

	public String getOsservazioniSuParereDefinitivo() {
		return OsservazioniSuParereDefinitivo;
	}

	public void setOsservazioniSuParereDefinitivo(
			String osservazioniSuParereDefinitivo) {
		OsservazioniSuParereDefinitivo = osservazioniSuParereDefinitivo;
	}

	public Date getDataDepositoDecretoOmologa() {
		return DataDepositoDecretoOmologa;
	}

	public void setDataDepositoDecretoOmologa(Date dataDepositoDecretoOmologa) {
		DataDepositoDecretoOmologa = dataDepositoDecretoOmologa;
	}

	public Date getDataNotificaDecretoOmologa() {
		return DataNotificaDecretoOmologa;
	}

	public void setDataNotificaDecretoOmologa(Date dataNotificaDecretoOmologa) {
		DataNotificaDecretoOmologa = dataNotificaDecretoOmologa;
	}

	public Date getDataProtocolloDecretoOmologa() {
		return DataProtocolloDecretoOmologa;
	}

	public void setDataProtocolloDecretoOmologa(Date dataProtocolloDecretoOmologa) {
		DataProtocolloDecretoOmologa = dataProtocolloDecretoOmologa;
	}

	public long getCodiceEsitoCausa() {
		return CodiceEsitoCausa;
	}

	public void setCodiceEsitoCausa(long codiceEsitoCausa) {
		CodiceEsitoCausa = codiceEsitoCausa;
	}

	public String getEsitoCausa() {
		return EsitoCausa;
	}

	public void setEsitoCausa(String esitoCausa) {
		EsitoCausa = esitoCausa;
	}

	public BigDecimal getSpesePagate() {
		return SpesePagate;
	}

	public void setSpesePagate(BigDecimal spesePagate) {
		SpesePagate = spesePagate;
	}

	public Date getDataRevisioneSanitaria() {
		return DataRevisioneSanitaria;
	}

	public void setDataRevisioneSanitaria(Date dataRevisioneSanitaria) {
		DataRevisioneSanitaria = dataRevisioneSanitaria;
	}

	public Date getDataDefinizioneGiudizio() {
		return DataDefinizioneGiudizio;
	}

	public void setDataDefinizioneGiudizio(Date dataDefinizioneGiudizio) {
		DataDefinizioneGiudizio = dataDefinizioneGiudizio;
	}

	public long getCodicePagamentoSpeseLegaliInserite() {
		return CodicePagamentoSpeseLegaliInserite;
	}

	public void setCodicePagamentoSpeseLegaliInserite(
			long codicePagamentoSpeseLegaliInserite) {
		CodicePagamentoSpeseLegaliInserite = codicePagamentoSpeseLegaliInserite;
	}

	public String getPresenzaDecretoOmologa() {
		return PresenzaDecretoOmologa;
	}

	public void setPresenzaDecretoOmologa(String presenzaDecretoOmologa) {
		PresenzaDecretoOmologa = presenzaDecretoOmologa;
	}

	public Date getDataLiquidazionePrestazione() {
		return DataLiquidazionePrestazione;
	}

	public void setDataLiquidazionePrestazione(Date dataLiquidazionePrestazione) {
		DataLiquidazionePrestazione = dataLiquidazionePrestazione;
	}

	public String getCondannaPagamentoSpeseLegali() {
		return CondannaPagamentoSpeseLegali;
	}

	public void setCondannaPagamentoSpeseLegali(String condannaPagamentoSpeseLegali) {
		CondannaPagamentoSpeseLegali = condannaPagamentoSpeseLegali;
	}

	public String getPrecetto() {
		return Precetto;
	}

	public void setPrecetto(String precetto) {
		Precetto = precetto;
	}

	public String getPrecettoSpeseLegali() {
		return PrecettoSpeseLegali;
	}

	public void setPrecettoSpeseLegali(String precettoSpeseLegali) {
		PrecettoSpeseLegali = precettoSpeseLegali;
	}

	public String getPignoramento() {
		return Pignoramento;
	}

	public void setPignoramento(String pignoramento) {
		Pignoramento = pignoramento;
	}

	public String getPignoramentoSpeseLegali() {
		return PignoramentoSpeseLegali;
	}

	public void setPignoramentoSpeseLegali(String pignoramentoSpeseLegali) {
		PignoramentoSpeseLegali = pignoramentoSpeseLegali;
	}

	public Date getDataComunicazioneUffAmmPerEsecuzione() {
		return DataComunicazioneUffAmmPerEsecuzione;
	}

	public void setDataComunicazioneUffAmmPerEsecuzione(
			Date dataComunicazioneUffAmmPerEsecuzione) {
		DataComunicazioneUffAmmPerEsecuzione = dataComunicazioneUffAmmPerEsecuzione;
	}

	public Date getDataPresaInCaricoEsecuzione() {
		return DataPresaInCaricoEsecuzione;
	}

	public void setDataPresaInCaricoEsecuzione(Date dataPresaInCaricoEsecuzione) {
		DataPresaInCaricoEsecuzione = dataPresaInCaricoEsecuzione;
	}

	public BigDecimal getInteressiRivalutazioni() {
		return InteressiRivalutazioni;
	}

	public void setInteressiRivalutazioni(BigDecimal interessiRivalutazioni) {
		InteressiRivalutazioni = interessiRivalutazioni;
	}

	public BigDecimal getImportoPrestazioneEconomica() {
		return ImportoPrestazioneEconomica;
	}

	public void setImportoPrestazioneEconomica(
			BigDecimal importoPrestazioneEconomica) {
		ImportoPrestazioneEconomica = importoPrestazioneEconomica;
	}

	public Date getDataLiquidazioneCTU() {
		return DataLiquidazioneCTU;
	}

	public void setDataLiquidazioneCTU(Date dataLiquidazioneCTU) {
		DataLiquidazioneCTU = dataLiquidazioneCTU;
	}

	public BigDecimal getImportoSpeseCTUPagate() {
		return ImportoSpeseCTUPagate;
	}

	public void setImportoSpeseCTUPagate(BigDecimal importoSpeseCTUPagate) {
		ImportoSpeseCTUPagate = importoSpeseCTUPagate;
	}

	public Date getDataPagamentoSpeseLegali() {
		return DataPagamentoSpeseLegali;
	}

	public void setDataPagamentoSpeseLegali(Date dataPagamentoSpeseLegali) {
		DataPagamentoSpeseLegali = dataPagamentoSpeseLegali;
	}

	public Date getDataDepositoDissensoInps() {
		return DataDepositoDissensoInps;
	}

	public void setDataDepositoDissensoInps(Date dataDepositoDissensoInps) {
		DataDepositoDissensoInps = dataDepositoDissensoInps;
	}

	public Date getDataDepositoDissensoCNP() {
		return DataDepositoDissensoCNP;
	}

	public void setDataDepositoDissensoCNP(Date dataDepositoDissensoCNP) {
		DataDepositoDissensoCNP = dataDepositoDissensoCNP;
	}

	public long getIdPraticheSisco() {
		return idPraticheSisco;
	}

	public void setIdPraticheSisco(long idPraticheSisco) {
		this.idPraticheSisco = idPraticheSisco;
	}

		

}