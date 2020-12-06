package it.tecnet.crs.jpa.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="CRS_ASS_DELEGATO_DIRIGENTE")
public class CrsAssDelegatoDirigente implements Serializable {
	@Id
	@SequenceGenerator(name="CRS_ASS_DELEGATO_DIRIGENTE_GENERATOR", sequenceName="ID_ASS_DELEGATO_DIRIGENTE_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CRS_ASS_DELEGATO_DIRIGENTE_GENERATOR")
	@Column(name="ID_ASS_DELEGATO_DIRIGENTE")
	private long idAssDelegatoDirigente;

	@Column(name="ID_DELEGATO")
	private long idDelegato;

	@Column(name="ID_DIRIGENTE")
	private long idDirigente;

	public long getIdAssDelegatoDirigente() {
		return idAssDelegatoDirigente;
	}

	public void setIdAssDelegatoDirigente(long idAssDelegatoDirigente) {
		this.idAssDelegatoDirigente = idAssDelegatoDirigente;
	}


	public long getIdDirigente() {
		return idDirigente;
	}

	public void setIdDirigente(long idDirigente) {
		this.idDirigente = idDirigente;
	}

	public long getIdDelegato() {
		return idDelegato;
	}

	public void setIdDelegato(long idDelegato) {
		this.idDelegato = idDelegato;
	}


	
}
