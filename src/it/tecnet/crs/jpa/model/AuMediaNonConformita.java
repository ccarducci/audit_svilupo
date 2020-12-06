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
@Table(name="AU_MEDIA_NON_CONFORMITA")
public class AuMediaNonConformita implements Serializable {
	@Id
	@SequenceGenerator(name="AU_MEDIA_NON_CONFORMITA_GENERATOR", sequenceName="ID_MEDIANONCONFORMITA_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="AU_MEDIA_NON_CONFORMITA_GENERATOR")
	@Column(name="ID_MEDIA_NON_CONFORMITA")
	private long idMediaNonConformita;

	
	@ManyToOne
	@JoinColumn(name="ID_NON_CONFORMITA")
	private AuNonConformita idNonConformita;

	private static final long serialVersionUID = 1L;

	public AuMediaNonConformita() {
		super();
	}

	public long getIdMediaNonConformita() {
		return idMediaNonConformita;
	}

	public void setIdMediaNonConformita(long idMediaNonConformita) {
		this.idMediaNonConformita = idMediaNonConformita;
	}

	public AuNonConformita getIdNonConformita() {
		return idNonConformita;
	}

	public void setIdNonConformita(AuNonConformita idNonConformita) {
		this.idNonConformita = idNonConformita;
	}



}
