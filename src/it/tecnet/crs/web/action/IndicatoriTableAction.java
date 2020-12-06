package it.tecnet.crs.web.action;

import it.tecnet.crs.service.AuditNonConformitaService;
import it.tecnet.crs.web.beans.IndicatoriTablePaginator;

import com.opensymphony.xwork2.ModelDriven;

public class IndicatoriTableAction extends BaseAction implements
ModelDriven<IndicatoriTablePaginator> {

	/**
	 * Indicatori fase Notifica e Indicoatori fase Definiziojne in modifica-pratiche-accessi.jsp
	 */
	private static final long serialVersionUID = 1L;

	private IndicatoriTablePaginator sessioneTp = new IndicatoriTablePaginator();
	private AuditNonConformitaService auditNonConformitaService;

	public IndicatoriTableAction(AuditNonConformitaService auditNonConformitaService) {
		super();
		this.auditNonConformitaService = auditNonConformitaService;
	}


	


	public AuditNonConformitaService getAuditNonConformitaService() {
		return auditNonConformitaService;
	}

	public void setAuditNonConformitaService(
			AuditNonConformitaService auditNonConformitaService) {
		this.auditNonConformitaService = auditNonConformitaService;
	}

	@Override
	public IndicatoriTablePaginator getModel() {
		// TODO Auto-generated method stub
		return sessioneTp;
	}


}
