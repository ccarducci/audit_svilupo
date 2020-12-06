package it.tecnet.crs.audit.web.action.tassonomie;

import java.util.List;

import it.tecnet.crs.audit.service.CrsTassonomiaService;
import it.tecnet.crs.componenti.web.bean.GlossarioModel;
import it.tecnet.crs.jpa.model.CrsAmbito;
import it.tecnet.crs.jpa.model.CrsEntita;
import it.tecnet.crs.web.action.BaseAction;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ModelDriven;

public class EntitaCrudAction extends BaseAction implements
		ModelDriven<GlossarioModel> {

	protected static Logger log = Logger.getLogger(EntitaCrudAction.class);

	private static final long serialVersionUID = 1L;
	private GlossarioModel glossarioModel = new GlossarioModel();
	private CrsTassonomiaService crsTassonomiaService;

	public EntitaCrudAction(CrsTassonomiaService crsTassonomiaService) {
		super();
		this.crsTassonomiaService = crsTassonomiaService;
	}

	@Override
	public GlossarioModel getModel() {
		return glossarioModel;
	}

	public String InserisciEntitaTassonomie() {
		try {
			CrsEntita entita = new CrsEntita();
			entita.setEntita(glossarioModel.getEntita());
			entita.setTipo(glossarioModel.getEntitaTipo());
			entita.setDescrEntita(glossarioModel.getEntitaDescrizione());
			if (glossarioModel.getEntitaDataInizio() != null)
				entita.setDataInizio(glossarioModel.getEntitaDataInizio());
			if (glossarioModel.getEntitaDataFine() != null)
				entita.setDataFine(glossarioModel.getEntitaDataFine());
			crsTassonomiaService.salva(entita);
			getModel().setStatus(0);
			log.debug("InserisciEntitaTassonomie OK");
		} catch (Exception ex) {
			log.error(ex.getMessage());
			getModel().setStatus(1);
			getModel().setError(1);
			getModel().setErrorDescription(ex.getMessage());
		}
		return SUCCESS;
	}

	public String EliminaEntitaTassonomie() {
		try {			
			// RICERCA AMBITO BY ENTITA
			List<CrsAmbito> ambiti = crsTassonomiaService.getAmbitoFromIdEntita(getModel()
					.getEntitaID());
			
			if (ambiti.size() == 0){
				crsTassonomiaService.deleteById(CrsEntita.class, getModel()
						.getEntitaID());
				getModel().setStatus(0);
				log.debug("EliminaEntitaTassonomie OK");
			}else{
				log.error("Non è possibile eliminare l'entità perchè associata ad un ambito.");
				getModel().setStatus(1);
				getModel().setError(2);
				getModel().setErrorDescription("Non è possibile eliminare l'entità perchè associata ad un ambito.");
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
			getModel().setStatus(1);
			getModel().setError(1);
			getModel().setErrorDescription(ex.getMessage());
		}
		return SUCCESS;
	}

	public String ModificaEntitaTassonomie() {
		CrsEntita entita = crsTassonomiaService.findById(CrsEntita.class,
				getModel().getEntitaID());
		entita.setEntita(glossarioModel.getEntita());
		entita.setTipo(glossarioModel.getEntitaTipo());
		entita.setDescrEntita(glossarioModel.getEntitaDescrizione());
		entita.setDataInizio(glossarioModel.getEntitaDataInizio());
		entita.setDataFine(glossarioModel.getEntitaDataFine());
		crsTassonomiaService.salva(entita);
		getModel().setStatus(0);
		return SUCCESS;
	}

}
