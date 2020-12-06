package it.tecnet.crs.mod.web.bean;

import it.tecnet.crs.util.datatables.DataTablesModel;

import java.util.ArrayList;
import java.util.List;

public class DominiSottoProcessoPaginator extends DataTablesModel {
	
	private long idDomini;
	
	private String listId;
	
	private long idSottoProcesso;
	
  	



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


	public void setIdSottoProcesso(long idSottoProcesso) {
		this.idSottoProcesso = idSottoProcesso;
	}


	public long getIdSottoProcesso() {
		return idSottoProcesso;
	}



}
