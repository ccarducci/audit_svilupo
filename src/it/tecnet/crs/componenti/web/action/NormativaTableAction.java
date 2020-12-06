package it.tecnet.crs.componenti.web.action;

import it.tecnet.crs.componenti.jpa.model.CrsAssCircolariInpsDocumenti;
import it.tecnet.crs.componenti.jpa.model.CrsAssDominiDocumenti;
import it.tecnet.crs.componenti.jpa.model.CrsAssLeggiDecretiDocumenti;
import it.tecnet.crs.componenti.jpa.model.CrsAssMessaggiInpsDocumenti;
import it.tecnet.crs.componenti.jpa.model.CrsAssNoteDecretiDocumenti;
import it.tecnet.crs.componenti.jpa.model.CrsCircolariInps;
import it.tecnet.crs.componenti.jpa.model.CrsDocumentiMedia;
import it.tecnet.crs.componenti.service.GestioneComponentiService;
import it.tecnet.crs.componenti.web.bean.CircolariInps;
import it.tecnet.crs.componenti.web.bean.LeggiDecreti;
import it.tecnet.crs.componenti.web.bean.MessaggiInps;
import it.tecnet.crs.componenti.web.bean.NormativaTablePaginator;
import it.tecnet.crs.componenti.web.bean.NoteDecreti;
import it.tecnet.crs.componenti.web.dto.CrsDocumentiAllegatiDto;
import it.tecnet.crs.session.DatiUtente;
import it.tecnet.crs.util.TipoNormativaEnum;
import it.tecnet.crs.util.datatables.DataTablesParamUtility;
import it.tecnet.crs.web.action.BaseAction;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

public class NormativaTableAction  extends BaseAction implements ModelDriven<NormativaTablePaginator> {

	private static final long serialVersionUID = 1L;
	
	
	private NormativaTablePaginator normativaTp = new NormativaTablePaginator();
	private GestioneComponentiService gestioneComponentiService;
	
	
	public NormativaTableAction(GestioneComponentiService gestioneComponentiService){
		super();
		this.setGestioneComponentiService(gestioneComponentiService);
	}
	
	
	 
	 public String getCircolariInpsTable(){
		 
		DataTablesParamUtility.loadParameters(getModel(),ActionContext.getContext().getParameters());
		 
		List<CircolariInps> returnList = gestioneComponentiService.getCircolariInpsTable(getModel());
											
		int total = gestioneComponentiService.countAllCircolariInpsTable(getModel());
		
		getModel().setiTotalDisplayRecords(total);
		getModel().setiTotalRecords(total);
		
		for(Object normativa : returnList){	
			getModel().getaaData().add(normativa);
		}
			

		return SUCCESS;

	}
	 
	 
	 public String getNoteDecretiTable(){
		 
		DataTablesParamUtility.loadParameters(getModel(),ActionContext.getContext().getParameters());
		 
		List<NoteDecreti> returnList = gestioneComponentiService.getNoteDecretiTable(getModel());
									
		int total = gestioneComponentiService.countAllNoteDecretiTable(getModel());
		
		getModel().setiTotalDisplayRecords(total);
		getModel().setiTotalRecords(total);
		
		for(Object normativa : returnList){	
			getModel().getaaData().add(normativa);
		}
			

		return SUCCESS;

	}
	 
	 
	 public String getMessaggiInpsTable(){
		 
		DataTablesParamUtility.loadParameters(getModel(),ActionContext.getContext().getParameters());
		 
		List<MessaggiInps> returnList = gestioneComponentiService.getMessaggiInpsTable(getModel());
											
		int total = gestioneComponentiService.countAllMessaggiInpsTable(getModel());
		
		getModel().setiTotalDisplayRecords(total);
		getModel().setiTotalRecords(total);
		
		for(Object normativa : returnList){	
			getModel().getaaData().add(normativa);
		}
			

		return SUCCESS;

	}
	 
	 public String getLeggiDecretiTable(){
		
		DataTablesParamUtility.loadParameters(getModel(),ActionContext.getContext().getParameters());
		 
		List<LeggiDecreti> returnList = gestioneComponentiService.getLeggiDecretiTable(getModel());
											
		int total = gestioneComponentiService.countAllLeggiDecretiTable(getModel());
		
		getModel().setiTotalDisplayRecords(total);
		getModel().setiTotalRecords(total);
		
		for(Object normativa : returnList){	
			getModel().getaaData().add(normativa);
		}
			

		return SUCCESS;

	}
	 
	 
	 public String getAllegatiCircolariInps(){
		 
		DataTablesParamUtility.loadParameters(getModel(),ActionContext.getContext().getParameters());
		 
		List<CrsDocumentiAllegatiDto> returnList = gestioneComponentiService.getAllegatiCircolariInpsTable(getModel());
		if(returnList.isEmpty()){
			//prova a settare l'id della circolare
			 HttpServletRequest request = ServletActionContext.getRequest();
			 DatiUtente user= (DatiUtente)request.getSession().getAttribute("DatiUtente");
			 long idNormativa= user.getIdNormativa();
			 getModel().setIdCircolariInps(idNormativa);
			 returnList = gestioneComponentiService.getAllegatiCircolariInpsTable(getModel());
		}
		int total = gestioneComponentiService.countAllAllegatiCircolariInpsTable(getModel());
		
		getModel().setiTotalDisplayRecords(total);
		getModel().setiTotalRecords(total);
		
		for(Object allegati : returnList){	
			getModel().getaaData().add(allegati);
		}
			

		return SUCCESS;

	}
	 
	 public String getAllegatiNoteDecreti(){
		 
		DataTablesParamUtility.loadParameters(getModel(),ActionContext.getContext().getParameters());
		 
		List<CrsDocumentiAllegatiDto> returnList = gestioneComponentiService.getAllegatiNoteDecretiTable(getModel());
		if(returnList.isEmpty()){
			//prova a settare l'id della circolare
			 HttpServletRequest request = ServletActionContext.getRequest();
			 DatiUtente user= (DatiUtente)request.getSession().getAttribute("DatiUtente");
			 long idNotaDecreto= user.getIdNoteDecreti();
			 getModel().setIdNoteDecreti(idNotaDecreto);
			 returnList = gestioneComponentiService.getAllegatiNoteDecretiTable(getModel());
		}											
		int total = gestioneComponentiService.countAllAllegatiNoteDecretiTable(getModel());
		
		getModel().setiTotalDisplayRecords(total);
		getModel().setiTotalRecords(total);
		
		for(Object allegati : returnList){	
			getModel().getaaData().add(allegati);
		}
			

		return SUCCESS;

	}
	 
	 public String getAllegatiMessaggiInps(){
		 
		DataTablesParamUtility.loadParameters(getModel(),ActionContext.getContext().getParameters());
		 
		List<CrsDocumentiAllegatiDto> returnList = gestioneComponentiService.getAllegatiMessaggiInpsTable(getModel());
		if(returnList.isEmpty()){
			//prova a settare l'id della circolare
			 HttpServletRequest request = ServletActionContext.getRequest();
			 DatiUtente user= (DatiUtente)request.getSession().getAttribute("DatiUtente");
			 long idMessInps= user.getIdMessaggiNormativa();
			 getModel().setIdMessaggiInps(idMessInps);
			 returnList = gestioneComponentiService.getAllegatiMessaggiInpsTable(getModel());
		}
		int total = gestioneComponentiService.countAllAllegatiMessaggiInpsTable(getModel());
		
		getModel().setiTotalDisplayRecords(total);
		getModel().setiTotalRecords(total);
		
		for(Object allegati : returnList){	
			getModel().getaaData().add(allegati);
		}
			

		return SUCCESS;

	}
	 
	 public String getAllegatiLeggiDecreti(){
		 
		DataTablesParamUtility.loadParameters(getModel(),ActionContext.getContext().getParameters());
		 
		List<CrsDocumentiAllegatiDto> returnList = gestioneComponentiService.getAllegatiLeggiDecretiTable(getModel());
		if(returnList.isEmpty()){
			//prova a settare l'id della circolare
			 HttpServletRequest request = ServletActionContext.getRequest();
			 DatiUtente user= (DatiUtente)request.getSession().getAttribute("DatiUtente");
			 long idLeggDecr= user.getIdLeggDecreti();
			 getModel().setIdLeggiDecreti(idLeggDecr);
			 returnList = gestioneComponentiService.getAllegatiLeggiDecretiTable(getModel());
		}									
		int total = gestioneComponentiService.countAllAllegatiLeggiDecretiTable(getModel());
		
		getModel().setiTotalDisplayRecords(total);
		getModel().setiTotalRecords(total);
		
		for(Object allegati : returnList){	
			getModel().getaaData().add(allegati);
		}
			

		return SUCCESS;

	}
	 
	 
	 public String getDocNoAllegatiCircolariInps(){
		 
		DataTablesParamUtility.loadParameters(getModel(),ActionContext.getContext().getParameters());
		 
		List<CrsDocumentiMedia> returnList =	gestioneComponentiService.getNonAllegatiCircolariInps(getModel());
											
		int total = gestioneComponentiService.countListNonAllegatiCircolariInps(getModel());
		
		getModel().setiTotalDisplayRecords(total);
		getModel().setiTotalRecords(total);
		
		for(Object allegati : returnList){	
			getModel().getaaData().add(allegati);
		}
			

		return SUCCESS;

	}
	 
	 public String getDocNoAllegatiNoteDecreti(){
		 
		DataTablesParamUtility.loadParameters(getModel(),ActionContext.getContext().getParameters());
		 
		List<CrsDocumentiMedia> returnList =	gestioneComponentiService.getNonAllegatiNoteDecreti(getModel());
											
		int total = gestioneComponentiService.countListNonAllegatiNoteDecreti(getModel());
		
		getModel().setiTotalDisplayRecords(total);
		getModel().setiTotalRecords(total);
		
		for(Object allegati : returnList){	
			getModel().getaaData().add(allegati);
		}
			

		return SUCCESS;

	}
	 
	 
	 public String getDocNoAllegatiMessaggiInps(){
		 
		DataTablesParamUtility.loadParameters(getModel(),ActionContext.getContext().getParameters());
		 
		List<CrsDocumentiMedia> returnList =	gestioneComponentiService.getNonAllegatiMessaggiInps(getModel());
											
		int total = gestioneComponentiService.countListNonAllegatiMessaggiInps(getModel());
		
		getModel().setiTotalDisplayRecords(total);
		getModel().setiTotalRecords(total);
		
		for(Object allegati : returnList){	
			getModel().getaaData().add(allegati);
		}
			

		return SUCCESS;

	}
	 
	 
	 public String getDocNoAllegatiLeggiDecreti(){
		 
		DataTablesParamUtility.loadParameters(getModel(),ActionContext.getContext().getParameters());
		 
		List<CrsDocumentiMedia> returnList =	gestioneComponentiService.getNonAllegatiLeggiDecreti(getModel());
											
		int total = gestioneComponentiService.countListNonAllegatiLeggiDecreti(getModel());
		
		getModel().setiTotalDisplayRecords(total);
		getModel().setiTotalRecords(total);
		
		for(Object allegati : returnList){	
			getModel().getaaData().add(allegati);
		}
			

		return SUCCESS;

	}
	 
	 
	 public String addAllegatoNormativa(){
		 
		long idDocumento = getModel().getIdDocumento();
		long idNormativa = getModel().getIdNormativa();

		if(getModel().getIdTipo() == TipoNormativaEnum.CIRCOLARI_INPS.getCodice()){
			CrsAssCircolariInpsDocumenti allegato = new CrsAssCircolariInpsDocumenti();
			allegato.setIdDocumento(idDocumento);
			allegato.setIdCircolariInps(idNormativa);
			
			gestioneComponentiService.salva(allegato);
		}
		if(getModel().getIdTipo() == TipoNormativaEnum.NOTE_DESCRETI.getCodice()){
			CrsAssNoteDecretiDocumenti allegato = new CrsAssNoteDecretiDocumenti();
			allegato.setIdDocumento(idDocumento);
			allegato.setIdNoteDecreti(idNormativa);
			
			gestioneComponentiService.salva(allegato);
		}
		if(getModel().getIdTipo() == TipoNormativaEnum.MESSAGGI_INPS.getCodice()){
			CrsAssMessaggiInpsDocumenti allegato = new CrsAssMessaggiInpsDocumenti();
			allegato.setIdDocumento(idDocumento);
			allegato.setIdMessaggiInps(idNormativa);
			
			gestioneComponentiService.salva(allegato);
		}
		if(getModel().getIdTipo() == TipoNormativaEnum.LEGGI_DECRETI.getCodice()){
			CrsAssLeggiDecretiDocumenti allegato = new CrsAssLeggiDecretiDocumenti();
			allegato.setIdDocumento(idDocumento);
			allegato.setIdLeggiDecreti(idNormativa);
			
			gestioneComponentiService.salva(allegato);
		}
		
		return SUCCESS;
	}
	 
	 
	 public String eliminaAllegatoNormativa(){
			
		String listaId=getModel().getListId();
		String[] idDaCancellare=listaId.split(",");
		for(String i : idDaCancellare){
			
			try{
				
				if(getModel().getIdTipo() == TipoNormativaEnum.CIRCOLARI_INPS.getCodice()){
					
					CrsAssCircolariInpsDocumenti docM= gestioneComponentiService.cerca(CrsAssCircolariInpsDocumenti.class, Long.valueOf(i) );
					gestioneComponentiService.remove(docM);
					
				}
				if(getModel().getIdTipo() == TipoNormativaEnum.NOTE_DESCRETI.getCodice()){
					
					CrsAssNoteDecretiDocumenti docM= gestioneComponentiService.cerca(CrsAssNoteDecretiDocumenti.class, Long.valueOf(i) );
					gestioneComponentiService.remove(docM);
					
				}
				if(getModel().getIdTipo() == TipoNormativaEnum.MESSAGGI_INPS.getCodice()){
					
					CrsAssMessaggiInpsDocumenti docM= gestioneComponentiService.cerca(CrsAssMessaggiInpsDocumenti.class, Long.valueOf(i) );
					gestioneComponentiService.remove(docM);
					
				}
				if(getModel().getIdTipo() == TipoNormativaEnum.LEGGI_DECRETI.getCodice()){
					
					CrsAssLeggiDecretiDocumenti docM= gestioneComponentiService.cerca(CrsAssLeggiDecretiDocumenti.class, Long.valueOf(i) );
					gestioneComponentiService.remove(docM);
					
				}
				
				
			}catch(Exception e){
				e.printStackTrace();
			}

		}

		return SUCCESS;
	}
	 
	 
	 
	 
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return super.execute();
	}

	
	
	@Override
	public NormativaTablePaginator getModel() {
		return normativaTp;
	}

	public void setGestioneComponentiService(GestioneComponentiService gestioneComponentiService) {
		this.gestioneComponentiService = gestioneComponentiService;
	}

	public GestioneComponentiService getGestioneComponentiService() {
		return gestioneComponentiService;
	}

}
