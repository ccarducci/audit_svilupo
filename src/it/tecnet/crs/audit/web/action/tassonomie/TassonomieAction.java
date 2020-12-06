package it.tecnet.crs.audit.web.action.tassonomie;

import it.tecnet.crs.web.action.BaseAction;

import com.opensymphony.xwork2.ModelDriven;

public class TassonomieAction extends BaseAction implements ModelDriven<String>{

	private static final long serialVersionUID = 1L;
	private String model;
	
	@Override
	public String getModel() {
		return model;
	}

	public String getTassonomie(){
		return SUCCESS;
	}
	
	public String getTassonomieSinonimo(){
		return SUCCESS;
	}
	
	public String getTassonomieGlossario(){
		return SUCCESS;
	}
	
	public String getTassonomieAmbito(){
		return SUCCESS;
	}
	
	public String getTassonomieEntita(){
		return SUCCESS;
	}

	public String getTassonomieSinonimoInserisci(){
		return SUCCESS;
	}
	
	public String getTassonomieSinonimoModifica(){
		return SUCCESS;
	}

	public String getTassonomieAmbitoInserisci(){
		return SUCCESS;
	}
	
	public String getTassonomieAmbitoModifica(){
		return SUCCESS;
	}
}
