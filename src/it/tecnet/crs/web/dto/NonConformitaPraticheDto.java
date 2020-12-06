package it.tecnet.crs.web.dto;


public class NonConformitaPraticheDto {
	
	private long idVerbale;

	private String numeroProtocollo;
	
	private String codiceFiscale;
	
	private String descRSociale;
	
	private String descFase;
	
	//Codice non conformita
	private String codNonConf;
	
	private String descNonConf;
	
	private String codVarComp;
	
	private String descVarComp;
	
	private String pesoVarComp;
	
	private String colore;
	
	private String dataAttribuzione;

	public long getIdVerbale() {
		return idVerbale;
	}

	public void setIdVerbale(long idVerbale) {
		this.idVerbale = idVerbale;
	}

	public String getNumeroProtocollo() {
		return numeroProtocollo;
	}

	public void setNumeroProtocollo(String numeroProtocollo) {
		this.numeroProtocollo = numeroProtocollo;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public String getDescRSociale() {
		return descRSociale;
	}

	public void setDescRSociale(String descRSociale) {
		this.descRSociale = descRSociale;
	}

	public String getDescFase() {
		return descFase;
	}

	public void setDescFase(String descFase) {
		this.descFase = descFase;
	}

	public String getCodNonConf() {
		return codNonConf;
	}

	public void setCodNonConf(String codNonConf) {
		this.codNonConf = codNonConf;
	}

	public String getDescNonConf() {
		return descNonConf;
	}

	public void setDescNonConf(String descNonConf) {
		this.descNonConf = descNonConf;
	}

	public String getCodVarComp() {
		return codVarComp;
	}

	public void setCodVarComp(String codVarComp) {
		this.codVarComp = codVarComp;
	}

	public String getDescVarComp() {
		return descVarComp;
	}

	public void setDescVarComp(String descVarComp) {
		this.descVarComp = descVarComp;
	}

	public String getPesoVarComp() {
		return pesoVarComp;
	}

	public void setPesoVarComp(String pesoVarComp) {
		this.pesoVarComp = pesoVarComp;
	}

	public String getColore() {
		return colore;
	}

	public void setColore(String colore) {
		this.colore = colore;
	}

	public String getDataAttribuzione() {
		return dataAttribuzione;
	}

	public void setDataAttribuzione(String dataAttribuzione) {
		this.dataAttribuzione = dataAttribuzione;
	}
	
	

}
