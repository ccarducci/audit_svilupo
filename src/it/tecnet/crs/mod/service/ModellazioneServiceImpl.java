package it.tecnet.crs.mod.service;

import it.tecnet.crs.componenti.jpa.model.CrsComponenteTecnico;
import it.tecnet.crs.componenti.jpa.model.CrsDocumentiMedia;
import it.tecnet.crs.componenti.jpa.model.CrsDomini;
import it.tecnet.crs.componenti.jpa.model.CrsTplCompTecnico;
import it.tecnet.crs.componenti.web.bean.CircolariInps;
import it.tecnet.crs.componenti.web.bean.DatiTipoNormativa;
import it.tecnet.crs.componenti.web.bean.LeggiDecreti;
import it.tecnet.crs.componenti.web.bean.MessaggiInps;
import it.tecnet.crs.componenti.web.bean.NormativaTablePaginator;
import it.tecnet.crs.componenti.web.bean.NoteDecreti;
import it.tecnet.crs.componenti.web.dto.CrsCompTecnicoDto;
import it.tecnet.crs.jpa.model.CrsAssAcClasse;
import it.tecnet.crs.jpa.model.CrsAssAdClasse;
import it.tecnet.crs.jpa.model.CrsAssProcessoClasse;
import it.tecnet.crs.jpa.model.CrsAssSottoprocessoClasse;
import it.tecnet.crs.mod.jpa.dao.ModellazioneDao;
import it.tecnet.crs.mod.jpa.model.CrsArea;
import it.tecnet.crs.mod.jpa.model.CrsAttivitaComponente;
import it.tecnet.crs.mod.jpa.model.CrsAttivitaDettaglio;
import it.tecnet.crs.mod.jpa.model.CrsProcesso;
import it.tecnet.crs.mod.jpa.model.CrsSottoprocesso;
import it.tecnet.crs.mod.web.bean.Area;
import it.tecnet.crs.mod.web.bean.AreaTablePaginator;
import it.tecnet.crs.mod.web.bean.AttivitaComponente;
import it.tecnet.crs.mod.web.bean.AttivitaComponenteTablePaginator;
import it.tecnet.crs.mod.web.bean.AttivitaDettaglio;
import it.tecnet.crs.mod.web.bean.AttivitaDettaglioTablePaginator;
import it.tecnet.crs.mod.web.bean.CompTecniciAttivitaDettaglioPaginator;
import it.tecnet.crs.mod.web.bean.CompTecniciProcessoPaginator;
import it.tecnet.crs.mod.web.bean.CompTecniciSottoProcessoPaginator;
import it.tecnet.crs.mod.web.bean.DocumentiAttivitaDettaglioPaginator;
import it.tecnet.crs.mod.web.bean.DocumentiProcessoPaginator;
import it.tecnet.crs.mod.web.bean.DocumentiSottoProcessoPaginator;
import it.tecnet.crs.mod.web.bean.DominiAttivitaDettaglioPaginator;
import it.tecnet.crs.mod.web.bean.DominiProcessoPaginator;
import it.tecnet.crs.mod.web.bean.DominiSottoProcessoPaginator;
import it.tecnet.crs.mod.web.bean.Processo;
import it.tecnet.crs.mod.web.bean.ProcessoTablePaginator;
import it.tecnet.crs.mod.web.bean.SottoProcesso;
import it.tecnet.crs.mod.web.bean.SottoProcessoTablePaginator;
import it.tecnet.crs.mod.web.dto.CompTecAttCompDto;
import it.tecnet.crs.mod.web.dto.DocMediaAttCompDto;
import it.tecnet.crs.mod.web.dto.DomAttCompDto;
import it.tecnet.crs.mod.web.dto.ProcessoDto;
import it.tecnet.crs.util.BeanToModel;
import it.tecnet.crs.util.ModelToBean;
import it.tecnet.crs.util.ModelToDto;
import it.tecnet.crs.web.beans.AssAcClasse;
import it.tecnet.crs.web.beans.AssAdClasse;
import it.tecnet.crs.web.beans.AssProcessoClasse;
import it.tecnet.crs.web.beans.AssSottoProcessoClasse;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import org.springframework.transaction.annotation.Transactional;


public class ModellazioneServiceImpl implements ModellazioneService {

	private ModellazioneDao modellazioneDao;

	public ModellazioneDao getModellazioneDao() {
		return modellazioneDao;
	}

	public void setModellazioneDao(ModellazioneDao daoModImpl) {
		this.modellazioneDao = daoModImpl;
	}



	public List<Long> getIdPadriByIdAttivitaComponente(long idAttivitaComponente){

		List<Object[]> lista = null;
		List<Long> listaID = new ArrayList<Long>();

		lista = modellazioneDao.getIdPadriByIdAttivitaComponente(idAttivitaComponente);

		for(Object[] obj : lista){	

			listaID.add(0, (Long)obj[0]);
			listaID.add(1, (Long)obj[1]);
			listaID.add(2, (Long)obj[2]);
		}

		return listaID;
	}

	/**
	 ***********************************************
	 *************	SEZIONE AREA	****************
	 ***********************************************	
	 */
	@Transactional
	public void saveArea(Area areaBean){

		CrsArea areaJpa = BeanToModel.beanToModel(areaBean);
		modellazioneDao.saveArea(areaJpa);
	}	

	public void deleteArea(long idArea){
		modellazioneDao.deleteArea(idArea);
	}

	public List<Area> getListaAree (AreaTablePaginator filter){
		List<CrsArea> lista = null;
		List<Area> listaAreaBean=new ArrayList<Area>();
		lista=modellazioneDao.getListaAree(filter);

		for(CrsArea crsArea : lista){	
			Area area = ModelToBean.modelToBean(crsArea);
			listaAreaBean.add(area);
		}

		return listaAreaBean;	
	}

	public Integer countAllAree(AreaTablePaginator filter){
		return modellazioneDao.countAllAree(filter);
	}

	@Transactional
	public void disableArea( Long idArea )
	{
		modellazioneDao.disableArea(idArea);
	}

	@Transactional
	public void enableArea( Long idArea )
	{		
		modellazioneDao.enableArea(idArea);
	}


	public List<Area> listAllAreeAttive(AreaTablePaginator filter)
	{			
		List<CrsArea> lista = null;
		List<Area> listaAreaBean=new ArrayList<Area>();;
		lista=modellazioneDao.getListaAree(filter);

		for(int i=0; i<lista.size();i++){	
			Area area = ModelToBean.modelToBean(lista.get(i));
			listaAreaBean.add(area);
		}

		return listaAreaBean;
	}


	@Override
	public Area getAreaById(Long idArea) {

		CrsArea areaJpa = new CrsArea();
		Area area = new Area();

		areaJpa = modellazioneDao.getAreaById(idArea);

		area = ModelToBean.modelToBean(areaJpa);

		return area;
	}


	/**
	 * Il metodo propaga la dataFine validita dell'area a tutte le entità figlie
	 * (processi-fasi-attivita componenti-attivita dettagli) 
	 */
	public boolean chiudiValiditaArea(Area area){

		boolean error = false;

		try{

			// 1. Recupero tutte le entità figlie in base all' idArea.
			List<Processo> processiList = getProcessiByIdArea(area.getIdArea());
			List<SottoProcesso> sottoProcessiList = getSottoProcessiByIdArea(area.getIdArea());
			List<AttivitaComponente> attComponenteList = getAttivitaComponentiByIdArea(area.getIdArea());
			List<AttivitaDettaglio> attDettaglioList = getAttivitaDettagliByIdArea(area.getIdArea());

			// 2. Propago la dataFine dell'area per ogni lista recuperata.

			// 2a. Processi
			for(Processo processo : processiList){
				processo.setDataFine(area.getDataFine());
				saveProcesso(processo);
			}

			// 2b. SottoProcessi
			for(SottoProcesso sottoProcesso : sottoProcessiList){
				sottoProcesso.setDataFine(area.getDataFine());
				saveSottoProcesso(sottoProcesso);
			}

			// 2c. Attivita Componenti
			for(AttivitaComponente attComponente : attComponenteList){
				attComponente.setDataFine(area.getDataFine());
				saveAttivitaComponente(attComponente);
			}

			// 2d. Attivita Dettaglio
			for(AttivitaDettaglio attDettaglio : attDettaglioList){
				attDettaglio.setDataFine(area.getDataFine());
				saveAttivitaDettaglio(attDettaglio);
			}


		}catch(Exception ex){
			error = true;
			ex.printStackTrace();

		}


		return error;
	}


	/**
	 ***************************************************
	 *************	SEZIONE PROCESSI  ******************
	 ***************************************************	
	 */

	public List<ProcessoDto> getListaProcessi (ProcessoTablePaginator filter, long idUtente){

		List<Object[]> lista = null;
		List<ProcessoDto> listaProcessi = new ArrayList<ProcessoDto>();

		lista=modellazioneDao.getListaProcessi(filter, idUtente);

		for(Object[] obj : lista){	
			ProcessoDto processo = ModelToDto.modelToProcessoDto(obj);
			listaProcessi.add(processo);
		}

		return listaProcessi;	
	}

	public Integer countAllProcessi(ProcessoTablePaginator filter, long IdUtente){
		return modellazioneDao.countAllProcessi(filter,IdUtente);
	}


	public List<Area> getComboArea (){
		List<CrsArea> lista = null;
		List<Area> listaAreaBean=new ArrayList<Area>();
		lista=modellazioneDao.getComboArea();

		for(CrsArea crsArea : lista){	
			Area area = ModelToBean.modelToBean(crsArea);
			listaAreaBean.add(area);
		}

		return listaAreaBean;	
	}


	@Transactional
	public void saveProcesso(Processo processo){

		CrsProcesso processoJpa = BeanToModel.beanToModel(processo);
		modellazioneDao.saveProcesso(processoJpa);
	}

	public void deleteProcesso(long idProcesso){

		modellazioneDao.deleteProcesso(idProcesso);

	}


	@Transactional
	public void disableProcesso(Long idProcesso){

		modellazioneDao.disableProcesso(idProcesso);

	}

	@Transactional
	public void pubblicaProcesso(Long idProcesso){

		modellazioneDao.pubblicaProcesso(idProcesso);
	}

	@Override
	public Processo getProcessoById(Long idProcesso) {

		CrsProcesso processoJpa = new CrsProcesso();
		Processo processo = new Processo();

		processoJpa = modellazioneDao.getProcessoById(idProcesso);

		processo = ModelToBean.modelToBean(processoJpa);

		return processo;
	}


	public List<CircolariInps> getAssociabiliCircolariInpsTable(ProcessoTablePaginator filter){

		List<Object[]> lista = null;
		List<CircolariInps> listaReturn = new ArrayList<CircolariInps>();

		lista=modellazioneDao.getAssociabiliCircolariInpsTable(filter);

		for(Object[] obj : lista){	
			CircolariInps objectBean = ModelToBean.modelToCircolariInpsBean(obj);
			listaReturn.add(objectBean);
		}

		return listaReturn;	
	}

	public Integer countAllAssociabiliCircolariInps(ProcessoTablePaginator filter){
		return modellazioneDao.countAllAssociabiliCircolariInps(filter);
	}

	public List<NoteDecreti> getAssociabiliNoteDecreti(ProcessoTablePaginator filter){

		List<Object[]> lista = null;
		List<NoteDecreti> listaReturn = new ArrayList<NoteDecreti>();

		lista=modellazioneDao.getAssociabiliNoteDecreti(filter);

		for(Object[] obj : lista){	
			NoteDecreti objectBean = ModelToBean.modelToNoteDecretiBean(obj);
			listaReturn.add(objectBean);
		}

		return listaReturn;	
	}

	public Integer countAllAssociabiliNoteDecreti(ProcessoTablePaginator filter){
		return modellazioneDao.countAllAssociabiliNoteDecreti(filter);
	}

	public List<MessaggiInps> getAssociabiliMessaggiInps(ProcessoTablePaginator filter){

		List<Object[]> lista = null;
		List<MessaggiInps> listaReturn = new ArrayList<MessaggiInps>();

		lista=modellazioneDao.getAssociabiliMessaggiInps(filter);

		for(Object[] obj : lista){	
			MessaggiInps objectBean = ModelToBean.modelToMessaggiInpsBean(obj);
			listaReturn.add(objectBean);
		}

		return listaReturn;	
	}

	public Integer countAllAssociabiliMessaggiInps(ProcessoTablePaginator filter){
		return modellazioneDao.countAllAssociabiliMessaggiInps(filter);
	}

	public List<LeggiDecreti> getAssociabiliLeggiDecreti(ProcessoTablePaginator filter){

		List<Object[]> lista = null;
		List<LeggiDecreti> listaReturn = new ArrayList<LeggiDecreti>();

		lista=modellazioneDao.getAssociabiliLeggiDecreti(filter);

		for(Object[] obj : lista){	
			LeggiDecreti objectBean = ModelToBean.modelToLeggiDecretiBean(obj);
			listaReturn.add(objectBean);
		}

		return listaReturn;	
	}

	public Integer countAllAssociabiliLeggiDecreti(ProcessoTablePaginator filter){
		return modellazioneDao.countAllAssociabiliLeggiDecreti(filter);
	}

	@Transactional
	public void associaProcessoNormativa(AssProcessoClasse associazione){

		CrsAssProcessoClasse associazioneJpa = BeanToModel.beanToModel(associazione);
		modellazioneDao.associaProcessoNormativa(associazioneJpa);
	}

	public long getAssProcessoClassePK(AssProcessoClasse associazione){

		long primaryKey = modellazioneDao.getAssProcessoClassePK(associazione);

		return primaryKey;
	}

	public void rimuoviAssociazioneProcessoNormativa(long idAssProcessoClasse){

		modellazioneDao.rimuoviAssociazioneProcessoNormativa(idAssProcessoClasse);

	}


	public boolean checkFieldOrdinamento(long idTabella, int ordinamento, String tabella) {

		boolean check = modellazioneDao.checkFieldOrdinamento(idTabella, ordinamento, tabella);

		return check;
	}
	
	
	public String getUserNameDirigente(long idUtente){
	
		return modellazioneDao.getUserNameDirigente(idUtente);
		
		
	}

	/**
	 *******************************************************
	 *************	SEZIONE SOTTOPROCESSI	****************
	 *******************************************************	
	 */

	public List<Processo> getComboProcessoByIdArea(long idArea){
		List<CrsProcesso> lista = null;
		List<Processo> listaProcessoBean=new ArrayList<Processo>();
		lista=modellazioneDao.getComboProcessoByIdArea(idArea);

		for(CrsProcesso crsProcesso : lista){	
			Processo processo = ModelToBean.modelToBean(crsProcesso);
			listaProcessoBean.add(processo);
		}

		return listaProcessoBean;	
	}

	@Transactional
	public void saveSottoProcesso(SottoProcesso sottoProcesso){

		CrsSottoprocesso sottoProcessoJpa = BeanToModel.beanToModel(sottoProcesso);
		modellazioneDao.saveSottoProcesso(sottoProcessoJpa);

	}

	public void deleteSottoProcesso(long idSottoProcesso){

		modellazioneDao.deleteSottoProcesso(idSottoProcesso);

	}


	public List<SottoProcesso> getListaSottoProcessi (SottoProcessoTablePaginator filter, long idUtente){
		List<Object[]> lista = null;
		List<SottoProcesso> listaBean=new ArrayList<SottoProcesso>();

		lista=modellazioneDao.getListaSottoProcessi(filter,idUtente);

		for(Object[] obj : lista){	
			SottoProcesso fase = ModelToBean.modelToBean(obj);
			listaBean.add(fase);
		}

		return listaBean;	
	}

	public Integer countAllSottoProcessi(SottoProcessoTablePaginator filter,long idUtente){
		return modellazioneDao.countAllSottoProcessi(filter,idUtente);
	}


	@Transactional
	public void disableSottoprocesso(long idSottoProcesso){

		modellazioneDao.disableSottoProcesso(idSottoProcesso);

	}

	@Transactional
	public void enableSottoprocesso(long idSottoProcesso){

		modellazioneDao.enableSottoProcesso(idSottoProcesso);

	}

	@Override
	public SottoProcesso getSottoProcessoById(Long idSottoProcesso) {

		CrsSottoprocesso modelJpa = new CrsSottoprocesso();
		SottoProcesso bean = new SottoProcesso();

		modelJpa = modellazioneDao.getSottoProcessoById(idSottoProcesso);

		bean = ModelToBean.modelToBean(modelJpa);

		return bean;
	}


	public List<DatiTipoNormativa> getListaSottoProcessoNormative(SottoProcessoTablePaginator filter){

		List<Object[]> lista = null;
		List<DatiTipoNormativa> listaReturn = new ArrayList<DatiTipoNormativa>();

		lista=modellazioneDao.getListaSottoProcessoNormative(filter);

		for(Object[] obj : lista){	
			DatiTipoNormativa objectBean = ModelToBean.modelToDatiTipoNormativaBean(obj);
			listaReturn.add(objectBean);
		}

		return listaReturn;	
	}

	public Integer countAllSottoProcessoNormative(SottoProcessoTablePaginator filter){
		return modellazioneDao.countAllSottoProcessoNormative(filter);
	}

	@Transactional
	public void associaSottoProcessoNormativa(AssSottoProcessoClasse associazione){

		CrsAssSottoprocessoClasse associazioneJpa = BeanToModel.beanToModel(associazione);
		modellazioneDao.associaSottoProcessoNormativa(associazioneJpa);
	}

	public long getAssSottoProcessoClassePK(AssSottoProcessoClasse associazione){

		long primaryKey = modellazioneDao.getAssSottoProcessoClassePK(associazione);

		return primaryKey;
	}

	public void rimuoviAssociazioneSottoProcessoNormativa(long idAssSottoProcessoClasse){

		modellazioneDao.rimuoviAssociazioneSottoProcessoNormativa(idAssSottoProcessoClasse);

	}


	public long getIdAreaByIdSottoProcesso(long idSottoProcesso){

		long idArea = modellazioneDao.getIdAreaByIdSottoProcesso(idSottoProcesso);

		return idArea;
	}


	/**
	 ***********************************************************
	 *************	SEZIONE ATTIVITA COMPONENTE	****************
	 ***********************************************************	
	 */

	public List<SottoProcesso> getComboSottoProcessoByIdProcesso(long idProcesso){
		List<CrsSottoprocesso> lista = null;
		List<SottoProcesso> listaBean=new ArrayList<SottoProcesso>();
		lista=modellazioneDao.getComboSottoProcessoByIdProcesso(idProcesso);

		for(CrsSottoprocesso crsSottoprocesso : lista){	
			SottoProcesso sottoProcesso = ModelToBean.modelToBean(crsSottoprocesso);
			listaBean.add(sottoProcesso);
		}

		return listaBean;	
	}


	@Transactional
	public void saveAttivitaComponente(AttivitaComponente attivitaComponente){

		CrsAttivitaComponente attivitaComponenteJpa = BeanToModel.beanToModel(attivitaComponente);
		modellazioneDao.saveAttivitaComponente(attivitaComponenteJpa);

	}

	public void deleteAttivitaComponente(long idAttivitaComponente){

		modellazioneDao.deleteAttivitaComponente(idAttivitaComponente);

	}

	public List<AttivitaComponente> getListaAttivitaComponente (AttivitaComponenteTablePaginator filter,long idUtente){

		List<Object[]> lista = null;
		List<AttivitaComponente> listaBean=new ArrayList<AttivitaComponente>();

		lista=modellazioneDao.getListaAttivitaComponente(filter,idUtente);

		for(Object[] obj : lista){	
			AttivitaComponente bean = ModelToBean.modelToAttivitaComponenteBean(obj);
			listaBean.add(bean);
		}

		return listaBean;	
	}

	public Integer countAllAttivitaComponente(AttivitaComponenteTablePaginator filter, long idUtente){
		return modellazioneDao.countAllAttivitaComponente(filter,idUtente);
	}

	@Transactional
	public void disableAttivitaComponente(Long idAttivitaComponente){

		modellazioneDao.disableAttivitaComponente(idAttivitaComponente);

	}

	@Transactional
	public void enableAttivitaComponente(Long idAttivitaComponente){

		modellazioneDao.enableAttivitaComponente(idAttivitaComponente);

	}


	@Override
	public AttivitaComponente getAttivitaComponenteById(Long idAttivitaComponente) {

		CrsAttivitaComponente modelJpa = new CrsAttivitaComponente();
		AttivitaComponente bean = new AttivitaComponente();

		modelJpa = modellazioneDao.getAttivitaComponenteById(idAttivitaComponente);

		bean = ModelToBean.modelToAttivitaComponenteBean(modelJpa);

		return bean;
	}


	public List<DatiTipoNormativa> getListaAttivitaComponenteNormative(AttivitaComponenteTablePaginator filter){

		List<Object[]> lista = null;
		List<DatiTipoNormativa> listaReturn = new ArrayList<DatiTipoNormativa>();

		lista=modellazioneDao.getListaAttivitaComponenteNormative(filter);

		for(Object[] obj : lista){	
			DatiTipoNormativa objectBean = ModelToBean.modelToDatiTipoNormativaBean(obj);
			listaReturn.add(objectBean);
		}

		return listaReturn;	
	}

	public Integer countAllAttivitaComponenteNormative(AttivitaComponenteTablePaginator filter){
		return modellazioneDao.countAllAttivitaComponenteNormative(filter);
	}

	@Transactional
	public void associaAttivitaComponenteNormativa(AssAcClasse associazione){

		CrsAssAcClasse associazioneJpa = BeanToModel.beanToModel(associazione);
		modellazioneDao.associaAttivitaComponenteNormativa(associazioneJpa);
	}

	public long getAssAttivitaComponenteClassePK(AssAcClasse associazione){

		long primaryKey = modellazioneDao.getAssAttivitaComponenteClassePK(associazione);

		return primaryKey;
	}

	public void rimuoviAssociazioneAttivitaComponenteNormativa(long idAssAcClasse){

		modellazioneDao.rimuoviAssociazioneAttivitaComponenteNormativa(idAssAcClasse);

	}


	public long getIdAreaByIdAttivitaComponente(long idAttivitaComponente){

		long idArea = modellazioneDao.getIdAreaByIdAttivitaComponente(idAttivitaComponente);

		return idArea;
	}

	public List<SottoProcesso> getComboSottoProcessoByIdArea(long idArea){
		List<CrsSottoprocesso> lista = null;
		List<SottoProcesso> listaBean=new ArrayList<SottoProcesso>();
		lista=modellazioneDao.getComboSottoProcessoByIdArea(idArea);

		for(CrsSottoprocesso crsSottoprocesso : lista){	
			SottoProcesso sottoProcesso = ModelToBean.modelToBean(crsSottoprocesso);
			listaBean.add(sottoProcesso);
		}

		return listaBean;	
	}


	/**
	 ***********************************************************
	 *************	SEZIONE ATTIVITA DETTAGLIO	****************
	 ***********************************************************	
	 */

	public List<AttivitaComponente> getComboAttivitaComponenteByIdSottoProcesso(long idSottoProcesso){
		List<CrsAttivitaComponente> lista = null;
		List<AttivitaComponente> listaBean=new ArrayList<AttivitaComponente>();
		lista=modellazioneDao.getComboAttivitaComponenteByIdSottoProcesso(idSottoProcesso);

		for(CrsAttivitaComponente crsAttivitaComponente : lista){	
			AttivitaComponente attivitaComponente = ModelToBean.modelToAttivitaComponenteBean(crsAttivitaComponente);
			listaBean.add(attivitaComponente);
		}

		return listaBean;	
	}

	@Transactional
	public void saveAttivitaDettaglio(AttivitaDettaglio attivitaDettaglio){

		CrsAttivitaDettaglio attivitaDettaglioJpa = BeanToModel.beanToModel(attivitaDettaglio);
		modellazioneDao.saveAttivitaDettaglio(attivitaDettaglioJpa);

	}

	public void deleteAttivitaDettaglio(long idAttivitaDettaglio){

		modellazioneDao.deleteAttivitaDettaglio(idAttivitaDettaglio);

	}


	public List<AttivitaDettaglio> getListaAttivitaDettaglio (AttivitaDettaglioTablePaginator filter,long idUtente){

		List<Object[]> lista = null;
		List<AttivitaDettaglio> listaBean=new ArrayList<AttivitaDettaglio>();

		lista=modellazioneDao.getListaAttivitaDettaglio(filter,idUtente);

		for(Object[] obj : lista){	
			AttivitaDettaglio bean = ModelToBean.modelToAttivitaDettaglioBean(obj);
			listaBean.add(bean);
		}

		return listaBean;	
	}

	public Integer countAllAttivitaDettaglio(AttivitaDettaglioTablePaginator filter,long idUtente){
		return modellazioneDao.countAllAttivitaDettaglio(filter,idUtente);
	}

	@Transactional
	public void disableAttivitaDettaglio(Long idAttivitaDettaglio){

		modellazioneDao.disableAttivitaDettaglio(idAttivitaDettaglio);

	}

	@Transactional
	public void enableAttivitaDettaglio(Long idAttivitaDettaglio){

		modellazioneDao.enableAttivitaDettaglio(idAttivitaDettaglio);

	}


	@Override
	public AttivitaDettaglio getAttivitaDettaglioById(Long idAttivitaDettaglio) {

		CrsAttivitaDettaglio modelJpa = new CrsAttivitaDettaglio();
		AttivitaDettaglio bean = new AttivitaDettaglio();

		modelJpa = modellazioneDao.getAttivitaDettaglioById(idAttivitaDettaglio);

		bean = ModelToBean.modelToAttivitaDettaglioBean(modelJpa);

		return bean;
	}


	public List<DatiTipoNormativa> getListaAttivitaDettaglioNormative(AttivitaDettaglioTablePaginator filter){

		List<Object[]> lista = null;
		List<DatiTipoNormativa> listaReturn = new ArrayList<DatiTipoNormativa>();

		lista=modellazioneDao.getListaAttivitaDettaglioNormative(filter);

		for(Object[] obj : lista){	
			DatiTipoNormativa objectBean = ModelToBean.modelToDatiTipoNormativaBean(obj);
			listaReturn.add(objectBean);
		}

		return listaReturn;	
	}

	public Integer countAllAttivitaDettaglioNormative(AttivitaDettaglioTablePaginator filter){
		return modellazioneDao.countAllAttivitaDettaglioNormative(filter);
	}

	@Transactional
	public void associaAttivitaDettaglioNormativa(AssAdClasse associazione){

		CrsAssAdClasse associazioneJpa = BeanToModel.beanToModel(associazione);
		modellazioneDao.associaAttivitaDettaglioNormativa(associazioneJpa);
	}

	public long getAssAttivitaDettaglioClassePK(AssAdClasse associazione){

		long primaryKey = modellazioneDao.getAssAttivitaDettaglioClassePK(associazione);

		return primaryKey;
	}

	public void rimuoviAssociazioneAttivitaDettaglioNormativa(long idAssAdClasse){

		modellazioneDao.rimuoviAssociazioneAttivitaDettaglioNormativa(idAssAdClasse);

	}


	public long getIdAreaByIdAttivitaDettaglio(long idAttivitaDettaglio){

		long idArea = modellazioneDao.getIdAreaByIdAttivitaDettaglio(idAttivitaDettaglio);

		return idArea;
	}

	public List<AttivitaComponente> getComboAttivitaComponenteByIdArea(long idArea){
		List<CrsAttivitaComponente> lista = null;
		List<AttivitaComponente> listaBean=new ArrayList<AttivitaComponente>();
		lista=modellazioneDao.getComboAttivitaComponenteByIdArea(idArea);

		for(CrsAttivitaComponente crsAttivitaComponente : lista){	
			AttivitaComponente attivitaComponente = ModelToBean.modelToAttivitaComponenteBean(crsAttivitaComponente);
			listaBean.add(attivitaComponente);
		}

		return listaBean;		
	}





	public List<Processo> getProcessiByIdArea(long idArea){

		List<Object[]> lista = null;
		List<Processo> listaProcessoBean=new ArrayList<Processo>();

		lista=modellazioneDao.getProcessiByIdArea(idArea);

		for(Object[] obj : lista){	
			Processo processo = ModelToBean.modelToProcessoBean(obj);
			listaProcessoBean.add(processo);
		}

		return listaProcessoBean;	
	}

	public List<SottoProcesso> getSottoProcessiByIdArea(long idArea){

		List<Object[]> lista = null;
		List<SottoProcesso> listaBean=new ArrayList<SottoProcesso>();

		lista=modellazioneDao.getSottoProcessiByIdArea(idArea);

		for(Object[] obj : lista){	
			SottoProcesso bean = ModelToBean.modelToSottoProcessoBean(obj);
			listaBean.add(bean);
		}

		return listaBean;	
	}

	public List<AttivitaComponente> getAttivitaComponentiByIdArea(long idArea){

		List<Object[]> lista = null;
		List<AttivitaComponente> listaBean=new ArrayList<AttivitaComponente>();

		lista=modellazioneDao.getAttivitaComponentiByIdArea(idArea);

		for(Object[] obj : lista){	
			AttivitaComponente bean = ModelToBean.modelToAttivitaComponenteBeanByIdArea(obj);
			listaBean.add(bean);
		}

		return listaBean;	
	}


	public List<AttivitaDettaglio> getAttivitaDettagliByIdArea(long idArea){

		List<Object[]> lista = null;
		List<AttivitaDettaglio> listaBean=new ArrayList<AttivitaDettaglio>();

		lista=modellazioneDao.getAttivitaDettagliByIdArea(idArea);

		for(Object[] obj : lista){	
			AttivitaDettaglio bean = ModelToBean.modelToAttivitaDettaglioBeanByIdArea(obj);
			listaBean.add(bean);
		}

		return listaBean;	
	}




	@Override
	public List<DocMediaAttCompDto> getDocMediaAttComp(long idAttivitaComponente, int pageNumber, int pageSize, int columnNameToOrder,
			String orderType, String search) {
		List<Object[]> obj = modellazioneDao.getDocMediaAttComp(idAttivitaComponente, pageNumber, pageSize,  columnNameToOrder,  orderType, search) ;
		List<DocMediaAttCompDto> l= new ArrayList<DocMediaAttCompDto>();
		if(obj !=null){
			for(int i=0; i<obj.size();i++){
				DocMediaAttCompDto t= new DocMediaAttCompDto();
				t.setIdAssociazione((Long)obj.get(i)[0]);
				t.setIdDoc((Long)obj.get(i)[1]);
				t.setNomeFile((String)obj.get(i)[2]);
				t.setDescrizione((String)obj.get(i)[3]);
				t.setDataInizio((Date)obj.get(i)[4]);

				l.add(t);
			}
		}
		return l;
	}


	@Override
	public Integer countDocMediaAttComp(long idAttivitaComponente,
			String search) {
		// TODO Auto-generated method stub
		return modellazioneDao.countDocMediaAttComp(idAttivitaComponente, search);
	}


	@Override
	public List<CrsDocumentiMedia> getDocAttCompDaAssociare(long idAttivitaComponente, int getiDisplayStart,
			int getiDisplayLength, int getiSortCol_0, String getsSortDir_0,
			String getsSearch) {
		List<Object[]> obj = modellazioneDao.getDocAttCompDaAssociare(idAttivitaComponente, getiDisplayStart, getiDisplayLength,  getiSortCol_0,  getsSortDir_0, getsSearch) ;
		List<CrsDocumentiMedia> l= new ArrayList<CrsDocumentiMedia>();
		if(obj !=null){
			for(int i=0; i<obj.size();i++){
				CrsDocumentiMedia t= new CrsDocumentiMedia();
				t.setId((Long)obj.get(i)[0]);
				t.setNomeFile((String)obj.get(i)[1]);
				t.setDescrizione((String)obj.get(i)[2]);
				t.setDataInizio((Date)obj.get(i)[3]);

				l.add(t);
			}
		}
		return l;
	}

	@Override
	public Integer countDocAttCompDaAssociare(long idAttivitaComponente,
			String getsSearch) {
		// TODO Auto-generated method stub
		return modellazioneDao.countDocAttCompDaAssociare(idAttivitaComponente,getsSearch );
	}



	@Override
	public List<DomAttCompDto> getListDominiAttComp(long idAttivitaComponente,
			int getiDisplayStart, int getiDisplayLength, int getiSortCol_0,
			String getsSortDir_0, String getsSearch) {
		List<Object[]> obj = modellazioneDao.getListDominiAttComp(idAttivitaComponente, getiDisplayStart, getiDisplayLength,  getiSortCol_0,  getsSortDir_0, getsSearch) ;
		List<DomAttCompDto> l= new ArrayList<DomAttCompDto>();
		if(obj !=null){
			for(int i=0; i<obj.size();i++){
				DomAttCompDto t= new DomAttCompDto();
				t.setIdAssociazione((Long)obj.get(i)[0]);
				t.setDescrizione((String)obj.get(i)[1]);
				t.setDataInizio((Date)obj.get(i)[2]);

				l.add(t);
			}
		}
		return l;
	}

	@Override
	public Integer countListDominiAttComp(long idAttivitaComponente,
			String search) {
		// TODO Auto-generated method stub
		return modellazioneDao.countListDominiAttComp(idAttivitaComponente,search );
	}


	@Override
	public List<CrsDomini> getListDominiAttCompDaAssociare(long idAttivitaComponente, int getiDisplayStart,
			int getiDisplayLength, int getiSortCol_0, String getsSortDir_0,
			String getsSearch) {
		List<Object[]> obj = modellazioneDao.getListDominiAttCompDaAssociare(idAttivitaComponente, getiDisplayStart, getiDisplayLength,  getiSortCol_0,  getsSortDir_0, getsSearch) ;
		List<CrsDomini> l= new ArrayList<CrsDomini>();
		if(obj !=null){
			for(int i=0; i<obj.size();i++){
				CrsDomini t= new CrsDomini();
				t.setId((Long)obj.get(i)[0]);
				t.setDescrizione((String)obj.get(i)[1]);
				t.setDataInizio((Date)obj.get(i)[2]);

				l.add(t);
			}
		}
		return l;
	}

	@Override
	public Integer countListDominiAttCompDaAssociare(long idAttivitaComponente,
			String search) {
		// TODO Auto-generated method stub
		return modellazioneDao.countListDominiAttCompDaAssociare(idAttivitaComponente,search);
	}

	@Override
	public List<CompTecAttCompDto> getListCompTecAttComp(long idAttivitaComponente, int getiDisplayStart,
			int getiDisplayLength, int getiSortCol_0, String getsSortDir_0,
			String getsSearch) {
		List<Object[]> obj = modellazioneDao.getListCompTecAttComp(idAttivitaComponente, getiDisplayStart, getiDisplayLength,  getiSortCol_0,  getsSortDir_0, getsSearch) ;
		List<CompTecAttCompDto> l= new ArrayList<CompTecAttCompDto>();
		if(obj !=null){
			for(int i=0; i<obj.size();i++){
				CompTecAttCompDto t= new CompTecAttCompDto();
				t.setIdAssociazione((Long)obj.get(i)[0]);
				t.setDescrizione((String)obj.get(i)[1]);
				t.setCodice((String)obj.get(i)[2]);
				if(obj.get(i)[3] !=null){
					t.setAutore((String)obj.get(i)[3]);
				}
				if(obj.get(i)[4] !=null){
					t.setDataPubblicazione((Date)obj.get(i)[4]);
				}
				if(obj.get(i)[5] !=null){
					t.setVersione((String)obj.get(i)[5]);
				}
				
				t.setIdTplCompTecnico((Long)obj.get(i)[6]);
				CrsTplCompTecnico tpl= this.cerca(CrsTplCompTecnico.class, t.getIdTplCompTecnico());
				t.setTipoCompTecnico(tpl.getDescrizione());
				l.add(t);

			
			}
		}
		return l;
	}

	@Override
	public Integer countListCompTecAttComp(long idAttivitaComponente,
			String getsSearch) {
		// TODO Auto-generated method stub
		return modellazioneDao.countListCompTecAttComp(idAttivitaComponente,getsSearch);
	}
	

	@Override
	public List<CrsCompTecnicoDto> getListCompTecAttCompDaAssociare(long idAttivitaComponente, int getiDisplayStart,
			int getiDisplayLength, int getiSortCol_0, String getsSortDir_0,
			String getsSearch) {
		List<Object[]> obj = modellazioneDao.getListCompTecAttCompDaAssociare(idAttivitaComponente, getiDisplayStart, getiDisplayLength,  getiSortCol_0,  getsSortDir_0, getsSearch) ;
		List<CrsCompTecnicoDto> l= new ArrayList<CrsCompTecnicoDto>();
		
			if(obj !=null){
				for(int i=0; i<obj.size();i++){
					CrsCompTecnicoDto t= new CrsCompTecnicoDto();
					t.setId((Long)obj.get(i)[0]);
					t.setDescrizione((String)obj.get(i)[1]);
					t.setCodice((String)obj.get(i)[2]);
					if(obj.get(i)[3] !=null){
						t.setAutore((String)obj.get(i)[3]);
					}
					if(obj.get(i)[4] !=null){
						t.setDataPubblicazione((Date)obj.get(i)[4]);
					}
					if(obj.get(i)[5] !=null){
						t.setVersione((String)obj.get(i)[5]);
					}
					
					t.setIdTplCompTecnico((Long)obj.get(i)[6]);
					CrsTplCompTecnico tpl= this.cerca(CrsTplCompTecnico.class, t.getIdTplCompTecnico());
					t.setTipoCompTecnico(tpl.getDescrizione());
					l.add(t);
				}
			}
		return l;
	}

	@Override
	public Integer countListCompTecAttCompDaAssociare(
			long idAttivitaComponente, String getsSearch) {
		// TODO Auto-generated method stub
		return modellazioneDao.countListCompTecAttCompDaAssociare(idAttivitaComponente,getsSearch);
	}



	
	@Override
	public List<DocMediaAttCompDto> getDocMediaProcesso(DocumentiProcessoPaginator filter) {
		
		List<Object[]> obj = modellazioneDao.getDocMediaProcesso(filter);
		List<DocMediaAttCompDto> l= new ArrayList<DocMediaAttCompDto>();
		
		if(obj !=null){
			for(int i=0; i<obj.size();i++){
				DocMediaAttCompDto t= new DocMediaAttCompDto();
				t.setIdAssociazione((Long)obj.get(i)[0]);
				t.setIdDoc((Long)obj.get(i)[1]);
				t.setNomeFile((String)obj.get(i)[2]);
				t.setDescrizione((String)obj.get(i)[3]);
				t.setDataInizio((Date)obj.get(i)[4]);

				l.add(t);
			}
		}
		
		return l;
	}


	@Override
	public Integer countDocMediaProcesso(DocumentiProcessoPaginator filter) {
		// TODO Auto-generated method stub
		return modellazioneDao.countDocMediaProcesso(filter);
	}


	@Override
	public List<CrsDocumentiMedia> getDocProcessoDaAssociare(DocumentiProcessoPaginator filter) {
		
		List<Object[]> obj = modellazioneDao.getDocProcessoDaAssociare(filter);
		List<CrsDocumentiMedia> l= new ArrayList<CrsDocumentiMedia>();
		
		if(obj !=null){
			for(int i=0; i<obj.size();i++){
				CrsDocumentiMedia t= new CrsDocumentiMedia();
				t.setId((Long)obj.get(i)[0]);
				t.setNomeFile((String)obj.get(i)[1]);
				t.setDescrizione((String)obj.get(i)[2]);
				t.setDataInizio((Date)obj.get(i)[3]);

				l.add(t);
			}
		}
		
		return l;
	}

	@Override
	public Integer countDocProcessoDaAssociare(DocumentiProcessoPaginator filter) {
		// TODO Auto-generated method stub
		return modellazioneDao.countDocProcessoDaAssociare(filter);
	}
	
	
	
	@Override
	public List<DomAttCompDto> getDominiProcesso(DominiProcessoPaginator filter) {
		
		List<Object[]> obj = modellazioneDao.getDominiProcesso(filter);
		List<DomAttCompDto> l= new ArrayList<DomAttCompDto>();
		
		if(obj !=null){
			for(int i=0; i<obj.size();i++){
				DomAttCompDto t= new DomAttCompDto();
				t.setIdAssociazione((Long)obj.get(i)[0]);
				t.setDescrizione((String)obj.get(i)[1]);
				t.setDataInizio((Date)obj.get(i)[2]);

				l.add(t);
			}
		}
		
		return l;
	}


	@Override
	public Integer countDominiProcesso(DominiProcessoPaginator filter) {
		// TODO Auto-generated method stub
		return modellazioneDao.countDominiProcesso(filter);
	}


	@Override
	public List<CrsDomini> getDominiProcessoDaAssociare(DominiProcessoPaginator filter) {
		
		List<Object[]> obj = modellazioneDao.getDominiProcessoDaAssociare(filter);
		List<CrsDomini> l= new ArrayList<CrsDomini>();
		
		if(obj !=null){
			for(int i=0; i<obj.size();i++){
				CrsDomini t= new CrsDomini();
				t.setId((Long)obj.get(i)[0]);
				t.setDescrizione((String)obj.get(i)[1]);
				t.setDataInizio((Date)obj.get(i)[2]);

				l.add(t);
			}
		}
		
		return l;
	}

	@Override
	public Integer countDominiProcessoDaAssociare(DominiProcessoPaginator filter) {
		// TODO Auto-generated method stub
		return modellazioneDao.countDominiProcessoDaAssociare(filter);
	}
	
	
	@Override
	public List<CompTecAttCompDto> getCompTecProcesso(CompTecniciProcessoPaginator filter) {
		
		List<Object[]> obj = modellazioneDao.getCompTecProcesso(filter);
		List<CompTecAttCompDto> l = new ArrayList<CompTecAttCompDto>();
		
		if(obj !=null){
			for(int i=0; i<obj.size();i++){
				CompTecAttCompDto t= new CompTecAttCompDto();
				t.setIdAssociazione((Long)obj.get(i)[0]);
				t.setDescrizione((String)obj.get(i)[1]);
				t.setCodice((String)obj.get(i)[2]);
				if(obj.get(i)[3] !=null){
					t.setAutore((String)obj.get(i)[3]);
				}
				if(obj.get(i)[4] !=null){
					t.setDataPubblicazione((Date)obj.get(i)[4]);
				}
				if(obj.get(i)[5] !=null){
					t.setVersione((String)obj.get(i)[5]);
				}
				
				t.setIdTplCompTecnico((Long)obj.get(i)[6]);
				CrsTplCompTecnico tpl= this.cerca(CrsTplCompTecnico.class, t.getIdTplCompTecnico());
				t.setTipoCompTecnico(tpl.getDescrizione());
				l.add(t);

			
			}
		}
		return l;
	}

	@Override
	public Integer countCompTecProcesso(CompTecniciProcessoPaginator filter) {
		// TODO Auto-generated method stub
		return modellazioneDao.countCompTecProcesso(filter);
	}
	
	
	@Override
	public List<CrsCompTecnicoDto> getCompTecProcessoDaAssociare(CompTecniciProcessoPaginator filter) {
		
		List<Object[]> obj = modellazioneDao.getCompTecProcessoDaAssociare(filter);
		List<CrsCompTecnicoDto> l= new ArrayList<CrsCompTecnicoDto>();
		
		if(obj !=null){
			for(int i=0; i<obj.size();i++){
				CrsCompTecnicoDto t= new CrsCompTecnicoDto();
				t.setId((Long)obj.get(i)[0]);
				t.setDescrizione((String)obj.get(i)[1]);
				t.setCodice((String)obj.get(i)[2]);
				if(obj.get(i)[3] !=null){
					t.setAutore((String)obj.get(i)[3]);
				}
				if(obj.get(i)[4] !=null){
					t.setDataPubblicazione((Date)obj.get(i)[4]);
				}
				if(obj.get(i)[5] !=null){
					t.setVersione((String)obj.get(i)[5]);
				}
				
				t.setIdTplCompTecnico((Long)obj.get(i)[6]);
				CrsTplCompTecnico tpl= this.cerca(CrsTplCompTecnico.class, t.getIdTplCompTecnico());
				t.setTipoCompTecnico(tpl.getDescrizione());
				l.add(t);
			}
		}
		return l;
	}

	@Override
	public Integer countCompTecProcessoDaAssociare(CompTecniciProcessoPaginator filter) {
		// TODO Auto-generated method stub
		return modellazioneDao.countCompTecProcessoDaAssociare(filter);
	}
	
	
	
	@Override
	public List<DocMediaAttCompDto> getDocMediaSottoProcesso(DocumentiSottoProcessoPaginator filter) {
		
		List<Object[]> obj = modellazioneDao.getDocMediaSottoProcesso(filter);
		List<DocMediaAttCompDto> l= new ArrayList<DocMediaAttCompDto>();
		
		if(obj !=null){
			for(int i=0; i<obj.size();i++){
				DocMediaAttCompDto t= new DocMediaAttCompDto();
				t.setIdAssociazione((Long)obj.get(i)[0]);
				t.setIdDoc((Long)obj.get(i)[1]);
				t.setNomeFile((String)obj.get(i)[2]);
				t.setDescrizione((String)obj.get(i)[3]);
				t.setDataInizio((Date)obj.get(i)[4]);

				l.add(t);
			}
		}
		
		return l;
	}


	@Override
	public Integer countDocMediaSottoProcesso(DocumentiSottoProcessoPaginator filter) {
		// TODO Auto-generated method stub
		return modellazioneDao.countDocMediaSottoProcesso(filter);
	}


	@Override
	public List<CrsDocumentiMedia> getDocSottoProcessoDaAssociare(DocumentiSottoProcessoPaginator filter) {
		
		List<Object[]> obj = modellazioneDao.getDocSottoProcessoDaAssociare(filter);
		List<CrsDocumentiMedia> l= new ArrayList<CrsDocumentiMedia>();
		
		if(obj !=null){
			for(int i=0; i<obj.size();i++){
				CrsDocumentiMedia t= new CrsDocumentiMedia();
				t.setId((Long)obj.get(i)[0]);
				t.setNomeFile((String)obj.get(i)[1]);
				t.setDescrizione((String)obj.get(i)[2]);
				t.setDataInizio((Date)obj.get(i)[3]);

				l.add(t);
			}
		}
		
		return l;
	}

	@Override
	public Integer countDocSottoProcessoDaAssociare(DocumentiSottoProcessoPaginator filter) {
		// TODO Auto-generated method stub
		return modellazioneDao.countDocSottoProcessoDaAssociare(filter);
	}
	
	
	
	@Override
	public List<DomAttCompDto> getDominiSottoProcesso(DominiSottoProcessoPaginator filter) {
		
		List<Object[]> obj = modellazioneDao.getDominiSottoProcesso(filter);
		List<DomAttCompDto> l= new ArrayList<DomAttCompDto>();
		
		if(obj !=null){
			for(int i=0; i<obj.size();i++){
				DomAttCompDto t= new DomAttCompDto();
				t.setIdAssociazione((Long)obj.get(i)[0]);
				t.setDescrizione((String)obj.get(i)[1]);
				t.setDataInizio((Date)obj.get(i)[2]);

				l.add(t);
			}
		}
		
		return l;
	}


	@Override
	public Integer countDominiSottoProcesso(DominiSottoProcessoPaginator filter) {
		// TODO Auto-generated method stub
		return modellazioneDao.countDominiSottoProcesso(filter);
	}


	@Override
	public List<CrsDomini> getDominiSottoProcessoDaAssociare(DominiSottoProcessoPaginator filter) {
		
		List<Object[]> obj = modellazioneDao.getDominiSottoProcessoDaAssociare(filter);
		List<CrsDomini> l= new ArrayList<CrsDomini>();
		
		if(obj !=null){
			for(int i=0; i<obj.size();i++){
				CrsDomini t= new CrsDomini();
				t.setId((Long)obj.get(i)[0]);
				t.setDescrizione((String)obj.get(i)[1]);
				t.setDataInizio((Date)obj.get(i)[2]);

				l.add(t);
			}
		}
		
		return l;
	}

	@Override
	public Integer countDominiSottoProcessoDaAssociare(DominiSottoProcessoPaginator filter) {
		// TODO Auto-generated method stub
		return modellazioneDao.countDominiSottoProcessoDaAssociare(filter);
	}
	
	
	@Override
	public List<CompTecAttCompDto> getCompTecSottoProcesso(CompTecniciSottoProcessoPaginator filter) {
		
		List<Object[]> obj = modellazioneDao.getCompTecSottoProcesso(filter);
		List<CompTecAttCompDto> l = new ArrayList<CompTecAttCompDto>();
		
		if(obj !=null){
			for(int i=0; i<obj.size();i++){
				CompTecAttCompDto t= new CompTecAttCompDto();
				t.setIdAssociazione((Long)obj.get(i)[0]);
				t.setDescrizione((String)obj.get(i)[1]);
				t.setCodice((String)obj.get(i)[2]);
				if(obj.get(i)[3] !=null){
					t.setAutore((String)obj.get(i)[3]);
				}
				if(obj.get(i)[4] !=null){
					t.setDataPubblicazione((Date)obj.get(i)[4]);
				}
				if(obj.get(i)[5] !=null){
					t.setVersione((String)obj.get(i)[5]);
				}
				
				t.setIdTplCompTecnico((Long)obj.get(i)[6]);
				CrsTplCompTecnico tpl= this.cerca(CrsTplCompTecnico.class, t.getIdTplCompTecnico());
				t.setTipoCompTecnico(tpl.getDescrizione());
				l.add(t);

			
			}
		}
		return l;
	}

	@Override
	public Integer countCompTecSottoProcesso(CompTecniciSottoProcessoPaginator filter) {
		// TODO Auto-generated method stub
		return modellazioneDao.countCompTecSottoProcesso(filter);
	}
	
	
	@Override
	public List<CrsCompTecnicoDto> getCompTecSottoProcessoDaAssociare(CompTecniciSottoProcessoPaginator filter) {
		
		List<Object[]> obj = modellazioneDao.getCompTecSottoProcessoDaAssociare(filter);
		List<CrsCompTecnicoDto> l= new ArrayList<CrsCompTecnicoDto>();
		
		if(obj !=null){
			for(int i=0; i<obj.size();i++){
				CrsCompTecnicoDto t= new CrsCompTecnicoDto();
				t.setId((Long)obj.get(i)[0]);
				t.setDescrizione((String)obj.get(i)[1]);
				t.setCodice((String)obj.get(i)[2]);
				if(obj.get(i)[3] !=null){
					t.setAutore((String)obj.get(i)[3]);
				}
				if(obj.get(i)[4] !=null){
					t.setDataPubblicazione((Date)obj.get(i)[4]);
				}
				if(obj.get(i)[5] !=null){
					t.setVersione((String)obj.get(i)[5]);
				}
				
				t.setIdTplCompTecnico((Long)obj.get(i)[6]);
				CrsTplCompTecnico tpl= this.cerca(CrsTplCompTecnico.class, t.getIdTplCompTecnico());
				t.setTipoCompTecnico(tpl.getDescrizione());
				l.add(t);
			}
		}
		return l;
	}

	@Override
	public Integer countCompTecSottoProcessoDaAssociare(CompTecniciSottoProcessoPaginator filter) {
		// TODO Auto-generated method stub
		return modellazioneDao.countCompTecSottoProcessoDaAssociare(filter);
	}
	
	
	
	
	@Override
	public List<DocMediaAttCompDto> getDocMediaAttivitaDettaglio(DocumentiAttivitaDettaglioPaginator filter) {
		
		List<Object[]> obj = modellazioneDao.getDocMediaAttivitaDettaglio(filter);
		List<DocMediaAttCompDto> l= new ArrayList<DocMediaAttCompDto>();
		
		if(obj !=null){
			for(int i=0; i<obj.size();i++){
				DocMediaAttCompDto t= new DocMediaAttCompDto();
				t.setIdAssociazione((Long)obj.get(i)[0]);
				t.setIdDoc((Long)obj.get(i)[1]);
				t.setNomeFile((String)obj.get(i)[2]);
				t.setDescrizione((String)obj.get(i)[3]);
				t.setDataInizio((Date)obj.get(i)[4]);

				l.add(t);
			}
		}
		
		return l;
	}


	@Override
	public Integer countDocMediaAttivitaDettaglio(DocumentiAttivitaDettaglioPaginator filter) {
		// TODO Auto-generated method stub
		return modellazioneDao.countDocMediaAttivitaDettaglio(filter);
	}


	@Override
	public List<CrsDocumentiMedia> getDocAttivitaDettaglioDaAssociare(DocumentiAttivitaDettaglioPaginator filter) {
		
		List<Object[]> obj = modellazioneDao.getDocAttivitaDettaglioDaAssociare(filter);
		List<CrsDocumentiMedia> l= new ArrayList<CrsDocumentiMedia>();
		
		if(obj !=null){
			for(int i=0; i<obj.size();i++){
				CrsDocumentiMedia t= new CrsDocumentiMedia();
				t.setId((Long)obj.get(i)[0]);
				t.setNomeFile((String)obj.get(i)[1]);
				t.setDescrizione((String)obj.get(i)[2]);
				t.setDataInizio((Date)obj.get(i)[3]);

				l.add(t);
			}
		}
		
		return l;
	}

	@Override
	public Integer countDocAttivitaDettaglioDaAssociare(DocumentiAttivitaDettaglioPaginator filter) {
		// TODO Auto-generated method stub
		return modellazioneDao.countDocAttivitaDettaglioDaAssociare(filter);
	}
	
	
	
	@Override
	public List<DomAttCompDto> getDominiAttivitaDettaglio(DominiAttivitaDettaglioPaginator filter) {
		
		List<Object[]> obj = modellazioneDao.getDominiAttivitaDettaglio(filter);
		List<DomAttCompDto> l= new ArrayList<DomAttCompDto>();
		
		if(obj !=null){
			for(int i=0; i<obj.size();i++){
				DomAttCompDto t= new DomAttCompDto();
				t.setIdAssociazione((Long)obj.get(i)[0]);
				t.setDescrizione((String)obj.get(i)[1]);
				t.setDataInizio((Date)obj.get(i)[2]);

				l.add(t);
			}
		}
		
		return l;
	}


	@Override
	public Integer countDominiAttivitaDettaglio(DominiAttivitaDettaglioPaginator filter) {
		// TODO Auto-generated method stub
		return modellazioneDao.countDominiAttivitaDettaglio(filter);
	}


	@Override
	public List<CrsDomini> getDominiAttivitaDettaglioDaAssociare(DominiAttivitaDettaglioPaginator filter) {
		
		List<Object[]> obj = modellazioneDao.getDominiAttivitaDettaglioDaAssociare(filter);
		List<CrsDomini> l= new ArrayList<CrsDomini>();
		
		if(obj !=null){
			for(int i=0; i<obj.size();i++){
				CrsDomini t= new CrsDomini();
				t.setId((Long)obj.get(i)[0]);
				t.setDescrizione((String)obj.get(i)[1]);
				t.setDataInizio((Date)obj.get(i)[2]);

				l.add(t);
			}
		}
		
		return l;
	}

	@Override
	public Integer countDominiAttivitaDettaglioDaAssociare(DominiAttivitaDettaglioPaginator filter) {
		// TODO Auto-generated method stub
		return modellazioneDao.countDominiAttivitaDettaglioDaAssociare(filter);
	}
	
	
	
	@Override
	public List<CompTecAttCompDto> getCompTecAttivitaDettaglio(CompTecniciAttivitaDettaglioPaginator filter) {
		
		List<Object[]> obj = modellazioneDao.getCompTecAttivitaDettaglio(filter);
		List<CompTecAttCompDto> l = new ArrayList<CompTecAttCompDto>();
		
		if(obj !=null){
			for(int i=0; i<obj.size();i++){
				CompTecAttCompDto t= new CompTecAttCompDto();
				t.setIdAssociazione((Long)obj.get(i)[0]);
				t.setDescrizione((String)obj.get(i)[1]);
				t.setCodice((String)obj.get(i)[2]);
				if(obj.get(i)[3] !=null){
					t.setAutore((String)obj.get(i)[3]);
				}
				if(obj.get(i)[4] !=null){
					t.setDataPubblicazione((Date)obj.get(i)[4]);
				}
				if(obj.get(i)[5] !=null){
					t.setVersione((String)obj.get(i)[5]);
				}
				
				t.setIdTplCompTecnico((Long)obj.get(i)[6]);
				CrsTplCompTecnico tpl= this.cerca(CrsTplCompTecnico.class, t.getIdTplCompTecnico());
				t.setTipoCompTecnico(tpl.getDescrizione());
				l.add(t);

			
			}
		}
		return l;
	}

	@Override
	public Integer countCompTecAttivitaDettaglio(CompTecniciAttivitaDettaglioPaginator filter) {
		// TODO Auto-generated method stub
		return modellazioneDao.countCompTecAttivitaDettaglio(filter);
	}
	
	
	@Override
	public List<CrsCompTecnicoDto> getCompTecAttivitaDettaglioDaAssociare(CompTecniciAttivitaDettaglioPaginator filter) {
		
		List<Object[]> obj = modellazioneDao.getCompTecAttivitaDettaglioDaAssociare(filter);
		List<CrsCompTecnicoDto> l= new ArrayList<CrsCompTecnicoDto>();
		
		if(obj !=null){
			for(int i=0; i<obj.size();i++){
				CrsCompTecnicoDto t= new CrsCompTecnicoDto();
				t.setId((Long)obj.get(i)[0]);
				t.setDescrizione((String)obj.get(i)[1]);
				t.setCodice((String)obj.get(i)[2]);
				if(obj.get(i)[3] !=null){
					t.setAutore((String)obj.get(i)[3]);
				}
				if(obj.get(i)[4] !=null){
					t.setDataPubblicazione((Date)obj.get(i)[4]);
				}
				if(obj.get(i)[5] !=null){
					t.setVersione((String)obj.get(i)[5]);
				}
				
				t.setIdTplCompTecnico((Long)obj.get(i)[6]);
				CrsTplCompTecnico tpl= this.cerca(CrsTplCompTecnico.class, t.getIdTplCompTecnico());
				t.setTipoCompTecnico(tpl.getDescrizione());
				l.add(t);
			}
		}
		return l;
	}

	@Override
	public Integer countCompTecAttivitaDettaglioDaAssociare(CompTecniciAttivitaDettaglioPaginator filter) {
		// TODO Auto-generated method stub
		return modellazioneDao.countCompTecAttivitaDettaglioDaAssociare(filter);
	}
	
	
	
	

	/*
	 *  O P    C O M U N I________________
	 */

	@Override
	public void remove(Object o) {
		modellazioneDao.remove(o);

	}

	@Override
	public <T> T salva(T obj) {
		return modellazioneDao.salva(obj);
	}
	@Override
	public <T> T cerca(Class<T> obj, Object pk) {
		// TODO Auto-generated method stub
		return modellazioneDao.cerca(obj , pk);
	}

	









}
