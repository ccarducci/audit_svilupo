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
import javax.persistence.Transient;

@Entity
@Table(name = "AU_S_VARCOMP")
public class AuSVarcomp implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "AU_S_VARCOMP_GENERATOR", sequenceName = "ID_S_VARCOMP_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AU_S_VARCOMP_GENERATOR")
	@Column(name = "ID_S_VARCOMP")
	private long idSVarcomp;

	@Column(name = "ID_S_NONCONF")
	private long idSNonconf;

	@Column(name = "ID_M_NONCONF")
	private long idMNonconf;

	@Column(name = "DATA_INIZIO")
	private Date dataInizio;

	@Column(name = "DATA_FINE")
	private Date dataFine;

	@Column(name = "NUM")
	private int num;

	@Column(name = "PERC_SU_PS")
	private Double percSuPs;

	@Column(name = "PERC_PESATA")
	private Double percPesata;

	@Column(name = "CRITICITA")
	private String criticita;

	@Column(name = "AZIONI_CORRETTIVE")
	private String azioniCorrettive;

	@Column(name = "ID_M_VARCOMP")
	private long idMVarcomp;

	@Transient
	private long idMNonConf;

	public long getIdSVarcomp() {
		return idSVarcomp;
	}

	public void setIdSVarcomp(long idSVarcomp) {
		this.idSVarcomp = idSVarcomp;
	}

	public long getIdSNonconf() {
		return idSNonconf;
	}

	public void setIdSNonconf(long idSNonconf) {
		this.idSNonconf = idSNonconf;
	}

	public long getIdMNonconf() {
		return idMNonconf;
	}

	public void setIdMNonconf(long idMNonconf) {
		this.idMNonconf = idMNonconf;
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

	public Double getPercSuPs() {
		return percSuPs;
	}

	public void setPercSuPs(Double percSuPs) {
		this.percSuPs = percSuPs;
	}

	public Double getPercPesata() {
		return percPesata;
	}

	public void setPercPesata(Double percPesata) {
		this.percPesata = percPesata;
	}

	public void setCriticita(String criticita) {
		this.criticita = criticita;
	}

	public String getCriticita() {
		return criticita;
	}

	public void setAzioniCorrettive(String azioniCorrettive) {
		this.azioniCorrettive = azioniCorrettive;
	}

	public String getAzioniCorrettive() {
		return azioniCorrettive;
	}

	public long getIdMVarcomp() {
		return idMVarcomp;
	}

	public void setIdMVarcomp(long idMVarcomp) {
		this.idMVarcomp = idMVarcomp;
	}

	public long getIdMNonConf() {
		return idMNonConf;
	}

	public void setIdMNonConf(long idMNonConf) {
		this.idMNonConf = idMNonConf;
	}

}
