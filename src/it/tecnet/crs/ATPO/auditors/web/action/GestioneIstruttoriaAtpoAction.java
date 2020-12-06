package it.tecnet.crs.ATPO.auditors.web.action;

import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoFaseGestioneIstruttoria;
import it.tecnet.crs.ATPO.auditors.service.AtpoFasiService;
import it.tecnet.crs.ATPO.auditors.web.beans.ModelAuditorsGestioneIstruttoriaATPO;
import it.tecnet.crs.ATPO.auditors.web.dto.AuTplTipologicheAtpoDto;
import it.tecnet.crs.jpa.model.AuSPratica;
import it.tecnet.crs.session.DatiUtente;
import it.tecnet.crs.web.action.BaseAction;
import it.tecnet.crs.web.beans.AppUser;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ModelDriven;

public class GestioneIstruttoriaAtpoAction extends BaseAction implements
		ModelDriven<ModelAuditorsGestioneIstruttoriaATPO> {

	private static final long serialVersionUID = 1L;
	private ModelAuditorsGestioneIstruttoriaATPO model = new ModelAuditorsGestioneIstruttoriaATPO();
	private AtpoFasiService service;

	public GestioneIstruttoriaAtpoAction(AtpoFasiService service) {
		super();
		this.service = service;
	}

	public String getGestioneIstruttoria() {

		HttpServletRequest request = ServletActionContext.getRequest();
		DatiUtente user = (DatiUtente) request.getSession().getAttribute(
				"DatiUtente");
		if (user != null) {

			// INIZIO - VERIFICA PRATICA MODIFICABILE
			AuSPratica auSPratica = service.cerca(AuSPratica.class, user
					.getIdSPratica());
			AppUser appUser = (AppUser) request.getSession().getAttribute(
					"AppUser");
			if (auSPratica.getUserOwner() == null)
				getModel().setPraticaModificable("V");
			else if (auSPratica.getUserOwner().equals(appUser.getUsername()))
				getModel().setPraticaModificable("V");
			else
				getModel().setPraticaModificable("F");
			// FINE - VERIFICA PRATICA MODIFICABILE

			AtpoFaseGestioneIstruttoria gestioneIstruttoria = null;
			try {
				gestioneIstruttoria = service.getFaseGestioneIstruttoria(user
						.getIdFaseDati());

				if (gestioneIstruttoria != null) {

					// metto in sessione l id gestione istruttoria
					user.setIdGestioneIstruttoria(gestioneIstruttoria
							.getIdGestioneIstruttoria());

					getModel()
							.setStatoEsamePratica(user.getStatoEsamePratica());
					if(gestioneIstruttoria.getDataCostitGiudizio()==null){
						gestioneIstruttoria.setCostGiudTelematica("");
					}
					// converto i campi tipologici da codifica a descrizione
					gestioneIstruttoria = convertCodToDescr(gestioneIstruttoria);
					
					getModel().setGestioneIStruttoria(gestioneIstruttoria);

				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			// una query per il dropdown SI NO
			try {

				List<AuTplTipologicheAtpoDto> siNo = service
						.getTipologicaAtpo("V040");
				if (!siNo.isEmpty()) {

					for (AuTplTipologicheAtpoDto s : siNo) {
						getModel().getYesNo().add(s);
					}

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			/*
			 * query per dropdown: - litispendeza, - decadenza, -
			 * indeterminatezza dell'oggetto della domanda - Carenza interesse
			 * ad agire (art. 100 c.p.c.) - Altre eccezioni processuali
			 */
			try {
				List<AuTplTipologicheAtpoDto> optionsCondivise = service
						.getTipologicaAtpo("V025");
				if (!optionsCondivise.isEmpty()) {

					for (AuTplTipologicheAtpoDto a : optionsCondivise) {
						getModel().getOptionsDropDownCondivisi().add(a);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			// query per dropdown Corrispondenza tra istanza ATP e domanda
			// amministrativa di invalidità
			try {

				List<AuTplTipologicheAtpoDto> corrispondenzaIstanzaATP = service
						.getTipologicaAtpo("V030");
				if (!corrispondenzaIstanzaATP.isEmpty()) {

					for (AuTplTipologicheAtpoDto a : corrispondenzaIstanzaATP) {
						getModel().getCorrispondenzaIstanzaATP().add(a);
					}

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			// query dropdown Verifica della correttezza della dichiarazione di
			// esenzione dal pagamento spese
			try {

				List<AuTplTipologicheAtpoDto> correttezzaDichiarazioneEsenzione = service
						.getTipologicaAtpo("V026");
				if (!correttezzaDichiarazioneEsenzione.isEmpty()) {

					for (AuTplTipologicheAtpoDto a : correttezzaDichiarazioneEsenzione) {
						getModel().getCorrettezzaDichiarazioneEsenzione()
								.add(a);
					}

				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		return SUCCESS;
	}

	public String salvaGestioneIstruttoria() {
		HttpServletRequest request = ServletActionContext.getRequest();
		DatiUtente user = (DatiUtente) request.getSession().getAttribute(
				"DatiUtente");
		if (user != null) {
			long idGestioneIstruttoria = user.getIdGestioneIstruttoria();
			long idFaseDati = user.getIdFaseDati();
			AtpoFaseGestioneIstruttoria a = getModel().getGestioneIStruttoria();
			if (idGestioneIstruttoria != 0) {
				a.setIdGestioneIstruttoria(idGestioneIstruttoria);
				a.setFasePronta("S");
			}
			a.setIdFaseDati(idFaseDati);
			service.salva(a);
		}

		return SUCCESS;

	}

	/*
	 * CONVERTE LE CODIFICHE TIPOLOGICHE NELLE RISPETTIVE DESCRIZIONI DA
	 * MOSTRARE
	 */
	private AtpoFaseGestioneIstruttoria convertCodToDescr(
			AtpoFaseGestioneIstruttoria gestioneIstruttoria) {

		try {
			// eccezioni non rilevabili
			if (gestioneIstruttoria.getEccezioniNonRilevabili() != null
					&& !gestioneIstruttoria.getEccezioniNonRilevabili().trim()
							.equals("")) {
				String codificaEccNonRilevabili = gestioneIstruttoria
						.getEccezioniNonRilevabili().trim();
				getModel()
						.setCodificaEccNonRilevabili(codificaEccNonRilevabili);
				AuTplTipologicheAtpoDto t = service
						.getDescrTipologicaByCodifica("V040",
								codificaEccNonRilevabili.trim());
				gestioneIstruttoria.setEccezioniNonRilevabili(t
						.getDescrizione());
			}
			// Listispedenza
			if (gestioneIstruttoria.getLitispendenza() != null
					&& !gestioneIstruttoria.getLitispendenza().trim()
							.equals("")) {
				String codificaLitispendenza = gestioneIstruttoria
						.getLitispendenza().trim();
				getModel().setCodificaLitispendenza(codificaLitispendenza);
				AuTplTipologicheAtpoDto t = service
						.getDescrTipologicaByCodifica("V025",
								codificaLitispendenza);
				gestioneIstruttoria.setLitispendenza(t.getDescrizione());
			}
			// Decadenza
			if (gestioneIstruttoria.getDecadenza() != null
					&& !gestioneIstruttoria.getDecadenza().trim().equals("")) {
				String codificaDecadenza = gestioneIstruttoria.getDecadenza()
						.trim();
				getModel().setCodificaDecadenza(codificaDecadenza);
				AuTplTipologicheAtpoDto t = service
						.getDescrTipologicaByCodifica("V025", codificaDecadenza);
				gestioneIstruttoria.setDecadenza(t.getDescrizione());
			}
			// Corrispondenza tra istanza ATP e domanda amministrativa di
			// invalidità
			if (gestioneIstruttoria.getCorrispAtpDomAmmInv() != null
					&& !gestioneIstruttoria.getCorrispAtpDomAmmInv().trim()
							.equals("")) {
				String codificaCorrisp = gestioneIstruttoria
						.getCorrispAtpDomAmmInv().trim();
				getModel().setCodificaCorrisp(codificaCorrisp);
				AuTplTipologicheAtpoDto t = service
						.getDescrTipologicaByCodifica("V030", codificaCorrisp);
				gestioneIstruttoria.setCorrispAtpDomAmmInv(t.getDescrizione());
			}
			// Verifica della correttezza della dichiarazione di esenzione dal
			// pagamento spese
			if (gestioneIstruttoria.getVerificaDicEsPagSpese() != null
					&& !gestioneIstruttoria.getVerificaDicEsPagSpese().trim()
							.equals("")) {
				String codificaCorrett = gestioneIstruttoria
						.getVerificaDicEsPagSpese().trim();
				getModel().setCodificaCorrett(codificaCorrett);
				AuTplTipologicheAtpoDto t = service
						.getDescrTipologicaByCodifica("V026", codificaCorrett);
				gestioneIstruttoria
						.setVerificaDicEsPagSpese(t.getDescrizione());
			}
			// Indeterminatezza dell'oggetto della domanda
			if (gestioneIstruttoria.getIndeterminatezzaOggDom() != null
					&& !gestioneIstruttoria.getIndeterminatezzaOggDom().trim()
							.equals("")) {
				String codificaIndet = gestioneIstruttoria
						.getIndeterminatezzaOggDom().trim();
				getModel().setCodificaIndet(codificaIndet);
				AuTplTipologicheAtpoDto t = service
						.getDescrTipologicaByCodifica("V025", codificaIndet);
				gestioneIstruttoria.setIndeterminatezzaOggDom(t
						.getDescrizione());
			}
			// Carenza interesse ad agire (art. 100 c.p.c.)
			if (gestioneIstruttoria.getCarenzaInteresseAdAgire() != null
					&& !gestioneIstruttoria.getCarenzaInteresseAdAgire().trim()
							.equals("")) {
				String codificacar = gestioneIstruttoria
						.getCarenzaInteresseAdAgire().trim();
				getModel().setCodificacar(codificacar);
				AuTplTipologicheAtpoDto t = service
						.getDescrTipologicaByCodifica("V025", codificacar);
				gestioneIstruttoria.setCarenzaInteresseAdAgire(t
						.getDescrizione());
			}
			// Costituzione in giudizio telematica
			if (gestioneIstruttoria.getCostGiudTelematica() != null
					&& !gestioneIstruttoria.getCostGiudTelematica().trim()
							.equals("")) {
				String codificatel = gestioneIstruttoria
						.getCostGiudTelematica().trim();
				getModel().setCodificatel(codificatel);
				AuTplTipologicheAtpoDto t = service
						.getDescrTipologicaByCodifica("V040", codificatel);
				gestioneIstruttoria.setCostGiudTelematica(t.getDescrizione());
			}
			// Altre eccezioni processuali
			if (gestioneIstruttoria.getAltreEccProcessuali() != null
					&& !gestioneIstruttoria.getAltreEccProcessuali().trim()
							.equals("")) {
				String codifiAltreC = gestioneIstruttoria
						.getAltreEccProcessuali().trim();
				getModel().setCodifiAltreC(codifiAltreC);
				AuTplTipologicheAtpoDto t = service
						.getDescrTipologicaByCodifica("V025", codifiAltreC);
				gestioneIstruttoria.setAltreEccProcessuali(t.getDescrizione());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return gestioneIstruttoria;
	}

	public ModelAuditorsGestioneIstruttoriaATPO getModel() {

		return model;
	}

}
