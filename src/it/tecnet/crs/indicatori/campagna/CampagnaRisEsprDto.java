package it.tecnet.crs.indicatori.campagna;

import java.util.Date;

import javax.persistence.Column;

public class CampagnaRisEsprDto {

	private Long ID_C_CAMPAGNA;

	private Long ID_S_RISCHIO;
	
	private Date DATA_INIZIO;
	
	private Date DATA_FINE;
	
	private Integer NUM;
	
	private Double SU_TOT;
	
	private Double SU_PS;
	
	private Double IMPORTO;

	private Long ID_M_RISCHIO;
	
	private Long ID_M_RISESPR;

	public Long getID_C_CAMPAGNA() {
		return ID_C_CAMPAGNA;
	}

	public void setID_C_CAMPAGNA(Long id_c_campagna) {
		ID_C_CAMPAGNA = id_c_campagna;
	}

	public Long getID_S_RISCHIO() {
		return ID_S_RISCHIO;
	}

	public void setID_S_RISCHIO(Long id_s_rischio) {
		ID_S_RISCHIO = id_s_rischio;
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

	public Integer getNUM() {
		return NUM;
	}

	public void setNUM(Integer num) {
		NUM = num;
	}

	public Double getSU_TOT() {
		return SU_TOT;
	}

	public void setSU_TOT(Double su_tot) {
		SU_TOT = su_tot;
	}

	public Double getSU_PS() {
		return SU_PS;
	}

	public void setSU_PS(Double su_ps) {
		SU_PS = su_ps;
	}

	public Double getIMPORTO() {
		return IMPORTO;
	}

	public void setIMPORTO(Double importo) {
		IMPORTO = importo;
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
	
	
	
}
