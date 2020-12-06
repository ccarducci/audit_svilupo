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
@Table(name="CRS_ASS_AC_CLASSE")
public class CrsAssAcClasse implements Serializable {
	
	@Id
	@SequenceGenerator(name="CRS_ASS_AC_CLASSE_GENERATOR", sequenceName="ID_ASS_AC_CLASSE_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CRS_ASS_AC_CLASSE_GENERATOR")
	@Column(name="ID_ASS_AC_CLASSE")
	private long idAssAcClasse;
	
	@Column(name="ID_ATTIVITA_COMPONENTE")
	private long idAttivitaComponente;
	
	@Column(name="ID_NORMATIVA")
	private long idNormativa;
	
	@Column(name="ID_TIPO")
	private long idTipo;

	private static final long serialVersionUID = 1L;

	public CrsAssAcClasse() {
		super();
	}

	public long getIdAssAcClasse() {
		return idAssAcClasse;
	}

	public void setIdAssAcClasse(long idAssAcClasse) {
		this.idAssAcClasse = idAssAcClasse;
	}

	public long getIdAttivitaComponente() {
		return idAttivitaComponente;
	}

	public void setIdAttivitaComponente(long idAttivitaComponente) {
		this.idAttivitaComponente = idAttivitaComponente;
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
