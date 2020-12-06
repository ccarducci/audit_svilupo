package it.tecnet.crs.audit.web.dto;

import java.util.Date;

public class PraticheCampagnaDto {
//select sede.NOME_SEDE, sisco.FASCICOLO, sisco.NOTIFICA, sisco.APERTURA, 
	//cogisan.RG, cogisan.PARTE, cogisan.VISITA_PERITALE 
	private long id;
	
	private String nomeSede;
	
	private String fascicolo;
	
	private Date notifica;
	
	private Date apertura;
	
	private String rg;
	
	private String parte;
	
	private String visitaPeritale;
	
	private String esito;
	
	private String presenzaVisitePeritali;
	
	private String Funzionario;
	private String MedicoINPS;
	private String TempoAperturaPratica;
	private String TempoChiusuraPratica;
	private String PresenzaCTU;
	
	
	

	public String getNomeSede() {
		return nomeSede;
	}

	public void setNomeSede(String nomeSede) {
		this.nomeSede = nomeSede;
	}

	public String getFascicolo() {
		return fascicolo;
	}

	public void setFascicolo(String fascicolo) {
		this.fascicolo = fascicolo;
	}

	public Date getNotifica() {
		return notifica;
	}

	public void setNotifica(Date notifica) {
		this.notifica = notifica;
	}

	public Date getApertura() {
		return apertura;
	}

	public void setApertura(Date apertura) {
		this.apertura = apertura;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getParte() {
		return parte;
	}

	public void setParte(String parte) {
		this.parte = parte;
	}

	public String getVisitaPeritale() {
		return visitaPeritale;
	}

	public void setVisitaPeritale(String visitaPeritale) {
		this.visitaPeritale = visitaPeritale;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEsito() {
		return esito;
	}

	public void setEsito(String esito) {
		this.esito = esito;
	}

	public String getPresenzaVisitePeritali() {
		return presenzaVisitePeritali;
	}

	public void setPresenzaVisitePeritali(String presenzaVisitePeritali) {
		this.presenzaVisitePeritali = presenzaVisitePeritali;
	}

	public String getFunzionario() {
		return Funzionario;
	}

	public void setFunzionario(String funzionario) {
		Funzionario = funzionario;
	}

	public String getMedicoINPS() {
		return MedicoINPS;
	}

	public void setMedicoINPS(String medicoINPS) {
		MedicoINPS = medicoINPS;
	}

	public String getTempoAperturaPratica() {
		return TempoAperturaPratica;
	}

	public void setTempoAperturaPratica(String tempoAperturaPratica) {
		TempoAperturaPratica = tempoAperturaPratica;
	}

	public String getTempoChiusuraPratica() {
		return TempoChiusuraPratica;
	}

	public void setTempoChiusuraPratica(String tempoChiusuraPratica) {
		TempoChiusuraPratica = tempoChiusuraPratica;
	}

	public String getPresenzaCTU() {
		return PresenzaCTU;
	}

	public void setPresenzaCTU(String presenzaCTU) {
		PresenzaCTU = presenzaCTU;
	}

	
	
	
}
