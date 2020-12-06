package it.tecnet.crs.audit.jpa.dao;

import java.util.Date;

public class CampagnaDto {
	private Integer idCampagna;
	private Long ID_M_NONCONF;
	private Long ID_M_VARCOMP;
	private Date DATA_INIZIO;
	private Date DATA_FINE;
	private Long ID_FASE;
	private Integer NUM;
	private Double PERC_PESATA;
	public Integer getIdCampagna() {
		return idCampagna;
	}
	public void setIdCampagna(Integer idCampagna) {
		this.idCampagna = idCampagna;
	}
	public Long getID_M_NONCONF() {
		return ID_M_NONCONF;
	}
	public void setID_M_NONCONF(Long id_m_nonconf) {
		ID_M_NONCONF = id_m_nonconf;
	}
	public Long getID_M_VARCOMP() {
		return ID_M_VARCOMP;
	}
	public void setID_M_VARCOMP(Long id_m_varcomp) {
		ID_M_VARCOMP = id_m_varcomp;
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
	public Long getID_FASE() {
		return ID_FASE;
	}
	public void setID_FASE(Long id_fase) {
		ID_FASE = id_fase;
	}
	public Integer getNUM() {
		return NUM;
	}
	public void setNUM(Integer num) {
		NUM = num;
	}
	public Double getPERC_PESATA() {
		return PERC_PESATA;
	}
	public void setPERC_PESATA(Double perc_pesata) {
		PERC_PESATA = perc_pesata;
	}


}
