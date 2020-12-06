package it.tecnet.crs.util.datatables;

import it.tecnet.crs.web.action.BaseAction;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

public abstract class AbstractDataTablesAction extends BaseAction implements ModelDriven<DataTablesModel>{

	private static final long serialVersionUID = 1L;
	private DataTablesModel model = new DataTablesModel();
	
	@Override
	public DataTablesModel getModel() {
		return model;
	}

	public String execute() {
		AbstractDataTablesParamUtility dataTablesParamUtility = 
			new AbstractDataTablesParamUtility(model,ActionContext.getContext().getParameters());
		dataTablesParamUtility.loadParameters();
		getData(model);
		return SUCCESS;
	}
	
	public abstract void getData(DataTablesModel model);
}
