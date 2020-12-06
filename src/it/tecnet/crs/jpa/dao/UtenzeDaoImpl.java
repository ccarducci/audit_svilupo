package it.tecnet.crs.jpa.dao;

import it.tecnet.crs.jpa.model.AuAudit;
import it.tecnet.crs.jpa.model.CrsAssUtenteRuolo;
import it.tecnet.crs.jpa.model.CrsRuolo;
import it.tecnet.crs.jpa.model.CrsUtenteAdv;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

public class UtenzeDaoImpl  implements UtenzeDao {

	protected static Logger log = Logger.getLogger(UtenzeDaoImpl.class);

	public enum TipoUtente{Admin, User};
	@PersistenceContext()
	private EntityManager em;
	
	public UtenzeDaoImpl(){
		
	}

	@Override
	public CrsUtenteAdv getUtenteByUsername(String username) {
		String qry="SELECT * FROM CRS_UTENTE_ADV WHERE USERNAME = '"+username+"'";
		Query query=em.createNativeQuery(qry,CrsUtenteAdv.class);
		CrsUtenteAdv result=null;
		try{
			result=(CrsUtenteAdv)query.getSingleResult();
		}catch(Exception e){
			log.error("impossibile recuperare il priflo - "+e.getMessage());
			e.printStackTrace();
		}
		return  result;
	}
	@Override
	public Long getDirigenteByUserId(long userId) {
		String qry=" select distinct u.id_utente from CRS_UTENTE_ADV u, CRS_ASS_UTENTE_RUOLO aur, CRS_ASS_CODICE_DIRIGENTE acd, CRS_RUOLO r "+
		" where aur.ID_UTENTE="+userId+
		" and aur.ID_RUOLO = r.ID_RUOLO "+
		" and r.CODICE= acd.CODICE "+
		" and u.ID_UTENTE=acd.ID_DIRIGENTE ";
		Query query=em.createNativeQuery(qry);
		Long result=null;
		try{
			result=(Long)query.getSingleResult();
		}catch(Exception e){
			log.error("impossibile recuperare il priflo - "+e.getMessage());
			e.printStackTrace();
		}
		return  result;
	}

	@Override
	public List<CrsRuolo> getValidRolesList() {
		String qryStr="SELECT * FROM CRS_RUOLO";
		Query query=em.createNativeQuery(qryStr, CrsRuolo.class);
		List<CrsRuolo> listaruoli=new ArrayList<CrsRuolo>();
		try{
			 listaruoli=(List<CrsRuolo>)query.getResultList();			
		}catch (Exception e) {
			log.error("Impossibile recuperare i ruoli "+e.getMessage());
			e.printStackTrace();
		}
		return listaruoli;
	}


	@Override
	public List<String> getRuoliAssociatiUtente(long id) {
		String qryStrin="SELECT r.CODICE FROM CRS_RUOLO r INNER JOIN CRS_ASS_UTENTE_RUOLO ass ON ass.ID_RUOLO=r.ID_RUOLO"+
		" WHERE ID_UTENTE= "+id;
		Query query=em.createNativeQuery(qryStrin, String.class);
		List<String> result=new ArrayList<String>();
		try{
			result=query.getResultList();
		}catch (Exception e) {
			log.error("impossibile recuperare ruoli associati all'utente - "+e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean checkIfProfileIsAttivo(String username) {
		String qryStr="SELECT COUNT (ID_UTENTE) FROM CRS_UTENTE_ADV WHERE USERNAME= "+username+
		" and ATTIVO = 'A'";
		Query query=em.createNativeQuery(qryStr,Integer.class);
		
		int result=(Integer)query.getSingleResult();
		if(result>0){
			return true;
		}else{			
			return false;
		}
	}
	
	
	@Transactional
	public <T> T salva(T obj) {
		try {
			return em.merge(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Transactional
	public <T> T salvaRestituisci(T obj) {
		T result=null;
		try {
			result=em.merge(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public <T> T trova(Class<T> clss, Object primaryKey) {
		T result=null;
		try{
			result=(T)em.find(clss, primaryKey);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Integer countAudit(String textSearch) {
		Integer count = null;
		String query = "select count(a.ID_UTENTE) from CRS_UTENTE_ADV a";
		if (!StringUtils.isEmpty(textSearch)) {
			query += " WHERE " + "upper(COGNOME) like UPPER('%"+ textSearch + "%') " + "";
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

	@Override
	public List<Object[]> getListUtenti(Integer pageNumber, Integer pageSize,
			int columnNameToOrder, String orderType, String search) {
		List<Object[]> auditList = new ArrayList<Object[]>();
		String queryStr = "SELECT [ID_UTENTE]      ,[NOME]      ,[COGNOME]      ,[USERNAME]      ,[EMAIL]      ,[ATTIVO]      ,[DATA_ULTIMO_ACCESSO]  FROM [CRS_UTENTE_ADV]";

		if (!StringUtils.isEmpty(search)) {
			queryStr += " where (" + "upper(COGNOME) like UPPER('%"+ search + "%') " + ")";
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

	@Transactional
	public void deleteUtente(long idUtente) {
		CrsUtenteAdv utente = em.find(CrsUtenteAdv.class, idUtente);
		cancellaRuoliAssociatiUtente(idUtente);
		em.remove(utente);
		
	}

	@Transactional
	public void saveRuoliUtente(long idUtente, List<String> ruoli) {
		cancellaRuoliAssociatiUtente(idUtente);
		for(String i : ruoli){
//			auditService.addFaseToAudit(Long.parseLong(i), idAudit);
			saveRuoloUtente(idUtente, Integer.parseInt(i));
		}
		
		
	}
	@Transactional
	public void saveRuoloUtente(long idUtente, long idRuolo) {
		CrsAssUtenteRuolo assUtenteRuolo = new CrsAssUtenteRuolo();
		assUtenteRuolo.setIdRuolo(idRuolo);
		assUtenteRuolo.setIdUtente(idUtente);
		em.merge(assUtenteRuolo);
	}
	@Transactional
	public void cancellaRuoliAssociatiUtente(long id) {
		
		
         
		String qryStrin="DELETE FROM  CRS_ASS_UTENTE_RUOLO  WHERE ID_UTENTE = "+id;
		Query query=em.createNativeQuery(qryStrin);
		try{
			query.executeUpdate();
		}catch (Exception e) {
			log.error("impossibile recuperare ruoli associati all'utente - "+e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public List getRuoliUtente(long idUtente) {
		String qryStrin="SELECT r.ID_RUOLO FROM CRS_RUOLO r INNER JOIN CRS_ASS_UTENTE_RUOLO ass ON ass.ID_RUOLO=r.ID_RUOLO"+
		" WHERE ID_UTENTE= "+idUtente;
		Query query=em.createNativeQuery(qryStrin, String.class);
		List<String> result=new ArrayList<String>();
		try{
			result=query.getResultList();
		}catch (Exception e) {
			log.error("impossibile recuperare ruoli associati all'utente - "+e.getMessage());
			e.printStackTrace();
		}
		return result;
	}




	

	
	
	
}
