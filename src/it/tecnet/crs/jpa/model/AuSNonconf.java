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
@Table(name = "AU_S_NONCONF")
public class AuSNonconf implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "AU_S_NONCONF_GENERATOR", sequenceName = "ID_S_NONCONF_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AU_S_NONCONF_GENERATOR")
	@Column(name = "ID_S_NONCONF")
	private long idSNonconf;

	@Column(name = "ID_M_NONCONF")
	private long idMNonconf;

	@Column(name = "ID_S_SESSIONE")
	private long idSSessione;

	@Column(name = "DATA_INIZIO")
	private Date dataInizio;

	@Column(name = "DATA_FINE")
	private Date dataFine;

	@Column(name = "CODICE")
	private String codice;

	@Column(name = "PESO_NONCONF")
	private Double pesoNonconf;

	@Column(name = "NUM")
	private Integer num;

	@Column(name = "VALORE_INCC")
	private Double valoreIncc;

	@Column(name = "VALORE_PESATO")
	private Double valorePesato;

	@Column(name = "VALORE_PRATICA_NONSOGGETTA")
	private String valorePraticaNonsoggetta;

	@Column(name = "VALORE_MAX_ATTIVITA")
	private String valoreMaxAttivita;

	@Column(name = "VALORE_MIN_ATTIVITA")
	private String valoreMinAttivita;

	@Column(name = "MINIMO")
	private int minimo;

	@Column(name = "MASSIMO")
	private int massimo;

	@Column(name = "VALORE_PESATO_FASE")
	private double valorePesatoFase;

	
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

	public long getIdSSessione() {
		return idSSessione;
	}

	public void setIdSSessione(long idSSessione) {
		this.idSSessione = idSSessione;
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

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public Double getPesoNonconf() {
		return pesoNonconf;
	}

	public void setPesoNonconf(Double pesoNonconf) {
		this.pesoNonconf = pesoNonconf;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Double getValoreIncc() {
		return valoreIncc;
	}

	public void setValoreIncc(Double valoreIncc) {
		this.valoreIncc = valoreIncc;
	}

	public Double getValorePesato() {
		return valorePesato;
	}

	public void setValorePesato(Double valorePesato) {
		this.valorePesato = valorePesato;
	}

	public String getValorePraticaNonsoggetta() {
		return valorePraticaNonsoggetta;
	}

	public void setValorePraticaNonsoggetta(String valorePraticaNonsoggetta) {
		this.valorePraticaNonsoggetta = valorePraticaNonsoggetta;
	}

	public String getValoreMaxAttivita() {
		return valoreMaxAttivita;
	}

	public void setValoreMaxAttivita(String valoreMaxAttivita) {
		this.valoreMaxAttivita = valoreMaxAttivita;
	}

	public String getValoreMinAttivita() {
		return valoreMinAttivita;
	}

	public void setValoreMinAttivita(String valoreMinAttivita) {
		this.valoreMinAttivita = valoreMinAttivita;
	}

	public int getMinimo() {
		return minimo;
	}

	public void setMinimo(int minimo) {
		this.minimo = minimo;
	}

	public int getMassimo() {
		return massimo;
	}

	public void setMassimo(int massimo) {
		this.massimo = massimo;
	}

	public double getValorePesatoFase() {
		return valorePesatoFase;
	}

	public void setValorePesatoFase(double valorePesatoFase) {
		this.valorePesatoFase = valorePesatoFase;
	}

}
