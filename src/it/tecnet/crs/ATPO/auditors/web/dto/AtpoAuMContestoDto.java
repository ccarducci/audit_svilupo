package it.tecnet.crs.ATPO.auditors.web.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;


public class AtpoAuMContestoDto {

	private Long idMContesto;


	private Long idAudit;


	private Long idSede;


	private String funzionari;


	private String mediciInps;


	private String aperte;


	private long ggMediApertura;


	private long ggMediChiusura;


	private String abbandonoDom;


	private String abbandonoRev;


	private String acqErrDom;


	private String acqErrRev;


	private String cessMatContendDom;


	private String cessMatContendRev;


	private String defAutAtpDom;


	private String defAutAtpRev;


	private String dissensoDom;


	private String dissensoRev;


	private String estintaDom;


	private String estintaRev;


	private String favDom;


	private String favRev;


	private String inammissDom;


	private String inammissRev;


	private String incompDom;


	private String incompRev;


	private String parzFavDom;


	private String parzFavRev;


	private String sfavDom;


	private String sfavRev;


	private String trasfAltroSettDom;


	private String trasfAltroSettRev;


	private String dissensoInps;


	private String ctu;


	private String visitePeritali;


	private Double percCvpSuNrCtu;


	private Double percCvpSuNrMeidici;


	private Double percParereConcorde;


	private Double periodoRif;

	private int numeroPraticheDef;
	
	private Date dataInizioOsservazione;
	private Date dataFineOsservazione;
	public Long getIdMContesto() {
		return idMContesto;
	}
	public void setIdMContesto(Long idMContesto) {
		this.idMContesto = idMContesto;
	}
	public Long getIdAudit() {
		return idAudit;
	}
	public void setIdAudit(Long idAudit) {
		this.idAudit = idAudit;
	}
	public Long getIdSede() {
		return idSede;
	}
	public void setIdSede(Long idSede) {
		this.idSede = idSede;
	}
	public String getFunzionari() {
		return funzionari;
	}
	public void setFunzionari(String funzionari) {
		this.funzionari = funzionari;
	}
	public String getMediciInps() {
		return mediciInps;
	}
	public void setMediciInps(String mediciInps) {
		this.mediciInps = mediciInps;
	}
	public String getAperte() {
		return aperte;
	}
	public void setAperte(String aperte) {
		this.aperte = aperte;
	}
	public long getGgMediApertura() {
		return ggMediApertura;
	}
	public void setGgMediApertura(long ggMediApertura) {
		this.ggMediApertura = ggMediApertura;
	}
	public long getGgMediChiusura() {
		return ggMediChiusura;
	}
	public void setGgMediChiusura(long ggMediChiusura) {
		this.ggMediChiusura = ggMediChiusura;
	}
	public String getAbbandonoDom() {
		return abbandonoDom;
	}
	public void setAbbandonoDom(String abbandonoDom) {
		this.abbandonoDom = abbandonoDom;
	}
	public String getAbbandonoRev() {
		return abbandonoRev;
	}
	public void setAbbandonoRev(String abbandonoRev) {
		this.abbandonoRev = abbandonoRev;
	}
	public String getAcqErrDom() {
		return acqErrDom;
	}
	public void setAcqErrDom(String acqErrDom) {
		this.acqErrDom = acqErrDom;
	}
	public String getAcqErrRev() {
		return acqErrRev;
	}
	public void setAcqErrRev(String acqErrRev) {
		this.acqErrRev = acqErrRev;
	}
	public String getCessMatContendDom() {
		return cessMatContendDom;
	}
	public void setCessMatContendDom(String cessMatContendDom) {
		this.cessMatContendDom = cessMatContendDom;
	}
	public String getCessMatContendRev() {
		return cessMatContendRev;
	}
	public void setCessMatContendRev(String cessMatContendRev) {
		this.cessMatContendRev = cessMatContendRev;
	}
	public String getDefAutAtpDom() {
		return defAutAtpDom;
	}
	public void setDefAutAtpDom(String defAutAtpDom) {
		this.defAutAtpDom = defAutAtpDom;
	}
	public String getDefAutAtpRev() {
		return defAutAtpRev;
	}
	public void setDefAutAtpRev(String defAutAtpRev) {
		this.defAutAtpRev = defAutAtpRev;
	}
	public String getDissensoDom() {
		return dissensoDom;
	}
	public void setDissensoDom(String dissensoDom) {
		this.dissensoDom = dissensoDom;
	}
	public String getDissensoRev() {
		return dissensoRev;
	}
	public void setDissensoRev(String dissensoRev) {
		this.dissensoRev = dissensoRev;
	}
	public String getEstintaDom() {
		return estintaDom;
	}
	public void setEstintaDom(String estintaDom) {
		this.estintaDom = estintaDom;
	}
	public String getEstintaRev() {
		return estintaRev;
	}
	public void setEstintaRev(String estintaRev) {
		this.estintaRev = estintaRev;
	}
	public String getFavDom() {
		return favDom;
	}
	public void setFavDom(String favDom) {
		this.favDom = favDom;
	}
	public String getFavRev() {
		return favRev;
	}
	public void setFavRev(String favRev) {
		this.favRev = favRev;
	}
	public String getInammissDom() {
		return inammissDom;
	}
	public void setInammissDom(String inammissDom) {
		this.inammissDom = inammissDom;
	}
	public String getInammissRev() {
		return inammissRev;
	}
	public void setInammissRev(String inammissRev) {
		this.inammissRev = inammissRev;
	}
	public String getIncompDom() {
		return incompDom;
	}
	public void setIncompDom(String incompDom) {
		this.incompDom = incompDom;
	}
	public String getIncompRev() {
		return incompRev;
	}
	public void setIncompRev(String incompRev) {
		this.incompRev = incompRev;
	}
	public String getParzFavDom() {
		return parzFavDom;
	}
	public void setParzFavDom(String parzFavDom) {
		this.parzFavDom = parzFavDom;
	}
	public String getParzFavRev() {
		return parzFavRev;
	}
	public void setParzFavRev(String parzFavRev) {
		this.parzFavRev = parzFavRev;
	}
	public String getSfavDom() {
		return sfavDom;
	}
	public void setSfavDom(String sfavDom) {
		this.sfavDom = sfavDom;
	}
	public String getSfavRev() {
		return sfavRev;
	}
	public void setSfavRev(String sfavRev) {
		this.sfavRev = sfavRev;
	}
	public String getTrasfAltroSettDom() {
		return trasfAltroSettDom;
	}
	public void setTrasfAltroSettDom(String trasfAltroSettDom) {
		this.trasfAltroSettDom = trasfAltroSettDom;
	}
	public String getTrasfAltroSettRev() {
		return trasfAltroSettRev;
	}
	public void setTrasfAltroSettRev(String trasfAltroSettRev) {
		this.trasfAltroSettRev = trasfAltroSettRev;
	}
	public String getDissensoInps() {
		return dissensoInps;
	}
	public void setDissensoInps(String dissensoInps) {
		this.dissensoInps = dissensoInps;
	}
	public String getCtu() {
		return ctu;
	}
	public void setCtu(String ctu) {
		this.ctu = ctu;
	}
	public String getVisitePeritali() {
		return visitePeritali;
	}
	public void setVisitePeritali(String visitePeritali) {
		this.visitePeritali = visitePeritali;
	}
	public Double getPercCvpSuNrCtu() {
		
		return percCvpSuNrCtu;
	}
	public void setPercCvpSuNrCtu(Double percCvpSuNrCtu) {
		this.percCvpSuNrCtu = percCvpSuNrCtu;
	}
	public Double getPercCvpSuNrMeidici() {
		return percCvpSuNrMeidici;
		
	}
	public void setPercCvpSuNrMeidici(Double percCvpSuNrMeidici) {
		this.percCvpSuNrMeidici = percCvpSuNrMeidici;
	}
	public Double getPercParereConcorde() {
		return percParereConcorde;
	}
	public void setPercParereConcorde(Double percParereConcorde) {
		this.percParereConcorde = percParereConcorde;
	}
	public Double getPeriodoRif() {
		return periodoRif;
	}
	public void setPeriodoRif(Double periodoRif) {
		this.periodoRif = periodoRif;
	}
	public int getNumeroPraticheDef() {
		return numeroPraticheDef;
	}
	public void setNumeroPraticheDef(int numeroPraticheDef) {
		this.numeroPraticheDef = numeroPraticheDef;
	}
	public Date getDataInizioOsservazione() {
		return dataInizioOsservazione;
	}
	public void setDataInizioOsservazione(Date dataInizioOsservazione) {
		this.dataInizioOsservazione = dataInizioOsservazione;
	}
	public Date getDataFineOsservazione() {
		return dataFineOsservazione;
	}
	public void setDataFineOsservazione(Date dataFineOsservazione) {
		this.dataFineOsservazione = dataFineOsservazione;
	}
	
	
	


}
