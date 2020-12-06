package it.tecnet.crs.web.action;

import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoFaseAcquisizioneIstanza;
import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoFaseAutotutelaResistenzaGiudizio;
import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoFaseEsecuzioneProvvedimenti;
import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoFaseGestioneIstruttoria;
import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoFasePeritale;
import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoFasePostPeritale;
import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoRiepilogoFascicolo;
import it.tecnet.crs.audit.web.dto.AuAuditDto;
import it.tecnet.crs.indicatori.CalcoloIndicatori;
import it.tecnet.crs.jpa.model.AuMediaFase;
import it.tecnet.crs.jpa.model.AuNonConformita;
import it.tecnet.crs.jpa.model.AuNonConformitaVerbale;
import it.tecnet.crs.jpa.model.AuQuestionario;
import it.tecnet.crs.jpa.model.AuSessioni;
import it.tecnet.crs.service.AuditNonConformitaService;
import it.tecnet.crs.service.AuditQuestionarioService;
import it.tecnet.crs.service.AuditService;
import it.tecnet.crs.session.DatiUtente;
import it.tecnet.crs.util.BuildChartFasi;
import it.tecnet.crs.web.beans.Accesso;
import it.tecnet.crs.web.dto.NonConformitaVerbaleDto;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ModelDriven;

public class AuditorsAction extends BaseAction implements ModelDriven<Accesso> {

	private static final long serialVersionUID = -2879655035580628498L;
	private Accesso accesso = new Accesso();
	private AuditService auditService;
	private AuditQuestionarioService auditQuestionarioService;
	private AuditNonConformitaService auditNonConformitaService;

	// costruttore
	public AuditorsAction(AuditQuestionarioService auditService) {
		super();
		this.auditQuestionarioService = auditService;

	}

	public String getAccessi() {

		return SUCCESS;
	}
	public String getReports() {


		return SUCCESS;
	}

	public String getDetailGrafFase(){
		HttpServletRequest request = ServletActionContext.getRequest();
		DatiUtente user = (DatiUtente) request.getSession().getAttribute("DatiUtente");
		String nameGrafico= getModel().getTitleGrafico();
		user.setNomeGrafico(nameGrafico);
		return SUCCESS;
	}

	public String initReportsfasi(){
		HttpServletRequest request = ServletActionContext.getRequest();
		DatiUtente user = (DatiUtente) request.getSession().getAttribute("DatiUtente");

		String nameGrafico= user.getNomeGrafico();
		if(!nameGrafico.trim().equals("")){
			List<Object> list = auditService.getListFase(nameGrafico);
			String charts=null;
			String chartTwo=null;
			String chartThree=null;
			String chartFour=null;
			if(list != null ){
				if(list.get(0) instanceof AtpoFaseAcquisizioneIstanza){
					charts= BuildChartFasi.buildChartAcquisizioneIstanza(list);
					
				}else if(list.get(0) instanceof AtpoFaseAutotutelaResistenzaGiudizio){
					charts=BuildChartFasi.buildChartAutotutelaResGiudizio(list);
					
				}else if(list.get(0) instanceof AtpoFaseGestioneIstruttoria){
					charts=BuildChartFasi.buildChartGestioneIstruttoria(list);
					
				}else if(list.get(0) instanceof AtpoFasePeritale){
					charts=BuildChartFasi.buildChartPeritale(list);

				}else if(list.get(0) instanceof AtpoFasePostPeritale){
					if(user.getPostPeritaleType().equalsIgnoreCase("a")){
						charts=BuildChartFasi.buildChartPostPeritale(list);
					}else{
						charts=BuildChartFasi.buildChartPostPeritaleB(list);
					}

				}else if(list.get(0) instanceof AtpoFaseEsecuzioneProvvedimenti){
					if(user.getEsProvvedimentiType().equalsIgnoreCase("a")){
						getModel().setThisFase(1);
						charts=BuildChartFasi.buildChartEsecuzioneProvvedimenti(list);
						chartTwo=BuildChartFasi.buildChartEsecuzioneProvvedimentiTwo(list);
						getModel().setToReturnTwo(chartTwo);
						chartThree=BuildChartFasi.buildChartEsecuzioneProvvedimentiPrecetto(list);
						getModel().setToReturnThree(chartThree);
						chartFour=BuildChartFasi.buildChartEsecuzioneProvvedimentiPignoramento(list);
						getModel().setToReturnFour(chartFour);
					}else{
						charts=BuildChartFasi.buildChartEsecuzioneProvvedimentiDissenso(list);
					}
					
				}else if(list.get(0) instanceof AtpoRiepilogoFascicolo){
					charts=BuildChartFasi.buildChartRiepilogoFascicolo(list);
				}

			}
			getModel().setToReturn(charts);
		}
		

		return SUCCESS;
	}





	/*
	 * 1- Salva la risposta della domanda del questionario 2- Salva il punteggio
	 * del questionario 3- Scarica i dati aggiornati da mostrare in tabella
	 * Indicatori/Dati Generali
	 */



	public String salvaRispostaQuestionario() {
		int idSessione = getModel().getIdSessione();
		auditQuestionarioService.salvaRispostaQuestionariSessione(idSessione,
				getModel().getIdRisposta(), getModel().getIdDomanda());

		//	salvaPunteggioQuestionario(idSessione);

		List<AuMediaFase> mfList = auditNonConformitaService
		.getIndicatoriDatiGenerali(idSessione);

		if (mfList.size() != 0) {
			getModel().setMediaFase(mfList.get(0));
		}

		return SUCCESS;
	}

	@Override
	public Accesso getModel() {
		// TODO Auto-generated method stub
		return accesso;
	}

	public Accesso getAccesso() {
		return accesso;
	}

	public void setAccesso(Accesso accesso) {
		this.accesso = accesso;
	}

	public AuditQuestionarioService getAuditQuestionarioService() {
		return auditQuestionarioService;
	}

	public void setAuditQuestionarioService(
			AuditQuestionarioService auditQuestionarioService) {
		this.auditQuestionarioService = auditQuestionarioService;
	}

	public AuditService getAuditService() {
		return auditService;
	}

	public void setAuditService(AuditService auditService) {
		this.auditService = auditService;
	}

	public void setNonConformitaService(
			AuditNonConformitaService nonConformitaService) {
		this.auditNonConformitaService = nonConformitaService;
	}

	public AuditNonConformitaService getNonConformitaService() {
		return auditNonConformitaService;
	}



}
