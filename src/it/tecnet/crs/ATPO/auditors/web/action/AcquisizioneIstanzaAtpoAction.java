package it.tecnet.crs.ATPO.auditors.web.action;

import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoFaseAcquisizioneIstanza;
import it.tecnet.crs.ATPO.auditors.service.AtpoFasiService;
import it.tecnet.crs.ATPO.auditors.web.beans.ModelAuditorsAcquisizioneIstanzaATPO;
import it.tecnet.crs.ATPO.auditors.web.dto.AuTplTipologicheAtpoDto;
import it.tecnet.crs.jpa.model.AuSPratica;
import it.tecnet.crs.session.DatiUtente;
import it.tecnet.crs.web.action.BaseAction;
import it.tecnet.crs.web.beans.AppUser;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ModelDriven;

public class AcquisizioneIstanzaAtpoAction extends BaseAction implements
		ModelDriven<ModelAuditorsAcquisizioneIstanzaATPO> {

	private static final long serialVersionUID = 1L;
	private ModelAuditorsAcquisizioneIstanzaATPO model = new ModelAuditorsAcquisizioneIstanzaATPO();
	private AtpoFasiService service;

	public AcquisizioneIstanzaAtpoAction(AtpoFasiService service) {
		super();
		this.service = service;
	}

	public String getAcquisizioneIstanza() {
		HttpServletRequest request = ServletActionContext.getRequest();
		DatiUtente user = (DatiUtente) request.getSession().getAttribute(
				"DatiUtente");

		// INIZIO - VERIFICA PRATICA MODIFICABILE
		AuSPratica auSPratica = service.cerca(AuSPratica.class, user
				.getIdSPratica());
		AppUser appUser = (AppUser) request.getSession()
				.getAttribute("AppUser");
		if (auSPratica.getUserOwner() == null)
			getModel().setPraticaModificable("V");
		else if (auSPratica.getUserOwner().equals(appUser.getUsername()))
			getModel().setPraticaModificable("V");
		else
			getModel().setPraticaModificable("F");
		// FINE - VERIFICA PRATICA MODIFICABILE

		AtpoFaseAcquisizioneIstanza acquisizioneIstanza = null;
		if (user != null) {
			try {
				// query per prendermi acquisizione istanza
				acquisizioneIstanza = service.getFaseAcquisizioneIstanza(user
						.getIdFaseDati());

				if (acquisizioneIstanza != null) {
					if(acquisizioneIstanza.getDataProtocollo()== null){
						acquisizioneIstanza.setProtocolloConImg("");
					}
					getModel().setAcquisizioneIstanza(acquisizioneIstanza);
					// metto in sessione l id acquisizioneistanza
					user.setIdAcquisizioneIstanza(acquisizioneIstanza
							.getIdFaseAcquisizioneIstanza());

					// se la pratica è chiusa il pulsante salva è disabilitato
					getModel()
							.setStatoEsamePratica(user.getStatoEsamePratica());

					// converto i campi tipologici da codifica a descrizione
					acquisizioneIstanza = convertCodToDescr(acquisizioneIstanza);

				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			// una query cper il dropdown protocollo con immagine
			try {

				List<AuTplTipologicheAtpoDto> siNo = service
						.getTipologicaAtpo("V040");
				if (!siNo.isEmpty()) {

					for (AuTplTipologicheAtpoDto s : siNo) {
						getModel().getSiNo().add(s);
					}

				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			// una query per il dropdown vocetitolario
			try {

				List<AuTplTipologicheAtpoDto> listTopologiche = service
						.getTipologicaAtpo("V024");
				if (!listTopologiche.isEmpty()) {

					for (AuTplTipologicheAtpoDto s : listTopologiche) {
						getModel().getVocetitolario().add(s);
					}

				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		return SUCCESS;
	}

	private AtpoFaseAcquisizioneIstanza convertCodToDescr(
			AtpoFaseAcquisizioneIstanza acquisizioneIstanza) {

		// voce titolario
		if (acquisizioneIstanza.getVoceTitolarioErrata() != null
				&& !acquisizioneIstanza.getVoceTitolarioErrata().trim().equals(
						"")) {
			String codificaVoceTitolario = acquisizioneIstanza
					.getVoceTitolarioErrata().trim();
			getModel().setCodificaVoceTitolario(codificaVoceTitolario);
			AuTplTipologicheAtpoDto t = service.getDescrTipologicaByCodifica(
					"V024", codificaVoceTitolario.trim());
			acquisizioneIstanza.setVoceTitolarioErrata(t.getDescrizione());
		}
		// img
		if (acquisizioneIstanza.getProtocolloConImg() != null
				&& !acquisizioneIstanza.getProtocolloConImg().trim().equals("")) {
			String codificaProtocolloConImg = acquisizioneIstanza
					.getProtocolloConImg().trim();
			getModel().setCodificaProtImg(codificaProtocolloConImg);
			AuTplTipologicheAtpoDto t = service.getDescrTipologicaByCodifica(
					"V040", codificaProtocolloConImg);
			acquisizioneIstanza.setProtocolloConImg(t.getDescrizione());
		}

		return acquisizioneIstanza;
	}

	public String salvaAcquisizioneIstanza() {
		HttpServletRequest request = ServletActionContext.getRequest();
		DatiUtente user = (DatiUtente) request.getSession().getAttribute(
				"DatiUtente");
		if (user != null) {
			long idAcquisizioneIstanza = user.getIdAcquisizioneIstanza();
			long idFaseDati = user.getIdFaseDati();
			AtpoFaseAcquisizioneIstanza a = getModel().getAcquisizioneIstanza();
			if (user.getIdAcquisizioneIstanza() != 0) {
				a.setIdFaseAcquisizioneIstanza(idAcquisizioneIstanza);
				a.setFasePronta("S");
			}

			a.setIdFaseDati(idFaseDati);
			service.salva(a);
		}

		return SUCCESS;
	}

	@Override
	public ModelAuditorsAcquisizioneIstanzaATPO getModel() {
		// TODO Auto-generated method stub
		return model;
	}

}
