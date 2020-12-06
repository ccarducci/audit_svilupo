package it.tecnet.crs.ATPO.auditors.jpa.dao;

import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoFaseDati;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

public class AuditorsAtpoUpdPraticheDaoImpl implements
AuditorsAtpoUpdPraticheDao {

	@PersistenceContext()
	private EntityManager em;

	protected static Logger log = Logger
	.getLogger(AuditorsAtpoUpdPraticheDaoImpl.class);

	// GET FASE DATI IN BASE AL FASCIOLO
	public AtpoFaseDati getFaseDati(String fascicolo, String codSede) {

		List<AtpoFaseDati> l= new ArrayList<AtpoFaseDati>();
		String queryStr = "select fd.* from ATPO_FASE_DATI fd  "+
		" where fd.FASCICOLO='"+fascicolo+"' and fd.COD_SEDE='"+codSede+"'";
		try {
			Query querySel = em.createNativeQuery(queryStr, AtpoFaseDati.class);
			l= querySel.getResultList();
			if (l != null && l.size() != 0 && l.get(0) != null) {
				//fd = l.get(0);
				return l.get(0);
			}

		} catch (Throwable e) {
			e.printStackTrace();
			log.error(e);

		}
		return null;
	}

	// POPOLO TAB DATI_GENERALI IN MODIFICA PRATICA
	public Object[] getDatiPratica(long idPratica, String fascicolo) {
		List<Object[]> list = new ArrayList<Object[]>();
		Object[] p = null;
		String queryStr = "select sede.NOME_SEDE, s.FASCICOLO, s.RICHIESTA, s.ESITO, s.AUTOTUTELA, s.PARTE, s.DATA_NOTIFICA, s.DATA_APERTURA, s.DATA_COSTITUZIONE, "
			+ " s.DATA_UDIENZA, s.PARERE_MEDICO, s.T_DISSENSO, s.COM, s.CAR, s.ESEC, s.PR_RIC, s.PR_BZZ, s.PR_DEF, p.RG, p.VISITA_PERITALE, "
			+ " p.OSSERVAZIONI_SANITARIE, s.provv, s.not_provv, s.diss_inps, sp.ESAME_PRATICA "
			+ " from ATPO_PRATICHE_SISCO s, ATPO_PRATICHE_COGISAN p, AU_SEDE sede , AU_S_PRATICA sp "
			+ " where s.FASCICOLO=  p.FASCICOLO and sede.CODICE_SEDE = s.COD_SEDE and s.ID_PRATICHE_SISCO="
			+ idPratica
			+ " and s.fascicolo='"
			+ fascicolo.trim()
			+ "' AND sp.ID_PRATICA= s.ID_PRATICHE_SISCO";

		try {
			Query querySel = em.createNativeQuery(queryStr);
			list = (List<Object[]>) querySel.getResultList();
			if (list != null && list.size() != 0 && list.get(0) != null) {
				p = list.get(0);
				return p;
			}

		} catch (Throwable e) {
			e.printStackTrace();
			log.error(e);
			return null;
		}
		return p;
	}

	// TABELLA RISCHI IN PRATICHE
	@Override
	public List<Object[]> getTabRischiPratAtpo(long idAudit,
			Integer pageNumber, Integer pageSize, int columnNameToOrder,
			String orderType, String search, long idPratica) {
		List<Object[]> list = new ArrayList<Object[]>();

		/*
		 * String queryStr =
		 * "SELECT mr.ID_M_RISCHIO, mr.CODICE_RISCHIO, mr.DESCRIZIONE_RISCHIO,  risespr.CODICE,risespr.DESCRIZIONE, spris.DATA_ATTRIBUZIONE, spris.IMPORTO "
		 * +
		 * "FROM AU_S_PRATICA_RIS spris, AU_M_RISCHIO mr, AU_M_RISESPR risespr "
		 * +
		 * "WHERE spris.ID_M_RISCHIO= mr.ID_M_RISCHIO AND mr.ID_M_RISCHIO=risespr.ID_M_RISCHIO AND mr.ID_AUDIT="
		 * +idAudit;
		 */

		String queryStr = "SELECT " + " 	mr.ID_M_RISCHIO "
		+ " 	, mr.CODICE_RISCHIO " + " 	, mr.DESCRIZIONE_RISCHIO "
		+ " 	, risespr.CODICE " + " 	, risespr.DESCRIZIONE "
		+ " 	, spris.DATA_ATTRIBUZIONE " + " 	, spris.IMPORTO  "
		+ " FROM	AU_S_PRATICA_RIS spris " + " 		, AU_M_RISCHIO mr "
		+ " 		, AU_M_RISESPR risespr  " + " 		, AU_S_PRATICA pra "
		+ " WHERE	spris.ID_M_RISCHIO = MR.ID_M_RISCHIO "
		+ " AND		SPRIS.ID_ESPR_RISCHIO = risespr.ID_M_RISESPR "
		+ " AND		mr.ID_AUDIT = " + idAudit
		+ " AND		spris.ID_S_PRATICA = pra.ID_S_PRATICA "
		+ "	AND		PRA.ID_PRATICA  = " + idPratica;

		if (!StringUtils.isEmpty(search)) {
			queryStr += " and (upper(mr.DESCRIZIONE_RISCHIO) like UPPER('%"
				+ search + "%'))";
		}

		queryStr += " ORDER BY " + (columnNameToOrder + 1) + " " + orderType;
		if (pageSize != -1) {
			queryStr += " OFFSET " + pageNumber + " ROWS ";
			queryStr += " FETCH NEXT " + pageSize + " ROWS ONLY ";
		}

		try {
			Query querySel = em.createNativeQuery(queryStr);
			list = (List<Object[]>) querySel.getResultList();
			if (list != null && list.size() != 0 && list.get(0) != null) {

				return list;
			}

		} catch (Throwable e) {
			e.printStackTrace();
			log.error(e);
			return null;
		}
		return null;
	}

	@Override
	public int countTabRischiPratAtpo(long idAudit, String search,
			long idPratica) {
		Integer count = null;

		/*
		 * String queryStr = "SELECT count(mr.ID_M_RISCHIO) "+
		 * "FROM AU_S_PRATICA_RIS spris, AU_M_RISCHIO mr, AU_M_RISESPR risespr "
		 * +
		 * "WHERE spris.ID_M_RISCHIO= mr.ID_M_RISCHIO AND mr.ID_M_RISCHIO=risespr.ID_M_RISCHIO AND mr.ID_AUDIT="
		 * +idAudit;
		 */

		String queryStr = "SELECT count(mr.ID_M_RISCHIO)  "
			+ " FROM	AU_S_PRATICA_RIS spris " + " 		, AU_M_RISCHIO mr "
			+ " 		, AU_M_RISESPR risespr  " + " 		, AU_S_PRATICA pra "
			+ " WHERE	spris.ID_M_RISCHIO = MR.ID_M_RISCHIO "
			+ " AND		SPRIS.ID_ESPR_RISCHIO = risespr.ID_M_RISESPR "
			+ " AND		mr.ID_AUDIT = " + idAudit
			+ " AND		spris.ID_S_PRATICA = pra.ID_S_PRATICA "
			+ "	AND		PRA.ID_PRATICA  = " + idPratica;

		if (!StringUtils.isEmpty(search)) {
			queryStr += " and (upper(mr.DESCRIZIONE_RISCHIO) like UPPER('%"
				+ search + "%'))";
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

	/*
	 * TABELLA NON CONFORMITA IN PRATICHE
	 */
	@Override
	public List<Object[]> getNonConfPratAtpo(long idAudit, long idPratica, String filtro,
			Integer pageNumber, Integer pageSize, int columnNameToOrder,
			String orderType, String search) {
		List<Object[]> list = new ArrayList<Object[]>();

		String queryStr = "SELECT 	NC.ID_M_NON_CONF "
			+ "		,FASE.ORDINAMENTO, FASE.DESCRIZIONE as descrizioneFase  "
			+ "		, NC.DESCRIZIONE as descrNonConformita  "
			+ "		, PVC.VAR_COMP  "
			+ "		, MVC.DESCRIZIONE as descrizioneVarComp  "
			+ "		, CAST(ISNC.SOGLIA AS DECIMAL(18, 2)) "
			+ "		, ISNC.COLORE  " + "		, PVC.DATA_ATTRIBUZIONE  "
			+ " FROM  " + "		AU_S_PRATICA PRA  "
			+ "		, AU_S_PRATICA_VARCOMP PVC  " + "		, AU_M_VARCOMP MVC  "
			+ "		, AU_M_NONCONF NC  " + "		, CRS_SOTTOPROCESSO FASE  "
			+ "		, AU_TPL_ISNC ISNC  " + "	WHERE   "
			+ "		PRA.ID_PRATICA = " + idPratica + "	AND nc.ID_AUDIT = "
			+ idAudit + "	AND PRA.ESAME_PRATICA = 'C' "
			+ "	AND PRA.ID_S_PRATICA = PVC.ID_S_PRATICA  "
			+ "	AND PVC.VAR_COMP = MVC.CODICE_VC "
			+ "	AND MVC.ID_M_NONCONF = NC.ID_M_NON_CONF "
			+ "	AND NC.ID_FASE = fase.ID_SOTTOPROCESSO   "
			+ "	AND MVC.PESO_VC  = isnc.ID_TPL_ISNC		 ";

		
		if(!filtro.trim().equals("")){
			queryStr +=" AND upper(FASE.DESCRIZIONE) like UPPER('%"+filtro+"%')";	
		}

		if (!StringUtils.isEmpty(search)) {
			queryStr += " and (upper(nc.DESCRIZIONE) like UPPER('%" + search+ "%'))";
		}

		queryStr += " ORDER BY " + (columnNameToOrder + 1) + " " + orderType;
		if (pageSize != -1) {
			queryStr += " OFFSET " + pageNumber + " ROWS ";
			queryStr += " FETCH NEXT " + pageSize + " ROWS ONLY ";
		}

		try {
			Query querySel = em.createNativeQuery(queryStr);
			list = (List<Object[]>) querySel.getResultList();
			if (list != null && list.size() != 0 && list.get(0) != null) {

				return list;
			}

		} catch (Throwable e) {
			e.printStackTrace();
			log.error(e);
			return null;
		}
		return null;
	}

	@Override
	public int countNonConfPratAtpo(long idAudit, long idPratica, String filtro,
			String getsSearch) {
		Integer count = null;

		/*
		 * String queryStr = "SELECT count(nc.ID_M_NON_CONF) "+
		 * "FROM CRS_SOTTOPROCESSO fase, AU_M_NONCONF nc, AU_S_PRATICA_VARCOMP vc, AU_M_VARCOMP mvc, "
		 * + "AU_TPL_ISNC isnc, AU_S_NONCONF snc, AU_S_PRATICA sp "+
		 * "WHERE fase.ID_SOTTOPROCESSO=nc.ID_FASE AND nc.ID_AUDIT="+idAudit +
		 * "AND nc.ID_M_NON_CONF= mvc.ID_M_NONCONF AND isnc.ID_AUDIT=nc.ID_AUDIT "
		 * +
		 * "AND snc.ID_M_NONCONF= nc.ID_M_NON_CONF AND snc.ID_M_NONCONF=vc.ID_NONCONF AND "
		 * + "vc.ID_S_PRATICA= sp.ID_S_PRATICA AND sp.ID_PRATICA="+idPratica;
		 */

		String queryStr = "SELECT count(nc.ID_M_NON_CONF) " + " FROM  "
		+ "		AU_S_PRATICA PRA  " + "		, AU_S_PRATICA_VARCOMP PVC  "
		+ "		, AU_M_VARCOMP MVC  " + "		, AU_M_NONCONF NC  "
		+ "		, CRS_SOTTOPROCESSO FASE  " + "		, AU_TPL_ISNC ISNC  "
		+ "	WHERE   " + "		PRA.ID_PRATICA = " + idPratica
		+ "	AND nc.ID_AUDIT = " + idAudit
		+ "	AND PRA.ESAME_PRATICA = 'C' "
		+ "	AND PRA.ID_S_PRATICA = PVC.ID_S_PRATICA  "
		+ "	AND PVC.VAR_COMP = MVC.CODICE_VC "
		+ "	AND MVC.ID_M_NONCONF = NC.ID_M_NON_CONF "
		+ "	AND NC.ID_FASE = fase.ID_SOTTOPROCESSO   "
		+ "	AND MVC.PESO_VC  = isnc.ID_TPL_ISNC		 ";

		if(!filtro.trim().equals("")){
			queryStr +=" AND upper(FASE.DESCRIZIONE) like UPPER('%"+filtro+"%')";	
		}
		
		
		if (!StringUtils.isEmpty(getsSearch)) {
			queryStr += " and (upper(nc.DESCRIZIONE) like UPPER('%"
				+ getsSearch + "%'))";
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

	// OP COMUNI

	@Transactional
	public <T> T salva(T obj) {

		return em.merge(obj);

	}

	@Transactional
	public <T> T cerca(Class<T> obj, Object pk) {
		// a.setIdAudit(null);
		try {
			return (T) em.find(obj, pk);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Object[]> getTipologicaAtpo(String tipo) {
		List<Object[]> list = new ArrayList<Object[]>();

		String queryStr = "select t.ID_TPL_TIPOLOGICHE, t.CODIFICA, t.TIPO, t.DESCRIZIONE  from AU_TPL_TIPOLOGICHE t WHERE TIPO='"
			+ tipo + "'";

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
	public Object[] getDescrTipologicaByCodifica(String codifica) {
		List<Object[]> list = new ArrayList<Object[]>();

		String queryStr = "select t.ID_TPL_TIPOLOGICHE, t.CODIFICA, t.TIPO, t.DESCRIZIONE  from AU_TPL_TIPOLOGICHE t WHERE t.CODIFICA='"
			+ codifica + "'";

		try {
			Query querySel = em.createNativeQuery(queryStr);
			list = querySel.getResultList();
			if (list != null) {
				return list.get(0);
			}

		} catch (Throwable e) {
			e.printStackTrace();
			log.error(e);
		}
		return null;
	}

	@Override
	public Long getAuSPraticaByIdPratica(Long idPratica) {
		Long idSPratica = null;

		String queryStr = "SELECT ID_S_PRATICA  "
			+ " FROM AU_S_PRATICA WHERE ID_PRATICA = " + idPratica;

		try {
			Query querySel = em.createNativeQuery(queryStr);
			idSPratica = (Long) querySel.getSingleResult();
		} catch (Throwable e) {
			e.printStackTrace();
			log.error(e);
		}
		return idSPratica;
	}

	@Override
	public List<Object[]> getDescrizioneFaseAssociate(long idAudit) {
		
		List<Object[]> list = new ArrayList<Object[]>();

		String queryStr = "SELECT s.DESCRIZIONE FROM CRS_SOTTOPROCESSO s, AU_ASS_AUDIT_FASE af " +
						  " WHERE s.ID_SOTTOPROCESSO = af.ID_SOTTOPROCESSO AND af.ID_AUDIT="+idAudit;

		try {
			Query querySel = em.createNativeQuery(queryStr);
			list = querySel.getResultList();
			if (list != null) {
				return list;
			}

		} catch (Throwable e) {
			e.printStackTrace();
			log.error(e);
		}
		return null;
	}

}
