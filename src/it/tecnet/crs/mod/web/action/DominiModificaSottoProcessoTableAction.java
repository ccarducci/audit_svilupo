package it.tecnet.crs.mod.web.action;

import it.tecnet.crs.componenti.jpa.model.CrsDomini;
import it.tecnet.crs.mod.jpa.model.CrsAssProcessoDomini;
import it.tecnet.crs.mod.jpa.model.CrsAssSottoProcessoDomini;
import it.tecnet.crs.mod.service.ModellazioneService;
import it.tecnet.crs.mod.web.bean.DominiProcessoPaginator;
import it.tecnet.crs.mod.web.bean.DominiSottoProcessoPaginator;
import it.tecnet.crs.mod.web.dto.DomAttCompDto;
import it.tecnet.crs.util.datatables.DataTablesParamUtility;
import it.tecnet.crs.web.action.BaseAction;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

public class DominiModificaSottoProcessoTableAction extends BaseAction implements ModelDriven<DominiSottoProcessoPaginator> {
	
	
	private static final long serialVersionUID = 1L;
	private ModellazioneService modellazioneService;
	private DominiSottoProcessoPaginator model= new DominiSottoProcessoPaginator();
	
	public DominiModificaSottoProcessoTableAction(ModellazioneService modellazioneService){
		super();
		this.setModellazioneService(modellazioneService);
	}
	
	public String getDominiSottoProcesso(){
		
		return SUCCESS;
	}

	@Override
	public DominiSottoProcessoPaginator getModel() {
		// TODO Auto-generated method stub
		return model;
	}
	
	public String searchDominiSottoProcesso(){
		
		DataTablesParamUtility.loadParameters(getModel(),ActionContext.getContext().getParameters());
		
		List<DomAttCompDto> dominiList = modellazioneService.getDominiSottoProcesso(getModel());

		try{
			int total = modellazioneService.countDominiSottoProcesso(getModel());

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
	
	public String searchDominiSottoProcessoDaAssociare(){
		
		DataTablesParamUtility.loadParameters(getModel(),ActionContext.getContext().getParameters());

		List<CrsDomini> dominiList = modellazioneService.getDominiSottoProcessoDaAssociare(getModel());

		try{
			int total = modellazioneService.countDominiSottoProcessoDaAssociare(getModel());

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
	
	public String saveDominiSottoProcesso(){
		
		CrsAssSottoProcessoDomini dc = new CrsAssSottoProcessoDomini();
		dc.setIdSottoProcesso(getModel().getIdSottoProcesso());
		dc.setIdDomini(getModel().getIdDomini());
		try{
			modellazioneService.salva(dc);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	public String eliminaAssDominiSottoProcesso(){
		
		try{
			String listaId=getModel().getListId();
			String[] idDaCancellare=listaId.split(",");
			for(String i : idDaCancellare){
				CrsAssSottoProcessoDomini docM = modellazioneService.cerca(CrsAssSottoProcessoDomini.class, Long.valueOf(i) );
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
