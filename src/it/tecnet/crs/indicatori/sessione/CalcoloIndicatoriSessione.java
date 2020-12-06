package it.tecnet.crs.indicatori.sessione;

import it.tecnet.crs.audit.jpa.dao.AuCalcolaIndicatoriDao;
import it.tecnet.crs.audit.web.dto.CalcoloIndicatoriRiepilogoPraticheNonConfFasi;
import it.tecnet.crs.indicatori.pratica.NoPraticheException;
import it.tecnet.crs.jpa.model.AuInccDes;
import it.tecnet.crs.jpa.model.AuMNonConf;
import it.tecnet.crs.jpa.model.AuMRischio;
import it.tecnet.crs.jpa.model.AuMRisepr;
import it.tecnet.crs.jpa.model.AuMVarcomp;
import it.tecnet.crs.jpa.model.AuSNonconf;
import it.tecnet.crs.jpa.model.AuSPraCalIndLog;
import it.tecnet.crs.jpa.model.AuSPratica;
import it.tecnet.crs.jpa.model.AuSPraticaRis;
import it.tecnet.crs.jpa.model.AuSPraticaVarcomp;
import it.tecnet.crs.jpa.model.AuSRischio;
import it.tecnet.crs.jpa.model.AuSRisespr;
import it.tecnet.crs.jpa.model.AuSRisrag;
import it.tecnet.crs.jpa.model.AuSSessione;
import it.tecnet.crs.jpa.model.AuSTempi;
import it.tecnet.crs.jpa.model.AuSTesito;
import it.tecnet.crs.jpa.model.AuSTfascicolo;
import it.tecnet.crs.jpa.model.AuSTvalori;
import it.tecnet.crs.jpa.model.AuSVarcomp;
import it.tecnet.crs.jpa.model.AuTdocmanc;
import it.tecnet.crs.jpa.model.AuTplIsnc;
import it.tecnet.crs.jpa.model.AuTplTipologiche;
import it.tecnet.crs.util.SessioneUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

public class CalcoloIndicatoriSessione {
	protected static Logger log = Logger
			.getLogger(CalcoloIndicatoriSessione.class);

	private AuSSessione sSessione;
	private AuCalcolaIndicatoriDao auCalcolaIndicatoriDao;

	public CalcoloIndicatoriSessione(AuSSessione sessione,
			AuCalcolaIndicatoriDao auCalcolaIndicatoriDao) {
		super();
		this.sSessione = sessione;
		this.auCalcolaIndicatoriDao = auCalcolaIndicatoriDao;
	}

	@Transactional
	public void calcolaIndicatoriSessione(List<AuSPraCalIndLog> listLogDb) throws NoPraticheException {
		
		SessioneUtil.insertLog(listLogDb, "Inizio Calcolo Indicatori per idSessione: "
				+ sSessione.getIdSessione(), sSessione.getIdSSessione(), null, null);
		
		System.out.println("Inizio Calcolo Indicatori per idSessione: "
				+ sSessione.getIdSessione());
		
		log.info("Inizio Calcolo Indicatori per idSessione: "
				+ sSessione.getIdSessione());
		
		Date dataCalcolo = new Date();
		// PULIZIA TAB DI CALCOLOPRIMA DEI CALCOLI
		SessioneUtil.insertLog(listLogDb, "Pulizia tab. per il  calcolo sessione", sSessione.getIdSSessione(), null, null);
		auCalcolaIndicatoriDao.cleanCalcoloIndiciSessione(sSessione.getIdSSessione());
		
		List<AuSPratica> listSPratiche =  auCalcolaIndicatoriDao
										.getSPraticaByIdSSessione(sSessione.getIdSSessione());

		List<AuMRisepr> listaMRisEspr = auCalcolaIndicatoriDao.getAuMRisepr();
		
		if (listSPratiche.size() == 0){
			SessioneUtil.insertLog(listLogDb, "Non ci sono pratiche", sSessione.getIdSSessione(), null, null);
			throw new NoPraticheException("Non ci sono pratiche");
		}
		
		SessioneUtil.insertLog(listLogDb, "getSPraticaClosedByIdSSessione", sSessione.getIdSSessione(), null, null);
		List<AuSPratica> listSPraticheClosed =  auCalcolaIndicatoriDao
										.getSPraticaClosedByIdSSessione(sSessione.getIdSSessione());
		
		Integer numeroPraticheEsaminate = listSPraticheClosed.size();
		
		List<AuSPraticaRis> listSPraticheRisClosed =  auCalcolaIndicatoriDao
							.getSPraticaRisClosedByIdSSessione(sSessione.getIdSSessione());

		System.out.println("AGGIORNO SESSIONE CON IL NUMERO PRATICHE E IL NUMERO PRATICHE ESAMINATE "
				+ sSessione.getIdSessione());
		
		log.info("AGGIORNO SESSIONE CON IL NUMERO PRATICHE E IL NUMERO PRATICHE ESAMINATE "
				+ sSessione.getIdSessione());
		// AGGIORNO SESSIONE CON IL NUMERO PRATICHE E IL NUMERO PRATICHE ESAMINATE
		sSessione.setNrPratiche(listSPratiche.size());
		sSessione.setNrPraticheEsaminate(listSPraticheClosed.size());
		Integer numeroPraticheEsaminateSessione = listSPraticheClosed.size();
		
		List<String> listTipoDissenso = auCalcolaIndicatoriDao.getAtpoFasePeritaleByIdSSessione(sSessione.getIdSSessione());
		
		System.out.println("AGGIORNAMENTO SESSIONE CON IL NUMERO PRATICHE E IL NUMERO PRATICHE ESAMINATE "
				+ sSessione.getIdSessione() + " OK");
		
		log.info("AGGIORNAMENTO SESSIONE CON IL NUMERO PRATICHE E IL NUMERO PRATICHE ESAMINATE "
				+ sSessione.getIdSessione() + " OK");
		
		/* ----------------------------------------------------------------------------------------------------
		 * CALCOLARE INFO SU SINGOLA VARIANTE COMPORTAMENTALE – aggiornare S-VarComp
		 */
		System.out.println("CALCOLARE INFO SU SINGOLA VARIANTE COMPORTAMENTALE – aggiornare S-VarComp "
				+ sSessione.getIdSessione());
		
		log.info("CALCOLARE INFO SU SINGOLA VARIANTE COMPORTAMENTALE – aggiornare S-VarComp "
				+ sSessione.getIdSessione());
		
		List<AuTplIsnc> listAuTplIsnc = auCalcolaIndicatoriDao.getAuTplIsnc();
		SessioneUtil.insertLog(listLogDb, "CALCOLARE INFO SU SINGOLA VARIANTE COMPORTAMENTALE – aggiornare S-VarComp", sSessione.getIdSSessione(), null, null);
		List<AuMVarcomp> auMVarComp = auCalcolaIndicatoriDao.getAuMVarcomp();
		List<AuSPraticaVarcomp> auSPraticaClosedVarcomp = auCalcolaIndicatoriDao
											.getAuSPraticaClosedVarcompBySessione(sSessione.getIdSSessione());
		
		List<AuSVarcomp> auSVarcomp = new ArrayList<AuSVarcomp>();
		
		CalcoloInfoSuVarComp calcoloInfoSuVarComp 
						= new CalcoloInfoSuVarComp(auMVarComp, auSPraticaClosedVarcomp, auSVarcomp, listAuTplIsnc);
		
		System.out.println("CALCOLARE INFO SU SINGOLA VARIANTE COMPORTAMENTALE – salvataggio S-VarComp "
				+ sSessione.getIdSessione());
		
		
		log.info("CALCOLARE INFO SU SINGOLA VARIANTE COMPORTAMENTALE – salvataggio S-VarComp "
				+ sSessione.getIdSessione());
		
		auSVarcomp = calcoloInfoSuVarComp.calcoloInfoSuVarComp();
		SessioneUtil.insertLog(listLogDb, "CALCOLARE INFO SU SINGOLA VARIANTE COMPORTAMENTALE – salvataggio S-VarComp", sSessione.getIdSSessione(), null, null);
		
		System.out.println("CALCOLARE INFO SU SINGOLA VARIANTE COMPORTAMENTALE – salvataggio S-VarComp : auSVarcomp = calcoloInfoSuVarComp.calcoloInfoSuVarComp(); "
				+ sSessione.getIdSessione());
		
		log.info("CALCOLARE INFO SU SINGOLA VARIANTE COMPORTAMENTALE – salvataggio S-VarComp : auSVarcomp = calcoloInfoSuVarComp.calcoloInfoSuVarComp(); "
				+ sSessione.getIdSessione());
		
		for (AuSVarcomp item : auSVarcomp) {
			item.setDataInizio(dataCalcolo);
			item.setDataFine(dataCalcolo);
			//auCalcolaIndicatoriDao.persist(item);
		}
		
		
		System.out.println("CALCOLARE INFO SU SINGOLA VARIANTE COMPORTAMENTALE – salvataggio S-VarComp : "
				+ sSessione.getIdSessione() + " OK");
		
		log.info("CALCOLARE INFO SU SINGOLA VARIANTE COMPORTAMENTALE – salvataggio S-VarComp : "
				+ sSessione.getIdSessione()  + " OK" );
		
		/* ----------------------------------------------------------------------------------------------------| */
		/* ----------------------------------------------------------------------------------------------------
		 * CALCOLARE INFO SU SINGOLA NON CONFORMITA' – aggiornare S-NonConf
		 */
		System.out.println("CALCOLARE INFO SU SINGOLA NON CONFORMITA' – aggiornare S-NonConf "
				+ sSessione.getIdSessione());
		
		log.info("CALCOLARE INFO SU SINGOLA NON CONFORMITA' – aggiornare S-NonConf "
				+ sSessione.getIdSessione());
				
		
		SessioneUtil.insertLog(listLogDb, "CALCOLARE INFO SU SINGOLA NON CONFORMITA' – aggiornare S-NonConf", sSessione.getIdSSessione(), null, null);
		List<AuMNonConf> listAuMNonConf = auCalcolaIndicatoriDao.getAuNonconf();
		List <AuSNonconf> listAuSNonconf = new ArrayList<AuSNonconf>();
		List<AuSTvalori> listAuTValori = auCalcolaIndicatoriDao.getAuSTvalori();
		CalcoloInfoSuNonConf calcoloInfoSuNonConf = 
					new CalcoloInfoSuNonConf(	auSVarcomp, 
												listAuMNonConf, 
												listAuSNonconf, 
												sSessione,
												auMVarComp,
												listAuTValori );
		
		listAuSNonconf = calcoloInfoSuNonConf.calcoloInfoSuNonConf();
		System.out.println("CALCOLARE INFO SU SINGOLA NON CONFORMITA' – salvataggio S-NonConf "
				+ sSessione.getIdSessione());
		
		log.info("CALCOLARE INFO SU SINGOLA NON CONFORMITA' – salvataggio S-NonConf "
				+ sSessione.getIdSessione());
		
		
		SessioneUtil.insertLog(listLogDb, "CALCOLARE INFO SU SINGOLA NON CONFORMITA' – salvataggio S-NonConf", sSessione.getIdSSessione(), null, null);
		for (AuSNonconf auSNonconf : listAuSNonconf) {
			auSNonconf.setDataInizio(dataCalcolo);
			auSNonconf.setDataFine(dataCalcolo);
			
			// SALVO LA NON CONFORMITA
			auCalcolaIndicatoriDao.persist(auSNonconf);
			Long idNonConf = auSNonconf.getIdMNonconf();
			Long idSNonConf = auSNonconf.getIdSNonconf();
			
			/*
			 * setto idSNonConf salvata alle relative Variabili comportamentali
			 */
			for (AuSVarcomp auSVarcompItem : auSVarcomp) {
				if( auSVarcompItem.getIdMNonconf() ==  idNonConf){
					auSVarcompItem.setDataInizio(dataCalcolo);
					auSVarcompItem.setDataFine(dataCalcolo);
					auSVarcompItem.setIdSNonconf(idSNonConf);
					auCalcolaIndicatoriDao.persist(auSVarcompItem);
				}
			}
			
		}
		
		System.out.println("AGGIORNO IL VALORE_PESATO_FASE "
				+ sSessione.getIdSessione());
		
		log.info("AGGIORNO IL VALORE_PESATO_FASE "
				+ sSessione.getIdSessione());
		
		// AGGIORNO IL VALORE_PESATO_FASE
		List<CalcoloIndicatoriRiepilogoPraticheNonConfFasi> listaRiepilogoNonConfFasi = auCalcolaIndicatoriDao.getRiepilogoFasiNonConf(sSessione.getIdSSessione());
		for (AuSNonconf auSNonconf : listAuSNonconf) {
			Long idFase = SessioneUtil.getIdFaseByIdMNonconf(auSNonconf.getIdMNonconf(),listaRiepilogoNonConfFasi);
			if (idFase != null && idFase.doubleValue() > 0d){
				Double sumValorePesatoFase = SessioneUtil.getSumByFase(idFase,listaRiepilogoNonConfFasi);
				if (sumValorePesatoFase != null && sumValorePesatoFase.doubleValue() > 0d
						&&  auSNonconf.getValorePesatoFase() > 0d){
					double valoreInccFase = auSNonconf.getValorePesatoFase() / sumValorePesatoFase.doubleValue();
					auSNonconf.setValorePesatoFase( valoreInccFase );
				}else{
					auSNonconf.setValorePesatoFase(0D);
				}
			}else{
				auSNonconf.setValorePesatoFase(0D);
			}
		}
		System.out.println("AGGIORNO IL VALORE_PESATO_FASE "
				+ sSessione.getIdSessione() + " OK");
		
		log.info("AGGIORNO IL VALORE_PESATO_FASE "
				+ sSessione.getIdSessione() + " OK");
		/* ----------------------------------------------------------------------------------------------------| */
		/* ----------------------------------------------------------------------------------------------------
		 * CALCOLARE INFO SU SINGOLA ESPRESSIONE DI RISCHIO – aggiornare S-RisEspr 
		 */
		System.out.println("CALCOLARE INFO SU SINGOLA ESPRESSIONE DI RISCHIO – aggiornare S-RisEspr "
				+ sSessione.getIdSessione());
		
		log.info("CALCOLARE INFO SU SINGOLA ESPRESSIONE DI RISCHIO – aggiornare S-RisEspr "
				+ sSessione.getIdSessione());
		
		SessioneUtil.insertLog(listLogDb, "CALCOLARE INFO SU SINGOLA ESPRESSIONE DI RISCHIO – aggiornare S-RisEspr", sSessione.getIdSSessione(), null, null);

		HashMap<Long, Long> numPerStessoRischio = SessioneUtil.getNumPerStessoRischio(listSPraticheRisClosed);
		List<AuSRisespr> listAuSRisespr = new ArrayList<AuSRisespr>();
		
		CalcoloInfoSuSingolaEsprRischio calcoloInfoSuSingolaEsprRischio =
							new CalcoloInfoSuSingolaEsprRischio(listAuSRisespr, 
																listSPraticheRisClosed,
																/* numConStessoRischioConRaggruppamento3, */
																numPerStessoRischio,
																numeroPraticheEsaminateSessione);
 				
		listAuSRisespr = calcoloInfoSuSingolaEsprRischio.calcoloInfoSuSingolaEsprRischio();
		
		/*
		 * INSERISCO S-RISCHIO PER IL RELATIVO S-ESPR
		 * E POI INSERISCO IL RELATIVO RISCHIO
		 * POI IL RELATIVO S-ESPR
		 */
		System.out.println("INSERISCO S-RISCHIO PER IL RELATIVO S-ESPR E POI INSERISCO IL RELATIVO RISCHIO POI IL RELATIVO S-ESPR "
				+ sSessione.getIdSessione());
		
		log.info("INSERISCO S-RISCHIO PER IL RELATIVO S-ESPR E POI INSERISCO IL RELATIVO RISCHIO POI IL RELATIVO S-ESPR "
				+ sSessione.getIdSessione());
		
		SessioneUtil.insertLog(listLogDb, "CALCOLARE INFO SU SINGOLA ESPRESSIONE DI RISCHIO – salvataggio S-RisEspr", sSessione.getIdSSessione(), null, null);
		HashMap<Long, AuSRischio> listaRischio = new HashMap<Long, AuSRischio>();
		for (AuSRisespr risEspr : listAuSRisespr) {
			// VERIFICO SE idMRischio DELL' ESPR SIA PRESENTE NEL CASO LO INSERISCO
			AuSRischio rischio = listaRischio.get(risEspr.getIdMRischio());
			
			if (rischio == null ){
				rischio = new AuSRischio();
				rischio.setDataInizio(dataCalcolo);
				rischio.setDataFine(dataCalcolo);
				rischio.setIdMRischio(risEspr.getIdMRischio());
				rischio.setIdSSessione(sSessione.getIdSSessione());
				rischio = (AuSRischio) auCalcolaIndicatoriDao.salva(rischio);
				listaRischio.put(risEspr.getIdMRischio(),rischio);
			}
			
			risEspr.setDataInizio(dataCalcolo);
			risEspr.setDataFine(dataCalcolo);
			
			// SETTO idSRischio nell risEspr 
			risEspr.setIdSRischio(rischio.getIdSRischio());
			auCalcolaIndicatoriDao.persist(risEspr);
		}
		
		System.out.println("INSERISCO S-RISCHIO PER IL RELATIVO S-ESPR E POI INSERISCO IL RELATIVO RISCHIO POI IL RELATIVO S-ESPR "
				+ sSessione.getIdSessione() + " OK");
		
		log.info("INSERISCO S-RISCHIO PER IL RELATIVO S-ESPR E POI INSERISCO IL RELATIVO RISCHIO POI IL RELATIVO S-ESPR "
				+ sSessione.getIdSessione() + " OK");
		/* ----------------------------------------------------------------------------------------------------| */
		/* ----------------------------------------------------------------------------------------------------
		 * CALCOLARE INFO SU SINGOLO RAGGRUPPAMENTO DI ESPRESSIONE DI RISCHIO - aggiornare S-RigRag 
		 */
		System.out.println("CALCOLARE INFO SU SINGOLO RAGGRUPPAMENTO DI ESPRESSIONE DI RISCHIO - aggiornare S-RigRag "
				+ sSessione.getIdSessione());
		
		log.info("CALCOLARE INFO SU SINGOLO RAGGRUPPAMENTO DI ESPRESSIONE DI RISCHIO - aggiornare S-RigRag "
				+ sSessione.getIdSessione());
		
		SessioneUtil.insertLog(listLogDb, "CALCOLARE INFO SU SINGOLO RAGGRUPPAMENTO DI ESPRESSIONE DI RISCHIO - aggiornare S-RigRag", sSessione.getIdSSessione(), null, null);
		
		List<AuTotH3PerRischio> numConStessoRischioConRaggruppamento3 
			= auCalcolaIndicatoriDao
				.getNumPraticheRischiH3NonSoggette(sSessione.getIdSSessione());
		
		
		List<AuSRisrag> listAuSRisrag = new ArrayList<AuSRisrag>();
		
		CalcoloInfoSuSingoloRaggruDiEsprRischio calcoloInfoSuSingoloRaggruDiEsprRischio = 
							new CalcoloInfoSuSingoloRaggruDiEsprRischio( listAuSRisrag,
																		 numConStessoRischioConRaggruppamento3,
																		 sSessione.getIdSSessione());
		
		List<Object[]> listaRisRagObject = 
				(List<Object[]>) auCalcolaIndicatoriDao.getRischioRaggruppamento(sSessione.getIdSSessione());
		
		
		listAuSRisrag = calcoloInfoSuSingoloRaggruDiEsprRischio.calcoloInfoSuSingoloRaggruDiEsprRischio(listaRisRagObject,numeroPraticheEsaminateSessione);
		
		SessioneUtil.insertLog(listLogDb, "CALCOLARE INFO SU SINGOLO RAGGRUPPAMENTO DI ESPRESSIONE DI RISCHIO - salvataggio S-RigRag", sSessione.getIdSSessione(), null, null);
		for (AuSRisrag auSRisrag : listAuSRisrag) {
			auSRisrag.setDataInizio(dataCalcolo);
			auSRisrag.setDataFine(dataCalcolo);
			auCalcolaIndicatoriDao.persist(auSRisrag);
		}

		System.out.println("UPDATE ESPRRISCHIO "
				+ sSessione.getIdSessione());

		log.info("UPDATE ESPRRISCHIO "
				+ sSessione.getIdSessione());
		
		
		// UPDATE ESPRRISCHIO
		List<AuTotH3PerRischio> numConStessoRischioConRaggruppamento4 = new ArrayList<AuTotH3PerRischio>();
		
		for (AuSRisespr auSRisespr : listAuSRisespr) {
			boolean notespr3 = true;
			AuTotH3PerRischio itemToAdd = new AuTotH3PerRischio();
			
			for (AuMRisepr auMRisepr : listaMRisEspr) {
								
				if (auMRisepr.getIdMRisepr()  == auSRisespr.getIdMRisespr()){
					
					itemToAdd.setSomma(new Long(auSRisespr.getNum()));
					itemToAdd.setIdRischio(auSRisespr.getIdMRischio());
					itemToAdd.setRaggruppamento(auMRisepr.getRaggruppamentoRischio()
							.trim());
					
					if (auMRisepr.getRaggruppamentoRischio().trim().equals("3")){
						notespr3=false;
					}					
				}
			}
			
			numConStessoRischioConRaggruppamento4.add(itemToAdd);
			
			if(notespr3){
				Long totConStessoRischio = calcoloInfoSuSingoloRaggruDiEsprRischio.getNumConStessoRischio(auSRisespr.getIdMRischio());
				if (totConStessoRischio > 0){
					Double suPs = auSRisespr.getNum() / totConStessoRischio.doubleValue();
					auSRisespr.setSuPs(suPs);
				}else{
					auSRisespr.setSuPs(0d);
				}
			}else{
				auSRisespr.setSuPs(0d);
			}
		}
		
		System.out.println("UPDATE ESPRRISCHIO "
				+ sSessione.getIdSessione() + " OK");

		log.info("UPDATE ESPRRISCHIO "
				+ sSessione.getIdSessione() + " OK");
		
		/* ----------------------------------------------------------------------------------------------------| */
		/* ----------------------------------------------------------------------------------------------------
		 * CALCOLARE INFO SU SINGOLO RISCHIO – aggiornare S-Rischio
		 */
		System.out.println("CALCOLARE INFO SU SINGOLO RISCHIO – aggiornare S-Rischio "
				+ sSessione.getIdSessione());
		
		log.info("CALCOLARE INFO SU SINGOLO RISCHIO – aggiornare S-Rischio "
				+ sSessione.getIdSessione());
		
		
		SessioneUtil.insertLog(listLogDb, "CALCOLARE INFO SU SINGOLO RISCHIO – aggiornare S-Rischio", sSessione.getIdSSessione(), null, null);
		List<AuMRischio> listAuMRischio = auCalcolaIndicatoriDao.getAuMRischio();
		
		CalcoloInfoSuSingoloRischio calcoloInfoSuSingoloRischio =
										new CalcoloInfoSuSingoloRischio(listAuSRisespr,
												 						numConStessoRischioConRaggruppamento3,
												 						numConStessoRischioConRaggruppamento4,
												 						listAuMRischio, 
												 						listaRischio,
												 						numeroPraticheEsaminate.longValue());
		
		calcoloInfoSuSingoloRischio.calcoloInfoSuSingoloRischio();
		
		SessioneUtil.insertLog(listLogDb, "CALCOLARE INFO SU SINGOLO RISCHIO – salvataggio S-Rischio", sSessione.getIdSSessione(), null, null);
		for (Entry<Long, AuSRischio> aurRischio : listaRischio.entrySet()) {
			auCalcolaIndicatoriDao.persist(aurRischio.getValue());
		}
		
		System.out.println("CALCOLARE INFO SU SINGOLO RISCHIO – aggiornare S-Rischio "
				+ sSessione.getIdSessione() + " OK ");
		
		log.info("CALCOLARE INFO SU SINGOLO RISCHIO – aggiornare S-Rischio "
				+ sSessione.getIdSessione() + " OK ");
		
		/* ----------------------------------------------------------------------------------------------------| */
		/* ----------------------------------------------------------------------------------------------------
		 * CALCOLARE INFO SULLA SESSIONE – aggiornare S-Sessione
		 */
		System.out.println("CALCOLARE INFO SULLA SESSIONE – aggiornare S-Sessione "
				+ sSessione.getIdSessione());

		log.info("CALCOLARE INFO SULLA SESSIONE – aggiornare S-Sessione "
				+ sSessione.getIdSessione());
		
		SessioneUtil.insertLog(listLogDb, "CALCOLARE INFO SULLA SESSIONE – aggiornare S-Sessione", sSessione.getIdSSessione(), null, null);
		Double totalePesiValoreAssoluto = SessioneUtil.getTotalePesiValoreAssoluto(listaRischio);
		List<AuInccDes> listAuInccDes = auCalcolaIndicatoriDao.getAuInccDes();
		
		CalcoloInfoSuSessione calcoloInfoSuSessione = 
										new CalcoloInfoSuSessione(totalePesiValoreAssoluto, 
																  listAuSNonconf,
																  listAuInccDes,
																  sSessione,
																  listTipoDissenso);
		
		SessioneUtil.insertLog(listLogDb, "CALCOLARE INFO SULLA SESSIONE – salvataggio S-Sessione", sSessione.getIdSSessione(), null, null);
		calcoloInfoSuSessione.calcoloInfoSuSessione();
		
		System.out.println("CALCOLARE INFO SULLA SESSIONE – aggiornare S-Sessione "
				+ sSessione.getIdSessione() + " OK" );

		log.info("CALCOLARE INFO SULLA SESSIONE – aggiornare S-Sessione "
				+ sSessione.getIdSessione()+ " OK");
				
		/* ----------------------------------------------------------------------------------------------------| */
		/* ----------------------------------------------------------------------------------------------------
		 * CALCOLARE INFO SULLA S-T*
		 */
		System.out.println("CALCOLARE INFO SULLA S-T* "
				+ sSessione.getIdSessione());
		
		
		log.info("CALCOLARE INFO SULLA S-T* "
				+ sSessione.getIdSessione());
		
		
		SessioneUtil.insertLog(listLogDb, "CALCOLARE INFO SULLA S-T ", sSessione.getIdSSessione(), null, null);
		List<AuTplTipologiche> tipologica = auCalcolaIndicatoriDao.getTipologica();

		CalcoloInfoST calcoloInfoST = new CalcoloInfoST(sSessione, tipologica, listSPraticheClosed);
		
		System.out.println("CALCOLARE INFO SULLA S-T AuTdocmanc "
				+ sSessione.getIdSessione());
		
		log.info("CALCOLARE INFO SULLA S-T AuTdocmanc "
				+ sSessione.getIdSessione());
		
		SessioneUtil.insertLog(listLogDb, "CALCOLARE INFO SULLA S-T AuTdocmanc", sSessione.getIdSSessione(), null, null);
		List<RiepilogoTipologica> riepilogoTipologicaTDocmanc = auCalcolaIndicatoriDao.getRiepilogoTipologicaTDocmanc(sSessione.getIdSSessione());
		if (riepilogoTipologicaTDocmanc != null){
			List<AuTdocmanc> listAuTdocmanc =  calcoloInfoST.getSTDocManc(riepilogoTipologicaTDocmanc, numeroPraticheEsaminate);
			if (listAuTdocmanc != null){
				SessioneUtil.insertLog(listLogDb, "CALCOLARE INFO SULLA S-T salvataggio AuTdocmanc", sSessione.getIdSSessione(), null, null);
				for (AuTdocmanc itemToSave : listAuTdocmanc) {
					auCalcolaIndicatoriDao.persist(itemToSave);
				}
			}
		}
		
		System.out.println("CALCOLARE INFO SULLA S-T AuSTfascicolo "
				+ sSessione.getIdSessione());
		
		log.info("CALCOLARE INFO SULLA S-T AuSTfascicolo "
				+ sSessione.getIdSessione());
				
		SessioneUtil.insertLog(listLogDb, "CALCOLARE INFO SULLA S-T AuSTfascicolo", sSessione.getIdSSessione(), null, null);
		List<RiepilogoTipologica> riepilogoTipologicaTFascicolo = auCalcolaIndicatoriDao.getRiepilogoTipologicaTFascicolo(sSessione.getIdSSessione());
		if(riepilogoTipologicaTFascicolo != null){
			List<AuSTfascicolo> listAuSTfascicolo = calcoloInfoST.getAuSTfascicolo(riepilogoTipologicaTFascicolo, numeroPraticheEsaminate);
			if (listAuSTfascicolo != null){
				SessioneUtil.insertLog(listLogDb, "CALCOLARE INFO SULLA S-T salvataggio AuSTfascicolo", sSessione.getIdSSessione(), null, null);
				for (AuSTfascicolo itemToSave : listAuSTfascicolo) {
					auCalcolaIndicatoriDao.persist(itemToSave);
				}
			}
		}
		
		System.out.println("CALCOLARE INFO SULLA S-T AuSTempi "
				+ sSessione.getIdSessione());
		
		log.info("CALCOLARE INFO SULLA S-T AuSTempi "
				+ sSessione.getIdSessione());
		
		
		SessioneUtil.insertLog(listLogDb, "CALCOLARE INFO SULLA S-T AuSTempi", sSessione.getIdSSessione(), null, null);
		List<RiepilogoTipologica> riepilogoTipologicaSTempi = auCalcolaIndicatoriDao.getRiepilogoTipologicaSTempi(sSessione.getIdSSessione());
		List<AuSTempi> listAuSTempi = null;
		if(riepilogoTipologicaSTempi != null){
			listAuSTempi = calcoloInfoST.getSTempi(riepilogoTipologicaSTempi, numeroPraticheEsaminate);
			if (listAuSTempi != null){
				SessioneUtil.insertLog(listLogDb, "CALCOLARE INFO SULLA S-T salvataggio AuSTempi", sSessione.getIdSSessione(), null, null);
				for (AuSTempi itemToSave : listAuSTempi) {
					auCalcolaIndicatoriDao.persist(itemToSave);
				}		
			}
		}
		
		System.out.println("CALCOLARE INFO SULLA S-T AuSTesito "
				+ sSessione.getIdSessione());
		
		log.info("CALCOLARE INFO SULLA S-T AuSTesito "
				+ sSessione.getIdSessione());
		
		SessioneUtil.insertLog(listLogDb, "CALCOLARE INFO SULLA S-T AuSTesito", sSessione.getIdSSessione(), null, null);
		List<RiepilogoTipologica> riepilogoTipologicaSTesito = auCalcolaIndicatoriDao.getRiepilogoTipologicaSTesito(sSessione.getIdSSessione());
		List<AuSTesito> listAuSTesito = null;
		if(riepilogoTipologicaSTesito != null){
			listAuSTesito = calcoloInfoST.getSTEsito(riepilogoTipologicaSTesito, numeroPraticheEsaminate);
			if (listAuSTesito != null){
				SessioneUtil.insertLog(listLogDb, "CALCOLARE INFO SULLA S-T salvataggio AuSTesito", sSessione.getIdSSessione(), null, null);
				for (AuSTesito itemToSave : listAuSTesito) {
					auCalcolaIndicatoriDao.persist(itemToSave);
				}	
			}
		}
		
		System.out.println("CALCOLARE INFO SULLA S-T* "
				+ sSessione.getIdSessione()+ " OK");
		
		
		log.info("CALCOLARE INFO SULLA S-T* "
				+ sSessione.getIdSessione()+ " OK");
		
		
		/* ----------------------------------------------------------------------------------------------------| */
		System.out.println("CALCOLARE INFO SULLA AU_S_SESSIONE " + sSessione.getIdSessione());
		log.info("CALCOLARE INFO SULLA AU_S_SESSIONE " + sSessione.getIdSessione());
		
		Integer numDissensiAmm = auCalcolaIndicatoriDao.getNumDissensiByTipo("1");
		Integer numDissensiSan = auCalcolaIndicatoriDao.getNumDissensiByTipo("2");
		
		if (numDissensiAmm == null)numDissensiAmm = 0;
		if (numDissensiSan == null)numDissensiSan = 0;
		
		Integer TotSissensi = numDissensiAmm.intValue() + numDissensiSan.intValue();
		
		if (TotSissensi > 0){
			
			if(numDissensiAmm > 0){
				Double perc = ( numDissensiAmm.doubleValue() / TotSissensi.doubleValue()) * 100;
				sSessione.setPercNumDissAmm(perc);
			}

			if(numDissensiSan > 0){
				Double perc = ( numDissensiSan.doubleValue()  / TotSissensi.doubleValue()) * 100;
				sSessione.setPercNumDissSan(perc);
			}
			
		}else{
			sSessione.setPercNumDissAmm(0D);
			sSessione.setPercNumDissSan(0D);
		}
		
		sSessione.setNumDissensiAmm(numDissensiAmm);
		sSessione.setNumDissensiSan(numDissensiSan);
		SessioneUtil.insertLog(listLogDb, "Salvataggio SESSIONE", sSessione.getIdSSessione(), null, null);
		sSessione.setStatoEsameSessione(SessioneUtil.ESAME_SESSIONE_CHIUSO);
		sSessione.setDataInizio(dataCalcolo);
		sSessione.setDataFine(dataCalcolo);
		sSessione.setDataAggDatiSess(new Date());
		auCalcolaIndicatoriDao.salva(sSessione);
		
		SessioneUtil.insertLog(listLogDb, "Fine Calcolo Indicatori per idSessione: "
				+ sSessione.getIdSessione(), sSessione.getIdSSessione(), null, null);
		
		System.out.println("CALCOLARE INFO SULLA AU_S_SESSIONE " + sSessione.getIdSessione() + " OK");
		log.info("CALCOLARE INFO SULLA AU_S_SESSIONE " + sSessione.getIdSessione() + " OK");
		
		System.out.println("FINE CALCOLI SESSIONE " + sSessione.getIdSessione());
		log.info("FINE CALCOLI SESSIONE " + sSessione.getIdSessione());
	}

}
