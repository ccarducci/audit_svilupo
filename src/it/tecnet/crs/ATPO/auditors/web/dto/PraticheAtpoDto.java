package it.tecnet.crs.ATPO.auditors.web.dto;

import java.util.Date;

public class PraticheAtpoDto {

	// attributi ATPO_PRATICHE_SISCO + NOME_SEDE

	private Long idPraticheATPO;

	private String richiesta;
	private String esito;
	private String autotutela;
	private String parte;
	private String parereMedico;
	private String prRic;
	private String prBzz;
	private String prDef;
	private String codSede;
	private String nomeSede;
	private String fascicolo;
	private String rg;
	private String visitaPeritale;
	private String osservazioniSanitarie;
	private Date notifica;
	private Date apertura;
	private Date costituzione;
	private Date udienza;
	private Date dissenso;
	private Date dissensoINPS;
	private Date provv;
	private Date notProvv;
	private Date com;
	private Date car;
	private Date esec;

	// campi aggiunti
	private String statoEsamePratica;
	private String dSeiMuno;

	private String userIdOwner;
	private String praticaModificable = "F";

	public String getRichiesta() {
		return richiesta;
	}

	public void setRichiesta(String richiesta) {
		this.richiesta = richiesta;
	}

	public String getEsito() {
		return esito;
	}

	public void setEsito(String esito) {
		this.esito = esito;
	}

	public String getAutotutela() {
		return autotutela;
	}

	public void setAutotutela(String autotutela) {
		this.autotutela = autotutela;
	}

	public String getParereMedico() {
		return parereMedico;
	}

	public void setParereMedico(String parereMedico) {
		this.parereMedico = parereMedico;
	}

	public String getPrRic() {
		return prRic;
	}

	public void setPrRic(String prRic) {
		this.prRic = prRic;
	}

	public String getPrDef() {
		return prDef;
	}

	public void setPrDef(String prDef) {
		this.prDef = prDef;
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

	public Date getCostituzione() {
		return costituzione;
	}

	public void setCostituzione(Date costituzione) {
		this.costituzione = costituzione;
	}

	public Date getUdienza() {
		return udienza;
	}

	public void setUdienza(Date udienza) {
		this.udienza = udienza;
	}

	public Date getDissenso() {
		return dissenso;
	}

	public void setDissenso(Date dissenso) {
		this.dissenso = dissenso;
	}

	public Date getDissensoINPS() {
		return dissensoINPS;
	}

	public void setDissensoINPS(Date dissensoINPS) {
		this.dissensoINPS = dissensoINPS;
	}

	public Date getProvv() {
		return provv;
	}

	public void setProvv(Date provv) {
		this.provv = provv;
	}

	public Date getNotProvv() {
		return notProvv;
	}

	public void setNotProvv(Date notProvv) {
		this.notProvv = notProvv;
	}

	public Date getCom() {
		return com;
	}

	public void setCom(Date com) {
		this.com = com;
	}

	public Date getCar() {
		return car;
	}

	public void setCar(Date car) {
		this.car = car;
	}

	public Date getEsec() {
		return esec;
	}

	public void setEsec(Date esec) {
		this.esec = esec;
	}

	public Long getIdPraticheATPO() {
		return idPraticheATPO;
	}

	public void setIdPraticheATPO(Long idPraticheATPO) {
		this.idPraticheATPO = idPraticheATPO;
	}

	public String getCodSede() {
		return codSede;
	}

	public void setCodSede(String codSede) {
		this.codSede = codSede;
	}

	public String getFascicolo() {
		return fascicolo;
	}

	public void setFascicolo(String fascicolo) {
		this.fascicolo = fascicolo;
	}

	public String getParte() {
		return parte;
	}

	public void setParte(String parte) {
		this.parte = parte;
	}

	public void setNomeSede(String nomeSede) {
		this.nomeSede = nomeSede;
	}

	public String getNomeSede() {
		return nomeSede;
	}

	public void setPrBzz(String prBzz) {
		this.prBzz = prBzz;
	}

	public String getPrBzz() {
		return prBzz;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getRg() {
		return rg;
	}

	public void setVisitaPeritale(String visitaPeritale) {
		this.visitaPeritale = visitaPeritale;
	}

	public String getVisitaPeritale() {
		return visitaPeritale;
	}

	public void setOsservazioniSanitarie(String osservazioniSanitarie) {
		this.osservazioniSanitarie = osservazioniSanitarie;
	}

	public String getOsservazioniSanitarie() {
		return osservazioniSanitarie;
	}

	public void setStatoEsamePratica(String statoEsamePratica) {
		this.statoEsamePratica = statoEsamePratica;
	}

	public String getStatoEsamePratica() {
		return statoEsamePratica;
	}

	public void setDSeiMuno(String dSeiMuno) {
		this.dSeiMuno = dSeiMuno;
	}

	public String getDSeiMuno() {
		return dSeiMuno;
	}

	public String getUserIdOwner() {
		return userIdOwner;
	}

	public void setUserIdOwner(String userIdOwner) {
		this.userIdOwner = userIdOwner;
	}

	public String getPraticaModificable() {
		return praticaModificable;
	}

	public void setPraticaModificable(String praticaModificable) {
		this.praticaModificable = praticaModificable;
	}

}
