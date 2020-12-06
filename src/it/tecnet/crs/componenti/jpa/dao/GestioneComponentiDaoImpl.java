package it.tecnet.crs.componenti.jpa.dao;

import it.tecnet.crs.componenti.jpa.model.CrsCircolariInps;
import it.tecnet.crs.componenti.jpa.model.CrsDatiTipo;
import it.tecnet.crs.componenti.jpa.model.CrsDescrizioneTipo;
import it.tecnet.crs.componenti.jpa.model.CrsLeggiDecreti;
import it.tecnet.crs.componenti.jpa.model.CrsMessaggiInps;
import it.tecnet.crs.componenti.jpa.model.CrsNoteDecreti;
import it.tecnet.crs.componenti.jpa.model.CrsTipo;
import it.tecnet.crs.componenti.jpa.model.CrsTplCompTecnico;
import it.tecnet.crs.componenti.jpa.model.CrsTplDocMedia;
import it.tecnet.crs.componenti.web.bean.DominiValoriPaginator;
import it.tecnet.crs.componenti.web.bean.EnteEmittente;
import it.tecnet.crs.componenti.web.bean.NormativaTablePaginator;
import it.tecnet.crs.jpa.model.AuMRisepr;
import it.tecnet.crs.mod.jpa.model.CrsProcesso;
import it.tecnet.crs.util.ApplicationUtil;
import it.tecnet.crs.util.TipoNormativaEnum;

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

public class GestioneComponentiDaoImpl implements GestioneComponentiDao{

	protected static Logger log = Logger.getLogger(GestioneComponentiDaoImpl.class);
	@PersistenceContext()
	private EntityManager em;


	public GestioneComponentiDaoImpl() {

	}



	/*
	 * 		NORMATIVA
	 * */


	@SuppressWarnings("unchecked")
	public List<CrsDescrizioneTipo> getListaNormativaLabel(long idTipo){

		List<CrsDescrizioneTipo> model = new ArrayList<CrsDescrizioneTipo>();

		String queryStr = "select * from CRS_DESCRIZIONE_TIPO where id_tipo= " + idTipo;	

		try {
			Query querySel = em.createNativeQuery(queryStr, CrsDescrizioneTipo.class);
			model = querySel.getResultList();

		} catch (Throwable e) {
			e.printStackTrace();
			log.error(e);
		}
		return model;
	}



	@Transactional
	public void saveNormativa(CrsDatiTipo normativa){

		try{
			em.merge(normativa);
		}catch(Exception e){
			log.error(e);
			e.printStackTrace();
		}

	}

//	@Transactional
//	public void deleteNormativa(long idDatiTipo){
//
//		Validate.notNull(idDatiTipo, "Id " + idDatiTipo + " non valorizzato.");
//
//		CrsDatiTipo normativa = em.find(CrsDatiTipo.class, idDatiTipo);
//
//		Validate.notNull(normativa, "La Normativa con id " + idDatiTipo + " non esiste.");
//
//		em.remove(normativa);
//
//	}

	//	@SuppressWarnings("unchecked")
	//	public List<Object[]> getListaNormative(NormativaTablePaginator filter){
	//
	//		String queryStr = "SELECT " +
	//		"a.ID_DATI_TIPO, a.DATE1, a.DATE2, " +
	//		"TEXT1, TEXT2, TEXT3, TEXT4, TEXT5, TEXT6, TEXT7, TEXT8, TEXT9, TEXT10, " +
	//		"NUMBER1, NUMBER2, NUMBER3, NUMBER4, NUMBER5, NUMBER6, NUMBER7, NUMBER8, NUMBER9, NUMBER10 " +
	//		"from CRS_DATI_TIPO as a join CRS_DESCRIZIONE_TIPO as b on a.ID_DESCRIZIONE_TIPO = b.ID_DESCRIZIONE_TIPO ";
	//		if(filter.getIdProcesso() != 0){
	//			queryStr += " join CRS_ASS_PROCESSO_CLASSE as pc on pc.ID_DATI_TIPO = a.ID_DATI_TIPO ";
	//		}
	//		if(filter.getIdSottoProcesso() != 0){
	//			queryStr += " join CRS_ASS_SOTTOPROCESSO_CLASSE as pc on pc.ID_DATI_TIPO = a.ID_DATI_TIPO ";
	//		}
	//		if(filter.getIdAttivitaComponente() != 0){
	//			queryStr += " join CRS_ASS_AC_CLASSE as pc on pc.ID_DATI_TIPO = a.ID_DATI_TIPO ";
	//		}
	//		if(filter.getIdAttivitaDettaglio() != 0){
	//			queryStr += " join CRS_ASS_AD_CLASSE as pc on pc.ID_DATI_TIPO = a.ID_DATI_TIPO ";
	//		}
	//		queryStr += " where 1 = 1  and b.ID_TIPO = " + filter.getIdTipo();
	//
	//		if(filter.getIdProcesso() != 0){
	//			queryStr += " and pc.ID_PROCESSO = " + filter.getIdProcesso();
	//		}
	//		if(filter.getIdSottoProcesso() != 0){
	//			queryStr += " and pc.ID_SOTTOPROCESSO = " + filter.getIdSottoProcesso();
	//		}
	//		if(filter.getIdAttivitaComponente() != 0){
	//			queryStr += " and pc.ID_ATTIVITA_COMPONENTE = " + filter.getIdAttivitaComponente();
	//		}
	//		if(filter.getIdAttivitaDettaglio() != 0){
	//			queryStr += " and pc.ID_ATTIVITA_DETTAGLIO = " + filter.getIdAttivitaDettaglio();
	//		}
	//
	//
	//		if(StringUtils.isNotEmpty(filter.getsSearch())){
	//			queryStr += " and ("; 
	//			queryStr += " UPPER(TEXT3) like UPPER('%" + filter.getsSearch() + "%') ";
	//			queryStr += " )";
	//
	//		}
	//
	//		queryStr += " ORDER BY " + (filter.getiSortCol_0()+1) + " " + filter.getsSortDir_0();
	//		queryStr += " OFFSET " + filter.getiDisplayStart() + " ROWS FETCH NEXT " + filter.getiDisplayLength() + " ROWS ONLY";
	//
	//		Query querySel = em.createNativeQuery(queryStr);
	//
	//		List<Object[]> listaReturn = querySel.getResultList();
	//
	//		return listaReturn;
	//
	//	}

	//	public Integer countAllNormative(NormativaTablePaginator filter){
	//
	//		String queryStr = "SELECT count(a.ID_DATI_TIPO) " +
	//		"from CRS_DATI_TIPO as a join CRS_DESCRIZIONE_TIPO as b on a.ID_DESCRIZIONE_TIPO = b.ID_DESCRIZIONE_TIPO ";
	//
	//		if(filter.getIdProcesso() != 0){
	//			queryStr += " join CRS_ASS_PROCESSO_CLASSE as pc on pc.ID_DATI_TIPO = a.ID_DATI_TIPO ";
	//		}
	//		if(filter.getIdSottoProcesso() != 0){
	//			queryStr += " join CRS_ASS_SOTTOPROCESSO_CLASSE as pc on pc.ID_DATI_TIPO = a.ID_DATI_TIPO ";
	//		}
	//		if(filter.getIdAttivitaComponente() != 0){
	//			queryStr += " join CRS_ASS_AC_CLASSE as pc on pc.ID_DATI_TIPO = a.ID_DATI_TIPO ";
	//		}
	//		if(filter.getIdAttivitaDettaglio() != 0){
	//			queryStr += " join CRS_ASS_AD_CLASSE as pc on pc.ID_DATI_TIPO = a.ID_DATI_TIPO ";
	//		}
	//
	//		queryStr += " where b.ID_TIPO = " + filter.getIdTipo();
	//
	//		if(filter.getIdProcesso() != 0){
	//			queryStr += " and pc.ID_PROCESSO = " + filter.getIdProcesso();
	//		}
	//		if(filter.getIdSottoProcesso() != 0){
	//			queryStr += " and pc.ID_SOTTOPROCESSO = " + filter.getIdSottoProcesso();
	//		}
	//		if(filter.getIdAttivitaComponente() != 0){
	//			queryStr += " and pc.ID_ATTIVITA_COMPONENTE = " + filter.getIdAttivitaComponente();
	//		}
	//		if(filter.getIdAttivitaDettaglio() != 0){
	//			queryStr += " and pc.ID_ATTIVITA_DETTAGLIO = " + filter.getIdAttivitaDettaglio();
	//		}					
	//
	//
	//		if(StringUtils.isNotEmpty(filter.getsSearch())){
	//			queryStr += " and ("; 
	//			queryStr += " UPPER(TEXT3) like UPPER('%" + filter.getsSearch() + "%') ";
	//			queryStr += " )";
	//
	//		}
	//
	//		Query querySel = em.createNativeQuery(queryStr);
	//
	//		Integer count =  (Integer)querySel.getSingleResult();
	//		return count == null ? 0 : count;
	//	}


	@SuppressWarnings("unchecked")
	public List<CrsTipo> getComboTipoNormativa(long idClasse){

		String queryStr = "SELECT " + 
		"ID_TIPO, " + 
		"DESCRIZIONE " + 
		"FROM CRS_TIPO " +
		"WHERE ID_CLASSE = " + idClasse +
		" ORDER BY DESCRIZIONE ";

		Query querySel = em.createNativeQuery(queryStr, CrsTipo.class);

		List<CrsTipo> lista = querySel.getResultList();

		return lista;	
	}


	public CrsDatiTipo getNormativaById(Long idDatiTipo){

		CrsDatiTipo model = em.find(CrsDatiTipo.class, idDatiTipo);

		Validate.notNull(model, "La Normativa con id " + idDatiTipo + " non esiste.");

		return model;
	}



	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getCrsAssProcByIdDatiTipo(long idNormativa, long idTipo) {
		List<Object[]> list = new ArrayList<Object[]>();
		String queryStr = "select " + 
							"ID_ASS_PROCESSO_CLASSE, ID_PROCESSO, ID_NORMATIVA, ID_TIPO " +
							"from CRS_ASS_PROCESSO_CLASSE " +
							"where ID_NORMATIVA = "+ idNormativa +
							" and ID_TIPO = " + idTipo;

		try {
			Query querySel = em.createNativeQuery(queryStr );

			list =  querySel.getResultList();
			if(list != null || list.size() > 0){
				return list;
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.error("Errore durante la verifica di crs ass processo by id dati tipo");
		}
		return null;




	}



	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getCrsAssSottProcByIdDatiTipo(long idNormativa, long idTipo) {
		List<Object[]> list = new ArrayList<Object[]>();
		String queryStr = "SELECT " + 
							"ID_ASS_SOTTOPROCESSO_CLASSE, ID_SOTTOPROCESSO, ID_NORMATIVA, ID_TIPO "+
							"from CRS_ASS_SOTTOPROCESSO_CLASSE "+
							"where ID_NORMATIVA =  " + idNormativa +
							" and ID_TIPO = " + idTipo;

		try {
			Query querySel = em.createNativeQuery(queryStr );

			list =  querySel.getResultList();
			if(list != null || list.size() > 0){
				return list;
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.error("Errore durante la verifica di crs ass sottoprocesso by id dati tipo");
		}
		return null;
	}

	/*
	 *  GESTIONE COMPONENTI - DOCUMENTI MEDIA__________________________________________________________
	 */



	@Override
	public List<Object[]> getListDocMedia(Integer pageNumber, Integer pageSize,
			int columnNameToOrder, String orderType, String search) {

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String currentDate= dateFormat.format(date);

		List<Object[]> docMediaList = new ArrayList<Object[]>();
		String queryStr = "select dc.ID, dc.codice, dc.DESCRIZIONE, dc.DATA_INIZIO, dc.DATA_FINE from CRS_DOCUMENTI_MEDIA dc "+
		" where dc.DATA_FINE > '"+currentDate+"' OR dc.DATA_FINE IS NULL";

		if (!StringUtils.isEmpty(search)) {
			queryStr += " and (" + "upper(dc.descrizione) like UPPER('%"+ search + "%') " + ")";
		}
		if(pageNumber != -1){
			queryStr +=" ORDER BY "+(columnNameToOrder +2)+" "+orderType +" OFFSET "+pageNumber +" ROWS ";
			queryStr +=" FETCH NEXT "+pageSize+" ROWS ONLY ";
		}


		try {
			Query querySel = em.createNativeQuery(queryStr );
			docMediaList =  querySel.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
			log.error("");
		}
		return docMediaList;
	}



	@Override
	public Integer countListDocMedia(String search) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String currentDate= dateFormat.format(date);
		String queryStr = "select count(dc.ID) from CRS_DOCUMENTI_MEDIA dc "+
		" where dc.DATA_FINE > '"+currentDate+"' OR dc.DATA_FINE IS NULL";

		if (!StringUtils.isEmpty(search)) {
			queryStr += " and (" + "upper(dc.descrizione) like UPPER('%"+ search + "%') " +")";
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
	public Object[] checkCodiceEsistente(String codice) {
		List<Object[]> docMedia =null;
		String queryStr = "select dc.ID, dc.DESCRIZIONE from CRS_DOCUMENTI_MEDIA dc "+
		" where dc.codice = '"+codice+"'";
		try {
			Query querySel = em.createNativeQuery(queryStr);

			docMedia =  querySel.getResultList();
			if(docMedia != null && docMedia.size() > 0){
				return docMedia.get(0);
			}

		} catch (Exception e) {

			log.error("Errore durante la verifica del codice documenti media");
		}
		return null;


	}

	@Override
	public List<Object[]> getListDocMediaFiltro(Integer pageNumber, Integer pageSize,
			int columnNameToOrder, String orderType, String search) {

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String currentDate= dateFormat.format(date);

		List<Object[]> docMediaList = new ArrayList<Object[]>();
		String queryStr = "select dc.ID, dc.codice, dc.DESCRIZIONE, dc.DATA_INIZIO, dc.DATA_FINE from CRS_DOCUMENTI_MEDIA dc "+
		" where dc.DATA_FINE < '"+currentDate+"'";

		if (!StringUtils.isEmpty(search)) {
			queryStr += " and (" + "upper(dc.descrizione) like UPPER('%"+ search + "%') " + ")";
		}
		if(pageNumber != -1){
			queryStr +=" ORDER BY "+(columnNameToOrder +2)+" "+orderType +" OFFSET "+pageNumber +" ROWS ";
			queryStr +=" FETCH NEXT "+pageSize+" ROWS ONLY ";
		}


		try {
			Query querySel = em.createNativeQuery(queryStr );
			docMediaList =  querySel.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
			log.error("");
		}
		return docMediaList;
	}

	@Override
	public Integer countListDocMediaFiltro(String search) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String currentDate= dateFormat.format(date);
		String queryStr = "select count(dc.ID) from CRS_DOCUMENTI_MEDIA dc "+
		" where dc.DATA_FINE < '"+currentDate+"'";

		if (!StringUtils.isEmpty(search)) {
			queryStr += " and (" + "upper(dc.descrizione) like UPPER('%"+ search + "%') " +")";
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
	public List<CrsTplDocMedia> getTplDocMedia() {
		List<CrsTplDocMedia> list = null;
		try {
			list = em.createNamedQuery("CrsTplDocMedia.findAll").getResultList();
		} catch (NoResultException e) {
			log.warn("Nessuna tipologica tipo documento trovata");
		}
		return list;
	}


	/*
	 *  CIRCOLARI__________________________________________________________
	 */

	private String getNameFieldByDataTablePosition( String nameTable, String pos){
		
		if (nameTable.equals("CIRCOLARI")){
			if (pos.equals("1"))return "DATA_EMISSIONE";
		}else{
			return "DATA_INIZIO";
		}
		
		return "1";
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getCircolariInpsTable(NormativaTablePaginator filter){
		
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
							"ANNO " +
							"FROM " +
							"CRS_CIRCOLARI_INPS ";
							
		if(filter.getIdProcesso() != 0){
			queryStr += " join CRS_ASS_PROCESSO_CLASSE as pc on pc.ID_NORMATIVA = ID_CIRCOLARI_INPS ";
		}
		if(filter.getIdSottoProcesso() != 0){
			queryStr += " join CRS_ASS_SOTTOPROCESSO_CLASSE as pc on pc.ID_NORMATIVA = ID_CIRCOLARI_INPS ";
		}
		if(filter.getIdAttivitaComponente() != 0){
			queryStr += " join CRS_ASS_AC_CLASSE as pc on pc.ID_NORMATIVA = ID_CIRCOLARI_INPS ";
		}
		if(filter.getIdAttivitaDettaglio() != 0){
			queryStr += " join CRS_ASS_AD_CLASSE as pc on pc.ID_NORMATIVA = ID_CIRCOLARI_INPS ";
		}					
							
		queryStr +=	"WHERE 1 = 1 ";
		
		if(filter.getIdProcesso() != 0){
			queryStr += " and pc.ID_PROCESSO = " + filter.getIdProcesso();
			queryStr += " and pc.ID_TIPO = " + TipoNormativaEnum.CIRCOLARI_INPS.getCodice();
		}else
		if(filter.getIdSottoProcesso() != 0){
			queryStr += " and pc.ID_SOTTOPROCESSO = " + filter.getIdSottoProcesso();
			queryStr += " and pc.ID_TIPO = " + TipoNormativaEnum.CIRCOLARI_INPS.getCodice();
		}else
		if(filter.getIdAttivitaComponente() != 0){
			queryStr += " and pc.ID_ATTIVITA_COMPONENTE = " + filter.getIdAttivitaComponente();
			queryStr += " and pc.ID_TIPO = " + TipoNormativaEnum.CIRCOLARI_INPS.getCodice();
		}else
		if(filter.getIdAttivitaDettaglio() != 0){
			queryStr += " and pc.ID_ATTIVITA_DETTAGLIO = " + filter.getIdAttivitaDettaglio();
			queryStr += " and pc.ID_TIPO = " + TipoNormativaEnum.CIRCOLARI_INPS.getCodice();
		}else{
			if(!filter.isStorico()){
				queryStr += " and (DATA_FINE >= '" + currentDate + "' OR DATA_FINE IS NULL)";
			}else{
				queryStr += " and (DATA_FINE < '"+currentDate+"')";
			}
		}

		if(StringUtils.isNotEmpty(filter.getSSearch())){
			queryStr += " and ("; 
			queryStr += " UPPER(OGGETTO) like UPPER('%" + filter.getSSearch() + "%') ";
			queryStr += " )";

		}

		queryStr += " ORDER BY " + (getNameFieldByDataTablePosition("CIRCOLARI",filter.getISortColArray().get(0))) + " " + filter.getSSortDirArray().get(0);
		queryStr += " OFFSET " + filter.getiDisplayStart() + " ROWS FETCH NEXT " + filter.getiDisplayLength() + " ROWS ONLY";

		Query querySel = em.createNativeQuery(queryStr);

		List<Object[]> listaReturn = querySel.getResultList();

		return listaReturn;

	}

	public Integer countAllCircolariInpsTable(NormativaTablePaginator filter){
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String currentDate= dateFormat.format(date);

		String queryStr = "SELECT count(ID_CIRCOLARI_INPS) " +
							"FROM " +
							"CRS_CIRCOLARI_INPS ";
		
		if(filter.getIdProcesso() != 0){
			queryStr += " join CRS_ASS_PROCESSO_CLASSE as pc on pc.ID_NORMATIVA = ID_CIRCOLARI_INPS ";
		}
		if(filter.getIdSottoProcesso() != 0){
			queryStr += " join CRS_ASS_SOTTOPROCESSO_CLASSE as pc on pc.ID_NORMATIVA = ID_CIRCOLARI_INPS ";
		}
		if(filter.getIdAttivitaComponente() != 0){
			queryStr += " join CRS_ASS_AC_CLASSE as pc on pc.ID_NORMATIVA = ID_CIRCOLARI_INPS ";
		}
		if(filter.getIdAttivitaDettaglio() != 0){
			queryStr += " join CRS_ASS_AD_CLASSE as pc on pc.ID_NORMATIVA = ID_CIRCOLARI_INPS ";
		}
		
		queryStr +=	"WHERE 1 = 1 ";
		
		if(filter.getIdProcesso() != 0){
			queryStr += " and pc.ID_PROCESSO = " + filter.getIdProcesso();
			queryStr += " and pc.ID_TIPO = " + TipoNormativaEnum.CIRCOLARI_INPS.getCodice();
		}else
		if(filter.getIdSottoProcesso() != 0){
			queryStr += " and pc.ID_SOTTOPROCESSO = " + filter.getIdSottoProcesso();
			queryStr += " and pc.ID_TIPO = " + TipoNormativaEnum.CIRCOLARI_INPS.getCodice();
		}else
		if(filter.getIdAttivitaComponente() != 0){
			queryStr += " and pc.ID_ATTIVITA_COMPONENTE = " + filter.getIdAttivitaComponente();
			queryStr += " and pc.ID_TIPO = " + TipoNormativaEnum.CIRCOLARI_INPS.getCodice();
		}else
		if(filter.getIdAttivitaDettaglio() != 0){
			queryStr += " and pc.ID_ATTIVITA_DETTAGLIO = " + filter.getIdAttivitaDettaglio();
			queryStr += " and pc.ID_TIPO = " + TipoNormativaEnum.CIRCOLARI_INPS.getCodice();
		}else{
			if(!filter.isStorico()){
				queryStr += " and (DATA_FINE >= '" + currentDate + "' OR DATA_FINE IS NULL)";
			}else{
				queryStr += " and (DATA_FINE < '"+currentDate+"')";
			}
		}


		if(StringUtils.isNotEmpty(filter.getSSearch())){
			queryStr += " and ("; 
			queryStr += " UPPER(OGGETTO) like UPPER('%" + filter.getSSearch() + "%') ";
			queryStr += " )";

		}

		Query querySel = em.createNativeQuery(queryStr);

		Integer count =  (Integer)querySel.getSingleResult();

		if(count == null){
			count = 0;
		}

		return count;
	}


	@SuppressWarnings("unchecked")
	public List<Object[]> getNoteDecretiTable(NormativaTablePaginator filter){
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String currentDate= dateFormat.format(date);

		String queryStr = "SELECT " +
							"ID_NOTE_DECRETI, " +
							"DESCR_SINTETICA, DESCR_DETTAGLIO, DATA_EMISSIONE, " +
							"CODICE, OGGETTO, RIFERIMENTI " +
							"FROM " +
							"CRS_NOTE_DECRETI ";
		
		if(filter.getIdProcesso() != 0){
			queryStr += " join CRS_ASS_PROCESSO_CLASSE as pc on pc.ID_NORMATIVA = ID_NOTE_DECRETI ";
		}
		if(filter.getIdSottoProcesso() != 0){
			queryStr += " join CRS_ASS_SOTTOPROCESSO_CLASSE as pc on pc.ID_NORMATIVA = ID_NOTE_DECRETI ";
		}
		if(filter.getIdAttivitaComponente() != 0){
			queryStr += " join CRS_ASS_AC_CLASSE as pc on pc.ID_NORMATIVA = ID_NOTE_DECRETI ";
		}
		if(filter.getIdAttivitaDettaglio() != 0){
			queryStr += " join CRS_ASS_AD_CLASSE as pc on pc.ID_NORMATIVA = ID_NOTE_DECRETI ";
		}					
							
		queryStr +=	"WHERE 1 = 1 ";
		
		if(filter.getIdProcesso() != 0){
			queryStr += " and pc.ID_PROCESSO = " + filter.getIdProcesso();
			queryStr += " and pc.ID_TIPO = " + TipoNormativaEnum.NOTE_DESCRETI.getCodice();
		}else
		if(filter.getIdSottoProcesso() != 0){
			queryStr += " and pc.ID_SOTTOPROCESSO = " + filter.getIdSottoProcesso();
			queryStr += " and pc.ID_TIPO = " + TipoNormativaEnum.NOTE_DESCRETI.getCodice();
		}else
		if(filter.getIdAttivitaComponente() != 0){
			queryStr += " and pc.ID_ATTIVITA_COMPONENTE = " + filter.getIdAttivitaComponente();
			queryStr += " and pc.ID_TIPO = " + TipoNormativaEnum.NOTE_DESCRETI.getCodice();
		}else
		if(filter.getIdAttivitaDettaglio() != 0){
			queryStr += " and pc.ID_ATTIVITA_DETTAGLIO = " + filter.getIdAttivitaDettaglio();
			queryStr += " and pc.ID_TIPO = " + TipoNormativaEnum.NOTE_DESCRETI.getCodice();
		}else{
			if(!filter.isStorico()){
				queryStr += " and (DATA_FINE >= '" + currentDate + "' OR DATA_FINE IS NULL)";
			}else{
				queryStr += " and (DATA_FINE < '"+currentDate+"')";
			}
		}


		if(StringUtils.isNotEmpty(filter.getSSearch())){
			queryStr += " and ("; 
			queryStr += " UPPER(OGGETTO) like UPPER('%" + filter.getSSearch() + "%') ";
			queryStr += " )";

		}

		queryStr += " ORDER BY " + (getNameFieldByDataTablePosition("NOTE_E_DECRETI",filter.getISortColArray().get(0))) + " " + filter.getSSortDirArray().get(0);
		queryStr += " OFFSET " + filter.getiDisplayStart() + " ROWS FETCH NEXT " + filter.getiDisplayLength() + " ROWS ONLY";

		Query querySel = em.createNativeQuery(queryStr);

		List<Object[]> listaReturn = querySel.getResultList();

		return listaReturn;

	}

	public Integer countAllNoteDecretiTable(NormativaTablePaginator filter){
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String currentDate= dateFormat.format(date);

		String queryStr = "SELECT count(ID_NOTE_DECRETI) " +
							"FROM " +
							"CRS_NOTE_DECRETI ";
		
		if(filter.getIdProcesso() != 0){
			queryStr += " join CRS_ASS_PROCESSO_CLASSE as pc on pc.ID_NORMATIVA = ID_NOTE_DECRETI ";
		}
		if(filter.getIdSottoProcesso() != 0){
			queryStr += " join CRS_ASS_SOTTOPROCESSO_CLASSE as pc on pc.ID_NORMATIVA = ID_NOTE_DECRETI ";
		}
		if(filter.getIdAttivitaComponente() != 0){
			queryStr += " join CRS_ASS_AC_CLASSE as pc on pc.ID_NORMATIVA = ID_NOTE_DECRETI ";
		}
		if(filter.getIdAttivitaDettaglio() != 0){
			queryStr += " join CRS_ASS_AD_CLASSE as pc on pc.ID_NORMATIVA = ID_NOTE_DECRETI ";
		}					
							
		queryStr +=	"WHERE 1 = 1 ";
		
		if(filter.getIdProcesso() != 0){
			queryStr += " and pc.ID_PROCESSO = " + filter.getIdProcesso();
			queryStr += " and pc.ID_TIPO = " + TipoNormativaEnum.NOTE_DESCRETI.getCodice();
		}else
		if(filter.getIdSottoProcesso() != 0){
			queryStr += " and pc.ID_SOTTOPROCESSO = " + filter.getIdSottoProcesso();
			queryStr += " and pc.ID_TIPO = " + TipoNormativaEnum.NOTE_DESCRETI.getCodice();
		}else
		if(filter.getIdAttivitaComponente() != 0){
			queryStr += " and pc.ID_ATTIVITA_COMPONENTE = " + filter.getIdAttivitaComponente();
			queryStr += " and pc.ID_TIPO = " + TipoNormativaEnum.NOTE_DESCRETI.getCodice();
		}else
		if(filter.getIdAttivitaDettaglio() != 0){
			queryStr += " and pc.ID_ATTIVITA_DETTAGLIO = " + filter.getIdAttivitaDettaglio();
			queryStr += " and pc.ID_TIPO = " + TipoNormativaEnum.NOTE_DESCRETI.getCodice();
		}else{
			if(!filter.isStorico()){
				queryStr += " and (DATA_FINE >= '" + currentDate + "' OR DATA_FINE IS NULL)";
			}else{
				queryStr += " and (DATA_FINE < '"+currentDate+"')";
			}
		}

		if(StringUtils.isNotEmpty(filter.getSSearch())){
			queryStr += " and ("; 
			queryStr += " UPPER(OGGETTO) like UPPER('%" + filter.getSSearch() + "%') ";
			queryStr += " )";

		}

		Query querySel = em.createNativeQuery(queryStr);

		Integer count =  (Integer)querySel.getSingleResult();

		if(count == null){
			count = 0;
		}

		return count;
	}

	/*
	 *  GESTIONE COMPONENTI - DOMINI__________________________________________________________
	 */

	@Override
	public List<Object[]> getListDomini(Integer pageNumber, Integer pageSize,
			int columnNameToOrder, String orderType, String search) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String currentDate= dateFormat.format(date);

		List<Object[]> dominiList = new ArrayList<Object[]>();
		String queryStr = "select d.ID, d.DESCRIZIONE, d.CODICE, d.DATA_INIZIO, d.DATA_FINE from CRS_DOMINI d "+
		" where (d.DATA_FINE > '"+currentDate+"' OR d.DATA_FINE IS NULL)";

		if (!StringUtils.isEmpty(search)) {
			queryStr += " and (" + "upper(d.descrizione) like UPPER('%"+ search + "%') " + ")";
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
	public Integer countDomini(String getsSearch) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String currentDate= dateFormat.format(date);
		String queryStr = "select count(d.ID) from CRS_DOMINI d "+
		" where d.DATA_FINE > '"+currentDate+"' OR d.DATA_FINE IS NULL";

		if (!StringUtils.isEmpty(getsSearch)) {
			queryStr += " and (" + "upper(d.descrizione) like UPPER('%"+ getsSearch + "%') " +")";
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
	public Object[] checkCodiceEsistenteDominio(String codice) {
		List<Object[]> domini =null;
		String queryStr = "select d.ID, d.DESCRIZIONE from CRS_DOMINI d "+
		" where d.codice = '"+codice+"'";
		try {
			Query querySel = em.createNativeQuery(queryStr);

			domini =  querySel.getResultList();
			if(domini != null && domini.size() > 0){
				return domini.get(0);
			}

		} catch (Exception e) {

			log.error("Errore durante la verifica del codice dominio");
		}
		return null;
	}
	
	@Override
	public List<Object[]> getListDominiFiltro(Integer pageNumber,
			Integer pageSize, int columnNameToOrder, String orderType,
			String search) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String currentDate= dateFormat.format(date);

		List<Object[]> dominiList = new ArrayList<Object[]>();
		String queryStr = "select d.ID, d.DESCRIZIONE, d.codice, d.DATA_INIZIO, d.DATA_FINE from CRS_DOMINI d "+
		" where (d.DATA_FINE < '"+currentDate+"')";

		if (!StringUtils.isEmpty(search)) {
			queryStr += " and (" + "upper(d.descrizione) like UPPER('%"+ search + "%') " + ")";
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
			log.error("Errore durante il recupero dello storico domini ");
		}
		return dominiList;
	}
	
	@Override
	public Integer countDominiFiltro(String getsSearch) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String currentDate= dateFormat.format(date);
		String queryStr = "select count(d.ID) from CRS_DOMINI d "+
		" where d.DATA_FINE < '"+currentDate+"'";

		if (!StringUtils.isEmpty(getsSearch)) {
			queryStr += " and (" + "upper(d.descrizione) like UPPER('%"+ getsSearch + "%') " +")";
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
	public List<Object[]> getAllegatiDominio(long idDominio,Integer pageNumber,
			Integer pageSize, int columnNameToOrder, String orderType,
			String search) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String currentDate= dateFormat.format(date);

		List<Object[]> dominiList = new ArrayList<Object[]>();
		String queryStr = "select dc.ID, d.ID, d.NOME_FILE, d.DESCRIZIONE, d.DATA_INIZIO from CRS_DOCUMENTI_MEDIA d ,CRS_ASS_DOMINI_DOCUMENTI dc "+
						  " WHERE dc.ID_DOCUMENTO= d.id AND dc.id_dominio="+idDominio+" AND (d.DATA_FINE > '"+currentDate+"' OR d.DATA_FINE IS NULL)";

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
	public Integer countListAllegatiDominio(long idDominio,String getsSearch) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String currentDate= dateFormat.format(date);
		String queryStr = "select count(dc.ID) from CRS_DOCUMENTI_MEDIA d ,CRS_ASS_DOMINI_DOCUMENTI dc "+
		  " WHERE dc.ID_DOCUMENTO= d.id AND dc.id_dominio="+idDominio+" AND (d.DATA_FINE > '"+currentDate+"' OR d.DATA_FINE IS NULL)";


		if (!StringUtils.isEmpty(getsSearch)) {
			queryStr += " and (" + "upper(d.NOME_FILE) like UPPER('%"+ getsSearch + "%') " +")";
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
	public List<Object[]> getNonAllegatiDominio(long idDominio,
			Integer pageNumber, Integer pageSize, int columnNameToOrder,
			String orderType, String search) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String currentDate= dateFormat.format(date);

		List<Object[]> dominiList = new ArrayList<Object[]>();
		String queryStr = "select d.ID, d.NOME_FILE, d.DESCRIZIONE, d.DATA_INIZIO from CRS_DOCUMENTI_MEDIA d "+
						  " WHERE (d.DATA_FINE > '"+currentDate+"' OR d.DATA_FINE IS NULL) AND d.ID "+
						  " NOT IN(select dc.ID_DOCUMENTO from CRS_ASS_DOMINI_DOCUMENTI dc where dc.ID_DOMINIO="+idDominio+")";


		if (!StringUtils.isEmpty(search)) {
			queryStr += " and (" + "upper(d.NOME_FILE) like UPPER('%"+ search + "%') " + ")";
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
	public Integer countListNonAllegatiDominio(long idDominio, String search) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String currentDate= dateFormat.format(date);
		String queryStr = "select count(d.ID) from CRS_DOCUMENTI_MEDIA d "+
		  " WHERE (d.DATA_FINE > '"+currentDate+"' OR d.DATA_FINE IS NULL) AND d.ID "+
		  " NOT IN(select dc.ID_DOCUMENTO from CRS_ASS_DOMINI_DOCUMENTI dc where dc.ID_DOMINIO="+idDominio+")";

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
	public List<Object[]> getListCompTecnico(Integer pageNumber,
			Integer pageSize, int columnNameToOrder, String orderType,
			String search) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String currentDate= dateFormat.format(date);

		List<Object[]> compTecList = new ArrayList<Object[]>();
		String queryStr = "select ct.ID, ct.DESCRIZIONE,ct.CODICE,ct.AUTORE, ct.DATA_PUBBLICAZIONE,ct.VERSIONE, ct.ID_TIPO_COMP_TEC from CRS_COMPONENTE_TECNICO ct "+
		" where (ct.DATA_FINE > '"+currentDate+"' OR ct.DATA_FINE IS NULL)";

		if (!StringUtils.isEmpty(search)) {
			queryStr += " and (" + "upper(ct.descrizione) like UPPER('%"+ search + "%') " + ")";
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
	public Integer countListCompTecnico(String search) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String currentDate= dateFormat.format(date);
		String queryStr = "select count(ct.ID) from CRS_COMPONENTE_TECNICO ct "+
		" where ct.DATA_FINE > '"+currentDate+"' OR ct.DATA_FINE IS NULL";

		if (!StringUtils.isEmpty(search)) {
			queryStr += " and (" + "upper(ct.descrizione) like UPPER('%"+ search + "%') " +")";
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
	public Object[] checkCodEsistenteCompTecnico(String codice) {
		List<Object[]> ct =null;
		String queryStr = "select ct.ID, ct.descrizione from CRS_COMPONENTE_TECNICO ct "+
		" where ct.codice = '"+codice.trim()+"'";
		try {
			Query querySel = em.createNativeQuery(queryStr);

			ct =  querySel.getResultList();
			if(ct != null && ct.size() > 0){
				return ct.get(0);
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.error("Errore durante la verifica del codice compoenente tecnico");
		}
		return null;
	}


	@Override
	public List<Object[]> getListCompTecnicoStorico(Integer pageNumber,
			Integer pageSize, int columnNameToOrder, String orderType,
			String search) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String currentDate= dateFormat.format(date);

		List<Object[]> compTecList = new ArrayList<Object[]>();
		String queryStr = "select ct.ID, ct.DESCRIZIONE,ct.CODICE,ct.AUTORE, ct.DATA_PUBBLICAZIONE,ct.VERSIONE, ct.ID_TIPO_COMP_TEC from CRS_COMPONENTE_TECNICO ct "+
		" where (ct.DATA_FINE < '"+currentDate+"')";

		if (!StringUtils.isEmpty(search)) {
			queryStr += " and (" + "upper(ct.descrizione) like UPPER('%"+ search + "%') " + ")";
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
	public Integer countListCompTecnicoStorico(String search) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String currentDate= dateFormat.format(date);
		String queryStr = "select count(ct.ID) from CRS_COMPONENTE_TECNICO ct "+
		" where ct.DATA_FINE < '"+currentDate+"'";

		if (!StringUtils.isEmpty(search)) {
			queryStr += " and (" + "upper(ct.descrizione) like UPPER('%"+ search + "%') " +")";
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
	public List<Object[]> getAllegatiCt(long idCt, Integer pageNumber,
			Integer pageSize, int columnNameToOrder, String orderType,
			String search) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String currentDate= dateFormat.format(date);

		List<Object[]> dominiList = new ArrayList<Object[]>();
		String queryStr = "select ctd.ID, d.ID, d.NOME_FILE, d.DESCRIZIONE, d.DATA_INIZIO "+
		 				" from CRS_DOCUMENTI_MEDIA d , CRS_ASS_COMP_TECN_DOCUMENTI ctd "+
		 				" WHERE ctd.ID_DOCUMENTO= d.id AND ctd.ID_COMPONENTE_TECNICO ="+idCt+" AND (d.DATA_FINE > '"+currentDate+"' OR d.DATA_FINE IS NULL)";

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
	public Integer countListAllegatiCt(long idCT, String search) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String currentDate= dateFormat.format(date);
		String queryStr = "select count(ctd.ID) from CRS_DOCUMENTI_MEDIA d , CRS_ASS_COMP_TECN_DOCUMENTI ctd "+
		  " WHERE ctd.ID_DOCUMENTO= d.id AND ctd.ID_COMPONENTE_TECNICO ="+idCT+" AND (d.DATA_FINE > '"+currentDate+"' OR d.DATA_FINE IS NULL)";


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
	public List<Object[]> getNonAllegatiCompTec(long idCt, Integer pageNumber,
			Integer pageSize, int columnNameToOrder, String orderType,
			String search) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String currentDate= dateFormat.format(date);

		List<Object[]> nonAllegCtList = new ArrayList<Object[]>();
		String queryStr = "select d.ID, d.NOME_FILE, d.DESCRIZIONE, d.DATA_INIZIO from CRS_DOCUMENTI_MEDIA d "+
						  " WHERE (d.DATA_FINE > '"+currentDate+"' OR d.DATA_FINE IS NULL) AND d.ID "+
						  " NOT IN(select dc.ID_DOCUMENTO from CRS_ASS_COMP_TECN_DOCUMENTI dc where dc.ID_COMPONENTE_TECNICO="+idCt+")";


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
	public Integer countListNonAllegatiCompTec(long idCt, String search) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String currentDate= dateFormat.format(date);
		String queryStr = "select count(d.ID) from CRS_DOCUMENTI_MEDIA d "+
		  " WHERE (d.DATA_FINE > '"+currentDate+"' OR d.DATA_FINE IS NULL) AND d.ID "+
		  " NOT IN(select dc.ID_DOCUMENTO from CRS_ASS_COMP_TECN_DOCUMENTI dc where dc.ID_COMPONENTE_TECNICO="+idCt+")";

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


	/*
	 * OP COMUNI___________________________________________________________
	 */
	@Transactional
	public <T> T save(T entity){
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


	
	@SuppressWarnings("unchecked")
	public List<Object[]> getMessaggiInpsTable(NormativaTablePaginator filter){
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String currentDate= dateFormat.format(date);

		String queryStr = "SELECT " +
							"ID_MESSAGGI_INPS, " +
							"DESCR_SINTETICA, DESCR_DETTAGLIO, DATA_EMISSIONE, " +
							"CODICE, OGGETTO " +
							"FROM " +
							"CRS_MESSAGGI_INPS ";
		
		if(filter.getIdProcesso() != 0){
			queryStr += " join CRS_ASS_PROCESSO_CLASSE as pc on pc.ID_NORMATIVA = ID_MESSAGGI_INPS ";
		}
		if(filter.getIdSottoProcesso() != 0){
			queryStr += " join CRS_ASS_SOTTOPROCESSO_CLASSE as pc on pc.ID_NORMATIVA = ID_MESSAGGI_INPS ";
		}
		if(filter.getIdAttivitaComponente() != 0){
			queryStr += " join CRS_ASS_AC_CLASSE as pc on pc.ID_NORMATIVA = ID_MESSAGGI_INPS ";
		}
		if(filter.getIdAttivitaDettaglio() != 0){
			queryStr += " join CRS_ASS_AD_CLASSE as pc on pc.ID_NORMATIVA = ID_MESSAGGI_INPS ";
		}					
							
		queryStr +=	"WHERE 1 = 1 ";
		
		if(filter.getIdProcesso() != 0){
			queryStr += " and pc.ID_PROCESSO = " + filter.getIdProcesso();
			queryStr += " and pc.ID_TIPO = " + TipoNormativaEnum.MESSAGGI_INPS.getCodice();
		}else
		if(filter.getIdSottoProcesso() != 0){
			queryStr += " and pc.ID_SOTTOPROCESSO = " + filter.getIdSottoProcesso();
			queryStr += " and pc.ID_TIPO = " + TipoNormativaEnum.MESSAGGI_INPS.getCodice();
		}else
		if(filter.getIdAttivitaComponente() != 0){
			queryStr += " and pc.ID_ATTIVITA_COMPONENTE = " + filter.getIdAttivitaComponente();
			queryStr += " and pc.ID_TIPO = " + TipoNormativaEnum.MESSAGGI_INPS.getCodice();
		}else
		if(filter.getIdAttivitaDettaglio() != 0){
			queryStr += " and pc.ID_ATTIVITA_DETTAGLIO = " + filter.getIdAttivitaDettaglio();
			queryStr += " and pc.ID_TIPO = " + TipoNormativaEnum.MESSAGGI_INPS.getCodice();
		}else{
			if(!filter.isStorico()){
				queryStr += " and (DATA_FINE >= '" + currentDate + "' OR DATA_FINE IS NULL)";
			}else{
				queryStr += " and (DATA_FINE < '"+currentDate+"')";
			}
		}
		
		if(StringUtils.isNotEmpty(filter.getSSearch())){
			queryStr += " and ("; 
			queryStr += " UPPER(OGGETTO) like UPPER('%" + filter.getSSearch() + "%') ";
			queryStr += " )";

		}

		queryStr += " ORDER BY " + (getNameFieldByDataTablePosition("MESSAGGI",filter.getISortColArray().get(0))) + " " + filter.getSSortDirArray().get(0);
		queryStr += " OFFSET " + filter.getiDisplayStart() + " ROWS FETCH NEXT " + filter.getiDisplayLength() + " ROWS ONLY";

		Query querySel = em.createNativeQuery(queryStr);

		List<Object[]> listaReturn = querySel.getResultList();

		return listaReturn;

	}
	
	public Integer countAllMessaggiInpsTable(NormativaTablePaginator filter){
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String currentDate= dateFormat.format(date);

		String queryStr = "SELECT count(ID_MESSAGGI_INPS) " +
							"FROM " +
							"CRS_MESSAGGI_INPS ";
		
		if(filter.getIdProcesso() != 0){
			queryStr += " join CRS_ASS_PROCESSO_CLASSE as pc on pc.ID_NORMATIVA = ID_MESSAGGI_INPS ";
		}
		if(filter.getIdSottoProcesso() != 0){
			queryStr += " join CRS_ASS_SOTTOPROCESSO_CLASSE as pc on pc.ID_NORMATIVA = ID_MESSAGGI_INPS ";
		}
		if(filter.getIdAttivitaComponente() != 0){
			queryStr += " join CRS_ASS_AC_CLASSE as pc on pc.ID_NORMATIVA = ID_MESSAGGI_INPS ";
		}
		if(filter.getIdAttivitaDettaglio() != 0){
			queryStr += " join CRS_ASS_AD_CLASSE as pc on pc.ID_NORMATIVA = ID_MESSAGGI_INPS ";
		}					
							
		queryStr +=	"WHERE 1 = 1 ";
		
		if(filter.getIdProcesso() != 0){
			queryStr += " and pc.ID_PROCESSO = " + filter.getIdProcesso();
			queryStr += " and pc.ID_TIPO = " + TipoNormativaEnum.MESSAGGI_INPS.getCodice();
		}else
		if(filter.getIdSottoProcesso() != 0){
			queryStr += " and pc.ID_SOTTOPROCESSO = " + filter.getIdSottoProcesso();
			queryStr += " and pc.ID_TIPO = " + TipoNormativaEnum.MESSAGGI_INPS.getCodice();
		}else
		if(filter.getIdAttivitaComponente() != 0){
			queryStr += " and pc.ID_ATTIVITA_COMPONENTE = " + filter.getIdAttivitaComponente();
			queryStr += " and pc.ID_TIPO = " + TipoNormativaEnum.MESSAGGI_INPS.getCodice();
		}else
		if(filter.getIdAttivitaDettaglio() != 0){
			queryStr += " and pc.ID_ATTIVITA_DETTAGLIO = " + filter.getIdAttivitaDettaglio();
			queryStr += " and pc.ID_TIPO = " + TipoNormativaEnum.MESSAGGI_INPS.getCodice();
		}else{
			if(!filter.isStorico()){
				queryStr += " and (DATA_FINE >= '" + currentDate + "' OR DATA_FINE IS NULL)";
			}else{
				queryStr += " and (DATA_FINE < '"+currentDate+"')";
			}
		}

		if(StringUtils.isNotEmpty(filter.getSSearch())){
			queryStr += " and ("; 
			queryStr += " UPPER(OGGETTO) like UPPER('%" + filter.getSSearch() + "%') ";
			queryStr += " )";

		}

		Query querySel = em.createNativeQuery(queryStr);

		Integer count =  (Integer)querySel.getSingleResult();
		
		if(count == null){
			count = 0;
		}
		
		return count;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getLeggiDecretiTable(NormativaTablePaginator filter){
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String currentDate= dateFormat.format(date);

		String queryStr = "SELECT " +
							"ID_LEGGI_DECRETI, " +
							"DESCR_SINTETICA, DESCR_DETTAGLIO, DATA_EMISSIONE, " +
							"CODICE, OGGETTO, ARTICOLO, ANNO_GUI, NUMERO_GUI " +
							"FROM " +
							"CRS_LEGGI_DECRETI ";
		
		if(filter.getIdProcesso() != 0){
			queryStr += " join CRS_ASS_PROCESSO_CLASSE as pc on pc.ID_NORMATIVA = ID_LEGGI_DECRETI ";
		}
		if(filter.getIdSottoProcesso() != 0){
			queryStr += " join CRS_ASS_SOTTOPROCESSO_CLASSE as pc on pc.ID_NORMATIVA = ID_LEGGI_DECRETI ";
		}
		if(filter.getIdAttivitaComponente() != 0){
			queryStr += " join CRS_ASS_AC_CLASSE as pc on pc.ID_NORMATIVA = ID_LEGGI_DECRETI ";
		}
		if(filter.getIdAttivitaDettaglio() != 0){
			queryStr += " join CRS_ASS_AD_CLASSE as pc on pc.ID_NORMATIVA = ID_LEGGI_DECRETI ";
		}					
							
		queryStr +=	"WHERE 1 = 1 ";
		
		if(filter.getIdProcesso() != 0){
			queryStr += " and pc.ID_PROCESSO = " + filter.getIdProcesso();
			queryStr += " and pc.ID_TIPO = " + TipoNormativaEnum.LEGGI_DECRETI.getCodice();
		}else
		if(filter.getIdSottoProcesso() != 0){
			queryStr += " and pc.ID_SOTTOPROCESSO = " + filter.getIdSottoProcesso();
			queryStr += " and pc.ID_TIPO = " + TipoNormativaEnum.LEGGI_DECRETI.getCodice();
		}else
		if(filter.getIdAttivitaComponente() != 0){
			queryStr += " and pc.ID_ATTIVITA_COMPONENTE = " + filter.getIdAttivitaComponente();
			queryStr += " and pc.ID_TIPO = " + TipoNormativaEnum.LEGGI_DECRETI.getCodice();
		}else
		if(filter.getIdAttivitaDettaglio() != 0){
			queryStr += " and pc.ID_ATTIVITA_DETTAGLIO = " + filter.getIdAttivitaDettaglio();
			queryStr += " and pc.ID_TIPO = " + TipoNormativaEnum.LEGGI_DECRETI.getCodice();
		}else{
			if(!filter.isStorico()){
				queryStr += " and (DATA_FINE >= '" + currentDate + "' OR DATA_FINE IS NULL)";
			}else{
				queryStr += " and (DATA_FINE < '"+currentDate+"')";
			}
		}
		
		if(StringUtils.isNotEmpty(filter.getSSearch())){
			queryStr += " and ("; 
			queryStr += " UPPER(OGGETTO) like UPPER('%" + filter.getSSearch() + "%') ";
			queryStr += " )";

		}

		queryStr += " ORDER BY " + (getNameFieldByDataTablePosition("LEGGI_E_DECRETI",filter.getISortColArray().get(0))) + " " + filter.getSSortDirArray().get(0);
		queryStr += " OFFSET " + filter.getiDisplayStart() + " ROWS FETCH NEXT " + filter.getiDisplayLength() + " ROWS ONLY";

		Query querySel = em.createNativeQuery(queryStr);

		List<Object[]> listaReturn = querySel.getResultList();

		return listaReturn;

	}
	
	public Integer countAllLeggiDecretiTable(NormativaTablePaginator filter){
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String currentDate= dateFormat.format(date);

		String queryStr = "SELECT count(ID_LEGGI_DECRETI) " +
							"FROM " +
							"CRS_LEGGI_DECRETI ";
		
		if(filter.getIdProcesso() != 0){
			queryStr += " join CRS_ASS_PROCESSO_CLASSE as pc on pc.ID_NORMATIVA = ID_LEGGI_DECRETI ";
		}
		if(filter.getIdSottoProcesso() != 0){
			queryStr += " join CRS_ASS_SOTTOPROCESSO_CLASSE as pc on pc.ID_NORMATIVA = ID_LEGGI_DECRETI ";
		}
		if(filter.getIdAttivitaComponente() != 0){
			queryStr += " join CRS_ASS_AC_CLASSE as pc on pc.ID_NORMATIVA = ID_LEGGI_DECRETI ";
		}
		if(filter.getIdAttivitaDettaglio() != 0){
			queryStr += " join CRS_ASS_AD_CLASSE as pc on pc.ID_NORMATIVA = ID_LEGGI_DECRETI ";
		}					
							
		queryStr +=	"WHERE 1 = 1 ";
		
		if(filter.getIdProcesso() != 0){
			queryStr += " and pc.ID_PROCESSO = " + filter.getIdProcesso();
			queryStr += " and pc.ID_TIPO = " + TipoNormativaEnum.LEGGI_DECRETI.getCodice();
		}else
		if(filter.getIdSottoProcesso() != 0){
			queryStr += " and pc.ID_SOTTOPROCESSO = " + filter.getIdSottoProcesso();
			queryStr += " and pc.ID_TIPO = " + TipoNormativaEnum.LEGGI_DECRETI.getCodice();
		}else
		if(filter.getIdAttivitaComponente() != 0){
			queryStr += " and pc.ID_ATTIVITA_COMPONENTE = " + filter.getIdAttivitaComponente();
			queryStr += " and pc.ID_TIPO = " + TipoNormativaEnum.LEGGI_DECRETI.getCodice();
		}else
		if(filter.getIdAttivitaDettaglio() != 0){
			queryStr += " and pc.ID_ATTIVITA_DETTAGLIO = " + filter.getIdAttivitaDettaglio();
			queryStr += " and pc.ID_TIPO = " + TipoNormativaEnum.LEGGI_DECRETI.getCodice();
		}else{
			if(!filter.isStorico()){
				queryStr += " and (DATA_FINE >= '" + currentDate + "' OR DATA_FINE IS NULL)";
			}else{
				queryStr += " and (DATA_FINE < '"+currentDate+"')";
			}
		}

		if(StringUtils.isNotEmpty(filter.getSSearch())){
			queryStr += " and ("; 
			queryStr += " UPPER(OGGETTO) like UPPER('%" + filter.getSSearch() + "%') ";
			queryStr += " )";

		}

		Query querySel = em.createNativeQuery(queryStr);

		Integer count =  (Integer)querySel.getSingleResult();
		
		if(count == null){
			count = 0;
		}
		
		return count;
	}



	@Transactional
	public void deleteCircolariInps(long idCircolariInps){

		Validate.notNull(idCircolariInps, "Id " + idCircolariInps + " non valorizzato.");

		CrsCircolariInps normativa = em.find(CrsCircolariInps.class, idCircolariInps);

		Validate.notNull(normativa, "La Normativa con id " + idCircolariInps + " non esiste.");

		em.remove(normativa);

	}


	@Transactional
	public void deleteNoteDecreti(long idNoteDecreti){

		Validate.notNull(idNoteDecreti, "Id " + idNoteDecreti + " non valorizzato.");

		CrsNoteDecreti normativa = em.find(CrsNoteDecreti.class, idNoteDecreti);

		Validate.notNull(normativa, "La Normativa con id " + idNoteDecreti + " non esiste.");

		em.remove(normativa);

	}

	@Transactional
	public void deleteMessaggiInps(long idMessaggiInps){

		Validate.notNull(idMessaggiInps, "Id " + idMessaggiInps + " non valorizzato.");

		CrsMessaggiInps normativa = em.find(CrsMessaggiInps.class, idMessaggiInps);

		Validate.notNull(normativa, "La Normativa con id " + idMessaggiInps + " non esiste.");

		em.remove(normativa);

	}

	
	@Transactional
	public void deleteLeggiDecreti(long idLeggiDecreti){

		Validate.notNull(idLeggiDecreti, "Id " + idLeggiDecreti + " non valorizzato.");

		CrsLeggiDecreti normativa = em.find(CrsLeggiDecreti.class, idLeggiDecreti);

		Validate.notNull(normativa, "La Normativa con id " + idLeggiDecreti + " non esiste.");

		em.remove(normativa);

	}

	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getAllegatiCircolariInpsTable(NormativaTablePaginator filter){
		

		String queryStr = "SELECT " + 
							"dc.ID, " + 
							"d.ID, " + 
							"d.NOME_FILE, " +
							"d.DESCRIZIONE, " +
							"d.DATA_INIZIO " +
							" FROM CRS_DOCUMENTI_MEDIA d join CRS_ASS_CIRCOLARI_INPS_DOCUMENTI dc on dc.ID_DOCUMENTO= d.id " +
							" WHERE 1 = 1 " +
							" AND dc.ID_CIRCOLARI_INPS = " + filter.getIdCircolariInps();

		if (StringUtils.isNotEmpty(filter.getSSearch())) {
		queryStr += " and (" + "upper(d.NOME_FILE) like UPPER('%"+ filter.getSSearch() + "%') " + ")";
		}
		
		queryStr += " ORDER BY " + (filter.getISortColArray().get(0)) + " " + filter.getSSortDirArray().get(0);
		queryStr += " OFFSET " + filter.getiDisplayStart() + " ROWS FETCH NEXT " + filter.getiDisplayLength() + " ROWS ONLY";
		

		Query querySel = em.createNativeQuery(queryStr);

		List<Object[]> listaReturn = querySel.getResultList();

		return listaReturn;

	}
	
	public Integer countAllAllegatiCircolariInpsTable(NormativaTablePaginator filter){
		

		String queryStr = "SELECT count(dc.ID) " +
							"FROM " +
							"CRS_DOCUMENTI_MEDIA d join CRS_ASS_CIRCOLARI_INPS_DOCUMENTI dc on dc.ID_DOCUMENTO= d.id " +
							" WHERE 1 = 1 " +
							" AND dc.ID_CIRCOLARI_INPS = " + filter.getIdCircolariInps();


		if(StringUtils.isNotEmpty(filter.getSSearch())){
			queryStr += " and ("; 
			queryStr += " upper(d.NOME_FILE) like UPPER('%" + filter.getSSearch() + "%') ";
			queryStr += " )";

		}

		Query querySel = em.createNativeQuery(queryStr);

		Integer count =  (Integer)querySel.getSingleResult();

		if(count == null){
			count = 0;
		}

		return count;
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getAllegatiNoteDecretiTable(NormativaTablePaginator filter){
		

		String queryStr = "SELECT " + 
							"dc.ID, " + 
							"d.ID, " + 
							"d.NOME_FILE, " +
							"d.DESCRIZIONE, " +
							"d.DATA_INIZIO " +
							" FROM CRS_DOCUMENTI_MEDIA d join CRS_ASS_NOTE_DECRETI_DOCUMENTI dc on dc.ID_DOCUMENTO= d.id " +
							" WHERE 1 = 1 " +
							" AND dc.ID_NOTE_DECRETI = " + filter.getIdNoteDecreti();

		if (StringUtils.isNotEmpty(filter.getSSearch())) {
		queryStr += " and (" + "upper(d.NOME_FILE) like UPPER('%"+ filter.getSSearch() + "%') " + ")";
		}
		
		queryStr += " ORDER BY " + (filter.getISortColArray().get(0)) + " " + filter.getSSortDirArray().get(0);
		queryStr += " OFFSET " + filter.getiDisplayStart() + " ROWS FETCH NEXT " + filter.getiDisplayLength() + " ROWS ONLY";
		

		Query querySel = em.createNativeQuery(queryStr);

		List<Object[]> listaReturn = querySel.getResultList();

		return listaReturn;

	}
	
	public Integer countAllAllegatiNoteDecretiTable(NormativaTablePaginator filter){
		

		String queryStr = "SELECT count(dc.ID) " +
							"FROM " +
							"CRS_DOCUMENTI_MEDIA d join CRS_ASS_NOTE_DECRETI_DOCUMENTI dc on dc.ID_DOCUMENTO= d.id " +
							" WHERE 1 = 1 " +
							" AND dc.ID_NOTE_DECRETI = " + filter.getIdNoteDecreti();


		if(StringUtils.isNotEmpty(filter.getSSearch())){
			queryStr += " and ("; 
			queryStr += " UPPER(d.NOME_FILE) like UPPER('%" + filter.getSSearch() + "%') ";
			queryStr += " )";

		}

		Query querySel = em.createNativeQuery(queryStr);

		Integer count =  (Integer)querySel.getSingleResult();

		if(count == null){
			count = 0;
		}

		return count;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getAllegatiMessaggiInpsTable(NormativaTablePaginator filter){
		

		String queryStr = "SELECT " + 
							"dc.ID, " + 
							"d.ID, " + 
							"d.NOME_FILE, " +
							"d.DESCRIZIONE, " +
							"d.DATA_INIZIO " +
							" FROM CRS_DOCUMENTI_MEDIA d join CRS_ASS_MESSAGGI_INPS_DOCUMENTI dc on dc.ID_DOCUMENTO= d.id " +
							" WHERE 1 = 1 " +
							" AND dc.ID_MESSAGGI_INPS = " + filter.getIdMessaggiInps();

		if (StringUtils.isNotEmpty(filter.getSSearch())) {
		queryStr += " and (" + "upper(d.NOME_FILE) like UPPER('%"+ filter.getSSearch() + "%') " + ")";
		}
		
		queryStr += " ORDER BY " + (filter.getISortColArray().get(0)) + " " + filter.getSSortDirArray().get(0);
		queryStr += " OFFSET " + filter.getiDisplayStart() + " ROWS FETCH NEXT " + filter.getiDisplayLength() + " ROWS ONLY";
		

		Query querySel = em.createNativeQuery(queryStr);

		List<Object[]> listaReturn = querySel.getResultList();

		return listaReturn;

	}
	
	public Integer countAllAllegatiMessaggiInpsTable(NormativaTablePaginator filter){
		

		String queryStr = "SELECT count(dc.ID) " +
							"FROM " +
							"CRS_DOCUMENTI_MEDIA d join CRS_ASS_MESSAGGI_INPS_DOCUMENTI dc on dc.ID_DOCUMENTO= d.id " +
							" WHERE 1 = 1 " +
							" AND dc.ID_MESSAGGI_INPS = " + filter.getIdMessaggiInps();


		if(StringUtils.isNotEmpty(filter.getSSearch())){
			queryStr += " and ("; 
			queryStr += " UPPER(d.NOME_FILE) like UPPER('%" + filter.getSSearch() + "%') ";
			queryStr += " )";

		}

		Query querySel = em.createNativeQuery(queryStr);

		Integer count =  (Integer)querySel.getSingleResult();

		if(count == null){
			count = 0;
		}

		return count;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getAllegatiLeggiDecretiTable(NormativaTablePaginator filter){
		

		String queryStr = "SELECT " + 
							"dc.ID, " + 
							"d.ID, " + 
							"d.NOME_FILE, " +
							"d.DESCRIZIONE, " +
							"d.DATA_INIZIO " +
							" FROM CRS_DOCUMENTI_MEDIA d join CRS_ASS_LEGGI_DECRETI_DOCUMENTI dc on dc.ID_DOCUMENTO= d.id " +
							" WHERE 1 = 1 " +
							" AND dc.ID_LEGGI_DECRETI = " + filter.getIdLeggiDecreti();

		if (StringUtils.isNotEmpty(filter.getSSearch())) {
		queryStr += " and (" + "upper(d.NOME_FILE) like UPPER('%"+ filter.getSSearch() + "%') " + ")";
		}
		
		queryStr += " ORDER BY " + (filter.getISortColArray().get(0)) + " " + filter.getSSortDirArray().get(0);
		queryStr += " OFFSET " + filter.getiDisplayStart() + " ROWS FETCH NEXT " + filter.getiDisplayLength() + " ROWS ONLY";
		

		Query querySel = em.createNativeQuery(queryStr);

		List<Object[]> listaReturn = querySel.getResultList();

		return listaReturn;

	}
	
	public Integer countAllAllegatiLeggiDecretiTable(NormativaTablePaginator filter){
		

		String queryStr = "SELECT count(dc.ID) " +
							"FROM " +
							"CRS_DOCUMENTI_MEDIA d join CRS_ASS_LEGGI_DECRETI_DOCUMENTI dc on dc.ID_DOCUMENTO= d.id " +
							" WHERE 1 = 1 " +
							" AND dc.ID_LEGGI_DECRETI = " + filter.getIdLeggiDecreti();


		if(StringUtils.isNotEmpty(filter.getSSearch())){
			queryStr += " and ("; 
			queryStr += " UPPER(d.NOME_FILE) like UPPER('%" + filter.getSSearch() + "%') ";
			queryStr += " )";

		}

		Query querySel = em.createNativeQuery(queryStr);

		Integer count =  (Integer)querySel.getSingleResult();

		if(count == null){
			count = 0;
		}

		return count;
	}
	
	
	public List<Object[]> getNonAllegatiCircolariInps(NormativaTablePaginator filter) {

		List<Object[]> returnList = new ArrayList<Object[]>();
		String queryStr = "select " +
						  "d.ID, d.NOME_FILE, d.DESCRIZIONE, d.DATA_INIZIO from CRS_DOCUMENTI_MEDIA d " +
						  " WHERE d.ID " +
						  " NOT IN(select dc.ID_DOCUMENTO from CRS_ASS_CIRCOLARI_INPS_DOCUMENTI dc where dc.ID_CIRCOLARI_INPS = " + filter.getIdCircolariInps() + ")";


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
	public Integer countListNonAllegatiCircolariInps(NormativaTablePaginator filter) {
		
		String queryStr = "select count(d.ID) from CRS_DOCUMENTI_MEDIA d " +
		  					" WHERE d.ID "+
		  					" NOT IN(select dc.ID_DOCUMENTO from CRS_ASS_CIRCOLARI_INPS_DOCUMENTI dc where dc.ID_CIRCOLARI_INPS = " + filter.getIdCircolariInps() + ")";

		if (StringUtils.isNotEmpty(filter.getSSearch())) {
			queryStr += " and (" + "upper(d.NOME_FILE) like UPPER('%"+ filter.getSSearch() + "%') " +")";
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
	public List<Object[]> getNonAllegatiNoteDecreti(NormativaTablePaginator filter) {

		List<Object[]> returnList = new ArrayList<Object[]>();
		String queryStr = "select " +
						  "d.ID, d.NOME_FILE, d.DESCRIZIONE, d.DATA_INIZIO from CRS_DOCUMENTI_MEDIA d " +
						  " WHERE d.ID " +
						  " NOT IN(select dc.ID_DOCUMENTO from CRS_ASS_NOTE_DECRETI_DOCUMENTI dc where dc.ID_NOTE_DECRETI = " + filter.getIdNoteDecreti() + ")";


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
	public Integer countListNonAllegatiNoteDecreti(NormativaTablePaginator filter) {
		
		String queryStr = "select count(d.ID) from CRS_DOCUMENTI_MEDIA d " +
		  					" WHERE d.ID "+
		  					" NOT IN(select dc.ID_DOCUMENTO from CRS_ASS_NOTE_DECRETI_DOCUMENTI dc where dc.ID_NOTE_DECRETI = " + filter.getIdNoteDecreti() + ")";

		if (StringUtils.isNotEmpty(filter.getSSearch())) {
			queryStr += " and (" + "upper(d.NOME_FILE) like UPPER('%"+ filter.getSSearch() + "%') " +")";
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
	public List<Object[]> getNonAllegatiMessaggiInps(NormativaTablePaginator filter) {

		List<Object[]> returnList = new ArrayList<Object[]>();
		String queryStr = "select " +
						  "d.ID, d.NOME_FILE, d.DESCRIZIONE, d.DATA_INIZIO from CRS_DOCUMENTI_MEDIA d " +
						  " WHERE d.ID " +
						  " NOT IN(select dc.ID_DOCUMENTO from CRS_ASS_MESSAGGI_INPS_DOCUMENTI dc where dc.ID_MESSAGGI_INPS = " + filter.getIdMessaggiInps() + ")";


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
	public Integer countListNonAllegatiMessaggiInps(NormativaTablePaginator filter) {
		
		String queryStr = "select count(d.ID) from CRS_DOCUMENTI_MEDIA d " +
		  					" WHERE d.ID "+
		  					" NOT IN(select dc.ID_DOCUMENTO from CRS_ASS_MESSAGGI_INPS_DOCUMENTI dc where dc.ID_MESSAGGI_INPS = " + filter.getIdMessaggiInps() + ")";

		if (StringUtils.isNotEmpty(filter.getSSearch())) {
			queryStr += " and (" + "upper(d.NOME_FILE) like UPPER('%"+ filter.getSSearch() + "%') " +")";
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
	public List<Object[]> getNonAllegatiLeggiDecreti(NormativaTablePaginator filter) {

		List<Object[]> returnList = new ArrayList<Object[]>();
		String queryStr = "select " +
						  "d.ID, d.NOME_FILE, d.DESCRIZIONE, d.DATA_INIZIO from CRS_DOCUMENTI_MEDIA d " +
						  " WHERE d.ID " +
						  " NOT IN(select dc.ID_DOCUMENTO from CRS_ASS_LEGGI_DECRETI_DOCUMENTI dc where dc.ID_LEGGI_DECRETI = " + filter.getIdLeggiDecreti() + ")";


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
	public Integer countListNonAllegatiLeggiDecreti(NormativaTablePaginator filter) {
		
		String queryStr = "select count(d.ID) from CRS_DOCUMENTI_MEDIA d " +
		  					" WHERE d.ID "+
		  					" NOT IN(select dc.ID_DOCUMENTO from CRS_ASS_LEGGI_DECRETI_DOCUMENTI dc where dc.ID_LEGGI_DECRETI = " + filter.getIdLeggiDecreti() + ")";

		if (StringUtils.isNotEmpty(filter.getSSearch())) {
			queryStr += " and (" + "upper(d.NOME_FILE) like UPPER('%"+ filter.getSSearch() + "%') " +")";
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
	public List<Object[]> getComboEnteEmittente(){

		String queryStr = "SELECT " + 
		"ID_ENTE_EMITTENTE, " +
		"CODICE, " +
		"DESCRIZIONE " + 
		"FROM CRS_TPL_ENTE_EMITTENTE ";

		Query querySel = em.createNativeQuery(queryStr);

		List<Object[]> lista = querySel.getResultList();

		return lista;	
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getComboTipoLegge(){

		String queryStr = "SELECT " + 
		"ID_TIPO_LEGGE, " +
		"CODICE, " +
		"DESCRIZIONE " + 
		"FROM CRS_TPL_TIPO_LEGGE ";

		Query querySel = em.createNativeQuery(queryStr);

		List<Object[]> lista = querySel.getResultList();

		return lista;	
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getDominiValoriTable(DominiValoriPaginator filter){
		
		String queryStr = "SELECT " +
							"ID_DOMINI_VALORI, " +
							"CODICE_VALORE, " +
							"CODICE_DOMINIO, " + 
							"DESCRIZIONE, " +
							"DATA_INIZIO, " +
							"DATA_FINE " +
							"FROM " +
							"CRS_DOMINI_VALORI ";
							
		queryStr +=	"WHERE 1 = 1 ";
		
		if(filter.getIdDominiValori() != null && filter.getIdDominiValori() != 0){
			queryStr += " and ID_DOMINI_VALORI = " + filter.getIdDominiValori();
		}
		
		if(StringUtils.isNotEmpty(filter.getCodiceDominio())){
			queryStr += " and CODICE_DOMINIO = '" + filter.getCodiceDominio() + "'";
		}

		if(StringUtils.isNotEmpty(filter.getSSearch())){
			queryStr += " and ("; 
			queryStr += " UPPER(DESCRIZIONE) like UPPER('%" + filter.getSSearch() + "%') ";
			queryStr += " )";

		}

		queryStr += " ORDER BY " + (filter.getISortColArray().get(0)) + " " + filter.getSSortDirArray().get(0);
		queryStr += " OFFSET " + filter.getiDisplayStart() + " ROWS FETCH NEXT " + filter.getiDisplayLength() + " ROWS ONLY";

		Query querySel = em.createNativeQuery(queryStr);

		List<Object[]> listaReturn = querySel.getResultList();

		return listaReturn;

	}
	
	
	public Integer countAllDominiValoriTable(DominiValoriPaginator filter){
		
		String queryStr = "SELECT count(ID_DOMINI_VALORI) " +
							"FROM " +
							"CRS_DOMINI_VALORI ";
		
		queryStr +=	"WHERE 1 = 1 ";
		
		if(filter.getIdDominiValori() != null && filter.getIdDominiValori() != 0){
			queryStr += " and ID_DOMINI_VALORI = " + filter.getIdDominiValori();
		}
		
		if(StringUtils.isNotEmpty(filter.getCodiceDominio())){
			queryStr += " and CODICE_DOMINIO = '" + filter.getCodiceDominio() + "'";
		}

		if(StringUtils.isNotEmpty(filter.getSSearch())){
			queryStr += " and ("; 
			queryStr += " UPPER(DESCRIZIONE) like UPPER('%" + filter.getSSearch() + "%') ";
			queryStr += " )";

		}

		Query querySel = em.createNativeQuery(queryStr);

		Integer count =  (Integer)querySel.getSingleResult();

		if(count == null){
			count = 0;
		}

		return count;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Object[] checkCodiceEsistenteDominiValori(String codice) {
		List<Object[]> domini =null;
		String queryStr = "select " + 
							"ID_DOMINI_VALORI, DESCRIZIONE " + 
							"from CRS_DOMINI_VALORI " +
							" where CODICE_VALORE = '"+codice+"'";
		try {
			Query querySel = em.createNativeQuery(queryStr);

			domini =  querySel.getResultList();
			
			if(domini != null && domini.size() > 0){
				return domini.get(0);
			}

		} catch (Exception e) {

			log.error("Errore durante la verifica del codice dominio");
		}
		return null;
	}



	@Override
	public Object[] cercaEmittenteByCodice(String enteEmittente) {
		List<Object[]> enti =null;
		String queryStr = "select e.ID_ENTE_EMITTENTE, e.CODICE, e.DESCRIZIONE from CRS_TPL_ENTE_EMITTENTE e "+
		" where e.codice = '"+enteEmittente+"'";
		try {
			Query querySel = em.createNativeQuery(queryStr);

			enti =  querySel.getResultList();
			if(enti != null && enti.size() > 0){
				return enti.get(0);
			}

		} catch (Exception e) {

			log.error("Errore durante cercaEmittenteByCodice ");
		}
		return null;
	}



	@Override
	public Object[] cercaLeggeByCodice(String tipoLegge) {
		List<Object[]> leggi =null;
		String queryStr = "select l.ID_TIPO_LEGGE, l.CODICE, l.DESCRIZIONE from CRS_TPL_TIPO_LEGGE l "+
		" where l.codice = '"+tipoLegge+"'";
		try {
			Query querySel = em.createNativeQuery(queryStr);

			leggi =  querySel.getResultList();
			if(leggi != null && leggi.size() > 0){
				return leggi.get(0);
			}

		} catch (Exception e) {

			log.error("Errore durante cercaLeggeByCodice ");
		}
		return null;
	}



	@Override
	public List<CrsTplCompTecnico> getTplCompTec() {
		List<CrsTplCompTecnico> list = null;
		try {
			list = em.createNamedQuery("CrsTplCompTecnico.getAll").getResultList();
		} catch (NoResultException e) {
			log.warn("Nessuna tipologica tipo comp tec trovata");
		}
		return list;
	}
	

}






