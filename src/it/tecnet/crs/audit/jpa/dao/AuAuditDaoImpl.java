package it.tecnet.crs.audit.jpa.dao;

import it.tecnet.crs.jpa.model.AuAssAuditFase;
import it.tecnet.crs.jpa.model.AuAssUtenteAudit;
import it.tecnet.crs.jpa.model.AuAssUtenteSessione;
import it.tecnet.crs.jpa.model.AuAudit;
import it.tecnet.crs.jpa.model.AuMDomanda;
import it.tecnet.crs.jpa.model.AuMRisposta;
import it.tecnet.crs.jpa.model.AuQuestionario;
import it.tecnet.crs.mod.web.dto.ProcessoDto;
import it.tecnet.crs.web.dto.NonConformitaDto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

public class AuAuditDaoImpl implements AuAuditDao {

	@PersistenceContext()
	private EntityManager em;

	protected static Logger log = Logger.getLogger(AuAuditDaoImpl.class);

	/*
	 * carica gli audit in audit/generale->table(non-Javadoc)
	 * 
	 * @see
	 * it.tecnet.crs.audit.jpa.dao.AuAuditDao#getListAuditGenerale(java.lang
	 * .Integer, java.lang.Integer, java.lang.String, java.lang.String,
	 * java.lang.String)
	 */
	public List<Object[]> getListAuditGenerale(long idUtente,Integer pageNumber,Integer pageSize, int columnNameToOrder, String orderType,String search) {
		List<Object[]> auditList = new ArrayList<Object[]>();
		String queryStr = "select a.ID_AUDIT, a.NOME, a.DESCRIZIONE, a.DATA_INIZIO, a.DATA_FINE , a.INPUT, a.OUTPUT, a.OBIETTIVO "
				+ "from au_audit a , AU_ASS_UTENTE_AUDIT ada, CRS_UTENTE_ADV utente"
				+ " where utente.ID_UTENTE="+idUtente
				+ " and utente.ID_UTENTE=ada.ID_UTENTE"
				+ " and a.ID_AUDIT=ada.ID_AUDIT";

		if (!StringUtils.isEmpty(search)) {
			queryStr += " and (" + "upper(a.descrizione) like UPPER('%"+ search + "%') " + ")";
		}
		if(pageNumber != -1){
			queryStr +=" ORDER BY "+(columnNameToOrder +1)+" "+orderType +" OFFSET "+pageNumber +" ROWS ";
			queryStr +=" FETCH NEXT "+pageSize+" ROWS ONLY ";
		}
	

		try {
			Query querySel = em.createNativeQuery(queryStr );
			auditList =  querySel.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
			log.error("");
		}
		return auditList;
	}

	/*
	 * conta il numero di audit per mostrare in tabella le varie info
	 * audit/generale->table(non-Javadoc)
	 * 
	 * @see it.tecnet.crs.audit.jpa.dao.AuAuditDao#countAudit(java.lang.String)
	 */
	public Integer countAudit(long idUtente, String textSearch) {
		Integer count = null;
		String query = "select count(a.ID_AUDIT) from au_audit a, AU_ASS_UTENTE_AUDIT ada, CRS_UTENTE_ADV utente"
		+ " where utente.ID_UTENTE="+idUtente
		+ " and utente.ID_UTENTE=ada.ID_UTENTE"
		+ " and a.ID_AUDIT=ada.ID_AUDIT";
		if (!StringUtils.isEmpty(textSearch)) {
		   query+= " and (" + "upper(a.descrizione) like UPPER('%"+ textSearch + "%') " + ")";
		}

		try {
			Query querySel = em.createNativeQuery(query);
			count = (Integer) querySel.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Errore durante l'esecuzione del meotodo  countAudit() della classe AuAuditDaoImpl.java ");

		}
		log.info("Metodo countAudit() della classe AuAuditDaoImpl.java eseguito");
		return count == null ? 0 : count;
	}

	/*
	 * SALVA AUDIT
	 */

	@Transactional
	public void salvaAudit(AuAudit a) {
		a.setIdAudit(null);
		try {
			em.merge(a);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	/*
	 * SALVA MODIFIHCE AUDIT
	 */

	@Transactional
	public void salvaModifiche(AuAudit a) {
		try {
			em.merge(a);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/*
	 * ELIMINA AUDIT
	 */
	@Transactional
	public void eliminaAudit(long idAudit) {
		Validate.notNull(idAudit, "Id " + idAudit + " non valorizzato.");

		AuAudit audit = em.find(AuAudit.class, idAudit);

		Validate.notNull(audit, "L\'area con id " + idAudit + " non esiste.");

		em.remove(audit);
	}

	/*
	 * mostra i dati nella tab AUDIT->GENRALE->MODIFICA->DATI(non-Javadoc)
	 * 
	 * @see it.tecnet.crs.audit.jpa.dao.AuAuditDao#getAuditDaModificare(long)
	 */
	public AuAudit getAuditDaModificare(long idAudit) {
		
		
		return em.find(AuAudit.class, idAudit);
	}

	
	/*
	 *  F A S I ___________________________________________________________________________________________________
	 */
	
	
	
	/*
	 * mostra i dati nella tabella AUDIT->GENRALE->MODIFICA->FASI(non-Javadoc)
	 * 
	 * @see it.tecnet.crs.audit.jpa.dao.AuAuditDao#getFasiModificaGenerale(long,
	 * java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String,
	 * java.lang.String)
	 */
	public List<Object[]> getFasiModificaGenerale(long idAudit,Integer pageNumber,Integer pageSize, int columnNameToOrder, String orderType,String search) {
		List<Object[]> list = new ArrayList<Object[]>();
		String queryStr = "SELECT sp.id_sottoprocesso,p.descrizione, sp.descrizione, sp.input ,sp.output , sp.uo_coinvolte,  a.stato "
				+ "from CRS_SOTTOPROCESSO sp "
				+ "inner join crs_processo p on sp.ID_PROCESSO= p.ID_PROCESSO "
				+ "inner join CRS_AREA a on p.ID_AREA = a.ID_AREA "
				+ "inner join AU_ASS_AUDIT_FASE aaf on sp.ID_SOTTOPROCESSO = aaf.ID_SOTTOPROCESSO "
				+ "inner join AU_AUDIT ad on aaf.ID_AUDIT = ad.ID_AUDIT "
				+ "where ad.ID_AUDIT=" + idAudit;

		if (!StringUtils.isEmpty(search)) {
			queryStr += " and (" + "upper(sp.descrizione) like UPPER('%" + search + "%') " + ")";
			queryStr += " or (" + "upper(p.descrizione) like UPPER('%" + search + "%') " + ")";

		}
		
		queryStr +=" ORDER BY "+(columnNameToOrder + 1)+" "+orderType +" OFFSET "+pageNumber+" ROWS ";
		queryStr +=" FETCH NEXT "+pageSize+" ROWS ONLY ";

		try {
			Query querySel = em.createNativeQuery(queryStr);
			list =  querySel.getResultList();

		} catch (Throwable e) {
			log.error("");
		}
		return list;
	}

	/*
	 * conta le righe nella tabella AUDIT->GENRALE->MODIFICA->FASI(non-Javadoc)
	 * 
	 * @see it.tecnet.crs.audit.jpa.dao.AuAuditDao#countFasiAudit(long,
	 * java.lang.String)
	 */
	public Integer countFasiAudit(long idAudit, String textSearch) {

		Integer count = null;
		String queryStr = "SELECT COUNT (sp.id_sottoprocesso)"+
				" from CRS_SOTTOPROCESSO sp"+
				" inner join crs_processo p on sp.ID_PROCESSO= p.ID_PROCESSO"+
				" inner join CRS_AREA a on p.ID_AREA = a.ID_AREA"+
				" inner join AU_ASS_AUDIT_FASE aaf on sp.ID_SOTTOPROCESSO = aaf.ID_SOTTOPROCESSO"+
				" inner join AU_AUDIT ad on aaf.ID_AUDIT = ad.ID_AUDIT"+
				" where ad.ID_AUDIT=" + idAudit;

		if (!StringUtils.isEmpty(textSearch)) {

			queryStr += " and (" + "upper(sp.descrizione) like UPPER('%" + textSearch + "%') "+ ")";
			queryStr += " or (" + "upper(p.descrizione) like UPPER('%" + textSearch + "%') "+ ")";

		}
		try {
			Query querySel = em.createNativeQuery(queryStr);

			count = (Integer) querySel.getSingleResult();

		} catch (Exception e) {
			log
					.error("Errore durante l'esecuzione del meotodo countFasiAudit() della classe AuAuditDaoImpl.java ");

		}
		log
				.info("Metodo countFasiAudit() della classe AuAuditDaoImpl.java eseguito");

		return count == null ? 0 : count;
	}

	/*
	 * QUERY CHE POPOLA IL MENU A TENDINA DEI PROCESSI (IN ASIDE)
	 */
	public List<Object[]> getProcessi() {
		List<Object[]> list = new ArrayList<Object[]>();
		String query = "SELECT p.id_processo, p.descrizione FROM crs_processo p";
		Query querySel = em.createNativeQuery(query);
		list = querySel.getResultList();
		return list;
	}

	// popola la tabella dell'aside in aggiungi fase(in base al processo che
	// selezionni dal menu a tendina)
	public List<Object[]> getFasiInAsideTable(long idProcesso,
			long idAudit, Integer pageNumber,Integer pageSize, int columnNameToOrder, String orderType,String search) {

		List<Object[]> list = new ArrayList<Object[]>();

		String queryStr = "SELECT sp.ID_SOTTOPROCESSO,p.descrizione, sp.descrizione,sp.input,sp.output, sp.uo_coinvolte,  a.stato "
				+ "from CRS_SOTTOPROCESSO sp "
				+ "inner join crs_processo p on sp.ID_PROCESSO= p.ID_PROCESSO "
				+ "inner join CRS_AREA a on p.ID_AREA = a.ID_AREA "
				+ "where p.ID_PROCESSO="
				+ idProcesso
				+ " AND sp.ID_SOTTOPROCESSO NOT IN(select au.ID_SOTTOPROCESSO "
				+ "from AU_ASS_AUDIT_FASE au "
				+ "where au.ID_AUDIT="
				+ idAudit
				+ ")";

		if (!StringUtils.isEmpty(search)) {
			queryStr += " AND (" + "upper(sp.descrizione) like UPPER('%" + search + "%') " + ")";
			queryStr += " or (" + "upper(p.descrizione) like UPPER('%" + search + "%') " + ")";
		}
		
		queryStr +=" ORDER BY "+(columnNameToOrder + 1)+" OFFSET "+pageNumber+" ROWS ";
		queryStr +=" FETCH NEXT "+pageSize+" ROWS ONLY ";

		try {
			Query querySel = em.createNativeQuery(queryStr);
			list =  querySel.getResultList();

		} catch (Throwable e) {
			log.error("");
		}
		return list;
	}

	/*
	 * COUNT DELLA TABELLA NELL ASIDE
	 */
	public int countFasiInAsideTable(long idProcesso, long idAudit,
			String textSearch) {
		Integer count = null;
		String query = "SELECT count(sp.ID_SOTTOPROCESSO) "
				+ "from CRS_SOTTOPROCESSO sp "
				+ "inner join crs_processo p on sp.ID_PROCESSO= p.ID_PROCESSO "
				+ "inner join CRS_AREA a on p.ID_AREA = a.ID_AREA "
				+ "where p.ID_PROCESSO=" + idProcesso
				+ " AND sp.ID_SOTTOPROCESSO NOT IN(select au.ID_SOTTOPROCESSO "
				+ "from AU_ASS_AUDIT_FASE au " + "where au.ID_AUDIT=" + idAudit
				+ ")";

		if (!StringUtils.isEmpty(textSearch)) {
			query += " and (" + "upper(sp.descrizione) like UPPER('%" + textSearch + "%') "+ ")";
			query += " or (" + "upper(p.descrizione) like UPPER('%" + textSearch + "%') "+ ")";

		}

		try {
			Query querySel = em.createNativeQuery(query);
			count = (Integer) querySel.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			log
					.error("Errore durante l'esecuzione del meotodo  countFasiInAsideTable() della classe AuAuditDaoImpl.java ");

		}
		log
				.info("Metodo countFasiInAsideTable() della classe AuAuditDaoImpl.java eseguito");
		return count == null ? 0 : count;
	}

	/*
	 * AGGIUNGE FASE ALL AUDIT
	 */
	@Transactional
	public void addFaseToAudit(long idSottoprocesso, long idAudit) {
		AuAssAuditFase a = new AuAssAuditFase();
		a.setIdAudit(idAudit);
		a.setIdSottoprocesso(idSottoprocesso);
		try {
			em.merge(a);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/*
	 * ELIMINA FASE ASSOCIATA ALL AUDI
	 */
	@Transactional
	public void eliminaFase(long idAudit, long idSottoprocesso) {

		try {
			Query query = em.createQuery("DELETE FROM AuAssAuditFase a " +

			"WHERE a.idSottoprocesso=" + idSottoprocesso + " and a.idAudit="
					+ idAudit);

			int deletedCount = query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/*
	 * QUESTIONARIO
	 */

	//metodi cancellati
	
	
	/*
	 * NON CONFORMITA
	 */

	public List<Object[]> getListaNonConformita(Long idAudit,long idFase, Integer pageNumber,
			Integer pageSize, int columnNameToOrder, String orderType,String search)		
			
			{
		List<Object[]> l=null;
			String query = "select anc.* "+
					"from AU_NON_CONFORMITA anc "+
					"inner join CRS_SOTTOPROCESSO cs on anc.ID_FASE = cs.ID_SOTTOPROCESSO "+
					"inner join AU_ASS_AUDIT_FASE af on cs.ID_SOTTOPROCESSO = af.ID_SOTTOPROCESSO "+
					"where af.ID_AUDIT="+idAudit+" and af.ID_SOTTOPROCESSO="+idFase;
			if (!StringUtils.isEmpty(search)) {
				query += " and (" + "upper(anc.descrizione) like UPPER('%"+ search + "%') " +")";
			}
			
			query +=" ORDER BY "+(columnNameToOrder + 1)+" "+orderType +" OFFSET "+pageNumber+" ROWS ";
			query +=" FETCH NEXT "+pageSize+" ROWS ONLY ";
			
			
			
			try {
				Query querySel = em.createNativeQuery(query);
				l =querySel.getResultList();

			} catch (Throwable e) {
				e.printStackTrace();
			}
			
		
		return l;
	}

	@Override
	public int countNonConformita(long idAudit, long idFase, String getsSearch ) {
		String query = "select count(anc.descrizione)"+
		"from AU_NON_CONFORMITA anc "+
		"inner join CRS_SOTTOPROCESSO cs on anc.ID_FASE = cs.ID_SOTTOPROCESSO "+
		"inner join AU_ASS_AUDIT_FASE af on cs.ID_SOTTOPROCESSO = af.ID_SOTTOPROCESSO "+
		"where af.ID_AUDIT="+idAudit+" and af.ID_SOTTOPROCESSO="+idFase;
			if (!StringUtils.isEmpty(getsSearch)) {
				query += " and (" + "upper(anc.descrizione) like UPPER('%"+ getsSearch + "%') " +")";
			}


			int count=0;

			try {
				Query querySel = em.createNativeQuery(query, Integer.class);
				count = (Integer) querySel.getSingleResult();

			} catch (Throwable e) {
				e.printStackTrace();
			}
		return count;
	}
	
	

//	@Override
//	public int countCampagneAssociateAudit(long idAudit) {
//		String qryString="select COUNT (ID_CAMPAGNA) from AU_CAMPAGNA  where ID_AUDIT= "+idAudit;
//		Query query=em.createNativeQuery(qryString,Integer.class);
//		int numeroCampagne;
//		try{
//			numeroCampagne=(Integer)query.getSingleResult();			
//		}catch (Exception e) {
//			numeroCampagne=0;
//		}
//		return numeroCampagne;
//	}
	@Override
	public int isAuditReferenced(long idAudit) {
		String qryString="select distinct ID_AUDIT from AU_M_NONCONF where ID_AUDIT = "+ idAudit+
			" union select distinct ID_AUDIT from AU_M_RISCHIO where ID_AUDIT = "+ idAudit+
			" union select distinct ID_AUDIT from AU_IICG where ID_AUDIT = "+ idAudit+
			" union select distinct ID_AUDIT from AU_IRDES where ID_AUDIT = "+ idAudit+
			" union select distinct ID_AUDIT from AU_ISNC where ID_AUDIT = "+ idAudit+
			" union select distinct ID_AUDIT from AU_TPL_ISNC where ID_AUDIT = "+ idAudit+
			" union select distinct ID_AUDIT from AU_CAMPAGNA where ID_AUDIT = "+ idAudit+
			" union select distinct ID_AUDIT from AU_ASS_AUDIT_FASE where ID_AUDIT ="+ idAudit;
		
		Query query=em.createNativeQuery(qryString);
		int size=query.getResultList().size();
		return size;
	}

	@Override
	public int countQuestionariAssociatiAudit(long idAudit) {
		String qryString="select COUNT (ID_QUESTIONARIO) from AU_QUESTIONARIO where ID_AUDIT= "+idAudit;
		Query query=em.createNativeQuery(qryString,Integer.class);
		int result;
		try{
			result=(Integer)query.getSingleResult();
		}catch (Exception e) {
			result=0;
		}
		return result;
	}

	@Transactional
	@Override
	public void deleteQuestionario(long idAudit) {
		String qryString="DELETE FROM AU_QUESTIONARIO WHERE ID_AUDIT= "+idAudit;
		Query query=em.createNativeQuery(qryString);
		try{
			int delete=query.executeUpdate();			
		}catch (Exception e) {
			log.error("IMPOSSIBILE ELIMINARE QUESTIONARIO "+e.getMessage());
		}
	}

	/*
	 * D E L E G A T O_____________________________________________________________________________________________________________
	 */

	@Override
	public long getIdDirigenteFromDelegato(long idDelegato) {
		String qryString ="SELECT ID_DIRIGENTE FROM CRS_ASS_DELEGATO_DIRIGENTE WHERE ID_DELEGATO= "+idDelegato;
		Query query=em.createNativeQuery(qryString);
		long result;
		try{
			result=(Long)query.getSingleResult();
		}catch (Exception e) {
			result=0;
		}
		return result;
	}

	@Override
	public List<Object[]> getListaDelegati(long idDirigente, long idAudit, Integer pageNumber,Integer pageSize, int columnNameToOrder, String orderType,String search) {
		String qry="SELECT * FROM("+
		" SELECT u.ID_UTENTE ,u.NOME, u.COGNOME, 'disattivo' stato"+
		" FROM CRS_ASS_UTENTE_RUOLO aur, CRS_RUOLO r, CRS_ASS_CODICE_DIRIGENTE acd,CRS_UTENTE_ADV u"+
		" WHERE acd.ID_DIRIGENTE= "+idDirigente+
		" and acd.CODICE=r.CODICE"+
		" and r.ID_RUOLO=aur.ID_RUOLO"+
		" and r.DESCRIZIONE like 'delegato%'"+
		" and aur.ID_UTENTE=u.ID_UTENTE"+
		" and acd.ID_DIRIGENTE!=u.ID_UTENTE"+
		
		" and u.ID_UTENTE not in (SELECT u.ID_UTENTE"+
		" FROM  CRS_UTENTE_ADV u, AU_ASS_UTENTE_AUDIT ass"+
		" WHERE ass.ID_AUDIT= "+idAudit+ 
		" and ass.ID_UTENTE=u.ID_UTENTE"+
		" and u.ID_UTENTE != "+idDirigente+" and ass.DATA_FINE is null)"+
		
		" UNION"+
		
		" SELECT u.ID_UTENTE ,u.NOME, u.COGNOME, 'attivo' stato"+
		" FROM  CRS_UTENTE_ADV u, AU_ASS_UTENTE_AUDIT ass"+
		" WHERE ass.ID_AUDIT= "+idAudit+ 
		" and ass.ID_UTENTE=u.ID_UTENTE"+ 
		" and u.ID_UTENTE != "+idDirigente+" and ass.DATA_FINE is null) result";
		
		if (!StringUtils.isEmpty(search)) {
			qry += " WHERE (" + "upper(result.NOME) like UPPER('%"+ search + "%') " + ")";
			qry += " or (" + "upper(result.COGNOME) like UPPER('%"+ search + "%') " + ")";
		}
		if(pageNumber != -1){
			qry +=" ORDER BY "+(columnNameToOrder +1)+" "+orderType +" OFFSET "+pageNumber +" ROWS ";
			qry +=" FETCH NEXT "+pageSize+" ROWS ONLY ";
		}
		
		Query query=em.createNativeQuery(qry);
		List<Object[]> result= null;
		try{
			result=query.getResultList();
		}catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
			e.getMessage();
		}
		
		return result;
	}

	@Override
	public int countListaDelegati(long idDirigente, long idAudit, String textSearch) {
		String qry="SELECT COUNT (result.ID_UTENTE) FROM("+
		" SELECT u.ID_UTENTE ,u.NOME, u.COGNOME, 'disattivo' stato"+
		" FROM CRS_ASS_UTENTE_RUOLO aur, CRS_RUOLO r, CRS_ASS_CODICE_DIRIGENTE acd,CRS_UTENTE_ADV u"+
		" WHERE acd.ID_DIRIGENTE= "+idDirigente+
		" and acd.CODICE=r.CODICE"+
		" and r.ID_RUOLO=aur.ID_RUOLO"+
		" and r.DESCRIZIONE like 'delegato%'"+
		" and aur.ID_UTENTE=u.ID_UTENTE"+
		" and acd.ID_DIRIGENTE!=u.ID_UTENTE"+
		
		" and u.ID_UTENTE not in (SELECT u.ID_UTENTE"+
		" FROM  CRS_UTENTE_ADV u, AU_ASS_UTENTE_AUDIT ass"+
		" WHERE ass.ID_AUDIT= "+idAudit+ 
		" and ass.ID_UTENTE=u.ID_UTENTE"+
		" and u.ID_UTENTE != "+idDirigente+")"+
		
		" UNION"+
		
		" SELECT u.ID_UTENTE ,u.NOME, u.COGNOME, 'attivo' stato"+
		" FROM  CRS_UTENTE_ADV u, AU_ASS_UTENTE_AUDIT ass"+
		" WHERE ass.ID_AUDIT= "+idAudit+ 
		" and ass.ID_UTENTE=u.ID_UTENTE"+ 
		" and u.ID_UTENTE != "+idDirigente+") result";
		
		if (!StringUtils.isEmpty(textSearch)) {
			   qry+= " WHERE (" + "upper(result.NOME) like UPPER('%"+ textSearch + "%') " + ")";
			   qry+= " or (" + "upper(result.COGNOME) like UPPER('%"+ textSearch + "%') " + ")";
			}
		
		Query query=em.createNativeQuery(qry,Integer.class);
		Integer result=null;
		try{
			result=(Integer)query.getSingleResult();
		}catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
			e.getMessage();
		}
		
		return result;
	}

	@Deprecated
	public void dissociaDelegato(long idAudit, long idUtente) {
		String qry="DELETE FROM AU_ASS_UTENTE_AUDIT WHERE ID_UTENTE= "+idUtente+
		" AND ID_AUDIT= "+idAudit;
		Query query=em.createNativeQuery(qry);
		
		try{
			query.executeUpdate();
		}catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
			e.getMessage();
		}
	}
	
	@SuppressWarnings("unchecked")
	public AuAssUtenteAudit getAssociazioneDelegato(long idAudit, long idUtente) {
		
		List<AuAssUtenteAudit> associazione = null;
		
		String qry = "SELECT " +
					 "ID_ASS_UTENTE_AUDIT, ID_UTENTE, ID_AUDIT, DATA_INIZIO, DATA_FINE " +
					 "FROM AU_ASS_UTENTE_AUDIT " + 
					 "WHERE ID_UTENTE = " + idUtente + " AND ID_AUDIT = " + idAudit;
		
		Query query=em.createNativeQuery(qry,AuAssUtenteAudit.class);
		
		try{
			associazione = query.getResultList();
		}catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
			e.getMessage();
		}
		
		return (associazione != null && associazione.size() > 0) ? associazione.get(0) : new AuAssUtenteAudit();
	}

	/*
	 *  A U D I T O R S _______________________________________________________________________________________________________
	 */
	
	@Override
	public List<Object[]> getListaAuditors(long idDirigente, long idAccesso,
			Integer pageNumber, Integer pageSize, int columnNameToOrder,
			String orderType, String search) {

//		String qry="SELECT * FROM("+
//		" SELECT u.ID_UTENTE ,u.NOME, u.COGNOME, u.USERNAME, 'disattivo' stato"+
//		" FROM CRS_ASS_UTENTE_RUOLO aur, CRS_RUOLO r, CRS_ASS_CODICE_DIRIGENTE acd,CRS_UTENTE_ADV u"+
//		" WHERE acd.ID_DIRIGENTE= "+idDirigente+
//		" and acd.CODICE=r.CODICE"+
//		" and r.ID_RUOLO=aur.ID_RUOLO"+
//		" and r.DESCRIZIONE like 'auditors%'"+
//		" and aur.ID_UTENTE=u.ID_UTENTE"+
//		" and acd.ID_DIRIGENTE!=u.ID_UTENTE"+
//		
//		" and u.ID_UTENTE not in (SELECT u.ID_UTENTE"+
//		" FROM  CRS_UTENTE_ADV u, AU_ASS_UTENTE_SESSIONE ass"+
//		" WHERE ass.ID_SESSIONE= "+idAccesso+ 
//		" and ass.ID_UTENTE=u.ID_UTENTE"+
//		" and u.ID_UTENTE != "+idDirigente+" and ass.DATA_FINE is null)"+
//		
//		" UNION"+
//		
//		" SELECT u.ID_UTENTE ,u.NOME, u.COGNOME, u.USERNAME, 'attivo' stato"+
//		" FROM  CRS_UTENTE_ADV u, AU_ASS_UTENTE_SESSIONE ass"+
//		" WHERE ass.ID_SESSIONE= "+idAccesso+ 
//		" and ass.ID_UTENTE=u.ID_UTENTE"+ 
//		" and u.ID_UTENTE != "+idDirigente+" and ass.DATA_FINE is null) result";
		
		String qry=" SELECT * FROM( "+
			" SELECT u.ID_UTENTE ,u.NOME, u.COGNOME, u.USERNAME, 'disattivo' stato "+
			" FROM CRS_ASS_UTENTE_RUOLO aur, CRS_RUOLO r, CRS_ASS_CODICE_DIRIGENTE acd,CRS_UTENTE_ADV u "+
			" WHERE acd.ID_DIRIGENTE= "+idDirigente+" and acd.CODICE=r.CODICE and r.ID_RUOLO=aur.ID_RUOLO "+
			" and r.DESCRIZIONE like 'auditors%' and aur.ID_UTENTE=u.ID_UTENTE 	"+	        
			" and u.ID_UTENTE not in "+
			" (SELECT u.ID_UTENTE FROM  CRS_UTENTE_ADV u, AU_ASS_UTENTE_SESSIONE ass "+
			"  WHERE ass.ID_SESSIONE= "+idAccesso+" and ass.ID_UTENTE=u.ID_UTENTE "+
			"  and ass.DATA_FINE is null)"+
			" UNION "+
			" SELECT u.ID_UTENTE ,u.NOME, u.COGNOME, u.USERNAME, 'attivo' stato "+
			" FROM  CRS_UTENTE_ADV u, AU_ASS_UTENTE_SESSIONE ass "+
			" WHERE ass.ID_SESSIONE= "+idAccesso+" and ass.ID_UTENTE=u.ID_UTENTE "+	    
			" and ass.DATA_FINE is null) "+
			" result ";
		
		if (!StringUtils.isEmpty(search)) {
			qry += " WHERE (" + "upper(result.NOME) like UPPER('%"+ search + "%') " + ")";
			qry += " or (" + "upper(result.COGNOME) like UPPER('%"+ search + "%') " + ")";
		}
		if(pageNumber != -1){
			qry +=" ORDER BY "+(columnNameToOrder +1)+" "+orderType +" OFFSET "+pageNumber +" ROWS ";
			qry +=" FETCH NEXT "+pageSize+" ROWS ONLY ";
		}
		
		Query query=em.createNativeQuery(qry);
		List<Object[]> result= null;
		try{
			result=query.getResultList();
		}catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
			e.getMessage();
		}
		
		return result;
		
	}

	@Override
	public int countListaAuditors(long idDirigente, long idAccesso,
			String textSearch) {
		
		String qry="SELECT COUNT (result.ID_UTENTE) FROM("+
		" SELECT u.ID_UTENTE ,u.NOME, u.COGNOME, u.USERNAME, 'disattivo' stato "+
		" FROM CRS_ASS_UTENTE_RUOLO aur, CRS_RUOLO r, CRS_ASS_CODICE_DIRIGENTE acd,CRS_UTENTE_ADV u "+
		" WHERE acd.ID_DIRIGENTE= "+idDirigente+" and acd.CODICE=r.CODICE and r.ID_RUOLO=aur.ID_RUOLO "+
		" and r.DESCRIZIONE like 'auditors%' and aur.ID_UTENTE=u.ID_UTENTE 	"+	        
		" and u.ID_UTENTE not in "+
		" (SELECT u.ID_UTENTE FROM  CRS_UTENTE_ADV u, AU_ASS_UTENTE_SESSIONE ass "+
		"  WHERE ass.ID_SESSIONE= "+idAccesso+" and ass.ID_UTENTE=u.ID_UTENTE "+
		"  and ass.DATA_FINE is null)"+
		" UNION "+
		" SELECT u.ID_UTENTE ,u.NOME, u.COGNOME, u.USERNAME, 'attivo' stato "+
		" FROM  CRS_UTENTE_ADV u, AU_ASS_UTENTE_SESSIONE ass "+
		" WHERE ass.ID_SESSIONE= "+idAccesso+" and ass.ID_UTENTE=u.ID_UTENTE "+	    
		" and ass.DATA_FINE is null) "+
		" result ";
		
		if (!StringUtils.isEmpty(textSearch)) {
			   qry+= " WHERE (" + "upper(result.NOME) like UPPER('%"+ textSearch + "%') " + ")";
			   qry+= " or (" + "upper(result.COGNOME) like UPPER('%"+ textSearch + "%') " + ")";
			}
		
		Query query=em.createNativeQuery(qry,Integer.class);
		Integer result=null;
		try{
			result=(Integer)query.getSingleResult();
		}catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
			e.getMessage();
		}
		
		return result;
	}

	@Deprecated
	public void dissociaAuditors(long idAccesso, long idUtente) {
		String qry="DELETE FROM AU_ASS_UTENTE_SESSIONE WHERE ID_UTENTE= "+idUtente+
		" AND ID_SESSIONE= "+idAccesso;
		Query query=em.createNativeQuery(qry);
		
		try{
			query.executeUpdate();
		}catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
			e.getMessage();
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public AuAssUtenteSessione getAssociazioneAuditors(long idSessione, long idUtente) {
		
		List<AuAssUtenteSessione> associazione = null;
		
		String qry = "SELECT " +
					 "ID_ASS_US, ID_UTENTE, ID_SESSIONE, DATA_INIZIO, DATA_FINE " +
					 "FROM AU_ASS_UTENTE_SESSIONE " + 
					 "WHERE ID_UTENTE = " + idUtente + " AND ID_SESSIONE = " + idSessione;
		
		Query query=em.createNativeQuery(qry,AuAssUtenteSessione.class);
		
		try{
			associazione = query.getResultList();
		}catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
			e.getMessage();
		}
		
		return (associazione != null && associazione.size() > 0) ? associazione.get(0) : new AuAssUtenteSessione();
	}
	
	/*
	 * 	 Q U E S T I O N A R I O ______________________________________________________________________________-
	 */
	
	@Override
	public AuQuestionario cercaQuestionario(long idAudit) {
		AuQuestionario q=null;
		String queryStr = "select q.* "+ 
					      "from AU_QUESTIONARIO q "+
					      "where q.ID_AUDIT =" + idAudit;


		try {
			Query querySel = em.createNativeQuery(queryStr, AuQuestionario.class);
		
			q = (AuQuestionario)querySel.getSingleResult();
			
		} catch (Throwable e) {
			e.printStackTrace();

		}
		return q;
		
		
	}
	
	@Override
	public List<Object[]> getDomande(long idAudit, int pageNumber,
			int pageSize, int columnNameToOrder, String orderType, String search) {
		List<Object[]> domande = new ArrayList<Object[]>();
		String queryStr = "select q.id_questionario, d.id_domanda, d.id_m_rischio, d.descrizione, d.PESO_DOMANDA, d.VALORE_MAX_RISPOSTA, r.DESCRIZIONE_RISCHIO, d.PESO_PERCENTUALE, d.CONTROLLO_PROCESSO "+ 
					      "from AU_M_DOMANDA d, AU_M_RISCHIO r, AU_QUESTIONARIO q "+
					      "where r.ID_M_RISCHIO = d.ID_M_RISCHIO and "+
					      "d.ID_QUESTIONARIO = q.ID_QUESTIONARIO and q.ID_AUDIT =" + idAudit;

		if (!StringUtils.isEmpty(search)) {
			queryStr += " and((" + "upper(d.descrizione) like UPPER('%"+ search + "%')"+ "))";
			
		}

		
		queryStr +=" ORDER BY "+(columnNameToOrder + 3)+" "+orderType +" OFFSET "+pageNumber+" ROWS ";
		queryStr +=" FETCH NEXT "+pageSize+" ROWS ONLY ";


		try {
			Query querySel = em.createNativeQuery(queryStr);
			domande = querySel.getResultList();

		} catch (Throwable e) {
			e.printStackTrace();

		}
		return domande;
	}
	
	@Override
	public int countDomande(long idAudit, int pageNumber, int pageSize,
			int columnNameToOrder, String orderType, String search) {
		Integer count=null;
		String queryStr = "select count(d.id_domanda) "+ 
	      "from AU_M_DOMANDA d, AU_M_RISCHIO r, AU_QUESTIONARIO q "+
	      "where r.ID_M_RISCHIO = d.ID_M_RISCHIO and "+
	      "d.ID_QUESTIONARIO = q.ID_QUESTIONARIO and q.ID_AUDIT =" + idAudit;

		
		if (!StringUtils.isEmpty(search)) {
			queryStr += " and((" + "upper(d.descrizione) like UPPER('%"+ search + "%')"+ "))";
			
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
	public long getNumDomandeStessoRischio(long idRischio, long idQuestionario) {
		Integer count=null;
		String queryStr = "select count(id_domanda) "+
						  "from AU_M_DOMANDA "+
						  "where id_questionario="+idQuestionario+"  and id_m_rischio="+idRischio;

		
		
		try {
			Query querySel = em.createNativeQuery(queryStr);
			count = (Integer) querySel.getSingleResult();

		} catch (Throwable e) {
			e.printStackTrace();

		}
		return count;
		
	}
	
/*
 * viene chiamato in fase di creazione domanda 
 */
	@Override
	public Double sommaPesiDomandeStessoRischio(long idRischio,
			long idQuestionario) {
		BigDecimal count=null;
		String queryStr = "select sum(peso_percentuale) "+
						  "from AU_M_DOMANDA "+
						  "where id_questionario="+idQuestionario+" and id_m_rischio="+idRischio;

		
		
		try {
			Query querySel = em.createNativeQuery(queryStr);
			count = (BigDecimal) querySel.getSingleResult();

		} catch (Throwable e) {
			e.printStackTrace();

		}
		return count.doubleValue();
		
	}
	/*
	 * viene chiamato in fase di modifica di una domanda 
	 */
	public Double sommaPesiDomandeStessoRischio(long idRischio,long idQuestionario, long idDomanda) {
		BigDecimal count=null;
		String queryStr = "select sum(peso_percentuale) "+
						  "from AU_M_DOMANDA "+
						  "where id_questionario="+idQuestionario+" and id_m_rischio="+idRischio +" and ID_DOMANDA !="+ idDomanda;

		
		
		try {
			Query querySel = em.createNativeQuery(queryStr);
			count = (BigDecimal) querySel.getSingleResult();

		} catch (Throwable e) {
			e.printStackTrace();

		}
		if(count!=null){
			return count.doubleValue();
		}else{
			return 0D;
		}
		
	}
	
	@Override
	public List<AuMRisposta> getRisposteDomanda(long idDomanda, int pageNumber,
			int pageSize, int columnNameToOrder, String orderType, String search) {
		List<AuMRisposta> list = null;
		String queryStr = "SELECT R.* "+
						  "FROM AU_M_RISPOSTA R "+
						  "WHERE R.ID_DOMANDA= "+idDomanda;	

		if (!StringUtils.isEmpty(search)) {
			queryStr += " and (upper(R.DESCRIZIONE) like UPPER('%"+ search + "%') or UPPER(R.VALORE_RISPOSTA) like UPPER('%"+search +"%'))";
		}

		queryStr +=" ORDER BY "+(columnNameToOrder + 1)+" "+orderType +" OFFSET "+pageNumber+" ROWS ";
		queryStr +=" FETCH NEXT "+pageSize+" ROWS ONLY ";


		try {
			//Query querySel = em.createNativeQuery(queryStr,AuMRischio.class);
			List<AuMRisposta> resultList = em.createNativeQuery(queryStr, AuMRisposta.class).getResultList();

			//solution 
			list = new ArrayList<AuMRisposta>(resultList); 


		} catch (Throwable e) {
			e.printStackTrace();

		}
		return list;
	}
	
	@Override
	public int countRisposte(long idDomanda, int pageNumber, int pageSize,
			int columnNameToOrder, String orderType, String search) {
		Integer count=null;
		String queryStr = "select count(r.id_risposta) FROM AU_M_RISPOSTA R "+
						  "WHERE R.ID_DOMANDA= "+idDomanda;			

		if (!StringUtils.isEmpty(search)) {
			queryStr += " and (upper(R.DESCRIZIONE) like UPPER('%"+ search + "%') or UPPER(R.VALORE_RISPOSTA) like UPPER('%"+search +"%'))";
		} 				 

		try {
			Query querySel = em.createNativeQuery(queryStr);
			count = (Integer) querySel.getSingleResult();

		} catch (Throwable e) {
			e.printStackTrace();

		}
		return count;
	}

	/*
	 * In fase di eliminazione della domanda controlla che non ci siano risposte associate
	 */
	@Override
	public AuMDomanda checkRisposteDomanda(AuMDomanda i) {
		AuMDomanda d=null;
		List<AuMDomanda> l = null;
		String queryStr = "select d.* "+ 
					      "from AU_M_DOMANDA d, AU_M_RISPOSTA r "+
					      "where r.id_domanda=" + i.getIdDomanda();


		try {
			//Query querySel = em.createNativeQuery(queryStr,AuMRischio.class);
			List<AuMDomanda> resultList = em.createNativeQuery(queryStr, AuMDomanda.class).getResultList();

			//solution 
			l = new ArrayList<AuMDomanda>(resultList); 
			if(!l.isEmpty()){
				d= l.get(0);
			}
		} catch (Throwable e) {
			e.printStackTrace();

		}
		
		return d;
	}



	
	/*
	 *  O P   C O M U N I__________________________________________________________
	 */

	@Transactional
	public <T> Object salva(T obj) {
		//a.setIdAudit(null);
		try {
			Object debug=em.merge(obj);
			return debug;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}


}
