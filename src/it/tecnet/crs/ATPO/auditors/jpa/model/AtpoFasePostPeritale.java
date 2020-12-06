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
@Table(name = "ATPO_FASE_POST_PERITALE")
@NamedQueries( { @NamedQuery(name = AtpoFasePostPeritale.QUERY_FAEPOSTPERITALE_BY_IDFASEDATI, query = "SELECT t FROM AtpoFasePostPeritale t WHERE t.idfaseDati = :"
		+ AtpoFasePostPeritale.QUERY_PARAM_IDFASEDATI) })
public class AtpoFasePostPeritale implements Serializable {

	public static final String QUERY_FAEPOSTPERITALE_BY_IDFASEDATI = "AtpoFasePostPeritale.findAllByIdFaseDati";
	public static final String QUERY_PARAM_IDFASEDATI = "idfaseDati";

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "ATPO_POST_PERITALE_GENERATOR", sequenceName = "ATPO_FASE_POST_PERITALE_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ATPO_POST_PERITALE_GENERATOR")
	@Column(name = "ID_POST_PERITALE")
	private long idPostPeritale;

	@Column(name = "DATA_DEPOSITO_DEC_OMOLOGA")
	private Date dataDepositoDecOmologa;

	@Column(name = "DATA_NOTIFICA_DEC_OMOLOGA")
	private Date dataNotificaDecOmologa;

	@Column(name = "DATA_PROT_DEC_OMOLOGA_NOTIF")
	private Date dataProtDecOmologaNotif;

	@Column(name = "INT_TEMP_NOTIF_OMG_PROT_OMG")
	private long intTempNotifOmgProtOmg;

	@Column(name = "COD_CHIUSURA_CORRETTO")
	private String codChiusuraCorretto;

	@Column(name = "COD_CHIUSURA_INSERITO")
	private String codChiusuraInserito;

	@Column(name = "SPESE_PAGATE")
	private Double spesePagate;

	@Column(name = "SPESE_DECR_OMOLOGA")
	private Double speseDecrOmologa;

	@Column(name = "CORRISP_DECR_OMG_E_CTU_DEF")
	private String corrispDecrOmgEctuDef;

	@Column(name = "COD_PAGAMENTO_SPESE_LEGALI")
	private String codPagamentoSpeseLegali;

	@Column(name = "COD_PAGAMENTO_SPESE_LEGALI_CORRETTO")
	private String codPagamentoSpeseLegaliCorretto;

	@Column(name = "REG_DATI_PRATICA")
	private String recDatiPratica;

	@Column(name = "DATA_TRASMISS_DECR_LPS")
	private Date dataTrasmissDecrLPS;

	@Column(name = "OMOLOGA_ALLEGATA")
	private String omologaAllegata;

	@Column(name = "INT_TEMP_NOTIF_DECR_OMG_A_DECR_LPS")
	private long intTempNotifDecrOmgAdecrLps;

	@Column(name = "ID_FASE_DATI")
	private long idfaseDati;

	@Column(name = "FASE_PRONTA")
	private String fasePronta;

	@Column(name = "DATA_DEPOSITO_DISSENSO")
	private Date dataDepDiss;
	
	@Column(name = "INT_TEMP_DEPOSITO_DISS_TERMINE_DISS")
	private long intTempDepDiss;
	
	@Column(name = "DATA_DEP_RIC_PRIMO_G")
	private Date dataDepRicPrimoG;
	
	@Column(name = "DATA_DEF_PRATICA")
	private Date dataDefPratica;
	
	@Column(name = "TIPO_DISSENSO")
	private String tipoDissenso;
	
	public long getIdPostPeritale() {
		return idPostPeritale;
	}

	@Column(name = "COM_DEP_DISS_UFF_LEGALE")
	private String comDepDissUffLegale;

	public void setIdPostPeritale(long idPostPeritale) {
		this.idPostPeritale = idPostPeritale;
	}

	public Date getDataDepositoDecOmologa() {
		return dataDepositoDecOmologa;
	}

	public void setDataDepositoDecOmologa(Date dataDepositoDecOmologa) {
		this.dataDepositoDecOmologa = dataDepositoDecOmologa;
	}

	public Date getDataNotificaDecOmologa() {
		return dataNotificaDecOmologa;
	}

	public void setDataNotificaDecOmologa(Date dataNotificaDecOmologa) {
		this.dataNotificaDecOmologa = dataNotificaDecOmologa;
	}

	public Date getDataProtDecOmologaNotif() {
		return dataProtDecOmologaNotif;
	}

	public void setDataProtDecOmologaNotif(Date dataProtDecOmologaNotif) {
		this.dataProtDecOmologaNotif = dataProtDecOmologaNotif;
	}

	public long getIntTempNotifOmgProtOmg() {
		return intTempNotifOmgProtOmg;
	}

	public void setIntTempNotifOmgProtOmg(long intTempNotifOmgProtOmg) {
		this.intTempNotifOmgProtOmg = intTempNotifOmgProtOmg;
	}

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

	public Double getSpesePagate() {
		return spesePagate;
	}

	public void setSpesePagate(Double spesePagate) {
		this.spesePagate = spesePagate;
	}

	public Double getSpeseDecrOmologa() {
		return speseDecrOmologa;
	}

	public void setSpeseDecrOmologa(Double speseDecrOmologa) {
		this.speseDecrOmologa = speseDecrOmologa;
	}

	public String getCorrispDecrOmgEctuDef() {
		return corrispDecrOmgEctuDef;
	}

	public void setCorrispDecrOmgEctuDef(String corrispDecrOmgEctuDef) {
		this.corrispDecrOmgEctuDef = corrispDecrOmgEctuDef;
	}

	public String getCodPagamentoSpeseLegali() {
		return codPagamentoSpeseLegali;
	}

	public void setCodPagamentoSpeseLegali(String codPagamentoSpeseLegali) {
		this.codPagamentoSpeseLegali = codPagamentoSpeseLegali;
	}

	public String getCodPagamentoSpeseLegaliCorretto() {
		return codPagamentoSpeseLegaliCorretto;
	}

	public void setCodPagamentoSpeseLegaliCorretto(
			String codPagamentoSpeseLegaliCorretto) {
		this.codPagamentoSpeseLegaliCorretto = codPagamentoSpeseLegaliCorretto;
	}

	public String getRecDatiPratica() {
		return recDatiPratica;
	}

	public void setRecDatiPratica(String recDatiPratica) {
		this.recDatiPratica = recDatiPratica;
	}

	public Date getDataTrasmissDecrLPS() {
		return dataTrasmissDecrLPS;
	}

	public void setDataTrasmissDecrLPS(Date dataTrasmissDecrLPS) {
		this.dataTrasmissDecrLPS = dataTrasmissDecrLPS;
	}

	public String getOmologaAllegata() {
		return omologaAllegata;
	}

	public void setOmologaAllegata(String omologaAllegata) {
		this.omologaAllegata = omologaAllegata;
	}

	public long getIntTempNotifDecrOmgAdecrLps() {
		return intTempNotifDecrOmgAdecrLps;
	}

	public void setIntTempNotifDecrOmgAdecrLps(long intTempNotifDecrOmgAdecrLps) {
		this.intTempNotifDecrOmgAdecrLps = intTempNotifDecrOmgAdecrLps;
	}

	public long getIdfaseDati() {
		return idfaseDati;
	}

	public void setIdfaseDati(long idfaseDati) {
		this.idfaseDati = idfaseDati;
	}

	public void setFasePronta(String fasePronta) {
		this.fasePronta = fasePronta;
	}

	public String getFasePronta() {
		return fasePronta;
	}

	public String getComDepDissUffLegale() {
		return comDepDissUffLegale;
	}

	public void setComDepDissUffLegale(String comDepDissUffLegale) {
		this.comDepDissUffLegale = comDepDissUffLegale;
	}

	public void setDataDepDiss(Date dataDepDiss) {
		this.dataDepDiss = dataDepDiss;
	}

	public Date getDataDepDiss() {
		return dataDepDiss;
	}

	public void setIntTempDepDiss(long intTempDepDiss) {
		this.intTempDepDiss = intTempDepDiss;
	}

	public long getIntTempDepDiss() {
		return intTempDepDiss;
	}

	public void setDataDepRicPrimoG(Date dataDepRicPrimoG) {
		this.dataDepRicPrimoG = dataDepRicPrimoG;
	}

	public Date getDataDepRicPrimoG() {
		return dataDepRicPrimoG;
	}

	public void setDataDefPratica(Date dataDefPratica) {
		this.dataDefPratica = dataDefPratica;
	}

	public Date getDataDefPratica() {
		return dataDefPratica;
	}

	public void setTipoDissenso(String tipoDissenso) {
		this.tipoDissenso = tipoDissenso;
	}

	public String getTipoDissenso() {
		return tipoDissenso;
	}

}
