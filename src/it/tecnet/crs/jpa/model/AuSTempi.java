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
@Table(name = "AU_S_TEMPI")
public class AuSTempi implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "AU_S_TEMPI_GENERATOR", sequenceName = "ID_S_TEMPI_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AU_S_TEMPI_GENERATOR")
	@Column(name = "ID_S_TEMPI")
	private long idSTempi;

	@Column(name = "tipo")
	private String tipo;

	@Column(name = "codifica")
	private String codifica;

	@Column(name = "quantita")
	private int quantita;

	@Column(name = "MEDIA_GG")
	private double mediaGg;

	@Column(name = "DATA_INSERIMENTO")
	private Date dataInserimento;

	@Column(name = "ID_S_SESSIONE")
	private long idSSessione;

	@Column(name = "NC")
	private String nc;

	@Column(name = "ORDINAMENTO")
	private int ordinamento;

	public long getIdSTempi() {
		return idSTempi;
	}

	public void setIdSTempi(long idSTempi) {
		this.idSTempi = idSTempi;
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

	public int getQuantita() {
		return quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

	public double getMediaGg() {
		return mediaGg;
	}

	public void setMediaGg(double mediaGg) {
		this.mediaGg = mediaGg;
	}

	public Date getDataInserimento() {
		return dataInserimento;
	}

	public void setDataInserimento(Date dataInserimento) {
		this.dataInserimento = dataInserimento;
	}

	public long getIdSSessione() {
		return idSSessione;
	}

	public void setIdSSessione(long idSSessione) {
		this.idSSessione = idSSessione;
	}

	public String getNc() {
		return nc;
	}

	public void setNc(String nc) {
		this.nc = nc;
	}

	public int getOrdinamento() {
		return ordinamento;
	}

	public void setOrdinamento(int ordinamento) {
		this.ordinamento = ordinamento;
	}

}
