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
@Table(name="CRS_DOCUMENTI_MEDIA")
public class CrsDocumentiMedia implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="CRS_DOCUMENTI_MEDIA_GENERATOR", sequenceName="CRS_DOCUMENTI_MEDIA_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CRS_DOCUMENTI_MEDIA_GENERATOR")
	@Column(name="ID")
	private long id;
	
	@Column(name="CODICE")
	private String codice;
	
	@Column(name="DESCRIZIONE")
	private String descrizione;
	
	@Column(name="DATA_INIZIO")
	private Date dataInizio;
	
	@Column(name="DATA_FINE")
	private Date dataFine;
	
	@Column(name="DOC")
	private byte[] doc;
	
	@Column(name="PREFISSO")
	private String prefisso;
	
	@Column(name="NOME_FILE")
	private String nomeFile;
	
	@Column(name="TITOLO")
	private String titolo;
	
	@Column(name="AUTORE")
	private String autore;
	
	@Column(name="DATA_PUBBLICAZIONE")
	private Date dataPubblicazione;
	
	@Column(name="VERSIONE")
	private String versione;
	
	@Column(name="ID_CRS_TIPL_DOC_MEDIA")
	private long idCrsTplDocMedia;
	
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String oggetto) {
		this.descrizione = oggetto;
	}
	public String getTitolo() {
		return titolo;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	public String getAutore() {
		return autore;
	}
	public void setAutore(String autore) {
		this.autore = autore;
	}
	public Date getDataPubblicazione() {
		return dataPubblicazione;
	}
	public void setDataPubblicazione(Date dataPubblicazione) {
		this.dataPubblicazione = dataPubblicazione;
	}
	public String getVersione() {
		return versione;
	}
	public void setVersione(String versione) {
		this.versione = versione;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCodice() {
		return codice;
	}
	public void setCodice(String codice) {
		this.codice = codice;
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
	public byte[] getDoc() {
		return doc;
	}
	public void setDoc(byte[] doc) {
		this.doc = doc;
	}
	
	public void setPrefisso(String prefisso) {
		this.prefisso = prefisso;
	}
	public String getPrefisso() {
		return prefisso;
	}
	public void setNomeFile(String nomeFile) {
		this.nomeFile = nomeFile;
	}
	public String getNomeFile() {
		return nomeFile;
	}
	public void setIdCrsTplDocMedia(long idCrsTplDocMedia) {
		this.idCrsTplDocMedia = idCrsTplDocMedia;
	}
	public long getIdCrsTplDocMedia() {
		return idCrsTplDocMedia;
	}
	

}
