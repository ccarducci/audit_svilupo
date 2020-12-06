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
@Table(name = "AU_S_RISCHIO")
public class AuSRischio implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "AU_S_RISCHIO_GENERATOR", sequenceName = "ID_S_RISCHIO_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AU_S_RISCHIO_GENERATOR")
	@Column(name = "ID_S_RISCHIO")
	private long idSRischio;

	@Column(name = "ID_S_SESSIONE")
	private long idSSessione;

	@Column(name = "ID_M_RISCHIO")
	private long idMRischio;

	@Column(name = "DATA_INIZIO")
	private Date dataInizio;

	@Column(name = "DATA_FINE")
	private Date dataFine;

	@Column(name = "PESO_RISCHIO")
	private Double pesoRischio;

	@Column(name = "SU_TOT_PS_PERC")
	private Double suTotPsPerc;

	@Column(name = "NUM")
	private int num;

	@Column(name = "SU_TOT_PERC")
	private Double suTotPerc;

	@Column(name = "SU_PS_PERC")
	private Double suPsPerc;

	@Column(name = "IMPORTO")
	private Double importo;

	@Column(name = "NUM_PS")
	private int numPs;

	public long getIdSRischio() {
		return idSRischio;
	}

	public void setIdSRischio(long idSRischio) {
		this.idSRischio = idSRischio;
	}

	public long getIdSSessione() {
		return idSSessione;
	}

	public void setIdSSessione(long idSSessione) {
		this.idSSessione = idSSessione;
	}

	public long getIdMRischio() {
		return idMRischio;
	}

	public void setIdMRischio(long idMRischio) {
		this.idMRischio = idMRischio;
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

	public Double getPesoRischio() {
		return pesoRischio;
	}

	public void setPesoRischio(Double pesoRischio) {
		this.pesoRischio = pesoRischio;
	}

	public Double getSuTotPsPerc() {
		return suTotPsPerc;
	}

	public void setSuTotPsPerc(Double suTotPsPerc) {
		this.suTotPsPerc = suTotPsPerc;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public Double getSuTotPerc() {
		return suTotPerc;
	}

	public void setSuTotPerc(Double suTotPerc) {
		this.suTotPerc = suTotPerc;
	}

	public Double getSuPsPerc() {
		return suPsPerc;
	}

	public void setSuPsPerc(Double suPsPerc) {
		this.suPsPerc = suPsPerc;
	}

	public Double getImporto() {
		return importo;
	}

	public void setImporto(Double importo) {
		this.importo = importo;
	}

	public int getNumPs() {
		return numPs;
	}

	public void setNumPs(int numPs) {
		this.numPs = numPs;
	}

}
