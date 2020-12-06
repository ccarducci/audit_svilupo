package it.tecnet.crs.jpa.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="AU_ASS_UTENTE_SESSIONE")
public class AuAssUtenteSessione implements Serializable {
	@Id
	@SequenceGenerator(name="AU_ASS_UTENTE_SESSIONE_GENERATOR", sequenceName="ID_ASSUTSES_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="AU_ASS_UTENTE_SESSIONE_GENERATOR")
	@Column(name="ID_ASS_US")
	private long idAssUS;
	
	@Column(name="ID_UTENTE")
	private long idUtente;

	@Column(name="ID_SESSIONE")
	private long idSessione;
	
	@Column(name="DATA_INIZIO")
	private Date dataInizio;

	@Column(name="DATA_FINE")
	private Date dataFine;

	private static final long serialVersionUID = 1L;

	public AuAssUtenteSessione() {
		super();
	}

	public long getIdAssUS() {
		return idAssUS;
	}

	public void setIdAssUS(long idAssUS) {
		this.idAssUS = idAssUS;
	}

	public long getIdUtente() {
		return idUtente;
	}

	public void setIdUtente(long idUtente) {
		this.idUtente = idUtente;
	}

	public long getIdSessione() {
		return idSessione;
	}

	public void setIdSessione(long idSessione) {
		this.idSessione = idSessione;
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
