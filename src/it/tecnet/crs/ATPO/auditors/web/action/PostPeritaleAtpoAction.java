package it.tecnet.crs.ATPO.auditors.web.action;

import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoFaseDati;
import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoFasePeritale;
import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoFasePostPeritale;
import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoPraticheSISCO;
import it.tecnet.crs.ATPO.auditors.service.AtpoFasiService;
import it.tecnet.crs.ATPO.auditors.web.beans.ModelAuditorsPostPeritaleAtpo;
import it.tecnet.crs.ATPO.auditors.web.dto.AuTplTipologicheAtpoDto;
import it.tecnet.crs.jpa.model.AuSPratica;
import it.tecnet.crs.session.DatiUtente;
import it.tecnet.crs.util.PraticaUtil;
import it.tecnet.crs.web.action.BaseAction;
import it.tecnet.crs.web.beans.AppUser;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ModelDriven;

public class PostPeritaleAtpoAction extends BaseAction implements
		ModelDriven<ModelAuditorsPostPeritaleAtpo> {

	private static final long serialVersionUID = 1L;
	private ModelAuditorsPostPeritaleAtpo model = new ModelAuditorsPostPeritaleAtpo();
	private AtpoFasiService service;

	public PostPeritaleAtpoAction(AtpoFasiService service) {
		super();
		this.service = service;
	}

	/*
	 * SERVE A SAPERE QUALE MASCHERA MOSTRARE
	 */
	public String getGiudizioFaseDati() {
		HttpServletRequest request = ServletActionContext.getRequest();
		DatiUtente user = (DatiUtente) request.getSession().getAttribute(
				"DatiUtente");
		getModel().setGiudiziofaseDati(user.getGiudizioFaseDati().trim());
		return SUCCESS;
	}

	/*
	 * maschera da mostrare quando giudizio fase dati ="Y02" || ="Y01" || ="Y04"
	 */

	public String getPostPeritale() {

		HttpServletRequest request = ServletActionContext.getRequest();
		DatiUtente user = (DatiUtente) request.getSession().getAttribute(
				"DatiUtente");
		AtpoFasePostPeritale postPeritale = null;

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
				// query per prendermi peritale
				postPeritale = service.getFasePostPeritale(user.getIdFaseDati());
				getModel().setGiudiziofaseDati(user.getGiudizioFaseDati().trim());
				getModel().setStatoEsamePratica(user.getStatoEsamePratica());
				if (postPeritale != null) {

					// metto in sessione l id peritale
					user.setIdPostPeritale(postPeritale.getIdPostPeritale());
					// converto i campi tipologici da codifica a descrizione
					postPeritale = convertCodToDescr(postPeritale);
					getModel().setPostPeritale(postPeritale);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			// query per i dropdown codice chiusura corretto e codice chiusura
			// inserito

			try {

				List<AuTplTipologicheAtpoDto> optionsCodChiusura = service
						.getTipologicaAtpo("V019");
				if (!optionsCodChiusura.isEmpty()) {

					for (AuTplTipologicheAtpoDto s : optionsCodChiusura) {
						getModel().getOptionsCodChiusura().add(s);
					}

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			// query per il dropdown verifica corrispondenza tra decreto di
			// omologa e ctu def
			try {

				List<AuTplTipologicheAtpoDto> corrispDecrOMGeCtuDef = service
						.getTipologicaAtpo("V033");
				if (!corrispDecrOMGeCtuDef.isEmpty()) {

					for (AuTplTipologicheAtpoDto a : corrispDecrOMGeCtuDef) {
						getModel().getCorrispDecrOMGeCtuDef().add(a);
					}

				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			// query per il dropdown cod pagamento spese legali INPS e spese
			// legali corretto
			try {

				List<AuTplTipologicheAtpoDto> codPagamentoSpeseLegaliINPS = service
						.getTipologicaAtpo("V012");
				if (!codPagamentoSpeseLegaliINPS.isEmpty()) {

					for (AuTplTipologicheAtpoDto a : codPagamentoSpeseLegaliINPS) {
						getModel().getCodPagamentoSpeseLegaliINPS().add(a);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			// query per il dropdown registrazione dati pratica
			try {

				List<AuTplTipologicheAtpoDto> optionsRecDatiPratica = service
						.getTipologicaAtpo("V016");
				if (!optionsRecDatiPratica.isEmpty()) {

					for (AuTplTipologicheAtpoDto a : optionsRecDatiPratica) {
						getModel().getOptionsRecDatiPratica().add(a);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			// query per il dropdown Omologa allegata
			try {

				List<AuTplTipologicheAtpoDto> omologaAllegata = service
						.getTipologicaAtpo("V040");
				if (!omologaAllegata.isEmpty()) {

					for (AuTplTipologicheAtpoDto a : omologaAllegata) {
						getModel().getOptionsSiNoPP().add(a);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return SUCCESS;
	}

	/*
	 * POST PERITALE MASCHERA 2
	 */
	public String getPostPeritaleB() {
		HttpServletRequest request = ServletActionContext.getRequest();
		DatiUtente user = (DatiUtente) request.getSession().getAttribute(
				"DatiUtente");
		AtpoFasePostPeritale postPeritale = null;
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
				// query per prendermi peritale
				getModel().setStatoEsamePratica(user.getStatoEsamePratica());
				postPeritale = service
						.getFasePostPeritale(user.getIdFaseDati());
				// query per prendere data termine dissenso in sisco
				peritale = service.getFasePeritale(user.getIdFaseDati());

				if (postPeritale == null) {
					// inserisco l oggetto peritale con idFaseDati
					postPeritale = new AtpoFasePostPeritale();
					postPeritale.setIdfaseDati(user.getIdFaseDati());
					postPeritale.setFasePronta("N");
					postPeritale = service.salva(postPeritale);

				}
				
				if (postPeritale != null && postPeritale.getCodChiusuraInserito() != null)
					getModel().setCodChiusuraInserito(postPeritale.getCodChiusuraInserito().trim());
				
				if (postPeritale != null && postPeritale.getCodChiusuraCorretto() != null)
					getModel().setCodChiusuraCorretto(postPeritale.getCodChiusuraCorretto().trim());
				
				if (peritale != null) {
					// mostro data dissenso di
					getModel().setDataDissenso(peritale.getDataTermDissSisco());
				}
				if(postPeritale.getDataDepDiss()!=null && peritale.getDataTermDissSisco() !=null){
					int giorni=PraticaUtil.daysBetween(postPeritale.getDataDepDiss(),peritale.getDataTermDissSisco());
					postPeritale.setIntTempDepDiss(giorni);
				}
				if(postPeritale.getDataDepDiss()==null || peritale.getDataTermDissSisco() ==null){
					postPeritale.setIntTempDepDiss(0);
				}
				
				getModel().setPostPeritale(postPeritale);
				// metto in sessione l id peritale
				user.setIdPostPeritale(postPeritale.getIdPostPeritale());
				// converto i campi tipologici da codifica a descrizione
				postPeritale = convertCodToDescr(postPeritale);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// query per il dropdown comunicato deposito dissenso a uff legale
			try {

				List<AuTplTipologicheAtpoDto> comunicatoDepDissUffL = service
						.getTipologicaAtpo("V015");
				if (!comunicatoDepDissUffL.isEmpty()) {

					for (AuTplTipologicheAtpoDto a : comunicatoDepDissUffL) {
						getModel().getComunicatoDepDissUffL().add(a);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {

				List<AuTplTipologicheAtpoDto> tipoDis = service
						.getTipologicaAtpo("V034");
				if (!tipoDis.isEmpty()) {

					for (AuTplTipologicheAtpoDto a : tipoDis) {
						getModel().getTipoDis().add(a);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return SUCCESS;
	}

	/*
	 * converte codifiche in descrizione
	 */
	private AtpoFasePostPeritale convertCodToDescr(
			AtpoFasePostPeritale postPeritale) {

		if (postPeritale.getCodChiusuraCorretto() != null
				&& !postPeritale.getCodChiusuraCorretto().trim().equals("")) {
			String codificaCodChiusuraCorretto = postPeritale
					.getCodChiusuraCorretto().trim();
			getModel().setCodificaCodChiusuraCorretto(
					codificaCodChiusuraCorretto);
			AuTplTipologicheAtpoDto t = service.getDescrTipologicaByCodifica(
					"V019", codificaCodChiusuraCorretto.trim());
			postPeritale.setCodChiusuraCorretto(t.getDescrizione());
		}
		if (postPeritale.getCodChiusuraInserito() != null
				&& !postPeritale.getCodChiusuraInserito().trim().equals("")) {
			String codificaCodChiusuraInserito = postPeritale
					.getCodChiusuraInserito().trim();
			getModel().setCodificaCodChiusuraInserito(
					codificaCodChiusuraInserito);
			AuTplTipologicheAtpoDto t = service.getDescrTipologicaByCodifica(
					"V019", codificaCodChiusuraInserito.trim());
			postPeritale.setCodChiusuraInserito(t.getDescrizione());
		}
		if (postPeritale.getCorrispDecrOmgEctuDef() != null
				&& !postPeritale.getCorrispDecrOmgEctuDef().trim().equals("")) {
			String codCorrispDecrOmgEctuDef = postPeritale
					.getCorrispDecrOmgEctuDef().trim();
			getModel().setCodCorrispDecrOmgEctuDef(codCorrispDecrOmgEctuDef);
			AuTplTipologicheAtpoDto t = service.getDescrTipologicaByCodifica(
					"V033", codCorrispDecrOmgEctuDef.trim());
			postPeritale.setCorrispDecrOmgEctuDef(t.getDescrizione());
		}
		if (postPeritale.getCodPagamentoSpeseLegali() != null
				&& !postPeritale.getCodPagamentoSpeseLegali().trim().equals("")) {
			String codCodPagamentoSpeseLegali = postPeritale
					.getCodPagamentoSpeseLegali().trim();
			getModel()
					.setCodCodPagamentoSpeseLegali(codCodPagamentoSpeseLegali);
			AuTplTipologicheAtpoDto t = service.getDescrTipologicaByCodifica(
					"V012", codCodPagamentoSpeseLegali.trim());
			postPeritale.setCodPagamentoSpeseLegali(t.getDescrizione());
		}
		if (postPeritale.getCodPagamentoSpeseLegaliCorretto() != null
				&& !postPeritale.getCodPagamentoSpeseLegaliCorretto().trim()
						.equals("")) {
			String codCodPagamentoSpeseLegaliCorr = postPeritale
					.getCodPagamentoSpeseLegaliCorretto().trim();
			getModel().setCodCodPagamentoSpeseLegaliCorretto(
					codCodPagamentoSpeseLegaliCorr);
			AuTplTipologicheAtpoDto t = service.getDescrTipologicaByCodifica(
					"V012", codCodPagamentoSpeseLegaliCorr.trim());
			postPeritale.setCodPagamentoSpeseLegaliCorretto(t.getDescrizione());
		}
		if (postPeritale.getRecDatiPratica() != null
				&& !postPeritale.getRecDatiPratica().trim().equals("")) {
			String codRecDatiPratica = postPeritale.getRecDatiPratica().trim();
			getModel().setCodRecDatiPratica(codRecDatiPratica);
			AuTplTipologicheAtpoDto t = service.getDescrTipologicaByCodifica(
					"V016", codRecDatiPratica.trim());
			postPeritale.setRecDatiPratica(t.getDescrizione());
		}
		if (postPeritale.getOmologaAllegata() != null
				&& !postPeritale.getOmologaAllegata().trim().equals("")) {
			String codOmologaAllegata = postPeritale.getOmologaAllegata()
					.trim();
			getModel().setCodOmologaAllegata(codOmologaAllegata);
			AuTplTipologicheAtpoDto t = service.getDescrTipologicaByCodifica(
					"V040", codOmologaAllegata.trim());
			postPeritale.setOmologaAllegata(t.getDescrizione());
		}
		// maschera 2
		if (postPeritale.getComDepDissUffLegale() != null
				&& !postPeritale.getComDepDissUffLegale().trim().equals("")) {
			String codComDepDissUffLegale = postPeritale
					.getComDepDissUffLegale().trim();
			getModel().setCodComDepDissUffLegale(codComDepDissUffLegale);
			AuTplTipologicheAtpoDto t = service.getDescrTipologicaByCodifica(
					"V015", codComDepDissUffLegale.trim());
			postPeritale.setComDepDissUffLegale(t.getDescrizione());
		}
		if (postPeritale.getTipoDissenso() != null
				&& !postPeritale.getTipoDissenso().trim().equals("")) {
			String tipoDiss = postPeritale.getTipoDissenso().trim();
			getModel().setTipoDiss(tipoDiss);
			AuTplTipologicheAtpoDto t = service.getDescrTipologicaByCodifica(
					"V034", tipoDiss.trim());
			postPeritale.setTipoDissenso(t.getDescrizione());
		}
		return postPeritale;
	}

	public String salvaPostPeritale() {
		HttpServletRequest request = ServletActionContext.getRequest();
		DatiUtente user = (DatiUtente) request.getSession().getAttribute(
				"DatiUtente");
		if (user != null) {
			long idPostPeritale = user.getIdPostPeritale();
			long idFaseDati = user.getIdFaseDati();
			AtpoFasePostPeritale a = getModel().getPostPeritale();

			a.setIdPostPeritale(idPostPeritale);
			a.setIdfaseDati(idFaseDati);
			a.setFasePronta("S");
			try{
				a=service.salva(a);
			}catch(Exception e){
				e.printStackTrace();
			}
			
			// il campo “Spese legali nel decreto di omologa” deve essere valorizzato sole se è valorizzato il campo “Spese legali pagate”;
			if(a.getSpesePagate() !=null){
				Double importoSpeseLegali= new Double(a.getSpesePagate());
				AtpoFaseDati fd= service.cerca(AtpoFaseDati.class, idFaseDati);
				fd.setImportoSpeseLegali(BigDecimal.valueOf(importoSpeseLegali));
				service.salva(fd);
			}else{
				AtpoFaseDati fd= service.cerca(AtpoFaseDati.class, idFaseDati);
				fd.setImportoSpeseLegali(BigDecimal.valueOf(0D));
				service.salva(fd);
			}
		
		}
		return SUCCESS;

	}
	
	public String salvaPostPeritaleB() {
		HttpServletRequest request = ServletActionContext.getRequest();
		DatiUtente user = (DatiUtente) request.getSession().getAttribute(
				"DatiUtente");
		/**/
		ModelAuditorsPostPeritaleAtpo model  = getModel();
		if (user != null) {
			long idPostPeritale = user.getIdPostPeritale();
			long idFaseDati = user.getIdFaseDati();
			AtpoFasePostPeritale fasePostPeritale = service.getFasePostPeritale(idFaseDati);
			
			if(fasePostPeritale != null && model != null){
				
				// Cod_chiusura_Corretto
				if (model.getCodChiusuraCorretto()!= null){
					fasePostPeritale.setCodChiusuraCorretto(model.getCodChiusuraCorretto().trim());
				}
				
				// Cod_chiusura_inserito
				if (model.getCodChiusuraInserito()!= null){
					fasePostPeritale.setCodChiusuraInserito(model.getCodChiusuraInserito().trim());
				}
				
				// Datadeposito Diss
				fasePostPeritale.setDataDepDiss(model.getPostPeritale().getDataDepDiss());

				
				// intTempDepDis
				fasePostPeritale.setIntTempDepDiss(model.getPostPeritale().getIntTempDepDiss());

				// comDepDissUffLegale
				if(model.getPostPeritale().getComDepDissUffLegale() != null)
					fasePostPeritale.setComDepDissUffLegale(model.getPostPeritale().getComDepDissUffLegale());
				
				// dataDepRicPrimoG
				fasePostPeritale.setDataDepRicPrimoG(model.getPostPeritale().getDataDepRicPrimoG());
				
				// dataDefP
				fasePostPeritale.setDataDefPratica(model.getPostPeritale().getDataDefPratica());
				
				//  tipoDiss
				fasePostPeritale.setTipoDissenso(model.getPostPeritale().getTipoDissenso());
				
				//  spesePagate
				fasePostPeritale.setSpesePagate(model.getPostPeritale().getSpesePagate());
			}
				
			fasePostPeritale.setIdPostPeritale(idPostPeritale);
			fasePostPeritale.setIdfaseDati(idFaseDati);
			fasePostPeritale.setFasePronta("S");
			try{
				fasePostPeritale=service.salva(fasePostPeritale);
			}catch(Exception e){
				e.printStackTrace();
			}
			
			//il campo “spese legali pagate” va scritto anche su ATPO_FASE_DATI -> IMPORTO_SPESE_LEGALI
			
			if(fasePostPeritale.getSpesePagate() !=null){
				Double importoSpeseLegali= new Double(fasePostPeritale.getSpesePagate());
				AtpoFaseDati fd = service.cerca(AtpoFaseDati.class, idFaseDati);
				fd.setImportoSpeseLegali(BigDecimal.valueOf(importoSpeseLegali));
				service.salva(fd);
			}else{
				AtpoFaseDati fd= service.cerca(AtpoFaseDati.class, idFaseDati);
				fd.setImportoSpeseLegali(BigDecimal.valueOf(0D));
				service.salva(fd);
			}
			//il campo data termine dissenso in sisco va scritto in post peritale
			Date dataDissenso = getModel().getDataDissenso();
			AtpoFasePeritale peritale = service.getFasePeritale(user.getIdFaseDati());
			peritale.setDataTermDissSisco(dataDissenso);
			service.salva(peritale);
		}
		
		return SUCCESS;

	}
	

	public ModelAuditorsPostPeritaleAtpo getModel() {

		return model;
	}

}
