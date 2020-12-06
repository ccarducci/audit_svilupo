package it.tecnet.crs.audit.model;

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
@Table(name="AU_ASS_VERBALE_CAMPAGNA")
public class AuAssVerbaleCampagna implements Serializable {
	@Id
	@SequenceGenerator(name="AU_ASS_VERBALE_CAMPAGNA_GENERATOR", sequenceName="ID_ASS_VERB_CMPGN_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="AU_ASS_VERBALE_CAMPAGNA_GENERATOR")
	@Column(name="ID_ASS_VERB_CMPGN")
	private long idAssVerbCmpgn;

	@Column(name="ID_CAMPAGNA")
	private long idCampagna;
	
	@Column(name="ID_VERBALE")
	private long idVerbale;

	public long getIdAssVerbCmpgn() {
		return idAssVerbCmpgn;
	}

	public void setIdAssVerbCmpgn(long idAssVerbCmpgn) {
		this.idAssVerbCmpgn = idAssVerbCmpgn;
	}

	public long getIdCampagna() {
		return idCampagna;
	}

	public void setIdCampagna(long idCampagna) {
		this.idCampagna = idCampagna;
	}

	public long getIdVerbale() {
		return idVerbale;
	}

	public void setIdVerbale(long idVerbale) {
		this.idVerbale = idVerbale;
	}
	
}