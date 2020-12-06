package it.tecnet.crs.web.action;

import it.tecnet.crs.service.AuditService;
import it.tecnet.crs.web.beans.AppUser;
import it.tecnet.crs.web.beans.PraticheTablePaginator;
import it.tecnet.crs.web.dto.VerbaleDto;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ModelDriven;

public class PraticaTableAction  extends BaseAction implements ModelDriven<PraticheTablePaginator> {


	private static final long serialVersionUID = 5973367340513057617L;
	private PraticheTablePaginator praticheTp = new PraticheTablePaginator();
	
	private AuditService auditService;
	
	public PraticaTableAction(AuditService auditService){
		super();
		this.setAuditService(auditService);
	}
	
	 public String searchPratiche(){
		 
		 HttpServletRequest request = ServletActionContext.getRequest();
			AppUser user= (AppUser)request.getSession().getAttribute("AppUser");
			if (user != null) {
				//TODO aggiungere la lista delle sessioni/accessi per auditors loggato
				
				String colunmName="";
				if(getModel().getiSortCol_0()!=0){
					colunmName=""+getModel().getiSortCol_0();
					
				}
				//String colunmName = getModel().getiSortCol_0() >= 0 ?getModel().columnPosition[ getModel().getiSortCol_0() ] : getModel().columnPosition[0];
				try{
					List<VerbaleDto> praticheList=auditService.getListaPraticheSessione(getModel().getSessionId(), getModel().getiDisplayStart(), getModel().getiDisplayLength(), colunmName, getModel().getsSortDir_0(), getModel().getsSearch());
					
					int total = auditService.countAllPraticheSessione(getModel().getSessionId(), getModel().getsSearch()).intValue();
					
					getModel().setiTotalDisplayRecords(total);
					getModel().setiTotalRecords(total);
					
					if(praticheList.size()!=0){
						for(Object pratiche : praticheList){	
							getModel().getAaData().add(pratiche);
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
	public PraticheTablePaginator getModel() {
		return praticheTp;
	}

	public void setAuditService(AuditService auditService) {
		this.auditService = auditService;
	}

	public AuditService getAuditService() {
		return auditService;
	}

}
