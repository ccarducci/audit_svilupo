package it.tecnet.crs.audit.web.action;

import it.tecnet.crs.audit.service.AuAuditService;
import it.tecnet.crs.audit.web.beans.ModelAuditors;
import it.tecnet.crs.audit.web.dto.AuAuditorsDto;
import it.tecnet.crs.jpa.model.AuAssUtenteSessione;
import it.tecnet.crs.service.UtenzeService;
import it.tecnet.crs.session.DatiUtente;
import it.tecnet.crs.web.action.BaseAction;
import it.tecnet.crs.web.beans.AppUser;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ModelDriven;

public class AuAuditAuditorsActionTable extends BaseAction implements ModelDriven<ModelAuditors> {

	private AuAuditService auditService;
	private UtenzeService utenzeService;
	private ModelAuditors ma= new ModelAuditors();

	public AuAuditAuditorsActionTable(AuAuditService auditService, UtenzeService utenzeService) {
		super();
		this.auditService=auditService;
		this.utenzeService=utenzeService;
	}
	
	public String getAuditors(){
		HttpServletRequest request = ServletActionContext.getRequest();
		AppUser appUser=(AppUser)request.getSession().getAttribute("AppUser");
		DatiUtente user = (DatiUtente) request.getSession().getAttribute("DatiUtente");
		
		long idUtente=appUser.getIdUtente();
		long idAccesso=user.getIdSessione();
		
		if(appUser.getRuoloAttivo().startsWith("delegato")){
			idUtente=utenzeService.getDirigenteByUserId(idUtente);
		}
		
		List<AuAuditorsDto> listaAuditors =auditService.getListaAuditors(idUtente, idAccesso,getModel().getiDisplayStart(), 
				getModel().getiDisplayLength(),
				getModel().getiSortCol_0(),
				getModel().getsSortDir_0(),
				getModel().getsSearch());
		
		if(listaAuditors.size()>0){
			for(AuAuditorsDto auditors:listaAuditors){	
				getModel().getaaData().add(auditors);
			}
		}
		
		int total = auditService.countListaAuditors(idUtente, idAccesso, getModel().getsSearch());
		getModel().setiTotalDisplayRecords(total);
		getModel().setiTotalRecords(total);
		
		return SUCCESS;
	}
	
	/**
	 * Il metodo recupera l'associazione presente sul DB e imposta la 
	 * data inizio alla data corrente sbiancando l'eventuale data fine presente.
	 * @return
	 */
	public String associaAuditors(){
		HttpServletRequest request = ServletActionContext.getRequest();
		DatiUtente user = (DatiUtente) request.getSession().getAttribute("DatiUtente");
		
		long idUtente=getModel().getIdAuditors();
		long idAccesso=user.getIdSessione();
		
		//AuAssUtenteSessione ass = new AuAssUtenteSessione();
		AuAssUtenteSessione ass = auditService.getAssociazioneAuditors(idAccesso, idUtente);
		ass.setIdSessione(idAccesso);
		ass.setIdUtente(idUtente);
		ass.setDataInizio(new Date());
		ass.setDataFine(null);
		AuAssUtenteSessione managed=(AuAssUtenteSessione)auditService.salva(ass);
		
		return SUCCESS;
	}
	
	/**
	 * Il metodo recupera l'associazione presente sul DB e imposta la 
	 * data fine alla data corrente.
	 * @return
	 */
	public String dissociaAuditors(){
		HttpServletRequest request = ServletActionContext.getRequest();
		DatiUtente user = (DatiUtente) request.getSession().getAttribute("DatiUtente");
		
		long idUtente=getModel().getIdAuditors();
		long idAccesso=user.getIdSessione();
		
		AuAssUtenteSessione ass = auditService.getAssociazioneAuditors(idAccesso, idUtente);
		ass.setIdSessione(idAccesso);
		ass.setIdUtente(idUtente);
		ass.setDataFine(new Date());
		AuAssUtenteSessione managed=(AuAssUtenteSessione)auditService.salva(ass);
		
		//auditService.dissociaAuditors(idAccesso, idUtente);
		
		return SUCCESS;
	}

	public ModelAuditors getModel() {
		return ma;
	}
}