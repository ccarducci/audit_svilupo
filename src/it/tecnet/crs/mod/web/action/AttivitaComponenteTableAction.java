package it.tecnet.crs.mod.web.action;

import it.tecnet.crs.componenti.web.bean.DatiTipoNormativa;
import it.tecnet.crs.mod.service.ModellazioneService;
import it.tecnet.crs.mod.web.bean.Area;
import it.tecnet.crs.mod.web.bean.AttivitaComponente;
import it.tecnet.crs.mod.web.bean.AttivitaComponenteTablePaginator;
import it.tecnet.crs.mod.web.bean.ProcessoTablePaginator;
import it.tecnet.crs.mod.web.bean.SottoProcesso;
import it.tecnet.crs.mod.web.bean.SottoProcessoTablePaginator;
import it.tecnet.crs.mod.web.dto.ProcessoDto;
import it.tecnet.crs.web.action.BaseAction;
import it.tecnet.crs.web.beans.AppUser;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ModelDriven;

public class AttivitaComponenteTableAction  extends BaseAction implements ModelDriven<AttivitaComponenteTablePaginator> {

	private static final long serialVersionUID = 1L;
	
	private AttivitaComponenteTablePaginator attivitaComponenteTp = new AttivitaComponenteTablePaginator();
	
	private ModellazioneService modellazioneService;
	
	
	public AttivitaComponenteTableAction(ModellazioneService modellazioneService){
		super();
		this.setModellazioneService(modellazioneService);
	}
	

	public String getAttivitaComponenteTable(){
		 
		 HttpServletRequest request = ServletActionContext.getRequest();
			AppUser user= (AppUser)request.getSession().getAttribute("AppUser");
			if (user != null) {
				//TODO aggiungere la lista delle sessioni/accessi per auditors loggato
				
				long idUtente=user.getIdUtente();
				
				List<AttivitaComponente> attivitaList = modellazioneService.getListaAttivitaComponente(getModel(),idUtente);
													
				int total = modellazioneService.countAllAttivitaComponente(getModel(),idUtente);
				
				getModel().setiTotalDisplayRecords(total);
				getModel().setiTotalRecords(total);
				
				
				for(Object attivita : attivitaList){	
					getModel().getAaData().add(attivita);
				}
				
			}

			return SUCCESS;


	 }
	
	/**
	  * metodo che popola la aside dell'associazione attivita componente/normativa
	  * (Tab Normativa in modifica attivita componente).
	  * @return
	  */
	 public String getAttivitaComponenteNormativaTable(){
			
			List<DatiTipoNormativa> returnList = modellazioneService.getListaAttivitaComponenteNormative(getModel());
			
			int total = modellazioneService.countAllAttivitaComponenteNormative(getModel());
			
			getModel().setiTotalDisplayRecords(total);
			getModel().setiTotalRecords(total);
			
			for(Object normativa : returnList){	
				getModel().getAaData().add(normativa);
			}
			
			
			return SUCCESS;
		}
	 

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return super.execute();
	}

	
	
	@Override
	public AttivitaComponenteTablePaginator getModel() {
		return attivitaComponenteTp;
	}

	public void setModellazioneService(ModellazioneService modellazioneService) {
		this.modellazioneService = modellazioneService;
	}

	public ModellazioneService getModellazioneService() {
		return modellazioneService;
	}

}
