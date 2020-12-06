package it.tecnet.crs.componenti.web.bean;

import java.util.Date;

public class GlossarioModel {
	// GLOSSARIO
	private Long glossarioID;
	private String glossarioParola;
	private String glossarioDescrizioneParola;
	private Date glossarioDataInizio;
	private Date glossarioDataFine;

	// ENTITA
	private Long entitaID;
	private String entita;
	private String entitaDescrizione;
	private Date entitaDataInizio;
	private Date entitaDataFine;
	private String entitaTipo; // M, T, A

	// SINONIMO
	private Long sinonimoID;
	private String sinonimo;
	private String sinonimoDesr;
	private Date sinonimoDataInizio;
	private Date sinonimoDataFine;

	// AMBITO
	private Long ambitoID;
	private String ambito;
	private String ambitoDescrizione;
	private Date ambitoDataInizio;
	private Date ambitoDataFine;
	private String ambitoTipo; // M, T, A

	// RISPOSTA JSON
	private Integer status;
	private Integer error;
	private String errorDescription;

	public Long getGlossarioID() {
		return glossarioID;
	}

	public void setGlossarioID(Long glossarioID) {
		this.glossarioID = glossarioID;
	}

	public String getGlossarioParola() {
		return glossarioParola;
	}

	public void setGlossarioParola(String glossarioParola) {
		this.glossarioParola = glossarioParola;
	}

	public String getGlossarioDescrizioneParola() {
		return glossarioDescrizioneParola;
	}

	public void setGlossarioDescrizioneParola(String glossarioDescrizioneParola) {
		this.glossarioDescrizioneParola = glossarioDescrizioneParola;
	}

	public Date getGlossarioDataInizio() {
		return glossarioDataInizio;
	}

	public void setGlossarioDataInizio(Date glossarioDataInizio) {
		this.glossarioDataInizio = glossarioDataInizio;
	}

	public Date getGlossarioDataFine() {
		return glossarioDataFine;
	}

	public void setGlossarioDataFine(Date glossarioDataFine) {
		this.glossarioDataFine = glossarioDataFine;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getError() {
		return error;
	}

	public void setError(Integer error) {
		this.error = error;
	}

	public String getErrorDescription() {
		return errorDescription;
	}

	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}

	public Long getEntitaID() {
		return entitaID;
	}

	public void setEntitaID(Long entitaID) {
		this.entitaID = entitaID;
	}

	public String getEntita() {
		return entita;
	}

	public void setEntita(String entita) {
		this.entita = entita;
	}

	public String getEntitaDescrizione() {
		return entitaDescrizione;
	}

	public void setEntitaDescrizione(String entitaDescrizione) {
		this.entitaDescrizione = entitaDescrizione;
	}

	public Date getEntitaDataInizio() {
		return entitaDataInizio;
	}

	public void setEntitaDataInizio(Date entitaDataInizio) {
		this.entitaDataInizio = entitaDataInizio;
	}

	public Date getEntitaDataFine() {
		return entitaDataFine;
	}

	public void setEntitaDataFine(Date entitaDataFine) {
		this.entitaDataFine = entitaDataFine;
	}

	public String getEntitaTipo() {
		return entitaTipo;
	}

	public void setEntitaTipo(String entitaTipo) {
		this.entitaTipo = entitaTipo;
	}

	public Long getSinonimoID() {
		return sinonimoID;
	}

	public void setSinonimoID(Long sinonimoID) {
		this.sinonimoID = sinonimoID;
	}

	public String getSinonimo() {
		return sinonimo;
	}

	public void setSinonimo(String sinonimo) {
		this.sinonimo = sinonimo;
	}

	public Date getSinonimoDataInizio() {
		return sinonimoDataInizio;
	}

	public void setSinonimoDataInizio(Date sinonimoDataInizio) {
		this.sinonimoDataInizio = sinonimoDataInizio;
	}

	public Date getSinonimoDataFine() {
		return sinonimoDataFine;
	}

	public void setSinonimoDataFine(Date sinonimoDataFine) {
		this.sinonimoDataFine = sinonimoDataFine;
	}

	public Long getAmbitoID() {
		return ambitoID;
	}

	public void setAmbitoID(Long ambitoID) {
		this.ambitoID = ambitoID;
	}

	public String getAmbito() {
		return ambito;
	}

	public void setAmbito(String ambito) {
		this.ambito = ambito;
	}

	public String getAmbitoDescrizione() {
		return ambitoDescrizione;
	}

	public void setAmbitoDescrizione(String ambitoDescrizione) {
		this.ambitoDescrizione = ambitoDescrizione;
	}

	public Date getAmbitoDataInizio() {
		return ambitoDataInizio;
	}

	public void setAmbitoDataInizio(Date ambitoDataInizio) {
		this.ambitoDataInizio = ambitoDataInizio;
	}

	public Date getAmbitoDataFine() {
		return ambitoDataFine;
	}

	public void setAmbitoDataFine(Date ambitoDataFine) {
		this.ambitoDataFine = ambitoDataFine;
	}

	public String getAmbitoTipo() {
		return ambitoTipo;
	}

	public void setAmbitoTipo(String ambitoTipo) {
		this.ambitoTipo = ambitoTipo;
	}

	public String getSinonimoDesr() {
		return sinonimoDesr;
	}

	public void setSinonimoDesr(String sinonimoDesr) {
		this.sinonimoDesr = sinonimoDesr;
	}

}
