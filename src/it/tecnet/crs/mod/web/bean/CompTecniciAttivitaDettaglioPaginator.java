package it.tecnet.crs.mod.web.bean;

import it.tecnet.crs.util.datatables.DataTablesModel;

import java.util.ArrayList;
import java.util.List;

public class CompTecniciAttivitaDettaglioPaginator extends DataTablesModel {
	
	private long idCompTec;
	
	private String listId;
	
	private long idAttivitaDettaglio;
	
  	
	public void setIdCompTec(long idCompTec) {
		this.idCompTec = idCompTec;
	}


	public long getIdCompTec() {
		return idCompTec;
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
