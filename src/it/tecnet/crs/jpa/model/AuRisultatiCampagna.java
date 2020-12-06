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
@Table(name="AU_RISULTATI_CAMPAGNA")
public class AuRisultatiCampagna implements Serializable {
	@Id
	@SequenceGenerator(name="AU_RISULTATI_CAMPAGNA_GENERATOR", sequenceName="ID_RISULTATICAMPAGNA_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="AU_RISULTATI_CAMPAGNA_GENERATOR")
	@Column(name="ID_RIS_CAMPAGNA")
	private long idRisCampagna;

	private String res1;

	private String res2;

	private String res3;

	private String res4;

	@Column(name="ID_CAMPAGNA")
	private long idCampagna;
	
	@Column(name="ID_REGOLA_CAMP")
	private long idRegolaCamp;

	private static final long serialVersionUID = 1L;

	public AuRisultatiCampagna() {
		super();
	}

	public long getIdRisCampagna() {
		return this.idRisCampagna;
	}

	public void setIdRisCampagna(long idRisCampagna) {
		this.idRisCampagna = idRisCampagna;
	}

	public String getRes1() {
		return this.res1;
	}

	public void setRes1(String res1) {
		this.res1 = res1;
	}

	public String getRes2() {
		return this.res2;
	}

	public void setRes2(String res2) {
		this.res2 = res2;
	}

	public String getRes3() {
		return this.res3;
	}

	public void setRes3(String res3) {
		this.res3 = res3;
	}

	public String getRes4() {
		return this.res4;
	}

	public void setRes4(String res4) {
		this.res4 = res4;
	}

	public long getIdRegolaCamp() {
		return this.idRegolaCamp;
	}

	public void setIdRegolaCamp(long idRegolaCamp) {
		this.idRegolaCamp = idRegolaCamp;
	}

	public long getIdCampagna() {
		return idCampagna;
	}

	public void setIdCampagna(long idCampagna) {
		this.idCampagna = idCampagna;
	}

}
