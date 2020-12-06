package it.tecnet.crs.web.dto;

import java.math.BigDecimal;
import java.util.Date;

public class RischiPraticheDto {
	
	
	
	//tabella AU_M_RISESPR
	private String descrizione; //er sarebbe la descrizione
	
	
	//tabella Au_S_PRATICA_RIS
	private String dataAttribuzione;
	private BigDecimal importoContrNonIncass;
	private BigDecimal importoIndebSospeso;
	private BigDecimal importoPrescritto;
	private long edu;
	private long rm;
	private long manifestazioneRischio;
	
	public String getDataAttribuzione() {
		return dataAttribuzione;
	}
	public void setDataAttribuzione(String dataAttribuzione) {
		this.dataAttribuzione = dataAttribuzione;
	}
	public BigDecimal getImportoContrNonIncass() {
		return importoContrNonIncass;
	}
	public void setImportoContrNonIncass(BigDecimal importoContrNonIncass) {
		this.importoContrNonIncass = importoContrNonIncass;
	}
	public BigDecimal getImportoIndebSospeso() {
		return importoIndebSospeso;
	}
	public void setImportoIndebSospeso(BigDecimal importoIndebSospeso) {
		this.importoIndebSospeso = importoIndebSospeso;
	}
	public BigDecimal getImportoPrescritto() {
		return importoPrescritto;
	}
	public void setImportoPrescritto(BigDecimal importoPrescritto) {
		this.importoPrescritto = importoPrescritto;
	}
	public long getEdu() {
		return edu;
	}
	public void setEdu(long edu) {
		this.edu = edu;
	}
	public long getRm() {
		return rm;
	}
	public void setRm(long rm) {
		this.rm = rm;
	}
	public long getManifestazioneRischio() {
		return manifestazioneRischio;
	}
	public void setManifestazioneRischio(long manifestazioneRischio) {
		this.manifestazioneRischio = manifestazioneRischio;
	}
	
	
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	

}
