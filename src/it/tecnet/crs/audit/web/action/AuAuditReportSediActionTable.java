package it.tecnet.crs.audit.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import it.tecnet.crs.audit.model.AuSediCritiche;
import it.tecnet.crs.audit.service.AuCampagnaService;
import it.tecnet.crs.audit.web.beans.ModelReportSedi;
import it.tecnet.crs.audit.web.dto.ReportSediDto;
import it.tecnet.crs.session.DatiUtente;
import it.tecnet.crs.web.action.BaseAction;

import com.opensymphony.xwork2.ModelDriven;

public class AuAuditReportSediActionTable extends BaseAction implements ModelDriven<ModelReportSedi> {
	
	
	private static final long serialVersionUID = 1L;
	
	private ModelReportSedi mrs= new ModelReportSedi();
	
	private AuCampagnaService campagnaService;
	
	
	public AuAuditReportSediActionTable(AuCampagnaService campagnaService){
		super();
		this.setCampagnaService(campagnaService);
		
	}

	public String searchReportSedi(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		
		DatiUtente user= (DatiUtente)request.getSession().getAttribute("DatiUtente");
			if (user!= null) {
				int total=0;
				long idCampagna=user.getIdCampagna();
				 List<AuSediCritiche> list= campagnaService.getListaSediCritiche(getModel()
							.getiDisplayStart(), getModel().getiDisplayLength(), getModel()
							.getiSortCol_0(), getModel().getsSortDir_0(), getModel()
							.getsSearch());
				
				total  = campagnaService.countSediCritiche(getModel().getiDisplayStart(), getModel().getiDisplayLength(), getModel()
							.getiSortCol_0(), getModel().getsSortDir_0(), getModel()
							.getsSearch()).intValue();

					getModel().setiTotalDisplayRecords(total);
					getModel().setiTotalRecords(total);
				 
				 if(list.size()!=0){
					 for(Object s : list){
							getModel().getAaData().add(s);
						}
				 }
				
			}
			
		
	
		
		return SUCCESS;
	}

	
	
	
	
	public ModelReportSedi getModel() {
		
		return mrs;
	}

	public void setCampagnaService(AuCampagnaService campagnaService) {
		this.campagnaService = campagnaService;
	}

	public AuCampagnaService getCampagnaService() {
		return campagnaService;
	}

}
