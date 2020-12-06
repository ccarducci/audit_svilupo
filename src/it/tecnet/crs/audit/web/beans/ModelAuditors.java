package it.tecnet.crs.audit.web.beans;

import java.util.ArrayList;
import java.util.List;

public class ModelAuditors {

  	public String sSearch;

  	public long idAuditors;
  	
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
  	
  	
  	public String getsSearch() {
		return sSearch;
	}

	public void setsSearch(String search) {
		sSearch = search;
	}

	public int getiDisplayLength() {
		return iDisplayLength;
	}

	public void setiDisplayLength(int displayLength) {
		iDisplayLength = displayLength;
	}

	public int getiDisplayStart() {
		return iDisplayStart;
	}

	public void setiDisplayStart(int displayStart) {
		iDisplayStart = displayStart;
	}

	public int getiColumns() {
		return iColumns;
	}

	public void setiColumns(int columns) {
		iColumns = columns;
	}

	public int getiSortingCols() {
		return iSortingCols;
	}

	public void setiSortingCols(int sortingCols) {
		iSortingCols = sortingCols;
	}

	public String getsColumns() {
		return sColumns;
	}

	public void setsColumns(String columns) {
		sColumns = columns;
	}

	public int getiTotalRecords() {
		return iTotalRecords;
	}

	public void setiTotalRecords(int totalRecords) {
		iTotalRecords = totalRecords;
	}

	public int getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}

	public void setiTotalDisplayRecords(int totalDisplayRecords) {
		iTotalDisplayRecords = totalDisplayRecords;
	}

	public List<Object> getaaData() {
		return aaData;
	}

	public void setaaData(List<Object> aaData) {
		this.aaData = aaData;
	}

	public int getiSortCol_0() {
		return iSortCol_0;
	}

	public void setiSortCol_0(int sortCol_0) {
		iSortCol_0 = sortCol_0;
	}

	public String getsSortDir_0() {
		return sSortDir_0;
	}

	public void setsSortDir_0(String sortDir_0) {
		sSortDir_0 = sortDir_0;
	}

	public long getIdAuditors() {
		return idAuditors;
	}

	public void setIdAuditors(long idAuditors) {
		this.idAuditors = idAuditors;
	}	
}
