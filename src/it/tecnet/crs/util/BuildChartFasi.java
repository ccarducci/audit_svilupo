package it.tecnet.crs.util;

import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoFaseAcquisizioneIstanza;
import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoFaseAutotutelaResistenzaGiudizio;
import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoFaseEsecuzioneProvvedimenti;
import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoFaseGestioneIstruttoria;
import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoFasePeritale;
import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoFasePostPeritale;
import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoRiepilogoFascicolo;

import java.util.List;

public class BuildChartFasi {

	//LABEL
	static String labelSi= "{\"label\": \"SI\",\"backgroundColor\": \"#FFA500\",\"data\": ";
	static String labelNo= "{\"label\": \"NO\",\"backgroundColor\": \"#FF4500\",\"data\": ";
	static String labelVal=" {\"label\": \"Valorizzato\",\"backgroundColor\": \"#2E8B57\",\"data\": ";
	static String labelNoVal=" {\"label\": \"Non valorizzato\",\"backgroundColor\": \"#FF0000\",\"data\": " ;
	static String labelConcorde=" {\"label\": \"Concorde\",\"backgroundColor\": \"#2E8B57\",\"data\": ";
	static String labelNonConcorde=" {\"label\": \"Non concorde\",\"backgroundColor\": \"#663399\",\"data\": ";
	static String labelDaNonEccepire=" {\"label\": \"Da non eccepire\",\"backgroundColor\": \"#DB7093\",\"data\":";
	static String labelDaEccepENonEccep=" {\"label\": \"Da eccepire e non eccepita\",\"backgroundColor\": \"#8e5ea2\",\"data\": ";
	static String labelDaEccepEEccep=" {\"label\": \"Da eccepire ed eccepita\",\"backgroundColor\": \"#40E0D0\",\"data\": ";
	static String labelNonRilevabile=" {\"label\": \"Non rilevabile\",\"backgroundColor\": \"#6495ED\",\"data\": ";
	static String labelAssente=" {\"label\": \"Assente\",\"backgroundColor\": \"#8e5ea2\",\"data\": ";
	static String labelCogisan=" {\"label\": \"Cogisan\",\"backgroundColor\": \"#40E0D0\",\"data\": ";
	static String labelPortale=" {\"label\": \"Portale\",\"backgroundColor\": \"#6495ED\",\"data\": ";
	static String labelSisco=" {\"label\": \"Sisco\",\"backgroundColor\": \"#008080\",\"data\": ";
	static String labelSiscoPostCtu=" {\"label\": \"Sisco post CTU\",\"backgroundColor\": \"#D8BFD8\",\"data\": ";
	static String labelIstanzeConEsitoFavorevole=" {\"label\": \"Istanze esito favorevole\",\"backgroundColor\": \"#6495ED\",\"data\": " ;
	static String labelIstanzeConEsitoParzFavorevole=" {\"label\": \"Istanze esito parzialmente favorevole\",\"backgroundColor\": \"#40E0D0\",\"data\": " ;
	static String labelIstanzeConEsitoSfavorevole=" {\"label\": \"Istanze esito sfavorevole\",\"backgroundColor\": \"#008080\",\"data\": " ;
	static String labelIstanzeConEsitoDissenso=" {\"label\": \"Istanze esito dissenso\",\"backgroundColor\": \"#8e5ea2\",\"data\": " ;
	static String labelMancataCorrispMancataRichiesta=" {\"label\": \"Mancata corrispondenza e mancata richiesta di correzione\",\"backgroundColor\": \"#6495ED\",\"data\": " ;
	static String labelMancataCorrispRichiestaCorr=" {\"label\": \"Mancata corrispondenza e fatta richiesta di correzione\",\"backgroundColor\": \"#40E0D0\",\"data\": " ;
	static String labelSpeseCompExArt=" {\"label\": \" SPESE COMPENSATE ex art. 152 c.p.c\",\"backgroundColor\": \"#6495ED\",\"data\": " ;
	static String labelSpeseCompNoExArt=" {\"label\": \" SPESE COMPENSATE NO ex art. 152 c.p.c\",\"backgroundColor\": \"#40E0D0\",\"data\": " ;
	static String labelCondAltrSogg=" {\"label\": \" CONDANNA ALTRO SOGGETTO CONVENUTO\",\"backgroundColor\": \"#008080\",\"data\": " ;
	static String labelCondControP=" {\"label\": \" CONDANNA CONTROPARTE A PAGAMENTO SPESE\",\"backgroundColor\": \"#8e5ea2\",\"data\": " ;
	static String labelCodPagPa=" {\"label\": \" CONDANNA A PAGAMENTO PARZIALE\",\"backgroundColor\": \"#6495ED\",\"data\": " ;
	static String labelCondInpPagS=" {\"label\": \" CONDANNA INPS A PAGAMENTO SPESE\",\"backgroundColor\": \"#40E0D0\",\"data\": " ;
	static String labelCorretti=" {\"label\": \"Corretti\",\"backgroundColor\": \"#6495ED\",\"data\": " ;
	static String labelnonCorr=" {\"label\": \"Non corretti\",\"backgroundColor\": \"#40E0D0\",\"data\": " ;
	static String labelParzCorrtetti=" {\"label\": \"Parzialmente corretti\",\"backgroundColor\": \"#8e5ea2\",\"data\": " ;   
	static String labelAmministrativo=" {\"label\": \"Amministrativo\",\"backgroundColor\": \"#008080\",\"data\": " ;   
	static String labelSanitario=" {\"label\": \"Sanitario\",\"backgroundColor\": \"#6495ED\",\"data\": " ;   
	static String labelCompensate=" {\"label\": \" COMPENSATE \",\"backgroundColor\": \"#6495ED\",\"data\": " ;
	static String labelParzCompensate=" {\"label\": \" PARZIALMENTE COMPENSATE \",\"backgroundColor\": \"#6495ED\",\"data\": " ;
	static String labelSiConFlagPrimoGrado=" {\"label\": \"Si, con flag 1 grado\",\"backgroundColor\": \"#6495ED\",\"data\": ";
	static String labelSiConAltraModalita=" {\"label\": \"Si, con altra modalita\",\"backgroundColor\": \"#008080\",\"data\": ";
	static String labelnonNecessario=" {\"label\": \"Non necessario\",\"backgroundColor\": \"#8e5ea2\",\"data\": " ;   
	static String labelAvvDistrattario=" {\"label\": \"avv.to distrattario\",\"backgroundColor\": \"#008080\",\"data\": " ;
	static String labelAvvNoDistrattarioNoDelega=" {\"label\": \"avv.to no distrattario no delega\",\"backgroundColor\": \"#8e5ea2\",\"data\": " ;
	static String labelAvvNoDistrattarioConDelega=" {\"label\": \"avv.to no distrattario con delega\",\"backgroundColor\": \"#6495ED\",\"data\": " ;
	static String labelFascicolo=" {\"label\": \"fascicolo\",\"backgroundColor\": \"#6495ED\",\"data\": " ;
	
	static String labelSiCompleto=" {\"label\": \"Si completo\",\"backgroundColor\": \"#2E8B57\",\"data\": " ;
	static String labelSiIncompleto=" {\"label\": \"si incompleto\",\"backgroundColor\": \"#6495ED\",\"data\": " ;
	static String labelInesistente=" {\"label\": \"inesistente\",\"backgroundColor\": \"#40E0D0\",\"data\": " ;
	static String labelNonDefinito=" {\"label\": \"non definito\",\"backgroundColor\": \"#FF0000\",\"data\": " ;


	//COSTRUISCE GRAFICO AUTOTUTELA RESISTENZA GIUDIZIO
	public static String buildChartAutotutelaResGiudizio(List<Object> list) {
		String toReturn="";
		//parere
		int mancaParere=0;
		int siAutotutela=0;
		int siResistenza=0;

		//termini prima udienza
		int tardivaAttivazione=0;
		int mancataAutorizz=0;
		int mancataRedaz=0;
		int defTermini=0;

		int tot=list.size();
		for(Object o: list){
			try{
				AtpoFaseAutotutelaResistenzaGiudizio ai = (AtpoFaseAutotutelaResistenzaGiudizio) o;
				if(Integer.parseInt(ai.getParere().trim())==1){
					mancaParere++;
				}
				if(Integer.parseInt(ai.getParere().trim())==2){
					siAutotutela++;
					if(Integer.parseInt(ai.getTerminiPrimaUdienza().trim())==1){
						tardivaAttivazione++;
					}
					if(Integer.parseInt(ai.getTerminiPrimaUdienza().trim())==2){
						mancataAutorizz++;
					}
					if(Integer.parseInt(ai.getTerminiPrimaUdienza().trim())==3){
						mancataRedaz++;
					}
					if(Integer.parseInt(ai.getTerminiPrimaUdienza().trim())==4){
						defTermini++;
					}
				}
				if(Integer.parseInt(ai.getParere().trim())==3){
					siResistenza++;
				}

			}catch(Exception e){
				e.printStackTrace();
			}
		}

		toReturn="{\"labels\": [\"Parere\", \"Termini prima udienza\"], " +
		"\"datasets\": [{\"label\": \"Manca parere\",\"backgroundColor\": \"#FFA500\",\"data\": " +
		"						["+mancaParere+","+0+ "]}," +
		" {\"label\": \"Si, autotutela\",\"backgroundColor\": \"#FF4500\",\"data\": ["+siAutotutela+","+0+"]}," +
		" {\"label\": \"Si, resistenza in giudizio\",\"backgroundColor\": \"#DB7093\",\"data\": ["+siResistenza+","+0+"]}," +
		" {\"label\": \"No, per tardiva attivazione procedimento autotutela\",\"backgroundColor\": \"#8e5ea2\",\"data\": ["+0+","+tardivaAttivazione+"]}," +
		" {\"label\": \"No, per mancata autorizzazione nei termini del CMS\",\"backgroundColor\": \"#40E0D0\",\"data\": ["+0+","+mancataAutorizz+"]}," +
		" {\"label\": \"No, per mancata redazione del verbale definitivo del CML\",\"backgroundColor\": \"#FF6347\",\"data\": ["+0+","+mancataRedaz+"]}," +
		" {\"label\": \"Definizione nei termini\",\"backgroundColor\": \"#FF6347\",\"data\": ["+0+","+defTermini+"]}" +
		"]}";

		return toReturn;
	}


	//COSTRUISCE GRAFICO ACQUISIZIONE ISTANZA
	public static String buildChartAcquisizioneIstanza(List<Object> list) {
		String toReturn="";
		int dataProtocollo=0;
		int dataNotifica=0;
		int voceTitolarioCorrettaErr=0;
		int protocolloImgSiNo=0;
		int dataAcquisizioneSisco=0;


		int tot=list.size();
		for(Object o: list){
			try{
				AtpoFaseAcquisizioneIstanza ai = (AtpoFaseAcquisizioneIstanza) o;
				if(ai.getDataProtocollo()!=null){
					dataProtocollo++;
				}
				if(ai.getDataNotifica()!=null){
					dataNotifica++;
				}
				if(ai.getVoceTitolarioErrata()!=null && !ai.getVoceTitolarioErrata().trim().equals("") && ai.getVoceTitolarioErrata().trim().length()>0 && Integer.parseInt(ai.getVoceTitolarioErrata().trim())==0){
					voceTitolarioCorrettaErr++;
				}
				if(ai.getProtocolloConImg()!=null && ai.getProtocolloConImg().trim().equalsIgnoreCase("s")){
					protocolloImgSiNo++;
				}
				if(ai.getDataAcquisizioneSISCO()!=null){
					dataAcquisizioneSisco++;
				}


			}catch(Exception e){
				e.printStackTrace();
			}
		}
		toReturn="{\"labels\": [\"Data protocollo\", \"Data notifica\", \"Data acquisizione in  SISCO\", \"Protocollo con img\",\"Voce titolario\"], \"datasets\":[ " +
		labelVal +
		"["+dataProtocollo+","+dataNotifica+","+dataAcquisizioneSisco+","+0+","+0+ "]}," +
		labelNoVal +
		"["+(tot-dataProtocollo)+","+ (tot-dataNotifica)+","+(tot-dataAcquisizioneSisco)+","+0+","+0+"]}," +
		labelSi +
		"["+0+","+0+","+0+","+protocolloImgSiNo+","+0+"]}," +
		labelNo +
		"["+0+","+0+","+0+","+(tot-protocolloImgSiNo)+","+0+"]}," +
		labelCorretti +
		" ["+0+","+0+","+0+","+0+","+voceTitolarioCorrettaErr+"]}," +
		labelnonCorr +
		"["+0+","+0+","+0+","+0+","+(tot-voceTitolarioCorrettaErr)+"]}" +
		"]}";

		return toReturn;
	}


	public static String buildChartGestioneIstruttoria(List<Object> list) {
		String toReturn="";
		//Atto di costituzione rintracciabile
		int attoCostNonRintracciabileSI=0;
		int attoCostNonRintracciabileNo=0;
		//litispedenza
		int litispedenzaDaNonEccep=0;
		int litispedenzaDaEccepENonEccep=0;
		int litispedenzaDaEccepEEccep=0;
		int litispedenzaNonRilev=0;
		//decadenza
		int decadenzaDaNonEccep=0;
		int decadenzaDaEccepENonEccep=0;
		int decadenzaDaEccepEEccep=0;
		int decadenzaNonRilev=0;
		//Corrispondenza tra istanza ATP e domanda amministrativa di invalidità
		int atpSiCorrisp=0;
		int atpNoCorrispNoEccep=0;
		int atpNoCorrispEccep=0;
		int atpNoNonRilev=0;
		//Verifica correttezza dichiarazione di esenzione pagamento spese
		int esenzPagSDaNonEccep=0;
		int esenzPagSDaEccepENonEccep=0;
		int esenzPagSDaEccepEEccep=0;
		int esenzPagSNonRilev=0;
		int esenzPagSNonPres=0;
		//Indeterminatezza dell'oggetto della domanda
		int oggDomDaNonEccep=0;
		int oggDomDaEccepENonEccep=0;
		int oggDomDaEccepEEccep=0;
		int oggDomNonRilev=0;
		//Carenza interesse ad agire (art. 100 c.p.c.)
		int carIntAgDaNonEccep=0;
		int  carIntAgDaEccepENonEccep=0;
		int  carIntAgDaEccepEEccep=0;
		int  carIntAgNonRilev=0;
		//Data di costituzione in giudizio
		int dataCostGiudizioValorizzata=0;
		int dataCostGiudizioNonValorizzata=0;
		//Costituzione in giudizio telematica
		int costGiudTelematicaSI=0;
		int costGiudTelematicaNo=0;
		//Data 1^ udienza
		int dataPrimaUdienzValorizzata=0;
		int dataPrimaUdienzNonValorizzata=0;
		//Altre eccezioni processuali
		int eccezProcDaNonEccep=0;
		int  eccezProcDaEccepENonEccep=0;
		int  eccezProcDaEccepEEccep=0;
		int  eccezProcNonRilev=0;

		int tot=list.size();
		for(Object o: list){
			try{
				AtpoFaseGestioneIstruttoria ai = (AtpoFaseGestioneIstruttoria) o;

				if(ai.getEccezioniNonRilevabili().trim().equalsIgnoreCase("s")){
					attoCostNonRintracciabileSI++;
				}else{
					attoCostNonRintracciabileNo++;
				}
				//LITISPEDENZA
				if(Integer.parseInt(ai.getLitispendenza().trim())==1){
					litispedenzaDaNonEccep++;
				}
				if(Integer.parseInt(ai.getLitispendenza().trim())==2){
					litispedenzaDaEccepENonEccep++;
				}
				if(Integer.parseInt(ai.getLitispendenza().trim())==3){
					litispedenzaDaEccepEEccep++;
				}
				if(Integer.parseInt(ai.getLitispendenza().trim())==4){
					litispedenzaNonRilev++;
				}
				//DECADENZA
				if(Integer.parseInt(ai.getDecadenza().trim())==1){
					decadenzaDaNonEccep++;
				}
				if(Integer.parseInt(ai.getDecadenza().trim())==2){
					decadenzaDaEccepENonEccep++;
				}
				if(Integer.parseInt(ai.getDecadenza().trim())==3){
					decadenzaDaEccepEEccep++;
				}
				if(Integer.parseInt(ai.getDecadenza().trim())==4){
					decadenzaNonRilev++;
				}
				//Corrispondenza tra istanza ATP e domanda amministrativa di invalidità
				if(Integer.parseInt(ai.getCorrispAtpDomAmmInv().trim())==1){
					atpSiCorrisp++;

				}
				if(Integer.parseInt(ai.getCorrispAtpDomAmmInv().trim())==2){
					atpNoCorrispNoEccep++;
				}
				if(Integer.parseInt(ai.getCorrispAtpDomAmmInv().trim())==3){
					atpNoCorrispEccep++;
				}
				if(Integer.parseInt(ai.getCorrispAtpDomAmmInv().trim())==4){
					atpNoNonRilev++;
				}

				//Verifica correttezza dichiarazione di esenzione pagamento spese
				if(Integer.parseInt(ai.getVerificaDicEsPagSpese().trim())==1){
					esenzPagSDaNonEccep++;
				}
				if(Integer.parseInt(ai.getVerificaDicEsPagSpese().trim())==2){
					esenzPagSDaEccepENonEccep++;
				}
				if(Integer.parseInt(ai.getVerificaDicEsPagSpese().trim())==3){
					esenzPagSDaEccepEEccep++;
				}
				if(Integer.parseInt(ai.getVerificaDicEsPagSpese().trim())==4){
					esenzPagSNonRilev++;

				}
				if(Integer.parseInt(ai.getVerificaDicEsPagSpese().trim())==5){
					esenzPagSNonPres++;
				}

				//Indeterminatezza dell'oggetto della domanda
				if(Integer.parseInt(ai.getIndeterminatezzaOggDom().trim())==1){
					oggDomDaNonEccep++;

				}
				if(Integer.parseInt(ai.getIndeterminatezzaOggDom().trim())==2){
					oggDomDaEccepENonEccep++;

				}
				if(Integer.parseInt(ai.getIndeterminatezzaOggDom().trim())==3){
					oggDomDaEccepEEccep++;

				}
				if(Integer.parseInt(ai.getIndeterminatezzaOggDom().trim())==4){
					oggDomNonRilev++;
				}
				//Carenza interesse ad agire (art. 100 c.p.c.)
				if(Integer.parseInt(ai.getCarenzaInteresseAdAgire().trim())==1){
					carIntAgDaNonEccep++;
				}
				if(Integer.parseInt(ai.getCarenzaInteresseAdAgire().trim())==2){
					carIntAgDaEccepENonEccep++;
				}
				if(Integer.parseInt(ai.getCarenzaInteresseAdAgire().trim())==3){
					carIntAgDaEccepEEccep++;
				}
				if(Integer.parseInt(ai.getCarenzaInteresseAdAgire().trim())==4){
					carIntAgNonRilev++;
				}
				//Data di costituzione in giudizio
				if(ai.getDataCostitGiudizio()!=null){
					dataCostGiudizioValorizzata++;
				}else{
					dataCostGiudizioNonValorizzata++;
				}

				//Costituzione in giudizio telematica
				if(ai.getCostGiudTelematica().trim().equalsIgnoreCase("s")){
					costGiudTelematicaSI++;
				}else{
					costGiudTelematicaNo++;
				}
				//Data 1^ udienza
				if(ai.getDataPrimaUdienza()!=null){
					dataPrimaUdienzValorizzata++;
				}else{
					dataPrimaUdienzNonValorizzata++;
				}
				//Altre eccezioni processuali
				if(Integer.parseInt(ai.getAltreEccProcessuali().trim())==1){
					eccezProcDaNonEccep++;

				}
				if(Integer.parseInt(ai.getAltreEccProcessuali().trim())==2){
					eccezProcDaEccepENonEccep++;

				}
				if(Integer.parseInt(ai.getAltreEccProcessuali().trim())==3){
					eccezProcDaEccepEEccep++;

				}
				if(Integer.parseInt(ai.getAltreEccProcessuali().trim())==4){
					eccezProcNonRilev++;
				}

			}catch(Exception e){
				e.printStackTrace();
			}
		}


		toReturn="{\"labels\": [\"Atto di costituzione rintracciabile\", \"Litispendenza\", \"Decadenza\", \"Corrispondenza tra istanza ATP e domanda amministrativa di invalidita\",\"Verifica correttezza dichiarazione di esenzione pagamento spese\"," +
		"\"Indeterminatezza dell'oggetto della domanda\", \"Carenza interesse ad agire (art. 100 c.p.c.)\", \"Data di costituzione in giudizio\", \"Costituzione in giudizio telematica\",\"data prima udienza\",\"Altre eccezioni processuali\"], " +
		"\"datasets\":[" +
		labelSi +
		"["+attoCostNonRintracciabileSI+","+0+","+0+","+0+","+0+","+0+","+0+","+0+","+costGiudTelematicaSI+","+0+","+0+ "]}," +
		labelNo +
		"["+attoCostNonRintracciabileNo+","+0+","+0+","+0+","+0+","+0+","+0+","+0+","+costGiudTelematicaNo+","+0+","+0+ "]}," +
		labelDaNonEccepire +
		"["+0+","+litispedenzaDaNonEccep+","+decadenzaDaNonEccep+","+0+","+esenzPagSDaNonEccep+","+oggDomDaNonEccep+","+carIntAgDaNonEccep+","+0+","+0+","+0+","+eccezProcDaNonEccep+ "]}," +
		labelDaEccepENonEccep +
		"["+0+","+litispedenzaDaEccepENonEccep+","+decadenzaDaEccepENonEccep+","+0+","+esenzPagSDaEccepENonEccep+","+oggDomDaEccepENonEccep+","+carIntAgDaEccepENonEccep+","+0+","+0+","+0+","+eccezProcDaEccepENonEccep+ "]}," +
		labelDaEccepEEccep +
		"["+0+","+litispedenzaDaEccepEEccep+","+decadenzaDaEccepEEccep+","+0+","+esenzPagSDaEccepEEccep+","+oggDomDaEccepEEccep+","+carIntAgDaEccepEEccep+","+0+","+0+","+0+","+eccezProcDaEccepEEccep+ "]}," +
		labelNonRilevabile +
		"["+0+","+litispedenzaNonRilev+","+decadenzaNonRilev+","+atpNoNonRilev+","+esenzPagSNonRilev+","+oggDomNonRilev+","+carIntAgNonRilev+","+0+","+0+","+0+","+eccezProcNonRilev+ "]}," +
		" {\"label\": \"Si, corrispondente\",\"backgroundColor\": \"#32CD32\",\"data\":" +
		"["+0+","+0+","+0+","+atpSiCorrisp+","+0+","+0+","+0+","+0+","+0+","+0+","+0+ "]}," +
		" {\"label\": \"Non corrispondente e non eccepita\",\"backgroundColor\": \"#CD5C5C\",\"data\": " +
		"["+0+","+0+","+0+","+atpNoCorrispNoEccep+","+0+","+0+","+0+","+0+","+0+","+0+","+0+ "]}," +
		" {\"label\": \"Non corrispondente ed eccepita\",\"backgroundColor\": \"#FF8C00\",\"data\": " +
		"["+0+","+0+","+0+","+atpNoCorrispEccep+","+0+","+0+","+0+","+0+","+0+","+0+","+0+ "]}," +
		" {\"label\": \"Non presente\",\"backgroundColor\": \"#8B008B\",\"data\": " +
		"["+0+","+0+","+0+","+0+","+esenzPagSNonPres+","+0+","+0+","+0+","+0+","+0+","+0+ "]}," +
		labelVal +
		"["+0+","+0+","+0+","+0+","+0+","+0+","+0+","+dataCostGiudizioValorizzata+","+0+","+dataPrimaUdienzValorizzata+","+0+ "]}," +
		labelNoVal +
		"["+0+","+0+","+0+","+0+","+0+","+0+","+0+","+dataCostGiudizioNonValorizzata+","+0+","+dataPrimaUdienzNonValorizzata+","+0+ "]}" +
		"]}";

		return toReturn;

	}


	public static String buildChartPeritale(List<Object> list) {
		String toReturn="";

		//Data comunicazione inizio operazioni peritali da parte del CTU 
		int dataInizioOpPeritaliVal=0;
		int dataInizioOpPeritaliNoVal=0;

		//Registrazione informazioni operazioni peritali

		int regInfoAssente=0;
		int regInfoCogisan=0;
		int regInfoPortale=0;
		int regInfoSisco=0;
		int regInfoPostCtSiscoPostCtu=0;

		//Assegnazione CTU a medico INPS 
		int assCtuMedInpsSi=0;
		int assCtuMedInpsNo=0;

		//Presenza CTP INPS operazioni peritali
		int presCtpInpsOpPSi=0;
		int presCtpInpsOpPNo=0;


		//Presenza medico INPS da documentazione CTU 
		int presMedInpsDocCtuSi=0;
		int presMedInpsDocCtuNo=0;

		//Data arrivo bozza
		int dataArrBozzaVal=0;
		int dataArrBozzaNoVal=0;

		//Data protocollo bozza 
		int dataProtBozzaVal=0;
		int dataProtBozzaNoVal=0;

		//CTU bozza con immagine agli atti
		int ctuBozzImgAttSi=0;
		int ctuBozzImgAttNo=0;

		//Parere su bozza CTU:
		int parBozzaCtuAssente=0;
		int parBozzaCtuSiConc=0;
		int parBozzaCtuNoConc=0;

		//Osservazioni su bozza 
		int ossBozzaSi=0;
		int ossBozzaNo=0;


		//Data deposito CTU definitiva 
		int dataDepCtuDefVal=0;
		int dataDepCtuDefNoVal=0;

		//Data protocollo CTU definitiva
		int dataProtCtuDefVal=0;
		int dataProtCtuDefNoVal=0;

		//CTU definitiva con immagine agli atti
		int ctuDefImgAttSi=0;
		int ctuDefImgAttNo=0;

		//Data termine DISSENSO in SISCO
		int dataTermDissSiscoVal=0;
		int dataTermDissSiscoNoVal=0;

		//Data termine DISSENSO da decreto
		int dataTermDissDecrVal=0;
		int dataTermDissDecrNoVal=0;


		//Parere sanitario di dissenso/accettazione:
		int parSanDissAccettAssente=0;
		int parSanDissAccettSiConc=0;
		int parSanDissAccettNoConc=0;

		//Osservazioni su parere definitivo
		int ossParDefSi=0;
		int ossParDefNo=0;
		int tot=list.size();

		for(Object o: list){
			try{
				AtpoFasePeritale ai = (AtpoFasePeritale) o;

				if(ai.getDataComOpPerCTU()!=null){
					dataInizioOpPeritaliVal++;
				}else{
					dataInizioOpPeritaliNoVal++;
				}
				String [] recInfo=ai.getRecInfoOpPeritali().split(" ",-1);
				if(recInfo !=null){
					if(String.valueOf(ai.getRecInfoOpPeritali().trim().charAt(0)).equalsIgnoreCase("a")){
						regInfoAssente++;
					}
					if(String.valueOf(ai.getRecInfoOpPeritali().trim().charAt(0)).equalsIgnoreCase("c")){
						regInfoCogisan++;
					}
					if(String.valueOf(ai.getRecInfoOpPeritali().trim().charAt(0)).equalsIgnoreCase("p")){
						regInfoPortale++;
					}
					if(String.valueOf(ai.getRecInfoOpPeritali().trim().charAt(0)).equalsIgnoreCase("s")){
						regInfoSisco++;
					}
				}else if(recInfo.length>1 && String.valueOf(ai.getRecInfoOpPeritali().trim().charAt(0)).equalsIgnoreCase("s")){
					regInfoPostCtSiscoPostCtu++;
				}

				if(ai.getAssCTUMedicoInps()!=null && ai.getAssCTUMedicoInps().trim().equalsIgnoreCase("s")){
					assCtuMedInpsSi++;
				}else{
					assCtuMedInpsNo++;
				}

				if(ai.getCtpINPSopPeritali()!=null && ai.getCtpINPSopPeritali().trim().equalsIgnoreCase("s")){
					presCtpInpsOpPSi++;
				}else{
					presCtpInpsOpPNo++;
				}

				if(ai.getPresMedicoInpsDoc()!=null && ai.getPresMedicoInpsDoc().trim().equalsIgnoreCase("s")){
					presMedInpsDocCtuSi++;
				}else{
					presMedInpsDocCtuNo++;
				}

				if(ai.getDataArrBozza()!=null){
					dataArrBozzaVal++;
				}else{
					dataArrBozzaNoVal++;
				}

				if(ai.getDataProtBozza()!=null){
					dataProtBozzaVal++;
				}else{
					dataProtBozzaNoVal++;
				}
				if(ai.getCtuBozzaImgAtti()!=null && ai.getCtuBozzaImgAtti().trim().equalsIgnoreCase("s")){
					ctuBozzImgAttSi++;
				}else{
					ctuBozzImgAttNo++;
				}

				if(ai.getParereBozzaCtu()!=null && Integer.parseInt(ai.getParereBozzaCtu().trim())==1){
					parBozzaCtuAssente++;

				}
				if(ai.getParereBozzaCtu()!=null && Integer.parseInt(ai.getParereBozzaCtu().trim())==2){
					parBozzaCtuSiConc++;

				}
				if(ai.getParereBozzaCtu()!=null && Integer.parseInt(ai.getParereBozzaCtu().trim())==3){
					parBozzaCtuNoConc++;

				}
				if(ai.getOssBozza()!=null && ai.getOssBozza().trim().equalsIgnoreCase("s")){
					ossBozzaSi++;
				}else{
					ossBozzaNo++;
				}

				if(ai.getDataComDepCTUDefCanc()!=null){
					dataDepCtuDefVal++;
				}else{
					dataDepCtuDefNoVal++;
				}

				if(ai.getDataProtCTUDef()!=null){
					dataProtCtuDefVal++;
				}else{
					dataProtCtuDefNoVal++;
				}
				if(ai.getCtuDefImgAtti() !=null && ai.getCtuDefImgAtti().trim().equalsIgnoreCase("s")){
					ctuDefImgAttSi++;
				}else{
					ctuDefImgAttNo++;
				}
				if(ai.getDataTermDissSisco()!=null){
					dataTermDissSiscoVal++;
				}else{
					dataTermDissSiscoNoVal++;
				}
				if(ai.getParereDissAccetfascitazione()!=null && Integer.parseInt(ai.getParereDissAccetfascitazione().trim())==1){
					parSanDissAccettAssente++;

				}
				if(ai.getParereDissAccetfascitazione()!=null && Integer.parseInt(ai.getParereDissAccetfascitazione().trim())==2){
					parSanDissAccettSiConc++;

				}
				if(ai.getParereDissAccetfascitazione()!=null && Integer.parseInt(ai.getParereDissAccetfascitazione().trim())==3){
					parSanDissAccettNoConc++;

				}

				if(ai.getOssParDef()!=null && ai.getOssParDef().trim().equalsIgnoreCase("s")){
					ossParDefSi++;
				}else{
					ossParDefNo++;
				}
			}catch(Exception e){
				e.printStackTrace();

			}

		}
		toReturn="{\"labels\": [\"Data comunicazione inizio operazioni peritali CTU\", \"Registrazione info operazioni peritali\", \"Assegnazione CTU a medico INPS\", \"Presenza CTP INPS operazioni peritali\",\"Presenza medico INPS da doc CTU\"," +
		"\"Data arrivo bozza\", \"Data protocollo bozza\", \"CTU bozza con immagine agli atti\", \"Parere su bozza CTU\",\"Osservazioni su bozza\",\"Data deposito CTU definitiva\"," +
		"\"Data protocollo CTU definitiva\", \"CTU definitiva con immagine agli atti\", \"Data termine DISSENSO in SISCO\", \"Data termine DISSENSO da decreto\",\"Parere sanitario di dissenso-accettazione\",\"Osservazioni su parere definitivo\"], " +
		"\"datasets\":[" +
		labelSi +
		"["+0+","+0+","+assCtuMedInpsSi+","+presCtpInpsOpPSi+","+presMedInpsDocCtuSi+","+0+","+0+","+ctuBozzImgAttSi+","+0+","+ossBozzaSi+","+0+","+0+","+ctuDefImgAttSi+","+0+","+0+","+0+","+0+","+ossParDefSi+ "]}," +
		labelNo +
		"["+0+","+0+","+assCtuMedInpsNo+","+presCtpInpsOpPNo+","+presMedInpsDocCtuNo+","+0+","+0+","+ctuBozzImgAttNo+","+0+","+ossBozzaNo+","+0+","+0+","+ctuDefImgAttNo+","+0+","+0+","+0+","+0+","+ossParDefNo+ "]},"+
		labelVal +
		"["+dataInizioOpPeritaliVal+","+0+","+0+","+0+","+0+","+dataArrBozzaVal+","+dataProtBozzaVal+","+0+","+0+","+0+","+dataDepCtuDefVal+","+dataProtCtuDefVal+","+0+","+0+","+dataTermDissSiscoVal+","+dataTermDissDecrVal+","+0+","+0+ "]}," +
		labelNoVal +
		"["+dataInizioOpPeritaliNoVal+","+0+","+0+","+0+","+0+","+dataArrBozzaNoVal+","+dataProtBozzaNoVal+","+0+","+0+","+0+","+dataDepCtuDefNoVal+","+dataProtCtuDefNoVal+","+0+","+0+","+dataTermDissSiscoNoVal+","+dataTermDissDecrNoVal+","+0+","+0+ "]}," +
		labelAssente +
		"["+0+","+regInfoAssente+","+0+","+0+","+0+","+0+","+0+","+0+","+parBozzaCtuAssente+","+0+","+0+","+0+","+0+","+0+","+0+","+0+","+parSanDissAccettAssente+","+0+ "]}," +
		labelCogisan +
		"["+0+","+regInfoCogisan+","+0+","+0+","+0+","+0+","+0+","+0+","+0+","+0+","+0+","+0+","+0+","+0+","+0+","+0+","+0+","+0+ "]}," +
		labelPortale +
		"["+0+","+regInfoPortale+","+0+","+0+","+0+","+0+","+0+","+0+","+0+","+0+","+0+","+0+","+0+","+0+","+0+","+0+","+0+","+0+ "]}," +
		labelSisco +
		"["+0+","+regInfoSisco+","+0+","+0+","+0+","+0+","+0+","+0+","+0+","+0+","+0+","+0+","+0+","+0+","+0+","+0+","+0+","+0+ "]}," +
		labelSiscoPostCtu +
		"["+0+","+regInfoPostCtSiscoPostCtu+","+0+","+0+","+0+","+0+","+0+","+0+","+0+","+0+","+0+","+0+","+0+","+0+","+0+","+0+","+0+","+0+ "]}," +
		labelConcorde +
		"["+0+","+0+","+0+","+0+","+0+","+0+","+0+","+0+","+parBozzaCtuSiConc+","+0+","+0+","+0+","+0+","+0+","+0+","+0+","+parSanDissAccettSiConc+","+0+ "]}," +
		labelNonConcorde +
		"["+0+","+0+","+0+","+0+","+0+","+0+","+0+","+0+","+parBozzaCtuNoConc+","+0+","+0+","+0+","+0+","+0+","+0+","+0+","+parSanDissAccettNoConc+","+0+ "]}" +
		"]}";

		return toReturn;

	}


	public static String buildChartPostPeritale(List<Object> list) {

		String toReturn="";
		//Data deposito del Decreto di OMOLOGA val
		int dataDepDecrOmVal=0;
		int dataDepDecrOmNoVal=0;

		//Data NOTIFICA Decreto di OMOLOGA val
		int dataNotifDecrOmVal=0;
		int dataNotifDecrOmNoVal=0;
		//Data protocollo Decreto di OMOLOGA notificato val
		int dataProtDecrOmNotifVal=0;
		int dataProtDecrOmNotifNoVal=0;
		//spese legali pagate val
		int speseLegaliPagateVal=0;
		int speseLegaliPagateNoVal=0;
		//spese legali nel decreto di mologa val
		int speseLegaliDecrOmolVal=0;
		int speseLegaliDecrOmolNoVal=0;

		//Data trasmissione Decreto alla LPS val 
		int dataTrasmisDecLPSVal=0;
		int dataTrasmisDecLPSNoVal=0;

		//omologa allegata
		int omAllegataSi=0;
		int omAllegNo=0;


		//codice chiusura corretto
		int codChiusCorrIstEsitoFav=0;
		int codChiusCorrIstEsitoParzFav=0;
		int codChiusCorrIstEsitoSfav=0;
		int codChiusCorrIstEsitoDiss=0;

		//codice chiusura inserito
		int codChiusInsIstEsitoFav=0;
		int codChiusInsIstEsitoParzFav=0;
		int codChiusInsIstEsitoSfav=0;
		int codChiusInsIstEsitoDiss=0;

		//verifica corrisp tra decr di omologa e ctu def
		int verifCorriDecrOmCtu1=0;
		int verifCorriDecrOmCtu2=0;
		int verifCorriDecrOmCtu3=0;
		int verifCorriDecrOmCtu4=0;

		//Cod. pagamento spese legali ins.
		int codPagSpeseLegali1=0;
		int codPagSpeseLegali2=0;
		int codPagSpeseLegali3=0;
		int codPagSpeseLegali4=0;
		int codPagSpeseLegali5=0;
		int codPagSpeseLegali6=0;

		//Cod. pagamento spese legali corr
		int codPagSpeseLegaliCorr1=0;
		int codPagSpeseLegaliCorr2=0;
		int codPagSpeseLegaliCorr3=0;
		int codPagSpeseLegaliCorr4=0;
		int codPagSpeseLegaliCorr5=0;
		int codPagSpeseLegaliCorr6=0;


		//registraziojne dati pratica
		int regDatiPratica1=0;
		int regDatiPratica2=0;
		int regDatiPratica3=0;




		for(Object o: list){
			try{
				AtpoFasePostPeritale ai = (AtpoFasePostPeritale) o;

				if(ai.getRecDatiPratica()!=null && Integer.parseInt(ai.getRecDatiPratica().trim())==1){
					regDatiPratica1++;
				}
				if(ai.getRecDatiPratica()!=null && Integer.parseInt(ai.getRecDatiPratica().trim())==2){
					regDatiPratica2++;
				}
				if(ai.getRecDatiPratica()!=null && Integer.parseInt(ai.getRecDatiPratica().trim())==3){
					regDatiPratica3++;
				}


				if(ai.getCodPagamentoSpeseLegaliCorretto()!=null && Integer.parseInt(ai.getCodPagamentoSpeseLegaliCorretto().trim())==1){
					codPagSpeseLegaliCorr1++;
				}
				if(ai.getCodPagamentoSpeseLegaliCorretto()!=null && Integer.parseInt(ai.getCodPagamentoSpeseLegaliCorretto().trim())==2){
					codPagSpeseLegaliCorr2++;
				}
				if(ai.getCodPagamentoSpeseLegaliCorretto()!=null && Integer.parseInt(ai.getCodPagamentoSpeseLegaliCorretto().trim())==3){
					codPagSpeseLegaliCorr3++;
				}
				if(ai.getCodPagamentoSpeseLegaliCorretto()!=null && Integer.parseInt(ai.getCodPagamentoSpeseLegaliCorretto().trim())==4){
					codPagSpeseLegaliCorr4++;
				}
				if(ai.getCodPagamentoSpeseLegaliCorretto()!=null && Integer.parseInt(ai.getCodPagamentoSpeseLegaliCorretto().trim())==5){
					codPagSpeseLegaliCorr5++;
				}
				if(ai.getCodPagamentoSpeseLegaliCorretto()!=null && Integer.parseInt(ai.getCodPagamentoSpeseLegaliCorretto().trim())==6){
					codPagSpeseLegaliCorr6++;
				}
				if(ai.getCodPagamentoSpeseLegali()!=null && Integer.parseInt(ai.getCodPagamentoSpeseLegali().trim())==1){
					codPagSpeseLegali1++;
				}
				if(ai.getCodPagamentoSpeseLegali()!=null && Integer.parseInt(ai.getCodPagamentoSpeseLegali().trim())==2){
					codPagSpeseLegali2++;
				}
				if(ai.getCodPagamentoSpeseLegali()!=null && Integer.parseInt(ai.getCodPagamentoSpeseLegali().trim())==3){
					codPagSpeseLegali3++;
				}
				if(ai.getCodPagamentoSpeseLegali()!=null && Integer.parseInt(ai.getCodPagamentoSpeseLegali().trim())==4){
					codPagSpeseLegali4++;
				}
				if(ai.getCodPagamentoSpeseLegali()!=null && Integer.parseInt(ai.getCodPagamentoSpeseLegali().trim())==5){
					codPagSpeseLegali5++;
				}
				if(ai.getCodPagamentoSpeseLegali()!=null && Integer.parseInt(ai.getCodPagamentoSpeseLegali().trim())==6){
					codPagSpeseLegali6++;
				}

				if(ai.getCorrispDecrOmgEctuDef()!=null && Integer.parseInt(ai.getCorrispDecrOmgEctuDef().trim())==1){
					verifCorriDecrOmCtu1++;
				}
				if(ai.getCorrispDecrOmgEctuDef()!=null && Integer.parseInt(ai.getCorrispDecrOmgEctuDef().trim())==2){
					verifCorriDecrOmCtu2++;
				}
				if(ai.getCorrispDecrOmgEctuDef()!=null && Integer.parseInt(ai.getCorrispDecrOmgEctuDef().trim())==3){
					verifCorriDecrOmCtu3++;
				}
				if(ai.getCorrispDecrOmgEctuDef()!=null && Integer.parseInt(ai.getCorrispDecrOmgEctuDef().trim())==4){
					verifCorriDecrOmCtu4++;
				}

				if(ai.getCodChiusuraInserito()!=null && Integer.parseInt(ai.getCodChiusuraInserito().trim())==1){
					codChiusInsIstEsitoFav++;
				}
				if(ai.getCodChiusuraInserito()!=null && Integer.parseInt(ai.getCodChiusuraInserito().trim())==2){
					codChiusInsIstEsitoParzFav++;
				}
				if(ai.getCodChiusuraInserito()!=null && Integer.parseInt(ai.getCodChiusuraInserito().trim())==3){
					codChiusInsIstEsitoSfav++;
				}
				if(ai.getCodChiusuraInserito()!=null && Integer.parseInt(ai.getCodChiusuraInserito().trim())==4){
					codChiusInsIstEsitoDiss++;
				}

				if(ai.getOmologaAllegata()!= null && ai.getOmologaAllegata().trim().equalsIgnoreCase("s")){
					omAllegataSi++;
				}else{
					omAllegNo++;
				}
				if(ai.getCodChiusuraCorretto()!=null && Integer.parseInt(ai.getCodChiusuraCorretto().trim())==1){
					codChiusCorrIstEsitoFav++;
				}
				if(ai.getCodChiusuraCorretto()!=null && Integer.parseInt(ai.getCodChiusuraCorretto().trim())==2){
					codChiusCorrIstEsitoParzFav++;
				}
				if(ai.getCodChiusuraCorretto()!=null && Integer.parseInt(ai.getCodChiusuraCorretto().trim())==3){
					codChiusCorrIstEsitoSfav++;
				}
				if(ai.getCodChiusuraCorretto()!=null && Integer.parseInt(ai.getCodChiusuraCorretto().trim())==4){
					codChiusCorrIstEsitoDiss++;
				}
				if(ai.getDataDepositoDecOmologa()!=null){
					dataDepDecrOmVal++;
				}else{
					dataDepDecrOmNoVal++;
				}
				if(ai.getDataNotificaDecOmologa()!=null){
					dataNotifDecrOmVal++;
				}else{
					dataNotifDecrOmNoVal++;
				}
				if(ai.getDataProtDecOmologaNotif()!=null){
					dataProtDecrOmNotifVal++;
				}else{
					dataProtDecrOmNotifNoVal++;
				}
				if(ai.getSpesePagate()!=null){
					speseLegaliPagateVal++;
				}else{
					speseLegaliPagateNoVal++;
				}
				if(ai.getSpeseDecrOmologa()!=null){
					speseLegaliDecrOmolVal++;
				}else{
					speseLegaliDecrOmolNoVal++;
				}
				if(ai.getDataTrasmissDecrLPS()!=null){
					dataTrasmisDecLPSVal++;
				}else{
					dataTrasmisDecLPSNoVal++;
				}
			}catch(Exception e){
				e.printStackTrace();

			}


			toReturn="{\"labels\": [\"Data deposito del Decreto di OMOLOGA\", \"Data NOTIFICA Decreto di OMOLOGA\", \"Data protocollo Decreto di OMOLOGA notificato\", \"Spese legali pagate\",\"Spese legali nel decreto di omologa\"," +
			"\"Codice chiusura corretto\", \"Codice chiusura inserito\", \"Verifica corrispondenza tra decreto di OMOLOGA e CTU definitiva\", \"Cod. pagamento spese legali ins\",\"Cod. pagamento spese legali corretto\",\"Registrazione dati pratica\"," +
			"\"Data trasmissione Decreto alla LPS\", \"Omologa allegata\"], " +
			"\"datasets\":[" +
			labelVal +
			"["+dataDepDecrOmVal+","+dataNotifDecrOmVal+","+dataProtDecrOmNotifVal+","+speseLegaliPagateVal+","+speseLegaliDecrOmolVal+","+0+","+0+","+0+","+0+","+0+","+0+","+dataTrasmisDecLPSVal+","+0+"]}," +
			labelNoVal +
			"["+dataDepDecrOmNoVal+","+dataNotifDecrOmNoVal+","+dataProtDecrOmNotifNoVal+","+speseLegaliPagateNoVal+","+speseLegaliDecrOmolNoVal+","+0+","+0+","+0+","+0+","+0+","+0+","+dataTrasmisDecLPSNoVal+","+0+"]}," +
			labelSi +
			"["+0+","+0+","+0+","+0+","+0+","+0+","+0+","+verifCorriDecrOmCtu3+","+0+","+0+","+0+","+0+","+omAllegataSi+"]}," +
			labelNo +
			"["+0+","+0+","+0+","+0+","+0+","+0+","+0+","+0+","+0+","+0+","+0+","+0+","+omAllegNo+"]}," +
			labelIstanzeConEsitoFavorevole +
			"["+0+","+0+","+0+","+0+","+0+","+codChiusCorrIstEsitoFav+","+codChiusInsIstEsitoFav+","+0+","+0+","+0+","+0+","+0+"]}," +
			labelIstanzeConEsitoParzFavorevole +
			"["+0+","+0+","+0+","+0+","+0+","+codChiusCorrIstEsitoParzFav+","+codChiusInsIstEsitoParzFav+","+0+","+0+","+0+","+0+","+0+"]}," +
			labelIstanzeConEsitoSfavorevole +
			"["+0+","+0+","+0+","+0+","+0+","+codChiusCorrIstEsitoSfav+","+codChiusInsIstEsitoSfav+","+0+","+0+","+0+","+0+","+0+"]}," +
			labelIstanzeConEsitoDissenso +
			"["+0+","+0+","+0+","+0+","+0+","+codChiusCorrIstEsitoDiss+","+codChiusInsIstEsitoDiss+","+0+","+0+","+0+","+0+","+0+"]}," +
			labelMancataCorrispMancataRichiesta +
			"["+0+","+0+","+0+","+0+","+0+","+0+","+0+","+verifCorriDecrOmCtu1+","+0+","+0+","+0+","+0+"]}," +
			labelMancataCorrispRichiestaCorr +
			"["+0+","+0+","+0+","+0+","+0+","+0+","+0+","+verifCorriDecrOmCtu2+","+0+","+0+","+0+","+0+"]}," +
			labelNonRilevabile +
			"["+0+","+0+","+0+","+0+","+0+","+0+","+0+","+verifCorriDecrOmCtu4+","+0+","+0+","+0+","+0+"]}," +
			labelSpeseCompExArt +
			"["+0+","+0+","+0+","+0+","+0+","+0+","+0+","+0+","+codPagSpeseLegali1+","+codPagSpeseLegaliCorr1+","+0+","+0+"]}," +
			labelSpeseCompNoExArt +
			"["+0+","+0+","+0+","+0+","+0+","+0+","+0+","+0+","+codPagSpeseLegali2+","+codPagSpeseLegaliCorr2+","+0+","+0+"]}," +
			labelCondAltrSogg +
			"["+0+","+0+","+0+","+0+","+0+","+0+","+0+","+0+","+codPagSpeseLegali3+","+codPagSpeseLegaliCorr3+","+0+","+0+"]}," +
			labelCondControP +
			"["+0+","+0+","+0+","+0+","+0+","+0+","+0+","+0+","+codPagSpeseLegali4+","+codPagSpeseLegaliCorr4+","+0+","+0+"]}," +
			labelCodPagPa +
			"["+0+","+0+","+0+","+0+","+0+","+0+","+0+","+0+","+codPagSpeseLegali5+","+codPagSpeseLegaliCorr5+","+0+","+0+"]}," +
			labelCondInpPagS +
			"["+0+","+0+","+0+","+0+","+0+","+0+","+0+","+0+","+codPagSpeseLegali6+","+codPagSpeseLegaliCorr6+","+0+","+0+"]}," +
			labelCorretti +
			"["+0+","+0+","+0+","+0+","+0+","+0+","+0+","+0+","+0+","+0+","+regDatiPratica1+","+0+","+0+"]}," +
			labelnonCorr +
			"["+0+","+0+","+0+","+0+","+0+","+0+","+0+","+0+","+0+","+0+","+regDatiPratica1+","+0+","+0+"]}," +
			labelParzCorrtetti +
			"["+0+","+0+","+0+","+0+","+0+","+0+","+0+","+0+","+0+","+0+","+regDatiPratica1+","+0+","+0+"]}" +

			"]}";

		}



		return toReturn;
	}


	public static String buildChartPostPeritaleB(List<Object> list) {
		String toReturn="";

		//Data deposito dissenso
		int dataDepDissVal=0;
		int dataDepDissNoVal=0;
		//Data deposito ricorso di 1 grado
		int dataDepRicorsoVal=0;
		int dataDepRicorsoNoVal=0;
		//Data di definizione pratica
		int dataDefPratVal=0;
		int dataDefPratNoVal=0;
		//spese legali pagate
		int speseLegPagVal=0;
		int speseLegPagNoVal=0;
		//tipom dissenso
		int tipoDissenso1=0;
		int tipoDissenso2=0;
		int tipoDissenso3=0;
		//Comunicazione deposito dissenso a Uff.legale
		int comDepDissUffLeg1=0;
		int comDepDissUffLeg2=0;
		int comDepDissUffLeg3=0;


		for(Object o: list){
			try{
				AtpoFasePostPeritale ai = (AtpoFasePostPeritale) o;

				if(ai.getComDepDissUffLegale()!=null && Integer.parseInt(ai.getComDepDissUffLegale().trim())==1){
					comDepDissUffLeg1++;
				}
				if(ai.getComDepDissUffLegale()!=null && Integer.parseInt(ai.getComDepDissUffLegale().trim())==2){
					comDepDissUffLeg2++;
				}
				if(ai.getComDepDissUffLegale()!=null && Integer.parseInt(ai.getComDepDissUffLegale().trim())==3){
					comDepDissUffLeg3++;
				}

				if(ai.getTipoDissenso()!=null && Integer.parseInt(ai.getTipoDissenso().trim())==1){
					tipoDissenso1++;//aministrativo
				}
				if(ai.getTipoDissenso()!=null && Integer.parseInt(ai.getTipoDissenso().trim())==2){
					tipoDissenso2++;//sanitario
				}
				if(ai.getTipoDissenso()!=null && Integer.parseInt(ai.getTipoDissenso().trim())==3){
					tipoDissenso3++;//assente
				}

				if(ai.getDataDepDiss()!=null){
					dataDepDissVal++;
				}else{
					dataDepDissNoVal++;
				}
				if(ai.getDataDepRicPrimoG()!=null){
					dataDepRicorsoVal++;
				}else{
					dataDepRicorsoNoVal++;
				}
				if(ai.getDataDefPratica()!=null){
					dataDefPratVal++;
				}else{
					dataDefPratNoVal++;
				}
				if(ai.getSpesePagate()!=null){
					speseLegPagVal++;
				}else{
					speseLegPagNoVal++;
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			toReturn="{\"labels\": [\"Data deposito dissenso\", \"Comunicazione deposito dissenso a Uff.legale\", \"Data deposito ricorso di 1 grado\", \"Data di definizione pratica\",\"Tipo dissenso\"," +
			"\"spese legali pagate\"], " +
			"\"datasets\":[" +
			labelVal +
			"["+dataDepDissVal+","+0+","+dataDepRicorsoVal+","+dataDefPratVal+","+0+","+speseLegPagVal+"]}," +
			labelNoVal +
			"["+dataDepDissNoVal+","+0+","+dataDepRicorsoNoVal+","+dataDefPratNoVal+","+0+","+speseLegPagNoVal+"]}," +
			labelAssente+
			"["+0+","+0+","+0+","+0+","+tipoDissenso1+","+0+"]}," +
			labelAmministrativo +
			"["+0+","+0+","+0+","+0+","+tipoDissenso2+","+0+"]}," +
			labelSanitario +
			"["+0+","+0+","+0+","+0+","+tipoDissenso3+","+0+"]}," +
			labelSiConFlagPrimoGrado+
			"["+0+","+comDepDissUffLeg1+","+0+","+0+","+0+","+0+"]}," +
			labelSiConAltraModalita +
			"["+0+","+comDepDissUffLeg2+","+0+","+0+","+0+","+0+"]}," +
			labelNo +
			"["+0+","+comDepDissUffLeg3+","+0+","+0+","+0+","+0+"]}" +
			"]}";

		}
		return toReturn;
	}


	//esecuzione provvedimenti


	public static String buildChartEsecuzioneProvvedimenti(List<Object> list) {
		String toReturn="";
		int tot= list.size();
		//Data decreto liquidazione CTU
		int dataDecrLiqCtuVal=0;
		//Data registrazione dati liquidazione
		int dataRecDatiLiqVal=0;
		//Data liquidazione prestazione da LPS
		int dataLiqPrestLpsVal=0;
		//importo mensile rata euro
		int importoMensileRataVal=0;
		//Data presa in carico Decreto di omologa da LPS
		int dataPresCaricoDecrOmLpsVal=0;
		//importo rata dovuta
		int impRataDovVal=0;
		//interessi legali pagati
		int intLegPagVal=0;
		///interessi legali dovuti
		int intLegaliDovutiVal=0;
		//Data decorrenza per calcolo interessi legali
		int dataDecCalcIntLegali=0;
		//Data decorrenza prestazione inserita
		int dataDecPrestInsVal=0;
		//Data decorrenza prestazione
		int dataDecPrestVal=0;
		//Presenza decreto di omologa nel fascicolo
		int presDecrOmFascSi=0;
		int presDecrOmFascNo=0;
		//Condanna a pagamento CTU ATPO
		int condAPagCtuAtpoSi=0;
		int condAPagCtuAtpoNo=0;
		//Prestazione corrispondente al decreto di omologa
		int presCorrispDecrOmFascSi=0;
		int presCorrispDecrOmFascNo=0;

		for(Object o: list){
			try{
				AtpoFaseEsecuzioneProvvedimenti ai = (AtpoFaseEsecuzioneProvvedimenti) o;

				if(ai.getPresDecrOmgFasc()!=null && ai.getPresDecrOmgFasc().trim().equalsIgnoreCase("s")){
					presDecrOmFascSi++;
				}else{
					presDecrOmFascNo++;
				}
				if(ai.getPrestCorrisp()!=null && ai.getPrestCorrisp().trim().equalsIgnoreCase("s")){
					presCorrispDecrOmFascSi++;
				}else{
					presCorrispDecrOmFascNo++;
				}

				if(ai.getCondannaPagCtuAtpo()!=null && ai.getCondannaPagCtuAtpo().trim().equalsIgnoreCase("s")){
					condAPagCtuAtpoSi++;
				}else{
					condAPagCtuAtpoNo++;
				}

				if(ai.getDataDecrLiqCtu()!=null){
					dataDecrLiqCtuVal++;
				}
				if(ai.getRecDatiLiq()!=null){
					dataRecDatiLiqVal++;
				}
				if(ai.getDataLiqPrestLps()!=null){
					dataLiqPrestLpsVal++;
				}
				if(ai.getImportoRataMensile()!=null){
					importoMensileRataVal++;
				}
				if(ai.getDataPresaInCaricoDecrOmgLps()!=null){
					dataPresCaricoDecrOmLpsVal++;
				}
				if(ai.getImportoRataDovuta()!=null){
					impRataDovVal++;
				}
				if(ai.getInteressiLegaliPagati()!=null){
					intLegPagVal++;
				}
				if(ai.getInteressiLegaliDovuti()!=null){
					intLegaliDovutiVal++;
				}
				if(ai.getDataDecCalcoloIntLegali()!=null){
					dataDecCalcIntLegali++;
				}
				if(ai.getDataDecPrestInserita()!=null){
					dataDecPrestInsVal++;
				}
				if(ai.getDataDecPrestInserita()!=null){
					dataDecPrestVal++;
				}
				if(ai.getDataDecPrestInserita()!=null){
					dataDecPrestVal++;
				}

			}catch(Exception e){
				e.printStackTrace();
			}
			toReturn="{\"labels\": [\"Presenza decreto di omologa nel fascicolo\", \"Data decreto liquidazione CTU\", \"Data registrazione dati liquidazione\", \"Data liquidazione prestazione da LPS\",\"importo mensile rata\"," +
			"\"Data presa in carico Decreto di omologa da LPS\", \"Importo rata dovuta\", \"Interessi legai pagati\", \"Interessi legai dovuti\",\"Data decorrenza per calcolo interessi legali\",\"Data decorrenza prestazione inserita\"," +
			"\"Data decorrenza prestazione\", \"Prestazione corrispondente al decreto di omologa\", \"//Condanna a pagamento CTU ATPO\"], " +
			"\"datasets\":[" +
			labelVal +
			"["+0+","+dataDecrLiqCtuVal+","+dataRecDatiLiqVal+","+dataLiqPrestLpsVal+","+importoMensileRataVal+","+dataPresCaricoDecrOmLpsVal+","+impRataDovVal+","+intLegPagVal+","+intLegaliDovutiVal+","+dataDecCalcIntLegali+","+dataDecPrestInsVal+","+dataDecPrestVal+","+0+","+0+"]}," +
			labelNoVal +
			"["+0+","+(tot-dataDecrLiqCtuVal)+","+(tot-dataRecDatiLiqVal)+","+(tot-dataLiqPrestLpsVal)+","+(tot-importoMensileRataVal)+","+(tot-dataPresCaricoDecrOmLpsVal)+","+(tot-impRataDovVal)+","+(tot-intLegPagVal)+","+(tot-intLegaliDovutiVal)+","+(tot-dataDecCalcIntLegali)+","+(tot-dataDecPrestInsVal)+","+(tot-dataDecPrestVal)+","+0+","+0+"]}," +
			labelSi +
			"["+presDecrOmFascSi+","+0+","+0+","+0+","+0+","+0+","+impRataDovVal+","+0+","+0+","+0+","+0+","+0+","+presCorrispDecrOmFascSi+","+condAPagCtuAtpoSi+"]}," +
			labelNo +
			"["+presDecrOmFascNo+","+0+","+0+","+0+","+0+","+0+","+0+","+0+","+0+","+0+","+0+","+0+","+presCorrispDecrOmFascNo+","+condAPagCtuAtpoNo+"]}" +
			"]}";

		}
		return toReturn;


	}


	public static String buildChartEsecuzioneProvvedimentiTwo(List<Object> list) {
		String toReturn="";
		int tot= list.size();
		//Data fattura/richiesta pagamento CTU
		int dataFattRichPagCtuVal=0;
		//Data liquidazione CTU
		int dataLiqCtu=0;
		//Anticipate spese CTU
		int antSpeseCtuSi=0;
		int antSpeseCtuNo=0;
		//importo spese ctu pagate
		int impSpesePagVal=0;
		//importo spese ctu dovute
		int impSpeseCtuDovuteVal=0;
		//Data lettera di invito al pagamento spese legal
		int dataLetteraInvPagSLegVal=0;
		//Data lettera recupero spese CTU
		int dataLetteraRecSCtuVal=0;
		//Condanna pagamento spese legali
		int condPagSLegaliSi=0;
		int condPagSLegaliNo=0;
		int condPagSLegaliCompensate=0;
		int condPagSLegaliParzCompensate=0;
		//Soggetto richiedente il pagamento
		int soggRicPag1=0;
		int soggRicPag2=0;
		int soggRicPag3=0;
		//Data richiesta spese legali
		int dataRichSpLVal=0;
		//Data pagamento spese legali Avv. c/parte
		int dataPagSpeseLAvvCParteVal=0;
		//Presenza decreto/sentenza per mancato pagamento prestazione
		int presDecSentMancPagPSi=0;
		int presDecSentMancPagNo=0;
		//costo giudizio per mancato pagamento prestazione
		int costoGiudizioMancPagPVal=0;
		//Data limite del calcolo impatto
		int dataLimiteCalcImpVal=0;
		for(Object o: list){
			try{
				AtpoFaseEsecuzioneProvvedimenti ai = (AtpoFaseEsecuzioneProvvedimenti) o;

				if(ai.getDataFattura()!=null){
					dataFattRichPagCtuVal++;
				}
				if(ai.getDataLiqCtuAtpo()!=null){
					dataLiqCtu++;
				}
				if(ai.getDataLiqCtuAtpo()!=null){
					dataLiqCtu++;
				}
				if(ai.getAntSpeseCtu()!=null && ai.getAntSpeseCtu().trim().equalsIgnoreCase("s")){
					antSpeseCtuSi++;
				}else{
					antSpeseCtuNo++;
				}
				if(ai.getImpSpeseCtuPagate()!=null){
					impSpesePagVal++;
				}
				if(ai.getImpSpeseCtuDovute()!=null){
					impSpeseCtuDovuteVal++;
				}
				if(ai.getDatalettInvPagSpeseLegali()!=null){
					dataLetteraInvPagSLegVal++;
				}
				if(ai.getDataLetteraRecuperoSpeseCtu()!=null){
					dataLetteraRecSCtuVal++;
				}
				if(ai.getCondannaPagSpeseLegali()!=null && Integer.parseInt(ai.getCondannaPagSpeseLegali().trim())==1){
					condPagSLegaliSi++;
				}
				if(ai.getCondannaPagSpeseLegali()!=null && Integer.parseInt(ai.getCondannaPagSpeseLegali().trim())==2){
					condPagSLegaliNo++;
				}
				if(ai.getCondannaPagSpeseLegali()!=null && Integer.parseInt(ai.getCondannaPagSpeseLegali().trim())==3){
					condPagSLegaliCompensate++;
				}
				if(ai.getCondannaPagSpeseLegali()!=null && Integer.parseInt(ai.getCondannaPagSpeseLegali().trim())==4){
					condPagSLegaliParzCompensate++;
				}
				
				if(ai.getSoggRichPagamento()!=null && ai.getSoggRichPagamento().trim().equalsIgnoreCase("k1")){
					soggRicPag1++;
				}
				if(ai.getSoggRichPagamento()!=null && ai.getSoggRichPagamento().trim().equalsIgnoreCase("k2")){
					soggRicPag2++;
				}
				if(ai.getSoggRichPagamento()!=null && ai.getSoggRichPagamento().trim().equalsIgnoreCase("k3")){
					soggRicPag3++;
				}
				if(ai.getDataArriviNotula()!=null){
					dataRichSpLVal++;
				}
				if(ai.getDataPagSpseLegaliAvvCparte()!=null){
					dataPagSpeseLAvvCParteVal++;
				}
				if(ai.getPresDecrSentMancPagPrest()!=null && ai.getPresDecrSentMancPagPrest().trim().equalsIgnoreCase("s")){
					presDecSentMancPagPSi++;
				}else{
					presDecSentMancPagNo++;
				}
				if(ai.getCostoGiudizioMancPagPrest()!=null){
					costoGiudizioMancPagPVal++;
				}
				if(ai.getDataLimiteCalcImpatto()!=null){
					dataLimiteCalcImpVal++;
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			toReturn="{\"labels\": [\"Data fattura-richiesta pagamento CTU\", \"Data liquidazione CTU\", \"Anticipate spese CTU\", \"importo spese ctu pagate\",\"importo spese ctu dovute\"," +
			"\"Data lettera di invito al pagamento spese legali\", \"Data lettera recupero spese CTU\", \"Condanna pagamento spese legali\", \"Soggetto richiedente il pagamento\",\"Data richiesta spese legali\",\"Data pagamento spese legali Avv. c/parte\"," +
			"\"Presenza decreto-sentenza per mancato pagamento prestazione\", \"costo giudizio per mancato pagamento prestazione\", \"//Data limite del calcolo impatto\"], " +
			"\"datasets\":[" +
			labelVal +
			"["+dataFattRichPagCtuVal+","+dataLiqCtu+","+0+","+impSpesePagVal+","+impSpeseCtuDovuteVal+","+dataLetteraInvPagSLegVal+","+dataLetteraRecSCtuVal+","+0+","+0+","+dataRichSpLVal+","+dataPagSpeseLAvvCParteVal+","+0+","+costoGiudizioMancPagPVal+","+dataLimiteCalcImpVal+"]}," +
			labelNoVal +
			"["+(tot-dataFattRichPagCtuVal)+","+(tot-dataLiqCtu)+","+0+","+(tot-impSpesePagVal)+","+(tot-impSpeseCtuDovuteVal)+","+(tot-dataLetteraInvPagSLegVal)+","+(tot-dataLetteraRecSCtuVal)+","+0+","+0+","+(tot-dataRichSpLVal)+","+(tot-dataPagSpeseLAvvCParteVal)+","+0+","+(tot-costoGiudizioMancPagPVal)+","+(tot-dataLimiteCalcImpVal)+"]}," +
			labelSi +
			"["+0+","+0+","+antSpeseCtuSi+","+0+","+0+","+0+","+0+","+condPagSLegaliSi+","+0+","+0+","+0+","+presDecSentMancPagPSi+","+0+","+0+"]}," +
			labelNo +
			"["+0+","+0+","+antSpeseCtuNo+","+0+","+0+","+0+","+0+","+condPagSLegaliNo+","+0+","+0+","+0+","+presDecSentMancPagNo+","+0+","+0+"]}," +
			labelCompensate +
			"["+0+","+0+","+0+","+0+","+0+","+0+","+0+","+condPagSLegaliCompensate+","+0+","+0+","+0+","+0+","+0+","+0+"]}," +
			labelParzCompensate +
			"["+0+","+0+","+0+","+0+","+0+","+0+","+0+","+condPagSLegaliParzCompensate+","+0+","+0+","+0+","+0+","+0+","+0+"]}," +
			labelAvvDistrattario +
			"["+0+","+0+","+0+","+0+","+0+","+0+","+0+","+0+","+soggRicPag1+","+0+","+0+","+0+","+0+","+0+"]}," +
			labelAvvNoDistrattarioNoDelega +
			"["+0+","+0+","+0+","+0+","+0+","+0+","+0+","+0+","+soggRicPag2+","+0+","+0+","+0+","+0+","+0+"]}," +
			labelAvvNoDistrattarioConDelega +
			"["+0+","+0+","+0+","+0+","+0+","+0+","+0+","+0+","+soggRicPag3+","+0+","+0+","+0+","+0+","+0+"]}" +
			"]}";

		}
		return toReturn;

	}


	public static String buildChartEsecuzioneProvvedimentiPrecetto(List<Object> list) {
		String toReturn="";
		int tot= list.size();
		
		int dataSpeseLegaliPrecVal=0;
		int dataComPrecSpeseLVal=0;
		int costoPrecSpeseL=0;
		
		int dataSpeseLegaliPrecCtuVal=0;
		int dataComPrecSpeseCtuVal=0;
		int costoPrecSpeseCtu=0;
		
		int dataPrecPrestazioneVal=0;
		int dataComPrecPresVal=0;
		int costoPrecPrestVal=0;
		
		for(Object o: list){
			try{
				AtpoFaseEsecuzioneProvvedimenti ai = (AtpoFaseEsecuzioneProvvedimenti) o;
				
				
				if(ai.getDataSpeseLegaliPrec()!=null){
					dataSpeseLegaliPrecVal++;
				}
				if(ai.getDataComPreSl()!=null){
					dataComPrecSpeseLVal++;
				}
				if(ai.getCostoPreSl()!=null){
					costoPrecSpeseL++;
				}
				if(ai.getDataSpeseCtuPrec()!=null){
					dataSpeseLegaliPrecCtuVal++;
				}
				if(ai.getDataComPreSctu()!=null){
					dataComPrecSpeseCtuVal++;
				}
				if(ai.getCostoPreSctu()!=null){
					costoPrecSpeseCtu++;
				}
				if(ai.getDataPrestazione()!=null){
					dataPrecPrestazioneVal++;
				}
				if(ai.getDataComPrePrest()!=null){
					dataComPrecPresVal++;
				}
				if(ai.getCostoPrePrest()!=null){
					costoPrecPrestVal++;
				}
				toReturn="{\"labels\": [\"Data spese legali precetto\", \"Data comunicazione precetto spese legali\", \"Costo precetto spese legali\"," +
				"\"Data precetto per spese legali CTU\", \"Data comunicazione precetto spese CTU\", \"Costo precetto spese legali\", " +
				"\"Data precetto per la prestazione\",\"Data comunicazione precetto prestazione\",\"Costo precetto prestazione\"], " +
				"\"datasets\":[" +
				labelVal +
				"["+dataSpeseLegaliPrecVal+","+dataComPrecSpeseLVal+","+costoPrecSpeseL+","+dataSpeseLegaliPrecCtuVal+","+dataComPrecSpeseCtuVal+","+costoPrecSpeseCtu+","+dataPrecPrestazioneVal+","+dataComPrecPresVal+","+costoPrecPrestVal+"]}," +
				labelNoVal +
				"["+(tot-dataSpeseLegaliPrecVal)+","+(tot-dataComPrecSpeseLVal)+","+(tot-costoPrecSpeseL)+","+(tot-dataSpeseLegaliPrecCtuVal)+","+(tot-dataComPrecSpeseCtuVal)+","+(tot-costoPrecSpeseCtu)+","+(tot-dataPrecPrestazioneVal)+","+(tot-dataComPrecPresVal)+","+(tot-costoPrecPrestVal)+"]}" +
				"]}";
				
				
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return toReturn;
	}


	public static String buildChartEsecuzioneProvvedimentiPignoramento(List<Object> list) {
		String toReturn="";
		int tot= list.size();
		
		int dataSpeseLegaliPignVal=0;
		int costoPignVal=0;
		
		int dataSpeseLegaliPignCtuVal=0;
		int costoPignSpeseCtu=0;
		
		int dataPignPrestazioneVal=0;
		int costoPignPrestVal=0;
		
		for(Object o: list){
			try{
				AtpoFaseEsecuzioneProvvedimenti ai = (AtpoFaseEsecuzioneProvvedimenti) o;
				
				
				if(ai.getDataSpeseLegaliPign()!=null){
					dataSpeseLegaliPignVal++;
				}
				if(ai.getCostoPignSl()!=null){
					costoPignVal++;
				}
				if(ai.getDataSpeseCtuPign()!=null){
					dataSpeseLegaliPignCtuVal++;
				}
				if(ai.getCostoPignSctu()!=null){
					costoPignSpeseCtu++;
				}
				if(ai.getDataPignoramentoPres()!=null){
					dataPignPrestazioneVal++;
				}
				if(ai.getCostoPignPrest()!=null){
					costoPignPrestVal++;
				}
				
				toReturn="{\"labels\": [\"Data spese legali pignoramento\", \"Costo pignoramento prestazione\"," +
				"\"Data pignoramento per spese legali CTU\", \"Costo pignoramento spese CTU\", " +
				"\"Data pignoramentoper la prestazione\",\"Costo pignoramento prestazione\"], " +
				"\"datasets\":[" +
				labelVal +
				"["+dataSpeseLegaliPignVal+","+costoPignVal+","+dataSpeseLegaliPignCtuVal+","+costoPignSpeseCtu+","+dataPignPrestazioneVal+","+costoPignPrestVal+"]}," +
				labelNoVal +
				"["+(tot-dataSpeseLegaliPignVal)+","+(tot-costoPignVal)+","+(tot-dataSpeseLegaliPignCtuVal)+","+(tot-costoPignSpeseCtu)+","+(tot-dataPignPrestazioneVal)+","+(tot-costoPignPrestVal)+"]}" +
				"]}";
				
				
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return toReturn;
	}


	public static String buildChartEsecuzioneProvvedimentiDissenso(List<Object> list) {
		String toReturn="";
		int tot= list.size();
		
		int dataDecretoLiqCtuVal=0;
		int condanPagCtuSi=0;
		int condanPagCtuNo=0;
		int dataFatturaVal=0;
		int dataLiqCtu=0;
		
		int condannaPagCtuPGSi=0;
		int condannaPagCtuPGNo=0;
		
		int verificaPagCtuEff1=0;//si
		int verificaPagCtuEff2=0;//no
		int verificaPagCtuEff3=0;//non necessario
		
		
		int impSpeseCtuPagateVal=0;
		int impSpeseCtuDovuteVal=0;
		
		for(Object o: list){
			try{
				AtpoFaseEsecuzioneProvvedimenti ai = (AtpoFaseEsecuzioneProvvedimenti) o;
			
				if(ai.getImpSpeseCtuPagate()!=null){
					impSpeseCtuPagateVal++;
				}
				if(ai.getImpSpeseCtuDovute()!=null){
					impSpeseCtuDovuteVal++;
				}
				
				if(ai.getDataDecrLiqCtu()!=null){
					dataDecretoLiqCtuVal++;
				}
				if(ai.getCondannaPagCtuAtpo()!=null && ai.getCondannaPagCtuAtpo().trim().equalsIgnoreCase("s")){
					condanPagCtuSi++;
				}else{
					condanPagCtuNo++;
				}
				if(ai.getDataFattura()!=null){
					dataFatturaVal++;
				}
				if(ai.getDataLiqCtuAtpo()!=null){
					dataLiqCtu++;
				}
				
				if(ai.getCondannaPagCtu1g()!=null && ai.getCondannaPagCtu1g().trim().equalsIgnoreCase("s")){
					condannaPagCtuPGSi++;
				}else{
					condannaPagCtuPGNo++;
				}
				
				if(ai.getVerPagCtuEff()!=null && Integer.parseInt(ai.getVerPagCtuEff().trim())==1){
					verificaPagCtuEff1++;
				}
				if(ai.getVerPagCtuEff()!=null && Integer.parseInt(ai.getVerPagCtuEff().trim())==2){
					verificaPagCtuEff2++;
				}
				if(ai.getVerPagCtuEff()!=null && Integer.parseInt(ai.getVerPagCtuEff().trim())==3){
					verificaPagCtuEff3++;
				}
				
				
				toReturn="{\"labels\": [\"Data decreto liquidazione CTU\", \"Condanna a pagamento CTU ATPO\", \"Data fattura\"," +
				"\"Data liquidazione CTU ATPO\", \"Condanna a pagamento CTU 1 grado\", \"Verifica pagamento CTU già effettuato\", " +
				"\"Importo spese CTU pagate\",\"Importo spese CTU dovute\"], " +
				"\"datasets\":[" +
				
				labelVal +
				"["+dataDecretoLiqCtuVal+","+0+","+dataFatturaVal+","+dataLiqCtu+","+0+","+0+","+impSpeseCtuPagateVal+","+impSpeseCtuDovuteVal+"]}," +
				labelNoVal +
				"["+(tot-dataDecretoLiqCtuVal)+","+0+","+(tot-dataFatturaVal)+","+(tot-dataLiqCtu)+","+0+","+0+","+(tot-impSpeseCtuPagateVal)+","+(tot-impSpeseCtuDovuteVal)+"]}," +
				labelSi +
				"["+0+","+condanPagCtuSi+","+0+","+0+","+condannaPagCtuPGSi+","+verificaPagCtuEff1+","+0+","+0+"]}," +
				labelNo +
				"["+0+","+condanPagCtuNo+","+0+","+0+","+condannaPagCtuPGNo+","+verificaPagCtuEff2+","+0+","+0+"]}," +
				labelnonNecessario +
				"["+0+","+0+","+0+","+0+","+0+","+verificaPagCtuEff3+","+0+","+0+"]}" +
				"]}";
				
				
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return toReturn;
	}


	public static String buildChartRiepilogoFascicolo(List<Object> list) {
		String toReturn="";
		int tot= list.size();
		
		int fascicolo1=0;//completo
		int fascicolo2=0;//si completo
		int fascicolo3=0;//si incompleto
		int fascicolo4=0;//non presente valore
		
		int allegatiSi=0;
		int allegatiNo=0;
		long lastid = 0;
		long currentid = 0;
		for(Object o: list){
			try{
				AtpoRiepilogoFascicolo ai = (AtpoRiepilogoFascicolo) o;
				lastid=ai.getIdRiepilogoFascicolo();
				if(currentid!= lastid){
					if(ai.getFascicoloElettronico()!=null && Integer.parseInt(ai.getFascicoloElettronico().trim())==1){
						fascicolo1++;	
					}
					if(ai.getFascicoloElettronico()!=null && Integer.parseInt(ai.getFascicoloElettronico().trim())==2){
						fascicolo2++;	
					}
					if(ai.getFascicoloElettronico()!=null && Integer.parseInt(ai.getFascicoloElettronico().trim())==3){
						fascicolo3++;	
					}
					if(ai.getFascicoloElettronico()==null ){
						fascicolo4++;	
					}
					if(ai.getDettDocMancante()!=null && ai.getDettDocMancante().trim().equals("")){
						allegatiNo++;
					}
					else{
						allegatiSi++;
					}
					currentid=ai.getIdRiepilogoFascicolo();
				}
				
				
				
				toReturn="{\"labels\": [\"Fascicolo\", \"Documentazione mancante\"], " +
				"\"datasets\":[" +
				labelSiCompleto+
				"["+fascicolo2+","+0+"]}," +
				labelSiIncompleto+
				"["+fascicolo3+","+0+"]}," +
				labelInesistente+
				"["+fascicolo1+","+0+"]}," +
				labelNonDefinito+
				"["+fascicolo4+","+0+"]}," +
				labelSi+
				"["+0+","+allegatiSi+"]}," +
				labelNo+
				"["+0+","+allegatiNo+"]}" +
				"]}";
				
				
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return toReturn;
	}




}
