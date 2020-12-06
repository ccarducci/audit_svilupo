package it.tecnet.crs.mod.web.bean;

import it.tecnet.crs.componenti.jpa.model.CrsDomini;

import java.util.ArrayList;
import java.util.List;

public class ProcessoTablePaginator {

	private long idDominioProcesso;
	private CrsDomini dominio;
	
		public int sessionId;
		
		public String sEcho;
	    
	  	public String sSearch;

	  	public int iDisplayLength;

	  	public int iDisplayStart;

	  	public int iColumns;

	  	public int iSortingCols;
	    
	  	public String sColumns;
	    
	  	public int  iTotalRecords;
	    
	  	public int  iTotalDisplayRecords;

	  	public List<Object> aaData = new ArrayList<Object>();
	  
	  	public int iSortCol_0=0;

	  	public String sSortDir_0;
	  	
	  	public Processo processo;
	  	
	  	public long idProcesso;
	  	
	  	public long idArea;

		public Object dataInizio;

		public Object dataFine;

		public String descrizione;
		
		public String stato;
		
		public long idSottoProcesso;
		
		public long idTipo;
	  	
	  	public long idClasse;
	  	
	  	public long idAttivitaComponente;
	  	
	  	public long idAttivitaDettaglio;
		

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
	  

		public int getSessionId() {
			return sessionId;
		}


		public void setSessionId(int sessionId) {
			this.sessionId = sessionId;
		}


		public Processo getProcesso() {
			return processo;
		}


		public void setProcesso(Processo processo) {
			this.processo = processo;
		}


		public long getIdProcesso() {
			return idProcesso;
		}


		public void setIdProcesso(long idProcesso) {
			this.idProcesso = idProcesso;
		}


		public long getIdArea() {
			return idArea;
		}


		public void setIdArea(long idArea) {
			this.idArea = idArea;
		}


		public Object getDataInizio() {
			return dataInizio;
		}


		public void setDataInizio(Object dataInizio) {
			this.dataInizio = dataInizio;
		}


		public Object getDataFine() {
			return dataFine;
		}


		public void setDataFine(Object dataFine) {
			this.dataFine = dataFine;
		}


		public String getDescrizione() {
			return descrizione;
		}


		public void setDescrizione(String descrizione) {
			this.descrizione = descrizione;
		}


		public String getStato() {
			return stato;
		}


		public void setStato(String stato) {
			this.stato = stato;
		}


		public long getIdSottoProcesso() {
			return idSottoProcesso;
		}


		public void setIdSottoProcesso(long idSottoProcesso) {
			this.idSottoProcesso = idSottoProcesso;
		}


		public long getIdTipo() {
			return idTipo;
		}


		public void setIdTipo(long idTipo) {
			this.idTipo = idTipo;
		}


		public long getIdClasse() {
			return idClasse;
		}


		public void setIdClasse(long idClasse) {
			this.idClasse = idClasse;
		}


		public long getIdAttivitaComponente() {
			return idAttivitaComponente;
		}


		public void setIdAttivitaComponente(long idAttivitaComponente) {
			this.idAttivitaComponente = idAttivitaComponente;
		}


		public long getIdAttivitaDettaglio() {
			return idAttivitaDettaglio;
		}


		public void setIdAttivitaDettaglio(long idAttivitaDettaglio) {
			this.idAttivitaDettaglio = idAttivitaDettaglio;
		}


		public void setIdDominioProcesso(long idDominioProcesso) {
			this.idDominioProcesso = idDominioProcesso;
		}


		public long getIdDominioProcesso() {
			return idDominioProcesso;
		}


		public void setDominio(CrsDomini dominio) {
			this.dominio = dominio;
		}


		public CrsDomini getDominio() {
			return dominio;
		}

	}



