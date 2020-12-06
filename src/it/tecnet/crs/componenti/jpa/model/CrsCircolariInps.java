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
@Table(name="CRS_CIRCOLARI_INPS")
public class CrsCircolariInps implements Serializable {
	@Id
	@Column(name="ID_CIRCOLARI_INPS")
	@SequenceGenerator(name="CRS_CIRCOLARI_INPS_GENERATOR", sequenceName="ID_CIRCOLARIINPS_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CRS_CIRCOLARI_INPS_GENERATOR")
	private long idCircolariInps;
	
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

	@Column(name="DIREZIONE_EMITTENTE1")
	private String direzioneEmittente1;
	
	@Column(name="DIREZIONE_EMITTENTE2")
	private String direzioneEmittente2;

	@Column(name="DIREZIONE_EMITTENTE3")
	private String direzioneEmittente3;
	
	@Column(name="DIREZIONE_EMITTENTE4")
	private String direzioneEmittente4;
	
	@Column(name="DIREZIONE_EMITTENTE5")
	private String direzioneEmittente5;
	
	@Column(name="DIREZIONE_EMITTENTE6")
	private String direzioneEmittente6;
	
	private String sommario;
	
	private Integer anno;
	
	@Column(name="ENTE_EMITTENTE")
	private String enteEmittente;


	private static final long serialVersionUID = 1L;

	public CrsCircolariInps() {
		super();
	}

	
	
	public long getIdCircolariInps() {
		return idCircolariInps;
	}

	public void setIdCircolariInps(long idCircolariInps) {
		this.idCircolariInps = idCircolariInps;
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



	public void setDirezioneEmittente1(String direzioneEmittente1) {
		this.direzioneEmittente1 = direzioneEmittente1;
	}



	public String getDirezioneEmittente1() {
		return direzioneEmittente1;
	}



	public void setDirezioneEmittente2(String direzioneEmittente2) {
		this.direzioneEmittente2 = direzioneEmittente2;
	}



	public String getDirezioneEmittente2() {
		return direzioneEmittente2;
	}



	public void setDirezioneEmittente3(String direzioneEmittente3) {
		this.direzioneEmittente3 = direzioneEmittente3;
	}



	public String getDirezioneEmittente3() {
		return direzioneEmittente3;
	}



	public void setDirezioneEmittente4(String direzioneEmittente4) {
		this.direzioneEmittente4 = direzioneEmittente4;
	}



	public String getDirezioneEmittente4() {
		return direzioneEmittente4;
	}



	public void setDirezioneEmittente5(String direzioneEmittente5) {
		this.direzioneEmittente5 = direzioneEmittente5;
	}



	public String getDirezioneEmittente5() {
		return direzioneEmittente5;
	}



	public void setDirezioneEmittente6(String direzioneEmittente6) {
		this.direzioneEmittente6 = direzioneEmittente6;
	}



	public String getDirezioneEmittente6() {
		return direzioneEmittente6;
	}



	public void setSommario(String sommario) {
		this.sommario = sommario;
	}



	public String getSommario() {
		return sommario;
	}



	public void setAnno(Integer anno) {
		this.anno = anno;
	}



	public Integer getAnno() {
		return anno;
	}



	public void setEnteEmittente(String enteEmittente) {
		this.enteEmittente = enteEmittente;
	}



	public String getEnteEmittente() {
		return enteEmittente;
	}


}
