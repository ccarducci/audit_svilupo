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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="CRS_ATTIVITA_DETTAGLIO")
public class CrsAttivitaDettaglio implements Serializable {
	@Id
	@SequenceGenerator(name="CRS_ATTIVITA_DETTAGLIO_GENERATOR", sequenceName="ID_ATTIVITADETTAGLIO_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CRS_ATTIVITA_DETTAGLIO_GENERATOR")
	@Column(name="ID_ATTIVITA_DETTAGLIO")
	private long idAttivitaDettaglio;

	private String descrizione;

	@Column(name="DATA_INIZIO")
	private Date dataInizio;

	@Column(name="DATA_FINE")
	private Date dataFine;

	@Column(name="ID_ATTIVITA_COMPONENTE")
	private long idAttivitaComponente;
	
	private String stato;

	//private long idClasse;
	
	private int ordinamento;
	
	

	private static final long serialVersionUID = 1L;

	public CrsAttivitaDettaglio() {
		super();
	}

	public long getIdAttivitaDettaglio() {
		return this.idAttivitaDettaglio;
	}

	public void setIdAttivitaDettaglio(long idAttivitaDettaglio) {
		this.idAttivitaDettaglio = idAttivitaDettaglio;
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

	public void setIdAttivitaComponente(long idAttivitaComponente) {
		this.idAttivitaComponente = idAttivitaComponente;
	}

	public long getIdAttivitaComponente() {
		return idAttivitaComponente;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public String getStato() {
		return stato;
	}

	public void setOrdinamento(int ordinamento) {
		this.ordinamento = ordinamento;
	}

	public int getOrdinamento() {
		return ordinamento;
	}

//	public void setIdClasse(long idClasse) {
//		this.idClasse = idClasse;
//	}
//
//	public long getIdClasse() {
//		return idClasse;
//	}


}
