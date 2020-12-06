package it.tecnet.crs.mod.jpa.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="CRS_PROCESSO")
public class CrsProcesso implements Serializable {
	@Id
	@SequenceGenerator(name="CRS_PROCESSO_GENERATOR", sequenceName="ID_PROCESSO_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CRS_PROCESSO_GENERATOR")
	@Column(name="ID_PROCESSO")
	private long idProcesso;

	private String descrizione;

	@Column(name="DATA_INIZIO")
	private Date dataInizio;

	@Column(name="DATA_FINE")
	private Date dataFine;

	@Column(name="ID_AREA")
	private long idArea;
	
	@Column(name="STATO")
	private String stato;
	
	private String input;

	private String output;
	
	private int ordinamento;
	
	private String owner;
	
	private String pubblicazione;

	
	private static final long serialVersionUID = 1L;

	public CrsProcesso() {
		super();
	}

	public long getIdProcesso() {
		return this.idProcesso;
	}

	public void setIdProcesso(long idProcesso) {
		this.idProcesso = idProcesso;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Date getDataInizio() {
		return this.dataInizio;
	}

	public void setDataInizio(Date dataInizio) {
		this.dataInizio = dataInizio;
	}

	public Date getDataFine() {
		return this.dataFine;
	}

	public void setDataFine(Date dataFine) {
		this.dataFine = dataFine;
	}



	public void setIdArea(long idArea) {
		this.idArea = idArea;
	}

	public long getIdArea() {
		return idArea;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public String getStato() {
		return stato;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public String getInput() {
		return input;
	}

	public void setOutput(String output) {
		this.output = output;
	}

	public String getOutput() {
		return output;
	}

	public void setOrdinamento(int ordinamento) {
		this.ordinamento = ordinamento;
	}

	public int getOrdinamento() {
		return ordinamento;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getOwner() {
		return owner;
	}

	public void setPubblicazione(String pubblicazione) {
		this.pubblicazione = pubblicazione;
	}

	public String getPubblicazione() {
		return pubblicazione;
	}


}
