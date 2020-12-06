package it.tecnet.crs.ATPO.audit.web.action;

import it.tecnet.crs.audit.service.AuCampagnaService;
import it.tecnet.crs.audit.web.beans.ModelVerbaliAudit;
import it.tecnet.crs.web.action.BaseAction;

import com.opensymphony.xwork2.ModelDriven;

public class AuAuditVerbaliATPOActionTable extends BaseAction implements ModelDriven<ModelVerbaliAudit> {
	
	private static final long serialVersionUID = 1L;
	private ModelVerbaliAudit mva= new ModelVerbaliAudit();
	private AuCampagnaService campagnaService;
	
	public AuAuditVerbaliATPOActionTable( AuCampagnaService campagnaService){
		super();
		this.campagnaService= campagnaService;
		
	}

	public String getVerbaliCampagna(){
//		HttpServletRequest request = ServletActionContext.getRequest();
//
//		DatiUtente user= (DatiUtente)request.getSession().getAttribute("DatiUtente");
//		if (user!= null) {
//		
//			long idCampagna=user.getIdCampagna();
//	
//		List<AuVerbale>l =campagnaService.getListaVerbaliCampagna(idCampagna,getModel()
//				.getiDisplayStart(), getModel().getiDisplayLength(), getModel()
//				.getiSortCol_0(), getModel().getsSortDir_0(), getModel()
//				.getsSearch());
//		
//		 int total = campagnaService.countListaVerbaliCampagna(idCampagna,getModel()
//					.getiDisplayStart(), getModel().getiDisplayLength(), getModel()
//					.getiSortCol_0(), getModel().getsSortDir_0(), getModel()
//					.getsSearch()).intValue();
//
//			getModel().setiTotalDisplayRecords(total);
//			getModel().setiTotalRecords(total);
//		 
//		 if(l.size()!=0){
//			 for(Object s : l){
//					getModel().getAaData().add(s);
//				}
//		 }
//		
//		}
		return SUCCESS;
	}

	@Override
	public ModelVerbaliAudit getModel() {
		
		return mva;
	}

}
