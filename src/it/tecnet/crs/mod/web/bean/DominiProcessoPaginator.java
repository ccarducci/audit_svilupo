package it.tecnet.crs.mod.web.bean;

import it.tecnet.crs.util.datatables.DataTablesModel;

import java.util.ArrayList;
import java.util.List;

public class DominiProcessoPaginator extends DataTablesModel {
	
	private long idDomini;
	
	private String listId;
	
	private long idProcesso;
	
  	



	public void setIdDomini(long idDomini) {
		this.idDomini = idDomini;
	}


	public long getIdDomini() {
		return idDomini;
	}


	public void setListId(String listId) {
		this.listId = listId;
	}


	public String getListId() {
		return listId;
	}


	public void setIdProcesso(long idProcesso) {
		this.idProcesso = idProcesso;
	}


	public long getIdProcesso() {
		return idProcesso;
	}

}
