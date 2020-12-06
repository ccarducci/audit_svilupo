package it.tecnet.crs.ATPO.auditors.jpa.model;

import java.io.Serializable;
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
@Table(name = "ATPO_FASE_ESECUZIONE_PROVVEDIMENTI")
@NamedQueries( { @NamedQuery(name = AtpoFaseEsecuzioneProvvedimenti.QUERY_FASEESECPROVV_BY_IDFASEDATI, query = "SELECT t FROM AtpoFaseEsecuzioneProvvedimenti t WHERE t.idFaseDati = :"
		+ AtpoFaseEsecuzioneProvvedimenti.QUERY_PARAM_IDFASEDATI) })
public class AtpoFaseEsecuzioneProvvedimenti implements Serializable {

	public static final String QUERY_FASEESECPROVV_BY_IDFASEDATI = "AtpoFaseEsecuzioneProvvedimenti.findAllByIdFaseDati";
	public static final String QUERY_PARAM_IDFASEDATI = "idFaseDati";

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "ATPO_FASE_ESECUZIONE_PROVVEDIMENTI_GENERATOR", sequenceName = "ATPO_FASE_ESECUZIONE_PROVVEDIMENTI_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ATPO_FASE_ESECUZIONE_PROVVEDIMENTI_GENERATOR")
	@Column(name = "ID_ESECUZIONE_PROVVEDIMENTI")
	private long idEsecuzioneProvvedimenti;

	@Column(name = "PRES_DECR_OMG_NEL_FASC")
	private String presDecrOmgFasc;

	@Column(name = "DATA_DREC_LIQ_CTU")
	private Date dataDecrLiqCtu;

	@Column(name = "DATA_PRESA_IN_CARICO_DECR_OMG_LPS")
	private Date dataPresaInCaricoDecrOmgLps;

	@Column(name = "DATA_LIQ_PREST_LPS")
	private Date dataLiqPrestLps;

	@Column(name = "IMPORTO_MENSILE_RATA")
	private Double importoRataMensile;

	@Column(name = "DATA_REC_DATI_LIQ")
	private Date recDatiLiq;

	@Column(name = "GG_DA_NOTIF_DECR_OMG_A_LIQ_PREST")
	private long ggNotifDecrOmgLiqPres;

	@Column(name = "GG_DA_TRASM_DECR_LPS_A_DECR_OMG_DA_LPS")
	private long ggTrasmDecrLpsDecrOmglps;

	@Column(name = "INTERESSI_LEGALI_PAGATI")
	private Double interessiLegaliPagati;

	@Column(name = "INTERESSI_LEGALI_DOVUTI")
	private Double interessiLegaliDovuti;

	@Column(name = "DATA_DEC_CALCOLO_INT_LEGALI")
	private Date dataDecCalcoloIntLegali;

	@Column(name = "DATA_DEC_PREST_INSERITA")
	private Date dataDecPrestInserita;

	@Column(name = "DATA_CORR_DEC_PRESTAZIONE")
	private Date dataCorrDecPrestazione;

	@Column(name = "PRESTAZIONE_CORRISP")
	private String prestCorrisp;

	@Column(name = "IMPORTO_RATA_DOVUTA")
	private Double importoRataDovuta;

	@Column(name = "CONDANNA_A_PAGAMENTO_CTU_ATPO")
	private String condannaPagCtuAtpo;

	@Column(name = "DATA_FATTURA")
	private Date dataFattura;

	@Column(name = "DATA_LIQ_CTU_ATPO")
	private Date dataLiqCtuAtpo;

	@Column(name = "INT_TEMP_DA_FATT_ELETTR_A_PAG_CTU_ATPO")
	private long intTempFattElettrpagCtuAtpo;

	@Column(name = "ANTICIPATE_SPESE_CTU")
	private String antSpeseCtu;

	@Column(name = "IMPORTO_SPESE_CTU_PAGATE")
	private Double impSpeseCtuPagate;

	@Column(name = "IMPORTO_SPESE_CTU_DOVUTE")
	private Double impSpeseCtuDovute;

	@Column(name = "DATA_LETT_INV_PAG_SPESE_LEGAL")
	private Date datalettInvPagSpeseLegali;

	@Column(name = "DATA_LETTERA_RECUPERO_SPESE_CTU")
	private Date dataLetteraRecuperoSpeseCtu;

	@Column(name = "CONDANNA_PAG_SPESE_LEGALI")
	private String condannaPagSpeseLegali;

	@Column(name = "SOGG_RICH_PAGAMENTO")
	private String soggRichPagamento;

	@Column(name = "DATA_ARRIVO_NOTULA")
	private Date dataArriviNotula;

	@Column(name = "DATA_PAG_SPESE_LEGALI_AVV_CPARTE")
	private Date dataPagSpseLegaliAvvCparte;

	@Column(name = "INT_T_DEP_DECR_OMG_A_PAG_SPESE_LEGALI_CPARTE")
	private long intTdepDecromgPagSpeseL;

	@Column(name = "PRES_DECR_SENT_MANC_PAG_PREST")
	private String presDecrSentMancPagPrest;

	@Column(name = "COSTO_GIUDIZIO_MANC_PAG_PREST")
	private Double costoGiudizioMancPagPrest;

	@Column(name = "ID_FASE_DATI")
	private long idFaseDati;

	@Column(name = "CONDANNA_PAG_CTU_1G")
	private String condannaPagCtu1g;

	@Column(name = "VER_PAG_CTU_EFF")
	private String verPagCtuEff;

	@Column(name = "NO_PRECETTO", nullable=true)
	private String noPrecetto;

	@Column(name = "DATA_COM_PRE_SL", nullable=true)
	private Date dataComPreSl;

	@Column(name = "COSTO_PRE_SL", nullable=true)
	private Double costoPreSl;

	@Column(name = "DATA_COM_PRE_SCTU", nullable=true)
	private Date dataComPreSctu;

	@Column(name = "COSTO_PRE_SCTU", nullable=true)
	private Double costoPreSctu;

	@Column(name = "DATA_PRESTAZIONE", nullable=true)
	private Date dataPrestazione;

	@Column(name = "DATA_COM_PRE_PREST", nullable=true)
	private Date dataComPrePrest;

	@Column(name = "COSTO_PRE_PREST", nullable=true)
	private Double costoPrePrest;

	@Column(name = "NO_PIGNORAMENTO", nullable=true)
	private String noPignoramento;

	@Column(name = "COSTO_PIGN_SL", nullable=true)
	private Double costoPignSl;

	@Column(name = "COSTO_PIGN_SCTU", nullable=true)
	private Double costoPignSctu;

	@Column(name = "COSTO_PIGN_PREST", nullable=true)
	private Double costoPignPrest;

	@Column(name = "DATA_PIGNORAMENTO_PRESTAZIONE", nullable=true)
	private Date dataPignoramentoPres;

	@Column(name = "FASE_PRONTA")
	private String fasePronta;
	
	@Column(name = "INT_T_DEP_DECR_OMG_DATA_REC_LEQUIDAZIONE", nullable=true)
	private Long intDepDecrOmgDRecLiquid;
	
	@Column(name = "DATA_LIMITE_CALCOLO_IMPATTO", nullable=true)
	private Date dataLimiteCalcImpatto;
	
	// -----------------------------------------------------------
	// CAMPI SEZIONE PRECETTO ------------------------------------

	@Column(name = "SPESE_LEGALI_FLAG_PREC")
	private String speseLegaliFlagPrec;

	@Column(name = "DATA_SPESE_LEGALI_PREC")
	private Date dataSpeseLegaliPrec;

	@Column(name = "SPESE_CTU_FLAG_PREC")
	private String speseCtuFlagPrec;

	@Column(name = "DATA_SPESE_CTU_PREC")
	private Date dataSpeseCtuPrec;

	@Column(name = "PRESTAZIONE_FLAG_PREC")
	private String prestazioneFlagPrec;

	// ---------------------------------------------------------------
	// CAMPI SEZIONE PIGNORAMENTO ------------------------------------
	@Column(name = "SPESE_LEGALI_FLAG_PIGN")
	private String speseLegaliFlagPign;

	@Column(name = "DATA_SPESE_LEGALI_PIGN")
	private Date dataSpeseLegaliPign;

	@Column(name = "SPESE_CTU_FLAG_PIGN")
	private String speseCtuFlagPign;

	@Column(name = "DATA_SPESE_CTU_PIGN")
	private Date dataSpeseCtuPign;

	@Column(name = "PRESTAZIONE_FLAG_PIGN")
	private String prestazioneFlagPign;

	public long getIdEsecuzioneProvvedimenti() {
		return idEsecuzioneProvvedimenti;
	}

	public void setIdEsecuzioneProvvedimenti(long idEsecuzioneProvvedimenti) {
		this.idEsecuzioneProvvedimenti = idEsecuzioneProvvedimenti;
	}

	public String getPresDecrOmgFasc() {
		return presDecrOmgFasc;
	}

	public void setPresDecrOmgFasc(String presDecrOmgFasc) {
		this.presDecrOmgFasc = presDecrOmgFasc;
	}

	public Date getDataDecrLiqCtu() {
		return dataDecrLiqCtu;
	}

	public void setDataDecrLiqCtu(Date dataDecrLiqCtu) {
		this.dataDecrLiqCtu = dataDecrLiqCtu;
	}

	public Date getDataPresaInCaricoDecrOmgLps() {
		return dataPresaInCaricoDecrOmgLps;
	}

	public void setDataPresaInCaricoDecrOmgLps(Date dataPresaInCaricoDecrOmgLps) {
		this.dataPresaInCaricoDecrOmgLps = dataPresaInCaricoDecrOmgLps;
	}

	public Date getDataLiqPrestLps() {
		return dataLiqPrestLps;
	}

	public void setDataLiqPrestLps(Date dataLiqPrestLps) {
		this.dataLiqPrestLps = dataLiqPrestLps;
	}

	public Double getImportoRataMensile() {
		return importoRataMensile;
	}

	public void setImportoRataMensile(Double importoRataMensile) {
		this.importoRataMensile = importoRataMensile;
	}

	public Date getRecDatiLiq() {
		return recDatiLiq;
	}

	public void setRecDatiLiq(Date recDatiLiq) {
		this.recDatiLiq = recDatiLiq;
	}

	public long getGgNotifDecrOmgLiqPres() {
		return ggNotifDecrOmgLiqPres;
	}

	public void setGgNotifDecrOmgLiqPres(long ggNotifDecrOmgLiqPres) {
		this.ggNotifDecrOmgLiqPres = ggNotifDecrOmgLiqPres;
	}

	public long getGgTrasmDecrLpsDecrOmglps() {
		return ggTrasmDecrLpsDecrOmglps;
	}

	public void setGgTrasmDecrLpsDecrOmglps(long ggTrasmDecrLpsDecrOmglps) {
		this.ggTrasmDecrLpsDecrOmglps = ggTrasmDecrLpsDecrOmglps;
	}

	public Double getInteressiLegaliPagati() {
		return interessiLegaliPagati;
	}

	public void setInteressiLegaliPagati(Double interessiLegaliPagati) {
		this.interessiLegaliPagati = interessiLegaliPagati;
	}

	public Double getInteressiLegaliDovuti() {
		return interessiLegaliDovuti;
	}

	public void setInteressiLegaliDovuti(Double interessiLegaliDovuti) {
		this.interessiLegaliDovuti = interessiLegaliDovuti;
	}

	public Date getDataDecCalcoloIntLegali() {
		return dataDecCalcoloIntLegali;
	}

	public void setDataDecCalcoloIntLegali(Date dataDecCalcoloIntLegali) {
		this.dataDecCalcoloIntLegali = dataDecCalcoloIntLegali;
	}

	public Date getDataDecPrestInserita() {
		return dataDecPrestInserita;
	}

	public void setDataDecPrestInserita(Date dataDecPrestInserita) {
		this.dataDecPrestInserita = dataDecPrestInserita;
	}

	public Date getDataCorrDecPrestazione() {
		return dataCorrDecPrestazione;
	}

	public void setDataCorrDecPrestazione(Date dataCorrDecPrestazione) {
		this.dataCorrDecPrestazione = dataCorrDecPrestazione;
	}

	public String getPrestCorrisp() {
		return prestCorrisp;
	}

	public void setPrestCorrisp(String prestCorrisp) {
		this.prestCorrisp = prestCorrisp;
	}

	public Double getImportoRataDovuta() {
		return importoRataDovuta;
	}

	public void setImportoRataDovuta(Double importoRataDovuta) {
		this.importoRataDovuta = importoRataDovuta;
	}

	public String getCondannaPagCtuAtpo() {
		return condannaPagCtuAtpo;
	}

	public void setCondannaPagCtuAtpo(String condannaPagCtuAtpo) {
		this.condannaPagCtuAtpo = condannaPagCtuAtpo;
	}
 
	public Date getDataFattura() {
		return dataFattura;
	}

	public void setDataFattura(Date dataFattura) {
		this.dataFattura = dataFattura;
	}

	public Date getDataLiqCtuAtpo() {
		return dataLiqCtuAtpo;
	}

	public void setDataLiqCtuAtpo(Date dataLiqCtuAtpo) {
		this.dataLiqCtuAtpo = dataLiqCtuAtpo;
	}

	public long getIntTempFattElettrpagCtuAtpo() {
		return intTempFattElettrpagCtuAtpo;
	}

	public void setIntTempFattElettrpagCtuAtpo(long intTempFattElettrpagCtuAtpo) {
		this.intTempFattElettrpagCtuAtpo = intTempFattElettrpagCtuAtpo;
	}

	public String getAntSpeseCtu() {
		return antSpeseCtu;
	}

	public void setAntSpeseCtu(String antSpeseCtu) {
		this.antSpeseCtu = antSpeseCtu;
	}

	public Double getImpSpeseCtuPagate() {
		return impSpeseCtuPagate;
	}

	public void setImpSpeseCtuPagate(Double impSpeseCtuPagate) {
		this.impSpeseCtuPagate = impSpeseCtuPagate;
	}

	public Double getImpSpeseCtuDovute() {
		return impSpeseCtuDovute;
	}

	public void setImpSpeseCtuDovute(Double impSpeseCtuDovute) {
		this.impSpeseCtuDovute = impSpeseCtuDovute;
	}

	public Date getDatalettInvPagSpeseLegali() {
		return datalettInvPagSpeseLegali;
	}

	public void setDatalettInvPagSpeseLegali(Date datalettInvPagSpeseLegali) {
		this.datalettInvPagSpeseLegali = datalettInvPagSpeseLegali;
	}

	public Date getDataLetteraRecuperoSpeseCtu() {
		return dataLetteraRecuperoSpeseCtu;
	}

	public void setDataLetteraRecuperoSpeseCtu(Date dataLetteraRecuperoSpeseCtu) {
		this.dataLetteraRecuperoSpeseCtu = dataLetteraRecuperoSpeseCtu;
	}

	public String getCondannaPagSpeseLegali() {
		return condannaPagSpeseLegali;
	}

	public void setCondannaPagSpeseLegali(String condannaPagSpeseLegali) {
		this.condannaPagSpeseLegali = condannaPagSpeseLegali;
	}

	public String getSoggRichPagamento() {
		return soggRichPagamento;
	}

	public void setSoggRichPagamento(String soggRichPagamento) {
		this.soggRichPagamento = soggRichPagamento;
	}

	public Date getDataArriviNotula() {
		return dataArriviNotula;
	}

	public void setDataArriviNotula(Date dataArriviNotula) {
		this.dataArriviNotula = dataArriviNotula;
	}

	public Date getDataPagSpseLegaliAvvCparte() {
		return dataPagSpseLegaliAvvCparte;
	}

	public void setDataPagSpseLegaliAvvCparte(Date dataPagSpseLegaliAvvCparte) {
		this.dataPagSpseLegaliAvvCparte = dataPagSpseLegaliAvvCparte;
	}

	public long getIntTdepDecromgPagSpeseL() {
		return intTdepDecromgPagSpeseL;
	}

	public void setIntTdepDecromgPagSpeseL(long intTdepDecromgPagSpeseL) {
		this.intTdepDecromgPagSpeseL = intTdepDecromgPagSpeseL;
	}

	public String getPresDecrSentMancPagPrest() {
		return presDecrSentMancPagPrest;
	}

	public void setPresDecrSentMancPagPrest(String presDecrSentMancPagPrest) {
		this.presDecrSentMancPagPrest = presDecrSentMancPagPrest;
	}

	public Double getCostoGiudizioMancPagPrest() {
		return costoGiudizioMancPagPrest;
	}

	public void setCostoGiudizioMancPagPrest(
			Double costoGiudizioMancPagPrest) {
		this.costoGiudizioMancPagPrest = costoGiudizioMancPagPrest;
	}

	public long getIdFaseDati() {
		return idFaseDati;
	}

	public void setIdFaseDati(long idFaseDati) {
		this.idFaseDati = idFaseDati;
	}

	public String getCondannaPagCtu1g() {
		return condannaPagCtu1g;
	}

	public void setCondannaPagCtu1g(String condannaPagCtu1g) {
		this.condannaPagCtu1g = condannaPagCtu1g;
	}

	public String getVerPagCtuEff() {
		return verPagCtuEff;
	}

	public void setVerPagCtuEff(String verPagCtuEff) {
		this.verPagCtuEff = verPagCtuEff;
	}

	public String getNoPrecetto() {
		return noPrecetto;
	}

	public void setNoPrecetto(String noPrecetto) {
		this.noPrecetto = noPrecetto;
	}

	public Date getDataComPreSl() {
		return dataComPreSl;
	}

	public void setDataComPreSl(Date dataComPreSl) {
		this.dataComPreSl = dataComPreSl;
	}

	public Double getCostoPreSl() {
		return costoPreSl;
	}

	public void setCostoPreSl(Double costoPreSl) {
		this.costoPreSl = costoPreSl;
	}

	public Date getDataComPreSctu() {
		return dataComPreSctu;
	}

	public void setDataComPreSctu(Date dataComPreSctu) {
		this.dataComPreSctu = dataComPreSctu;
	}

	public Double getCostoPreSctu() {
		return costoPreSctu;
	}

	public void setCostoPreSctu(Double costoPreSctu) {
		this.costoPreSctu = costoPreSctu;
	}

	public Date getDataPrestazione() {
		return dataPrestazione;
	}

	public void setDataPrestazione(Date dataPrestazione) {
		this.dataPrestazione = dataPrestazione;
	}

	public Date getDataComPrePrest() {
		return dataComPrePrest;
	}

	public void setDataComPrePrest(Date dataComPrePrest) {
		this.dataComPrePrest = dataComPrePrest;
	}

	public Double getCostoPrePrest() {
		return costoPrePrest;
	}

	public void setCostoPrePrest(Double costoPrePrest) {
		this.costoPrePrest = costoPrePrest;
	}

	public String getNoPignoramento() {
		return noPignoramento;
	}

	public void setNoPignoramento(String noPignoramento) {
		this.noPignoramento = noPignoramento;
	}

	public Double getCostoPignSl() {
		return costoPignSl;
	}

	public void setCostoPignSl(Double costoPignSl) {
		this.costoPignSl = costoPignSl;
	}

	public Double getCostoPignSctu() {
		return costoPignSctu;
	}

	public void setCostoPignSctu(Double costoPignSctu) {
		this.costoPignSctu = costoPignSctu;
	}

	public Double getCostoPignPrest() {
		return costoPignPrest;
	}

	public void setCostoPignPrest(Double costoPignPrest) {
		this.costoPignPrest = costoPignPrest;
	}

	public Date getDataPignoramentoPres() {
		return dataPignoramentoPres;
	}

	public void setDataPignoramentoPres(Date dataPignoramentoPres) {
		this.dataPignoramentoPres = dataPignoramentoPres;
	}

	public String getSpeseLegaliFlagPrec() {
		return speseLegaliFlagPrec;
	}

	public void setSpeseLegaliFlagPrec(String speseLegaliFlagPrec) {
		this.speseLegaliFlagPrec = speseLegaliFlagPrec;
	}

	public Date getDataSpeseLegaliPrec() {
		return dataSpeseLegaliPrec;
	}

	public void setDataSpeseLegaliPrec(Date dataSpeseLegaliPrec) {
		this.dataSpeseLegaliPrec = dataSpeseLegaliPrec;
	}



	public Date getDataSpeseCtuPrec() {
		return dataSpeseCtuPrec;
	}

	public void setDataSpeseCtuPrec(Date dataSpeseCtuPrec) {
		this.dataSpeseCtuPrec = dataSpeseCtuPrec;
	}

	public String getPrestazioneFlagPrec() {
		return prestazioneFlagPrec;
	}

	public void setPrestazioneFlagPrec(String prestazioneFlagPrec) {
		this.prestazioneFlagPrec = prestazioneFlagPrec;
	}

	public String getSpeseLegaliFlagPign() {
		return speseLegaliFlagPign;
	}

	public void setSpeseLegaliFlagPign(String speseLegaliFlagPign) {
		this.speseLegaliFlagPign = speseLegaliFlagPign;
	}

	public Date getDataSpeseLegaliPign() {
		return dataSpeseLegaliPign;
	}

	public void setDataSpeseLegaliPign(Date dataSpeseLegaliPign) {
		this.dataSpeseLegaliPign = dataSpeseLegaliPign;
	}

	public String getSpeseCtuFlagPign() {
		return speseCtuFlagPign;
	}

	public void setSpeseCtuFlagPign(String speseVtuFlagPign) {
		this.speseCtuFlagPign = speseVtuFlagPign;
	}

	public Date getDataSpeseCtuPign() {
		return dataSpeseCtuPign;
	}

	public void setDataSpeseCtuPign(Date dataSpeseCtuPign) {
		this.dataSpeseCtuPign = dataSpeseCtuPign;
	}

	public String getPrestazioneFlagPign() {
		return prestazioneFlagPign;
	}

	public void setPrestazioneFlagPign(String prestazioneFlagPign) {
		this.prestazioneFlagPign = prestazioneFlagPign;
	}

	public void setFasePronta(String fasePronta) {
		this.fasePronta = fasePronta;
	}

	public String getFasePronta() {
		return fasePronta;
	}

	public void setIntDepDecrOmgDRecLiquid(Long intDepDecrOmgDRecLiquid) {
		this.intDepDecrOmgDRecLiquid = intDepDecrOmgDRecLiquid;
	}

	public Long getIntDepDecrOmgDRecLiquid() {
		return intDepDecrOmgDRecLiquid;
	}

	public void setSpeseCtuFlagPrec(String speseCtuFlagPrec) {
		this.speseCtuFlagPrec = speseCtuFlagPrec;
	}

	public String getSpeseCtuFlagPrec() {
		return speseCtuFlagPrec;
	}

	public void setDataLimiteCalcImpatto(Date dataLimiteCalcImpatto) {
		this.dataLimiteCalcImpatto = dataLimiteCalcImpatto;
	}

	public Date getDataLimiteCalcImpatto() {
		return dataLimiteCalcImpatto;
	}

}
