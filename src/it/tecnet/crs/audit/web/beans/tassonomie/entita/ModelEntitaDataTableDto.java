package it.tecnet.crs.audit.web.beans.tassonomie.entita;

import java.util.Date;

public class ModelEntitaDataTableDto {

	private Long idEntita;
	private String entita;
	private String descEntita;
	private Date dataInizio;
	private Date dataFine;
	private String tipoEntita;

	public Long getIdEntita() {
		return idEntita;
	}

	public void setIdEntita(Long idEntita) {
		this.idEntita = idEntita;
	}

	public String getEntita() {
		return entita;
	}

	public void setEntita(String entita) {
		this.entita = entita;
	}

	public String getDescEntita() {
		return descEntita;
	}

	public void setDescEntita(String descEntita) {
		this.descEntita = descEntita;
	}

	public Date getDataInizio() {
		return dataInizio;
	}

	public void setDataInizio(Date dataInizio) {
		this.dataInizio = dataInizio;
	}

	public Date getDataFine() {
		return dataFine;
	}

	public void setDataFine(Date dataFine) {
		this.dataFine = dataFine;
	}

	public String getTipoEntita() {
		return tipoEntita;
	}

	public void setTipoEntita(String tipoEntita) {
		this.tipoEntita = tipoEntita;
	}

}
