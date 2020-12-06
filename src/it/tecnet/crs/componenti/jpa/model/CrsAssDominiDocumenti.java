package it.tecnet.crs.componenti.jpa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="CRS_ASS_DOMINI_DOCUMENTI")
public class CrsAssDominiDocumenti  implements Serializable {
	

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CRS_ASS_DOMINI_DOCUMENTI_GENERATOR", sequenceName="CRS_ASS_DOMINI_DOCUMENTI_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CRS_ASS_DOMINI_DOCUMENTI_GENERATOR")
	private long id;
	
	@Column(name="ID_DOMINIO")
	private long idDominio;
	
	@Column(name="ID_DOCUMENTO")
	private long idDocumento;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getIdDominio() {
		return idDominio;
	}

	public void setIdDominio(long idDominio) {
		this.idDominio = idDominio;
	}

	public long getIdDocumento() {
		return idDocumento;
	}

	public void setIdDocumento(long idDocumento) {
		this.idDocumento = idDocumento;
	}

	
	
	
}
