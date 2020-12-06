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

@Entity
@Table(name = "AU_S_TVALORI")
@NamedQueries( {
		@NamedQuery(name = AuSTvalori.QUERY_TVALORI_ALL, query = "SELECT t FROM AuSTvalori t")
}) 
public class AuSTvalori implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public static final String QUERY_TVALORI_ALL = "AuSPratica.findAll";
	
	@Id
	@SequenceGenerator(name = "AU_S_TVALORI_GENERATOR", sequenceName = "ID_S_AUDIT_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AU_S_TVALORI_GENERATOR")
	@Column(name = "ID_S_AUDIT")
	private long idSAudit;

	@Column(name = "CODICE")
	private String codice;

	@Column(name = "VARIABILE")
	private String variabile;

	@Column(name = "VALORE")
	private Double valore;

	@Column(name = "DATA_INIZIO")
	private Date dataInizio;

	@Column(name = "DATA_FINE")
	private Date dataFine;

	@Column(name = "DESCRIZIONE")
	private String descrizione;

	public long getIdSAudit() {
		return idSAudit;
	}

	public void setIdSAudit(long idSAudit) {
		this.idSAudit = idSAudit;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getVariabile() {
		return variabile;
	}

	public void setVariabile(String variabile) {
		this.variabile = variabile;
	}

	public Double getValore() {
		return valore;
	}

	public void setValore(Double valore) {
		this.valore = valore;
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

}
