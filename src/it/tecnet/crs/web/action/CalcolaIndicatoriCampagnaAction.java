package it.tecnet.crs.web.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import it.tecnet.crs.indicatori.campagna.CalcoloIndicatoriCampagnaService;


import com.opensymphony.xwork2.ModelDriven;

public class CalcolaIndicatoriCampagnaAction extends BaseAction implements
		ModelDriven<String> {

	private static final long serialVersionUID = 1L;
	private String model = new String();

	private CalcoloIndicatoriCampagnaService calcoloIndicatoriCampagnaService;


	public CalcolaIndicatoriCampagnaAction(
			CalcoloIndicatoriCampagnaService calcoloIndicatoriCampagnaService) {
		super();
		this.calcoloIndicatoriCampagnaService = calcoloIndicatoriCampagnaService;
	}

	@Override
	public String getModel() {
		return model;
	}

	public String calcolaIndicatoriCampagna() {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		String idCampagnaS = (String)request.getParameter("idCampangna");
		long idCampagna = Long.parseLong(idCampagnaS);
		//idCampagna = 2;
		calcoloIndicatoriCampagnaService.calcolaIndicatoriCampagna(idCampagna);
		model = "{status:'OK',description:''}";
		return SUCCESS;
	}

	public String calcolaIndicatoriApriCampagna() {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		String idCampagnaS = (String)request.getParameter("idCampangna");
		
		long idCampagna = Long.parseLong(idCampagnaS);
		//idCampagna = 2;
		
		calcoloIndicatoriCampagnaService.aggiornaStatoCampagna(idCampagna, "A");
		
		model = "{status:'OK',description:''}";
		return SUCCESS;
	}
}
