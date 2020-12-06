package it.tecnet.crs.jpa.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="AU_CAMPAGNA")
public class AuCampagna implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public AuCampagna() {
		super();
	}
	
	@Id
	@SequenceGenerator(name="AU_CAMPAGNA_GENERATOR", sequenceName="ID_CAMPAGNA_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="AU_CAMPAGNA_GENERATOR")
	@Column(name="ID_CAMPAGNA")
	private long idCampagna;
	
	@Column(name="NOME")
	private String nome;

	@Column(name="DATA_INIZIO")
	private Date dataInizio;

	@Column(name="DATA_FINE")
	private Date dataFine;
	
	@Column(name="DATA_INIZIO_OSSERVAZIONE")
	private Date dataInizioOsservazione;

	@Column(name="DATA_FINE_OSSERVAZIONE")
	private Date dataFineOsservazione;
	
	@Column(name="STATO")
	private String stato;

	@Column(name="ID_AUDIT")
	private long idAudit;

	public long getIdCampagna() {
		return this.idCampagna;
	}

	public void setIdCampagna(long idCampagna) {
		this.idCampagna = idCampagna;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getStato() {
		return this.stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public Date getDataInizio() {
		return dataInizio;
	}

	public void setDataInizio(Date dataInizio) {
		this.dataInizio = dataInizio;
	}

	public Date getDataInizioOsservazione() {
		return dataInizioOsservazione;
	}

	public void setDataInizioOsservazione(Date dataInizioOsservazione) {
		this.dataInizioOsservazione = dataInizioOsservazione;
	}

	public Date getDataFine() {
		return dataFine;
	}

	public void setDataFine(Date dataFine) {
		this.dataFine = dataFine;
	}

	public Date getDataFineOsservazione() {
		return dataFineOsservazione;
	}

	public void setDataFineOsservazione(Date dataFineOsservazione) {
		this.dataFineOsservazione = dataFineOsservazione;
	}

	public long getIdAudit() {
		return idAudit;
	}

	public void setIdAudit(long idAudit) {
		this.idAudit = idAudit;
	}


}
