package it.tecnet.crs.web.action;


import it.tecnet.crs.audit.service.AuCampagnaService;
import it.tecnet.crs.jpa.model.AuCampagna;
import it.tecnet.crs.service.AuditNonConformitaService;
import it.tecnet.crs.service.AuditService;
import it.tecnet.crs.web.beans.AppUser;
import it.tecnet.crs.web.beans.SessioneTablePaginator;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ModelDriven;


public class TestDaoServiceAction extends BaseAction implements ModelDriven<SessioneTablePaginator> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6171674902864842397L;

	private SessioneTablePaginator sessioneTp = new SessioneTablePaginator();

	private AuditService auditService;
	private AuditNonConformitaService auditNonConformitaService;
	private AuCampagnaService campagnaService;


	
	public TestDaoServiceAction(AuditService auditService,AuditNonConformitaService auditNonConformitaService, AuCampagnaService campagnaService) {
		super();
		this.auditService = auditService;
		this.auditNonConformitaService = auditNonConformitaService;
		this.campagnaService=campagnaService;
		this.campagnaService=campagnaService;
	}

	 
	public String test(){


		return SUCCESS;

	}




	private void associaVerbaliCampagna(){

		AuCampagna camp= campagnaService.getCampagnaDaModificare(1L);
		campagnaService.associaVerbaliCampagna(camp);
	}





	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return super.execute();
	}


	@Override
	public SessioneTablePaginator getModel() {
		return sessioneTp;
	}

	public AuditService getAuditService() {
		return auditService;
	}

	public void setAuditService(AuditService auditService) {
		this.auditService = auditService;
	}

	public AuditNonConformitaService getAuditNonConformitaService() {
		return auditNonConformitaService;
	}

	public void setAuditNonConformitaService(
			AuditNonConformitaService auditNonConformitaService) {
		this.auditNonConformitaService = auditNonConformitaService;
	}


	public String logOut(){
		HttpServletRequest request = ServletActionContext.getRequest();
		
		AppUser user=null; 
		request.getSession().setAttribute("AppUser", user);
		
		return SUCCESS;
	}

}
