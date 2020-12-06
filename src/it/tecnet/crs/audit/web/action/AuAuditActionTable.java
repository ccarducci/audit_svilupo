package it.tecnet.crs.audit.web.action;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import it.tecnet.crs.audit.service.AuAuditService;
import it.tecnet.crs.audit.web.beans.ModelAudit;
import it.tecnet.crs.session.DatiUtente;
import it.tecnet.crs.web.action.BaseAction;
import it.tecnet.crs.web.beans.AppUser;
import it.tecnet.crs.web.dto.QuestionarioDto;
import it.tecnet.crs.audit.web.dto.AuAuditDto;
import it.tecnet.crs.jpa.model.AuAssUtenteAudit;
import it.tecnet.crs.jpa.model.AuAudit;
import it.tecnet.crs.jpa.model.AuQuestionario;
import it.tecnet.crs.mod.web.dto.ProcessoDto;
import com.opensymphony.xwork2.ModelDriven;

public class AuAuditActionTable extends BaseAction implements ModelDriven<ModelAudit> {
	protected static Logger log = Logger.getLogger(AuAuditActionTable.class);
	private AuAuditService auditService;
	private ModelAudit ma= new ModelAudit();

	public AuAuditActionTable(AuAuditService auditService) {
		super();
		this.auditService=auditService;
	}

	//ritorna solo la pagina da mostrare
	public String getAudit() {

		return SUCCESS;
	}

	//get di tutti gli audit + count
	public String searchAuAudit(){
		HttpServletRequest request = ServletActionContext.getRequest();
		AppUser user=(AppUser)request.getSession().getAttribute("AppUser");
		long idUtente=user.getIdUtente();
		String tipo=user.getRuoloAttivo();
		
		List<AuAuditDto> auditList=	auditService.getListAuditGenerale(idUtente,getModel().getiDisplayStart(),getModel().getiDisplayLength(),
				getModel().getiSortCol_0(),getModel().getsSortDir_0(),getModel().getsSearch());
		

		try{
			int total = auditService.countAudit(idUtente,getModel().getsSearch()).intValue();

			getModel().setiTotalDisplayRecords(total);
			getModel().setiTotalRecords(total);

			if(auditList==null){
				return SUCCESS;
			}

			for(Object o : auditList){	
				getModel().getAaData().add(o);

			}
		}catch(Exception e){
			e.printStackTrace();
			log.error(e);
		}

		return SUCCESS;

	}


	/*
	 * SALVA NUOVO AUDIT
	 */
	public String salvaNuovoAudit(){
		HttpServletRequest request = ServletActionContext.getRequest();
		AppUser user=(AppUser)request.getSession().getAttribute("AppUser");
		long idUtente=user.getIdUtente();
		
		AuAudit a= getModel().getAudit();
		AuQuestionario q= new AuQuestionario();
	
		a=(AuAudit) auditService.salva(a);
		//creo il questionario per quell'audit
		q.setIdAudit(a.getIdAudit());
		auditService.salva(q);
		
		AuAssUtenteAudit ass = auditService.getAssociazioneDelegato(a.getIdAudit(), idUtente);
		ass.setIdUtente(idUtente);
		ass.setIdAudit(a.getIdAudit());
		ass.setDataInizio(new Date());
		ass.setDataFine(null);
		AuAssUtenteAudit managed=(AuAssUtenteAudit)auditService.salva(ass);
		
		return SUCCESS;
	}

	/*
	 * ELIMINA AUDIT 
	 */
	public String eliminaAudit(){
		auditService.deleteAudit(getModel().getIdAudit());
		return SUCCESS;
	}

	/*
	 * MODIFICA AUDIT
	 */
	public String salvaModificheAudit(){
		HttpServletRequest request = ServletActionContext.getRequest();
		//il model viene inizializzato dalla loadDiv(..?idAudit=checkboxID)

		DatiUtente user= (DatiUtente)request.getSession().getAttribute("DatiUtente");
		if (user!= null) {
			AuAudit a= getModel().getAudit();
			a.setIdAudit(user.getIdAudit());
			auditService.salvaModifiche(a);
		}





		return SUCCESS;
	}
	
	
	/*
	 * inizializza le tab ../fasi/questionario per modificare l'audti selezionato
	 */
	
	public String getDataToUpdateAudit(){
		// salva l'idAudit in session
		HttpServletRequest request = ServletActionContext.getRequest();
		DatiUtente user= (DatiUtente)request.getSession().getAttribute("DatiUtente");
		AppUser appUser=(AppUser)request.getSession().getAttribute("AppUser");

		
		//il model viene inizializzato dalla loadDiv(..?idAudit=checkboxID)
		long idAudit= getModel().getIdAudit();
		appUser.setIdAudit(idAudit);
		String nomintivo=appUser.getNominativo();
		getModel().setNomeDirigenteAudit(nomintivo);
		if (user!= null) {

			user.setIdAudit(idAudit);
		}

		try{
			AuAudit a=auditService.getAuditDaModificare(idAudit);
			getModel().setAudit(a);
		}catch(Exception e){
			e.printStackTrace();
		}

		//popola menu a tendina dell'aside (aggiungi fase all'audit)
		List<ProcessoDto>list=auditService.getProcessi();

		if(list.size() !=0){
			for(ProcessoDto l: list){
				getModel().getListMenu().add(l);
			}
		}

		

		return SUCCESS;
	}


	public String addFasiToAudit(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		DatiUtente user= (DatiUtente)request.getSession().getAttribute("DatiUtente");
		if (user!= null) {
			long idAudit=user.getIdAudit();

			String fasi=getModel().idFaseToAdd;
			String[] idFasiDaAggiungere= fasi.split("-");

			for(String i : idFasiDaAggiungere){
				auditService.addFaseToAudit(Long.parseLong(i), idAudit);
			}
		}
		return SUCCESS;
	}

	/*
	 * QUESTIONARIO
	 */



	
	public String checkIfAuditHasChildren(){
		long idAudit=getModel().getIdAudit();
		//getModel().setCampagneAssociateAudit(auditService.checkIfAuditHasCampagne(idAudit));
		getModel().setIsAuditReferenced(auditService.isAuditReferenced(idAudit));
		getModel().setQuestionariAssociatiAudit(auditService.checkIfAuditHasQuestionari(idAudit));
		return SUCCESS;
	}

	
	public String deleteQuestionario(){
		long idAudit=getModel().getIdAudit();
		auditService.deleteQuestionario(idAudit);
		return SUCCESS;
	}
	


	public ModelAudit getModel() {
		return ma;
	}

}
