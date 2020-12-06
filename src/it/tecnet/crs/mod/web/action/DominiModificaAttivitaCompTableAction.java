package it.tecnet.crs.mod.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import it.tecnet.crs.componenti.jpa.model.CrsDomini;
import it.tecnet.crs.mod.jpa.model.CrsAssAttivitaComponenteDomini;
import it.tecnet.crs.mod.service.ModellazioneService;
import it.tecnet.crs.mod.web.bean.DominiAttCompPaginator;
import it.tecnet.crs.mod.web.dto.DomAttCompDto;
import it.tecnet.crs.session.DatiUtente;
import it.tecnet.crs.web.action.BaseAction;
import it.tecnet.crs.web.beans.AppUser;

import com.opensymphony.xwork2.ModelDriven;

public class DominiModificaAttivitaCompTableAction extends BaseAction implements ModelDriven<DominiAttCompPaginator> {

	
	private static final long serialVersionUID = 1L;
	private ModellazioneService modellazioneService;
	private DominiAttCompPaginator model= new DominiAttCompPaginator();
	
	public DominiModificaAttivitaCompTableAction(ModellazioneService modellazioneService){
		super();
		this.setModellazioneService(modellazioneService);
	}
	
	public String getDominiAttivitaComponente(){
		return SUCCESS;
	}
	
	public String searchDominiAttComp(){
		HttpServletRequest request = ServletActionContext.getRequest();
		DatiUtente user= (DatiUtente)request.getSession().getAttribute("DatiUtente");
		long idAttivitaComponente= user.getIdAttivitaComponente();
		List<DomAttCompDto> dominiList = modellazioneService.getListDominiAttComp(idAttivitaComponente, getModel().getiDisplayStart(),getModel().getiDisplayLength(),
				getModel().getiSortCol_0(),getModel().getsSortDir_0(),getModel().getsSearch());


		try{
			int total = modellazioneService.countListDominiAttComp(idAttivitaComponente, getModel().getsSearch()).intValue();

			getModel().setiTotalDisplayRecords(total);
			getModel().setiTotalRecords(total);

			if(dominiList==null){
				return SUCCESS;
			}

			for(Object o : dominiList){	
				getModel().getAaData().add(o);

			}
		}catch(Exception e){
			e.printStackTrace();
			log.error(e);
		}

		return SUCCESS;
		
	}
	
	public String searchDominiAttCompDaAssociare(){
		HttpServletRequest request = ServletActionContext.getRequest();
		DatiUtente user= (DatiUtente)request.getSession().getAttribute("DatiUtente");
		long idAttivitaComponente= user.getIdAttivitaComponente();
		List<CrsDomini> dominiList = modellazioneService.getListDominiAttCompDaAssociare(idAttivitaComponente, getModel().getiDisplayStart(),getModel().getiDisplayLength(),
				getModel().getiSortCol_0(),getModel().getsSortDir_0(),getModel().getsSearch());


		try{
			int total = modellazioneService.countListDominiAttCompDaAssociare(idAttivitaComponente, getModel().getsSearch()).intValue();

			getModel().setiTotalDisplayRecords(total);
			getModel().setiTotalRecords(total);

			if(dominiList==null){
				return SUCCESS;
			}

			for(Object o : dominiList){	
				getModel().getAaData().add(o);

			}
		}catch(Exception e){
			e.printStackTrace();
			log.error(e);
		}

		return SUCCESS;
	}
	
	public String saveDomAttComp(){
		
		
		HttpServletRequest request = ServletActionContext.getRequest();
		DatiUtente user= (DatiUtente)request.getSession().getAttribute("DatiUtente");
		CrsAssAttivitaComponenteDomini dc= new CrsAssAttivitaComponenteDomini();
		dc.setIdAttivitaComponente(user.getIdAttivitaComponente());
		dc.setIdDominio(getModel().getIdDominio());
		try{
			modellazioneService.salva(dc);
		}catch(Exception e){
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	
	public String eliminaAssDomAttComp(){
		try{
			String listaId=getModel().getListId();
			String[] idDaCancellare=listaId.split(",");
			for(String i : idDaCancellare){
				CrsAssAttivitaComponenteDomini dom= modellazioneService.cerca(CrsAssAttivitaComponenteDomini.class, Long.valueOf(i) );
				modellazioneService.remove(dom);

			}
		}catch(Exception e){
			e.printStackTrace();
		}
		

		return SUCCESS;
	}
	
	@Override
	public DominiAttCompPaginator getModel() {
	
		return model;
	}
	
	
	public void setModellazioneService(ModellazioneService modellazioneService) {
		this.modellazioneService = modellazioneService;
	}
	public ModellazioneService getModellazioneService() {
		return modellazioneService;
	}

}
