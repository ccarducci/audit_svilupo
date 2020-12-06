package it.tecnet.crs.jpa.model;

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
@Table(name = "AU_M_NONCONF")
@NamedQueries( {
		@NamedQuery(name = AuMNonConf.QUERY_NONCONF_BY_IDAUDIT, query = "SELECT e FROM AuMNonConf e WHERE e.idAudit = :idAudit"),
		@NamedQuery(name = AuMNonConf.QUERY_NONCONF_ALL, query = "SELECT e FROM AuMNonConf e"),
		@NamedQuery(name = AuMNonConf.QUERY_NONCONF_BY_IDFASE, query = "SELECT e FROM AuMNonConf e WHERE e.idFase = :idFase") })
public class AuMNonConf {

	public static final String QUERY_NONCONF_BY_IDAUDIT = "AuMNonConf.findAllByIdAudit";
	public static final String QUERY_NONCONF_ALL = "AuMNonConf.findAll";
	public static final String QUERY_NONCONF_BY_IDFASE = "AuMNonConf.findAllByIdFase";

	@Id
	@SequenceGenerator(name = "AU_M_NONCONF_GENERATOR", sequenceName = "AU_M_NONCONF_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AU_M_NONCONF_GENERATOR")
	@Column(name = "ID_M_NON_CONF")
	private long idMNonConf;

	@Column(name = "ID_AUDIT")
	private long idAudit;

	@Column(name = "ID_FASE")
	private long idFase;

	@Column(name = "DATA_INIZIO")
	private Date dataInizio;

	@Column(name = "DATA_FINE")
	private Date dataFine;

	@Column(name = "DESCRIZIONE")
	private String descrizione;

	@Column(name = "PESO_NON_CONF")
	private double pesoNonConf;

	@Column(name = "CODICE_NC")
	private String codiceNc;

	@Column(name = "ordinamento")
	private String ordinamento;

	public long getIdMNonConf() {
		return idMNonConf;
	}

	public void setIdMNonConf(long idMNonConf) {
		this.idMNonConf = idMNonConf;
	}

	public long getIdAudit() {
		return idAudit;
	}

	public void setIdAudit(long idAudit) {
		this.idAudit = idAudit;
	}

	public long getIdFase() {
		return idFase;
	}

	public void setIdFase(long idFase) {
		this.idFase = idFase;
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

	public double getPesoNonConf() {
		return pesoNonConf;
	}

	public void setPesoNonConf(double pesoNonConf) {
		this.pesoNonConf = pesoNonConf;
	}

	public void setCodiceNc(String codiceNc) {
		this.codiceNc = codiceNc;
	}

	public String getCodiceNc() {
		return codiceNc;
	}

	public String getOrdinamento() {
		return ordinamento;
	}

	public void setOrdinamento(String ordinamento) {
		this.ordinamento = ordinamento;
	}

}
