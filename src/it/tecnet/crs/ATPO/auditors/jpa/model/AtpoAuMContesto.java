package it.tecnet.crs.ATPO.auditors.jpa.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "AU_M_CONTESTO")
public class AtpoAuMContesto implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "AU_M_CONTESTO_GENERATOR", sequenceName = "AU_M_CONTESTO_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AU_M_CONTESTO_GENERATOR")
	@Column(name = "ID_M_CONTESTO")
	private Long idMContesto;
	
	@Column(name = "ID_SESSIONE")
	private Long idSessione;
	
	
	@Column(name = "FUNZIONARI")
	private String funzionari;

	@Column(name = "MEDICI_INPS")
	private String mediciInps;
	
	@Column(name = "APERTE")
	private String aperte;
	
	@Column(name = "GG_MEDI_APERTURA")
	private long ggMediApertura;
	
	@Column(name = "GG_MEDI_CHIUSURA")
	private long ggMediChiusura;
	
	@Column(name = "ABBANDONO_DOM")
	private String abbandonoDom;
	
	@Column(name = "ABBANDONO_REV")
	private String abbandonoRev;
	
	@Column(name = "ACQ_ERR_DOM")
	private String acqErrDom;
	
	@Column(name = "ACQ_ERR_REV")
	private String acqErrRev;
	
	@Column(name = "CESS_MAT_CONTENDERE_DOM")
	private String cessMatContendDom;
	
	@Column(name = "CESS_MAT_CONTENDERE_REV")
	private String cessMatContendRev;

	@Column(name = "DEF_AUT_ATP_DOM")
	private String defAutAtpDom;
	
	@Column(name = "DEF_AUT_ATP_REV")
	private String defAutAtpRev;
	
	@Column(name = "DISSENSO_DOM")
	private String dissensoDom;
	
	@Column(name = "DISSENSO_REV")
	private String dissensoRev;
	
	@Column(name = "ESTINTA_DOM")
	private String estintaDom;
	
	@Column(name = "ESTINTA_REV")
	private String estintaRev;
	
	@Column(name = "FAV_DOM")
	private String favDom;
	
	@Column(name = "FAV_REV")
	private String favRev;
	
	@Column(name = "INAMMISS_DOM")
	private String inammissDom;

	@Column(name = "INAMMISS_REV")
	private String inammissRev;
	
	@Column(name = "INCOMP_DOM")
	private String incompDom;
	
	@Column(name = "INCOMP_REV")
	private String incompRev;
	
	@Column(name = "PARZ_FAV_DOM")
	private String parzFavDom;
	
	@Column(name = "PARZ_FAV_REV")
	private String parzFavRev;
	
	@Column(name = "SFAV_DOM")
	private String sfavDom;
	
	@Column(name = "SFAV_REV")
	private String sfavRev;

	@Column(name = "TRASF_ALTRO_SETT_DOM")
	private String trasfAltroSettDom;
	
	@Column(name = "TRASF_ALTRO_SETT_REV")
	private String trasfAltroSettRev;
	
	@Column(name = "DISSENSO_INPS")
	private String dissensoInps;
	
	@Column(name = "CTU")
	private String ctu;
	
	@Column(name = "VISITE_PERITALI")
	private String visitePeritali;
	
	@Column(name = "PERC_CVP_SU_NR_CTU")
	private Double percCvpSuNrCtu;
	
	@Column(name = "PERC_VP_SU_NR_MEDICI")
	private Double percCvpSuNrMeidici;
	
	@Column(name = "PERC_PARERE_CONCORDE")
	private Double percParereConcorde;
	
	@Column(name = "PERIODO_RIF")
	private Double periodoRif;

	public Long getIdMContesto() {
		return idMContesto;
	}

	public void setIdMContesto(Long idMContesto) {
		this.idMContesto = idMContesto;
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

	public Long getIdSessione() {
		return idSessione;
	}

	public void setIdSessione(Long idSessione) {
		this.idSessione = idSessione;
	}
	
	

	
	
	
	
}





