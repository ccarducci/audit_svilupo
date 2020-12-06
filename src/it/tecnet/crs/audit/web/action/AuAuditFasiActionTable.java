package it.tecnet.crs.audit.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import it.tecnet.crs.audit.service.AuAuditService;
import it.tecnet.crs.audit.web.beans.ModelFasiAudit;
import it.tecnet.crs.audit.web.dto.AuditFasiDto;
import it.tecnet.crs.session.DatiUtente;
import it.tecnet.crs.web.action.BaseAction;

import com.opensymphony.xwork2.ModelDriven;

public class AuAuditFasiActionTable extends BaseAction implements ModelDriven<ModelFasiAudit> {

	private static final long serialVersionUID = 1L;
	private AuAuditService auditService;
	private ModelFasiAudit mfa= new ModelFasiAudit();
	//private DatiUtente datiUtenteSession = new DatiUtente();
	
	public AuAuditFasiActionTable(AuAuditService auditService) {
		super();
		this.setAuditService(auditService);
	}
	
	//tabella fasi in audit/generale
	public String getFasiModificaGenerale(){
		HttpServletRequest request = ServletActionContext.getRequest();
		
		DatiUtente user= (DatiUtente)request.getSession().getAttribute("DatiUtente");
		if (user!= null) {
			long idAudit=user.getIdAudit();
		
		
		List<AuditFasiDto> list= auditService.getFasiModificaGenerale(
				idAudit,
				getModel().getiDisplayStart(), 
				getModel().getiDisplayLength(),
				getModel().getiSortCol_0(),
				getModel().getsSortDir_0(), 
				getModel().getsSearch());
		
		
		
		int total = auditService.countFasiAudit(idAudit,getModel().getsSearch()).intValue();

		getModel().setiTotalDisplayRecords(total);
		getModel().setiTotalRecords(total);
		if(list.size()!=0){
			for(Object o : list){	
				
				getModel().getAaData().add(o);
			}
		}
		
		}
		return SUCCESS;
	}
	
	//tabella aside generale/fasi
	public String getFasiInAsideTable(){
		HttpServletRequest request = ServletActionContext.getRequest();
		DatiUtente user= (DatiUtente)request.getSession().getAttribute("DatiUtente");
		if (user!= null) {
			long idAudit=user.getIdAudit();
	
	
		
		long idProcesso=getModel().idProcessoMenuAside;
		System.out.print(idProcesso);
		
		
		List<AuditFasiDto> list= auditService.getFasiInAsideTable(idProcesso,idAudit,
				getModel().getiDisplayStart(), 
				getModel().getiDisplayLength(),
				getModel().getiSortCol_0(),
				getModel().getsSortDir_0(),
				getModel().getsSearch());
		
		
		if(list.size() !=0){
			for(Object o : list){	
				getModel().getAaData().add(o);
			}
			int total = auditService.countFasiInAsideTable(idProcesso,idAudit, getModel().getsSearch()).intValue();

			getModel().setiTotalDisplayRecords(total);
			getModel().setiTotalRecords(total);
		}
		
		
		
		}
		return SUCCESS;
	}
	
	//elimina la fase assegnata all'audit
	public String eliminaFase(){
		HttpServletRequest request = ServletActionContext.getRequest();
		DatiUtente user= (DatiUtente)request.getSession().getAttribute("DatiUtente");
		if (user!= null) {
			long idAudit=user.getIdAudit();
		
		
		auditService.eliminaFase(idAudit,getModel().getIdSottoprocesso());
		}
		return SUCCESS;
	}
	
	@Override
	public ModelFasiAudit getModel() {
		// TODO Auto-generated method stub
		return mfa;
	}
	public void setAuditService(AuAuditService auditService) {
		this.auditService = auditService;
	}

	public AuAuditService getAuditService() {
		return auditService;
	}

}
