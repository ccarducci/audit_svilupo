package it.tecnet.crs.web.dto;

import java.math.BigDecimal;

public class AuMVarCompDto {
	private long idSVarComp;
	private long idMVarComp;
	private long idMComp;
	private String descrizioneVarComp;
	private BigDecimal percSuPs;
	private String criticita;
	private String azioniCorrettive;
	
	private int num;
	
	private BigDecimal perPesata;
	
	private String colore;
	
	public void setPercSuPs(BigDecimal percSuPs) {
		this.percSuPs = percSuPs;
	}
	public BigDecimal getPercSuPs() {
		return percSuPs;
	}
	public void setDescrizioneVarComp(String descrizioneVarComp) {
		this.descrizioneVarComp = descrizioneVarComp;
	}
	public String getDescrizioneVarComp() {
		return descrizioneVarComp;
	}
	public void setIdMComp(long idMComp) {
		this.idMComp = idMComp;
	}
	public long getIdMComp() {
		return idMComp;
	}
	public void setCriticita(String criticita) {
		this.criticita = criticita;
	}
	public String getCriticita() {
		return criticita;
	}
	public void setAzioniCorrettive(String azioniCorrettive) {
		this.azioniCorrettive = azioniCorrettive;
	}
	public String getAzioniCorrettive() {
		return azioniCorrettive;
	}
	public void setIdSVarComp(long idSVarComp) {
		this.idSVarComp = idSVarComp;
	}
	public long getIdSVarComp() {
		return idSVarComp;
	}
	public void setIdMVarComp(long idMVarComp) {
		this.idMVarComp = idMVarComp;
	}
	public long getIdMVarComp() {
		return idMVarComp;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getNum() {
		return num;
	}
	public void setPerPesata(BigDecimal perPesata) {
		this.perPesata = perPesata;
	}
	public BigDecimal getPerPesata() {
		return perPesata;
	}
	public void setColore(String colore) {
		this.colore = colore;
	}
	public String getColore() {
		return colore;
	}

}
