package it.tecnet.crs.jpa.model;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="AU_CAMPIONE")
public class AuCampione implements Serializable {
	@Id
	@SequenceGenerator(name="AU_CAMPIONE_GENERATOR", sequenceName="ID_CAMPIONE_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="AU_CAMPIONE_GENERATOR")
	@Column(name="ID_CAMPIONE")
	private long idCampione;

	private String descrizione;
	
	@Column(name="ID_SESSIONE")
	private long idSessione;


	private static final long serialVersionUID = 1L;

	public AuCampione() {
		super();
	}

	public long getIdCampione() {
		return this.idCampione;
	}

	public void setIdCampione(long idCampione) {
		this.idCampione = idCampione;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public long getIdSessione() {
		return idSessione;
	}

	public void setIdSessione(long idSessione) {
		this.idSessione = idSessione;
	}

	
}
