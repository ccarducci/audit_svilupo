package it.tecnet.crs.audit.web.beans.tassonomie.glossario;

import java.util.Date;

public class ModelGlossarioDataTableDto {
	
	private Long idGlossario;
	private String parola;
	private String descParola;
	private Date dataInizio;
	private Date dataFine;

	public Long getIdGlossario() {
		return idGlossario;
	}

	public void setIdGlossario(Long idGlossario) {
		this.idGlossario = idGlossario;
	}

	public String getParola() {
		return parola;
	}

	public void setParola(String parola) {
		this.parola = parola;
	}

	public String getDescParola() {
		return descParola;
	}

	public void setDescParola(String descParola) {
		this.descParola = descParola;
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

}
