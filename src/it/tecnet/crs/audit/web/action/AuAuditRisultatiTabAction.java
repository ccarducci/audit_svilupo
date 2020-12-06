package it.tecnet.crs.audit.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import it.tecnet.crs.audit.service.AuCampagnaService;
import it.tecnet.crs.audit.web.beans.ModelCampagna;
import it.tecnet.crs.audit.web.beans.ModelRisultatiTab;
import it.tecnet.crs.audit.web.dto.RisultatoRegolaCampagnaCampioneDto;
import it.tecnet.crs.session.DatiUtente;
import it.tecnet.crs.web.action.BaseAction;

import com.opensymphony.xwork2.ModelDriven;

public class AuAuditRisultatiTabAction extends BaseAction implements ModelDriven<ModelRisultatiTab> {
	
	
	private static final long serialVersionUID = 1L;
	
	private ModelRisultatiTab mrt= new ModelRisultatiTab();
	
	private AuCampagnaService campagnaService;
	
	private ModelCampagna mc= new ModelCampagna();
	public AuAuditRisultatiTabAction(AuCampagnaService campagnaService){
		super();
		this.setCampagnaService(campagnaService);
		
	}
	public String initPageRisultatiTab(){
		HttpServletRequest request = ServletActionContext.getRequest();

		DatiUtente user= (DatiUtente)request.getSession().getAttribute("DatiUtente");
		if (user!= null) {
			List<RisultatoRegolaCampagnaCampioneDto> dtoLs= new ArrayList<RisultatoRegolaCampagnaCampioneDto>();
			long idSessione= user.getIdSessione();
			//getLista id campioni
			
			List<Long> idRegole= campagnaService.getIdRegoleCampione(idSessione);
			
			
			if(idRegole.size()!=0){
				for(Long i : idRegole){
					 dtoLs=campagnaService.getRisultatiRegolaCampagnaCampione(i);
					
					 getModel().getMapRisultati().put(i, dtoLs);
					
				}
			}
			
			
		}
		
		return SUCCESS;
	}
	
	
	public AuCampagnaService getCampagnaService() {
		return campagnaService;
	}
	public void setCampagnaService(AuCampagnaService campagnaService) {
		this.campagnaService = campagnaService;
	}
	
	@Override
	public ModelRisultatiTab getModel() {
		return mrt;
	}

}
