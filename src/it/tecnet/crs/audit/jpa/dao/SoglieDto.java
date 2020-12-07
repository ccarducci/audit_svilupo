package it.tecnet.crs.audit.jpa.dao;

public class SoglieDto {
	private Long ID_M_NONCONF;
	private Double SOGLIA;
	
	public Long getID_M_NONCONF() {
		return ID_M_NONCONF;
	}
	public void setID_M_NONCONF(Long id_m_nonconf) {
		ID_M_NONCONF = id_m_nonconf;
	}
	public Double getSOGLIA() {
		return SOGLIA;
	}
	public void setSOGLIA(Double soglia) {
		SOGLIA = soglia;
	}
	
	
}
