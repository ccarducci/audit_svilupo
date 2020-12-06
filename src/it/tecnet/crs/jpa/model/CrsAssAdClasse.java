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
@Table(name="CRS_ASS_AD_CLASSE")
public class CrsAssAdClasse implements Serializable {
	
	@Id
	@SequenceGenerator(name="CRS_ASS_AD_CLASSE_GENERATOR", sequenceName="ID_ASS_AD_CLASSE_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CRS_ASS_AD_CLASSE_GENERATOR")
	@Column(name="ID_ASS_AD_CLASSE")
	private long idAssAdClasse;
	
	@Column(name="ID_ATTIVITA_DETTAGLIO")
	private long idAttivitaDettaglio;
	
	@Column(name="ID_NORMATIVA")
	private long idNormativa;
	
	@Column(name="ID_TIPO")
	private long idTipo;

	private static final long serialVersionUID = 1L;

	public CrsAssAdClasse() {
		super();
	}

	public long getIdAssAdClasse() {
		return idAssAdClasse;
	}

	public void setIdAssAdClasse(long idAssAdClasse) {
		this.idAssAdClasse = idAssAdClasse;
	}

	public long getIdAttivitaDettaglio() {
		return idAttivitaDettaglio;
	}

	public void setIdAttivitaDettaglio(long idAttivitaDettaglio) {
		this.idAttivitaDettaglio = idAttivitaDettaglio;
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
