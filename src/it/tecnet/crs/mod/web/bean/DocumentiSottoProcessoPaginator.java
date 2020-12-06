package it.tecnet.crs.mod.web.bean;

import it.tecnet.crs.util.datatables.DataTablesModel;

public class DocumentiSottoProcessoPaginator extends DataTablesModel {
	
	private long idFile;
	
	private String listId;
	
	private long idSottoProcesso;
	
  	
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


	public void setIdSottoProcesso(long idSottoProcesso) {
		this.idSottoProcesso = idSottoProcesso;
	}


	public long getIdSottoProcesso() {
		return idSottoProcesso;
	}


}
