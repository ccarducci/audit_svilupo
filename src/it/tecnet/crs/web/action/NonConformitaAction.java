package it.tecnet.crs.web.action;


import it.tecnet.crs.service.AuditService;
import it.tecnet.crs.session.DatiUtente;
import it.tecnet.crs.web.beans.NonConformitaPaginator;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ModelDriven;

public class NonConformitaAction extends BaseAction implements ModelDriven<NonConformitaPaginator> {

	
	
	private static final long serialVersionUID = 1L;
	private NonConformitaPaginator model = new NonConformitaPaginator();
	private AuditService auditService;
	
	public NonConformitaAction(AuditService auditService){
		super();
		this.auditService= auditService;
	}
	
	public String getTabNonConformitaActionPratiche(){
		
		HttpServletRequest request = ServletActionContext.getRequest();

		DatiUtente user= (DatiUtente)request.getSession().getAttribute("DatiUtente");
		if(user!=null){
			long idVerbale = user.getIdVerbale();
			//AuVerbale verb=auditService.getVerbale(idVerbale);
			//getModel().setVerb(verb);
		}
		
		return SUCCESS;
	}
		
	
	@Override
	public NonConformitaPaginator getModel() {
		return model;
	}

	public void setAuditService(AuditService auditService) {
		this.auditService = auditService;
	}

	public AuditService getAuditService() {
		return auditService;
	}

}
