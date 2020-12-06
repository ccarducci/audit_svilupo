package it.tecnet.crs.jpa.dao;

import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoFaseAcquisizioneIstanza;
import it.tecnet.crs.ATPO.util.ObjectToDto;
import it.tecnet.crs.jpa.model.AuAudit;
import it.tecnet.crs.jpa.model.AuCampagna;
import it.tecnet.crs.jpa.model.AuMNonConf;
import it.tecnet.crs.jpa.model.AuMRischio;
import it.tecnet.crs.jpa.model.AuMRisepr;
import it.tecnet.crs.jpa.model.AuMVarcomp;
import it.tecnet.crs.jpa.model.AuSPraCalIndLog;
import it.tecnet.crs.jpa.model.AuSPratica;
import it.tecnet.crs.jpa.model.AuSessioni;
import it.tecnet.crs.session.DatiUtente;
import it.tecnet.crs.web.dto.NonConformitaDto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.transaction.annotation.Transactional;

public class AuditDaoImpl implements AuditDao {

	protected static Logger log = Logger.getLogger(AuditDaoImpl.class);

	@PersistenceContext()
	private EntityManager em;

	public AuditDaoImpl() {

	}

	public Long getIdAuditBySessione(Long idSessione){

		String queryStr = "select a.ID_AUDIT from AU_AUDIT a, AU_CAMPAGNA c,AU_SESSIONI s ";
		queryStr +=" where s.ID_SESSIONE ="+idSessione;
		queryStr +=" and s.ID_CAMPAGNA=c.ID_CAMPAGNA ";
		queryStr +=" and c.ID_AUDIT=a.ID_AUDIT ";
		Long id = null;
		try{
			Query querySel = em.createNativeQuery(queryStr);
			id = (Long) querySel.getSingleResult();
		}catch(Exception e){
			e.printStackTrace();
			log.error("Errore durante l'esecuzioe del meotodo getIdAuditBySessione() della classe AuditDaoImpl.java ");

		}
		log.info("Metodo getIdAuditBySessione() della classe AuditDaoImpl.java eseguito");
		return id == null ? 0L : id;

	}

	/*
	 * CAMPAGNA
	 */

	/*
	 * CAMPIONE
	 */

	/* AGGIUNGERE FILTRO RISCHIO */
	public Integer countAllCampioneByIdSessione(long idSessione,
			boolean rischio, String textSearch) {
		Integer count=null;
		String queryStr = "SELECT COUNT(v.ID_VERBALE) "
			+ " from AU_VERBALE v, AU_CAMPAGNA c, AU_SESSIONI s, AU_ASS_VERBALE_CAMPAGNA ass "
			+ "  where v.ID_VERBALE=ass.IDVERBALE " 
			+ "  and ass.ID_CAMPAGNA=s.ID_CAMPAGNA "
			//+ "  and c.ID_CAMPAGNA = v.ID_CAMPAGNA "
			+ "  and s.ID_SESSIONE = " + idSessione;
		;

		if (!StringUtils.isEmpty(textSearch)) {
			queryStr += " and (" + "upper(v.SEDE) like UPPER('%" + textSearch
			+ "%') " + "or upper(v.AZIENDA) like UPPER('%" + textSearch
			+ "%')" + "or upper(v.CODICE_FISCALE) like UPPER('%"
			+ textSearch + "%')" + "or upper(c.NOME) like UPPER('%"
			+ textSearch + "%')" + ")";
		}


		try{
			Query querySel = em.createNativeQuery(queryStr);
			count = (Integer) querySel.getSingleResult();
		}catch(Exception e){
			e.printStackTrace();
			log.error("Errore durante l'esecuzioe del meotodo countAllCampioneByIdSessione() della classe AuditDaoImpl.java ");

		}
		log.info("Metodo countAllCampioneByIdSessione() della classe AuditDaoImpl.java eseguito");
		return count == null ? 0 : count;
	}



	/*
	 * DOMANDA
	 */

	/*
	 * RISPOSTA
	 */

	/*
	 * RISULTATI CAMPAGNA
	 */

	/*
	 * RISULTATI CAMPIONE
	 */

	/*
	 * SESSIONI
	 */

	public List<AuSessioni> getSessioneByIdAudit(long idAudit) {
		String queryStr = "SELECT ID_SESSIONE,S.ID_CAMPAGNA,C.NOME,SEDE,TIPO,S.DATA_INIZIO,S.DATA_FINE,S.STATO,DIRIGENTE,AUDITORS, S.NOTA "
			+ " FROM AU_SESSIONI S INNER JOIN  AU_CAMPAGNA C ON S.ID_CAMPAGNA = S.ID_CAMPAGNA "
			+ " INNER JOIN   AU_AUDIT A ON C.ID_AUDIT = A.ID_AUDIT WHERE ID_AUDIT = :idAudit";
		Query querySel = em.createQuery(queryStr);
		querySel.setParameter("ID_AUDIT", idAudit);

		List<AuSessioni> sessioniList = (List<AuSessioni>) querySel
		.getResultList();
		try {
			if (sessioniList.size() == 0) {
				throw new Exception(
						" Non esiste nessuna sessione con id audit'" + idAudit
						+ "'");
			}
		} catch (Throwable e) {
			log.error("Errore getSessioneByIdAudit. Errore:" + e.getMessage(),
					e);
		}
		return sessioniList;
	}

	public Integer countAllSessioniUtente(long idUtente, String textSearch) {
		Integer count=null;
		String queryStr = "SELECT COUNT (s.id_sessione) "+
		"FROM Au_Sessioni s,  Au_Ass_Utente_Sessione us, Crs_Utente_adv u, "+
		"AU_AUDIT aud, AU_CAMPAGNA cmp , AU_ASS_AUDIT_TIPO_ACCESSO ata "+
		"where s.id_Sessione=us.id_Sessione "+
		"and us.id_utente= u.id_utente "+
		"and u.id_Utente = "+idUtente+
		" and cmp.ID_CAMPAGNA= s.ID_CAMPAGNA and aud.ID_AUDIT=cmp.ID_AUDIT "+
		"and ata.ID_AUDIT=aud.ID_AUDIT "+
		"and (us.data_fine is null or us.data_fine > getDate()) ";

		if (!StringUtils.isEmpty(textSearch)) {

			queryStr += " and (" + "upper(aud.DESCRIZIONE) like UPPER('%" + textSearch + "%'))";
		}
		try{
			Query querySel = em.createNativeQuery(queryStr);

			count = (Integer) querySel.getSingleResult();

		}catch(Exception e){
			e.printStackTrace();
			log.error("Errore durante l'esecuzione del meotodo countAllSessioniUtente() della classe AuditDaoImpl.java ");

		}
		log.info("Metodo countAllSessioniUtente() della classe AuditDaoImpl.java eseguito");

		return count == null ? 0 : count;
	}

	public List<Object[]> getListaSessioniUtente(long idUtente,
			Integer pageNumber, Integer pageSize, String columnNameToOrder,
			String orderType, String textSearch) {

		List<Object[]> sessioniList=null;
		String queryStr = "SELECT s.id_sessione, cmp.NOME, aud.DESCRIZIONE, s.SEDE, s.DATA_INIZIO, s.DATA_FINE, s.STATO, s.DIRIGENTE, ata.TIPO_ACCESSO "
			+ " FROM Au_Sessioni s,  Au_Ass_Utente_Sessione us, Crs_Utente_adv u,"
			+ " AU_AUDIT aud, AU_CAMPAGNA cmp , AU_ASS_AUDIT_TIPO_ACCESSO ata"
			+ " where s.id_Sessione=us.id_Sessione "
			+ " and us.id_utente= u.id_utente "
			+ " and u.id_Utente = "
			+ idUtente
			+ " and cmp.ID_CAMPAGNA= s.ID_CAMPAGNA and aud.ID_AUDIT=cmp.ID_AUDIT "
			+ " and ata.ID_AUDIT=aud.ID_AUDIT "
			+ " and (us.data_fine is null or us.data_fine > getDate()) ";

		if (!StringUtils.isEmpty(textSearch)) {

			queryStr += " and (" + "upper(aud.descrizione) like UPPER('%" + textSearch + "%') "  + ")";
		}
		if (!StringUtils.isEmpty(columnNameToOrder)) {
			int columnNametoOrder= Integer.valueOf(columnNameToOrder);
			queryStr +=" ORDER BY "+(columnNametoOrder + 1)+" "+orderType +" OFFSET "+pageNumber+" ROWS ";
			queryStr +=" FETCH NEXT "+pageSize+" ROWS ONLY ";
		}

		Query querySel = em.createNativeQuery(queryStr);
		/*if (pageNumber != null && pageSize != null && pageSize > 0) {
			querySel.setFirstResult(pageNumber);
			querySel.setMaxResults(pageSize);
		}*/

		try{
			sessioniList = querySel.getResultList();
		}catch(Exception e){
			log.error("Errore durante l'esecuzione del meotodo getListaSessioniUtente() della classe AuditDaoImpl.java ");
		}
		log.info("Metodo getListaSessioniUtente() della classe AuditDaoImpl.java eseguito");

		return sessioniList;
	}

	@Override
	public String getNotaSessione(long idSessione) {
		String nota=null;
		String queryStr = "select nota from AU_SESSIONI where ID_SESSIONE="+idSessione;
		try{
			Query querySel = em.createNativeQuery(queryStr);
			nota= (String) querySel.getSingleResult();
		}catch(Exception e){
			log.error("Errore durante l'esecuzione del meotodo getNotaSessione() della classe AuditDaoImpl.java ");

		}
		log.info("Metodo getNotaSessione() della classe AuditDaoImpl.java eseguito");

		return nota;
	}

	@Override
	public void salvaNota(AuSessioni s) {
		try{
			em.merge(s);
		}catch(Exception e){
			log.error("Errore durante l'esecuzione del meotodo salvaNota() della classe AuditDaoImpl.java ");
		}

		log.info("Metodo salvaNota() della classe AuditDaoImpl.java eseguito");
	}

	/*
	 * VERBALE
	 */

	public Integer countAllPraticheSessione(long idSessione, String textSearch) {
		Integer count =null;
		String queryStr = "SELECT count (v.id_verbale) "
			+ " from AU_ASS_CAMP_VERB ass "
			+ " inner join au_campione c on ass.ID_CAMPIONE=c.ID_CAMPIONE"
			+ " inner join AU_VERBALE v on v.ID_VERBALE = ass.ID_VERBALE"
			+ " inner join AU_SESSIONI s on s.ID_SESSIONE=c.ID_SESSIONE"
			+ " inner join AU_ASS_VERBALE_CAMPAGNA assvc on assvc.ID_VERBALE= v.ID_VERBALE"
			+ " inner join au_campagna ca on ca.ID_CAMPAGNA=assvc.ID_CAMPAGNA"

			+ " where s.ID_SESSIONE=" + idSessione;

		if (!StringUtils.isEmpty(textSearch)) {

			queryStr += " and (" + 
			"upper(v.NUMERO_PROTOCOLLO) like UPPER('%" + textSearch+ "%') " + ")";
		}

		try{
			Query querySel = em.createNativeQuery(queryStr);

			count = (Integer) querySel.getSingleResult();
		}catch(Exception e){
			log.error("Errore durante l'esecuzione del meotodo countAllPraticheSessione() della classe AuditDaoImpl.java ");
		}
		log.info("Metodo countAllPraticheSessione() della classe AuditDaoImpl.java eseguito");
		return count == null ? 0 : count;
	}







	@Transactional
	public <T> void salva(T obj) {
		//a.setIdAudit(null);
		try {
			em.merge(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/*
	 * VERBALE MANUALE NOTIFICA
	 */














	@Override
	public List<Object[]> getTabRischiPrat(long idAudit, Integer pageNumber,
			Integer pageSize, int columnNameToOrder, String orderType,
			String search) {
		List<Object[]>l= new ArrayList<Object[]>();


		String query = "SELECT   RE.DESCRIZIONE,PR.IMPORTO_CONTR_NONICASSATA, PR.IMPORTO_INDEB_SOSPESO, PR.IMPORTO_PRESCRITTO, "+
		"PR.MANIFESTAZ_RISCHIO, PR.RM, PR.EDU,PR.DATA_ATTRIBUZIONE   "+
		"FROM AU_M_RISESPR RE, AU_S_PRATICA_RIS PR , AU_M_RISCHIO R  "+
		"WHERE RE.ID_M_RISCHIO=R.ID_M_RISCHIO AND RE.ID_M_RISCHIO = PR.ID_RISCHIO AND R.ID_AUDIT="+idAudit;
		if (!StringUtils.isEmpty(search)) {
			query += " and (upper(re.descrizione) like UPPER('%"+ search + "%'))";
		}

		query +=" ORDER BY "+(columnNameToOrder+1)+" "+orderType +" OFFSET "+pageNumber+" ROWS ";
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
	public int countTabRischiPrat(long idAudit, Integer pageNumber,
			Integer pageSize, int columnNameToOrder, String orderType,
			String search) {
		Integer count=null;
		String query = "SELECT COUNT(RE.DESCRIZIONE) "+  
		"FROM AU_M_RISESPR RE, AU_S_PRATICA_RIS PR , AU_M_RISCHIO R  "+
		"WHERE RE.ID_M_RISCHIO=R.ID_M_RISCHIO AND RE.ID_M_RISCHIO = PR.ID_RISCHIO AND R.ID_AUDIT="+idAudit;

		if (!StringUtils.isEmpty(search)) {
			query += " and (upper(re.descrizione) like UPPER('%"+ search + "%'))";
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
	public List<NonConformitaDto> mediaNonConformita(long idSessione) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	public <T> T cerca(Class<T> obj , Object pk) {
		//a.setIdAudit(null);
		try {
			return (T) em.find(obj,pk);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * TIPOLOGICHE
	 */
	@Override
	public List<Object[]> getCorrettoErrato() {
		List<Object[]> list = new ArrayList<Object[]>();

		String queryStr = "select * from AU_TPL_CORRETTO_ERRATO";	

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
	public List<Object[]> getModalitaNotifica() {
		List<Object[]> list = new ArrayList<Object[]>();

		String queryStr = "select * from AU_TPL_MODALITA_NOTIFICA";	

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
	public List<Object[]> getChiNotifica() {
		List<Object[]> list = new ArrayList<Object[]>();

		String queryStr = "select * from AU_TPL_CHI_NOTIFICA";	

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
	public List<AuSessioni> getListaSessioniUtenteAsDirigente(long idUtente,
			Integer pageNumber, Integer pageSize, String columnNameToOrder,
			String orderType, String textSearch) {

		List<AuSessioni> sessioniList=null;
		String queryStr = "SELECT sess.id_sessione, cmp.NOME, au.DESCRIZIONE, sess.SEDE, sess.DATA_INIZIO, sess.DATA_FINE, sess.STATO, sess.DIRIGENTE"+ 
		" FROM AU_SESSIONI sess,AU_CAMPAGNA cmp,AU_ASS_UTENTE_AUDIT ass,AU_AUDIT au"+
		" WHERE ass.ID_UTENTE= "+idUtente+
		" AND sess.ID_CAMPAGNA=cmp.ID_CAMPAGNA"+
		" AND cmp.ID_AUDIT= ass.ID_AUDIT"+
		" AND au.ID_AUDIT=ass.ID_AUDIT";

		if (!StringUtils.isEmpty(textSearch)) {

			queryStr += " and (" + "upper(au.DESCRIZIONE) like UPPER('%" + textSearch + "%') "  + ")";
		}
		if (!StringUtils.isEmpty(columnNameToOrder)) {
			int columnNametoOrder= Integer.valueOf(columnNameToOrder);
			queryStr +=" ORDER BY "+(columnNametoOrder + 1)+" "+orderType +" OFFSET "+pageNumber+" ROWS ";
			queryStr +=" FETCH NEXT "+pageSize+" ROWS ONLY ";
		}

		Query querySel = em.createNativeQuery(queryStr);
		/*if (pageNumber != null && pageSize != null && pageSize > 0) {
			querySel.setFirstResult(pageNumber);
			querySel.setMaxResults(pageSize);
		}*/

		try{
			sessioniList = (List<AuSessioni>) querySel.getResultList();
		}catch(Exception e){
			log.error("Errore durante l'esecuzione del meotodo getListaSessioniUtenteAsDirigente() della classe AuditDaoImpl.java ");
		}
		log.info("Metodo getListaSessioniUtenteAsDirigente() della classe AuditDaoImpl.java eseguito");

		return sessioniList;

	}

	@Override
	public Integer countAllSessioniUtenteAsDirigente(long idUtente, String textSearch) {

		Integer count = null;
		String queryStr = "SELECT count(sess.ID_SESSIONE)"+
		" from AU_SESSIONI sess,AU_CAMPAGNA cmp,AU_ASS_UTENTE_AUDIT ass,AU_AUDIT au"+ 
		" WHERE ass.ID_UTENTE= "+idUtente+
		" and sess.ID_CAMPAGNA=cmp.ID_CAMPAGNA"+
		" and cmp.ID_AUDIT= ass.ID_AUDIT"+
		" and au.ID_AUDIT=ass.ID_AUDIT";	

		if (!StringUtils.isEmpty(textSearch)) {
			queryStr += " and (" + "upper(au.descrizione) like UPPER('%" + textSearch + "%'))";
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

	@Override
	public List<Object[]> getSiNo() {
		List<Object[]> list = new ArrayList<Object[]>();
		String queryStr = "select * from ATPO_SI_NO";	
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
	public List<Object[]> getEsitoReg() {
		List<Object[]> list = new ArrayList<Object[]>();
		String queryStr = "select * from AU_TPL_ESITO_REGOLA";	
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
	public List<Object[]> getErroreEsito() {
		List<Object[]> list = new ArrayList<Object[]>();
		String queryStr = "select * from AU_TPL_ERRORE_ESITO";	
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
	public List<Object[]> getComDiscRdl() {
		List<Object[]> list = new ArrayList<Object[]>();
		String queryStr = "select * from AU_TPL_MODALITA_DISCON";	
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
	public List<Object[]> getCredPrescr() {
		List<Object[]> list = new ArrayList<Object[]>();
		String queryStr = "select * from AU_TPL_CREDITO_PRESCRITTO";	
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
	public AuSPratica getSPraticaByIDVerbale(long idVerbale) {
		AuSPratica pratica = null;
		String queryStr = "select * from AU_S_PRATICA WHERE ID_PRATICA = " + idVerbale;
		try {
			Query querySel = em.createNativeQuery(queryStr, AuSPratica.class);
			pratica = (AuSPratica)querySel.getSingleResult();
		} catch (NoResultException e) {
			log.error("Non è stata trovata la AU_S_PRATICA con idVerbale: " + idVerbale);
		} catch (NonUniqueResultException e) {
			log.error("Sono stati trovati più recors di AU_S_PRATICA con idVerbale: " + idVerbale);
		}
		return pratica;
	}

	public List<Object[]> getListaNonConformitaPratiche(long idAudit,Integer pageNumber, Integer pageSize, int columnNameToOrder,
			String orderType, String search) { 

		List<Object[]>l= new ArrayList<Object[]>();
		String query = "SELECT fase.DESCRIZIONE, nncf.CODICE_NC, nncf.DESCRIZIONE, varcomp.CODICE_VC,  varcomp.DESCRIZIONE, varcomp.PESO_VC,'blu' colore, '01-01-1972' data_attribuzione "+
		"FROM  AU_M_NONCONF nncf, AU_M_VARCOMP varcomp, CRS_SOTTOPROCESSO fase  "+
		"where nncf.ID_AUDIT="+idAudit + " and fase.ID_SOTTOPROCESSO=nncf.ID_FASE  and varcomp.ID_M_NONCONF=nncf.ID_M_NON_CONF";
		if (!StringUtils.isEmpty(search)) {
			query += " and (upper(nncf.descrizione) like UPPER('%"+ search + "%'))";
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




	public Integer countAllNonConformitaPratiche(long idAudit, String textSearch) {

		Integer count = null;
		String queryStr = "SELECT count(fase.DESCRIZIONE) "+
		" FROM  AU_M_NONCONF nncf, AU_M_VARCOMP varcomp, CRS_SOTTOPROCESSO fase "+
		" where nncf.ID_AUDIT= "+ idAudit +
		" and fase.ID_SOTTOPROCESSO=nncf.ID_FASE "+
		" and varcomp.ID_M_NONCONF=nncf.ID_M_NON_CONF ";

		if (!StringUtils.isEmpty(textSearch)) {
			queryStr += " and (" + "upper(nncf.DESCRIZIONE) like UPPER('%" + textSearch + "%'))";
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getListaNonConformitaAccessi(long idAudit,long idSSessione,String filtro, Integer pageNumber, Integer pageSize,
			int columnNameToOrder, String orderType, String search) {
		List<Object[]>l= new ArrayList<Object[]>();

		String query = "SELECT " +
		"mnc.ID_M_NON_CONF, " +
		"snc.ID_S_NONCONF, " + 
		" fase.ORDINAMENTO, fase.DESCRIZIONE, "+
		"mnc.DESCRIZIONE, " +
		"mnc.CODICE_NC, " +
		"convert(nvarchar(10), snc.DATA_INIZIO, 103) as data_inizio, " +
		"convert(nvarchar(10), snc.DATA_FINE, 103) as data_fine, " +
		"snc.NUM, " +
		"snc.VALORE_INCC " +
		"FROM AU_M_NONCONF mnc join AU_S_NONCONF snc on mnc.ID_M_NON_CONF = snc.ID_M_NONCONF " +
		" join CRS_SOTTOPROCESSO fase on mnc.ID_FASE  = fase.ID_SOTTOPROCESSO "+
		"WHERE snc.ID_S_SESSIONE = " + idSSessione + " AND ( mnc.DATA_FINE is null or mnc.DATA_FINE > getdate()) "; 
		if(!filtro.trim().equals("")){
			query +=" AND upper(fase.DESCRIZIONE) like UPPER('%"+filtro+"%')";	
		}


		if (!StringUtils.isEmpty(search)) {
			query += " and (upper(mnc.descrizione) like UPPER('%"+ search + "%'))";
		}

		query +=" ORDER BY "+(columnNameToOrder+2)+ " "+orderType ;
		if (pageSize != -1 ){ 
			query += " OFFSET "+pageNumber+" ROWS ";
			query +=" FETCH NEXT "+pageSize+" ROWS ONLY ";
		}

		try {
			Query querySel = em.createNativeQuery(query);
			l = querySel.getResultList();

		} catch (Throwable e) {
			e.printStackTrace();
		}
		return l;
	}

	@Override
	public int countTabNonConfAccess(long idAudit, long idSSessione,String filtro, String search) {
		Integer count = null;
		String query = "SELECT " + 
		"count(mnc.ID_M_NON_CONF) " +
		"FROM AU_M_NONCONF mnc join AU_S_NONCONF snc on mnc.ID_M_NON_CONF = snc.ID_M_NONCONF " +
		"join CRS_SOTTOPROCESSO fase on mnc.ID_FASE  = fase.ID_SOTTOPROCESSO "+
		"WHERE " +
		"snc.ID_S_SESSIONE = " + idSSessione + " AND ( mnc.DATA_FINE is null or mnc.DATA_FINE > getdate()) ";; 
		if(!filtro.trim().equals("")){
			query +=" AND upper(fase.DESCRIZIONE) like UPPER('%"+filtro+"%')";	
		}
		if (!StringUtils.isEmpty(search)) {
			query += " and (" + "upper(mnc.DESCRIZIONE) like UPPER('%" + search + "%'))";
		}

		try {
			Query querySel = em.createNativeQuery(query);
			count = (Integer) querySel.getSingleResult();
		} catch (Throwable e) {
			e.printStackTrace();
			log.error(e);
		}
		return count;
	}

	@Override
	public int countPraticheNC(long idNonConformita, long idSessione) {
		Integer count = null;
		String query = "SELECT COUNT(ID_S_PRATICA_VARCOMP) "+
		"FROM AU_S_PRATICA_VARCOMP "+
		"WHERE ID_NONCONF="+idNonConformita;


		try {
			Query querySel = em.createNativeQuery(query);
			count = (Integer) querySel.getSingleResult();
		} catch (Throwable e) {
			e.printStackTrace();
			log.error(e);
		}
		return count;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object[] getDettagliNonConformita(long idNonConfM, long idNonConfS) {

		List<Object[]>l= new ArrayList<Object[]>();

		String query = "SELECT " + 
		"mnc.ID_M_NON_CONF, "+
		"snc.ID_S_NONCONF, " +
		"mnc.descrizione, " +
		"mnc.CODICE_NC, " +
		"convert(nvarchar(10), snc.DATA_INIZIO, 103) as data_inizio, " +
		"convert(nvarchar(10), snc.DATA_FINE, 103) as data_fine, " +
		"snc.NUM, " +
		"snc.VALORE_INCC, " +
		"snc.PESO_NONCONF, " +
		"snc.VALORE_PESATO, " +
		"snc.MINIMO, " +
		"snc.MASSIMO, " +
		"snc.valore_min_attivita, " +
		"snc.valore_max_attivita " +
		"FROM " +
		"AU_M_NONCONF mnc join AU_S_NONCONF snc on mnc.ID_M_NON_CONF = snc.ID_M_NONCONF " +
		"WHERE " +
		"mnc.ID_M_NON_CONF = " + idNonConfM + " AND snc.ID_S_NONCONF = " + idNonConfS;


		try {
			Query querySel = em.createNativeQuery(query);
			l =querySel.getResultList();

		} catch (Throwable e) {
			e.printStackTrace();
		}
		return l.get(0);
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getTabRischiAccess(long idSSessione, Integer pageNumber, Integer pageSize, int columnNameToOrder, String orderType, String search) {

		List<Object[]>l= new ArrayList<Object[]>();
		String query = "SELECT " + 
		"mr.ID_M_RISCHIO, " +
		"sr.ID_S_RISCHIO, " +
		"mr.codice_rischio, " +
		"mr.DESCRIZIONE_RISCHIO, " +
		"sr.NUM, " +
		"sr.SU_TOT_PERC, " +
		"sr.SU_PS_PERC " +
		"FROM AU_M_RISCHIO mr join AU_S_RISCHIO sr on mr.ID_M_RISCHIO = sr.ID_M_RISCHIO " +
		"WHERE " +
		"sr.ID_S_SESSIONE = " + idSSessione;


		if (!StringUtils.isEmpty(search)) {
			query += " and (upper(mr.DESCRIZIONE_RISCHIO) like UPPER('%"+ search + "%'))";
		}

		query +=" ORDER BY "+(columnNameToOrder+2)+ " "+orderType ;
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
	public int countTabRischiAccess(long idSSessione, String search) {

		Integer count = null;
		String query = "SELECT " +
		"count(mr.ID_M_RISCHIO) "+
		"FROM AU_M_RISCHIO mr join AU_S_RISCHIO sr on mr.ID_M_RISCHIO = sr.ID_M_RISCHIO " +
		"WHERE " +
		"sr.ID_S_SESSIONE = " + idSSessione;

		if (!StringUtils.isEmpty(search)) {
			query += " and (" + "upper(mr.DESCRIZIONE_RISCHIO) like UPPER('%" + search + "%'))";
		}

		try {
			Query querySel = em.createNativeQuery(query);
			count = (Integer) querySel.getSingleResult();
		} catch (Throwable e) {
			e.printStackTrace();
			log.error(e);
		}
		return count;
	}


	@SuppressWarnings("unchecked")
	@Override
	public Object[] getDettagliRischi(long idRischioM, long idRischioS) {

		List<Object[]>l= new ArrayList<Object[]>();

		String query = "SELECT " +
		"mr.ID_M_RISCHIO, " +
		"sr.ID_S_RISCHIO, " +
		"mr.codice_rischio, " +
		"mr.DESCRIZIONE_RISCHIO, " +
		"sr.NUM, " +
		"sr.SU_TOT_PERC, " +
		"sr.SU_PS_PERC, " +
		"sr.PESO_RISCHIO, " +
		"sr.IMPORTO " +
		"FROM AU_M_RISCHIO mr join AU_S_RISCHIO sr on mr.ID_M_RISCHIO = sr.ID_M_RISCHIO " +
		"where sr.ID_M_RISCHIO = " + idRischioM + " AND sr.ID_S_RISCHIO = " + idRischioS;

		try {
			Query querySel = em.createNativeQuery(query);
			l =querySel.getResultList();
			return l.get(0);

		} catch (Throwable e) {
			e.printStackTrace();
			return null;
		}

	}



	@Override
	public List<Object[]> getTipologica(String tipo) {
		List<Object[]> list = new ArrayList<Object[]>();

		String queryStr = "select * from AU_TPL_TIPOLOGICHE WHERE TIPO='"+tipo+"'";	

		try {
			Query querySel = em.createNativeQuery(queryStr);
			list = querySel.getResultList();

		} catch (Throwable e) {
			e.printStackTrace();
			log.error(e);
		}
		return list;
	}

	public long getIdCambagnaByIdSSessione(long idSSessione){
		Long idCampanga;
		String queryStr = "select distinct(c.ID_CAMPAGNA) as idCampagna "; 
		queryStr += "from  AU_S_SESSIONE ss, AU_SESSIONI s , AU_CAMPAGNA c ";
		queryStr += "where ss.ID_SESSIONE = s.ID_SESSIONE ";
		queryStr += "and s.ID_CAMPAGNA = c.ID_CAMPAGNA ";
		queryStr += "and ss.ID_S_SESSIONE = " + idSSessione;
		Query querySel = em.createNativeQuery(queryStr,Long.class);
		idCampanga =(Long) querySel.getSingleResult();
		return idCampanga;
	}

	@Override
	public AuCampagna getCampagnaById(long idCampagna) {
		return em.find(AuCampagna.class, idCampagna);
	}

	@Override
	public AuAudit getAuditById(long idAudit) {
		return em.find(AuAudit.class, idAudit);
	}

	@Override
	public List<AuMRisepr> getEspressioRischioByAudit(long idAudit) {
		List<AuMRisepr> auMRisepr = null;
		try {
			auMRisepr = em.createNamedQuery(AuMRisepr.QUERY_ESPR_BY_IDAUDIT)
			.setParameter("idAudit", idAudit)
			.getResultList();
		} catch (NoResultException e) {
			log.warn("AuMRisepr NoResultException for idAudit: " + idAudit);
		}
		return auMRisepr;
	}

	@Override
	public List<AuMRischio> getRishioByAudit(long idAudit) {
		List<AuMRischio> auMRischio = null;
		try {
			auMRischio = em.createNamedQuery(AuMRischio.QUERY_RISCHIO_BY_IDAUDIT)
			.setParameter("idAudit", idAudit)
			.getResultList();
		} catch (NoResultException e) {
			log.warn("AuMRischio NoResultException for idAudit: " + idAudit);
		}
		return auMRischio;
	}



	@Transactional
	@Override
	public void deleteSPtaricaRisByIdSPratica(long idSPratica){
		em.createNativeQuery("DELETE FROM AU_S_PRATICA_RIS WHERE ID_S_PRATICA = " + idSPratica)
		.executeUpdate();
	}

	@Transactional
	@Override
	public void deleteSPtaricaVarcompByIdSPratica(long idSPratica){
		em.createNativeQuery("DELETE FROM AU_S_PRATICA_VARCOMP WHERE ID_S_PRATICA = " + idSPratica)
		.executeUpdate();
	}

	@Override
	public List<AuMNonConf> getMNonConfByAudit(long idAudit) {
		List<AuMNonConf> auMRisepr = null;
		try {
			auMRisepr = em.createNamedQuery(AuMNonConf.QUERY_NONCONF_BY_IDAUDIT)
			.setParameter("idAudit", idAudit)
			.getResultList();
		} catch (NoResultException e) {
			log.warn("AuMNonConf NoResultException for idAudit: " + idAudit);
		}
		return auMRisepr;
	}

	@Transactional
	public void salvaSPraticaCalIndLog(AuSPraCalIndLog entity) {
		em.persist(entity);
	}

	@Override
	public List<AuMVarcomp> getAuMVarcomp() {
		return em.createNativeQuery("SELECT * FROM AU_M_VARCOMP",AuMVarcomp.class).getResultList();
	}

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

	@Override
	public List<Object[]> getVarCompNonConfAccessi(long idNonConfM, long idNonConfS,
			int pageNumber, int pageSize, int columnNameToOrder, String orderType,
			String search) {
		List<Object[]>l= new ArrayList<Object[]>();
		String query = "SELECT vc.ID_M_COMP, vc.DESCRIZIONE, svp.NUM, svp.PERC_SU_PS, svp.PERC_PESATA, isnc.COLORE "+
		"FROM AU_S_VARCOMP svp, AU_M_VARCOMP vc, AU_TPL_ISNC isnc "+
		"WHERE svp.ID_M_VARCOMP = vc.ID_M_COMP and svp.ID_M_NONCONF= vc.ID_M_NONCONF "+
		"AND vc.ID_M_NONCONF="+idNonConfM +" and svp.ID_S_NONCONF="+idNonConfS +"  AND vc.PESO_VC  = isnc.ID_TPL_ISNC";


		if (!StringUtils.isEmpty(search)) {
			query += " and (upper(vc.DESCRIZIONE) like UPPER('%"+ search + "%'))";
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
	public int countVarCompNonConfAccessi(long idNonConfM, long idNonConfS,
			String textSearch) {
		int count=0;
		String query = "SELECT count(vc.ID_M_COMP) "+
		"FROM AU_S_VARCOMP svp, AU_M_VARCOMP vc, AU_TPL_ISNC isnc "+
		"WHERE svp.ID_M_VARCOMP = vc.ID_M_COMP and svp.ID_M_NONCONF= vc.ID_M_NONCONF "+
		"AND vc.ID_M_NONCONF="+idNonConfM +" and svp.ID_S_NONCONF="+idNonConfS +"  AND vc.PESO_VC  = isnc.ID_TPL_ISNC";
		if (!StringUtils.isEmpty(textSearch)) {
			query += " and (" + "upper(vc.DESCRIZIONE) like UPPER('%" + textSearch + "%'))";
		}

		try {
			Query querySel = em.createNativeQuery(query);
			count = (Integer) querySel.getSingleResult();
		} catch (Throwable e) {
			e.printStackTrace();
			log.error(e);
		}
		return count;

	}
	@Override
	public List<Object[]> getStatoEsprRischioTable(long idMRischio,long idSsessione, int pageNumber, int pageSize,
			int columnNameToOrder, String orderType, String search) {


		List<Object[]>l= new ArrayList<Object[]>();
		String query = "SELECT ser.ID_S_ESPRESSIONE_RISCHIO, mer.DESCRIZIONE, ser.NUM, ser.SU_TOT, ser.SU_PS, ser.IMPORTO "+
		" FROM AU_M_RISESPR as mer "+
		"join AU_S_RISESPR as ser on ser.ID_M_RISESPR = mer.ID_M_RISESPR  "+
		"join AU_S_RISCHIO as sr on sr.ID_S_RISCHIO = ser.ID_S_RISCHIO "+
		"where sr.ID_S_SESSIONE ="+ idSsessione +" and sr.ID_M_RISCHIO ="+idMRischio;



		if (!StringUtils.isEmpty(search)) {
			query += " and (upper(mer.DESCRIZIONE) like UPPER('%"+ search + "%'))";
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
	public int countStatoEsprRischioTable(long idMRischio, long idSsessione,
			String getsSearch) {
		int count=0;
		List<Object[]>l= new ArrayList<Object[]>();
		String query = "SELECT count(ser.ID_S_ESPRESSIONE_RISCHIO) "+
		" FROM AU_M_RISESPR as mer "+
		"join AU_S_RISESPR as ser on ser.ID_M_RISESPR = mer.ID_M_RISESPR  "+
		"join AU_S_RISCHIO as sr on sr.ID_S_RISCHIO = ser.ID_S_RISCHIO "+
		"where sr.ID_S_SESSIONE ="+ idSsessione +" and sr.ID_M_RISCHIO ="+idMRischio;



		if (!StringUtils.isEmpty(getsSearch)) {
			query += " and (upper(mer.DESCRIZIONE) like UPPER('%"+ getsSearch + "%'))";
		}

		try {
			Query querySel = em.createNativeQuery(query);
			count = (Integer) querySel.getSingleResult();
		} catch (Throwable e) {
			e.printStackTrace();
			log.error(e);
		}
		return count;
	}

	@Override
	public List<Object[]> getListFase(String nameGrafico) {
		HttpServletRequest request = ServletActionContext.getRequest();
		DatiUtente user = (DatiUtente) request.getSession().getAttribute("DatiUtente");
		String nomeTabella= getNomeTabella(nameGrafico);

		List<Object[]>l= new ArrayList<Object[]>();
		String query="";
		if(nomeTabella.equals("ATPO_FASE_POST_PERITALE_A")){
			user.setPostPeritaleType("A");
			 query=("select pp.ID_POST_PERITALE, pp.DATA_DEPOSITO_DEC_OMOLOGA, pp.DATA_NOTIFICA_DEC_OMOLOGA,pp.DATA_PROT_DEC_OMOLOGA_NOTIF, pp.INT_TEMP_NOTIF_OMG_PROT_OMG, "+
						  " pp.COD_CHIUSURA_CORRETTO, pp.COD_CHIUSURA_INSERITO, pp.SPESE_PAGATE, pp.SPESE_DECR_OMOLOGA, pp.CORRISP_DECR_OMG_E_CTU_DEF, pp.COD_PAGAMENTO_SPESE_LEGALI, "+
						  "pp.COD_PAGAMENTO_SPESE_LEGALI_CORRETTO,pp.REG_DATI_PRATICA, pp.DATA_TRASMISS_DECR_LPS, pp.OMOLOGA_ALLEGATA, pp.INT_TEMP_NOTIF_DECR_OMG_A_DECR_LPS, "+
						  "pp.DATA_DEPOSITO_DISSENSO, pp.INT_TEMP_DEPOSITO_DISS_TERMINE_DISS, pp.COM_DEP_DISS_UFF_LEGALE, pp.DATA_DEP_RIC_PRIMO_G, pp.DATA_DEF_PRATICA, pp.TIPO_DISSENSO, "+
						  " pp.ID_FASE_DATI, pp.FASE_PRONTA, pp.COM_DEP_DISS_UL "+
						  "from ATPO_FASE_POST_PERITALE pp, ATPO_FASE_DATI fd  where pp.FASE_PRONTA='S' "+
						  "and  pp.id_fase_dati = fd.id_fase_dati "+
						"	and fd.GIUDIZIO != 4 AND fd.GIUDIZIO IS NOT NULL ");
		}
		else if(nomeTabella.equals("ATPO_FASE_POST_PERITALE_B")){
			user.setPostPeritaleType("B");
			query=("select pp.ID_POST_PERITALE, pp.DATA_DEPOSITO_DEC_OMOLOGA, pp.DATA_NOTIFICA_DEC_OMOLOGA,pp.DATA_PROT_DEC_OMOLOGA_NOTIF, pp.INT_TEMP_NOTIF_OMG_PROT_OMG, "+
					  " pp.COD_CHIUSURA_CORRETTO, pp.COD_CHIUSURA_INSERITO, pp.SPESE_PAGATE, pp.SPESE_DECR_OMOLOGA, pp.CORRISP_DECR_OMG_E_CTU_DEF, pp.COD_PAGAMENTO_SPESE_LEGALI, "+
					  "pp.COD_PAGAMENTO_SPESE_LEGALI_CORRETTO,pp.REG_DATI_PRATICA, pp.DATA_TRASMISS_DECR_LPS, pp.OMOLOGA_ALLEGATA, pp.INT_TEMP_NOTIF_DECR_OMG_A_DECR_LPS, "+
					  "pp.DATA_DEPOSITO_DISSENSO, pp.INT_TEMP_DEPOSITO_DISS_TERMINE_DISS, pp.COM_DEP_DISS_UFF_LEGALE, pp.DATA_DEP_RIC_PRIMO_G, pp.DATA_DEF_PRATICA, pp.TIPO_DISSENSO, "+
					  " pp.ID_FASE_DATI, pp.FASE_PRONTA, pp.COM_DEP_DISS_UL "+
					  "from ATPO_FASE_POST_PERITALE pp, ATPO_FASE_DATI fd  where pp.FASE_PRONTA='S' "+
					  "and  pp.id_fase_dati = fd.id_fase_dati "+
					"	and fd.GIUDIZIO = 4 AND fd.GIUDIZIO IS NOT NULL ");
		}
		else if(nomeTabella.equals("ATPO_FASE_ESECUZIONE_PROVVEDIMENTI")){
			user.setEsProvvedimentiType("A");
			query=(" select ep.ID_ESECUZIONE_PROVVEDIMENTI,ep.PRES_DECR_OMG_NEL_FASC, ep.DATA_DREC_LIQ_CTU, "+
						  "ep.DATA_PRESA_IN_CARICO_DECR_OMG_LPS,ep.INT_T_DEP_DECR_OMG_DATA_REC_LEQUIDAZIONE, "+
						  "ep.DATA_LIQ_PREST_LPS,ep.IMPORTO_MENSILE_RATA,ep.DATA_REC_DATI_LIQ, ep.GG_DA_NOTIF_DECR_OMG_A_LIQ_PREST, "+
						  "ep.GG_DA_TRASM_DECR_LPS_A_DECR_OMG_DA_LPS, ep.INTERESSI_LEGALI_PAGATI, "+
						  "ep.INTERESSI_LEGALI_DOVUTI, ep.DATA_DEC_CALCOLO_INT_LEGALI, ep.DATA_DEC_PREST_INSERITA, "+
						  "ep.DATA_CORR_DEC_PRESTAZIONE,ep.PRESTAZIONE_CORRISP, "+
						  "ep.IMPORTO_RATA_DOVUTA, ep.CONDANNA_A_PAGAMENTO_CTU_ATPO, ep.DATA_FATTURA, ep.DATA_LIQ_CTU_ATPO, "+
						  "ep.INT_TEMP_DA_FATT_ELETTR_A_PAG_CTU_ATPO, ep.ANTICIPATE_SPESE_CTU, "+
						  "ep.IMPORTO_SPESE_CTU_PAGATE, ep.IMPORTO_SPESE_CTU_DOVUTE,ep.DATA_LETT_INV_PAG_SPESE_LEGAL, "+
						  "ep.DATA_LETTERA_RECUPERO_SPESE_CTU,ep.CONDANNA_PAG_SPESE_LEGALI, "+
						  "ep.SOGG_RICH_PAGAMENTO,ep.DATA_ARRIVO_NOTULA,ep.DATA_PAG_SPESE_LEGALI_AVV_CPARTE, "+
						  "ep.INT_T_DEP_DECR_OMG_A_PAG_SPESE_LEGALI_CPARTE, ep.PRES_DECR_SENT_MANC_PAG_PREST, "+
						  "ep.COSTO_GIUDIZIO_MANC_PAG_PREST, ep.CONDANNA_PAG_CTU_1G, ep.VER_PAG_CTU_EFF, "+
						  " ep.NO_PRECETTO, ep.SPESE_LEGALI_FLAG_PREC, ep.DATA_SPESE_LEGALI_PREC, ep.DATA_COM_PRE_SL, "+
						  " ep.COSTO_PRE_SL, ep.SPESE_CTU_FLAG_PREC, ep.DATA_SPESE_CTU_PREC, ep.DATA_COM_PRE_SCTU, "+
						  " ep.COSTO_PRE_SCTU, ep.PRESTAZIONE_FLAG_PREC, ep.DATA_PRESTAZIONE, ep.DATA_COM_PRE_PREST, "+
						  " ep.COSTO_PRE_PREST, " +
						  " ep.NO_PIGNORAMENTO, ep.SPESE_LEGALI_FLAG_PIGN, ep.DATA_SPESE_LEGALI_PIGN, "+
						  " ep.COSTO_PIGN_SL, ep.SPESE_CTU_FLAG_PIGN, ep.DATA_SPESE_CTU_PIGN, ep.COSTO_PIGN_SCTU, "+
						  " ep.PRESTAZIONE_FLAG_PIGN, ep.DATA_PIGNORAMENTO_PRESTAZIONE, ep.COSTO_PIGN_PREST, ep.DATA_LIMITE_CALCOLO_IMPATTO "+
						  " from ATPO_FASE_ESECUZIONE_PROVVEDIMENTI ep , ATPO_FASE_DATI fd "+
						  " where ep.id_fase_dati = fd.id_fase_dati	and fd.GIUDIZIO != 4 AND fd.GIUDIZIO IS NOT NULL ");
			
			
		}else if(nomeTabella.equals("ATPO_FASE_ESECUZIONE_PROVVEDIMENTI_B")){
			user.setEsProvvedimentiType("B");
			query=(" select ep.ID_ESECUZIONE_PROVVEDIMENTI,ep.PRES_DECR_OMG_NEL_FASC, ep.DATA_DREC_LIQ_CTU, "+
					  "ep.DATA_PRESA_IN_CARICO_DECR_OMG_LPS,ep.INT_T_DEP_DECR_OMG_DATA_REC_LEQUIDAZIONE, "+
					  "ep.DATA_LIQ_PREST_LPS,ep.IMPORTO_MENSILE_RATA,ep.DATA_REC_DATI_LIQ, ep.GG_DA_NOTIF_DECR_OMG_A_LIQ_PREST, "+
					  "ep.GG_DA_TRASM_DECR_LPS_A_DECR_OMG_DA_LPS, ep.INTERESSI_LEGALI_PAGATI, "+
					  "ep.INTERESSI_LEGALI_DOVUTI, ep.DATA_DEC_CALCOLO_INT_LEGALI, ep.DATA_DEC_PREST_INSERITA, "+
					  "ep.DATA_CORR_DEC_PRESTAZIONE,ep.PRESTAZIONE_CORRISP, "+
					  "ep.IMPORTO_RATA_DOVUTA, ep.CONDANNA_A_PAGAMENTO_CTU_ATPO, ep.DATA_FATTURA, ep.DATA_LIQ_CTU_ATPO, "+
					  "ep.INT_TEMP_DA_FATT_ELETTR_A_PAG_CTU_ATPO, ep.ANTICIPATE_SPESE_CTU, "+
					  "ep.IMPORTO_SPESE_CTU_PAGATE, ep.IMPORTO_SPESE_CTU_DOVUTE,ep.DATA_LETT_INV_PAG_SPESE_LEGAL, "+
					  "ep.DATA_LETTERA_RECUPERO_SPESE_CTU,ep.CONDANNA_PAG_SPESE_LEGALI, "+
					  "ep.SOGG_RICH_PAGAMENTO,ep.DATA_ARRIVO_NOTULA,ep.DATA_PAG_SPESE_LEGALI_AVV_CPARTE, "+
					  "ep.INT_T_DEP_DECR_OMG_A_PAG_SPESE_LEGALI_CPARTE, ep.PRES_DECR_SENT_MANC_PAG_PREST, "+
					  "ep.COSTO_GIUDIZIO_MANC_PAG_PREST, ep.CONDANNA_PAG_CTU_1G, ep.VER_PAG_CTU_EFF, "+
					  " ep.NO_PRECETTO, ep.SPESE_LEGALI_FLAG_PREC, ep.DATA_SPESE_LEGALI_PREC, ep.DATA_COM_PRE_SL, "+
					  " ep.COSTO_PRE_SL, ep.SPESE_CTU_FLAG_PREC, ep.DATA_SPESE_CTU_PREC, ep.DATA_COM_PRE_SCTU, "+
					  " ep.COSTO_PRE_SCTU, ep.PRESTAZIONE_FLAG_PREC, ep.DATA_PRESTAZIONE, ep.DATA_COM_PRE_PREST, "+
					  " ep.COSTO_PRE_PREST, " +
					  " ep.NO_PIGNORAMENTO, ep.SPESE_LEGALI_FLAG_PIGN, ep.DATA_SPESE_LEGALI_PIGN, "+
					  " ep.COSTO_PIGN_SL, ep.SPESE_CTU_FLAG_PIGN, ep.DATA_SPESE_CTU_PIGN, ep.COSTO_PIGN_SCTU, "+
					  " ep.PRESTAZIONE_FLAG_PIGN, ep.DATA_PIGNORAMENTO_PRESTAZIONE, ep.COSTO_PIGN_PREST, ep.DATA_LIMITE_CALCOLO_IMPATTO "+
					  " from ATPO_FASE_ESECUZIONE_PROVVEDIMENTI ep , ATPO_FASE_DATI fd "+
					  " where ep.id_fase_dati = fd.id_fase_dati	and fd.GIUDIZIO = 4 AND fd.GIUDIZIO IS NOT NULL ");
		
		}else if(nomeTabella.equals("ATPO_DETTAGLIO_FASCICOLO")){
			
			 query = "select  df.ID_RIEPILOGO_FASCICOLO, f.FASCICOLO_ELETTRONICO, df.CODIFICA  "+
						"  from ATPO_DETTAGLIO_FASCICOLO df, ATPO_FASE_RIEPILOGO_FASCICOLO f , ATPO_FASE_DATI fd "+
						"  where f.id_fase_dati = fd.id_fase_dati "+
						"  and  df.ID_RIEPILOGO_FASCICOLO=f.ID_RIEPILOGO_FASCICOLO order by df.ID_RIEPILOGO_FASCICOLO";
		}
		else{
		 query = "SELECT f.* from "+ nomeTabella +" f where f.fase_pronta='S'";
		}
		try {
			Query querySel = em.createNativeQuery(query);
			l =querySel.getResultList();

		} catch (Throwable e) {
			e.printStackTrace();
		}
		return l;
	}

	private String getNomeTabella(String nameGrafico) {
		
		String nomeTabella="";
		
		if(nameGrafico.equalsIgnoreCase("acquisizioneistanza") ){
			nomeTabella="ATPO_FASE_ACQUISIZIONE_ISTANZA";
		}else if(nameGrafico.equalsIgnoreCase("giudizio")){
			nomeTabella="ATPO_FASE_AUTOTUTELA_RESISTENZA_GIUDIZIO";
		}else if(nameGrafico.equalsIgnoreCase("istruttoria") ){
			nomeTabella="ATPO_FASE_GESTIONE_ISTRUTTORIA";
		}else if(nameGrafico.equalsIgnoreCase("peritale")){
			nomeTabella="ATPO_FASE_PERITALE";
		}else if(nameGrafico.equalsIgnoreCase("pperitaleA") ){
			nomeTabella="ATPO_FASE_POST_PERITALE_A";
		}else if(nameGrafico.equalsIgnoreCase("pperitaleB") ){
			nomeTabella="ATPO_FASE_POST_PERITALE_B";
		}else if(nameGrafico.equalsIgnoreCase("provvedimenti")){
			nomeTabella="ATPO_FASE_ESECUZIONE_PROVVEDIMENTI";
		}else if(nameGrafico.equalsIgnoreCase("provvedimentiB")){
			nomeTabella="ATPO_FASE_ESECUZIONE_PROVVEDIMENTI_B";
		}else if(nameGrafico.equalsIgnoreCase("fascicolo")){
			nomeTabella="ATPO_DETTAGLIO_FASCICOLO";
		}
		return nomeTabella;
	}




}
