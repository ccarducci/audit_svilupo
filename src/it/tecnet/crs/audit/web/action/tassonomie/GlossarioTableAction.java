package it.tecnet.crs.audit.web.action.tassonomie;

import it.tecnet.crs.audit.service.CrsTassonomiaService;
import it.tecnet.crs.util.datatables.AbstractDataTablesAction;
import it.tecnet.crs.util.datatables.DataTablesModel;

public class GlossarioTableAction  extends AbstractDataTablesAction 
 /*extends BaseAction implements ModelDriven<DataTablesModel> */{

	private static final long serialVersionUID = 1L;
	
	// service
	private CrsTassonomiaService crsTassonomiaService;
	
	public GlossarioTableAction(CrsTassonomiaService crsTassonomiaService) {
		super();
		this.crsTassonomiaService = crsTassonomiaService;
	}

	@Override
	public void getData(DataTablesModel model) {
		crsTassonomiaService.getGlossarioDataTable(model);
	}
	
	/*
	private DataTablesModel model = new DataTablesModel();
	
	@Override
	public DataTablesModel getModel() {
		return model;
	}
	
	public String getTassonomiaGlossarioTable(){
		DataTablesParamUtility.loadParameters(model,ActionContext.getContext().getParameters());
		crsTassonomiaService.getGlossarioDataTable(model);
		return SUCCESS;
	}
	*/
}
