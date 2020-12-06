package it.tecnet.crs.jpa.model;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.IdClass;

@Entity
@Table(name="CRS_ASS_SOTTOPROCESSO_CLASSE")
public class CrsAssSottoprocessoClasse implements Serializable {
	
	@Id
	@SequenceGenerator(name="CRS_ASS_SOTTOPROCESSO_CLASSE_GENERATOR", sequenceName="ID_ASS_SOTTOPROCESSO_CLASSE_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CRS_ASS_SOTTOPROCESSO_CLASSE_GENERATOR")
	@Column(name="ID_ASS_SOTTOPROCESSO_CLASSE")
	private long idAssSottoProcessoClasse;
	
	@Column(name="ID_SOTTOPROCESSO")
	private long idSottoprocesso;
	
	@Column(name="ID_NORMATIVA")
	private long idNormativa;
	
	@Column(name="ID_TIPO")
	private long idTipo;

	private static final long serialVersionUID = 1L;

	public CrsAssSottoprocessoClasse() {
		super();
	}

	public long getIdAssSottoProcessoClasse() {
		return idAssSottoProcessoClasse;
	}

	public void setIdAssSottoProcessoClasse(long idAssSottoProcessoClasse) {
		this.idAssSottoProcessoClasse = idAssSottoProcessoClasse;
	}

	public long getIdSottoprocesso() {
		return idSottoprocesso;
	}

	public void setIdSottoprocesso(long idSottoprocesso) {
		this.idSottoprocesso = idSottoprocesso;
	}

	public void setIdNormativa(long idNormativa) {
		this.idNormativa = idNormativa;
	}

	public long getIdNormativa() {
		return idNormativa;
	}

	public void setIdTipo(long idTipo) {
		this.idTipo = idTipo;
	}

	public long getIdTipo() {
		return idTipo;
	}



}
