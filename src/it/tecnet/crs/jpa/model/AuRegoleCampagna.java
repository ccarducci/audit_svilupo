package it.tecnet.crs.jpa.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="AU_REGOLE_CAMPAGNA")
public class AuRegoleCampagna implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="AU_REGOLE_CAMPAGNA_GENERATOR", sequenceName="ID_REGOLECAMPAGNA_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="AU_REGOLE_CAMPAGNA_GENERATOR")
	@Column(name="ID_REGOLA_CAMPAGNA")
	private long idRegolaCamp;

	private String valore1;

	private String valore2;

	private String valore3;
	
	private String valore4;

	private String valore5;

	private String descrizione;
	
	private String stato;
	

	public AuRegoleCampagna() {
		super();
	}

	public long getIdRegolaCamp() {
		return idRegolaCamp;
	}

	public void setIdRegolaCamp(long idRegolaCamp) {
		this.idRegolaCamp = idRegolaCamp;
	}

	public String getValore1() {
		return valore1;
	}

	public void setValore1(String valore1) {
		this.valore1 = valore1;
	}

	public String getValore2() {
		return valore2;
	}

	public void setValore2(String valore2) {
		this.valore2 = valore2;
	}

	public String getValore3() {
		return valore3;
	}

	public void setValore3(String valore3) {
		this.valore3 = valore3;
	}

	public String getValore4() {
		return valore4;
	}

	public void setValore4(String valore4) {
		this.valore4 = valore4;
	}

	public String getValore5() {
		return valore5;
	}

	public void setValore5(String valore5) {
		this.valore5 = valore5;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public String getStato() {
		return stato;
	}


}
