package it.tecnet.crs.ATPO.auditors.web.dto;

public class AtpoDettaglioFascicoloDto {
	
	private long idDettFasc;
	private long idRiepilogoFascicolo;
	private String codifica;
	private String descrizione;
	
	public long getIdDettFasc() {
		return idDettFasc;
	}
	public void setIdDettFasc(long idDettFasc) {
		this.idDettFasc = idDettFasc;
	}
	public long getIdRiepilogoFascicolo() {
		return idRiepilogoFascicolo;
	}
	public void setIdRiepilogoFascicolo(long idRiepilogoFascicolo) {
		this.idRiepilogoFascicolo = idRiepilogoFascicolo;
	}
	public String getCodifica() {
		return codifica;
	}
	public void setCodifica(String codifica) {
		this.codifica = codifica;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	
	

}
