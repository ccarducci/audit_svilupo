package it.tecnet.crs.web.dto;



public class NonConformitaVerbaleDto {
	
	private long idNCV;
	
	private String vComp;

	private String rischio;

	private java.math.BigDecimal valoreCalcolato;

	private java.math.BigDecimal valoreReale;
	
	private String note;

	public long getIdNCV() {
		return idNCV;
	}

	public void setIdNCV(long idNCV) {
		this.idNCV = idNCV;
	}

	public String getVComp() {
		return vComp;
	}

	public void setVComp(String vComp) {
		this.vComp = vComp;
	}

	public String getRischio() {
		return rischio;
	}

	public void setRischio(String rischio) {
		this.rischio = rischio;
	}

	public java.math.BigDecimal getValoreCalcolato() {
		return valoreCalcolato;
	}

	public void setValoreCalcolato(java.math.BigDecimal valoreCalcolato) {
		this.valoreCalcolato = valoreCalcolato;
	}

	public java.math.BigDecimal getValoreReale() {
		return valoreReale;
	}

	public void setValoreReale(java.math.BigDecimal valoreReale) {
		this.valoreReale = valoreReale;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}



}