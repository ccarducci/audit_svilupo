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
@Table(name="CRS_MESSAGGI_INPS")
public class CrsMessaggiInps implements Serializable {
	@Id
	@Column(name="ID_MESSAGGI_INPS")
	@SequenceGenerator(name="CRS_MESSAGGI_INPS_GENERATOR", sequenceName="ID_MESSAGGIINPS_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CRS_MESSAGGI_INPS_GENERATOR")
	private long idMessaggiInps;
	
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
	
	@Column(name="ENTE_EMITTENTE")
	private String enteEmittente;


	private static final long serialVersionUID = 1L;

	public CrsMessaggiInps() {
		super();
	}

	public long getIdMessaggiInps() {
		return idMessaggiInps;
	}

	public void setIdMessaggiInps(long idMessaggiInps) {
		this.idMessaggiInps = idMessaggiInps;
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

	public void setEnteEmittente(String enteEmittente) {
		this.enteEmittente = enteEmittente;
	}

	public String getEnteEmittente() {
		return enteEmittente;
	}


	

}
