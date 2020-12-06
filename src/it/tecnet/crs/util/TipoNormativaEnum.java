package it.tecnet.crs.util;

public enum TipoNormativaEnum {
	
	CIRCOLARI_INPS(1, "CIRCOLARI"),
	NOTE_DESCRETI(2, "NOTE E DECRETI DIR.MDL"),
	MESSAGGI_INPS(3, "MESSAGGI"),
	LEGGI_DECRETI(4, "LEGGI E DECRETI");

	
	private Integer codice;
	private String descrizione;
	
	private TipoNormativaEnum(Integer codice, String descrizione){
		
		this.codice = codice;
		this.descrizione = descrizione;
	}
	

	public Integer getCodice(){
		return codice;
	}
	
	public String getDescrizione(){
		return descrizione;
	}
}
