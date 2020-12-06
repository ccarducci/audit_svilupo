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
@Table(name="CRS_TPL_COMP_TECNICO")
@NamedQueries( { @NamedQuery(name = "CrsTplCompTecnico.getAll", 
		query = "SELECT tipo FROM CrsTplCompTecnico tipo")
		})
public class CrsTplCompTecnico implements Serializable {

	@Id
	@SequenceGenerator(name="CRS_TPL_COMP_TECNICO_GENERATOR", sequenceName="CRS_TPL_COMP_TECNICO_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CRS_TPL_COMP_TECNICO_GENERATOR")
	@Column(name="ID_TPL_COMP_TECNICO")
	private long id;
	
	@Column(name="CODICE")
	private String codice;
	
	@Column(name="DESCRIZIONE")
	private String descrizione;
	
	@Column(name="DATA_INIZIO")
	private Date dataInizio;
	
	@Column(name="DATA_FINE")
	private Date dataFine;
	
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
	public void setDataInizio(Date dataInizio) {
		this.dataInizio = dataInizio;
	}
	public Date getDataInizio() {
		return dataInizio;
	}
	public void setDataFine(Date dataFine) {
		this.dataFine = dataFine;
	}
	public Date getDataFine() {
		return dataFine;
	}
	

}



