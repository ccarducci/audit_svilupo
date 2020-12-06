package it.tecnet.crs.audit.web.action.tassonomie;

import it.tecnet.crs.audit.service.CrsTassonomiaService;
import it.tecnet.crs.componenti.web.bean.GlossarioModel;
import it.tecnet.crs.jpa.model.CrsAmbito;
import it.tecnet.crs.jpa.model.CrsGlossario;
import it.tecnet.crs.jpa.model.CrsSinonimo;
import it.tecnet.crs.web.action.BaseAction;

import java.util.List;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ModelDriven;

public class GlossarioCrudAction extends BaseAction implements
		ModelDriven<GlossarioModel> {

	protected static Logger log = Logger.getLogger(GlossarioCrudAction.class);

	private static final long serialVersionUID = 1L;
	private GlossarioModel glossarioModel = new GlossarioModel();
	private CrsTassonomiaService crsTassonomiaService;

	public GlossarioCrudAction(CrsTassonomiaService crsTassonomiaService) {
		super();
		this.crsTassonomiaService = crsTassonomiaService;
	}

	@Override
	public GlossarioModel getModel() {
		return glossarioModel;
	}

	public String InserisciParolaTassonomieGlossario() {
		try {
			CrsGlossario paroleNew = new CrsGlossario();
			paroleNew.setParola(glossarioModel.getGlossarioParola());
			paroleNew.setDescrParola(glossarioModel
					.getGlossarioDescrizioneParola());
			if (glossarioModel.getGlossarioDataInizio() != null)
				paroleNew
						.setDataInizio(glossarioModel.getGlossarioDataInizio());
			if (glossarioModel.getGlossarioDataFine() != null)
				paroleNew.setDataFine(glossarioModel.getGlossarioDataFine());
			crsTassonomiaService.persist(paroleNew);
			getModel().setStatus(0);
			log.debug("InserisciParolaTassonomieGlossario OK");
		} catch (Exception ex) {
			log.error(ex.getMessage());
			getModel().setStatus(1);
			getModel().setError(1);
			getModel().setErrorDescription(ex.getMessage());
		}
		return SUCCESS;
	}

	public String EliminaParolaTassonomieGlossario() {
		try {
			List<CrsAmbito> ambiti = crsTassonomiaService.getAmbitoFromIdGlossario(getModel()
					.getGlossarioID());
			
			
			List<CrsSinonimo> sinonimi = crsTassonomiaService.getSinonimoFromIdGlossario(getModel()
					.getGlossarioID());
			
			if (ambiti.size() == 0  && sinonimi.size() == 0){
				crsTassonomiaService.deleteById(CrsGlossario.class, getModel()
						.getGlossarioID());
				getModel().setStatus(0);
				log.debug("EliminaParolaTassonomieGlossario OK");
			}else if (ambiti.size() > 0  && sinonimi.size() > 0){
				log.error("Non è possibile eliminare il glossario perchè associato ad un sinonimo e ad un ambito.");
				getModel().setStatus(1);
				getModel().setError(2);
				getModel().setErrorDescription("Non è possibile eliminare il glossario perchè associato ad un sinonimo e ad un ambito.");
				
			}else if (ambiti.size() > 0  && sinonimi.size() == 0){
				log.error("Non è possibile eliminare il glossario perchè associato ad un ambito.");
				getModel().setStatus(1);
				getModel().setError(2);
				getModel().setErrorDescription("Non è possibile eliminare il glossario perchè associato ad un ambito.");
			}else if (ambiti.size() == 0  && sinonimi.size() > 0){
				log.error("Non è possibile eliminare il glossario perchè associato ad un sinonimo.");
				getModel().setStatus(1);
				getModel().setError(2);
				getModel().setErrorDescription("Non è possibile eliminare il glossario perchè associato ad un sinonimo.");
			}
			
		} catch (Exception ex) {
			log.error(ex.getMessage());
			getModel().setStatus(1);
			getModel().setError(1);
			getModel().setErrorDescription(ex.getMessage());
		}
		return SUCCESS;
	}

	public String ModificaParolaTassonomieGlossario() {
		try {
			CrsGlossario parola = crsTassonomiaService.findById(
					CrsGlossario.class, getModel().getGlossarioID());
			parola.setParola(glossarioModel.getGlossarioParola());
			parola.setDescrParola(glossarioModel
					.getGlossarioDescrizioneParola());
			parola.setDataInizio(glossarioModel.getGlossarioDataInizio());
			parola.setDataFine(glossarioModel.getGlossarioDataFine());
			crsTassonomiaService.salva(parola);
			getModel().setStatus(0);
			log.debug("ModificaParolaTassonomieGlossario OK");
		} catch (Exception ex) {
			log.error(ex.getMessage());
			getModel().setStatus(1);
			getModel().setError(1);
			getModel().setErrorDescription(ex.getMessage());
		}
		return SUCCESS;
	}
}
