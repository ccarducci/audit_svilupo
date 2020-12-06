package it.tecnet.crs.componenti.jpa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="CRS_ASS_MESSAGGI_INPS_DOCUMENTI")
public class CrsAssMessaggiInpsDocumenti  implements Serializable {
	

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CRS_ASS_MESSAGGI_INPS_DOCUMENTI_GENERATOR", sequenceName="CRS_ASS_MESSAGGI_INPS_DOCUMENTI_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CRS_ASS_MESSAGGI_INPS_DOCUMENTI_GENERATOR")
	private long id;
	
	@Column(name="ID_MESSAGGI_INPS")
	private long idMessaggiInps;
	
	@Column(name="ID_DOCUMENTO")
	private long idDocumento;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public void setIdMessaggiInps(long idMessaggiInps) {
		this.idMessaggiInps = idMessaggiInps;
	}

	public long getIdMessaggiInps() {
		return idMessaggiInps;
	}

	public long getIdDocumento() {
		return idDocumento;
	}

	public void setIdDocumento(long idDocumento) {
		this.idDocumento = idDocumento;
	}

	
	
	
}
