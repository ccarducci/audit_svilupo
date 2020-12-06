package it.tecnet.crs.audit.web.beans.tassonomie.ambito;

import java.util.Date;

public class ModelAmbitoDataTableDto {

	private Long idAmbito;
	private String ambito;
	private String descAmbito;
	private Date dataInizio;
	private Date dataFine;
	private Long idGlossario;
	private Long idEntita;
	private String tipoAmbito;

	public Long getIdAmbito() {
		return idAmbito;
	}

	public void setIdAmbito(Long idAmbito) {
		this.idAmbito = idAmbito;
	}

	public String getAmbito() {
		return ambito;
	}

	public void setAmbito(String ambito) {
		this.ambito = ambito;
	}

	public String getDescAmbito() {
		return descAmbito;
	}

	public void setDescAmbito(String descAmbito) {
		this.descAmbito = descAmbito;
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

	public Long getIdGlossario() {
		return idGlossario;
	}

	public void setIdGlossario(Long idGlossario) {
		this.idGlossario = idGlossario;
	}

	public Long getIdEntita() {
		return idEntita;
	}

	public void setIdEntita(Long idEntita) {
		this.idEntita = idEntita;
	}

	public String getTipoAmbito() {
		return tipoAmbito;
	}

	public void setTipoAmbito(String tipoAmbito) {
		this.tipoAmbito = tipoAmbito;
	}

}
