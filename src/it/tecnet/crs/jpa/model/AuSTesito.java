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
@Table(name = "AU_S_TESITO")
public class AuSTesito implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "AU_S_TESITO_GENERATOR", sequenceName = "ID_S_TESITO_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AU_S_TESITO_GENERATOR")
	@Column(name = "ID_S_TESITO")
	private long idSTesito;

	@Column(name = "ID_S_SESSIONE")
	private long idSSessione;

	@Column(name = "TIPO_DIFESA")
	private String tipoDifesa;

	@Column(name = "COD_CHIUSURA_CORRETTO")
	private String codChiusuraCorretto;

	@Column(name = "QUANTITA")
	private int quantita;

	@Column(name = "PERC_QUANTITA")
	private double percQuantita;

	@Column(name = "NUM_PRESTAZIONI")
	private int numPrestazioni;

	@Column(name = "IMPORTO_PRESTAZIONE")
	private double importoPrestazione;

	@Column(name = "SPESE_LEGALI")
	private double speseLegali;

	@Column(name = "DATA_INSERIMENTO")
	private Date dataInserimento;

	@Column(name = "SPESE_LEGALI_CTU")
	private double speseLegaliCtu;

	public long getIdSTesito() {
		return idSTesito;
	}

	public void setIdSTesito(long idSTesito) {
		this.idSTesito = idSTesito;
	}

	public long getIdSSessione() {
		return idSSessione;
	}

	public void setIdSSessione(long idSSessione) {
		this.idSSessione = idSSessione;
	}

	public String getTipoDifesa() {
		return tipoDifesa;
	}

	public void setTipoDifesa(String tipoDifesa) {
		this.tipoDifesa = tipoDifesa;
	}

	public String getCodChiusuraCorretto() {
		return codChiusuraCorretto;
	}

	public void setCodChiusuraCorretto(String codChiusuraCorretto) {
		this.codChiusuraCorretto = codChiusuraCorretto;
	}

	public int getQuantita() {
		return quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

	public double getPercQuantita() {
		return percQuantita;
	}

	public void setPercQuantita(double percQuantita) {
		this.percQuantita = percQuantita;
	}

	public int getNumPrestazioni() {
		return numPrestazioni;
	}

	public void setNumPrestazioni(int numPrestazioni) {
		this.numPrestazioni = numPrestazioni;
	}

	public double getImportoPrestazione() {
		return importoPrestazione;
	}

	public void setImportoPrestazione(double importoPrestazione) {
		this.importoPrestazione = importoPrestazione;
	}

	public double getSpeseLegali() {
		return speseLegali;
	}

	public void setSpeseLegali(double speseLegali) {
		this.speseLegali = speseLegali;
	}

	public Date getDataInserimento() {
		return dataInserimento;
	}

	public void setDataInserimento(Date dataInserimento) {
		this.dataInserimento = dataInserimento;
	}

	public double getSpeseLegaliCtu() {
		return speseLegaliCtu;
	}

	public void setSpeseLegaliCtu(double speseLegaliCtu) {
		this.speseLegaliCtu = speseLegaliCtu;
	}

}
