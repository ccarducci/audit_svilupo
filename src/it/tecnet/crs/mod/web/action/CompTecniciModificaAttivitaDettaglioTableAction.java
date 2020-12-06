package it.tecnet.crs.mod.web.action;

import it.tecnet.crs.componenti.jpa.model.CrsComponenteTecnico;
import it.tecnet.crs.componenti.jpa.model.CrsDomini;
import it.tecnet.crs.componenti.web.dto.CrsCompTecnicoDto;
import it.tecnet.crs.mod.jpa.model.CrsAssAttivitaDettaglioCompTec;
import it.tecnet.crs.mod.jpa.model.CrsAssProcessoCompTec;
import it.tecnet.crs.mod.jpa.model.CrsAssProcessoDomini;
import it.tecnet.crs.mod.jpa.model.CrsAssSottoProcessoCompTec;
import it.tecnet.crs.mod.service.ModellazioneService;
import it.tecnet.crs.mod.web.bean.CompTecniciAttivitaDettaglioPaginator;
import it.tecnet.crs.mod.web.bean.CompTecniciProcessoPaginator;
import it.tecnet.crs.mod.web.bean.CompTecniciSottoProcessoPaginator;
import it.tecnet.crs.mod.web.bean.DominiProcessoPaginator;
import it.tecnet.crs.mod.web.dto.CompTecAttCompDto;
import it.tecnet.crs.mod.web.dto.DomAttCompDto;
import it.tecnet.crs.util.datatables.DataTablesParamUtility;
import it.tecnet.crs.web.action.BaseAction;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

public class CompTecniciModificaAttivitaDettaglioTableAction extends BaseAction implements ModelDriven<CompTecniciAttivitaDettaglioPaginator> {
	
	
	private static final long serialVersionUID = 1L;
	private ModellazioneService modellazioneService;
	private CompTecniciAttivitaDettaglioPaginator model= new CompTecniciAttivitaDettaglioPaginator();
	
	public CompTecniciModificaAttivitaDettaglioTableAction(ModellazioneService modellazioneService){
		super();
		this.setModellazioneService(modellazioneService);
	}
	
	public String getCompTecniciAttivitaDettaglio(){
		
		return SUCCESS;
	}

	@Override
	public CompTecniciAttivitaDettaglioPaginator getModel() {
		// TODO Auto-generated method stub
		return model;
	}
	
	public String searchCompTecniciAttivitaDettaglio(){
		
		DataTablesParamUtility.loadParameters(getModel(),ActionContext.getContext().getParameters());
		
		List<CompTecAttCompDto> ctList = modellazioneService.getCompTecAttivitaDettaglio(getModel());

		try{
			int total = modellazioneService.countCompTecAttivitaDettaglio(getModel());

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
	
	public String searchCompTecniciAttivitaDettaglioDaAssociare(){
		
		DataTablesParamUtility.loadParameters(getModel(),ActionContext.getContext().getParameters());

		List<CrsCompTecnicoDto> ctList = modellazioneService.getCompTecAttivitaDettaglioDaAssociare(getModel());

		try{
			int total = modellazioneService.countCompTecAttivitaDettaglioDaAssociare(getModel());

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
	
	public String saveCompTecniciAttivitaDettaglio(){
		
		CrsAssAttivitaDettaglioCompTec ct = new CrsAssAttivitaDettaglioCompTec();
		ct.setIdAttivitaDettaglio(getModel().getIdAttivitaDettaglio());
		ct.setIdComponenteTec(getModel().getIdCompTec());
		try{
			modellazioneService.salva(ct);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return SUCCESS;
	}
	
	public String eliminaAssCompTecniciAttivitaDettaglio(){
		try{
			String listaId=getModel().getListId();
			String[] idDaCancellare=listaId.split(",");
			for(String i : idDaCancellare){
				CrsAssAttivitaDettaglioCompTec ctM = modellazioneService.cerca(CrsAssAttivitaDettaglioCompTec.class, Long.valueOf(i) );
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
