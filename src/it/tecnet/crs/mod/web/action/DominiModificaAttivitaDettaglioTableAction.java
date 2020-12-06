package it.tecnet.crs.mod.web.action;

import it.tecnet.crs.componenti.jpa.model.CrsDomini;
import it.tecnet.crs.mod.jpa.model.CrsAssAttivitaDettaglioDomini;
import it.tecnet.crs.mod.jpa.model.CrsAssProcessoDomini;
import it.tecnet.crs.mod.jpa.model.CrsAssSottoProcessoDomini;
import it.tecnet.crs.mod.service.ModellazioneService;
import it.tecnet.crs.mod.web.bean.DominiAttivitaDettaglioPaginator;
import it.tecnet.crs.mod.web.bean.DominiProcessoPaginator;
import it.tecnet.crs.mod.web.bean.DominiSottoProcessoPaginator;
import it.tecnet.crs.mod.web.dto.DomAttCompDto;
import it.tecnet.crs.util.datatables.DataTablesParamUtility;
import it.tecnet.crs.web.action.BaseAction;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

public class DominiModificaAttivitaDettaglioTableAction extends BaseAction implements ModelDriven<DominiAttivitaDettaglioPaginator> {
	
	
	private static final long serialVersionUID = 1L;
	private ModellazioneService modellazioneService;
	private DominiAttivitaDettaglioPaginator model= new DominiAttivitaDettaglioPaginator();
	
	public DominiModificaAttivitaDettaglioTableAction(ModellazioneService modellazioneService){
		super();
		this.setModellazioneService(modellazioneService);
	}
	
	public String getDominiAttivitaDettaglio(){
		
		return SUCCESS;
	}

	@Override
	public DominiAttivitaDettaglioPaginator getModel() {
		// TODO Auto-generated method stub
		return model;
	}
	
	public String searchDominiAttivitaDettaglio(){
		
		DataTablesParamUtility.loadParameters(getModel(),ActionContext.getContext().getParameters());
		
		List<DomAttCompDto> dominiList = modellazioneService.getDominiAttivitaDettaglio(getModel());

		try{
			int total = modellazioneService.countDominiAttivitaDettaglio(getModel());

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
	
	public String searchDominiAttivitaDettaglioDaAssociare(){
		
		DataTablesParamUtility.loadParameters(getModel(),ActionContext.getContext().getParameters());

		List<CrsDomini> dominiList = modellazioneService.getDominiAttivitaDettaglioDaAssociare(getModel());

		try{
			int total = modellazioneService.countDominiAttivitaDettaglioDaAssociare(getModel());

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
	
	public String saveDominiAttivitaDettaglio(){
		
		CrsAssAttivitaDettaglioDomini dc = new CrsAssAttivitaDettaglioDomini();
		dc.setIdAttivitaDettaglio(getModel().getIdAttivitaDettaglio());
		dc.setIdDomini(getModel().getIdDomini());
		try{
			modellazioneService.salva(dc);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	public String eliminaAssDominiAttivitaDettaglio(){
		
		try{
			String listaId=getModel().getListId();
			String[] idDaCancellare=listaId.split(",");
			for(String i : idDaCancellare){
				CrsAssAttivitaDettaglioDomini docM = modellazioneService.cerca(CrsAssAttivitaDettaglioDomini.class, Long.valueOf(i) );
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
