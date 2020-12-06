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
@Table(name="CRS_ASS_ATTIVITA_COMPONENTE_COMP_TEC")
public class CrsAssAttivitaComponenteCompTec implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CRS_ASS_ATTIVITA_COMPONENTE_COMP_TEC_GENERATOR", sequenceName="CRS_ASS_ATTIVITA_COMPONENTE_COMP_TEC_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CRS_ASS_ATTIVITA_COMPONENTE_COMP_TEC_GENERATOR")
	@Column(name="ID")
	private long id;

	@Column(name="ID_ATTIVITA_COMPONENTE")
	private long idAttivitaComponente;
	
	@Column(name="ID_COMPONENTE_TEC")
	private long idComponenteTec;

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

	public long getIdComponenteTec() {
		return idComponenteTec;
	}

	public void setIdComponenteTec(long idComponenteTec) {
		this.idComponenteTec = idComponenteTec;
	}

	
	
	
}
