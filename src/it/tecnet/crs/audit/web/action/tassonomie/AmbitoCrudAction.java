package it.tecnet.crs.audit.web.action.tassonomie;

import org.apache.log4j.Logger;

import it.tecnet.crs.audit.service.CrsTassonomiaService;
import it.tecnet.crs.componenti.web.bean.GlossarioModel;
import it.tecnet.crs.jpa.model.CrsAmbito;
import it.tecnet.crs.jpa.model.CrsEntita;
import it.tecnet.crs.jpa.model.CrsGlossario;
import it.tecnet.crs.web.action.BaseAction;

import com.opensymphony.xwork2.ModelDriven;

public class AmbitoCrudAction extends BaseAction implements
		ModelDriven<GlossarioModel> {

	protected static Logger log = Logger.getLogger(AmbitoCrudAction.class);

	private static final long serialVersionUID = 1L;
	private GlossarioModel glossarioModel = new GlossarioModel();
	private CrsTassonomiaService crsTassonomiaService;

	public AmbitoCrudAction(CrsTassonomiaService crsTassonomiaService) {
		super();
		this.crsTassonomiaService = crsTassonomiaService;
	}

	@Override
	public GlossarioModel getModel() {
		return glossarioModel;
	}

	public String InserisciAmbitoTassonomie() {
		try {
			CrsAmbito ambito = new CrsAmbito();
			ambito.setIdEntita(glossarioModel.getEntitaID());
			ambito.setIdGlossario(glossarioModel.getGlossarioID());
			ambito.setDescrAmbito(glossarioModel.getAmbitoDescrizione());
			ambito.setTipoAmbito(glossarioModel.getAmbitoTipo());
			ambito.setDataInizio(glossarioModel.getAmbitoDataFine());
			ambito.setDataFine(glossarioModel.getAmbitoDataFine());
			ambito.setAmbito(glossarioModel.getAmbitoDescrizione());
			crsTassonomiaService.persist(ambito);
			getModel().setStatus(0);
			log.debug("InserisciAmbitoTassonomie OK");
		} catch (Exception ex) {
			log.error(ex.getMessage());
			getModel().setStatus(1);
			getModel().setError(1);
			getModel().setErrorDescription(ex.getMessage());
		}
		return SUCCESS;
	}

	public String EliminaAmbitoTassonomie() {
		try {
			crsTassonomiaService.deleteById(CrsAmbito.class, getModel()
					.getAmbitoID());
			getModel().setStatus(0);
			log.debug("EliminaAmbitoTassonomie OK");
		} catch (Exception ex) {
			log.error(ex.getMessage());
			getModel().setStatus(1);
			getModel().setError(1);
			getModel().setErrorDescription(ex.getMessage());
		}
		return SUCCESS;
	}

	public String ModificaAmbitoTassonomie() {
		try {
			CrsAmbito ambito = crsTassonomiaService.findById(CrsAmbito.class,
					glossarioModel.getAmbitoID());
			ambito.setIdEntita(glossarioModel.getEntitaID());
			ambito.setIdGlossario(glossarioModel.getGlossarioID());
			ambito.setDescrAmbito(glossarioModel.getAmbitoDescrizione());
			ambito.setTipoAmbito(glossarioModel.getAmbitoTipo());
			ambito.setDataInizio(glossarioModel.getAmbitoDataInizio());
			ambito.setDataFine(glossarioModel.getAmbitoDataFine());
			ambito.setAmbito(glossarioModel.getAmbitoDescrizione());
			crsTassonomiaService.salva(ambito);
			getModel().setStatus(0);
			log.debug("ModificaAmbitoTassonomie OK");
		} catch (Exception ex) {
			log.error(ex.getMessage());
			getModel().setStatus(1);
			getModel().setError(1);
			getModel().setErrorDescription(ex.getMessage());
		}
		return SUCCESS;
	}

	public String getAmbito() {
		CrsAmbito ambito = crsTassonomiaService.findById(CrsAmbito.class,
				glossarioModel.getAmbitoID());
		CrsGlossario glossario = crsTassonomiaService.findById(
				CrsGlossario.class, ambito.getIdGlossario());
		glossarioModel.setGlossarioParola(glossario.getParola());
		CrsEntita entita = crsTassonomiaService.findById(CrsEntita.class,
				ambito.getIdEntita());
		glossarioModel.setEntita(entita.getEntita());
		glossarioModel.setAmbito(ambito.getAmbito());
		glossarioModel.setAmbitoDescrizione(ambito.getDescrAmbito());
		glossarioModel.setAmbitoTipo(ambito.getTipoAmbito());
		glossarioModel.setAmbitoDataInizio(ambito.getDataInizio());
		glossarioModel.setAmbitoDataFine(ambito.getDataFine());
		glossarioModel.setGlossarioID(glossario.getIdGlossario());
		glossarioModel.setEntitaID(entita.getIdEntita());
		getModel().setStatus(0);
		return SUCCESS;
	}

}
