package it.tecnet.crs.ATPO.auditors.web.dto;

public class TipologiaPraticheCampioneDto {
	
	/*
	 * questo dto serve nel tab dati contesto -> Tipologia di pratiche presenti nel campione:”
	 */
	private Integer numero;
	private String descrizione;
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public Integer getNumero() {
		return numero;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getDescrizione() {
		return descrizione;
	}

}
