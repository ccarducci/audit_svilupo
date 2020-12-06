package it.tecnet.crs.componenti.web.bean;

import it.tecnet.crs.componenti.jpa.model.CrsDocumentiMedia;
import it.tecnet.crs.componenti.jpa.model.CrsTplDocMedia;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

public class DocumentiMediaPaginator {
	
	private List<CrsTplDocMedia> tplDocMedia= new ArrayList<CrsTplDocMedia>();
	private CrsTplDocMedia optionSelected;
	private CrsDocumentiMedia docMedia;
	private String base64File;
	private String codiceDocMedia;
	private Boolean codEsistente;
	private String listId;
	private Long idDocumento;
	private String prefix;
	private String nomeDocumento;
	private byte[] toBlob;
	
	public String sEcho;

	public String sSearch;

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


	public void setDocMedia(CrsDocumentiMedia docMedia) {
		this.docMedia = docMedia;
	}


	public CrsDocumentiMedia getDocMedia() {
		return docMedia;
	}


	public void setBase64File(String base64File) {
		this.base64File = base64File;
	}


	public String getBase64File() {
		return base64File;
	}


	public void setCodiceDocMedia(String codiceDocMedia) {
		this.codiceDocMedia = codiceDocMedia;
	}


	public String getCodiceDocMedia() {
		return codiceDocMedia;
	}


	public void setCodEsistente(Boolean codEsistente) {
		this.codEsistente = codEsistente;
	}


	public Boolean getCodEsistente() {
		return codEsistente;
	}


	public void setListId(String listId) {
		this.listId = listId;
	}


	public String getListId() {
		return listId;
	}


	public void setIdDocumento(Long idDocumento) {
		this.idDocumento = idDocumento;
	}


	public Long getIdDocumento() {
		return idDocumento;
	}


	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}


	public String getPrefix() {
		return prefix;
	}


	public void setNomeDocumento(String nomeDocumento) {
		this.nomeDocumento = nomeDocumento;
	}


	public String getNomeDocumento() {
		return nomeDocumento;
	}



	public void setToBlob(byte[] toBlob) {
		this.toBlob = toBlob;
	}


	public byte[] getToBlob() {
		return toBlob;
	}


	public void setTplDocMedia(List<CrsTplDocMedia> tplDocMedia) {
		this.tplDocMedia = tplDocMedia;
	}


	public List<CrsTplDocMedia> getTplDocMedia() {
		return tplDocMedia;
	}


	public void setOptionSelected(CrsTplDocMedia optionSelected) {
		this.optionSelected = optionSelected;
	}


	public CrsTplDocMedia getOptionSelected() {
		return optionSelected;
	}

}
