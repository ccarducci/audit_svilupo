package it.tecnet.crs.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import it.tecnet.crs.jpa.model.AuMRischio;
import it.tecnet.crs.service.AuditService;
import it.tecnet.crs.session.DatiUtente;
import it.tecnet.crs.web.beans.RischiAccessiPaginator;
import it.tecnet.crs.web.dto.NonConformitaAccessiDto;
import it.tecnet.crs.web.dto.RischiAccessiDto;

import com.opensymphony.xwork2.ModelDriven;

public class RischiAccessiAction extends BaseAction implements ModelDriven<RischiAccessiPaginator> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private RischiAccessiPaginator model= new RischiAccessiPaginator();
	private AuditService auditService;
	
	public RischiAccessiAction(AuditService auditService) {
		super();
		this.setAuditService(auditService);
	}
	
	public String getTabRischiAccessi(){
		
		
		return SUCCESS;
	}
	
	public String searchRischiAccessi(){
		
		HttpServletRequest request = ServletActionContext.getRequest();

		DatiUtente user= (DatiUtente)request.getSession().getAttribute("DatiUtente");
		if(user!=null){
			
			long idSSessione=user.getIdSSessione();
			
			List<RischiAccessiDto>l =auditService.getTabRischiAccess(idSSessione, getModel().getiDisplayStart(), 
					getModel().getiDisplayLength(),
					getModel().getiSortCol_0(),
					getModel().getsSortDir_0(),
					getModel().getsSearch());
			
			
			 int total = auditService.countTabRischiAccess(idSSessione, getModel().getiDisplayStart(), getModel().getiDisplayLength(),
						getModel().getiSortCol_0(),
						getModel().getsSortDir_0(),
						getModel().getsSearch());

				getModel().setiTotalDisplayRecords(total);
				getModel().setiTotalRecords(total);
			 
			 if(l.size()!=0){
				 for(RischiAccessiDto s : l){
						getModel().getAaData().add(s);
					}
			 }
			
		}
		
		
		return SUCCESS;
		
	}
	
	public String getDettagliRischiAccessi(){
		
		HttpServletRequest request = ServletActionContext.getRequest();

		DatiUtente user= (DatiUtente)request.getSession().getAttribute("DatiUtente");
		if(user!=null){
			
			long idSSessione=user.getIdSSessione();
			
			String id= getModel().getId();
			String []p= id.split("-");
			long idRischioM= Long.valueOf(p[0]);
			long idRischioS=Long.valueOf(p[1]);
			
			RischiAccessiDto nc=auditService.getDettagliRischi(idRischioM, idRischioS);
			getModel().setR(nc);
			
		}
		
		return SUCCESS;
	}
	
	public String getStatoEspRischiAccessi(){
		
		try{
			HttpServletRequest request = ServletActionContext.getRequest();

			DatiUtente user= (DatiUtente)request.getSession().getAttribute("DatiUtente");
			if(user!=null){
				
				long idSSessione=user.getIdSSessione();
				
				String id= getModel().getId();
				String []p= id.split("-");
				long idRischioM= Long.valueOf(p[0]);
				long idRischioS=Long.valueOf(p[1]);
				
				user.setIdMRischio(idRischioM);
				user.setIdSRischio(idRischioS);
				
				AuMRischio rm= auditService.cerca(AuMRischio.class, idRischioM);
				RischiAccessiDto r= new RischiAccessiDto();
				r.setDescrizione(rm.getDescrizioneRischio());
				getModel().setR(r);
				
				
				
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
			
		}
		
		
		return SUCCESS;
	}
	
	public String searchStatoEsprRischioTable(){
		
		HttpServletRequest request = ServletActionContext.getRequest();

		DatiUtente user= (DatiUtente)request.getSession().getAttribute("DatiUtente");
		if(user!=null){
			
			long idMRischio= user.getIdMRischio();
			long idSsessione= user.getIdSSessione();
			
			List<RischiAccessiDto>l =auditService.getStatoEsprRischioTable(idMRischio,idSsessione, getModel().getiDisplayStart(), 
					getModel().getiDisplayLength(),
					getModel().getiSortCol_0(),
					getModel().getsSortDir_0(),
					getModel().getsSearch());
			
			int total = auditService.countStatoEsprRischioTable(idMRischio,idSsessione,getModel().getsSearch());
			
			getModel().setiTotalDisplayRecords(total);
			getModel().setiTotalRecords(total);
			if(l!=null){
			 
				 for(RischiAccessiDto s : l){
						getModel().getAaData().add(s);
					}
			
			}
		}
		
		
		
		return SUCCESS;
	}
	
	@Override
	public RischiAccessiPaginator getModel() {
		// TODO Auto-generated method stub
		return model;
	}





	public void setAuditService(AuditService auditService) {
		this.auditService = auditService;
	}





	public AuditService getAuditService() {
		return auditService;
	}
	
	

}
