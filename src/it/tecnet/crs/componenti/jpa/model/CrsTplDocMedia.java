package it.tecnet.crs.componenti.jpa.model;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="CRS_TPL_DOC_MEDIA")
@NamedQueries( { @NamedQuery(name = "CrsTplDocMedia.findAll", 
		query = "SELECT tipo FROM CrsTplDocMedia tipo")
		})
public class CrsTplDocMedia implements Serializable {
	
	
	
	
	@Id
	@SequenceGenerator(name="CRS_TPL_DOC_MEDIA_GENERATOR", sequenceName="CRS_TPL_DOC_MEDIA_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CRS_TPL_DOC_MEDIA_GENERATOR")
	@Column(name="ID")
	private long id;

	@Column(name="DESCRIZIONE")
	private String descrizione;

	@Column(name="DATA_INIZIO")
	private Date dataInizio;

	@Column(name="DATA_FINE")
	private Date dataFine;



	
	private static final long serialVersionUID = 1L;

	
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Date getDataInizio() {
		return this.dataInizio;
	}

	public void setDataInizio(Date dataInizio) {
		this.dataInizio = dataInizio;
	}

	public Date getDataFine() {
		return this.dataFine;
	}

	public void setDataFine(Date dataFine) {
		this.dataFine = dataFine;
	}

	

}
