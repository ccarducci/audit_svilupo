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

public class CalcoloIndicatoriPraticaInfoRis7 extends
		CalcoloIndicatoriPraticaInfoRisBase {

	protected static Logger log = Logger
			.getLogger(CalcoloIndicatoriPraticaInfoRis7.class);

	private AuSPratica pratica;
	private AtpoFaseEsecuzioneProvvedimenti praticaEp;
	private List<AuMRischio> mRischio;
	private long idSSessione;
	private long idSPratica;
	private List<AuSPraticaRis> listaPraticheRisToInsert;
	private List<AuMRisepr> listAuMRisespr;
	
	private List<CalcoloIndicatoriErrore> listaErrori;
	@SuppressWarnings("unused")
	private AuCampagna campagna;
	
	public CalcoloIndicatoriPraticaInfoRis7(AuSPratica pratica,
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

	public void calcoloInfoRischio7() {
		
		/*
		log.info("Inizio determinazione Info Rischio 7 pratica: (idSPratica) "
				+ pratica.getIdSPratica());
		*/
		insertLogRischi("Rischio n. 7","INFO","Inizio determinazione Info Rischio 7 pratica: (idSPratica) "
				+ pratica.getIdSPratica());
		
		String espressioneRischio = "";
		double importo = 0d;
		boolean isToInsert = false;

		// Data_dliq_omol
		Date dataDliqOmol = PraticaUtil.getDateWithoutTimeUsingFormat(praticaEp
				.getDataDecrLiqCtu());
		// Flag_decr_omol
		String flagDescrOmol = PraticaUtil
				.checkStringAndTrimUpperCaseSiNo(praticaEp.getPresDecrOmgFasc());
		// Imp_spesectu_pag
		Double impSpesectuPag = PraticaUtil.checkValueImp(praticaEp
				.getImpSpeseCtuPagate());
		// Imp_spesectu_dov
		Double impSpesectuDov = PraticaUtil.checkValueImp(praticaEp
				.getImpSpeseCtuDovute());
		// Condanna_pag_ctu
		String condannaPagCtu = PraticaUtil
				.checkStringAndTrimUpperCaseSiNo(praticaEp.getCondannaPagCtuAtpo());
		
		if(dataDliqOmol != null){
			if (PraticaUtil.NO.equals(flagDescrOmol) && impSpesectuPag.doubleValue() > 0D) {
				// Se Flag_decr_omol= “no” e Imp_spesectu_pag <> Null = “E026”
				espressioneRischio = PraticaUtil.E026;
			} else if (PraticaUtil.SI.equals(flagDescrOmol)
					&& impSpesectuPag.doubleValue() > impSpesectuDov.doubleValue()) {
				// Se Flag_decr_omol = “si” e Imp_spesectu_pag > Imp_spesectu_dov
				// allora ER = “E027”
				espressioneRischio = "E027";
			} else if (PraticaUtil.SI.equals(flagDescrOmol)
					&& impSpesectuPag.doubleValue() < impSpesectuDov.doubleValue()) {
				// Se Flag_decr_omol = “si” e Imp_spesectu_pag < Imp_spesectu_dov
				// allora ER = “E028”
				espressioneRischio = PraticaUtil.E028;
			} else if (PraticaUtil.SI.equals(flagDescrOmol)
					&& impSpesectuPag.doubleValue() == impSpesectuDov.doubleValue()) {
				// Se Flag_decr_omol = “si” e Imp_spesectu_pag = Imp_spesectu_dov
				// allora ER = “E034”
				espressioneRischio = PraticaUtil.E034;
			} 
		}else if( PraticaUtil.SI.equals(flagDescrOmol) && PraticaUtil.NO.equals(condannaPagCtu)
					&& impSpesectuPag.doubleValue() > 0D && impSpesectuDov.doubleValue() == 0D){
				espressioneRischio = PraticaUtil.E026;
		}
		 
		/*
		 * Se ER = “E026” allora Importo_1 = Imp_spesectu_pag Altrimenti Se (ER
		 * = “E027” o ER = “E028”) allora Importo_1 = (Imp_spesectu_pag -
		 * Imp_spesectu_dov) Altrimenti Importo_1 = Null.
		 */
		if (espressioneRischio.length() > 0) {
			if (PraticaUtil.E026.equals(espressioneRischio)) {
				importo = impSpesectuPag;
				isToInsert = true;
			} else if (PraticaUtil.E027.equals(espressioneRischio)
					|| PraticaUtil.E028.equals(espressioneRischio)) {
				importo = impSpesectuPag - impSpesectuDov;
				isToInsert = true;
			}else{
				isToInsert = true;
				importo = 0;
			}
		}

		if (isToInsert) {
			Long idMRischio = PraticaUtil.getIdMRischio(mRischio,
					PraticaUtil.R007);
			if (idMRischio != null) {
				insertRischio(idMRischio, new Date(), espressioneRischio,
						idSPratica, idSSessione, importo,
						listaPraticheRisToInsert, listAuMRisespr);
			}
			insertLogRischi("Rischio n. 7","INFO",espressioneRischio
					+ pratica.getIdSPratica());
		}

		/*
		log.info("Fine determinazione Info Rischio 7 pratica: (idSPratica) "
				+ pratica.getIdSPratica());
				*/
		insertLogRischi("Rischio n. 7","INFO","Fine determinazione Info Rischio 7 pratica: (idSPratica) "
				+ pratica.getIdSPratica());
	}

	@SuppressWarnings("unused")
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
