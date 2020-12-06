package it.tecnet.crs.jpa.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "AU_S_SESSIONE")
public class AuSSessione implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "AU_S_SESSIONI_GENERATOR", sequenceName = "ID_S_SESSIONI_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AU_S_SESSIONI_GENERATOR")
	@Column(name = "ID_S_SESSIONE")
	private long idSSessione;

	@Column(name = "ID_SESSIONE")
	private long idSessione;

	@Column(name = "ID_SEDE")
	private long idSede;

	@Column(name = "DATA_INIZIO")
	private Date DataInizio;

	@Column(name = "DATA_FINE")
	private Date DataFine;

	@Column(name = "NR_PRATICHE")
	private Integer nrPratiche;

	@Column(name = "NR_PRATICHE_ESAMINATE")
	private Integer nrPraticheEsaminate;

	@Column(name = "NR_PRATICHE_ND")
	private Integer nrPraticheNd;

	@Column(name = "MINIMO")
	private Double minimo;

	@Column(name = "MASSIMO")
	private Double massimo;

	@Column(name = "MEDIA")
	private Double media;

	@Column(name = "DEV_STANDARD")
	private Double devStandard;

	@Column(name = "INCC")
	private Double incc;

	@Column(name = "INCC_DESCRIZIONE", length = 100)
	private String inccDescrizione;

	@Column(name = "MPP_IMPORTO_MAX_REG")
	private Double mppImportoMaxReg;

	@Column(name = "TOT_PRATICHE_NON_NOTIF")
	private Double totPraticheNonNotIf;

	@Column(name = "TOT_PESI_VALORE_ASS")
	private Double totPesiValoreAss;

	@Column(name = "IR")
	private Integer ir;

	@Column(name = "DESCRITTIVO")
	private Integer descrittivo;

	@Column(name = "IMP_CONTR_NON_INCASSATA")
	private Double impContrNonIncassata;

	@Column(name = "IMP_INDEB_SOSPESO")
	private Double impIndebSospeso;

	@Column(name = "IMP_PRESCRITTO")
	private Double impPrescritto;

	@Column(name = "IMP_SANZIONI_ERRATE")
	private Double impSanzioniErrate;

	@Column(name = "IMP_REG_TOT_CAMPIONE")
	private Double impRegTotCampione;

	@Column(name = "IMP_SANZIONI_TOT_CAMPIONE")
	private Double impSanzioniTotCampione;

	@Column(name = "STATO_PRATICHE")
	private String statoPratiche;

	@Column(name = "DATA_AGG_DATI_SESS")
	private Date dataAggDatiSess;

	@Column(name = "STATO_ESAME_SESSIONE")
	private String statoEsameSessione;

	@Column(name = "NUM_DISSENSI_AMM")
	private int numDissensiAmm;

	@Column(name = "NUM_DISSENSI_SAN")
	private int numDissensiSan;

	@Column(name = "PERC_NUM_DISSENSI_AMM")
	private Double percNumDissAmm;

	@Column(name = "PERC_NUM_DISSENSI_SAN")
	private Double percNumDissSan;
	
	@Column(name = "NOTA")
	private String nota;

	public long getIdSSessione() {
		return idSSessione;
	}

	public void setIdSSessione(long idSSessione) {
		this.idSSessione = idSSessione;
	}

	public long getIdSessione() {
		return idSessione;
	}

	public void setIdSessione(long idSessione) {
		this.idSessione = idSessione;
	}

	public long getIdSede() {
		return idSede;
	}

	public void setIdSede(long idSede) {
		this.idSede = idSede;
	}

	public Date getDataInizio() {
		return DataInizio;
	}

	public void setDataInizio(Date dataInizio) {
		DataInizio = dataInizio;
	}

	public Date getDataFine() {
		return DataFine;
	}

	public void setDataFine(Date dataFine) {
		DataFine = dataFine;
	}

	public Integer getNrPratiche() {
		return nrPratiche;
	}

	public void setNrPratiche(Integer nrPratiche) {
		this.nrPratiche = nrPratiche;
	}

	public Integer getNrPraticheEsaminate() {
		return nrPraticheEsaminate;
	}

	public void setNrPraticheEsaminate(Integer nrPraticheEsaminate) {
		this.nrPraticheEsaminate = nrPraticheEsaminate;
	}

	public Integer getNrPraticheNd() {
		return nrPraticheNd;
	}

	public void setNrPraticheNd(Integer nrPraticheNd) {
		this.nrPraticheNd = nrPraticheNd;
	}

	public Double getMinimo() {
		return minimo;
	}

	public void setMinimo(Double minimo) {
		this.minimo = minimo;
	}

	public Double getMassimo() {
		return massimo;
	}

	public void setMassimo(Double massimo) {
		this.massimo = massimo;
	}

	public Double getMedia() {
		return media;
	}

	public void setMedia(Double media) {
		this.media = media;
	}

	public Double getDevStandard() {
		return devStandard;
	}

	public void setDevStandard(Double devStandard) {
		this.devStandard = devStandard;
	}

	public Double getIncc() {
		return incc;
	}

	public void setIncc(Double incc) {
		this.incc = incc;
	}

	public String getInccDescrizione() {
		return inccDescrizione;
	}

	public void setInccDescrizione(String inccDescrizione) {
		this.inccDescrizione = inccDescrizione;
	}

	public Double getMppImportoMaxReg() {
		return mppImportoMaxReg;
	}

	public void setMppImportoMaxReg(Double mppImportoMaxReg) {
		this.mppImportoMaxReg = mppImportoMaxReg;
	}

	public Double getTotPraticheNonNotIf() {
		return totPraticheNonNotIf;
	}

	public void setTotPraticheNonNotIf(Double totPraticheNonNotIf) {
		this.totPraticheNonNotIf = totPraticheNonNotIf;
	}

	public Double getTotPesiValoreAss() {
		return totPesiValoreAss;
	}

	public void setTotPesiValoreAss(Double totPesiValoreAss) {
		this.totPesiValoreAss = totPesiValoreAss;
	}

	public Integer getIr() {
		return ir;
	}

	public void setIr(Integer ir) {
		this.ir = ir;
	}

	public Integer getDescrittivo() {
		return descrittivo;
	}

	public void setDescrittivo(Integer descrittivo) {
		this.descrittivo = descrittivo;
	}

	public Double getImpContrNonIncassata() {
		return impContrNonIncassata;
	}

	public void setImpContrNonIncassata(Double impContrNonIncassata) {
		this.impContrNonIncassata = impContrNonIncassata;
	}

	public Double getImpIndebSospeso() {
		return impIndebSospeso;
	}

	public void setImpIndebSospeso(Double impIndebSospeso) {
		this.impIndebSospeso = impIndebSospeso;
	}

	public Double getImpPrescritto() {
		return impPrescritto;
	}

	public void setImpPrescritto(Double impPrescritto) {
		this.impPrescritto = impPrescritto;
	}

	public Double getImpSanzioniErrate() {
		return impSanzioniErrate;
	}

	public void setImpSanzioniErrate(Double impSanzioniErrate) {
		this.impSanzioniErrate = impSanzioniErrate;
	}

	public Double getImpRegTotCampione() {
		return impRegTotCampione;
	}

	public void setImpRegTotCampione(Double impRegTotCampione) {
		this.impRegTotCampione = impRegTotCampione;
	}

	public Double getImpSanzioniTotCampione() {
		return impSanzioniTotCampione;
	}

	public void setImpSanzioniTotCampione(Double impSanzioniTotCampione) {
		this.impSanzioniTotCampione = impSanzioniTotCampione;
	}

	public void setStatoPratiche(String statoPratiche) {
		this.statoPratiche = statoPratiche;
	}

	public String getStatoPratiche() {
		return statoPratiche;
	}

	public void setDataAggDatiSess(Date dataAggDatiSess) {
		this.dataAggDatiSess = dataAggDatiSess;
	}

	public Date getDataAggDatiSess() {
		return dataAggDatiSess;
	}

	public String getStatoEsameSessione() {
		return statoEsameSessione;
	}

	public void setStatoEsameSessione(String statoEsameSessione) {
		this.statoEsameSessione = statoEsameSessione;
	}

	public int getNumDissensiAmm() {
		return numDissensiAmm;
	}

	public void setNumDissensiAmm(int numDissensiAmm) {
		this.numDissensiAmm = numDissensiAmm;
	}

	public int getNumDissensiSan() {
		return numDissensiSan;
	}

	public void setNumDissensiSan(int numDissensiSan) {
		this.numDissensiSan = numDissensiSan;
	}

	public Double getPercNumDissAmm() {
		return percNumDissAmm;
	}

	public void setPercNumDissAmm(Double percNumDissAmm) {
		this.percNumDissAmm = percNumDissAmm;
	}

	public Double getPercNumDissSan() {
		return percNumDissSan;
	}

	public void setPercNumDissSan(Double percNumDissSan) {
		this.percNumDissSan = percNumDissSan;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}

	public String getNota() {
		return nota;
	}

}
