package it.tecnet.crs.ATPO.auditors.web.action;

import it.tecnet.crs.ATPO.auditors.service.AuditorsAccessiATPOService;
import it.tecnet.crs.ATPO.auditors.web.beans.ModelAccessiReport;
import it.tecnet.crs.ATPO.auditors.web.dto.AuSMEsprRischioDTO;
import it.tecnet.crs.jpa.model.AuMNonConf;
import it.tecnet.crs.jpa.model.AuMRischio;
import it.tecnet.crs.jpa.model.AuSRisespr;
import it.tecnet.crs.jpa.model.AuSVarcomp;
import it.tecnet.crs.session.DatiUtente;
import it.tecnet.crs.web.action.BaseAction;
import it.tecnet.crs.web.dto.AuMVarCompDto;
import it.tecnet.crs.web.dto.AuSSessioneDto;
import it.tecnet.crs.web.dto.NonConformitaAccessiDto;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ModelDriven;

public class PianoMiglioramentoAction extends BaseAction implements
		ModelDriven<ModelAccessiReport> {

	private static final long serialVersionUID = 1L;
	private ModelAccessiReport model = new ModelAccessiReport();
	private AuditorsAccessiATPOService serviceAccessiATPO;

	public PianoMiglioramentoAction(
			AuditorsAccessiATPOService serviceAccessiATPO) {
		super();
		this.serviceAccessiATPO = serviceAccessiATPO;
	}

	// tab non conformita in report/piano miglioramento
	public String getTabReportPianoMiglioramento() {
		HttpServletRequest request = ServletActionContext.getRequest();
		DatiUtente user = (DatiUtente) request.getSession().getAttribute(
				"DatiUtente");
		if (user != null) {
			long idSessione = user.getIdSessione();
			Long idAudit = null;
			AuSSessioneDto ss = serviceAccessiATPO.getSSessione(idSessione);
			// se la data aggiornamento dati sessione != null mostro la tabella
			// altrimenti nulla
			if (ss != null && ss.getDataAggiornamentoDatiSessione() != null) {
				getModel().setDataAggiornamentoDatiSessione(
						ss.getDataAggiornamentoDatiSessione());
				try {
					idAudit = user.getIdAudit();
					List<AuMNonConf> nonConformita = serviceAccessiATPO
							.getNonConformitaAccessiAtpo(idAudit);
					if (nonConformita != null) {
						for (AuMNonConf nc : nonConformita) {
							// non conformita da mostrare nel dropDown
							getModel().getNonConformita().add(nc);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				getModel().setDataAggiornamentoDatiSessione(null);

			}

		}

		return SUCCESS;
	}

	// IN BASE DALLA NON CONFORMITA SELEZIONATA CARICA INCC: Valore/Pratica non
	// soggetta DA S-NonConf
	public String getTableVariantiComportamentali() {
		HttpServletRequest request = ServletActionContext.getRequest();
		DatiUtente user = (DatiUtente) request.getSession().getAttribute(
				"DatiUtente");
		if (user != null) {
			long idMNonConf = getModel().getIdMNonConf();
			user.setIdMNonConf(idMNonConf);
			long idSSessione = user.getIdSSessione();
			try {
				NonConformitaAccessiDto snc = serviceAccessiATPO
						.getSNConfByIdMNonConf(idMNonConf, idSSessione);
				if (snc != null) {
					getModel().setAuSNonConf(snc);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return SUCCESS;

	}

	// TABELLA VARIANTI COMPORTAMENTALI
	public String searchVComp() {

		HttpServletRequest request = ServletActionContext.getRequest();

		DatiUtente user = (DatiUtente) request.getSession().getAttribute(
				"DatiUtente");
		if (user != null) {

			long idMNonConf = user.getIdMNonConf();
			long idSSessione = user.getIdSSessione();
			List<AuMVarCompDto> l = serviceAccessiATPO.getVarComp(idMNonConf,
					idSSessione, getModel().getiDisplayStart(), getModel()
							.getiDisplayLength(), getModel().getiSortCol_0(),
					getModel().getsSortDir_0(), getModel().getsSearch());

			int total = serviceAccessiATPO.countVarComp(idMNonConf,
					idSSessione, getModel().getiDisplayStart(), getModel()
							.getiDisplayLength(), getModel().getiSortCol_0(),
					getModel().getsSortDir_0(), getModel().getsSearch());

			getModel().setiTotalDisplayRecords(total);
			getModel().setiTotalRecords(total);

			if (l != null) {
				for (Object s : l) {
					getModel().getAaData().add(s);
				}
			}
		}
		return SUCCESS;

	}

	/*
	 * TAB RISCHI
	 */

	public String getTabRischiPianoMiglioramento() {
		HttpServletRequest request = ServletActionContext.getRequest();

		DatiUtente user = (DatiUtente) request.getSession().getAttribute(
				"DatiUtente");
		if (user != null) {

			Long idAudit = user.getIdAudit();
			try {

				List<AuMRischio> rischi = serviceAccessiATPO
						.getTabRischiPM(idAudit);
				if (rischi != null) {
					for (AuMRischio r : rischi) {
						// rischi nel dropDown
						getModel().getRischi().add(r);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return SUCCESS;
	}

	// IN BASE AL RISCHIO SELEZIONATO CARICA Impatto economico complessivo euro
	public String getTableEsprRischioPM() {
		HttpServletRequest request = ServletActionContext.getRequest();
		DatiUtente user = (DatiUtente) request.getSession().getAttribute(
				"DatiUtente");
		if (user != null) {
			// long idSSessione = user.getIdSSessione();
			long idRischio = getModel().getIdRischio();
			user.setIdRischioReportPianoMiglioramento(idRischio);
		}

		return SUCCESS;
	}

	// TABELLA ESPR RISCHIO
	public String searchEsprRischiTablePM() {

		HttpServletRequest request = ServletActionContext.getRequest();

		DatiUtente user = (DatiUtente) request.getSession().getAttribute(
				"DatiUtente");
		if (user != null) {

			long idRischio = user.getIdRischioReportPianoMiglioramento();
			long idSSessione = user.getIdSSessione();
			List<AuSMEsprRischioDTO> l = serviceAccessiATPO
					.searchEsprRischiTablePM(idRischio, idSSessione, getModel()
							.getiDisplayStart(),
							getModel().getiDisplayLength(), getModel()
									.getiSortCol_0(), getModel()
									.getsSortDir_0(), getModel().getsSearch());

			int total = serviceAccessiATPO.countEsprRischiTablePM(idRischio,
					idSSessione, getModel().getiDisplayStart(), getModel()
							.getiDisplayLength(), getModel().getiSortCol_0(),
					getModel().getsSortDir_0(), getModel().getsSearch());

			getModel().setiTotalDisplayRecords(total);
			getModel().setiTotalRecords(total);

			if (l != null) {
				for (Object s : l) {
					getModel().getAaData().add(s);
				}
			}
		}
		return SUCCESS;

	}

	// salva campi colonne tabella var comportamentali
	public String saveVarCompInputTextTable() {
		HttpServletRequest request = ServletActionContext.getRequest();

		DatiUtente user = (DatiUtente) request.getSession().getAttribute(
				"DatiUtente");
		if (user != null) {
			try {
				String criticita = getModel().getCriticitaVarComp();
				String azioniCorrettive = getModel().getAzioniCorrettVarComp();
				long idSVarComp = getModel().getIdSVarComp();

				AuSVarcomp sv = serviceAccessiATPO.cerca(AuSVarcomp.class,
						idSVarComp);
				if (sv != null) {
					sv.setCriticita(criticita);
					sv.setAzioniCorrettive(azioniCorrettive);
					serviceAccessiATPO.salva(sv);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return SUCCESS;
	}

	// salva campi colonne tabella espr rischio
	public String saveEsprRischioInputTextTable() {
		HttpServletRequest request = ServletActionContext.getRequest();

		DatiUtente user = (DatiUtente) request.getSession().getAttribute(
				"DatiUtente");
		if (user != null) {
			try {
				String motivazioni = getModel().getMotivazioni();
				String azioniCorrett = getModel().getAzioniCorrett();
				long idSEsprRischio = getModel().getIdSEsprRischio();

				AuSRisespr rs = serviceAccessiATPO.cerca(AuSRisespr.class,
						idSEsprRischio);
				if (rs != null) {
					rs.setPossMotivRischio(motivazioni);
					rs.setAzioniCorrettive(azioniCorrett);
					serviceAccessiATPO.salva(rs);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return SUCCESS;
	}

	public void setServiceAccessiATPO(
			AuditorsAccessiATPOService serviceAccessiATPO) {
		this.serviceAccessiATPO = serviceAccessiATPO;
	}

	public AuditorsAccessiATPOService getServiceAccessiATPO() {
		return serviceAccessiATPO;
	}

	@Override
	public ModelAccessiReport getModel() {
		return model;
	}

}
