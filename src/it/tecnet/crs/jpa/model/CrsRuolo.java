package it.tecnet.crs.jpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="CRS_RUOLO")
public class CrsRuolo {
	
	@Id
	@SequenceGenerator(name="CRS_RUOLO_GENERATOR", sequenceName="ID_RUOLO_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CRS_RUOLO_GENERATOR")
	@Column(name="ID_RUOLO")
	private long idRuolo;
	
	@Column(name="CODICE")
	private String codice;
	
	@Column(name="DESCRIZIONE")
	private String descrizione;

	public CrsRuolo() {
		super();
	}

	public long getIdRuolo() {
		return idRuolo;
	}

	public void setIdRuolo(long idRuolo) {
		this.idRuolo = idRuolo;
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

}
