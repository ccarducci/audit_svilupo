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

public class CalcoloIndicatoriPraticaInfoRis2 extends
		CalcoloIndicatoriPraticaInfoRisBase {

	protected static Logger log = Logger
			.getLogger(CalcoloIndicatoriPraticaInfoRis2.class);

	private AtpoFaseEsecuzioneProvvedimenti praticaEp;
	private AtpoFasePostPeritale praticaPp;
	private AtpoFaseDati praticaFd;
	private AuCampagna campagna;

	private AuSPratica pratica;
	private List<AuMRischio> mRischio;
	private long idSSessione;
	private long idSPratica;
	private List<AuSPraticaRis> listaPraticheRisToInsert;
	private List<AuMRisepr> listAuMRisespr;

	private List<CalcoloIndicatoriErrore> listaErrori;

	public CalcoloIndicatoriPraticaInfoRis2(
			AtpoFaseEsecuzioneProvvedimenti praticaEp,
			AtpoFasePostPeritale praticaPp,
			List<CalcoloIndicatoriErrore> listaErrori, AuSPratica pratica,
			List<AuMRischio> rischio, long idSSessione, long idSPratica,
			List<AuSPraticaRis> listaPraticheRisToInsert,
			AtpoFaseDati praticaFD, AuCampagna campagna,
			List<AuMRisepr> listAuMRisespr) {
		super();
		;
		this.praticaEp = praticaEp;
		this.praticaPp = praticaPp;
		this.listaErrori = listaErrori;
		this.pratica = pratica;
		this.mRischio = rischio;
		this.idSSessione = idSSessione;
		this.idSPratica = idSPratica;
		this.listaPraticheRisToInsert = listaPraticheRisToInsert;
		this.praticaFd = praticaFD;
		this.campagna = campagna;
		this.listAuMRisespr = listAuMRisespr;
	}

	/*
	 * Calcolare info Rischio n. 2 Pagamento spese per mancato o tardivo
	 * pagamento
	 */
	public void calcoloInfoRischio2() {
		insertLogRischi("Rischio n. 2","INFO","Inizio determinazione Info Rischio 2 pratica: (idSPratica) "
				+ pratica.getIdSPratica());
		/*
		log.info("Inizio determinazione Info Rischio 2 pratica: (idSPratica) "
				+ pratica.getIdSPratica());
		*/
		String espressioneRischio = "";
		double importo1 = 0d;
		boolean isToInsert = false;

		// cod_chiu_cor 
		String codChiuCorr = PraticaUtil.checkStringAndTrimUpperCaseSiNo(praticaPp.getCodChiusuraCorretto());
		
		// Dat_liq_prlps
		Date dataLiqPrlps = PraticaUtil.getDateWithoutTimeUsingFormat(praticaEp
				.getDataLiqPrestLps());
		// Data_depd_omol
		Date dataDepdOmol = PraticaUtil.getDateWithoutTimeUsingFormat(praticaPp
				.getDataDepositoDecOmologa());
		// data_notd_omol
		Date dataNotdOmol = PraticaUtil.getDateWithoutTimeUsingFormat(praticaPp
				.getDataNotificaDecOmologa());
		// Pres_sent_pagpr
		String presSentPagpr = PraticaUtil
				.checkStringAndTrimUpperCaseSiNo(praticaEp
						.getPresDecrSentMancPagPrest());
		// Costo_giud_mppr
		Double costoGiudMppr = PraticaUtil.checkValueImp(praticaEp
				.getCostoGiudizioMancPagPrest());
		// Costo_pign_prest
		Double costoPignPrest = PraticaUtil.checkValueImp(praticaEp
				.getCostoPignPrest());
		// Data_prestaz
		Date dataPrestaz = PraticaUtil.getDateWithoutTimeUsingFormat(praticaEp
				.getDataPrestazione());
		// Data_pign_prest
		Date dataPignPrest = PraticaUtil
				.getDateWithoutTimeUsingFormat(praticaEp
						.getDataPignoramentoPres());
		// Costo_prec_prest
		Double costoPrecPrest = PraticaUtil.checkValueImp(praticaEp
				.getCostoPrePrest());
		// prest_er
		Double prestEr = PraticaUtil.checkValueImp(praticaFd
				.getImportoPrestazioneErogata());
		// Prest_ec
		String prestEc = PraticaUtil.checkStringAndTrimUpperCaseSiNo(praticaFd
				.getPrestazioneEconomica());
		// Flag_no_prec
		String flagNoPrec = PraticaUtil.checkStringAndTrimUpperCase(praticaEp
				.getNoPrecetto());
		// data_campagna
		Date dataCampagna = PraticaUtil.getDateWithoutTimeUsingFormat(campagna
				.getDataInizio());

		if (dataLiqPrlps == null || dataDepdOmol == null
				|| dataNotdOmol == null || dataPrestaz == null
				|| dataPignPrest == null || dataCampagna == null) {
			/*
			CalcoloIndicatoriErrore errore = new CalcoloIndicatoriErrore();
			errore.setTipoRischio("calcoloInfoRischio2");
			errore.setTipoErrore("CALCOLO RIS2");
			errore
					.setMessaggio("Calcolo rischio 2 data null tra dataLiqPrlps, dataDepdOmol, dataNotdOmol, dataPrestaz, dataPignPrest, dataCampagna");
			listaErrori.add(errore);
			*/
			insertErrorRischi("Rischio n. 2","WARNING","data null tra dataLiqPrlps, dataDepdOmol, dataNotdOmol, dataPrestaz, dataPignPrest, dataCampagna "
					+ pratica.getIdSPratica());
		}

		if (codChiuCorr.equals("2")  || codChiuCorr.equals("3") ){
		
			if (((dataLiqPrlps != null && dataDepdOmol != null && PraticaUtil
					.daysBetween( dataDepdOmol , dataLiqPrlps) > 180  ) || (dataLiqPrlps != null
					&& dataNotdOmol != null && PraticaUtil.daysBetween(
							dataNotdOmol , dataLiqPrlps ) > 120)
				 )
					&& ((PraticaUtil.SI.equals(presSentPagpr) && costoGiudMppr.doubleValue() > 0D)
							|| dataPrestaz != null || dataPignPrest != null)
				) {
				/*
				 * Se ( (Dat_liq_prlps - Data_depd_omol) > 180 o (Dat_liq_prlps -
				 * data_notd_omol) > 120) e ( (Pres_sent_pagpr = "si" e
				 * Costo_giud_mppr <> Null) o Data_prestaz <> Null o Data_pign_prest
				 * <> Null ) allora ER = “E002”
				 */
				espressioneRischio = PraticaUtil.E002;
			} else if (((dataLiqPrlps != null && dataDepdOmol != null && PraticaUtil
					.daysBetween( dataDepdOmol, dataLiqPrlps) < 180) || (dataLiqPrlps != null
					&& dataNotdOmol != null && PraticaUtil.daysBetween(
							dataNotdOmol , dataLiqPrlps) < 120))
					&& prestEr.doubleValue() > 0D) {
				/*
				 * Se ((Dat_liq_prlps - Data_depd_omol) < 180 o (Dat_liq_prlps -
				 * data_notd_omol) < 120) e prest_er > 0 allora ER = “E030”
				 */
				espressioneRischio = PraticaUtil.E030;
			} else if ((dataCampagna != null && dataDepdOmol != null && PraticaUtil
					.daysBetween( dataDepdOmol , dataCampagna ) < 180)
					|| (dataCampagna != null && dataNotdOmol != null && PraticaUtil
							.daysBetween(dataNotdOmol,dataCampagna) < 120)) {
				/*
				 * Se ((data_campagna - Data_depd_omol) < 180 o (data_campagna -
				 * data_notd_omol) < 120) allora ER = “E005”
				 */
				espressioneRischio = PraticaUtil.E005;
			} else if (PraticaUtil.NO.equals(prestEc)) {
				/*
				 * Se Prest_ec = Falso allora ER = “E005”
				 */
				espressioneRischio = PraticaUtil.E005;
			} else if ((dataCampagna != null && dataNotdOmol != null && PraticaUtil
					.daysBetween( dataNotdOmol , dataCampagna) > 120)
					&& /* "V" */PraticaUtil.VERO.equals(flagNoPrec)
					&& PraticaUtil.SI.equals(prestEc)) {
				/*
				 * Se (data_campagna - data_notd_omol) > 120 e Flag_no_prec = Vero e
				 * Prest_ec = Vero allora ER = “E001”
				 */
				espressioneRischio = PraticaUtil.E001;
			} else if (((dataLiqPrlps != null && dataDepdOmol != null && PraticaUtil
					.daysBetween( dataDepdOmol , dataLiqPrlps ) > 180) && /* "F" */PraticaUtil.FALSO
					.equals(flagNoPrec))
					|| ((dataLiqPrlps != null && dataNotdOmol != null && PraticaUtil
							.daysBetween( dataNotdOmol , dataLiqPrlps) > 120) && /* "F" */PraticaUtil.FALSO
							.equals(flagNoPrec))) {
				/*
				 * Se (Dat_liq_prlps - Data_depd_omol) > 180 e Flag_no_prec = “NO” o
				 * (Dat_liq_prlps - data_notd_omol) > 120 e Flag_no_prec = “NO”
				 * allora ER = “E001”
				 */
				espressioneRischio = PraticaUtil.E001;
			}
		}
		
		/*
		 * Determina Importo_1 Se (ER = “E003” o ER = “E004) allora Importo_1 =
		 * (Costo_prec_prest + Costo_pign_prest) Altrimenti Se ER = “E002”
		 * allora Importo_1 = Costo_prec_prest Altrimenti Importo_1 = Null.
		 */
		if (espressioneRischio.length() > 0) {
			/*
			if (PraticaUtil.E003.equals(espressioneRischio)
					|| PraticaUtil.E004.equals(espressioneRischio)) {
				importo1 = costoPrecPrest + costoPignPrest;
				isToInsert = true;
			} else if (PraticaUtil.E002.equals(espressioneRischio)) {
				importo1 = costoPignPrest;
				isToInsert = true;
			}
			*/
			if (PraticaUtil.E001.equals(espressioneRischio)){
				isToInsert = true;
				importo1 = 0;
			}else{
				isToInsert = true;
				importo1 = costoPrecPrest + costoPignPrest;
			}
		}

		if (isToInsert) {
			Long idMRischio = PraticaUtil.getIdMRischio(mRischio,
					PraticaUtil.R002);
			if (idMRischio != null) {
				insertRischio(idMRischio, new Date(), espressioneRischio,
						idSPratica, idSSessione, importo1,
						listaPraticheRisToInsert, listAuMRisespr);
			}
			insertLogRischi("Rischio n. 2","INFO",espressioneRischio
					+ pratica.getIdSPratica());
		}

		/*
		log.info("Fine determinazione Info Rischio 2 pratica: (idSPratica) "
				+ pratica.getIdSPratica());
		*/
		insertLogRischi("Rischio n. 2","INFO","Fine determinazione Info Rischio 2 pratica: (idSPratica) "
				+ pratica.getIdSPratica());
	}

	private void insertErrorRischi(String rischio , String TipoErrore, String stackTrace) {
		try{
			log.info(TipoErrore + " - " + stackTrace);
			CalcoloIndicatoriErrore errore = new CalcoloIndicatoriErrore();
			if (rischio !=null)errore.setTipoRischio(rischio);
			if (TipoErrore !=null)errore.setTipoErrore(TipoErrore);
			if (stackTrace !=null)errore.setMessaggio(stackTrace);
			listaErrori.add(errore);
		}catch(Exception e){
			log.error("errore nel logging applicatiovo");
		}
	}
	
	private void insertLogRischi( String rischio , String TipoErrore, String stackTrace) {
		try{
			log.info(TipoErrore + " - " + stackTrace);
			CalcoloIndicatoriErrore errore = new CalcoloIndicatoriErrore();
			if (rischio !=null)errore.setTipoRischio(rischio);
			if (TipoErrore !=null)errore.setTipoErrore(TipoErrore);
			if (stackTrace !=null)errore.setMessaggio(stackTrace);
			listaErrori.add(errore);
		}catch(Exception e){
			log.error("errore nel logging applicatiovo");
		}
	}
	
}
