package it.tecnet.crs.mod.web.bean;

import it.tecnet.crs.util.datatables.DataTablesModel;

import java.util.ArrayList;
import java.util.List;

public class CompTecniciSottoProcessoPaginator extends DataTablesModel {
	
	private long idCompTec;
	
	private String listId;
	
	private long idSottoProcesso;
	
  	




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


	public void setIdSottoProcesso(long idSottoProcesso) {
		this.idSottoProcesso = idSottoProcesso;
	}


	public long getIdSottoProcesso() {
		return idSottoProcesso;
	}


}
