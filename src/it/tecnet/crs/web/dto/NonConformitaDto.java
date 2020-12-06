package it.tecnet.crs.web.dto;

import java.io.IOException;
import java.math.BigDecimal;

import java.io.Serializable;



public class NonConformitaDto {
	
	
	public Long idNonConformita;
	public String descrizione;
	public String descrizioneNonConformita;
	public String fase;
	public BigDecimal media;
	private String rischio;
	private BigDecimal rischioEconomico;
	

	public Long getIdNonConformita() {
		return idNonConformita;
	}
	public void setIdNonConformita(long idNonConformita) {
		this.idNonConformita = idNonConformita;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getDescrizioneNonConformita() {
		return descrizioneNonConformita;
	}
	public void setDescrizioneNonConformita(String descrizioneNonConformita) {
		this.descrizioneNonConformita = descrizioneNonConformita;
	}
	public String getFase() {
		return fase;
	}
	public void setFase(String fase) {
		this.fase = fase;
	}
	public BigDecimal getMedia() {
		return media;
	}
	public void setMedia(BigDecimal bigDecimal) {
		this.media = bigDecimal;
	}
	public void setRischio(String rischio) {
		this.rischio = rischio;
	}
	public String getRischio() {
		return rischio;
	}
	public void setRischioEconomico(BigDecimal rischioEconomico) {
		this.rischioEconomico = rischioEconomico;
	}
	public BigDecimal getRischioEconomico() {
		return rischioEconomico;
	}


	
}
