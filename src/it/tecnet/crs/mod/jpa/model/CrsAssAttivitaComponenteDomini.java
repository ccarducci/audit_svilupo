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
@Table(name="CRS_ASS_ATTIVITA_COMPONENTE_DOMINI")
public class CrsAssAttivitaComponenteDomini implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CRS_ASS_ATTIVITA_COMPONENTE_DOMINI_GENERATOR", sequenceName="CRS_ASS_ATTIVITA_COMPONENTE_DOMINI_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CRS_ASS_ATTIVITA_COMPONENTE_DOMINI_GENERATOR")
	@Column(name="ID")
	private long id;
	
	@Column(name="ID_ATTIVITA_COMPONENTE")
	private long idAttivitaComponente;
	
	@Column(name="ID_DOMINIO")
	private long idDominio;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getIdAttivitaComponente() {
		return idAttivitaComponente;
	}

	public void setIdAttivitaComponente(long idAttivitaComponente) {
		this.idAttivitaComponente = idAttivitaComponente;
	}

	public long getIdDominio() {
		return idDominio;
	}

	public void setIdDominio(long idDominio) {
		this.idDominio = idDominio;
	}
	
	
	

}
