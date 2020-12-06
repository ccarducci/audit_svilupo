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
@Table(name="CRS_COMPONENTE_TECNICO")
public class CrsComponenteTecnico implements Serializable{
	
	
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CRS_COMPONENTE_TECNICO_GENERATOR", sequenceName="CRS_COMPONENTE_TECNICO_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CRS_COMPONENTE_TECNICO_GENERATOR")
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
	
	@Column(name="DATA_PUBBLICAZIONE")
	private Date dataPubblicazione;
	
	@Column(name="VERSIONE")
	private String versione;
	
	@Column(name="AUTORE")
	private String autore;

	@Column(name="ID_TIPO_COMP_TEC")
	private long idTipoCompTecnico;
	
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

	public String getAutore() {
		return autore;
	}

	public void setAutore(String autore) {
		this.autore = autore;
	}

	public long getIdTipoCompTecnico() {
		return idTipoCompTecnico;
	}

	public void setIdTipoCompTecnico(long idTipoCompTecnico) {
		this.idTipoCompTecnico = idTipoCompTecnico;
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
