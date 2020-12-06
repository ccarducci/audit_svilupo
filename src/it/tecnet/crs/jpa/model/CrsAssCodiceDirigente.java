package it.tecnet.crs.jpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name="CRS_ASS_CODICE_DIRIGENTE")
public class CrsAssCodiceDirigente {
	
	@Id
	@SequenceGenerator(name="CRS_ASS_CODICE_DIRIGENTE_GENERATOR", sequenceName="CRS_ASS_CODICE_DIRIGENTE_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CRS_ASS_CODICE_DIRIGENTE_GENERATOR")
	@Column(name="ID_CODICE_DIRIGENTE")
	private long idCodiceDirigente;
	
	@Column(name="ID_DIRIGENTE")
	private long idDirigente;
	
	@Column(name="CODICE")
	private String codice;

	public CrsAssCodiceDirigente() {
		super();
	}

	public long getIdCodiceDirigente() {
		return idCodiceDirigente;
	}

	public void setIdCodiceDirigente(long idCodiceDirigente) {
		this.idCodiceDirigente = idCodiceDirigente;
	}

	public long getIdDirigente() {
		return idDirigente;
	}

	public void setIdDirigente(long idDirigente) {
		this.idDirigente = idDirigente;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}
}
