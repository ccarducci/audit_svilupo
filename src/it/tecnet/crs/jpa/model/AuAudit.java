package it.tecnet.crs.jpa.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="AU_AUDIT")
public class AuAudit implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="AU_AUDIT_GENERATOR", sequenceName="ID_AUDIT_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="AU_AUDIT_GENERATOR")
	@Column(name="ID_AUDIT")
	private Long idAudit;

	@Column(name="nome")
	private String nome;

	@Column(name="descrizione")
	private String descrizione;

	@Column(name="DATA_INIZIO")
	private Date dataInizio;
	
	@Column(name="DATA_FINE")
	private Date dataFine;

	@Column(name="input")
	private String input;

	@Column(name="output")
	private String output;
	
	@Column(name="obiettivo")
	private String obiettivo;


	
	public AuAudit() {
		super();
	}

	public long getIdAudit() {
		return this.idAudit;
	}

	public void setIdAudit(Long idAudit) {
		this.idAudit = idAudit;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
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

	public String getInput() {
		return this.input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public String getOutput() {
		return this.output;
	}

	public void setOutput(String output) {
		this.output = output;
	}

	public String getObiettivo() {
		return this.obiettivo;
	}

	public void setObiettivo(String obiettivo) {
		this.obiettivo = obiettivo;
	}


}
