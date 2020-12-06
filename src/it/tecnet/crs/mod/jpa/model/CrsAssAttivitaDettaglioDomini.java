package it.tecnet.crs.mod.jpa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name="CRS_ASS_ATTIVITA_DETTAGLIO_DOMINI")
public class CrsAssAttivitaDettaglioDomini implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CRS_ASS_ATTIVITA_DETTAGLIO_DOMINI_GENERATOR", sequenceName="CRS_ASS_ATTIVITA_DETTAGLIO_DOMINI_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CRS_ASS_ATTIVITA_DETTAGLIO_DOMINI_GENERATOR")
	@Column(name="ID")
	private long id;
	
	@Column(name="ID_ATTIVITA_DETTAGLIO")
	private long idAttivitaDettaglio;
	
	@Column(name="ID_DOMINI")
	private long idDomini;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public void setIdAttivitaDettaglio(long idAttivitaDettaglio) {
		this.idAttivitaDettaglio = idAttivitaDettaglio;
	}

	public long getIdAttivitaDettaglio() {
		return idAttivitaDettaglio;
	}

	public void setIdDomini(long idDomini) {
		this.idDomini = idDomini;
	}

	public long getIdDomini() {
		return idDomini;
	}

	
	
	

}
