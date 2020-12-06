package it.tecnet.crs.web.action;

import it.tecnet.crs.service.AuditService;
import it.tecnet.crs.web.beans.AppUser;
import it.tecnet.crs.web.beans.SessioneTablePaginator;
import it.tecnet.crs.web.dto.SessioneDto;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ModelDriven;

public class SessioneTableAction extends BaseAction implements ModelDriven<SessioneTablePaginator> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6171674902864842397L;

	private SessioneTablePaginator sessioneTp = new SessioneTablePaginator();



	private AuditService auditService;


	public SessioneTableAction(AuditService auditService) {
		super();
		this.auditService = auditService;
	}

	public String searchSessioni(){

		HttpServletRequest request = ServletActionContext.getRequest();
		AppUser user= (AppUser)request.getSession().getAttribute("AppUser");
		if (user != null) {
			String colunmName="1";
			if(getModel().getiSortCol_0()!=0){
				colunmName=""+getModel().getiSortCol_0();
			}

			String currentRole=user.getRuoloAttivo();

			try{
				List<SessioneDto> sessionList=new ArrayList<SessioneDto>();
				if(currentRole.equalsIgnoreCase("dirigente")){

					sessionList=auditService.getListaSessioniUtenteAsDirigente(user.getIdUtente(),getModel().getiDisplayStart(), getModel().getiDisplayLength(), colunmName, getModel().getsSortDir_0(), getModel().getsSearch());
					int total = auditService.countAllSessioniUtenteAsDirigente(user.getIdUtente(), getModel().getsSearch()).intValue();

					getModel().setiTotalDisplayRecords(total);
					getModel().setiTotalRecords(total);


					if(sessionList.size()!=0){
						for(Object sessione : sessionList){	
							getModel().getAaData().add(sessione);
						}
					}
				}else{
					sessionList=auditService.getListaSessioniUtente(user.getIdUtente(),getModel().getiDisplayStart(), getModel().getiDisplayLength(), colunmName, getModel().getsSortDir_0(), getModel().getsSearch());
					int total = auditService.countAllSessioniUtente(user.getIdUtente(), getModel().getsSearch()).intValue();

					getModel().setiTotalDisplayRecords(total);
					getModel().setiTotalRecords(total);


					if(sessionList.size()!=0){
						for(Object sessione : sessionList){	
							getModel().getAaData().add(sessione);
						}
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
	public SessioneTablePaginator getModel() {
		return sessioneTp;
	}




	public AuditService getAuditService() {
		return auditService;
	}




	public void setAuditService(AuditService auditService) {
		this.auditService = auditService;
	}


}
