package it.tecnet.crs.mod.web.bean;

import it.tecnet.crs.util.datatables.DataTablesModel;

public class DocumentiAttivitaDettaglioPaginator extends DataTablesModel {
	
	private long idFile;
	
	private String listId;
	
	private long idAttivitaDettaglio;
	
  	
	public void setIdFile(long idFile) {
		this.idFile = idFile;
	}


	public long getIdFile() {
		return idFile;
	}


	public void setListId(String listId) {
		this.listId = listId;
	}


	public String getListId() {
		return listId;
	}


	public void setIdAttivitaDettaglio(long idAttivitaDettaglio) {
		this.idAttivitaDettaglio = idAttivitaDettaglio;
	}


	public long getIdAttivitaDettaglio() {
		return idAttivitaDettaglio;
	}


}
