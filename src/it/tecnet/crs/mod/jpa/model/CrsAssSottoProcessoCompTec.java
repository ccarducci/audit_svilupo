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
@Table(name="CRS_ASS_SOTTOPROCESSO_COMP_TEC")
public class CrsAssSottoProcessoCompTec implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CRS_ASS_SOTTOPROCESSO_COMP_TEC_GENERATOR", sequenceName="CRS_ASS_SOTTOPROCESSO_COMP_TEC_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CRS_ASS_SOTTOPROCESSO_COMP_TEC_GENERATOR")
	@Column(name="ID")
	private long id;

	@Column(name="ID_SOTTOPROCESSO")
	private long idSottoProcesso;
	
	@Column(name="ID_COMPONENTE_TEC")
	private long idComponenteTec;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setIdSottoProcesso(long idSottoProcesso) {
		this.idSottoProcesso = idSottoProcesso;
	}

	public long getIdSottoProcesso() {
		return idSottoProcesso;
	}

	public void setIdComponenteTec(long idComponenteTec) {
		this.idComponenteTec = idComponenteTec;
	}

	public long getIdComponenteTec() {
		return idComponenteTec;
	}

}
