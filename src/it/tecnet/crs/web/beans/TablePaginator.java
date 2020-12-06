package it.tecnet.crs.web.beans;

import java.util.ArrayList;
import java.util.List;

public class TablePaginator {
	
	  	private String sEcho;
	    
	  	private String sSearch;

	  	private int iDisplayLength;

	  	private int iDisplayStart;

	  	private int iColumns;

	  	private int iSortingCols;
	    
	  	private String sColumns;
	    
	  	private int  iTotalRecords;
	    
	  	private int  iTotalDisplayRecords;

	  	private List<Object> aaData = new ArrayList<Object>();
	  
	  	private int iSortCol_0=0;

	  	private String sSortDir_0;

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
	  
}
