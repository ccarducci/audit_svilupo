package it.tecnet.crs.indicatori.campagna;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "AU_C_RISCHIO")
public class AU_C_RISCHIO {

	@Id
	@SequenceGenerator(name = "AU_ASS_VERBALE_CAMPAGNA_GENERATOR", sequenceName = "ID_ASS_VERB_CMPGN_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AU_ASS_VERBALE_CAMPAGNA_GENERATOR")
	private Long id;

	@Column(name = "ID_C_CAMPAGNA")
	private Long ID_C_CAMPAGNA;

	@Column(name = "ID_M_RISCHIO")
	private Long ID_M_RISCHIO;

	@Column(name = "DATA_INIZIO")
	private Date DATA_INIZIO;
	
	@Column(name = "DATA_FINE")
	private Date DATA_FINE;

	@Column(name = "PESO_RISCHIO")
	private Double PESO_RISCHIO;
	
	@Column(name = "SU_TOT_PS_PERC")
	private Double SU_TOT_PS_PERC;
	
	@Column(name = "NUM")
	private Integer NUM;

	@Column(name = "SU_TOT_PERC")
	private Double SU_TOT_PERC;
	
	@Column(name = "SU_PS_PERC")
	private Double SU_PS_PERC;
	
	@Column(name = "IMPORTO")
	private Double IMPORTO;
	
	@Column(name = "NUM_PS")
	private Double NUM_PS;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getID_C_CAMPAGNA() {
		return ID_C_CAMPAGNA;
	}

	public void setID_C_CAMPAGNA(Long id_c_campagna) {
		ID_C_CAMPAGNA = id_c_campagna;
	}

	public Long getID_M_RISCHIO() {
		return ID_M_RISCHIO;
	}

	public void setID_M_RISCHIO(Long id_m_rischio) {
		ID_M_RISCHIO = id_m_rischio;
	}

	public Date getDATA_INIZIO() {
		return DATA_INIZIO;
	}

	public void setDATA_INIZIO(Date data_inizio) {
		DATA_INIZIO = data_inizio;
	}

	public Date getDATA_FINE() {
		return DATA_FINE;
	}

	public void setDATA_FINE(Date data_fine) {
		DATA_FINE = data_fine;
	}

	public Double getPESO_RISCHIO() {
		return PESO_RISCHIO;
	}

	public void setPESO_RISCHIO(Double peso_rischio) {
		PESO_RISCHIO = peso_rischio;
	}

	public Double getSU_TOT_PS_PERC() {
		return SU_TOT_PS_PERC;
	}

	public void setSU_TOT_PS_PERC(Double su_tot_ps_perc) {
		SU_TOT_PS_PERC = su_tot_ps_perc;
	}

	public Integer getNUM() {
		return NUM;
	}

	public void setNUM(Integer num) {
		NUM = num;
	}

	public Double getSU_TOT_PERC() {
		return SU_TOT_PERC;
	}

	public void setSU_TOT_PERC(Double su_tot_perc) {
		SU_TOT_PERC = su_tot_perc;
	}

	public Double getSU_PS_PERC() {
		return SU_PS_PERC;
	}

	public void setSU_PS_PERC(Double su_ps_perc) {
		SU_PS_PERC = su_ps_perc;
	}

	public Double getIMPORTO() {
		return IMPORTO;
	}

	public void setIMPORTO(Double importo) {
		IMPORTO = importo;
	}

	public Double getNUM_PS() {
		return NUM_PS;
	}

	public void setNUM_PS(Double num_ps) {
		NUM_PS = num_ps;
	}

	
	
}
