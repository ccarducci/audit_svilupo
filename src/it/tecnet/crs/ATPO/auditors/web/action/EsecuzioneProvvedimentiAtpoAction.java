package it.tecnet.crs.ATPO.auditors.web.action;

import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoFaseDati;
import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoFaseEsecuzioneProvvedimenti;
import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoFasePostPeritale;
import it.tecnet.crs.ATPO.auditors.service.AtpoFasiService;
import it.tecnet.crs.ATPO.auditors.web.beans.ModelAuditorsEsecuzioneProvvedimentiAtpo;
import it.tecnet.crs.ATPO.auditors.web.dto.AuTplTipologicheAtpoDto;
import it.tecnet.crs.jpa.model.AuSPratica;
import it.tecnet.crs.session.DatiUtente;
import it.tecnet.crs.web.action.BaseAction;
import it.tecnet.crs.web.beans.AppUser;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ModelDriven;

public class EsecuzioneProvvedimentiAtpoAction extends BaseAction implements
		ModelDriven<ModelAuditorsEsecuzioneProvvedimentiAtpo> {
	protected static Logger log = Logger.getLogger(EsecuzioneProvvedimentiAtpoAction.class);

	private static final long serialVersionUID = 1L;
	private ModelAuditorsEsecuzioneProvvedimentiAtpo model = new ModelAuditorsEsecuzioneProvvedimentiAtpo();
	private AtpoFasiService service;

	public EsecuzioneProvvedimentiAtpoAction(AtpoFasiService service) {
		super();
		this.service = service;
	}

	public String getGiudizioFaseDati() {

		HttpServletRequest request = ServletActionContext.getRequest();
		DatiUtente user = (DatiUtente) request.getSession().getAttribute("DatiUtente");
		getModel().setGiudiziofaseDati(user.getGiudizioFaseDati().trim());

		return SUCCESS;
	}

	public String getEsecuzioneProvvedimento() {

		HttpServletRequest request = ServletActionContext.getRequest();
		DatiUtente user = (DatiUtente) request.getSession().getAttribute("DatiUtente");
		AtpoFaseEsecuzioneProvvedimenti esecuzioneProvvedimenti = null;
		AtpoFasePostPeritale p = null;

		if (user != null) {

			// INIZIO - VERIFICA PRATICA MODIFICABILE
			AuSPratica auSPratica = service.cerca(AuSPratica.class, user.getIdSPratica());
			AppUser appUser = (AppUser) request.getSession().getAttribute("AppUser");
			if (auSPratica.getUserOwner() == null)
				getModel().setPraticaModificable("V");
			else if (auSPratica.getUserOwner().equals(appUser.getUsername()))
				getModel().setPraticaModificable("V");
			else
				getModel().setPraticaModificable("F");
			// FINE - VERIFICA PRATICA MODIFICABILE

			try {
				// Recupero data Sessione/Accesso
				Long idSessione = user.getIdSessione();
				Date dataAccesso = service.getDateSessione(idSessione);
				getModel().setDataAccesso(dataAccesso);
				
				// query per prendermi AtpoFaseEsecuzioneProvvedimenti
				esecuzioneProvvedimenti = service.getEsecuzioneProvvedimenti(user.getIdFaseDati());
				getModel().setStatoEsamePratica(user.getStatoEsamePratica());
				if (esecuzioneProvvedimenti != null) {
					// prendo le date omologa da post peritale
					p = service.getFasePostPeritale(user.getIdFaseDati());
					if (p != null) {
						getModel().setDataDepositoDecretoOmologa(p.getDataDepositoDecOmologa());
						getModel().setDataNotificaDecretoOmologa(p.getDataNotificaDecOmologa());
						getModel().setDataTrasmissioneLps(p.getDataTrasmissDecrLPS());
					}

					// converto i campi tipologici da codifica a descrizione
					esecuzioneProvvedimenti = convertCodToDescr(esecuzioneProvvedimenti);

					getModel().setEsecuzioneProvvedimenti(esecuzioneProvvedimenti);

					// metto in sessione l id esecuzione provvedimenti
					user.setIdEsecuzioneProvvedimenti(esecuzioneProvvedimenti
							.getIdEsecuzioneProvvedimenti());

				}

			} catch (Exception e) {
				log.warn("Nessuna fase esecuzione provvedimenti trovata in DB");
			}
			// query per i dropdown si/no Esecuzione Provvedimenti
			try {

				List<AuTplTipologicheAtpoDto> optionsSiNoEsProvv = service
						.getTipologicaAtpo("V040");
				if (!optionsSiNoEsProvv.isEmpty()) {

					for (AuTplTipologicheAtpoDto s : optionsSiNoEsProvv) {
						getModel().getOptionsSiNoEsProvv().add(s);
					}

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			// query per il dropdown condanna pagamento spese legali Esecuzione
			// Provvedimenti
			try {

				List<AuTplTipologicheAtpoDto> opzCondPagSpeseLegali = service
						.getTipologicaAtpo("V031");
				if (!opzCondPagSpeseLegali.isEmpty()) {

					for (AuTplTipologicheAtpoDto a : opzCondPagSpeseLegali) {
						getModel().getOpzCondPagSpeseLegali().add(a);
					}

				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			// query per il dropdown soggetto richiedente il pagamento
			// Esecuzione Provvedimenti
			try {
				List<AuTplTipologicheAtpoDto> opzSoggRichPagamento = service
						.getTipologicaAtpo("V014");
				if (!opzSoggRichPagamento.isEmpty()) {

					for (AuTplTipologicheAtpoDto a : opzSoggRichPagamento) {
						getModel().getOpzSoggRichPagamento().add(a);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		return SUCCESS;
	}

	public String getEsecuzioneProvvedimentoB() {
		HttpServletRequest request = ServletActionContext.getRequest();
		DatiUtente user = (DatiUtente) request.getSession().getAttribute("DatiUtente");

		// INIZIO - VERIFICA PRATICA MODIFICABILE
		AuSPratica auSPratica = service.cerca(AuSPratica.class, user.getIdSPratica());
		AppUser appUser = (AppUser) request.getSession().getAttribute("AppUser");
		if (auSPratica.getUserOwner() == null)
			getModel().setPraticaModificable("V");
		else if (auSPratica.getUserOwner().equals(appUser.getUsername()))
			getModel().setPraticaModificable("V");
		else
			getModel().setPraticaModificable("F");
		// FINE - VERIFICA PRATICA MODIFICABILE

		AtpoFaseEsecuzioneProvvedimenti esecuzioneProvvedimenti = null;
		try {
			// query per prendermi AtpoFaseEsecuzioneProvvedimenti
			esecuzioneProvvedimenti = service.getEsecuzioneProvvedimenti(user.getIdFaseDati());
			if (esecuzioneProvvedimenti != null) {
				// converto i campi tipologici da codifica a descrizione
				esecuzioneProvvedimenti = convertCodToDescr(esecuzioneProvvedimenti);

				getModel().setEsecuzioneProvvedimenti(esecuzioneProvvedimenti);
				getModel().setStatoEsamePratica(user.getStatoEsamePratica());

				// metto in sessione l id esecuzione provvedimenti
				user.setIdEsecuzioneProvvedimenti(esecuzioneProvvedimenti
						.getIdEsecuzioneProvvedimenti());
			}
		} catch (Exception e) {

		}

		// query per il dropdown verifica pagamento CTU già effettuato
		try {
			List<AuTplTipologicheAtpoDto> optionsVerPagCtuEff = service.getTipologicaAtpo("V032");
			if (!optionsVerPagCtuEff.isEmpty()) {

				for (AuTplTipologicheAtpoDto a : optionsVerPagCtuEff) {
					getModel().getOptionsVerPagCtuEff().add(a);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// query per i dropdown si/no Esecuzione Provvedimenti
		try {

			List<AuTplTipologicheAtpoDto> optionsSiNoEsProvv = service.getTipologicaAtpo("V040");
			if (!optionsSiNoEsProvv.isEmpty()) {

				for (AuTplTipologicheAtpoDto s : optionsSiNoEsProvv) {
					getModel().getOptionsSiNoEsProvv().add(s);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return SUCCESS;
	}

	/*
	 * CONVERTE DA CODIFICA A DESCRIZIONE
	 */

	private AtpoFaseEsecuzioneProvvedimenti convertCodToDescr(
			AtpoFaseEsecuzioneProvvedimenti esecuzioneProvvedimenti) {
		try {
			if (esecuzioneProvvedimenti.getPresDecrOmgFasc() != null
					&& !esecuzioneProvvedimenti.getPresDecrOmgFasc().trim().equals("")) {
				String codPresDecrOmgFasc = esecuzioneProvvedimenti.getPresDecrOmgFasc().trim();
				getModel().setCodPresDecrOmgFasc(codPresDecrOmgFasc);
				AuTplTipologicheAtpoDto t = service.getDescrTipologicaByCodifica("V040",
						codPresDecrOmgFasc.trim());
				esecuzioneProvvedimenti.setPresDecrOmgFasc(t.getDescrizione());
			}
			if (esecuzioneProvvedimenti.getPrestCorrisp() != null
					&& !esecuzioneProvvedimenti.getPrestCorrisp().trim().equals("")) {
				String codPrestCorrisp = esecuzioneProvvedimenti.getPrestCorrisp().trim();
				getModel().setCodPrestCorrisp(codPrestCorrisp);
				AuTplTipologicheAtpoDto t = service.getDescrTipologicaByCodifica("V040",
						codPrestCorrisp.trim());
				esecuzioneProvvedimenti.setPrestCorrisp(t.getDescrizione());
			}
			if (esecuzioneProvvedimenti.getCondannaPagCtuAtpo() != null
					&& !esecuzioneProvvedimenti.getCondannaPagCtuAtpo().trim().equals("")) {
				String codCondannaPagCtuAtpo = esecuzioneProvvedimenti.getCondannaPagCtuAtpo()
						.trim();
				getModel().setCodCondannaPagCtuAtpo(codCondannaPagCtuAtpo);
				AuTplTipologicheAtpoDto t = service.getDescrTipologicaByCodifica("V040",
						codCondannaPagCtuAtpo.trim());
				esecuzioneProvvedimenti.setCondannaPagCtuAtpo(t.getDescrizione());
			}
			if (esecuzioneProvvedimenti.getAntSpeseCtu() != null
					&& !esecuzioneProvvedimenti.getAntSpeseCtu().trim().equals("")) {
				String codAntSpeseCtu = esecuzioneProvvedimenti.getAntSpeseCtu().trim();
				getModel().setCodAntSpeseCtu(codAntSpeseCtu);
				AuTplTipologicheAtpoDto t = service.getDescrTipologicaByCodifica("V040",
						codAntSpeseCtu.trim());
				esecuzioneProvvedimenti.setAntSpeseCtu(t.getDescrizione());
			}
			if (esecuzioneProvvedimenti.getCondannaPagSpeseLegali() != null
					&& !esecuzioneProvvedimenti.getCondannaPagSpeseLegali().trim().equals("")) {
				String codCondannaPagSpeseLegali = esecuzioneProvvedimenti
						.getCondannaPagSpeseLegali().trim();
				getModel().setCodCondannaPagSpeseLegali(codCondannaPagSpeseLegali);
				AuTplTipologicheAtpoDto t = service.getDescrTipologicaByCodifica("V031",
						codCondannaPagSpeseLegali.trim());
				if (t != null)
					esecuzioneProvvedimenti.setCondannaPagSpeseLegali(t.getDescrizione());
			}
			if (esecuzioneProvvedimenti.getSoggRichPagamento() != null
					&& !esecuzioneProvvedimenti.getSoggRichPagamento().trim().equals("")) {
				String codSoggRichPagamento = esecuzioneProvvedimenti.getSoggRichPagamento().trim();
				getModel().setCodSoggRichPagamento(codSoggRichPagamento);
				AuTplTipologicheAtpoDto t = service.getDescrTipologicaByCodifica("V014",
						codSoggRichPagamento.trim());
				esecuzioneProvvedimenti.setSoggRichPagamento(t.getDescrizione());
			}
			if (esecuzioneProvvedimenti.getPresDecrSentMancPagPrest() != null
					&& !esecuzioneProvvedimenti.getPresDecrSentMancPagPrest().trim().equals("")) {
				String codDresDecrSentMancPagPrest = esecuzioneProvvedimenti
						.getPresDecrSentMancPagPrest().trim();
				getModel().setCodDresDecrSentMancPagPrest(codDresDecrSentMancPagPrest);
				AuTplTipologicheAtpoDto t = service.getDescrTipologicaByCodifica("V040",
						codDresDecrSentMancPagPrest.trim());
				esecuzioneProvvedimenti.setPresDecrSentMancPagPrest(t.getDescrizione());
			}
			if (esecuzioneProvvedimenti.getCondannaPagCtu1g() != null
					&& !esecuzioneProvvedimenti.getCondannaPagCtu1g().trim().equals("")) {
				String codCondannaPagCtuPg = esecuzioneProvvedimenti.getCondannaPagCtu1g().trim();
				getModel().setCodCondannaPagCtuPg(codCondannaPagCtuPg);
				AuTplTipologicheAtpoDto t = service.getDescrTipologicaByCodifica("V040",
						codCondannaPagCtuPg.trim());
				esecuzioneProvvedimenti.setCondannaPagCtu1g(t.getDescrizione());
			}
			if (esecuzioneProvvedimenti.getVerPagCtuEff() != null
					&& !esecuzioneProvvedimenti.getVerPagCtuEff().trim().equals("")) {
				String codVerPagCtuEff = esecuzioneProvvedimenti.getVerPagCtuEff().trim();
				getModel().setCodVerPagCtuEff(codVerPagCtuEff);
				AuTplTipologicheAtpoDto t = service.getDescrTipologicaByCodifica("V032",
						codVerPagCtuEff.trim());
				esecuzioneProvvedimenti.setVerPagCtuEff(t.getDescrizione());
			}
			return esecuzioneProvvedimenti;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * SALVA ESECUZIONE PROVVEDIMENTI
	 */
	public String salvaEsecuzioneProvvedimenti() {

		HttpServletRequest request = ServletActionContext.getRequest();
		DatiUtente user = (DatiUtente) request.getSession().getAttribute("DatiUtente");

		if (user != null) {
			long idEsecuzioneProvvedimenti = user.getIdEsecuzioneProvvedimenti();
			long idFaseDati = user.getIdFaseDati();

			AtpoFaseEsecuzioneProvvedimenti a = getModel().getEsecuzioneProvvedimenti();
			
			/* INIZIO - Per le pratiche con esito differente da dissenso in “Esecuzione provvedimenti” il campo “Importo spese CTU dovute” 
			 * deve essere valorizzato obbligatoriamente (anche con valore 0) se “Importo spese CTU pagate” è diverso da 0;
			 */
			//AtpoFaseDati faseDati = service.cerca(AtpoFaseDati.class,idFaseDati);
			//String giudizioFaseDati = faseDati.getGiudizio();
			String giudizioFaseDati = user.getGiudizioFaseDati();
			if ( !"4".equals(giudizioFaseDati)  
				&& a.getImpSpeseCtuPagate() != null 
				&& a.getImpSpeseCtuPagate().doubleValue() > 0D
				&& a.getImpSpeseCtuDovute() == null ) {
				a.setImpSpeseCtuDovute(0D);
			}else{
				
			}
			/* FINE */
			
			a.setIdEsecuzioneProvvedimenti(idEsecuzioneProvvedimenti);
			a.setIdFaseDati(idFaseDati);
			a.setFasePronta("S");

			// il campo “Importo rata dovuta” deve essere valorizzato sole se è valorizzato i campi “Importo mensile rata Euro” e “Data liquidazione 
			// prestazione da LPS” con quest’ultimo avente data precedente  
			if (a.getDataLiqPrestLps() != null && a.getImportoRataMensile() != null && a.getImportoRataDovuta() == null){
				a.setImportoRataDovuta(0D);
			}
			
			try {
				a = service.salva(a); 
			} catch (Exception e) {
				e.printStackTrace();
			}

			// salvo importo spese ctu dovute in atpoFaseDati
			// ( il campo “Importo spese CTU dovute” deve essere valorizzato sole se è valorizzato il campo “Importo spese CTU pagate” )
			if (a.getImpSpeseCtuDovute() != null) {
				try {
					Double impSpeseCtuDovute = new Double(a.getImpSpeseCtuDovute());
					AtpoFaseDati fd = service.cerca(AtpoFaseDati.class, idFaseDati);
					fd.setImportoSpeseCTU(BigDecimal.valueOf(impSpeseCtuDovute));
					service.salva(fd);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				AtpoFaseDati fd = service.cerca(AtpoFaseDati.class, idFaseDati);
				fd.setImportoSpeseCTU(BigDecimal.valueOf(0D));
				service.salva(fd);
			}
			
		}
		return SUCCESS;
	}

	@Override
	public ModelAuditorsEsecuzioneProvvedimentiAtpo getModel() {
		return model;
	}
}
