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
@Table(name="CRS_LEGGI_DECRETI")
public class CrsLeggiDecreti implements Serializable {
	@Id
	@Column(name="ID_LEGGI_DECRETI")
	@SequenceGenerator(name="CRS_LEGGI_DECRETI_GENERATOR", sequenceName="ID_LEGGIDECRETI_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CRS_LEGGI_DECRETI_GENERATOR")
	private long idLeggiDecreti;
	
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

	private String articolo;
	
	@Column(name="ANNO_GUI")
	private Integer annoGui;
	
	@Column(name="NUMERO_GUI")
	private String numeroGui;
	
	@Column(name="TIPO_LEGGE")
	private String tipoLegge;


	private static final long serialVersionUID = 1L;

	public CrsLeggiDecreti() {
		super();
	}

	public long getIdLeggiDecreti() {
		return idLeggiDecreti;
	}

	public void setIdLeggiDecreti(long idLeggiDecreti) {
		this.idLeggiDecreti = idLeggiDecreti;
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

	public String getArticolo() {
		return articolo;
	}

	public void setArticolo(String articolo) {
		this.articolo = articolo;
	}


	public void setAnnoGui(Integer annoGui) {
		this.annoGui = annoGui;
	}

	public Integer getAnnoGui() {
		return annoGui;
	}

	public String getNumeroGui() {
		return numeroGui;
	}

	public void setNumeroGui(String numeroGui) {
		this.numeroGui = numeroGui;
	}

	public void setTipoLegge(String tipoLegge) {
		this.tipoLegge = tipoLegge;
	}

	public String getTipoLegge() {
		return tipoLegge;
	}


	
}
