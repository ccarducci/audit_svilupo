package it.tecnet.crs.ATPO.auditors.web.action;

import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoFasePeritale;
import it.tecnet.crs.ATPO.auditors.service.AtpoFasiService;
import it.tecnet.crs.ATPO.auditors.web.beans.ModelAuditorsPeritaleAtpo;
import it.tecnet.crs.ATPO.auditors.web.dto.AuTplTipologicheAtpoDto;
import it.tecnet.crs.jpa.model.AuSPratica;
import it.tecnet.crs.session.DatiUtente;
import it.tecnet.crs.web.action.BaseAction;
import it.tecnet.crs.web.beans.AppUser;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ModelDriven;

public class PeritaleAtpoAction extends BaseAction implements
		ModelDriven<ModelAuditorsPeritaleAtpo> {

	private static final long serialVersionUID = 1L;
	private ModelAuditorsPeritaleAtpo model = new ModelAuditorsPeritaleAtpo();
	private AtpoFasiService service;

	public PeritaleAtpoAction(AtpoFasiService service) {
		super();
		this.service = service;
	}

	public String getPeritale() {

		HttpServletRequest request = ServletActionContext.getRequest();
		DatiUtente user = (DatiUtente) request.getSession().getAttribute(
				"DatiUtente");
		// query per prendermi peritale
		AtpoFasePeritale peritale = null;
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

			try {
				peritale = service.getFasePeritale(user.getIdFaseDati());
				getModel().setStatoEsamePratica(user.getStatoEsamePratica());
				if (peritale != null) {

					// metto in sessione l id peritale
					user.setIdPeritale(peritale.getIdPeritale());

					// converto i campi tipologici da codifica a descrizione
					peritale = convertCodToDescr(peritale);
					getModel().setPeritale(peritale);

				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			// una query per il dropdown Registrazione informazioni operazioni
			// peritali
			try {

				List<AuTplTipologicheAtpoDto> optionsRegInfo = service
						.getTipologicaAtpo("V011");
				if (!optionsRegInfo.isEmpty()) {

					for (AuTplTipologicheAtpoDto s : optionsRegInfo) {
						getModel().getOptionsRegInfo().add(s);
					}

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			// una query per i dropdown SI/NO
			try {

				List<AuTplTipologicheAtpoDto> optionsSiNoPeritale = service
						.getTipologicaAtpo("V040");
				if (!optionsSiNoPeritale.isEmpty()) {

					for (AuTplTipologicheAtpoDto a : optionsSiNoPeritale) {
						getModel().getOptionsSiNoPeritale().add(a);
					}

				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			// una query per i dropdown SI/NO/NON RILEVABILE
			try {

				List<AuTplTipologicheAtpoDto> optionsSiNonNonRilevabile = service
						.getTipologicaAtpo("V013");
				if (!optionsSiNonNonRilevabile.isEmpty()) {

					for (AuTplTipologicheAtpoDto a : optionsSiNonNonRilevabile) {
						getModel().getOptionsSiNonNonRilevabile().add(a);
					}

				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			// una query per i dropdown CONCORDE..
			try {

				List<AuTplTipologicheAtpoDto> optionsPareBozzaCtu = service
						.getTipologicaAtpo("V013");
				if (!optionsPareBozzaCtu.isEmpty()) {

					for (AuTplTipologicheAtpoDto a : optionsPareBozzaCtu) {
						getModel().getOptionsPareBozzaCtu().add(a);
					}

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return SUCCESS;
	}

	public String salvaPeritale() {
		HttpServletRequest request = ServletActionContext.getRequest();
		DatiUtente user = (DatiUtente) request.getSession().getAttribute(
				"DatiUtente");
		if (user != null) {
			long idPeritale = user.getIdPeritale();
			long idFaseDati = user.getIdFaseDati();
			AtpoFasePeritale a = getModel().getPeritale();
			if (idPeritale != 0) {
				a.setIdPeritale(idPeritale);
				a.setFasePronta("S");
			}

			a.setIdFaseDati(idFaseDati);
			try{
				service.salva(a);
			}catch(Exception e){
				e.printStackTrace();
			}
			
			
			
		}
		return SUCCESS;
	}

	private AtpoFasePeritale convertCodToDescr(AtpoFasePeritale peritale) {
		try {
			// Registrazione informazioni operazioni peritali
			if (peritale.getRecInfoOpPeritali() != null
					&& !peritale.getRecInfoOpPeritali().trim().equals("")) {
				String codificaRec = peritale.getRecInfoOpPeritali().trim();
				getModel().setRecInfo(codificaRec);
				AuTplTipologicheAtpoDto t = service
						.getDescrTipologicaByCodifica("V011", codificaRec
								.trim());
				peritale.setRecInfoOpPeritali(t.getDescrizione());
			}
			// Assegnazione CTU a medico INPS
			if (peritale.getAssCTUMedicoInps() != null
					&& !peritale.getAssCTUMedicoInps().trim().equals("")) {
				String codificaAssCtu = peritale.getAssCTUMedicoInps().trim();
				getModel().setAssegnCtuInps(codificaAssCtu);
				AuTplTipologicheAtpoDto t = service
						.getDescrTipologicaByCodifica("V040", codificaAssCtu);
				peritale.setAssCTUMedicoInps(t.getDescrizione());
			}
			// Presenza CTP INPS operazioni peritali
			if (peritale.getCtpINPSopPeritali() != null
					&& !peritale.getCtpINPSopPeritali().trim().equals("")) {
				String presCtp = peritale.getCtpINPSopPeritali().trim();
				getModel().setPresenzaCTPINPS((presCtp));
				AuTplTipologicheAtpoDto t = service
						.getDescrTipologicaByCodifica("V040", presCtp);
				peritale.setCtpINPSopPeritali(t.getDescrizione());
			}
			// CTU bozza con immagine agli atti
			if (peritale.getCtuBozzaImgAtti() != null
					&& !peritale.getCtuBozzaImgAtti().trim().equals("")) {
				String codificaCtuBoz = peritale.getCtuBozzaImgAtti().trim();
				getModel().setCodCtuDefImgAtti(codificaCtuBoz);
				AuTplTipologicheAtpoDto t = service
						.getDescrTipologicaByCodifica("V040", codificaCtuBoz);
				peritale.setCtuBozzaImgAtti(t.getDescrizione());
			}
			// Parere su bozza CTU
			if (peritale.getParereBozzaCtu() != null
					&& !peritale.getParereBozzaCtu().trim().equals("")) {
				String codificaParereBozzaCtu = peritale.getParereBozzaCtu()
						.trim();
				getModel().setCodParereBozzaCtu(codificaParereBozzaCtu);
				AuTplTipologicheAtpoDto t = service
						.getDescrTipologicaByCodifica("V013",
								codificaParereBozzaCtu);
				peritale.setParereBozzaCtu(t.getDescrizione());
			}
			// Osservazioni su bozza
			if (peritale.getOssBozza() != null
					&& !peritale.getOssBozza().trim().equals("")) {
				String codificaOssBozza = peritale.getOssBozza().trim();
				getModel().setCodOssBozza(codificaOssBozza);
				AuTplTipologicheAtpoDto t = service
						.getDescrTipologicaByCodifica("V040", codificaOssBozza);
				peritale.setOssBozza(t.getDescrizione());
			}
			// CTU definitiva con immagine agli atti
			if (peritale.getCtuDefImgAtti() != null
					&& !peritale.getCtuDefImgAtti().trim().equals("")) {
				String codifiCtuDefImgAtti = peritale.getCtuDefImgAtti().trim();
				getModel().setCodCtuDefImgAtti(codifiCtuDefImgAtti);
				AuTplTipologicheAtpoDto t = service
						.getDescrTipologicaByCodifica("V040",
								codifiCtuDefImgAtti);
				peritale.setCtuDefImgAtti(t.getDescrizione());
			}
			// Parere di DISSENSO/ACCETTAZIONE
			if (peritale.getParereDissAccetfascitazione() != null
					&& !peritale.getParereDissAccetfascitazione().trim()
							.equals("")) {
				String codificaParereDissAccetfascitazione = peritale
						.getParereDissAccetfascitazione().trim();
				getModel()
						.setDissensAccett(codificaParereDissAccetfascitazione);
				AuTplTipologicheAtpoDto t = service
						.getDescrTipologicaByCodifica("V013",
								codificaParereDissAccetfascitazione);
				peritale.setParereDissAccetfascitazione(t.getDescrizione());
			}

			// Osservazioni su parere definitivo
			if (peritale.getOssParDef() != null
					&& !peritale.getOssParDef().trim().equals("")) {
				String codifOssParDef = peritale.getOssParDef().trim();
				getModel().setOssParereDef(codifOssParDef);
				AuTplTipologicheAtpoDto t = service
						.getDescrTipologicaByCodifica("V040", codifOssParDef);
				peritale.setOssParDef(t.getDescrizione());
			}
			// Presenza medico INPS da documentazione CTU
			if (peritale.getPresMedicoInpsDoc() != null
					&& !peritale.getPresMedicoInpsDoc().trim().equals("")) {
				String codPresMedicoInpsDoc = peritale.getPresMedicoInpsDoc()
						.trim();
				getModel().setPresMedicoInpsDoc(codPresMedicoInpsDoc);
				AuTplTipologicheAtpoDto t = service
						.getDescrTipologicaByCodifica("V040",
								codPresMedicoInpsDoc);
				peritale.setPresMedicoInpsDoc(t.getDescrizione());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return peritale;
	}

	public ModelAuditorsPeritaleAtpo getModel() {
		return model;
	}
}
