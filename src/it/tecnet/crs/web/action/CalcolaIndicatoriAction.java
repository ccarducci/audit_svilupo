package it.tecnet.crs.web.action;

import it.tecnet.crs.audit.service.AuCalcolaIndicatoriService;
import it.tecnet.crs.indicatori.pratica.CheckPraticaDatiException;
import it.tecnet.crs.indicatori.pratica.NoPraticheException;
import it.tecnet.crs.jpa.model.AuSPraCalIndLog;
import it.tecnet.crs.session.DatiUtente;
import it.tecnet.crs.util.PraticaUtil;
import it.tecnet.crs.web.beans.AppUser;
import it.tecnet.crs.web.beans.CalcolaIndicatori;
import it.tecnet.crs.web.dto.StatoSessionePratica;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ModelDriven;

public class CalcolaIndicatoriAction extends BaseAction implements
		ModelDriven<CalcolaIndicatori> {

	private static final long serialVersionUID = 1L;
	private CalcolaIndicatori model = new CalcolaIndicatori();

	private AuCalcolaIndicatoriService auCalcolaIndicatoriService;

	public CalcolaIndicatoriAction(
			AuCalcolaIndicatoriService auCalcolaIndicatoriService) {
		super();
		this.auCalcolaIndicatoriService = auCalcolaIndicatoriService;
	}

	@Override
	public CalcolaIndicatori getModel() {
		return model;
	}

	public String calcolaIndicatoriPratica() {
		HttpServletRequest request = ServletActionContext.getRequest();
		DatiUtente user = (DatiUtente) request.getSession().getAttribute(
				"DatiUtente");
		AppUser appUser = (AppUser) request.getSession()
				.getAttribute("AppUser");
		// populateUserForTest(user);

		Long idSSessione = user.getIdSSessione();
		Long id = user.getIdPraticaAtpo();

		if (idSSessione == null || idSSessione == 0 || id == null || id == 0) {
			getModel().setStatus(1);
			getModel().setMessage(PraticaUtil.MESS_CONTROLLO_BEFORE_CALCOLO);
			getModel().setStackTrace(
					"idSSessione o idPratica o sono 0 o sono null");
			return SUCCESS;
		}

		if (idSSessione != null) {
			if (auCalcolaIndicatoriService.isSSsessionOpen(idSSessione)) {
				getModel().setStatus(1);
				getModel()
						.setMessage(
								"La Sessione è chiusa, non è possibile calcolare l'indice per la pratica!");
				return SUCCESS;
			}

			if (user != null) {
				try {
					StatoSessionePratica stato = auCalcolaIndicatoriService
							.canCalculateIndicatori(id);
					if (it.tecnet.crs.util.StatoSessionePratica.ESAME_SESSIONE_APERTO
							.equals(stato.getStatoEsameSessione())
							&& it.tecnet.crs.util.StatoSessionePratica.ESAME_PRATICA_INLAVORAZIONE
									.equals(stato.getEsamePratica())

					) {
						try {

							// PULISCO I LOG
							auCalcolaIndicatoriService
									.cleanCalcoloIndiciPraticaLog(id);

							// SETTO ESAME_SESSIONE PRATICA IN E
							int rowUpdate = auCalcolaIndicatoriService
									.setPraticaInCalcolata(id, appUser
											.getUsername());
							if (rowUpdate == 1) {
								List<AuSPraCalIndLog> listErrori = auCalcolaIndicatoriService
										.calcolaIndicatoriPratica(id);

								user.setStatoEsamePratica("C");
								try {
									for (AuSPraCalIndLog item : listErrori) {
										auCalcolaIndicatoriService.salva(item);
									}
								} catch (Exception e) {
									log.warn("Calcolo Indicaotri Log error: "
											+ e.getMessage());
								}
								getModel().setStatus(0);
								getModel().setMessage(
										"Calcolo Indicatori Eseguito!");
							} else {
								getModel().setStatus(1);
								getModel()
										.setMessage(
												"Non è possibile calcolare gli indicatori la pratica!");
							}
						} catch (CheckPraticaDatiException e) {
							auCalcolaIndicatoriService
									.setEsamePratica(
											id,
											it.tecnet.crs.util.StatoSessionePratica.ESAME_PRATICA_INLAVORAZIONE,
											appUser.getUsername(),appUser.getUsername());
							getModel().setStatus(1);
							getModel().setMessage(
									e.getMessage());
							getModel().setStackTrace(e.getMessage());
						} catch (Exception e) {
							auCalcolaIndicatoriService
									.setEsamePratica(
											id,
											it.tecnet.crs.util.StatoSessionePratica.ESAME_PRATICA_INLAVORAZIONE,
											appUser.getUsername(),appUser.getUsername());
							getModel().setStatus(1);
							getModel().setMessage(
									"Calcolo Indicatori non Eseguito!");
							getModel().setStackTrace(e.getMessage());
						} finally {
							// VERIFICO ESAME_SESSIONE PRATICA SIA ANCORA IN E
							// SE SI ESAME_SESSIONE PRATICA setto ad A
							stato = auCalcolaIndicatoriService
									.canCalculateIndicatori(id);
							if (it.tecnet.crs.util.StatoSessionePratica.ESAME_PRATICA_ELABORAZIONE
									.equals(stato.getStatoEsameSessione()))
								auCalcolaIndicatoriService
										.setEsamePratica(
												id,
												it.tecnet.crs.util.StatoSessionePratica.ESAME_PRATICA_APERTO,
												appUser.getUsername(),appUser.getUsername());
						}
					} else {
						getModel().setStatus(1);
						getModel()
								.setMessage(
										"Calcolo Indicatori non è possibile perchè già in esecuzione!");
					}
				} catch (Exception e) {
					AuSPraCalIndLog item = new AuSPraCalIndLog();
					item.setIdPratica(id);
					item.setMessaggio(e.getMessage());
					item.setTipoErrore("ERRORE_CODICE");
					item.setTipoErrore("CODICE");
					item.setDataInserimento(new java.util.Date());
					auCalcolaIndicatoriService.salva(item);
					getModel().setStatus(1);
					getModel().setMessage("Calcolo Indicatori non Eseguito!");
					getModel().setStackTrace(e.getMessage());
				}
			} else {
				getModel().setStatus(1);
				getModel().setMessage("Calcolo Indicatori non Eseguito!");
			}
		} else {
			getModel().setStatus(1);
			getModel()
					.setMessage(
							"Calcolo Indicatori non Eseguito a causa del parametro idSSessione non valorizzato!");
		}
		return SUCCESS;
	}

	public String calcolaIndicatoriSessione() {
		HttpServletRequest request = ServletActionContext.getRequest();
		DatiUtente user = (DatiUtente) request.getSession().getAttribute(
				"DatiUtente");
		log.debug("calcolaIndicatoriSessione()");
		List<AuSPraCalIndLog> listErrori = null;
		try {
			listErrori = auCalcolaIndicatoriService
					.calcolaIndicatoriSessione(user.getIdSSessione());
			for (AuSPraCalIndLog item : listErrori) {
				auCalcolaIndicatoriService.salva(item);
			}
			AuSPraCalIndLog item = new AuSPraCalIndLog();
			item.setIdSSessione(user.getIdSSessione());
			item.setMessaggio("Calcolo Indicatori Completato");
			item.setDataInserimento(new Date());
			auCalcolaIndicatoriService.salva(item);
		} catch (NoPraticheException e) {
			AuSPraCalIndLog item = new AuSPraCalIndLog();
			item.setIdSSessione(user.getIdSSessione());
			item.setMessaggio(e.getMessage());
			item.setDataInserimento(new Date());
			auCalcolaIndicatoriService.salva(item);
			log.info(e.getMessage());
			getModel().setStatus(1);
			getModel().setMessage("Non ci sono pratiche nella sessione!");
			getModel().setStackTrace(e.getMessage());
		} catch (Exception e) {
			AuSPraCalIndLog item = new AuSPraCalIndLog();
			item.setIdSSessione(user.getIdSSessione());
			item.setMessaggio(e.getMessage());
			item.setDataInserimento(new Date());
			auCalcolaIndicatoriService.salva(item);
			log.error(e.getMessage());
			getModel().setStatus(1);
			getModel().setMessage(PraticaUtil.MESS_CONTROLLO_BEFORE_CALCOLO);
			getModel().setStackTrace(e.getMessage());
		}

		return SUCCESS;
	}

	public String inLavorazionePratica() {
		HttpServletRequest request = ServletActionContext.getRequest();

		DatiUtente user = (DatiUtente) request.getSession().getAttribute(
				"DatiUtente");

		if (user != null) {
			if (auCalcolaIndicatoriService.isSSsessionOpen(user
					.getIdSSessione())) {
				getModel().setStatus(1);
				getModel()
						.setMessage(
								"Non è possibile effettuare l'operazione perchè la sessione è chiusa!");
				return SUCCESS;
			}

			try {
				AppUser appUser = (AppUser) request.getSession().getAttribute(
						"AppUser");
				int rowUpdate = auCalcolaIndicatoriService
						.setPraticaInLavorazione(user.getIdPraticaAtpo(),
								appUser.getUsername());
				if (rowUpdate == 1) {
					getModel().setStatus(0);
					getModel().setMessage("In Lavorazione pratica eseguita!");
					user.setStatoEsamePratica("L");
				} else {
					getModel().setStatus(1);
					getModel()
							.setMessage(
									"Non è possibile lavorare la pratica perchè in uso da un'altro utente!");
				}
			} catch (Exception e) {
				getModel().setStatus(1);
				getModel().setMessage("Non è possibile lavorare la pratica!");
			}
		} else {
			getModel().setStatus(1);
			getModel().setMessage("Non è possibile lavorare la pratica!");
			// user.setStatoEsamePratica("A");
		}
		return SUCCESS;
	}

	public String riapriPratica() {
		HttpServletRequest request = ServletActionContext.getRequest();

		DatiUtente user = (DatiUtente) request.getSession().getAttribute(
				"DatiUtente");

		if (user != null) {
			if (auCalcolaIndicatoriService.isSSsessionOpen(user
					.getIdSSessione())) {
				getModel().setStatus(1);
				getModel()
						.setMessage(
								"Non è possibile effettuare l'operazione perchè la sessione è chiusa!");
				return SUCCESS;
			}

			try {
				AppUser appUser = (AppUser) request.getSession().getAttribute(
						"AppUser");
				int rowUpdate = auCalcolaIndicatoriService.setEsamePratica(user
						.getIdPraticaAtpo(), PraticaUtil.ESAME_PRATICA_APERTO,
						appUser.getUsername(),null);
				if (rowUpdate == 1) {
					getModel().setStatus(0);
					getModel().setMessage("Riapertura pratica eseguita!");
					user.setStatoEsamePratica("A");
				} else {
					getModel().setStatus(1);
					getModel()
							.setMessage(
									"Non è possibile riaprire la pratica perchè in uso da un'altro utente!");
				}
			} catch (Exception e) {
				getModel().setStatus(1);
				getModel().setMessage("Riapertura pratica non eseguita!");
			}
		} else {
			getModel().setStatus(1);
			getModel().setMessage("Riapertura pratica non eseguita!");
		}
		return SUCCESS;
	}

	public String riapriSessione() {
		HttpServletRequest request = ServletActionContext.getRequest();
		DatiUtente user = (DatiUtente) request.getSession().getAttribute(
				"DatiUtente");

		auCalcolaIndicatoriService.riapriSessione(user.getIdSSessione());
		return SUCCESS;
	}

}
