package it.tecnet.crs.mod.web.dto;

import java.util.Date;

public class DocMediaAttCompDto {
	

	private long idAssociazione;
	private long idDoc;
	private String codice;
	private String descrizione;
	private Date dataInizio;
	private Date dataFine;
	private byte[] doc;
	private String prefisso;
	private String nomeFile;
	
	
	public String getCodice() {
		return codice;
	}
	public void setCodice(String codice) {
		this.codice = codice;
	}
	public String getDescrizione() {
		return descrizione;
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
	public void setIdDoc(long idDoc) {
		this.idDoc = idDoc;
	}
	public long getIdDoc() {
		return idDoc;
	}
	public void setIdAssociazione(long idAssociazione) {
		this.idAssociazione = idAssociazione;
	}
	public long getIdAssociazione() {
		return idAssociazione;
	}

}
