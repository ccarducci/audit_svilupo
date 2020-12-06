package it.tecnet.crs.indicatori.pratica;

import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoFaseAutotutelaResistenzaGiudizio;
import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoFaseGestioneIstruttoria;
import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoFasePeritale;
import it.tecnet.crs.jpa.model.AuSPratica;
import it.tecnet.crs.jpa.model.AuSPraticaRis;
import it.tecnet.crs.util.PraticaUtil;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

public class CalcoloIndicatoriPraticaInfo {
	protected static Logger log = Logger
			.getLogger(CalcoloIndicatoriPraticaInfo.class);

	private AtpoFaseGestioneIstruttoria praticaGi;
	private AtpoFasePeritale praticaPe;
	private AtpoFaseAutotutelaResistenzaGiudizio praticaAr;
	private AuSPratica pratica;
	private List<AuSPraticaRis> praticheRis;

	private Double max = 0D;
	private Double min = 0D;

	public CalcoloIndicatoriPraticaInfo(AtpoFaseGestioneIstruttoria praticaGi,
			AtpoFasePeritale praticaPe, AuSPratica pratica,
			List<AuSPraticaRis> praticheRis, AtpoFaseAutotutelaResistenzaGiudizio praticaAr) {
		super();
		this.praticaGi = praticaGi;
		this.praticaPe = praticaPe;
		this.pratica = pratica;
		this.praticheRis = praticheRis;
		this.praticaAr = praticaAr;
		getMinMaxFrom();
	}

	public void calcoloIndicatoriPraticaInfoLivelloPratica() {
		// Data_cost_giud
		Date dataCostGiud = praticaGi.getDataCostitGiudizio();

		// Pres_ctu_oper
		String presCtuOper = PraticaUtil.checkStringAndTrimUpperCaseSiNo(praticaPe.getPresMedicoInpsDoc());

		// Oss_parere_def
		String ossParereDef = PraticaUtil.checkStringAndTrimUpperCaseSiNo(praticaPe
				.getOssParDef());

		// Parere_bozza_ctu
		String parereBozztCtu = PraticaUtil.checkStringAndTrimUpperCase(praticaPe.getParereBozzaCtu());
		int parereBozzCtu = 0;
		try {
			parereBozzCtu = Integer.parseInt(parereBozztCtu);
		} catch (Exception e) {
			log.error(e);
		}
		
		// parere
		String parere = PraticaUtil.checkStringAndTrimUpperCase(praticaAr.getParere());
		
		String attoDiConstRintr = PraticaUtil.checkStringAndTrimUpperCaseSiNo(praticaGi.getEccezioniNonRilevabili());
		
		/*
			U02	 Difesa completa (valutazione di autotutela/resistenza effettuata, 
				 costituzione in giudizio, 
				 visita peritale effettuata, 
				 osservazioni sanitarie rese)
			U03	 Difesa essenziale (costituzione in giudizio, visita peritale effettuata)
			U04	 Difesa incompleta (manca l’atto di costituzione e/o la visita peritale)
		 */
		
		/*
		 * Data_cost_giud 	= Data di costituzione in giudizio
		 * presCtuOper 		= Presenza medico INPS da documentazione CTU
		 * parereBozzCtu	= Parere su bozza CTU
		 * ossParereDef		= Osservazioni su parere definitivo
		 */
		
		/*
			Calcolo Difesa -> la difesa è completa se:
			
			- Valutazione di autotutela/resistenza effettuata, (si, autotutela/ si, resistenza in giudizio)
			- costituzione in giudizio, 
			- visita peritale effettuata, 
			- osservazioni sanitarie rese 
			
			Nel caso in cui non è valorizzata la data di costituzione in giudizio:
			- se "Atto di costituzione rintracciabile" è si --> difesa completa
			- altrimenti difessa incompleta
			
			In gernerale, a parte il caso descritto, se manca la data di costituzione in giudizio la difesa è incompleta.
			
		 */
		
		String tipoDifesa = "";
		
		if(	dataCostGiud != null && presCtuOper.equals(PraticaUtil.SI)
			&& ( parereBozzCtu > 1 || ossParereDef.equals(PraticaUtil.SI) )
			&& ( parere.length() > 0 && ( "2".equals(parere) || "3".equals(parere) ) )
			){
			tipoDifesa = PraticaUtil.TIPO_DIFESA_U02;
		}else if(dataCostGiud != null && presCtuOper.equals(PraticaUtil.SI)){
			// Se (Data_cost_giud <> “” e Pres_ctp_oper = “Si”) allora Tipo Difesa = “U03”
			tipoDifesa = PraticaUtil.TIPO_DIFESA_U03;
		}else if(dataCostGiud != null || presCtuOper.equals(PraticaUtil.NO)){
			// Se (Data_cost_giud = “” o Pres_ctp_oper = “No”) allora Tipo Difesa = “U04”
			tipoDifesa = PraticaUtil.TIPO_DIFESA_U04;
		}else if (dataCostGiud == null && attoDiConstRintr.equals(PraticaUtil.SI) ) {
			tipoDifesa = PraticaUtil.TIPO_DIFESA_U02;
		}else{
			tipoDifesa = PraticaUtil.TIPO_DIFESA_U04;
		}
		
		Double importo_1 = 0d;
		if(max > 0) importo_1 = max;
		
		pratica.setTipoDifesa(tipoDifesa);
		pratica.setImporto(importo_1);
			
	}

	private void getMinMaxFrom() {
		boolean isFirst = true;
		for (AuSPraticaRis auSPraticaRis : praticheRis) {
			if (isFirst) {
				max = auSPraticaRis.getImporto();
				min = auSPraticaRis.getImporto();
				isFirst = false;
			} else {
				if (auSPraticaRis.getImporto() > max)
					max = auSPraticaRis.getImporto();
				if (auSPraticaRis.getImporto() < min)
					min = auSPraticaRis.getImporto();
			}
		}
	}
}
