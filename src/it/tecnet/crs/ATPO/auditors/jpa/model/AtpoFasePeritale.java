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
@Table(name = "ATPO_FASE_PERITALE")
@NamedQueries( { @NamedQuery(name = AtpoFasePeritale.QUERY_FASEPERITALE_BY_IDFASEDATI, query = "SELECT t FROM AtpoFasePeritale t WHERE t.idFaseDati = :"
		+ AtpoFasePeritale.QUERY_PARAM_IDFASEDATI) })
public class AtpoFasePeritale implements Serializable {

	public static final String QUERY_FASEPERITALE_BY_IDFASEDATI = "AtpoFasePeritale.findAllByIdFaseDati";
	public static final String QUERY_PARAM_IDFASEDATI = "idFaseDati";

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "ATPO_PERITALE_GENERATOR", sequenceName = "ATPO_PERITALE_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ATPO_PERITALE_GENERATOR")
	@Column(name = "id_peritale")
	private long idPeritale;

	@Column(name = "data_com_i_op_per_CTU")
	private Date dataComOpPerCTU;

	@Column(name = "reg_info_op_peritali")
	private String recInfoOpPeritali;

	@Column(name = "assegn_CTU_a_med_INPS")
	private String assCTUMedicoInps;

	@Column(name = "ctp_INPS_op_peritali")
	private String ctpINPSopPeritali;

	@Column(name = "data_arr_bozza")
	private Date dataArrBozza;

	@Column(name = "data_prot_bozza")
	private Date dataProtBozza;

	@Column(name = "CTU_bozza_img_atti")
	private String ctuBozzaImgAtti;

	@Column(name = "parere_bozza_CTU")
	private String parereBozzaCtu;

	@Column(name = "oss_bozza")
	private String ossBozza;

	@Column(name = "data_com_dep_CTU_def_canc")
	private Date dataComDepCTUDefCanc;

	@Column(name = "data_prot_CTU_def")
	private Date dataProtCTUDef;

	@Column(name = "CTU_def_img_atti")
	private String ctuDefImgAtti;

	@Column(name = "int_temp_com_DEP_CTU_to_CTU_def")
	private long intTempComDepCTUtoCTUdef;

	@Column(name = "data_term_DISS_SISCO")
	private Date dataTermDissSisco;

	@Column(name = "data_term_DISS_da_decreto")
	private Date dataTermDissDecr;

	@Column(name = "parere_diss_accett")
	private String parereDissAccetfascitazione;

	@Column(name = "oss_par_def")
	private String ossParDef;

	@Column(name = "id_fase_dati")
	private long idFaseDati;

	@Column(name = "fase_pronta")
	private String fasePronta;

	@Column(name = "presenza_medico_inps_doc")
	private String presMedicoInpsDoc;

	public long getIdPeritale() {
		return idPeritale;
	}

	public void setIdPeritale(long idPeritale) {
		this.idPeritale = idPeritale;
	}

	public Date getDataComOpPerCTU() {
		return dataComOpPerCTU;
	}

	public void setDataComOpPerCTU(Date dataComOpPerCTU) {
		this.dataComOpPerCTU = dataComOpPerCTU;
	}

	public String getRecInfoOpPeritali() {
		return recInfoOpPeritali;
	}

	public void setRecInfoOpPeritali(String recInfoOpPeritali) {
		this.recInfoOpPeritali = recInfoOpPeritali;
	}

	public String getAssCTUMedicoInps() {
		return assCTUMedicoInps;
	}

	public void setAssCTUMedicoInps(String assCTUMedicoInps) {
		this.assCTUMedicoInps = assCTUMedicoInps;
	}

	public String getCtpINPSopPeritali() {
		return ctpINPSopPeritali;
	}

	public void setCtpINPSopPeritali(String ctpINPSopPeritali) {
		this.ctpINPSopPeritali = ctpINPSopPeritali;
	}

	public Date getDataArrBozza() {
		return dataArrBozza;
	}

	public void setDataArrBozza(Date dataArrBozza) {
		this.dataArrBozza = dataArrBozza;
	}

	public Date getDataProtBozza() {
		return dataProtBozza;
	}

	public void setDataProtBozza(Date dataProtBozza) {
		this.dataProtBozza = dataProtBozza;
	}

	public String getCtuBozzaImgAtti() {
		return ctuBozzaImgAtti;
	}

	public void setCtuBozzaImgAtti(String ctuBozzaImgAtti) {
		this.ctuBozzaImgAtti = ctuBozzaImgAtti;
	}

	public String getParereBozzaCtu() {
		return parereBozzaCtu;
	}

	public void setParereBozzaCtu(String parereBozzaCtu) {
		this.parereBozzaCtu = parereBozzaCtu;
	}

	public String getOssBozza() {
		return ossBozza;
	}

	public void setOssBozza(String ossBozza) {
		this.ossBozza = ossBozza;
	}

	public Date getDataComDepCTUDefCanc() {
		return dataComDepCTUDefCanc;
	}

	public void setDataComDepCTUDefCanc(Date dataComDepCTUDefCanc) {
		this.dataComDepCTUDefCanc = dataComDepCTUDefCanc;
	}

	public Date getDataProtCTUDef() {
		return dataProtCTUDef;
	}

	public void setDataProtCTUDef(Date dataProtCTUDef) {
		this.dataProtCTUDef = dataProtCTUDef;
	}

	public String getCtuDefImgAtti() {
		return ctuDefImgAtti;
	}

	public void setCtuDefImgAtti(String ctuDefImgAtti) {
		this.ctuDefImgAtti = ctuDefImgAtti;
	}

	public long getIntTempComDepCTUtoCTUdef() {
		return intTempComDepCTUtoCTUdef;
	}

	public void setIntTempComDepCTUtoCTUdef(long intTempComDepCTUtoCTUdef) {
		this.intTempComDepCTUtoCTUdef = intTempComDepCTUtoCTUdef;
	}

	public Date getDataTermDissSisco() {
		return dataTermDissSisco;
	}

	public void setDataTermDissSisco(Date dataTermDissSisco) {
		this.dataTermDissSisco = dataTermDissSisco;
	}

	public Date getDataTermDissDecr() {
		return dataTermDissDecr;
	}

	public void setDataTermDissDecr(Date dataTermDissDecr) {
		this.dataTermDissDecr = dataTermDissDecr;
	}

	public String getParereDissAccetfascitazione() {
		return parereDissAccetfascitazione;
	}

	public void setParereDissAccetfascitazione(
			String parereDissAccetfascitazione) {
		this.parereDissAccetfascitazione = parereDissAccetfascitazione;
	}

	public String getOssParDef() {
		return ossParDef;
	}

	public void setOssParDef(String ossParDef) {
		this.ossParDef = ossParDef;
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

	public String getPresMedicoInpsDoc() {
		return presMedicoInpsDoc;
	}

	public void setPresMedicoInpsDoc(String presMedicoInpsDoc) {
		this.presMedicoInpsDoc = presMedicoInpsDoc;
	}

}
