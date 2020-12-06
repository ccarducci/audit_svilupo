package it.tecnet.crs.mod.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import it.tecnet.crs.componenti.jpa.model.CrsComponenteTecnico;
import it.tecnet.crs.componenti.jpa.model.CrsDomini;
import it.tecnet.crs.componenti.web.dto.CrsCompTecnicoDto;
import it.tecnet.crs.mod.jpa.model.CrsAssAttivitaComponenteCompTec;
import it.tecnet.crs.mod.jpa.model.CrsAssAttivitaComponenteDomini;
import it.tecnet.crs.mod.service.ModellazioneService;
import it.tecnet.crs.mod.web.bean.CompTecniciAttCompPaginator;
import it.tecnet.crs.mod.web.bean.DocumentiAttivitaCompPaginator;
import it.tecnet.crs.mod.web.dto.CompTecAttCompDto;
import it.tecnet.crs.mod.web.dto.DomAttCompDto;
import it.tecnet.crs.session.DatiUtente;
import it.tecnet.crs.web.action.BaseAction;

import com.opensymphony.xwork2.ModelDriven;

public class CompTecniciModificaAttivitaCompTableAction extends BaseAction implements ModelDriven<CompTecniciAttCompPaginator> {
	
	
	private static final long serialVersionUID = 1L;
	private ModellazioneService modellazioneService;
	private CompTecniciAttCompPaginator model= new CompTecniciAttCompPaginator();
	
	public CompTecniciModificaAttivitaCompTableAction(ModellazioneService modellazioneService){
		super();
		this.setModellazioneService(modellazioneService);
	}
	
	public String getCompTecniciAttivitaComponente(){
		
		return SUCCESS;
	}
	
	
	public String searchCompTecniciAttComp(){
		//
		
		HttpServletRequest request = ServletActionContext.getRequest();
		DatiUtente user= (DatiUtente)request.getSession().getAttribute("DatiUtente");
		long idAttivitaComponente= user.getIdAttivitaComponente();
		List<CompTecAttCompDto> compTecList = modellazioneService.getListCompTecAttComp(idAttivitaComponente, getModel().getiDisplayStart(),getModel().getiDisplayLength(),
				getModel().getiSortCol_0(),getModel().getsSortDir_0(),getModel().getsSearch());


		try{
			int total = modellazioneService.countListCompTecAttComp(idAttivitaComponente, getModel().getsSearch()).intValue();

			getModel().setiTotalDisplayRecords(total);
			getModel().setiTotalRecords(total);

			if(compTecList==null){
				return SUCCESS;
			}

			for(Object o : compTecList){	
				getModel().getAaData().add(o);

			}
		}catch(Exception e){
			e.printStackTrace();
			log.error(e);
		}

		return SUCCESS;
	}
	
	public String searchCompTecAttCompDaAssociare(){
		HttpServletRequest request = ServletActionContext.getRequest();
		DatiUtente user= (DatiUtente)request.getSession().getAttribute("DatiUtente");
		long idAttivitaComponente= user.getIdAttivitaComponente();
		List<CrsCompTecnicoDto> compTecnicoList = modellazioneService.getListCompTecAttCompDaAssociare(idAttivitaComponente, getModel().getiDisplayStart(),getModel().getiDisplayLength(),
				getModel().getiSortCol_0(),getModel().getsSortDir_0(),getModel().getsSearch());


		try{
			int total = modellazioneService.countListCompTecAttCompDaAssociare(idAttivitaComponente, getModel().getsSearch()).intValue();

			getModel().setiTotalDisplayRecords(total);
			getModel().setiTotalRecords(total);

			if(compTecnicoList==null){
				return SUCCESS;
			}

			for(Object o : compTecnicoList){	
				getModel().getAaData().add(o);

			}
		}catch(Exception e){
			e.printStackTrace();
			log.error(e);
		}

		return SUCCESS;
	}
	
	public String saveCompTecAttComp(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		DatiUtente user= (DatiUtente)request.getSession().getAttribute("DatiUtente");
		CrsAssAttivitaComponenteCompTec ct= new CrsAssAttivitaComponenteCompTec();
		ct.setIdAttivitaComponente(user.getIdAttivitaComponente());
		ct.setIdComponenteTec(getModel().getIdCompTec());
		try{
			modellazioneService.salva(ct);
		}catch(Exception e){
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String eliminaAssCompTecAttComp(){
		try{
			String listaId=getModel().getListId();
			String[] idDaCancellare=listaId.split(",");
			for(String i : idDaCancellare){
				CrsAssAttivitaComponenteCompTec cct= modellazioneService.cerca(CrsAssAttivitaComponenteCompTec.class, Long.valueOf(i) );
				modellazioneService.remove(cct);

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

	@Override
	public CompTecniciAttCompPaginator getModel() {
		// TODO Auto-generated method stub
		return model;
	}
}
