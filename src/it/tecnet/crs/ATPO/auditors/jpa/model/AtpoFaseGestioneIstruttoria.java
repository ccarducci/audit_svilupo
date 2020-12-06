package it.tecnet.crs.ATPO.auditors.jpa.model;

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
@Table(name = "ATPO_FASE_GESTIONE_ISTRUTTORIA")
@NamedQueries( { @NamedQuery(name = AtpoFaseGestioneIstruttoria.QUERY_FASE_GESTIONE_ISTRUTTORIA_BY_IDDASEDATI, query = "SELECT t FROM AtpoFaseGestioneIstruttoria t WHERE t.idFaseDati = :"
		+ AtpoFaseGestioneIstruttoria.QUERY_PARAM_IDFASEDATI) })
public class AtpoFaseGestioneIstruttoria {

	public static final String QUERY_FASE_GESTIONE_ISTRUTTORIA_BY_IDDASEDATI = "AtpoFaseGestioneIstruttoria.findAllByIdFaseDati";
	public static final String QUERY_PARAM_IDFASEDATI = "idFaseDati";

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "ATPO_FASE_GESTIONE_ISTRUTTORIA_GENERATOR", sequenceName = "G_ISTRUT_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ATPO_FASE_GESTIONE_ISTRUTTORIA_GENERATOR")
	@Column(name = "id_gestione_istruttoria")
	private Long idGestioneIstruttoria;

	@Column(name = "eccezioni_non_rilevabili")
	private String eccezioniNonRilevabili;

	@Column(name = "litispendenza")
	private String litispendenza;

	@Column(name = "decadenza")
	private String decadenza;
	
	@Column(name = "corrisp_ATP_dom_amm_inv")
	private String corrispAtpDomAmmInv;

	@Column(name = "verifica_dic_es_pag_spese")
	private String verificaDicEsPagSpese;

	@Column(name = "indeterminatezza_ogg_dom")
	private String indeterminatezzaOggDom;

	@Column(name = "carenza_interesse_ad_agire")
	private String carenzaInteresseAdAgire;

	@Column(name = "data_costit_giudizio")
	private Date dataCostitGiudizio;

	@Column(name = "cost_giud_telematica")
	private String costGiudTelematica;

	@Column(name = "data_prima_udienza")
	private Date dataPrimaUdienza;

	@Column(name = "intervallo_costGiud_udienza")
	private long intervalloCostGiudUdienza;

	@Column(name = "altre_ecc_processuali")
	private String altreEccProcessuali;

	@Column(name = "id_fase_dati")
	private long idFaseDati;

	@Column(name = "fase_pronta")
	private String fasePronta;
	
	public Long getIdGestioneIstruttoria() {
		return idGestioneIstruttoria;
	}

	public void setIdGestioneIstruttoria(Long idGestioneIstruttoria) {
		this.idGestioneIstruttoria = idGestioneIstruttoria;
	}

	public String getEccezioniNonRilevabili() {
		return eccezioniNonRilevabili;
	}

	public void setEccezioniNonRilevabili(String eccezioniNonRilevabili) {
		this.eccezioniNonRilevabili = eccezioniNonRilevabili;
	}

	public String getLitispendenza() {
		return litispendenza;
	}

	public void setLitispendenza(String litispendenza) {
		this.litispendenza = litispendenza;
	}

	public String getDecadenza() {
		return decadenza;
	}

	public void setDecadenza(String decadenza) {
		this.decadenza = decadenza;
	}

	public String getCorrispAtpDomAmmInv() {
		return corrispAtpDomAmmInv;
	}

	public void setCorrispAtpDomAmmInv(String corrispAtpDomAmmInv) {
		this.corrispAtpDomAmmInv = corrispAtpDomAmmInv;
	}

	public String getVerificaDicEsPagSpese() {
		return verificaDicEsPagSpese;
	}

	public void setVerificaDicEsPagSpese(String verificaDicEsPagSpese) {
		this.verificaDicEsPagSpese = verificaDicEsPagSpese;
	}

	public String getIndeterminatezzaOggDom() {
		return indeterminatezzaOggDom;
	}

	public void setIndeterminatezzaOggDom(String indeterminatezzaOggDom) {
		this.indeterminatezzaOggDom = indeterminatezzaOggDom;
	}

	public String getCarenzaInteresseAdAgire() {
		return carenzaInteresseAdAgire;
	}

	public void setCarenzaInteresseAdAgire(String carenzaInteresseAdAgire) {
		this.carenzaInteresseAdAgire = carenzaInteresseAdAgire;
	}

	public Date getDataCostitGiudizio() {
		return dataCostitGiudizio;
	}

	public void setDataCostitGiudizio(Date dataCostitGiudizio) {
		this.dataCostitGiudizio = dataCostitGiudizio;
	}

	public String getCostGiudTelematica() {
		return costGiudTelematica;
	}

	public void setCostGiudTelematica(String costGiudTelematica) {
		this.costGiudTelematica = costGiudTelematica;
	}

	public Date getDataPrimaUdienza() {
		return dataPrimaUdienza;
	}

	public void setDataPrimaUdienza(Date dataPrimaUdienza) {
		this.dataPrimaUdienza = dataPrimaUdienza;
	}

	public long getIntervalloCostGiudUdienza() {
		return intervalloCostGiudUdienza;
	}

	public void setIntervalloCostGiudUdienza(long intervalloCostGiudUdienza) {
		this.intervalloCostGiudUdienza = intervalloCostGiudUdienza;
	}

	public String getAltreEccProcessuali() {
		return altreEccProcessuali;
	}

	public void setAltreEccProcessuali(String altreEccProcessuali) {
		this.altreEccProcessuali = altreEccProcessuali;
	}

	public long getIdFaseDati() {
		return idFaseDati;
	}

	public void setIdFaseDati(long idFaseDati) {
		this.idFaseDati = idFaseDati;
	}

	public void setFasePronta(String fasePronta) {
		this.fasePronta = fasePronta;
	}

	public String getFasePronta() {
		return fasePronta;
	}

}
