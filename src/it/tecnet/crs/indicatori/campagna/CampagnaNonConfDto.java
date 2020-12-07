package it.tecnet.crs.indicatori.campagna;

import java.util.Date;

public class CampagnaNonConfDto {
	private Long ID_CAMPAGNA; 
	private Long ID_M_NONCONF; 
	private Date DATA_INIZIO; 
	private Date DATA_FINE;
	private String CODICE; 
	private Double PESO_NONCONF;
	
	public Long getID_CAMPAGNA() {
		return ID_CAMPAGNA;
	}
	public void setID_CAMPAGNA(Long iD_CAMPAGNA) {
		ID_CAMPAGNA = iD_CAMPAGNA;
	}
	public Long getID_M_NONCONF() {
		return ID_M_NONCONF;
	}
	public void setID_M_NONCONF(Long iD_M_NONCONF) {
		ID_M_NONCONF = iD_M_NONCONF;
	}
	public Date getDATA_INIZIO() {
		return DATA_INIZIO;
	}
	public void setDATA_INIZIO(Date dATA_INIZIO) {
		DATA_INIZIO = dATA_INIZIO;
	}
	public Date getDATA_FINE() {
		return DATA_FINE;
	}
	public void setDATA_FINE(Date dATA_FINE) {
		DATA_FINE = dATA_FINE;
	}
	public String getCODICE() {
		return CODICE;
	}
	public void setCODICE(String cODICE) {
		CODICE = cODICE;
	}
	public Double getPESO_NONCONF() {
		return PESO_NONCONF;
	}
	public void setPESO_NONCONF(Double pESO_NONCONF) {
		PESO_NONCONF = pESO_NONCONF;
	}
	

}
