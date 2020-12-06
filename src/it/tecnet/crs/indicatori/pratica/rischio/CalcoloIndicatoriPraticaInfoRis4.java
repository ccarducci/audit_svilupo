package it.tecnet.crs.indicatori.pratica.rischio;

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

public class CalcoloIndicatoriPraticaInfoRis4 extends
		CalcoloIndicatoriPraticaInfoRisBase {

	protected static Logger log = Logger
			.getLogger(CalcoloIndicatoriPraticaInfoRis4.class);

	private AtpoFaseEsecuzioneProvvedimenti praticaEp;
	private AtpoFasePostPeritale praticaPp;
	private AuCampagna campagna;
	
	private AuSPratica pratica;
	private List<AuMRischio> mRischio;
	private long idSSessione;
	private long idSPratica;
	private List<AuSPraticaRis> listaPraticheRisToInsert;
	private List<AuMRisepr> listAuMRisespr;
	
	private List<CalcoloIndicatoriErrore> listaErrori;
	
	public CalcoloIndicatoriPraticaInfoRis4(
			AtpoFaseEsecuzioneProvvedimenti praticaEp,
			AtpoFasePostPeritale praticaPp,
			List<CalcoloIndicatoriErrore> listaErrori, AuSPratica pratica,
			List<AuMRischio> rischio, long idSSessione, long idSPratica,
			List<AuSPraticaRis> listaPraticheRisToInsert, AuCampagna campagna,
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
		this.campagna = campagna;
		this.listAuMRisespr = listAuMRisespr;
	}

	/*
	 * Calcolare info Rischio n. 4 – Pagamento spese azione esecutiva per
	 * mancato/tardivo pagamento spese legali
	 */
	public void calcoloInfoRischio4() {
		
		insertLogRischi("Rischio n. 4","INFO","Inizio determinazione Info Rischio 4 pratica: (idSPratica) "
				+ pratica.getIdSPratica());
		
		/*
		log.info("Inizio determinazione Info Rischio 4 pratica: (idSPratica) "
				+ pratica.getIdSPratica());
		*/
		String espressioneRischio = "";
		double importo = 0d;
		boolean isToInsert = false;

		// Costo_sl_prec
		Double costoSlPrec = PraticaUtil.checkValueImp(praticaEp
				.getCostoPreSl());
		// Costo_pign_sl
		Double costoPignSl = PraticaUtil.checkValueImp(praticaEp
				.getCostoPignSl());
		// Data_pagsl_avv
		Date dataPagslAvv = PraticaUtil.getDateWithoutTimeUsingFormat(praticaEp
				.getDataPagSpseLegaliAvvCparte());
		// Data_arr_notula
		Date dataArrNotula = PraticaUtil
				.getDateWithoutTimeUsingFormat(praticaEp.getDataArriviNotula());
		// Data_depd_omol
		Date dataDepdOmol = PraticaUtil.getDateWithoutTimeUsingFormat(praticaPp
				.getDataDepositoDecOmologa());
		// Data_comu_slpr
		Date dataComuSlpr = PraticaUtil.getDateWithoutTimeUsingFormat(praticaEp
				.getDataComPreSl());
		// Data_campagna
		Date dataCampagna = PraticaUtil.getDateWithoutTimeUsingFormat(campagna
				.getDataInizio());
		// Data_sl_prec
		Date dataSlPrec = PraticaUtil.getDateWithoutTimeUsingFormat(praticaEp
				.getDataSpeseLegaliPrec());
		// Data_pign_sl
		Date dataPignSl = PraticaUtil.getDateWithoutTimeUsingFormat(praticaEp
				.getDataSpeseLegaliPign());
		
		if (	dataPagslAvv == null
				|| dataArrNotula == null
				|| dataDepdOmol  == null
				|| dataComuSlpr == null
				|| dataCampagna == null
				|| dataSlPrec == null 
				|| dataPignSl == null ){
			/*
			CalcoloIndicatoriErrore errore = new CalcoloIndicatoriErrore();
			errore.setTipoRischio("calcoloInfoRischio4");
			errore.setTipoErrore("CALCOLO RIS4");
			errore.setMessaggio("Calcolo rischio 4 data null tra dataPagslAvv, dataArrNotula, dataDepdOmol, dataComuSlpr, dataCampagna, dataSlPrec, dataPignSl");
			listaErrori.add(errore);
			*/
			insertErrorRischi("Rischio n. 4","WARNING","data null tra dataPagslAvv, dataArrNotula, dataDepdOmol, dataComuSlpr, dataCampagna, dataSlPrec, dataPignSl "
					+ pratica.getIdSPratica());
		}
		
		if ((  (dataPagslAvv!= null &&  dataArrNotula != null 
				&& PraticaUtil.daysBetween(dataArrNotula, dataPagslAvv) > 120 ) 
				|| ( dataPagslAvv!= null &&  dataDepdOmol != null &&  PraticaUtil
				.daysBetween(dataDepdOmol,dataPagslAvv) > 180))
				&& dataSlPrec == null) {
			/*
			 * Se ((Data_pagsl_avv - Data_arr_notula) > 120 o (Data_pagsl_avv -
			 * Data_depd_omol) > 180) e Data_sl_prec = Null allora ER = “E011”
			 */
			espressioneRischio = PraticaUtil.E011;
		} else if ((  (dataPagslAvv!= null &&  dataArrNotula != null 
				&&  PraticaUtil.daysBetween(dataArrNotula,dataPagslAvv) > 120 )
				|| ( dataPagslAvv!= null &&  dataDepdOmol != null &&  PraticaUtil
				.daysBetween(dataDepdOmol,dataPagslAvv) > 180))
				&& dataSlPrec != null
				&& dataPignSl != null
				&& dataComuSlpr != null
				&& 
				// PraticaUtil.daysBetween(dataComuSlpr, dataSlPrec) < 11
				PraticaUtil.daysBetween(dataSlPrec,dataComuSlpr) < 11
				) {
			/*
			 * Se ((Data_pagsl_avv - Data_arr_notula) > 120 o (Data_pagsl_avv -
			 * Data_depd_omol) > 180) e Data_sl_prec <> Null e Data_pign_sl <>
			 * Null e (Data_comu_slpr - Data_sl_prec) < 11 allora ER = “E013”
			 */
			espressioneRischio = PraticaUtil.E013;
		} else if (( (dataPagslAvv!= null &&  dataArrNotula != null 
				&&  PraticaUtil.daysBetween( dataArrNotula , dataPagslAvv) > 120 )
				||(dataPagslAvv!= null &&  dataDepdOmol != null &&  PraticaUtil
				.daysBetween(dataDepdOmol,dataPagslAvv) > 180) )
				&& dataSlPrec != null
				&& dataPignSl != null
				&& dataComuSlpr != null
				&& 
				// PraticaUtil.daysBetween(dataComuSlpr, dataSlPrec) > 10
				PraticaUtil.daysBetween(dataSlPrec,dataComuSlpr)  > 10
				) {
			/*
			 * Se ((Data_pagsl_avv - Data_arr_notula) > 120 o (Data_pagsl_avv -
			 * Data_depd_omol) > 180) e Data_sl_prec <> Null e Data_pign_sl <>
			 * Null e (Data_comu_slpr - Data_sl_prec) > 10 allora ER = “E017”
			 */
			espressioneRischio = PraticaUtil.E017;
		} else if (( ( dataPagslAvv!= null &&  dataArrNotula != null 
				&& PraticaUtil.daysBetween(dataArrNotula,dataPagslAvv) > 120 )
				|| ( dataPagslAvv!= null &&  dataDepdOmol != null &&  PraticaUtil
				.daysBetween(dataDepdOmol,dataPagslAvv) > 180 ))
				&& dataSlPrec != null) {
			/*
			 * Se ((Data_pagsl_avv - Data_arr_notula) > 120 o (Data_pagsl_avv -
			 * Data_depd_omol) > 180) e Data_sl_prec <> Null allora ER = “E012”
			 */
			espressioneRischio = PraticaUtil.E012;
		} else if ( ( dataPagslAvv!= null &&  dataArrNotula != null &&  PraticaUtil.daysBetween(dataArrNotula,dataPagslAvv) < 121 )
				|| ( dataPagslAvv!= null &&  dataDepdOmol != null && PraticaUtil.daysBetween(dataDepdOmol,dataPagslAvv) < 181)) {
			// Se ((Data_pagsl_avv - Data_arr_notula) < 121 o (Data_pagsl_avv -
			// Data_depd_omol) < 181)
			// allora ER = “E032”
			espressioneRischio = PraticaUtil.E032;
		} else if (dataPagslAvv == null && dataSlPrec != null 
				&& dataArrNotula != null ){
			espressioneRischio = PraticaUtil.E012;
		} else if ( ( dataCampagna!= null &&  dataDepdOmol != null &&   PraticaUtil.daysBetween( dataDepdOmol , dataCampagna) < 181 )
				|| (dataCampagna!= null &&  dataArrNotula != null &&  PraticaUtil.daysBetween(dataArrNotula, dataCampagna) < 121)) {
			/*
			 * Se ((data_campagna - Data_depd_omol) < 181 o (data_campagna -
			 * Data_arr_notula) < 121) allora ER = “E031”
			 */
			espressioneRischio = PraticaUtil.E031;
		}

		/*
		 * Se ER = “E012” allora Importo_1 = Costo_sl_prec Altrimenti Se (ER =
		 * “E013” o ER = “E017”) allora Importo_1 = Costo_sl_prec +
		 * Costo_pign_sl Altrimenti Importo_1 = Null.
		 */
		if (espressioneRischio.length() > 0) {
			if (PraticaUtil.E012.equals(espressioneRischio)) {
				importo = costoSlPrec;
				isToInsert = true;
			} else if (PraticaUtil.E013.equals(espressioneRischio)
					|| PraticaUtil.E017.equals(espressioneRischio)) {
				importo = costoSlPrec + costoPignSl;
				isToInsert = true;
			}else{
				isToInsert = true;
				importo = 0;
			}
		}

		if (isToInsert) {
			Long idMRischio = PraticaUtil.getIdMRischio(mRischio,
					PraticaUtil.R004);
			if (idMRischio != null) {
				insertRischio(idMRischio, new Date(), espressioneRischio,
						idSPratica, idSSessione, importo,
						listaPraticheRisToInsert, listAuMRisespr);
			}
			insertLogRischi("Rischio n. 4","INFO",espressioneRischio
					+ pratica.getIdSPratica());
		}

		/*
		log.info("Fine determinazione Info Rischio 4 pratica: (idSPratica) "
				+ pratica.getIdSPratica());
				*/
		insertLogRischi("Rischio n. 4","INFO","Fine determinazione Info Rischio 4 pratica: (idSPratica) "
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
