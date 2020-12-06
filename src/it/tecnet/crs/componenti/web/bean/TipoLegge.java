package it.tecnet.crs.componenti.web.bean;


public class TipoLegge extends TipoNormativaCommon{
	
	private long idTipoLegge;
	
	private String codice;
	
	private String descrizione;
	


	public void setIdTipoLegge(long idTipoLegge) {
		this.idTipoLegge = idTipoLegge;
	}

	public long getIdTipoLegge() {
		return idTipoLegge;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	


	
	
	


}
