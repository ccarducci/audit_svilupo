package it.tecnet.crs.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import it.tecnet.crs.service.AuditNonConformitaService;
import it.tecnet.crs.session.DatiUtente;
import it.tecnet.crs.web.beans.MediaNonConformitaFaseNotificaPaginator;
import it.tecnet.crs.web.dto.NonConformitaDto;

import com.opensymphony.xwork2.ModelDriven;

public class MediaNonConformitaFaseNotifica extends BaseAction implements ModelDriven<MediaNonConformitaFaseNotificaPaginator> {

	private static final long serialVersionUID = 1L;
	//	private DatiUtente datiUtenteSession = new DatiUtente();
	private MediaNonConformitaFaseNotificaPaginator fn= new MediaNonConformitaFaseNotificaPaginator();
	private AuditNonConformitaService auditNonConformitaService;

	// costruttore
	public MediaNonConformitaFaseNotifica(AuditNonConformitaService auditNonConformitaService) {
		super();
		this.auditNonConformitaService = auditNonConformitaService;

	}

	//TABELLE FASE DEFINIZIONE modifica-accesso.jsp
	public String getMediaNonConformitaNotifica() {

		HttpServletRequest request = ServletActionContext.getRequest();
		DatiUtente user= (DatiUtente)request.getSession().getAttribute("DatiUtente");
		if(user!=null){
			long idSessione = user.getIdSessione();


			String colunmName = "";
			if (getModel().getiSortCol_0() != 0) {
				colunmName = "" + getModel().getiSortCol_0();

			}

			try{
				List<NonConformitaDto> ncdList=auditNonConformitaService.getMediaNonConformita(idSessione, "N",
						getModel().getiDisplayStart(), getModel().getiDisplayLength(), colunmName, getModel().getsSortDir_0(), getModel().getsSearch());

				//count 
				Integer countRecord=auditNonConformitaService.countRecordMediaNotifica(idSessione);


				getModel().setiTotalDisplayRecords(countRecord);
				getModel().setiTotalRecords(countRecord);

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
	public MediaNonConformitaFaseNotificaPaginator getModel() {
		// TODO Auto-generated method stub
		return fn;
	}

	public AuditNonConformitaService getAuditNonConformitaService() {
		return auditNonConformitaService;
	}


	public void setAuditNonConformitaService(
			AuditNonConformitaService auditNonConformitaService) {
		this.auditNonConformitaService = auditNonConformitaService;
	}



}
