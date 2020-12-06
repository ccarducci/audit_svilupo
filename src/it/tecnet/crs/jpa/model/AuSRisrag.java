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
@Table(name = "AU_S_RISRAG")
public class AuSRisrag implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "AU_S_RISRAG_GENERATOR", sequenceName = "ID_S_RISRAG_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AU_S_RISRAG_GENERATOR")
	@Column(name = "ID_S_RISRAG")
	private long idSRisrag;

	@Column(name = "ID_S_SESSIONE")
	private long idSSessione;

	@Column(name = "RAGGRUP_RISCHIO")
	private int raggrupRischio;

	@Column(name = "DATA_INIZIO")
	private Date dataInizio;

	@Column(name = "DATA_FINE")
	private Date dataFine;

	@Column(name = "NUM")
	private int num;

	@Column(name = "TOT_PERC")
	private Double totPerc;

	@Column(name = "PS_PERC")
	private Double psPerc;

	@Column(name = "IMPORTO")
	private Double importo;

	@Column(name = "ID_M_RISCHIO")
	private long idMRischio;

	public long getIdSRisrag() {
		return idSRisrag;
	}

	public void setIdSRisrag(long idSRisrag) {
		this.idSRisrag = idSRisrag;
	}

	public long getIdSSessione() {
		return idSSessione;
	}

	public void setIdSSessione(long idSSessione) {
		this.idSSessione = idSSessione;
	}

	public int getRaggrupRischio() {
		return raggrupRischio;
	}

	public void setRaggrupRischio(int raggrupRischio) {
		this.raggrupRischio = raggrupRischio;
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

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public Double getTotPerc() {
		return totPerc;
	}

	public void setTotPerc(Double totPerc) {
		this.totPerc = totPerc;
	}

	public Double getPsPerc() {
		return psPerc;
	}

	public void setPsPerc(Double psPerc) {
		this.psPerc = psPerc;
	}

	public Double getImporto() {
		return importo;
	}

	public void setImporto(Double importo) {
		this.importo = importo;
	}

	public long getIdMRischio() {
		return idMRischio;
	}

	public void setIdMRischio(long idMRischio) {
		this.idMRischio = idMRischio;
	}

}
