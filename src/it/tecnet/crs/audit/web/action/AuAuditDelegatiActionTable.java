package it.tecnet.crs.audit.web.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import it.tecnet.crs.audit.service.AuAuditService;
import it.tecnet.crs.audit.web.beans.ModelDelegati;
import it.tecnet.crs.audit.web.dto.AuditDelegatoDto;
import it.tecnet.crs.jpa.model.AuAssUtenteAudit;
import it.tecnet.crs.web.action.BaseAction;
import it.tecnet.crs.web.beans.AppUser;
import com.opensymphony.xwork2.ModelDriven;

public class AuAuditDelegatiActionTable extends BaseAction implements ModelDriven<ModelDelegati> {

	private AuAuditService auditService;
	private ModelDelegati md= new ModelDelegati();

	public AuAuditDelegatiActionTable(AuAuditService auditService) {
		super();
		this.auditService=auditService;
	}
	
	public String getDelegati(){
		HttpServletRequest request = ServletActionContext.getRequest();
		AppUser user=(AppUser)request.getSession().getAttribute("AppUser");
		long idUtente=user.getIdUtente();
		long idAudit=user.getIdAudit();
		List<AuditDelegatoDto> listaDelegati =auditService.getListaDelegati(idUtente, idAudit,getModel().getiDisplayStart(), 
				getModel().getiDisplayLength(),
				getModel().getiSortCol_0(),
				getModel().getsSortDir_0(),
				getModel().getsSearch());
		
		if(listaDelegati.size()>0){
			for(AuditDelegatoDto del:listaDelegati){	
				getModel().getaaData().add(del);
			}
		}
		
		int total = auditService.countListaDelegati(idUtente, idAudit, getModel().getsSearch());
		getModel().setiTotalDisplayRecords(total);
		getModel().setiTotalRecords(total);
		
		return SUCCESS;
	}
	
	/**
	 * Il metodo recupera l'associazione presente sul DB e imposta la 
	 * data inizio alla data corrente sbiancando l'eventuale data fine presente.
	 * @return
	 */
	public String associaDelegato(){
		HttpServletRequest request = ServletActionContext.getRequest();
		AppUser user=(AppUser)request.getSession().getAttribute("AppUser");
		long idUtente=getModel().getIdDelegato();
		long idAudit=user.getIdAudit();
		
		//AuAssUtenteAudit ass = new AuAssUtenteAudit();
		AuAssUtenteAudit ass = auditService.getAssociazioneDelegato(idAudit, idUtente);
		ass.setIdUtente(idUtente);
		ass.setIdAudit(idAudit);
		ass.setDataInizio(new Date());
		ass.setDataFine(null);
		AuAssUtenteAudit managed=(AuAssUtenteAudit)auditService.salva(ass);
		
		return SUCCESS;
	}
	
	/**
	 * Il metodo recupera l'associazione presente sul DB e imposta la 
	 * data fine alla data corrente.
	 * @return
	 */
	public String dissociaDelegato(){
		HttpServletRequest request = ServletActionContext.getRequest();
		AppUser user=(AppUser)request.getSession().getAttribute("AppUser");
		long idUtente=getModel().getIdDelegato();
		long idAudit=user.getIdAudit();
		
		//AuAssUtenteAudit ass = new AuAssUtenteAudit();
		AuAssUtenteAudit ass = auditService.getAssociazioneDelegato(idAudit, idUtente);
		ass.setIdUtente(idUtente);
		ass.setIdAudit(idAudit);
		ass.setDataFine(new Date());
		AuAssUtenteAudit managed=(AuAssUtenteAudit)auditService.salva(ass);
		
		//auditService.dissociaDelegato(idAudit, idUtente);
		
		return SUCCESS;
	}

	public ModelDelegati getModel() {
		return md;
	}
}
