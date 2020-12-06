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
@Table(name="AU_RISULTATI_CAMPIONE")
public class AuRisultatiCampione implements Serializable {
	@Id
	@SequenceGenerator(name="AU_RISULTATI_CAMPIONE_GENERATOR", sequenceName="ID_RISULTATICAMPIONE_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="AU_RISULTATI_CAMPIONE_GENERATOR")
	@Column(name="ID_RIS_CAMPIONE")
	private long idRisCampione;

	@Column(name="RES_1")
	private String res1;

	@Column(name="RES_2")
	private String res2;

	@Column(name="RES_3")
	private String res3;

	@Column(name="RES_4")
	private String res4;

	@Column(name="ID_REGOLA_CAMP")
	private long idRegolaCamp;

	@Column(name="ID_CAMPIONE")
	private long idCampione;

	private static final long serialVersionUID = 1L;

	public AuRisultatiCampione() {
		super();
	}

	public long getIdRisCampione() {
		return this.idRisCampione;
	}

	public void setIdRisCampione(long idRisCampione) {
		this.idRisCampione = idRisCampione;
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

	public long getIdCampione() {
		return this.idCampione;
	}

	public void setIdCampione(long idCampione) {
		this.idCampione = idCampione;
	}

}
