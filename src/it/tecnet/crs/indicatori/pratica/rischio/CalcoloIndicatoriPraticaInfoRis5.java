package it.tecnet.crs.indicatori.pratica.rischio;

import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoFaseEsecuzioneProvvedimenti;
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

public class CalcoloIndicatoriPraticaInfoRis5 extends
		CalcoloIndicatoriPraticaInfoRisBase {

	protected static Logger log = Logger
			.getLogger(CalcoloIndicatoriPraticaInfoRis5.class);

	private AuSPratica pratica;
	private AuCampagna campagna;
	private AtpoFaseEsecuzioneProvvedimenti praticaEp;
	private List<AuMRischio> mRischio;
	private long idSSessione;
	private long idSPratica;
	private List<AuSPraticaRis> listaPraticheRisToInsert;
	private List<AuMRisepr> listAuMRisespr;
	
	private List<CalcoloIndicatoriErrore> listaErrori;

	public CalcoloIndicatoriPraticaInfoRis5(AuSPratica pratica,
			AuCampagna campagna, AtpoFaseEsecuzioneProvvedimenti praticaEp,
			List<AuMRischio> rischio, long idSSessione, long idSPratica,
			List<AuSPraticaRis> listaPraticheRisToInsert,
			List<CalcoloIndicatoriErrore> listaErrori,
			List<AuMRisepr> listAuMRisespr) {
		super();
		this.pratica = pratica;
		this.campagna = campagna;
		this.praticaEp = praticaEp;
		this.mRischio = rischio;
		this.idSSessione = idSSessione;
		this.idSPratica = idSPratica;
		this.listaPraticheRisToInsert = listaPraticheRisToInsert;
		this.listaErrori = listaErrori;
		this.listAuMRisespr = listAuMRisespr;
	}

	/*
	 * Calcolare info Rischio n. 5 – Pagamento spese azione esecutiva per
	 * mancato/tardivo pagamento spese CTU
	 */
	public void calcoloInfoRischio5() {
		insertLogRischi("Rischio n. 5","INFO","Inizio determinazione Info Rischio 5 pratica: (idSPratica) "
				+ pratica.getIdSPratica());
		/*
		log.info("Inizio determinazione Info Rischio 5 pratica: (idSPratica) "
				+ pratica.getIdSPratica());
		*/
		String espressioneRischio = "";
		double importo = 0d;
		boolean isToInsert = false;

		// Data_campagna
		Date dataCampagna = PraticaUtil.getDateWithoutTimeUsingFormat(campagna
				.getDataInizio());
		// Data_fattura
		Date dataFattura = PraticaUtil.getDateWithoutTimeUsingFormat(praticaEp
				.getDataFattura());
		// Data_liq_ctu
		Date dataLiqCtu = PraticaUtil.getDateWithoutTimeUsingFormat(praticaEp
				.getDataLiqCtuAtpo());
		// Data_comu_psctu
		Date dataComuPsctu = PraticaUtil
				.getDateWithoutTimeUsingFormat(praticaEp.getDataComPreSctu());
		// Data_pign_sctu
		Date dataPignSctu = PraticaUtil.getDateWithoutTimeUsingFormat(praticaEp
				.getDataSpeseCtuPign());
		// Data_spese_ctu
		Date dataSpeseCtu = PraticaUtil.getDateWithoutTimeUsingFormat(praticaEp
				.getDataSpeseCtuPrec());
		// Costo_prec_sctu
		Double costoPrecSctu = PraticaUtil.checkValueImp(praticaEp
				.getCostoPreSctu());
		// Costo_pign_sctu
		Double costoPignSctu = PraticaUtil.checkValueImp(praticaEp
				.getCostoPignSctu());
		
		if (	dataCampagna == null
				|| dataFattura == null
				|| dataLiqCtu  == null
				|| dataComuPsctu == null
				|| dataPignSctu == null
				|| dataSpeseCtu == null 
				 ){
			/*
			CalcoloIndicatoriErrore errore = new CalcoloIndicatoriErrore();
			errore.setTipoRischio("calcoloInfoRischio5");
			errore.setTipoErrore("CALCOLO RIS5");
			errore.setMessaggio("Calcolo rischio 5 data null tra dataCampagna, dataFattura, dataLiqCtu, dataComuPsctu, dataPignSctu, dataSpeseCtu");
			listaErrori.add(errore);
			*/
			insertErrorRischi("Rischio n. 5","WARNING","data null tra dataPagslAvv, dataArrNotula, dataDepdOmol, dataComuSlpr, dataCampagna, dataSlPrec, dataPignSl "
					+ pratica.getIdSPratica());
		}
		
		
		if ( dataCampagna!= null && dataFattura != null && PraticaUtil.daysBetween( dataFattura , dataCampagna) < 31) {
			// Se (data_campagna - Data_fattura) < 31 allora ER = “E019”
			espressioneRischio = PraticaUtil.E019;
		} else if (dataLiqCtu!= null && dataFattura != null &&  PraticaUtil.daysBetween(dataFattura,dataLiqCtu) < 31) {
			// Se (Data_liq_ctu - Data_fattura) < 31 allora ER = “E020”
			espressioneRischio = PraticaUtil.E020;
		} else if (dataLiqCtu!= null && dataFattura != null &&  PraticaUtil.daysBetween(dataFattura,dataLiqCtu) > 120
				&& dataComuPsctu != null && dataPignSctu != null
				&& dataSpeseCtu != null && PraticaUtil.daysBetween(dataSpeseCtu,dataComuPsctu) < 11) {
			/*
			 * Se (Data_liq_ctu - Data_fattura) > 120 e Data_comu_psctu <> Null e
			 * Data_pign_sctu <> Null e (Data_comu_psctu - Data_spese_ctu) < 11
			 * allora ER = “E016”
			 */
			espressioneRischio = PraticaUtil.E016;
		} else if (dataLiqCtu!= null && dataFattura != null && PraticaUtil.daysBetween(dataFattura,dataLiqCtu) > 120
				&& dataComuPsctu != null && dataPignSctu != null
				&& dataSpeseCtu != null &&  PraticaUtil.daysBetween(dataSpeseCtu,dataComuPsctu) > 10) {
			/*
			 * Se (Data_liq_ctu - Data_fattura) > 120 e Data_comu_psctu <> Null e
			 * Data_pign_sctu <> Null e (Data_comu_psctu - Data_spese_ctu) > 10
			 * allora ER = “E018”
			 */
			espressioneRischio = PraticaUtil.E018;
		} else if (dataLiqCtu != null && dataFattura != null && PraticaUtil.daysBetween(dataFattura,dataLiqCtu) > 120
				&& dataSpeseCtu == null) {
			// Se (Data_liq_ctu - Data_fattura) > 120 e Data_spese_ctu = Null
			// allora ER = “E014”
			espressioneRischio = PraticaUtil.E014;
		} else if (dataLiqCtu != null && dataFattura != null && PraticaUtil.daysBetween(dataFattura,dataLiqCtu ) > 120
				&& dataSpeseCtu != null) {
			// Se (Data_liq_ctu- Data_fattura) > 120 e Data_spese_ctu <> Null
			// allora ER = “E015”
			espressioneRischio = PraticaUtil.E015;
		}

		/*
		 * Se ER = “E015” allora Importo_1 = Costo_prec_sctu Altrimenti Se (ER =
		 * “E016” o ER = “E018”) allora Importo_1 = (Costo_prec_sctu +
		 * Costo_pign_sctu) Altrimenti Importo_1 = Null.
		 */
		if (espressioneRischio.length() > 0) {
			if (PraticaUtil.E015.equals(espressioneRischio)) {
				importo = costoPrecSctu;
				isToInsert = true;
			} else if (PraticaUtil.E016.equals(espressioneRischio)
					|| PraticaUtil.E018.equals(espressioneRischio)) {
				importo = costoPrecSctu + costoPignSctu;
				isToInsert = true;
			}else{
				isToInsert = true;
				importo = 0;
			}
		}

		if (isToInsert) {
			Long idMRischio = PraticaUtil.getIdMRischio(mRischio,
					PraticaUtil.R005);
			if (idMRischio != null) {
				insertRischio(idMRischio, new Date(), espressioneRischio,
						idSPratica, idSSessione, importo,
						listaPraticheRisToInsert, listAuMRisespr);
			}
			insertLogRischi("Rischio n. 5","INFO",espressioneRischio
					+ pratica.getIdSPratica());
		}
		/*
		log.info("Fine determinazione Info Rischio 5 pratica: (idSPratica) "
				+ pratica.getIdSPratica());
		*/
		insertLogRischi("Rischio n. 5","INFO","Fine determinazione Info Rischio 5 pratica: (idSPratica) "
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
