package it.tecnet.crs.audit.service;

import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoFaseAcquisizioneIstanza;
import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoFaseAutotutelaResistenzaGiudizio;
import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoFaseDati;
import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoFaseEsecuzioneProvvedimenti;
import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoFaseGestioneIstruttoria;
import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoFasePeritale;
import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoFasePostPeritale;
import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoPraticheSISCO;
import it.tecnet.crs.audit.jpa.dao.AuCalcolaIndicatoriDao;
import it.tecnet.crs.indicatori.pratica.CalcoloIndicatoriErrore;
import it.tecnet.crs.indicatori.pratica.CalcoloIndicatoriPratica;
import it.tecnet.crs.indicatori.pratica.CheckPraticaDatiException;
import it.tecnet.crs.indicatori.sessione.CalcoloIndicatoriSessione;
import it.tecnet.crs.jpa.model.AuCampagna;
import it.tecnet.crs.jpa.model.AuInccDes;
import it.tecnet.crs.jpa.model.AuMRischio;
import it.tecnet.crs.jpa.model.AuMRisepr;
import it.tecnet.crs.jpa.model.AuMVarcomp;
import it.tecnet.crs.jpa.model.AuSPraCalIndLog;
import it.tecnet.crs.jpa.model.AuSPratica;
import it.tecnet.crs.jpa.model.AuSPraticaRis;
import it.tecnet.crs.jpa.model.AuSPraticaVarcomp;
import it.tecnet.crs.jpa.model.AuSSessione;
import it.tecnet.crs.util.PraticaUtil;
import it.tecnet.crs.util.SessioneUtil;
import it.tecnet.crs.web.dto.StatoSessionePratica;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

public class AuCalcolaIndicatoriServiceImpl implements
		AuCalcolaIndicatoriService {

	protected static Logger log = Logger
			.getLogger(AuCalcolaIndicatoriServiceImpl.class);

	private AuCalcolaIndicatoriDao auCalcolaIndicatoriDao;

	public AuCalcolaIndicatoriDao getAuCalcolaIndicatoriDao() {
		return auCalcolaIndicatoriDao;
	}

	public void setAuCalcolaIndicatoriDao(
			AuCalcolaIndicatoriDao auCalcolaIndicatoriDao) {
		this.auCalcolaIndicatoriDao = auCalcolaIndicatoriDao;
	}

	@Override
	public <T> Object salva(T obj) {
		return auCalcolaIndicatoriDao.salva(obj);
	}

	@Override
	public StatoSessionePratica canCalculateIndicatori(long idPratica) {
		return auCalcolaIndicatoriDao.canCalculateIndicatori(idPratica);
	}

	@Override
	public boolean isSSsessionOpen(Long idSSsessione) {
		return auCalcolaIndicatoriDao.isSSsessionOpen(idSSsessione);
	}

	@Override
	public boolean cleanCalcoloIndiciPraticaLog(long idPratica) {
		return auCalcolaIndicatoriDao.cleanCalcoloIndiciPraticaLog(idPratica);
	}

	@Override
	public int setEsamePratica(long idPratica, String stato, String userId,
			String userIdToUpdate) {
		return auCalcolaIndicatoriDao.setEsamePratica(idPratica, stato, userId,
				userIdToUpdate);
	}

	@Override
	public int setPraticaInLavorazione(long idPratica, String userId) {
		return auCalcolaIndicatoriDao
				.setPraticaInLavorazione(idPratica, userId);
	}

	@Override
	public int setPraticaInCalcolata(long idVerbale, String userId) {
		return auCalcolaIndicatoriDao.setPraticaInCalcolata(idVerbale, userId);
	}

	@Transactional
	@Override
	public List<AuSPraCalIndLog> calcolaIndicatoriPratica(long idPratica)
			throws Exception {

		List<CalcoloIndicatoriErrore> listaErrori = new ArrayList<CalcoloIndicatoriErrore>();
		CalcoloIndicatoriErrore info = new CalcoloIndicatoriErrore();
		info.setTipoRischio("Inizio calcoloIndicatori Pratica");
		info.setTipoErrore("INFO");
		info.setMessaggio("Calcolo indicatori");
		listaErrori.add(info);

		AuSPratica pratica = auCalcolaIndicatoriDao
				.getSPraticaByIDPratica(idPratica);
		AuCampagna campagna = auCalcolaIndicatoriDao
				.getCampagnaById(auCalcolaIndicatoriDao
						.getIdCambagnaByIdSSessione(pratica.getIdSSessione()));

		AtpoPraticheSISCO praticaSico = auCalcolaIndicatoriDao.findById(
				AtpoPraticheSISCO.class, pratica.getIdPratica());
		String codSede = praticaSico.getCodSede().trim();
		String fascicolo = praticaSico.getFascicolo().trim();

		// ATPO_FASE_DATI
		AtpoFaseDati praticaFd = auCalcolaIndicatoriDao
				.getFaseDatiByCodSedeAndFascicolo(codSede, fascicolo);
		long idFaseDati = praticaFd.getIdfaseDati();

		// ATPO_FASE_POST_PERITALE
		AtpoFasePostPeritale praticaPp = auCalcolaIndicatoriDao
				.getAtpoFasePostPeritaleByIdFaseDati(idFaseDati);
		// ATPO_FASE_ESECUZIONE_PROVVEDIMENTI
		AtpoFaseEsecuzioneProvvedimenti praticaEp = auCalcolaIndicatoriDao
				.getAtpoFaseEsecuzioneProvvedimentiByIdFaseDati(idFaseDati);
		// ATPO_FASE_PERITALE
		AtpoFasePeritale praticaPe = auCalcolaIndicatoriDao
				.getAtpoFasePeritaleByIdFaseDati(idFaseDati);
		// ATPO_FASE_GESTIONE_ISTRUTTORIA
		AtpoFaseGestioneIstruttoria praticaGi = auCalcolaIndicatoriDao
				.getAtpoFaseGestioneIstruttoriaByIdFaseDati(idFaseDati);
		// ATPO_FASE_AUTOTUTELA_RESISTENZA_GIUDIZIO
		AtpoFaseAutotutelaResistenzaGiudizio praticaAr = auCalcolaIndicatoriDao
				.getAtpoFaseAutotutelaResistenzaGiudizioByIdFaseDati(idFaseDati);
		// ATPO_FASE_ACQUISIZIONE_ISTANZA
		AtpoFaseAcquisizioneIstanza praticaAi = auCalcolaIndicatoriDao
				.getAtpoFaseAcquisizioneIstanzaByIdFaseDati(idFaseDati);

		// TIPOLOGICHE
		List<AuMVarcomp> auMVarcomp = auCalcolaIndicatoriDao.getAuMVarcomp();
		List<AuMRischio> mRrischio = auCalcolaIndicatoriDao
				.getRishioByAudit(campagna.getIdAudit());
		List<AuMRisepr> auMRisespr = auCalcolaIndicatoriDao.getAuMRisepr();

		// STRUTTURE DI CALCOLO DA SALVARE
		List<AuSPraticaRis> listaPraticheRisToInsert = new ArrayList<AuSPraticaRis>();
		List<AuSPraticaVarcomp> listaPraticheVarCompToInsert = new ArrayList<AuSPraticaVarcomp>();

		// VERIFICO SE I DATI PER IL CALCOLO CI SIANO TUTTI
		/**/
		if (pratica == null || campagna == null || auMVarcomp == null
				|| mRrischio == null || auMRisespr == null)
			throw new CheckPraticaDatiException(
					"Anagrafiche non presenti per calcolare gli indicatori");

		if (praticaPp == null || praticaPp.getFasePronta() == null
				|| praticaPp.getFasePronta().endsWith("N")) {
			throw new CheckPraticaDatiException(
					"Anagrafica Fase Post Peritale non salvata per il calcolo degli indicatori");
		}  
		if (praticaFd == null || praticaFd.getFasePronta() == null
				|| praticaFd.getFasePronta().endsWith("N")) {
			throw new CheckPraticaDatiException(
					"Anagrafica Fase Dati non salvata per il calcolo degli indicatori");
		} 
		if (praticaEp == null || praticaEp.getFasePronta() == null
				|| praticaEp.getFasePronta().endsWith("N")) {
			throw new CheckPraticaDatiException(
					"Anagrafica Fase Esecuzione Provvedimenti non salvata per il calcolo degli indicatori");
		}
		if (praticaPe == null || praticaPe.getFasePronta() == null
				|| praticaPe.getFasePronta().endsWith("N")) {
			throw new CheckPraticaDatiException(
					"Anagrafica Fase Peritale non salvata per il calcolo degli indicatori");
		}
		if (praticaGi == null || praticaGi.getFasePronta() == null
				|| praticaGi.getFasePronta().endsWith("N")) {
			throw new CheckPraticaDatiException(
					"Anagrafica Fase Gestione Istruttoria non salvata per il calcolo degli indicatori");
		}
		if (praticaAr == null || praticaAr.getFasePronta() == null
				|| praticaAr.getFasePronta().endsWith("N")) {
			throw new CheckPraticaDatiException(
					"Anagrafica Fase Autotutela Resistenza Giudizio non salvata per il calcolo degli indicatori");
		} 
		if (praticaAi == null || praticaAi.getFasePronta() == null
				|| praticaAi.getFasePronta().endsWith("N")) {
			throw new CheckPraticaDatiException(
					"Anagrafica Fase Acquisizione Istanza non salvata per il calcolo degli indicatori");
		}

		if (pratica != null) {
			auCalcolaIndicatoriDao.deleteSPtaricaRisByIdSPratica(pratica
					.getIdSPratica());
			auCalcolaIndicatoriDao.deleteSPtaricaVarcompByIdSPratica(pratica
					.getIdSPratica());
		}

		CalcoloIndicatoriPratica colcoloIndicatoriPratica = new CalcoloIndicatoriPratica(
				campagna, pratica, mRrischio, praticaPp, praticaFd, praticaEp,
				praticaPe, praticaGi, praticaAr, praticaAi,
				listaPraticheRisToInsert, listaPraticheVarCompToInsert,
				auMVarcomp, listaErrori, pratica.getIdSSessione(), pratica
						.getIdSPratica(), praticaSico, auMRisespr);

		colcoloIndicatoriPratica.calcolaIndicatori();
		// populateForTest(colcoloIndicatoriPratica);

		if (colcoloIndicatoriPratica.getPraticheRis().size() > 0) {
			// SALVATAGGIO AU_S_PRATICA_RIS
			for (AuSPraticaRis item : colcoloIndicatoriPratica.getPraticheRis()) {
				auCalcolaIndicatoriDao.salva(item);
			}
		}

		if (colcoloIndicatoriPratica.getPraticheVarcomp().size() > 0) {
			// SALVATAGGIO AU_S_PRATICA_varcomp
			for (AuSPraticaVarcomp praticaVarComp : colcoloIndicatoriPratica
					.getPraticheVarcomp()) {
				auCalcolaIndicatoriDao.salva(praticaVarComp);
			}
		}

		// SALVO AU_S_PRATICA
		pratica.setEsamePratica(PraticaUtil.ESAME_PRATICA_CHIUSO);
		auCalcolaIndicatoriDao.salva(pratica);

		CalcoloIndicatoriErrore infoFine = new CalcoloIndicatoriErrore();
		infoFine.setTipoRischio("Fine calcoloIndicatori Pratica");
		infoFine.setTipoErrore("INFO");
		infoFine.setMessaggio("Calcolo indicatori");
		listaErrori.add(infoFine);

		List<AuSPraCalIndLog> logList = new ArrayList<AuSPraCalIndLog>();
		for (CalcoloIndicatoriErrore errore : colcoloIndicatoriPratica
				.getListaErrori()) {
			AuSPraCalIndLog errToIns = new AuSPraCalIndLog();
			errToIns.setDataInserimento(new Date());
			errToIns.setIdPratica(idPratica);
			errToIns.setMessaggio(errore.getMessaggio());
			errToIns.setTipoCalcolo(errore.getTipoRischio());
			errToIns.setTipoErrore(errore.getTipoErrore());
			errToIns.setIdSSessione(pratica.getIdSSessione());
			logList.add(errToIns);
		}

		return logList;
	}

	@SuppressWarnings("unused")
	private void populateForTest(
			CalcoloIndicatoriPratica colcoloIndicatoriPratica) {
		List<AuSPraticaRis> listaAuSPraticaRis = new ArrayList<AuSPraticaRis>();
		List<AuSPraticaVarcomp> listaAuSPraticaVarcomp = new ArrayList<AuSPraticaVarcomp>();

		AuSPraticaRis AuSPraticaRisItem = new AuSPraticaRis();
		// AuSPraticaRisItem.setCodEspressioneDiRischio("A003");
		AuSPraticaRisItem.setIdMRischio(5);
		AuSPraticaRisItem.setIdSPratica(1);
		AuSPraticaVarcomp AuSPraticaVarcompItem = new AuSPraticaVarcomp();
		AuSPraticaVarcompItem.setIdNonConf(1);
		AuSPraticaVarcompItem.setIdSPratica(1);
		listaAuSPraticaRis.add(AuSPraticaRisItem);
		colcoloIndicatoriPratica.setPraticheRis(listaAuSPraticaRis);
		listaAuSPraticaVarcomp.add(AuSPraticaVarcompItem);
		colcoloIndicatoriPratica.setPraticheVarcomp(listaAuSPraticaVarcomp);
	}

	@Override
	public void cleanCalcoloIndiciSessione(long idSSessione) {
		auCalcolaIndicatoriDao.cleanCalcoloIndiciSessione(idSSessione);
	}

	@Transactional
	@Override
	public List<AuSPraCalIndLog> calcolaIndicatoriSessione(long idSSessione)
			throws Exception {
		System.out.println("1 - calcolaIndicatoriSessione(long idSSessione) - Inizio");
		log.info("1 - calcolaIndicatoriSessione(long idSSessione) - Inizio");
		List<AuSPraCalIndLog> ret = new ArrayList<AuSPraCalIndLog>();
		SessioneUtil.insertLog(ret, "Inizio Calcolo Sessione", idSSessione,
				null, null);
		
		log.info("2 - auCalcolaIndicatoriDao.cleanCalcoloIndiciSessione(idSSessione);");
		System.out.println("2 - auCalcolaIndicatoriDao.cleanCalcoloIndiciSessione(idSSessione);");
		// PULIZIA DEI CALCOLI PRECEDENTEMENTE EFFETTUATI
		auCalcolaIndicatoriDao.cleanCalcoloIndiciSessione(idSSessione);
		AuSSessione auSSessione = auCalcolaIndicatoriDao.findById(
				AuSSessione.class, idSSessione);
		
		System.out.println("3 - calcolaIndicatoriSessione = new CalcoloIndicatoriSessione(auSSessione, auCalcolaIndicatoriDao);");
		log.info("3 - calcolaIndicatoriSessione = new CalcoloIndicatoriSessione(auSSessione, auCalcolaIndicatoriDao);");
		CalcoloIndicatoriSessione calcolaIndicatoriSessione = new CalcoloIndicatoriSessione(
				auSSessione, auCalcolaIndicatoriDao);

		System.out.println("4 - calcolaIndicatoriSessione.calcolaIndicatoriSessione(ret);");
		log.info("4 - calcolaIndicatoriSessione.calcolaIndicatoriSessione(ret);");
		
		try{
			calcolaIndicatoriSessione.calcolaIndicatoriSessione(ret);
		}catch(Exception ex){
			System.out.println("5 - (ERRORE) calcolaIndicatoriSessione(long idSSessione):" + ex.getStackTrace());
			log.info("5 - (ERRORE) calcolaIndicatoriSessione(long idSSessione):" + ex.getStackTrace());
			ex.printStackTrace();
			throw ex;
		}
		
		SessioneUtil.insertLog(ret, "Fine Calcolo Sessione", idSSessione, null,
				null);
		System.out.println("5 - calcolaIndicatoriSessione(long idSSessione) - Fine");
		log.info("5 - calcolaIndicatoriSessione(long idSSessione) - Fine;");
		return ret;
	}

	@Override
	public void riapriSessione(long idSSessione) {
		auCalcolaIndicatoriDao.riapriSessione(idSSessione);
	}

	@Override
	public List<AuInccDes> getAuInccDes() {
		return auCalcolaIndicatoriDao.getAuInccDes();
	}

	@Override
	public Double getPraticheEsamintate(long idSSessione) {
		return auCalcolaIndicatoriDao.getPraticheEsamintate(idSSessione);
	}

}
