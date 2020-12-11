package it.tecnet.crs.report.jpa.dao;

import java.util.Date;

import com.ibm.math.BigDecimal;

public class CampagnaInfoDto {
	private Long idCampagna;
	private Date dataInzio;
	private Date dataFine;
	private Integer numPratiche;
	
	public Long getIdCampagna() {
		return idCampagna;
	}
	public void setIdCampagna(Long idCampagna) {
		this.idCampagna = idCampagna;
	}
	public Date getDataInzio() {
		return dataInzio;
	}
	public void setDataInzio(Date dataInzio) {
		this.dataInzio = dataInzio;
	}
	public Date getDataFine() {
		return dataFine;
	}
	public void setDataFine(Date dataFine) {
		this.dataFine = dataFine;
	}
	public Integer getNumPratiche() {
		return numPratiche;
	}
	public void setNumPratiche(Integer numPratiche) {
		this.numPratiche = numPratiche;
	}

	
}
