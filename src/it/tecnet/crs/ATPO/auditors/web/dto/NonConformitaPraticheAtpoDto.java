package it.tecnet.crs.ATPO.auditors.web.dto;

import java.math.BigDecimal;
import java.util.Date;

public class NonConformitaPraticheAtpoDto {

	private long idNonConf;
	private int ordinamento;
	private String descrizioneFase;
	private String descrNonConformita;
	private String varianteComportamentale;//(S_PRATICA_VAR_COMP)
	private String descrizioneVarComp;
	private BigDecimal pesoVarComp;
	private String colore;
	private Date dataAttribuzione;
	
	public long getIdNonConf() {
		return idNonConf;
	}
	public void setIdNonConf(long idNonConf) {
		this.idNonConf = idNonConf;
	}
	public String getDescrizioneFase() {
		return descrizioneFase;
	}
	public void setDescrizioneFase(String descrizioneFase) {
		this.descrizioneFase = descrizioneFase;
	}
	public String getDescrNonConformita() {
		return descrNonConformita;
	}
	public void setDescrNonConformita(String descrNonConformita) {
		this.descrNonConformita = descrNonConformita;
	}
	public String getVarianteComportamentale() {
		return varianteComportamentale;
	}
	public void setVarianteComportamentale(String varianteComportamentale) {
		this.varianteComportamentale = varianteComportamentale;
	}
	public String getDescrizioneVarComp() {
		return descrizioneVarComp;
	}
	public void setDescrizioneVarComp(String descrizioneVarComp) {
		this.descrizioneVarComp = descrizioneVarComp;
	}
	public BigDecimal getPesoVarComp() {
		return pesoVarComp;
	}
	public void setPesoVarComp(BigDecimal pesoVarComp) {
		this.pesoVarComp = pesoVarComp;
	}
	public String getColore() {
		return colore;
	}
	public void setColore(String colore) {
		this.colore = colore;
	}
	public Date getDataAttribuzione() {
		return dataAttribuzione;
	}
	public void setDataAttribuzione(Date dataAttribuzione) {
		this.dataAttribuzione = dataAttribuzione;
	}
	public void setOrdinamento(int ordinamento) {
		this.ordinamento = ordinamento;
	}
	public int getOrdinamento() {
		return ordinamento;
	}
	
	
	
	
}