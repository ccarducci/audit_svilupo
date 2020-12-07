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
@Table(name = "AU_C_RISESPR")
public class AU_C_RISESPR {

	@Id
	@SequenceGenerator(name = "AU_ASS_VERBALE_CAMPAGNA_GENERATOR", sequenceName = "ID_ASS_VERB_CMPGN_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AU_ASS_VERBALE_CAMPAGNA_GENERATOR")
	private Long id;

	@Column(name = "ID_C_CAMPAGNA")
	private Long ID_C_CAMPAGNA;

	@Column(name = "ID_M_RISCHIO")
	private Long ID_M_RISCHIO;
	
	@Column(name = "ID_M_RISESPR")
	private Long ID_M_RISESPR;
	
	@Column(name = "NUM1")
	private Integer NUM1;
	
	@Column(name = "NUM_RS")
	private Integer NUM_RS;
	
	@Column(name = "SU_TOT")
	private Double SU_TOT;
	
	@Column(name = "IMPORTO")
	private Double IMPORTO;

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

	public Long getID_M_RISESPR() {
		return ID_M_RISESPR;
	}

	public void setID_M_RISESPR(Long id_m_risespr) {
		ID_M_RISESPR = id_m_risespr;
	}

	public Integer getNUM1() {
		return NUM1;
	}

	public void setNUM1(Integer num1) {
		NUM1 = num1;
	}

	public Integer getNUM_RS() {
		return NUM_RS;
	}

	public void setNUM_RS(Integer num_rs) {
		NUM_RS = num_rs;
	}

	public Double getSU_TOT() {
		return SU_TOT;
	}

	public void setSU_TOT(Double su_tot) {
		SU_TOT = su_tot;
	}

	public Double getIMPORTO() {
		return IMPORTO;
	}

	public void setIMPORTO(Double importo) {
		IMPORTO = importo;
	}



	
}
