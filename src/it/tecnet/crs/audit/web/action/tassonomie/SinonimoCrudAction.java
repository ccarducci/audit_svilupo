package it.tecnet.crs.audit.web.action.tassonomie;

import it.tecnet.crs.audit.service.CrsTassonomiaService;
import it.tecnet.crs.componenti.web.bean.GlossarioModel;
import it.tecnet.crs.jpa.model.CrsGlossario;
import it.tecnet.crs.jpa.model.CrsSinonimo;
import it.tecnet.crs.web.action.BaseAction;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ModelDriven;

public class SinonimoCrudAction extends BaseAction implements
		ModelDriven<GlossarioModel> {

	protected static Logger log = Logger.getLogger(SinonimoCrudAction.class);

	private static final long serialVersionUID = 1L;
	private GlossarioModel glossarioModel = new GlossarioModel();
	private CrsTassonomiaService crsTassonomiaService;

	public SinonimoCrudAction(CrsTassonomiaService crsTassonomiaService) {
		super();
		this.crsTassonomiaService = crsTassonomiaService;
	}

	@Override
	public GlossarioModel getModel() {
		return glossarioModel;
	}

	public String InserisciSinonimoTassonomie() {
		try {
			CrsSinonimo sinonimo = new CrsSinonimo();
			sinonimo.setIdGlossario(glossarioModel.getGlossarioID());
			sinonimo.setSinonimo(glossarioModel.getSinonimo());
			sinonimo.setDescrSinonimo(glossarioModel.getSinonimoDesr());
			if (glossarioModel.getSinonimoDataInizio() != null)
				sinonimo.setDataInizio(glossarioModel.getSinonimoDataInizio());
			if (glossarioModel.getSinonimoDataFine() != null)
				sinonimo.setDataFine(glossarioModel.getSinonimoDataFine());
			crsTassonomiaService.persist(sinonimo);
			getModel().setStatus(0);
			log.debug("InserisciSinonimoTassonomie OK");
		} catch (Exception ex) {
			log.error(ex.getMessage());
			getModel().setStatus(1);
			getModel().setError(1);
			getModel().setErrorDescription(ex.getMessage());
		}
		return SUCCESS;
	}

	public String EliminaSinonimoTassonomie() {
		try {
			crsTassonomiaService.deleteById(CrsSinonimo.class, getModel()
					.getSinonimoID());
			getModel().setStatus(0);
			log.debug("EliminaSinonimoTassonomie OK");
		} catch (Exception ex) {
			log.error(ex.getMessage());
			getModel().setStatus(1);
			getModel().setError(1);
			getModel().setErrorDescription(ex.getMessage());
		}
		return SUCCESS;
	}

	public String ModificaSinonimoTassonomie() {
		try {
			CrsSinonimo sinonimo = crsTassonomiaService.findById(
					CrsSinonimo.class, glossarioModel.getSinonimoID());
			sinonimo.setIdGlossario(glossarioModel.getGlossarioID());
			sinonimo.setSinonimo(glossarioModel.getSinonimo());
			sinonimo.setDescrSinonimo(glossarioModel.getSinonimoDesr());
			sinonimo.setDataInizio(glossarioModel.getSinonimoDataInizio());
			sinonimo.setDataFine(glossarioModel.getSinonimoDataFine());
			crsTassonomiaService.salva(sinonimo);
			getModel().setStatus(0);
			log.debug("ModificaSinonimoTassonomie OK");
		} catch (Exception ex) {
			log.error(ex.getMessage());
			getModel().setStatus(1);
			getModel().setError(1);
			getModel().setErrorDescription(ex.getMessage());
		}
		return SUCCESS;
	}

	public String getSinonimo() {
		CrsSinonimo sinonimo = crsTassonomiaService.findById(CrsSinonimo.class,
				glossarioModel.getSinonimoID());
		glossarioModel.setGlossarioID(sinonimo.getIdGlossario());
		glossarioModel.setSinonimo(sinonimo.getSinonimo());
		glossarioModel.setSinonimoDesr(sinonimo.getDescrSinonimo());
		glossarioModel.setSinonimoDataInizio(sinonimo.getDataInizio());
		glossarioModel.setSinonimoDataFine(sinonimo.getDataFine());
		CrsGlossario glossario = crsTassonomiaService.findById(
				CrsGlossario.class, sinonimo.getIdGlossario());
		glossarioModel.setGlossarioParola(glossario.getParola());
		log.debug("getSinonimo OK");
		return SUCCESS;
	}
}
