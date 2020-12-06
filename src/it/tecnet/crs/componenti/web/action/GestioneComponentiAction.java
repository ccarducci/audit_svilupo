package it.tecnet.crs.componenti.web.action;


import it.tecnet.crs.componenti.jpa.model.CrsCircolariInps;
import it.tecnet.crs.componenti.jpa.model.CrsDescrizioneTipo;
import it.tecnet.crs.componenti.jpa.model.CrsDomini;
import it.tecnet.crs.componenti.jpa.model.CrsLeggiDecreti;
import it.tecnet.crs.componenti.jpa.model.CrsMessaggiInps;
import it.tecnet.crs.componenti.jpa.model.CrsNoteDecreti;
import it.tecnet.crs.componenti.service.GestioneComponentiService;
import it.tecnet.crs.componenti.web.bean.CircolariInps;
import it.tecnet.crs.componenti.web.bean.DatiTipoNormativa;
import it.tecnet.crs.componenti.web.bean.DescrizioneTipoNormativa;
import it.tecnet.crs.componenti.web.bean.EnteEmittente;
import it.tecnet.crs.componenti.web.bean.GestioneComponentiModel;
import it.tecnet.crs.componenti.web.bean.TipoLegge;
import it.tecnet.crs.componenti.web.bean.TipoNormativa;
import it.tecnet.crs.jpa.model.CrsAssProcessoClasse;
import it.tecnet.crs.jpa.model.CrsAssSottoprocessoClasse;
import it.tecnet.crs.mod.jpa.model.CrsSottoprocesso;
import it.tecnet.crs.mod.web.bean.Area;
import it.tecnet.crs.mod.web.bean.Processo;
import it.tecnet.crs.mod.web.bean.SottoProcesso;
import it.tecnet.crs.session.DatiUtente;
import it.tecnet.crs.web.action.BaseAction;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ModelDriven;

public class GestioneComponentiAction  extends BaseAction implements ModelDriven<GestioneComponentiModel> {

	private static final long serialVersionUID = 1L;


	private GestioneComponentiModel gestioneComponenti = new GestioneComponentiModel();
	private GestioneComponentiService gestioneComponentiService;


	public GestioneComponentiAction(GestioneComponentiService gestioneComponentiService){
		super();
		this.setGestioneComponentiService(gestioneComponentiService);
	}




	/**
	 ***************************************************
	 *************	SEZIONE NORMATIVA	****************
	 ***************************************************	
	 */

	public String getNormativaList(){
		
		// eseguo la chiamata per popolare la combo Ente emittente della a-side
		// di inserimento/modifica normative.
		List<EnteEmittente> list = gestioneComponentiService.getComboEnteEmittente();
		getModel().setEntiEmittenti(list);
		
		// eseguo la chiamata per popolare la combo Tipo Legge della a-side
		// di inserimento/modifica normative.
		List<TipoLegge> listTL = gestioneComponentiService.getComboTipoLegge();
		getModel().setTipiLegge(listTL);

		return SUCCESS;

	}
	
	
	public String getPageCircolariInps(){

		return SUCCESS;

	}
	
	public String getPageNoteDecreti(){

		return SUCCESS;

	}
	
	public String getPageMessaggiInps(){

		return SUCCESS;

	}
	
	public String getPageLeggiDecreti(){
		
		return SUCCESS;

	}

	
	// salva/modifica circolari INPS
	public String salvaCircolariInps() {

		CrsCircolariInps entity = new CrsCircolariInps();

		entity = getModel().getCircolariInps();

		gestioneComponentiService.salva(entity);

		return SUCCESS;
	}
	
	
	// salva/modifica note decreti
	public String salvaNoteDecreti() {

		CrsNoteDecreti entity = new CrsNoteDecreti();

		entity = getModel().getNoteDecreti();

		gestioneComponentiService.salva(entity);

		return SUCCESS;
	}
	
	// salva/modifica messaggi inps
	public String salvaMessaggiInps() {

		CrsMessaggiInps entity = new CrsMessaggiInps();

		entity = getModel().getMessaggiInps();

		gestioneComponentiService.salva(entity);

		return SUCCESS;
	}
	
	// salva/modifica leggi decreti
	public String salvaLeggiDecreti() {

		CrsLeggiDecreti entity = new CrsLeggiDecreti();

		entity = getModel().getLeggiDecreti();

		gestioneComponentiService.salva(entity);

		return SUCCESS;
	}

//	public String getNormativaById() {
//
//		// eseguo la chiamata per recuperare la normativa da modificare.
//		DatiTipoNormativa normativa = gestioneComponentiService.getNormativaById(getModel().getDatiTipoNormativa().getIdDatiTipo());
//
//		getModel().setDatiTipoNormativa(normativa);
//
//		return SUCCESS;
//	}

	// elimina una normativa
	public String eliminaNormativa() {

		gestioneComponentiService.deleteNormativa(getModel().getIdNormativa(), getModel().getIdTipo());

		return SUCCESS;
	}

	// controllo riferimenti in  CRS_ASS_SOTTOPROCESSO_CLASSE e CRS_ASS_PROCESSO_CLASSE
	public String eliminaNormativaCheckReference(){

		CrsAssProcessoClasse assPc= gestioneComponentiService.getCrsAssProcByIdDatiTipo(getModel().getIdNormativa(), getModel().getIdTipo());
		if(assPc !=null){
			getModel().setCheckReferenceNormativa("s");
			return SUCCESS;
		}
		//controllo che abbia riferimenti con CRS_ASS_SOTTOPROCESSO_CLASSE
		CrsAssSottoprocessoClasse assSott=gestioneComponentiService.getCrsAssSottProcByIdDatiTipo(getModel().getIdNormativa(), getModel().getIdTipo());
		if(assSott !=null){
			getModel().setCheckReferenceNormativa("s");
			return SUCCESS;
		}
		//nessuna relazione
		getModel().setCheckReferenceNormativa("n");


		return SUCCESS;
	}

	
	
	
	public String getCircolariInpsById(){
		
		long idNormativa = getModel().getIdNormativa();
		
		try{
			
			CrsCircolariInps normativa = gestioneComponentiService.cerca(CrsCircolariInps.class, idNormativa);
			getModel().setCircolariInps(normativa);
			
			// eseguo la chiamata per popolare la combo Ente emittente della a-side
			// di inserimento/modifica normative.
			List<EnteEmittente> list = gestioneComponentiService.getComboEnteEmittente();
			getModel().setEntiEmittenti(list);
			
		}catch(Exception e){
			e.printStackTrace();
		}

		return SUCCESS;
	}
	
	public String getNoteDecretiById(){
		
		long idNormativa = getModel().getIdNormativa();
		
		try{
			
			CrsNoteDecreti normativa = gestioneComponentiService.cerca(CrsNoteDecreti.class, idNormativa);
			getModel().setNoteDecreti(normativa);
			
		}catch(Exception e){
			e.printStackTrace();
		}

		return SUCCESS;
	}
	
	public String getMessaggiInpsById(){
		
		long idNormativa = getModel().getIdNormativa();
		
		try{
			
			CrsMessaggiInps normativa = gestioneComponentiService.cerca(CrsMessaggiInps.class, idNormativa);
			getModel().setMessaggiInps(normativa);
			
			// eseguo la chiamata per popolare la combo Ente emittente della a-side
			// di inserimento/modifica normative.
			List<EnteEmittente> list = gestioneComponentiService.getComboEnteEmittente();
			getModel().setEntiEmittenti(list);
			
		}catch(Exception e){
			e.printStackTrace();
		}

		return SUCCESS;
	}
	
	public String getLeggiDecretiById(){
		
		long idNormativa = getModel().getIdNormativa();
		
		try{
			
			CrsLeggiDecreti normativa = gestioneComponentiService.cerca(CrsLeggiDecreti.class, idNormativa);
			getModel().setLeggiDecreti(normativa);
			
			// eseguo la chiamata per popolare la combo Tipo Legge della a-side
			// di inserimento/modifica normative.
			List<TipoLegge> listTL = gestioneComponentiService.getComboTipoLegge();
			getModel().setTipiLegge(listTL);
			
		}catch(Exception e){
			e.printStackTrace();
		}

		return SUCCESS;
	}
	
	
	public String getPageAllegatiCircolariInps(){

		return SUCCESS;

	}
	
	public String getPageAllegatiNoteDecreti(){

		return SUCCESS;

	}
	
	public String getPageAllegatiMessaggiInps(){

		return SUCCESS;

	}
	
	public String getPageAllegatiLeggiDecreti(){

		return SUCCESS;

	}
	
	
	public String getComboEnteEmittente() {
		
		// eseguo la chiamata per popolare la combo Ente emittente della a-side
		// di inserimento/modifica normative.
		List<EnteEmittente> list = gestioneComponentiService.getComboEnteEmittente();
		getModel().setEntiEmittenti(list);
		
		return SUCCESS;
	}
	

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return super.execute();
	}



	@Override
	public GestioneComponentiModel getModel() {
		return gestioneComponenti;
	}

	public void setGestioneComponentiService(GestioneComponentiService gestioneComponentiService) {
		this.gestioneComponentiService = gestioneComponentiService;
	}

	public GestioneComponentiService getGestioneComponentiService() {
		return gestioneComponentiService;
	}

}
