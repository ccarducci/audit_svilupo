package it.tecnet.crs.ATPO.auditors.web.beans;

import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoRiepilogoFascicolo;
import it.tecnet.crs.ATPO.auditors.web.dto.AuTplTipologicheAtpoDto;

import java.util.ArrayList;
import java.util.List;

public class ModelAuditorsRiepilogoFascicoloATPO {
	private String praticaModificable;

	public String getPraticaModificable() {
		return praticaModificable;
	}

	public void setPraticaModificable(String praticaModificable) {
		this.praticaModificable = praticaModificable;
	}

	private AtpoRiepilogoFascicolo fascicolo;

	private List<AuTplTipologicheAtpoDto> fascicoliElettroniciEcartacei = new ArrayList<AuTplTipologicheAtpoDto>();

	private List<AuTplTipologicheAtpoDto> dettDocMancate = new ArrayList<AuTplTipologicheAtpoDto>();

	private String statoEsamePratica;

	private String codFascElettr;
	private String codFascCart;
	private String codDocManc;

	private String dettaglioDocMancante;

	private String listaDocMancante;

	public AtpoRiepilogoFascicolo getFascicolo() {
		return fascicolo;
	}

	public void setFascicolo(AtpoRiepilogoFascicolo fascicolo) {
		this.fascicolo = fascicolo;
	}

	public List<AuTplTipologicheAtpoDto> getFascicoliElettroniciEcartacei() {
		return fascicoliElettroniciEcartacei;
	}

	public void setFascicoliElettroniciEcartacei(
			List<AuTplTipologicheAtpoDto> fascicoliElettroniciEcartacei) {
		this.fascicoliElettroniciEcartacei = fascicoliElettroniciEcartacei;
	}

	public List<AuTplTipologicheAtpoDto> getDettDocMancate() {
		return dettDocMancate;
	}

	public void setDettDocMancate(List<AuTplTipologicheAtpoDto> dettDocMancate) {
		this.dettDocMancate = dettDocMancate;
	}

	public String sEcho;

	public String sSearch;

	public int iDisplayLength;

	public int iDisplayStart;

	public int iColumns;

	public int iSortingCols;

	public String sColumns;

	public int iTotalRecords;

	public int iTotalDisplayRecords;

	public List<Object> aaData = new ArrayList<Object>();

	public int iSortCol_0 = 0;

	public String sSortDir_0;

	public String getsEcho() {
		return sEcho;
	}

	public void setsEcho(String sEcho) {
		this.sEcho = sEcho;
	}

	public String getsSearch() {
		return sSearch;
	}

	public void setsSearch(String sSearch) {
		this.sSearch = sSearch;
	}

	public int getiDisplayLength() {
		return iDisplayLength;
	}

	public void setiDisplayLength(int iDisplayLength) {
		this.iDisplayLength = iDisplayLength;
	}

	public int getiDisplayStart() {
		return iDisplayStart;
	}

	public void setiDisplayStart(int iDisplayStart) {
		this.iDisplayStart = iDisplayStart;
	}

	public int getiColumns() {
		return iColumns;
	}

	public void setiColumns(int iColumns) {
		this.iColumns = iColumns;
	}

	public int getiSortingCols() {
		return iSortingCols;
	}

	public void setiSortingCols(int iSortingCols) {
		this.iSortingCols = iSortingCols;
	}

	public String getsColumns() {
		return sColumns;
	}

	public void setsColumns(String sColumns) {
		this.sColumns = sColumns;
	}

	public int getiTotalRecords() {
		return iTotalRecords;
	}

	public void setiTotalRecords(int iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}

	public int getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}

	public void setiTotalDisplayRecords(int iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}

	public List<Object> getAaData() {
		return aaData;
	}

	public void setAaData(List<Object> aaData) {
		this.aaData = aaData;
	}

	public int getiSortCol_0() {
		return iSortCol_0;
	}

	public void setiSortCol_0(int iSortCol_0) {
		this.iSortCol_0 = iSortCol_0;
	}

	public String getsSortDir_0() {
		return sSortDir_0;
	}

	public void setsSortDir_0(String sSortDir_0) {
		this.sSortDir_0 = sSortDir_0;
	}

	public void setCodFascElettr(String codFascElettr) {
		this.codFascElettr = codFascElettr;
	}

	public String getCodFascElettr() {
		return codFascElettr;
	}

	public void setCodFascCart(String codFascCart) {
		this.codFascCart = codFascCart;
	}

	public String getCodFascCart() {
		return codFascCart;
	}

	public void setCodDocManc(String codDocManc) {
		this.codDocManc = codDocManc;
	}

	public String getCodDocManc() {
		return codDocManc;
	}

	public void setDettaglioDocMancante(String dettaglioDocMancante) {
		this.dettaglioDocMancante = dettaglioDocMancante;
	}

	public String getDettaglioDocMancante() {
		return dettaglioDocMancante;
	}

	public void setListaDocMancante(String listaDocMancante) {
		this.listaDocMancante = listaDocMancante;
	}

	public String getListaDocMancante() {
		return listaDocMancante;
	}

	public void setStatoEsamePratica(String statoEsamePratica) {
		this.statoEsamePratica = statoEsamePratica;
	}

	public String getStatoEsamePratica() {
		return statoEsamePratica;
	}

}
