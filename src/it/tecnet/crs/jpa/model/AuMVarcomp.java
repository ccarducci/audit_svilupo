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
@Table(name = "AU_M_VARCOMP")
public class AuMVarcomp implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "AU_M_VARCOMP_GENERATOR", sequenceName = "AU_M_VARCOMP_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AU_M_VARCOMP_GENERATOR")
	@Column(name = "ID_M_COMP")
	private long idMComp;

	@Column(name = "ID_M_NONCONF")
	private long idMNonConf;

	@Column(name = "DATA_INIZIO")
	private Date dataInizio;

	@Column(name = "DATA_FINE")
	private Date dataFine;

	@Column(name = "DESCRIZIONE")
	private String descrizione;

	@Column(name = "CODICE_VC")
	private String codicevc;

	@Column(name = "peso_vc")
	private Double pesoVc;

	public long getIdMComp() {
		return idMComp;
	}

	public void setIdMComp(long idMComp) {
		this.idMComp = idMComp;
	}

	public long getIdMNonConf() {
		return idMNonConf;
	}

	public void setIdMNonConf(long idMNonConf) {
		this.idMNonConf = idMNonConf;
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

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getCodicevc() {
		return codicevc;
	}

	public void setCodicevc(String codicevc) {
		this.codicevc = codicevc;
	}

	public Double getPesoVc() {
		return pesoVc;
	}

	public void setPesoVc(Double pesoVc) {
		this.pesoVc = pesoVc;
	}

}
