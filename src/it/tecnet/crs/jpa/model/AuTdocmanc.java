package it.tecnet.crs.jpa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "AU_TDOCMANC")
public class AuTdocmanc implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "AU_TDOCMANC_GENERATOR", sequenceName = "ID_TDOCMANC_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AU_TDOCMANC_GENERATOR")
	@Column(name = "ID_TDOCMANC")
	private long idTdocmanc;

	@Column(name = "ID_S_SESSIONE")
	private long idSSessione;

	@Column(name = "TIPO")
	private String tipo;

	@Column(name = "CODIFICA")
	private String codifica;

	@Column(name = "QUANTITA")
	private long quantita;

	@Column(name = "PERCENTUALE")
	private Double percentuale;

	public long getIdTdocmanc() {
		return idTdocmanc;
	}

	public void setIdTdocmanc(long idTdocmanc) {
		this.idTdocmanc = idTdocmanc;
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

	public long getQuantita() {
		return quantita;
	}

	public void setQuantita(long quantita) {
		this.quantita = quantita;
	}

	public Double getPercentuale() {
		return percentuale;
	}

	public void setPercentuale(Double percentuale) {
		this.percentuale = percentuale;
	}

}
