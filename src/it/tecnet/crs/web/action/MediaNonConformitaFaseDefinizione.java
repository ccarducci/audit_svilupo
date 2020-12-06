package it.tecnet.crs.web.action;

import it.tecnet.crs.service.AuditNonConformitaService;
import it.tecnet.crs.session.DatiUtente;
import it.tecnet.crs.web.beans.AppUser;
import it.tecnet.crs.web.beans.MediaNonConformitaFaseDefinizionePaginator;
import it.tecnet.crs.web.dto.NonConformitaDto;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ModelDriven;

public class MediaNonConformitaFaseDefinizione extends BaseAction implements
ModelDriven<MediaNonConformitaFaseDefinizionePaginator> {

	private static final long serialVersionUID = 1L;
	private MediaNonConformitaFaseDefinizionePaginator fd = new MediaNonConformitaFaseDefinizionePaginator();
	private AuditNonConformitaService auditNonConformitaService;

	// costruttore
	public MediaNonConformitaFaseDefinizione(
			AuditNonConformitaService auditNonConformitaService) {
		super();
		this.auditNonConformitaService = auditNonConformitaService;

	}

	// TABELLE FASE DEFINIZIONE modifica-accesso.jsp
	public String getMediaNonConformitaDefinizione() {
		HttpServletRequest request = ServletActionContext.getRequest();

		DatiUtente user= (DatiUtente)request.getSession().getAttribute("DatiUtente");
		if(user!=null){
			long idSessione = user.getIdSessione();

			String colunmName = "";
			if (getModel().getiSortCol_0() != 0) {
				colunmName = "" + getModel().getiSortCol_0();

			}
			try{
				List<NonConformitaDto> ncdList=auditNonConformitaService.getMediaNonConformita(idSessione, "D",
						getModel().getiDisplayStart(), getModel().getiDisplayLength(), colunmName, getModel().getsSortDir_0(), getModel().getsSearch());

				getModel().setiTotalDisplayRecords(10);
				getModel().setiTotalRecords(10);
				if(ncdList.size()!=0){
					for(Object sessione : ncdList){	
						getModel().getAaData().add(sessione);
					}
				}


			}catch(Exception e){
				e.printStackTrace();
			}

		}
		return SUCCESS;
	}

	@Override
	public MediaNonConformitaFaseDefinizionePaginator getModel() {

		return fd;
	}

	public AuditNonConformitaService getAuditNonConformitaService() {
		return auditNonConformitaService;
	}

	public void setAuditNonConformitaService(
			AuditNonConformitaService auditNonConformitaService) {
		this.auditNonConformitaService = auditNonConformitaService;
	}

}
