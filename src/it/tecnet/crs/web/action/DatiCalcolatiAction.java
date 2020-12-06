package it.tecnet.crs.web.action;

import it.tecnet.crs.jpa.model.AuSPratica;
import it.tecnet.crs.service.AuditService;
import it.tecnet.crs.session.DatiUtente;
import it.tecnet.crs.web.beans.DatiCalcolati;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ModelDriven;

public class DatiCalcolatiAction extends BaseAction implements
		ModelDriven<DatiCalcolati> {

	private static final long serialVersionUID = 1L;
	private AuditService auditService;
	private DatiCalcolati model = new DatiCalcolati();

	public DatiCalcolatiAction(AuditService auditService) {
		super();
		this.auditService = auditService;
	}

	public String getTabDatiCalcolatiPratica() {
		HttpServletRequest request = ServletActionContext.getRequest();

		DatiUtente user = (DatiUtente) request.getSession().getAttribute(
				"DatiUtente");
		if (user != null) {
			long idVerbale = user.getIdVerbale();
			AuSPratica pratica = auditService.getSPraticaByIDVerbale(idVerbale);
			getModel().setPratica(pratica);
		}
		return SUCCESS;
	}

	@Override
	public DatiCalcolati getModel() {
		return model;
	}

}
