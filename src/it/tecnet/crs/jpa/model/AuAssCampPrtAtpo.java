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
@Table(name="AU_ASS_CAMP_PRT_ATPO")
public class AuAssCampPrtAtpo implements Serializable {
	@Id
	@SequenceGenerator(name="AU_ASS_CAMP_PRT_ATPO_GENERATOR", sequenceName="AU_ASS_CAMP_PRT_ATPO_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="AU_ASS_CAMP_PRT_ATPO_GENERATOR")
	@Column(name="ID_ASS_CAMP_PRT_ATPO")
	private long idAssCampPrtAtpo;
	
	@Column(name="ID_CAMPIONE")
	private long idCampione;
	

	@Column(name="ID_PRATICHE_SISCO")
	private long idPraticheSisco;


	public long getIdAssCampPrtAtpo() {
		return idAssCampPrtAtpo;
	}


	public void setIdAssCampPrtAtpo(long idAssCampPrtAtpo) {
		this.idAssCampPrtAtpo = idAssCampPrtAtpo;
	}


	public long getIdCampione() {
		return idCampione;
	}


	public void setIdCampione(long idCampione) {
		this.idCampione = idCampione;
	}


	public long getIdPraticheSisco() {
		return idPraticheSisco;
	}


	public void setIdPraticheSisco(long idPraticheSisco) {
		this.idPraticheSisco = idPraticheSisco;
	}

}
