package it.tecnet.crs.audit.web.beans.tassonomie.sinonimo;

import java.util.Date;

public class ModelSinonimoDataTableDto {

	private Long idSinonimo;
	private Long idGlossario;
	private String sinonimo;
	private String descSinonimo;
	private Date dataInizio;
	private Date dataFine;

	public Long getIdSinonimo() {
		return idSinonimo;
	}

	public void setIdSinonimo(Long idSinonimo) {
		this.idSinonimo = idSinonimo;
	}

	public Long getIdGlossario() {
		return idGlossario;
	}

	public void setIdGlossario(Long idGlossario) {
		this.idGlossario = idGlossario;
	}

	public String getSinonimo() {
		return sinonimo;
	}

	public void setSinonimo(String sinonimo) {
		this.sinonimo = sinonimo;
	}

	public String getDescSinonimo() {
		return descSinonimo;
	}

	public void setDescSinonimo(String descSinonimo) {
		this.descSinonimo = descSinonimo;
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
