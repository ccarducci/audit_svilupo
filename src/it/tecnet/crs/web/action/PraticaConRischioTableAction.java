package it.tecnet.crs.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import it.tecnet.crs.service.AuditService;
import it.tecnet.crs.web.beans.AppUser;
import it.tecnet.crs.web.beans.PraticheConRischioTablePaginator;
import it.tecnet.crs.web.dto.PraticaConRischioDto;
import com.opensymphony.xwork2.ModelDriven;

public class PraticaConRischioTableAction  extends BaseAction implements ModelDriven<PraticheConRischioTablePaginator> {

	
	private static final long serialVersionUID = 1L;
	private AuditService auditService;
	private PraticheConRischioTablePaginator praticheConRischioTp = new PraticheConRischioTablePaginator();
	public PraticaConRischioTableAction(AuditService auditService){
		super();
		this.auditService=auditService;
	}
	public String searchPraticheConRischio(){
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
					List<PraticaConRischioDto> praticheRischioList=auditService.getListaPraticheRischio(getModel().getSessionId(), getModel().getiDisplayStart(), getModel().getiDisplayLength(), colunmName, getModel().getsSortDir_0(), getModel().getsSearch());
					
					int total = auditService.countAllPraticheConRischio(getModel()
							.getSessionId(), getModel().getsSearch(), colunmName,
							getModel().getsSortDir_0(), getModel().getiDisplayStart(),
							getModel().getiDisplayLength());
					
					getModel().setiTotalDisplayRecords(total);
					getModel().setiTotalRecords(total);
					
					if(praticheRischioList.size()!=0){
						for(Object praticheConRischio : praticheRischioList){	
							getModel().getAaData().add(praticheConRischio);
						}
					}
				
					
				}catch(Exception e){
					e.printStackTrace();
				}
				
				
			}
		
		return SUCCESS;
	}
	@Override
	public PraticheConRischioTablePaginator getModel() {
		// TODO Auto-generated method stub
		return praticheConRischioTp;
	}
	public void setRaticheConRischioTp(PraticheConRischioTablePaginator raticheConRischioTp) {
		this.praticheConRischioTp = raticheConRischioTp;
	}
	public PraticheConRischioTablePaginator getRaticheConRischioTp() {
		return praticheConRischioTp;
	}

	
	
	
	
	
}
