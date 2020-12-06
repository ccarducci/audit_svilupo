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

public class CalcoloIndicatoriPraticaInfoRis1 extends
		CalcoloIndicatoriPraticaInfoRisBase {

	protected static Logger log = Logger
			.getLogger(CalcoloIndicatoriPraticaInfoRis1.class);


	private AtpoFaseEsecuzioneProvvedimenti praticaEp;
	
	private AuSPratica pratica;
	private List<AuMRischio> mRischio;
	private long idSSessione;
	private long idSPratica;
	private List<AuSPraticaRis> listaPraticheRisToInsert;
	private List<AuMRisepr> listAuMRisespr;
	private AuCampagna campagna;
	private AtpoFasePostPeritale praticaPp;
	
	private List<CalcoloIndicatoriErrore> listaErrori;
	
	public CalcoloIndicatoriPraticaInfoRis1(
											AtpoFaseEsecuzioneProvvedimenti praticaEp,
											List<CalcoloIndicatoriErrore> listaErrori, 
											AuSPratica pratica,
											List<AuMRischio> rischio, 
											long idSSessione, 
											long idSPratica,
											List<AuSPraticaRis> listaPraticheRisToInsert,
											List<AuMRisepr> listAuMRisespr,
											AuCampagna campagna, AtpoFasePostPeritale praticaPp) {
		super();
		this.praticaEp = praticaEp;
		this.listaErrori = listaErrori;
		this.pratica = pratica;
		this.mRischio = rischio;
		this.idSSessione = idSSessione;
		this.idSPratica = idSPratica;
		this.listaPraticheRisToInsert = listaPraticheRisToInsert;
		this.listAuMRisespr = listAuMRisespr;
		this.campagna = campagna;
		this.praticaPp = praticaPp;
	}
	
	/*
	 * Calcolare info Rischio n. 1 Prestazione o somma indebita (di invalidità
	 * civile)
	 */
	public void calcoloInfoRischio1(){
		insertLogRischi("Rischio n. 1","INFO","Inizio determinazione Info Rischio 1 pratica: (idSPratica) "
				+ pratica.getIdSPratica());
		/*
		log.info("Inizio determinazione Info Rischio 1 pratica: (idSPratica) "
				+ pratica.getIdSPratica());
		*/
		String espressioneRischio = "";
		double importo1 = 0d;
		double importo2 = 0d;
		double importo_parziale = 0d;
		boolean isToInsert = false;
		
		// Flag_decr_omol
		String flagDecrOmol = PraticaUtil.checkStringAndTrimUpperCaseSiNo(praticaEp
				.getPresDecrOmgFasc());
		// Dat_liq_prlps
		Date datLiqPrlps = PraticaUtil.getDateWithoutTimeUsingFormat(praticaEp
				.getDataLiqPrestLps());
		// Dat_corr_prins
		Date datCorrPrins = PraticaUtil.getDateWithoutTimeUsingFormat(praticaEp
				.getDataDecPrestInserita());
		// Dat_corr_prest
		Date datCorrPrest = PraticaUtil.getDateWithoutTimeUsingFormat(praticaEp
				.getDataCorrDecPrestazione());
		// Imp_mens_rata
		Double impMensRata = PraticaUtil.checkValueImp(praticaEp
				.getImportoRataMensile());
		// Imp_rata_dov
		Double impRataDovuto = PraticaUtil.checkValueImp(praticaEp
				.getImportoRataDovuta());
		// Impatto
		//Double impatto = PraticaUtil.checkValueImp(praticaEp.getImpatto());

		// Data_trasm_lsp
		Date dataTrasmLsp = PraticaUtil.getDateWithoutTimeUsingFormat(praticaPp.getDataTrasmissDecrLPS());
		
		// data_campagna
		Date dataCampagna = PraticaUtil.getDateWithoutTimeUsingFormat(campagna
				.getDataInizio());
		
		if (datCorrPrest == null){
			insertErrorRischi("Rischio n. 1","WARNING","data null tra datLiqPrlps o datCorrPrins o datCorrPrest "
					+ pratica.getIdSPratica());
			/*
			CalcoloIndicatoriErrore errore = new CalcoloIndicatoriErrore();
			errore.setTipoRischio("calcoloInfoRischio1");
			errore.setTipoErrore("CALCOLO RIS1");
			errore.setMessaggio("Calcolo rischio 1 data null tra datLiqPrlps o datCorrPrins o datCorrPrest");
			listaErrori.add(errore);
			*/
		}
		
		/*
		if (  (PraticaUtil.DECRETO_OMOLOGATO_NO.equals(flagDecrOmol.trim()) && datLiqPrlps != null ) 
				|| dataTrasmLsp == null  
				|| ( datLiqPrlps != null &&  dataTrasmLsp.getTime() > datLiqPrlps.getTime() )) {
			// Se (Flag_decr_omol = “no” e Dat_liq_prlps <> Null) 
			// o (Data_trasm_lsp = null) 
			// o (Dat_liq_prlps <> Null e Data_trasm_lsp > Dat_liq_prlps) allora 
			// ER = “E035”
		
		*/
		if (  	(	
					PraticaUtil.DECRETO_OMOLOGATO_SI.equals(flagDecrOmol.trim()) || PraticaUtil.DECRETO_OMOLOGATO_NO.equals(flagDecrOmol.trim()) 	
				) &&
				dataTrasmLsp != null && datLiqPrlps != null &&  dataTrasmLsp.getTime() > datLiqPrlps.getTime() ){
			/*
			 	Rischio 1 – espressione di rischio E035: se “Presenza decreto di omologa nel fascicolo” è NO 
			 	oppure se “Presenza decreto di omologa nel fascicolo” è SI 
			 	ma il pagamento della prestazione (“Data liquidazione prestazione da LPS “) è effettuato prima della trasmissione alla LPS (campo “Data trasmissione Decreto alla LPS” presente in “Post peritale”), allora come danno va considerato il valore del “Importo mensile rata Euro “ per il numero di rate erogate; 
			 */
			espressioneRischio = PraticaUtil.E035;
		}else if (PraticaUtil.DECRETO_OMOLOGATO_SI.equals(flagDecrOmol.trim())
				&& impMensRata.doubleValue() == impRataDovuto.doubleValue()
				&& datCorrPrins != null
				&& datCorrPrest != null
				&& datCorrPrins.getTime() < datCorrPrest.getTime()
				&& datLiqPrlps != null
			){
			// Se Flag_decr_omol = “si” 
			// e Imp_mens_rata = Imp_rata_dov 
			// e Dat_corr_prins <> null 
			// e Dat_corr_prest <> null 
			// e Dat_corr_prins < Dat_corr_prest 
			// e Dat_liq_prlps <> Null allora 
			// ER = “E037” 
			espressioneRischio = PraticaUtil.E037;
		}else if (PraticaUtil.DECRETO_OMOLOGATO_SI.equals(flagDecrOmol.trim())
				&& impMensRata.doubleValue() == impRataDovuto.doubleValue()
				&& datCorrPrins != null
				&& datCorrPrest != null
				&& datCorrPrins.getTime() > datCorrPrest.getTime()
				&& datLiqPrlps != null
			){
			// Se Flag_decr_omol = “si” 
			// e Imp_mens_rata = Imp_rata_dov 
			// e Dat_corr_prins <> null 
			// e Dat_corr_prest <> null 
			// e Dat_corr_prins > Dat_corr_prest 
			// e Dat_liq_prlps <> Null allora 
			// ER = “E039” 
			espressioneRischio = PraticaUtil.E039;
		}else if (PraticaUtil.DECRETO_OMOLOGATO_SI.equals(flagDecrOmol.trim())
				&& impMensRata.doubleValue() == impRataDovuto.doubleValue()
				&& datCorrPrins.getTime() == datCorrPrest.getTime()
				&& datLiqPrlps != null
			){
			// Se Flag_decr_omol = “si” 
			// e Imp_mens_rata = Imp_rata_dov 
			// e Dat_corr_prins = Dat_corr_prest 
			// e Dat_liq_prlps <> Null allora allora 
			// ER = “E040” 
			espressioneRischio = PraticaUtil.E040;
		}else if (PraticaUtil.DECRETO_OMOLOGATO_SI.equals(flagDecrOmol.trim())
				&& impMensRata.doubleValue() < impRataDovuto.doubleValue()
				&& datCorrPrins != null
				&& datCorrPrest != null
				&& datCorrPrins.getTime() == datCorrPrest.getTime()
				&& datLiqPrlps != null
			){
			// Se Flag_decr_omol = “si” 
			// e Imp_mens_rata < Imp_rata_dov 
			// e Dat_corr_prins <> null 
			// e Dat_corr_prest <> null 
			// e Dat_corr_prins = Dat_corr_prest 
			// e Dat_liq_prlps <> Null allora 
			// ER = “E038”
			espressioneRischio = PraticaUtil.E038;
		}else if (PraticaUtil.DECRETO_OMOLOGATO_SI.equals(flagDecrOmol.trim())
				&& impMensRata.doubleValue() > impRataDovuto.doubleValue()
				&& datCorrPrins != null
				&& datCorrPrest != null
				&& datCorrPrins.getTime() == datCorrPrest.getTime()
				&& datLiqPrlps != null
			){
			// Se Flag_decr_omol = “si” 
			// e Imp_mens_rata > Imp_rata_dov 
			// e Dat_corr_prins <> null 
			// e Dat_corr_prest <> null 
			// e Dat_corr_prins = Dat_corr_prest 
			// e Dat_liq_prlps <> Null
			// ER = “E036”
			espressioneRischio = PraticaUtil.E036;
		}else if(PraticaUtil.DECRETO_OMOLOGATO_SI.equals(flagDecrOmol.trim())
				&& datLiqPrlps != null ){	
			
			if (datCorrPrins.getTime() > datCorrPrest.getTime()){
				// Impatto 1 = ((Dat_corr_prins - Dat_corr_prest) * Imp_rata_dov) 
				int monthDifferences = PraticaUtil.getMonthsDifference(datCorrPrest,datCorrPrins);
				importo_parziale =  monthDifferences * impRataDovuto * -1;
			}else{
				
				// Impatto 1 = ((Dat_corr_prest - Dat_corr_prins) * Imp_mens_rata)  att.ne mantenere l’ordine di date
				int monthDifferences = PraticaUtil.getMonthsDifference(datCorrPrins,datCorrPrest );
				importo_parziale =  monthDifferences * impMensRata;
			}	
			
			// Impatto 2 = ((Imp_mens_rata - Imp_rata_dov) * (Data inizio campagna – Dat_corr_prest) * -1
			int monthDifferences2 = PraticaUtil.getMonthsDifference(datCorrPrest , dataCampagna);
			importo2 = (impMensRata - impRataDovuto ) * monthDifferences2;
			
			// Importo_1 = impatto 1 + impatto 2.
			importo_parziale = importo_parziale + importo2 ;
		
			espressioneRischio = "CALCOLO_IMPATTO";
		}else if(espressioneRischio != null){
			isToInsert = true;
			importo1 = 0;
		}

		if (espressioneRischio != null){
			if (espressioneRischio.equals("CALCOLO_IMPATTO")){
				/* Se Importo_1 < 0
						ER = “E038” (EDU per errata valutazione decreto di omologa (esito sfavorevole)) 
					Altrimenti
					Se importo_1 > 0
						ER = “E036” (rischio manifestato per errata valutazione decreto di omologa) 
					Altrimenti
						ER = “E040” (rischio non manifestato (pagata correttamente))
					Importo_1 = 0.
				*/
				if(importo_parziale < 0){
					espressioneRischio = PraticaUtil.E038;
					isToInsert = true;
					importo1 = importo_parziale;
				}else if(importo_parziale > 0){
					espressioneRischio = PraticaUtil.E036;
					isToInsert = true;
					importo1 = importo_parziale;
				}else{
					espressioneRischio = PraticaUtil.E040;
					isToInsert = true;
					importo1 = 0;
				}
			}else if(espressioneRischio.equals(PraticaUtil.E035)){
				// Importo_1 = (Imp_rata_dov - Imp_mens_rata) * (Data_campagna - Dat_corr_prins)
				int monthDifferences = PraticaUtil.getMonthsDifference(datCorrPrins , dataCampagna);
				/*
			 		Rischio 1 – espressione di rischio E035: se “Presenza decreto di omologa nel fascicolo” è NO 
			 		oppure se “Presenza decreto di omologa nel fascicolo” è SI 
			 		ma il pagamento della prestazione (“Data liquidazione prestazione da LPS “) è effettuato prima della trasmissione alla LPS (campo “Data trasmissione Decreto alla LPS” presente in “Post peritale”), allora come danno va considerato il valore del “Importo mensile rata Euro “ per il numero di rate erogate; 
				 */
				// importo1 = (impRataDovuto - impMensRata) * monthDifferences;
				 importo1 =  impMensRata * monthDifferences;
				isToInsert = true;
			}
			else if(espressioneRischio.equals(PraticaUtil.E037)){
				// Importo_1 = (Data corretta decorrenza prestazione - Data decorrenza prestazione inserita) * Importo mensile rata 
				int monthDifferences = PraticaUtil.getMonthsDifference(datCorrPrins , datCorrPrest);
				importo1 = monthDifferences * impMensRata * 1;
				isToInsert = true;
			}
			else if(espressioneRischio.equals(PraticaUtil.E039)){
				// Importo_1 = (Data decorrenza prestazione inserita - Data corretta decorrenza prestazione -) * Importo rata dovuta
				int monthDifferences = PraticaUtil.getMonthsDifference(datCorrPrest , datCorrPrins);
				importo1 = monthDifferences * impRataDovuto * -1;
				isToInsert = true;
			}
			else if(espressioneRischio.equals(PraticaUtil.E040)){
				// Importo_1 = 0
				importo1 = 0;
				isToInsert = true;
			}else if(espressioneRischio.equals(PraticaUtil.E038)){
				// Importo_1 = (Importo rata dovuta - Importo mensile rata) * (Data_campagna - Dat_corr_prins)
				int monthDifferences = PraticaUtil.getMonthsDifference(datCorrPrins , dataCampagna);
				importo1 = (impRataDovuto - impMensRata) * monthDifferences;
				isToInsert = true;
			}
			else if(espressioneRischio.equals(PraticaUtil.E036)){
				// Importo_1 = (Importo mensile rata - Importo rata dovuta) * (Data_campagna - Dat_corr_prins)
				int monthDifferences = PraticaUtil.getMonthsDifference(datCorrPrins , dataCampagna);
				importo1 = (impMensRata - impRataDovuto) * monthDifferences * -1;
				isToInsert = true;
			}
			
		}else if(espressioneRischio != null){
			isToInsert = true;
			importo1 = 0;
		}
		
		if (isToInsert) {
			Long idMRischio = PraticaUtil.getIdMRischio(mRischio,
					PraticaUtil.R001);
			if (idMRischio != null) {
				insertRischio(idMRischio, new Date(), espressioneRischio,
						idSPratica, idSSessione, importo1,
						listaPraticheRisToInsert, listAuMRisespr);
			}
			insertLogRischi("Rischio n. 1","INFO",espressioneRischio
					+ pratica.getIdSPratica());
		}

		insertLogRischi("Rischio n. 1","INFO","Fine determinazione Info Rischio 1 pratica: (idSPratica) "
				+ pratica.getIdSPratica());
		/*
		log.info("Fine determinazione Info Rischio 1 pratica: (idSPratica) "
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
