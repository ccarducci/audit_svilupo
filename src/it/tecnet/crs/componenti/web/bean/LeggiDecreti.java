package it.tecnet.crs.componenti.web.bean;


public class LeggiDecreti extends TipoNormativaCommon{
	
	private long idLeggiDecreti;
	
	private String articolo;
	
	private Integer annoGui;
	
	private String numeroGui;
	
	

	public void setIdLeggiDecreti(long idLeggiDecreti) {
		this.idLeggiDecreti = idLeggiDecreti;
	}

	public long getIdLeggiDecreti() {
		return idLeggiDecreti;
	}

	public void setArticolo(String articolo) {
		this.articolo = articolo;
	}

	public String getArticolo() {
		return articolo;
	}

	public void setAnnoGui(Integer annoGui) {
		this.annoGui = annoGui;
	}

	public Integer getAnnoGui() {
		return annoGui;
	}

	public void setNumeroGui(String numeroGui) {
		this.numeroGui = numeroGui;
	}

	public String getNumeroGui() {
		return numeroGui;
	}

	
	

}
