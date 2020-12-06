package it.tecnet.crs.audit.web.action;

import it.tecnet.crs.audit.web.beans.ModelUtenti;
import it.tecnet.crs.jpa.model.CrsUtenteAdv;
import it.tecnet.crs.service.UtenzeService;
import it.tecnet.crs.session.DatiUtente;
import it.tecnet.crs.web.action.BaseAction;
import it.tecnet.crs.web.beans.AppUser;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ModelDriven;

public class UserListActionTable extends BaseAction implements ModelDriven<ModelUtenti> {
	protected static Logger log = Logger.getLogger(UserListActionTable.class);
	private UtenzeService userService;
	private ModelUtenti ma= new ModelUtenti();

	public UserListActionTable(UtenzeService userService) {
		super();
		this.userService=userService;
	}

	//ritorna solo la pagina da mostrare
	public String getUsers() {
		HttpServletRequest request = ServletActionContext.getRequest();
		request.getSession().setAttribute("validRolesList", userService.getValidRolesList());
		return SUCCESS;
	}

	//get di tutti gli audit + count
	public String searchUsers(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		AppUser user=(AppUser)request.getSession().getAttribute("AppUser");
		request.getSession().setAttribute("validRolesList", userService.getValidRolesList());
		
		long idUtente=user.getIdUtente();
		String tipo=user.getRuoloAttivo();
		
		List<CrsUtenteAdv> auditList=	userService.getListUtenti(getModel().getiDisplayStart(),getModel().getiDisplayLength(),
				getModel().getiSortCol_0(),getModel().getsSortDir_0(),getModel().getsSearch());
		

		try{
			int total = userService.countUsers(getModel().getsSearch()).intValue();

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
	public String salvaNuovoUtente(){
		HttpServletRequest request = ServletActionContext.getRequest();
		AppUser user=(AppUser)request.getSession().getAttribute("AppUser");
		long idUtente=user.getIdUtente();
		
		CrsUtenteAdv a= getModel().getUtente();
		String rolesString = getModel().getRuoli()  ;
//		System.out.println("CHIAMATO s "+getModel().getRuoli()  );
		a = (CrsUtenteAdv)userService.salva(a);
		String[] ruoli = rolesString.split(",");

		userService.saveRuoliUtente(a.getIdUtente(), Arrays.asList(ruoli));

		return SUCCESS;
	}

	/*
	 * ELIMINA AUDIT 
	 */
	public String eliminaUtente(){
		userService.deleteUtente(getModel().getIdUtente());
		return SUCCESS;
	}

	/*
	 * MODIFICA AUDIT
	 */
	public String salvaModificheAudit(){
//		HttpServletRequest request = ServletActionContext.getRequest();
//		//il model viene inizializzato dalla loadDiv(..?idAudit=checkboxID)
//
//		DatiUtente user= (DatiUtente)request.getSession().getAttribute("DatiUtente");
//		if (user!= null) {
//			AuAudit a= getModel().getAudit();
//			a.setIdAudit(user.getIdAudit());
//			auditService.salvaModifiche(a);
//		}





		return SUCCESS;
	}
	
	
	/*
	 * inizializza le tab ../fasi/questionario per modificare l'audti selezionato
	 */
	
	public String modificaUtenteLoad(){
		// salva l'idAudit in session
		HttpServletRequest request = ServletActionContext.getRequest();
		DatiUtente user= (DatiUtente)request.getSession().getAttribute("DatiUtente");
		AppUser appUser=(AppUser)request.getSession().getAttribute("AppUser");

		
		//il model viene inizializzato dalla loadDiv(..?idAudit=checkboxID)
		long idutente= getModel().getIdUtente();
		CrsUtenteAdv utente = userService.trova(CrsUtenteAdv.class, idutente);
		getModel().setRuoliUpdate(userService.getRuoliUtente(idutente));
		getModel().setUtente(utente);
		
		

		return SUCCESS;
	}


	public String addFasiToAudit(){
		
//		HttpServletRequest request = ServletActionContext.getRequest();
//		DatiUtente user= (DatiUtente)request.getSession().getAttribute("DatiUtente");
//		if (user!= null) {
//			long idAudit=user.getIdAudit();
//
//			String fasi=getModel().idFaseToAdd;
//			String[] idFasiDaAggiungere= fasi.split("-");
//
//			for(String i : idFasiDaAggiungere){
//				auditService.addFaseToAudit(Long.parseLong(i), idAudit);
//			}
//		}
		return SUCCESS;
	}

	/*
	 * QUESTIONARIO
	 */



	
	public String checkIfAuditHasChildren(){
//		long idAudit=getModel().getIdAudit();
//		//getModel().setCampagneAssociateAudit(auditService.checkIfAuditHasCampagne(idAudit));
//		getModel().setIsAuditReferenced(auditService.isAuditReferenced(idAudit));
//		getModel().setQuestionariAssociatiAudit(auditService.checkIfAuditHasQuestionari(idAudit));
		return SUCCESS;
	}

	
	public String deleteQuestionario(){
//		long idAudit=getModel().getIdAudit();
//		auditService.deleteQuestionario(idAudit);
		return SUCCESS;
	}
	


	public ModelUtenti getModel() {
		return ma;
	}

}
