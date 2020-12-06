package it.tecnet.crs.mod.jpa.dao;

import it.tecnet.crs.componenti.web.bean.NormativaTablePaginator;
import it.tecnet.crs.jpa.model.CrsAssAcClasse;
import it.tecnet.crs.jpa.model.CrsAssAdClasse;
import it.tecnet.crs.jpa.model.CrsAssProcessoClasse;
import it.tecnet.crs.jpa.model.CrsAssSottoprocessoClasse;
import it.tecnet.crs.mod.jpa.model.CrsArea;
import it.tecnet.crs.mod.jpa.model.CrsAttivitaComponente;
import it.tecnet.crs.mod.jpa.model.CrsAttivitaDettaglio;
import it.tecnet.crs.mod.jpa.model.CrsProcesso;
import it.tecnet.crs.mod.jpa.model.CrsSottoprocesso;
import it.tecnet.crs.mod.web.bean.AreaTablePaginator;
import it.tecnet.crs.mod.web.bean.AttivitaComponenteTablePaginator;
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
import it.tecnet.crs.mod.web.bean.ProcessoTablePaginator;
import it.tecnet.crs.mod.web.bean.SottoProcessoTablePaginator;
import it.tecnet.crs.util.ApplicationUtil;
import it.tecnet.crs.util.TipoNormativaEnum;
import it.tecnet.crs.web.beans.AssAcClasse;
import it.tecnet.crs.web.beans.AssAdClasse;
import it.tecnet.crs.web.beans.AssProcessoClasse;
import it.tecnet.crs.web.beans.AssSottoProcessoClasse;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

public class ModellazioneDaoImpl implements ModellazioneDao{

	protected static Logger log = Logger.getLogger(ModellazioneDaoImpl.class);
	@PersistenceContext()
	private EntityManager em;
	public ModellazioneDaoImpl() {
	
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getIdPadriByIdAttivitaComponente(long idAttivitaComponente){
		
		String queryStr = "select a.ID_AREA, p.ID_PROCESSO, sp.ID_SOTTOPROCESSO from CRS_ATTIVITA_COMPONENTE AS AC " +
							"join CRS_SOTTOPROCESSO as sp on ac.ID_SOTTOPROCESSO = sp.ID_SOTTOPROCESSO " +
							"join CRS_PROCESSO as p on sp.ID_PROCESSO = p.ID_PROCESSO " +
							"join CRS_AREA as a on p.ID_AREA = a.ID_AREA " +
							" where ac.ID_ATTIVITA_COMPONENTE = " + idAttivitaComponente;
		
		Query querySel = em.createNativeQuery(queryStr);

		List<Object[]> returnLisyt =  querySel.getResultList();
		
		return returnLisyt;
	}
	
	/*
	 * 		AREA
	 * */
	@Transactional
	public void saveArea(CrsArea area){
		
		try{
			em.merge(area);
		}catch(Exception e){
			log.error(e);
			e.printStackTrace();
		}
			
	}
	
	@Transactional
	public void deleteArea(long idArea){
		Validate.notNull(idArea, "Id " + idArea + " non valorizzato.");
		
		CrsArea area = em.find(CrsArea.class, idArea);
		
		Validate.notNull(area, "L\'area con id " + idArea + " non esiste.");

		em.remove(area);

	}
	
	@SuppressWarnings("unchecked")
	public List<CrsArea> getListaAree (AreaTablePaginator filter){
		String queryStr = "SELECT " + 
						"ID_AREA, " +
						"DESCRIZIONE, " + 
						"DATA_INIZIO, " + 
						"DATA_FINE, " + 
						"STATO " + 
						"FROM CRS_AREA where 1=1 ";
		
		if(StringUtils.isNotEmpty(filter.getsSearch())){
			queryStr += " and ("; 
			queryStr += " UPPER(descrizione) like UPPER('%" + filter.getsSearch() + "%') ";
			queryStr += " )";
			
		}
		
		if(StringUtils.equalsIgnoreCase(filter.getStato(), "A")){
			queryStr += " and stato = 'A' "; 
		}else if(StringUtils.equalsIgnoreCase(filter.getStato(), "D"))
		{
			queryStr += " and stato = 'D' "; 
		}
		
		queryStr += " ORDER BY " + (filter.getiSortCol_0()+1) + " " + filter.getsSortDir_0();
		queryStr += " OFFSET " + filter.getiDisplayStart() + " ROWS FETCH NEXT " + filter.getiDisplayLength() + " ROWS ONLY";
		
		Query querySel = em.createNativeQuery(queryStr, CrsArea.class);
		
//		if( pageNumber != null  && pageSize != null && pageSize > 0)
//		{
//			querySel.setFirstResult(pageNumber);//((pageNumber-1) * pageSize);
//			querySel.setMaxResults(pageSize);
//		}
		
		List<CrsArea> listaAree = querySel.getResultList();
		
		return listaAree;	
	}
	
	public Integer countAllAree(AreaTablePaginator filter){
		
		String queryStr = "SELECT count(id_area) " +
							"FROM CRS_AREA where 1=1 ";
		
		if(StringUtils.isNotEmpty(filter.getsSearch())){
			queryStr += " and ("; 
			queryStr += " UPPER(descrizione) like UPPER('%" + filter.getsSearch() + "%') ";
			queryStr += " )";
			
		}
		
		Query querySel = em.createNativeQuery(queryStr);
		

		Integer count =  (Integer)querySel.getSingleResult();
		return count == null ? 0 : count;
	}
	
	@Transactional
	public void disableArea( Long idArea )
	{
		
		CrsArea area = em.find(CrsArea.class, idArea);
		
		Validate.notNull(area, "L\'area con id " + idArea + " non esiste.");

		area.setStato("D");		
		em.merge(area);

	}
	
	@Transactional
	public void enableArea( Long idArea )
	{
		
		CrsArea area = em.find(CrsArea.class, idArea);
		
		Validate.notNull(area, "L\'area con id " + idArea + " non esiste.");

		area.setStato("A");		
		em.merge(area);

	}
	
	
	public CrsArea getAreaById(Long idArea)
	{
		
		CrsArea area = em.find(CrsArea.class, idArea);
		
		Validate.notNull(area, "L\'area con id " + idArea + " non esiste.");
		
		return area;
	}
	
	
	/*
	 * 		PROCESSO
	 * */
	
	@Transactional
	public void saveProcesso(CrsProcesso processo){
		
		try{
			em.merge(processo);
		}catch(Exception e){
			log.error(e);
			e.printStackTrace();
		}
			
	}
	
	@Transactional
	public void deleteProcesso(long idProcesso){
		
		Validate.notNull(idProcesso, "Id " + idProcesso + " non valorizzato.");
		
		CrsProcesso processo = em.find(CrsProcesso.class, idProcesso);
		
		Validate.notNull(processo, "Il Processo con id " + idProcesso + " non esiste.");

		em.remove(processo);
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getListaProcessi (ProcessoTablePaginator filter, long idUtente){
	
		String queryStr = "SELECT " +
							"ID_PROCESSO, P.ORDINAMENTO, P.DESCRIZIONE, A.DESCRIZIONE, P.DATA_INIZIO, P.DATA_FINE, " +
							"P.STATO, P.ID_AREA, P.PUBBLICAZIONE " +
							"FROM CRS_PROCESSO P INNER JOIN CRS_AREA A  on P.id_area = A.id_area " +
							"where 1 = 1";
		
		
		if(filter.getIdArea() != 0){
			queryStr += " and P.ID_AREA = " + filter.getIdArea();
		}
		
		
		if(StringUtils.isNotEmpty(filter.getsSearch())){
			queryStr += " and ("; 
			queryStr += " UPPER(P.descrizione) like UPPER('%" + filter.getsSearch() + "%') ";
			queryStr += " )";
			
		}
		
		if(StringUtils.equalsIgnoreCase(filter.getStato(), "A")){
			queryStr += " and P.stato = 'A' "; 
		}else if(StringUtils.equalsIgnoreCase(filter.getStato(), "D"))
		{
			queryStr += " and P.stato = 'D' "; 
		}
		
		
		queryStr += " ORDER BY " + (filter.getiSortCol_0()+1) + " " ;
		
		if(filter.getIdArea() == 0){
			queryStr += filter.getsSortDir_0() + " OFFSET " + filter.getiDisplayStart() + " ROWS FETCH NEXT " + filter.getiDisplayLength() + " ROWS ONLY";			
		}
		
		Query querySel = em.createNativeQuery(queryStr);
		
//		if( pageNumber != null  && pageSize != null && pageSize > 0)
//		{
//			querySel.setFirstResult(pageNumber);//((pageNumber-1) * pageSize);
//			querySel.setMaxResults(pageSize);
//		}
		
		
		List<Object[]> listaProcesso = querySel.getResultList();
		return listaProcesso;	
	}
	
	public Integer countAllProcessi(ProcessoTablePaginator filter, long idUtente){
		
		String queryStr = "SELECT count(id_processo) " +
							"FROM CRS_PROCESSO P INNER JOIN CRS_AREA A  on P.id_area = A.id_area " +
							"where 1 = 1" ;
		
		if(StringUtils.isNotEmpty(filter.getsSearch())){
			queryStr += " and ("; 
			queryStr += " UPPER(P.descrizione) like UPPER('%" + filter.getsSearch() + "%') ";
			queryStr += " )";
			
		}
		
		Query querySel = em.createNativeQuery(queryStr);
		

		Integer count =  (Integer)querySel.getSingleResult();
		return count == null ? 0 : count;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<CrsArea> getComboArea(){
		String queryStr = "SELECT " + 
						"ID_AREA, " + 
						"DESCRIZIONE " + 
						"FROM CRS_AREA " +
						"ORDER BY DESCRIZIONE ";
						//"WHERE DATA_FINE > CONVERT(date, getdate()) ";
		
		Query querySel = em.createNativeQuery(queryStr, CrsArea.class);
		
		List<CrsArea> listaAree = querySel.getResultList();
		
		return listaAree;	
	}
	
	
	@Transactional
	public void disableProcesso(Long idProcesso){
		
		CrsProcesso processo = em.find(CrsProcesso.class, idProcesso);
		
		Validate.notNull(processo, "Il Processo con id " + idProcesso + " non esiste.");

		processo.setStato("D");		
		em.merge(processo);
		
	}
	
	@Transactional
	public void pubblicaProcesso(Long idProcesso){
		
		CrsProcesso processo = em.find(CrsProcesso.class, idProcesso);
		
		Validate.notNull(processo, "Il Processo con id " + idProcesso + " non esiste.");

		processo.setPubblicazione("S");		
		em.merge(processo);
		
		
	}
	
	
	public CrsProcesso getProcessoById(Long idProcesso){
		
		CrsProcesso processo = em.find(CrsProcesso.class, idProcesso);
		
		Validate.notNull(processo, "Il Processo con id " + idProcesso + " non esiste.");
		
		return processo;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getAssociabiliCircolariInpsTable(ProcessoTablePaginator filter){
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String currentDate= dateFormat.format(date);
		
		String queryStr = "SELECT " +
							"ID_CIRCOLARI_INPS, " +
							"DESCR_SINTETICA, DESCR_DETTAGLIO, DATA_EMISSIONE, " +
							"CODICE, OGGETTO, " +
							"DIREZIONE_EMITTENTE1, " +
							"DIREZIONE_EMITTENTE2, " +
							"DIREZIONE_EMITTENTE3, " +
							"DIREZIONE_EMITTENTE4, " +
							"DIREZIONE_EMITTENTE5, " +
							"DIREZIONE_EMITTENTE6, " +
							"SOMMARIO, " +
							"ANNO, " +
							"DATA_INIZIO, DATA_FINE " +
							"FROM " +
							"CRS_CIRCOLARI_INPS " +
							" where 1 = 1 AND (DATA_FINE > '"+currentDate+"' OR DATA_FINE IS NULL) " + 
							" and ID_CIRCOLARI_INPS not in ";
		
		if(filter.getIdProcesso() > 0){
			
			queryStr += "(select pc.ID_NORMATIVA from CRS_ASS_PROCESSO_CLASSE as pc " + 
						"where pc.ID_PROCESSO = " + filter.getIdProcesso() + " and pc.ID_TIPO = " + TipoNormativaEnum.CIRCOLARI_INPS.getCodice() + ")";
			
		}
		if(filter.getIdSottoProcesso() > 0){
			
			queryStr += "(select pc.ID_NORMATIVA from CRS_ASS_SOTTOPROCESSO_CLASSE as pc " + 
						"where pc.ID_SOTTOPROCESSO = " + filter.getIdSottoProcesso() + " and pc.ID_TIPO = " + TipoNormativaEnum.CIRCOLARI_INPS.getCodice() + ")";
			
		}
		if(filter.getIdAttivitaComponente() > 0){
			
			queryStr += "(select pc.ID_NORMATIVA from CRS_ASS_AC_CLASSE as pc " + 
						"where pc.ID_ATTIVITA_COMPONENTE = " + filter.getIdAttivitaComponente() + " and pc.ID_TIPO = " + TipoNormativaEnum.CIRCOLARI_INPS.getCodice() + ")";
			
		}
		if(filter.getIdAttivitaDettaglio() > 0){
			
			queryStr += "(select pc.ID_NORMATIVA from CRS_ASS_AD_CLASSE as pc " + 
						"where pc.ID_ATTIVITA_DETTAGLIO = " + filter.getIdAttivitaDettaglio() + " and pc.ID_TIPO = " + TipoNormativaEnum.CIRCOLARI_INPS.getCodice() + ")";
			
		}
		
		if(StringUtils.isNotEmpty(filter.getsSearch())){
			queryStr += " and ("; 
			queryStr += " UPPER(OGGETTO) like UPPER('%" + filter.getsSearch() + "%') ";
			queryStr += " )";
			
		}
		
		queryStr += " ORDER BY " + (filter.getiSortCol_0()+1) + " " + filter.getsSortDir_0();
		queryStr += " OFFSET " + filter.getiDisplayStart() + " ROWS FETCH NEXT " + filter.getiDisplayLength() + " ROWS ONLY";
		
		Query querySel = em.createNativeQuery(queryStr);
		
		List<Object[]> listaReturn = querySel.getResultList();
		
		return listaReturn;
		
	}
	
	public Integer countAllAssociabiliCircolariInps(ProcessoTablePaginator filter){
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String currentDate= dateFormat.format(date);
		String queryStr = "SELECT count(ID_CIRCOLARI_INPS) " +
							"FROM " +
							"CRS_CIRCOLARI_INPS " +
							" where 1 = 1  AND (DATA_FINE > '"+currentDate+"' OR DATA_FINE IS NULL) " + 
							" and ID_CIRCOLARI_INPS not in ";
		
		if(filter.getIdProcesso() > 0){
			
			queryStr += "(select pc.ID_NORMATIVA from CRS_ASS_PROCESSO_CLASSE as pc " + 
						"where pc.ID_PROCESSO = " + filter.getIdProcesso() + " and pc.ID_TIPO = " + TipoNormativaEnum.CIRCOLARI_INPS.getCodice() + ")";
			
		}
		if(filter.getIdSottoProcesso() > 0){
			
			queryStr += "(select pc.ID_NORMATIVA from CRS_ASS_SOTTOPROCESSO_CLASSE as pc " + 
						"where pc.ID_SOTTOPROCESSO = " + filter.getIdSottoProcesso() + " and pc.ID_TIPO = " + TipoNormativaEnum.CIRCOLARI_INPS.getCodice() + ")";
			
		}
		if(filter.getIdAttivitaComponente() > 0){
			
			queryStr += "(select pc.ID_NORMATIVA from CRS_ASS_AC_CLASSE as pc " + 
						"where pc.ID_ATTIVITA_COMPONENTE = " + filter.getIdAttivitaComponente() + " and pc.ID_TIPO = " + TipoNormativaEnum.CIRCOLARI_INPS.getCodice() + ")";
			
		}
		if(filter.getIdAttivitaDettaglio() > 0){
			
			queryStr += "(select pc.ID_NORMATIVA from CRS_ASS_AD_CLASSE as pc " + 
						"where pc.ID_ATTIVITA_DETTAGLIO = " + filter.getIdAttivitaDettaglio() + " and pc.ID_TIPO = " + TipoNormativaEnum.CIRCOLARI_INPS.getCodice() + ")";
			
		}
		
		if(StringUtils.isNotEmpty(filter.getsSearch())){
			queryStr += " and ("; 
			queryStr += " UPPER(OGGETTO) like UPPER('%" + filter.getsSearch() + "%') ";
			queryStr += " )";
			
		}
		
		Query querySel = em.createNativeQuery(queryStr);

		Integer count =  (Integer)querySel.getSingleResult();
		return count == null ? 0 : count;
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getAssociabiliNoteDecreti(ProcessoTablePaginator filter){
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String currentDate= dateFormat.format(date);
		
		String queryStr = "SELECT " +
							"ID_NOTE_DECRETI, " +
							"DESCR_SINTETICA, DESCR_DETTAGLIO, DATA_EMISSIONE, " +
							"CODICE, OGGETTO, RIFERIMENTI,DATA_INIZIO, DATA_FINE " +
							"FROM " +
							"CRS_NOTE_DECRETI " +
							" where 1 = 1 AND (DATA_FINE > '"+currentDate+"' OR DATA_FINE IS NULL) " + 
							" and ID_NOTE_DECRETI not in ";
		
		if(filter.getIdProcesso() > 0){
			
			queryStr += "(select pc.ID_NORMATIVA from CRS_ASS_PROCESSO_CLASSE as pc " + 
						"where pc.ID_PROCESSO = " + filter.getIdProcesso() + " and pc.ID_TIPO = " + TipoNormativaEnum.NOTE_DESCRETI.getCodice() + ")";
			
		}
		if(filter.getIdSottoProcesso() > 0){
			
			queryStr += "(select pc.ID_NORMATIVA from CRS_ASS_SOTTOPROCESSO_CLASSE as pc " + 
						"where pc.ID_SOTTOPROCESSO = " + filter.getIdSottoProcesso() + " and pc.ID_TIPO = " + TipoNormativaEnum.NOTE_DESCRETI.getCodice() + ")";
			
		}
		if(filter.getIdAttivitaComponente() > 0){
			
			queryStr += "(select pc.ID_NORMATIVA from CRS_ASS_AC_CLASSE as pc " + 
						"where pc.ID_ATTIVITA_COMPONENTE = " + filter.getIdAttivitaComponente() + " and pc.ID_TIPO = " + TipoNormativaEnum.NOTE_DESCRETI.getCodice() + ")";
			
		}
		if(filter.getIdAttivitaDettaglio() > 0){
			
			queryStr += "(select pc.ID_NORMATIVA from CRS_ASS_AD_CLASSE as pc " + 
						"where pc.ID_ATTIVITA_DETTAGLIO = " + filter.getIdAttivitaDettaglio() + " and pc.ID_TIPO = " + TipoNormativaEnum.NOTE_DESCRETI.getCodice() + ")";
			
		}
		
		if(StringUtils.isNotEmpty(filter.getsSearch())){
			queryStr += " and ("; 
			queryStr += " UPPER(OGGETTO) like UPPER('%" + filter.getsSearch() + "%') ";
			queryStr += " )";
			
		}
		
		queryStr += " ORDER BY " + (filter.getiSortCol_0()+1) + " " + filter.getsSortDir_0();
		queryStr += " OFFSET " + filter.getiDisplayStart() + " ROWS FETCH NEXT " + filter.getiDisplayLength() + " ROWS ONLY";
		
		Query querySel = em.createNativeQuery(queryStr);
		
		List<Object[]> listaReturn = querySel.getResultList();
		
		return listaReturn;
		
	}
	
	public Integer countAllAssociabiliNoteDecreti(ProcessoTablePaginator filter){
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String currentDate= dateFormat.format(date);
		
		String queryStr = "SELECT count(ID_NOTE_DECRETI) " +
							"FROM " +
							"CRS_NOTE_DECRETI " +
							" where 1 = 1 AND (DATA_FINE > '"+currentDate+"' OR DATA_FINE IS NULL) " + 
							" and ID_NOTE_DECRETI not in ";

		if(filter.getIdProcesso() > 0){
			
			queryStr += "(select pc.ID_NORMATIVA from CRS_ASS_PROCESSO_CLASSE as pc " + 
						"where pc.ID_PROCESSO = " + filter.getIdProcesso() + " and pc.ID_TIPO = " + TipoNormativaEnum.NOTE_DESCRETI.getCodice() + ")";
			
		}
		if(filter.getIdSottoProcesso() > 0){
			
			queryStr += "(select pc.ID_NORMATIVA from CRS_ASS_SOTTOPROCESSO_CLASSE as pc " + 
						"where pc.ID_SOTTOPROCESSO = " + filter.getIdSottoProcesso() + " and pc.ID_TIPO = " + TipoNormativaEnum.NOTE_DESCRETI.getCodice() + ")";
			
		}
		if(filter.getIdAttivitaComponente() > 0){
			
			queryStr += "(select pc.ID_NORMATIVA from CRS_ASS_AC_CLASSE as pc " + 
						"where pc.ID_ATTIVITA_COMPONENTE = " + filter.getIdAttivitaComponente() + " and pc.ID_TIPO = " + TipoNormativaEnum.NOTE_DESCRETI.getCodice() + ")";
			
		}
		if(filter.getIdAttivitaDettaglio() > 0){
			
			queryStr += "(select pc.ID_NORMATIVA from CRS_ASS_AD_CLASSE as pc " + 
						"where pc.ID_ATTIVITA_DETTAGLIO = " + filter.getIdAttivitaDettaglio() + " and pc.ID_TIPO = " + TipoNormativaEnum.NOTE_DESCRETI.getCodice() + ")";
			
		}
		
		if(StringUtils.isNotEmpty(filter.getsSearch())){
			queryStr += " and ("; 
			queryStr += " UPPER(OGGETTO) like UPPER('%" + filter.getsSearch() + "%') ";
			queryStr += " )";
			
		}
		
		Query querySel = em.createNativeQuery(queryStr);

		Integer count =  (Integer)querySel.getSingleResult();
		return count == null ? 0 : count;
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getAssociabiliMessaggiInps(ProcessoTablePaginator filter){
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String currentDate= dateFormat.format(date);
		
		String queryStr = "SELECT " +
							"ID_MESSAGGI_INPS, " +
							"DESCR_SINTETICA, DESCR_DETTAGLIO, DATA_EMISSIONE, " +
							"CODICE, OGGETTO,DATA_INIZIO, DATA_FINE " +
							"FROM " +
							"CRS_MESSAGGI_INPS " +
							" where 1 = 1 AND (DATA_FINE > '"+currentDate+"' OR DATA_FINE IS NULL) " + 
							" and ID_MESSAGGI_INPS not in ";

		if(filter.getIdProcesso() > 0){
			
			queryStr += "(select pc.ID_NORMATIVA from CRS_ASS_PROCESSO_CLASSE as pc " + 
						"where pc.ID_PROCESSO = " + filter.getIdProcesso() + " and pc.ID_TIPO = " + TipoNormativaEnum.MESSAGGI_INPS.getCodice() + ")";
			
		}
		if(filter.getIdSottoProcesso() > 0){
			
			queryStr += "(select pc.ID_NORMATIVA from CRS_ASS_SOTTOPROCESSO_CLASSE as pc " + 
						"where pc.ID_SOTTOPROCESSO = " + filter.getIdSottoProcesso() + " and pc.ID_TIPO = " + TipoNormativaEnum.MESSAGGI_INPS.getCodice() + ")";
			
		}
		if(filter.getIdAttivitaComponente() > 0){
			
			queryStr += "(select pc.ID_NORMATIVA from CRS_ASS_AC_CLASSE as pc " + 
						"where pc.ID_ATTIVITA_COMPONENTE = " + filter.getIdAttivitaComponente() + " and pc.ID_TIPO = " + TipoNormativaEnum.MESSAGGI_INPS.getCodice() + ")";
			
		}
		if(filter.getIdAttivitaDettaglio() > 0){
			
			queryStr += "(select pc.ID_NORMATIVA from CRS_ASS_AD_CLASSE as pc " + 
						"where pc.ID_ATTIVITA_DETTAGLIO = " + filter.getIdAttivitaDettaglio() + " and pc.ID_TIPO = " + TipoNormativaEnum.MESSAGGI_INPS.getCodice() + ")";
			
		}
		
		if(StringUtils.isNotEmpty(filter.getsSearch())){
			queryStr += " and ("; 
			queryStr += " UPPER(OGGETTO) like UPPER('%" + filter.getsSearch() + "%') ";
			queryStr += " )";
			
		}
		
		queryStr += " ORDER BY " + (filter.getiSortCol_0()+1) + " " + filter.getsSortDir_0();
		queryStr += " OFFSET " + filter.getiDisplayStart() + " ROWS FETCH NEXT " + filter.getiDisplayLength() + " ROWS ONLY";
		
		Query querySel = em.createNativeQuery(queryStr);
		
		List<Object[]> listaReturn = querySel.getResultList();
		
		return listaReturn;
		
	}
	
	public Integer countAllAssociabiliMessaggiInps(ProcessoTablePaginator filter){
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String currentDate= dateFormat.format(date);
		String queryStr = "SELECT count(ID_MESSAGGI_INPS) " +
							"FROM " +
							"CRS_MESSAGGI_INPS " +
							" where 1 = 1 AND (DATA_FINE > '"+currentDate+"' OR DATA_FINE IS NULL) " + 
							" and ID_MESSAGGI_INPS not in ";

		if(filter.getIdProcesso() > 0){
			
			queryStr += "(select pc.ID_NORMATIVA from CRS_ASS_PROCESSO_CLASSE as pc " + 
						"where pc.ID_PROCESSO = " + filter.getIdProcesso() + " and pc.ID_TIPO = " + TipoNormativaEnum.MESSAGGI_INPS.getCodice() + ")";
			
		}
		if(filter.getIdSottoProcesso() > 0){
			
			queryStr += "(select pc.ID_NORMATIVA from CRS_ASS_SOTTOPROCESSO_CLASSE as pc " + 
						"where pc.ID_SOTTOPROCESSO = " + filter.getIdSottoProcesso() + " and pc.ID_TIPO = " + TipoNormativaEnum.MESSAGGI_INPS.getCodice() + ")";
			
		}
		if(filter.getIdAttivitaComponente() > 0){
			
			queryStr += "(select pc.ID_NORMATIVA from CRS_ASS_AC_CLASSE as pc " + 
						"where pc.ID_ATTIVITA_COMPONENTE = " + filter.getIdAttivitaComponente() + " and pc.ID_TIPO = " + TipoNormativaEnum.MESSAGGI_INPS.getCodice() + ")";
			
		}
		if(filter.getIdAttivitaDettaglio() > 0){
			
			queryStr += "(select pc.ID_NORMATIVA from CRS_ASS_AD_CLASSE as pc " + 
						"where pc.ID_ATTIVITA_DETTAGLIO = " + filter.getIdAttivitaDettaglio() + " and pc.ID_TIPO = " + TipoNormativaEnum.MESSAGGI_INPS.getCodice() + ")";
			
		}
		
		if(StringUtils.isNotEmpty(filter.getsSearch())){
			queryStr += " and ("; 
			queryStr += " UPPER(OGGETTO) like UPPER('%" + filter.getsSearch() + "%') ";
			queryStr += " )";
			
		}
		
		Query querySel = em.createNativeQuery(queryStr);

		Integer count =  (Integer)querySel.getSingleResult();
		return count == null ? 0 : count;
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getAssociabiliLeggiDecreti(ProcessoTablePaginator filter){
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String currentDate= dateFormat.format(date);
		
		String queryStr = "SELECT " +
							"ID_LEGGI_DECRETI, " +
							"DESCR_SINTETICA, DESCR_DETTAGLIO, DATA_EMISSIONE, " +
							"CODICE, OGGETTO, ARTICOLO, ANNO_GUI, NUMERO_GUI, DATA_INIZIO, DATA_FINE " +
							"FROM " +
							"CRS_LEGGI_DECRETI " +
							" where 1 = 1 AND (DATA_FINE > '"+currentDate+"' OR DATA_FINE IS NULL)" + 
							" and ID_LEGGI_DECRETI not in ";

		if(filter.getIdProcesso() > 0){
			
			queryStr += "(select pc.ID_NORMATIVA from CRS_ASS_PROCESSO_CLASSE as pc " + 
						"where pc.ID_PROCESSO = " + filter.getIdProcesso() + " and pc.ID_TIPO = " + TipoNormativaEnum.LEGGI_DECRETI.getCodice() + ")";
			
		}
		if(filter.getIdSottoProcesso() > 0){
			
			queryStr += "(select pc.ID_NORMATIVA from CRS_ASS_SOTTOPROCESSO_CLASSE as pc " + 
						"where pc.ID_SOTTOPROCESSO = " + filter.getIdSottoProcesso() + " and pc.ID_TIPO = " + TipoNormativaEnum.LEGGI_DECRETI.getCodice() + ")";
			
		}
		if(filter.getIdAttivitaComponente() > 0){
			
			queryStr += "(select pc.ID_NORMATIVA from CRS_ASS_AC_CLASSE as pc " + 
						"where pc.ID_ATTIVITA_COMPONENTE = " + filter.getIdAttivitaComponente() + " and pc.ID_TIPO = " + TipoNormativaEnum.LEGGI_DECRETI.getCodice() + ")";
			
		}
		if(filter.getIdAttivitaDettaglio() > 0){
			
			queryStr += "(select pc.ID_NORMATIVA from CRS_ASS_AD_CLASSE as pc " + 
						"where pc.ID_ATTIVITA_DETTAGLIO = " + filter.getIdAttivitaDettaglio() + " and pc.ID_TIPO = " + TipoNormativaEnum.LEGGI_DECRETI.getCodice() + ")";
			
		}
		
		if(StringUtils.isNotEmpty(filter.getsSearch())){
			queryStr += " and ("; 
			queryStr += " UPPER(OGGETTO) like UPPER('%" + filter.getsSearch() + "%') ";
			queryStr += " )";
			
		}
		
		queryStr += " ORDER BY " + (filter.getiSortCol_0()+1) + " " + filter.getsSortDir_0();
		queryStr += " OFFSET " + filter.getiDisplayStart() + " ROWS FETCH NEXT " + filter.getiDisplayLength() + " ROWS ONLY";
		
		Query querySel = em.createNativeQuery(queryStr);
		
		List<Object[]> listaReturn = querySel.getResultList();
		
		return listaReturn;
		
	}
	
	public Integer countAllAssociabiliLeggiDecreti(ProcessoTablePaginator filter){
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String currentDate= dateFormat.format(date);
		String queryStr = "SELECT count(ID_LEGGI_DECRETI) " +
							"FROM " +
							"CRS_LEGGI_DECRETI " +
							" where 1 = 1 AND (DATA_FINE > '"+currentDate+"' OR DATA_FINE IS NULL)" + 
							" and ID_LEGGI_DECRETI not in ";

		if(filter.getIdProcesso() > 0){
			
			queryStr += "(select pc.ID_NORMATIVA from CRS_ASS_PROCESSO_CLASSE as pc " + 
						"where pc.ID_PROCESSO = " + filter.getIdProcesso() + " and pc.ID_TIPO = " + TipoNormativaEnum.LEGGI_DECRETI.getCodice() + ")";
			
		}
		if(filter.getIdSottoProcesso() > 0){
			
			queryStr += "(select pc.ID_NORMATIVA from CRS_ASS_SOTTOPROCESSO_CLASSE as pc " + 
						"where pc.ID_SOTTOPROCESSO = " + filter.getIdSottoProcesso() + " and pc.ID_TIPO = " + TipoNormativaEnum.LEGGI_DECRETI.getCodice() + ")";
			
		}
		if(filter.getIdAttivitaComponente() > 0){
			
			queryStr += "(select pc.ID_NORMATIVA from CRS_ASS_AC_CLASSE as pc " + 
						"where pc.ID_ATTIVITA_COMPONENTE = " + filter.getIdAttivitaComponente() + " and pc.ID_TIPO = " + TipoNormativaEnum.LEGGI_DECRETI.getCodice() + ")";
			
		}
		if(filter.getIdAttivitaDettaglio() > 0){
			
			queryStr += "(select pc.ID_NORMATIVA from CRS_ASS_AD_CLASSE as pc " + 
						"where pc.ID_ATTIVITA_DETTAGLIO = " + filter.getIdAttivitaDettaglio() + " and pc.ID_TIPO = " + TipoNormativaEnum.LEGGI_DECRETI.getCodice() + ")";
			
		}
		
		if(StringUtils.isNotEmpty(filter.getsSearch())){
			queryStr += " and ("; 
			queryStr += " UPPER(OGGETTO) like UPPER('%" + filter.getsSearch() + "%') ";
			queryStr += " )";
			
		}
		
		Query querySel = em.createNativeQuery(queryStr);

		Integer count =  (Integer)querySel.getSingleResult();
		return count == null ? 0 : count;
	}
	
	@Transactional
	public void associaProcessoNormativa(CrsAssProcessoClasse associazione){
		
		try{
			em.merge(associazione);
		}catch(Exception e){
			log.error(e);
			e.printStackTrace();
		}
			
	}
	
	public long getAssProcessoClassePK(AssProcessoClasse filter){
		
		String queryStr = "select ID_ASS_PROCESSO_CLASSE " +
							"from CRS_ASS_PROCESSO_CLASSE " +
							"where ID_PROCESSO = " + filter.getIdProcesso() +
							" and ID_NORMATIVA = " + filter.getIdNormativa() +
							" and ID_TIPO = " + filter.getIdTipo();

		
		Query querySel = em.createNativeQuery(queryStr);

		Long pk =  (Long)querySel.getSingleResult();
		return pk == null ? new Long(0).longValue() : pk.longValue();
	}
	
	@Transactional
	public void rimuoviAssociazioneProcessoNormativa(long idAssProcessoClasse){
		
		Validate.notNull(idAssProcessoClasse, "Id " + idAssProcessoClasse + " non valorizzato.");
		
		CrsAssProcessoClasse associazione = em.find(CrsAssProcessoClasse.class, idAssProcessoClasse);
		
		Validate.notNull(associazione, "Associazione con id " + idAssProcessoClasse + " non presente in archivio.");

		em.remove(associazione);
		
	}

	
	@SuppressWarnings("unchecked")
	public boolean checkFieldOrdinamento(long idTabella, int ordinamento, String tabella){
		
		boolean check = true;
		String queryStr = "";
		Query querySel;
		Object[] obj = null;
		String colonna = "";
		
		// imposto il nome colonna in base alla tabella in input
		if(StringUtils.equalsIgnoreCase(tabella, "CRS_PROCESSO")){
			colonna = "ID_PROCESSO";
		}else if(StringUtils.equalsIgnoreCase(tabella, "CRS_SOTTOPROCESSO")){
			colonna = "ID_SOTTOPROCESSO";
		}else if(StringUtils.equalsIgnoreCase(tabella, "CRS_ATTIVITA_COMPONENTE")){
			colonna = "ID_ATTIVITA_COMPONENTE";
		}else {
			colonna = "ID_ATTIVITA_DETTAGLIO";
		}
		
		// INSERIMENTO
		if(idTabella == 0){
		
			queryStr = "select ORDINAMENTO " +
			"from " + tabella +
			 " where ORDINAMENTO = " + ordinamento;

			querySel = em.createNativeQuery(queryStr);
			
			try{
			int ord =  (Integer)querySel.getSingleResult();
			}catch(NoResultException ex){
			check = false;
			}
			
		// MODIFICA	
		}else{
			
			queryStr = "select A.ordinamento AS COPPIA, B.ordinamento AS SINGOLO from(" +
			" (select ordinamento from " + tabella + 
			" where (" + colonna +" = " + idTabella + " and ORDINAMENTO = " + ordinamento + " )) as A" + 
			" right join" +
			" (select ordinamento from " + tabella +
			" where ordinamento = " +  ordinamento + ") as B on a.ORDINAMENTO = b.ORDINAMENTO)";

			querySel = em.createNativeQuery(queryStr);
			List<Object[]> result = querySel.getResultList();
			
			if(result.size() > 0){
				obj = result.get(0);
			
				// se il secondo campo è vuoto il controllo è ok.
				if(obj[1] == null){
					check = false;
				
					// altrimenti si controlla il primo campo:
					// 1) se è valorizzato l'ordinamneto è già presente per quell' ID (OK)
					// 2) se non è valorizzato l'ordinamento è già prensente per un altro ID (KO)
				}else{ 
					if(obj[0] != null){
					check = false;
					}else{
						check = true;
					}
				}
			}else{
				check = false;
			}
			
		}
		
		return check;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public String getUserNameDirigente(long idUtente){
		
		String queryStr = "select distinct " + 
							"adv2.USERNAME as DIRIGENTE " +
							"from " +
							"CRS_UTENTE_ADV as adv " +
							"join CRS_ASS_UTENTE_RUOLO as ur on adv.ID_UTENTE = ur.ID_UTENTE " +
							"join CRS_RUOLO as r on ur.ID_RUOLO = r.ID_RUOLO " +
							"join CRS_ASS_CODICE_DIRIGENTE as cd on r.CODICE = cd.CODICE " +
							"join CRS_UTENTE_ADV as adv2 on cd.ID_DIRIGENTE = adv2.ID_UTENTE " +
							"where adv.ID_UTENTE = " + idUtente;
		
		Query querySel = em.createNativeQuery(queryStr);

		String returnObj =  (String)querySel.getSingleResult();
		
		return returnObj;
	}

	
	/*
	 * 		SOTTOPROCESSO
	 * */
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getListaSottoProcessi (SottoProcessoTablePaginator filter, long idUtente){
		String queryStr = "SELECT " + 
						  " ID_SOTTOPROCESSO, sp.ORDINAMENTO, sp.DESCRIZIONE, a.DESCRIZIONE, p.DESCRIZIONE, sp.INPUT, sp.OUTPUT, UO_COINVOLTE, sp.STATO" +
						  " FROM " + 
						  " CRS_SOTTOPROCESSO as sp" +
						  " join CRS_PROCESSO as p on sp.ID_PROCESSO = p.ID_PROCESSO" + 
						  //" join CRS_ASS_DIRIGENTE_PROCESSO as ass on sp.ID_PROCESSO = ass.ID_PROCESSO" + 
						  " join CRS_AREA as a on p.ID_AREA = a.ID_AREA" + 
						  " WHERE 1 = 1 ";
		
		if(filter.getSottProcesso().getProcesso().getIdProcesso() != 0){
			queryStr += " and sp.id_processo = " + filter.getSottProcesso().getProcesso().getIdProcesso(); 
		}
		
		
		if(StringUtils.isNotEmpty(filter.getsSearch())){
			queryStr += " and ("; 
			queryStr += " UPPER(sp.descrizione) like UPPER('%" + filter.getsSearch() + "%') ";
			queryStr += " )";
			
		}
		
		if(StringUtils.equalsIgnoreCase(filter.getSottProcesso().getStato(), "A")){
			queryStr += " and sp.stato = 'A' "; 
		}else if(StringUtils.equalsIgnoreCase(filter.getSottProcesso().getStato(), "D"))
		{
			queryStr += " and sp.stato = 'D' "; 
		}
		
		queryStr += " ORDER BY " + (filter.getiSortCol_0()+1) + " " + filter.getsSortDir_0();
		queryStr += " OFFSET " + filter.getiDisplayStart() + " ROWS FETCH NEXT " + filter.getiDisplayLength() + " ROWS ONLY";
		
		Query querySel = em.createNativeQuery(queryStr);
		
		List<Object[]> listaSottoProcessi = querySel.getResultList();
		
		return listaSottoProcessi;	
	}
	
	
	public Integer countAllSottoProcessi(SottoProcessoTablePaginator filter, long idUtente){
		
		String queryStr = "SELECT count(id_sottoprocesso) " + 
							"FROM CRS_SOTTOPROCESSO as sp join CRS_PROCESSO as p on sp.ID_PROCESSO = p.ID_PROCESSO where 1 = 1 ";
		
		if(filter.getSottProcesso().getProcesso().getIdProcesso() != 0){
			queryStr += " and sp.id_processo = " + filter.getSottProcesso().getProcesso().getIdProcesso(); 
		}
		
		if(StringUtils.isNotEmpty(filter.getsSearch())){
			queryStr += " and ("; 
			queryStr += " UPPER(sp.descrizione) like UPPER('%" + filter.getsSearch() + "%') ";
			queryStr += " )";
			
		}
		
		Query querySel = em.createNativeQuery(queryStr);
		

		Integer count =  (Integer)querySel.getSingleResult();
		return count == null ? 0 : count;
	}
	
	@Transactional
	public void saveSottoProcesso(CrsSottoprocesso sottoProcesso){
		
		try{
			em.merge(sottoProcesso);
		}catch(Exception e){
			log.error(e);
			e.printStackTrace();
		}
		
	}
	
	@Transactional
	public void deleteSottoProcesso(long idSottoProcesso){
		
		Validate.notNull(idSottoProcesso, "Id " + idSottoProcesso + " non valorizzato.");
		
		CrsSottoprocesso sottoProcesso = em.find(CrsSottoprocesso.class, idSottoProcesso);
		
		Validate.notNull(sottoProcesso, "La Fase con id " + idSottoProcesso + " non esiste.");

		em.remove(sottoProcesso);
		
	}
	
	
	public List<CrsSottoprocesso> getListaSottoProcessi (String ricerca, Integer pageNumber, Integer pageSize, String columnNameToOrder, String orderType, String textSearch, char stato){

		List<CrsSottoprocesso> listaSottoProcessi = null;
		return listaSottoProcessi;	
	}
	
	@Transactional
	public void disableSottoProcesso(long idSottoProcesso){
		
		CrsSottoprocesso sottoProcesso = em.find(CrsSottoprocesso.class, idSottoProcesso);
		
		Validate.notNull(sottoProcesso, "La Fase con id " + idSottoProcesso + " non esiste.");

		sottoProcesso.setStato("D");		
		em.merge(sottoProcesso);
		
	}
	
	@Transactional
	public void enableSottoProcesso(long idSottoProcesso){
		
		CrsSottoprocesso sottoProcesso = em.find(CrsSottoprocesso.class, idSottoProcesso);
		
		Validate.notNull(sottoProcesso, "La Fase con id " + idSottoProcesso + " non esiste.");

		sottoProcesso.setStato("A");		
		em.merge(sottoProcesso);
		
	}
	
	@SuppressWarnings("unchecked")
	public List<CrsProcesso> getComboProcessoByIdArea(long idArea){
		String queryStr = "SELECT " + 
						"ID_PROCESSO, " + 
						"DESCRIZIONE " + 
						"FROM CRS_PROCESSO " +
						"WHERE ID_AREA = " + idArea + 
						" ORDER BY DESCRIZIONE ";
						//"WHERE DATA_FINE > CONVERT(date, getdate()) ";
		
		Query querySel = em.createNativeQuery(queryStr, CrsProcesso.class);
		
		List<CrsProcesso> lista = querySel.getResultList();
		
		return lista;	
	}
	

	public CrsSottoprocesso getSottoProcessoById(Long idSottoProcesso){
		
		CrsSottoprocesso model = em.find(CrsSottoprocesso.class, idSottoProcesso);
		
		Validate.notNull(model, "La Fase con id " + idSottoProcesso + " non esiste.");
		
		return model;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getListaSottoProcessoNormative(SottoProcessoTablePaginator filter){
	
		String queryStr = "SELECT " +
						"a.ID_DATI_TIPO, a.DATE1, a.DATE2, " +
						"TEXT1, TEXT2, TEXT3, TEXT4, TEXT5, TEXT6, TEXT7, TEXT8, TEXT9, TEXT10, " +
						"NUMBER1, NUMBER2, NUMBER3, NUMBER4, NUMBER5, NUMBER6, NUMBER7, NUMBER8, NUMBER9, NUMBER10 " +
						"from CRS_DATI_TIPO as a join CRS_DESCRIZIONE_TIPO as b on a.ID_DESCRIZIONE_TIPO = b.ID_DESCRIZIONE_TIPO " +
						" where 1 = 1  " + 
						" and b.ID_TIPO = " + filter.getIdTipo() +
						" and a.ID_DATI_TIPO not in " + 
						"(select pc.ID_DATI_TIPO from CRS_ASS_SOTTOPROCESSO_CLASSE as pc where pc.ID_SOTTOPROCESSO = " + filter.getIdSottoProcesso() + ")";
		
		
		if(StringUtils.isNotEmpty(filter.getsSearch())){
			queryStr += " and ("; 
			queryStr += " UPPER(TEXT3) like UPPER('%" + filter.getsSearch() + "%') ";
			queryStr += " )";
			
		}
		
		queryStr += " ORDER BY " + (filter.getiSortCol_0()+1) + " " + filter.getsSortDir_0();
		queryStr += " OFFSET " + filter.getiDisplayStart() + " ROWS FETCH NEXT " + filter.getiDisplayLength() + " ROWS ONLY";
		
		Query querySel = em.createNativeQuery(queryStr);
		
		List<Object[]> listaReturn = querySel.getResultList();
		
		return listaReturn;
		
	}
	
	public Integer countAllSottoProcessoNormative(SottoProcessoTablePaginator filter){
		
		String queryStr = "SELECT count(ID_DATI_TIPO) " +
						"from CRS_DATI_TIPO as a join CRS_DESCRIZIONE_TIPO as b on a.ID_DESCRIZIONE_TIPO = b.ID_DESCRIZIONE_TIPO " +
						" where 1 = 1  " + 
						" and b.ID_TIPO = " + filter.getIdTipo() +
						" and a.ID_DATI_TIPO not in " + 
						"(select pc.ID_DATI_TIPO from CRS_ASS_SOTTOPROCESSO_CLASSE as pc where pc.ID_SOTTOPROCESSO = " + filter.getIdSottoProcesso() + ")";
		
		if(StringUtils.isNotEmpty(filter.getsSearch())){
			queryStr += " and ("; 
			queryStr += " UPPER(TEXT3) like UPPER('%" + filter.getsSearch() + "%') ";
			queryStr += " )";
			
		}
		
		Query querySel = em.createNativeQuery(queryStr);

		Integer count =  (Integer)querySel.getSingleResult();
		return count == null ? 0 : count;
	}
	
	
	@Transactional
	public void associaSottoProcessoNormativa(CrsAssSottoprocessoClasse associazione){
		
		try{
			em.merge(associazione);
		}catch(Exception e){
			log.error(e);
			e.printStackTrace();
		}
			
	}
	
	public long getAssSottoProcessoClassePK(AssSottoProcessoClasse filter){
		
		String queryStr = "select ID_ASS_SOTTOPROCESSO_CLASSE " +
							"from CRS_ASS_SOTTOPROCESSO_CLASSE " +
							"where ID_SOTTOPROCESSO = " + filter.getIdSottoProcesso() +
							" and ID_NORMATIVA = " + filter.getIdNormativa() +
							" and ID_TIPO = " + filter.getIdTipo();

		
		Query querySel = em.createNativeQuery(queryStr);

		Long pk =  (Long)querySel.getSingleResult();
		return pk == null ? new Long(0).longValue() : pk.longValue();
	}
	
	@Transactional
	public void rimuoviAssociazioneSottoProcessoNormativa(long idAssSottoProcessoClasse){
		
		Validate.notNull(idAssSottoProcessoClasse, "Id " + idAssSottoProcessoClasse + " non valorizzato.");
		
		CrsAssSottoprocessoClasse associazione = em.find(CrsAssSottoprocessoClasse.class, idAssSottoProcessoClasse);
		
		Validate.notNull(associazione, "Associazione con id " + idAssSottoProcessoClasse + " non presente in archivio.");

		em.remove(associazione);
		
	}
	
	
	public long getIdAreaByIdSottoProcesso(long idSottoProcesso){
		
		String queryStr = "select a.ID_AREA from CRS_SOTTOPROCESSO as sp " +
							"join CRS_PROCESSO as p on sp.ID_PROCESSO = p.ID_PROCESSO " +
							"join CRS_AREA as a on p.ID_AREA = a.ID_AREA " +
							" where sp.ID_SOTTOPROCESSO = " + idSottoProcesso;
		
		Query querySel = em.createNativeQuery(queryStr);

		Long idArea =  (Long)querySel.getSingleResult();
		return idArea == null ? new Long(0).longValue() : idArea.longValue();
	}
	
	
	
	/*
	 * 		ATTIVITA' COMPONENTE
	 * */
	public void saveAttivitaComponente(CrsAttivitaComponente attivitaComponente){
		
		try{
			em.merge(attivitaComponente);
		}catch(Exception e){
			log.error(e);
			e.printStackTrace();
			
		}
		
	}
	
	@Transactional
	public void deleteAttivitaComponente(long idAttivitaComponente){
		
		Validate.notNull(idAttivitaComponente, "Id " + idAttivitaComponente + " non valorizzato.");
		
		CrsAttivitaComponente attivitaComponente = em.find(CrsAttivitaComponente.class, idAttivitaComponente);
		
		Validate.notNull(attivitaComponente, "Attivita Componente con id " + idAttivitaComponente + " non esiste.");

		em.remove(attivitaComponente);
		
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getListaAttivitaComponente(AttivitaComponenteTablePaginator filter,long idUtente){
		
		String queryStr = "SELECT " + 
							"ID_ATTIVITA_COMPONENTE, ac.ORDINAMENTO, ac.DESCRIZIONE, " + 
							"a.DESCRIZIONE, p.DESCRIZIONE, sp.DESCRIZIONE, ac.DATA_INIZIO, ac.DATA_FINE, ac.VINCOLO, ac.STATO " + 
							"FROM CRS_ATTIVITA_COMPONENTE as ac " + 
							"join CRS_SOTTOPROCESSO as sp on ac.ID_SOTTOPROCESSO = sp.ID_SOTTOPROCESSO " + 
							"join CRS_PROCESSO as p on sp.ID_PROCESSO = p.ID_PROCESSO " + 
							"join CRS_AREA as a on p.ID_AREA = a.ID_AREA " +
							"where 1=1 ";
		
		if(filter.getAttivitaComponente().getSottoProcesso().getIdSottoProcesso() != 0){
			queryStr += " and ac.ID_SOTTOPROCESSO = " + filter.getAttivitaComponente().getSottoProcesso().getIdSottoProcesso(); 
		}
		
		
		if(StringUtils.isNotEmpty(filter.getsSearch())){
			queryStr += " and ("; 
			queryStr += " UPPER(ac.descrizione) like UPPER('%" + filter.getsSearch() + "%') ";
			queryStr += " )";
			
		}
		
		if(StringUtils.equalsIgnoreCase(filter.getAttivitaComponente().getStato(), "A")){
			queryStr += " and ac.stato = 'A' "; 
		}else if(StringUtils.equalsIgnoreCase(filter.getAttivitaComponente().getStato(), "D"))
		{
			queryStr += " and ac.stato = 'D' "; 
		}
		
		queryStr += " ORDER BY " + (filter.getiSortCol_0()+1) + " " + filter.getsSortDir_0();
		queryStr += " OFFSET " + filter.getiDisplayStart() + " ROWS FETCH NEXT " + filter.getiDisplayLength() + " ROWS ONLY";
		
		Query querySel = em.createNativeQuery(queryStr);
		
		List<Object[]> listaReturn = querySel.getResultList();
		
		return listaReturn;	
	}
	
	
	public Integer countAllAttivitaComponente(AttivitaComponenteTablePaginator filter, long idUtente){
		
		String queryStr = "SELECT count(id_attivita_componente) " + 
							"FROM CRS_ATTIVITA_COMPONENTE as a join CRS_SOTTOPROCESSO as sp on a.ID_SOTTOPROCESSO = sp.ID_SOTTOPROCESSO where 1 = 1 ";
		
		if(filter.getAttivitaComponente().getSottoProcesso().getIdSottoProcesso() != 0){
			queryStr += " and a.id_sottoprocesso = " + filter.getAttivitaComponente().getSottoProcesso().getIdSottoProcesso(); 
		}
		
		if(StringUtils.isNotEmpty(filter.getsSearch())){
			queryStr += " and ("; 
			queryStr += " UPPER(a.descrizione) like UPPER('%" + filter.getsSearch() + "%') ";
			queryStr += " )";
			
		}
		
		Query querySel = em.createNativeQuery(queryStr);
		

		Integer count =  (Integer)querySel.getSingleResult();
		return count == null ? 0 : count;
	}
	
	
	public void disableAttivitaComponente(Long idAttivitaComponente){
		
		CrsAttivitaComponente attivitaComponente = em.find(CrsAttivitaComponente.class, idAttivitaComponente);
		
		Validate.notNull(attivitaComponente, "Attivita Componente con id " + idAttivitaComponente + " non presente in archivio.");

		attivitaComponente.setStato("D");		
		em.merge(attivitaComponente);
		
	}
	
	
	public void enableAttivitaComponente(Long idAttivitaComponente){
		
		CrsAttivitaComponente attivitaComponente = em.find(CrsAttivitaComponente.class, idAttivitaComponente);
		
		Validate.notNull(attivitaComponente, "Attivita Componente con id " + idAttivitaComponente + " non presente in archivio.");

		attivitaComponente.setStato("A");		
		em.merge(attivitaComponente);
		
	}
	
	
	@SuppressWarnings("unchecked")
	public List<CrsSottoprocesso> getComboSottoProcessoByIdProcesso(long idProcesso){
		String queryStr = "SELECT " + 
						"ID_SOTTOPROCESSO, " + 
						"DESCRIZIONE " + 
						"FROM CRS_SOTTOPROCESSO " + 
						"WHERE ID_PROCESSO = " + idProcesso +
						" ORDER BY DESCRIZIONE ";
						//"WHERE DATA_FINE > CONVERT(date, getdate()) ";
		
		Query querySel = em.createNativeQuery(queryStr, CrsSottoprocesso.class);
		
		List<CrsSottoprocesso> lista = querySel.getResultList();
		
		return lista;	
	}
	
	
	public CrsAttivitaComponente getAttivitaComponenteById(Long idAttivitaComponente){
		
		CrsAttivitaComponente model = em.find(CrsAttivitaComponente.class, idAttivitaComponente);
		
		Validate.notNull(model, "Attivita Componente con id " + idAttivitaComponente + " non presente in archivio.");
		
		return model;
	}

	
	@SuppressWarnings("unchecked")
	public List<Object[]> getListaAttivitaComponenteNormative(AttivitaComponenteTablePaginator filter){
	
		String queryStr = "SELECT " +
						"a.ID_DATI_TIPO, a.DATE1, a.DATE2, " +
						"TEXT1, TEXT2, TEXT3, TEXT4, TEXT5, TEXT6, TEXT7, TEXT8, TEXT9, TEXT10, " +
						"NUMBER1, NUMBER2, NUMBER3, NUMBER4, NUMBER5, NUMBER6, NUMBER7, NUMBER8, NUMBER9, NUMBER10 " +
						"from CRS_DATI_TIPO as a join CRS_DESCRIZIONE_TIPO as b on a.ID_DESCRIZIONE_TIPO = b.ID_DESCRIZIONE_TIPO " +
						" where 1 = 1  " + 
						" and b.ID_TIPO = " + filter.getIdTipo() +
						" and a.ID_DATI_TIPO not in " + 
						"(select pc.ID_DATI_TIPO from CRS_ASS_AC_CLASSE as pc where pc.ID_ATTIVITA_COMPONENTE = " + filter.getIdAttivitaComponente() + ")";
		
		
		if(StringUtils.isNotEmpty(filter.getsSearch())){
			queryStr += " and ("; 
			queryStr += " UPPER(TEXT3) like UPPER('%" + filter.getsSearch() + "%') ";
			queryStr += " )";
			
		}
		
		queryStr += " ORDER BY " + (filter.getiSortCol_0()+1) + " " + filter.getsSortDir_0();
		queryStr += " OFFSET " + filter.getiDisplayStart() + " ROWS FETCH NEXT " + filter.getiDisplayLength() + " ROWS ONLY";
		
		Query querySel = em.createNativeQuery(queryStr);
		
		List<Object[]> listaReturn = querySel.getResultList();
		
		return listaReturn;
		
	}
	
	public Integer countAllAttivitaComponenteNormative(AttivitaComponenteTablePaginator filter){
		
		String queryStr = "SELECT count(ID_DATI_TIPO) " +
						"from CRS_DATI_TIPO as a join CRS_DESCRIZIONE_TIPO as b on a.ID_DESCRIZIONE_TIPO = b.ID_DESCRIZIONE_TIPO " +
						" where 1 = 1  " + 
						" and b.ID_TIPO = " + filter.getIdTipo() +
						" and a.ID_DATI_TIPO not in " + 
						"(select pc.ID_DATI_TIPO from CRS_ASS_AC_CLASSE as pc where pc.ID_ATTIVITA_COMPONENTE = " + filter.getIdAttivitaComponente() + ")";
		
		if(StringUtils.isNotEmpty(filter.getsSearch())){
			queryStr += " and ("; 
			queryStr += " UPPER(TEXT3) like UPPER('%" + filter.getsSearch() + "%') ";
			queryStr += " )";
			
		}
		
		Query querySel = em.createNativeQuery(queryStr);

		Integer count =  (Integer)querySel.getSingleResult();
		return count == null ? 0 : count;
	}
	
	
	@Transactional
	public void associaAttivitaComponenteNormativa(CrsAssAcClasse associazione){
		
		try{
			em.merge(associazione);
		}catch(Exception e){
			log.error(e);
			e.printStackTrace();
		}
			
	}
	
	public long getAssAttivitaComponenteClassePK(AssAcClasse filter){
		
		String queryStr = "select ID_ASS_AC_CLASSE " +
							"from CRS_ASS_AC_CLASSE " +
							"where ID_ATTIVITA_COMPONENTE = " + filter.getIdAttivitaComponente() +
							" and ID_NORMATIVA = " + filter.getIdNormativa() +
							" and ID_TIPO = " + filter.getIdTipo();

		
		Query querySel = em.createNativeQuery(queryStr);

		Long pk =  (Long)querySel.getSingleResult();
		return pk == null ? new Long(0).longValue() : pk.longValue();
	}
	
	@Transactional
	public void rimuoviAssociazioneAttivitaComponenteNormativa(long idAssAcClasse){
		
		Validate.notNull(idAssAcClasse, "Id " + idAssAcClasse + " non valorizzato.");
		
		CrsAssAcClasse associazione = em.find(CrsAssAcClasse.class, idAssAcClasse);
		
		Validate.notNull(associazione, "Associazione con id " + idAssAcClasse + " non presente in archivio.");

		em.remove(associazione);
		
	}
	
	public long getIdAreaByIdAttivitaComponente(long idAttivitaComponente){
		
		String queryStr = "select a.ID_AREA " + 
							"from CRS_ATTIVITA_COMPONENTE as ac " +
							"join CRS_SOTTOPROCESSO as sp on ac.ID_SOTTOPROCESSO = sp.ID_SOTTOPROCESSO " +
							"join CRS_PROCESSO as p on sp.ID_PROCESSO = p.ID_PROCESSO " +
							"join CRS_AREA as a on p.ID_AREA = a.ID_AREA " +
							"where ac.ID_ATTIVITA_COMPONENTE = " + idAttivitaComponente;
		
		Query querySel = em.createNativeQuery(queryStr);

		Long idArea =  (Long)querySel.getSingleResult();
		return idArea == null ? new Long(0).longValue() : idArea.longValue();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<CrsSottoprocesso> getComboSottoProcessoByIdArea(long idArea){
		String queryStr = "SELECT " + 
						"SP.ID_SOTTOPROCESSO, " + 
						"SP.DESCRIZIONE " + 
						"FROM CRS_SOTTOPROCESSO AS SP " + 
						"JOIN CRS_PROCESSO AS P ON SP.ID_PROCESSO = P.ID_PROCESSO " + 
						"JOIN CRS_AREA AS A ON P.ID_AREA = A.ID_AREA " + 
						"WHERE A.ID_AREA = " + idArea +
						" ORDER BY SP.DESCRIZIONE ";
		
		Query querySel = em.createNativeQuery(queryStr, CrsSottoprocesso.class);
		
		List<CrsSottoprocesso> lista = querySel.getResultList();
		
		return lista;	
	}

	
	/*
	 * 		ATTIVITA' DETTAGLIO
	 * */
	
	
	@SuppressWarnings("unchecked")
	public List<CrsAttivitaComponente> getComboAttivitaComponenteByIdSottoProcesso(long idSottoProcesso){
		String queryStr = "SELECT " + 
						"ID_ATTIVITA_COMPONENTE, " + 
						"DESCRIZIONE " + 
						"FROM CRS_ATTIVITA_COMPONENTE " +
						"WHERE ID_SOTTOPROCESSO = " + idSottoProcesso +
						"ORDER BY DESCRIZIONE ";
		
		Query querySel = em.createNativeQuery(queryStr, CrsAttivitaComponente.class);
		
		List<CrsAttivitaComponente> lista = querySel.getResultList();
		
		return lista;	
	}
	
	public void saveAttivitaDettaglio(CrsAttivitaDettaglio attivitaDettaglio){
		
		try{
			em.merge(attivitaDettaglio);
		}catch(Exception e){
			log.error(e);
			e.printStackTrace();
			
		}
		
	}
	
	@Transactional
	public void deleteAttivitaDettaglio(long idAttivitaDettaglio){
		
		Validate.notNull(idAttivitaDettaglio, "Id " + idAttivitaDettaglio + " non valorizzato.");
		
		CrsAttivitaDettaglio attivitaDettaglio = em.find(CrsAttivitaDettaglio.class, idAttivitaDettaglio);
		
		Validate.notNull(attivitaDettaglio, "Attivita Dettaglio con id " + idAttivitaDettaglio + " non presente in archivio.");

		em.remove(attivitaDettaglio);
		
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getListaAttivitaDettaglio(AttivitaDettaglioTablePaginator filter, long idUtente){
		
		String queryStr = "SELECT " + 
							"ID_ATTIVITA_DETTAGLIO, ad.ORDINAMENTO, ad.DESCRIZIONE, " + 
							"a.DESCRIZIONE, p.DESCRIZIONE, sp.DESCRIZIONE, ac.DESCRIZIONE, ad.DATA_INIZIO, ad.DATA_FINE, ad.STATO " + 
							"FROM CRS_ATTIVITA_DETTAGLIO as ad " +
							"join CRS_ATTIVITA_COMPONENTE as ac on ad.ID_ATTIVITA_COMPONENTE = ac.ID_ATTIVITA_COMPONENTE " + 
							"join CRS_SOTTOPROCESSO as sp on ac.ID_SOTTOPROCESSO = sp.ID_SOTTOPROCESSO " +
							"join CRS_PROCESSO as p on sp.ID_PROCESSO = p.ID_PROCESSO " +
							"join CRS_AREA as a on p.ID_AREA = a.ID_AREA " +
							"where 1=1 "; 
		
		if(filter.getAttivitaDettaglio().getAttivitaComponente().getIdAttivitaComponente() != 0){
			queryStr += " and ad.ID_ATTIVITA_COMPONENTE = " + filter.getAttivitaDettaglio().getAttivitaComponente().getIdAttivitaComponente(); 
		}
		
		
		if(StringUtils.isNotEmpty(filter.getsSearch())){
			queryStr += " and ("; 
			queryStr += " UPPER(ad.descrizione) like UPPER('%" + filter.getsSearch() + "%') ";
			queryStr += " )";
			
		}
		
		if(StringUtils.equalsIgnoreCase(filter.getAttivitaDettaglio().getStato(), "A")){
			queryStr += " and ad.stato = 'A' "; 
		}else if(StringUtils.equalsIgnoreCase(filter.getAttivitaDettaglio().getStato(), "D"))
		{
			queryStr += " and ad.stato = 'D' "; 
		}
		
		queryStr += " ORDER BY " + (filter.getiSortCol_0()+1) + " " + filter.getsSortDir_0();
		queryStr += " OFFSET " + filter.getiDisplayStart() + " ROWS FETCH NEXT " + filter.getiDisplayLength() + " ROWS ONLY";
		
		Query querySel = em.createNativeQuery(queryStr);
		
		List<Object[]> listaReturn = querySel.getResultList();
		
		return listaReturn;	
	}
	
	public Integer countAllAttivitaDettaglio(AttivitaDettaglioTablePaginator filter,long idUtente){
		
		String queryStr = "SELECT count(id_attivita_dettaglio) " + 
							"FROM CRS_ATTIVITA_DETTAGLIO as d join CRS_ATTIVITA_COMPONENTE as c on d.ID_ATTIVITA_COMPONENTE = c.ID_ATTIVITA_COMPONENTE " +
							"where 1=1 ";
		
		if(filter.getAttivitaDettaglio().getAttivitaComponente().getIdAttivitaComponente() != 0){
			queryStr += " and d.id_attivita_componente = " + filter.getAttivitaDettaglio().getAttivitaComponente().getIdAttivitaComponente(); 
		}
		
		if(StringUtils.isNotEmpty(filter.getsSearch())){
			queryStr += " and ("; 
			queryStr += " UPPER(d.descrizione) like UPPER('%" + filter.getsSearch() + "%') ";
			queryStr += " )";
			
		}
		
		Query querySel = em.createNativeQuery(queryStr);
		

		Integer count =  (Integer)querySel.getSingleResult();
		return count == null ? 0 : count;
	}
	
	
	public void disableAttivitaDettaglio(Long idAttivitaDettaglio){
		
		CrsAttivitaDettaglio attivitaDettaglio = em.find(CrsAttivitaDettaglio.class, idAttivitaDettaglio);
		
		Validate.notNull(attivitaDettaglio, "Attivita Dettaglio con id " + idAttivitaDettaglio + " non presente in archivio.");

		attivitaDettaglio.setStato("D");		
		em.merge(attivitaDettaglio);
		
	}
	
	
	public void enableAttivitaDettaglio(Long idAttivitaDettaglio){
		
		CrsAttivitaDettaglio attivitaDettaglio = em.find(CrsAttivitaDettaglio.class, idAttivitaDettaglio);
		
		Validate.notNull(attivitaDettaglio, "Attivita Dettaglio con id " + idAttivitaDettaglio + " non presente in archivio.");

		attivitaDettaglio.setStato("A");		
		em.merge(attivitaDettaglio);
		
	}
	
	
	public CrsAttivitaDettaglio getAttivitaDettaglioById(Long idAttivitaDettaglio){
		
		CrsAttivitaDettaglio model = em.find(CrsAttivitaDettaglio.class, idAttivitaDettaglio);
		
		Validate.notNull(model, "Attivita Dettaglio con id " + idAttivitaDettaglio + " non presente in archivio.");
		
		return model;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getListaAttivitaDettaglioNormative(AttivitaDettaglioTablePaginator filter){
	
		String queryStr = "SELECT " +
						"a.ID_DATI_TIPO, a.DATE1, a.DATE2, " +
						"TEXT1, TEXT2, TEXT3, TEXT4, TEXT5, TEXT6, TEXT7, TEXT8, TEXT9, TEXT10, " +
						"NUMBER1, NUMBER2, NUMBER3, NUMBER4, NUMBER5, NUMBER6, NUMBER7, NUMBER8, NUMBER9, NUMBER10 " +
						"from CRS_DATI_TIPO as a join CRS_DESCRIZIONE_TIPO as b on a.ID_DESCRIZIONE_TIPO = b.ID_DESCRIZIONE_TIPO " +
						" where 1 = 1  " + 
						" and b.ID_TIPO = " + filter.getIdTipo() +
						" and a.ID_DATI_TIPO not in " + 
						"(select pc.ID_DATI_TIPO from CRS_ASS_AD_CLASSE as pc where pc.ID_ATTIVITA_DETTAGLIO = " + filter.getIdAttivitaDettaglio() + ")";
		
		
		if(StringUtils.isNotEmpty(filter.getsSearch())){
			queryStr += " and ("; 
			queryStr += " UPPER(TEXT3) like UPPER('%" + filter.getsSearch() + "%') ";
			queryStr += " )";
			
		}
		
		queryStr += " ORDER BY " + (filter.getiSortCol_0()+1) + " " + filter.getsSortDir_0();
		queryStr += " OFFSET " + filter.getiDisplayStart() + " ROWS FETCH NEXT " + filter.getiDisplayLength() + " ROWS ONLY";
		
		Query querySel = em.createNativeQuery(queryStr);
		
		List<Object[]> listaReturn = querySel.getResultList();
		
		return listaReturn;
		
	}
	
	public Integer countAllAttivitaDettaglioNormative(AttivitaDettaglioTablePaginator filter){
		
		String queryStr = "SELECT count(ID_DATI_TIPO) " +
						"from CRS_DATI_TIPO as a join CRS_DESCRIZIONE_TIPO as b on a.ID_DESCRIZIONE_TIPO = b.ID_DESCRIZIONE_TIPO " +
						" where 1 = 1  " + 
						" and b.ID_TIPO = " + filter.getIdTipo() +
						" and a.ID_DATI_TIPO not in " + 
						"(select pc.ID_DATI_TIPO from CRS_ASS_AD_CLASSE as pc where pc.ID_ATTIVITA_DETTAGLIO = " + filter.getIdAttivitaDettaglio() + ")";
		
		if(StringUtils.isNotEmpty(filter.getsSearch())){
			queryStr += " and ("; 
			queryStr += " UPPER(TEXT3) like UPPER('%" + filter.getsSearch() + "%') ";
			queryStr += " )";
			
		}
		
		Query querySel = em.createNativeQuery(queryStr);

		Integer count =  (Integer)querySel.getSingleResult();
		return count == null ? 0 : count;
	}
	
	
	@Transactional
	public void associaAttivitaDettaglioNormativa(CrsAssAdClasse associazione){
		
		try{
			em.merge(associazione);
		}catch(Exception e){
			log.error(e);
			e.printStackTrace();
		}
			
	}
	
	public long getAssAttivitaDettaglioClassePK(AssAdClasse filter){
		
		String queryStr = "select ID_ASS_AD_CLASSE " +
							"from CRS_ASS_AD_CLASSE " +
							"where ID_ATTIVITA_DETTAGLIO = " + filter.getIdAttivitaDettaglio() +
							" and ID_NORMATIVA = " + filter.getIdNormativa() +
							" and ID_TIPO = " + filter.getIdTipo();

		
		Query querySel = em.createNativeQuery(queryStr);

		Long pk =  (Long)querySel.getSingleResult();
		return pk == null ? new Long(0).longValue() : pk.longValue();
	}
	
	@Transactional
	public void rimuoviAssociazioneAttivitaDettaglioNormativa(long idAssAdClasse){
		
		Validate.notNull(idAssAdClasse, "Id " + idAssAdClasse + " non valorizzato.");
		
		CrsAssAdClasse associazione = em.find(CrsAssAdClasse.class, idAssAdClasse);
		
		Validate.notNull(associazione, "Associazione con id " + idAssAdClasse + " non presente in archivio.");

		em.remove(associazione);
		
	}
	
	public long getIdAreaByIdAttivitaDettaglio(long idAttivitaDettaglio){
		
		String queryStr = "select a.ID_AREA " +
							"from CRS_ATTIVITA_DETTAGLIO as ad " +
							"join CRS_ATTIVITA_COMPONENTE as ac on ad.ID_ATTIVITA_COMPONENTE = ac.ID_ATTIVITA_COMPONENTE " +
							"join CRS_SOTTOPROCESSO as sp on ac.ID_SOTTOPROCESSO = sp.ID_SOTTOPROCESSO " +
							"join CRS_PROCESSO as p on sp.ID_PROCESSO = p.ID_PROCESSO " +
							"join CRS_AREA as a on p.ID_AREA = a.ID_AREA " +
							"where ad.ID_ATTIVITA_DETTAGLIO = " + idAttivitaDettaglio;
		
		Query querySel = em.createNativeQuery(queryStr);

		Long idArea =  (Long)querySel.getSingleResult();
		return idArea == null ? new Long(0).longValue() : idArea.longValue();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<CrsAttivitaComponente> getComboAttivitaComponenteByIdArea(long idArea){
		String queryStr = "SELECT " + 
						"AC.ID_ATTIVITA_COMPONENTE, " + 
						"AC.DESCRIZIONE " + 
						"FROM CRS_ATTIVITA_COMPONENTE AS AC " +
						"JOIN CRS_SOTTOPROCESSO AS SP ON AC.ID_SOTTOPROCESSO = SP.ID_SOTTOPROCESSO " + 
						"JOIN CRS_PROCESSO AS P ON SP.ID_PROCESSO = P.ID_PROCESSO " + 
						"JOIN CRS_AREA AS A ON P.ID_AREA = A.ID_AREA " + 
						"WHERE A.ID_AREA = " + idArea +
						"ORDER BY AC.DESCRIZIONE ";
		
		Query querySel = em.createNativeQuery(queryStr, CrsAttivitaComponente.class);
		
		List<CrsAttivitaComponente> lista = querySel.getResultList();
		
		return lista;	
	}
	
	
	@Override
	public void associaSottoProcessoAudit(long idAudit, long idSottoProcesso) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<CrsSottoprocesso> getListaSottoProcessiByIdAudit(long idAudit,
			String ricerca, Integer pageNumber, Integer pageSize,
			String columnNameToOrder, String orderType, String textSearch,
			char stato) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getProcessiByIdArea(long idArea){
		String queryStr = "SELECT " + 
						"ID_PROCESSO, " +
						"ID_AREA, " +
						"DESCRIZIONE, " +
						"DATA_INIZIO, " + 
						"DATA_FINE, " +
						"STATO, " +
						"INPUT, " + 
						"OUTPUT, " +
						"ORDINAMENTO " +
						"FROM CRS_PROCESSO " +
						"WHERE ID_AREA = " + idArea; 
		
		Query querySel = em.createNativeQuery(queryStr);
		
		List<Object[]> lista = querySel.getResultList();
		
		return lista;	
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getSottoProcessiByIdArea(long idArea){
		String queryStr = "SELECT " + 
						"SP.ID_SOTTOPROCESSO, " +
						"SP.ID_PROCESSO, " +
						"SP.DESCRIZIONE, " +
						"SP.DATA_INIZIO, " + 
						"SP.DATA_FINE, " +
						"SP.INPUT, " + 
						"SP.OUTPUT, " +
						"SP.UO_COINVOLTE, " + 
						"SP.STATO, " +
						"SP.ORDINAMENTO " +
						"FROM CRS_SOTTOPROCESSO AS SP " + 
						"JOIN CRS_PROCESSO AS P ON SP.ID_PROCESSO = P.ID_PROCESSO " + 
						"JOIN CRS_AREA AS A ON P.ID_AREA = A.ID_AREA " + 
						"WHERE A.ID_AREA = " + idArea; 
		
		Query querySel = em.createNativeQuery(queryStr);
		
		List<Object[]> lista = querySel.getResultList();
		
		return lista;	
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getAttivitaComponentiByIdArea(long idArea){
		String queryStr = "SELECT " + 
						"AC.ID_ATTIVITA_COMPONENTE, " +
						"AC.ID_SOTTOPROCESSO, " +
						"AC.DESCRIZIONE, " +
						"AC.DATA_INIZIO, " + 
						"AC.DATA_FINE, " +
						"AC.VINCOLO, " + 
						"AC.STATO, " +
						"AC.ORDINAMENTO " +
						"FROM CRS_ATTIVITA_COMPONENTE AS AC " +
						"JOIN CRS_SOTTOPROCESSO AS SP ON AC.ID_SOTTOPROCESSO = SP.ID_SOTTOPROCESSO " + 
						"JOIN CRS_PROCESSO AS P ON SP.ID_PROCESSO = P.ID_PROCESSO " + 
						"JOIN CRS_AREA AS A ON P.ID_AREA = A.ID_AREA " + 
						"WHERE A.ID_AREA = " + idArea;
		
		Query querySel = em.createNativeQuery(queryStr);
		
		List<Object[]> lista = querySel.getResultList();
		
		return lista;	
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getAttivitaDettagliByIdArea(long idArea){
		String queryStr = "SELECT " + 
						"AD.ID_ATTIVITA_DETTAGLIO, " +
						"AD.ID_ATTIVITA_COMPONENTE, " +
						"AD.DESCRIZIONE, " +
						"AD.DATA_INIZIO, " + 
						"AD.DATA_FINE, " +
						"AD.STATO, " +
						"AD.ORDINAMENTO " +
						"FROM CRS_ATTIVITA_DETTAGLIO AS AD " +
						"JOIN CRS_ATTIVITA_COMPONENTE AS AC ON AD.ID_ATTIVITA_COMPONENTE = AC.ID_ATTIVITA_COMPONENTE " + 
						"JOIN CRS_SOTTOPROCESSO AS SP ON AC.ID_SOTTOPROCESSO = SP.ID_SOTTOPROCESSO " + 
						"JOIN CRS_PROCESSO AS P ON SP.ID_PROCESSO = P.ID_PROCESSO " + 
						"JOIN CRS_AREA AS A ON P.ID_AREA = A.ID_AREA " + 
						"WHERE A.ID_AREA = " + idArea;
		
		Query querySel = em.createNativeQuery(queryStr);
		
		List<Object[]> lista = querySel.getResultList();
		
		return lista;	
	}



	@Override
	public List<Object[]> getDocMediaAttComp(long idAttivitaComponente,int pageNumber, int pageSize, int columnNameToOrder,String orderType, String search) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String currentDate= dateFormat.format(date);

		List<Object[]> dominiList = new ArrayList<Object[]>();
		String queryStr =" select dc.ID, d.ID, d.NOME_FILE, d.DESCRIZIONE, d.DATA_INIZIO from CRS_DOCUMENTI_MEDIA d ,CRS_ASS_ATTIVITA_COMPONENTE_DOC_MEDIA dc "+
		   				" WHERE dc.ID_DOCUMENTO= d.id AND dc.ID_ATTIVITA_COMPONENTE="+idAttivitaComponente+" AND (d.DATA_FINE > '"+currentDate+"' OR d.DATA_FINE IS NULL)";

		if (!StringUtils.isEmpty(search)) {
			queryStr += " and (" + "upper(d.NOME_FILE) like UPPER('%"+ search + "%') " + ")";
		}
		if(pageNumber != -1){
			queryStr +=" ORDER BY "+(columnNameToOrder +2)+" "+orderType +" OFFSET "+pageNumber +" ROWS ";
			queryStr +=" FETCH NEXT "+pageSize+" ROWS ONLY ";
		}


		try {
			Query querySel = em.createNativeQuery(queryStr );
			dominiList =  querySel.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
			log.error("");
		}
		return dominiList;
	}
	
	@Override
	public Integer countDocMediaAttComp(long idAttivitaComponente, String search) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String currentDate= dateFormat.format(date);

		List<Object[]> dominiList = new ArrayList<Object[]>();
		String queryStr =" select count(dc.ID) from CRS_DOCUMENTI_MEDIA d ,CRS_ASS_ATTIVITA_COMPONENTE_DOC_MEDIA dc "+
		   				" WHERE dc.ID_DOCUMENTO= d.id AND dc.ID_ATTIVITA_COMPONENTE="+idAttivitaComponente+" AND (d.DATA_FINE > '"+currentDate+"' OR d.DATA_FINE IS NULL)";
		if (!StringUtils.isEmpty(search)) {
			queryStr += " and (" + "upper(d.NOME_FILE) like UPPER('%"+ search + "%') " +")";
		}

		int count=0;
		try {
			Query querySel = em.createNativeQuery(queryStr, Integer.class);
			count = (Integer) querySel.getSingleResult();

		} catch (Throwable e) {
			e.printStackTrace();
		}
		return count;
		
	}


	@Override
	public List<Object[]> getDocAttCompDaAssociare(long idAttivitaComponente,
			int pageNumber, int pageSize, int columnNameToOrder,
			String orderType, String search) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String currentDate= dateFormat.format(date);

		List<Object[]> nonAllegCtList = new ArrayList<Object[]>();
		String queryStr = "select d.ID, d.NOME_FILE, d.DESCRIZIONE, d.DATA_INIZIO from CRS_DOCUMENTI_MEDIA d "+
						  " WHERE (d.DATA_FINE > '"+currentDate+"' OR d.DATA_FINE IS NULL) AND d.ID "+
						  " NOT IN(select dc.ID_DOCUMENTO from CRS_ASS_ATTIVITA_COMPONENTE_DOC_MEDIA dc where dc.ID_ATTIVITA_COMPONENTE="+idAttivitaComponente+")";


		if (!StringUtils.isEmpty(search)) {
			queryStr += " and (" + "upper(d.NOME_FILE) like UPPER('%"+ search + "%') " + ")";
		}
		if(pageNumber != -1){
			queryStr +=" ORDER BY "+(columnNameToOrder +1)+" "+orderType +" OFFSET "+pageNumber +" ROWS ";
			queryStr +=" FETCH NEXT "+pageSize+" ROWS ONLY ";
		}


		try {
			Query querySel = em.createNativeQuery(queryStr );
			nonAllegCtList =  querySel.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
			log.error("");
		}
		return nonAllegCtList;
	}


	@Override
	public Integer countDocAttCompDaAssociare(long idAttivitaComponente,
			String search) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String currentDate= dateFormat.format(date);

		List<Object[]> dominiList = new ArrayList<Object[]>();
		String queryStr = "select count(d.ID) from CRS_DOCUMENTI_MEDIA d "+
		  " WHERE (d.DATA_FINE > '"+currentDate+"' OR d.DATA_FINE IS NULL) AND d.ID "+
		  " NOT IN(select dc.ID_DOCUMENTO from CRS_ASS_ATTIVITA_COMPONENTE_DOC_MEDIA dc where dc.ID_ATTIVITA_COMPONENTE="+idAttivitaComponente+")";

		if (!StringUtils.isEmpty(search)) {
			queryStr += " and (" + "upper(d.NOME_FILE) like UPPER('%"+ search + "%') " +")";
		}

		int count=0;
		try {
			Query querySel = em.createNativeQuery(queryStr, Integer.class);
			count = (Integer) querySel.getSingleResult();

		} catch (Throwable e) {
			e.printStackTrace();
		}
		return count;
		
	}


	@Override
	public List<Object[]> getListDominiAttComp(long idAttivitaComponente,
			int pageNumber, int pageSize, int columnNameToOrder,
			String orderType, String search) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String currentDate= dateFormat.format(date);

		List<Object[]> dominiList = new ArrayList<Object[]>();
		String queryStr =" select dc.ID, d.DESCRIZIONE, d.DATA_INIZIO "+
						 "from CRS_DOMINI d ,CRS_ASS_ATTIVITA_COMPONENTE_DOMINI dc "+
						 "WHERE dc.ID_DOMINIO= d.id AND dc.ID_ATTIVITA_COMPONENTE="+idAttivitaComponente+" AND "+ 
						 "(d.DATA_FINE > '"+currentDate+"' OR d.DATA_FINE IS NULL)";
						

		if (!StringUtils.isEmpty(search)) {
			queryStr += " and (" + "upper(d.DESCRIZIONE) like UPPER('%"+ search + "%') " + ")";
		}
		if(pageNumber != -1){
			queryStr +=" ORDER BY "+(columnNameToOrder +1)+" "+orderType +" OFFSET "+pageNumber +" ROWS ";
			queryStr +=" FETCH NEXT "+pageSize+" ROWS ONLY ";
		}


		try {
			Query querySel = em.createNativeQuery(queryStr );
			dominiList =  querySel.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
			log.error("");
		}
		return dominiList;
	}

	@Override
	public Integer countListDominiAttComp(long idAttivitaComponente,
			String search) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String currentDate= dateFormat.format(date);

		List<Object[]> dominiList = new ArrayList<Object[]>();
		String queryStr = "select count(dc.ID) "+
		 "from CRS_DOMINI d ,CRS_ASS_ATTIVITA_COMPONENTE_DOMINI dc "+
		 "WHERE dc.ID_DOMINIO= d.id AND dc.ID_ATTIVITA_COMPONENTE="+idAttivitaComponente+" AND "+ 
		 "(d.DATA_FINE > '"+currentDate+"' OR d.DATA_FINE IS NULL)";
		if (!StringUtils.isEmpty(search)) {
			queryStr += " and (" + "upper(d.descrizione) like UPPER('%"+ search + "%') " +")";
		}

		int count=0;
		try {
			Query querySel = em.createNativeQuery(queryStr, Integer.class);
			count = (Integer) querySel.getSingleResult();

		} catch (Throwable e) {
			e.printStackTrace();
		}
		return count;
	}
	
	@Override
	public List<Object[]> getListDominiAttCompDaAssociare(long idAttivitaComponente, int pageNumber, int pageSize,
			int columnNameToOrder, String orderType, String search) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String currentDate= dateFormat.format(date);

		List<Object[]> dominiList = new ArrayList<Object[]>();
		String queryStr ="  select d.ID, d.DESCRIZIONE, d.DATA_INIZIO "+
						 "from CRS_DOMINI d "+
						 " WHERE (d.DATA_FINE > '"+currentDate+"' OR d.DATA_FINE IS NULL) AND d.id not in "+
						" (select dc.ID_DOMINIO from CRS_ASS_ATTIVITA_COMPONENTE_DOMINI dc where dc.ID_ATTIVITA_COMPONENTE="+idAttivitaComponente+")";
						

		if (!StringUtils.isEmpty(search)) {
			queryStr += " and (" + "upper(d.DESCRIZIONE) like UPPER('%"+ search + "%') " + ")";
		}
		if(pageNumber != -1){
			queryStr +=" ORDER BY "+(columnNameToOrder +1)+" "+orderType +" OFFSET "+pageNumber +" ROWS ";
			queryStr +=" FETCH NEXT "+pageSize+" ROWS ONLY ";
		}


		try {
			Query querySel = em.createNativeQuery(queryStr );
			dominiList =  querySel.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
			log.error("");
		}
		return dominiList;
	}

	
	@Override
	public Integer countListDominiAttCompDaAssociare(long idAttivitaComponente,
			String search) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String currentDate= dateFormat.format(date);

		List<Object[]> dominiList = new ArrayList<Object[]>();
		String queryStr ="  select count(d.ID) "+
		 "from CRS_DOMINI d "+
		 " WHERE (d.DATA_FINE > '"+currentDate+"' OR d.DATA_FINE IS NULL) AND d.id not in "+
		" (select dc.ID_DOMINIO from CRS_ASS_ATTIVITA_COMPONENTE_DOMINI dc where dc.ID_ATTIVITA_COMPONENTE="+idAttivitaComponente+")";
		if (!StringUtils.isEmpty(search)) {
			queryStr += " and (" + "upper(d.descrizione) like UPPER('%"+ search + "%') " +")";
		}

		int count=0;
		try {
			Query querySel = em.createNativeQuery(queryStr, Integer.class);
			count = (Integer) querySel.getSingleResult();

		} catch (Throwable e) {
			e.printStackTrace();
		}
		return count;
	}
	

	@Override
	public List<Object[]> getListCompTecAttComp(long idAttivitaComponente,
			int pageNumber, int pageSize, int columnNameToOrder,
			String orderType, String search) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String currentDate= dateFormat.format(date);

		List<Object[]> dominiList = new ArrayList<Object[]>();
		String queryStr =" select dc.ID, ct.DESCRIZIONE, ct.CODICE, ct.AUTORE, ct.DATA_PUBBLICAZIONE, ct.VERSIONE, ct.ID_TIPO_COMP_TEC " +
						 "from CRS_COMPONENTE_TECNICO ct ,CRS_ASS_ATTIVITA_COMPONENTE_COMP_TEC dc "+
						 "WHERE dc.ID_COMPONENTE_TEC= ct.id AND dc.ID_ATTIVITA_COMPONENTE="+idAttivitaComponente+" AND "+ 
						 "(ct.DATA_FINE > '"+currentDate+"' OR ct.DATA_FINE IS NULL)";
						

		if (!StringUtils.isEmpty(search)) {
			queryStr += " and (" + "upper(ct.DESCRIZIONE) like UPPER('%"+ search + "%') " + ")";
		}
		if(pageNumber != -1){
			queryStr +=" ORDER BY "+(columnNameToOrder +1)+" "+orderType +" OFFSET "+pageNumber +" ROWS ";
			queryStr +=" FETCH NEXT "+pageSize+" ROWS ONLY ";
		}


		try {
			Query querySel = em.createNativeQuery(queryStr );
			dominiList =  querySel.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
			log.error("");
		}
		return dominiList;
	}
	
	@Override
	public Integer countListCompTecAttComp(long idAttivitaComponente,
			String search) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String currentDate= dateFormat.format(date);

		List<Object[]> dominiList = new ArrayList<Object[]>();
		String queryStr =" select count(dc.ID) "+
		 "from CRS_COMPONENTE_TECNICO ct ,CRS_ASS_ATTIVITA_COMPONENTE_COMP_TEC dc "+
		 "WHERE dc.ID_COMPONENTE_TEC= ct.id AND dc.ID_ATTIVITA_COMPONENTE="+idAttivitaComponente+" AND "+ 
		 "(ct.DATA_FINE > '"+currentDate+"' OR ct.DATA_FINE IS NULL)";
		if (!StringUtils.isEmpty(search)) {
			queryStr += " and (" + "upper(d.descrizione) like UPPER('%"+ search + "%') " +")";
		}

		int count=0;
		try {
			Query querySel = em.createNativeQuery(queryStr, Integer.class);
			count = (Integer) querySel.getSingleResult();

		} catch (Throwable e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public List<Object[]> getListCompTecAttCompDaAssociare(long idAttivitaComponente, int pageNumber, int pageSize,
			int columnNameToOrder, String orderType, String search) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String currentDate= dateFormat.format(date);

		List<Object[]> compTecList = new ArrayList<Object[]>();
		String queryStr ="  select ct.ID, ct.DESCRIZIONE, ct.CODICE, ct.AUTORE, ct.DATA_PUBBLICAZIONE, ct.VERSIONE, ct.ID_TIPO_COMP_TEC " +
						 "from CRS_COMPONENTE_TECNICO ct "+
						 " WHERE (ct.DATA_FINE > '"+currentDate+"' OR ct.DATA_FINE IS NULL) AND ct.id not in "+
						" (select dc.ID_COMPONENTE_TEC from CRS_ASS_ATTIVITA_COMPONENTE_COMP_TEC dc where dc.ID_ATTIVITA_COMPONENTE="+idAttivitaComponente+")";
						

		if (!StringUtils.isEmpty(search)) {
			queryStr += " and (" + "upper(ct.DESCRIZIONE) like UPPER('%"+ search + "%') " + ")";
		}
		if(pageNumber != -1){
			queryStr +=" ORDER BY "+(columnNameToOrder +1)+" "+orderType +" OFFSET "+pageNumber +" ROWS ";
			queryStr +=" FETCH NEXT "+pageSize+" ROWS ONLY ";
		}


		try {
			Query querySel = em.createNativeQuery(queryStr );
			compTecList =  querySel.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
			log.error("");
		}
		return compTecList;
	}
	
	@Override
	public Integer countListCompTecAttCompDaAssociare(
			long idAttivitaComponente, String getsSearch) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String currentDate= dateFormat.format(date);

		List<Object[]> dominiList = new ArrayList<Object[]>();
		String queryStr ="  select count(ct.ID) "+
		 "from CRS_COMPONENTE_TECNICO ct "+
		 " WHERE (ct.DATA_FINE > '"+currentDate+"' OR ct.DATA_FINE IS NULL) AND ct.id not in "+
		" (select dc.ID_COMPONENTE_TEC from CRS_ASS_ATTIVITA_COMPONENTE_COMP_TEC dc where dc.ID_ATTIVITA_COMPONENTE="+idAttivitaComponente+")";
		
		if (!StringUtils.isEmpty(getsSearch)) {
			queryStr += " and (" + "upper(ct.descrizione) like UPPER('%"+ getsSearch + "%') " +")";
		}

		int count=0;
		try {
			Query querySel = em.createNativeQuery(queryStr, Integer.class);
			count = (Integer) querySel.getSingleResult();

		} catch (Throwable e) {
			e.printStackTrace();
		}
		return count;
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getDocMediaProcesso(DocumentiProcessoPaginator filter) {

		List<Object[]> returnList = new ArrayList<Object[]>();
		String queryStr ="SELECT " + 
							"dc.ID, d.ID, d.NOME_FILE, d.DESCRIZIONE, d.DATA_INIZIO " +
							"FROM CRS_DOCUMENTI_MEDIA d " +
							"JOIN CRS_ASS_PROCESSO_DOC_MEDIA dc on dc.ID_DOCUMENTO = d.id "+
		   				"WHERE dc.ID_PROCESSO = " + filter.getIdProcesso();
		
		if (StringUtils.isNotEmpty(filter.getSSearch())) {
			queryStr += " and (" + "upper(d.NOME_FILE) like UPPER('%"+ filter.getSSearch() + "%') " + ")";
		}
		
		queryStr += " ORDER BY " + (filter.getISortColArray().get(0)) + " " + filter.getSSortDirArray().get(0);
		queryStr += " OFFSET " + filter.getiDisplayStart() + " ROWS FETCH NEXT " + filter.getiDisplayLength() + " ROWS ONLY";

		try {
			Query querySel = em.createNativeQuery(queryStr );
			returnList =  querySel.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
			log.error("");
		}
		return returnList;
	}
	
	@Override
	public Integer countDocMediaProcesso(DocumentiProcessoPaginator filter) {
		
		String queryStr ="SELECT COUNT(dc.ID) " + 
							"FROM CRS_DOCUMENTI_MEDIA d " +
							"JOIN CRS_ASS_PROCESSO_DOC_MEDIA dc on dc.ID_DOCUMENTO = d.id "+
							"WHERE dc.ID_PROCESSO = " + filter.getIdProcesso();
		
		
		if (StringUtils.isNotEmpty(filter.getSSearch())) {
			queryStr += " and (" + "upper(d.NOME_FILE) like UPPER('%"+ filter.getSSearch() + "%') " + ")";
		}

		int count=0;
		try {
			Query querySel = em.createNativeQuery(queryStr, Integer.class);
			count = (Integer) querySel.getSingleResult();

		} catch (Throwable e) {
			e.printStackTrace();
		}
		return count;
		
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getDocProcessoDaAssociare(DocumentiProcessoPaginator filter) {

		List<Object[]> nonAllegCtList = new ArrayList<Object[]>();
		
		String queryStr = "SELECT " + 
							"d.ID, d.NOME_FILE, d.DESCRIZIONE, d.DATA_INIZIO " +
							"FROM CRS_DOCUMENTI_MEDIA d " +
						  "WHERE d.ID " +
						  "NOT IN(select dc.ID_DOCUMENTO from CRS_ASS_PROCESSO_DOC_MEDIA dc where dc.ID_PROCESSO = " + filter.getIdProcesso() + ")";


		if (StringUtils.isNotEmpty(filter.getSSearch())) {
			queryStr += " and (" + "upper(d.NOME_FILE) like UPPER('%"+ filter.getSSearch() + "%') " + ")";
		}
		
		queryStr += " ORDER BY " + (filter.getISortColArray().get(0)) + " " + filter.getSSortDirArray().get(0);
		queryStr += " OFFSET " + filter.getiDisplayStart() + " ROWS FETCH NEXT " + filter.getiDisplayLength() + " ROWS ONLY";


		try {
			Query querySel = em.createNativeQuery(queryStr );
			nonAllegCtList =  querySel.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
			log.error("");
		}
		return nonAllegCtList;
	}


	@Override
	public Integer countDocProcessoDaAssociare(DocumentiProcessoPaginator filter) {

		String queryStr = "SELECT COUNT(d.ID) " +
						  	"FROM CRS_DOCUMENTI_MEDIA d "+
						"WHERE d.ID " +
						"NOT IN(select dc.ID_DOCUMENTO from CRS_ASS_PROCESSO_DOC_MEDIA dc where dc.ID_PROCESSO = " + filter.getIdProcesso() + ")";


			if (StringUtils.isNotEmpty(filter.getSSearch())) {
				queryStr += " and (" + "upper(d.NOME_FILE) like UPPER('%"+ filter.getSSearch() + "%') " + ")";
			}

		int count=0;
		try {
			Query querySel = em.createNativeQuery(queryStr, Integer.class);
			count = (Integer) querySel.getSingleResult();

		} catch (Throwable e) {
			e.printStackTrace();
		}
		return count;
		
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getDominiProcesso(DominiProcessoPaginator filter) {

		List<Object[]> returnList = new ArrayList<Object[]>();
		String queryStr ="SELECT " + 
							"dc.ID, d.DESCRIZIONE, d.DATA_INIZIO " +
							"FROM CRS_DOMINI d " +
							"JOIN CRS_ASS_PROCESSO_DOMINI dc on dc.ID_DOMINI = d.id "+
		   				"WHERE dc.ID_PROCESSO = " + filter.getIdProcesso();
		
		
		if (StringUtils.isNotEmpty(filter.getSSearch())) {
			queryStr += " and (" + "upper(d.DESCRIZIONE) like UPPER('%"+ filter.getSSearch() + "%') " + ")";
		}
		
		queryStr += " ORDER BY " + (filter.getISortColArray().get(0)) + " " + filter.getSSortDirArray().get(0);
		queryStr += " OFFSET " + filter.getiDisplayStart() + " ROWS FETCH NEXT " + filter.getiDisplayLength() + " ROWS ONLY";

		try {
			Query querySel = em.createNativeQuery(queryStr );
			returnList =  querySel.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
			log.error("");
		}
		return returnList;
	}
	
	@Override
	public Integer countDominiProcesso(DominiProcessoPaginator filter) {
		
		String queryStr ="SELECT COUNT(dc.ID) " + 
							"FROM CRS_DOMINI d " +
							"JOIN CRS_ASS_PROCESSO_DOMINI dc on dc.ID_DOMINI = d.id "+
							"WHERE dc.ID_PROCESSO = " + filter.getIdProcesso();
		
		
		if (StringUtils.isNotEmpty(filter.getSSearch())) {
			queryStr += " and (" + "upper(d.DESCRIZIONE) like UPPER('%"+ filter.getSSearch() + "%') " + ")";
		}

		int count=0;
		try {
			Query querySel = em.createNativeQuery(queryStr, Integer.class);
			count = (Integer) querySel.getSingleResult();

		} catch (Throwable e) {
			e.printStackTrace();
		}
		return count;
		
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getDominiProcessoDaAssociare(DominiProcessoPaginator filter) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String currentDate= dateFormat.format(date);
		List<Object[]> nonAllegCtList = new ArrayList<Object[]>();
		
		String queryStr = "SELECT " + 
							"d.ID, d.DESCRIZIONE, d.DATA_INIZIO " +
							"FROM CRS_DOMINI d " +
						  "WHERE (d.DATA_FINE > '"+currentDate+"' OR d.DATA_FINE IS NULL) AND d.ID " +
						  "NOT IN(select dc.ID_DOMINI from CRS_ASS_PROCESSO_DOMINI dc where dc.ID_PROCESSO = " + filter.getIdProcesso() + ")";


		if (StringUtils.isNotEmpty(filter.getSSearch())) {
			queryStr += " and (" + "upper(d.DESCRIZIONE) like UPPER('%"+ filter.getSSearch() + "%') " + ")";
		}
		
		queryStr += " ORDER BY " + (filter.getISortColArray().get(0)) + " " + filter.getSSortDirArray().get(0);
		queryStr += " OFFSET " + filter.getiDisplayStart() + " ROWS FETCH NEXT " + filter.getiDisplayLength() + " ROWS ONLY";


		try {
			Query querySel = em.createNativeQuery(queryStr );
			nonAllegCtList =  querySel.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
			log.error("");
		}
		return nonAllegCtList;
	}


	@Override
	public Integer countDominiProcessoDaAssociare(DominiProcessoPaginator filter) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String currentDate= dateFormat.format(date);
		String queryStr = "SELECT COUNT(d.ID) " +
						  	"FROM CRS_DOMINI d "+
						  "WHERE (d.DATA_FINE > '"+currentDate+"' OR d.DATA_FINE IS NULL) AND d.ID " +
						"NOT IN(select dc.ID_DOMINI from CRS_ASS_PROCESSO_DOMINI dc where dc.ID_PROCESSO = " + filter.getIdProcesso() + ")";


			if (StringUtils.isNotEmpty(filter.getSSearch())) {
				queryStr += " and (" + "upper(d.DESCRIZIONE) like UPPER('%"+ filter.getSSearch() + "%') " + ")";
			}

		int count=0;
		try {
			Query querySel = em.createNativeQuery(queryStr, Integer.class);
			count = (Integer) querySel.getSingleResult();

		} catch (Throwable e) {
			e.printStackTrace();
		}
		return count;
		
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getCompTecProcesso(CompTecniciProcessoPaginator filter) {

		List<Object[]> returnList = new ArrayList<Object[]>();
		String queryStr ="SELECT " + 
							"dc.ID, ct.DESCRIZIONE, ct.CODICE, ct.AUTORE, ct.DATA_PUBBLICAZIONE, ct.VERSIONE, ct.ID_TIPO_COMP_TEC " +
							"FROM CRS_COMPONENTE_TECNICO ct " +
							"JOIN CRS_ASS_PROCESSO_COMP_TEC dc on dc.ID_COMPONENTE_TEC = ct.id "+
		   				"WHERE dc.ID_PROCESSO = " + filter.getIdProcesso();
		
		if (StringUtils.isNotEmpty(filter.getSSearch())) {
			queryStr += " and (" + "upper(ct.DESCRIZIONE) like UPPER('%"+ filter.getSSearch() + "%') " + ")";
		}
		
		queryStr += " ORDER BY " + (filter.getISortColArray().get(0)) + " " + filter.getSSortDirArray().get(0);
		queryStr += " OFFSET " + filter.getiDisplayStart() + " ROWS FETCH NEXT " + filter.getiDisplayLength() + " ROWS ONLY";

		try {
			Query querySel = em.createNativeQuery(queryStr );
			returnList =  querySel.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
			log.error("");
		}
		return returnList;
	}
	
	@Override
	public Integer countCompTecProcesso(CompTecniciProcessoPaginator filter) {
		
		String queryStr ="SELECT COUNT(dc.ID) " + 
							"FROM CRS_COMPONENTE_TECNICO ct " +
							"JOIN CRS_ASS_PROCESSO_COMP_TEC dc on dc.ID_COMPONENTE_TEC = ct.id "+
							"WHERE dc.ID_PROCESSO = " + filter.getIdProcesso();
		
		
		if (StringUtils.isNotEmpty(filter.getSSearch())) {
			queryStr += " and (" + "upper(ct.DESCRIZIONE) like UPPER('%"+ filter.getSSearch() + "%') " + ")";
		}

		int count=0;
		try {
			Query querySel = em.createNativeQuery(queryStr, Integer.class);
			count = (Integer) querySel.getSingleResult();

		} catch (Throwable e) {
			e.printStackTrace();
		}
		return count;
		
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getCompTecProcessoDaAssociare(CompTecniciProcessoPaginator filter) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String currentDate= dateFormat.format(date);
		List<Object[]> nonAllegCtList = new ArrayList<Object[]>();
		
		String queryStr = "SELECT " + 
		"ct.ID, ct.DESCRIZIONE, ct.CODICE, ct.AUTORE, ct.DATA_PUBBLICAZIONE, ct.VERSIONE, ct.ID_TIPO_COMP_TEC " +
							"FROM CRS_COMPONENTE_TECNICO ct " +
						  "WHERE (ct.DATA_FINE > '"+currentDate+"' OR ct.DATA_FINE IS NULL) AND ct.ID " +
						  "NOT IN(select dc.ID_COMPONENTE_TEC from CRS_ASS_PROCESSO_COMP_TEC dc where dc.ID_PROCESSO = " + filter.getIdProcesso() + ")";


		if (StringUtils.isNotEmpty(filter.getSSearch())) {
			queryStr += " and (" + "upper(ct.DESCRIZIONE) like UPPER('%"+ filter.getSSearch() + "%') " + ")";
		}
		
		queryStr += " ORDER BY " + (filter.getISortColArray().get(0)) + " " + filter.getSSortDirArray().get(0);
		queryStr += " OFFSET " + filter.getiDisplayStart() + " ROWS FETCH NEXT " + filter.getiDisplayLength() + " ROWS ONLY";


		try {
			Query querySel = em.createNativeQuery(queryStr );
			nonAllegCtList =  querySel.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
			log.error("");
		}
		return nonAllegCtList;
	}


	@Override
	public Integer countCompTecProcessoDaAssociare(CompTecniciProcessoPaginator filter) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String currentDate= dateFormat.format(date);
		String queryStr = "SELECT COUNT(ct.ID) " +
							"FROM CRS_COMPONENTE_TECNICO ct " +
							"WHERE (ct.DATA_FINE > '"+currentDate+"' OR ct.DATA_FINE IS NULL) AND ct.ID " +
							  "NOT IN(select dc.ID_COMPONENTE_TEC from CRS_ASS_PROCESSO_COMP_TEC dc where dc.ID_PROCESSO = " + filter.getIdProcesso() + ")";


			if (StringUtils.isNotEmpty(filter.getSSearch())) {
				queryStr += " and (" + "upper(ct.DESCRIZIONE) like UPPER('%"+ filter.getSSearch() + "%') " + ")";
			}

		int count=0;
		try {
			Query querySel = em.createNativeQuery(queryStr, Integer.class);
			count = (Integer) querySel.getSingleResult();

		} catch (Throwable e) {
			e.printStackTrace();
		}
		return count;
		
	}
	
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getDocMediaSottoProcesso(DocumentiSottoProcessoPaginator filter) {

		List<Object[]> returnList = new ArrayList<Object[]>();
		String queryStr ="SELECT " + 
							"dc.ID, d.ID, d.NOME_FILE, d.DESCRIZIONE, d.DATA_INIZIO " +
							"FROM CRS_DOCUMENTI_MEDIA d " +
							"JOIN CRS_ASS_SOTTOPROCESSO_DOC_MEDIA dc on dc.ID_DOCUMENTO = d.id "+
		   				"WHERE dc.ID_SOTTOPROCESSO = " + filter.getIdSottoProcesso();
		
		if (StringUtils.isNotEmpty(filter.getSSearch())) {
			queryStr += " and (" + "upper(d.NOME_FILE) like UPPER('%"+ filter.getSSearch() + "%') " + ")";
		}
		
		queryStr += " ORDER BY " + (filter.getISortColArray().get(0)) + " " + filter.getSSortDirArray().get(0);
		queryStr += " OFFSET " + filter.getiDisplayStart() + " ROWS FETCH NEXT " + filter.getiDisplayLength() + " ROWS ONLY";

		try {
			Query querySel = em.createNativeQuery(queryStr );
			returnList =  querySel.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
			log.error("");
		}
		return returnList;
	}
	
	@Override
	public Integer countDocMediaSottoProcesso(DocumentiSottoProcessoPaginator filter) {
		
		String queryStr ="SELECT COUNT(dc.ID) " + 
							"FROM CRS_DOCUMENTI_MEDIA d " +
							"JOIN CRS_ASS_SOTTOPROCESSO_DOC_MEDIA dc on dc.ID_DOCUMENTO = d.id "+
							"WHERE dc.ID_SOTTOPROCESSO = " + filter.getIdSottoProcesso();
		
		
		if (StringUtils.isNotEmpty(filter.getSSearch())) {
			queryStr += " and (" + "upper(d.NOME_FILE) like UPPER('%"+ filter.getSSearch() + "%') " + ")";
		}

		int count=0;
		try {
			Query querySel = em.createNativeQuery(queryStr, Integer.class);
			count = (Integer) querySel.getSingleResult();

		} catch (Throwable e) {
			e.printStackTrace();
		}
		return count;
		
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getDocSottoProcessoDaAssociare(DocumentiSottoProcessoPaginator filter) {

		List<Object[]> nonAllegCtList = new ArrayList<Object[]>();
		
		String queryStr = "SELECT " + 
							"d.ID, d.NOME_FILE, d.DESCRIZIONE, d.DATA_INIZIO " +
							"FROM CRS_DOCUMENTI_MEDIA d " +
						  "WHERE d.ID " +
						  "NOT IN(select dc.ID_DOCUMENTO from CRS_ASS_SOTTOPROCESSO_DOC_MEDIA dc where dc.ID_SOTTOPROCESSO = " + filter.getIdSottoProcesso() + ")";


		if (StringUtils.isNotEmpty(filter.getSSearch())) {
			queryStr += " and (" + "upper(d.NOME_FILE) like UPPER('%"+ filter.getSSearch() + "%') " + ")";
		}
		
		queryStr += " ORDER BY " + (filter.getISortColArray().get(0)) + " " + filter.getSSortDirArray().get(0);
		queryStr += " OFFSET " + filter.getiDisplayStart() + " ROWS FETCH NEXT " + filter.getiDisplayLength() + " ROWS ONLY";


		try {
			Query querySel = em.createNativeQuery(queryStr );
			nonAllegCtList =  querySel.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
			log.error("");
		}
		return nonAllegCtList;
	}


	@Override
	public Integer countDocSottoProcessoDaAssociare(DocumentiSottoProcessoPaginator filter) {

		String queryStr = "SELECT COUNT(d.ID) " +
						  	"FROM CRS_DOCUMENTI_MEDIA d "+
						"WHERE d.ID " +
						"NOT IN(select dc.ID_DOCUMENTO from CRS_ASS_SOTTOPROCESSO_DOC_MEDIA dc where dc.ID_SOTTOPROCESSO = " + filter.getIdSottoProcesso() + ")";


			if (StringUtils.isNotEmpty(filter.getSSearch())) {
				queryStr += " and (" + "upper(d.NOME_FILE) like UPPER('%"+ filter.getSSearch() + "%') " + ")";
			}

		int count=0;
		try {
			Query querySel = em.createNativeQuery(queryStr, Integer.class);
			count = (Integer) querySel.getSingleResult();

		} catch (Throwable e) {
			e.printStackTrace();
		}
		return count;
		
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getDominiSottoProcesso(DominiSottoProcessoPaginator filter) {

		List<Object[]> returnList = new ArrayList<Object[]>();
		String queryStr ="SELECT " + 
							"dc.ID, d.DESCRIZIONE, d.DATA_INIZIO " +
							"FROM CRS_DOMINI d " +
							"JOIN CRS_ASS_SOTTOPROCESSO_DOMINI dc on dc.ID_DOMINI = d.id "+
		   				"WHERE dc.ID_SOTTOPROCESSO = " + filter.getIdSottoProcesso();
		
		
		if (StringUtils.isNotEmpty(filter.getSSearch())) {
			queryStr += " and (" + "upper(d.DESCRIZIONE) like UPPER('%"+ filter.getSSearch() + "%') " + ")";
		}
		
		queryStr += " ORDER BY " + (filter.getISortColArray().get(0)) + " " + filter.getSSortDirArray().get(0);
		queryStr += " OFFSET " + filter.getiDisplayStart() + " ROWS FETCH NEXT " + filter.getiDisplayLength() + " ROWS ONLY";

		try {
			Query querySel = em.createNativeQuery(queryStr );
			returnList =  querySel.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
			log.error("");
		}
		return returnList;
	}
	
	@Override
	public Integer countDominiSottoProcesso(DominiSottoProcessoPaginator filter) {
		
		String queryStr ="SELECT COUNT(dc.ID) " + 
							"FROM CRS_DOMINI d " +
							"JOIN CRS_ASS_SOTTOPROCESSO_DOMINI dc on dc.ID_DOMINI = d.id "+
							"WHERE dc.ID_SOTTOPROCESSO = " + filter.getIdSottoProcesso();
		
		
		if (StringUtils.isNotEmpty(filter.getSSearch())) {
			queryStr += " and (" + "upper(d.DESCRIZIONE) like UPPER('%"+ filter.getSSearch() + "%') " + ")";
		}

		int count=0;
		try {
			Query querySel = em.createNativeQuery(queryStr, Integer.class);
			count = (Integer) querySel.getSingleResult();

		} catch (Throwable e) {
			e.printStackTrace();
		}
		return count;
		
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getDominiSottoProcessoDaAssociare(DominiSottoProcessoPaginator filter) {

		List<Object[]> nonAllegCtList = new ArrayList<Object[]>();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String currentDate= dateFormat.format(date);
		String queryStr = "SELECT " + 
							"d.ID, d.DESCRIZIONE, d.DATA_INIZIO " +
							"FROM CRS_DOMINI d " +
							"WHERE (d.DATA_FINE > '"+currentDate+"' OR d.DATA_FINE IS NULL) AND d.ID " +
						  "NOT IN(select dc.ID_DOMINI from CRS_ASS_SOTTOPROCESSO_DOMINI dc where dc.ID_SOTTOPROCESSO = " + filter.getIdSottoProcesso() + ")";


		if (StringUtils.isNotEmpty(filter.getSSearch())) {
			queryStr += " and (" + "upper(d.DESCRIZIONE) like UPPER('%"+ filter.getSSearch() + "%') " + ")";
		}
		
		queryStr += " ORDER BY " + (filter.getISortColArray().get(0)) + " " + filter.getSSortDirArray().get(0);
		queryStr += " OFFSET " + filter.getiDisplayStart() + " ROWS FETCH NEXT " + filter.getiDisplayLength() + " ROWS ONLY";


		try {
			Query querySel = em.createNativeQuery(queryStr );
			nonAllegCtList =  querySel.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
			log.error("");
		}
		return nonAllegCtList;
	}


	@Override
	public Integer countDominiSottoProcessoDaAssociare(DominiSottoProcessoPaginator filter) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String currentDate= dateFormat.format(date);
		String queryStr = "SELECT COUNT(d.ID) " +
						  	"FROM CRS_DOMINI d "+
						"WHERE (d.DATA_FINE > '"+currentDate+"' OR d.DATA_FINE IS NULL) AND d.ID " +
						"NOT IN(select dc.ID_DOMINI from CRS_ASS_SOTTOPROCESSO_DOMINI dc where dc.ID_SOTTOPROCESSO = " + filter.getIdSottoProcesso() + ")";


			if (StringUtils.isNotEmpty(filter.getSSearch())) {
				queryStr += " and (" + "upper(d.DESCRIZIONE) like UPPER('%"+ filter.getSSearch() + "%') " + ")";
			}

		int count=0;
		try {
			Query querySel = em.createNativeQuery(queryStr, Integer.class);
			count = (Integer) querySel.getSingleResult();

		} catch (Throwable e) {
			e.printStackTrace();
		}
		return count;
		
	}

	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getCompTecSottoProcesso(CompTecniciSottoProcessoPaginator filter) {

		List<Object[]> returnList = new ArrayList<Object[]>();
		String queryStr ="SELECT " + 
							"dc.ID, ct.DESCRIZIONE, ct.CODICE, ct.AUTORE, ct.DATA_PUBBLICAZIONE, ct.VERSIONE, ct.ID_TIPO_COMP_TEC " +
							"FROM CRS_COMPONENTE_TECNICO ct " +
							"JOIN CRS_ASS_SOTTOPROCESSO_COMP_TEC dc on dc.ID_COMPONENTE_TEC = ct.id "+
		   				"WHERE dc.ID_SOTTOPROCESSO = " + filter.getIdSottoProcesso();
		
		if (StringUtils.isNotEmpty(filter.getSSearch())) {
			queryStr += " and (" + "upper(ct.DESCRIZIONE) like UPPER('%"+ filter.getSSearch() + "%') " + ")";
		}
		
		queryStr += " ORDER BY " + (filter.getISortColArray().get(0)) + " " + filter.getSSortDirArray().get(0);
		queryStr += " OFFSET " + filter.getiDisplayStart() + " ROWS FETCH NEXT " + filter.getiDisplayLength() + " ROWS ONLY";

		try {
			Query querySel = em.createNativeQuery(queryStr );
			returnList =  querySel.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
			log.error("");
		}
		return returnList;
	}
	
	@Override
	public Integer countCompTecSottoProcesso(CompTecniciSottoProcessoPaginator filter) {
		
		String queryStr ="SELECT COUNT(dc.ID) " + 
							"FROM CRS_COMPONENTE_TECNICO ct " +
							"JOIN CRS_ASS_SOTTOPROCESSO_COMP_TEC dc on dc.ID_COMPONENTE_TEC = ct.id "+
							"WHERE dc.ID_SOTTOPROCESSO = " + filter.getIdSottoProcesso();
		
		
		if (StringUtils.isNotEmpty(filter.getSSearch())) {
			queryStr += " and (" + "upper(ct.DESCRIZIONE) like UPPER('%"+ filter.getSSearch() + "%') " + ")";
		}

		int count=0;
		try {
			Query querySel = em.createNativeQuery(queryStr, Integer.class);
			count = (Integer) querySel.getSingleResult();

		} catch (Throwable e) {
			e.printStackTrace();
		}
		return count;
		
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getCompTecSottoProcessoDaAssociare(CompTecniciSottoProcessoPaginator filter) {

		List<Object[]> nonAllegCtList = new ArrayList<Object[]>();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String currentDate= dateFormat.format(date);
		
		String queryStr = "SELECT " + 
		"ct.ID, ct.DESCRIZIONE, ct.CODICE, ct.AUTORE, ct.DATA_PUBBLICAZIONE, ct.VERSIONE, ct.ID_TIPO_COMP_TEC " +
							"FROM CRS_COMPONENTE_TECNICO ct " +
						  "WHERE (ct.DATA_FINE > '"+currentDate+"' OR ct.DATA_FINE IS NULL) AND ct.ID " +
						  "NOT IN(select dc.ID_COMPONENTE_TEC from CRS_ASS_SOTTOPROCESSO_COMP_TEC dc where dc.ID_SOTTOPROCESSO = " + filter.getIdSottoProcesso() + ")";


		if (StringUtils.isNotEmpty(filter.getSSearch())) {
			queryStr += " and (" + "upper(ct.DESCRIZIONE) like UPPER('%"+ filter.getSSearch() + "%') " + ")";
		}
		
		queryStr += " ORDER BY " + (filter.getISortColArray().get(0)) + " " + filter.getSSortDirArray().get(0);
		queryStr += " OFFSET " + filter.getiDisplayStart() + " ROWS FETCH NEXT " + filter.getiDisplayLength() + " ROWS ONLY";


		try {
			Query querySel = em.createNativeQuery(queryStr );
			nonAllegCtList =  querySel.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
			log.error("");
		}
		return nonAllegCtList;
	}


	@Override
	public Integer countCompTecSottoProcessoDaAssociare(CompTecniciSottoProcessoPaginator filter) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String currentDate= dateFormat.format(date);
		String queryStr = "SELECT COUNT(ct.ID) " +
							"FROM CRS_COMPONENTE_TECNICO ct " +
							"WHERE (ct.DATA_FINE > '"+currentDate+"' OR ct.DATA_FINE IS NULL) AND ct.ID " +
							  "NOT IN(select dc.ID_COMPONENTE_TEC from CRS_ASS_SOTTOPROCESSO_COMP_TEC dc where dc.ID_SOTTOPROCESSO = " + filter.getIdSottoProcesso() + ")";


			if (StringUtils.isNotEmpty(filter.getSSearch())) {
				queryStr += " and (" + "upper(ct.DESCRIZIONE) like UPPER('%"+ filter.getSSearch() + "%') " + ")";
			}

		int count=0;
		try {
			Query querySel = em.createNativeQuery(queryStr, Integer.class);
			count = (Integer) querySel.getSingleResult();

		} catch (Throwable e) {
			e.printStackTrace();
		}
		return count;
		
	}
	
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getDocMediaAttivitaDettaglio(DocumentiAttivitaDettaglioPaginator filter) {

		List<Object[]> returnList = new ArrayList<Object[]>();
		String queryStr ="SELECT " + 
							"dc.ID, d.ID, d.NOME_FILE, d.DESCRIZIONE, d.DATA_INIZIO " +
							"FROM CRS_DOCUMENTI_MEDIA d " +
							"JOIN CRS_ASS_ATTIVITA_DETTAGLIO_DOC_MEDIA dc on dc.ID_DOCUMENTO = d.id "+
		   				"WHERE dc.ID_ATTIVITA_DETTAGLIO = " + filter.getIdAttivitaDettaglio();
		
		if (StringUtils.isNotEmpty(filter.getSSearch())) {
			queryStr += " and (" + "upper(d.NOME_FILE) like UPPER('%"+ filter.getSSearch() + "%') " + ")";
		}
		
		queryStr += " ORDER BY " + (filter.getISortColArray().get(0)) + " " + filter.getSSortDirArray().get(0);
		queryStr += " OFFSET " + filter.getiDisplayStart() + " ROWS FETCH NEXT " + filter.getiDisplayLength() + " ROWS ONLY";

		try {
			Query querySel = em.createNativeQuery(queryStr );
			returnList =  querySel.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
			log.error("");
		}
		return returnList;
	}
	
	@Override
	public Integer countDocMediaAttivitaDettaglio(DocumentiAttivitaDettaglioPaginator filter) {
		
		String queryStr ="SELECT COUNT(dc.ID) " + 
							"FROM CRS_DOCUMENTI_MEDIA d " +
							"JOIN CRS_ASS_ATTIVITA_DETTAGLIO_DOC_MEDIA dc on dc.ID_DOCUMENTO = d.id "+
							"WHERE dc.ID_ATTIVITA_DETTAGLIO = " + filter.getIdAttivitaDettaglio();
		
		
		if (StringUtils.isNotEmpty(filter.getSSearch())) {
			queryStr += " and (" + "upper(d.NOME_FILE) like UPPER('%"+ filter.getSSearch() + "%') " + ")";
		}

		int count=0;
		try {
			Query querySel = em.createNativeQuery(queryStr, Integer.class);
			count = (Integer) querySel.getSingleResult();

		} catch (Throwable e) {
			e.printStackTrace();
		}
		return count;
		
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getDocAttivitaDettaglioDaAssociare(DocumentiAttivitaDettaglioPaginator filter) {

		List<Object[]> nonAllegCtList = new ArrayList<Object[]>();
		
		String queryStr = "SELECT " + 
							"d.ID, d.NOME_FILE, d.DESCRIZIONE, d.DATA_INIZIO " +
							"FROM CRS_DOCUMENTI_MEDIA d " +
						  "WHERE d.ID " +
						  "NOT IN(select dc.ID_DOCUMENTO from CRS_ASS_ATTIVITA_DETTAGLIO_DOC_MEDIA dc where dc.ID_ATTIVITA_DETTAGLIO = " + filter.getIdAttivitaDettaglio() + ")";


		if (StringUtils.isNotEmpty(filter.getSSearch())) {
			queryStr += " and (" + "upper(d.NOME_FILE) like UPPER('%"+ filter.getSSearch() + "%') " + ")";
		}
		
		queryStr += " ORDER BY " + (filter.getISortColArray().get(0)) + " " + filter.getSSortDirArray().get(0);
		queryStr += " OFFSET " + filter.getiDisplayStart() + " ROWS FETCH NEXT " + filter.getiDisplayLength() + " ROWS ONLY";


		try {
			Query querySel = em.createNativeQuery(queryStr );
			nonAllegCtList =  querySel.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
			log.error("");
		}
		return nonAllegCtList;
	}


	@Override
	public Integer countDocAttivitaDettaglioDaAssociare(DocumentiAttivitaDettaglioPaginator filter) {

		String queryStr = "SELECT COUNT(d.ID) " +
						  	"FROM CRS_DOCUMENTI_MEDIA d "+
						"WHERE d.ID " +
						"NOT IN(select dc.ID_DOCUMENTO from CRS_ASS_ATTIVITA_DETTAGLIO_DOC_MEDIA dc where dc.ID_ATTIVITA_DETTAGLIO = " + filter.getIdAttivitaDettaglio() + ")";


			if (StringUtils.isNotEmpty(filter.getSSearch())) {
				queryStr += " and (" + "upper(d.NOME_FILE) like UPPER('%"+ filter.getSSearch() + "%') " + ")";
			}

		int count=0;
		try {
			Query querySel = em.createNativeQuery(queryStr, Integer.class);
			count = (Integer) querySel.getSingleResult();

		} catch (Throwable e) {
			e.printStackTrace();
		}
		return count;
		
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getDominiAttivitaDettaglio(DominiAttivitaDettaglioPaginator filter) {

		List<Object[]> returnList = new ArrayList<Object[]>();
		String queryStr ="SELECT " + 
							"dc.ID, d.DESCRIZIONE, d.DATA_INIZIO " +
							"FROM CRS_DOMINI d " +
							"JOIN CRS_ASS_ATTIVITA_DETTAGLIO_DOMINI dc on dc.ID_DOMINI = d.id "+
		   				"WHERE dc.ID_ATTIVITA_DETTAGLIO = " + filter.getIdAttivitaDettaglio();
		
		
		if (StringUtils.isNotEmpty(filter.getSSearch())) {
			queryStr += " and (" + "upper(d.DESCRIZIONE) like UPPER('%"+ filter.getSSearch() + "%') " + ")";
		}
		
		queryStr += " ORDER BY " + (filter.getISortColArray().get(0)) + " " + filter.getSSortDirArray().get(0);
		queryStr += " OFFSET " + filter.getiDisplayStart() + " ROWS FETCH NEXT " + filter.getiDisplayLength() + " ROWS ONLY";

		try {
			Query querySel = em.createNativeQuery(queryStr );
			returnList =  querySel.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
			log.error("");
		}
		return returnList;
	}
	
	@Override
	public Integer countDominiAttivitaDettaglio(DominiAttivitaDettaglioPaginator filter) {
		
		String queryStr ="SELECT COUNT(dc.ID) " + 
							"FROM CRS_DOMINI d " +
							"JOIN CRS_ASS_ATTIVITA_DETTAGLIO_DOMINI dc on dc.ID_DOMINI = d.id "+
							"WHERE dc.ID_ATTIVITA_DETTAGLIO = " + filter.getIdAttivitaDettaglio();
		
		
		if (StringUtils.isNotEmpty(filter.getSSearch())) {
			queryStr += " and (" + "upper(d.DESCRIZIONE) like UPPER('%"+ filter.getSSearch() + "%') " + ")";
		}

		int count=0;
		try {
			Query querySel = em.createNativeQuery(queryStr, Integer.class);
			count = (Integer) querySel.getSingleResult();

		} catch (Throwable e) {
			e.printStackTrace();
		}
		return count;
		
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getDominiAttivitaDettaglioDaAssociare(DominiAttivitaDettaglioPaginator filter) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String currentDate= dateFormat.format(date);
		List<Object[]> nonAllegCtList = new ArrayList<Object[]>();
		
		String queryStr = "SELECT " + 
							"d.ID, d.DESCRIZIONE, d.DATA_INIZIO " +
							"FROM CRS_DOMINI d " +
							  "WHERE (d.DATA_FINE > '"+currentDate+"' OR d.DATA_FINE IS NULL) AND d.ID " +
						  "NOT IN(select dc.ID_DOMINI from CRS_ASS_ATTIVITA_DETTAGLIO_DOMINI dc where dc.ID_ATTIVITA_DETTAGLIO = " + filter.getIdAttivitaDettaglio() + ")";


		if (StringUtils.isNotEmpty(filter.getSSearch())) {
			queryStr += " and (" + "upper(d.DESCRIZIONE) like UPPER('%"+ filter.getSSearch() + "%') " + ")";
		}
		
		queryStr += " ORDER BY " + (filter.getISortColArray().get(0)) + " " + filter.getSSortDirArray().get(0);
		queryStr += " OFFSET " + filter.getiDisplayStart() + " ROWS FETCH NEXT " + filter.getiDisplayLength() + " ROWS ONLY";


		try {
			Query querySel = em.createNativeQuery(queryStr );
			nonAllegCtList =  querySel.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
			log.error("");
		}
		return nonAllegCtList;
	}


	@Override
	public Integer countDominiAttivitaDettaglioDaAssociare(DominiAttivitaDettaglioPaginator filter) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String currentDate= dateFormat.format(date);
		String queryStr = "SELECT COUNT(d.ID) " +
						  	"FROM CRS_DOMINI d "+
						    "WHERE (d.DATA_FINE > '"+currentDate+"' OR d.DATA_FINE IS NULL) AND d.ID " +
						"NOT IN(select dc.ID_DOMINI from CRS_ASS_ATTIVITA_DETTAGLIO_DOMINI dc where dc.ID_ATTIVITA_DETTAGLIO = " + filter.getIdAttivitaDettaglio() + ")";


			if (StringUtils.isNotEmpty(filter.getSSearch())) {
				queryStr += " and (" + "upper(d.DESCRIZIONE) like UPPER('%"+ filter.getSSearch() + "%') " + ")";
			}

		int count=0;
		try {
			Query querySel = em.createNativeQuery(queryStr, Integer.class);
			count = (Integer) querySel.getSingleResult();

		} catch (Throwable e) {
			e.printStackTrace();
		}
		return count;
		
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getCompTecAttivitaDettaglio(CompTecniciAttivitaDettaglioPaginator filter) {

		List<Object[]> returnList = new ArrayList<Object[]>();
		String queryStr ="SELECT " + 
		"dc.ID, ct.DESCRIZIONE, ct.CODICE, ct.AUTORE, ct.DATA_PUBBLICAZIONE, ct.VERSIONE, ct.ID_TIPO_COMP_TEC " +
							"FROM CRS_COMPONENTE_TECNICO ct " +
							"JOIN CRS_ASS_ATTIVITA_DETTAGLIO_COMP_TEC dc on dc.ID_COMPONENTE_TEC = ct.id "+
		   				"WHERE dc.ID_ATTIVITA_DETTAGLIO = " + filter.getIdAttivitaDettaglio();
		
		if (StringUtils.isNotEmpty(filter.getSSearch())) {
			queryStr += " and (" + "upper(ct.DESCRIZIONE) like UPPER('%"+ filter.getSSearch() + "%') " + ")";
		}
		
		queryStr += " ORDER BY " + (filter.getISortColArray().get(0)) + " " + filter.getSSortDirArray().get(0);
		queryStr += " OFFSET " + filter.getiDisplayStart() + " ROWS FETCH NEXT " + filter.getiDisplayLength() + " ROWS ONLY";

		try {
			Query querySel = em.createNativeQuery(queryStr );
			returnList =  querySel.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
			log.error("");
		}
		return returnList;
	}
	
	@Override
	public Integer countCompTecAttivitaDettaglio(CompTecniciAttivitaDettaglioPaginator filter) {
		
		String queryStr ="SELECT COUNT(dc.ID) " + 
							"FROM CRS_COMPONENTE_TECNICO ct " +
							"JOIN CRS_ASS_ATTIVITA_DETTAGLIO_COMP_TEC dc on dc.ID_COMPONENTE_TEC = ct.id "+
							"WHERE dc.ID_ATTIVITA_DETTAGLIO = " + filter.getIdAttivitaDettaglio();
		
		
		if (StringUtils.isNotEmpty(filter.getSSearch())) {
			queryStr += " and (" + "upper(ct.DESCRIZIONE) like UPPER('%"+ filter.getSSearch() + "%') " + ")";
		}

		int count=0;
		try {
			Query querySel = em.createNativeQuery(queryStr, Integer.class);
			count = (Integer) querySel.getSingleResult();

		} catch (Throwable e) {
			e.printStackTrace();
		}
		return count;
		
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getCompTecAttivitaDettaglioDaAssociare(CompTecniciAttivitaDettaglioPaginator filter) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String currentDate= dateFormat.format(date);
		List<Object[]> nonAllegCtList = new ArrayList<Object[]>();
		
		String queryStr = "SELECT " + 
		"ct.ID, ct.DESCRIZIONE, ct.CODICE, ct.AUTORE, ct.DATA_PUBBLICAZIONE, ct.VERSIONE, ct.ID_TIPO_COMP_TEC " +
							"FROM CRS_COMPONENTE_TECNICO ct " +
						  "WHERE (ct.DATA_FINE > '"+currentDate+"' OR ct.DATA_FINE IS NULL) AND ct.ID " +
						  "NOT IN(select dc.ID_COMPONENTE_TEC from CRS_ASS_ATTIVITA_DETTAGLIO_COMP_TEC dc where dc.ID_ATTIVITA_DETTAGLIO = " + filter.getIdAttivitaDettaglio() + ")";


		if (StringUtils.isNotEmpty(filter.getSSearch())) {
			queryStr += " and (" + "upper(ct.DESCRIZIONE) like UPPER('%"+ filter.getSSearch() + "%') " + ")";
		}
		
		queryStr += " ORDER BY " + (filter.getISortColArray().get(0)) + " " + filter.getSSortDirArray().get(0);
		queryStr += " OFFSET " + filter.getiDisplayStart() + " ROWS FETCH NEXT " + filter.getiDisplayLength() + " ROWS ONLY";


		try {
			Query querySel = em.createNativeQuery(queryStr );
			nonAllegCtList =  querySel.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
			log.error("");
		}
		return nonAllegCtList;
	}


	@Override
	public Integer countCompTecAttivitaDettaglioDaAssociare(CompTecniciAttivitaDettaglioPaginator filter) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String currentDate= dateFormat.format(date);
		String queryStr = "SELECT COUNT(ct.ID) " +
							"FROM CRS_COMPONENTE_TECNICO ct " +
							  "WHERE (ct.DATA_FINE > '"+currentDate+"' OR ct.DATA_FINE IS NULL) AND ct.ID " +
							  "NOT IN(select dc.ID_COMPONENTE_TEC from CRS_ASS_ATTIVITA_DETTAGLIO_COMP_TEC dc where dc.ID_ATTIVITA_DETTAGLIO = " + filter.getIdAttivitaDettaglio() + ")";


			if (StringUtils.isNotEmpty(filter.getSSearch())) {
				queryStr += " and (" + "upper(ct.DESCRIZIONE) like UPPER('%"+ filter.getSSearch() + "%') " + ")";
			}

		int count=0;
		try {
			Query querySel = em.createNativeQuery(queryStr, Integer.class);
			count = (Integer) querySel.getSingleResult();

		} catch (Throwable e) {
			e.printStackTrace();
		}
		return count;
		
	}
	
	
	
	

	/*
	 * O P C O M U N I 
	 */
	
	@Transactional
	public <T> T salva(T entity){
		try{
			return em.merge(entity);
		}catch(Exception e){
			e.printStackTrace();
		}

		return null;
	}

	@Transactional
	public <T> T cerca(Class<T> obj , Object pk) {

		try {
			return (T) em.find(obj,pk);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Transactional
	public void remove(Object o) {
		try{
			em.remove(o);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
