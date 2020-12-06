package it.tecnet.crs.mod.web.action;

import it.tecnet.crs.mod.service.ModellazioneService;
import it.tecnet.crs.mod.web.bean.Area;
import it.tecnet.crs.mod.web.bean.AreaTablePaginator;
import it.tecnet.crs.web.action.BaseAction;
import it.tecnet.crs.web.beans.AppUser;
import it.tecnet.crs.web.beans.PraticheTablePaginator;
import it.tecnet.crs.web.dto.VerbaleDto;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ModelDriven;

public class AreaTableAction  extends BaseAction implements ModelDriven<AreaTablePaginator> {


	private static final long serialVersionUID = 1927057433370184274L;

	private AreaTablePaginator areaTp = new AreaTablePaginator();
	private ModellazioneService modellazioneService;
	
	
	public AreaTableAction(ModellazioneService modellazioneService){
		super();
		this.setModellazioneService(modellazioneService);
	}
	
	
	
	 public String getAreaTable(){
		 
		 HttpServletRequest request = ServletActionContext.getRequest();
			AppUser user= (AppUser)request.getSession().getAttribute("AppUser");
			if (user !=null) {
				//TODO aggiungere la lista delle sessioni/accessi per auditors loggato
				
				List<Area> areaList = modellazioneService.getListaAree(getModel());
													
				int total = modellazioneService.countAllAree(getModel());
				
				getModel().setiTotalDisplayRecords(total);
				getModel().setiTotalRecords(total);
				
				
				for(Object area : areaList){	
					getModel().getAaData().add(area);
				}
				
			}

			return SUCCESS;


	 }
	 

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return super.execute();
	}

	
	
	@Override
	public AreaTablePaginator getModel() {
		return areaTp;
	}

	public void setModellazioneService(ModellazioneService modellazioneService) {
		this.modellazioneService = modellazioneService;
	}

	public ModellazioneService getModellazioneService() {
		return modellazioneService;
	}

}
