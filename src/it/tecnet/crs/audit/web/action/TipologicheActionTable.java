package it.tecnet.crs.audit.web.action;

import it.tecnet.crs.audit.web.beans.ModelTipologica;
import it.tecnet.crs.audit.web.beans.ModelUtenti;
import it.tecnet.crs.jpa.model.AuTplIsnc;
import it.tecnet.crs.jpa.model.AuTplTipologiche;
import it.tecnet.crs.jpa.model.CrsBatchCaricamentoPratiche;
import it.tecnet.crs.jpa.model.CrsUtenteAdv;
import it.tecnet.crs.service.TipologicheService;
import it.tecnet.crs.session.DatiUtente;
import it.tecnet.crs.web.action.BaseAction;
import it.tecnet.crs.web.beans.AppUser;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ModelDriven;

public class TipologicheActionTable extends BaseAction implements ModelDriven<ModelTipologica> {
	protected static Logger log = Logger.getLogger(TipologicheActionTable.class);
	private TipologicheService tipologicheService;
	private ModelTipologica ma= new ModelTipologica();

	public TipologicheActionTable(TipologicheService tipologicheService) {
		super();
		this.tipologicheService=tipologicheService;
	}
	
	public String getCaricamentoPratiche() {
		HttpServletRequest request = ServletActionContext.getRequest();
		request.getSession().setAttribute("campagneList", tipologicheService.getCampagneList());
		request.getSession().setAttribute("sediList", tipologicheService.getSediList());
		return SUCCESS;
	}
	public String getTipologiche() {
		return SUCCESS;
	}
	public String getSoglieColori() {
		HttpServletRequest request = ServletActionContext.getRequest();
		request.getSession().setAttribute("auditList", tipologicheService.getAuditList());
		return SUCCESS;
	}

	public String searchSoglieColori(){
		HttpServletRequest request = ServletActionContext.getRequest();
		AppUser user=(AppUser)request.getSession().getAttribute("AppUser");
		request.getSession().setAttribute("validRolesList", tipologicheService.getValidRolesList());
		
		long idUtente=user.getIdUtente();
		String tipo=user.getRuoloAttivo();
		
		List<AuTplIsnc> auditList=	tipologicheService.getListIsnc(getModel().getiDisplayStart(),getModel().getiDisplayLength(),
				getModel().getiSortCol_0(),getModel().getsSortDir_0(),getModel().getsSearch());
		

		try{
			int total = tipologicheService.countIsnc(getModel().getsSearch()).intValue();

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
	
	public String searchCaricamentoPratiche(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		AppUser user=(AppUser)request.getSession().getAttribute("AppUser");
		
		
		long idUtente=user.getIdUtente();
		String tipo=user.getRuoloAttivo();
		
		List<CrsBatchCaricamentoPratiche> auditList=	tipologicheService.getListCaricamentoPratiche(getModel().getiDisplayStart(),getModel().getiDisplayLength(),
				getModel().getiSortCol_0(),getModel().getsSortDir_0(),getModel().getsSearch());
		

		try{
			int total = tipologicheService.countCaricametoPratiche(getModel().getsSearch()).intValue();

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
	
	public String searchTipologiche(){
		HttpServletRequest request = ServletActionContext.getRequest();
		AppUser user=(AppUser)request.getSession().getAttribute("AppUser");
		
		
		long idUtente=user.getIdUtente();
		String tipo=user.getRuoloAttivo();
		
		List<AuTplTipologiche> auditList=	tipologicheService.getListTipologiche(getModel().getiDisplayStart(),getModel().getiDisplayLength(),
				getModel().getiSortCol_0(),getModel().getsSortDir_0(),getModel().getsSearch());
		

		try{
			int total = tipologicheService.countTipologiche(getModel().getsSearch()).intValue();

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

	
	public String salvaNuovoCaricamentoPratica(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		AppUser user=(AppUser)request.getSession().getAttribute("AppUser");
		
		System.out.println("CAHIAMATO salvaNuovoCaricamentoPratica");
		CrsBatchCaricamentoPratiche a= getModel().getCaricamentopratica();
		
		a = (CrsBatchCaricamentoPratiche)tipologicheService.salva(a);
		

		
		return SUCCESS;
	}
	public String salvaNuovaTipologica(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		AppUser user=(AppUser)request.getSession().getAttribute("AppUser");
		
		
		AuTplTipologiche a= getModel().getTipologica();
		
		a = (AuTplTipologiche)tipologicheService.salva(a);
		

		
		return SUCCESS;
	}
	public String salvaNuovaIsnc(){
		HttpServletRequest request = ServletActionContext.getRequest();
		AppUser user=(AppUser)request.getSession().getAttribute("AppUser");
		
		
		AuTplIsnc a= getModel().getIsnc();
		
		a = (AuTplIsnc)tipologicheService.salva(a);
		

		
		return SUCCESS;
	}
	public String eliminaTipologica(){
		tipologicheService.deleteTipologica(getModel().getIdTplTipologica());
		return SUCCESS;
	}
	public String eliminaIsnc(){
		tipologicheService.deleteIsnc(getModel().getIdTplIsnc());
		return SUCCESS;
	}
	public String eliminaCaricamentoPratiche(){
		
		tipologicheService.deleteCaricamentoPratiche(getModel().getIdBcp());
		return SUCCESS;
	}

	
	
	/*
	 * inizializza le tab ../fasi/questionario per modificare l'audti selezionato
	 */
	
	public String modificaTipologicaLoad(){
		// salva l'idAudit in session
		HttpServletRequest request = ServletActionContext.getRequest();
		DatiUtente user= (DatiUtente)request.getSession().getAttribute("DatiUtente");
		AppUser appUser=(AppUser)request.getSession().getAttribute("AppUser");

		
		//il model viene inizializzato dalla loadDiv(..?idAudit=checkboxID)
		long idutente= getModel().getIdTplTipologica();
		AuTplTipologiche tipologica = tipologicheService.trova(AuTplTipologiche.class, idutente);
		getModel().setTipologica(tipologica);
		
		

		return SUCCESS;
	}
	public String modificaIsncLoad(){
		// salva l'idAudit in session
		HttpServletRequest request = ServletActionContext.getRequest();
		DatiUtente user= (DatiUtente)request.getSession().getAttribute("DatiUtente");
		AppUser appUser=(AppUser)request.getSession().getAttribute("AppUser");

		
		//il model viene inizializzato dalla loadDiv(..?idAudit=checkboxID)
		long idutente= getModel().getIdTplIsnc();
		AuTplIsnc tipologica = tipologicheService.trova(AuTplIsnc.class, idutente);
		getModel().setIsnc(tipologica);
		
		

		return SUCCESS;
	}
	public String modificaCaricamentoPraticaLoad(){
		// salva l'idAudit in session
		HttpServletRequest request = ServletActionContext.getRequest();
		DatiUtente user= (DatiUtente)request.getSession().getAttribute("DatiUtente");
		AppUser appUser=(AppUser)request.getSession().getAttribute("AppUser");

		
		//il model viene inizializzato dalla loadDiv(..?idAudit=checkboxID)
		long idutente= getModel().getIdBcp();
		CrsBatchCaricamentoPratiche caricamentopratica = tipologicheService.trova(CrsBatchCaricamentoPratiche.class, idutente);
		getModel().setCaricamentopratica(caricamentopratica);
		
		

		return SUCCESS;
	}


	


	public ModelTipologica getModel() {
		return ma;
	}

}
