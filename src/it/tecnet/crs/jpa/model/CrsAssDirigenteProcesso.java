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
@Table(name="CRS_ASS_DIRIGENTE_PROCESSO")
public class CrsAssDirigenteProcesso implements Serializable {
	@Id
	@SequenceGenerator(name="CRS_ASS_DIRIGENTE_PROCESSO_GENERATOR", sequenceName="ID_ASS_DIRIGENTE_PROCESSO_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CRS_ASS_DIRIGENTE_PROCESSO_GENERATOR")
	@Column(name="ID_ASS_DIRIGENTE_PROCESSO")
	private long idAssDirigenteProcesso;

	@Column(name="ID_PROCESSO")
	private long idProcesso;

	@Column(name="ID_DIRIGENTE")
	private long idDirigente;

	public long getIdAssDirigenteProcesso() {
		return idAssDirigenteProcesso;
	}

	public void setIdAssDirigenteProcesso(long idAssDirigenteProcesso) {
		this.idAssDirigenteProcesso = idAssDirigenteProcesso;
	}

	public long getIdProcesso() {
		return idProcesso;
	}

	public void setIdProcesso(long idProcesso) {
		this.idProcesso = idProcesso;
	}

	public long getIdDirigente() {
		return idDirigente;
	}

	public void setIdDirigente(long idDirigente) {
		this.idDirigente = idDirigente;
	}

	
	
}
