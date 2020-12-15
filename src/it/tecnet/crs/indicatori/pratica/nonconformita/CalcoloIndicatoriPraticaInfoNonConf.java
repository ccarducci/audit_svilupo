package it.tecnet.crs.indicatori.pratica.nonconformita;

import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoFaseAcquisizioneIstanza;
import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoFaseAutotutelaResistenzaGiudizio;
import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoFaseDati;
import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoFaseEsecuzioneProvvedimenti;
import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoFaseGestioneIstruttoria;
import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoFasePeritale;
import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoFasePostPeritale;
import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoPraticheSISCO;
import it.tecnet.crs.indicatori.pratica.CalcoloIndicatoriErrore;
import it.tecnet.crs.jpa.model.AuMVarcomp;
import it.tecnet.crs.jpa.model.AuSPraticaVarcomp;
import it.tecnet.crs.util.PraticaUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

public class CalcoloIndicatoriPraticaInfoNonConf {

	protected static Logger log = Logger
			.getLogger(CalcoloIndicatoriPraticaInfoNonConf.class);

	private AtpoPraticheSISCO praticaCisco;
	private AtpoFaseEsecuzioneProvvedimenti praticaEp;
	private AtpoFasePostPeritale praticaPp;
	private AtpoFasePeritale praticaPe;
	private AtpoFaseGestioneIstruttoria praticaGi;
	private AtpoFaseAutotutelaResistenzaGiudizio praticaAr;
	private AtpoFaseDati praticaFd;
	private AtpoFaseAcquisizioneIstanza praticaAi;
	private List<AuSPraticaVarcomp> listaPraticheVarCompToInsert;
	private long idSPratica;
	private HashMap<String, Integer> varcompPresent = new HashMap<String, Integer>();

	private List<CalcoloIndicatoriErrore> listaErrori;
	private List<AuMVarcomp> auMVarcomp;

	public CalcoloIndicatoriPraticaInfoNonConf(
			AtpoFaseEsecuzioneProvvedimenti praticaEp,
			AtpoFasePostPeritale praticaPp, AtpoFasePeritale praticaPe,
			AtpoFaseGestioneIstruttoria praticaGi,
			AtpoFaseAutotutelaResistenzaGiudizio praticaAr,
			AtpoFaseDati praticaFd, AtpoFaseAcquisizioneIstanza praticaAi,
			List<AuSPraticaVarcomp> listaPraticheVarCompToInsert,
			long idSSessione, long idSPratica,
			List<CalcoloIndicatoriErrore> listaErrori,
			List<AuMVarcomp> auMVarcomp, AtpoPraticheSISCO praticaCisco) {
		super();
		this.praticaEp = praticaEp;
		this.praticaPp = praticaPp;
		this.praticaPe = praticaPe;
		this.praticaGi = praticaGi;
		this.praticaAr = praticaAr;
		this.praticaFd = praticaFd;
		this.praticaAi = praticaAi;
		this.listaPraticheVarCompToInsert = listaPraticheVarCompToInsert;
		this.idSPratica = idSPratica;
		this.listaErrori = listaErrori;
		this.auMVarcomp = auMVarcomp;
		this.praticaCisco = praticaCisco;
	}

	public void insertVarComp(
			List<AuSPraticaVarcomp> listaPraticheVarCompToInsert,
			long idSPratica, String varComp) {
		AuSPraticaVarcomp itemSpraticaVarCompToIns = new AuSPraticaVarcomp();
		// idSPratica
		itemSpraticaVarCompToIns.setIdSPratica(idSPratica);
		// idNonConf
		itemSpraticaVarCompToIns.setIdNonConf(getIdNonConfFromVarComp(varComp));
		// DataAttribuzione
		itemSpraticaVarCompToIns.setDataAttribuzione(new Date());
		// varComp
		itemSpraticaVarCompToIns.setVarComp(varComp);
		listaPraticheVarCompToInsert.add(itemSpraticaVarCompToIns);
	}

	public boolean varCompExist(String varcompToCheck,
			List<AuSPraticaVarcomp> listaPraticheVarCompToInsert) {
		for (AuSPraticaVarcomp auSPraticaVarcomp : listaPraticheVarCompToInsert) {
			if (auSPraticaVarcomp.getVarComp().trim().equals(
					varcompToCheck.trim()))
				return true;
		}
		return false;
	}

	private Long getIdNonConfFromVarComp(String varComp) {
		if (varComp == null)
			return null;
		for (AuMVarcomp item : auMVarcomp) {
			if (varComp.trim().toUpperCase().equals(
					item.getCodicevc().trim().toUpperCase()))
				return item.getIdMNonConf();
		}
		return 0L;
	}

	public void CalcoloNonConf() {
		try {
			insertLogNonConf("CalcoloNonConf1_TempCorrProt Inizio");
			CalcoloNonConf1_TempCorrProt();
		} catch (Exception e) {
			//log.error("CalcoloNonConf1_TempCorrProt: " + e.getMessage());
			insertErrorNonConf("CalcoloNonConf1_TempCorrProt", e.getMessage());
		}

		try {
			insertLogNonConf("CalcoloNonConf2_CorrScanz Inizio");
			CalcoloNonConf2_CorrScanz();
		} catch (Exception e) {
			//log.error("CalcoloNonConf2_CorrScanz: " + e.getMessage());
			insertErrorNonConf("CalcoloNonConf2_CorrScanz", e.getMessage());
		}

		try {
			insertLogNonConf("CalcoloNonConf3_TempAcqui Inizio");
			CalcoloNonConf3_TempAcqui();
		} catch (Exception e) {
			//log.error("CalcoloNonConf3_TempAcqui: " + e.getMessage());
			insertErrorNonConf("CalcoloNonConf3_TempAcqui", e.getMessage());
		}

		try {
			insertLogNonConf("CalcoloNonConf4_MancPareAutoResiGiud Inizio");
			CalcoloNonConf4_MancPareAutoResiGiud();
		} catch (Exception e) {
			//log.error("CalcoloNonConf4_MancPareAutoResiGiud: " + e.getMessage());
			insertErrorNonConf("CalcoloNonConf4_MancPareAutoResiGiud", e
					.getMessage());
		}

		try {
			insertLogNonConf("CalcoloNonConf5_DefinizioneTermPrimaUdiProc Inizio");
			CalcoloNonConf5_DefinizioneTermPrimaUdiProc();
		} catch (Exception e) {
			//log.error("CalcoloNonConf5_DefinizioneTermPrimaUdiProc: " + e.getMessage());
			insertErrorNonConf("CalcoloNonConf5_DefinizioneTermPrimaUdiProc", e
					.getMessage());
		}

		try {
			insertLogNonConf("CalcoloNonConf6_Litispedenza Inizio");
			CalcoloNonConf6_Litispedenza();
		} catch (Exception e) {
			//log.error("CalcoloNonConf6_Litispedenza: " + e.getMessage());
			insertErrorNonConf("CalcoloNonConf6_Litispedenza", e.getMessage());
		}

		try {
			insertLogNonConf("CalcoloNonConf7_CostiInGiudizio Inizio");
			CalcoloNonConf7_CostiInGiudizio();
		} catch (Exception e) {
			//log.error("CalcoloNonConf7_CostiInGiudizio: " + e.getMessage());
			insertErrorNonConf("CalcoloNonConf7_CostiInGiudizio", e
					.getMessage());
		}

		try {
			insertLogNonConf("CalcoloNonConf8_ModaCostGiudi Inizio");
			CalcoloNonConf8_ModaCostGiudi();
		} catch (Exception e) {
			//log.error("CalcoloNonConf8_ModaCostGiudi: " + e.getMessage());
			insertErrorNonConf("CalcoloNonConf8_ModaCostGiudi", e.getMessage());
		}

		try {
			insertLogNonConf("CalcoloNonConf9_Decadenza Inizio");
			CalcoloNonConf9_Decadenza();
		} catch (Exception e) {
			//log.error("CalcoloNonConf9_Decadenza: " + e.getMessage());
			insertErrorNonConf("CalcoloNonConf9_Decadenza", e.getMessage());
		}

		try {
			insertLogNonConf("CalcoloNonConf10_MancVerCorrTraRichAtpEDomInv Inizio");
			CalcoloNonConf10_MancVerCorrTraRichAtpEDomInv();
		} catch (Exception e) {
			//log.error("CalcoloNonConf10_MancVerCorrTraRichAtpEDomInv: " + e.getMessage());
			insertErrorNonConf("CalcoloNonConf10_MancVerCorrTraRichAtpEDomInv",
					e.getMessage());
		}

		try {
			insertLogNonConf("CalcoloNonConf11_GesRegOperPeritali Inizio");
			CalcoloNonConf11_GesRegOperPeritali();
		} catch (Exception e) {
			//log.error("CalcoloNonConf11_GesRegOperPeritali: " + e.getMessage());
			insertErrorNonConf("CalcoloNonConf11_GesRegOperPeritali", e
					.getMessage());
		}

		try {
			insertLogNonConf("CalcoloNonConf12_GesVisPeritali Inizio");
			CalcoloNonConf12_GesVisPeritali();
		} catch (Exception e) {
			//log.error("CalcoloNonConf12_GesVisPeritali: " + e.getMessage());
			insertErrorNonConf("CalcoloNonConf12_GesVisPeritali", e
					.getMessage());
		}

		try {
			insertLogNonConf("CalcoloNonConf13_GesParMedico Inizio");
			CalcoloNonConf13_GesParMedico();
		} catch (Exception e) {
			//log.error("CalcoloNonConf13_GesParMedico: " + e.getMessage());
			insertErrorNonConf("CalcoloNonConf13_GesParMedico", e.getMessage());
		}

		try {
			insertLogNonConf("CalcoloNonConf14_GesOssCtpInpsParConc Inizio");
			CalcoloNonConf14_GesOssCtpInpsParConc();
		} catch (Exception e) {
			//log.error("CalcoloNonConf14_GesOssCtpInpsParConc: " + e.getMessage());
			insertErrorNonConf("CalcoloNonConf14_GesOssCtpInpsParConc", e
					.getMessage());
		}

		try {
			insertLogNonConf("CalcoloNonConf15_GestioneBozzaCtu Inizio");
			CalcoloNonConf15_GestioneBozzaCtu();
		} catch (Exception e) {
			//log.error("CalcoloNonConf15_GestioneBozzaCtu: " + e.getMessage());
			insertErrorNonConf("CalcoloNonConf15_GestioneBozzaCtu", e
					.getMessage());
		}

		try {
			insertLogNonConf("CalcoloNonConf16_GestioneBozzaCtu Inizio");
			CalcoloNonConf16_GestioneBozzaCtu();
		} catch (Exception e) {
			//log.error("CalcoloNonConf16_GestioneBozzaCtu: " + e.getMessage());
			insertErrorNonConf("CalcoloNonConf16_GestioneBozzaCtu", e
					.getMessage());
		}

		try {
			insertLogNonConf("CalcoloNonConf17_GestioneComuCanc Inizio");
			CalcoloNonConf17_GestioneComuCanc();
		} catch (Exception e) {
			//log.error("CalcoloNonConf17_GestioneComuCanc: " + e.getMessage());
			insertErrorNonConf("CalcoloNonConf17_GestioneComuCanc", e
					.getMessage());
		}

		try {
			insertLogNonConf("CalcoloNonConf18_GestioneDiss Inizio");
			CalcoloNonConf18_GestioneDiss();
		} catch (Exception e) {
			//log.error("CalcoloNonConf18_GestioneDiss: " + e.getMessage());
			insertErrorNonConf("CalcoloNonConf18_GestioneDiss", e.getMessage());
		}

		try {
			insertLogNonConf("CalcoloNonConf19_CorreRegDati Inizio");
			CalcoloNonConf19_CorreRegDati();
		} catch (Exception e) {
			//log.error("CalcoloNonConf19_CorreRegDati: " + e.getMessage());
			insertErrorNonConf("CalcoloNonConf19_CorreRegDati", e.getMessage());
		}

		try {
			insertLogNonConf("CalcoloNonConf20_CorrChiuPratica Inizio");
			CalcoloNonConf20_CorrChiuPratica();
		} catch (Exception e) {
			//log.error("CalcoloNonConf20_CorrChiuPratica: " + e.getMessage());
			insertErrorNonConf("CalcoloNonConf20_CorrChiuPratica", e
					.getMessage());
		}

		try {
			insertLogNonConf("CalcoloNonConf21_ComuLps Inizio");
			CalcoloNonConf21_ComuLps();
		} catch (Exception e) {
			//log.error("CalcoloNonConf21_ComuLps: " + e.getMessage());
			insertErrorNonConf("CalcoloNonConf21_ComuLps", e.getMessage());
		}

		try {
			insertLogNonConf("CalcoloNonConf22_GesDepRicoPrimoGrado Inizio");
			CalcoloNonConf22_GesDepRicoPrimoGrado();
		} catch (Exception e) {
			//log.error("CalcoloNonConf22_GesDepRicoPrimoGrado: " + e.getMessage());
			insertErrorNonConf("CalcoloNonConf22_GesDepRicoPrimoGrado", e
					.getMessage());
		}

		try {
			insertLogNonConf("CalcoloNonConf23_LiqSpeseCTU Inizio");
			CalcoloNonConf23_LiqSpeseCTU();
		} catch (Exception e) {
			//log.error("CalcoloNonConf23_LiqSpeseCTU: " + e.getMessage());
			insertErrorNonConf("CalcoloNonConf23_LiqSpeseCTU", e.getMessage());
		}

		try {
			insertLogNonConf("CalcoloNonConf24_GesDiffControSpeseLegaRec Inizio");
			CalcoloNonConf24_GesDiffControSpeseLegaRec();
		} catch (Exception e) {
			//log.error("CalcoloNonConf24_GesDiffControSpeseLegaRec: " + e.getMessage());
			insertErrorNonConf("CalcoloNonConf24_GesDiffControSpeseLegaRec", e
					.getMessage());
		}

		try {
			insertLogNonConf("CalcoloNonConf25_GesDiffControSpeseCTURec Inizio");
			CalcoloNonConf25_GesDiffControSpeseCTURec();
		} catch (Exception e) {
			//log.error("CalcoloNonConf25_GesDiffControSpeseCTURec: " + e.getMessage());
			insertErrorNonConf("CalcoloNonConf25_GesDiffControSpeseCTURec", e
					.getMessage());
		}

		try {
			insertLogNonConf("CalcoloNonConf26_PresaInCarico Inizio");
			CalcoloNonConf26_PresaInCarico();
		} catch (Exception e) {
			//log.error("CalcoloNonConf26_PresaInCarico: " + e.getMessage());
			insertErrorNonConf("CalcoloNonConf26_PresaInCarico", e.getMessage());
		}

		try {
			insertLogNonConf("CalcoloNonConf27_RegistraInCaricoLiq Inizio");
			CalcoloNonConf27_RegistraInCaricoLiq();
		} catch (Exception e) {
			//log.error("CalcoloNonConf27_RegistraInCaricoLiq: " + e.getMessage());
			insertErrorNonConf("CalcoloNonConf27_RegistraInCaricoLiq", e
					.getMessage());
		}

		try {
			insertLogNonConf("CalcoloNonConf28_NonCorrDichiEsenzPagamento Inizio");
			CalcoloNonConf28_NonCorrDichiEsenzPagamento();
		} catch (Exception e) {
			//log.error("CalcoloNonConf28_NonCorrDichiEsenzPagamento: " + e.getMessage());
			insertErrorNonConf("CalcoloNonConf28_NonCorrDichiEsenzPagamento", e
					.getMessage());
		}

		try {
			insertLogNonConf("CalcoloNonConf29_AltreEccProff Inizio");
			CalcoloNonConf29_AltreEccProff();
		} catch (Exception e) {
			//log.error("CalcoloNonConf29_AltreEccProff: " + e.getMessage());
			insertErrorNonConf("CalcoloNonConf29_AltreEccProff", e.getMessage());
		}

		try {
			insertLogNonConf("CalcoloNonConf30_IndeterminatezzaDomanda Inizio");
			CalcoloNonConf30_IndeterminatezzaDomanda();
		} catch (Exception e) {
			//log.error("CalcoloNonConf30_IndeterminatezzaDomanda: " + e.getMessage());
			insertErrorNonConf("CalcoloNonConf30_IndeterminatezzaDomanda", e
					.getMessage());
		}

		try {
			insertLogNonConf("CalcoloNonConf31_EcceRilevabPreAssMemo Inizio");
			CalcoloNonConf31_EcceRilevabPreAssMemo();
		} catch (Exception e) {
			//log.error("CalcoloNonConf31_EcceRilevabPreAssMemo: " + e.getMessage());
			insertErrorNonConf("CalcoloNonConf31_EcceRilevabPreAssMemo", e
					.getMessage());
		}

		try {
			insertLogNonConf("CalcoloNonConf32_CarenzaInteresseAgire Inizio");
			CalcoloNonConf32_CarenzaInteresseAgire();
		} catch (Exception e) {
			//log.error("CalcoloNonConf32_CarenzaInteresseAgire: " + e.getMessage());
			insertErrorNonConf("CalcoloNonConf32_CarenzaInteresseAgire", e
					.getMessage());
		}

		try {
			insertLogNonConf("CalcoloNonConf33_VerificaCongruenzaPresMedicoCogisan Inizio");
			CalcoloNonConf33_VerificaCongruenzaPresMedicoCogisan();
		} catch (Exception e) {
			//log.error("CalcoloNonConf33_VerificaCongruenzaPresMedicoCogisan: " + e.getMessage());
			insertErrorNonConf(
					"CalcoloNonConf33_VerificaCongruenzaPresMedicoCogisan", e
							.getMessage());
		}

	}

	private void insertLogNonConf(String mess) {
		log.info(mess);
		CalcoloIndicatoriErrore errore = new CalcoloIndicatoriErrore();
		errore.setTipoRischio("Non Conformita");
		errore.setTipoErrore("INFO");
		if (mess !=null)errore.setMessaggio(mess);
		listaErrori.add(errore);
	}
	
	private void insertErrorNonConf(String TipoErrore, String stackTrace) {
		log.error(TipoErrore + " - " + stackTrace);
		CalcoloIndicatoriErrore errore = new CalcoloIndicatoriErrore();
		errore.setTipoRischio("Non Conformita");
		errore.setTipoErrore(TipoErrore);
		if (stackTrace !=null)errore.setMessaggio(stackTrace);
		listaErrori.add(errore);
	}

	/*
	 * Calcolare info Non conformità 1 – Tempestività e correttezza
	 * protocollazione
	 */
	private void CalcoloNonConf1_TempCorrProt() {
		String varComp = "";
		
		// Data_protocollo_AI
		Date dataProtocolloAi = PraticaUtil
				.getDateWithoutTimeUsingFormat(praticaAi.getDataProtocollo());
		// Data_notifica
		Date dataNotifica = PraticaUtil.getDateWithoutTimeUsingFormat(praticaAi
				.getDataNotifica());
		// voce titolario errata
		String voceTitolarioErrata = PraticaUtil
				.checkStringAndTrimUpperCase(praticaAi.getVoceTitolarioErrata());
		// Int_notifica_protocollo
		long intNotificaProtocollo = praticaAi
				.getIntervalloNotificaProtocollo();

		if (dataNotifica == null) {
			CalcoloIndicatoriErrore errore = new CalcoloIndicatoriErrore();
			errore.setTipoRischio("CalcoloNonConf1_TempCorrProt");
			errore.setTipoErrore("NON CONF TempCorrProt");
			errore.setMessaggio("Data dataProtocolloAi o dataNotifica null");
			listaErrori.add(errore);
			return;
		}
		
		
		if (dataProtocolloAi == null /* && dataNotifica != null */) {
			// Se Data_protocollo_AI = Null e Data_notifica <> Null
			// e voce titolario errata è null allora VC = “A001”
			varComp = PraticaUtil.A001;
		} else if (intNotificaProtocollo > 2 && "0".equals(voceTitolarioErrata)) {
			// Se Int_notifica_protocollo > 2 e voce titolario errata = 0 allora
			// VC = “A003”
			varComp = PraticaUtil.A003;
		} else if (intNotificaProtocollo > 2 && "1".equals(voceTitolarioErrata)) {
			// Se Int_notifica_protocollo > 2 e voce titolario errata = 1 allora
			// VC = “A013”
			varComp = PraticaUtil.A013;
		} else if (intNotificaProtocollo < 3 && "0".equals(voceTitolarioErrata)) {
			// Se Int_notifica_protocollo < 2 e voce titolario errata =0 allora
			// VC = “A014”
			varComp = PraticaUtil.A014;
		} else if (intNotificaProtocollo < 3 && "1".equals(voceTitolarioErrata)) {
			// Se Int_notifica_protocollo < 2 e voce titolario errata =1 allora
			// VC = “A015”
			varComp = PraticaUtil.A015;
		}

		if (varComp.length() > 0) {
			varcompPresent.put(varComp, 1);
			insertVarComp(listaPraticheVarCompToInsert, idSPratica, varComp);
		}
		insertLogNonConf("varComp: " + varComp);
		insertLogNonConf("Fine");
	}

	/*
	 * Calcolare info Non conformità 2 – Correttezza scansione
	 */
	private void CalcoloNonConf2_CorrScanz() {
		String varComp = "";

		// Prot_immag_ok
		String protImmagOk = PraticaUtil
				.checkStringAndTrimUpperCaseSiNo(praticaAi
						.getProtocolloConImg());

		if (PraticaUtil.NO.equals(protImmagOk)) {
			// Se Prot_immag_ok = "No" allora VC = “A006”
			varComp = PraticaUtil.A006;
		} else if (PraticaUtil.SI.equals(protImmagOk)) {
			// Se Prot_immag_ok ="si" allora VC = “A007”
			varComp = PraticaUtil.A007;
		}

		if (varComp.length() > 0) {
			varcompPresent.put(varComp, 1);
			insertVarComp(listaPraticheVarCompToInsert, idSPratica, varComp);
		}
		insertLogNonConf("varComp: " + varComp);
		insertLogNonConf("Fine");
	}

	/*
	 * Calcolare info Non conformità 3 - Tempestività acquisizione
	 */
	private void CalcoloNonConf3_TempAcqui() {
		String varComp = "";

		// Data_depd_omol
		Date dataDepdOmol = PraticaUtil.getDateWithoutTimeUsingFormat(praticaPp
				.getDataDepositoDecOmologa());
		// Data_acq_sisco
		Date dataAcqSisco = PraticaUtil.getDateWithoutTimeUsingFormat(praticaAi
				.getDataAcquisizioneSISCO());
		// Data_1_udie
		Date dataPrimaUdie = PraticaUtil
				.getDateWithoutTimeUsingFormat(praticaGi.getDataPrimaUdienza());
		// Data_cost_giud
		Date dataCostGiud = PraticaUtil.getDateWithoutTimeUsingFormat(praticaGi
				.getDataCostitGiudizio());
		// Int_notifica_acqsisco
		long intNotificaAcqsisco = praticaAi.getIntervalloNotificaSISCO();
		// Data_depo_diss
		Date dataDepoDiss = PraticaUtil
				.getDateWithoutTimeUsingFormat(praticaPp.getDataDepDiss());
		// Cod_chiu_corr
		String codChiuCorr = PraticaUtil.checkStringAndTrimUpperCase(praticaPp
				.getCodChiusuraCorretto());
		
		
		if (dataDepdOmol == null || dataAcqSisco == null
				|| dataPrimaUdie == null || dataCostGiud == null) {
			CalcoloIndicatoriErrore errore = new CalcoloIndicatoriErrore();
			errore.setTipoRischio("CalcoloNonConf3_TempAcqui");
			errore.setTipoErrore("NON CONF TempAcqui");
			errore
					.setMessaggio("Data null tra dataDepdOmol o dataAcqSisco o dataPrimaUdie o dataCostGiud");
			listaErrori.add(errore);
		}

		if (dataAcqSisco != null) {
			if (( dataDepdOmol != null && dataDepdOmol.getTime() <= dataAcqSisco.getTime())
					 || 
					 /*( dataDepdOmol == null && "4".equals(codChiuCorr) ) ) {   - modificato da frusso il 18/11/2020*/
					 ( dataDepdOmol == null && !"4".equals(codChiuCorr) ) ) {
			    // Se (Data_depd_omol <> null e Data_depd_omol <= Data_acq_sisco) 
				// o (Data_depd_omol = null e cod_chiu_corr <> 4) allora VC = “A011”
				varComp = PraticaUtil.A011;
			}else if(  ( dataDepoDiss != null && dataDepoDiss.getTime() <= dataAcqSisco.getTime() )  
					||   
					(  dataDepoDiss == null && "4".equals(codChiuCorr) )){
				// Se (Data_depo_diss <> null e Data_depo_diss <= Data_acq_sisco) 
				// o (Data_depo_diss = null e cod_chiu_corr = 4) allora VC = “A011”
				varComp = PraticaUtil.A011;
			} else if (dataPrimaUdie == null || dataPrimaUdie.getTime() <= dataAcqSisco.getTime()) {
				// Se Data_1_udie = null o Data_1_udie <= Data_acq_sisco allora VC = “A010”
				varComp = PraticaUtil.A010;
			} else if (dataCostGiud == null || dataCostGiud.getTime() <= dataAcqSisco.getTime()) {
				// Se Data_cost_giud = null o Data_cost_giud <= Data_acq_sisco  VC = “A009”
				varComp = PraticaUtil.A009;
			} else if (intNotificaAcqsisco > 3) {
				// Se Int_notifica_acqsisco > 3 allora VC = “A008”
				varComp = PraticaUtil.A008;
			} else if (intNotificaAcqsisco < 4) {
				// Se Int_notifica_acqsisco < 4 allora VC = “A012”
				varComp = PraticaUtil.A012;
			}
		}

		if (varComp.length() > 0) {
			varcompPresent.put(varComp, 1);
			insertVarComp(listaPraticheVarCompToInsert, idSPratica, varComp);
		}
		insertLogNonConf("varComp: " + varComp);
		insertLogNonConf("Fine");
	}

	/*
	 * Calcolare info Non conformità 4 – Mancato parere di autotutela
	 * /resistenza in giudizio
	 */
	private void CalcoloNonConf4_MancPareAutoResiGiud() {

		String varComp = "";
		// parere
		String parere = PraticaUtil.checkStringAndTrimUpperCase(praticaAr
				.getParere());

		if (varcompPresent.containsKey(PraticaUtil.A009)
				|| varcompPresent.containsKey(PraticaUtil.A010)
				|| varcompPresent.containsKey(PraticaUtil.A011)) {
			// Se (VC (nc 4) < “A012” e VC (nc 4) <> “A008” e VC (nc 4) <>
			// “A009”) allora VC = Null
			// NON FACCIO NIENTE
		} else if (!varcompPresent.containsKey(PraticaUtil.A005)){
			if(parere.equals("1")) {
				// Se (VC (nc 2) <> “A005”)
				// Se Parere = "manca parere di autotutela/resitenza in giudizio"
				// allora VC = “A206”
				varComp = PraticaUtil.A206;
			} else if (parere.equals("2".toUpperCase())) {
				// Se Parere = "si, autotutela" allora VC = “A207”
				varComp = PraticaUtil.A207;
			} else if (parere.equals("3".toUpperCase())) {
				// Se Parere = "si, resistenza in giudizio" VC = “A208”
				varComp = PraticaUtil.A208;
			}
		}
		if (varComp.length() > 0) {
			varcompPresent.put(varComp, 1);
			insertVarComp(listaPraticheVarCompToInsert, idSPratica, varComp);
		}
		insertLogNonConf("varComp: " + varComp);
		insertLogNonConf("Fine");
	}

	/*
	 * Calcolare info Non conformità 5 – Definizione nei termini entro la 1^
	 * udienza del procedimento
	 */
	private void CalcoloNonConf5_DefinizioneTermPrimaUdiProc() {
		String varComp = "";
		// Def_term_1udie
		String deftempPrimaUde = PraticaUtil
				.checkStringAndTrimUpperCase(praticaAr.getTerminiPrimaUdienza());

		if (varcompPresent.containsKey(PraticaUtil.A207)) {
			// Se VC (nc 4) = “A207”
			if (deftempPrimaUde.equals("1".trim())) {
				// se Def_term_1udie =
				// "no per tardiva attivazione procedimento autotutela" allora
				// VC = “A201”
				varComp = PraticaUtil.A201;
			} else if (deftempPrimaUde.equals("2".trim())) {
				// se Def_term_1udie =
				// "no per mancata autorizzazione nei termini del CMS" allora VC
				// = “A202”
				varComp = PraticaUtil.A202;
			} else if (deftempPrimaUde.equals("3".trim())) {
				// se Def_term_1udie =
				// "no per mancata redazione del verbale definitivo del CML"
				// allora VC = “A203”
				varComp = PraticaUtil.A203;
			} else if (deftempPrimaUde.equals("4".trim())) {
				// se Def_term_1udie = "definizione nei termini" allora VC =
				// “A204”
				varComp = PraticaUtil.A204;
			}
		}
		if (varComp.length() > 0) {
			varcompPresent.put(varComp, 1);
			insertVarComp(listaPraticheVarCompToInsert, idSPratica, varComp);
		}
		insertLogNonConf("varComp: " + varComp);
		insertLogNonConf("Fine");
	}

	/*
	 * Calcolare info Non conformità 6 - Litispedenza
	 */
	private void CalcoloNonConf6_Litispedenza() {
		String varComp = "";
		// Litispedenza
		String litispedenza = PraticaUtil.checkStringAndTrimUpperCase(praticaGi
				.getLitispendenza());

		if (litispedenza.equals("2")) {
			// Se Litispedenza = 2 allora VC = “A315”
			varComp = PraticaUtil.A315;
		} else if (litispedenza.equals("3")) {
			// Se Litispedenza = "DA ECCEPIRE ED ECCEPITA" allora VC = “A321”
			varComp = PraticaUtil.A321;
		}

		if (varComp.length() > 0) {
			varcompPresent.put(varComp, 1);
			insertVarComp(listaPraticheVarCompToInsert, idSPratica, varComp);
		}
		insertLogNonConf("varComp: " + varComp);
		insertLogNonConf("Fine");
	}

	/*
	 * Calcolare info Non conformità 7 – Costituzione in giudizio
	 */
	private void CalcoloNonConf7_CostiInGiudizio() {

		String varComp = "";
		// Data_1_udie
		Date dataPrimaUdie = PraticaUtil
				.getDateWithoutTimeUsingFormat(praticaGi.getDataPrimaUdienza());
		// Data_cost_giud
		Date dataCostGiu = PraticaUtil.getDateWithoutTimeUsingFormat(praticaGi
				.getDataCostitGiudizio());

		if (dataPrimaUdie == null) {
			CalcoloIndicatoriErrore errore = new CalcoloIndicatoriErrore();
			errore.setTipoRischio("CalcoloNonConf7_CostiInGiudizio");
			errore.setTipoErrore("NON CONF CostiInGiudizio");
			errore
					.setMessaggio("Data dataPrimaUdie è null");
			listaErrori.add(errore);
		}

		if (dataCostGiu == null) {
			// Se Data_cost_giud = "" allora VC = “A320”
			varComp = PraticaUtil.A320;
		} else if (dataPrimaUdie != null
				&& dataCostGiu.getTime() > dataPrimaUdie.getTime()) {
			// Se Data_cost_giud > Data_1_udie allora VC = “A320”
			varComp = PraticaUtil.A320;
		} else if (dataPrimaUdie == null || 
					(  (PraticaUtil.daysBetween(dataCostGiu, dataPrimaUdie) < 5) && (dataPrimaUdie.getTime() >= dataCostGiu.getTime()))
				) {
			// Se Data_1_udie = null o ((Data_1_udie - Data_cost_giud) < 5) e (Data_1_udie >= Data_cost_giud)) 
			varComp = PraticaUtil.A318;
		} else if (dataPrimaUdie != null
				&& PraticaUtil.daysBetween(dataCostGiu, dataPrimaUdie) > 4) {
			// Se (Data_1_udie - Data_cost_giud) > 4 allora VC = “A328”
			varComp = PraticaUtil.A328;
		}

		if (varComp.length() > 0) {
			varcompPresent.put(varComp, 1);
			insertVarComp(listaPraticheVarCompToInsert, idSPratica, varComp);
		}
		insertLogNonConf("varComp: " + varComp);
		insertLogNonConf("Fine");
	}

	/*
	 * Calcolare info Non conformità 8 – Modalità di costituzione in giudizio
	 */
	private void CalcoloNonConf8_ModaCostGiudi() {
		String varComp = "";
		// Data_notifica
		Date dataNotifica = PraticaUtil.getDateWithoutTimeUsingFormat(praticaAi
				.getDataNotifica());
		// Data_cost_giud
		Date dataCostGiu = PraticaUtil.getDateWithoutTimeUsingFormat(praticaGi
				.getDataCostitGiudizio());
		// Cost_giud_telem
		String costGiudTelem = PraticaUtil
				.checkStringAndTrimUpperCaseSiNo(praticaGi
						.getCostGiudTelematica());
		// not_ante_2014
		int notAnte2014 = 0;

		if (dataNotifica == null || dataCostGiu == null) {
			CalcoloIndicatoriErrore errore = new CalcoloIndicatoriErrore();
			errore.setTipoRischio("CalcoloNonConf8_ModaCostGiudi");
			errore.setTipoErrore("NON CONF ModaCostGiudi");
			errore
					.setMessaggio("Data null tra dataNotifica o dataCostGiu");
			listaErrori.add(errore);
		}

		Date dataComp = null;
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String dateInString = "30/06/2014";
		try {

			dataComp = formatter.parse(dateInString);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		if (dataComp != null) {
			/*
			 * Se Data_notifica < 30/06/2014 allora not_ante_2014 = 1 altrimenti
			 * not_ante_2014 = 0.
			 */
			if (dataNotifica != null) {
				if (dataNotifica.getTime() < dataComp.getTime())
					notAnte2014 = 1;

				if (dataCostGiu != null) {
					// Se Data_cost_giud <> “”
					if (costGiudTelem.equals(PraticaUtil.NO) && notAnte2014 == 0) {
						// Se Cost_giud_telem = “NO” e not_ante_2014 = 0 allora
						// VC = “A319”
						varComp = PraticaUtil.A319;
					} else if (costGiudTelem.equals(PraticaUtil.SI) && notAnte2014 == 0) {
						// Se Cost_giud_telem = “SI” e not_ante_2014 = 0 allora
						// VC = “A329”
						varComp = PraticaUtil.A329;
					}
				}
			}
		}

		if (varComp.length() > 0) {
			varcompPresent.put(varComp, 1);
			insertVarComp(listaPraticheVarCompToInsert, idSPratica, varComp);
		}
		insertLogNonConf("varComp: " + varComp);
		insertLogNonConf("Fine");
	}

	/*
	 * Calcolare info Non conformità 9 - Decadenza
	 */
	private void CalcoloNonConf9_Decadenza() {
		String varComp = "";
		// decadenza
		String decadenza = PraticaUtil.checkStringAndTrimUpperCase(praticaGi
				.getDecadenza());

		if (decadenza.equals("2")) {
			// Se Decadenza = 2 allora VC = “A313”
			varComp = PraticaUtil.A313;
		} else if (decadenza.equals("3")) {
			// Se Decadenza = 3 allora VC = “A322”
			varComp = PraticaUtil.A322;
		}

		if (varComp.length() > 0) {
			varcompPresent.put(varComp, 1);
			insertVarComp(listaPraticheVarCompToInsert, idSPratica, varComp);
		}
		insertLogNonConf("varComp: " + varComp);
		insertLogNonConf("Fine");
	}

	/*
	 * Calcolare info Non conformità 10 – Mancata verifica corrispondenza tra
	 * richiesta ATP e domanda invalidità
	 */
	private void CalcoloNonConf10_MancVerCorrTraRichAtpEDomInv() {
		String varComp = "";

		// Cor_iatp_dinv
		String corIatpDinv = PraticaUtil.checkStringAndTrimUpperCase(praticaGi
				.getCorrispAtpDomAmmInv());

		if (corIatpDinv.equals("2")) {
			// Se Cor_iatp_dinv = 2 allora VC = “A316”
			varComp = PraticaUtil.A316;
		} else if (corIatpDinv.equals("3")) {
			// Se Cor_iatp_dinv = 3 allora VC = “A323”
			varComp = PraticaUtil.A323;
		}

		if (varComp.length() > 0) {
			varcompPresent.put(varComp, 1);
			insertVarComp(listaPraticheVarCompToInsert, idSPratica, varComp);
		}
		insertLogNonConf("varComp: " + varComp);
		insertLogNonConf("Fine");
	}

	/*
	 * Calcolare info Non conformità 11 – Gestione registrazione operazioni
	 * peritali
	 */
	private void CalcoloNonConf11_GesRegOperPeritali() {
		String varComp = "";
		// Data_depd_omol
		Date dataDepdOmol = PraticaUtil.getDateWithoutTimeUsingFormat(praticaPp
				.getDataDepositoDecOmologa());
		// Data_acq_sisco
		Date dataAcqSisco = PraticaUtil.getDateWithoutTimeUsingFormat(praticaAi
				.getDataAcquisizioneSISCO());
		// Reg_inf_oper
		String regInfOper = PraticaUtil.checkStringAndTrimUpperCase(praticaPe
				.getRecInfoOpPeritali());
		// Data_iniz_oper
		Date dataInizOper = PraticaUtil.getDateWithoutTimeUsingFormat(praticaPe
				.getDataComOpPerCTU());

		if (dataDepdOmol == null || dataAcqSisco == null
				|| dataInizOper == null) {
			CalcoloIndicatoriErrore errore = new CalcoloIndicatoriErrore();
			errore.setTipoRischio("CalcoloNonConf11_GesRegOperPeritali");
			errore.setTipoErrore("NON CONF GesRegOperPeritali");
			errore
					.setMessaggio("Data null tra dataDepdOmol o dataAcqSisco o dataInizOper");
			listaErrori.add(errore);
		}

		if (dataDepdOmol == null || dataAcqSisco == null
				|| dataDepdOmol.getTime() <= dataAcqSisco.getTime()) {
			// Se Data_depd_omol <= Data_acq_sisco allora VC = null
			varComp = "";
		} else if (regInfOper.equals(PraticaUtil.PORTALE)) {
			// Se Reg_inf_oper = "portale" allora VC = null
			varComp = "";
		} else if (regInfOper.equals(PraticaUtil.ASSENTE) && dataInizOper == null) {
			// Se Reg_inf_oper = “assente” e Data_iniz_oper = Null allora VC =
			// “A423”
			varComp = PraticaUtil.A423;
		} else if (regInfOper.equals(PraticaUtil.SISCO_POST_CTU) && dataInizOper == null) {
			// Se Reg_inf_oper = “sisco post ctu” e Data_iniz_oper = Null allora
			// VC = “A456”
			varComp = PraticaUtil.A456;
		} else if (regInfOper.equals(PraticaUtil.ASSENTE)) {
			// Se Reg_inf_oper = “assente” allora VC = “A411”
			varComp = PraticaUtil.A411;
		} else if (regInfOper.equals(PraticaUtil.COGISAN) && dataInizOper != null) {
			// Se Reg_inf_oper = “cogisan” e Data_iniz_oper <> Null allora VC =
			// “A422”
			varComp = PraticaUtil.A422;
		} else if (regInfOper.length() > 0 && dataInizOper != null) {
			// Se Reg_inf_oper <> Null e Data_iniz_oper <> Null allora VC =
			// “A419”
			varComp = PraticaUtil.A419;
		}else if (regInfOper.equals(PraticaUtil.COGISAN) && dataInizOper == null) {
			// Se Reg_inf_oper = “cogisan” e Data_iniz_oper = Null allora VC =
			// “A455”
			varComp = PraticaUtil.A455;
		} else if (regInfOper.length() > 0 && dataInizOper == null) {
			// Se Reg_inf_oper <> Null E Data_iniz_oper = Null allora VC =
			// “A421”
			varComp = PraticaUtil.A421;
		} 

		if (varComp.length() > 0) {
			varcompPresent.put(varComp, 1);
			insertVarComp(listaPraticheVarCompToInsert, idSPratica, varComp);
		}
		insertLogNonConf("varComp: " + varComp);
		insertLogNonConf("Fine");
	}

	/*
	 * Calcolare info Non conformità 12 – Gestione visita peritale CTP INPS
	 */
	private void CalcoloNonConf12_GesVisPeritali() {
		String varComp = "";

		// Reg_inf_oper
		String regInfOper = PraticaUtil
				.checkStringAndTrimUpperCaseSiNo(praticaPe
						.getRecInfoOpPeritali());
		// Pres_ctu_oper
		String presCtuOper = PraticaUtil
				.checkStringAndTrimUpperCaseSiNo(praticaPe
						.getPresMedicoInpsDoc());
		// Ass_ctu_medico
		String assCtuMedico = PraticaUtil
				.checkStringAndTrimUpperCaseSiNo(praticaPe
						.getAssCTUMedicoInps());

		if ( !regInfOper.equals(PraticaUtil.ASSENTE) || regInfOper.length() == 0
				|| regInfOper.length() > 0) {
			// Se Reg_inf_oper <> "assente" o Reg_inf_oper = Null o Reg_inf_oper <> “” 
			
			if (presCtuOper.equals(PraticaUtil.SI) && assCtuMedico.equals(PraticaUtil.SI)) {
				// Se Pres_ctu_oper = “si” e Ass_ctu_medico = “si” allora VC =
				// “A428”
				varComp = PraticaUtil.A428;
			} else if (presCtuOper.equals(PraticaUtil.SI) && assCtuMedico.equals(PraticaUtil.NO)) {
				// Se Pres_ctu_oper = “si” e Ass_ctu_medico = “no” allora VC =
				// “A427”
				varComp = PraticaUtil.A427;
			} else if (presCtuOper.equals(PraticaUtil.NO) && assCtuMedico.equals(PraticaUtil.SI)) {
				// Se Pres_ctu_oper = “no” e Ass_ctu_medico = “si” allora VC =
				// “A415”
				varComp = PraticaUtil.A415;
			} else if (presCtuOper.equals(PraticaUtil.NO) && assCtuMedico.equals(PraticaUtil.NO)) {
				// Se Pres_ctu_oper = “no” e Ass_ctu_medico = “no” allora VC =
				// “A416”
				varComp = PraticaUtil.A416;
			}
		}

		if (varComp.length() > 0) {
			varcompPresent.put(varComp, 1);
			insertVarComp(listaPraticheVarCompToInsert, idSPratica, varComp);
		}
		insertLogNonConf("varComp: " + varComp);
		insertLogNonConf("Fine");
	}

	/*
	 * Calcolare info Non conformità 13 – Gestione parere medico
	 */
	private void CalcoloNonConf13_GesParMedico() {
		String varComp = "";

		// Data_prot_bozza
		Date dataProtBozza = PraticaUtil
				.getDateWithoutTimeUsingFormat(praticaPe.getDataProtBozza());
		// Data_prot_ctud
		Date dataProtCtud = PraticaUtil.getDateWithoutTimeUsingFormat(praticaPe
				.getDataProtCTUDef());
		// Parere_bozza_ctu
		String parereBozztCtu = PraticaUtil
				.checkStringAndTrimUpperCase(praticaPe.getParereBozzaCtu());
		// Parere_diss_accet
		String parereDissAccet = PraticaUtil
				.checkStringAndTrimUpperCase(praticaPe
						.getParereDissAccetfascitazione());

		if (dataProtBozza == null || dataProtCtud == null) {
			CalcoloIndicatoriErrore errore = new CalcoloIndicatoriErrore();
			errore.setTipoRischio("CalcoloNonConf13_GesParMedico");
			errore.setTipoErrore("NON CONF GesParMedico");
			errore
					.setMessaggio("Data null tra dataProtBozza o dataProtCtud");
			listaErrori.add(errore);
		}


		if (dataProtBozza != null && dataProtCtud != null) {
			// Se Data_prot_bozza <> Null e Data_prot_ctud <> Null
			if (!parereBozztCtu.equals("1") && !parereDissAccet.equals("1")) {
				// Se Parere_bozza_ctu <> 1 e Parere_diss_accet > 1 allora VC =
				// “A406”
				varComp = PraticaUtil.A406;
			} else if (!parereBozztCtu.equals("1")
					&& parereDissAccet.equals("1")) {
				// Se Parere_bozza_ctu <> 1 e Parere_diss_accet = 1 allora VC =
				// “A407”
				varComp = PraticaUtil.A407;
			} else if (parereBozztCtu.equals("1")
					&& !parereDissAccet.equals("1")) {
				// Se Parere_bozza_ctu = 1 e Parere_diss_accet > 1 allora VC =
				// “A404”
				varComp = PraticaUtil.A404;
			} else if (parereBozztCtu.equals("1")
					&& parereDissAccet.equals("1")) {
				// Se Parere_bozza_ctu = 1 e Parere_diss_accet = 1 allora VC =
				// “A417”
				varComp = PraticaUtil.A417;
			}

		} else {
			if (dataProtBozza != null && dataProtCtud == null) {
				// Se Data_prot_bozza <> Null e Data_prot_ctud = Null
				if (!parereBozztCtu.equals("1")) {
					// Se Parere_bozza_ctu > 1 allora VC = “A453”
					varComp = PraticaUtil.A453;
				} else {
					varComp = PraticaUtil.A454;
				}
			} else {
				if (dataProtBozza == null && parereBozztCtu != null) {
					// Se Data_prot_bozza = Null e Data_prot_ctud <> Null
					if (!parereDissAccet.equals("1")) {
						// Se Parere_diss_accet > 1 allora VC = “A451”
						varComp = PraticaUtil.A451;
					} else {
						// Altrimenti VC = “A452”
						varComp = PraticaUtil.A452;
					}
				}
			}
		}

		if (varComp.length() > 0) {
			varcompPresent.put(varComp, 1);
			insertVarComp(listaPraticheVarCompToInsert, idSPratica, varComp);
		}
		insertLogNonConf("varComp: " + varComp);
		insertLogNonConf("Fine");
	}

	/*
	 * Calcolare info Non conformità 14 – Gestione osservazioni CTP INPS su
	 * parere non concorde
	 */
	private void CalcoloNonConf14_GesOssCtpInpsParConc() {
		String varComp = "";

		// Parere_bozza_ctu
		String parereBozztCtu = PraticaUtil
				.checkStringAndTrimUpperCaseSiNo(praticaPe.getParereBozzaCtu());
		// Parere_diss_accet
		String parereDissAccet = PraticaUtil
				.checkStringAndTrimUpperCase(praticaPe
						.getParereDissAccetfascitazione());
		// Oss_bozza
		String ossBozza = PraticaUtil.checkStringAndTrimUpperCaseSiNo(praticaPe
				.getOssBozza());
		// Oss_parere_def
		String ossParereDef = PraticaUtil
				.checkStringAndTrimUpperCaseSiNo(praticaPe.getOssParDef());

		if (parereBozztCtu.equals("3") || parereDissAccet.equals("3")) {
			// Se Parere_bozza_ctu = 3 o Parere_diss_accet = 3
			if (ossBozza.equals(PraticaUtil.SI) && ossParereDef.equals(PraticaUtil.SI)
					&& parereBozztCtu.equals("2")
					&& parereDissAccet.equals("3")) {
				// Se Oss_bozza = “SI” e Oss_parere_def = “SI” e
				// Parere_bozza_ctu = 2 e Parere_diss_accet = 3 allora VC =
				// “A438”
				varComp = PraticaUtil.A438;
			} else if (ossBozza.equals(PraticaUtil.SI) && ossParereDef.equals(PraticaUtil.SI)
					&& parereBozztCtu.equals("3")
					&& parereDissAccet.equals("2")) {
				// Se Oss_bozza = “SI” e Oss_parere_def = “SI” e
				// Parere_bozza_ctu = 3 e Parere_diss_accet = 2 allora VC =
				// “A439”
				varComp = PraticaUtil.A439;
			} else if (ossBozza.equals(PraticaUtil.SI) && ossParereDef.equals(PraticaUtil.SI)
					&& parereBozztCtu.equals("3")
					&& parereDissAccet.equals("3")) {
				// Se Oss_bozza = “SI” e Oss_parere_def = “SI” e
				// Parere_bozza_ctu = 3 e Parere_diss_accet = 3 allora VC =
				// “A440”
				varComp = PraticaUtil.A440;
			} else if (ossBozza.equals(PraticaUtil.SI) && ossParereDef.equals(PraticaUtil.NO)
					&& parereBozztCtu.equals("2")
					&& parereDissAccet.equals("3")) {
				// Se Oss_bozza = “SI” e Oss_parere_def = “NO” e
				// Parere_bozza_ctu = 2 e Parere_diss_accet = 3 allora VC =
				// “A441”
				varComp = PraticaUtil.A441;
			} else if (ossBozza.equals(PraticaUtil.SI) && ossParereDef.equals(PraticaUtil.NO)
					&& parereBozztCtu.equals("3")
					&& parereDissAccet.equals("2")) {
				// Se Oss_bozza = “SI” e Oss_parere_def = “NO” e
				// Parere_bozza_ctu = 2 e Parere_diss_accet = 2 allora VC =
				// “A443”
				varComp = PraticaUtil.A443;
			} else if (ossBozza.equals(PraticaUtil.SI) && ossParereDef.equals(PraticaUtil.NO)
					&& parereBozztCtu.equals("3")
					&& parereDissAccet.equals("3")) {
				// Se Oss_bozza = “SI” e Oss_parere_def = “NO” e
				// Parere_bozza_ctu = 3 e Parere_diss_accet = 3 allora VC =
				// “A444”
				varComp = PraticaUtil.A444;
			} else if (ossBozza.equals(PraticaUtil.NO) && ossParereDef.equals(PraticaUtil.SI)
					&& parereBozztCtu.equals("2")
					&& parereDissAccet.equals("3")) {
				// Se Oss_bozza = “NO” e Oss_parere_def = “SI” e
				// Parere_bozza_ctu = 2 e Parere_diss_accet = 3 allora VC =
				// “A0445
				varComp = PraticaUtil.A445;
			} else if (ossBozza.equals(PraticaUtil.NO) && ossParereDef.equals(PraticaUtil.SI)
					&& parereBozztCtu.equals("3")
					&& parereDissAccet.equals("2")) {
				// Se Oss_bozza = “NO” e Oss_parere_def = “SI” e
				// Parere_bozza_ctu = 3 e Parere_diss_accet = 2 allora VC =
				// “A446”
				varComp = PraticaUtil.A446;
			} else if (ossBozza.equals(PraticaUtil.NO) && ossParereDef.equals(PraticaUtil.SI)
					&& parereBozztCtu.equals("3")
					&& parereDissAccet.equals("3")) {
				// Se Oss_bozza = “NO” e Oss_parere_def = “SI” e
				// Parere_bozza_ctu = 3 e Parere_diss_accet = 3 allora VC =
				// “A447”
				varComp = PraticaUtil.A447;
			} else if (ossBozza.equals(PraticaUtil.NO) && ossParereDef.equals(PraticaUtil.NO)
					&& parereBozztCtu.equals("2")
					&& parereDissAccet.equals("3")) {
				// Se Oss_bozza = “NO” e Oss_parere_def = “NO” e
				// Parere_bozza_ctu = 2 e Parere_diss_accet = 3 allora VC =
				// “A448”
				varComp = PraticaUtil.A448;
			} else if (ossBozza.equals(PraticaUtil.NO) && ossParereDef.equals(PraticaUtil.NO)
					&& parereBozztCtu.equals("3")
					&& parereDissAccet.equals("2")) {
				// Se Oss_bozza = “NO” e Oss_parere_def = “NO” e
				// Parere_bozza_ctu = 2 e Parere_diss_accet = 2 allora VC =
				// “A449”
				varComp = PraticaUtil.A449;
			} else if (ossBozza.equals(PraticaUtil.NO) && ossParereDef.equals(PraticaUtil.NO)
					&& parereBozztCtu.equals("3")
					&& parereDissAccet.equals("3")) {
				// Se Oss_bozza = “NO” e Oss_parere_def = “NO” e
				// Parere_bozza_ctu = 3 e Parere_diss_accet = 3 allora VC =
				// “A450”
				varComp = PraticaUtil.A450;
			}

		}

		if (varComp.length() > 0) {
			varcompPresent.put(varComp, 1);
			insertVarComp(listaPraticheVarCompToInsert, idSPratica, varComp);
		}
		insertLogNonConf("varComp: " + varComp);
		insertLogNonConf("Fine");
	}

	/*
	 * Calcolare info Non conformità 15 – Gestione bozza CTU
	 */
	private void CalcoloNonConf15_GestioneBozzaCtu() {
		
		String RecInfoOpPeritali = praticaPe.getRecInfoOpPeritali();
		String varComp = "";
		// Data_arr_bozza
		Date dataArrBozza = PraticaUtil.getDateWithoutTimeUsingFormat(praticaPe
				.getDataArrBozza());
		// Data_prot_bozza
		Date dataProtBozza = PraticaUtil
				.getDateWithoutTimeUsingFormat(praticaPe.getDataProtBozza());
		// Reg_inf_oper
		String regInfOper = PraticaUtil.checkStringAndTrimUpperCase(praticaPe
				.getRecInfoOpPeritali());
		// Ctu_bozza_immag
		String ctuBozzaImm = PraticaUtil
				.checkStringAndTrimUpperCaseSiNo(praticaPe.getCtuBozzaImgAtti());

		if (dataArrBozza == null || dataProtBozza == null) {
			CalcoloIndicatoriErrore errore = new CalcoloIndicatoriErrore();
			errore.setTipoRischio("CalcoloNonConf15_GestioneBozzaCtu");
			errore.setTipoErrore("NON CONF GestioneBozzaCtu");
			errore
					.setMessaggio("Data null tra dataArrBozza o dataProtBozza");
			listaErrori.add(errore);
		}

		if ((dataArrBozza != null || dataProtBozza != null)
				&& !regInfOper.equals(PraticaUtil.PORTALE)) {
			// Se (Data_arr_bozza <> Null o Data_prot_bozza <> Null) e Reg_inf_oper <> "portale" 
			if (dataProtBozza == null && dataArrBozza != null) {
				// Se Data_prot_bozza = Null e Data_arr_bozza <> Null allora VC
				// = “A433”
				varComp = PraticaUtil.A433;
			}else if (dataArrBozza != null && dataProtBozza != null
					&& PraticaUtil.daysBetween( dataArrBozza , dataProtBozza) < 3
					&& ctuBozzaImm.equals(PraticaUtil.SI)) {
				// Se (Data_prot_bozza - Data_arr_bozza) < 2 e Ctu_bozza_immag =
				// “si” allora VC = “A429”
				varComp = PraticaUtil.A429;
			} else if (dataArrBozza != null && dataProtBozza != null
					&& PraticaUtil.daysBetween( dataArrBozza,dataProtBozza) < 3
					&& ctuBozzaImm.equals(PraticaUtil.NO)) {
				// Se (Data_prot_bozza - Data_arr_bozza) < 2 e Ctu_bozza_immag =
				// “no” allora VC = “A430”
				varComp = PraticaUtil.A430;
			} else if (dataArrBozza != null && dataProtBozza != null
					&& PraticaUtil.daysBetween( dataArrBozza , dataProtBozza) > 4
					&& ctuBozzaImm.equals(PraticaUtil.SI)) {
				// Se (Data_prot_bozza - Data_arr_bozza) > 3 e Ctu_bozza_immag =
				// “si” allora VC = “A431”
				varComp = PraticaUtil.A431;
			} else if (dataArrBozza != null && dataProtBozza != null
					&& PraticaUtil.daysBetween( dataArrBozza, dataProtBozza ) < 5
					&& ctuBozzaImm.equals(PraticaUtil.SI)) {
				// se (Data_prot_bozza - Data_arr_bozza) < 4 e Ctu_bozza_immag =
				// “si” allora VC = “A432”
				varComp = PraticaUtil.A432;
			} 
		}
		
		if(RecInfoOpPeritali == null){
			RecInfoOpPeritali="";
		}
		if (dataProtBozza == null && dataArrBozza == null && !RecInfoOpPeritali.trim().equalsIgnoreCase("Portale")) {
			// Se Data_term_diss = Null e Data_term_dissd = Null allora VC =
			// “A413”
			varComp = PraticaUtil.A458;
		} 

		if (varComp.length() > 0) {
			varcompPresent.put(varComp, 1);
			insertVarComp(listaPraticheVarCompToInsert, idSPratica, varComp);
		}
		insertLogNonConf("varComp: " + varComp);
		insertLogNonConf("Fine");
	}

	/*
	 * Calcolare info Non conformità 16 – Gestione CTU definitiva
	 */
	private void CalcoloNonConf16_GestioneBozzaCtu() {
		String varComp = "";

		// Data_comu_depctu
		Date dataComuDepctu = PraticaUtil
				.getDateWithoutTimeUsingFormat(praticaPe.getDataComDepCTUDefCanc());
		// Data_prot_ctud
		Date dataProtCtud = PraticaUtil.getDateWithoutTimeUsingFormat(praticaPe
				.getDataProtCTUDef());
		// Ctu_def_immag
		String ctuDefImm = PraticaUtil
				.checkStringAndTrimUpperCaseSiNo(praticaPe.getCtuDefImgAtti());

		if (dataComuDepctu == null || dataProtCtud == null) {
			CalcoloIndicatoriErrore errore = new CalcoloIndicatoriErrore();
			errore.setTipoRischio("CalcoloNonConf16_GestioneBozzaCtu");
			errore.setTipoErrore("NON CONF GestioneBozzaCtu");
			errore
					.setMessaggio("Data null tra dataComuDepctu o dataProtCtud");
			listaErrori.add(errore);
		}

		if (dataComuDepctu != null && dataProtCtud != null) {
			// Se Data_comu_depctu <> Null

			if (PraticaUtil.daysBetween( dataComuDepctu , dataProtCtud ) < 2
					&& ctuDefImm.equals(PraticaUtil.SI)) {
				// Se (Data_prot_ctud - Data_comu_depctu) < 2 e Ctu_def_immag =
				// “si” allora VC = “A434”
				varComp = PraticaUtil.A434;
			} else if (PraticaUtil.daysBetween(dataComuDepctu , dataProtCtud) < 6
					&& ctuDefImm.equals(PraticaUtil.SI)) {
				// Se (Data_prot_ctud - Data_comu_depctu) < 6 e Ctu_def_immag =
				// “si” allora VC = “A437”
				varComp = PraticaUtil.A437;
			} else if (PraticaUtil.daysBetween(dataComuDepctu , dataProtCtud) < 2
					&& ctuDefImm.equals(PraticaUtil.NO)) {
				// Se (Data_prot_ctud - Data_comu_depctu) < 2 e Ctu_def_immag =
				// “no” allora VC = “A435”
				varComp = PraticaUtil.A435;
			} else if (PraticaUtil.daysBetween(dataComuDepctu , dataProtCtud) > 5
					&& ctuDefImm.equals(PraticaUtil.SI)) {
				// Se (Data_prot_ctud - Data_comu_depctu) > 5 e Ctu_def_immag =
				// “si” allora VC = “A436”
				varComp = PraticaUtil.A436;
			}

		}

		if (varComp.length() > 0) {
			varcompPresent.put(varComp, 1);
			insertVarComp(listaPraticheVarCompToInsert, idSPratica, varComp);
		}
		insertLogNonConf("varComp: " + varComp);
		insertLogNonConf("Fine");
	}

	/*
	 * Calcolare info Non conformità 17 – Gestione comunicazione cancelleria
	 */
	private void CalcoloNonConf17_GestioneComuCanc() {
		String varComp = "";

		// Data_depd_omol
		Date dataDepdOmol = PraticaUtil.getDateWithoutTimeUsingFormat(praticaPp
				.getDataDepositoDecOmologa());
		// Data_acq_sisco
		Date dataAcqSisco = PraticaUtil.getDateWithoutTimeUsingFormat(praticaAi
				.getDataAcquisizioneSISCO());
		// Data_term_diss
		Date dataTermDiss = PraticaUtil
				.getDateWithoutTimeUsingFormat(praticaPe.getDataTermDissSisco());
		// Data_term_dissd
		Date dataTermDissd = PraticaUtil
				.getDateWithoutTimeUsingFormat(praticaPe.getDataTermDissDecr());
		
		
		if (dataDepdOmol == null || dataAcqSisco == null
				|| dataTermDiss == null || dataTermDissd == null) {
			CalcoloIndicatoriErrore errore = new CalcoloIndicatoriErrore();
			errore.setTipoRischio("CalcoloNonConf17_GestioneComuCanc");
			errore.setTipoErrore("NON CONF GestioneComuCanc");
			errore
					.setMessaggio("Data null tra dataDepdOmol o dataAcqSisco o dataTermDiss o dataTermDissd");
			listaErrori.add(errore);
		}

		if (dataTermDissd != praticaCisco.getDissInps())
			PraticaUtil.getDateWithoutTimeUsingFormat(praticaPe
					.getDataTermDissDecr());

		if (dataDepdOmol != null && dataAcqSisco != null
				&& dataDepdOmol.getTime() <= dataAcqSisco.getTime()) {
			// Se Data_depd_omol <> null e Data_acq_sisco <> null e Data_depd_omol <= Data_acq_sisco allora VC = null
			varComp = "";
		} else if (dataTermDiss == null && dataTermDissd == null) {
			// Se Data_term_diss = Null e Data_term_dissd = Null allora VC =
			// “A413”
			varComp = PraticaUtil.A413;
		} else if (dataTermDiss != null && dataTermDissd == null) {
			// Se Data_term_diss <> Null e Data_term_dissd = Null allora VC =
			// “A426”
			varComp = PraticaUtil.A426;
		} else if (dataTermDiss == null && dataTermDissd != null) {
			// Se Data_term_diss = Null e Data_term_dissd <> Null allora VC =
			// “A413”
			varComp = PraticaUtil.A413;
		} else if (dataTermDiss != null && dataTermDissd != null
				&& dataTermDiss.getTime() == dataTermDissd.getTime()) {
			// Se Data_term_diss = Data_term_dissd allora VC = “A424”
			varComp = PraticaUtil.A424;
		} else if (dataTermDiss != null && dataTermDissd != null
				&& dataTermDiss.getTime() > dataTermDissd.getTime()) {
			// Se Data_term_diss > Data_term_dissd allora VC = “A425”
			varComp = PraticaUtil.A425;
		} else if (dataTermDiss != null && dataTermDissd != null
				&& dataTermDiss.getTime() < dataTermDissd.getTime()) {
			// Se Data_term_diss < Data_term_dissd allora VC = “A457”
			varComp = PraticaUtil.A457;
		}

		if (varComp.length() > 0) {
			varcompPresent.put(varComp, 1);
			insertVarComp(listaPraticheVarCompToInsert, idSPratica, varComp);
		}
		insertLogNonConf("varComp: " + varComp);
		insertLogNonConf("Fine");
	}

	/*
	 * Calcolare info Non conformità 18 – Gestione del dissenso (in caso di
	 * parere non concorde del medico)
	 */
	private void CalcoloNonConf18_GestioneDiss() {
		String varComp = "";
		// Parere_diss_accet
		String parereDissAccett = PraticaUtil
				.checkStringAndTrimUpperCase(praticaPe
						.getParereDissAccetfascitazione());
		// Int_depd_termd
		long intDepdTermd = praticaPe.getIntTempComDepCTUtoCTUdef();
		// Data_depd_omol
		Date dataDepdOmol = PraticaUtil.getDateWithoutTimeUsingFormat(praticaPp
				.getDataDepositoDecOmologa());
		// Data_term_diss
		Date dataTermDiss = PraticaUtil
				.getDateWithoutTimeUsingFormat(praticaCisco.getTDissenso());
		// Comu_depd_uffl
		String comuDepdIffl = PraticaUtil.checkStringAndTrimUpperCase(praticaPp
				.getComDepDissUffLegale());
		// Data_term_disss
		Date dataTermDisss = PraticaUtil
				.getDateWithoutTimeUsingFormat(praticaPe.getDataTermDissSisco());

		// Data_depo_diss
		Date dataDepoDiss = PraticaUtil
				.getDateWithoutTimeUsingFormat(praticaPp.getDataDepDiss());
		
		
		if (dataDepdOmol == null || dataTermDiss == null
				|| dataTermDisss == null || dataDepoDiss == null) {
			CalcoloIndicatoriErrore errore = new CalcoloIndicatoriErrore();
			errore.setTipoRischio("CalcoloNonConf18_GestioneDiss");
			errore.setTipoErrore("NON CONF GestioneDiss");
			errore
					.setMessaggio("Data null tra dataDepdOmol o dataTermDiss o dataTermDisss");
			listaErrori.add(errore);
		}

		if (parereDissAccett.equals("3") && dataTermDiss != null) {
			// Se Parere_diss_accet = 2 e Data_term_diss <> null 
			
			if (dataDepoDiss == null ){
				// Se Data_depo_diss = Null allora VC = “A517”
				varComp = PraticaUtil.A517;
			}else if ( intDepdTermd > -1
					&& comuDepdIffl.equals("1")) {
				// Se Int_depd_termd > -1 e Comu_depd_uffl = 1 allora VC = “A515”
				varComp = PraticaUtil.A515;
			} else if (intDepdTermd > -1
					&& comuDepdIffl.equals("2")) {
				// Se Int_depd_termd > -1 Comu_depd_uffl = 2 allora VC = “A516”
				varComp = PraticaUtil.A516;
			}  else if (dataDepoDiss.getTime() > dataTermDiss.getTime()) {
				// Se Data_depo_diss > Data_term_diss allora VC = “A517”
				varComp = PraticaUtil.A517;
			}
			
		}

		if (varComp.length() > 0) {
			varcompPresent.put(varComp, 1);
			insertVarComp(listaPraticheVarCompToInsert, idSPratica, varComp);
		}
		insertLogNonConf("varComp: " + varComp);
		insertLogNonConf("Fine");
	}

	/*
	 * Calcolare info Non conformità 19 – Correttezza registrazione dati
	 */
	private void CalcoloNonConf19_CorreRegDati() {
		String varComp = "";
		// Data_depd_omol
		Date dataDepdOmol = PraticaUtil.getDateWithoutTimeUsingFormat(praticaPp
				.getDataDepositoDecOmologa());
		// Reg_dati_prat
		String regDatiPrat = PraticaUtil.checkStringAndTrimUpperCase(praticaPp
				.getRecDatiPratica());
		// Cod_chiu_corr
		String codChiuCorr = PraticaUtil.checkStringAndTrimUpperCase(praticaPp
				.getCodChiusuraCorretto());
		
		if (dataDepdOmol == null) {
			CalcoloIndicatoriErrore errore = new CalcoloIndicatoriErrore();
			errore.setTipoRischio("CalcoloNonConf19_CorreRegDati");
			errore.setTipoErrore("NON CONF CorreRegDati");
			errore
					.setMessaggio("Data dataDepdOmol a null");
			listaErrori.add(errore);
		}

		if (codChiuCorr.equals("2") || codChiuCorr.equals("3")){
			// Se Cod_chiu_corr = 2 o Cod_chiu_corr = 3
			if (dataDepdOmol != null) {
				// Se Data_depd_omol <> Null
				if (regDatiPrat.equals("1")) {
					// Se Reg_dati_prat = 1 allora VC = “A501”
					varComp = PraticaUtil.A501;
				} else if (regDatiPrat.equals("2")) {
					// Se Reg_dati_prat = 2 allora VC = “A502”
					varComp = PraticaUtil.A503;
				} else if (regDatiPrat.equals("3")) {
					// Se Reg_dati_prat = 3 allora VC = “A503”
					varComp = PraticaUtil.A502;
				}
			}
		}
		
		if (varComp.length() > 0) {
			varcompPresent.put(varComp, 1);
			insertVarComp(listaPraticheVarCompToInsert, idSPratica, varComp);
		}
		insertLogNonConf("varComp: " + varComp);
		insertLogNonConf("Fine");
	}

	/*
	 * Calcolare info Non conformità 20 – Correttezza chiusura pratica
	 */
	private void CalcoloNonConf20_CorrChiuPratica() {
		String varComp = "";

		// Cod_chiu_ins
		String codChiuIns = PraticaUtil.checkStringAndTrimUpperCase(praticaPp
				.getCodChiusuraInserito());
		// Ver_domol_ctud
		String verDomolCstud = PraticaUtil
				.checkStringAndTrimUpperCase(praticaPp
						.getCorrispDecrOmgEctuDef());
		// Cod_chiu_corr
		String codChiuCorr = PraticaUtil.checkStringAndTrimUpperCase(praticaPp
				.getCodChiusuraCorretto());
		// Data_trasm_lsp
		Date dataTramLsp = PraticaUtil.getDateWithoutTimeUsingFormat(praticaPp
				.getDataTrasmissDecrLPS());

		if (dataTramLsp == null) {
			CalcoloIndicatoriErrore errore = new CalcoloIndicatoriErrore();
			errore.setTipoRischio("CalcoloNonConf20_CorrChiuPratica");
			errore.setTipoErrore("NON CONF CorrChiuPratica");
			errore
					.setMessaggio("Data dataTramLsp a null");
			listaErrori.add(errore);
		}

		if (codChiuIns.length() > 0 && verDomolCstud.equals("1")) {
			// Se Esito_da_proc <> Null e Ver_domol_ctud = 1 allora VC = “A506”
			varComp = PraticaUtil.A506;
		} else if (codChiuIns.length() > 0 && verDomolCstud.equals("2")
				&& dataTramLsp != null) {
			// Se Esito_da_proc <> Null e Ver_domol_ctud = 2 e Data_trasm_lsp <>
			// Null allora VC = “A507”
			varComp = PraticaUtil.A507;
		} else if (codChiuIns.length() == 0 && verDomolCstud.equals("3")) {
			// Se Esito_da_proc = Null e Ver_domol_ctud = 3 allora VC = “A508”
			varComp = PraticaUtil.A508;
		} else if (codChiuCorr.equals(codChiuIns)) {
			// Se Cod_chiu_corr = Esito_da_proc allora VC = “A504”
			varComp = PraticaUtil.A504;
		} else if (!codChiuCorr.equals(codChiuIns)) {
			// Se Cod_chiu_corr <> Esito_da_proc allora VC = “A505”
			varComp = PraticaUtil.A505;
		}

		if (varComp.length() > 0) {
			varcompPresent.put(varComp, 1);
			insertVarComp(listaPraticheVarCompToInsert, idSPratica, varComp);
		}
		insertLogNonConf("varComp: " + varComp);
		insertLogNonConf("Fine");
	}

	/*
	 * Calcolare info Non conformità 21 – Comunicazione alla LPS
	 */
	private void CalcoloNonConf21_ComuLps() {
		String varComp = "";

		// Data_depd_omol
		Date dataDepdOmol = PraticaUtil.getDateWithoutTimeUsingFormat(praticaPp
				.getDataDepositoDecOmologa());
		// Esito_da_proc
		String esitoDaProc = PraticaUtil.checkStringAndTrimUpperCase(praticaPp
				.getCodChiusuraInserito());
		// prest_ec
		String prestEc = PraticaUtil.checkStringAndTrimUpperCaseSiNo(praticaFd
				.getPrestazioneEconomica());
		// prest_er
		Double prestEr = PraticaUtil.checkValueImp(praticaFd
				.getImportoPrestazioneErogata());
		// Data_trasm_lsp
		Date dataTramLsp = PraticaUtil.getDateWithoutTimeUsingFormat(praticaPp
				.getDataTrasmissDecrLPS());
		// Omol_alleg
		String omolAlleg = PraticaUtil.checkStringAndTrimUpperCaseSiNo(praticaPp
				.getOmologaAllegata());

		if (dataDepdOmol == null || dataTramLsp == null) {
			CalcoloIndicatoriErrore errore = new CalcoloIndicatoriErrore();
			errore.setTipoRischio("CalcoloNonConf21_ComuLps");
			errore.setTipoErrore("NON CONF ComuLps");
			errore
					.setMessaggio("Data null tra dataDepdOmol o dataTramLsp");
			listaErrori.add(errore);
		}

		if (dataDepdOmol != null
				&& (esitoDaProc.equals("2") || esitoDaProc.equals("3"))
				&& (prestEc.equals(PraticaUtil.SI) || prestEr.doubleValue() > 0D)) {
			// Se Data_depd_omol <> Null
			// e (Esito_da_proc = 2 o Esito_da_proc = 3) e (prest_ec = Vero o
			// prest_er <> 0)

			if (dataTramLsp == null) {
				// Se Esito_da_proc > 1 e Data_depd_omol <> Null e
				// Data_trasm_lsp = Null allora VC = “A513”
				varComp = PraticaUtil.A513;
			}else if (dataTramLsp != null
					&& PraticaUtil.daysBetween( dataDepdOmol , dataTramLsp ) < 31
					&& omolAlleg.equals(PraticaUtil.SI)) {
				// Se (Data_trasm_lsp - Data_depd_omol) < 31 e Omol_alleg = “si”
				// allora VC = “A509”
				varComp = PraticaUtil.A509;
			} else if (dataTramLsp != null
					&& PraticaUtil.daysBetween(dataDepdOmol , dataTramLsp) < 31
					&& omolAlleg.equals(PraticaUtil.NO)) {
				// Se (Data_trasm_lsp - Data_depd_omol) < 31 e Omol_alleg = “no”
				// allora VC = “A510”
				varComp = PraticaUtil.A510;
			}
			if (dataTramLsp != null
					&& PraticaUtil.daysBetween(dataDepdOmol , dataTramLsp) > 30
					&& omolAlleg.equals(PraticaUtil.SI)) {
				// Se (Data_trasm_lsp - Data_depd_omol) > 30 e Omol_alleg = “si”
				// allora VC = “A511”
				varComp = PraticaUtil.A511;
			}
			if (dataTramLsp != null
					&& PraticaUtil.daysBetween(dataDepdOmol , dataTramLsp) > 30
					&& omolAlleg.equals(PraticaUtil.NO)) {
				// Se (Data_trasm_lsp - Data_depd_omol) > 30 e Omol_alleg = “no”
				// allora VC = “A512”
				varComp = PraticaUtil.A512;
			}

		}

		if (varComp.length() > 0) {
			varcompPresent.put(varComp, 1);
			insertVarComp(listaPraticheVarCompToInsert, idSPratica, varComp);
		}
		insertLogNonConf("varComp: " + varComp);
		insertLogNonConf("Fine");
	}

	/*
	 * Calcolare info Non conformità 22 – Gestione deposito ricorso di 1° grado
	 */
	private void CalcoloNonConf22_GesDepRicoPrimoGrado() {
		String varComp = "";

		// Data_term_diss
		Date dataTermDiss = PraticaUtil
				.getDateWithoutTimeUsingFormat(praticaPe.getDataTermDissSisco());
		
		// Data_depr_1grad -- DATA_DEP_RIC_PRIMO_G
		Date dataDeprPrimograd = PraticaUtil
				.getDateWithoutTimeUsingFormat(praticaPp.getDataDepRicPrimoG());

		if (dataTermDiss == null || dataDeprPrimograd == null) {
			CalcoloIndicatoriErrore errore = new CalcoloIndicatoriErrore();
			errore.setTipoRischio("CalcoloNonConf22_GesDepRicoPrimoGrado");
			errore.setTipoErrore("NON CONF GesDepRicoPrimoGrado");
			errore
					.setMessaggio("Data null tra dataTermDiss o dataDeprPrimograd");
			listaErrori.add(errore);
			insertLogNonConf("varComp: N/A ( Data null tra dataTermDiss o dataDeprPrimograd )" );
			insertLogNonConf("Fine");
			return;
		}

		if (dataTermDiss != null) {
			// Se Data_term_diss <> Null

			if (PraticaUtil.daysBetween( dataTermDiss , dataDeprPrimograd) < 31) {
				// Se (Data_depr_1grad - Data_term_diss) < 31 allora VC = “A523”
				varComp = PraticaUtil.A523;
			} else if (PraticaUtil.daysBetween(dataTermDiss , dataDeprPrimograd) > 30) {
				// Se (Data_depr_1grad - Data_term_diss) > 30 allora VC = “A524”
				varComp = PraticaUtil.A524;
			}
		}

		if (varComp.length() > 0) {
			varcompPresent.put(varComp, 1);
			insertVarComp(listaPraticheVarCompToInsert, idSPratica, varComp);
		}
		insertLogNonConf("varComp: " + varComp);
		insertLogNonConf("Fine");
	}

	/*
	 * Calcolare info Non conformità 23 – Liquidazione spese CTU
	 */
	private void CalcoloNonConf23_LiqSpeseCTU() {
		String varComp = "";

		// Flag_decr_omol
		String flagDescrOmol = PraticaUtil
				.checkStringAndTrimUpperCaseSiNo(praticaEp.getPresDecrOmgFasc());
		// Condanna_pag_ctu
		String condannaPagCtu = PraticaUtil
				.checkStringAndTrimUpperCaseSiNo(praticaEp
						.getCondannaPagCtuAtpo());
		// Data_liq_ctu
		Date dataLigctu = PraticaUtil.getDateWithoutTimeUsingFormat(praticaEp
				.getDataLiqCtuAtpo());
		// Data_dliq_omol
		Date dataDliqOmol = PraticaUtil.getDateWithoutTimeUsingFormat(praticaEp
				.getDataDecrLiqCtu());
		// Data_fattura
		Date dataFattura = PraticaUtil.getDateWithoutTimeUsingFormat(praticaEp
				.getDataFattura());
		// Data_term_diss
		Date dataTermDiss = PraticaUtil
				.getDateWithoutTimeUsingFormat(praticaCisco.getTDissenso());
		// Imp_spesectu_pag
		Double impSpeseCtuPag = PraticaUtil.checkValueImp(praticaEp.getImpSpeseCtuPagate());
		
		if (dataLigctu == null || dataDliqOmol == null || dataFattura == null
				|| dataTermDiss == null) {
			CalcoloIndicatoriErrore errore = new CalcoloIndicatoriErrore();
			errore.setTipoRischio("CalcoloNonConf23_LiqSpeseCTU");
			errore.setTipoErrore("NON CONF LiqSpeseCTU");
			errore
					.setMessaggio("Data null tra dataLigctu o dataDliqOmol o dataFattura o dataTermDiss");
			listaErrori.add(errore);
		}

		if(impSpeseCtuPag.doubleValue() > 0D){
			// Se Imp_spesectu_pag > 0

			if (flagDescrOmol.equals(PraticaUtil.SI) /* && !condannaPagCtu.equals("NO")*/
					&& dataLigctu != null) {
				// Se Flag_decr_omol ="si" e Condanna_pag_ctu <> "no" e
				// Data_liq_ctu<> Null
				if ( (dataDliqOmol == null || dataFattura == null)
						&& condannaPagCtu.equals(PraticaUtil.SI)) {
					// Se Data_dliq_omol = Null o Data_fattura = Null e
					// Condanna_pag_ctu = "si" allora VC = “A607”
					varComp = PraticaUtil.A607;
				} else if (dataDliqOmol != null && dataFattura != null
						&& condannaPagCtu.equals(PraticaUtil.SI)) {
					// Se Data_dliq_omol <> Null e Data_fattura <> e
					// Condanna_pag_ctu = "si" allora VC = “A606”
					varComp = PraticaUtil.A606;
				}
			} else {
				if (dataTermDiss != null) {
					// Se Data_term_diss <> Null
					if (dataDliqOmol == null && dataFattura == null
							&& condannaPagCtu.equals(PraticaUtil.SI)) {
						// Se Data_dliq_omol = null e Data_fattura = Null e
						// Condanna_pag_ctu = "si" allora VC = “A607”
						varComp = PraticaUtil.A607;
					} else if (dataTermDiss != null && dataFattura != null
							&& condannaPagCtu.equals(PraticaUtil.SI)) {
						// Se Data_dliq_omol <> Null e Data_fattura <> Null e
						// Condanna_pag_ctu = "si" allora VC = “A606”
						varComp = PraticaUtil.A606;
					}
				}
			}
		}
		
		if (varComp.length() > 0) {
			varcompPresent.put(varComp, 1);
			insertVarComp(listaPraticheVarCompToInsert, idSPratica, varComp);
		}
		insertLogNonConf("varComp: " + varComp);
		insertLogNonConf("Fine");
	}

	/*
	 * Calcolare info Non conformità 24 – Gestione diffida alla controparte per
	 * spese legali da recuperare
	 */
	private void CalcoloNonConf24_GesDiffControSpeseLegaRec() {
		String varComp = "";
		// Cod_pagam_slcor
		String codPagamSlcorString = PraticaUtil
				.checkStringAndTrimUpperCase(praticaPp
						.getCodPagamentoSpeseLegaliCorretto());
		Integer codPagamSlcor = 0;
		try {
			codPagamSlcor = Integer.parseInt(codPagamSlcorString);
		} catch (Exception e) {
			log.error(e);
		}

		// Data_lett_pagam
		Date dataLettPagam = PraticaUtil
				.getDateWithoutTimeUsingFormat(praticaEp
						.getDatalettInvPagSpeseLegali());
		// Data_depd_omol
		Date dataDepdOmol = PraticaUtil.getDateWithoutTimeUsingFormat(praticaPp
				.getDataDepositoDecOmologa());

		if (dataLettPagam == null || dataDepdOmol == null

		) {
			CalcoloIndicatoriErrore errore = new CalcoloIndicatoriErrore();
			errore.setTipoRischio("CalcoloNonConf24_GesDiffControSpeseLegaRec");
			errore.setTipoErrore("NON CONF GesDiffControSpeseLegaRec");
			errore
					.setMessaggio("Data null tra dataLettPagam o dataDepdOmol");
			listaErrori.add(errore);
		}

		if (codPagamSlcor == 3 || codPagamSlcor == 4) {
			// Se Cod_pagam_slcor = 3 o Cod_pagam_slcor = 4
			if (dataLettPagam == null) {
				// Se Data_depd_omol <> Null e Data_lett_pagam = Null allora VC
				// = “A616”
				varComp = PraticaUtil.A616;
			} else if (dataLettPagam != null
					&& dataDepdOmol != null
					&& PraticaUtil.daysBetween(dataDepdOmol ,dataLettPagam) < 31) {
				// Se (Data_lett_pagam - Data_depd_omol) < 31 allora VC = “A614”
				varComp = PraticaUtil.A614;
			} else if (dataLettPagam != null
					&& dataDepdOmol != null
					&& PraticaUtil.daysBetween(dataDepdOmol ,dataLettPagam) > 30) {
				// Se (Data_lett_pagam - Data_depd_omol) > 30 allora VC = “A615”
				varComp = PraticaUtil.A615;
			}  
		}

		if (varComp.length() > 0) {
			varcompPresent.put(varComp, 1);
			insertVarComp(listaPraticheVarCompToInsert, idSPratica, varComp);
		}
		insertLogNonConf("varComp: " + varComp);
		insertLogNonConf("Fine");
	}

	/*
	 * Calcolare info Non conformità 25 – Gestione diffida alla controparte per
	 * spese CTU da recuperare
	 */
	private void CalcoloNonConf25_GesDiffControSpeseCTURec() {
		String varComp = "";
		// Cod_pagam_slcor
		// String codPagamSlcor =
		// praticaPp.getCodPagamentoSpeseLegaliCorretto();
		String codPagamSlcorString = PraticaUtil
				.checkStringAndTrimUpperCase(praticaPp
						.getCodPagamentoSpeseLegaliCorretto());
		Integer codPagamSlcor = 0;
		try {
			codPagamSlcor = Integer.parseInt(codPagamSlcorString); // DA VERIFICARE
		} catch (Exception e) {
			log.error(e);
		}

		// Spese_ctu_ant
		String speseCtuAnt = PraticaUtil
				.checkStringAndTrimUpperCaseSiNo(praticaEp.getAntSpeseCtu());
		// Data_recup_spese
		Date dataRecupSpese = PraticaUtil
				.getDateWithoutTimeUsingFormat(praticaEp
						.getDataLetteraRecuperoSpeseCtu());
		// Data_depd_omol
		Date dataDepdOmol = PraticaUtil.getDateWithoutTimeUsingFormat(praticaPp
				.getDataDepositoDecOmologa());

		if (dataRecupSpese == null || dataDepdOmol == null

		) {
			CalcoloIndicatoriErrore errore = new CalcoloIndicatoriErrore();
			errore.setTipoRischio("CalcoloNonConf25_GesDiffControSpeseCTURec");
			errore.setTipoErrore("NON CONF GesDiffControSpeseCTURec");
			errore
					.setMessaggio("Data null tra dataDepdOmol o dataDepdOmol");
			listaErrori.add(errore);
		}

		if (codPagamSlcor < 5 && codPagamSlcor != 1 && speseCtuAnt.equals(PraticaUtil.SI)) {
			// Se Cod_pagam_slcor < 5 e Cod_pagam_slcor <> 1 e Spese_ctu_ant =
			// “si”
			 if (dataRecupSpese == null) {
					// Se Data_depd_omol <> Null e Data_recup_spese = Null e
					// Spese_ctu_ant = “si” allora VC = “A617”
					varComp = PraticaUtil.A617;
			}else if (dataRecupSpese != null
					&& dataDepdOmol != null
					&& PraticaUtil.daysBetween(dataDepdOmol,dataRecupSpese) < 31) {
				// Se (Data_recup_spese - Data_depd_omol) < 31 allora VC =
				// “A619”
				varComp = PraticaUtil.A619;
			} else if (dataRecupSpese != null
					&& dataDepdOmol != null
					&& PraticaUtil.daysBetween(dataDepdOmol,dataRecupSpese) > 30) {
				// Se (Data_recup_spese - Data_depd_omol) > 30 allora VC =
				// “A618”
				varComp = PraticaUtil.A618;
			} 
		}

		if (varComp.length() > 0) {
			varcompPresent.put(varComp, 1);
			insertVarComp(listaPraticheVarCompToInsert, idSPratica, varComp);
		}
		insertLogNonConf("varComp: " + varComp);
		insertLogNonConf("Fine");
	}

	/*
	 * Calcolare info Non conformità 26 – Presa in carico comunicazione da LPS
	 */
	private void CalcoloNonConf26_PresaInCarico() {
		String varComp = "";
		// Data_trasm_lsp
		Date dataTramLsp = PraticaUtil.getDateWithoutTimeUsingFormat(praticaPp
				.getDataTrasmissDecrLPS());
		// Data_carico_domol
		Date dataCaricoOmol = PraticaUtil
				.getDateWithoutTimeUsingFormat(praticaEp.getDataPresaInCaricoDecrOmgLps());

		if (dataTramLsp == null || dataCaricoOmol == null

		) {
			CalcoloIndicatoriErrore errore = new CalcoloIndicatoriErrore();
			errore.setTipoRischio("CalcoloNonConf26_PresaInCarico");
			errore.setTipoErrore("NON CONF PresaInCarico");
			errore
					.setMessaggio("Data null tra dataTramLsp o dataCaricoOmol");
			listaErrori.add(errore);
		}

		if (dataTramLsp != null) {
			
			// Se Data_trasm_lsp <> Null
			if (dataTramLsp != null && dataCaricoOmol == null) {
				// Se Data_trasm_lsp <> Null e Data_carico_domol = Null allora
				// VC = “A610”
				varComp = PraticaUtil.A610;
			} else if (dataCaricoOmol != null && dataTramLsp != null
					&& PraticaUtil.daysBetween(dataTramLsp,dataCaricoOmol) > 3) {
				// Se (Data_carico_domol - Data_trasm_lsp) > 3 allora VC =
				// “A609”
				varComp = PraticaUtil.A609;
			}else if (dataCaricoOmol != null && dataTramLsp != null
					&& PraticaUtil.daysBetween(dataTramLsp,dataCaricoOmol) < 4) {
				// Se (Data_carico_domol - Data_trasm_lsp) < 4 allora VC =
				// “A608”
				varComp = PraticaUtil.A608;
			}
			
		}

		if (varComp.length() > 0) {
			varcompPresent.put(varComp, 1);
			insertVarComp(listaPraticheVarCompToInsert, idSPratica, varComp);
		}
		insertLogNonConf("varComp: " + varComp);
		insertLogNonConf("Fine");
	}

	/*
	 * Calcolare info Non conformità 27 – Registrazione in sisco dati
	 * liquidazione
	 */
	private void CalcoloNonConf27_RegistraInCaricoLiq() {
		String varComp = "";
		// Dat_liq_prlps
		Date dataLiqPrls = PraticaUtil.getDateWithoutTimeUsingFormat(praticaEp
				.getDataLiqPrestLps());
		// Data_reg_liq
		Date dataRegLiq = PraticaUtil.getDateWithoutTimeUsingFormat(praticaEp
				.getRecDatiLiq());

		if (dataLiqPrls == null || dataRegLiq == null) {
			CalcoloIndicatoriErrore errore = new CalcoloIndicatoriErrore();
			errore.setTipoRischio("CalcoloNonConf27_RegistraInCaricoLiq");
			errore.setTipoErrore("NON CONF RegistraInCaricoLiq");
			errore
					.setMessaggio("Data null tra dataLiqPrls o dataRegLiq");
			listaErrori.add(errore);
		}

		if (dataLiqPrls != null) {
			// Se Dat_liq_prlps <> Null
			if (dataRegLiq == null && dataLiqPrls != null) {
				// Se Data_reg_liq = Null e Dat_liq_prlps <> Null allora VC =
				// “A613”
				varComp = PraticaUtil.A613;
			} else if (dataRegLiq != null && dataLiqPrls != null
					&& PraticaUtil.daysBetween(dataRegLiq,dataLiqPrls) > 2) {
				// Se (Data_reg_liq - Dat_liq_prlps) > 2 allora VC = “A612”
				varComp = PraticaUtil.A612;
			}else if (dataRegLiq != null && dataLiqPrls != null
					&& PraticaUtil.daysBetween(dataRegLiq,dataLiqPrls) < 3) {
				// Se (Data_reg_liq - Dat_liq_prlps) < 3 allora VC = “A611”
				varComp = PraticaUtil.A611;
			} 

		}

		if (varComp.length() > 0) {
			varcompPresent.put(varComp, 1);
			insertVarComp(listaPraticheVarCompToInsert, idSPratica, varComp);
		}
		insertLogNonConf("varComp: " + varComp);
		insertLogNonConf("Fine");
	}

	/*
	 * Calcolare info Non conformità 28 – Non correttezza della dichiarazione di
	 * esenzione pagamento spese legali
	 */
	private void CalcoloNonConf28_NonCorrDichiEsenzPagamento() {
		String varComp = "";
		// Ver_cor_dich
		String verCorDich = PraticaUtil.checkStringAndTrimUpperCase(praticaGi
				.getVerificaDicEsPagSpese());
		// Altre_ecc
		// String altreEcc =
		// PraticaUtil.checkStringAndTrimUpperCase(praticaGi.getAltreEccProcessuali());

		if (verCorDich.equals("2")) {
			// Se Ver_cor_dich = 2 allora VC = “A317”
			varComp = PraticaUtil.A317;
		} else if (verCorDich.equals("3")) {
			// Se Ver_cor_dich = 3 allora VC = “A324”
			varComp = PraticaUtil.A324;
		}/*
		 * else if(altreEcc.equals("2")){ // Se Altre_ecc = 2 allora VC = “A310”
		 * varComp = "A310"; }else if(altreEcc.equals("3")){ // Se Altre_ecc = 3
		 * allora VC = “A327” varComp = "A327"; }
		 */

		if (varComp.length() > 0) {
			varcompPresent.put(varComp, 1);
			insertVarComp(listaPraticheVarCompToInsert, idSPratica, varComp);
		}
		insertLogNonConf("varComp: " + varComp);
		insertLogNonConf("Fine");
	}

	/*
	 * Calcolare info Non conformità 29 – Altre eccezioni processuali
	 */
	private void CalcoloNonConf29_AltreEccProff() {
		String varComp = "";
		// Altre_ecc
		String altreEcc = PraticaUtil.checkStringAndTrimUpperCase(praticaGi
				.getAltreEccProcessuali());

		if (altreEcc.equals("2")) {
			// Se Altre_ecc = 2 allora VC = “A310”
			varComp = PraticaUtil.A310;
		} else if (altreEcc.equals("3")) {
			// Se Altre_ecc = 3 allora VC = “A327”
			varComp = PraticaUtil.A327;
		}

		if (varComp.length() > 0) {
			varcompPresent.put(varComp, 1);
			insertVarComp(listaPraticheVarCompToInsert, idSPratica, varComp);
		}
	}

	/*
	 * Calcolare info Non conformità 30 – Indeterminatezza nell’oggetto della
	 * domanda
	 */
	private void CalcoloNonConf30_IndeterminatezzaDomanda() {
		String varComp = "";

		// Indet_ogg_dom
		String indetOggDom = PraticaUtil.checkStringAndTrimUpperCase(praticaGi
				.getIndeterminatezzaOggDom());

		if (indetOggDom.equals("2")) {
			// Se Indet_ogg_dom = 2 allora VC = “A314”
			varComp = PraticaUtil.A314;
		} else if (indetOggDom.equals("3")) {
			// Se Indet_ogg_dom = 3 allora VC = “A326”
			varComp = PraticaUtil.A326;
		}

		if (varComp.length() > 0) {
			varcompPresent.put(varComp, 1);
			insertVarComp(listaPraticheVarCompToInsert, idSPratica, varComp);
		}
		insertLogNonConf("varComp: " + varComp);
		insertLogNonConf("Fine");
	}

	/*
	 * Calcolare info Non conformità 31 Eccezioni rilevabili e non in
	 * presenza/assenza di memoria
	 */
	private void CalcoloNonConf31_EcceRilevabPreAssMemo() {
		String varComp = "";
		// Ecc_mem_rintr
		String eccMemRintr = PraticaUtil
				.checkStringAndTrimUpperCaseSiNo(praticaGi
						.getEccezioniNonRilevabili());

		if (eccMemRintr.equals(PraticaUtil.NO)) {
			// Se Ecc_mem_rintr = "si" allora VC = “A330”
			varComp = PraticaUtil.A330;
		} else if (eccMemRintr.equals(PraticaUtil.SI)) {
			// Se Ecc_mem_rintr = "no" allora VC = “A331”
			varComp = PraticaUtil.A331;
		}

		if (varComp.length() > 0) {
			varcompPresent.put(varComp, 1);
			insertVarComp(listaPraticheVarCompToInsert, idSPratica, varComp);
		}
		insertLogNonConf("varComp: " + varComp);
		insertLogNonConf("Fine");
	}

	/*
	 * Calcolare info Non conformità 32 – Carenza interesse ad agire
	 */
	private void CalcoloNonConf32_CarenzaInteresseAgire() {
		String varComp = "";
		// Car_int_agire
		String carIntAgire = PraticaUtil.checkStringAndTrimUpperCase(praticaGi
				.getCarenzaInteresseAdAgire());

		if (carIntAgire.equals("2")) {
			// Se Car_int_agire = 2 allora VC = “A311”
			varComp = PraticaUtil.A311;
		} else if (carIntAgire.equals("3")) {
			// Se Car_int_agire = 3 allora VC = “A325”
			varComp = PraticaUtil.A325;
		}

		if (varComp.length() > 0) {
			varcompPresent.put(varComp, 1);
			insertVarComp(listaPraticheVarCompToInsert, idSPratica, varComp);
		}
		insertLogNonConf("varComp: " + varComp);
		insertLogNonConf("Fine");
	}

	private void CalcoloNonConf33_VerificaCongruenzaPresMedicoCogisan() {
		String varComp = "";
		// Pres_ctp_oper
		String presCtpOper = PraticaUtil
				.checkStringAndTrimUpperCaseSiNo(praticaPe
						.getCtpINPSopPeritali());
		// Pres_ctu_oper
		String presCtuOper = PraticaUtil
				.checkStringAndTrimUpperCaseSiNo(praticaPe
						.getPresMedicoInpsDoc());

		if (PraticaUtil.SI.equals(presCtpOper) && PraticaUtil.SI.equals(presCtuOper)) {
			varComp = PraticaUtil.A701;
		} else if (PraticaUtil.SI.equals(presCtpOper) && PraticaUtil.NO.equals(presCtuOper)) {
			varComp = PraticaUtil.A702;
		} else if (PraticaUtil.NO.equals(presCtpOper) && PraticaUtil.SI.equals(presCtuOper)) {
			varComp = PraticaUtil.A703;
		} else if (PraticaUtil.NO.equals(presCtpOper) && PraticaUtil.NO.equals(presCtuOper)) {
			varComp = PraticaUtil.A704;
		}

		if (varComp.length() > 0) {
			varcompPresent.put(varComp, 1);
			insertVarComp(listaPraticheVarCompToInsert, idSPratica, varComp);
		}
		insertLogNonConf("varComp: " + varComp);
		insertLogNonConf("Fine");
	}
}
