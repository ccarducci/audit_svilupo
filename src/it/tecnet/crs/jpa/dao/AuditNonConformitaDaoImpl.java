package it.tecnet.crs.jpa.dao;

import it.tecnet.crs.jpa.model.AuMediaFase;
import it.tecnet.crs.jpa.model.AuNonConformita;
import it.tecnet.crs.jpa.model.AuNonConformitaVerbale;
import it.tecnet.crs.util.ApplicationUtil;
import it.tecnet.crs.web.dto.NonConformitaVerbaleDto;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

public class AuditNonConformitaDaoImpl implements AuditNonConformitaDao {

	protected static Logger log = Logger.getLogger(AuditNonConformitaDaoImpl.class);

	@PersistenceContext()
	private EntityManager em;

	public AuditNonConformitaDaoImpl() {

	}

	public List<AuNonConformita> getListaNonConformita(Long idAudit, Long idFase) {
		List<AuNonConformita> nonConformitaList = null;
		String queryStr = "select ID_NON_CONFORMITA,DESCRIZIONE,SOGLIA1,SOGLIA2,"
			+ "SOGLIA3,SOGLIA4,SOGLIA5,SOGLIA6,RISCHIO,RISCHIO_ECONOMICO"
			+ " from AU_NON_CONFORMITA WHERE STATO <> 'D' AND ID_AUDIT="+idAudit
			+ " and ID_FASE="+idFase;
		try {
			Query querySel = em.createNativeQuery(queryStr,
					AuNonConformita.class);
			nonConformitaList = querySel.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Errore durante l'esecuzione del metodo getListaNonConformita() in AuditNonConformitaDaoImpl.java");

		}

		log.info("getListaNonConformita() in calsse AuditNonConformitaDaoImpl.java eseguito");

		return nonConformitaList;
	}

	/* REGOLE NON CONFORMITA */

	public List<AuNonConformita> getListaNonConformita(Long idAudit, String fase) {
		List<AuNonConformita> nonConformitaList = null;
		String queryStr = "select ID_NON_CONFORMITA,DESCRIZIONE,SOGLIA1,SOGLIA2,"
			+ "SOGLIA3,SOGLIA4,SOGLIA5,SOGLIA6,RISCHIO,RISCHIO_ECONOMICO"
			+ " from AU_NON_CONFORMITA WHERE STATO <> 'D' AND ID_AUDIT="+idAudit+ " AND FASE = +'"+fase+"'";
		try {
			Query querySel = em.createNativeQuery(queryStr,AuNonConformita.class);
			nonConformitaList = querySel.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Errore durante l'esecuzione del metodo getListaNonConformita() in AuditNonConformitaDaoImpl.java");

		}

		log.info("getListaNonConformita() in calsse AuditNonConformitaDaoImpl.java eseguito");

		return nonConformitaList;
	}

	/* NON CONFORMITA VERBALE */

	public Integer countAllListaNonConformitaVerbale(Long idVerbale,
			String textSearch, String fase) {
		Integer count = null;
		String queryStr = "SELECT count (NV.ID_NON_CONFORMITA_VERBALE)"
			+ "  FROM AU_NON_CONFORMITA_VERBALE NV "
			+ " INNER JOIN AU_NON_CONFORMITA N ON N.ID_NON_CONFORMITA=NV.ID_NON_CONFORMITA"
			+ " WHERE ID_VERBALE =" + idVerbale + " and NV.FASE ='" + fase
			+ "'";

		if (!StringUtils.isEmpty(textSearch)) {
			queryStr += " and (" + "upper(DESCRIZIONE) like UPPER('%"
			+ textSearch + "%') )";
		}
		try {
			Query querySel = em.createNativeQuery(queryStr);
			count = (Integer) querySel.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			log
			.error("Errore durante l'esecuzione del metodo countAllListaNonConformitaVerbale() in AuditNonConformitaDaoImpl.java");
		}

		log
		.info("countAllListaNonConformitaVerbale() in AuditNonConformitaDaoImpl.java eseguito");

		return count == null ? 0 : count;
	}

	public List<AuNonConformitaVerbale> getListaNonConformitaVerbale(
			Long idVerbale, String fase) {
		List<AuNonConformitaVerbale> verbaliNonConformitaList = null;
		String queryStr = "SELECT NV.*,N.* "
			+ "  FROM AU_NON_CONFORMITA_VERBALE NV "
			+ " INNER JOIN AU_NON_CONFORMITA N ON N.ID_NON_CONFORMITA=NV.ID_NON_CONFORMITA"
			+ " WHERE ID_VERBALE =" + idVerbale + " and NV.FASE ='" + fase
			+ "'";

		try {
			Query querySel = em.createNativeQuery(queryStr,
					AuNonConformitaVerbale.class);
			verbaliNonConformitaList = querySel.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			log
			.error("Errore durante l'esecuzione del metodo getListaNonConformitaVerbale() in AuditNonConformitaDaoImpl.java");

		}
		log
		.info("Metodo getListaNonConformitaVerbale() della classe AuditNonConformitaDaoImpl.java eseguito");
		return verbaliNonConformitaList;
	}

	public List<AuNonConformitaVerbale> getListaNonConformitaVerbaleIdNC(
			Long idVerbale, Long idNC) {
		List<AuNonConformitaVerbale> verbaliNonConformitaList = null;
		String queryStr = "SELECT NV.*,N.* "
			+ "  FROM AU_NON_CONFORMITA_VERBALE NV "
			+ " INNER JOIN AU_NON_CONFORMITA N ON N.ID_NON_CONFORMITA=NV.ID_NON_CONFORMITA"
			+ " WHERE ID_VERBALE =" + idVerbale
			+ " and NV.ID_NON_CONFORMITA =" + idNC;

		try {
			Query querySel = em.createNativeQuery(queryStr,
					AuNonConformitaVerbale.class);

			verbaliNonConformitaList = querySel.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
			log
			.error("Errore durante l'esecuzione del metodo getListaNonConformitaVerbaleIdNC() della classe AuditNonConformitaDaoImpl.java ");
		}
		log
		.info("metodo getListaNonConformitaVerbaleIdNC() della classe AuditNonConformitaDaoImpl.java eseguito");
		return verbaliNonConformitaList;
	}

	public List<AuNonConformitaVerbale> getListaNonConformitaVerbale(
			long idSessione) {
		List<AuNonConformitaVerbale> verbaliNonConformitaList = null;
		String queryStr = "SELECT  ncv.* "
			+ " from AU_ASS_CAMP_VERB ass "
			+ " inner join au_campione c on ass.ID_CAMPIONE=c.ID_CAMPIONE  "
			+ " inner join AU_VERBALE v on v.ID_VERBALE = ass.ID_VERBALE "
			+ " inner join AU_SESSIONI s on s.ID_SESSIONE=c.ID_SESSIONE "
			//+ "inner join au_campagna ca on ca.ID_CAMPAGNA=v.ID_CAMPAGNA "
			+ " inner join AU_ASS_VERBALE_CAMPAGNA assvc on assvc.ID_VERBALE= v.ID_VERBALE"
			+ " inner join au_campagna ca on ca.ID_CAMPAGNA=assvc.ID_CAMPAGNA"
			+ " inner join AU_NON_CONFORMITA_VERBALE ncv on ncv.ID_VERBALE= v.ID_VERBALE "
			+ " inner join AU_NON_CONFORMITA nc on nc.ID_NON_CONFORMITA = ncv.ID_NON_CONFORMITA "
			+ "  where s.ID_SESSIONE=" + idSessione;

		try {
			Query querySel = em.createNativeQuery(queryStr,
					AuNonConformitaVerbale.class);

			verbaliNonConformitaList = querySel.getResultList();

		} catch (Exception e) {

			log.error("Errore durante l'esecuzione del motodo getListaNonConformitaVerbale() della classe AuditNonConformitaDaoImpl.java ");
		}

		log.info("Metodo getListaNonConformitaVerbale() della classe AuditNonConformitaDaoImpl.java eseguito  ");

		return verbaliNonConformitaList;
	}


	

	

	@Transactional
	public void salvaNonConformitaVerbale(
			AuNonConformitaVerbale verbaleNonConformita) {
		try {
			em.merge(verbaleNonConformita);
		} catch (Exception e) {
			e.printStackTrace();

			log.error("errore durante il salvataggio del verbale non conformita con id: "
					+ verbaleNonConformita.getIdVerbale()
					+ "metodo salvaNonConformitaVerbale() della classe AuditNonConformitaDaoImpl.java ");
		}
		log.info("verbale non conformita salvato correttamente");
	}

	@Transactional
	public void salvaNonConformitaVerbaleDto(
			NonConformitaVerbaleDto verbaleNonConformita) {
		try {

			String queryStr = "UPDATE AU_NON_CONFORMITA_VERBALE SET NOTE = '"
				+ verbaleNonConformita.getNote() + "', "
				+ " RISULTATO_REALE = "
				+ verbaleNonConformita.getValoreReale() + " "
				+ " WHERE ID_NON_CONFORMITA_VERBALE = "
				+ verbaleNonConformita.getIdNCV();

			Query querySel = em.createNativeQuery(queryStr);

			querySel.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			log.error("Errore durante il salvataggio del verbale non conformita con id:"
					+ verbaleNonConformita.getIdNCV()
					+ ". Metodo salvaNonConformitaVerbaleDto della classe AuditNonConformitaDaoImpl.java");
		}
		log.info("salvaNonConformitaVerbaleDto() della classe AuditNonConformitaDaoImpl.java eseguito");
	}

	/*
	 * carica la tabella degli indicatori con la media di notifica e definizione
	 */
	public List<AuMediaFase> getIndicatoriDatiGenerali(long idSessione) {
		List<AuMediaFase> indicatoriDatiGenerali = null;
		String queryStr = "SELECT MF.* " + " FROM AU_MEDIA_FASE MF "
		+ " WHERE ID_SESSIONE =" + idSessione;

		Query querySel = em.createNativeQuery(queryStr, AuMediaFase.class);
		try {
			indicatoriDatiGenerali = querySel.getResultList();
		} catch (Exception e) {
			log.error("Errore durante l'esecuzione del metodo getIndicatoriDatiGenerali() della classe AuditNonConformitaDaoImpl.java");
		}

		log.info("Metodo getIndicatoriDatiGenerali() della classe AuditNonConformitaDaoImpl.java eseguito");
		return indicatoriDatiGenerali;
	}

	@Override
	public void aggiornaMedia(AuMediaFase mf) {
		em.merge(mf);

	}


	/*
	 * Aggiorna il punteggio del questionario dopo il salvataggio di una
	 * risposta
	 */
	public void aggiornaPunteggioQuestionario(AuMediaFase mf) {
		try {

			String queryStr = "UPDATE AU_MEDIA_FASE SET QUESTIONARIO = "
				+ mf.getQuestionario() + " " + " WHERE  ID_MEDIA_FASE = "
				+ mf.getIdMediaFase();

			Query querySel = em.createNativeQuery(queryStr);

			querySel.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			log
			.error("Errore durante l'esecuzione del metodo aggiornaPunteggioQuestionario() della classe AuditNonConformitaDaoImpl.java");
		}
		log
		.info("Metodo aggiornaPunteggioQuestionario() della classe AuditNonConformitaDaoImpl.java eseguito ");

	}

	/*
	 * Calcola la media per ogni non conformita Notifica
	 */

	public List<Object> mediaNonConformita(long idSessione, String tipo,
			Integer pageNumber, Integer pageSize, String columnNameToOrder,
			String orderType, String textSearch) {
		List<Object> mediaFaseNonConformitaN = null;
		String query = "SELECT nc.ID_NON_CONFORMITA, nc.DESCRIZIONENONCONFORMITA,  nc.DESCRIZIONE, avg(case when ncv.RISULTATO_REALE is not null then ncv.RISULTATO_REALE  else ncv.RISULTATO end) media  "
			+ " from AU_ASS_CAMP_VERB ass "
			+ " inner join au_campione c on ass.ID_CAMPIONE=c.ID_CAMPIONE "
			+ " inner join AU_VERBALE v on v.ID_VERBALE = ass.ID_VERBALE "
			+ " inner join AU_ASS_VERBALE_CAMPAGNA assvc on assvc.ID_VERBALE= v.ID_VERBALE"
			+ " inner join au_campagna ca on ca.ID_CAMPAGNA=assvc.ID_CAMPAGNA"
			+ " inner join AU_SESSIONI s on s.ID_SESSIONE=c.ID_SESSIONE "
			+ " inner join AU_NON_CONFORMITA_VERBALE ncv on ncv.ID_VERBALE= v.ID_VERBALE "
			+ " inner join AU_NON_CONFORMITA nc on nc.ID_NON_CONFORMITA = ncv.ID_NON_CONFORMITA"
			+ " where s.ID_SESSIONE=" + idSessione + "and ncv.FASE= '"+tipo+"'";

		if (!StringUtils.isEmpty(textSearch)) {

			query += " and (" + "upper(nc.descrizione) like UPPER('%"
			+ textSearch + "%') "
			+ "or upper(nc.DESCRIZIONENONCONFORMITA) like UPPER('%"
			+ textSearch + "%'))";

		}

		query += "  group by nc.ID_NON_CONFORMITA, nc.DESCRIZIONE, nc.DESCRIZIONENONCONFORMITA";
		
		if (!StringUtils.isEmpty(columnNameToOrder)) {
			int c= Integer.valueOf(columnNameToOrder);
			query +=" ORDER BY "+(c + 1)+" "+orderType +" OFFSET "+pageNumber+" ROWS ";
			query +=" FETCH NEXT "+pageSize+" ROWS ONLY ";

		
		}
		
		Query querySel = em.createNativeQuery(query);
		
		try {
			mediaFaseNonConformitaN = querySel.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Errore durante l'esecuzione del metodo mediaNonConformitaN() della classe AuditNonConformitaDaoImpl.java");
		}
		log.info("metodo mediaNonConformitaN() della classe AuditNonConformitaDaoImpl.java eseguito ");
		return mediaFaseNonConformitaN;
	}

	/*
	 * Calcola la media per ogni non conformita Definizione
	 */

//	public List<Object> mediaNonConformitaD(long idSessione,
//			Integer pageNumber, Integer pageSize, String columnNameToOrder,
//			String orderType, String textSearch) {
//		List<Object> mediaFaseNonConformitaN=null;
//		String query = "SELECT  nc.ID_NON_CONFORMITA,nc.DESCRIZIONE,nc.DESCRIZIONENONCONFORMITA, ncv.FASE,"
//			+ " avg(case when ncv.RISULTATO_REALE is not null then ncv.RISULTATO_REALE  else ncv.RISULTATO end) media "
//			+ " from AU_ASS_CAMP_VERB ass "
//			+ " inner join au_campione c on ass.ID_CAMPIONE=c.ID_CAMPIONE "
//			+ " inner join AU_VERBALE v on v.ID_VERBALE = ass.ID_VERBALE "
//			+ " inner join AU_SESSIONI s on s.ID_SESSIONE=c.ID_SESSIONE "
//			//+ " inner join au_campagna ca on ca.ID_CAMPAGNA=v.ID_CAMPAGNA "
//			+ " inner join AU_ASS_VERBALE_CAMPAGNA assvc on assvc.ID_VERBALE= v.ID_VERBALE"
//			+ " inner join au_campagna ca on ca.ID_CAMPAGNA=assvc.ID_CAMPAGNA"
//			+ " inner join AU_NON_CONFORMITA_VERBALE ncv on ncv.ID_VERBALE= v.ID_VERBALE "
//			+ " inner join AU_NON_CONFORMITA nc on nc.ID_NON_CONFORMITA = ncv.ID_NON_CONFORMITA"
//			+ " where s.ID_SESSIONE=" + idSessione + "and ncv.FASE= 'D'";
//
//		if (!StringUtils.isEmpty(textSearch)) {
//
//			query += " and (" + "upper(nc.descrizione) like UPPER('%"
//			+ textSearch + "%') "
//			+ "or upper(nc.DESCRIZIONENONCONFORMITA) like UPPER('%"
//			+ textSearch + "%'))";
//
//		}
//
//		if (!StringUtils.isEmpty(columnNameToOrder)) {
//			query += " order by " + columnNameToOrder + " " + orderType;
//		}
//
//		query += " group by nc.ID_NON_CONFORMITA ,nc.DESCRIZIONE, nc.DESCRIZIONENONCONFORMITA, ncv.fase ";
//		Query querySel = em.createNativeQuery(query);
//		if (pageNumber != null && pageSize != null && pageSize > 0) {
//			querySel.setFirstResult(pageNumber);
//			querySel.setMaxResults(pageSize);
//		}
//		try{
//			mediaFaseNonConformitaN = querySel.getResultList();
//		}catch(Exception e){
//			e.printStackTrace();
//			log.error("Errore durante l'esecuzione del metodo mediaNonConformitaD() della classe AuditNonConformitaDaoImpl.java");
//		}
//		log
//		.info("metodo mediaNonConformitaD() della classe AuditNonConformitaDaoImpl.java eseguito ");
//
//		return mediaFaseNonConformitaN;
//	}

	// COUNT RECORD FASE DEFINIZIONE(la media)
	public Integer countMediaNonConformitaD(long idSessione) {
		Integer count=null;
		String query = "SELECT count(nc.ID_NON_CONFORMITA)"
			+ " from AU_ASS_CAMP_VERB ass "
			+ " inner join au_campione c on ass.ID_CAMPIONE=c.ID_CAMPIONE "
			+ " inner join AU_VERBALE v on v.ID_VERBALE = ass.ID_VERBALE "
			+ " inner join AU_SESSIONI s on s.ID_SESSIONE=c.ID_SESSIONE "
			//+ " inner join au_campagna ca on ca.ID_CAMPAGNA=v.ID_CAMPAGNA "
			+ " inner join AU_ASS_VERBALE_CAMPAGNA assvc on assvc.ID_VERBALE= v.ID_VERBALE"
			+ " inner join au_campagna ca on ca.ID_CAMPAGNA=assvc.ID_CAMPAGNA"
			+ " inner join AU_NON_CONFORMITA_VERBALE ncv on ncv.ID_VERBALE= v.ID_VERBALE "
			+ " inner join AU_NON_CONFORMITA nc on nc.ID_NON_CONFORMITA = ncv.ID_NON_CONFORMITA"
			+ " where s.ID_SESSIONE=" + idSessione + "and ncv.FASE= 'D'"
			+ " group by nc.ID_NON_CONFORMITA ";

		try{
			Query querySel = em.createNativeQuery(query);
			count = querySel.getResultList().size();
		}catch(Exception e){
			e.printStackTrace();
			log.error("Errore durante l'esecuzione del metodo countMediaNonConformitaD() della classe AuditNonConformitaDaoImpl.java ");

		}
		log.info("Metodo countMediaNonConformitaD() della classe AuditNonConformitaDaoImpl.java eseguito");

		return count;

	}

	// COUNT RECORD FASE NOTIFICA(la media)
	public Integer countMediaNonConformitaN(long idSessione) {
		Integer count=null;
		String query = "SELECT count(nc.ID_NON_CONFORMITA)"
			+ " from AU_ASS_CAMP_VERB ass "
			+ " inner join au_campione c on ass.ID_CAMPIONE=c.ID_CAMPIONE "
			+ " inner join AU_VERBALE v on v.ID_VERBALE = ass.ID_VERBALE "
			+ " inner join AU_SESSIONI s on s.ID_SESSIONE=c.ID_SESSIONE "
			//+ " inner join au_campagna ca on ca.ID_CAMPAGNA=v.ID_CAMPAGNA "
			+ " inner join AU_ASS_VERBALE_CAMPAGNA assvc on assvc.ID_VERBALE= v.ID_VERBALE"
			+ " inner join au_campagna ca on ca.ID_CAMPAGNA=assvc.ID_CAMPAGNA"
			+ " inner join AU_NON_CONFORMITA_VERBALE ncv on ncv.ID_VERBALE= v.ID_VERBALE "
			+ " inner join AU_NON_CONFORMITA nc on nc.ID_NON_CONFORMITA = ncv.ID_NON_CONFORMITA"
			+ " where s.ID_SESSIONE=" + idSessione + "and ncv.FASE= 'N'"
			+ " group by nc.ID_NON_CONFORMITA ";

		try{
			Query querySel = em.createNativeQuery(query);

			count = querySel.getResultList().size();

		}catch(Exception e){
			e.printStackTrace();
			log.error("Errore durante l'esecuzione del metodo countMediaNonConformitaN() della classe AuditNonConformitaDaoImpl.java" );
		}
		log.info("Metodo countMediaNonConformitaN() della classe AuditNonConformitaDaoImpl.java eseguito");

		return count;

	}




	

}
