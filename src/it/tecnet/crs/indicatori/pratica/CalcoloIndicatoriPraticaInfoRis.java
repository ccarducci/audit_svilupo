package it.tecnet.crs.indicatori.pratica;

import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoFaseDati;
import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoFaseEsecuzioneProvvedimenti;
import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoFasePostPeritale;
import it.tecnet.crs.indicatori.pratica.rischio.CalcoloIndicatoriPraticaInfoRis1;
import it.tecnet.crs.indicatori.pratica.rischio.CalcoloIndicatoriPraticaInfoRis2;
import it.tecnet.crs.indicatori.pratica.rischio.CalcoloIndicatoriPraticaInfoRis3;
import it.tecnet.crs.indicatori.pratica.rischio.CalcoloIndicatoriPraticaInfoRis4;
import it.tecnet.crs.indicatori.pratica.rischio.CalcoloIndicatoriPraticaInfoRis5;
import it.tecnet.crs.indicatori.pratica.rischio.CalcoloIndicatoriPraticaInfoRis6;
import it.tecnet.crs.indicatori.pratica.rischio.CalcoloIndicatoriPraticaInfoRis7;
import it.tecnet.crs.jpa.model.AuCampagna;
import it.tecnet.crs.jpa.model.AuMRischio;
import it.tecnet.crs.jpa.model.AuMRisepr;
import it.tecnet.crs.jpa.model.AuSPratica;
import it.tecnet.crs.jpa.model.AuSPraticaRis;

import java.util.List;

import org.apache.log4j.Logger;

public class CalcoloIndicatoriPraticaInfoRis {

	protected static Logger log = Logger.getLogger(CalcoloIndicatoriPraticaInfoRis.class);
	
	private CalcoloIndicatoriPraticaInfoRis1 calcoloIndicatoriPraticaInfoRis1 = null;
	private CalcoloIndicatoriPraticaInfoRis2 calcoloIndicatoriPraticaInfoRis2 = null;
	private CalcoloIndicatoriPraticaInfoRis3 calcoloIndicatoriPraticaInfoRis3 = null;
	private CalcoloIndicatoriPraticaInfoRis4 calcoloIndicatoriPraticaInfoRis4 = null;
	private CalcoloIndicatoriPraticaInfoRis5 calcoloIndicatoriPraticaInfoRis5 = null;
	private CalcoloIndicatoriPraticaInfoRis6 calcoloIndicatoriPraticaInfoRis6 = null;
	private CalcoloIndicatoriPraticaInfoRis7 calcoloIndicatoriPraticaInfoRis7 = null;
	
	public CalcoloIndicatoriPraticaInfoRis(
			AtpoFaseEsecuzioneProvvedimenti praticaEp,
			List<CalcoloIndicatoriErrore> listaErrori, AuSPratica pratica,
			List<AuMRischio> rischio, long idSSessione, long idSPratica,
			List<AuSPraticaRis> listaPraticheRisToInsert,
			AtpoFasePostPeritale praticaPp, AtpoFaseDati praticaFd,
			AuCampagna campagna, List<AuMRisepr> listAuMRisespr) {
		super();

		calcoloIndicatoriPraticaInfoRis1 = new CalcoloIndicatoriPraticaInfoRis1(
				praticaEp, listaErrori, pratica, rischio,
				idSSessione, idSPratica, listaPraticheRisToInsert, listAuMRisespr, campagna, praticaPp);

		calcoloIndicatoriPraticaInfoRis2 = new CalcoloIndicatoriPraticaInfoRis2(
				praticaEp, praticaPp, listaErrori, pratica,
				rischio, idSSessione, idSPratica, listaPraticheRisToInsert,
				praticaFd, campagna, listAuMRisespr);

		calcoloIndicatoriPraticaInfoRis3 = new CalcoloIndicatoriPraticaInfoRis3(
				praticaEp, praticaPp, listaErrori, pratica,
				rischio, idSSessione, idSPratica, listaPraticheRisToInsert,
				praticaFd, campagna, listAuMRisespr);

		calcoloIndicatoriPraticaInfoRis4 = new CalcoloIndicatoriPraticaInfoRis4(
				praticaEp, praticaPp, listaErrori, pratica,
				rischio, idSSessione, idSPratica, listaPraticheRisToInsert,
				campagna, listAuMRisespr);

		calcoloIndicatoriPraticaInfoRis5 = new CalcoloIndicatoriPraticaInfoRis5(
				pratica, campagna, praticaEp, rischio, idSSessione, idSPratica,
				listaPraticheRisToInsert, listaErrori, listAuMRisespr);

		calcoloIndicatoriPraticaInfoRis6 = new CalcoloIndicatoriPraticaInfoRis6(
				pratica, campagna, praticaEp, praticaPp, rischio, idSSessione,
				idSPratica, listaPraticheRisToInsert, listaErrori, listAuMRisespr, praticaFd);

		calcoloIndicatoriPraticaInfoRis7 = new CalcoloIndicatoriPraticaInfoRis7(
				pratica, campagna, praticaEp, rischio, idSSessione, idSPratica,
				listaPraticheRisToInsert, listaErrori, listAuMRisespr);
	}
	
	public void calcolaIndicatoriRischio(){
		try{calcoloIndicatoriPraticaInfoRis1.calcoloInfoRischio1();} catch(Exception e)
		{
			log.error(e.getMessage());
		};
		try{calcoloIndicatoriPraticaInfoRis2.calcoloInfoRischio2();} catch(Exception e)
		{
			log.error(e.getMessage());
		};
		try{calcoloIndicatoriPraticaInfoRis3.calcoloInfoRischio3();} catch(Exception e)
		{
			log.error(e.getMessage());
		};
		try{calcoloIndicatoriPraticaInfoRis4.calcoloInfoRischio4();} catch(Exception e)
		{
			log.error(e.getMessage());
		};
		try{calcoloIndicatoriPraticaInfoRis5.calcoloInfoRischio5();} catch(Exception e)
		{
			log.error(e.getMessage());
		};
		try{calcoloIndicatoriPraticaInfoRis6.calcoloInfoRischio6();} catch(Exception e)
		{
			log.error(e.getMessage());
		};
		try{calcoloIndicatoriPraticaInfoRis7.calcoloInfoRischio7();} catch(Exception e)
		{
			log.error(e.getMessage());
		};
	}
}
