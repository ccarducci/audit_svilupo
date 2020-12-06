package it.tecnet.crs.ATPO.util;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoDettaglioFascicolo;
import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoFaseAcquisizioneIstanza;
import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoFaseAutotutelaResistenzaGiudizio;
import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoFaseEsecuzioneProvvedimenti;
import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoFaseGestioneIstruttoria;
import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoFasePeritale;
import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoFasePostPeritale;
import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoPraticheSISCO;
import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoRiepilogoFascicolo;
import it.tecnet.crs.ATPO.auditors.web.dto.AtpoAuMContestoDto;
import it.tecnet.crs.ATPO.auditors.web.dto.AtpoDettaglioFascicoloDto;
import it.tecnet.crs.ATPO.auditors.web.dto.NonConformitaPraticheAtpoDto;
import it.tecnet.crs.ATPO.auditors.web.dto.RischipraticheAtpoDto;

public class ObjectToDto {

	/*
	 * RISCHI PRATICHE ATPO
	 */
	public static RischipraticheAtpoDto objectToRischiPraticheDto(Object[] o){
		int i=0;

		RischipraticheAtpoDto p= new RischipraticheAtpoDto();
		if(o[i]!=null){
			p.setIdRischio((Long) o[i]);
		}i++;
		if(o[i]!=null){
			p.setCodiceRischio((String) o[i]);
		}i++;
		if(o[i]!=null){
			p.setDescrizione((String) o[i]);
		}i++;
		if(o[i]!=null){
			p.setCodiceEsprRischio((String) o[i]);
		}i++;
		if(o[i]!=null){
			p.setDescrizioneEpsrRischio((String) o[i]);
		}i++;
		if(o[i]!=null){
			p.setDataAttribuzione((Date) o[i]);
		}i++;
		if(o[i]!=null){
			p.setImporto((BigDecimal) o[i]);
		}i++;

		return p;
	}

	/*
	 * NON CONFORMITA PRATICHE ATPO
	 */
	public static NonConformitaPraticheAtpoDto objectToNonConformitaPraticheDto(Object[] o){
		int i=0;

		NonConformitaPraticheAtpoDto p= new NonConformitaPraticheAtpoDto();
		if(o[i]!=null){
			p.setIdNonConf((Long)o[i]);
		}i++;
		if(o[i]!=null){
			p.setOrdinamento((Integer)o[i]);
		}i++;
		if(o[i]!=null){
			p.setDescrizioneFase((String) o[i]);
		}i++;
		if(o[i]!=null){
			p.setDescrNonConformita((String) o[i]);
		}i++;
		if(o[i]!=null){
			p.setVarianteComportamentale((String) o[i]);
		}i++;
		if(o[i]!=null){
			p.setDescrizioneVarComp((String) o[i]);
		}i++;
		if(o[i]!=null){
			p.setPesoVarComp((BigDecimal)o[i]);
		}i++;
		if(o[i]!=null){
			p.setColore((String) o[i]);
		}i++;
		if(o[i]!=null){
			p.setDataAttribuzione((Date)o[i]);
		}i++;

		return p;
	}

	/*
	 * GESTIONE ISTRUTTORIA
	 */
	public static AtpoFaseGestioneIstruttoria objectToAtpoFaseGestioneIstruttoria(Object[] o){
		AtpoFaseGestioneIstruttoria a = new AtpoFaseGestioneIstruttoria();
		int i=0;
		if(o[i]!=null){
			a.setIdGestioneIstruttoria((Long)o[i]);
		}i++;
		if(o[i]!=null){
			a.setEccezioniNonRilevabili((String)o[i]);
		}i++;
		if(o[i]!=null){
			a.setLitispendenza((String)o[i]);
		}i++;
		if(o[i]!=null){
			a.setDecadenza((String)o[i]);
		}i++;
		if(o[i]!=null){
			a.setCorrispAtpDomAmmInv((String)o[i]);
		}i++;
		if(o[i]!=null){
			a.setVerificaDicEsPagSpese((String)o[i]);
		}i++;
		if(o[i]!=null){
			a.setIndeterminatezzaOggDom((String)o[i]);
		}i++;
		if(o[i]!=null){
			a.setCarenzaInteresseAdAgire((String)o[i]);
		}i++;
		if(o[i]!=null){
			a.setDataCostitGiudizio((Date)o[i]);
		}i++;
		if(o[i]!=null){
			a.setCostGiudTelematica((String)o[i]);
		}i++;
		if(o[i]!=null){
			a.setDataPrimaUdienza((Date)o[i]);
		}i++;
		if(o[i]!=null){
			a.setIntervalloCostGiudUdienza((Long)o[i]);
		}i++;
		if(o[i]!=null){
			a.setAltreEccProcessuali((String)o[i]);
		}i++;
		if(o[i]!=null){
			a.setIdFaseDati((Long)o[i]);
		}i++;
		return a;
	}
	/*
	 * PERITALE
	 */
	public static AtpoFasePeritale objectToAtpoFasePeritale(Object[] o) {
		AtpoFasePeritale a = new AtpoFasePeritale();
		int i=0;
		if(o[i]!=null){
			a.setIdPeritale((Long)o[i]);
		}i++;
		if(o[i]!=null){
			a.setDataComOpPerCTU((Date)o[i]);
		}i++;
		if(o[i]!=null){
			a.setRecInfoOpPeritali((String)o[i]);
		}i++;
		if(o[i]!=null){
			a.setAssCTUMedicoInps((String)o[i]);
		}i++;
		if(o[i]!=null){
			a.setCtpINPSopPeritali((String)o[i]);
		}i++;
		if(o[i]!=null){
			a.setDataArrBozza((Date)o[i]);
		}i++;
		if(o[i]!=null){
			a.setDataProtBozza((Date)o[i]);
		}i++;
		if(o[i]!=null){
			a.setCtuBozzaImgAtti((String)o[i]);
		}i++;
		if(o[i]!=null){
			a.setParereBozzaCtu((String)o[i]);
		}i++;
		if(o[i]!=null){
			a.setOssBozza((String)o[i]);
		}i++;
		if(o[i]!=null){
			a.setDataComDepCTUDefCanc((Date)o[i]);
		}i++;
		if(o[i]!=null){
			a.setDataProtCTUDef((Date)o[i]);
		}i++;
		if(o[i]!=null){
			a.setCtuDefImgAtti((String)o[i]);
		}i++;
		if(o[i]!=null){
			a.setIntTempComDepCTUtoCTUdef((Long)o[i]);
		}i++;
		if(o[i]!=null){
			a.setDataTermDissSisco((Date)o[i]);
		}i++;
		if(o[i]!=null){
			a.setDataTermDissDecr((Date)o[i]);
		}i++;
		if(o[i]!=null){
			a.setParereDissAccetfascitazione((String)o[i]);
		}i++;
		if(o[i]!=null){
			a.setOssParDef((String)o[i]);
		}i++;
		if(o[i]!=null){
			a.setIdFaseDati((Long)o[i]);
		}i++;
		
		if(o[i]!=null){
			a.setPresMedicoInpsDoc((String)o[i]);
		}i++;

		return a;
	}
	/*
	 * AUTOTUTELA RES GIUDIZIO
	 */
	public static AtpoFaseAutotutelaResistenzaGiudizio objectToAtpoAutotutelaResGiud(Object[] o) {
		AtpoFaseAutotutelaResistenzaGiudizio a= new AtpoFaseAutotutelaResistenzaGiudizio();
		int i=0;
		if(o[i]!=null){
			a.setIdAutotutela((Long)o[i]);
		}i++;
		if(o[i]!=null){
			a.setParere((String)o[i]);
		}i++;
		if(o[i]!=null){
			a.setTerminiPrimaUdienza((String)o[i]);
		}i++;
		if(o[i]!=null){
			a.setIdFaseDati((Long)o[i]);
		}i++;
		return a;
	}
	/*
	 * ACQUISIZIONE ISTANZA
	 */
	public static AtpoFaseAcquisizioneIstanza objectToAtpoAcquisizioneIstanza(Object[] o) {
		AtpoFaseAcquisizioneIstanza a = new AtpoFaseAcquisizioneIstanza();
		int i=0;
		if(o[i]!=null){
			a.setIdFaseAcquisizioneIstanza((Long)o[i]);
		}i++;
		if(o[i]!=null){
			a.setDataNotifica((Date)o[i]);
		}i++;
		if(o[i]!=null){
			a.setDataProtocollo((Date)o[i]);
		}i++;
		if(o[i]!=null){
			a.setVoceTitolarioErrata((String)o[i]);
		}i++;
		if(o[i]!=null){
			a.setIntervalloNotificaProtocollo((Long)o[i]);
		}i++;
		if(o[i]!=null){
			a.setProtocolloConImg((String)o[i]);
		}i++;
		if(o[i]!=null){
			a.setDataAcquisizioneSISCO((Date)o[i]);
		}i++;
		if(o[i]!=null){
			a.setIntervalloNotificaSISCO((Long)o[i]);
		}i++;
		if(o[i]!=null){
			a.setIdFaseDati((Long)o[i]);
		}i++;
		return a;
	}
	/*
	 * POST PERITALE SCHERMATA A
	 */
	public static AtpoFasePostPeritale objectToAtpoFasePostPeritaleA(Object[] o) {
		AtpoFasePostPeritale pp= new AtpoFasePostPeritale();
		int i=0;
		if(o[i]!=null){
			pp.setIdPostPeritale((Long)o[i]);
		}i++;
		if(o[i]!=null){
			pp.setDataDepositoDecOmologa((Date)o[i]);
		}i++;
		if(o[i]!=null){
			pp.setDataNotificaDecOmologa((Date)o[i]);
		}i++;
		if(o[i]!=null){
			pp.setDataProtDecOmologaNotif((Date)o[i]);
		}i++;
		if(o[i]!=null){
			pp.setIntTempNotifOmgProtOmg((Long)o[i]);
		}i++;
		if(o[i]!=null){
			pp.setCodChiusuraCorretto((String)o[i]);
		}i++;
		if(o[i]!=null){
			pp.setCodChiusuraInserito((String)o[i]);
		}i++;
		if(o[i]!=null){
			BigDecimal bd= (BigDecimal)o[i];
			pp.setSpesePagate(bd.doubleValue());
		}i++;
		if(o[i]!=null){
			BigDecimal bd= (BigDecimal)o[i];
			pp.setSpeseDecrOmologa(bd.doubleValue());
		}i++;
		if(o[i]!=null){
			pp.setCorrispDecrOmgEctuDef((String)o[i]);
		}i++;
		if(o[i]!=null){
			pp.setCodPagamentoSpeseLegali((String)o[i]);
		}i++;
		if(o[i]!=null){
			pp.setCodPagamentoSpeseLegaliCorretto((String)o[i]);
		}i++;
		if(o[i]!=null){
			pp.setRecDatiPratica((String)o[i]);
		}i++;
		if(o[i]!=null){
			pp.setDataTrasmissDecrLPS((Date)o[i]);
		}i++;
		if(o[i]!=null){
			pp.setOmologaAllegata((String)o[i]);
		}i++;
		if(o[i]!=null){
			pp.setIntTempNotifDecrOmgAdecrLps((Long)o[i]);
		}i++;
		if(o[i]!=null){
			pp.setDataDepDiss((Date)o[i]);
		}i++;
		if(o[i]!=null){
			pp.setIntTempDepDiss((Long)o[i]);
		}i++;
		if(o[i]!=null){
			pp.setComDepDissUffLegale((String)o[i]);
		}i++;
		if(o[i]!=null){
			pp.setDataDepRicPrimoG((Date)o[i]);
		}i++;
		if(o[i]!=null){
			pp.setDataDefPratica((Date)o[i]);
		}i++;
		if(o[i]!=null){
			pp.setTipoDissenso((String)o[i]);
		}i++;
		if(o[i]!=null){
			pp.setIdfaseDati((Long)o[i]);
		}i++;
		
		if(o[i]!=null){
			pp.setFasePronta((String)o[i]);
		}i++;
		
		return pp;
	}

	public static AtpoPraticheSISCO objectToAtpoPSisco(Object[] o) {
		AtpoPraticheSISCO pp= new AtpoPraticheSISCO();
		int i=0;
		if(o[i]!=null){
			pp.setIdPraticheSisco((Long)o[i]);
		}i++;
		if(o[i]!=null){
			pp.setTDissenso((Date)o[i]);
		}i++;
		return pp;
	}

	/*
	 * ESECUZIONE PROVVEDIMENTI MASCHERA A
	 */

	public static AtpoFaseEsecuzioneProvvedimenti objectToAtpoFaseEsecuzioneProvvedimenti(Object[] o) {
		AtpoFaseEsecuzioneProvvedimenti pp= new AtpoFaseEsecuzioneProvvedimenti();
		int i=0;
		try{
			if(o[i]!=null){
				pp.setIdEsecuzioneProvvedimenti((Long)o[i]);
			}i++;
			if(o[i]!=null){
				pp.setPresDecrOmgFasc((String)o[i]);
			}i++;
			if(o[i]!=null){
				pp.setDataDecrLiqCtu((Date)o[i]);
			}i++;
			if(o[i]!=null){
				pp.setDataPresaInCaricoDecrOmgLps((Date)o[i]);
			}i++;
			if(o[i]!=null){
				pp.setIntDepDecrOmgDRecLiquid((Long)o[i]);
			}i++;
			if(o[i]!=null){
				pp.setDataLiqPrestLps((Date)o[i]);
			}i++;
			if(o[i]!=null){
				BigDecimal bd= (BigDecimal)o[i];
				pp.setImportoRataMensile(bd.doubleValue());
			}i++;
			if(o[i]!=null){
				pp.setRecDatiLiq((Date)o[i]);
			}i++;
			if(o[i]!=null){
				pp.setGgNotifDecrOmgLiqPres((Long)o[i]);
			}i++;
			if(o[i]!=null){
				pp.setGgTrasmDecrLpsDecrOmglps((Long)o[i]);
			}i++;
			if(o[i]!=null){
				BigDecimal bd= (BigDecimal)o[i];
				pp.setInteressiLegaliPagati(bd.doubleValue());
			}i++;
			if(o[i]!=null){
				BigDecimal bd= (BigDecimal)o[i];
				pp.setInteressiLegaliDovuti(bd.doubleValue());
			}i++;
			if(o[i]!=null){
				pp.setDataDecCalcoloIntLegali((Date)o[i]);
			}i++;
			if(o[i]!=null){
				pp.setDataDecPrestInserita((Date)o[i]);
			}i++;
			if(o[i]!=null){
				pp.setDataCorrDecPrestazione((Date)o[i]);
			}i++;
			if(o[i]!=null){
				pp.setPrestCorrisp((String)o[i]);
			}i++;
			if(o[i]!=null){
				BigDecimal bd= (BigDecimal)o[i];
				pp.setImportoRataDovuta(bd.doubleValue());
			}i++;
			if(o[i]!=null){
				pp.setCondannaPagCtuAtpo((String)o[i]);
			}i++;
			if(o[i]!=null){
				pp.setDataFattura((Date)o[i]);
			}i++;
			if(o[i]!=null){
				pp.setDataLiqCtuAtpo((Date)o[i]);
			}i++;
			if(o[i]!=null){
				pp.setIntTempFattElettrpagCtuAtpo((Long)o[i]);
			}i++;
			if(o[i]!=null){
				pp.setAntSpeseCtu((String)o[i]);
			}i++;
			if(o[i]!=null){
				BigDecimal bd= (BigDecimal)o[i];
				pp.setImpSpeseCtuPagate(bd.doubleValue());
			}i++;
			if(o[i]!=null){
				BigDecimal bd= (BigDecimal)o[i];
				pp.setImpSpeseCtuDovute(bd.doubleValue());
			}i++;
			if(o[i]!=null){
				pp.setDatalettInvPagSpeseLegali((Date)o[i]);
			}i++;
			if(o[i]!=null){
				pp.setDataLetteraRecuperoSpeseCtu((Date)o[i]);
			}i++;
			if(o[i]!=null){
				pp.setCondannaPagSpeseLegali((String)o[i]);
			}i++;
			if(o[i]!=null){
				pp.setSoggRichPagamento((String)o[i]);
			}i++;
			if(o[i]!=null){
				pp.setDataArriviNotula((Date)o[i]);
			}i++;
			if(o[i]!=null){
				pp.setDataPagSpseLegaliAvvCparte((Date)o[i]);
			}i++;
			if(o[i]!=null){
				pp.setIntTdepDecromgPagSpeseL((Long)o[i]);
			}i++;
			if(o[i]!=null){
				pp.setPresDecrSentMancPagPrest((String)o[i]);
			}i++;
			if(o[i]!=null){
				BigDecimal bd= (BigDecimal)o[i];
				pp.setCostoGiudizioMancPagPrest(bd.doubleValue());
			}i++;
			if(o[i]!=null){
				pp.setCondannaPagCtu1g((String)o[i]);
			}i++;
			if(o[i]!=null){
				pp.setVerPagCtuEff((String)o[i]);
			}i++;
			//PRECETTO
			if(o[i]!=null){
				pp.setNoPrecetto((String)o[i]);
			}i++;
			if(o[i]!=null){
				pp.setSpeseLegaliFlagPrec((String)o[i]);
			}i++;
			if(o[i]!=null){
				pp.setDataSpeseLegaliPrec((Date)o[i]);
			}i++;
			if(o[i]!=null){
				pp.setDataComPreSl((Date)o[i]);
			}i++;
			if(o[i]!=null){
				BigDecimal bd= (BigDecimal)o[i];
				pp.setCostoPreSl(bd.doubleValue());
			}i++;
			if(o[i]!=null){
				pp.setSpeseCtuFlagPrec((String)o[i]);
			}i++;
			if(o[i]!=null){
				pp.setDataSpeseCtuPrec((Date)o[i]);
			}i++;
			if(o[i]!=null){
				pp.setDataComPreSctu((Date)o[i]);
			}i++;
			if(o[i]!=null){
				BigDecimal bd= (BigDecimal)o[i];
				pp.setCostoPreSctu(bd.doubleValue());
			}i++;
			if(o[i]!=null){
				pp.setPrestazioneFlagPrec((String)o[i]);
			}i++;
			if(o[i]!=null){
				pp.setDataPrestazione((Date)o[i]);
			}i++;
			if(o[i]!=null){
				pp.setDataComPrePrest((Date)o[i]);
			}i++;
			if(o[i]!=null){
				BigDecimal bd= (BigDecimal)o[i];
				pp.setCostoPrePrest(bd.doubleValue());
			}i++;
			if(o[i]!=null){
				pp.setNoPignoramento((String)o[i]);
			}i++;
			if(o[i]!=null){
				pp.setSpeseLegaliFlagPign((String)o[i]);
			}i++;
			if(o[i]!=null){
				pp.setDataSpeseLegaliPign((Date)o[i]);
			}i++;
			if(o[i]!=null){
				BigDecimal bd= (BigDecimal)o[i];
				pp.setCostoPignSl(bd.doubleValue());
			}i++;
			if(o[i]!=null){
				pp.setSpeseCtuFlagPign((String)o[i]);
			}i++;
			if(o[i]!=null){
				pp.setDataSpeseCtuPign((Date)o[i]);
			}i++;
			if(o[i]!=null){
				BigDecimal bd= (BigDecimal)o[i];
				pp.setCostoPignSctu(bd.doubleValue());
			}i++;
			if(o[i]!=null){
				pp.setPrestazioneFlagPign((String)o[i]);
			}i++;
			if(o[i]!=null){
				pp.setDataPignoramentoPres((Date)o[i]);
			}i++;
			if(o[i]!=null){
				BigDecimal bd= (BigDecimal)o[i];
				pp.setCostoPignPrest(bd.doubleValue());
			}i++;
			if(o[i]!=null){
				
				pp.setDataLimiteCalcImpatto((Date)o[i]);
			}i++;
			return pp;

		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	public static AtpoRiepilogoFascicolo objectToAtpoRiepFascicolo(Object[] o) {
		AtpoRiepilogoFascicolo pp= new AtpoRiepilogoFascicolo();
		int i=0;
		try{
			if(o[i]!=null){
				pp.setIdRiepilogoFascicolo((Long)o[i]);
			}i++;
			if(o[i]!=null){
				pp.setFascicoloElettronico((String)o[i]);
			}i++;
			
			if(o[i]!=null){
				pp.setDettDocMancante((String)o[i]);
			}i++;

			return pp;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}



	public static AtpoDettaglioFascicoloDto objectToDettMancDto(Object[] o) {
		AtpoDettaglioFascicoloDto pp= new AtpoDettaglioFascicoloDto();
		int i=0;
		try{
			if(o[i]!=null){
				pp.setIdDettFasc((Long)o[i]);
			}i++;
			if(o[i]!=null){
				pp.setIdRiepilogoFascicolo((Long)o[i]);
			}i++;
			if(o[i]!=null){
				pp.setCodifica((String)o[i]);
			}i++;
			if(o[i]!=null){
				pp.setDescrizione((String)o[i]);
			}i++;

			return pp;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	public static AtpoDettaglioFascicolo objectToDettManc(Object[] o) {
		AtpoDettaglioFascicolo pp= new AtpoDettaglioFascicolo();
		int i=0;
		try{
			if(o[i]!=null){
				pp.setIdDettFasc((Long)o[i]);
			}i++;
			if(o[i]!=null){
				pp.setIdRiepilogoFascicolo((Long)o[i]);
			}i++;
			if(o[i]!=null){
				pp.setCodifica((String)o[i]);
			}i++;

			return pp;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	public static AtpoAuMContestoDto objectToDatiContestoDto(Object[] o) {
		AtpoAuMContestoDto pp= new AtpoAuMContestoDto();
		int i=0;
		try{
			if(o[i]!=null){
				pp.setIdMContesto((Long)o[i]);
			}i++;
			if(o[i]!=null){
				pp.setFunzionari((String)o[i]);
			}i++;
			if(o[i]!=null){
				pp.setMediciInps((String)o[i]);
			}i++;
			if(o[i]!=null){
				pp.setAperte((String)o[i]);
			}i++;
			if(o[i]!=null){
				pp.setGgMediApertura((Long)o[i]);
			}i++;
			if(o[i]!=null){
				pp.setGgMediChiusura((Long)o[i]);
			}i++;
			if(o[i]!=null){
				pp.setAbbandonoDom((String)o[i]);
			}i++;
			if(o[i]!=null){
				pp.setAbbandonoRev((String)o[i]);
			}i++;
			if(o[i]!=null){
				pp.setAcqErrDom((String)o[i]);
			}i++;
			if(o[i]!=null){
				pp.setAcqErrRev((String)o[i]);
			}i++;
			if(o[i]!=null){
				pp.setCessMatContendDom((String)o[i]);
			}i++;
			if(o[i]!=null){
				pp.setCessMatContendRev((String)o[i]);
			}i++;
			if(o[i]!=null){
				pp.setDefAutAtpDom((String)o[i]);
			}i++;
			if(o[i]!=null){
				pp.setDefAutAtpRev((String)o[i]);
			}i++;
			if(o[i]!=null){
				pp.setDissensoDom((String)o[i]);
			}i++;
			if(o[i]!=null){
				pp.setDissensoRev((String)o[i]);
			}i++;
			if(o[i]!=null){
				pp.setEstintaDom((String)o[i]);
			}i++;
			if(o[i]!=null){
				pp.setEstintaRev((String)o[i]);
			}i++;
			if(o[i]!=null){
				pp.setFavDom((String)o[i]);
			}i++;
			if(o[i]!=null){
				pp.setFavRev((String)o[i]);
			}i++;
			if(o[i]!=null){
				pp.setInammissDom((String)o[i]);
			}i++;
			if(o[i]!=null){
				pp.setInammissRev((String)o[i]);
			}i++;
			if(o[i]!=null){
				pp.setIncompDom((String)o[i]);
			}i++;
			if(o[i]!=null){
				pp.setIncompRev((String)o[i]);
			}i++;
			if(o[i]!=null){
				pp.setParzFavDom((String)o[i]);
			}i++;
			if(o[i]!=null){
				pp.setParzFavRev((String)o[i]);
			}i++;
			if(o[i]!=null){
				pp.setSfavDom((String)o[i]);
			}i++;
			if(o[i]!=null){
				pp.setSfavRev((String)o[i]);
			}i++;
			if(o[i]!=null){
				pp.setTrasfAltroSettDom((String)o[i]);
			}i++;
			if(o[i]!=null){
				pp.setTrasfAltroSettRev((String)o[i]);
			}i++;
			if(o[i]!=null){
				pp.setDissensoInps((String)o[i]);
			}i++;
			if(o[i]!=null){
				pp.setCtu((String)o[i]);
			}i++;
			if(o[i]!=null){
				pp.setVisitePeritali((String)o[i]);
			}i++;
			if(o[i]!=null){
				BigDecimal bd= (BigDecimal)o[i];
				pp.setPercCvpSuNrCtu(bd.doubleValue());
			}i++;
			if(o[i]!=null){
				BigDecimal bd= (BigDecimal)o[i];
				pp.setPercCvpSuNrMeidici(bd.doubleValue());
			}i++;
			if(o[i]!=null){
				BigDecimal bd= (BigDecimal)o[i];
				pp.setPercParereConcorde(bd.doubleValue());
			}i++;
			if(o[i]!=null){
			
				pp.setDataInizioOsservazione((Date)o[i]);
			}i++;
			if(o[i]!=null){
				
				pp.setDataFineOsservazione((Date)o[i]);
			}i++;

			return pp;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}



}