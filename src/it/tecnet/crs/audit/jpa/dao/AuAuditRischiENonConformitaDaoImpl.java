package it.tecnet.crs.audit.jpa.dao;

import it.tecnet.crs.jpa.model.AuMNonConf;
import it.tecnet.crs.jpa.model.AuMRischio;
import it.tecnet.crs.jpa.model.AuMRisepr;
import it.tecnet.crs.jpa.model.AuMVarcomp;
import it.tecnet.crs.mod.jpa.model.CrsSottoprocesso;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Transactional;

public class AuAuditRischiENonConformitaDaoImpl implements AuAuditRischiENonConformitaDao{
	@PersistenceContext()
	private EntityManager em;


	/*
	 * TABELLA RISCHI____________________________________________________________________________
	 */


	@Override
	public List<AuMRischio> getRischi(long idAudit, Integer pageNumber,
			Integer pageSize, int columnNameToOrder, String orderType,
			String search) {
		List<AuMRischio> list = null;
		String queryStr = "select id_m_rischio, descrizione_rischio, codice_rischio, peso_rischio, valore_decr_rischio, data_inizio, data_fine "+
		" from AU_M_RISCHIO "+
		" where id_audit="+idAudit;	

		if (!StringUtils.isEmpty(search)) {
			queryStr += " and (upper(descrizione_rischio) like UPPER('%"+ search + "%') or UPPER(codice_rischio) like UPPER('%"+search +"%'))";
		}

		queryStr +=" ORDER BY "+(columnNameToOrder + 1)+" "+orderType +" OFFSET "+pageNumber+" ROWS ";
		queryStr +=" FETCH NEXT "+pageSize+" ROWS ONLY ";


		try {
			//Query querySel = em.createNativeQuery(queryStr,AuMRischio.class);
			List<AuMRischio> resultList = em.createNativeQuery(queryStr, AuMRischio.class).getResultList();

			//solution 
			list = new ArrayList<AuMRischio>(resultList); 


		} catch (Throwable e) {
			e.printStackTrace();

		}
		return list;
	}

	@Override
	public int countListaRischi(long idAudit, int pageNumber, int pageSize,
			int columnNameToOrder, String orderType, String search) {
		Integer count=null;
		String queryStr = "select count(*) from AU_M_RISCHIO  "+
		" where id_audit="+idAudit;			

		if (!StringUtils.isEmpty(search)) {
			queryStr += " and (upper(descrizione_rischio) like UPPER('%"+ search + "%') or UPPER(codice_rischio) like UPPER('%"+search +"%'))";
		} 				 

		try {
			Query querySel = em.createNativeQuery(queryStr);
			count = (Integer) querySel.getSingleResult();

		} catch (Throwable e) {
			e.printStackTrace();

		}
		return count;
	}



	@Override
	public List<AuMRischio> checkCodiceRischio() {
		List<AuMRischio> list = null;
		String queryStr = "select * from au_m_rischio ";

		try {
			//Query querySel = em.createNativeQuery(queryStr,AuMRischio.class);
			List<AuMRischio> resultList = em.createNativeQuery(queryStr, AuMRischio.class).getResultList();

			//solution 
			list = new ArrayList<AuMRischio>(resultList); 


		} catch (Throwable e) {
			e.printStackTrace();

		}
		return list;
	}


	@Override
	public AuMRischio checkRischioInAuDomandaAuRisepr(AuMRischio i) {

		List<AuMRischio> l = null;
		AuMRischio r=null;
		String queryStr = "select r.id_m_rischio, r.descrizione_rischio, r.codice_rischio, r.peso_rischio, "+
		"r.valore_decr_rischio, r.data_inizio, r.data_fine "+
		"from AU_M_RISCHIO r, AU_M_RISESPR ard , AU_M_DOMANDA d "+
		"where (ard.ID_M_RISCHIO= r.ID_M_RISCHIO or d.ID_M_RISCHIO = r.ID_M_RISCHIO) "+
		"and r.ID_M_RISCHIO="+i.getIdMRischio();	

		try {
			//Query querySel = em.createNativeQuery(queryStr,AuMRischio.class);
			List<AuMRischio> resultList = em.createNativeQuery(queryStr, AuMRischio.class).getResultList();

			//solution 
			l = new ArrayList<AuMRischio>(resultList); 
			if(!l.isEmpty()){
				r= l.get(0);
			}
		} catch (Throwable e) {
			e.printStackTrace();

		}
		return r;
	}


	public AuMNonConf checkNcInVarComportamentale(AuMNonConf i) {
		List<AuMNonConf> l = null;
		AuMNonConf r=null;
		String queryStr = "select nc.* from AU_M_NONCONF nc, au_m_varcomp vc "+
		"where nc.ID_M_NON_CONF = vc.id_m_nonconf "+
		"and nc.id_m_non_conf="+i.getIdMNonConf();	

		try {
			//Query querySel = em.createNativeQuery(queryStr,AuMRischio.class);
			List<AuMNonConf> resultList = em.createNativeQuery(queryStr, AuMNonConf.class).getResultList();

			//solution 
			l = new ArrayList<AuMNonConf>(resultList); 
			if(!l.isEmpty()){
				r= l.get(0);
			}
		} catch (Throwable e) {
			e.printStackTrace();

		}
		return r;
	}




	/*
	 * TABELLA ESPRESSIONE RISCHIO______________________________________________________________________________________
	 */
	public List<Object[]> getEsprRischi(long idAudit, Integer pageNumber,
			Integer pageSize, int columnNameToOrder, String orderType,
			String search) {
		List<Object[]> esprRischi = new ArrayList<Object[]>();
		String queryStr = "select er.ID_M_RISESPR, r.codice_rischio,r.id_m_rischio, er.DESCRIZIONE, r.DESCRIZIONE_RISCHIO,  er.codice, er.PERC_TOTALE_RISCHIO, er.RAGGRUPPAMENTO_RISCHIO, er.DATA_INIZIO, er.DATA_FINE "+
		"from AU_M_RISESPR er, AU_M_RISCHIO r "+
		"where(er.ID_M_RISCHIO = r.ID_M_RISCHIO and r.id_audit="+idAudit +")";

		if (!StringUtils.isEmpty(search)) {
			queryStr += " and ((" + "upper(er.descrizione) like UPPER('%"+ search + "%')"+ ")";
			queryStr += " or (" + "upper(er.codice) like UPPER('%"+ search + "%')"+ "))"; 
		}

		//dico +3 perche il checkbox contiene sia idEspr che codice_rischio che idRischio
		queryStr +=" ORDER BY "+(columnNameToOrder + 3)+" "+orderType +" OFFSET "+pageNumber+" ROWS ";
		queryStr +=" FETCH NEXT "+pageSize+" ROWS ONLY ";


		try {
			Query querySel = em.createNativeQuery(queryStr);
			esprRischi = querySel.getResultList();

		} catch (Throwable e) {
			e.printStackTrace();

		}
		return esprRischi;
	}

	//conta numero espr rischio
	public int countListaEsprRischi(long idAudit, int pageNumber, int pageSize,
			int columnNameToOrder, String orderType, String search) {
		Integer count=null;
		String queryStr = "select count(er.ID_M_RISESPR) "+
		"from AU_M_RISESPR er, AU_M_RISCHIO r "+
		"where(er.ID_M_RISCHIO = r.ID_M_RISCHIO and r.id_audit="+idAudit +")";	

		if (!StringUtils.isEmpty(search)) {
			queryStr += " and ((" + "upper(er.descrizione) like UPPER('%"+ search + "%')"+ ")";
			queryStr += " or (" + "upper(er.codice) like UPPER('%"+ search + "%')"+ "))"; 
		} 				 

		try {
			Query querySel = em.createNativeQuery(queryStr);
			count = (Integer) querySel.getSingleResult();

		} catch (Throwable e) {
			e.printStackTrace();

		}
		return count;
	}

	//	rischi da mostrare in aside aggiungi/modifica espr rischio
	public List<AuMRischio> getRischiPerDropDown(long idAudit) {
		List<AuMRischio> list = null;
		String queryStr = "select r.ID_M_RISCHIO, r.CODICE_RISCHIO "+
		"from AU_M_RISCHIO r "+
		"where r.id_audit="+idAudit;	

		try {
			//Query querySel = em.createNativeQuery(queryStr,AuMRischio.class);
			List<AuMRischio> resultList = em.createNativeQuery(queryStr, AuMRischio.class).getResultList();

			//solution 
			list = new ArrayList<AuMRischio>(resultList); 


		} catch (Throwable e) {
			e.printStackTrace();

		}
		return list;
	}

	/*
	 * TABELLA NON CONFORMITA__________________________________________________________________________________________________________
	 */
	public List<Object[]> getNonConf(long idAudit, Integer pageNumber,
			Integer pageSize, int columnNameToOrder, String orderType,
			String search) {
		List<Object[]> nonConf = new ArrayList<Object[]>();
		String queryStr = "select nnc.ID_M_NON_CONF, s.ID_SOTTOPROCESSO, nnc.CODICE_NC, nnc.DESCRIZIONE, a.DESCRIZIONE, s.DESCRIZIONE, "+
		"nnc.PESO_NON_CONF, nnc.DATA_INIZIO, nnc.DATA_FINE "+
		"from AU_M_NONCONF nnc, AU_AUDIT a, CRS_SOTTOPROCESSO s, AU_ASS_AUDIT_FASE ass "+
		"where a.ID_AUDIT= ass.ID_AUDIT and s.ID_SOTTOPROCESSO= ass.ID_SOTTOPROCESSO and nnc.id_audit= a.ID_AUDIT and nnc.ID_FASE = ass.ID_SOTTOPROCESSO "+
		"and a.ID_AUDIT="+idAudit;

		if (!StringUtils.isEmpty(search)) {
			queryStr += " and ((" + "upper( nnc.DESCRIZIONE) like UPPER('%"+ search + "%')"+ ")";
			queryStr += " or (" + "upper(s.DESCRIZIONE) like UPPER('%"+ search + "%')"+ "))"; 
		}

		//dico +3 perche il checkbox contiene sia idEspr che codice_rischio che idRischio
		queryStr +=" ORDER BY "+(columnNameToOrder + 2)+" "+orderType +" OFFSET "+pageNumber+" ROWS ";
		queryStr +=" FETCH NEXT "+pageSize+" ROWS ONLY ";


		try {
			Query querySel = em.createNativeQuery(queryStr);
			nonConf = querySel.getResultList();

		} catch (Throwable e) {
			e.printStackTrace();

		}
		return nonConf;
	}

	@Override
	public int countListaNonConf(long idAudit, int pageNumber, int pageSize,
			int columnNameToOrder, String orderType, String search) {
		Integer count=null;
		String queryStr = "select count(nnc.ID_M_NON_CONF) "+
		"from AU_M_NONCONF nnc, AU_AUDIT a, CRS_SOTTOPROCESSO s, AU_ASS_AUDIT_FASE ass "+
		"where a.ID_AUDIT= ass.ID_AUDIT and s.ID_SOTTOPROCESSO= ass.ID_SOTTOPROCESSO and nnc.id_audit= a.ID_AUDIT and nnc.ID_FASE = ass.ID_SOTTOPROCESSO "+
		"and a.ID_AUDIT="+idAudit;	

		if (!StringUtils.isEmpty(search)) {
			queryStr += " and ((" + "upper( nnc.DESCRIZIONE) like UPPER('%"+ search + "%')"+ ")";
			queryStr += " or (" + "upper(s.DESCRIZIONE) like UPPER('%"+ search + "%')"+ "))"; 
		}			 

		try {
			Query querySel = em.createNativeQuery(queryStr);
			count = (Integer) querySel.getSingleResult();

		} catch (Throwable e) {
			e.printStackTrace();

		}
		return count;
	}
	
	@Override
	public List<AuMNonConf> checkCodiceNC() {
		List<AuMNonConf> list = null;
		String queryStr = "select * from au_m_nonconf ";

		try {
			//Query querySel = em.createNativeQuery(queryStr,AuMRischio.class);
			List<AuMNonConf> resultList = em.createNativeQuery(queryStr, AuMNonConf.class).getResultList();

			//solution 
			list = new ArrayList<AuMNonConf>(resultList); 


		} catch (Throwable e) {
			e.printStackTrace();

		}
		return list;
		
	
	}


	/*
	 * TABELLA VARIANTE COMPORTAMENTALE______________________________________________________________________
	 * 
	 */
	@Override
	public List<Object[]> getVarComp(long idAudit, Integer pageNumber,
			Integer pageSize, int columnNameToOrder, String orderType,
			String search) {
		List<Object[]> list = null;
		String queryStr = "SELECT vc.id_m_comp, nc.id_m_non_conf, vc.descrizione, nc.descrizione, vc.codice_vc, vc.peso_vc, "+
		"vc.data_inizio, vc.data_fine "+
		"FROM AU_M_VARCOMP vc, AU_M_NONCONF nc "+
		"where(vc.ID_M_NONCONF = nc.ID_M_NON_CONF) and nc.ID_AUDIT="+idAudit;

		if (!StringUtils.isEmpty(search)) {
			queryStr += " and (upper(vc.descrizione) like UPPER('%"+ search + "%') or UPPER(nc.descrizione) like UPPER('%"+search +"%'))";
		}

		queryStr +=" ORDER BY "+(columnNameToOrder + 2)+" "+orderType +" OFFSET "+pageNumber+" ROWS ";
		queryStr +=" FETCH NEXT "+pageSize+" ROWS ONLY ";


		try {
			Query querySel = em.createNativeQuery(queryStr);
			list = querySel.getResultList();

		} catch (Throwable e) {
			e.printStackTrace();

		}
		return list;

	}

	@Override
	public int countListaVarComp(long idAudit, Integer pageNumber,
			Integer pageSize, int columnNameToOrder, String orderType,
			String search) {
		Integer count=null;
		String queryStr = "SELECT count(vc.id_m_comp) "+
		"FROM AU_M_VARCOMP vc, AU_M_NONCONF nc "+
		"where(vc.ID_M_NONCONF = nc.ID_M_NON_CONF) and nc.ID_AUDIT="+idAudit;

		if (!StringUtils.isEmpty(search)) {
			queryStr += " and (upper(vc.descrizione) like UPPER('%"+ search + "%') or UPPER(nc.descrizione) like UPPER('%"+search +"%'))";
		}		 

		try {
			Query querySel = em.createNativeQuery(queryStr);
			count = (Integer) querySel.getSingleResult();

		} catch (Throwable e) {
			e.printStackTrace();

		}
		return count;
	}

	@Override
	public List<AuMNonConf> getNC(long idAudit) {
		List<AuMNonConf> list = null;
		String queryStr = "select nc.* from AU_M_NONCONF nc where nc.id_audit="+ idAudit;	

		try {
			
			List<AuMNonConf> resultList = em.createNativeQuery(queryStr, AuMNonConf.class).getResultList();

			//solution 
			list = new ArrayList<AuMNonConf>(resultList); 


		} catch (Throwable e) {
			e.printStackTrace();

		}
		return list;
	}

	@Override
	public List<CrsSottoprocesso> getFasi(long idAudit) {
		List<CrsSottoprocesso> list = null;
		String queryStr = "select s.* "+
		"from AU_AUDIT a, CRS_SOTTOPROCESSO s, AU_ASS_AUDIT_FASE ass "+
		"where a.ID_AUDIT= ass.ID_AUDIT and s.ID_SOTTOPROCESSO= ass.ID_SOTTOPROCESSO "+
		"and a.ID_AUDIT="+ idAudit;	

		try {
			//Query querySel = em.createNativeQuery(queryStr,AuMRischio.class);
			List<CrsSottoprocesso> resultList = em.createNativeQuery(queryStr, CrsSottoprocesso.class).getResultList();

			//solution 
			list = new ArrayList<CrsSottoprocesso>(resultList); 


		} catch (Throwable e) {
			e.printStackTrace();

		}
		return list;
	}

	
	//variante comportamentale
	public List<AuMVarcomp> checkCodice() {
		List<AuMVarcomp> list = null;
		String queryStr = "select * from  AU_M_VARCOMP ";

		try {
			//Query querySel = em.createNativeQuery(queryStr,AuMRischio.class);
			List<AuMVarcomp> resultList = em.createNativeQuery(queryStr, AuMVarcomp.class).getResultList();

			//solution 
			list = new ArrayList<AuMVarcomp>(resultList); 


		} catch (Throwable e) {
			e.printStackTrace();

		}
		return list;
	}



	@Override
	public List<AuMRisepr> checkCodiceEsprRischio() {
		List<AuMRisepr> list = null;
		String queryStr = "select * from  AU_M_RISESPR ";

		try {
			//Query querySel = em.createNativeQuery(queryStr,AuMRischio.class);
			List<AuMRisepr> resultList = em.createNativeQuery(queryStr, AuMRisepr.class).getResultList();

			//solution 
			list = new ArrayList<AuMRisepr>(resultList); 


		} catch (Throwable e) {
			e.printStackTrace();

		}
		return list;
	}


	@Override
	public AuMRischio getRischioOptionDropModifica(long idEsprRischio) {
		List<AuMRischio> list=null;
		AuMRischio m=null;
		String queryStr = "select *	"+				
		"from AU_M_RISCHIO "+
		"where ID_M_RISCHIO in "+
		"(select ID_M_RISCHIO from au_m_risespr where id_m_risespr ="+idEsprRischio+")";	

		try {

			List<AuMRischio> resultList = em.createNativeQuery(queryStr, AuMRischio.class).getResultList();

			//solution 
			list = new ArrayList<AuMRischio>(resultList); 
			m= list.get(0);

		} catch (Throwable e) {
			e.printStackTrace();

		}
		return m;
	}





	/*
	 * FUNZIONI COMUNI
	 */


	@Transactional
	public void delete(AuMRischio i) {
		try{
			em.remove(i);
		}catch(Exception e){
			e.printStackTrace();
		}
	}


	@Transactional
	public <T> T cerca(Class<T> obj, Object pk) {
		try {
			return (T) em.find(obj,pk);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}



	@Transactional
	public <T> T save(T entity) {
		try{
			return em.merge(entity);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}


	@Transactional
	public void remove(Object entity) {
		try{
			em.remove(entity);
			return;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return;
	}




	
















}
