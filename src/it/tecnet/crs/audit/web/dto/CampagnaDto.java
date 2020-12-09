package it.tecnet.crs.audit.web.dto;

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


public class CampagnaDto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public CampagnaDto() {
		super();
	}
	

	private long idCampagna;

	private String nome;

	
	private Date dataInizio;

	
	private Date dataFine;
	
	
	private Date dataInizioOsservazione;

	
	private Date dataFineOsservazione;

	private String stato;

	
	private long idAudit;
	
	private String nomeAudit;
	
	private String tipo;

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
		if (this.stato == null) return "A";
		return this.stato.trim().toUpperCase();
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

	public String getNomeAudit() {
		return nomeAudit;
	}

	public void setNomeAudit(String nomeAudit) {
		this.nomeAudit = nomeAudit;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getTipo() {
		return tipo;
	}


}
