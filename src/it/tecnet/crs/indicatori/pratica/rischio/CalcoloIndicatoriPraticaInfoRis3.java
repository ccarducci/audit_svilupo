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

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

public class CalcoloIndicatoriPraticaInfoRis3 extends
		CalcoloIndicatoriPraticaInfoRisBase {

	protected static Logger log = Logger
			.getLogger(CalcoloIndicatoriPraticaInfoRis3.class);

	private AtpoFaseEsecuzioneProvvedimenti praticaEp;
	private AtpoFasePostPeritale praticaPp;
	private AtpoFaseDati praticaFD;
	private AuCampagna campagna;

	private AuSPratica pratica;
	private List<AuMRischio> mRischio;
	private long idSSessione;
	private long idSPratica;
	private List<AuSPraticaRis> listaPraticheRisToInsert;
	private List<AuMRisepr> listAuMRisespr;

	private List<CalcoloIndicatoriErrore> listaErrori;

	public CalcoloIndicatoriPraticaInfoRis3(
			AtpoFaseEsecuzioneProvvedimenti praticaEp,
			AtpoFasePostPeritale praticaPp,
			List<CalcoloIndicatoriErrore> listaErrori, AuSPratica pratica,
			List<AuMRischio> rischio, long idSSessione, long idSPratica,
			List<AuSPraticaRis> listaPraticheRisToInsert,
			AtpoFaseDati praticaFD, AuCampagna campagna,
			List<AuMRisepr> listAuMRisespr) {
		super();
		this.praticaEp = praticaEp;
		this.praticaPp = praticaPp;
		this.listaErrori = listaErrori;
		this.pratica = pratica;
		this.mRischio = rischio;
		this.idSSessione = idSSessione;
		this.idSPratica = idSPratica;
		this.listaPraticheRisToInsert = listaPraticheRisToInsert;
		this.praticaFD = praticaFD;
		this.campagna = campagna;
		this.listAuMRisespr = listAuMRisespr;
	}

	/*
	 * Calcolare info Rischio n. 3 – Interessi legali
	 */
	public void calcoloInfoRischio3() {
		insertLogRischi("Rischio n. 3","INFO","Info determinazione Info Rischio 3 pratica: (idSPratica) "
				+ pratica.getIdSPratica());
		/*
		log.info("Inizio determinazione Info Rischio 3 pratica: (idSPratica) "
				+ pratica.getIdSPratica());
		*/
		String espressioneRischio = "";
		double importo1 = 0d;
		boolean isToInsert = false;

		// data_campagna
		Date dataCampagna = PraticaUtil.getDateWithoutTimeUsingFormat(campagna
				.getDataInizio());
		// data_notd_omol
		Date dataNotdOmol = PraticaUtil.getDateWithoutTimeUsingFormat(praticaPp
				.getDataNotificaDecOmologa());
		// Prest_ec
		String prestEc = PraticaUtil.checkStringAndTrimUpperCaseSiNo(praticaFD
				.getPrestazioneEconomica());
		// prest_er
		Double prestEr = PraticaUtil.checkValueImp(praticaFD
				.getImportoPrestazioneErogata());
		// Dat_liq_prlps
		Date dataLiqPrlps = PraticaUtil.getDateWithoutTimeUsingFormat(praticaEp
				.getDataLiqPrestLps());
		// Interessi_leg_dov
		Double interessiLegDov = PraticaUtil.checkValueImp(praticaEp
				.getInteressiLegaliDovuti());
		// Data_dercor_int
		Date dataDecorInt = PraticaUtil.getDateWithoutTimeUsingFormat(praticaEp
				.getDataDecCalcoloIntLegali());
		// interessi_leg_pag
		Double interessiLegPag = PraticaUtil.checkValueImp(praticaEp
				.getInteressiLegaliPagati());

		if (dataCampagna == null || dataNotdOmol == null
				|| dataLiqPrlps == null || dataDecorInt == null) {
			/*
			CalcoloIndicatoriErrore errore = new CalcoloIndicatoriErrore();
			errore.setTipoRischio("calcoloInfoRischio3");
			errore.setTipoErrore("CALCOLO RIS3");
			errore
					.setMessaggio("Calcolo rischio 3 data null tra dataCampagna, dataNotdOmol, dataLiqPrlps, dataDecorInt");
			listaErrori.add(errore);
			*/
			insertErrorRischi("Rischio n. 3","WARNING","data null tra dataCampagna, dataNotdOmol, dataLiqPrlps, dataDecorInt "
					+ pratica.getIdSPratica());
		}

		if (dataLiqPrlps != null && dataNotdOmol != null
				&& PraticaUtil.daysBetween(dataNotdOmol , dataLiqPrlps) < 121
				&& prestEr.doubleValue() > 0D 
				&& interessiLegPag.doubleValue() > 0D

		) {
			/* 	
			  	ESPRESSIONE AGGIUNTA - ( MESSA ALL'INIZIO PRIMA DELL'ESPRESSIONE E008 PERCHE' SE MESSA DOPO NON 
				SI VERIFICHEREBBE MAI DATO CHE L'IF E SIMILE)
			*/
			/*
			 * Data liquidazione prestazione da LPS <> null 
			 * e Data NOTIFICA Decreto di OMOLOGA <> null 
			 * e Data liquidazione prestazione da LPS - Data NOTIFICA Decreto di OMOLOGA < 121 
			 * e Importo prestazione erogata > 0 
			 * e Interessi legali pagati > 0.
			 */
			espressioneRischio = PraticaUtil.E042;
		}else if (dataLiqPrlps != null && dataNotdOmol != null
				&& PraticaUtil.daysBetween(dataNotdOmol , dataLiqPrlps) < 121
				&& prestEr.doubleValue() > 0D

		) {
			/*
			 * Se (Dat_liq_prlps - data_notd_omol < 121 e prest_er > 0) allora
			 * ER = “E008”
			 */
			espressioneRischio = PraticaUtil.E008;
		} else if ( (dataCampagna != null && dataNotdOmol != null
				&& PraticaUtil.daysBetween( dataNotdOmol , dataCampagna) < 121)
				|| PraticaUtil.NO.equals(prestEc)) {
			/*
			 * Se (data_campagna - data_notd_omol < 121 o Prest_ec = Falso)
			 * allora ER = “E007”
			 */
			espressioneRischio = PraticaUtil.E007;
		} else if ((dataLiqPrlps != null && dataNotdOmol != null && PraticaUtil
				.daysBetween(dataNotdOmol,dataLiqPrlps) < 121)
				|| PraticaUtil.NO.equals(prestEc)) {
			/*
			 * Se (Dat_liq_prlps - data_notd_omol < 121 o Prest_ec = Falso)
			 * allora ER = “E007”
			 */
			espressioneRischio = PraticaUtil.E007;
		} else if (prestEr != null && prestEr.doubleValue() == 0D) {
			/*
			 * Se prest_er = Null allora ER = “E007”
			 */
			espressioneRischio = PraticaUtil.E007;
		} else if ((dataLiqPrlps != null && dataNotdOmol != null &&
				dataDecorInt != null &&
				PraticaUtil
				.daysBetween(dataNotdOmol,dataLiqPrlps) > 120)
				&& interessiLegPag.doubleValue() > 0D // flagPignPrest != null
				&& dataDecorInt != (PraticaUtil.dateAdd(dataNotdOmol,
						Calendar.DAY_OF_MONTH, 120))) {
			/*
			 * Se Dat_liq_prlps - data_notd_omol > 120 e Flag_pign_prest <> Null
			 * e Data_dercor_int <> (data_notd_omol + 120) allora ER = “E041”
			 */
			espressioneRischio = PraticaUtil.E041;
		} else if (dataLiqPrlps != null && interessiLegPag.doubleValue() > 0D // flagPignPrest
				&& dataNotdOmol == null) {
			/*
			 * Se Dat_liq_prlps <> Null e Flag_pign_prest <> 0 e data_notd_omol
			 * = Null allora ER = “E042”
			 */
			espressioneRischio = PraticaUtil.E042;
		} else if (dataLiqPrlps != null && dataNotdOmol != null
				&& PraticaUtil.daysBetween( dataNotdOmol , dataLiqPrlps ) > 120
				// && flagPignPrest != null && flagPignPrest > 0
				&& interessiLegPag.doubleValue() > 0D) {
			/*
			 * Se Dat_liq_prlps - data_notd_omol >120 e Flag_pign_prest <> 0
			 * allora ER = “E006”
			 */
			espressioneRischio = PraticaUtil.E006;
		} else if (dataCampagna != null && dataNotdOmol != null
				&& PraticaUtil.daysBetween(dataNotdOmol,dataCampagna) > 120
				&& PraticaUtil.SI.equals(prestEc)) {
			/*
			 * Se data_campagna - data_notd_omol > 120 e Prest_ec = Vero allora
			 * ER = “E043”
			 */
			espressioneRischio = PraticaUtil.E043;
		}

		/*
		 * Se (ER = “E006” o ER = “E042”) allora Importo_1 = Flag_pign_prest
		 * Altrimenti Se ER = “E041” allora Importo_1 = (Flag_pign_prest -
		 * Interessi_leg_dov) Altrimenti Importo_1 = 0.
		 */
		if (espressioneRischio.length() > 0) {
			if (PraticaUtil.E006.equals(espressioneRischio)
					|| PraticaUtil.E042.equals(espressioneRischio)) {
				importo1 = interessiLegPag;
				isToInsert = true;
			} else if (PraticaUtil.E041.equals(espressioneRischio)) {
				// importo1 = interessiLegPag - interessiLegDov;
				importo1 = interessiLegPag;
				isToInsert = true;
			}else{
				isToInsert = true;
				importo1 = 0;
			}
		}

		if (isToInsert) {
			Long idMRischio = PraticaUtil.getIdMRischio(mRischio,
					PraticaUtil.R003);
			if (idMRischio != null) {
				insertRischio(idMRischio, new Date(), espressioneRischio,
						idSPratica, idSSessione, importo1,
						listaPraticheRisToInsert, listAuMRisespr);
			}
			insertLogRischi("Rischio n. 3","INFO",espressioneRischio
					+ pratica.getIdSPratica());
		}

		insertLogRischi("Rischio n. 3","INFO","Fine determinazione Info Rischio 3 pratica: (idSPratica) "
				+ pratica.getIdSPratica());
		
		
		/*
		log.info("Fine determinazione Info Rischio 3 pratica: (idSPratica) "
				+ pratica.getIdSPratica());
		*/
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
