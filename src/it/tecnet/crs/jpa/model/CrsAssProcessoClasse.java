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
@Table(name="CRS_ASS_PROCESSO_CLASSE")
public class CrsAssProcessoClasse implements Serializable {
	
	@Id
	@SequenceGenerator(name="CRS_ASS_PROCESSO_CLASSE_GENERATOR", sequenceName="ID_ASS_PROCESSO_CLASSE_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CRS_ASS_PROCESSO_CLASSE_GENERATOR")
	@Column(name="ID_ASS_PROCESSO_CLASSE")
	private long idAssProcessoClasse;
	
	@Column(name="ID_PROCESSO")
	private long idProcesso;

	@Column(name="ID_NORMATIVA")
	private long idNormativa;
	
	@Column(name="ID_TIPO")
	private long idTipo;


	private static final long serialVersionUID = 1L;

	public CrsAssProcessoClasse() {
		super();
	}

	public long getIdAssProcessoClasse() {
		return idAssProcessoClasse;
	}

	public void setIdAssProcessoClasse(long idAssProcessoClasse) {
		this.idAssProcessoClasse = idAssProcessoClasse;
	}

	public long getIdProcesso() {
		return idProcesso;
	}

	public void setIdProcesso(long idProcesso) {
		this.idProcesso = idProcesso;
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
