package it.tecnet.crs.mod.web.bean;

import java.util.ArrayList;
import java.util.List;

public class AttivitaComponenteTablePaginator {

	  
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
	  	
	  	public AttivitaComponente attivitaComponente = new AttivitaComponente();
	  	
	  	public long idTipo;
	  	
	  	public long idClasse;

	  	public long idAttivitaComponente;
		

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


		public AttivitaComponente getAttivitaComponente() {
			return attivitaComponente;
		}


		public void setAttivitaComponente(AttivitaComponente attivitaComponente) {
			this.attivitaComponente = attivitaComponente;
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


}



