package it.tecnet.crs.audit.web.action.tassonomie;

import it.tecnet.crs.audit.service.CrsTassonomiaService;
import it.tecnet.crs.util.datatables.AbstractDataTablesAction;
import it.tecnet.crs.util.datatables.DataTablesModel;

public class AmbitoTableAction  extends AbstractDataTablesAction
/*extends BaseAction implements ModelDriven<DataTablesModel> */{

	private static final long serialVersionUID = 1L;
	
	// service
	private CrsTassonomiaService crsTassonomiaService;
	
	public AmbitoTableAction(CrsTassonomiaService crsTassonomiaService) {
		super();
		this.crsTassonomiaService = crsTassonomiaService;
	}

	@Override
	public void getData(DataTablesModel model) {
		crsTassonomiaService.getAmbitoDataTable(model);
	}

	/*
	private DataTablesModel model = new DataTablesModel(); 

	@Override
	public DataTablesModel getModel() {
		return model;
	}
	
	public String getTassonomiaAmbitoTable(){
		DataTablesParamUtility.loadParameters(model,ActionContext.getContext().getParameters());
		crsTassonomiaService.getAmbitoDataTable(model);
		return SUCCESS;
	}
	*/
}
