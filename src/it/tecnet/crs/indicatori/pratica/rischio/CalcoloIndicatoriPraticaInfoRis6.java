package it.tecnet.crs.indicatori.pratica.rischio;

import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoFaseDati;
import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoFaseEsecuzioneProvvedimenti;
import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoFasePostPeritale;
import it.tecnet.crs.indicatori.pratica.CalcoloIndicatoriErrore;
import it.tecnet.crs.jpa.model.AuCampagna;
import it.tecnet.crs.jpa.model.AuMRischio;
import it.tecnet.crs.jpa.model.AuMRisepr;
import it.tecnet.crs.jpa.model.AuSPratica;
import it.tecnet.crs.jpa.model.AuSPraticaRis;
import it.tecnet.crs.util.PraticaUtil;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

public class CalcoloIndicatoriPraticaInfoRis6 extends
		CalcoloIndicatoriPraticaInfoRisBase {
	protected static Logger log = Logger
			.getLogger(CalcoloIndicatoriPraticaInfoRis6.class);

	private AuSPratica pratica;

	private AtpoFaseEsecuzioneProvvedimenti praticaEp;
	private AtpoFasePostPeritale praticaPp;
	private List<AuMRischio> mRischio;
	private long idSSessione;
	private long idSPratica;
	private List<AuSPraticaRis> listaPraticheRisToInsert;
	private List<AuMRisepr> listAuMRisespr;
	private AtpoFaseDati praticaFd;
	
	private List<CalcoloIndicatoriErrore> listaErrori;
	@SuppressWarnings("unused")
	private AuCampagna campagna;

	public CalcoloIndicatoriPraticaInfoRis6(AuSPratica pratica,
			AuCampagna campagna, AtpoFaseEsecuzioneProvvedimenti praticaEp,
			AtpoFasePostPeritale praticaPp, List<AuMRischio> rischio,
			long idSSessione, long idSPratica,
			List<AuSPraticaRis> listaPraticheRisToInsert,
			List<CalcoloIndicatoriErrore> listaErrori,
			List<AuMRisepr> listAuMRisespr, AtpoFaseDati praticaFd) {
		super();
		this.pratica = pratica;
		this.campagna = campagna;
		this.praticaEp = praticaEp;
		this.praticaPp = praticaPp;
		this.mRischio = rischio;
		this.idSSessione = idSSessione;
		this.idSPratica = idSPratica;
		this.listaPraticheRisToInsert = listaPraticheRisToInsert;
		this.listaErrori = listaErrori;
		this.listAuMRisespr = listAuMRisespr;
		this.praticaFd = praticaFd;
	}

	public void calcoloInfoRischio6() {
		/*
		 * log.info("Inizio determinazione Info Rischio 6 pratica: (idSPratica) "
		 * + pratica.getIdSPratica());
		 */
		insertLogRischi("Rischio n. 6", "INFO",
				"Inizio determinazione Info Rischio 6 pratica: (idSPratica) "
						+ pratica.getIdSPratica());

		String espressioneRischio = "";
		double importo = 0d;
		boolean isToInsert = false;

		// Flag_decr_omol
		String flagDescrOmol = PraticaUtil
				.checkStringAndTrimUpperCaseSiNo(praticaEp.getPresDecrOmgFasc());
		// Data_pagsl_avv
		Date dataPagslAvv = PraticaUtil.getDateWithoutTimeUsingFormat(praticaEp
				.getDataPagSpseLegaliAvvCparte());
		// Sogg_rich_pag
		String soggRichPag = PraticaUtil.checkStringAndTrimUpperCase(praticaEp
				.getSoggRichPagamento());
		// Spese_pagate
		Double spesePagate = PraticaUtil.checkValueImp(praticaPp
				.getSpesePagate());
		// Ver_domol_ctud_2
		Double verDomolCtud_2 = PraticaUtil.checkValueImp(praticaPp
				.getSpeseDecrOmologa());
		// Cod_pagam_slins
		String codPagamSlins = PraticaUtil
				.checkStringAndTrimUpperCase(praticaPp
						.getCodPagamentoSpeseLegali());
		// Cod_pagam_slcor
		String codPagamSlcor = PraticaUtil
				.checkStringAndTrimUpperCase(praticaPp
						.getCodPagamentoSpeseLegaliCorretto());
		
		// Giudizio
		String giudizio = PraticaUtil
						.checkStringAndTrimUpperCase(praticaFd.getGiudizio());
		
		
		Integer codPagamSlcorInt = 0;
		try {
			codPagamSlcorInt = Integer.parseInt(codPagamSlcor); // VERIFICARE
		} catch (Exception e) {
			insertErrorRischi("Rischio n. 6", "ERROR", e.getMessage());
			// log.error(e);
		}

		if (dataPagslAvv == null) {
			/*
			 * CalcoloIndicatoriErrore errore = new CalcoloIndicatoriErrore();
			 * errore.setTipoRischio("calcoloInfoRischio6");
			 * errore.setTipoErrore("CALCOLO RIS6");
			 * errore.setMessaggio("Calcolo rischio 6 data dataPagslAvv a null"
			 * ); listaErrori.add(errore);
			 */
			insertErrorRischi("Rischio n. 6", "WARNING",
					" data dataPagslAvv a null " + pratica.getIdSPratica());
			// return; // VERIFICARE
		}

		if (PraticaUtil.NO.equals(flagDescrOmol) && dataPagslAvv != null) {
			// Se Flag_decr_omol = “no” e Data_pagsl_avv <> Null allora ER =
			// “E021”
			espressioneRischio = PraticaUtil.E021;
		} else if (PraticaUtil.SI.equals(flagDescrOmol) && dataPagslAvv != null
				&& "K2".equals(soggRichPag)) {
			// Se Flag_decr_omol = “si” e Data_pagsl_avv <> Null
			// e Sogg_rich_pag = “K2” allora ER = “E022”
			espressioneRischio = PraticaUtil.E022;
		/*} else if (PraticaUtil.SI.equals(flagDescrOmol)
				&& spesePagate.doubleValue() > verDomolCtud_2.doubleValue()
				&& soggRichPag != null && soggRichPag.length() > 0 && !"K2".equals(soggRichPag)
				&& soggRichPag.length() > 0 ) {
			// Se Flag_decr_omol = “si” e Spese_pagate > Ver_domol_ctud_2
			// e Sogg_rich_pag <> “K2” e Sogg_rich_pag <> Null allora ER =
			// “E023”
			espressioneRischio = PraticaUtil.E023;
		} else if (!codPagamSlins.equals(codPagamSlcor) && soggRichPag != null && soggRichPag.length() > 0
				&& !"K2".equals(soggRichPag)
				&& PraticaUtil.SI.equals(flagDescrOmol)) {
			// Se Cod_pagam_slins <> Cod_pagam_slcor e Sogg_rich_pag <> “K2”
			// e Sogg_rich_pag <> Null e Flag_decr_omol = “si” allora ER =
			// “E023”
			espressioneRischio = PraticaUtil.E023;
		*/
		//} else if (verDomolCtud_2.doubleValue() < spesePagate.doubleValue()) { 
		// Rischio 6 – espressione di rischio E024: il dovuto è maggiore di pagato e non viceversa.
		} else if ( !"4".equals(giudizio) &&
					verDomolCtud_2.doubleValue() > spesePagate.doubleValue()
				   ) {
			// Se Ver_domol_ctud_2 < Spese_pagate allora ER = “E024”
			espressioneRischio = PraticaUtil.E024;
		} else if (PraticaUtil.SI.equals(flagDescrOmol)
				&& spesePagate.doubleValue() == verDomolCtud_2.doubleValue()
				&& spesePagate.doubleValue() != 0) {
			// Se Flag_decr_omol = “si” e Spese_pagate = Ver_domol_ctud_2 allora
			// ER = “E033”
			espressioneRischio = PraticaUtil.E033;
		}

		/*
		 * Se (ER = “E021” o ER = “E022”) allora Importo_1 = Spese_pagate
		 * Altrimenti Se ER = “E023” allora Importo_1 = (Spese_pagate -
		 * Ver_domol_ctud_2) altrimenti Se ER = “E024” allora Importo_1 =
		 * (Ver_domol_ctud_2 - Spese_pagate) Altrimenti Importo_1 = Null.
		 */
		if (espressioneRischio.length() > 0) {
			if (PraticaUtil.E021.equals(espressioneRischio)
					|| (PraticaUtil.E022).equals(espressioneRischio)) {
				importo = spesePagate;
				isToInsert = true;
			} else if (PraticaUtil.E023.equals(espressioneRischio)) {
				importo = spesePagate - verDomolCtud_2;
				isToInsert = true;
			} else if (PraticaUtil.E024.equals(espressioneRischio)) {
				importo = verDomolCtud_2 - spesePagate;
				isToInsert = true;
			} else {
				isToInsert = true;
				importo = 0;
			}
		}

		if (isToInsert) {
			Long idMRischio = PraticaUtil.getIdMRischio(mRischio,
					PraticaUtil.R006);
			if (idMRischio != null) {
				insertRischio(idMRischio, new Date(), espressioneRischio,
						idSPratica, idSSessione, importo,
						listaPraticheRisToInsert, listAuMRisespr);
			}
			insertLogRischi("Rischio n. 6", "INFO", espressioneRischio
					+ pratica.getIdSPratica());
		}

		insertLogRischi("Rischio n. 6", "INFO",
				"Fine determinazione Info Rischio 6 pratica: (idSPratica) "
						+ pratica.getIdSPratica());

		/*
		 * log.info("Fine determinazione Info Rischio 6 pratica: (idSPratica) "
		 * + pratica.getIdSPratica());
		 */
	}

	private void insertErrorRischi(String rischio, String TipoErrore,
			String stackTrace) {
		try {
			log.info(TipoErrore + " - " + stackTrace);
			CalcoloIndicatoriErrore errore = new CalcoloIndicatoriErrore();
			if (rischio != null)
				errore.setTipoRischio(rischio);
			if (TipoErrore != null)
				errore.setTipoErrore(TipoErrore);
			if (stackTrace != null)
				errore.setMessaggio(stackTrace);
			listaErrori.add(errore);
		} catch (Exception e) {
			log.error("errore nel logging applicatiovo");
		}
	}

	private void insertLogRischi(String rischio, String TipoErrore,
			String stackTrace) {
		try {
			log.info(TipoErrore + " - " + stackTrace);
			CalcoloIndicatoriErrore errore = new CalcoloIndicatoriErrore();
			if (rischio != null)
				errore.setTipoRischio(rischio);
			if (TipoErrore != null)
				errore.setTipoErrore(TipoErrore);
			if (stackTrace != null)
				errore.setMessaggio(stackTrace);
			listaErrori.add(errore);
		} catch (Exception e) {
			log.error("errore nel logging applicatiovo");
		}
	}

}
