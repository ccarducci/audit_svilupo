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
@Table(name="AU_SEDE")
public class AuSede implements Serializable {
	

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="AU_SEDE_GENERATOR", sequenceName="ID_SEDE_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="AU_SEDE_GENERATOR")
	@Column(name="ID_SEDE")
	private long idSede;

	@Column(name="CODICE_SEDE")
	private String codiceSede;

	@Column(name="NOME_SEDE")
	private String nomeSede;

	
	
	public long getIdSede() {
		return idSede;
	}

	public void setIdSede(long idSede) {
		this.idSede = idSede;
	}

	public String getCodiceSede() {
		return codiceSede;
	}

	public void setCodiceSede(String codiceSede) {
		this.codiceSede = codiceSede;
	}

	public String getNomeSede() {
		return nomeSede;
	}

	public void setNomeSede(String nomeSede) {
		this.nomeSede = nomeSede;
	}

}
