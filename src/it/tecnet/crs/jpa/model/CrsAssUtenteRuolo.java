package it.tecnet.crs.jpa.model;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;


@Entity
@Table(name="CRS_ASS_UTENTE_RUOLO")



public class CrsAssUtenteRuolo {

	@Id
	@SequenceGenerator(name="CRS_ASS_UTENTE_RUOLO_GENERATOR", sequenceName="ID_ASS_UTENTE_RUOLO_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CRS_ASS_UTENTE_RUOLO_GENERATOR")
	@Column(name="ID_UTENTE_RUOLO")
	private long idUtenteRuolo;
	
	@Column(name="ID_UTENTE")
	private long idUtente;
	
	@Column(name="ID_RUOLO")
	private long idRuolo;

	public CrsAssUtenteRuolo() {
		super();
	}

	public long getIdUtenteRuolo() {
		return idUtenteRuolo;
	}

	public void setIdUtenteRuolo(long idUtenteRuolo) {
		this.idUtenteRuolo = idUtenteRuolo;
	}

	public long getIdUtente() {
		return idUtente;
	}

	public void setIdUtente(long idUtente) {
		this.idUtente = idUtente;
	}

	public long getIdRuolo() {
		return idRuolo;
	}

	public void setIdRuolo(long idRuolo) {
		this.idRuolo = idRuolo;
	}
}
