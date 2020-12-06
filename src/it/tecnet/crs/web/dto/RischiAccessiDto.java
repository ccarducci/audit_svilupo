package it.tecnet.crs.web.dto;

import java.math.BigDecimal;

public class RischiAccessiDto {
	
	//tabella
	private long idRischioM;
	private long idRischioS;
	
	private String descrizione;
	private String codice;
	
	private Integer num; 
	private BigDecimal suTotPerc;
	private BigDecimal suPsPerc;
	private BigDecimal ir;
	private String descrittivo;
	private BigDecimal ICGPonderato;
	
	//stato espr rischio
	private long idSRisepr;
	private BigDecimal percSuTot;
	private BigDecimal percSuPS;
	private BigDecimal totImporto;
	
	//dettagli
	private BigDecimal importo;
	private BigDecimal suProcTot;
	private BigDecimal impContribNonIncassata;
	private BigDecimal impIndebitSospeso;
	private BigDecimal impPrescirtto;
	private BigDecimal impMPP;
	private int numPS;
	private int irPS;
	private String descrittivoPS;
	private String deviazioneStandard;
	private String daAttenzionare;
	private String ok;
	private String rischiConPs;
	private int numMedioErrori;
	private int manifRischio;
	private int rm;
	private int edu;
	
	//questionario
	private int numDomande;
	private int percCopertura;
	private BigDecimal icg;
	private BigDecimal pesoRischio;
	private BigDecimal icgPonderato;
	
	
	public int getNumDomande() {
		return numDomande;
	}
	public void setNumDomande(int numDomande) {
		this.numDomande = numDomande;
	}
	public int getPercCopertura() {
		return percCopertura;
	}
	public void setPercCopertura(int percCopertura) {
		this.percCopertura = percCopertura;
	}
	public BigDecimal getIcg() {
		return icg;
	}
	public void setIcg(BigDecimal icg) {
		this.icg = icg;
	}
	public BigDecimal getPesoRischio() {
		return pesoRischio;
	}
	public void setPesoRischio(BigDecimal pesoRischio) {
		this.pesoRischio = pesoRischio;
	}
	public BigDecimal getIcgPonderato() {
		return icgPonderato;
	}
	public void setIcgPonderato(BigDecimal icgPonderato) {
		this.icgPonderato = icgPonderato;
	}
	public long getIdRischioM() {
		return idRischioM;
	}
	public void setIdRischioM(long idRischioM) {
		this.idRischioM = idRischioM;
	}
	public long getIdRischioS() {
		return idRischioS;
	}
	public void setIdRischioS(long idRischioS) {
		this.idRischioS = idRischioS;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getCodice() {
		return codice;
	}
	public void setCodice(String codice) {
		this.codice = codice;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public BigDecimal getSuTotPerc() {
		return suTotPerc;
	}
	public void setSuTotPerc(BigDecimal suTotPerc) {
		this.suTotPerc = suTotPerc;
	}
	public BigDecimal getSuPsPerc() {
		return suPsPerc;
	}
	public void setSuPsPerc(BigDecimal suPsPerc) {
		this.suPsPerc = suPsPerc;
	}
	public BigDecimal getIr() {
		return ir;
	}
	public void setIr(BigDecimal ir) {
		this.ir = ir;
	}
	public String getDescrittivo() {
		return descrittivo;
	}
	public void setDescrittivo(String descrittivo) {
		this.descrittivo = descrittivo;
	}
	public BigDecimal getICGPonderato() {
		return ICGPonderato;
	}
	public void setICGPonderato(BigDecimal ponderato) {
		ICGPonderato = ponderato;
	}
	public BigDecimal getSuProcTot() {
		return suProcTot;
	}
	public void setSuProcTot(BigDecimal suProcTot) {
		this.suProcTot = suProcTot;
	}
	public BigDecimal getImpContribNonIncassata() {
		return impContribNonIncassata;
	}
	public void setImpContribNonIncassata(BigDecimal impContribNonIncassata) {
		this.impContribNonIncassata = impContribNonIncassata;
	}
	public BigDecimal getImpIndebitSospeso() {
		return impIndebitSospeso;
	}
	public void setImpIndebitSospeso(BigDecimal impIndebitSospeso) {
		this.impIndebitSospeso = impIndebitSospeso;
	}
	public BigDecimal getImpPrescirtto() {
		return impPrescirtto;
	}
	public void setImpPrescirtto(BigDecimal impPrescirtto) {
		this.impPrescirtto = impPrescirtto;
	}
	public BigDecimal getImpMPP() {
		return impMPP;
	}
	public void setImpMPP(BigDecimal impMPP) {
		this.impMPP = impMPP;
	}
	public int getNumPS() {
		return numPS;
	}
	public void setNumPS(int numPS) {
		this.numPS = numPS;
	}
	public int getIrPS() {
		return irPS;
	}
	public void setIrPS(int irPS) {
		this.irPS = irPS;
	}
	public String getDescrittivoPS() {
		return descrittivoPS;
	}
	public void setDescrittivoPS(String descrittivoPS) {
		this.descrittivoPS = descrittivoPS;
	}
	public String getDeviazioneStandard() {
		return deviazioneStandard;
	}
	public void setDeviazioneStandard(String deviazioneStandard) {
		this.deviazioneStandard = deviazioneStandard;
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
	public String getRischiConPs() {
		return rischiConPs;
	}
	public void setRischiConPs(String rischiConPs) {
		this.rischiConPs = rischiConPs;
	}
	public int getNumMedioErrori() {
		return numMedioErrori;
	}
	public void setNumMedioErrori(int numMedioErrori) {
		this.numMedioErrori = numMedioErrori;
	}
	public int getManifRischio() {
		return manifRischio;
	}
	public void setManifRischio(int manifRischio) {
		this.manifRischio = manifRischio;
	}
	public int getRm() {
		return rm;
	}
	public void setRm(int rm) {
		this.rm = rm;
	}
	public int getEdu() {
		return edu;
	}
	public void setEdu(int edu) {
		this.edu = edu;
	}
	public void setImporto(BigDecimal importo) {
		this.importo = importo;
	}
	public BigDecimal getImporto() {
		return importo;
	}
	public void setPercSuTot(BigDecimal percSuTot) {
		this.percSuTot = percSuTot;
	}
	public BigDecimal getPercSuTot() {
		return percSuTot;
	}
	public void setPercSuPS(BigDecimal percSuPS) {
		this.percSuPS = percSuPS;
	}
	public BigDecimal getPercSuPS() {
		return percSuPS;
	}
	public void setTotImporto(BigDecimal totImporto) {
		this.totImporto = totImporto;
	}
	public BigDecimal getTotImporto() {
		return totImporto;
	}
	public void setIdSRisepr(long idSRisepr) {
		this.idSRisepr = idSRisepr;
	}
	public long getIdSRisepr() {
		return idSRisepr;
	}
	

}
