package it.tecnet.crs.mod.web.action;

import it.tecnet.crs.componenti.jpa.model.CrsDomini;
import it.tecnet.crs.mod.jpa.model.CrsAssProcessoDomini;
import it.tecnet.crs.mod.service.ModellazioneService;
import it.tecnet.crs.mod.web.bean.DominiProcessoPaginator;
import it.tecnet.crs.mod.web.dto.DomAttCompDto;
import it.tecnet.crs.util.datatables.DataTablesParamUtility;
import it.tecnet.crs.web.action.BaseAction;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

public class DominiModificaProcessoTableAction extends BaseAction implements ModelDriven<DominiProcessoPaginator> {
	
	
	private static final long serialVersionUID = 1L;
	private ModellazioneService modellazioneService;
	private DominiProcessoPaginator model= new DominiProcessoPaginator();
	
	public DominiModificaProcessoTableAction(ModellazioneService modellazioneService){
		super();
		this.setModellazioneService(modellazioneService);
	}
	
	public String getDominiProcesso(){
		
		return SUCCESS;
	}

	@Override
	public DominiProcessoPaginator getModel() {
		// TODO Auto-generated method stub
		return model;
	}
	
	public String searchDominiProcesso(){
		
		DataTablesParamUtility.loadParameters(getModel(),ActionContext.getContext().getParameters());
		
		List<DomAttCompDto> dominiList = modellazioneService.getDominiProcesso(getModel());

		try{
			int total = modellazioneService.countDominiProcesso(getModel());

			getModel().setiTotalDisplayRecords(total);
			getModel().setiTotalRecords(total);

			if(dominiList==null){
				return SUCCESS;
			}

			for(Object o : dominiList){	
				getModel().getaaData().add(o);

			}
		}catch(Exception e){
			e.printStackTrace();
			log.error(e);
		}

		return SUCCESS;
	}
	
	public String searchDominiProcessoDaAssociare(){
		
		DataTablesParamUtility.loadParameters(getModel(),ActionContext.getContext().getParameters());

		List<CrsDomini> dominiList = modellazioneService.getDominiProcessoDaAssociare(getModel());


		try{
			int total = modellazioneService.countDominiProcessoDaAssociare(getModel());

			getModel().setiTotalDisplayRecords(total);
			getModel().setiTotalRecords(total);

			if(dominiList==null){
				return SUCCESS;
			}

			for(Object o : dominiList){	
				getModel().getaaData().add(o);

			}
		}catch(Exception e){
			e.printStackTrace();
			log.error(e);
		}

		return SUCCESS;
		
	}
	
	public String saveDominiProcesso(){
		
		CrsAssProcessoDomini dc = new CrsAssProcessoDomini();
		dc.setIdProcesso(getModel().getIdProcesso());
		dc.setIdDomini(getModel().getIdDomini());
		try{
			modellazioneService.salva(dc);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return SUCCESS;
	}
	
	public String eliminaAssDominiProcesso(){
		try{
			String listaId=getModel().getListId();
			String[] idDaCancellare=listaId.split(",");
			for(String i : idDaCancellare){
				CrsAssProcessoDomini docM = modellazioneService.cerca(CrsAssProcessoDomini.class, Long.valueOf(i) );
				modellazioneService.remove(docM);

			}
		}catch(Exception e){
			e.printStackTrace();
		}
		

		return SUCCESS;
	}


	public void setModellazioneService(ModellazioneService modellazioneService) {
		this.modellazioneService = modellazioneService;
	}

	public ModellazioneService getModellazioneService() {
		return modellazioneService;
	}



}
