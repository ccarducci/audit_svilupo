package it.tecnet.crs.componenti.jpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="CRS_TPL_ENTE_EMITTENTE")
public class CrsTplEnteEmittente {
	
	@Column(name="ID_ENTE_EMITTENTE")
	private long id;
	
	@Column(name="CODICE")
	private String codice;
	
	@Column(name="DESCRIZIONE")
	private String descrizione;
	
	public void setId(long id) {
		this.id = id;
	}
	public long getId() {
		return id;
	}
	public void setCodice(String codice) {
		this.codice = codice;
	}
	public String getCodice() {
		return codice;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getDescrizione() {
		return descrizione;
	}
	

}
