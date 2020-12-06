package it.tecnet.crs.indicatori;


import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import it.tecnet.crs.jpa.model.AuNonConformita;
import it.tecnet.crs.util.ApplicationUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

public class CalcoloIndicatori {
	public static int NO_RESULT= -100;

	public static int MANCATOESITOECONOMICO=1;
	public static int CREDITOINDEBITAMENTESOSPESO=2;
	public static int ERRATOESITO=3;
	public static int MANCATOINVIOUL13=4;
	public static int TARDIVOINVIOUL13=5;
	public static int INFASAMENTOTARDIVODECADENZARUOLO=6;
	public static int MANCATATRASMISSIONEUFFLEG=7;
	public static int MANCATOINVIOCOMUNICAZIONE=8;
	public static int INVIOCOMUNICAZIONEMODALITADIVERSE=9;	
	public static int MANCATANOTIFICAVERBALE=10;
	public static int ERRATANOTIFICAERRATODESTINATARIO=11;
	public static int TARDIVANOTIFICA=12;
	public static int MANCATACONSEGNAFASCICOLOCARTACEO=13;
	public static int CONSEGNAFASCICOLOCARTACEOASSENZATRASMISSIONE=14;
	public static int TARDIVACONSEGNA=15;
	public static int MANCATOINSDATANOTIFICA=16;
	public static int ERRATOINSDATANOTIFICA=17;
	public static int TARDIVOINSDATANOTIFICANODECRUOLO=18;
	public static int INSDATANOTIFICAPERNOTIFICANONEFFETTUATA=19;

	protected static Logger log = Logger.getLogger(CalcoloIndicatori.class);

	/********************************************************************************
	 * 
	 * 						FASE      REGOLAZIONE
	 * 
	 *********************************************************************************/

	//	IF (ESITO_FLUSSO>0) then
	//		INCC=1
	//	ELSE
	//	    INCC=-0.2
	//	END IF	
	/*
	 ESITO_FLUSSO*/

	public double mancatoEsitoEconomico(AuNonConformita nc,int esitoFlusso){//soglia =0
		if(esitoFlusso>0 && esitoFlusso > Integer.parseInt(nc.getSoglia1())) 
			return 1;

		return -0.2;
	}

	//	IF (CODICE_SOSPENSIONE>0) then
	//    IF (MOTIVO_SOSPENSIONE="") then
	//        INCC=-0.2
	//    ELSE
	//        INCC=1
	//    END IF
	//  END IF
	/*CODICE_SOSPENSIONE
		MOTIVO_SOSPENSIONE*/

	public double creditoIndebitamenteSospeso(AuNonConformita nc,String codiceSospensione, String motivoSospensione){
		if(!StringUtils.isEmpty(codiceSospensione)){
			if(Integer.parseInt(codiceSospensione) > Integer.parseInt(nc.getSoglia1()) && StringUtils.isEmpty(motivoSospensione)) return -0.2;
			return 1;
		}
		return 0;
	}

	//	IF (ESITO_FLUSSO>0 OR ESITO_REALE>0) then
	//    IF (ESITO_REALE=ESITO_FLUSSO) then
	//        INCC=1
	//    ELSE
	//        INCC=-0.2
	//    END IF
	//  END IF
	/*ESITO_FLUSSO
		ESITO_REALE*/

	public double erratoEsito(AuNonConformita nc,Integer esitoFlusso, Integer esitoReale){
		if(esitoFlusso==null || esitoReale==null) return NO_RESULT;
		if(esitoFlusso>0 || esitoReale>0){

			if(esitoFlusso > Integer.parseInt(nc.getSoglia1()) && esitoReale > Integer.parseInt(nc.getSoglia1())){
				if(esitoFlusso==esitoReale) return 1;
			}
			return -0.2;
		}
		return 0;
	}

	//	IF (DATA_FALLIMENTO>0) then
	//    IF (DATA_INVIO_UL13 = 0) then
	//        INCC = -0.2
	//    ELSE
	//        INCC = 1
	//    END IF
	//  END IF
	/*DATA_FALLIMENTO
		DATA_INVIO_UL13*/

	public double mancatoInvioUL13(Date dataFallimento, Date dataInvioUL13){
		if(dataFallimento != null){
			if(dataInvioUL13 == null)
				return -0.2;
			else
				return 1;
		}
		return 0;
	}

	//	IF (DATA_FALLIMENTO>0) then
	//    IF (DATA_INVIO_UL13 > 0) then
	//        IF (DATA_INVIO_UL13 > MAX(DATA_NOTIFICA,DATA_FALLIMENTO) + 1 anno) then
	//            INCC = -0.2
	//        ELSE
	//            INCC = 1
	//        END IF
	//    ELSE
	//        IF (DATA_AUDIT > MAX(DATA_NOTIFICA,DATA_FALLIMENTO) + 1 anno) then
	//            INCC = -0.2
	//        END IF
	//    END IF
	//  END IF
	/*DATA_FALLIMENTO
		DATA_INVIO_UL13
		DATA_NOTIFICA*/

	public double tardivoInvioUL13(Date dataFallimento, Date dataInvioUL13, Date dataNotifica, Date dataAudit){
		Calendar dataMaxPiuUno = Calendar.getInstance();
		Calendar dataInvioUL13C = Calendar.getInstance();
		Calendar dataAuditC = Calendar.getInstance();


		Date maxDate=null;
		if (dataInvioUL13!=null)
			dataInvioUL13C.setTime(dataInvioUL13);
		if (dataAudit!=null)
			dataAuditC.setTime(dataAudit);
		try{
			if(dataFallimento!= null){
				if(dataNotifica.before(dataFallimento))
					maxDate=dataFallimento;
				else
					maxDate=dataNotifica;

				dataMaxPiuUno.setTime(maxDate);
				dataMaxPiuUno.add(Calendar.YEAR, 1);


				if(dataInvioUL13!= null){
					if(dataInvioUL13C.after(dataMaxPiuUno))
						return -0.2;
					return 1;
				}else{				
					if(dataAuditC.after(dataMaxPiuUno)){						
						return -0.2;
					}
				}						
			}
			return 0;
		}catch( Exception e ){
			log.error("Errore tardivoInvioUL13. Errore:" + e.getMessage(), e);
			return NO_RESULT;
		}
	}

	//	IF (DATA_INFASAMENTO>0) then
	//    IF (YEAR(DATA_INFASAMENTO)>YEAR(DATA_NOTIFICA)+1) then
	//        INCC=-0.2
	//    ELSE
	//        INCC=1
	//    END IF
	/*DATA_INFASAMENTO
		DATA_NOTIFICA*/

	public double infasamentoTardivoDecadenzaRuolo(Date dataInfasamento, Date dataNotifica){
		Calendar data = Calendar.getInstance();




		if(dataInfasamento!= null){
			if(dataNotifica== null) return NO_RESULT;
			data.setTime(dataInfasamento);
			int yearIn=data.get(Calendar.YEAR);		
			data.setTime(dataNotifica);
			int yearNoPiuUno=data.get(Calendar.YEAR) +1;
			if(yearIn>yearNoPiuUno)
				return -0.2;
			return 1;	
		}
		return 0;		
	}


	//	IF (DATA_INFASAMENTO=0 AND DATA_TRASMISSIONE_LEGALE=0) then
	//    IF (YEAR(DATA_AUDIT)>YEAR(DATA_NOTIFICA)+1) then
	//        INCC=-0.2
	//    ELSE
	//        INCC=1
	//    END IF
	/*DATA_INFASAMENTO
		DATA_TRASMISIONE_LEGALE
		DATA_AUDIT
		DATA_NOTIFICA*/

	public double mancataTrasmissioneUffLeg(Date dataInfasamento, Date dataTrasmissioneLegale, Date dataAudit, Date dataNotifica){
		Calendar data = Calendar.getInstance();

		if(dataAudit==null || dataNotifica==null) return NO_RESULT;
		
		data.setTime(dataAudit);
		int yearAu=data.get(Calendar.YEAR);		
		data.setTime(dataNotifica);
		int yearNoPiuUno=data.get(Calendar.YEAR) +1;	

		if(dataInfasamento== null && dataTrasmissioneLegale== null) {
			if(yearAu>yearNoPiuUno)
				return -0.2;
			return 1;	
		}
		return 0;		
	}

	//	IF (NUMERO_COMUNICAZIONI_INVIATE=NUMERO_COMUNICAZIONI_DA_INVIARE) then
	//    	INCC=1
	//    ELSE
	//    	INCC=-0.2
	//    END IF
	//  
	/*NUMERO_COMUNICAZIONI_INVIATE
		NUMERO_COMUNICAZIONI_DA_INVIARE*/

	public double mancatoInvioComunicazione(Long nComunicInviate, Long nComunicDaInviare){
		if(nComunicInviate==null || nComunicDaInviare==null) return NO_RESULT;
		if (nComunicInviate==nComunicDaInviare)
			return 1;
		return -0.2;

	}

	/********************************************************************************
	 * 
	 * 						FASE      NOTIFICA
	 * 
	 *********************************************************************************/


	//	IF (DATA_NOTIFICA=0) then
	//    	IF (DATA_AUDIT - MAX(DATA_FINE_ISPEZIONE,DATA_ATTI_INTERRUTTIVI) > 5*365) then
	//        	INCC = -0.2
	//        ELSE IF (DATA_AUDIT-DATA_FINE_ISPEZIONE > 90) then 
	//        	INCC=-0.05
	//        ELSE
	//        	INCC = 1
	//        END IF
	//    END IF
	/*DATA_NOTIFICA
		DATA_AUDIT
		DATA_FINE_ISPEZIONE
		DATA_ATTI_INTERRUTTIVI*/

	public double mancataNotificaVerbale(Date dataNotifica, Date dataAudit, Date dataFineIspezione, Date dataAttiInterruttivi){
		Date maxDate=null;

		if(dataNotifica==null){
			if(dataAttiInterruttivi==null || dataFineIspezione==null) return NO_RESULT;
			
			if(dataAttiInterruttivi.before(dataFineIspezione))
				maxDate=dataFineIspezione;
			else
				maxDate=dataAttiInterruttivi;

			GregorianCalendar dataAuditG = new GregorianCalendar();
			GregorianCalendar maxDateG = new GregorianCalendar();
			GregorianCalendar dataFineIspezioneG = new GregorianCalendar();
			dataAuditG.setTime(dataAudit);
			dataFineIspezioneG.setTime(dataFineIspezione);
			maxDateG.setTime(maxDate);

			long dataAuditGMS = dataAuditG.getTimeInMillis();
			long maxDateGMS = maxDateG.getTimeInMillis();
			long dataFineIspGMS = dataFineIspezioneG.getTimeInMillis();
			long giorno=1000*60*60*24; 
			long cinqueAnniMS = giorno*365*5;
			long novantaGgMS = giorno*90;

			if(dataAuditGMS-maxDateGMS > cinqueAnniMS) 
				return -0.2;
			else if (dataAuditGMS-dataFineIspGMS>novantaGgMS)
				return -0.05;	
			else
				return 1;	
		}
		return 0;

	}


	//	IF (TIPO_NOTIFICA='PEC') then    
	//	    IF (INDIRIZZO_PEC_VW = INDIRIZZO_PEC_REALE) then
	//	        INCC = 1
	//	    ELSE
	//	        INCC = -0.2
	//	    END IF
	//    END IF
	/*TIPO_NOTIFICA
	INDIRIZZO_PEC_VW
	INDIRIZZO_PEC_REALE*/

	//	IF (TIPO_NOTIFICA='RACCOMANDATA') then    
	//	    IF (DESTINATARIO_VW = DESTINATARIO_REALE) then
	//	        INCC = 1
	//	    ELSE
	//	        INCC = -0.2
	//	    END IF
	//    END IF
	/*TIPO_NOTIFICA
	DESTINATARIO_VW
	DESTINATARIO_REALE*/


	public double errataNotificaErratoDestinatario(String tipoNotifica,String indirizzoPecVW, String indirizzoPecReale,String indirizzoVW, String indirizzoReale){
		if(tipoNotifica==null || indirizzoPecVW==null || indirizzoPecReale == null || tipoNotifica== null || indirizzoVW == null) return NO_RESULT;
		if(tipoNotifica.equals("PEC")){
			if(indirizzoPecVW.equalsIgnoreCase(indirizzoPecReale))
				return 1;
			return -0.2;		
		}
		else if (tipoNotifica.equals("RACCOMANDATA")){
			if(indirizzoVW.equalsIgnoreCase(indirizzoReale))
				return 1;
			return -0.2;	
		}
		return 0;	
	}


	//	IF (DATA_NOTIFICA>0) then
	//    	IF (DATA_NOTIFICA - MAX(DATA_FINE_ISPEZIONE,DATA_ATTI_INTERRUTTIVI) > 5*365) then
	//    		INCC = -0.2
	//    	ELSE IF (DATA_NOTIFICA-DATA_FINE_ISPEZIONE > 90) then 
	//        	INCC=-0.05
	//        ELSE
	//        	INCC=1
	//        END IF
	//    END IF
	/*DATA_NOTIFICA
		DATA_FINE_ISPEZIONE
		DATA_ATTI_INTERRUTTIVI*/

	public double tardivaNotifica(Date dataNotifica,Date dataFineIspezione,Date dataAttiInterruttivi){
		Date maxDate=null;

		if(dataNotifica!=null){
			if(dataFineIspezione==null && dataAttiInterruttivi == null)
				return 0;
			if (dataFineIspezione==null)
				maxDate=dataAttiInterruttivi;
			else if (dataAttiInterruttivi==null)
				maxDate=dataFineIspezione;
			else{
				if(dataFineIspezione.before(dataAttiInterruttivi))
					maxDate=dataAttiInterruttivi;
				else
					maxDate=dataFineIspezione;
			}

			GregorianCalendar maxDateG = new GregorianCalendar();
			GregorianCalendar dataFineIspezioneG = new GregorianCalendar();
			GregorianCalendar dataNotificaG = new GregorianCalendar();
			dataFineIspezioneG.setTime(dataFineIspezione);
			dataNotificaG.setTime(dataNotifica);
			maxDateG.setTime(maxDate);

			long maxDateMS = maxDateG.getTimeInMillis();
			long dataFineIspMS = dataFineIspezioneG.getTimeInMillis();
			long dataNotMS = dataNotificaG.getTimeInMillis();			
			long giorno=1000*60*60*24; 
			long cinqueAMS = giorno*365*5;
			long novantaGgMS = giorno*90;

			if(dataNotMS-maxDateMS > cinqueAMS) 
				return -0.2;
			else if (dataNotMS-dataFineIspMS>novantaGgMS)
				return -0.05;	
			else
				return 1;	
		}
		return 0;	
	}

	//	IF (DATA_INSERIMENTO_POSIZIONE>0) then
	//    	IF (DATA_TRASMISIONE=0 AND DATA_RACCOMANDATA=0) then 
	//        	INCC = -0.2
	//        ELSE
	//        	INCC=1
	//        END IF
	//  END IF
	/*DATA_INSERIMENTO_POSIZIONE
		DATA_TRASMISSIONE
		DATA_RACCOMANDATA*/

	public double mancataConsegnaFascicoloCartaceo(Date dataInserimentoPosizione,Date dataTrasmissione,Date dataRaccomandata){
		if(dataInserimentoPosizione!=null){
			if(dataTrasmissione==null&&dataRaccomandata==null)
				return -0.2;
			return 1;		
		}
		return 0;
	}

	//	IF (DATA_TRASMISIONE>0 OR DATA_RACCOMANDATA>0) then
	//    	IF (DATA_INSERIMENTO_POSIZIONE=0) then 
	//    		INCC = -0.2
	//    	ELSE
	//    		INCC=1
	//    	END IF
	//    END IF
	/*DATA_INSERIMENTO_POSIZIONE
		DATA_TRASMISSIONE
		DATA_RACCOMANDATA*/

	public double consegnaFascicoloCartaceoAssenzaTrasmissione(Date dataInserimentoPosizione,Date dataTrasmissione,Date dataRaccomandata){
		if(dataTrasmissione!=null||dataRaccomandata!=null){
			if(dataInserimentoPosizione==null)
				return -0.2;
			return 1;		
		}
		return 0;

	}

	//	IF (DATA_NOTIFICA>0) then
	//	    IF (DATA_TRASMISSIONE>0) then
	//	        IF (DATA_TRASMISSIONE-DATA_NOTIFICA>30) then
	//	            INCC = -0.05
	//	        ELSE
	//	            INCC = 1
	//	        END IF
	//	    ELSE IF (DATA_RACCOMANDATA>0) then
	//	        IF (DATA_RACCOMANDATA-DATA_NOTIFICA>30) then
	//	            INCC = -0.05
	//	        ELSE
	//	            INCC = 1
	//	        END IF
	//	    END IF
	//	END IF
	/*DATA_NOTIFICA
		DATA_TRASMISSIONE
		DATA_RACCOMANDATA*/
	public double tardivaConsegna(Date dataNotifica,Date dataTrasmissione,Date dataRaccomandata){
		GregorianCalendar dataTrasmissioneG = new GregorianCalendar();
		GregorianCalendar dataNotificaG = new GregorianCalendar();
		GregorianCalendar dataRaccomandataG = new GregorianCalendar();
		
		if(dataTrasmissione==null || dataNotifica==null || dataRaccomandata == null) return NO_RESULT;
		
		dataTrasmissioneG.setTime(dataTrasmissione);
		dataNotificaG.setTime(dataNotifica);
		dataRaccomandataG.setTime(dataRaccomandata);

		long dataTrasmMS = dataTrasmissioneG.getTimeInMillis();
		long dataNotMS = dataNotificaG.getTimeInMillis();			
		long dataRaccMS = dataRaccomandataG.getTimeInMillis();			
		long giorno=1000*60*60*24; 
		long trentaGgMS = giorno*30;

		if(dataNotifica!=null){
			if(dataTrasmissione!=null){
				if(dataTrasmMS-dataNotMS>trentaGgMS)
					return -0.05;
				return 1;
			}else if (dataRaccomandata != null){
				if(dataRaccMS-dataNotMS>trentaGgMS)
					return -0.05;
				return 1;
			}
		}
		return 0;

	}

	//	IF (DATA_NOTIFICA_VW=0 AND (DATA_TRIBUNALE>0 OR DATA_AFFISSIONE>0 OR DATA_GIACENZA>0 OR DATA_RICEVUTA>0 OR DATA_CONSEGNA_MANO>0)) then
	//    	INCC = -0.2
	//    ELSE
	//    	INCC = 1
	//    END IF
	/*DATA_NOTIFICA_VW
		DATA_TRIBUNALE
		DATA_AFFISSIONE
		DATA_GIACENZA
		DATA_RICEVUTA
		DATA_CONSEGNA_MANO*/
	public double mancatoInsDataNotifica (Date dataNotificaVW,Date dataTribunale,Date dataAffissione,Date dataGiacenza,Date dataRicevuta,Date dataConsegnaMano){
		if(dataNotificaVW==null &&(dataTribunale!=null ||dataAffissione!=null||dataGiacenza!=null||dataRicevuta!=null||dataConsegnaMano!=null))
			return 0.2;
		return 1;	
	}

	//    IF (DATA_RICEVUTA>0) then
	//    	IF (DATA_RICEVUTA = DATA_NOTIFICA_VW) then
	//    		INCC = 1
	//    	ELSE
	//        	INCC = -0.2
	//        END IF
	//    END IF    

	//    IF (DATA_GIACENZA>0) then
	//	    IF (DATA_GIACENZA = DATA_NOTIFICA_VW) then
	//	        INCC = 1
	//	    ELSE
	//	        INCC = -0.2
	//	    END IF
	//    END IF	

	//    IF (DATA_AFFISSIONE>0) then
	//	    IF (DATA_AFFISSIONE = DATA_NOTIFICA_VW) then
	//	        INCC = 1
	//	    ELSE
	//	        INCC = -0.2
	//	    END IF
	//    END IF

	//    IF (DATA_TRIBUNALE>0) then
	//	    IF (DATA_TRIBUNALE = DATA_NOTIFICA_VW) then
	//	        INCC = 1
	//	    ELSE
	//	        INCC = -0.2
	//	    END IF
	//    END IF  

	//    IF (DATA_CONSEGNA_MANO>0) then
	//	    IF (DATA_CONSEGNA_MANO = DATA_NOTIFICA_VW) then
	//	        INCC = 1
	//	    ELSE
	//	        INCC = -0.2
	//	    END IF
	//    END IF   
	/*DATA_NOTIFICA_VW
		DATA_RICEVUTA
		DATA_GIACENZA
		DATA_AFFISSIONE
		DATA_TRIBUNALE
		DATA_CONSEGNA_MANO
	 */
	public double erratoInsDataNotifica (Date dataNotificaVW, Date dataRicevuta,Date dataGiacenza,Date dataAffissione,Date dataTribunale,Date dataConsegnaMano){  
		if(dataRicevuta!=null && dataNotificaVW==null){
			if(dataRicevuta.equals(dataNotificaVW)){
				return 1;
			}else {
				return -0.2;
			}
		}else if(dataGiacenza!=null && dataNotificaVW==null){
			if(dataGiacenza.equals(dataNotificaVW)){
				return 1;
			}else {
				return -0.2;
			}
		}else if(dataAffissione!=null && dataNotificaVW==null){
			if(dataAffissione.equals(dataNotificaVW)){
				return 1;
			}else {
				return -0.2;
			}
		}else if(dataTribunale!=null && dataNotificaVW==null){
			if(dataTribunale.equals(dataNotificaVW)){
				return 1;
			}else {
				return -0.2;
			}
		}else if(dataConsegnaMano!=null && dataNotificaVW==null){
			if(dataConsegnaMano.equals(dataNotificaVW)){
				return 1;
			}else {
				return -0.2;
			}
		}
		return NO_RESULT;	
	}

	//     IF (YEAR(DATA_INSERIMENTO_NOTIFICA)>YEAR(DATA_NOTIFICA)+1) then
	//     	INCC = -0.2
	//     ELSE IF (DATA_INSERIMENTO_NOTIFICA-DATA_NOTIFICA>90) then
	//     	INCC = -0.05
	//     ELSE 
	//     	INCC = 1
	//     END IF
	/*DATA_INSERIMENTO_NOTIFICA
		DATA_NOTIFICA*/

	public double tardivoInsDataNotificaNoDecRuolo (Date dataInsNotifica, Date dataNotifica){
		GregorianCalendar dataInsNotificaG = new GregorianCalendar();
		GregorianCalendar dataNotificaG = new GregorianCalendar();

		if(dataInsNotifica==null || dataNotifica == null) return NO_RESULT;
		
		dataInsNotificaG.setTime(dataInsNotifica);
		dataNotificaG.setTime(dataNotifica);

		long dataInsNotMS = dataInsNotificaG.getTimeInMillis();		//1537186980463
		long dataNotMS = dataNotificaG.getTimeInMillis();			//1537186996346
		long giorno=1000*60*60*24; 
		long novantaGgMS = giorno*90;

		Calendar data=Calendar.getInstance();
		data.setTime(dataInsNotifica);
		int annoInsNot=data.get(Calendar.YEAR);
		data.setTime(dataNotifica);
		int annoNot=data.get(Calendar.YEAR);

		if(annoInsNot > (annoNot +1))
			return -0.2;
		else if (dataInsNotMS-dataNotMS>novantaGgMS)
			return 0.05;
		else
			return 1;

	}

	//     IF (DATA_NOTIFICA_VW>0 AND DATA_TRIBUNALE=0 AND DATA_AFFISSIONE=0 AND DATA_GIACENZA=0 AND DATA_RICEVUTA=0 AND DATA_CONSEGNA_MANO=0) then
	//     	INCC=-0.2
	//     ELSE
	//     	INCC = 1
	//     END IF

	/*DATA_NOTIFICA_VW
	     DATA_TRIBUNALE
	     DATA_AFFISSIONE
	     DATA_GIACENZA
	     DATA_RICEVUTA
	     DATA_CONSEGNA_MANO*/

	public double insDataNotificaPerNotificaNonEffettuata(Date dataNotificaVW,Date dataTribunale, Date dataAffissione,Date dataGiacenza,Date dataRicevuta, Date dataConsegnaMano){		
		if(dataNotificaVW!=null &&(dataTribunale==null && 
				dataAffissione==null &&
				dataGiacenza==null &&
				dataRicevuta==null &&
				dataConsegnaMano==null))
			return 0.2;
		return 0;	
	}



}
