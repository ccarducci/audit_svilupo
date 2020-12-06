package it.tecnet.crs.web.dto;

import java.math.BigDecimal;
import java.util.Date;

public class AuSSessioneDto {

	private long idSSessione;

	private Date dataInizio;

	private Date dataFine;

	private int numeroPratiche;

	private int numeroPraticheEsaminate;

	private int numeroPraticheND;

	private double minimo;

	private double massimo;

	private double media;

	private double devStandard;

	private double INCC;

	private String INCCDescrizione;

	private BigDecimal importoMaxRegMPP;

	private BigDecimal totPraticheNonNotif;

	private BigDecimal totPesiValAssoluto;

	private int IR;

	private String IRDescritt;

	private BigDecimal importoContribNonIncass;

	private BigDecimal importoIndebitSosp;

	private BigDecimal importoPrescritto;

	private BigDecimal importoSanzioniErr;

	private BigDecimal importoRegolarizzTotCamp;

	private BigDecimal importoSanzTotCamp;

	private int praticaConEventiDannosiMR;

	private int praticaConEventiDannosiRM;

	private int praticaConEventiDannosiEDU;

	private int praticaConRmEdu;

	private String statoPratiche;

	private String daAttenzionare;

	private String ok;

	private String statoEsameSessione;

	private Date dataAggiornamentoDatiSessione;

	private int numDissAmm;

	private int numDissSan;

	private double percnumDissAmm;

	private double percnumDissSan;

	public int getPraticaConEventiDannosiMR() {
		return praticaConEventiDannosiMR;
	}

	public void setPraticaConEventiDannosiMR(int praticaConEventiDannosiMR) {
		this.praticaConEventiDannosiMR = praticaConEventiDannosiMR;
	}

	public int getPraticaConEventiDannosiRM() {
		return praticaConEventiDannosiRM;
	}

	public void setPraticaConEventiDannosiRM(int praticaConEventiDannosiRM) {
		this.praticaConEventiDannosiRM = praticaConEventiDannosiRM;
	}

	public int getPraticaConEventiDannosiEDU() {
		return praticaConEventiDannosiEDU;
	}

	public void setPraticaConEventiDannosiEDU(int praticaConEventiDannosiEDU) {
		this.praticaConEventiDannosiEDU = praticaConEventiDannosiEDU;
	}

	public int getPraticaConRmEdu() {
		return praticaConRmEdu;
	}

	public void setPraticaConRmEdu(int praticaConRmEdu) {
		this.praticaConRmEdu = praticaConRmEdu;
	}

	public String getStatoPratiche() {
		return statoPratiche;
	}

	public void setStatoPratiche(String statoPratiche) {
		this.statoPratiche = statoPratiche;
	}

	public String getDaAttenzionare() {
		return daAttenzionare;
	}

	public void setDaAttenzionare(String daAttenzionare) {
		this.daAttenzionare = daAttenzionare;
	}

	public String getOk() {
		return ok;
	}

	public void setOk(String ok) {
		this.ok = ok;
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

	public int getNumeroPratiche() {
		return numeroPratiche;
	}

	public void setNumeroPratiche(int numeroPratiche) {
		this.numeroPratiche = numeroPratiche;
	}

	public int getNumeroPraticheND() {
		return numeroPraticheND;
	}

	public void setNumeroPraticheND(int numeroPraticheND) {
		this.numeroPraticheND = numeroPraticheND;
	}

	public BigDecimal getImportoContribNonIncass() {
		return importoContribNonIncass;
	}

	public void setImportoContribNonIncass(BigDecimal importoContribNonIncass) {
		this.importoContribNonIncass = importoContribNonIncass;
	}

	public BigDecimal getImportoIndebitSosp() {
		return importoIndebitSosp;
	}

	public void setImportoIndebitSosp(BigDecimal importoIndebitSosp) {
		this.importoIndebitSosp = importoIndebitSosp;
	}

	public BigDecimal getImportoPrescritto() {
		return importoPrescritto;
	}

	public void setImportoPrescritto(BigDecimal importoPrescritto) {
		this.importoPrescritto = importoPrescritto;
	}

	public BigDecimal getImportoSanzioniErr() {
		return importoSanzioniErr;
	}

	public void setImportoSanzioniErr(BigDecimal importoSanzioniErr) {
		this.importoSanzioniErr = importoSanzioniErr;
	}

	public BigDecimal getImportoRegolarizzTotCamp() {
		return importoRegolarizzTotCamp;
	}

	public void setImportoRegolarizzTotCamp(BigDecimal importoRegolarizzTotCamp) {
		this.importoRegolarizzTotCamp = importoRegolarizzTotCamp;
	}

	public BigDecimal getImportoSanzTotCamp() {
		return importoSanzTotCamp;
	}

	public void setImportoSanzTotCamp(BigDecimal importoSanzTotCamp) {
		this.importoSanzTotCamp = importoSanzTotCamp;
	}

	public int getNumeroPraticheEsaminate() {
		return numeroPraticheEsaminate;
	}

	public void setNumeroPraticheEsaminate(int numeroPraticheEsaminate) {
		this.numeroPraticheEsaminate = numeroPraticheEsaminate;
	}

	public double getMinimo() {
		return minimo;
	}

	public void setMinimo(double minimo) {
		this.minimo = minimo;
	}

	public double getMassimo() {
		return massimo;
	}

	public void setMassimo(double massimo) {
		this.massimo = massimo;
	}

	public double getMedia() {
		return media;
	}

	public void setMedia(double media) {
		this.media = media;
	}

	public double getDevStandard() {
		return devStandard;
	}

	public void setDevStandard(double devStandard) {
		this.devStandard = devStandard;
	}

	public double getINCC() {
		return INCC;
	}

	public void setINCC(double incc) {
		INCC = incc;
	}

	public String getINCCDescrizione() {
		return INCCDescrizione;
	}

	public void setINCCDescrizione(String descrizione) {
		INCCDescrizione = descrizione;
	}

	public BigDecimal getImportoMaxRegMPP() {
		return importoMaxRegMPP;
	}

	public void setImportoMaxRegMPP(BigDecimal importoMaxRegMPP) {
		this.importoMaxRegMPP = importoMaxRegMPP;
	}

	public BigDecimal getTotPraticheNonNotif() {
		return totPraticheNonNotif;
	}

	public void setTotPraticheNonNotif(BigDecimal totPraticheNonNotif) {
		this.totPraticheNonNotif = totPraticheNonNotif;
	}

	public BigDecimal getTotPesiValAssoluto() {
		return totPesiValAssoluto;
	}

	public void setTotPesiValAssoluto(BigDecimal totPesiValAssoluto) {
		this.totPesiValAssoluto = totPesiValAssoluto;
	}

	public int getIR() {
		return IR;
	}

	public void setIR(int ir) {
		IR = ir;
	}

	public String getIRDescritt() {
		return IRDescritt;
	}

	public void setIRDescritt(String descritt) {
		IRDescritt = descritt;
	}

	public void setStatoEsameSessione(String statoEsameSessione) {
		this.statoEsameSessione = statoEsameSessione;
	}

	public String getStatoEsameSessione() {
		return statoEsameSessione;
	}

	public void setIdSSessione(long idSSessione) {
		this.idSSessione = idSSessione;
	}

	public long getIdSSessione() {
		return idSSessione;
	}

	public void setDataAggiornamentoDatiSessione(
			Date dataAggiornamentoDatiSessione) {
		this.dataAggiornamentoDatiSessione = dataAggiornamentoDatiSessione;
	}

	public Date getDataAggiornamentoDatiSessione() {
		return dataAggiornamentoDatiSessione;
	}

	public int getNumDissAmm() {
		return numDissAmm;
	}

	public void setNumDissAmm(int numDissAmm) {
		this.numDissAmm = numDissAmm;
	}

	public int getNumDissSan() {
		return numDissSan;
	}

	public void setNumDissSan(int numDissSan) {
		this.numDissSan = numDissSan;
	}

	public double getPercnumDissAmm() {
		return percnumDissAmm;
	}

	public void setPercnumDissAmm(double percnumDissAmm) {
		this.percnumDissAmm = percnumDissAmm;
	}

	public double getPercnumDissSan() {
		return percnumDissSan;
	}

	public void setPercnumDissSan(double percnumDissSan) {
		this.percnumDissSan = percnumDissSan;
	}

}
