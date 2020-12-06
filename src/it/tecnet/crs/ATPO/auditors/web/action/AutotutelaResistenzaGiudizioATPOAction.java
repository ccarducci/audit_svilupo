package it.tecnet.crs.ATPO.auditors.web.action;

import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoFaseAutotutelaResistenzaGiudizio;
import it.tecnet.crs.ATPO.auditors.service.AtpoFasiService;
import it.tecnet.crs.ATPO.auditors.web.beans.ModelAuditorsAutotutelaResGiudizioATPO;
import it.tecnet.crs.ATPO.auditors.web.dto.AuTplTipologicheAtpoDto;
import it.tecnet.crs.jpa.model.AuSPratica;
import it.tecnet.crs.session.DatiUtente;
import it.tecnet.crs.web.action.BaseAction;
import it.tecnet.crs.web.beans.AppUser;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ModelDriven;

public class AutotutelaResistenzaGiudizioATPOAction extends BaseAction
		implements ModelDriven<ModelAuditorsAutotutelaResGiudizioATPO> {

	private static final long serialVersionUID = 1L;
	private ModelAuditorsAutotutelaResGiudizioATPO model = new ModelAuditorsAutotutelaResGiudizioATPO();
	private AtpoFasiService service;

	public AutotutelaResistenzaGiudizioATPOAction(AtpoFasiService service) {
		super();
		this.service = service;
	}

	public String getAutotutelaResInGiudizio() {

		HttpServletRequest request = ServletActionContext.getRequest();
		DatiUtente user = (DatiUtente) request.getSession().getAttribute(
				"DatiUtente");
		if (user != null) {
			
			// INIZIO - VERIFICA PRATICA MODIFICABILE
			AuSPratica auSPratica = service.cerca(AuSPratica.class,
					user.getIdSPratica());
			AppUser appUser = (AppUser) request.getSession()
					.getAttribute("AppUser");
			if (auSPratica.getUserOwner() == null)
				getModel().setPraticaModificable("V");
			else if (auSPratica.getUserOwner().equals(
					appUser.getUsername()))
				getModel().setPraticaModificable("V");
			else
				getModel().setPraticaModificable("F");
			// FINE - VERIFICA PRATICA MODIFICABILE	
			
			AtpoFaseAutotutelaResistenzaGiudizio autotutelaResGiu = null;
			try {
				// query per prendermi acquisizione istanza
				autotutelaResGiu = service
						.getFaseAutotutelaResistenzaGiudizio(user
								.getIdFaseDati());

				if (autotutelaResGiu != null) {
					getModel().setAutotuResGiudizio(autotutelaResGiu);
					// metto in sessione l id autotutelaResGiudizio
					user.setIdAutotutelaResGiudizio(autotutelaResGiu
							.getIdAutotutela());

					getModel()
							.setStatoEsamePratica(user.getStatoEsamePratica());
					// converto i campi tipologici da codifica a descrizione
					autotutelaResGiu = convertCodToDescr(autotutelaResGiu);

				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			// una query cper il dropdown parere
			try {

				List<AuTplTipologicheAtpoDto> options = service
						.getTipologicaAtpo("V022");
				if (!options.isEmpty()) {

					for (AuTplTipologicheAtpoDto s : options) {
						getModel().getOptionsParere().add(s);
					}

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			// una query per il dropdown udienza autotutle
			try {

				List<AuTplTipologicheAtpoDto> optionsUdienzaAutotutela = service
						.getTipologicaAtpo("V023");
				if (!optionsUdienzaAutotutela.isEmpty()) {

					for (AuTplTipologicheAtpoDto a : optionsUdienzaAutotutela) {
						getModel().getOptionsTerminiUdienza().add(a);
					}

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return SUCCESS;
	}

	private AtpoFaseAutotutelaResistenzaGiudizio convertCodToDescr(
			AtpoFaseAutotutelaResistenzaGiudizio autotutelaResGiu) {
		try {
			// eccezioni non rilevabili
			if (autotutelaResGiu.getParere() != null
					&& !autotutelaResGiu.getParere().trim().equals("")) {
				String codificaParere = autotutelaResGiu.getParere().trim();
				getModel().setCodifParere(codificaParere);
				AuTplTipologicheAtpoDto t = service
						.getDescrTipologicaByCodifica("V022", codificaParere
								.trim());
				autotutelaResGiu.setParere(t.getDescrizione());
			}
			// Listispedenza
			if (autotutelaResGiu.getTerminiPrimaUdienza() != null
					&& !autotutelaResGiu.getTerminiPrimaUdienza().trim()
							.equals("")) {
				String codifterminiPrimaudienza = autotutelaResGiu
						.getTerminiPrimaUdienza().trim();
				getModel().setDefTerminiEntroPudienza(codifterminiPrimaudienza);
				AuTplTipologicheAtpoDto t = service
						.getDescrTipologicaByCodifica("V023",
								codifterminiPrimaudienza);
				autotutelaResGiu.setTerminiPrimaUdienza(t.getDescrizione());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return autotutelaResGiu;
	}

	public String salvaAutotutelaResistenzaGiudizio() {
		HttpServletRequest request = ServletActionContext.getRequest();
		DatiUtente user = (DatiUtente) request.getSession().getAttribute(
				"DatiUtente");
		if (user != null) {
			long idAutotutela = user.getIdAutotutelaResGiudizio();
			long idFaseDati = user.getIdFaseDati();
			AtpoFaseAutotutelaResistenzaGiudizio a = getModel()
					.getAutotuResGiudizio();
			if (idAutotutela != 0) {
				a.setIdAutotutela(idAutotutela);
				a.setFasePronta("S");
			}

			a.setIdFaseDati(idFaseDati);
			service.salva(a);
		}

		return SUCCESS;

	}

	public ModelAuditorsAutotutelaResGiudizioATPO getModel() {

		return model;
	}

}
