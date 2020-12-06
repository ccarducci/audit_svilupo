package it.tecnet.crs.mod.web.bean;

import it.tecnet.crs.util.datatables.DataTablesModel;

import java.util.ArrayList;
import java.util.List;

public class DocumentiProcessoPaginator extends DataTablesModel {
	
	private long idFile;
	
	private String listId;
	
	private long idProcesso;
	
  	
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


	public void setIdProcesso(long idProcesso) {
		this.idProcesso = idProcesso;
	}


	public long getIdProcesso() {
		return idProcesso;
	}

}
