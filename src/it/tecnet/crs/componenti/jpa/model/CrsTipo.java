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
@Table(name="CRS_TIPO")
public class CrsTipo implements Serializable {
	@Id
	@SequenceGenerator(name="CRS_TIPO_GENERATOR", sequenceName="ID_TIPO_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CRS_TIPO_GENERATOR")
	@Column(name="ID_TIPO")
	private long idTipo;

	private String descrizione;

	@Column(name="DATA_INIZIO")
	private Date dataInizio;

	@Column(name="DATA_FINE")
	private Date dataFine;

	@Column(name="ID_CLASSE")
	private long idClasse;

	
	private static final long serialVersionUID = 1L;

	public CrsTipo() {
		super();
	}

	public long getIdTipo() {
		return this.idTipo;
	}

	public void setIdTipo(long idTipo) {
		this.idTipo = idTipo;
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

	public void setIdClasse(long idClasse) {
		this.idClasse = idClasse;
	}

	public long getIdClasse() {
		return idClasse;
	}

}
