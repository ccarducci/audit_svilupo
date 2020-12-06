package it.tecnet.crs.ATPO.auditors.jpa.dao;

import it.tecnet.crs.jpa.model.AuAudit;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

public class AuditorsAtpoAccessiDaoImpl implements AuditorsAtpoAccessiDao{


	@PersistenceContext()
	private EntityManager em;

	protected static Logger log = Logger.getLogger(AuditorsAtpoAccessiDaoImpl.class);

	//TABELLA PRATICHE ATPO IN ACCESSI AUDITORS ATPO

	public List<Object[]> getPraticheATPO(int idSessione, int filtroEsito, int filtroStato,
			Integer pageNumber, Integer pageSize, Integer columnNameToOrder,
			String orderType, String textSearch) {
		List<Object[]> list = new ArrayList<Object[]>();

		String queryStr = "select s.ID_PRATICHE_SISCO, s.COD_SEDE, sede.NOME_SEDE, s.fascicolo,  s.parte, sp.ESAME_PRATICA,  s.ESITO "+
		" from AU_S_PRATICA sp, ATPO_PRATICHE_SISCO s  "+
		" inner join AU_SEDE sede on s.COD_SEDE = sede.CODICE_SEDE "+
		" inner join AU_ASS_CAMP_PRT_ATPO prt on s.ID_PRATICHE_SISCO = prt.ID_PRATICHE_SISCO "+
		" inner join AU_CAMPIONE c on prt.ID_CAMPIONE = c.ID_CAMPIONE "+
		" inner join AU_SESSIONI sess on c.ID_SESSIONE = sess.ID_SESSIONE "+
		" where sess.id_sessione="+idSessione +" AND s.ID_PRATICHE_SISCO= sp.ID_PRATICA";	

		switch(filtroEsito){

		case 0://ALL

			break;
		case 1: //DISSENSO
			queryStr +=" AND upper(s.ESITO) like UPPER('d%')";	
			break;
		case 2: //FAVOREVOLE ALL'ISTITUTO
			queryStr +=" AND upper(s.ESITO) like UPPER('f%')";	
			break;
		case 3: //SFAVOREVOLE ALL'ISTITUTO
			queryStr +=" AND upper(s.ESITO) like UPPER('s%')";	
			break;
		case 4: //PARZIALMENTE FAVOREVOLE 
			queryStr +=" AND upper(s.ESITO) like UPPER('p%')";	
			break;
		}
		switch(filtroStato){

		case 0://ALL

			break;
		case 1: //CHIUSO
			queryStr +=" AND upper(sp.ESAME_PRATICA) like UPPER('c%')";	
			break;
		case 2: //APERTO
			queryStr +=" AND upper(sp.ESAME_PRATICA) like UPPER('a%')";	
			break;
		case 3: //LAVORAZIONE
			queryStr +=" AND upper(sp.ESAME_PRATICA) like UPPER('l%')";	
			break;
		
		}


		if (!StringUtils.isEmpty(textSearch)) {
			queryStr += " and (" + "upper(s.fascicolo) like UPPER('%"+ textSearch + "%')"+ " OR upper(s.parte) like UPPER('%"+ textSearch + "%'))";
		}
		queryStr +=" ORDER BY "+(columnNameToOrder+1)+" "+orderType +" OFFSET "+pageNumber+" ROWS ";
		queryStr +=" FETCH NEXT "+pageSize+" ROWS ONLY ";

		try {
			Query querySel = em.createNativeQuery(queryStr);
			list = querySel.getResultList();

		} catch (Throwable e) {
			e.printStackTrace();
			log.error(e);
		}
		return list;
	}

	@Override
	public Integer countListaPraticheATPO(int idSessione,int filtroEsito, int filtroStato, int getiDisplayStart,
			int getiDisplayLength, int getiSortCol_0, String getsSortDir_0,
			String getsSearch) {
		Integer count=null;
		String queryStr = "select count(s.ID_PRATICHE_SISCO)  from AU_S_PRATICA sp, ATPO_PRATICHE_SISCO s "+
		" inner join AU_SEDE sede on s.COD_SEDE = sede.CODICE_SEDE "+
		" inner join AU_ASS_CAMP_PRT_ATPO prt on s.ID_PRATICHE_SISCO = prt.ID_PRATICHE_SISCO "+
		" inner join AU_CAMPIONE c on prt.ID_CAMPIONE = c.ID_CAMPIONE "+
		" inner join AU_SESSIONI sess on c.ID_SESSIONE = sess.ID_SESSIONE "+
		" where sess.id_sessione="+idSessione +" AND s.ID_PRATICHE_SISCO= sp.ID_PRATICA";	
		switch(filtroEsito){

		case 0://ALL

			break;
		case 1: //DISSENSO
			queryStr +=" AND upper(s.ESITO) like UPPER('d%')";	
			break;
		case 2: //FAVOREVOLE ALL'ISTITUTO
			queryStr +=" AND upper(s.ESITO) like UPPER('f%')";	
			break;
		case 3: //SFAVOREVOLE ALL'ISTITUTO
			queryStr +=" AND upper(s.ESITO) like UPPER('s%')";	
			break;
		case 4: //PARZIALMENTE FAVOREVOLE 
			queryStr +=" AND upper(s.ESITO) like UPPER('p%')";	
			break;
		}
		switch(filtroStato){

		case 0://ALL

			break;
		case 1: //CHIUSO
			queryStr +=" AND upper(sp.ESAME_PRATICA) like UPPER('c%')";	
			break;
		case 2: //APERTO
			queryStr +=" AND upper(sp.ESAME_PRATICA) like UPPER('a%')";	
			break;
		case 3: //LAVORAZIONE
			queryStr +=" AND upper(sp.ESAME_PRATICA) like UPPER('l%')";	
			
			break;
		
		}


		if (!StringUtils.isEmpty(getsSearch)) {
			queryStr += " and (" + "upper(s.fascicolo) like UPPER('%"+ getsSearch + "%')"+ " OR upper(s.parte) like UPPER('%"+ getsSearch + "%'))";
		}

		try {
			Query querySel = em.createNativeQuery(queryStr);
			count = (Integer) querySel.getSingleResult();

		} catch (Throwable e) {
			e.printStackTrace();
			log.error(e);
		}
		return count;
	}



	@Transactional
	public <T> T cerca(Class<T> obj, Object pk) {
		//a.setIdAudit(null);
		try {
			return (T) em.find(obj,pk);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public AuAudit getAuditByIdCampagna(long idCampagna) {

		AuAudit a= new AuAudit();
		String queryStr = "SELECT a.* FROM AU_AUDIT a, AU_CAMPAGNA c WHERE c.ID_CAMPAGNA="+idCampagna+" AND c.ID_AUDIT= a.ID_AUDIT";



		try {
			Query querySel = em.createNativeQuery(queryStr, AuAudit.class);
			a = (AuAudit) querySel.getSingleResult();

		} catch (Throwable e) {
			e.printStackTrace();
			log.error(e);
		}
		return a;
	}


	@SuppressWarnings("unchecked")
	@Override
	public Object[] getSSessioneDatiGenerali(long idSessione) {

		List<Object[]> list = new ArrayList<Object[]>();

		String query = "SELECT ss.ID_S_SESSIONE, SE.DATA_INIZIO, SE.DATA_FINE, ss.NR_PRATICHE, ss.NR_PRATICHE_ESAMINATE, " +
		"ss.MINIMO, ss.MASSIMO, ss.MEDIA, ss.DEV_STANDARD, ss.INCC, ss.INCC_DESCRIZIONE, " +
		"ss.STATO_PRATICHE, ss.STATO_ESAME_SESSIONE, ss.DATA_AGG_DATI_SESS, NUM_DISSENSI_AMM , NUM_DISSENSI_SAN, PERC_NUM_DISSENSI_AMM, PERC_NUM_DISSENSI_SAN " +
		"FROM AU_S_SESSIONE ss , AU_SESSIONI se " +
		"WHERE ss.ID_SESSIONE = " + idSessione + 
		" AND ss.ID_SESSIONE = SE.ID_SESSIONE";

		try {

			Query querySel = em.createNativeQuery(query);
			list =querySel.getResultList();

			if(list != null && list.size() != 0){
				return list.get(0);
			}
			return null;


		} catch (Throwable e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Object[] getDatiContesto(long idSessione, long idCampagna) {
		List<Object[]> list = new ArrayList<Object[]>();

		String query = "SELECT mc.ID_M_CONTESTO, mc.FUNZIONARI, mc.MEDICI_INPS, mc.APERTE, mc.GG_MEDI_APERTURA, "+
		"mc.GG_MEDI_CHIUSURA, mc.ABBANDONO_DOM, mc.ABBANDONO_REV, "+
		"mc.ACQ_ERR_DOM, mc.ACQ_ERR_REV,mc.CESS_MAT_CONTENDERE_DOM, mc.CESS_MAT_CONTENDERE_REV, "+
		"mc.DEF_AUT_ATP_DOM, mc.DEF_AUT_ATP_REV,  mc.DISSENSO_DOM, mc.DISSENSO_REV, "+
		"mc.ESTINTA_DOM ,mc.ESTINTA_REV,mc.FAV_DOM, mc.FAV_REV, mc.INAMMISS_DOM, mc.INAMMISS_REV, "+
		"mc.INCOMP_DOM, mc.INCOMP_REV,mc.PARZ_FAV_DOM,mc.PARZ_FAV_REV,mc.SFAV_DOM,mc.SFAV_REV, "+
		"mc.TRASF_ALTRO_SETT_DOM,mc.TRASF_ALTRO_SETT_REV, "+
		"mc.DISSENSO_INPS as SENTENZEPRIMOG, mc.CTU, mc.VISITE_PERITALI, mc.PERC_CVP_SU_NR_CTU, "+
		"mc.PERC_VP_SU_NR_MEDICI, mc.PERC_PARERE_CONCORDE, "+
		"c.DATA_INIZIO_OSSERVAZIONE, c.DATA_FINE_OSSERVAZIONE "+
		"FROM AU_M_CONTESTO mc, AU_CAMPAGNA c "+
		"WHERE mc.ID_SESSIONE="+idSessione +" and c.ID_CAMPAGNA="+idCampagna;

		try {

			Query querySel = em.createNativeQuery(query);
			list =querySel.getResultList();

			if(list != null && list.size() != 0){
				return list.get(0);
			}

		} catch (Throwable e) {
			e.printStackTrace();

		}
		return null;
	}

	/*
	 * restituisce il numero di pratiche esaminate
	 */
	public Integer getNumeroPraticheEsaminate(long idSessione) {

		int count=0;
		String query = "SELECT ss.NR_PRATICHE_ESAMINATE FROM AU_S_SESSIONE ss WHERE ss.ID_SESSIONE="+idSessione;

		try {

			Query querySel = em.createNativeQuery(query);
			count = (Integer) querySel.getSingleResult();
			return count;

		} catch (Throwable e) {
			e.printStackTrace();

		}
		return null;
	}

	@Override
	public List<Object[]> getTipologiaPraticheCampione(long idSessione) {
		List<Object[]> list = new ArrayList<Object[]>();
		//		String query = "SELECT count(ps.ID_PRATICHE_SISCO), ps.ESITO FROM AU_S_SESSIONE ss , "+
		//		"AU_SESSIONI s, AU_CAMPIONE c, AU_ASS_CAMP_PRT_ATPO cpa, ATPO_PRATICHE_SISCO ps "+
		//		"WHERE ss.ID_SESSIONE = s.ID_SESSIONE AND s.ID_SESSIONE="+ idSessione +" AND "+
		//		"s.ID_SESSIONE= c.ID_SESSIONE AND c.ID_CAMPIONE = cpa.ID_CAMPIONE "+
		//		"AND cpa.ID_PRATICHE_SISCO = ps.ID_PRATICHE_SISCO "+
		//		"GROUP BY ps.ESITO";

		String query = " select count(pr.ID_S_PRATICA), tpl.DESCRIZIONE from AU_S_SESSIONE ss , AU_SESSIONI s, AU_S_PRATICA pr, ATPO_FASE_DATI fd, "+
		" ATPO_FASE_POST_PERITALE fpp, ATPO_PRATICHE_SISCO sisco, AU_TPL_TIPOLOGICHE tpl "+
		" where s.ID_SESSIONE= "+ idSessione+
		" and s.ID_SESSIONE= ss.ID_SESSIONE and pr.ID_S_SESSIONE= ss.ID_S_SESSIONE "+
		" and pr.ESAME_PRATICA ='C' and pr.ID_PRATICA = sisco.ID_PRATICHE_SISCO "+
		" and fd.FASCICOLO = sisco.FASCICOLO and fd.COD_SEDE = sisco.COD_SEDE "+
		" and fpp.ID_FASE_DATI= fd.ID_FASE_DATI and tpl.CODIFICA = fpp.COD_CHIUSURA_CORRETTO "+
		" and tpl.TIPO='V019' GROUP BY tpl.DESCRIZIONE ";

		try {

			Query querySel = em.createNativeQuery(query);
			list =querySel.getResultList();
			return list;


		} catch (Throwable e) {
			e.printStackTrace();

		}
		return null;
	}

	@Override
	public List<Object[]> getNonConformitaAccessiAtpo(Long idAudit) {
		List<Object[]> list = new ArrayList<Object[]>();
		String query = "SELECT nc.ID_M_NON_CONF, nc.ID_AUDIT, nc.ID_FASE, nc.DATA_INIZIO, "+
		"nc.DATA_FINE, nc.DESCRIZIONE, nc.PESO_NON_CONF, nc.CODICE_NC FROM AU_M_NONCONF nc "+
		"WHERE nc.ID_AUDIT="+idAudit;

		try {

			Query querySel = em.createNativeQuery(query);
			list =querySel.getResultList();
			return list;


		} catch (Throwable e) {
			e.printStackTrace();

		}
		return null;
	}

	@Override
	public List<Object[]> getSNonConfByIdMNonConf(long idMNonConf,
			long idSSessione) {
		List<Object[]> o=null;
		String queryStr = "SELECT snc.VALORE_INCC, snc.VALORE_PRATICA_NONSOGGETTA "+
		"FROM AU_S_NONCONF snc,  AU_M_NONCONF nc "+
		"WHERE nc.ID_M_NON_CONF=snc.ID_M_NONCONF AND nc.ID_M_NON_CONF="+idMNonConf +" AND snc.ID_S_SESSIONE ="+idSSessione;


		try {
			Query querySel = em.createNativeQuery(queryStr);
			o =querySel.getResultList();
			if(o != null && o.size() >0){
				return o;
			}

		} catch (Exception e) {
			log.error("Non è stata trovata la non conformita con id: " + idMNonConf);

		}
		return null;
	}

	@Override
	public List<Object[]> getVarComp(long idMNonConf, long idSSessione,
			int pageNumber, int pageSize, int columnNameToOrder,
			String orderType, String search) {
		List<Object[]>l= new ArrayList<Object[]>();
		String query = "select svc.ID_S_VARCOMP, mvc.DESCRIZIONE, svc.PERC_SU_PS, svc.CRITICITA, "+
		"svc.AZIONI_CORRETTIVE from  AU_S_VARCOMP svc , "+
		"AU_S_SESSIONE sess, AU_S_NONCONF snc, AU_M_VARCOMP mvc, AU_M_NONCONF mnc "+
		"where sess.ID_S_SESSIONE="+idSSessione +" and snc.ID_S_SESSIONE=sess.ID_S_SESSIONE "+
		"and svc.ID_S_NONCONF= snc.ID_S_NONCONF and mvc.ID_M_COMP= svc.ID_M_VARCOMP "+
		"and mnc.ID_M_NON_CONF="+idMNonConf+" and mvc.ID_M_NONCONF=mnc.ID_M_NON_CONF";

		if (!StringUtils.isEmpty(search)) {
			query += " and (upper(mvc.DESCRIZIONE) like UPPER('%"+ search + "%'))";
		}

		query +=" ORDER BY "+(columnNameToOrder+1)+ " "+orderType ;
		if (pageSize != -1 ){ 
			query += " OFFSET "+pageNumber+" ROWS ";
			query +=" FETCH NEXT "+pageSize+" ROWS ONLY ";
		}

		try {
			Query querySel = em.createNativeQuery(query);
			l =querySel.getResultList();

		} catch (Throwable e) {
			e.printStackTrace();
		}
		return l;
	}

	@Override
	public int countVarComp(long idMNonConf, long idSSessione, String search) {


		Integer count=null;
		String query = "select count(svc.ID_S_VARCOMP) "+
		" from  AU_S_VARCOMP svc , "+
		"AU_S_SESSIONE sess, AU_S_NONCONF snc, AU_M_VARCOMP mvc, AU_M_NONCONF mnc "+
		"where sess.ID_S_SESSIONE="+idSSessione +" and snc.ID_S_SESSIONE=sess.ID_S_SESSIONE "+
		"and svc.ID_S_NONCONF= snc.ID_S_NONCONF and mvc.ID_M_COMP= svc.ID_M_VARCOMP "+
		"and mnc.ID_M_NON_CONF="+idMNonConf+" and mvc.ID_M_NONCONF=mnc.ID_M_NON_CONF";


		if (!StringUtils.isEmpty(search)) {
			query += " and (upper(mvc.descrizione) like UPPER('%"+ search + "%') or UPPER(nc.descrizione) like UPPER('%"+search +"%'))";
		}		 

		try {
			Query querySel = em.createNativeQuery(query);
			count = (Integer) querySel.getSingleResult();

		} catch (Throwable e) {
			e.printStackTrace();

		}
		return count;


	}

	@Override
	public List<Object[]> getTabRischiPM(Long idAudit) {
		List<Object[]> o=null;
		String queryStr = "SELECT r.ID_M_RISCHIO, r.DESCRIZIONE_RISCHIO FROM AU_M_RISCHIO r "+
		"WHERE ID_AUDIT="+idAudit;


		try {
			Query querySel = em.createNativeQuery(queryStr);
			o =querySel.getResultList();
			if(o != null && o.size() >0){
				return o;
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;
	}

	@Override
	public List<Object[]> searchEsprRischiTablePM(long idRischio,
			long idSSessione, int pageNumber, int pageSize,
			int columnNameToOrder, String orderType, String search) {
		List<Object[]>l= new ArrayList<Object[]>();
		String query = "SELECT esprs.ID_S_ESPRESSIONE_RISCHIO, esprR.descrizione, esprS.num,  "+
		"esprS.su_ps, esprS.POSSIBILI_MOTIVAZIONI_RISCHIO, esprS.AZIONI_CORRETTIVE "+
		"FROM AU_M_RISESPR esprR, AU_S_RISESPR esprS,  AU_S_RISCHIO rs "+
		"where rs.ID_S_RISCHIO = esprS.ID_S_RISCHIO "+
		"and esprS.ID_M_RISESPR = esprR.ID_M_RISESPR "+
		"AND esprR.ID_M_RISCHIO="+ idRischio +
		"AND rs.ID_S_SESSIONE= " + idSSessione;

		if (!StringUtils.isEmpty(search)) {
			query += " and (upper(esprR.descrizione) like UPPER('%"+ search + "%'))";
		}

		query +=" ORDER BY "+(columnNameToOrder+1)+ " "+orderType ;
		if (pageSize != -1 ){ 
			query += " OFFSET "+pageNumber+" ROWS ";
			query +=" FETCH NEXT "+pageSize+" ROWS ONLY ";
		}

		try {
			Query querySel = em.createNativeQuery(query);
			l =querySel.getResultList();

		} catch (Throwable e) {
			e.printStackTrace();
		}
		return l;
	}

	@Override
	public int countEsprRischiTablePM(long idRischio, long idSSessione,
			String search) {
		Integer count=null;
		String query = "SELECT count(esprs.ID_S_ESPRESSIONE_RISCHIO)  "+
		"FROM AU_M_RISESPR esprR, AU_S_RISESPR esprS,  AU_S_RISCHIO rs "+
		"where rs.ID_S_RISCHIO = esprS.ID_S_RISCHIO "+
		"and esprS.ID_M_RISESPR = esprR.ID_M_RISESPR "+
		"AND esprR.ID_M_RISCHIO="+ idRischio +
		"AND rs.ID_S_SESSIONE= " + idSSessione;

		if (!StringUtils.isEmpty(search)) {
			query += " and (upper(esprR.descrizione) like UPPER('%"+ search + "%'))";
		}



		try {
			Query querySel = em.createNativeQuery(query);
			count = (Integer) querySel.getSingleResult();

		} catch (Throwable e) {
			e.printStackTrace();

		}
		return count;
	}

	@Transactional
	public <T> T salva(T entity){
		return em.merge(entity);
	}

	@Override
	public List<Object[]> getRisultatiByTempo(long idSSessione, int pageNumber,
			int pageSize, int columnNameToOrder, String orderType, String search) {
		List<Object[]>l= new ArrayList<Object[]>();
		String query = "select  B.CODIFICA, " + 
		"B.descrizione, " + 
	  	"isnull(A.media_gg, 0) as giorni, " +
	  	"isnull(A.NC, 0) as nc " +
	  	"  , ORDINAMENTO " +
	  	"from( " +
		"(select " +
		"TIPO, " +
		"CODIFICA, " +
		"MEDIA_GG, " +
		"NC, " +
		"ORDINAMENTO " +
		"from " +
		"au_s_tempi " +
		"where ID_S_SESSIONE = " + idSSessione + ") as A right join " +
		"(select TIPO, CODIFICA, DESCRIZIONE " +
		"from AU_TPL_TIPOLOGICHE where tipo = 'V008' and LTRIM(RTRIM(CODIFICA)) in ( 'E06','E07','E08','E09','E10','E01','E04','E02' )) as B on A.tipo = B.tipo and A.codifica = B.codifica) ";

		if (!StringUtils.isEmpty(search)) {
			query += " and (upper(B.descrizione) like UPPER('%"+ search + "%'))";
		}

		if(columnNameToOrder ==0){
			
			query +=" ORDER BY ORDINAMENTO ";
		}else{
			query +=" ORDER BY "+(columnNameToOrder+1)+ " "+orderType ;
		}
		
		if (pageSize != -1 ){ 
			query += " OFFSET "+pageNumber+" ROWS ";
			query +=" FETCH NEXT "+pageSize+" ROWS ONLY ";
		}

		try {
			Query querySel = em.createNativeQuery(query);
			l =querySel.getResultList();

		} catch (Throwable e) {
			e.printStackTrace();
		}
		return l;
	}

	@Override
	public int countListTempo(long idSSessione, String search) {
		Integer count = null;
		String query = "select count( B.CODIFICA) " + 
	  	"from( " +
		"(select " +
		"TIPO, " +
		"CODIFICA, " +
		"MEDIA_GG, " +
		"NC, " +
		"ORDINAMENTO " +
		"from " +
		"au_s_tempi " +
		"where ID_S_SESSIONE = " + idSSessione + ") as A right join " +
		"(select TIPO, CODIFICA, DESCRIZIONE " +
		"from AU_TPL_TIPOLOGICHE where tipo = 'V008' and LTRIM(RTRIM(CODIFICA)) in ( 'E06','E07','E08','E09','E10','E01','E04','E02' )) as B on A.tipo = B.tipo and A.codifica = B.codifica) ";
		
		if (!StringUtils.isEmpty(search)) {
			query += " and (upper(B.descrizione) like UPPER('%"+ search + "%'))";
		}
		try {
			Query querySel = em.createNativeQuery(query);
			count = (Integer) querySel.getSingleResult();

		} catch (Throwable e) {
			e.printStackTrace();

		}
		return count;
	}





}
