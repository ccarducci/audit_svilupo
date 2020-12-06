package it.tecnet.crs.util;

import it.tecnet.crs.indicatori.pratica.CalcoloIndicatoriErrore;
import it.tecnet.crs.jpa.model.AuMRischio;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.log4j.Logger;

public class PraticaUtil {

	protected static Logger log = Logger.getLogger(PraticaUtil.class);

	public static final String MESS_CONTROLLO_BEFORE_CALCOLO = "Non ci sono informazioni per calcolare gli indicatori della pratica";
	
	public static final String DECRETO_OMOLOGATO_SI = "SI";
	public static final String DECRETO_OMOLOGATO_NO = "NO";

	public static final String R001 = "R001";
	public static final String R002 = "R002";
	public static final String R003 = "R003";
	public static final String R004 = "R004";
	public static final String R005 = "R005";
	public static final String R006 = "R006";
	public static final String R007 = "R007";
	
	
	public static final String E001 = "E001";
	public static final String E002 = "E002";
	public static final String E003 = "E003";
	public static final String E004 = "E004";
	public static final String E005 = "E005";
	public static final String E006 = "E006";
	public static final String E007 = "E007";
	public static final String E008 = "E008";
	public static final String E009 = "E009";
	public static final String E010 = "E010";
	public static final String E011 = "E011";
	public static final String E012 = "E012";
	public static final String E013 = "E013";
	public static final String E014 = "E014";
	public static final String E015 = "E015";
	public static final String E016 = "E016";
	public static final String E017 = "E017";
	public static final String E018 = "E018";
	public static final String E019 = "E019";
	public static final String E020 = "E020";
	public static final String E021 = "E021";
	public static final String E022 = "E022";
	public static final String E023 = "E023";
	public static final String E024 = "E024";
	public static final String E025 = "E025";
	public static final String E026 = "E026";
	public static final String E027 = "E027";
	public static final String E028 = "E028";
	public static final String E029 = "E029";
	public static final String E030 = "E030";
	public static final String E031 = "E031";
	public static final String E032 = "E032";
	public static final String E033 = "E033";
	public static final String E034 = "E034";
	public static final String E035 = "E035";
	public static final String E036 = "E036";
	public static final String E037 = "E037";
	public static final String E038 = "E038";
	public static final String E039 = "E039";
	public static final String E040 = "E040";
	public static final String E041 = "E041";
	public static final String E042 = "E042";
	public static final String E043 = "E043";
	
	public static Date getDateWithoutTimeUsingFormat(Date dateToFormat) {
		if (dateToFormat == null)
			return null;
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Date dataToReturn = formatter.parse(formatter.format(dateToFormat));
			return dataToReturn;
		} catch (ParseException e) {
			log.error("Errore formattazione per le date della pratica");
		}
		return null;
	}

	public static Long getIdMRischio(List<AuMRischio> listaMRischio, String er) {
		if (er == null || er.length() == 0)
			return null;
		for (AuMRischio auMRischio : listaMRischio) {
			if (er.trim().equals(auMRischio.getCodiceRischio().trim()))
				return auMRischio.getIdMRischio();
		}
		return null;
	}

	public static String checkString(String toCheck) {
		if (toCheck == null)
			return "";
		return toCheck;
	}

	public static String checkStringAndTrimUpperCaseSiNo(String toCheck) {
		if (toCheck == null)
			return "";
			StringBuilder strinbBuilder = new StringBuilder();
			strinbBuilder.append(toCheck.trim().toUpperCase());

			if (strinbBuilder.length() == 1){
				if(strinbBuilder.substring(0,1).equals("S")){
					strinbBuilder.append("I");
				}else if(strinbBuilder.substring(0,1).equals("N")){
					strinbBuilder.append("O");	
				}
			}
			
		return strinbBuilder.toString();
	}
	
	public static String checkStringAndTrimUpperCase(String toCheck) {
		if (toCheck == null)
			return "";
			StringBuilder strinbBuilder = new StringBuilder();
			strinbBuilder.append(toCheck.trim().toUpperCase());
			/*
			if (strinbBuilder.length() == 1){
				if(strinbBuilder.substring(0,1).equals("S")){
					strinbBuilder.append("I");
				}else if(strinbBuilder.substring(0,1).equals("N")){
					strinbBuilder.append("O");	
				}
			}
			*/
		return strinbBuilder.toString();
	}

	public static Double checkValueImp(BigDecimal numTocheck) {
		if (numTocheck == null)
			return 0d;
		return numTocheck.doubleValue();
	}

	public static Double checkValueImp(Double numTocheck) {
		if (numTocheck == null)
			return 0d;
		return numTocheck;
	}
	
	public static final String TIPO_DIFESA_U02 = "U02";
	public static final String TIPO_DIFESA_U03 = "U03";
	public static final String TIPO_DIFESA_U04 = "U04";
	
	public static final String SI = "SI";
	public static final String NO = "NO";
	
	public static final String VERO = "V";
	public static final String FALSO = "F";
	
	public static final String STATO_PROCESSO_S00 = "S00";

	public static final String USER_OWNER_SI = "S";
	public static final String USER_OWNER_NO = "N";
	
	public static final String ESAME_PRATICA_APERTO = "A";
	public static final String ESAME_PRATICA_IN_LAVORAZIONE = "L";
	public static final String ESAME_PRATICA_CHIUSO = "C";
	public static final String ESAME_PRATICA_ELABORAZIONE = "E";

	public static final String FALLIMENTO_SI = "SI";
	public static final String FALLIMENTO_NO = "NO";

	public static final String PRESENZA_OBBLIGATI_SOLIDALI_SI = "SI";
	public static final String PRESENZA_OBBLIGATI_SOLIDALI_NO = "NO";

	public static final String ERRORI_TIPO_RISCHIO_1 = "DETERMINAZIONE RISCHIO 1";
	public static final String ERRORI_TIPO_RISCHIO_2 = "DETERMINAZIONE RISCHIO 2";
	public static final String ERRORI_TIPO_RISCHIO_3 = "DETERMINAZIONE RISCHIO 3";
	public static final String ERRORI_TIPO_RISCHIO_4 = "DETERMINAZIONE RISCHIO 4";
	public static final String ERRORI_TIPO_RISCHIO_5 = "DETERMINAZIONE RISCHIO 5";
	public static final String ERRORI_TIPO_RISCHIO_6 = "DETERMINAZIONE RISCHIO 6";

	public static final String TIPO_NON_COMFORMITA_1 = "DETERMINAZIONE NON CONFORMITA 1";
	public static final String TIPO_NON_COMFORMITA_2 = "DETERMINAZIONE NON CONFORMITA 2";
	public static final String TIPO_NON_COMFORMITA_3 = "DETERMINAZIONE NON CONFORMITA 3";
	public static final String TIPO_NON_COMFORMITA_4 = "DETERMINAZIONE NON CONFORMITA 4";
	public static final String TIPO_NON_COMFORMITA_5 = "DETERMINAZIONE NON CONFORMITA 5";
	public static final String TIPO_NON_COMFORMITA_6 = "DETERMINAZIONE NON CONFORMITA 6";
	public static final String TIPO_NON_COMFORMITA_7 = "DETERMINAZIONE NON CONFORMITA 7";

	public static final String CREDITO_PRESCRTTO_TIPO_A1 = "A1";
	public static final String CREDITO_PRESCRTTO_TIPO_A2 = "A2";
	public static final String CREDITO_PRESCRTTO_TIPO_A3 = "A3";
	public static final String CREDITO_PRESCRTTO_TIPO_A4 = "A4";
	public static final String CREDITO_PRESCRTTO_TIPO_A5 = "A5";
	public static final String CREDITO_PRESCRTTO_TIPO_A6 = "A6";

	public static final String ESITO_REGOLARIZZAZIONE_R1 = "R1";
	public static final String ESITO_REGOLARIZZAZIONE_R2 = "R2";
	public static final String ESITO_REGOLARIZZAZIONE_R3 = "R3";
	public static final String ESITO_REGOLARIZZAZIONE_R4 = "R4";
	public static final String ESITO_REGOLARIZZAZIONE_R5 = "R5";
	public static final String ESITO_REGOLARIZZAZIONE_R6 = "R6";

	public static final String ESITO_REGOLARIZZAZIONE_IN_PROCEDURA_R1 = "R1";
	public static final String ESITO_REGOLARIZZAZIONE_IN_PROCEDURA_R2 = "R2";
	public static final String ESITO_REGOLARIZZAZIONE_IN_PROCEDURA_R3 = "R3";
	public static final String ESITO_REGOLARIZZAZIONE_IN_PROCEDURA_R4 = "R4";
	public static final String ESITO_REGOLARIZZAZIONE_IN_PROCEDURA_R5 = "R5";
	public static final String ESITO_REGOLARIZZAZIONE_IN_PROCEDURA_R6 = "R6";

	public static final String COMPENSAZIONE_GESTIONE_CONTRIBUTIVA_SI = "SI";
	public static final String COMPENSAZIONE_GESTIONE_CONTRIBUTIVA_NO = "NO";

	public static final String TIPOLOGIA_DI_VERBALE_ISPETTIVO_V1 = "V1";
	public static final String TIPOLOGIA_DI_VERBALE_ISPETTIVO_V2 = "V2";
	public static final String TIPOLOGIA_DI_VERBALE_ISPETTIVO_V3 = "V3";
	public static final String TIPOLOGIA_DI_VERBALE_ISPETTIVO_V4 = "V4";

	public static final String MODALITA_COMUNICAZIONE_DISC_RDL_U02 = "U02";
	public static final String MODALITA_COMUNICAZIONE_DISC_RDL_U03 = "U03";
	public static final String MODALITA_COMUNICAZIONE_DISC_RDL_U04 = "U04";

	public static final String PRESENTE_FASCICOLO_CAR_VERBALE_SI = "SI";
	public static final String PRESENTE_FASCICOLO_CAR_VERBALE_NO = "NO";

	public static final String A001 = "A001";
	public static final String A002 = "A002";
	public static final String A003 = "A003";
	public static final String A004 = "A004";
	public static final String A005 = "A005";
	public static final String A006 = "A006";
	public static final String A007 = "A007";
	public static final String A008 = "A008";
	public static final String A009 = "A009";
	public static final String A010 = "A010";
	public static final String A011 = "A011";
	public static final String A012 = "A012";
	public static final String A013 = "A013";
	public static final String A014 = "A014";
	public static final String A015 = "A015";
	public static final String A016 = "A016";
	public static final String A017 = "A017";
	public static final String A018 = "A017";
	public static final String A019 = "A019";
	public static final String A020 = "A020";
	public static final String A021 = "A021";
	public static final String A022 = "A022";
	public static final String A023 = "A023";
	public static final String A024 = "A024";
	public static final String A025 = "A025";
	public static final String A026 = "A026";
	public static final String A027 = "A027";
	public static final String A028 = "A028";
	public static final String A029 = "A029";
	public static final String A030 = "A030";
	public static final String A031 = "A031";
	public static final String A032 = "A032";
	public static final String A033 = "A033";
	public static final String A034 = "A034";
	public static final String A035 = "A035";
	public static final String A036 = "A036";
	public static final String A037 = "A037";
	public static final String A038 = "A038";
	public static final String A039 = "A039";
	
	public static final String A201 = "A201";
	public static final String A202 = "A202";
	public static final String A203 = "A203";
	public static final String A204 = "A204";
	
	public static final String A206 = "A206";
	public static final String A207 = "A207";
	public static final String A208 = "A208";

	public static final String A313 = "A313";
	public static final String A315 = "A315";
	public static final String A316 = "A316";
	public static final String A318 = "A318";
	public static final String A319 = "A319";
	public static final String A320 = "A320";
	public static final String A321 = "A321";
	public static final String A322 = "A322";
	public static final String A323 = "A323";
	public static final String A328 = "A328";
	public static final String A329 = "A329";
	
	public static final String A423 = "A423";
	public static final String A456 = "A456";
	public static final String A411 = "A411";
	public static final String A415 = "A415";
	public static final String A416 = "A416";
	public static final String A422 = "A422";
	public static final String A419 = "A419";
	public static final String A421 = "A421";
	public static final String A427 = "A427";
	public static final String A428 = "A428";
	public static final String A455 = "A455";
	public static final String A404 = "A404";
	public static final String A406 = "A406";
	public static final String A407 = "A407";
	public static final String A417 = "A417";
	public static final String A451 = "A451";
	public static final String A452 = "A452";
	public static final String A453 = "A453";
	public static final String A454 = "A454";
	
	public static final String A438 = "A438";
	public static final String A439 = "A439";
	public static final String A440 = "A440";
	public static final String A441 = "A441";
	public static final String A442 = "A442";
	public static final String A443 = "A443";
	public static final String A444 = "A444";
	public static final String A445 = "A445";
	public static final String A446 = "A446";
	public static final String A447 = "A447";
	public static final String A448 = "A448";
	public static final String A449 = "A449";
	public static final String A450 = "A450";
	
	public static final String A429 = "A429";
	public static final String A430 = "A430";
	public static final String A431 = "A431";
	public static final String A432 = "A432";
	public static final String A433 = "A433";
	public static final String A434 = "A434";
	public static final String A435 = "A435";
	public static final String A436 = "A436";
	public static final String A437 = "A437";
	
	public static final String A413 = "A413";
	public static final String A426 = "A426";
	public static final String A424 = "A424";
	public static final String A425 = "A425";
	public static final String A457 = "A457";
	public static final String A458 = "A458";
	 
	
	public static final String A514 = "A514";
	public static final String A515 = "A515";
	public static final String A516 = "A516";
	public static final String A517 = "A517";
	public static final String A527 = "A527";
	public static final String A522 = "A522";
	
	public static final String A501 = "A501";
	public static final String A502 = "A502";
	public static final String A503 = "A503";
	
	public static final String A504 = "A504";
	public static final String A505 = "A505";
	public static final String A506 = "A506";
	public static final String A507 = "A507";
	public static final String A508 = "A508";
	
	public static final String A509 = "A509";
	public static final String A510 = "A510";
	public static final String A511 = "A511";
	public static final String A512 = "A512";
	public static final String A513 = "A513";
	
	public static final String A523 = "A523";
	public static final String A524 = "A524";
	
	public static final String A606 = "A606";
	public static final String A607 = "A607";
	
	public static final String A614 = "A614";
	public static final String A615 = "A615";
	public static final String A616 = "A616";
	
	public static final String A617 = "A617";
	public static final String A618 = "A618";
	public static final String A619 = "A619";
	
	public static final String A608 = "A608";
	public static final String A609 = "A609";
	public static final String A610 = "A610";
	
	public static final String A611 = "A611";
	public static final String A612 = "A612";
	public static final String A613 = "A613";
	
	public static final String A317 = "A317";
	public static final String A324 = "A324";
	
	public static final String A310 = "A310";
	public static final String A327 = "A327";
	
	public static final String A314 = "A314";
	public static final String A326 = "A326";
	public static final String A330 = "A330";
	public static final String A331 = "A331";
	public static final String A311 = "A311";
	public static final String A325 = "A325";
	
	public static final String A701 = "A701";
	public static final String A702 = "A702";
	public static final String A703 = "A703";
	public static final String A704 = "A704";
	
	public static final String PORTALE = "PORTALE";
	public static final String ASSENTE = "ASSENTE";
	public static final String SISCO_POST_CTU = "SISCO POST CTU";
	public static final String COGISAN = "COGISAN";
	
	public static Date dateAdd(Date data, int type, int num) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		calendar.add(type, num);
		return calendar.getTime();
	}

	// FUNZIONI UTILITA'
	public static boolean isEmpty(Date data) {
		if (data == null)
			return true;
		return false;
	}

	public static boolean checkFieldError(Object campo, String nomeCampo,
			List<CalcoloIndicatoriErrore> listaErrori) {
		boolean error = false;
		if (campo == null) {
			CalcoloIndicatoriErrore errore = new CalcoloIndicatoriErrore();
			errore.setTipoErrore("Campo null");
			errore.setTipoErrore(nomeCampo);
			errore.setMessaggio(nomeCampo + " è null");
			listaErrori.add(errore);
			return true;
		}
		return error;
	}

	public static boolean checkFieldNonConfError(Object campo,
			String nomeCampo, List<CalcoloIndicatoriErrore> listaErrori) {
		boolean error = true;
		if (campo == null) {
			CalcoloIndicatoriErrore errore = new CalcoloIndicatoriErrore();
			errore.setTipoErrore("Campo null");
			errore.setTipoErrore(nomeCampo);
			errore.setMessaggio(nomeCampo + " è null");
			listaErrori.add(errore);
			return false;
		}
		return error;
	}

	public static boolean checkFieldDateError(Object campo, String nomeCampo,
			String typeControl, List<CalcoloIndicatoriErrore> listaErrori) {
		if ("O".equals(typeControl))
			return false;
		boolean error = false;
		if (campo == null) {
			CalcoloIndicatoriErrore errore = new CalcoloIndicatoriErrore();
			errore.setTipoErrore(nomeCampo);
			errore.setMessaggio(nomeCampo + " è null");
			listaErrori.add(errore);
			return true;
		}
		return error;
	}

	public static void insertError(String tipoErrore, String messaggio,
			String tipoRischio, List<CalcoloIndicatoriErrore> listaErrori) {
		CalcoloIndicatoriErrore errore = new CalcoloIndicatoriErrore();
		errore.setTipoRischio(tipoRischio);
		errore.setTipoErrore(tipoErrore);
		errore.setMessaggio(messaggio);
		listaErrori.add(errore);
	}

	/*
	public static int daysBetween(Date d1, Date d2) {
		if ( d1 == null  || d2 == null){
			log.error("daysBetween --> d1 or d2 is null");
			return 0;
		}
		return (int) ((d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
	}
	*/
	
 	public final static Date find(int year) 
		/*throws YearOutOfRangeException */{
		/*
		if ( (year < 1573) || (year > 2499) ) {
			throw new Easter.YearOutOfRangeException();
		}
		*/
			
		int a = year % 19;
		int b = year % 4;
		int c = year % 7;
		
		int m = 0;
		int n = 0;
		
		if ( (year >= 1583) && (year <= 1699) ) { m = 22; n = 2; }
		if ( (year >= 1700) && (year <= 1799) ) { m = 23; n = 3; }
		if ( (year >= 1800) && (year <= 1899) ) { m = 23; n = 4; }
		if ( (year >= 1900) && (year <= 2099) ) { m = 24; n = 5; }
		if ( (year >= 2100) && (year <= 2199) ) { m = 24; n = 6; }
		if ( (year >= 2200) && (year <= 2299) ) { m = 25; n = 0; }
		if ( (year >= 2300) && (year <= 2399) ) { m = 26; n = 1; }
		if ( (year >= 2400) && (year <= 2499) ) { m = 25; n = 1; }
		
		int d = (19 * a + m) % 30;
		int e = (2 * b + 4 * c + 6 * d + n) % 7;   
	
		Calendar calendar = new GregorianCalendar();
		calendar.set(Calendar.YEAR , year);
		
		if ( d+e < 10 ) {
			calendar.set(Calendar.MONTH , Calendar.MARCH);
			calendar.set(Calendar.DAY_OF_MONTH, d + e + 22);
		} else {
			calendar.set(Calendar.MONTH , Calendar.APRIL);
			int day = d+e-9;
			if ( 26 == day ) {day = 19;}
			if ( ( 25 == day ) && ( 28 == d) && ( e == 6 ) && ( a > 10 ) ) { day = 18; }
			calendar.set(Calendar.DAY_OF_MONTH, day);
		}
		
		return calendar.getTime();
	}
	
	public final static Date getPasquetta(Calendar calendar) throws ParseException 
	/* throws YearOutOfRangeException */  {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");				
		int year = calendar.get(Calendar.YEAR);
		Date easter = find(year);
		calendar.setTime(easter);
		int years = year;
		int month = (calendar.get(Calendar.MONTH) +1);  
		int day = calendar.get(Calendar.DAY_OF_MONTH) + 1;		
		return format.parse ( String.valueOf(years) +  "-" + String.valueOf(month) + "-" + String.valueOf(day)  ); 
	}
	 	
	public final static Date getPasquetta(Date date) throws ParseException 
		/* throws YearOutOfRangeException */  {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
				
		int year = calendar.get(Calendar.YEAR);
		Date easter = find(year);
		calendar.setTime(easter);
		int years = year;
		int month = (calendar.get(Calendar.MONTH) +1);  
		int day = calendar.get(Calendar.DAY_OF_MONTH) + 1;		
		return format.parse ( String.valueOf(years) +  "-" + String.valueOf(month) + "-" + String.valueOf(day)  ); 
	}
	
	public static int daysBetween(Date d1, Date d2) {
		return daysBetween(d1, d2, false);
	}
	
	public static int daysBetween(Date d1, Date d2, boolean calcoloConFestivita) {
		int daysToReturn = 0;
		
		if ( d1 == null  || d2 == null ){
			Calendar cd1 = new GregorianCalendar();
			cd1.setTime(d1);
			Calendar cd2 = new GregorianCalendar();
			cd2.setTime(d2);
			if ( cd1.get(Calendar.DAY_OF_MONTH) == cd2.get(Calendar.DAY_OF_MONTH) 
				&& cd1.get(Calendar.MONTH) == cd2.get(Calendar.MONTH)
				&& cd1.get(Calendar.YEAR) == cd2.get(Calendar.YEAR))
			return 0;
		}
		int daysWithNonLavorativi =  (int) ((d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
		
		for (int day = 1; day <= daysWithNonLavorativi; day++) {
			
	        Calendar cal = Calendar.getInstance();
	        cal.setTime(d1);
	        cal.add(Calendar.DATE, day);
	        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
	        
	        if ( calcoloConFestivita ){
		        try{
		        	Calendar calendar = new GregorianCalendar();
		        	calendar.setTime(cal.getTime());
		        	getPasquetta(calendar);

		        	if (dayOfWeek != Calendar.SUNDAY && dayOfWeek != Calendar.SATURDAY
		        			&& !isSameDay(cal,calendar) && !isFesta(cal.get(Calendar.DAY_OF_MONTH) , cal.get(Calendar.MONTH))){
			        	daysToReturn ++;
		        	}
		        	
		        }catch(Exception ex){
			        if (dayOfWeek != Calendar.SUNDAY && dayOfWeek != Calendar.SATURDAY){
			        	//System.out.println(cal.getTime());
			        	daysToReturn ++;
			        }
		        }
	        }else{
		        //if (dayOfWeek != Calendar.SUNDAY && dayOfWeek != Calendar.SATURDAY){
		        	daysToReturn ++;
		        //}
	        }
		}
		return daysToReturn;
	}
	
	private static boolean isFesta(int day ,int month){
		/*
			Capodanno o Primo dell'Anno			 	1 gennaio  	0
			Epifania o Befana						6 gennaio  	0
			Anniversario della Liberazione			25 aprile  	3
			Festa del Lavoro						1 maggio   	4
			Festa della Repubblica					2 giugno   	5
			Ferragosto o Assunzione					15 agosto	7
			Tutti i Santi (Ognissanti)				1 novembre	10
			Immacolata Concezione					8 dicembre	11
			Natale									25 dicembre	11
			Santo Stefano							26 dicembre	11
		 */
		if (day == 1 && month == 0) return true;
		if (day == 6 && month == 0) return true;
		if (day == 25 && month == 3) return true;
		if (day == 1 && month == 4) return true;
		if (day == 2 && month == 5) return true;
		if (day == 15 && month == 7) return true;
		if (day == 1 && month == 10) return true;
		if (day == 8 && month == 11) return true;
		if (day == 25 && month == 11) return true;
		if (day == 26 && month == 11) return true;
		return false;
	}
	
	public static Date truncateDate(Date dateToTruncate){
		Date d1 = org.apache.commons.lang.time.DateUtils.truncate(dateToTruncate, Calendar.DAY_OF_MONTH);
		return d1;
	}
	
    public static boolean isSameDay(Calendar cal1, Calendar cal2) {
        if (cal1 == null || cal2 == null) {
            throw new IllegalArgumentException("The dates must not be null");
        }
        return (cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA) &&
                cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR));
    }
	
	public static void main(String [ ] args) throws ParseException
	{
		SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
		String date1InString = "05-04-2015 10:20:56";
		String date2InString = "01-01-2018 10:20:56";
		
		 date1InString = "01-01-2018 10:20:56";
		 date2InString = "05-04-2015 10:20:56";
		 
		 date1InString = "01-01-2018 10:20:56";
		 date2InString = "05-01-2018 10:20:56";
		
		Date d1 = sdf.parse(date1InString);
		Date d2 = sdf.parse(date2InString); 
		
		//System.out.println("Days without festività: " + PraticaUtil.daysBetween(d1, d2));
		//System.out.println("Days with festività: " + PraticaUtil.daysBetween(d1, d2, true));

		System.out.println(PraticaUtil.getMonthsDifference(d1, d2));
	}
	
	public static Date normalizeDate(Date dataToNormalize) {
		Date dataToReturn = new Date(dataToNormalize.getTime());
		dataToReturn.setHours(0);
		dataToReturn.setMinutes(0);
		dataToReturn.setSeconds(0);
		return dataToReturn;
	}
	
	public static int getMonthsDifference(Date date1, Date date2) {
		int month = 0;
		if (date1.getYear() == date2.getYear()){
			return ( date2.getMonth() - date1.getMonth());
		}else{
			if (date1.getYear() < date2.getYear()){
				int beginYear = date1.getYear();
				int endYear = date2.getYear() + 1;
				while( beginYear < endYear ){
					
					if ( beginYear == date1.getYear()){
						month = month + ( 12 - (date1.getMonth() + 1 )  ) ;
					}else if ( beginYear == date2.getYear()){
						month = month + (date2.getMonth() + 1);
					}else{
						month = month + 12;
					}
					beginYear++;
				}
				month = month * -1;
			}else{
				
				int beginYear = date2.getYear();
				int endYear = date1.getYear() + 1;
				while( beginYear < endYear ){
					
					if ( beginYear == date2.getYear()){
						month = month + ( 12 - (date2.getMonth() + 1 )  ) ;
					}else if ( beginYear == date1.getYear()){
						month = month + (date1.getMonth() + 1);
					}else{
						month = month + 12;
					}
					beginYear++;
				}
				month = month ;
			}
			return month;
		}
		
	    //int m1 = date1.getYear() * 12 + date1.getMonth();
	    //int m2 = date2.getYear() * 12 + date2.getMonth();
	    //return  (m1 - m2) + 1;
	}
	
	public static void insertLogRischi(List<CalcoloIndicatoriErrore> listaErrori , String rischio , String TipoErrore, String stackTrace) {
		log.info(TipoErrore + " - " + stackTrace);
		CalcoloIndicatoriErrore errore = new CalcoloIndicatoriErrore();
		if (rischio !=null)errore.setTipoRischio(rischio);
		if (TipoErrore !=null)errore.setTipoErrore(TipoErrore);
		if (stackTrace !=null)errore.setMessaggio(stackTrace);
		listaErrori.add(errore);
	}
	
}
