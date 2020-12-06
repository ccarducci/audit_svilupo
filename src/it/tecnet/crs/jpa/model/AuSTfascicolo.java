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
@Table(name = "AU_S_TFASCICOLO")
public class AuSTfascicolo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "AU_S_TFASCICOLO_GENERATOR", sequenceName = "ID_S_TFASCICOLO_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AU_S_TFASCICOLO_GENERATOR")
	@Column(name = "ID_S_TFASCICOLO")
	private long idSTfascicolo;

	@Column(name = "ID_S_SESSIONE")
	private long idSSessione;

	@Column(name = "TIPO")
	private String tipo;

	@Column(name = "CODIFICA")
	private String codifica;

	@Column(name = "QUANTITA")
	private Integer quantita;

	@Column(name = "PERC")
	private double perc;

	@Column(name = "DATA_INSERIMENTO")
	private Date dataInserimento;

	public long getIdSTfascicolo() {
		return idSTfascicolo;
	}

	public void setIdSTfascicolo(long idSTfascicolo) {
		this.idSTfascicolo = idSTfascicolo;
	}

	public long getIdSSessione() {
		return idSSessione;
	}

	public void setIdSSessione(long idSSessione) {
		this.idSSessione = idSSessione;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getCodifica() {
		return codifica;
	}

	public void setCodifica(String codifica) {
		this.codifica = codifica;
	}

	public Integer getQuantita() {
		return quantita;
	}

	public void setQuantita(Integer quantita) {
		this.quantita = quantita;
	}

	public double getPerc() {
		return perc;
	}

	public void setPerc(double perc) {
		this.perc = perc;
	}

	public Date getDataInserimento() {
		return dataInserimento;
	}

	public void setDataInserimento(Date dataInserimento) {
		this.dataInserimento = dataInserimento;
	}

}
