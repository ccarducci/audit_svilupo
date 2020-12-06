package it.tecnet.crs.jpa.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="AU_ASS_CAMP_VERB")
public class AuAssCampVerb implements Serializable {
	@Id
	@SequenceGenerator(name="AU_ASS_CAMP_VERB_GENERATOR", sequenceName="ID_ASSCAMPVERB_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="AU_ASS_CAMP_VERB_GENERATOR")
	@Column(name="ID_ASS_CV")
	private long idAssCv;
	
	@Column(name="ID_CAMPIONE")
	private long idCampione;
	

	@Column(name="ID_VERBALE")
	private long idVerbale;

	public long getIdCampione() {
		return idCampione;
	}

	public void setIdCampione(long idCampione) {
		this.idCampione = idCampione;
	}

	public long getIdVerbale() {
		return idVerbale;
	}

	public void setIdVerbale(long idVerbale) {
		this.idVerbale = idVerbale;
	}


	private static final long serialVersionUID = 1L;

	public AuAssCampVerb() {
		super();
	}

	public long getIdAssCv() {
		return this.idAssCv;
	}

	public void setIdAssCv(long idAssCv) {
		this.idAssCv = idAssCv;
	}

	

}
