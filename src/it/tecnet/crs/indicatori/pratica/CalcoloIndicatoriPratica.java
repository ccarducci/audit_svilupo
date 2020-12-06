package it.tecnet.crs.indicatori.pratica;

import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoFaseAcquisizioneIstanza;
import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoFaseAutotutelaResistenzaGiudizio;
import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoFaseDati;
import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoFaseEsecuzioneProvvedimenti;
import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoFaseGestioneIstruttoria;
import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoFasePeritale;
import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoFasePostPeritale;
import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoPraticheSISCO;
import it.tecnet.crs.indicatori.pratica.nonconformita.CalcoloIndicatoriPraticaInfoNonConf;
import it.tecnet.crs.jpa.model.AuCampagna;
import it.tecnet.crs.jpa.model.AuMRischio;
import it.tecnet.crs.jpa.model.AuMRisepr;
import it.tecnet.crs.jpa.model.AuMVarcomp;
import it.tecnet.crs.jpa.model.AuSPratica;
import it.tecnet.crs.jpa.model.AuSPraticaRis;
import it.tecnet.crs.jpa.model.AuSPraticaVarcomp;

import java.util.List;

import org.apache.log4j.Logger;

public class CalcoloIndicatoriPratica {
	protected static Logger log = Logger
			.getLogger(CalcoloIndicatoriPratica.class);

	private AtpoPraticheSISCO praticaCisco;
	private AuCampagna campagna;
	private AuSPratica pratica;
	private List<AuMRischio> rischio;
	private AtpoFasePostPeritale praticaPp;
	private AtpoFaseDati praticaFd;
	private AtpoFaseEsecuzioneProvvedimenti praticaEp;
	private AtpoFasePeritale praticaPe;
	private AtpoFaseGestioneIstruttoria praticaGi;
	private AtpoFaseAutotutelaResistenzaGiudizio praticaAr;
	private AtpoFaseAcquisizioneIstanza praticaAi;

	private List<AuSPraticaRis> listaPraticheRisToInsert;
	private List<AuSPraticaVarcomp> listaPraticheVarCompToInsert;
	private List<CalcoloIndicatoriErrore> listaErrori;
	private List<AuMVarcomp> auMVarcomp;
	
	private List<AuMRisepr> listAuMRisespr;
	
	public CalcoloIndicatoriPratica(AuCampagna campagna,
			AuSPratica pratica,
			List<AuMRischio> rischio, AtpoFasePostPeritale praticaPp,
			AtpoFaseDati praticaFd, AtpoFaseEsecuzioneProvvedimenti praticaEp,
			AtpoFasePeritale praticaPe, AtpoFaseGestioneIstruttoria praticaGi,
			AtpoFaseAutotutelaResistenzaGiudizio praticaAr,
			AtpoFaseAcquisizioneIstanza praticaAi,
			List<AuSPraticaRis> listaPraticheRisToInsert,
			List<AuSPraticaVarcomp> listaPraticheVarCompToInsert,
			List<AuMVarcomp> auMVarcomp,
			List<CalcoloIndicatoriErrore> listaErrori, long idSSessione,
			long idSPratica,
			AtpoPraticheSISCO praticaCisco, List<AuMRisepr> listAuMRisespr) {
		super();
		this.campagna = campagna;
		this.pratica = pratica;
		this.rischio = rischio;
		this.praticaPp = praticaPp;
		this.praticaFd = praticaFd;
		this.praticaEp = praticaEp;
		this.praticaPe = praticaPe;
		this.praticaGi = praticaGi;
		this.praticaAr = praticaAr;
		this.praticaAi = praticaAi;
		this.listaPraticheRisToInsert = listaPraticheRisToInsert;
		this.listaPraticheVarCompToInsert = listaPraticheVarCompToInsert;
		this.listaErrori = listaErrori;
		this.auMVarcomp = auMVarcomp;
		this.praticaCisco = praticaCisco;
		this.listAuMRisespr = listAuMRisespr;
	}

	public void calcolaIndicatori() throws Exception {
		log.info("Inizio calcolo indicatori pratica: (idPratica) " + pratica.getIdPratica());
		
		CalcoloIndicatoriErrore infoInizio = new CalcoloIndicatoriErrore();
		infoInizio.setTipoRischio("Inizio Calcolo Rischi");
		infoInizio.setTipoErrore("INFO");
		infoInizio.setMessaggio("Calcolo indicatori");
		listaErrori.add(infoInizio);
		
		// CALCOLO RISCHI
		CalcoloIndicatoriPraticaInfoRis calcoloRischi = new CalcoloIndicatoriPraticaInfoRis(
				praticaEp, listaErrori, pratica, rischio, pratica
						.getIdSSessione(), pratica.getIdSPratica(),
				listaPraticheRisToInsert, praticaPp, praticaFd, campagna, listAuMRisespr);

		calcoloRischi.calcolaIndicatoriRischio();

		CalcoloIndicatoriErrore fineInizio = new CalcoloIndicatoriErrore();
		fineInizio.setTipoRischio("Fine Calcolo Rischi");
		fineInizio.setTipoErrore("INFO");
		fineInizio.setMessaggio("Calcolo indicatori");
		listaErrori.add(fineInizio);

		CalcoloIndicatoriErrore infoNonConfInizio = new CalcoloIndicatoriErrore();
		infoNonConfInizio.setTipoRischio("Inizio Calcolo Non Conf");
		infoNonConfInizio.setTipoErrore("INFO");
		infoNonConfInizio.setMessaggio("Calcolo indicatori");
		listaErrori.add(infoNonConfInizio);
		// CALCOLO NON CONF
		CalcoloIndicatoriPraticaInfoNonConf calcolaNonConf = new CalcoloIndicatoriPraticaInfoNonConf(
				praticaEp, praticaPp, praticaPe, praticaGi, praticaAr,
				praticaFd, praticaAi, listaPraticheVarCompToInsert, pratica
						.getIdSSessione(), pratica.getIdSPratica(), listaErrori, auMVarcomp, praticaCisco);
		
		calcolaNonConf.CalcoloNonConf();

		CalcoloIndicatoriErrore infoNonConfFine = new CalcoloIndicatoriErrore();
		infoNonConfFine.setTipoRischio("Fine Calcolo Non Conf");
		infoNonConfFine.setTipoErrore("INFO");
		infoNonConfFine.setMessaggio("Calcolo indicatori");
		listaErrori.add(infoNonConfFine);
		// CALCOLO PRATICA
		CalcoloIndicatoriPraticaInfo calcoloIndicatoriPraticaInfo = 
			new CalcoloIndicatoriPraticaInfo(praticaGi, praticaPe, pratica, listaPraticheRisToInsert, praticaAr);
		
		calcoloIndicatoriPraticaInfo.calcoloIndicatoriPraticaInfoLivelloPratica();
		CalcoloIndicatoriErrore infoFine = new CalcoloIndicatoriErrore();
		infoFine.setTipoRischio("Fine Calcolo Livello Pratica");
		infoFine.setTipoErrore("INFO");
		infoFine.setMessaggio("Calcolo indicatori");
		listaErrori.add(infoFine);
		log.info("Fine calcolo indicatori pratica: (idPratica) " + pratica.getIdPratica());
	}

	public List<CalcoloIndicatoriErrore> getListaErrori() {
		return listaErrori;
	}

	public List<AuSPraticaRis> getPraticheRis() {
		return listaPraticheRisToInsert;
	}

	public List<AuSPraticaVarcomp> getPraticheVarcomp() {
		return listaPraticheVarCompToInsert;
	}

	public void setPraticheRis(List<AuSPraticaRis> lista){
		listaPraticheRisToInsert = lista;
	}
	
	public void setPraticheVarcomp(List<AuSPraticaVarcomp> lista){
		listaPraticheVarCompToInsert = lista;
	}
}
