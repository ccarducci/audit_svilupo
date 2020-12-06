package it.tecnet.crs.web.action;


import it.tecnet.crs.service.AuditService;
import it.tecnet.crs.session.DatiUtente;
import it.tecnet.crs.web.beans.RischiPratichePaginator;
import it.tecnet.crs.web.dto.RischiPraticheDto;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ModelDriven;

public class RischiPraticheAction extends BaseAction implements ModelDriven<RischiPratichePaginator> {



	private static final long serialVersionUID = 1L;
	private RischiPratichePaginator model = new RischiPratichePaginator();
	private AuditService auditService;

	public RischiPraticheAction(AuditService auditService){
		super();
		this.auditService= auditService;
	}

	public String getTabRischioPratiche(){

		HttpServletRequest request = ServletActionContext.getRequest();

		DatiUtente user= (DatiUtente)request.getSession().getAttribute("DatiUtente");
		if(user!=null){
			long idVerbale = user.getIdVerbale();
			long idAudit= user.getIdAudit();

//			AuVerbale v=auditService.cerca(AuVerbale.class, idVerbale);	
//			
//			getModel().setV(v);

		}


		return SUCCESS;
	}

	public String searchRischiPratiche(){
		HttpServletRequest request = ServletActionContext.getRequest();

		DatiUtente user= (DatiUtente)request.getSession().getAttribute("DatiUtente");
		if(user!=null){
			
			long idAudit= user.getIdAudit();
			
			List<RischiPraticheDto>l =auditService.getTabRischiPrat(idAudit, getModel().getiDisplayStart(), 
					getModel().getiDisplayLength(),
					getModel().getiSortCol_0(),
					getModel().getsSortDir_0(),
					getModel().getsSearch());
			
			
			 int total = auditService.countTabRischiPrat(idAudit,getModel()
						.getiDisplayStart(), getModel().getiDisplayLength(), getModel()
						.getiSortCol_0(), getModel().getsSortDir_0(), getModel()
						.getsSearch());

				getModel().setiTotalDisplayRecords(total);
				getModel().setiTotalRecords(total);
			 
			 if(l.size()!=0){
				 for(Object s : l){
						getModel().getAaData().add(s);
					}
			 }
			
			
			
			
			
			
			
		}
		return SUCCESS;
	}

	@Override
	public RischiPratichePaginator getModel() {
		return model;
	}







	public void setAuditService(AuditService auditService) {
		this.auditService = auditService;
	}







	public AuditService getAuditService() {
		return auditService;
	}

}
