package it.tecnet.crs.jpa.model;

import java.io.Serializable;
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
import javax.persistence.Transient;

@Entity
@Table(name = "AU_TPL_ISNC")
@NamedQueries( {
		@NamedQuery(name = AuTplIsnc.QUERY_TPLISNC_ALL, query = "SELECT t FROM AuTplIsnc t")
}) 
public class AuTplIsnc implements Serializable {

	public static final String QUERY_TPLISNC_ALL = "AuTplIsnc.findAll";

	static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "AU_TPL_ISNC_GENERATOR", sequenceName = "ID_TPL_ISNC_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AU_TPL_ISNC_GENERATOR")
	@Column(name = "ID_TPL_ISNC")
	private long idTplIsnc;

	@Column(name = "SOGLIA")
	private String soglia;
	
	@Transient
	private String nomeaudit;

	@Column(name = "DATA_INIZIO")
	private Date dataInizio;

	@Column(name = "DATA_FINE")
	private Date dataFine;

	@Column(name = "COLORE")
	private String colore;

	@Column(name = "ID_AUDIT")
	private long idAudit;

	public long getIdTplIsnc() {
		return idTplIsnc;
	}

	public void setIdTplIsnc(long idTplIsnc) {
		this.idTplIsnc = idTplIsnc;
	}

	public String getSoglia() {
		return soglia;
	}

	public void setSoglia(String soglia) {
		this.soglia = soglia;
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

	public String getColore() {
		return colore;
	}

	public void setColore(String colore) {
		this.colore = colore;
	}

	public long getIdAudit() {
		return idAudit;
	}

	public void setIdAudit(long idAudit) {
		this.idAudit = idAudit;
	}

	public String getNomeaudit() {
		return nomeaudit;
	}

	public void setNomeaudit(String nomeaudit) {
		this.nomeaudit = nomeaudit;
	}

}
