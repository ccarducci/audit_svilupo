package it.tecnet.crs.audit.web.beans;

import it.tecnet.crs.jpa.model.AuAudit;
import it.tecnet.crs.mod.web.dto.ProcessoDto;
import it.tecnet.crs.web.dto.QuestionarioDto;

import java.util.ArrayList;
import java.util.List;

public class ModelAudit {
	
	private int questionariAssociatiAudit;
	private int isAuditReferenced;
	private long idAudit;
	private AuAudit audit; 
	private String nomeDirigenteAudit;
	private long idQuest;
	private String descrizioneDomanda;
	private long idDomanda;
	
	//menu a tendina aside
	public List<ProcessoDto> listMenu= new ArrayList<ProcessoDto>();
	
	//menu a tendina questionario
	public List<QuestionarioDto> listDomande= new ArrayList<QuestionarioDto>();

  	public String idFaseToAdd;
  	
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
  	
  	

	public void setAudit(AuAudit audit) {
		this.audit = audit;
	}


	public AuAudit getAudit() {
		return audit;
	}


	public void setIdAudit(long idAudit) {
		this.idAudit = idAudit;
	}


	public long getIdAudit() {
		return idAudit;
	}


	public void setListMenu(List<ProcessoDto> listMenu) {
		this.listMenu = listMenu;
	}


	public List<ProcessoDto> getListMenu() {
		return listMenu;
	}

	 public List<QuestionarioDto> getListDomande() {
			return listDomande;
		}


		public void setListDomande(List<QuestionarioDto> listDomande) {
			this.listDomande = listDomande;
		}

	

		public String getDescrizioneDomanda() {
			return descrizioneDomanda;
		}


		public void setDescrizioneDomanda(String descrizioneDomanda) {
			this.descrizioneDomanda = descrizioneDomanda;
		}


		public void setIdQuest(long idQuest) {
			this.idQuest = idQuest;
		}


		public long getIdQuest() {
			return idQuest;
		}


		public void setIdDomanda(long idDomanda) {
			this.idDomanda = idDomanda;
		}


		public long getIdDomanda() {
			return idDomanda;
		}


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


		


		public int getQuestionariAssociatiAudit() {
			return questionariAssociatiAudit;
		}


		public void setQuestionariAssociatiAudit(int questionariAssociatiAudit) {
			this.questionariAssociatiAudit = questionariAssociatiAudit;
		}


		public String getNomeDirigenteAudit() {
			return nomeDirigenteAudit;
		}


		public void setNomeDirigenteAudit(String nomeDirigenteAudit) {
			this.nomeDirigenteAudit = nomeDirigenteAudit;
		}


		public int getIsAuditReferenced() {
			return isAuditReferenced;
		}


		public void setIsAuditReferenced(int isAuditReferenced) {
			this.isAuditReferenced = isAuditReferenced;
		}
		
}
