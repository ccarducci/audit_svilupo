package it.tecnet.crs.mod.web.action;

import it.tecnet.crs.componenti.jpa.model.CrsDomini;
import it.tecnet.crs.componenti.web.bean.CircolariInps;
import it.tecnet.crs.componenti.web.bean.DatiTipoNormativa;
import it.tecnet.crs.componenti.web.bean.LeggiDecreti;
import it.tecnet.crs.componenti.web.bean.MessaggiInps;
import it.tecnet.crs.componenti.web.bean.NoteDecreti;
import it.tecnet.crs.mod.service.ModellazioneService;
import it.tecnet.crs.mod.web.bean.Area;
import it.tecnet.crs.mod.web.bean.ProcessoTablePaginator;
import it.tecnet.crs.mod.web.bean.SottoProcesso;
import it.tecnet.crs.mod.web.dto.ProcessoDto;
import it.tecnet.crs.web.action.BaseAction;
import it.tecnet.crs.web.beans.AppUser;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ModelDriven;

public class ProcessoTableAction  extends BaseAction implements ModelDriven<ProcessoTablePaginator> {

	private static final long serialVersionUID = -6032365920336093297L;
	
	private ProcessoTablePaginator processoTp = new ProcessoTablePaginator();
	private ModellazioneService modellazioneService;
	
	
	public ProcessoTableAction(ModellazioneService modellazioneService){
		super();
		this.setModellazioneService(modellazioneService);
	}
	
	
	
	 public String getProcessiTable(){
		 
		 HttpServletRequest request = ServletActionContext.getRequest();
			AppUser user= (AppUser)request.getSession().getAttribute("AppUser");
			if (user != null) {
				//TODO aggiungere la lista delle sessioni/accessi per auditors loggato
				
				long idUtente=user.getIdUtente();
				
				List<ProcessoDto> processiList = modellazioneService.getListaProcessi(getModel(),idUtente);
				
				int total = modellazioneService.countAllProcessi(getModel(),idUtente);
				
				getModel().setiTotalDisplayRecords(total);
				getModel().setiTotalRecords(total);
				
				
				for(Object processo : processiList){
					getModel().getAaData().add(processo);
				}
				
			}

			return SUCCESS;


	 }
	 
	 
	 /**
	  * metodo che popola la aside dell'associazione processo/circolari inps
	  * (Tab Normativa in modifica ).
	  * @return
	  */
	 public String getAssociabiliCircolariInpsTable(){
			
		List<CircolariInps> returnList = modellazioneService.getAssociabiliCircolariInpsTable(getModel());
		
		int total = modellazioneService.countAllAssociabiliCircolariInps(getModel());
		
		getModel().setiTotalDisplayRecords(total);
		getModel().setiTotalRecords(total);
		
		for(Object normativa : returnList){	
			getModel().getAaData().add(normativa);
		}
		
		return SUCCESS;
	}
	 
	 /**
	  * metodo che popola la aside dell'associazione processo/note decreti
	  * (Tab Normativa in modifica ).
	  * @return
	  */
	 public String getAssociabiliNoteDecretiTable(){
			
		List<NoteDecreti> returnList = modellazioneService.getAssociabiliNoteDecreti(getModel());
		
		int total = modellazioneService.countAllAssociabiliNoteDecreti(getModel());
		
		getModel().setiTotalDisplayRecords(total);
		getModel().setiTotalRecords(total);
		
		for(Object normativa : returnList){	
			getModel().getAaData().add(normativa);
		}
		
		return SUCCESS;
	}
	 
	 /**
	  * metodo che popola la aside dell'associazione processo/messaggi inps
	  * (Tab Normativa in modifica processo).
	  * @return
	  */
	 public String getAssociabiliMessaggiInpsTable(){
			
		List<MessaggiInps> returnList = modellazioneService.getAssociabiliMessaggiInps(getModel());
		
		int total = modellazioneService.countAllAssociabiliMessaggiInps(getModel());
		
		getModel().setiTotalDisplayRecords(total);
		getModel().setiTotalRecords(total);
		
		for(Object normativa : returnList){	
			getModel().getAaData().add(normativa);
		}
		
		return SUCCESS;
	}
	 
	 /**
	  * metodo che popola la aside dell'associazione processo/note decreti
	  * (Tab Normativa in modifica processo).
	  * @return
	  */
	 public String getAssociabiliLeggiDecretiTable(){
			
		List<LeggiDecreti> returnList = modellazioneService.getAssociabiliLeggiDecreti(getModel());
		
		int total = modellazioneService.countAllAssociabiliLeggiDecreti(getModel());
		
		getModel().setiTotalDisplayRecords(total);
		getModel().setiTotalRecords(total);
		
		for(Object normativa : returnList){	
			getModel().getAaData().add(normativa);
		}
		
		return SUCCESS;
	}
	 
	 
	 
	 public String getDocumentiMediaProcesso(){
		 
		 return SUCCESS;
	 }
	 
	 public String getDominiProcesso(){
		 
		 return SUCCESS;
	 }
	 
	 public String getDominioProcesso(){
		 
		 long idDominioProcesso= getModel().getIdDominioProcesso();
		 try{
			 CrsDomini d= modellazioneService.cerca(CrsDomini.class, idDominioProcesso);
			 getModel().setDominio(d);
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		
		 
		 
		 
		 return SUCCESS;
	 }
	 
	 
	 public String getCompTecniciProcesso(){
		 
		 return SUCCESS;
	 }
	 
	 
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return super.execute();
	}

	
	
	@Override
	public ProcessoTablePaginator getModel() {
		return processoTp;
	}

	public void setModellazioneService(ModellazioneService modellazioneService) {
		this.modellazioneService = modellazioneService;
	}

	public ModellazioneService getModellazioneService() {
		return modellazioneService;
	}

}
