package it.tecnet.crs.mod.web.action;

import it.tecnet.crs.componenti.web.bean.DatiTipoNormativa;
import it.tecnet.crs.mod.service.ModellazioneService;
import it.tecnet.crs.mod.web.bean.Area;
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

public class SottoProcessoTableAction  extends BaseAction implements ModelDriven<SottoProcessoTablePaginator> {

	private static final long serialVersionUID = 1L;
	
	private SottoProcessoTablePaginator sottoProcessoTp = new SottoProcessoTablePaginator();
	
	private ModellazioneService modellazioneService;
	
	
	public SottoProcessoTableAction(ModellazioneService modellazioneService){
		super();
		this.setModellazioneService(modellazioneService);
	}
	

	public String getSottoProcessiTable(){
		 
		 HttpServletRequest request = ServletActionContext.getRequest();
			AppUser user= (AppUser)request.getSession().getAttribute("AppUser");
			if (user != null) {
				//TODO aggiungere la lista delle sessioni/accessi per auditors loggato
				
				long idUtente=user.getIdUtente();
				
				List<SottoProcesso> sottoProcessiList = modellazioneService.getListaSottoProcessi(getModel(),idUtente);
													
				int total = modellazioneService.countAllSottoProcessi(getModel(),idUtente);
				
				getModel().setiTotalDisplayRecords(total);
				getModel().setiTotalRecords(total);
				
				
				for(Object sottoProcesso : sottoProcessiList){	
					getModel().getAaData().add(sottoProcesso);
				}
				
			}

			return SUCCESS;


	 }
	
	
	/**
	  * metodo che popola la aside dell'associazione sottoprocesso/normativa
	  * (Tab Normativa in modifica fase).
	  * @return
	  */
	 public String getSottoProcessoNormativaTable(){
			
			List<DatiTipoNormativa> returnList = modellazioneService.getListaSottoProcessoNormative(getModel());
			
			int total = modellazioneService.countAllSottoProcessoNormative(getModel());
			
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
	public SottoProcessoTablePaginator getModel() {
		return sottoProcessoTp;
	}

	public void setModellazioneService(ModellazioneService modellazioneService) {
		this.modellazioneService = modellazioneService;
	}

	public ModellazioneService getModellazioneService() {
		return modellazioneService;
	}

}
