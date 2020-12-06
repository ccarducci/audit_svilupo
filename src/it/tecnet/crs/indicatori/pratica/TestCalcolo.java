package it.tecnet.crs.indicatori.pratica;


public class TestCalcolo {
	/*
	public static void main(String[] args){
		System.out.println("Inizio");
		AuCampagna campagna = new AuCampagna();
		AuSPratica pratica = new AuSPratica(); 
		List<AuMRischio> rischio = new ArrayList<AuMRischio>();
		AtpoFasePostPeritale praticaPp = new AtpoFasePostPeritale();
		AtpoFaseDati praticaFd = new AtpoFaseDati();
		AtpoFaseEsecuzioneProvvedimenti praticaEp = new AtpoFaseEsecuzioneProvvedimenti();
		AtpoFasePeritale praticaPe = new AtpoFasePeritale();
		AtpoFaseGestioneIstruttoria praticaGi = new AtpoFaseGestioneIstruttoria(); 
		AtpoFaseAutotutelaResistenzaGiudizio praticaAr = new AtpoFaseAutotutelaResistenzaGiudizio();
		AtpoFaseAcquisizioneIstanza praticaAi = new AtpoFaseAcquisizioneIstanza();
		List<AuSPraticaRis> listaPraticheRisToInsert = new ArrayList<AuSPraticaRis>();
		List<AuSPraticaVarcomp> listaPraticheVarCompToInsert = new ArrayList<AuSPraticaVarcomp>();
		List<AuMVarcomp> auMVarcomp = new  ArrayList<AuMVarcomp>();
		List<CalcoloIndicatoriErrore> listaErrori = new ArrayList<CalcoloIndicatoriErrore>();
		long idSSessione = 1l;
		long idSPratica = 1l;
		AtpoPraticheSISCO praticaCisco = new  AtpoPraticheSISCO();	
		
		populate(	campagna, 
					pratica, 
					rischio, 
					praticaPp, 
					praticaFd, 
					praticaEp, 
					praticaPe, 
					praticaGi, 
					praticaAr, 
					praticaAi, 
					listaPraticheRisToInsert, 
					listaPraticheVarCompToInsert, 
					auMVarcomp, 
					listaErrori, 
					idSSessione, 
					idSPratica, 
					praticaCisco);
		
		CalcoloIndicatoriPratica cal = new CalcoloIndicatoriPratica(campagna, pratica, rischio, praticaPp, praticaFd, praticaEp, praticaPe, praticaGi, praticaAr, praticaAi, listaPraticheRisToInsert, listaPraticheVarCompToInsert, auMVarcomp, listaErrori, idSPratica, idSPratica, praticaCisco, null);
		
		try {
			cal.calcolaIndicatori();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Fine");
	}

	
	private static void populate(AuCampagna campagna2, AuSPratica pratica2,
			List<AuMRischio> rischio2, AtpoFasePostPeritale praticaPp2,
			AtpoFaseDati praticaFd2,
			AtpoFaseEsecuzioneProvvedimenti praticaEp2,
			AtpoFasePeritale praticaPe2,
			AtpoFaseGestioneIstruttoria praticaGi2,
			AtpoFaseAutotutelaResistenzaGiudizio praticaAr2,
			AtpoFaseAcquisizioneIstanza praticaAi2,
			List<AuSPraticaRis> listaPraticheRisToInsert2,
			List<AuSPraticaVarcomp> listaPraticheVarCompToInsert2,
			List<AuMVarcomp> auMVarcomp2,
			List<CalcoloIndicatoriErrore> listaErrori2, long idSPratica,
			long idSPratica2, AtpoPraticheSISCO praticaCisco2) {
		popolaAnagrafiche(rischio2, auMVarcomp2);
		long idSessione = 1l;
		long idPratica = 1l;
		idSPratica = 1l;
		long idfaseDati = 1;
		long idPostPeritale = 1;
		String codSede = "SEDE1";
		String fascicolo = "fasc1";
		// POPOLAMENTO CAMPAGNA
		campagna2.setDataFine(new Date());
		campagna2.setDataFineOsservazione(new Date());
		campagna2.setDataInizioOsservazione(new Date());
		campagna2.setIdAudit(1);
		campagna2.setIdCampagna(1);
		campagna2.setNome("Test");
		campagna2.setStato("A");
		// POPOLAMENTO PRATICA
		pratica2.setEsamePratica("A");
		pratica2.setDataFine(new Date());
		pratica2.setDataInizio(new Date());
		pratica2.setIdPratica(idPratica);
		pratica2.setIdSPratica(idSPratica);
		pratica2.setIdSSessione(idSessione);
		pratica2.setImporto(10.13d);
		pratica2.setIntTmpDatanDataas(4);
		pratica2.setIntTmpDatanDatap(2);
		// AtpoFasePostPeritale
		praticaPp2.setCodChiusuraCorretto("K2");
		praticaPp2.setCodChiusuraInserito("K3");
		praticaPp2.setCodPagamentoSpeseLegali("C1");
		praticaPp2.setCodPagamentoSpeseLegaliCorretto("C2");
		praticaPp2.setComDepDissUffLegale("BOOH");
		praticaPp2.setCorrispDecrOmgEctuDef("BOOH");
		praticaPp2.setDataDepositoDecOmologa(new Date());
		praticaPp2.setDataNotificaDecOmologa(new Date());
		praticaPp2.setDataProtDecOmologaNotif(new Date());
		praticaPp2.setDataTrasmissDecrLPS(new Date());
		praticaPp2.setFasePronta("S");
		praticaPp2.setIdfaseDati(idfaseDati);
		praticaPp2.setIdPostPeritale(idPostPeritale);
		praticaPp2.setIntTempNotifDecrOmgAdecrLps(3);
		praticaPp2.setIntTempNotifOmgProtOmg(5);
		praticaPp2.setOmologaAllegata("S");
		praticaPp2.setRecDatiPratica("SI");
		praticaPp2.setSpeseDecrOmologa(100D);
		praticaPp2.setSpesePagate(100D);
		// AtpoFaseDati
		praticaFd2.setCodSede(codSede);
		praticaFd2.setFascicolo(fascicolo);
		praticaFd2.setFasePronta("S");
		praticaFd2.setGiudizio("BOOH");
		praticaFd2.setIdfaseDati(idfaseDati);
		praticaFd2.setImportoPrestazioneErogata(new BigDecimal(100));
		praticaFd2.setImportoSpeseCTU(new BigDecimal(100));
		praticaFd2.setImportoSpeseLegali(new BigDecimal(100));
		praticaFd2.setPrestazioneEconomica("BOOH");
		// AtpoFaseEsecuzioneProvvedimenti 
		praticaEp2.setIdEsecuzioneProvvedimenti(1) ;
		praticaEp2.setPresDecrOmgFasc("BOOH") ;
		praticaEp2.setDataDecrLiqCtu(new Date()) ;
		praticaEp2.setDataPresaInCaricoDecrOmgLps(new Date()) ;
		praticaEp2.setDataLiqPrestLps(new Date()) ;
		praticaEp2.setImportoRataMensile(100.0) ;
		praticaEp2.setRecDatiLiq(new Date()) ;
		praticaEp2.setGgNotifDecrOmgLiqPres(3) ;
		praticaEp2.setGgTrasmDecrLpsDecrOmglps(5) ;
		praticaEp2.setInteressiLegaliPagati(100.0) ;
		praticaEp2.setInteressiLegaliDovuti(100.0) ;
		praticaEp2.setDataDecCalcoloIntLegali(new Date()) ;
		praticaEp2.setDataDecPrestInserita(new Date()) ;
		praticaEp2.setDataCorrDecPrestazione(new Date()) ;
		praticaEp2.setPrestCorrisp("BOOH") ;
		praticaEp2.setImportoRataDovuta(100.0) ;
		praticaEp2.setCondannaPagCtuAtpo("BOOH") ;
		praticaEp2.setDataFattura(new Date()) ;
		praticaEp2.setDataLiqCtuAtpo(new Date()) ;
		praticaEp2.setIntTempFattElettrpagCtuAtpo(4) ;
		praticaEp2.setAntSpeseCtu("BOOH") ;
		praticaEp2.setImpSpeseCtuPagate(100.0) ;
		praticaEp2.setImpSpeseCtuDovute(100.0) ;
		praticaEp2.setDatalettInvPagSpeseLegali(new Date()) ;
		praticaEp2.setDataLetteraRecuperoSpeseCtu(new Date()) ;
		praticaEp2.setCondannaPagSpeseLegali("BOOH") ;
		praticaEp2.setSoggRichPagamento("BOOH") ;
		praticaEp2.setDataArriviNotula(new Date()) ;
		praticaEp2.setDataPagSpseLegaliAvvCparte(new Date()) ;
		praticaEp2.setIntTdepDecromgPagSpeseL(1);
		praticaEp2.setPresDecrSentMancPagPrest("BOOH") ;
		praticaEp2.setCostoGiudizioMancPagPrest(100.0);
		praticaEp2.setIdFaseDati(idfaseDati) ;
		praticaEp2.setImpatto(10D) ;
		praticaEp2.setCondannaPagCtu1g("BOH") ;
		praticaEp2.setVerPagCtuEff("BOH") ;
		praticaEp2.setNoPrecetto("BOH") ;
		praticaEp2.setDataComPreSl(new Date()) ;
		praticaEp2.setCostoPreSl(10) ;
		praticaEp2.setDataComPreSctu(new Date()) ;
		praticaEp2.setCostoPreSctu(10D) ;
		praticaEp2.setDataPrestazione(new Date()) ;
		praticaEp2.setDataComPrePrest(new Date()) ;
		praticaEp2.setCostoPrePrest(100D) ;
		praticaEp2.setNoPignoramento("BOH") ;
		praticaEp2.setCostoPignSl(10D) ;
		praticaEp2.setCostoPignSctu(10D) ;
		praticaEp2.setCostoPignPrest(10D) ;
		praticaEp2.setDataPignoramentoPres(new Date()) ;
		praticaEp2.setSpeseLegaliFlagPrec("BOH") ;
		praticaEp2.setDataSpeseLegaliPrec(new Date()) ;
		praticaEp2.setSpeseVtuFlagPrec("BOH") ;
		praticaEp2.setDataSpeseCtuPrec(new Date()) ;
		praticaEp2.setPrestazioneFlagPrec("BOH") ;
		praticaEp2.setSpeseLegaliFlagPign("BOH") ;
		praticaEp2.setDataSpeseLegaliPign(new Date()) ;
		praticaEp2.setSpeseVtuFlagPign("BOH") ;
		praticaEp2.setDataSpeseCtuPign(new Date()) ;
		praticaEp2.setPrestazioneFlagPign("BOH") ;
		praticaEp2.setFasePronta("S") ;
		// AtpoFasePeritale 
		praticaPe2.setIdPeritale(1) ;
		praticaPe2.setDataComOpPerCTU(new Date()) ;
		praticaPe2.setRecInfoOpPeritali("BOOH") ;
		praticaPe2.setAssCTUMedicoInps("BOOH") ;
		praticaPe2.setCtpINPSopPeritali("BOOH") ;
		praticaPe2.setDataArrBozza(new Date()) ;
		praticaPe2.setDataProtBozza(new Date()) ;
		praticaPe2.setCtuBozzaImgAtti("BOOH") ;
		praticaPe2.setParereBozzaCtu("BOOH") ;
		praticaPe2.setOssBozza("BOOH") ;
		praticaPe2.setDataComDepCTUDefCanc(new Date());
		praticaPe2.setDataProtCTUDef(new Date()) ;
		praticaPe2.setCtuDefImgAtti("BOOH") ;
		praticaPe2.setIntTempComDepCTUtoCTUdef(3) ;
		praticaPe2.setDataTermDissSisco(new Date()) ;
		praticaPe2.setDataTermDissDecr(new Date()) ;
		praticaPe2.setParereDissAccetfascitazione("BOOH");
		praticaPe2.setOssParDef("BOOH") ;
		praticaPe2.setIdFaseDati(idfaseDati) ;
		praticaPe2.setTipoDissenso("BOOH") ;
		praticaPe2.setFasePronta("S") ;
		// AtpoFaseGestioneIstruttoria 
		praticaGi2.setIdGestioneIstruttoria(1L) ;
		praticaGi2.setEccezioniNonRilevabili("BOOH") ;
		praticaGi2.setLitispendenza("BOOH") ;
		praticaGi2.setDecadenza("BOOH") ;
		praticaGi2.setCorrispAtpDomAmmInv("BOOH") ;
		praticaGi2.setVerificaDicEsPagSpese("BOOH") ;
		praticaGi2.setIndeterminatezzaOggDom("BOOH") ;
		praticaGi2.setCarenzaInteresseAdAgire("BOOH") ;
		praticaGi2.setDataCostitGiudizio(new Date()) ;
		praticaGi2.setCostGiudTelematica("BOOH") ;
		praticaGi2.setDataPrimaUdienza(new Date()) ;
		praticaGi2.setIntervalloCostGiudUdienza(5) ;
		praticaGi2.setAltreEccProcessuali("BOOH") ;
		praticaGi2.setIdFaseDati(idfaseDati) ;
		praticaGi2.setFasePronta("S") ;
		// AtpoFaseAutotutelaResistenzaGiudizio 
		praticaAr2.setFasePronta("S");
		praticaAr2.setIdAutotutela(1L);
		praticaAr2.setIdFaseDati(idfaseDati);
		praticaAr2.setParere("BOOH");
		praticaAr2.setTerminiPrimaUdienza("BOOH");
		// AtpoFaseAcquisizioneIstanza 
		praticaAi2.setIdFaseAcquisizioneIstanza(1L) ;
		praticaAi2.setDataNotifica(new Date()) ;
		praticaAi2.setDataProtocollo(new Date()) ;
		praticaAi2.setVoceTitolarioErrata("BOOH") ;
		praticaAi2.setIntervalloNotificaProtocollo(1L);
		praticaAi2.setProtocolloConImg("BOOH") ;
		praticaAi2.setDataAcquisizioneSISCO(new Date()) ;
		praticaAi2.setIntervalloNotificaSISCO(1L) ;
		praticaAi2.setIdFaseDati(idfaseDati) ;
		praticaAi2.setFasePronta("S") ;
	}
	
	private static void popolaAnagrafiche(List<AuMRischio> rischio3,
			List<AuMVarcomp> auMVarcomp3) {
		
		
	}
	 */
}
