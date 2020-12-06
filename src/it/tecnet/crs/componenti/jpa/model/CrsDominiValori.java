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
@Table(name="CRS_DOMINI_VALORI")
public class CrsDominiValori implements Serializable{
	
	
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CRS_DOMINI_VALORI_GENERATOR", sequenceName="CRS_DOMINI_VALORI_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CRS_DOMINI_VALORI_GENERATOR")
	@Column(name="ID_DOMINI_VALORI")
	private long idDominiValori;
	
	@Column(name="CODICE_VALORE")
	private String codiceValore;
	
	@Column(name="CODICE_DOMINIO")
	private String codiceDominio;
	
	@Column(name="DESCRIZIONE")
	private String descrizione;
	
	@Column(name="DATA_INIZIO")
	private Date dataInizio;
	
	@Column(name="DATA_FINE")
	private Date dataFine;

	
	
	
	public long getIdDominiValori() {
		return idDominiValori;
	}

	public void setIdDominiValori(long idDominiValori) {
		this.idDominiValori = idDominiValori;
	}

	public String getCodiceValore() {
		return codiceValore;
	}

	public void setCodiceValore(String codiceValore) {
		this.codiceValore = codiceValore;
	}

	public String getCodiceDominio() {
		return codiceDominio;
	}

	public void setCodiceDominio(String codiceDominio) {
		this.codiceDominio = codiceDominio;
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


}
