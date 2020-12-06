package it.tecnet.crs.mod.web.action;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ModelDriven;
import it.tecnet.crs.componenti.jpa.model.CrsAssDominiDocumenti;
import it.tecnet.crs.componenti.jpa.model.CrsCircolariInps;
import it.tecnet.crs.componenti.jpa.model.CrsComponenteTecnico;
import it.tecnet.crs.componenti.jpa.model.CrsDescrizioneTipo;
import it.tecnet.crs.componenti.jpa.model.CrsDomini;
import it.tecnet.crs.componenti.jpa.model.CrsLeggiDecreti;
import it.tecnet.crs.componenti.jpa.model.CrsMessaggiInps;
import it.tecnet.crs.componenti.jpa.model.CrsNoteDecreti;
import it.tecnet.crs.componenti.jpa.model.CrsTplCompTecnico;
import it.tecnet.crs.componenti.jpa.model.CrsTplEnteEmittente;
import it.tecnet.crs.componenti.jpa.model.CrsTplTipoLegge;
import it.tecnet.crs.componenti.service.GestioneComponentiService;
import it.tecnet.crs.componenti.web.bean.DatiTipoNormativa;
import it.tecnet.crs.componenti.web.bean.TipoNormativa;
import it.tecnet.crs.jpa.model.CrsAssProcessoClasse;
import it.tecnet.crs.mod.jpa.model.CrsAssAttivitaComponenteCompTec;
import it.tecnet.crs.mod.jpa.model.CrsAssAttivitaComponenteDomini;
import it.tecnet.crs.mod.jpa.model.CrsAssAttivitaDettaglioCompTec;
import it.tecnet.crs.mod.jpa.model.CrsAssAttivitaDettaglioDomini;
import it.tecnet.crs.mod.jpa.model.CrsAssProcessoCompTec;
import it.tecnet.crs.mod.jpa.model.CrsAssProcessoDomini;
import it.tecnet.crs.mod.jpa.model.CrsAssSottoProcessoCompTec;
import it.tecnet.crs.mod.jpa.model.CrsAssSottoProcessoDomini;
import it.tecnet.crs.mod.service.ModellazioneService;
import it.tecnet.crs.mod.web.bean.Area;
import it.tecnet.crs.mod.web.bean.AttivitaComponente;
import it.tecnet.crs.mod.web.bean.AttivitaDettaglio;
import it.tecnet.crs.mod.web.bean.Modellazione;
import it.tecnet.crs.mod.web.bean.Processo;
import it.tecnet.crs.mod.web.bean.SottoProcesso;
import it.tecnet.crs.mod.web.dto.ProcessoDto;
import it.tecnet.crs.session.DatiUtente;
import it.tecnet.crs.web.action.BaseAction;
import it.tecnet.crs.web.beans.AppUser;
import it.tecnet.crs.web.beans.AssAcClasse;
import it.tecnet.crs.web.beans.AssAdClasse;
import it.tecnet.crs.web.beans.AssProcessoClasse;
import it.tecnet.crs.web.beans.AssSottoProcessoClasse;

public class ModellazioneAction extends BaseAction implements ModelDriven<Modellazione> {

	private static final long serialVersionUID = -6342460923585548568L;

	private ModellazioneService modellazioneService;
	private Modellazione modellazione = new Modellazione();

	private GestioneComponentiService gestioneComponentiService;



	public ModellazioneAction(ModellazioneService modellazioneService, GestioneComponentiService gestioneComponentiService) {
		super();
		this.modellazioneService = modellazioneService;
		this.gestioneComponentiService = gestioneComponentiService;
	}


	/**
	 ***********************************************
	 *************	SEZIONE AREA	****************
	 ***********************************************	
	 */

	public String getAreaList() {

		return SUCCESS;
	}


	// salva/modifica una nuova area
	public String salvaArea() {

		Area area = new Area();
		boolean check;

		area.setIdArea(getModel().getIdArea());
		area.setDescrizione(getModel().getDescrizione());
		area.setDataInizio(getModel().getDataInizio());
		area.setDataFine(getModel().getDataFine());
		area.setStato(getModel().getStato());

		// In modifica area l'eventuale dataFine validità deve essere
		// propagata a tutte le entità figlie (processi-fasi-attivita).
		if(getModel().getIdArea() != 0){

			if(getModel().getDataFine() != null){
				check = modellazioneService.chiudiValiditaArea(area);

				getModel().setErrorChiusuraValiditaArea(check);
			}
		}

		modellazioneService.saveArea(area);

		return SUCCESS;
	}

	// elimina un' area
	public String eliminaArea() {

		modellazioneService.deleteArea(getModel().getIdArea());

		return SUCCESS;
	}

	// attiva lo stato di un' area
	public String attivaArea() {

		modellazioneService.enableArea(getModel().getIdArea());

		return SUCCESS;
	}


	/**
	 ***************************************************
	 *************	SEZIONE PROCESSO	****************
	 ***************************************************	
	 */


	public String getProcessiList() {

		// eseguo la chiamata per popolare la combo Area della a-side
		// di modifica processo.
		List<Area> areaList = modellazioneService.getComboArea();
		getModel().setAreaList(areaList);

		return SUCCESS;
	}


	// salva/modifica un nuovo processo
	public String salvaProcesso() {

		boolean check = true;
		Processo processo = new Processo();
		Area areaPadre = new Area();

		processo = getModel().getProcesso();

		//Controllo che le date inizio/fine del figlio siano congruenti
		// con le date del padre.
		// 1. Recupero le date del padre
		areaPadre = modellazioneService.getAreaById(getModel().getProcesso().getArea().getIdArea());

		// 2. Confronto che dataInizio figlio non sia antecedente a dataInizio padre oppure
		// dataFine figlio non sia successiva a dataFine padre.
		if(areaPadre.getDataFine() != null && processo.getDataFine() != null){

			if(processo.getDataInizio().before(areaPadre.getDataInizio()) ||
					processo.getDataFine().after(areaPadre.getDataFine())){

				getModel().setError(true);
				check = false;
			}

		}


		// controllo che il campo Ordinamento non sia gia presente in archivio.
		if(modellazioneService.checkFieldOrdinamento(getModel().getProcesso().getIdProcesso(), getModel().getProcesso().getOrdinamento(), "CRS_PROCESSO")){
			getModel().setErrorOrdinamento(true);
			check = false;
		}

		if(check){
			modellazioneService.saveProcesso(processo);
		}	



		return SUCCESS;
	}

	// elimina un processo
	public String eliminaProcesso() {

		modellazioneService.deleteProcesso(getModel().getIdProcesso());

		return SUCCESS;
	}

	// gestisce la pubblicazione di un processo
	public String pubblicaProcesso() {

		if(StringUtils.equalsIgnoreCase(getModel().getPubblicazione(), "S")){
			modellazioneService.pubblicaProcesso(getModel().getIdProcesso());
		}
		//		else{
		//			modellazioneService.disableProcesso(getModel().getIdProcesso());
		//		}


		return SUCCESS;
	}


	public String getProcessoById() {

		// eseguo la chiamata per recuperare il processo da modificare.
		Processo processo = modellazioneService.getProcessoById(getModel().getIdProcesso());

		getModel().setProcesso(processo);

		// servizio che popola la comboBox Area.
		List<Area> areaList = modellazioneService.getComboArea();
		getModel().setAreaList(areaList);


		// eseguo la chiamata per popolare la combo Tipo Normativa del 
		// Tab Normativa
		//		List<TipoNormativa> comboList = gestioneComponentiService.getComboTipoNormativa(getModel().getIdClasse());
		//		getModel().setTipoNormativaList(comboList);
		//		
		//		List<CrsDescrizioneTipo> labels= gestioneComponentiService.getListaNormativaLabel(getModel().getIdTipo());
		//		getModel().getLabelList().add(labels.get(0));


		return SUCCESS;
	}
	public String getCircolariInpsAssociateAsideList(){


		return SUCCESS;

	}

	public String getNoteDecretiAssociateAsideList(){


		return SUCCESS;

	}
	public String getMessaggiInpsAssociateAsideList(){


		return SUCCESS;

	}
	public String getLeggiDecretiAssociateAsideList(){


		return SUCCESS;

	}

	// associa una normativa al processo selezionato
	public String associaProcessoNormativa() {

		AssProcessoClasse associazione = new AssProcessoClasse();

		associazione = getModel().getAssProcessoClasse();

		modellazioneService.associaProcessoNormativa(associazione);

		return SUCCESS;
	}

	// rimuove l'associazione di una normativa al processo selezionato
	public String rimuoviAssociazioneProcessoNormativa() {

		AssProcessoClasse associazione = new AssProcessoClasse();

		associazione = getModel().getAssProcessoClasse();

		// recupero la chiave primaria della tabella sulla quale fare la remove.
		long pk = modellazioneService.getAssProcessoClassePK(getModel().getAssProcessoClasse());

		modellazioneService.rimuoviAssociazioneProcessoNormativa(pk);

		return SUCCESS;
	}


	/**
	 ***********************************************************
	 *************	SEZIONE SOTTOPROCESSO/FASE	****************
	 ***********************************************************	
	 */

	public String getSottoProcessiList() {

		// eseguo la chiamata per popolare la combo Area della a-side
		// di modifica/inserimento fase.
		List<Area> areaList = modellazioneService.getComboArea();
		getModel().setAreaList(areaList);

		return SUCCESS;
	}


	// salva/modifica una nuova fase/sottoprocesso
	public String salvaSottoProcesso() {

		boolean check = true;
		SottoProcesso sottoProcesso = new SottoProcesso();
		Processo processoPadre = new Processo();

		sottoProcesso = getModel().getSottoProcesso();

		//Controllo che le date inizio/fine del figlio siano congruenti
		// con le date del padre.
		// 1. Recupero le date del padre
		processoPadre = modellazioneService.getProcessoById(getModel().getSottoProcesso().getProcesso().getIdProcesso());

		// 2. Confronto che dataInizio figlio non sia antecedente a dataInizio padre oppure
		// dataFine figlio non sia successiva a dataFine padre.
		if(processoPadre.getDataFine() != null && sottoProcesso.getDataFine() != null){

			if(sottoProcesso.getDataInizio().before(processoPadre.getDataInizio()) ||
					sottoProcesso.getDataFine().after(processoPadre.getDataFine())){

				getModel().setError(true);
				check = false;
			}

		}


		// controllo che il campo Ordinamento non sia gia presente in archivio.
		if(modellazioneService.checkFieldOrdinamento(getModel().getSottoProcesso().getIdSottoProcesso(), getModel().getSottoProcesso().getOrdinamento(), "CRS_SOTTOPROCESSO")){
			getModel().setErrorOrdinamento(true);
			check = false;
		}

		if(check){
			modellazioneService.saveSottoProcesso(sottoProcesso);
		}	

		return SUCCESS;
	}

	// gestisce il cambia stato attiva/disattiva di un sotto processo
	public String gestisciSottoProcesso() {

		if(StringUtils.equalsIgnoreCase(getModel().getSottoProcesso().getStato(), "Attiva")){
			modellazioneService.enableSottoprocesso(getModel().getSottoProcesso().getIdSottoProcesso());
		}else{
			modellazioneService.disableSottoprocesso(getModel().getSottoProcesso().getIdSottoProcesso());
		}


		return SUCCESS;
	}

	// elimina un sotto processo
	public String eliminaSottoProcesso() {

		modellazioneService.deleteSottoProcesso(getModel().getSottoProcesso().getIdSottoProcesso());

		return SUCCESS;
	}


	public String getSottoProcessoById() {

		// eseguo la chiamata per recuperare il sottoProcesso da modificare.
		SottoProcesso sottoProcesso = modellazioneService.getSottoProcessoById(getModel().getSottoProcesso().getIdSottoProcesso());

		getModel().setSottoProcesso(sottoProcesso);

		// partendo dall' Id sottoprocesso recupero l' ID area
		long idArea = modellazioneService.getIdAreaByIdSottoProcesso(getModel().getSottoProcesso().getIdSottoProcesso());


		// servizio che popola la comboBox Processo.
		List<Processo> processoList = modellazioneService.getComboProcessoByIdArea(idArea);
		getModel().setProcessoList(processoList);

		// eseguo la chiamata per popolare la combo Tipo Normativa del 
		// Tab Normativa
		List<TipoNormativa> comboList = gestioneComponentiService.getComboTipoNormativa(getModel().getIdClasse());
		getModel().setTipoNormativaList(comboList);

		List<CrsDescrizioneTipo> labels= gestioneComponentiService.getListaNormativaLabel(getModel().getIdTipo());
		getModel().getLabelList().add(labels.get(0));

		return SUCCESS;
	}


	// associa una normativa al sottoprocesso selezionato
	public String associaSottoProcessoNormativa() {

		AssSottoProcessoClasse associazione = new AssSottoProcessoClasse();

		associazione = getModel().getAssSottoProcessoClasse();

		modellazioneService.associaSottoProcessoNormativa(associazione);

		return SUCCESS;
	}

	// rimuove l'associazione di una normativa al sottoprocesso selezionato
	public String rimuoviAssociazioneSottoProcessoNormativa() {

		AssSottoProcessoClasse associazione = new AssSottoProcessoClasse();

		associazione = getModel().getAssSottoProcessoClasse();

		// recupero la chiave primaria della tabella sulla quale fare la remove.
		long pk = modellazioneService.getAssSottoProcessoClassePK(getModel().getAssSottoProcessoClasse());

		modellazioneService.rimuoviAssociazioneSottoProcessoNormativa(pk);

		return SUCCESS;
	}


	public String getComboProcessoByIdArea() {

		// eseguo la chiamata per popolare la combo Processo della a-side
		// di inserimento/modifica fase.
		List<Processo> processoList = modellazioneService.getComboProcessoByIdArea(getModel().getIdArea());
		getModel().setProcessoList(processoList);

		return SUCCESS;
	}


	public String getDocumentiMediaSottoProcesso(){

		return SUCCESS;
	}

	public String getDominiSottoProcesso(){

		return SUCCESS;
	}

	public String getCompTecniciSottoProcesso(){

		return SUCCESS;
	}



	/**
	 ***********************************************************
	 *************	SEZIONE ATTIVITA COMPONENTE	****************
	 ***********************************************************	
	 */

	public String getAttivitaComponenteList() {

		// eseguo la chiamata per popolare la combo Area della a-side
		// di inserimento/modifica attivita.
		List<Area> areaList = modellazioneService.getComboArea();
		getModel().setAreaList(areaList);


		return SUCCESS;
	}


	// salva/modifica una nuova attivita componente
	public String salvaAttivitaComponente() {

		boolean check = true;
		AttivitaComponente attivitaComponente = new AttivitaComponente();
		SottoProcesso sottoProcessoPadre = new SottoProcesso();

		attivitaComponente = getModel().getAttivitaComponente();

		//Controllo che le date inizio/fine del figlio siano congruenti
		// con le date del padre.
		// 1. Recupero le date del padre
		sottoProcessoPadre = modellazioneService.getSottoProcessoById(getModel().getAttivitaComponente().getSottoProcesso().getIdSottoProcesso());

		// 2. Confronto che dataInizio figlio non sia antecedente a dataInizio padre oppure
		// dataFine figlio non sia successiva a dataFine padre.
		if(sottoProcessoPadre.getDataFine() != null && attivitaComponente.getDataFine() != null){

			if(attivitaComponente.getDataInizio().before(sottoProcessoPadre.getDataInizio()) ||
					attivitaComponente.getDataFine().after(sottoProcessoPadre.getDataFine())){

				getModel().setError(true);
				check = false;
			}

		}

		// controllo che il campo Ordinamento non sia gia presente in archivio.
		if(modellazioneService.checkFieldOrdinamento(getModel().getAttivitaComponente().getIdAttivitaComponente(), getModel().getAttivitaComponente().getOrdinamento(), "CRS_ATTIVITA_COMPONENTE")){
			getModel().setErrorOrdinamento(true);
			check = false;
		}

		if(check){

			modellazioneService.saveAttivitaComponente(attivitaComponente);
		}

		return SUCCESS;
	}


	// elimina un' attivita componente
	public String eliminaAttivitaComponente() {

		modellazioneService.deleteAttivitaComponente(getModel().getAttivitaComponente().getIdAttivitaComponente());

		return SUCCESS;
	}

	// gestisce il cambia stato attiva/disattiva di un' attivita componente
	public String gestisciAttivitaComponente() {

		if(StringUtils.equalsIgnoreCase(getModel().getAttivitaComponente().getStato(), "Attiva")){
			modellazioneService.enableAttivitaComponente(getModel().getAttivitaComponente().getIdAttivitaComponente());
		}else{
			modellazioneService.disableAttivitaComponente(getModel().getAttivitaComponente().getIdAttivitaComponente());
		}


		return SUCCESS;
	}


	public String getAttivitaComponenteById() {
		HttpServletRequest request = ServletActionContext.getRequest();
		DatiUtente user= (DatiUtente)request.getSession().getAttribute("DatiUtente");


		// eseguo la chiamata per recuperare l'attivita componente da modificare.
		AttivitaComponente attivitaComponente = modellazioneService.getAttivitaComponenteById(getModel().getAttivitaComponente().getIdAttivitaComponente());

		user.setIdAttivitaComponente(getModel().getAttivitaComponente().getIdAttivitaComponente());

		getModel().setAttivitaComponente(attivitaComponente);

		// partendo dall' Id attivita componente recupero l'Id area
		long idArea = modellazioneService.getIdAreaByIdAttivitaComponente(getModel().getAttivitaComponente().getIdAttivitaComponente());
		//List<Long> listaID = modellazioneService.getIdPadriByIdAttivitaComponente(attivitaComponente.getIdAttivitaComponente());

		// carico comboBox SottoProcesso.
		List<SottoProcesso> sottoProcessoList = modellazioneService.getComboSottoProcessoByIdArea(idArea);
		getModel().setSottoProcessoList(sottoProcessoList);

		// eseguo la chiamata per popolare la combo Tipo Normativa del 
		// Tab Normativa
		List<TipoNormativa> comboList = gestioneComponentiService.getComboTipoNormativa(getModel().getIdClasse());
		getModel().setTipoNormativaList(comboList);

		List<CrsDescrizioneTipo> labels= gestioneComponentiService.getListaNormativaLabel(getModel().getIdTipo());
		getModel().getLabelList().add(labels.get(0));

		return SUCCESS;
	}



	// associa una normativa all' attivita componente selezionata
	public String associaAttivitaComponenteNormativa() {

		AssAcClasse associazione = new AssAcClasse();

		associazione = getModel().getAssAcClasse();

		modellazioneService.associaAttivitaComponenteNormativa(associazione);

		return SUCCESS;
	}

	// rimuove l'associazione di una normativa all' attivita componente selezionata
	public String rimuoviAssociazioneAttivitaComponenteNormativa() {

		AssAcClasse associazione = new AssAcClasse();

		associazione = getModel().getAssAcClasse();

		// recupero la chiave primaria della tabella sulla quale fare la remove.
		long pk = modellazioneService.getAssAttivitaComponenteClassePK(getModel().getAssAcClasse());

		modellazioneService.rimuoviAssociazioneAttivitaComponenteNormativa(pk);

		return SUCCESS;
	}


	public String getComboSottoProcessoByIdProcesso() {

		// eseguo la chiamata per popolare la combo Fase/SottoProcesso della a-side
		// di inserimento/modifica attivita.
		List<SottoProcesso> returnList = modellazioneService.getComboSottoProcessoByIdProcesso(getModel().getProcesso().getIdProcesso());
		getModel().setSottoProcessoList(returnList);

		return SUCCESS;
	}


	/**
	 ***********************************************************
	 *************	SEZIONE ATTIVITA DETTAGLIO	****************
	 ***********************************************************	
	 */

	public String getAttivitaDettaglioList() {


		// eseguo la chiamata per popolare la combo Area della a-side
		// di inserimento/modifica attivita.
		List<Area> areaList = modellazioneService.getComboArea();
		getModel().setAreaList(areaList);


		return SUCCESS;
	}


	// salva/modifica una nuova attivita dettaglio
	public String salvaAttivitaDettaglio() {

		boolean check = true;
		AttivitaDettaglio attivitaDettaglio = new AttivitaDettaglio();
		AttivitaComponente attivitaPadre = new AttivitaComponente();

		attivitaDettaglio = getModel().getAttivitaDettaglio();

		//Controllo che le date inizio/fine del figlio siano congruenti
		// con le date del padre.
		// 1. Recupero le date del padre
		attivitaPadre = modellazioneService.getAttivitaComponenteById(getModel().getAttivitaDettaglio().getAttivitaComponente().getIdAttivitaComponente());

		// 2. Confronto che dataInizio figlio non sia antecedente a dataInizio padre oppure
		// dataFine figlio non sia successiva a dataFine padre.
		if(attivitaPadre.getDataFine() != null && attivitaDettaglio.getDataFine() != null){

			if(attivitaDettaglio.getDataInizio().before(attivitaPadre.getDataInizio()) ||
					attivitaDettaglio.getDataFine().after(attivitaPadre.getDataFine())){

				getModel().setError(true);
				check = false;
			}

		}

		// controllo che il campo Ordinamento non sia gia presente in archivio.
		if(modellazioneService.checkFieldOrdinamento(getModel().getAttivitaDettaglio().getIdAttivitaDettaglio(), getModel().getAttivitaDettaglio().getOrdinamento(), "CRS_ATTIVITA_DETTAGLIO")){
			getModel().setErrorOrdinamento(true);
			check = false;
		}

		if(check){

			modellazioneService.saveAttivitaDettaglio(attivitaDettaglio);
		}

		return SUCCESS;
	}


	// elimina un' attivita dettaglio
	public String eliminaAttivitaDettaglio() {

		modellazioneService.deleteAttivitaDettaglio(getModel().getAttivitaDettaglio().getIdAttivitaDettaglio());

		return SUCCESS;
	}

	// gestisce il cambia stato attiva/disattiva di un' attivita dettaglio
	public String gestisciAttivitaDettaglio() {

		if(StringUtils.equalsIgnoreCase(getModel().getAttivitaDettaglio().getStato(), "Attiva")){
			modellazioneService.enableAttivitaDettaglio(getModel().getAttivitaDettaglio().getIdAttivitaDettaglio());
		}else{
			modellazioneService.disableAttivitaDettaglio(getModel().getAttivitaDettaglio().getIdAttivitaDettaglio());
		}


		return SUCCESS;
	}


	public String getAttivitaDettaglioById() {

		// eseguo la chiamata per recuperare l'attivita dettaglio da modificare.
		AttivitaDettaglio attivitaDettaglio = modellazioneService.getAttivitaDettaglioById(getModel().getAttivitaDettaglio().getIdAttivitaDettaglio());

		getModel().setAttivitaDettaglio(attivitaDettaglio);

		// partendo dall' Id attivita dettaglio recupero l'Id area
		long idArea = modellazioneService.getIdAreaByIdAttivitaDettaglio(getModel().getAttivitaDettaglio().getIdAttivitaDettaglio());

		// servizio che popola la comboBox Attivita componente.
		List<AttivitaComponente> attComponenteList = modellazioneService.getComboAttivitaComponenteByIdArea(idArea);
		getModel().setAttivitaComponenteList(attComponenteList);

		// eseguo la chiamata per popolare la combo Tipo Normativa del 
		// Tab Normativa
		List<TipoNormativa> comboList = gestioneComponentiService.getComboTipoNormativa(getModel().getIdClasse());
		getModel().setTipoNormativaList(comboList);

		List<CrsDescrizioneTipo> labels= gestioneComponentiService.getListaNormativaLabel(getModel().getIdTipo());
		getModel().getLabelList().add(labels.get(0));

		return SUCCESS;
	}


	// associa una normativa all' attivita dettaglio selezionata
	public String associaAttivitaDettaglioNormativa() {

		AssAdClasse associazione = new AssAdClasse();

		associazione = getModel().getAssAdClasse();

		modellazioneService.associaAttivitaDettaglioNormativa(associazione);

		return SUCCESS;
	}

	// rimuove l'associazione di una normativa all' attivita dettaglio selezionata
	public String rimuoviAssociazioneAttivitaDettaglioNormativa() {

		AssAdClasse associazione = new AssAdClasse();

		associazione = getModel().getAssAdClasse();

		// recupero la chiave primaria della tabella sulla quale fare la remove.
		long pk = modellazioneService.getAssAttivitaDettaglioClassePK(getModel().getAssAdClasse());

		modellazioneService.rimuoviAssociazioneAttivitaDettaglioNormativa(pk);

		return SUCCESS;
	}

	public String getComboAttivitaComponenteByIdSottoProcesso() {

		// eseguo la chiamata per popolare la combo Attivita Componente della a-side
		// di inserimento/modifica attivita.
		List<AttivitaComponente> returnList = modellazioneService.getComboAttivitaComponenteByIdSottoProcesso(getModel().getSottoProcesso().getIdSottoProcesso());
		getModel().setAttivitaComponenteList(returnList);

		return SUCCESS;
	}


	public String getDocumentiMediaAttivitaDettaglio(){

		return SUCCESS;
	}

	public String getDominiAttivitaDettaglio(){

		return SUCCESS;
	}

	public String getCompTecniciAttivitaDettaglio(){

		return SUCCESS;
	}



	/**
	 * controllo il ruolo dell'utente loggato per la valorizzazione
	 * del campo Owner della aSide di nuovo Processo:
	 * 1) se è dirigente stampo il suo username
	 * 2) se è delegato stampo lo username del dirigente associato.
	 * @return userName
	 */
	public String getUserNameDirigente() {

		HttpServletRequest r = ServletActionContext.getRequest();
		AppUser user= (AppUser)r.getSession().getAttribute("AppUser");
		long idUtente = user.getIdUtente();

		getModel().setUserNameDirigente(modellazioneService.getUserNameDirigente(idUtente));


		return SUCCESS;
	}


	@Override
	public Modellazione getModel() {
		// TODO Auto-generated method stub
		return modellazione;
	}



	public Modellazione getModellazione() {
		return modellazione;
	}



	public void setModellazione(Modellazione modellazione) {
		this.modellazione = modellazione;
	}


	public String getDominio(){
		HttpServletRequest request = ServletActionContext.getRequest();
		DatiUtente user= (DatiUtente)request.getSession().getAttribute("DatiUtente");



		long idAssociativa= getModel().getIdAssociativa();
		String tabellaAssociativa= getModel().getTabellaAssociativa();

		if(tabellaAssociativa.trim().equals("processo")){
			CrsAssProcessoDomini c= modellazioneService.cerca(CrsAssProcessoDomini.class, idAssociativa);
			user.setIdDominio(c.getIdDomini());
			CrsDomini d= modellazioneService.cerca(CrsDomini.class, c.getIdDomini());
			getModel().setDominio(d);
		}else if(tabellaAssociativa.trim().equals("sottoprocesso")){
			CrsAssSottoProcessoDomini d = modellazioneService.cerca(CrsAssSottoProcessoDomini.class, idAssociativa);
			user.setIdDominio(d.getIdDomini());
			CrsDomini dom= modellazioneService.cerca(CrsDomini.class, d.getIdDomini());
			getModel().setDominio(dom);

		}else if(tabellaAssociativa.trim().equals("attComponente")){
			CrsAssAttivitaComponenteDomini d = modellazioneService.cerca(CrsAssAttivitaComponenteDomini.class, idAssociativa);
			user.setIdDominio(d.getIdDominio());
			CrsDomini dom= modellazioneService.cerca(CrsDomini.class, d.getIdDominio());
			getModel().setDominio(dom);
		}else if(tabellaAssociativa.trim().equals("attDettaglio")){
			CrsAssAttivitaDettaglioDomini d = modellazioneService.cerca(CrsAssAttivitaDettaglioDomini.class, idAssociativa);
			user.setIdDominio(d.getIdDomini());
			CrsDomini dom= modellazioneService.cerca(CrsDomini.class, d.getIdDomini());
			getModel().setDominio(dom);

		}


		return SUCCESS;
	}

	public String getCompTecnico(){
		HttpServletRequest request = ServletActionContext.getRequest();
		DatiUtente user= (DatiUtente)request.getSession().getAttribute("DatiUtente");



		long idAssociativa= getModel().getIdAssociativa();
		String tabellaAssociativa= getModel().getTabellaAssociativa();

		if(tabellaAssociativa.trim().equals("processo")){
			CrsAssProcessoCompTec c= modellazioneService.cerca(CrsAssProcessoCompTec.class, idAssociativa);
			user.setIdComponenteTecnico(c.getIdComponenteTec());
			CrsComponenteTecnico d= modellazioneService.cerca(CrsComponenteTecnico.class, c.getIdComponenteTec());
			CrsTplCompTecnico tipo= modellazioneService.cerca(CrsTplCompTecnico.class, d.getIdTipoCompTecnico());
			String tipoCompTec=tipo.getDescrizione();
			getModel().setTipoCompTecn(tipoCompTec);
			
			getModel().setCompTecnico(d);
		}else if(tabellaAssociativa.trim().equals("sottoprocesso")){
			CrsAssSottoProcessoCompTec d = modellazioneService.cerca(CrsAssSottoProcessoCompTec.class, idAssociativa);
			user.setIdComponenteTecnico(d.getIdComponenteTec());
			CrsComponenteTecnico c= modellazioneService.cerca(CrsComponenteTecnico.class, d.getIdComponenteTec());
			CrsTplCompTecnico tipo= modellazioneService.cerca(CrsTplCompTecnico.class, c.getIdTipoCompTecnico());
			String tipoCompTec=tipo.getDescrizione();
			getModel().setTipoCompTecn(tipoCompTec);
			getModel().setCompTecnico(c);

		}else if(tabellaAssociativa.trim().equals("attComponente")){
			CrsAssAttivitaComponenteCompTec d = modellazioneService.cerca(CrsAssAttivitaComponenteCompTec.class, idAssociativa);
			user.setIdComponenteTecnico(d.getIdComponenteTec());
			CrsComponenteTecnico c= modellazioneService.cerca(CrsComponenteTecnico.class, d.getIdComponenteTec());
			CrsTplCompTecnico tipo= modellazioneService.cerca(CrsTplCompTecnico.class, c.getIdTipoCompTecnico());
			String tipoCompTec=tipo.getDescrizione();
			getModel().setTipoCompTecn(tipoCompTec);
			getModel().setCompTecnico(c);
		}else if(tabellaAssociativa.trim().equals("attDettaglio")){
			CrsAssAttivitaDettaglioCompTec d = modellazioneService.cerca(CrsAssAttivitaDettaglioCompTec.class, idAssociativa);
			user.setIdComponenteTecnico(d.getIdComponenteTec());
			CrsComponenteTecnico c= modellazioneService.cerca(CrsComponenteTecnico.class, d.getIdComponenteTec());
			CrsTplCompTecnico tipo= modellazioneService.cerca(CrsTplCompTecnico.class, c.getIdTipoCompTecnico());
			String tipoCompTec=tipo.getDescrizione();
			getModel().setTipoCompTecn(tipoCompTec);
			getModel().setCompTecnico(c);

		}


		return SUCCESS;

	}

	public String getNormativaCircolare(){
		HttpServletRequest request = ServletActionContext.getRequest();
		DatiUtente user= (DatiUtente)request.getSession().getAttribute("DatiUtente");
		long idAssociativa= getModel().getIdAssociativa();
		CrsCircolariInps normativa = gestioneComponentiService.cerca(CrsCircolariInps.class, idAssociativa);
		user.setIdNormativa(normativa.getIdCircolariInps());
		String enteEmittente= normativa.getEnteEmittente();
		if(enteEmittente !=null){
			CrsTplEnteEmittente ent=gestioneComponentiService.cercaEmittenteByCodice(enteEmittente);
			normativa.setEnteEmittente(ent.getDescrizione());
		}

		getModel().setCircolariInps(normativa);

		return SUCCESS;
	}
	
	public String getNormativaNoteDecreti(){
		HttpServletRequest request = ServletActionContext.getRequest();
		DatiUtente user= (DatiUtente)request.getSession().getAttribute("DatiUtente");
		long idAssociativa= getModel().getIdAssociativa();
		CrsNoteDecreti noteDecreti = gestioneComponentiService.cerca(CrsNoteDecreti.class, idAssociativa);
		user.setIdNoteDecreti(noteDecreti.getIdNoteDecreti());
		getModel().setNoteDecreti(noteDecreti);
		
		return SUCCESS;
	}
	
	public String getNormativaMessaggi(){
		HttpServletRequest request = ServletActionContext.getRequest();
		DatiUtente user= (DatiUtente)request.getSession().getAttribute("DatiUtente");
		long idAssociativa= getModel().getIdAssociativa();
		CrsMessaggiInps messaggi = gestioneComponentiService.cerca(CrsMessaggiInps.class, idAssociativa);
		user.setIdMessaggiNormativa(messaggi.getIdMessaggiInps());
		String enteEmittente= messaggi.getEnteEmittente();
		if(enteEmittente !=null){
			CrsTplEnteEmittente ent=gestioneComponentiService.cercaEmittenteByCodice(enteEmittente);
			messaggi.setEnteEmittente(ent.getDescrizione());
		}

		getModel().setMessaggiInps(messaggi);

		return SUCCESS;
		
	}
	
	public String getLeggiDecreti(){
		HttpServletRequest request = ServletActionContext.getRequest();
		DatiUtente user= (DatiUtente)request.getSession().getAttribute("DatiUtente");
		long idAssociativa= getModel().getIdAssociativa();
		CrsLeggiDecreti leggDecr = gestioneComponentiService.cerca(CrsLeggiDecreti.class, idAssociativa);
		user.setIdLeggDecreti(leggDecr.getIdLeggiDecreti());
		
		String tipoLegge= leggDecr.getTipoLegge();
		if(tipoLegge !=null){
			CrsTplTipoLegge tl=gestioneComponentiService.cercaLeggeByCodice(tipoLegge);
			leggDecr.setTipoLegge(tl.getDescrizione());
		}

		getModel().setLeggiDecreti(leggDecr);

		return SUCCESS;
		
	}
	


}
