package it.tecnet.crs.jpa.dao;

import java.util.List;
import it.tecnet.crs.jpa.model.AuQuestionario;
import it.tecnet.crs.util.ApplicationUtil;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

public class AuditQuestionarioDaoImpl implements AuditQuestionarioDao {

	protected static Logger log = Logger.getLogger(AuditQuestionarioDaoImpl.class);

	@PersistenceContext()
	private EntityManager em;

	public AuditQuestionarioDaoImpl() {

	}

	/*
	 * QUESTIONARIO
	 */

	public Integer countAllQuestionariSessione(long idSessione) {
		Integer count = null;
		String queryStr = "SELECT count (Q.ID_QUESTIONARIO)"
				+ "  FROM AU_QUESTIONARIO Q"
				+ "  INNER JOIN AU_M_DOMANDA D ON D.ID_QUESTIONARIO=Q.ID_QUESTIONARIO"
				+ "  INNER JOIN AU_M_RISPOSTA R ON R.ID_DOMANDA=D.ID_DOMANDA"
				+ "  INNER JOIN AU_CAMPAGNA C ON C.ID_AUDIT =Q.ID_AUDIT"
				+ "  INNER JOIN AU_SESSIONI S ON S.ID_CAMPAGNA=C.ID_CAMPAGNA"
				+ "  WHERE S.ID_SESSIONE= " + idSessione;

		try {
			Query querySel = em.createNativeQuery(queryStr);
			count = (Integer) querySel.getSingleResult();

		} catch (Exception e) {
			log
					.error("Errore durante l'esecuzione del metodo countAllQuestionariSessione() della classe AuditQuestionarioDaoImpl.java");
		}
		log
				.info("Metodo countAllQuestionariSessione() della classe AuditQuestionarioDaoImpl.java eseguito ");

		return count == null ? 0 : count;
	}

	public List<AuQuestionario> getListaQuestionariSessione(long idSessione) {
		List<AuQuestionario> questionarioList = null;

		String queryStr = "SELECT Q.ID_QUESTIONARIO, Q.ID_AUDIT, D.ID_DOMANDA, D.DESCRIZIONE AS DESCDOMANDA,"
				+ " R.ID_RISPOSTA,	R.DESCRIZIONE AS DESCRISPOSTA, PUNTEGGIO, C.ID_CAMPAGNA,C.NOME AS NOMECAMPAGNA,"
				+ " C.DATA_INIZIO AS INIZIOCAMPAGNA,  C.STATO AS STATOCAMPAGNA, S.ID_SESSIONE,"
				+ " S.SEDE SEDESESSIONE,	S.TIPO AS TIPOSESSIONE,S.DATA_INIZIO AS INIZIOSESSIONE,	S.DATA_FINE AS FINESESSIONE,"
				+ " S.STATO AS STATOSESSIONE, S.DIRIGENTE,S.AUDITORS,C.DATA_FINE AS FINECAMPAGNA, isnull(RS.ID_RISPOSTA,0) "
				+ "    FROM AU_QUESTIONARIO Q"
				+ "    INNER JOIN AU_DOMANDA D ON D.ID_QUESTIONARIO=Q.ID_QUESTIONARIO"
				+ "    INNER JOIN AU_RISPOSTA R ON R.ID_DOMANDA=D.ID_DOMANDA"
				+ "    INNER JOIN AU_CAMPAGNA C ON C.ID_AUDIT =Q.ID_AUDIT"
				+ "    INNER JOIN AU_SESSIONI S ON S.ID_CAMPAGNA=C.ID_CAMPAGNA"
				+ "    LEFT OUTER join AU_RISPOSTE_SESSIONE RS on RS.ID_RISPOSTA=R.ID_RISPOSTA"
				+ "    WHERE S.ID_SESSIONE="
				+ idSessione
				+ "    ORDER BY ID_DOMANDA ASC";

		try {
			Query querySel = em.createNativeQuery(queryStr);
			questionarioList = (List<AuQuestionario>) querySel.getResultList();
		} catch (Exception e) {
			log.error("Errore durante l'esecuzione del metodo getListaQuestionariSessione() della classe AuditQuestionarioDaoImpl.java");
		}
		log.info("Metodo getListaQuestionariSessione() della classe AuditQuestionarioDaoImpl.java eseguito ");

		return questionarioList;
	}

	public AuQuestionario getQuestionarioBySessione(Long idSessione){
		AuQuestionario questionario = null;

		String queryStr = "select q.* from AU_QUESTIONARIO q, AU_SESSIONI ss, AU_CAMPAGNA cm, AU_AUDIT a "+
		" where ss.ID_SESSIONE="+ idSessione +
		" and cm.ID_CAMPAGNA=ss.ID_CAMPAGNA "+
		" and q.id_audit = a.id_audit" +
		" and a.ID_AUDIT= cm.ID_AUDIT";

		try {
			Query querySel = em.createNativeQuery(queryStr,AuQuestionario.class);
			questionario = (AuQuestionario) querySel.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Errore durante l'esecuzione del metodo getListaQuestionariSessione() della classe AuditQuestionarioDaoImpl.java");
		}
		log.info("Metodo getListaQuestionariSessione() della classe AuditQuestionarioDaoImpl.java eseguito ");

		return questionario;
	}
	
	@Transactional
	public void salvaRispostaQuestionariSessione(long idSessione,
			long idRisposta, long idDomanda) {
		try {

			String queryStr = "delete from Au_Risposte_Sessione where id_Domanda= "
					+ idDomanda;
			Query querySel = em.createNativeQuery(queryStr);
			querySel.executeUpdate();
			queryStr = "insert into Au_Risposte_Sessione (id_risposta,id_sessione,id_domanda) values ("
					+ idRisposta + "," + idSessione + "," + idDomanda + ")";
			querySel = em.createNativeQuery(queryStr);
			querySel.executeUpdate();

		} catch (Exception e) {
			log
					.error("Errore durante l'esecuzione del metodo salvaRispostaQuestionariSessione() della classe AuditQuestionarioDaoImpl.java");
			e.printStackTrace();
		}
		log
				.info("Metodo salvaRispostaQuestionariSessione() della classe AuditQuestionarioDaoImpl.java eseguito ");

	} 

	/*	ERA GIA COMMENTATA
	 * public Integer calcolaPunteggio(long idQuestionario, long idSessione){
	 * String queryStr = "SELECT sum(punteggio)" + "  FROM AU_QUESTIONARIO Q" +
	 * "	INNER JOIN AU_DOMANDA D ON D.ID_QUESTIONARIO=Q.ID_QUESTIONARIO" +
	 * "	INNER JOIN AU_RISPOSTA R ON R.ID_DOMANDA=D.ID_DOMANDA " +
	 * "	INNER JOIN AU_CAMPAGNA C ON C.ID_AUDIT =Q.ID_AUDIT" +
	 * "	INNER JOIN AU_SESSIONI S ON S.ID_CAMPAGNA=C.ID_CAMPAGNA  " +
	 * "	INNER JOIN AU_RISPOSTE_SESSIONE RS on RS.ID_RISPOSTA=R.ID_RISPOSTA" +
	 * "	WHERE S.ID_SESSIONE = " + idSessione + "	AND Q.ID_QUESTIONARIO = "
	 * +idQuestionario ;
	 * 
	 * 
	 * Query querySel = em.createNativeQuery(queryStr);
	 * 
	 * Integer count = (Integer)querySel.getSingleResult(); return count == null
	 * ? 0 : count; }
	 */

	// calcola punteggio risposte questionario
	/*public Integer calcolaPunteggio(long idSessione) {
		Integer count = null;
		String queryStr = "select sum(VALORE_RISPOSTA) from AU_M_RISPOSTA r "
				+ " inner join AU_RISPOSTE_SESSIONE rs "
				+ " ON r.ID_RISPOSTA = rs.ID_RISPOSTA "
				+ " AND r.ID_DOMANDA = rs.ID_DOMANDA"
				+ " where rs.ID_SESSIONE = " + idSessione;

		try {
			Query querySel = em.createNativeQuery(queryStr);

			count = (Integer) querySel.getSingleResult();
		} catch (Exception e) {
			log
					.error("Errore durante l'esecuzione del metodo calcolaPunteggio() della classe AuditQuestionarioDaoImpl.java");
			e.printStackTrace();
		}
		log
				.info("Metodo calcolaPunteggio() della classe AuditQuestionarioDaoImpl.java eseguito ");
		return count == null ? 0 : count;
	}*/

}
