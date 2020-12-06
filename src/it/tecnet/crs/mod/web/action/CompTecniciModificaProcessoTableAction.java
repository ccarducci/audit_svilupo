package it.tecnet.crs.mod.web.action;

import it.tecnet.crs.componenti.jpa.model.CrsComponenteTecnico;
import it.tecnet.crs.componenti.jpa.model.CrsDomini;
import it.tecnet.crs.componenti.web.dto.CrsCompTecnicoDto;
import it.tecnet.crs.mod.jpa.model.CrsAssProcessoCompTec;
import it.tecnet.crs.mod.jpa.model.CrsAssProcessoDomini;
import it.tecnet.crs.mod.service.ModellazioneService;
import it.tecnet.crs.mod.web.bean.CompTecniciProcessoPaginator;
import it.tecnet.crs.mod.web.bean.DominiProcessoPaginator;
import it.tecnet.crs.mod.web.dto.CompTecAttCompDto;
import it.tecnet.crs.mod.web.dto.DomAttCompDto;
import it.tecnet.crs.util.datatables.DataTablesParamUtility;
import it.tecnet.crs.web.action.BaseAction;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

public class CompTecniciModificaProcessoTableAction extends BaseAction implements ModelDriven<CompTecniciProcessoPaginator> {
	
	
	private static final long serialVersionUID = 1L;
	private ModellazioneService modellazioneService;
	private CompTecniciProcessoPaginator model= new CompTecniciProcessoPaginator();
	
	public CompTecniciModificaProcessoTableAction(ModellazioneService modellazioneService){
		super();
		this.setModellazioneService(modellazioneService);
	}
	
	public String getCompTecniciProcesso(){
		
		return SUCCESS;
	}

	@Override
	public CompTecniciProcessoPaginator getModel() {
		// TODO Auto-generated method stub
		return model;
	}
	
	public String searchCompTecniciProcesso(){
		
		DataTablesParamUtility.loadParameters(getModel(),ActionContext.getContext().getParameters());
		
		List<CompTecAttCompDto> ctList = modellazioneService.getCompTecProcesso(getModel());

		try{
			int total = modellazioneService.countCompTecProcesso(getModel());

			getModel().setiTotalDisplayRecords(total);
			getModel().setiTotalRecords(total);

			if(ctList==null){
				return SUCCESS;
			}

			for(Object o : ctList){	
				getModel().getaaData().add(o);

			}
		}catch(Exception e){
			e.printStackTrace();
			log.error(e);
		}

		return SUCCESS;
	}
	
	public String searchCompTecniciProcessoDaAssociare(){
		
		DataTablesParamUtility.loadParameters(getModel(),ActionContext.getContext().getParameters());

		List<CrsCompTecnicoDto> ctList = modellazioneService.getCompTecProcessoDaAssociare(getModel());

		try{
			int total = modellazioneService.countCompTecProcessoDaAssociare(getModel());

			getModel().setiTotalDisplayRecords(total);
			getModel().setiTotalRecords(total);

			if(ctList==null){
				return SUCCESS;
			}

			for(Object o : ctList){	
				getModel().getaaData().add(o);

			}
		}catch(Exception e){
			e.printStackTrace();
			log.error(e);
		}

		return SUCCESS;
		
	}
	
	public String saveCompTecniciProcesso(){
		
		CrsAssProcessoCompTec ct = new CrsAssProcessoCompTec();
		ct.setIdProcesso(getModel().getIdProcesso());
		ct.setIdComponenteTec(getModel().getIdCompTec());
		try{
			modellazioneService.salva(ct);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return SUCCESS;
	}
	
	public String eliminaAssCompTecniciProcesso(){
		try{
			String listaId=getModel().getListId();
			String[] idDaCancellare=listaId.split(",");
			for(String i : idDaCancellare){
				CrsAssProcessoCompTec ctM = modellazioneService.cerca(CrsAssProcessoCompTec.class, Long.valueOf(i) );
				modellazioneService.remove(ctM);

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
