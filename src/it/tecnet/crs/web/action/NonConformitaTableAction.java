package it.tecnet.crs.web.action;

import it.tecnet.crs.service.AuditService;
import it.tecnet.crs.session.DatiUtente;
import it.tecnet.crs.web.beans.NonConformitaTablePaginator;
import it.tecnet.crs.web.dto.NonConformitaPraticheDto;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ModelDriven;

public class NonConformitaTableAction extends BaseAction implements ModelDriven<NonConformitaTablePaginator> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6171674902864842397L;

	private NonConformitaTablePaginator ncp = new NonConformitaTablePaginator();



	private AuditService auditService;


	public NonConformitaTableAction(AuditService auditService) {
		super();
		this.auditService = auditService;
	}

	
	//ritorna la pagina
	public String getTabNonConformitaActionPratiche(){
		
		HttpServletRequest request = ServletActionContext.getRequest();

		DatiUtente user= (DatiUtente)request.getSession().getAttribute("DatiUtente");
		if(user!=null){
			long idVerbale = user.getIdVerbale();
//			AuVerbale verb=auditService.getVerbale(idVerbale);
//			getModel().setVerb(verb);
		}
		
		return SUCCESS;
	}
	
	
	
	//popola la tabella
	public String searchNonConformita(){
		HttpServletRequest request = ServletActionContext.getRequest();

		DatiUtente user= (DatiUtente)request.getSession().getAttribute("DatiUtente");
		if(user!=null){
			
			long idAudit= user.getIdAudit();
			
			List<NonConformitaPraticheDto>l =auditService.getTabNonConfPrat(idAudit, getModel().getiDisplayStart(), 
					getModel().getiDisplayLength(),
					getModel().getiSortCol_0(),
					getModel().getsSortDir_0(),
					getModel().getsSearch());
			
			
			 int total = auditService.countTabConfPrat(idAudit, getModel().getiDisplayStart(), 
						getModel().getiDisplayLength(),
						getModel().getiSortCol_0(),
						getModel().getsSortDir_0(),
						getModel().getsSearch());

				getModel().setiTotalDisplayRecords(total);
				getModel().setiTotalRecords(total);
			 
			 if(l.size()!=0){
				 for(NonConformitaPraticheDto s : l){
						getModel().getAaData().add(s);
					}
			 }
			
		}
		
		return SUCCESS;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return super.execute();
	}
	@Override
	public NonConformitaTablePaginator getModel() {
		return ncp;
	}

	public AuditService getAuditService() {
		return auditService;
	}

	public void setAuditService(AuditService auditService) {
		this.auditService = auditService;
	}
}
