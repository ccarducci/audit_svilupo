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
@Table(name="AU_ASS_UTENTE_AUDIT")
public class AuAssUtenteAudit implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@SequenceGenerator(name="AU_ASS_UTENTE_AUDIT_GENERATOR", sequenceName="ID_ASS_UTENTE_AUDIT_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="AU_ASS_UTENTE_AUDIT_GENERATOR")
	@Column(name="ID_ASS_UTENTE_AUDIT")
	private Long idAssUtenteAudit;

	@Column(name="ID_UTENTE")
	private Long idUtente;
	
	@Column(name="ID_AUDIT")
	private Long idAudit;
	
	@Column(name="DATA_INIZIO")
	private Date dataInizio;

	@Column(name="DATA_FINE")
	private Date dataFine;
	

	public AuAssUtenteAudit() {
		super();
	}

	public Long getIdAssUtenteAudit() {
		return idAssUtenteAudit;
	}

	public void setIdAssUtenteAudit(Long idAssUtenteAudit) {
		this.idAssUtenteAudit = idAssUtenteAudit;
	}

	public Long getIdUtente() {
		return idUtente;
	}

	public void setIdUtente(Long idUtente) {
		this.idUtente = idUtente;
	}

	public Long getIdAudit() {
		return idAudit;
	}

	public void setIdAudit(Long idAudit) {
		this.idAudit = idAudit;
	}

	public void setDataInizio(Date dataInizio) {
		this.dataInizio = dataInizio;
	}

	public Date getDataInizio() {
		return dataInizio;
	}

	public void setDataFine(Date dataFine) {
		this.dataFine = dataFine;
	}

	public Date getDataFine() {
		return dataFine;
	}
}
