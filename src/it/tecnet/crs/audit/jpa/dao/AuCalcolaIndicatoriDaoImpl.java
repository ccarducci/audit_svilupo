package it.tecnet.crs.audit.jpa.dao;

import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoFaseAcquisizioneIstanza;
import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoFaseAutotutelaResistenzaGiudizio;
import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoFaseDati;
import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoFaseEsecuzioneProvvedimenti;
import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoFaseGestioneIstruttoria;
import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoFasePeritale;
import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoFasePostPeritale;
import it.tecnet.crs.audit.web.dto.CalcoloIndicatoriRiepilogoPraticheNonConfFasi;
import it.tecnet.crs.indicatori.campagna.AU_C_NONCONF;
import it.tecnet.crs.indicatori.campagna.AU_C_VARCOMP;
import it.tecnet.crs.indicatori.campagna.CampagnaNonConfDto;
import it.tecnet.crs.indicatori.sessione.AuTotH3PerRischio;
import it.tecnet.crs.indicatori.sessione.RiepilogoTipologica;
import it.tecnet.crs.jpa.model.AuCampagna;
import it.tecnet.crs.jpa.model.AuInccDes;
import it.tecnet.crs.jpa.model.AuMNonConf;
import it.tecnet.crs.jpa.model.AuMRischio;
import it.tecnet.crs.jpa.model.AuMRisepr;
import it.tecnet.crs.jpa.model.AuMVarcomp;
import it.tecnet.crs.jpa.model.AuSPratica;
import it.tecnet.crs.jpa.model.AuSPraticaRis;
import it.tecnet.crs.jpa.model.AuSPraticaVarcomp;
import it.tecnet.crs.jpa.model.AuSSessione;
import it.tecnet.crs.jpa.model.AuSTvalori;
import it.tecnet.crs.jpa.model.AuTplIsnc;
import it.tecnet.crs.jpa.model.AuTplTipologiche;
import it.tecnet.crs.util.SessioneUtil;
import it.tecnet.crs.web.dto.StatoSessionePratica;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

public class AuCalcolaIndicatoriDaoImpl implements AuCalcolaIndicatoriDao {

	@PersistenceContext()
	private EntityManager em;

	protected static Logger log = Logger
			.getLogger(AuCalcolaIndicatoriDaoImpl.class);

	@Override
	@Transactional
	public void persist(Object entity) {
		try {
			em.persist(entity);
		} catch (EntityExistsException e) {
			log.error(e.getMessage());
		}
	}

	@Override
	@Transactional
	public <T> T salva(T entity) {
		try {
			entity = em.merge(entity);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return entity;
	}

	@Override
	@Transactional
	public <T> T update(T entity) {
		try {
			entity = em.merge(entity);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return entity;
	}

	@Transactional
	@Override
	public int setEsamePratica(long idVerbale, String stato, String userId,
			String userIdToUpdate) {
		String querySql = "";
		if (userIdToUpdate == null) {
			querySql = "Update p set USER_OWNER = null , p.ESAME_PRATICA = '"
					+ stato + "' from AU_S_SESSIONE s, AU_S_PRATICA p "
					+ " where s.ID_S_SESSIONE = p.ID_S_SESSIONE "
					+ " and p.ID_PRATICA = " + idVerbale
					+ " and USER_OWNER = '" + userId + "'";
		} else {
			querySql = "Update p set USER_OWNER = '" + userIdToUpdate
					+ "' , p.ESAME_PRATICA = '" + stato
					+ "' from AU_S_SESSIONE s, AU_S_PRATICA p "
					+ " where s.ID_S_SESSIONE = p.ID_S_SESSIONE "
					+ " and p.ID_PRATICA = " + idVerbale
					+ " and USER_OWNER = '" + userId + "'";
		}
		log.debug("setEsamePratica");
		return em.createNativeQuery(querySql).executeUpdate();
	}

	@Transactional
	@Override
	public int setPraticaInLavorazione(long idVerbale, String userId) {
		String querySql = "Update p set USER_OWNER = '" + userId
				+ "' , p.ESAME_PRATICA = 'L'"
				+ " from AU_S_SESSIONE s, AU_S_PRATICA p "
				+ " where s.ID_S_SESSIONE = p.ID_S_SESSIONE "
				+ " and p.ID_PRATICA = " + idVerbale
				+ " and ( USER_OWNER is null or USER_OWNER = '" + userId
				+ "' )";
		log.debug("setPraticaInLavorazione");
		return em.createNativeQuery(querySql).executeUpdate();
	}

	@Transactional
	@Override
	public int setPraticaInCalcolata(long idVerbale, String userId) {
		String querySql = "Update p set p.ESAME_PRATICA = 'C'"
				+ " from AU_S_SESSIONE s, AU_S_PRATICA p "
				+ " where s.ID_S_SESSIONE = p.ID_S_SESSIONE "
				+ " and p.ID_PRATICA = " + idVerbale + " and USER_OWNER = '"
				+ userId + "'";
		log.debug("setPraticaInCalcolata");
		return em.createNativeQuery(querySql).executeUpdate();
	}

	@Transactional
	@Override
	public boolean cleanCalcoloIndiciPraticaLog(long idPratica) {
		try {
			em.createNativeQuery(
					"DELETE FROM AU_S_PRA_CAL_IND_LOG WHERE ID_PRATICA = "
							+ idPratica).executeUpdate();
			return true;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return false;
	}

	@Override
	public boolean isSSsessionOpen(Long idSSsessione) {

		String sqlString = "select * from AU_S_SESSIONE  where ID_S_SESSIONE = "
				+ idSSsessione;
		List<AuSSessione> ris = em.createNativeQuery(sqlString,
				AuSSessione.class).getResultList();
		if (ris.size() > 0) {
			AuSSessione sess = ris.get(0);
			String statoEsameSessione = sess.getStatoEsameSessione();
			if (statoEsameSessione.equals(SessioneUtil.ESAME_SESSIONE_CHIUSO))
				return true;
		}
		log.debug("isSSsessionOpen");
		return false;
	}

	@Override
	public StatoSessionePratica canCalculateIndicatori(long idVerbale) {
		StatoSessionePratica res = new StatoSessionePratica();
		
		System.out.println("canCalculateIndicatori begin " );
		log.info("canCalculateIndicatori begin " );
		
		try {
			String query = "SELECT S.STATO_ESAME_SESSIONE AS statoEsameSessione ,p.ESAME_PRATICA AS esamePratica from AU_S_SESSIONE s, AU_S_PRATICA p "
					+ " where s.ID_S_SESSIONE = p.ID_S_SESSIONE "
					+ " and p.ID_PRATICA = " + idVerbale;

			/*
			Query querySel = em.createNativeQuery(query,
					StatoSessionePratica.class);
			*/
			//Query querySel = em.createNativeQuery(query);
			
			
			List<Object[]> ret = em.createNativeQuery(query).getResultList();
			
			Object[] row =  ret.get(0);
			res.setStatoEsameSessione((String)row[0]);
			res.setEsamePratica((String)row[1]);
			log.debug("canCalculateIndicatori");
		} catch (NoResultException e) {
			log.warn("canCalculateIndicatori NoResultException for idVerbale: "
					+ idVerbale);
			System.out.println("ERRORE (NoResultException) getSPraticaByIDPratica: canCalculateIndicatori NoResultException for idVerbale:  "
					+ idVerbale+  " - " + e.getStackTrace());
			log.info("ERRORE (NoResultException) getSPraticaByIDPratica: canCalculateIndicatori NoResultException for idVerbale:   "
					+ idVerbale+  " - " + e.getStackTrace());
			e.printStackTrace();
			
		}
		
		System.out.println("canCalculateIndicatori ok " );
		log.info("canCalculateIndicatori ok " );
		return res;
	}

	@Override
	public <T> T findById(Class<T> entityClass, Object primaryKey) {
		return em.find(entityClass, primaryKey);
	}

	@Override
	public AtpoFaseDati getFaseDatiByCodSedeAndFascicolo(String codSede,
			String fascicolo) {
		AtpoFaseDati ret = null;

		System.out.println("getFaseDatiByCodSedeAndFascicolo begin " );
		log.info("getFaseDatiByCodSedeAndFascicolo begin " );
		
		Query q = em
				.createNamedQuery(AtpoFaseDati.QUERY_FASEDATI_BY_FASCICOLO_AND_CODSEDE);
		q.setParameter(AtpoFaseDati.QUERY_PARAM_FASCICOLO, fascicolo);
		q.setParameter(AtpoFaseDati.QUERY_PARAM_CODSEDE, codSede);
		ret = (AtpoFaseDati) q.getSingleResult();

		// String sqlString =
		// "SELECT * FROM ATPO_FASE_DATI t WHERE ltrim(rtrim(t.fascicolo)) = '"
		// + fascicolo
		// + "'  and ltrim(rtrim(t.COD_SEDE)) = '" + codSede + "'";
		// ret = (AtpoFaseDati)
		// em.createNativeQuery(sqlString,AtpoFaseDati.class).getSingleResult();

		System.out.println("getFaseDatiByCodSedeAndFascicolo ok " );
		log.info("getFaseDatiByCodSedeAndFascicolo ok " );
		return ret;
	}

	@Override
	public AtpoFasePostPeritale getAtpoFasePostPeritaleByIdFaseDati(
			long idFaseDati) {
		AtpoFasePostPeritale ret = null;

		System.out.println("getAtpoFasePostPeritaleByIdFaseDati begin " );
		log.info("getAtpoFasePostPeritaleByIdFaseDati begin " );
		
		Query q = em
				.createNamedQuery(AtpoFasePostPeritale.QUERY_FAEPOSTPERITALE_BY_IDFASEDATI);
		q.setParameter(AtpoFasePostPeritale.QUERY_PARAM_IDFASEDATI, idFaseDati);
		ret = (AtpoFasePostPeritale) q.getSingleResult();

	
		System.out.println("getAtpoFasePostPeritaleByIdFaseDati ok " );
		log.info("getAtpoFasePostPeritaleByIdFaseDati ok " );
		// String sqlString =
		// "SELECT * FROM ATPO_FASE_POST_PERITALE t WHERE t.id_fase_dati = " +
		// idFaseDati;
		// ret = (AtpoFasePostPeritale)
		// em.createNativeQuery(sqlString,AtpoFasePostPeritale.class).getSingleResult();
		return ret;
	}

	@Override
	public AtpoFaseEsecuzioneProvvedimenti getAtpoFaseEsecuzioneProvvedimentiByIdFaseDati(
			long idFaseDati) {
		AtpoFaseEsecuzioneProvvedimenti ret = null;

		System.out.println("getAtpoFaseEsecuzioneProvvedimentiByIdFaseDati begin " );
		log.info("getAtpoFaseEsecuzioneProvvedimentiByIdFaseDati begin " );
		
		Query q = em
				.createNamedQuery(AtpoFaseEsecuzioneProvvedimenti.QUERY_FASEESECPROVV_BY_IDFASEDATI);
		q.setParameter(AtpoFaseEsecuzioneProvvedimenti.QUERY_PARAM_IDFASEDATI,
				idFaseDati);
		ret = (AtpoFaseEsecuzioneProvvedimenti) q.getSingleResult();
		
		System.out.println("getAtpoFaseEsecuzioneProvvedimentiByIdFaseDati ok " );
		log.info("getAtpoFaseEsecuzioneProvvedimentiByIdFaseDati ok " );
		/*
		 * try{ String sqlString =
		 * "SELECT * FROM ATPO_FASE_ESECUZIONE_PROVVEDIMENTI t WHERE t.id_fase_dati = "
		 * + idFaseDati; ret = (AtpoFaseEsecuzioneProvvedimenti)
		 * em.createNativeQuery
		 * (sqlString,AtpoFaseEsecuzioneProvvedimenti.class).getSingleResult();
		 * }catch(NoResultException e){ log.error(e.getMessage()); }
		 */
		return ret;
	}

	@Override
	public AtpoFaseAutotutelaResistenzaGiudizio getAtpoFaseAutotutelaResistenzaGiudizioByIdFaseDati(
			long idFaseDati) {
		AtpoFaseAutotutelaResistenzaGiudizio ret = null;
		System.out.println("getAtpoFaseAutotutelaResistenzaGiudizioByIdFaseDati begin " );
		log.info("getAtpoFaseAutotutelaResistenzaGiudizioByIdFaseDati begin " );
		Query q = em
				.createNamedQuery(AtpoFaseAutotutelaResistenzaGiudizio.QUERY_FASE_AUTOTUTELA_RES_GIUD_BY_IDFASEDATI);
		q.setParameter(
				AtpoFaseAutotutelaResistenzaGiudizio.QUERY_PARAM_IDFASEDATI,
				idFaseDati);
		ret = (AtpoFaseAutotutelaResistenzaGiudizio) q.getSingleResult();
		/*
		 * try{ String sqlString =
		 * "SELECT * FROM ATPO_FASE_AUTOTUTELA_RESISTENZA_GIUDIZIO t WHERE t.id_fase_dati = "
		 * + idFaseDati; ret = (AtpoFaseAutotutelaResistenzaGiudizio)
		 * em.createNativeQuery
		 * (sqlString,AtpoFaseAutotutelaResistenzaGiudizio.class
		 * ).getSingleResult(); }catch(NoResultException e){
		 * log.error(e.getMessage()); }
		 */
		System.out.println("getAtpoFaseAutotutelaResistenzaGiudizioByIdFaseDati ok " );
		log.info("getAtpoFaseAutotutelaResistenzaGiudizioByIdFaseDati ok " );
		return ret;
	}

	@Override
	public AtpoFaseGestioneIstruttoria getAtpoFaseGestioneIstruttoriaByIdFaseDati(
			long idFaseDati) {
		AtpoFaseGestioneIstruttoria ret = null;

		System.out.println("getAtpoFaseGestioneIstruttoriaByIdFaseDati begin " );
		log.info("getAtpoFaseGestioneIstruttoriaByIdFaseDati begin " );
		
		Query q = em
				.createNamedQuery(AtpoFaseGestioneIstruttoria.QUERY_FASE_GESTIONE_ISTRUTTORIA_BY_IDDASEDATI);
		q.setParameter(AtpoFaseGestioneIstruttoria.QUERY_PARAM_IDFASEDATI,
				idFaseDati);
		ret = (AtpoFaseGestioneIstruttoria) q.getSingleResult();
		/*
		 * try{ String sqlString =
		 * "SELECT * FROM ATPO_FASE_GESTIONE_ISTRUTTORIA t WHERE t.id_fase_dati = "
		 * + idFaseDati; ret = (AtpoFaseGestioneIstruttoria)
		 * em.createNativeQuery
		 * (sqlString,AtpoFaseGestioneIstruttoria.class).getSingleResult();
		 * }catch(NoResultException e){ log.error(e.getMessage()); }
		 */
		
		System.out.println("getAtpoFaseGestioneIstruttoriaByIdFaseDati ok " );
		log.info("getAtpoFaseGestioneIstruttoriaByIdFaseDati ok " );
		return ret;
	}

	@Override
	public AtpoFasePeritale getAtpoFasePeritaleByIdFaseDati(long idFaseDati) {
		AtpoFasePeritale ret = null;

		System.out.println("getAtpoFasePeritaleByIdFaseDati begin " );
		log.info("getAtpoFasePeritaleByIdFaseDati begin " );
		
		Query q = em
				.createNamedQuery(AtpoFasePeritale.QUERY_FASEPERITALE_BY_IDFASEDATI);
		q.setParameter(AtpoFasePeritale.QUERY_PARAM_IDFASEDATI, idFaseDati);
		ret = (AtpoFasePeritale) q.getSingleResult();
		/*
		 * try{ String sqlString =
		 * "SELECT * FROM ATPO_FASE_PERITALE t WHERE t.id_fase_dati = " +
		 * idFaseDati; ret = (AtpoFasePeritale)
		 * em.createNativeQuery(sqlString,AtpoFasePeritale
		 * .class).getSingleResult(); }catch(NoResultException e){
		 * log.error(e.getMessage()); }
		 */
		System.out.println("getAtpoFasePeritaleByIdFaseDati ok " );
		log.info("getAtpoFasePeritaleByIdFaseDati ok " );
		return ret;
	}

	@Override
	public AtpoFaseAcquisizioneIstanza getAtpoFaseAcquisizioneIstanzaByIdFaseDati(
			long idFaseDati) {
		AtpoFaseAcquisizioneIstanza ret = null;

		System.out.println("getAtpoFaseAcquisizioneIstanzaByIdFaseDati begin " );
		log.info("getAtpoFaseAcquisizioneIstanzaByIdFaseDati begin " );
		
		Query q = em
				.createNamedQuery(AtpoFaseAcquisizioneIstanza.QUERY_FAEACQUISIZIONEISTA_BY_IDFASEDATI);
		q.setParameter(AtpoFaseAcquisizioneIstanza.QUERY_PARAM_IDFASEDATI,
				idFaseDati);
		ret = (AtpoFaseAcquisizioneIstanza) q.getSingleResult();
		/*
		 * try{ String sqlString =
		 * "SELECT * FROM ATPO_FASE_ACQUISIZIONE_ISTANZA t WHERE t.id_fase_dati = "
		 * + idFaseDati; ret = (AtpoFaseAcquisizioneIstanza)
		 * em.createNativeQuery
		 * (sqlString,AtpoFaseAcquisizioneIstanza.class).getSingleResult();
		 * }catch(NoResultException e){ log.error(e.getMessage()); }
		 */
		
		System.out.println("getAtpoFaseAcquisizioneIstanzaByIdFaseDati ok " );
		log.info("getAtpoFaseAcquisizioneIstanzaByIdFaseDati ok " );
		
		return ret;
	}

	@Override
	public AuSPratica getSPraticaByIDPratica(long idPratica) {
		AuSPratica pratica = null;
		
		System.out.println("getSPraticaByIDPratica begin " );
		log.info("getSPraticaByIDPratica begin " );
		
		
		String queryStr = "select * from AU_S_PRATICA WHERE ID_PRATICA = "
				+ idPratica;
		try {
			Query querySel = em.createNativeQuery(queryStr, AuSPratica.class);
			pratica = (AuSPratica) querySel.getSingleResult();
		} catch (NoResultException e) {
			log.error("Non è stata trovata la AU_S_PRATICA con idPratica: "
					+ idPratica);
			System.out.println("ERRORE (NoResultException) getSPraticaByIDPratica: Non è stata trovata la AU_S_PRATICA con idPratica:  "
					+ idPratica+  " - " + e.getStackTrace());
			log.info("ERRORE (NoResultException) getSPraticaByIDPratica: Non è stata trovata la AU_S_PRATICA con idPratica:  "
					+ idPratica+  " - " + e.getStackTrace());
			e.printStackTrace();
		} catch (NonUniqueResultException e) {
			log
					.error("Sono stati trovati più recors di AU_S_PRATICA con idPratica: "
							+ idPratica);
			System.out.println("ERRORE (NonUniqueResultException) getSPraticaByIDPratica: Sono stati trovati più recors di AU_S_PRATICA con idPratica: "
							+ idPratica+  " - " + e.getStackTrace());
			log.info("ERRORE (NonUniqueResultException) getSPraticaByIDPratica: Sono stati trovati più recors di AU_S_PRATICA con idPratica: "
					+ idPratica+  " - " + e.getStackTrace());
			e.printStackTrace();
		}

		System.out.println("getSPraticaByIDPratica ok " );
		log.info("getSPraticaByIDPratica ok " );
		
		return pratica;
	}

	@Override
	public AuCampagna getCampagnaById(long idCampagna) {
		return em.find(AuCampagna.class, idCampagna);
	}

	public long getIdCambagnaByIdSSessione(long idSSessione) {
		Long idCampanga;
		
		System.out.println("getIdCambagnaByIdSSessione begin " );
		log.info("getIdCambagnaByIdSSessione begin " );
		
		String queryStr = "select distinct(c.ID_CAMPAGNA) as idCampagna ";
		queryStr += "from  AU_S_SESSIONE ss, AU_SESSIONI s , AU_CAMPAGNA c ";
		queryStr += "where ss.ID_SESSIONE = s.ID_SESSIONE ";
		queryStr += "and s.ID_CAMPAGNA = c.ID_CAMPAGNA ";
		queryStr += "and ss.ID_S_SESSIONE = " + idSSessione;
		
		Query querySel = em.createNativeQuery(queryStr, Long.class);
		idCampanga = (Long) querySel.getSingleResult();
		
		System.out.println("getIdCambagnaByIdSSessione ok " );
		log.info("getIdCambagnaByIdSSessione ok " );
		
		return idCampanga;
	}

	@Override
	public List<AuMVarcomp> getAuMVarcomp() {
		List<AuMVarcomp> ret = null;
		
		System.out.println("getAuMVarcomp begin " );
		log.info("getAuMVarcomp begin " );
		
		try {
			ret = (List<AuMVarcomp>) em.createNativeQuery(
					"SELECT * FROM AU_M_VARCOMP", AuMVarcomp.class)
					.getResultList();
		} catch (NoResultException e) {
			
			System.out.println("ERRORE (NoResultException) getAuMVarcomp: " + e.getStackTrace());
			log.info("ERRORE (NoResultException) getAuMVarcomp: " + e.getStackTrace());
			e.printStackTrace();
		
		}
		
		System.out.println("getAuMVarcomp ok " );
		log.info("getAuMVarcomp ok " );

		return ret;
	}

	@Override
	public List<AuMRischio> getRishioByAudit(long idAudit) {
		List<AuMRischio> auMRischio = null;
		
		System.out.println("getRishioByAudit begin " );
		log.info("getRishioByAudit begin " );
		
		// try {
		auMRischio = em.createNamedQuery(AuMRischio.QUERY_RISCHIO_BY_IDAUDIT)
				.setParameter("idAudit", idAudit).getResultList();
		/*
		 * } catch (NoResultException e) {
		 * log.warn("AuMRischio NoResultException for idAudit: " + idAudit); }
		 */
		

		System.out.println("getRishioByAudit ok " );
		log.info("getRishioByAudit ok " );
		return auMRischio;
	}

	@Override
	public AuSPratica getSPraticaByIdPratica(long idPratica) {
		AuSPratica pratica = null;
		
		System.out.println("getSPraticaByIdPratica begin " );
		log.info("getSPraticaByIdPratica begin " );
		
		
		String queryStr = "select * from AU_S_PRATICA WHERE ID_PRATICA = "
				+ idPratica;
		// try {
		Query querySel = em.createNativeQuery(queryStr, AuSPratica.class);
		pratica = (AuSPratica) querySel.getSingleResult();
		/*
		 * } catch (NoResultException e) {
		 * log.error("Non è stata trovata la AU_S_PRATICA con idVerbale: " +
		 * idPratica); } catch (NonUniqueResultException e) {log.error(
		 * "Sono stati trovati più records di AU_S_PRATICA con idVerbale: " +
		 * idPratica); }
		 */
		
		System.out.println("getSPraticaByIdPratica ok " );
		log.info("getSPraticaByIdPratica ok " );
		return pratica;
	}

	@Transactional
	@Override
	public void deleteSPtaricaRisByIdSPratica(long idSPratica) {
		System.out.println("deleteSPtaricaRisByIdSPratica begin " );
		log.info("deleteSPtaricaRisByIdSPratica begin " );
		
		em.createNativeQuery(
				"DELETE FROM AU_S_PRATICA_RIS WHERE ID_S_PRATICA = "
						+ idSPratica).executeUpdate();
		
		System.out.println("deleteSPtaricaRisByIdSPratica ok " );
		log.info("deleteSPtaricaRisByIdSPratica ok " );
	}

	@Transactional
	@Override
	public void deleteSPtaricaVarcompByIdSPratica(long idSPratica) {
		
		System.out.println("deleteSPtaricaVarcompByIdSPratica begin " );
		log.info("deleteSPtaricaVarcompByIdSPratica begin " );
		
		em.createNativeQuery(
				"DELETE FROM AU_S_PRATICA_VARCOMP WHERE ID_S_PRATICA = "
						+ idSPratica).executeUpdate();
		
		System.out.println("deleteSPtaricaVarcompByIdSPratica ok " );
		log.info("deleteSPtaricaVarcompByIdSPratica ok " );
	}

	@Transactional
	@Override
	public void cleanCalcoloIndiciSessione(long idSSessione) {

		System.out.println("cleanCalcoloIndiciSessione begin " );
		log.info("cleanCalcoloIndiciSessione begin " );
		
		// SELECT * FROM AU_S_RISESPR WHERE ID_S_RISCHIO in (SELECT ID_S_RISCHIO
		// FROM AU_S_RISCHIO WHERE ID_S_SESSIONE = 1)
		String sqlDeleteAU_S_RISESPR = "DELETE AU_S_RISESPR  WHERE ID_S_RISCHIO in (SELECT ID_S_RISCHIO FROM AU_S_RISCHIO WHERE ID_S_SESSIONE = "
				+ idSSessione + " )";
		em.createNativeQuery(sqlDeleteAU_S_RISESPR).executeUpdate();

		// SELECT * FROM AU_S_VARCOMP WHERE ID_S_NONCONF in (SELECT ID_S_NONCONF
		// FROM AU_S_NONCONF WHERE ID_S_SESSIONE = 1)
		String sqlDeleteAU_S_VARCOMP = "DELETE AU_S_VARCOMP WHERE ID_S_NONCONF in (SELECT ID_S_NONCONF FROM AU_S_NONCONF WHERE ID_S_SESSIONE = "
				+ idSSessione + " )";
		em.createNativeQuery(sqlDeleteAU_S_VARCOMP).executeUpdate();

		// SELECT * FROM AU_S_RISRAG WHERE ID_S_SESSIONE = 1
		em.createNativeQuery(
				"DELETE AU_S_RISRAG WHERE ID_S_SESSIONE = " + idSSessione)
				.executeUpdate();

		// SELECT * FROM AU_S_NONCONF WHERE ID_S_SESSIONE = 1
		em.createNativeQuery(
				"DELETE AU_S_NONCONF WHERE ID_S_SESSIONE = " + idSSessione)
				.executeUpdate();

		// SELECT * FROM AU_S_RISCHIO WHERE ID_S_SESSIONE = 1
		em.createNativeQuery(
				"DELETE AU_S_RISCHIO WHERE ID_S_SESSIONE = " + idSSessione)
				.executeUpdate();

		// SELECT * FROM AU_TDOCMANC WHERE ID_S_SESSIONE = 1
		em.createNativeQuery(
				"DELETE AU_TDOCMANC WHERE ID_S_SESSIONE = " + idSSessione)
				.executeUpdate();

		// SELECT * FROM AU_S_TFASCICOLO WHERE ID_S_SESSIONE = 1
		em.createNativeQuery(
				"DELETE AU_S_TFASCICOLO WHERE ID_S_SESSIONE = " + idSSessione)
				.executeUpdate();

		// SELECT * FROM AU_S_TEMPI WHERE ID_S_SESSIONE = 1
		em.createNativeQuery(
				"DELETE AU_S_TEMPI WHERE ID_S_SESSIONE = " + idSSessione)
				.executeUpdate();

		// SELECT * FROM AU_S_TESITO WHERE ID_S_SESSIONE = 1
		em.createNativeQuery(
				"DELETE AU_S_TESITO WHERE ID_S_SESSIONE = " + idSSessione)
				.executeUpdate();

		System.out.println("cleanCalcoloIndiciSessione ok " );
		log.info("cleanCalcoloIndiciSessione ok " );
		
	}

	@Override
	public List<AuSPratica> getSPraticaByIdSSessione(long idSSessione) {
		List<AuSPratica> ret = null;
		
		System.out.println("getSPraticaByIdSSessione begin " );
		log.info("getSPraticaByIdSSessione begin " );
		
		Query q = em.createNamedQuery(AuSPratica.QUERY_SPRATICA_BY_IDSSESSIONE);
		q.setParameter(AuSPratica.QUERY_PARAM_IDSSESSIONE, idSSessione);
		ret = q.getResultList();

		System.out.println("getSPraticaByIdSSessione ok " );
		log.info("getSPraticaByIdSSessione ok " );
		
		return ret;
	}

	@Override
	public List<AuSPratica> getSPraticaClosedByIdSSessione(long idSSessione) {
		List<AuSPratica> ret = null;
		
		System.out.println("getSPraticaClosedByIdSSessione begin " );
		log.info("getSPraticaClosedByIdSSessione begin " );
		
		Query q = em
				.createNamedQuery(AuSPratica.QUERY_SPRATICA_BY_IDSSSESSIONE_AND_ESAMEPRATICA_C);
		q.setParameter(AuSPratica.QUERY_PARAM_IDSSESSIONE, idSSessione);
		ret = q.getResultList();
		
		System.out.println("getAuSPraticaClosedVarcompBySessione ok " );
		log.info("getAuSPraticaClosedVarcompBySessione ok " );
		
		return ret;
	}

	@Override
	public List<AuSPraticaVarcomp> getAuSPraticaClosedVarcompBySessione(
			long idSSessione) {
		
		System.out.println("getAuSPraticaClosedVarcompBySessione begin " );
		log.info("getAuSPraticaClosedVarcompBySessione begin " );
	
		
		String sql = "SELECT VC.* FROM AU_S_PRATICA_VARCOMP VC , AU_S_PRATICA SP "
				+ " WHERE SP.ID_S_SESSIONE = "
				+ idSSessione
				+ " AND SP.ID_S_PRATICA = VC.ID_S_PRATICA "
				+ " AND SP.ESAME_PRATICA = 'C' ";
		List<AuSPraticaVarcomp> ret = em.createNativeQuery(sql,
				AuSPraticaVarcomp.class).getResultList();
		
		System.out.println("getAuSPraticaClosedVarcompBySessione ok " );
		log.info("getAuSPraticaClosedVarcompBySessione ok " );
		
		return ret;
	}

	@Override
	public List<AuMNonConf> getAuNonconf() {
		return em.createNamedQuery(AuMNonConf.QUERY_NONCONF_ALL)
				.getResultList();
	}

	@Override
	public List<AuMRisepr> getAuMRisepr() {
		List<AuMRisepr> ret = null;

		System.out.println("getAuMRisepr begin");
		log.debug("getAuMRisepr begin");
		
		try {
			ret = em.createNamedQuery(AuMRisepr.QUERY_ESPR_ALL).getResultList();
		} catch (Exception e) {

			System.out.println("ERRORE getAuMRisepr: " + e.getStackTrace());
			log.info("ERRORE getAuMRisepr: " + e.getStackTrace());
			e.printStackTrace();
			
		}

		System.out.println("getAuMRisepr end");
		log.debug("getAuMRisepr end");
		
		return ret;
	}

	@Override
	public List<AuSPraticaRis> getSPraticaRisClosedByIdSSessione(
			long idSSessione) {
		String query = "select * from AU_S_PRATICA_RIS where ID_S_PRATICA in "
				+ " ( select ID_S_PRATICA from AU_S_PRATICA where ID_S_SESSIONE = "
				+ idSSessione + " and ESAME_PRATICA = 'C' ) ";
		return em.createNativeQuery(query, AuSPraticaRis.class).getResultList();
	}

	@Override
	public List<AuTotH3PerRischio> getNumPraticheRischiH3NonSoggette(
			long idSSessione) {
		List<AuTotH3PerRischio> ret = new ArrayList<AuTotH3PerRischio>();

		System.out.println("getNumPraticheRischiH3NonSoggette begin");
		log.debug("getNumPraticheRischiH3NonSoggette begin");
		
		try {
			String sql = "select p.id_m_rischio as idRischio, count(*) as somma   from AU_S_PRATICA_RIS p ,  AU_M_RISESPR e  "
					+ " where p.ID_ESPR_RISCHIO = e.ID_M_RISESPR "
					+ " and p.ID_S_PRATICA in (select ID_S_PRATICA from AU_S_PRATICA where ID_S_SESSIONE = "
					+ idSSessione
					+ " ) "
					+ " and  e.RAGGRUPPAMENTO_RISCHIO = '3' "
					/* + " and CONVERT(nvarchar,ltrim(rtrim(DESCRIZIONE))) like '%Prestazione non soggetta%' " */
					+ " group by p.id_m_rischio ";

			/*
			ret = (List<AuTotH3PerRischio>) em.createNativeQuery(sql,
					AuTotH3PerRischio.class).getResultList();
			*/
			
			List<Object[]> retObject = em.createNativeQuery(sql).getResultList();
			
			for (Object[] row : retObject) {
				AuTotH3PerRischio itemToAdd = new AuTotH3PerRischio();
				// p.id_m_rischio
				itemToAdd.setIdRischio((Long)row[0]);
				// INT
				Integer count = (Integer) row[1];
				itemToAdd.setSomma(count.longValue());
				ret.add(itemToAdd);
			}
			
			log.debug("getNumPraticheRischiH3NonSoggette iter");
			
		} catch (NoResultException e) {

			System.out.println("ERRORE (NoResultException) getNumPraticheRischiH3NonSoggette: " + e.getStackTrace());
			log.info("ERRORE (NoResultException) getNumPraticheRischiH3NonSoggette: " + e.getStackTrace());
			e.printStackTrace();

		} catch (Exception e) {
			
			System.out.println("ERRORE getNumPraticheRischiH3NonSoggette: " + e.getStackTrace());
			log.info("ERRORE getNumPraticheRischiH3NonSoggette: " + e.getStackTrace());
			e.printStackTrace();
			
		}


		System.out.println("getNumPraticheRischiH3NonSoggette end");
		log.debug("getNumPraticheRischiH3NonSoggette end");
		
		return ret;
	}

	@Override
	public List<AuMRischio> getAuMRischio() {
		return em.createNamedQuery(AuMRischio.QUERY_RISCHIO_ALL)
				.getResultList();
	}

	@Override
	public List<AuInccDes> getAuInccDes() {
		return em.createNamedQuery(AuInccDes.QUERY_INCCDES_ALL).getResultList();
	}

	@Override
	public List<AuSTvalori> getAuSTvalori() {
		return em.createNamedQuery(AuSTvalori.QUERY_TVALORI_ALL)
				.getResultList();
	}

	@Transactional
	@Override
	public List<Object[]> getRischioRaggruppamento(Long idSSessione) {
		
		System.out.println("getRischioRaggruppamento begin");
		log.debug("getRischioRaggruppamento begin");
		
		String sql = "SELECT " + "		   e.ID_M_RISCHIO as idMRischio "
				+ "		   , m.RAGGRUPPAMENTO_RISCHIO as ragRischio "
				+ "		   , sum(isnull(e.num,0)) as num "
				+ "		   , sum(isnull(e.IMPORTO,0)) as importo "
				+ "	FROM  AU_S_RISESPR e,  AU_S_RISCHIO ris  , AU_M_RISESPR m "
				+ "	WHERE " + "		e.ID_M_RISCHIO = ris.ID_M_RISCHIO "
				+ "	AND e.ID_M_RISESPR = m.ID_M_RISESPR "
				+ "	AND ris.ID_S_SESSIONE = " + idSSessione
				+ "	group by  e.ID_M_RISCHIO,m.RAGGRUPPAMENTO_RISCHIO ";
		List<Object[]> ret = em.createNativeQuery(sql).getResultList();

		System.out.println("getRischioRaggruppamento ok");
		log.debug("getRischioRaggruppamento ok");
		
		return ret;
	}

	@Override
	@Transactional
	public void riapriSessione(long idSSessione) {
		
		System.out.println("riapriSessione begin");
		log.debug("riapriSessione begin");
		
		String querySql = "Update AU_S_SESSIONE set STATO_ESAME_SESSIONE = 'A' where ID_S_SESSIONE = "
				+ idSSessione;
		em.createNativeQuery(querySql).executeUpdate();
		
		System.out.println("riapriSessione ok");
		log.debug("riapriSessione ok");
	}

	@Override
	public List<AuTplTipologiche> getTipologica() {
		List<AuTplTipologiche> ret = new ArrayList<AuTplTipologiche>();
		
		System.out.println("getTipologica begin");
		log.debug("getTipologica begin");
		
		try {
			ret = em
					.createNamedQuery(AuTplTipologiche.QUERY_TPLTIPOLOGICHE_ALL)
					.getResultList();
		} catch (Exception e) {

			System.out.println("ERRORE getTipologica: " + e.getStackTrace());
			log.info("ERRORE getTipologica: " + e.getStackTrace());
			e.printStackTrace();
			
		}
		log.debug("getTipologica OK");
		System.out.println("getTipologica OK");
		return ret;
	}

	@Override
	public List<RiepilogoTipologica> getRiepilogoTipologicaTDocmanc(
			long idSSessione) {
		List<RiepilogoTipologica> ret = new ArrayList<RiepilogoTipologica>();
		
		System.out.println("getRiepilogoTipologicaTDocmanc begin");
		log.debug("getRiepilogoTipologicaTDocmanc begin");
		
		String sql = "SELECT  "
				+ "			LTRIM(RTRIM(DETFASC.CODIFICA)) , COUNT(*) AS NUM "
				+ "			FROM ATPO_FASE_RIEPILOGO_FASCICOLO RF, "
				+ "					ATPO_DETTAGLIO_FASCICOLO  DETFASC, "
				+ "					AU_TPL_TIPOLOGICHE TPL, "
				+ "					(SELECT  "
				+ "						FS.ID_FASE_DATI , PR.ESAME_PRATICA ,PR.ID_S_SESSIONE "
				+ "					FROM  "
				+ "						AU_S_PRATICA PR, ATPO_PRATICHE_SISCO PRS,ATPO_FASE_DATI FS "
				+ "					WHERE PR.ID_PRATICA = PRS.ID_PRATICHE_SISCO "
				+ "					AND PRS.COD_SEDE = FS.COD_SEDE "
				+ "					AND PRS.FASCICOLO = FS.FASCICOLO) PRA "
				+ "			WHERE RF.ID_RIEPILOGO_FASCICOLO = DETFASC.ID_RIEPILOGO_FASCICOLO "
				+ "			AND DETFASC.CODIFICA = TPL.CODIFICA "
				+ "			AND TPL.TIPO  = 'V003' "
				+ "			AND RF.ID_FASE_DATI = PRA.ID_FASE_DATI "
				+ "			AND ID_S_SESSIONE = " + idSSessione
				+ "			GROUP BY DETFASC.CODIFICA";

		List<Object[]> listaObject = em.createNativeQuery(sql).getResultList();
		try{
			for (Object[] row : listaObject) {
				RiepilogoTipologica itemToAdd = new RiepilogoTipologica();
				String codifica = (String) row[0];
				Integer num = (Integer) row[1];
				itemToAdd.setCodifica(codifica);
				itemToAdd.setNum(num);
				ret.add(itemToAdd);
			}
		} catch (Exception e) {

			System.out.println("ERRORE getRiepilogoTipologicaTDocmanc: " + e.getStackTrace());
			log.info("ERRORE getRiepilogoTipologicaTDocmanc: " + e.getStackTrace());
			e.printStackTrace();
			
		}
		
		System.out.println("getRiepilogoTipologicaTDocmanc OK");
		log.debug("getRiepilogoTipologicaTDocmanc OK");
		
		return ret;
	}

	@Override
	public List<RiepilogoTipologica> getRiepilogoTipologicaTFascicolo(
			long idSSessione) {

		List<RiepilogoTipologica> ret = new ArrayList<RiepilogoTipologica>();

		System.out.println("getRiepilogoTipologicaTFascicolo begin");
		log.info("getRiepilogoTipologicaTFascicolo begin");
		
		String sql = "SELECT LTRIM(RTRIM(RF.FASCICOLO_ELETTRONICO)) ,COUNT(*) AS NUM FROM ATPO_FASE_RIEPILOGO_FASCICOLO RF , "
				+ "			(SELECT  "
				+ "				FS.ID_FASE_DATI , PR.ESAME_PRATICA ,PR.ID_S_SESSIONE  "
				+ "			FROM   "
				+ "				AU_S_PRATICA PR, ATPO_PRATICHE_SISCO PRS,ATPO_FASE_DATI FS  "
				+ "			WHERE PR.ID_PRATICA = PRS.ID_PRATICHE_SISCO  "
				+ "			AND PRS.COD_SEDE = FS.COD_SEDE  "
				+ "			AND PRS.FASCICOLO = FS.FASCICOLO "
				+ " 		AND  PR.ESAME_PRATICA = 'C' "
				+ "			AND PR.ID_S_SESSIONE = "
				+ idSSessione
				+ "			) PRA,  "
				+ "			AU_TPL_TIPOLOGICHE TPL  "
				+ "	WHERE RF.ID_FASE_DATI = PRA.ID_FASE_DATI  "
				+ "	AND RF.FASCICOLO_ELETTRONICO IS NOT NULL  "
				+ "	AND RF.FASCICOLO_ELETTRONICO = TPL.CODIFICA  "
				+ "	AND TPL.TIPO = 'V007'  "
				+ "	AND ID_S_SESSIONE = "
				+ idSSessione + "	GROUP BY FASCICOLO_ELETTRONICO";

		List<Object[]> listaObject = em.createNativeQuery(sql).getResultList();
		try{
			for (Object[] row : listaObject) {
				RiepilogoTipologica itemToAdd = new RiepilogoTipologica();
				String codifica = (String) row[0];
				Integer num = (Integer) row[1];
				itemToAdd.setCodifica(codifica);
				itemToAdd.setNum(num);
				ret.add(itemToAdd);
			}
		} catch (Exception e) {

			System.out.println("ERRORE getRiepilogoTipologicaTFascicolo: " + e.getStackTrace());
			log.info("ERRORE getRiepilogoTipologicaTFascicolo: " + e.getStackTrace());
			e.printStackTrace();
			
		}
		
		System.out.println("getRiepilogoTipologicaTFascicolo OK");
		log.info("getRiepilogoTipologicaTFascicolo OK");
		
		return ret;
	}

	@Override
	public List<RiepilogoTipologica> getRiepilogoTipologicaSTempi(
			long idSSessione) {
		List<RiepilogoTipologica> ret = new ArrayList<RiepilogoTipologica>();
		
		System.out.println("getRiepilogoTipologicaSTempi begin");
		log.info("getRiepilogoTipologicaSTempi begin");
		
		/*
		1	E06	Acquisizione Ista	da data notifica a data acquisizione in SISCO																						> 3 
		2	E07	Gestione Istru		da data costituzione in giudizio a data 1^ udienza																					< 5 
		3	E08	Peritale			da data comunicazione DEPOSITO CTU definitiva a data protocollo CTU definitiva														> 3
		4	E09	Post Peritale		da data deposito DISSENSO INPS a data termine DISSENSO		(D'AVINO/DOMENICO)														< 0
		5	E10	Post Peritale		da data deposito del Decreto di OMOLOGA a data trasmissione del Decreto alla L.P. o Servizio										> 30 
		6	E01	Esecuzione Pro		da data di notifica del decreto di Omologa alla data di liquidazione della prestazione												> 120 
		7	E04	Esecuzione Pro		da data deposito decreto di omologa a data pagamento spese legali																	> 180
		8	E02	Post Peritale		da data deposito decreto di omologa a data definizione in SISCO																		> 3 
		*/
		
		try {
			String sql = " "
					+ "select CODIFICA, NC , sum(QUANTITA) as QUANTITA , ord from ( "
					+ "		select 'E06' as CODIFICA, '> 3' as NC  , isNull(intervallo_notifica_SISCO,0) as QUANTITA , 1 ord from ATPO_FASE_ACQUISIZIONE_ISTANZA FI where FI.id_fase_dati in ( select ID_FASE_DATI from ATPO_FASE_DATI  fd ,(select COD_SEDE, FASCICOLO from ATPO_PRATICHE_SISCO where ID_PRATICHE_SISCO in (select ID_PRATICA from AU_S_PRATICA sa where sa.ID_S_SESSIONE = " + idSSessione + " and sa.ESAME_PRATICA = 'C')) fasi where fasi.COD_SEDE = fd.COD_SEDE and fasi.FASCICOLO = fd.FASCICOLO ) " 
					+ "		union all " 
					+ "		select 'E07' as CODIFICA, '< 5' as NC  , isNull(intervallo_costGiud_udienza,0) as QUANTITA , 2 ord  from ATPO_FASE_GESTIONE_ISTRUTTORIA FI where FI.id_fase_dati in ( select ID_FASE_DATI from ATPO_FASE_DATI  fd ,(select COD_SEDE, FASCICOLO from ATPO_PRATICHE_SISCO where ID_PRATICHE_SISCO in (select ID_PRATICA from AU_S_PRATICA sa where sa.ID_S_SESSIONE = " + idSSessione + " and sa.ESAME_PRATICA = 'C')) fasi where fasi.COD_SEDE = fd.COD_SEDE and fasi.FASCICOLO = fd.FASCICOLO ) " 
					+ "		union all " 
					+ "		select 'E08' as CODIFICA, '> 3' as NC  , isNull(int_temp_com_DEP_CTU_to_CTU_def,0) as QUANTITA , 3 ord from ATPO_FASE_PERITALE FI where FI.id_fase_dati in ( select ID_FASE_DATI from ATPO_FASE_DATI  fd ,(select COD_SEDE, FASCICOLO from ATPO_PRATICHE_SISCO where ID_PRATICHE_SISCO in (select ID_PRATICA from AU_S_PRATICA sa where sa.ID_S_SESSIONE = " + idSSessione + " and sa.ESAME_PRATICA = 'C')) fasi where fasi.COD_SEDE = fd.COD_SEDE and fasi.FASCICOLO = fd.FASCICOLO ) " 
					+ "		union all " 
					+ "		select 'E09' as CODIFICA, '> 0' as NC  , isNull(INT_TEMP_DEPOSITO_DISS_TERMINE_DISS,0) as QUANTITA , 4 ord from ATPO_FASE_POST_PERITALE FI where FI.id_fase_dati in ( select ID_FASE_DATI from ATPO_FASE_DATI  fd ,(select COD_SEDE, FASCICOLO from ATPO_PRATICHE_SISCO where ID_PRATICHE_SISCO in (select ID_PRATICA from AU_S_PRATICA sa where sa.ID_S_SESSIONE = " + idSSessione + " and sa.ESAME_PRATICA = 'C')) fasi where fasi.COD_SEDE = fd.COD_SEDE and fasi.FASCICOLO = fd.FASCICOLO ) " 
					+ "		union all " 
					+ "		select 'E10' as CODIFICA, '> 30' as NC  , isNull(INT_TEMP_NOTIF_DECR_OMG_A_DECR_LPS,0) as QUANTITA , 5 ord from ATPO_FASE_POST_PERITALE FI where FI.id_fase_dati in ( select ID_FASE_DATI from ATPO_FASE_DATI  fd ,(select COD_SEDE, FASCICOLO from ATPO_PRATICHE_SISCO where ID_PRATICHE_SISCO in (select ID_PRATICA from AU_S_PRATICA sa where sa.ID_S_SESSIONE = " + idSSessione + " and sa.ESAME_PRATICA = 'C')) fasi where fasi.COD_SEDE = fd.COD_SEDE and fasi.FASCICOLO = fd.FASCICOLO ) " 
					+ "		union all " 
					+ "		select 'E01' as CODIFICA, '> 120' as NC  , isNull(GG_DA_NOTIF_DECR_OMG_A_LIQ_PREST,0) as QUANTITA  , 6 ord from ATPO_FASE_ESECUZIONE_PROVVEDIMENTI FI where FI.id_fase_dati in ( select ID_FASE_DATI from ATPO_FASE_DATI  fd ,(select COD_SEDE, FASCICOLO from ATPO_PRATICHE_SISCO where ID_PRATICHE_SISCO in (select ID_PRATICA from AU_S_PRATICA sa where sa.ID_S_SESSIONE = " + idSSessione + " and sa.ESAME_PRATICA = 'C')) fasi where fasi.COD_SEDE = fd.COD_SEDE and fasi.FASCICOLO = fd.FASCICOLO ) " 
					+ "		union all " 
					+ "		select 'E04' as CODIFICA, '> 180' as NC  ,isNull(INT_T_DEP_DECR_OMG_A_PAG_SPESE_LEGALI_CPARTE,0) as QUANTITA  , 7 ord from ATPO_FASE_ESECUZIONE_PROVVEDIMENTI FI where FI.id_fase_dati in ( select ID_FASE_DATI from ATPO_FASE_DATI  fd ,(select COD_SEDE, FASCICOLO from ATPO_PRATICHE_SISCO where ID_PRATICHE_SISCO in (select ID_PRATICA from AU_S_PRATICA sa where sa.ID_S_SESSIONE = " + idSSessione + " and sa.ESAME_PRATICA = 'C')) fasi where fasi.COD_SEDE = fd.COD_SEDE and fasi.FASCICOLO = fd.FASCICOLO ) " 
					+ "		union all " 
					+ "		select 'E02' as CODIFICA, '> 3' as NC  , isNull(INT_TEMP_NOTIF_OMG_PROT_OMG,0) as QUANTITA , 8 ord from ATPO_FASE_POST_PERITALE FI where FI.id_fase_dati in ( select ID_FASE_DATI from ATPO_FASE_DATI  fd ,(select COD_SEDE, FASCICOLO from ATPO_PRATICHE_SISCO where ID_PRATICHE_SISCO in (select ID_PRATICA from AU_S_PRATICA sa where sa.ID_S_SESSIONE = " + idSSessione + " and sa.ESAME_PRATICA = 'C')) fasi where fasi.COD_SEDE = fd.COD_SEDE and fasi.FASCICOLO = fd.FASCICOLO ) " 
					+ "	 ) dati " 
					+ "	 group by CODIFICA, ord, NC "
					+ "	 order by ord ";

			List<Object[]> listaObject = em.createNativeQuery(sql)
					.getResultList();

			for (Object[] row : listaObject) {
				RiepilogoTipologica itemToAdd = new RiepilogoTipologica();
				String codifica = (String) row[0];
				String nc = (String) row[1];
				Long num = (Long) row[2];
				Integer ord = (Integer) row[3];
				itemToAdd.setCodifica(codifica);
				itemToAdd.setNc(nc);
				itemToAdd.setNum(num.intValue());
				itemToAdd.setOrd(ord);
				ret.add(itemToAdd);
			}
			
			System.out.println("getRiepilogoTipologicaSTempi iter");
			log.info("getRiepilogoTipologicaSTempi iter");
			
		} catch (Exception e) {

			System.out.println("ERRORE getRiepilogoTipologicaSTempi: " + e.getStackTrace());
			log.info("ERRORE getRiepilogoTipologicaSTempi: " + e.getStackTrace());
			e.printStackTrace();
			
		}
		
		System.out.println("getRiepilogoTipologicaSTempi OK");
		log.info("getRiepilogoTipologicaSTempi OK");
		
		return ret;
	}

	@Override
	public List<RiepilogoTipologica> getRiepilogoTipologicaSTesito(
			long idSSessione) {
		List<RiepilogoTipologica> ret = new ArrayList<RiepilogoTipologica>();

		System.out.println("getRiepilogoTipologicaSTesito begin");
		log.info("getRiepilogoTipologicaSTesito begin");
		
		try {

			String sql = "SELECT		"
					+ "	PR.TIPO_DIFESA , PP.COD_CHIUSURA_CORRETTO, "
					+ "	count(PR.TIPO_DIFESA) AS NUM,  "
					+ "	sum(CASE WHEN fs.PRESTAZIONE_ECONOMICA = 'S' THEN 1 ELSE 0 END) AS PRESTAZIONE_EC,   "
					+ "	isNull(sum(FS.IMPORTO_PRESTAZIONE_EROGATA),0) AS IMPORTO_PRESTAZIONE, "
					+ "	isNull(sum(FS.IMPORTO_SPESE_LEGALI),0) AS SPESE_LEGALI,   "
					+ "	isNull(sum(FS.IMPORTO_SPESE_CTU),0) AS IMPORTO_SPESE_CTU   "
					+ "FROM    " + "	AU_S_PRATICA PR,   "
					+ "	ATPO_PRATICHE_SISCO PRS,  " + "	ATPO_FASE_DATI FS,   "
					+ "	ATPO_FASE_POST_PERITALE PP "
					+ "WHERE PR.ID_PRATICA = PRS.ID_PRATICHE_SISCO  "
					+ "	AND PRS.COD_SEDE = FS.COD_SEDE   "
					+ "	AND PRS.FASCICOLO = FS.FASCICOLO   "
					+ "	AND FS.ID_FASE_DATI = PP.ID_FASE_DATI   "
					+ "	AND FS.ID_FASE_DATI =  PP.ID_FASE_DATI  "
					+ "	AND PR.TIPO_DIFESA in ('U02','U03','U04') "
					+ "	AND PR.ESAME_PRATICA = 'C'  "
					+ "	AND pr.ID_S_SESSIONE = " + idSSessione
					+ "GROUP BY PR.TIPO_DIFESA , PP.COD_CHIUSURA_CORRETTO";

			List<Object[]> listaObject = em.createNativeQuery(sql)
					.getResultList();

			for (Object[] row : listaObject) {
				RiepilogoTipologica itemToAdd = new RiepilogoTipologica();

				String codifica = (String) row[0]; // CODIFICA
				if (codifica != null)
					codifica = codifica.trim();
				String codifica2 = (String) row[1]; // CODIFICA
				if (codifica2 != null)
					codifica2 = codifica2.trim();
				Integer num = (Integer) row[2];
				Integer numPrestazioni = (Integer) row[3];
				Double importoPrestazione = ((BigDecimal) row[4]).doubleValue();
				Double speseLegali = ((BigDecimal) row[5]).doubleValue();
				Double speseLegaliCtu = ((BigDecimal) row[6]).doubleValue();

				if (codifica != null)
					itemToAdd.setCodifica(codifica);
				if (codifica2 != null)
					itemToAdd.setCodifica2(codifica2);
				if (num != null)
					itemToAdd.setNum(num.intValue());
				if (importoPrestazione != null)
					if (codifica != null)
						itemToAdd.setImportoPrestazione(importoPrestazione);
				if (numPrestazioni != null)
					itemToAdd.setNumPrestazioni(numPrestazioni);
				if (speseLegali != null)
					itemToAdd.setSpeseLegali(speseLegali);
				if (speseLegaliCtu != null)
					itemToAdd.setSpeseCtu(speseLegaliCtu);
				ret.add(itemToAdd);
			}
			log.debug("getRiepilogoTipologicaSTesito iter");
			System.out.println("getRiepilogoTipologicaSTesito iter");
		} catch (Exception e) {
		
			System.out.println("ERRORE getRiepilogoTipologicaSTesito: " + e.getStackTrace());
			log.info("ERRORE getRiepilogoTipologicaSTesito: " + e.getStackTrace());
			e.printStackTrace();
		
		}
		
		System.out.println("getRiepilogoTipologicaSTesito OK");
		log.info("getRiepilogoTipologicaSTesito OK");
		
		
		return ret;
	}

	@Override
	public int getNumDissensiByTipo(String tipo) {
		String sql = "SELECT count(*) as conteggio,'conteggio'  FROM ATPO_FASE_POST_PERITALE where tipo_dissenso = '"
				+ tipo + "'";
		
		System.out.println("getNumDissensiByTipo begin");
		log.info("getNumDissensiByTipo begin");
		log.info("getNumDissensiByTipo tipo: " + tipo);
		
		List<Object[]> listaObject = em.createNativeQuery(sql).getResultList();
		try{
			for (Object[] row : listaObject) {
				log.info("item type: " + row.getClass());
				int conteggio = 0;
				if (row != null){
					if ( row[0] instanceof Integer ){
						log.info("getNumDissensiByTipo cast Integer");
						conteggio = (Integer) row[0];
					}else if ( row[0] instanceof Double ){
						conteggio = ((Double) row[0]).intValue();
						log.info("getNumDissensiByTipo cast Double");
					}else if ( row[0] instanceof BigDecimal ){
						conteggio = ((BigDecimal) row[0]).intValue();
						log.info("getNumDissensiByTipo cast BigDecimal");
					}else if ( row[0] instanceof Float ){
						conteggio = ((Float) row[0]).intValue();
						log.info("getNumDissensiByTipo cast Float");
					}
				}
				System.out.println("getNumDissensiByTipo conteggio: " + conteggio);
				log.info("getNumDissensiByTipo conteggio: " + conteggio);
				return conteggio;
			}
		} catch (Throwable e) {
			System.out.println("ERRORE getNumDissensiByTipo: " + e.getStackTrace());
			log.error("ERRORE getNumDissensiByTipo: " + e.getStackTrace());
			e.printStackTrace();
		}
		
		System.out.println("getNumDissensiByTipo OK");
		log.info("getNumDissensiByTipo OK");
		
		return 0;
	}

	@Override
	public List<AuTplIsnc> getAuTplIsnc() {
		return em.createNamedQuery(AuTplIsnc.QUERY_TPLISNC_ALL).getResultList();
	}

	@Override
	public List<String> getAtpoFasePeritaleByIdSSessione(long idSSessione) {
		List<String> listRet = new ArrayList<String>();
		
		System.out.println("getAtpoFasePeritaleByIdSSessione begin");
		log.info("getAtpoFasePeritaleByIdSSessione begin");
		
		String sql = "select " + "		rtrim(ltrim(isnull(PE.tipo_dissenso,''))) "
				+ "	from " + "		AU_S_PRATICA PR "
				+ "		,ATPO_PRATICHE_SISCO PRS " + "		,ATPO_FASE_DATI FS "
				+ "		,ATPO_FASE_POST_PERITALE PE " + "	WHERE  "
				+ "		PR.ID_S_SESSIONE = " + idSSessione
				+ "	AND PR.ESAME_PRATICA = 'C' "
				+ "	AND PR.ID_PRATICA = PRS.ID_PRATICHE_SISCO "
				+ "	AND PRS.COD_SEDE = FS.COD_SEDE "
				+ "	AND PRS.FASCICOLO = FS.FASCICOLO "
				+ "	AND FS.ID_FASE_DATI = PE.id_fase_dati and PE.tipo_dissenso is not null";

		List<Object[]> listaObject = em.createNativeQuery(sql).getResultList();
		try{
			for (Object tipoDissenso : listaObject) {
				if (tipoDissenso != null)
					listRet.add((String) tipoDissenso);
			}
		} catch (Exception e) {
		
			System.out.println("ERRORE getAtpoFasePeritaleByIdSSessione: " + e.getStackTrace());
			log.info("ERRORE getAtpoFasePeritaleByIdSSessione: " + e.getStackTrace());
			e.printStackTrace();
		
		}
		
		System.out.println("getAtpoFasePeritaleByIdSSessione OK");
		log.info("getAtpoFasePeritaleByIdSSessione OK");
		
		return listRet;
	}

	@Override
	public Double getPraticheEsamintate(long idSSessione) {
		// SELECT NR_PRATICHE_ESAMINATE FROM AU_S_SESSIONE WHERE ID_S_SESSIONE =
		// 15
		System.out.println("getPraticheEsamintate begin");
		log.info("getPraticheEsamintate begin");
		String sql = "SELECT IsNull(NR_PRATICHE_ESAMINATE,0) FROM AU_S_SESSIONE WHERE ID_S_SESSIONE = "
				+ idSSessione;
		Integer total = (Integer)em.createNativeQuery(sql).getSingleResult();
		System.out.println("getPraticheEsamintate OK");
		log.info("getPraticheEsamintate OK");
		return total.doubleValue();
	}

	@Override
	public List<CalcoloIndicatoriRiepilogoPraticheNonConfFasi> getRiepilogoFasiNonConf(long idSSessione) {
		List<Object[]> lista = new ArrayList<Object[]>();
		System.out.println("getRiepilogoFasiNonConf begin");
		log.info("getRiepilogoFasiNonConf begin");
		List<CalcoloIndicatoriRiepilogoPraticheNonConfFasi> listaRet = 
			new ArrayList<CalcoloIndicatoriRiepilogoPraticheNonConfFasi>();
		String queryStr = 	" SELECT s.ID_M_NONCONF , m.ID_FASE, isnull( s.PESO_NONCONF , 0 ) " +
							" FROM  " +
							"	CRS_SOTTOPROCESSO as crs " + 
							"	join AU_M_NONCONF as m on m.ID_FASE = crs.ID_SOTTOPROCESSO " + 
							"	join AU_S_NONCONF as s on s.ID_M_NONCONF = m.ID_M_NON_CONF " + 
							" WHERE s.ID_S_SESSIONE = " + idSSessione + " order by 2,1";
		
		try {
			lista = em.createNativeQuery(queryStr).getResultList();
			for (Object[] row : lista) {
				CalcoloIndicatoriRiepilogoPraticheNonConfFasi item = 
					new CalcoloIndicatoriRiepilogoPraticheNonConfFasi();
				item.setIdMNonConf((Long)row[0]);
				item.setIdFase((Long)row[1]);
				item.setValorePesato(((BigDecimal)row[2]).doubleValue());
				listaRet.add(item);
			}
		} catch (Exception e) {
			System.out.println("EERRORE getRiepilogoFasiNonConf: " + e.getStackTrace());
			log.info("EERRORE getRiepilogoFasiNonConf: " + e.getStackTrace());
			e.printStackTrace();
		}
		System.out.println("getRiepilogoFasiNonConf OK");
		log.info("getRiepilogoFasiNonConf OK");
		return listaRet;
	}
 
	@Override
	public List<CampagnaDto> getDatiCampagnaVarCompDto(long idCampagna) {
		String queryStr = "select  " + idCampagna 
				+", AU_S_NONCONF.ID_M_NONCONF" +
				", ID_M_VARCOMP" +
				", AU_S_SESSIONE.DATA_INIZIO" +
				", AU_S_SESSIONE.DATA_FINE" +
				", ID_FASE" +
				", AU_S_VARCOMP.NUM  " +
				", AU_S_VARCOMP.PERC_PESATA " +
				"	FROM AU_S_NONCONF, AU_S_SESSIONE, AU_S_VARCOMP, AU_SESSIONI, AU_M_NONCONF " +
				" where AU_S_NONCONF.ID_S_SESSIONE = AU_S_SESSIONE.ID_S_SESSIONE  " +
				" AND AU_S_NONCONF.ID_S_NONCONF   = AU_S_VARCOMP.ID_S_NONCONF " +
				" AND AU_S_SESSIONE.ID_SESSIONE  = AU_SESSIONI.ID_SESSIONE  " +
				" AND AU_S_VARCOMP.ID_M_NONCONF  = AU_M_NONCONF.ID_M_NON_CONF " +
				" AND AU_SESSIONI.ID_CAMPAGNA   = " + idCampagna + 
				" AND AU_S_SESSIONE.STATO_ESAME_SESSIONE   = 'C'";
		List<Object[]> lista = new ArrayList<Object[]>();
		List<CampagnaDto> listRet = new ArrayList<CampagnaDto>();
		try {
			lista = em.createNativeQuery(queryStr).getResultList();
			for (Object[] row : lista) {
				CampagnaDto item = 
					new CampagnaDto();
				item.setIdCampagna((Integer)row[0]);
				item.setID_M_NONCONF((Long)row[1]);
				item.setID_M_VARCOMP((Long)row[2]);
				item.setDATA_INIZIO((Date)row[3]);
				item.setDATA_FINE((Date)row[4]);
				item.setID_FASE((Long)row[5]);
				item.setNUM((Integer)row[6]);
				// item.setValorePesato(((BigDecimal)row[2]).doubleValue());
				item.setPERC_PESATA(((BigDecimal)row[7]).doubleValue());
				listRet.add(item);

			}
		} catch (Exception e) {
			System.out.println("EERRORE getDatiCampagnaVarCompDto: " + e.getStackTrace());
			log.info("EERRORE getDatiCampagnaVarCompDto: " + e.getStackTrace());
			e.printStackTrace();
		}
		
		return listRet;
	}

	@Override
	public List<CampagnaMVarCompDto> getSumiCampagnaByIdMVarCompDto(long idCampagna) {
		String queryStr = "	select   " + 
							"ID_M_VARCOMP  " + 
							", Sum(AU_S_VARCOMP.NUM )   " + 
						"	 " + 
						"	FROM AU_S_NONCONF, AU_S_SESSIONE, AU_S_VARCOMP, AU_SESSIONI, AU_M_NONCONF   " + 
						"	where AU_S_NONCONF.ID_S_SESSIONE = AU_S_SESSIONE.ID_S_SESSIONE    " + 
						"	AND AU_S_NONCONF.ID_S_NONCONF   = AU_S_VARCOMP.ID_S_NONCONF   " + 
						"	AND AU_S_SESSIONE.ID_SESSIONE  = AU_SESSIONI.ID_SESSIONE    " + 
						"	AND AU_S_VARCOMP.ID_M_NONCONF  = AU_M_NONCONF.ID_M_NON_CONF   " + 
						"	AND AU_SESSIONI.ID_CAMPAGNA   =     " + idCampagna +
						"	AND AU_S_SESSIONE.STATO_ESAME_SESSIONE   = 'C' " + 
						"	group by ID_M_VARCOMP";
		List<Object[]> lista = new ArrayList<Object[]>();
		List<CampagnaMVarCompDto> listRet = new ArrayList<CampagnaMVarCompDto>();
		try {
			lista = em.createNativeQuery(queryStr).getResultList();
			for (Object[] row : lista) {
				CampagnaMVarCompDto item = 
					new CampagnaMVarCompDto();
				item.setID_M_VARCOMP((Long)row[0]);
				item.setSUM((Integer)row[1]);
				listRet.add(item);
				/*
				item.setIdMNonConf((Long)row[0]);
				item.setIdFase((Long)row[1]);
				item.setValorePesato(((BigDecimal)row[2]).doubleValue());
				listaRet.add(item);
				*/
			}
		} catch (Exception e) {
			System.out.println("EERRORE getDatiCampagnaVarCompDto: " + e.getStackTrace());
			log.info("EERRORE getDatiCampagnaVarCompDto: " + e.getStackTrace());
			e.printStackTrace();
		}
		
		return listRet;
	}

	@Override
	public void deleteDatiCampagnaVarComp(long idCampagna) {
		em.createNativeQuery(
				"DELETE AU_C_VARCOMP WHERE ID_C_CAMPAGNA = " + idCampagna)
				.executeUpdate();
	}
	
	@Override
	public void insertDatiCampagnaVarComp(AU_C_VARCOMP itemToInsert){
		em.persist(itemToInsert);
	}
	

	@Override
	public List<SoglieDto> getSoglieTipologica() {
		String query = "select MVC.ID_M_NONCONF, ISNC.SOGLIA from AU_M_VARCOMP MVC, AU_TPL_ISNC ISNC where MVC.PESO_VC  = isnc.ID_TPL_ISNC";
		List<Object[]> lista = new ArrayList<Object[]>();
		List<SoglieDto> listRet = new ArrayList<SoglieDto>();
		try {
			lista = em.createNativeQuery(query).getResultList();
			for (Object[] row : lista) {
				SoglieDto item = 
					new SoglieDto();
				item.setID_M_NONCONF((Long)row[0]);
				String soglia = row[1].toString();
				item.setSOGLIA(Double.parseDouble(soglia));
				listRet.add(item);

			}
		} catch (Exception e) {
			System.out.println("EERRORE getDatiCampagnaVarCompDto: " + e.getStackTrace());
			log.info("EERRORE getDatiCampagnaVarCompDto: " + e.getStackTrace());
			e.printStackTrace();
		}
		return listRet;
	}

	@Override
	public void deleteDatiCampagnaNonCConf(long idCampagna) {
		em.createNativeQuery(
				"DELETE AU_C_NONCONF WHERE ID_C_CAMPAGNA = " + idCampagna)
				.executeUpdate();
	}

	@Override
	public List<CampagnaNonConfDto> getDatiCampagnaVNonConfDto(long idCampagna) {
		String query = "select DISTINCT " + 
				"	AU_SESSIONI.ID_CAMPAGNA " + 
				",	AU_S_NONCONF.ID_M_NONCONF " + 
				", 	AU_S_NONCONF.DATA_INIZIO " + 
				", 	AU_S_NONCONF.DATA_FINE " + 
				", 	AU_S_NONCONF.CODICE " + 
				",	AU_S_NONCONF.PESO_NONCONF " + 
				"from " + 
				"AU_S_NONCONF " + 
				",AU_S_SESSIONE " + 
				",AU_S_VARCOMP " + 
				",AU_SESSIONI " + 
				"WHERE  " + 
				"AU_S_NONCONF.ID_S_SESSIONE  = AU_S_SESSIONE.ID_S_SESSIONE " + 
				"AND AU_S_VARCOMP.ID_S_NONCONF = AU_S_NONCONF.ID_S_NONCONF " + 
				"AND AU_S_SESSIONE.ID_SESSIONE = AU_SESSIONI.ID_SESSIONE " + 
				"AND AU_SESSIONI.ID_CAMPAGNA = " + idCampagna +  
				"AND AU_S_SESSIONE.STATO_ESAME_SESSIONE = 'C'";
		List<Object[]> lista = new ArrayList<Object[]>();
		List<CampagnaNonConfDto> listRet = new ArrayList<CampagnaNonConfDto>();
		try {
			lista = em.createNativeQuery(query).getResultList();
			for (Object[] row : lista) {
				CampagnaNonConfDto item = 
					new CampagnaNonConfDto();

				item.setID_CAMPAGNA((Long)row[0]);
				item.setID_M_NONCONF((Long)row[1]);
				item.setDATA_INIZIO((Date)row[2]);
				item.setDATA_FINE((Date)row[3]);
				if (row[4]!=null)item.setCODICE(row[4].toString());
				item.setPESO_NONCONF(((BigDecimal)row[5]).doubleValue());
 
				listRet.add(item);

			}
		} catch (Exception e) {
			System.out.println("EERRORE getDatiCampagnaVarCompDto: " + e.getStackTrace());
			log.info("EERRORE getDatiCampagnaVarCompDto: " + e.getStackTrace());
			e.printStackTrace();
		}
		return listRet;
		
	}

	@Override
	public void insertDatiCampagnaNonConf(AU_C_NONCONF nonConf) {
		em.persist(nonConf);
	}
}
