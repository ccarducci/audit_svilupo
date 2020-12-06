package it.tecnet.crs.componenti.jpa.model;

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
@Table(name="CRS_NOTE_DECRETI")
public class CrsNoteDecreti implements Serializable {
	@Id
	@Column(name="ID_NOTE_DECRETI")
	@SequenceGenerator(name="CRS_NOTE_DECRETI_GENERATOR", sequenceName="ID_NOTEDECRETI_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CRS_NOTE_DECRETI_GENERATOR")
	private long idNoteDecreti;
	
	@Column(name="DATA_INIZIO")
	private Date dataInizio;

	@Column(name="DATA_FINE")
	private Date dataFine;

	@Column(name="DESCR_SINTETICA")
	private String descSintetica;

	@Column(name="DESCR_DETTAGLIO")
	private String descDettaglio;
	
	@Column(name="DATA_EMISSIONE")
	private Date dataEmissione;

	private String codice;

	private String oggetto;

	private String riferimenti;


	private static final long serialVersionUID = 1L;

	public CrsNoteDecreti() {
		super();
	}

	public long getIdNoteDecreti() {
		return idNoteDecreti;
	}

	public void setIdNoteDecreti(long idNoteDecreti) {
		this.idNoteDecreti = idNoteDecreti;
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

	public String getDescSintetica() {
		return descSintetica;
	}

	public void setDescSintetica(String descSintetica) {
		this.descSintetica = descSintetica;
	}

	public String getDescDettaglio() {
		return descDettaglio;
	}

	public void setDescDettaglio(String descDettaglio) {
		this.descDettaglio = descDettaglio;
	}

	public Date getDataEmissione() {
		return dataEmissione;
	}

	public void setDataEmissione(Date dataEmissione) {
		this.dataEmissione = dataEmissione;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getOggetto() {
		return oggetto;
	}

	public void setOggetto(String oggetto) {
		this.oggetto = oggetto;
	}

	public String getRiferimenti() {
		return riferimenti;
	}

	public void setRiferimenti(String riferimenti) {
		this.riferimenti = riferimenti;
	}

	
	
	
	

}
