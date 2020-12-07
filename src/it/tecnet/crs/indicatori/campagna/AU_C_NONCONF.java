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
@Table(name = "AU_C_NONCONF")
public class AU_C_NONCONF {

	@Id
	@SequenceGenerator(name = "AU_ASS_VERBALE_CAMPAGNA_GENERATOR", sequenceName = "ID_ASS_VERB_CMPGN_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AU_ASS_VERBALE_CAMPAGNA_GENERATOR")
	private Long id;

	@Column(name = "ID_C_CAMPAGNA")
	private Long ID_C_CAMPAGNA;

	@Column(name = "ID_M_NONCONF")
	private Long ID_M_NONCONF;
	
	@Column(name = "PESO_NON_CONF")
	private Double PESO_NON_CONF;
	
	@Column(name = "INCC")
	private Double INCC;
	
	@Column(name = "PERC_SU_PS")
	private Double PERC_SU_PS;

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

	public Long getID_M_NONCONF() {
		return ID_M_NONCONF;
	}

	public void setID_M_NONCONF(Long id_m_nonconf) {
		ID_M_NONCONF = id_m_nonconf;
	}

	public Double getPESO_NON_CONF() {
		return PESO_NON_CONF;
	}

	public void setPESO_NON_CONF(Double peso_non_conf) {
		PESO_NON_CONF = peso_non_conf;
	}

	public Double getINCC() {
		return INCC;
	}

	public void setINCC(Double incc) {
		INCC = incc;
	}

	public Double getPERC_SU_PS() {
		return PERC_SU_PS;
	}

	public void setPERC_SU_PS(Double perc_su_ps) {
		PERC_SU_PS = perc_su_ps;
	}


	

	
	
}
