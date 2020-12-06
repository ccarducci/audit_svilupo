package it.tecnet.crs.util.datatables;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataTablesModel {
	private static final long serialVersionUID = 1L;

	private Map<String, Object> bodyParameters;

	// ****************************************************************************************************
	// INPUT
	// ****************************************************************************************************
	/**
	 * Request sequence number sent by DataTable, same value must be returned in
	 * response
	 */
	private int sEcho; // sEcho
	private String sSearch; // sSearch
	private boolean bRegex; // bRegex
	private String sColumns; // sColumns
	private int iColumns; // iColumns
	private int iSortingCols; // iSortingCols
	private boolean bSortable; // 

	// ARRAY
	private List<String> sSearchArray = new ArrayList<String>();
	private List<Boolean> bSearchableArray = new ArrayList<Boolean>();
	private List<Boolean> bSortableArray = new ArrayList<Boolean>();
	private List<Boolean> bRegexArray = new ArrayList<Boolean>();
	private List<String> mDataPropArray = new ArrayList<String>();
	private List<String> sSortDirArray = new ArrayList<String>();
	private List<String> iSortColArray = new ArrayList<String>();

	// ****************************************************************************************************
	// OUTPUT
	// ****************************************************************************************************
	private int iDisplayLength;
	private int iDisplayStart;
	private int iTotalRecords;
	private int iTotalDisplayRecords;
	
	// ****************************************************************************************************
	// COLLEZIONE OUTPUT
	// ****************************************************************************************************
	private List<Object> aaData = new ArrayList<Object>();

	public Map<String, Object> getBodyParameters() {
		return bodyParameters;
	}

	public void setBodyParameters(Map<String, Object> bodyParameters) {
		this.bodyParameters = bodyParameters;
	}

	public int getSEcho() {
		return sEcho;
	}

	public void setSEcho(int echo) {
		sEcho = echo;
	}

	public String getSSearch() {
		return sSearch;
	}

	public void setSSearch(String search) {
		sSearch = search;
	}

	public boolean isBRegex() {
		return bRegex;
	}

	public void setBRegex(boolean regex) {
		bRegex = regex;
	}

	public String getSColumns() {
		return sColumns;
	}

	public void setSColumns(String columns) {
		sColumns = columns;
	}

	public int getIColumns() {
		return iColumns;
	}

	public void setIColumns(int columns) {
		iColumns = columns;
	}

	public int getISortingCols() {
		return iSortingCols;
	}

	public void setISortingCols(int sortingCols) {
		iSortingCols = sortingCols;
	}

	public boolean isBSortable() {
		return bSortable;
	}

	public void setBSortable(boolean sortable) {
		bSortable = sortable;
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

	public List<String> getSSearchArray() {
		return sSearchArray;
	}

	public void setSSearchArray(List<String> searchArray) {
		sSearchArray = searchArray;
	}

	public List<Boolean> getBSearchableArray() {
		return bSearchableArray;
	}

	public void setBSearchableArray(List<Boolean> searchableArray) {
		bSearchableArray = searchableArray;
	}

	public List<Boolean> getBSortableArray() {
		return bSortableArray;
	}

	public void setBSortableArray(List<Boolean> sortableArray) {
		bSortableArray = sortableArray;
	}

	public List<Boolean> getBRegexArray() {
		return bRegexArray;
	}

	public void setBRegexArray(List<Boolean> regexArray) {
		bRegexArray = regexArray;
	}

	public List<String> getMDataPropArray() {
		return mDataPropArray;
	}

	public void setMDataPropArray(List<String> dataPropArray) {
		mDataPropArray = dataPropArray;
	}

	public List<String> getSSortDirArray() {
		return sSortDirArray;
	}

	public void setSSortDirArray(List<String> sortDirArray) {
		sSortDirArray = sortDirArray;
	}

	public List<String> getISortColArray() {
		return iSortColArray;
	}

	public void setISortColArray(List<String> sortColArray) {
		iSortColArray = sortColArray;
	}

}
