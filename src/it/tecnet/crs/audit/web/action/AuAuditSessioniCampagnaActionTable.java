package it.tecnet.crs.audit.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import it.tecnet.crs.audit.service.AuCampagnaService;
import it.tecnet.crs.audit.web.beans.ModelSessioniCampagna;
import it.tecnet.crs.jpa.model.AuSessioni;
import it.tecnet.crs.session.DatiUtente;
import it.tecnet.crs.web.action.BaseAction;

import com.opensymphony.xwork2.ModelDriven;

public class AuAuditSessioniCampagnaActionTable extends BaseAction implements ModelDriven<ModelSessioniCampagna> {

	ModelSessioniCampagna msc= new ModelSessioniCampagna();
	private AuCampagnaService s;
	
	
	public AuAuditSessioniCampagnaActionTable(AuCampagnaService s){
		super();
		this.s=s;
		
	}
	
	public String getAccessiCampagna(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		// il model viene inizializzato dalla loadDiv(..?idAudit=checkboxID)

		DatiUtente user = (DatiUtente) request.getSession().getAttribute("DatiUtente");
		if (user != null) {
			long idCampagna=user.getIdCampagna();
			List<AuSessioni> l= s.getListaSessioniCampagna(idCampagna,getModel()
					.getiDisplayStart(), getModel().getiDisplayLength(), getModel()
					.getiSortCol_0(), getModel().getsSortDir_0(), getModel()
					.getsSearch());
			
				 int total = s.countListaSessioniCampagna(idCampagna,getModel().getiDisplayStart(), getModel().getiDisplayLength(), getModel().getiSortCol_0(), getModel().getsSortDir_0(), getModel()
						.getsSearch()).intValue();

				getModel().setiTotalDisplayRecords(total);
				getModel().setiTotalRecords(total);
			if(l.size()!=0){
				for(Object a:l){
					getModel().getAaData().add(a);
				}
			}
			
		}
		
	
	return SUCCESS;
	}
	
	
	
	
	
	
	
	
	public ModelSessioniCampagna getModel() {
		
		return msc;
	}

}
