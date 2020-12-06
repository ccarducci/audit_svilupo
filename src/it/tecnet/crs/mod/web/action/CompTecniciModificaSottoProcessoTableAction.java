package it.tecnet.crs.mod.web.action;

import it.tecnet.crs.componenti.jpa.model.CrsComponenteTecnico;
import it.tecnet.crs.componenti.jpa.model.CrsDomini;
import it.tecnet.crs.componenti.web.dto.CrsCompTecnicoDto;
import it.tecnet.crs.mod.jpa.model.CrsAssProcessoCompTec;
import it.tecnet.crs.mod.jpa.model.CrsAssProcessoDomini;
import it.tecnet.crs.mod.jpa.model.CrsAssSottoProcessoCompTec;
import it.tecnet.crs.mod.service.ModellazioneService;
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

public class CompTecniciModificaSottoProcessoTableAction extends BaseAction implements ModelDriven<CompTecniciSottoProcessoPaginator> {
	
	
	private static final long serialVersionUID = 1L;
	private ModellazioneService modellazioneService;
	private CompTecniciSottoProcessoPaginator model= new CompTecniciSottoProcessoPaginator();
	
	public CompTecniciModificaSottoProcessoTableAction(ModellazioneService modellazioneService){
		super();
		this.setModellazioneService(modellazioneService);
	}
	
	public String getCompTecniciSottoProcesso(){
		
		return SUCCESS;
	}

	@Override
	public CompTecniciSottoProcessoPaginator getModel() {
		// TODO Auto-generated method stub
		return model;
	}
	
	public String searchCompTecniciSottoProcesso(){
		
		DataTablesParamUtility.loadParameters(getModel(),ActionContext.getContext().getParameters());
		
		List<CompTecAttCompDto> ctList = modellazioneService.getCompTecSottoProcesso(getModel());

		try{
			int total = modellazioneService.countCompTecSottoProcesso(getModel());

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
	
	public String searchCompTecniciSottoProcessoDaAssociare(){
		
		DataTablesParamUtility.loadParameters(getModel(),ActionContext.getContext().getParameters());

		List<CrsCompTecnicoDto> ctList = modellazioneService.getCompTecSottoProcessoDaAssociare(getModel());

		try{
			int total = modellazioneService.countCompTecSottoProcessoDaAssociare(getModel());

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
	
	public String saveCompTecniciSottoProcesso(){
		
		CrsAssSottoProcessoCompTec ct = new CrsAssSottoProcessoCompTec();
		ct.setIdSottoProcesso(getModel().getIdSottoProcesso());
		ct.setIdComponenteTec(getModel().getIdCompTec());
		try{
			modellazioneService.salva(ct);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return SUCCESS;
	}
	
	public String eliminaAssCompTecniciSottoProcesso(){
		try{
			String listaId=getModel().getListId();
			String[] idDaCancellare=listaId.split(",");
			for(String i : idDaCancellare){
				CrsAssSottoProcessoCompTec ctM = modellazioneService.cerca(CrsAssSottoProcessoCompTec.class, Long.valueOf(i) );
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
