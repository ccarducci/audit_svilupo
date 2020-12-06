package it.tecnet.crs.web.action;

import it.tecnet.crs.service.AuditService;
import it.tecnet.crs.web.beans.AppUser;
import it.tecnet.crs.web.beans.IndicatoriTablePaginator;
import it.tecnet.crs.web.dto.SessioneDto;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ModelDriven;

public class FaseNotificaTableAction extends BaseAction implements ModelDriven<IndicatoriTablePaginator> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6171674902864842397L;

	private IndicatoriTablePaginator model = new IndicatoriTablePaginator();

	private AuditService auditService;


	public FaseNotificaTableAction(AuditService auditService) {
		super();
		this.auditService = auditService;
	}

	public String search(){

		HttpServletRequest request = ServletActionContext.getRequest();
		
		AppUser user= (AppUser)request.getSession().getAttribute("AppUser");
		if (user != null) {
			//TODO aggiungere la lista delle sessioni/accessi per auditors loggato
			
			String colunmName="";
			if(getModel().getiSortCol_0()!=0){
				colunmName=""+getModel().getiSortCol_0();
				
			}
			
			try{
				List<SessioneDto> sessionList=auditService.getListaSessioniUtente(user.getIdUtente(),getModel().getiDisplayStart(), getModel().getiDisplayLength(), colunmName, getModel().getsSortDir_0(), getModel().getsSearch());
				int total = auditService.countAllSessioniUtente(user.getIdUtente(), getModel().getsSearch()).intValue();

				getModel().setiTotalDisplayRecords(total);
				getModel().setiTotalRecords(total);
				
				if(sessionList.size()!=0){
					for(Object sessione : sessionList){	
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
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return super.execute();
	}


	@Override
	public IndicatoriTablePaginator getModel() {
		return model;
	}




	public AuditService getAuditService() {
		return auditService;
	}




	public void setAuditService(AuditService auditService) {
		this.auditService = auditService;
	}


}
