package it.tecnet.crs.report.jpa.dao;

import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoPratiche;
import it.tecnet.crs.report.web.dto.ReportAccessoPDFDto;

import java.sql.CallableStatement;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.ibm.math.BigDecimal;

public class ReportPDFDaoImpl implements ReportPDFDao {
	 
	@PersistenceContext()
	private EntityManager em;

	protected static Logger log = Logger.getLogger(ReportPDFDaoImpl.class);
	@SuppressWarnings("unchecked")
	public List<AtpoPratiche> getReportAllegatiPDF(String sede, long idSessione){
		
	
	String queryStr = "select prt.* from ATPO_PRATICHE_SISCO sisco, ATPO_PRATICHE prt, ATPO_ASS_PRATICA_CAMPAGNA ass, AU_SESSIONI sess " 	
		+"where sisco.ID_PRATICHE_SISCO = prt.ID_PRATICHE_SISCO "
		+"and sisco.ID_PRATICHE_SISCO = ass.ID_PRATICA_SISCO "
		+"and sess.ID_SESSIONE= "+idSessione
		+"and sess.ID_CAMPAGNA = ass.ID_CAMPAGNA "
		+"and prt.sede = '"+sede+"'"
		+"and (prt.Esito like 'SFAVOREVOLE%' or  "
		+"	prt.Esito like 'FAVOREVOLE%' or  "
		+"	prt.Esito like 'PARZIALMENTE%' ) ";
//		
		try {
			
			List<AtpoPratiche> list = (List<AtpoPratiche>)em.createNativeQuery(queryStr,AtpoPratiche.class).getResultList();
			return list;
//			Query q = em.createNamedQuery(AtpoPratiche.QUERY_PRATICA_BY_SEDE);
//			q.setParameter(AtpoPratiche.QUERY_PARAM_SEDE,sede);
//			return (List<AtpoPratiche>) q.getResultList();

		} catch (Throwable e) {
			e.printStackTrace();

		}
		
		return null;
	}
	@SuppressWarnings("unchecked")
	public List<Object[]> getReportErmPDF(long idSSessione){
		
	
		
//		String queryStr = "SELECT  distinct(pratic.ID_PRATICHE_SISCO),	mr.ID_M_RISCHIO  	, mr.CODICE_RISCHIO  	, mr.DESCRIZIONE_RISCHIO  	, risespr.CODICE  	, risespr.DESCRIZIONE  	, spris.DATA_ATTRIBUZIONE  	, spris.IMPORTO ,pratic.FASCICOLO  FROM	AU_S_PRATICA_RIS spris  		, AU_S_RISCHIO sr , AU_M_RISCHIO mr  		, AU_M_RISESPR risespr   		, AU_S_PRATICA pra , ATPO_PRATICHE_SISCO pratic inner join AU_ASS_CAMP_PRT_ATPO prt on pratic.ID_PRATICHE_SISCO = prt.ID_PRATICHE_SISCO inner join AU_CAMPIONE c on prt.ID_CAMPIONE = c.ID_CAMPIONE  inner join AU_SESSIONI sess on c.ID_SESSIONE = sess.ID_SESSIONE WHERE	spris.ID_M_RISCHIO = MR.ID_M_RISCHIO  AND	pratic.ID_PRATICHE_SISCO=pra.ID_PRATICA AND	SPRIS.ID_ESPR_RISCHIO = risespr.ID_M_RISESPR   AND		spris.ID_S_PRATICA = pra.ID_S_PRATICA AND mr.ID_M_RISCHIO = sr.ID_M_RISCHIO	AND sess.ID_SESSIONE = " + idSSessione +"		 ORDER BY 1";
//		String queryStr = "SELECT  pra.ID_PRATICA,	mr.ID_M_RISCHIO  	, mr.CODICE_RISCHIO  	, mr.DESCRIZIONE_RISCHIO  	, risespr.CODICE  	, risespr.DESCRIZIONE  	, spris.DATA_ATTRIBUZIONE  	, spris.IMPORTO ,pratic.FASCICOLO  FROM	AU_S_PRATICA_RIS spris  		, AU_S_RISCHIO sr , AU_M_RISCHIO mr  		, AU_M_RISESPR risespr   		, AU_S_PRATICA pra , ATPO_PRATICHE_SISCO pratic  WHERE	spris.ID_M_RISCHIO = MR.ID_M_RISCHIO  AND	pratic.ID_PRATICHE_SISCO=pra.ID_PRATICA AND	SPRIS.ID_ESPR_RISCHIO = risespr.ID_M_RISESPR   AND		spris.ID_S_PRATICA = pra.ID_S_PRATICA AND mr.ID_M_RISCHIO = sr.ID_M_RISCHIO	AND sr.ID_S_SESSIONE = " + idSSessione +"		 ORDER BY 1";
		
		//SOLO LE CHIUSE
//		String queryStr = "SELECT  distinct(pratic.ID_PRATICHE_SISCO),	mr.ID_M_RISCHIO  	, mr.CODICE_RISCHIO  	, mr.DESCRIZIONE_RISCHIO  	, risespr.CODICE  	, risespr.DESCRIZIONE  	, spris.DATA_ATTRIBUZIONE  	, spris.IMPORTO ,pratic.FASCICOLO  FROM AU_S_PRATICA sp,	AU_S_PRATICA_RIS spris  		, AU_S_RISCHIO sr , AU_M_RISCHIO mr  		, AU_M_RISESPR risespr   		, AU_S_PRATICA pra , ATPO_PRATICHE_SISCO pratic inner join AU_ASS_CAMP_PRT_ATPO prt on pratic.ID_PRATICHE_SISCO = prt.ID_PRATICHE_SISCO inner join AU_CAMPIONE c on prt.ID_CAMPIONE = c.ID_CAMPIONE  inner join AU_SESSIONI sess on c.ID_SESSIONE = sess.ID_SESSIONE WHERE	spris.ID_M_RISCHIO = MR.ID_M_RISCHIO  AND	pratic.ID_PRATICHE_SISCO=pra.ID_PRATICA AND	SPRIS.ID_ESPR_RISCHIO = risespr.ID_M_RISESPR   AND		spris.ID_S_PRATICA = pra.ID_S_PRATICA AND mr.ID_M_RISCHIO = sr.ID_M_RISCHIO	AND sess.ID_SESSIONE = " + idSSessione +" AND sp.ID_PRATICA=pratic.ID_PRATICHE_SISCO 	AND upper(sp.ESAME_PRATICA) like UPPER('c%')	 ORDER BY 1";
		//TUTTE
		//String queryStr = "SELECT  distinct(pratic.ID_PRATICHE_SISCO),	mr.ID_M_RISCHIO  	, mr.CODICE_RISCHIO  	, mr.DESCRIZIONE_RISCHIO  	, risespr.CODICE  	, risespr.DESCRIZIONE  	, spris.DATA_ATTRIBUZIONE  	, spris.IMPORTO ,pratic.FASCICOLO  FROM AU_S_PRATICA sp,	AU_S_PRATICA_RIS spris  		, AU_S_RISCHIO sr , AU_M_RISCHIO mr  		, AU_M_RISESPR risespr   		, AU_S_PRATICA pra , ATPO_PRATICHE_SISCO pratic inner join AU_ASS_CAMP_PRT_ATPO prt on pratic.ID_PRATICHE_SISCO = prt.ID_PRATICHE_SISCO inner join AU_CAMPIONE c on prt.ID_CAMPIONE = c.ID_CAMPIONE  inner join AU_SESSIONI sess on c.ID_SESSIONE = sess.ID_SESSIONE WHERE	spris.ID_M_RISCHIO = MR.ID_M_RISCHIO  AND	pratic.ID_PRATICHE_SISCO=pra.ID_PRATICA AND	SPRIS.ID_ESPR_RISCHIO = risespr.ID_M_RISESPR   AND		spris.ID_S_PRATICA = pra.ID_S_PRATICA AND mr.ID_M_RISCHIO = sr.ID_M_RISCHIO	AND sess.ID_SESSIONE = " + idSSessione +" AND sp.ID_PRATICA=pratic.ID_PRATICHE_SISCO 	ORDER BY 1";
		String queryStr = "select s.ID_PRATICHE_SISCO ,s.FASCICOLO  ,sp.ESAME_PRATICA from AU_S_PRATICA sp, ATPO_PRATICHE_SISCO s  inner join AU_SEDE sede on s.COD_SEDE = sede.CODICE_SEDE  inner join AU_ASS_CAMP_PRT_ATPO prt on s.ID_PRATICHE_SISCO = prt.ID_PRATICHE_SISCO  inner join AU_CAMPIONE c on prt.ID_CAMPIONE = c.ID_CAMPIONE  inner join AU_SESSIONI sess on c.ID_SESSIONE = sess.ID_SESSIONE  where sess.id_sessione= " + idSSessione +" AND s.ID_PRATICHE_SISCO= sp.ID_PRATICA ";
		List<Object[]> ids = em.createNativeQuery(queryStr).getResultList();
		List<Object[]> totale=new ArrayList<Object[]>();
		for(int m = 0 ; m < ids.size();m++){
			Long id = (Long)ids.get(m)[0];
			String fascicolo = (String)ids.get(m)[1];
			String stato = (String)ids.get(m)[2];
//			System.out.println("STATO="+stato);
			if(!stato.substring(0,1).trim().equalsIgnoreCase("C")){
				totale.add(new Object[]{id,"","","","","","",new java.math.BigDecimal(0),fascicolo});
			}
			else{
				queryStr = "select 	sp.ID_PRATICA , mr.ID_M_RISCHIO , mr.CODICE_RISCHIO  	, mr.DESCRIZIONE_RISCHIO  , risespr.CODICE  	, risespr.DESCRIZIONE  	, spris.DATA_ATTRIBUZIONE  	, spris.IMPORTO ,pratic.FASCICOLO  from		AU_M_RISCHIO mr ,AU_S_PRATICA_RIS spris, AU_S_PRATICA sp , AU_M_RISESPR risespr ,ATPO_PRATICHE_SISCO pratic		where		pratic.ID_PRATICHE_SISCO=sp.ID_PRATICA AND		spris.ID_M_RISCHIO = MR.ID_M_RISCHIO  AND		SPRIS.ID_ESPR_RISCHIO = risespr.ID_M_RISESPR   AND		spris.ID_S_PRATICA=sp.ID_S_PRATICA AND upper(sp.ESAME_PRATICA) like UPPER('c%') AND 		sp.ID_PRATICA ="+id.longValue();
				List<Object[]> rischis = em.createNativeQuery(queryStr).getResultList();
				if(rischis.size()>0){
					totale.addAll(rischis);
				}
				else{
					totale.add(new Object[]{id,"","","","","","",new java.math.BigDecimal(0),fascicolo});
				}
			}
		}
		try {
			return  totale;


		} catch (Throwable e) {
			e.printStackTrace();

		}
		
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public Object[] getAllegatoReportAccessoPDF(long idSSessione){
		
		Object[] bean = null;
		
		String queryStr = "SELECT " + 
						  	"B.DIRIGENTE, B.SEDE, " +
						  	"A.ID_SESSIONE as idSessione, A.ID_S_SESSIONE AS idSSessione, " +
						  	"CONVERT(VARCHAR(10), B.DATA_INIZIO, 103) AS dataInizio, " +
						  	"CONVERT(VARCHAR(10), B.DATA_FINE, 103) AS dataFine, " +
						  	"CONVERT(VARCHAR(10), C.DATA_INIZIO_OSSERVAZIONE, 103) AS dataInizioOsservazione, " +
						  	"CONVERT(VARCHAR(10), C.DATA_FINE_OSSERVAZIONE, 103) AS dataFineOsservazione,  " +
						  	"A.NR_PRATICHE_ESAMINATE AS numeroPraticheEsaminate, " +
						  	"A.NR_PRATICHE_ND AS numeroPraticheND, " +
						  	"A.NR_PRATICHE AS numeroPratiche, " +
						  	"A.INCC, A.INCC_DESCRIZIONE AS INCCDescrizione, " +
						  	"CONVERT(VARCHAR(10), A.DATA_AGG_DATI_SESS, 103) as dataAggiornamentoDatiSessione " +
						  "FROM " + 
						  	"AU_S_SESSIONE AS A " + 
							"JOIN AU_SESSIONI AS B ON A.ID_SESSIONE = B.ID_SESSIONE " + 
							"JOIN AU_CAMPAGNA AS C ON B.ID_CAMPAGNA = C.ID_CAMPAGNA " +
							"WHERE A.ID_S_SESSIONE = " + idSSessione;
		
		try {
			List<Object[]> resultList = em.createNativeQuery(queryStr).getResultList();

			if(!resultList.isEmpty() && resultList.size() > 0){
				bean = resultList.get(0);
			}

		} catch (Throwable e) {
			e.printStackTrace();

		}
		
		return bean;
	}
	
	@SuppressWarnings("unchecked")
	public Object[] getReportAccessoPDF(long idSSessione){
		
		Object[] bean = null;
		
		String queryStr = "SELECT " + 
						  	"B.DIRIGENTE, B.SEDE, " +
						  	"A.ID_SESSIONE as idSessione, A.ID_S_SESSIONE AS idSSessione, " +
						  	"CONVERT(VARCHAR(10), B.DATA_INIZIO, 103) AS dataInizio, " +
						  	"CONVERT(VARCHAR(10), B.DATA_FINE, 103) AS dataFine, " +
						  	"CONVERT(VARCHAR(10), C.DATA_INIZIO_OSSERVAZIONE, 103) AS dataInizioOsservazione, " +
						  	"CONVERT(VARCHAR(10), C.DATA_FINE_OSSERVAZIONE, 103) AS dataFineOsservazione,  " +
						  	"A.NR_PRATICHE_ESAMINATE AS numeroPraticheEsaminate, " +
						  	"A.NR_PRATICHE_ND AS numeroPraticheND, " +
						  	"A.NR_PRATICHE AS numeroPratiche, " +
						  	"A.INCC, A.INCC_DESCRIZIONE AS INCCDescrizione, " +
						  	"CONVERT(VARCHAR(10), A.DATA_AGG_DATI_SESS, 103) as dataAggiornamentoDatiSessione " +
						  "FROM " + 
						  	"AU_S_SESSIONE AS A " + 
							"JOIN AU_SESSIONI AS B ON A.ID_SESSIONE = B.ID_SESSIONE " + 
							"JOIN AU_CAMPAGNA AS C ON B.ID_CAMPAGNA = C.ID_CAMPAGNA " +
							"WHERE A.ID_S_SESSIONE = " + idSSessione;
		
		try {
			List<Object[]> resultList = em.createNativeQuery(queryStr).getResultList();

			if(!resultList.isEmpty() && resultList.size() > 0){
				bean = resultList.get(0);
				if(bean.length>0){
					if(bean[0]==null){
						queryStr = "select usr.NOME , campagna.NOME from  AU_AUDIT audit, AU_ASS_UTENTE_AUDIT ass, CRS_UTENTE_ADV usr, CRS_ASS_UTENTE_RUOLO assur, CRS_RUOLO ruolo,AU_CAMPAGNA campagna ,AU_SESSIONI sessioni , AU_S_SESSIONE ssessioni where audit.ID_AUDIT = ass.ID_AUDIT and ass.ID_UTENTE = usr.ID_UTENTE and assur.ID_UTENTE = usr.ID_UTENTE and assur.ID_RUOLO = ruolo.ID_RUOLO and ssessioni.ID_SESSIONE = sessioni.ID_SESSIONE and campagna.ID_AUDIT = audit.ID_AUDIT and sessioni.ID_CAMPAGNA=campagna.ID_CAMPAGNA and ssessioni.ID_S_SESSIONE="+ idSSessione+" and ruolo.DESCRIZIONE ='dirigente'";
						resultList = em.createNativeQuery(queryStr).getResultList();
						try{
						bean[0]=resultList.get(0)[0];
						}
						catch(Exception e){}
					}
				}
			}

		} catch (Throwable e) {
			e.printStackTrace();

		}
		
		return bean;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getRiepilogoIstanze(long idSSessione){
		
		List<Object[]> lista = new ArrayList<Object[]>();
		
		String queryStr = "SELECT 	B.descrizione, " +
						  "			isnull(A.quantita,0) as quantita " +
						  "FROM " + 
						  "		(" +
						  "			select " + 
						  "				COD_CHIUSURA_CORRETTO,  " + 
						  "				sum(quantita) as quantita " +
						  "			from au_s_tesito " + 
						  "			where id_s_sessione = " + idSSessione +
						  " 		group by COD_CHIUSURA_CORRETTO" +
						  "		) as A right join" +
						  "		(	select " +
						  "			tipo, codifica, descrizione " +
						  "			from au_tpl_tipologiche " +
						  "			where tipo = 'V019'" +
						  "		) as B on B.codifica = A.COD_CHIUSURA_CORRETTO";
		
		try {
			lista = em.createNativeQuery(queryStr).getResultList();


		} catch (Throwable e) {
			e.printStackTrace();

		}
		
		return lista;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getRiepilogoFasi(long idSSessione){
		
		List<Object[]> lista = new ArrayList<Object[]>();
		
		String queryStr = "SELECT 	crs.ID_SOTTOPROCESSO, " +
						  "			crs.DESCRIZIONE, " +
						  /*"			CAST(AVG(VALORE_INCC) AS DECIMAL(10,2))  as valore_incc " + */
						  "			CAST(SUM(isnull(s.VALORE_PESATO_FASE,0)) AS DECIMAL(10,2))  as valore_incc " +		
						  "FROM " + 
						  "		CRS_SOTTOPROCESSO as crs " + 
						  "		join AU_M_NONCONF as m on m.ID_FASE = crs.ID_SOTTOPROCESSO " + 
						  "		join AU_S_NONCONF as s on s.ID_M_NONCONF = m.ID_M_NON_CONF " +
						  "WHERE s.ID_S_SESSIONE = " + idSSessione + 
						  " group by crs.ID_SOTTOPROCESSO, crs.DESCRIZIONE";
		
		try {
			lista = em.createNativeQuery(queryStr).getResultList();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		return lista;
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getMNonConfByIdFase(long idFase,long idSSessione) {
		
		List<Object[]> lista = new ArrayList<Object[]>();
		
		String queryStr = "SELECT 	mnc.ID_M_NON_CONF, " +
						  "			mnc.DESCRIZIONE, " +
						  "			snc.VALORE_INCC " +
						  "FROM " + 
						  "		AU_M_NONCONF as mnc " + 
						  "			join AU_S_NONCONF as snc on mnc.ID_M_NON_CONF = snc.ID_M_NONCONF " + 
						  "where mnc.ID_FASE = " + idFase + 
						  " and snc.ID_S_SESSIONE = " + idSSessione + "  order by ordinamento "; 
		
		try {
			lista = em.createNativeQuery(queryStr).getResultList();
		} catch (Throwable e) {
			e.printStackTrace();

		}		
		return lista;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getVarCompByIdMNonConf(long sSessione, Long idFase, Long idMNonConf){
		List<Object[]> lista = new ArrayList<Object[]>();
			
		/*
		String queryStr = 		"select	DESCRIZIONE " + 
								"		, NUM1 " + 
								"		, NUM2  " + 
								"		, cast(((cast(NUM1 as decimal) / cast(NUM2 as decimal))) as decimal(18,2)) * 100  AS PERCENTUALE " +
								" 		, COLORE " +		
								"from ( " + 
								"	select mvar.DESCRIZIONE, sconf.ID_M_NONCONF, svar.ID_M_VARCOMP, isnc.COLORE , count(svar.ID_M_VARCOMP) as NUM1 " + 
								"	from AU_M_NONCONF nconf " + 
								"		join AU_S_NONCONF sconf on nconf.ID_M_NON_CONF = sconf.ID_M_NONCONF " + 
								"		join AU_S_VARCOMP svar on svar.ID_S_NONCONF = sconf.ID_S_NONCONF " + 
								"		join AU_M_VARCOMP mvar on svar.ID_M_VARCOMP = mvar.ID_M_COMP " + 
								"		join AU_TPL_ISNC isnc on  mvar.PESO_VC = isnc.ID_TPL_ISNC " +	
								"	where sconf.ID_S_SESSIONE =  " +  sessione +
								"	and nconf.ID_FASE = " + idFase +
								" 	and nconf.ID_M_NON_CONF = " + idMNonConf +
								"	group by mvar.DESCRIZIONE, sconf.ID_M_NONCONF,svar.ID_M_VARCOMP  , isnc.COLORE " + 
								")  as riepilogoVarcomp	 " + 
								", ( " + 
								"	select  nconf.ID_M_NON_CONF, count(*) as NUM2 " + 
								"	from AU_M_NONCONF nconf " + 
								"		join AU_S_NONCONF sconf on nconf.ID_M_NON_CONF = sconf.ID_M_NONCONF " + 
								"		join AU_S_VARCOMP svar on svar.ID_S_NONCONF = sconf.ID_S_NONCONF " + 
								"		join AU_M_VARCOMP mvar on svar.ID_M_VARCOMP = mvar.ID_M_COMP " + 
								"	where	sconf.ID_S_SESSIONE = " +  sessione +
								"	and		nconf.ID_FASE = " + idFase +
								" 	and nconf.ID_M_NON_CONF = " + idMNonConf +
								"	group by nconf.ID_M_NON_CONF " + 
								"	) as riepilogoNonConf " + 
								"where riepilogoVarcomp.ID_M_NONCONF = riepilogoNonConf.ID_M_NON_CONF ";
		*/
		
		String queryStr = 		" select  " +
								"		mvar.DESCRIZIONE " +
								"		, svar.NUM as NUM1 " +
								"		, svar.NUM as NUM2 " +
								"		, svar.PERC_SU_PS * 100 as NUM2 " +
								"		, isnc.COLORE " +
								"		, mvar.CODICE_VC " +
								
								" from	AU_S_VARCOMP svar  " +
								"		, AU_S_NONCONF snc " +
								"		, AU_M_VARCOMP mvar " +
								"		, AU_TPL_ISNC isnc " +
								"		, AU_M_NONCONF mnonconf " +
								" where  " +
								"	svar.ID_S_NONCONF = snc.ID_S_NONCONF" +
								" and svar.ID_M_VARCOMP = mvar.ID_M_COMP  " +
								" and mvar.PESO_VC = isnc.ID_TPL_ISNC " +
								" and snc.ID_M_NONCONF = mnonconf.ID_M_NON_CONF" +
								" and snc.ID_S_SESSIONE = " + sSessione +
								
								
								" and  mnonconf.ID_FASE = " + idFase  ;
		
								if(idMNonConf>0){
									queryStr = queryStr+	" and svar.ID_M_NONCONF = " + idMNonConf +" ";
								}
								
					
		
		try {
			lista = em.createNativeQuery(queryStr).getResultList();


		} catch (Throwable e) {
			e.printStackTrace();

		}
		
		return lista;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getVarCompByIdMNonConf(long idMNonConf){
		
		List<Object[]> lista = new ArrayList<Object[]>();
		
		String queryStr = "SELECT " + 
						  	"A.DESCRIZIONE, A.NUM1, A.NUM2, cast(((cast(A.NUM1 as decimal) / cast(A.NUM2 as decimal))) as decimal(18,2)) " +
						  	"from( " +
						  	"select " +
						  	"m.ID_M_COMP, m.ID_M_NONCONF, m.DESCRIZIONE, sv.NUM AS NUM1, s.NUM AS NUM2 " +
						  	"FROM " + 
						  	"AU_M_VARCOMP as m " + 
							"join AU_S_VARCOMP as sv on sv.ID_M_NONCONF = m.ID_M_NONCONF " + 
							"join AU_S_NONCONF as s on s.ID_S_NONCONF = sv.ID_S_NONCONF " +
							"where m.ID_M_NONCONF = " + idMNonConf + ") as A"; 
		
		try {
			lista = em.createNativeQuery(queryStr).getResultList();


		} catch (Throwable e) {
			e.printStackTrace();

		}
		
		return lista;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getRiepilogoRischi(long idSSessione){
		
		List<Object[]> lista = new ArrayList<Object[]>();
		
		String queryStr = "SELECT " + 
						  	" m.ID_M_RISCHIO, " +
						  	" s.ID_S_RISCHIO, " +
						  	" m.DESCRIZIONE_RISCHIO ," +
						  	" s.importo, " +
						  	" s.SU_PS_PERC, " +
						  	" s.NUM " +
						  "FROM " + 
						  	"AU_S_RISCHIO as s join AU_M_RISCHIO as m on s.ID_M_RISCHIO = m.ID_M_RISCHIO " + 
							"WHERE s.ID_S_SESSIONE = " + idSSessione +
							" and  s.ID_M_RISCHIO in ( " + 
							"		select  " +
							"			distinct s.ID_M_RISCHIO " +
							"		from  " +
							"			AU_S_RISCHIO as s join AU_S_RISESPR as rise on s.ID_S_RISCHIO = rise.ID_S_RISCHIO " +
							"				join AU_M_RISESPR as mrise on rise.ID_M_RISESPR = mrise.ID_M_RISESPR " +
							"		where  " +
							"			s.ID_S_SESSIONE = " + idSSessione +
							"		and mrise.RAGGRUPPAMENTO_RISCHIO not in (3,4) " +
							" )";
		
		try {
			lista = em.createNativeQuery(queryStr).getResultList();


		} catch (Throwable e) {
			e.printStackTrace();

		}
		
		return lista;
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getRisEsprByIdMNonConf(long idMRischio, long idSSessione){
		
		List<Object[]> lista = new ArrayList<Object[]>();
		/*
		String queryStr = 	"SELECT " + 
						  	"m.DESCRIZIONE, sr.NUM, sr.SU_PS_PERC, sr.IMPORTO " +
						  	"FROM " + 
						  	"AU_M_RISESPR as m " + 
							"join AU_S_RISESPR as s on s.ID_M_RISESPR = m.ID_M_RISESPR " + 
							"join AU_S_RISCHIO as sr on sr.ID_S_RISCHIO = s.ID_S_RISCHIO " +
							"where m.ID_M_RISCHIO = " + idMRischio +
							" and sr.ID_S_SESSIONE = " + idSSessione; 	
		
		*/
		String queryStr = 	"SELECT " + 
						  	"m.DESCRIZIONE, s.NUM , s.SU_PS , s.IMPORTO " +
						  	"FROM " + 
						  	"AU_M_RISESPR as m " + 
							"join AU_S_RISESPR as s on s.ID_M_RISESPR = m.ID_M_RISESPR " + 
							"join AU_S_RISCHIO as sr on sr.ID_S_RISCHIO = s.ID_S_RISCHIO " +
							"where sr.ID_M_RISCHIO = " + idMRischio +
							" and sr.ID_S_SESSIONE = " + idSSessione +
							" and  m.RAGGRUPPAMENTO_RISCHIO not in (3,4)"; 	
 		
		try {
			lista = em.createNativeQuery(queryStr).getResultList();


		} catch (Throwable e) {
			e.printStackTrace();

		}
		
		return lista;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getRiepilogoFascicolo(long idSSessione){
		
		List<Object[]> lista = new ArrayList<Object[]>();
		/*
		String queryStr = "SELECT " + 
						  	"t.DESCRIZIONE, " +
						  	"f.QUANTITA " +
						  "FROM " + 
						  	"AU_S_TFASCICOLO as f join AU_TPL_TIPOLOGICHE as t on f.TIPO = t.TIPO " + 
							"where f.ID_S_SESSIONE = " + idSSessione;
		*/
		String queryStr = 	"select " + 
							"	tpl.DESCRIZIONE, " + 
							"	sum(isnull(t.QUANTITA,0)) " + 
							"from " + 
							"	AU_TPL_TIPOLOGICHE tpl left join ( Select * from AU_S_TFASCICOLO where ID_S_SESSIONE = " + idSSessione + ") as t on  tpl.CODIFICA = t.CODIFICA  " + 
							"where " + 
							"	tpl.TIPO  = 'V007' " + 
							"group by tpl.DESCRIZIONE";
		
		try {
			lista = em.createNativeQuery(queryStr).getResultList();


		} catch (Throwable e) {
			e.printStackTrace();

		}
		
		return lista;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getRiepilogoDocManc(long idSSessione){
		
		List<Object[]> lista = new ArrayList<Object[]>();
		/*
		String queryStr = "SELECT " + 
						  	"t.DESCRIZIONE, " +
						  	"f.QUANTITA " +
						  "FROM " + 
						  	"AU_TDOCMANC as f join AU_TPL_TIPOLOGICHE as t on f.TIPO = t.TIPO " + 
							"where f.ID_S_SESSIONE = " + idSSessione +
							" and f.TIPO = 'V003' " +
							"and f.CODIFICA in ('R01','R02','R05','R06','R08','R09','R10','R12','R23','R24')";
		*/
		String queryStr = 	"select	tpl.DESCRIZIONE, sum(isnull(td.QUANTITA,0)) " +
							"from AU_TPL_TIPOLOGICHE tpl left join (select * from AU_TDOCMANC where ID_S_SESSIONE = " + idSSessione + " ) as td on tpl.CODIFICA = td.CODIFICA    " + 
							"where	tpl.TIPO = 'V003'  " +
							"group by tpl.DESCRIZIONE"; 
							// "and	tpl.CODIFICA in ('R01','R02','R03','R05','R06','R08','R09','R10','R12','R23','R24') " +
		
		try {
			lista = em.createNativeQuery(queryStr).getResultList();


		} catch (Throwable e) {
			e.printStackTrace();

		}
		
		return lista;
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getEsitoByTipoDifesa(long idSSessione){
		
		List<Object[]> lista = new ArrayList<Object[]>();
		
		/*
		String queryStr = "select B.DESCRIZIONE, isnull(A.QUANTITA,0) as quantita " + 
						  	"from( " +
						  	"(select " +
						  	"COD_CHIUSURA_CORRETTO, QUANTITA " +
						  	"FROM " + 
						  	"AU_S_TESITO " + 
							"where ID_S_SESSIONE = " + idSSessione + ") as A right join " +
							"(select TIPO, CODIFICA, DESCRIZIONE " +
							"from AU_TPL_TIPOLOGICHE where tipo = 'V009') as B on A.COD_CHIUSURA_CORRETTO = B.CODIFICA)";
		*/
		String queryStr =" select t.DESCRIZIONE , t.CODIFICA, cast(sum(isnull(e.QUANTITA,0)) as int) as quantita " +
							"	from  AU_TPL_TIPOLOGICHE t  left join (select * from AU_S_TESITO where ID_S_SESSIONE = "+idSSessione +
							" ) e  on t.CODIFICA = e.TIPO_DIFESA " +
							"	where tipo = 'V009' " +
							" group by  t.DESCRIZIONE, t.CODIFICA";
		
		
		try {
			lista = em.createNativeQuery(queryStr).getResultList();


		} catch (Throwable e) {
			e.printStackTrace();

		}
		
		return lista;
	}
	
	public List<Object[]> getEsitoByTipoDifesaWithDissensoByCampagna(long idCampagna){
		List<Object[]> lista = new ArrayList<Object[]>();

		String queryStr =" select t.DESCRIZIONE , t.CODIFICA, cast(sum(isnull(e.QUANTITA,0)) as int) as quantita " +
							"	from  AU_TPL_TIPOLOGICHE t  left join (select * from AU_S_TESITO where  COD_CHIUSURA_CORRETTO in ('1', '2', '3') " +
							"                                            AND ID_S_SESSIONE in (  select sess.ID_S_SESSIONE from AU_S_SESSIONE sess , AU_SESSIONI se where  sess.ID_SESSIONE = se.ID_SESSIONE and ID_CAMPAGNA = "+idCampagna +"  ) " + 
							" ) e  on t.CODIFICA = e.TIPO_DIFESA " +
							"	where tipo = 'V009' " +
							" group by  t.DESCRIZIONE, t.CODIFICA";
		
		
		try {
			lista = em.createNativeQuery(queryStr).getResultList();


		} catch (Throwable e) {
			e.printStackTrace();

		}
		
		return lista;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getEsitoByTipoDifesaWithDissenso(long idSSessione){
		
		List<Object[]> lista = new ArrayList<Object[]>();
		
		/*
		String queryStr = "select B.DESCRIZIONE, isnull(A.QUANTITA,0) as quantita " + 
						  	"from( " +
						  	"(select " +
						  	"COD_CHIUSURA_CORRETTO, QUANTITA " +
						  	"FROM " + 
						  	"AU_S_TESITO " + 
							"where ID_S_SESSIONE = " + idSSessione + ") as A right join " +
							"(select TIPO, CODIFICA, DESCRIZIONE " +
							"from AU_TPL_TIPOLOGICHE where tipo = 'V009') as B on A.COD_CHIUSURA_CORRETTO = B.CODIFICA)";
		*/
		String queryStr =" select t.DESCRIZIONE , t.CODIFICA, cast(sum(isnull(e.QUANTITA,0)) as int) as quantita " +
							"	from  AU_TPL_TIPOLOGICHE t  left join (select * from AU_S_TESITO where  COD_CHIUSURA_CORRETTO in ('1', '2', '3') and  ID_S_SESSIONE = "+idSSessione +
							" ) e  on t.CODIFICA = e.TIPO_DIFESA " +
							"	where tipo = 'V009' " +
							" group by  t.DESCRIZIONE, t.CODIFICA";
		
		
		try {
			lista = em.createNativeQuery(queryStr).getResultList();


		} catch (Throwable e) {
			e.printStackTrace();

		}
		
		return lista;
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getRiepilogoGiudizi(long idSSessione){
		
		List<Object[]> lista = new ArrayList<Object[]>();
		/*
		String queryStr = "select " + 
							"A.DESCRIZIONE, " + 
						  	"isnull(A.QUANTITA,0) as nr_giudizi, " +
						  	"isnull(A.PERC_QUANTITA,0) as percentuale, " +
						  	"isnull(A.NUM_PRESTAZIONI, 0) as num_prestazioni, " +
						  	"isnull(A.IMPORTO_PRESTAZIONE, 0) as importo_prestazioni, " + 
						  	"isnull(A.SPESE_LEGALI, 0) as spese_legali " + 
						  	"from( " +
							"(select " +
							"'U02' as esito, " +
							"'COMPLETA + ESSENZIALE' AS DESCRIZIONE, " +
							"sum(QUANTITA) as quantita, sum(PERC_QUANTITA) as perc_quantita, " +
							"sum(NUM_PRESTAZIONI) as num_prestazioni, sum(IMPORTO_PRESTAZIONE) as importo_prestazione, sum(SPESE_LEGALI) as spese_legali " +
							"from " +
							"AU_S_TESITO " +
							"where ID_S_SESSIONE = " + idSSessione + " and TIPO_DIFESA in ('U02', 'U03') " +
							"union all " +
							"select " +
							"TIPO_DIFESA, 'INCOMPLETA' AS DESCRIZIONE, QUANTITA, PERC_QUANTITA, NUM_PRESTAZIONI, IMPORTO_PRESTAZIONE, SPESE_LEGALI " +
							"from " +
							"AU_S_TESITO " +
							"where ID_S_SESSIONE = " + idSSessione + " and TIPO_DIFESA in ('U04')) as A right join " +
							"(select TIPO, CODIFICA " +
							"from AU_TPL_TIPOLOGICHE where tipo = 'V009') as B on A.ESITO = B.CODIFICA) " +
							"where ESITO <> 'NULL'";
		*/					
		String queryStr =  "SELECT tab.descrizione, " +
						    "    Sum(TAB.quantita)            AS nr_giudizi, " +
						    "    Sum(TAB.perc_quantita)  * 100     AS perc_quantita, " +
						    "    Sum(TAB.num_prestazioni)     AS num_prestazioni, " +
						    "    Sum(TAB.importo_prestazione) AS importo_prestazione, " +
						    "    Sum(TAB.spese_legali)        AS spese_legali, " +
						    "    Sum(TAB.spese_ctu)        AS spese_legali_ctu " +
							"FROM   (SELECT * " +
							"        FROM   (SELECT 'U02'                            AS esito, " +
							"                       'COMPLETA + ESSENZIALE'          AS DESCRIZIONE, " +
							"                       Isnull(a.quantita, 0)            AS quantita, " +
							"                       Isnull(a.perc_quantita, 0)       AS perc_quantita, " +
							"                       Isnull(a.num_prestazioni, 0)     AS num_prestazioni, " +
							"                       Isnull(a.importo_prestazione, 0) AS importo_prestazione, " +
							"                       Isnull(a.spese_legali, 0)        AS spese_legali, " +
							"                       Isnull(a.spese_legali_ctu, 0)        AS spese_ctu " +
							"                FROM   (select * from au_s_tesito where COD_CHIUSURA_CORRETTO in (2,3) AND ID_S_SESSIONE = "+ idSSessione + " ) a " +
							"                       RIGHT JOIN (SELECT * " +
							"                                   FROM   au_tpl_tipologiche b " +
							"                                   WHERE  tipo = 'V009' " +
							"                                          AND b.codifica IN ( 'U02', 'U03' )) b " +
							"                               ON a.tipo_difesa = B.codifica) AS A " +
							"        UNION ALL " +
							"        SELECT * " +
							"        FROM   (SELECT b.codifica                       AS esito, " +
							"                       'INCOMPLETA'                     AS DESCRIZIONE, " +
							"                       Isnull(a.quantita, 0)            AS quantita, " +
							"                       Isnull(a.perc_quantita, 0)       AS perc_quantita, " +
							"                       Isnull(a.num_prestazioni, 0)     AS num_prestazioni, " +
							"                       Isnull(a.importo_prestazione, 0) AS importo_prestazione, " +
							"                       Isnull(a.spese_legali, 0)        AS spese_legali, " +
							"                       Isnull(a.spese_legali_ctu, 0)        AS spese_ctu " +
							"                FROM   (select * from au_s_tesito where COD_CHIUSURA_CORRETTO in (2,3) AND ID_S_SESSIONE = " + idSSessione + " ) a " +
							"                       RIGHT JOIN (SELECT * " +
							"                                   FROM   au_tpl_tipologiche b " +
							"                                   WHERE  tipo = 'V009' " +
							"                                          AND b.codifica IN ( 'U04' )) b " +
							"                               ON a.tipo_difesa = B.codifica) AS A) AS TAB " +
							"GROUP  BY tab.descrizione   ";
		try {
			lista = em.createNativeQuery(queryStr).getResultList();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		return lista;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getRisultatiByTempo(long idSSessione){
		
		List<Object[]> lista = new ArrayList<Object[]>();
		
		String queryStr = "select " + 
							"B.descrizione, " + 
						  	"isnull(A.media_gg, 0) as giorni, " +
						  	"isnull(A.NC, 0) as nc " +
						  	"  , ORDINAMENTO " +
						  	"from( " +
							"(select " +
							"TIPO, " +
							"CODIFICA, " +
							"MEDIA_GG, " +
							"NC, " +
							"ORDINAMENTO " +
							"from " +
							"au_s_tempi " +
							"where ID_S_SESSIONE = " + idSSessione + ") as A right join " +
							"(select TIPO, CODIFICA, DESCRIZIONE " +
							"from AU_TPL_TIPOLOGICHE where tipo = 'V008' and LTRIM(RTRIM(CODIFICA)) in ( 'E06','E07','E08','E09','E10','E01','E04','E02' )) as B on A.tipo = B.tipo and A.codifica = B.codifica) order by ORDINAMENTO";
							
		
		try {
			lista = em.createNativeQuery(queryStr).getResultList();


		} catch (Throwable e) {
			e.printStackTrace();

		}
		
		return lista;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<String> getListaAuditors(long idSSessione){
		
		List<String> list = null;
		
		String queryStr = "select d.COGNOME + ' ' + d.NOME as auditors " + 
					  	"from AU_S_SESSIONE as a join " + 
						"AU_SESSIONI as b on a.ID_SESSIONE = b.ID_SESSIONE join " + 
						"AU_ASS_UTENTE_SESSIONE as c on c.ID_SESSIONE = b.ID_SESSIONE join " +
						"CRS_UTENTE_ADV as d on d.ID_UTENTE = c.ID_UTENTE " +
						"WHERE a.ID_S_SESSIONE =  " + idSSessione; 
		
		try {
			list = em.createNativeQuery(queryStr).getResultList();
			
		} catch (Throwable e) {
			e.printStackTrace();

		}
		
		return list;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getGiudiziDefinitiviSisco(long idSSessione, String tipoDifesa){
		//V009
		List<Object[]> lista = new ArrayList<Object[]>();
		
		String queryStr = "select " + 
							"B.DESCRIZIONE, " + 
						  	"isnull(A.QUANTITA,0) as quantita " +
						  	"from( " +
							"(select " +
							"COD_CHIUSURA_CORRETTO, " +
							"quantita " +
							"from AU_S_TESITO " +
							"where ID_S_SESSIONE = " + idSSessione + " and tipo_difesa = '" + tipoDifesa +"') as A right join " +
							"(select TIPO, CODIFICA, DESCRIZIONE " +
							"from AU_TPL_TIPOLOGICHE where codifica in ('1', '2', '3') and TIPO = 'V019') as B on A.COD_CHIUSURA_CORRETTO = B.CODIFICA) ";
							
		
		try {
			lista = em.createNativeQuery(queryStr).getResultList();


		} catch (Throwable e) {
			e.printStackTrace();

		}
		
		return lista;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getTipoDifesaIncompleta(long idSSessione, String tipoDifesa){
		
		List<Object[]> lista = new ArrayList<Object[]>();
		
		String queryStr = "select " + 
							"B.DESCRIZIONE, " + 
						  	"isnull(A.QUANTITA,0) as quantita, " +
						  	"isnull(A.PERC_QUANTITA, 0) as perc_quantita " +
						  	"from( " +
							"(select " +
							"COD_CHIUSURA_CORRETTO, " +
							"quantita, " +
							"PERC_QUANTITA " +
							"from AU_S_TESITO " +
							"where ID_S_SESSIONE = " + idSSessione + " and tipo_difesa = '" + tipoDifesa +"') as A right join " +
							"(select TIPO, CODIFICA, DESCRIZIONE " +
							"from AU_TPL_TIPOLOGICHE where codifica in ('1', '2', '3') and TIPO = 'V019') as B on A.COD_CHIUSURA_CORRETTO = B.CODIFICA) ";
							
		
		try {
			lista = em.createNativeQuery(queryStr).getResultList();


		} catch (Throwable e) {
			e.printStackTrace();

		}
		
		return lista;
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getTipoDifesaIncompletaWithDissenso(long idSSessione, String tipoDifesa){
		
		List<Object[]> lista = new ArrayList<Object[]>();
		
		String queryStr = "select " + 
							"B.DESCRIZIONE, " + 
						  	"isnull(A.QUANTITA,0) as quantita, " +
						  	"isnull(A.PERC_QUANTITA, 0) as perc_quantita " +
						  	"from( " +
							"(select " +
							"COD_CHIUSURA_CORRETTO, " +
							"quantita, " +
							"PERC_QUANTITA " +
							"from AU_S_TESITO " +
							"where ID_S_SESSIONE = " + idSSessione + " and tipo_difesa = '" + tipoDifesa +"') as A right join " +
							"(select TIPO, CODIFICA, DESCRIZIONE " +
							"from AU_TPL_TIPOLOGICHE where codifica in ('1', '2', '3', '4') and TIPO = 'V019') as B on A.COD_CHIUSURA_CORRETTO = B.CODIFICA) ";
							
		try {
			lista = em.createNativeQuery(queryStr).getResultList();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		return lista;
	}


	@Override
	public List<Object[]> getGiudiziDefinitiviSiscoByCampagna(long idCampagna, String tipoDifesa) {
		List<Object[]> lista = new ArrayList<Object[]>();
		
		String queryStr = "select " + 
							"B.DESCRIZIONE, " + 
						  	"isnull(A.QUANTITA,0) as quantita " +
						  	"from( " +
							"(select " +
							"COD_CHIUSURA_CORRETTO, " +
							"quantita " +
							"from AU_S_TESITO " +
							"where ID_S_SESSIONE in ( " +
							" select sess.ID_S_SESSIONE from AU_S_SESSIONE sess , AU_SESSIONI se where  sess.ID_SESSIONE = se.ID_SESSIONE and ID_CAMPAGNA = " +  idCampagna +
							" ) " +
							" and tipo_difesa = '" + tipoDifesa +"') as A right join " +
							"(select TIPO, CODIFICA, DESCRIZIONE " +
							"from AU_TPL_TIPOLOGICHE where codifica in ('1', '2', '3') and TIPO = 'V019') as B on A.COD_CHIUSURA_CORRETTO = B.CODIFICA) ";
							
		try {
			lista = em.createNativeQuery(queryStr).getResultList();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		return lista;
	}

	@Override
	public List<Object[]> getGiudiziDefinitiviSiscoByCampagnaAndSede(long idCampagna, String tipoDifesa, String codsede) {
		List<Object[]> lista = new ArrayList<Object[]>();
		
		String queryStr = 	"SELECT		 " +
						  	"		PR.TIPO_DIFESA  " +
						 	"		, PP.COD_CHIUSURA_CORRETTO " +
							"		, count(PR.TIPO_DIFESA) AS NUM " +
							"		, sum(CASE WHEN fs.PRESTAZIONE_ECONOMICA = 'S' THEN 1 ELSE 0 END) AS PRESTAZIONE_EC " +
							"		, isNull(sum(FS.IMPORTO_PRESTAZIONE_EROGATA),0) AS IMPORTO_PRESTAZIONE " +
							"		, isNull(sum(FS.IMPORTO_SPESE_LEGALI),0) AS SPESE_LEGALI " +
							"		, isNull(sum(FS.IMPORTO_SPESE_CTU),0) AS IMPORTO_SPESE_CTU   " + 
							"FROM	 " +
							"		AU_S_PRATICA PR   " +
							"		, ATPO_PRATICHE PRS  	 " +
							"		, ATPO_FASE_DATI FS " +
							"		, ATPO_FASE_POST_PERITALE PP  " +
							"		, ATPO_ASS_PRATICA_CAMPAGNA ASS_CAMP  " +
							"WHERE  " +
							"			PR.ID_PRATICA = PRS.ID_PRATICHE_SISCO   " +
							"		AND PRS.codiceSEDE = FS.COD_SEDE    " +
							
							"		AND FS.ID_FASE_DATI = PP.ID_FASE_DATI    " +
							"		AND FS.ID_FASE_DATI =  PP.ID_FASE_DATI   " +
							"		AND PRS.ID_PRATICHE_SISCO = ASS_CAMP.ID_PRATICA_SISCO " +
							"		AND PR.TIPO_DIFESA in ( " + tipoDifesa + " ) " +
							"		AND ASS_CAMP.ID_CAMPAGNA = " + idCampagna +
							"		AND FS.COD_SEDE IN ( SELECT CODICE_SEDE FROM AU_SEDE WHERE NOME_SEDE = '" + codsede + "' ) " +
							"GROUP BY " + 
							"		PR.TIPO_DIFESA " +
							"		, PP.COD_CHIUSURA_CORRETTO  ";
							
		try {
			lista = em.createNativeQuery(queryStr).getResultList();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		return lista;
	}
	
	@Override
	public List<Object[]> getGiudiziDefinitiviSiscoByCampagnaAndSedeWithOutFavorevoli(long idCampagna, String tipoDifesa, String codsede) {
		List<Object[]> lista = new ArrayList<Object[]>();
		
		String queryStr = 	"SELECT		 " +
						  	"		PR.TIPO_DIFESA  " +
						 	"		, PP.COD_CHIUSURA_CORRETTO " +
							"		, count(PR.TIPO_DIFESA) AS NUM " +
							"		, sum(CASE WHEN fs.PRESTAZIONE_ECONOMICA = 'S' THEN 1 ELSE 0 END) AS PRESTAZIONE_EC " +
							"		, isNull(sum(FS.IMPORTO_PRESTAZIONE_EROGATA),0) AS IMPORTO_PRESTAZIONE " +
							"		, isNull(sum(FS.IMPORTO_SPESE_LEGALI),0) AS SPESE_LEGALI " +
							"		, isNull(sum(FS.IMPORTO_SPESE_CTU),0) AS IMPORTO_SPESE_CTU   " + 
							"FROM	 " +
							"		AU_S_PRATICA PR   " +
							"		, ATPO_PRATICHE PRS  	 " +
							"		, ATPO_FASE_DATI FS " +
							"		, ATPO_FASE_POST_PERITALE PP  " +
							"		, ATPO_ASS_PRATICA_CAMPAGNA ASS_CAMP  " +
							"WHERE  " +
							"			PR.ID_PRATICA = PRS.ID_PRATICHE_SISCO   " +
							"		AND PRS.codiceSEDE = FS.COD_SEDE    " +
							
							"		AND FS.ID_FASE_DATI = PP.ID_FASE_DATI    " +
							"		AND FS.ID_FASE_DATI =  PP.ID_FASE_DATI   " +
							"		AND PRS.ID_PRATICHE_SISCO = ASS_CAMP.ID_PRATICA_SISCO " +
							"		AND PR.TIPO_DIFESA in ( " + tipoDifesa + " ) " +
							"		AND ASS_CAMP.ID_CAMPAGNA = " + idCampagna +
							"		AND FS.COD_SEDE IN ( SELECT CODICE_SEDE FROM AU_SEDE WHERE NOME_SEDE = '" + codsede + "' ) " +
							"		AND PP.COD_CHIUSURA_CORRETTO in ( 2,3,4 ) " +	
							"GROUP BY " + 
							"		PR.TIPO_DIFESA " +
							"		, PP.COD_CHIUSURA_CORRETTO  ";
							
		try {
			lista = em.createNativeQuery(queryStr).getResultList();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		return lista;
	}
	
	public int getGiudiziDefinitiviSiscoByCampagnaTotale(long idCampagna, String tipoDifesa, String sede){
		
		List<Object[]> lista = new ArrayList<Object[]>();
		String queryCount = "SELECT		" +
							"		'TOTALE' as TOTALE 	" +
							"		, count(PR.TIPO_DIFESA) AS NUM	" +
							"FROM		" +
							"		AU_S_PRATICA PR  	" +
							"		, ATPO_PRATICHE PRS  		" +
							"		, ATPO_FASE_DATI FS	" +
							"		, ATPO_FASE_POST_PERITALE PP 	" +
							"		, ATPO_ASS_PRATICA_CAMPAGNA ASS_CAMP 	" +
							"WHERE 	" +
							"			PR.ID_PRATICA = PRS.ID_PRATICHE_SISCO  	" +
							"		AND PRS.codiceSEDE = FS.COD_SEDE   	" +
							"		AND FS.ID_FASE_DATI = PP.ID_FASE_DATI   	" +
							"		AND FS.ID_FASE_DATI =  PP.ID_FASE_DATI  	" +
							"		AND PRS.ID_PRATICHE_SISCO = ASS_CAMP.ID_PRATICA_SISCO	" +
							"		AND PR.TIPO_DIFESA in ( " + tipoDifesa + " ) " +
							"		AND ASS_CAMP.ID_CAMPAGNA = " + idCampagna +
							"		AND FS.COD_SEDE IN ( SELECT CODICE_SEDE FROM AU_SEDE WHERE NOME_SEDE = '"+ sede +"' )" ;
		try {
			lista = em.createNativeQuery(queryCount).getResultList();
			for (Object[] objects : lista) {
				Integer rowCount = (Integer) objects[1];
				return rowCount;
			}
		} catch (Throwable e) {
			System.out.println("ERRORE getGiudiziDefinitiviSiscoByCampagnaTotale: " + e.getStackTrace());
			log.info("ERRORE getGiudiziDefinitiviSiscoByCampagnaTotale: " + e.getStackTrace());
			e.printStackTrace();
		}
		return 0;
	}

	public List<Object[]> getGiudiziDefinitiviEtichette(){
		List<Object[]> listaQuery = new ArrayList<Object[]>();
		
		String query = "select CODIFICA, DESCRIZIONE  from AU_TPL_TIPOLOGICHE where codifica in ('1', '2', '3' , '4') and TIPO = 'V019'";
		try {
			listaQuery = em.createNativeQuery(query).getResultList();
		} catch (Throwable e) {
			System.out.println("ERRORE getGiudiziDefinitiviEtichette: " + e.getStackTrace());
			log.info("ERRORE getGiudiziDefinitiviEtichette: " + e.getStackTrace());
			e.printStackTrace();
		}
		return listaQuery;
	}
	
	@Override
	public List<Object[]> getRiepilogoGiudiziByCampagna(long idCampagna) {
		List<Object[]> lista = new ArrayList<Object[]>();
					
		String queryStr =  "SELECT tab.descrizione, " +
						    "    Sum(TAB.quantita)            AS nr_giudizi, " +
						    "    Sum(TAB.perc_quantita)  * 100     AS perc_quantita, " +
						    "    Sum(TAB.num_prestazioni)     AS num_prestazioni, " +
						    "    Sum(TAB.importo_prestazione) AS importo_prestazione, " +
						    "    Sum(TAB.spese_legali)        AS spese_legali, " +
						    "    Sum(TAB.spese_ctu)        AS spese_legali_ctu " +
							"FROM   (SELECT * " +
							"        FROM   (SELECT 'U02'                            AS esito, " +
							"                       'COMPLETA + ESSENZIALE'          AS DESCRIZIONE, " +
							"                       Isnull(a.quantita, 0)            AS quantita, " +
							"                       Isnull(a.perc_quantita, 0)       AS perc_quantita, " +
							"                       Isnull(a.num_prestazioni, 0)     AS num_prestazioni, " +
							"                       Isnull(a.importo_prestazione, 0) AS importo_prestazione, " +
							"                       Isnull(a.spese_legali, 0)        AS spese_legali, " +
							"                       Isnull(a.spese_legali_ctu, 0)        AS spese_ctu " +
							"                FROM   (" +
							"							select * from au_s_tesito where COD_CHIUSURA_CORRETTO in (2,3) AND " +
							"							ID_S_SESSIONE in ( select sess.ID_S_SESSIONE from AU_S_SESSIONE sess , AU_SESSIONI se where  sess.ID_SESSIONE = se.ID_SESSIONE and ID_CAMPAGNA = "+idCampagna +" ) " +
							"						) a " +
							"                       RIGHT JOIN (SELECT * " +
							"                                   FROM   au_tpl_tipologiche b " +
							"                                   WHERE  tipo = 'V009' " +
							"                                          AND b.codifica IN ( 'U02', 'U03' )) b " +
							"                               ON a.tipo_difesa = B.codifica) AS A " +
							"        UNION ALL " +
							"        SELECT * " +
							"        FROM   (SELECT b.codifica                       AS esito, " +
							"                       'INCOMPLETA'                     AS DESCRIZIONE, " +
							"                       Isnull(a.quantita, 0)            AS quantita, " +
							"                       Isnull(a.perc_quantita, 0)       AS perc_quantita, " +
							"                       Isnull(a.num_prestazioni, 0)     AS num_prestazioni, " +
							"                       Isnull(a.importo_prestazione, 0) AS importo_prestazione, " +
							"                       Isnull(a.spese_legali, 0)        AS spese_legali, " +
							"                       Isnull(a.spese_legali_ctu, 0)        AS spese_ctu " +
							"                FROM   (select * from au_s_tesito where COD_CHIUSURA_CORRETTO in (2,3) AND " +
							"							ID_S_SESSIONE in ( select sess.ID_S_SESSIONE from AU_S_SESSIONE sess , AU_SESSIONI se where  sess.ID_SESSIONE = se.ID_SESSIONE and ID_CAMPAGNA = "+idCampagna +" ) " +
							"						) a " +
							"                       RIGHT JOIN (SELECT * " +
							"                                   FROM   au_tpl_tipologiche b " +
							"                                   WHERE  tipo = 'V009' " +
							"                                          AND b.codifica IN ( 'U04' )) b " +
							"                               ON a.tipo_difesa = B.codifica) AS A) AS TAB " +
							"GROUP  BY tab.descrizione   ";
		try {
			lista = em.createNativeQuery(queryStr).getResultList();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		return lista;
	}
	@Override
	public String[] getElencoRischi() {
		String[] bean = null;
try {
		
		String queryStr = "SELECT DESCRIZIONE_RISCHIO,CODICE_RISCHIO  FROM [AU_M_RISCHIO] where ID_AUDIT=1 order by 2";
		
		List<Object[]> ret =  em.createNativeQuery(queryStr).getResultList();
		bean = new String[ret.size()];
		for(int n = 0 ; n < ret.size();n++){
			System.out.println(ret.get(n)[0]);
			bean[n]=ret.get(n)[0].toString();
		}
		

		} catch (Throwable e) {
			e.printStackTrace();

		}
		
		return bean;
	}
	
	
	// -------------------------------------------------------------------------------------------
	// NUOVI REPORT ANGELO ACCESSO
	@Override
	public List<Object[]> getEsitoByTipoDifesaAnnuale(long idCampagna) {
		// TODO Stub di metodo generato automaticamente
		return null;
	}
	@Override
	public List<Object[]> getEsitoByTipoDifesaWithDissensoAnnuale(
			long idCampagna) {
		// TODO Stub di metodo generato automaticamente
		return null;
	}
	@Override
	public List<Object[]> getGiudiziDefinitiviEtichetteAnnuale() {
		// TODO Stub di metodo generato automaticamente
		return null;
	}
	@Override
	public List<String> getListaAuditorsAnnuale(long idCampagna) {
		// TODO Stub di metodo generato automaticamente
		return null;
	}
	@Override
	public List<Object[]> getMNonConfByIdFaseAnnuale(Long idFase,
			long idCampagna) {
		// TODO Stub di metodo generato automaticamente
		return null;
	}
	@Override
	public Object[] getReportAccessoPDFAnnuale(long idCampagna) {
		// TODO Stub di metodo generato automaticamente
		return null;
	}
	@Override
	public List<Object[]> getRiepilogoDocMancAnnuale(long idCampagna) {
		// TODO Stub di metodo generato automaticamente
		return null;
	}
	@Override
	public List<Object[]> getRiepilogoFascicoloAnnuale(long idCampagna) {
		// TODO Stub di metodo generato automaticamente
		return null;
	}
	@Override
	public List<Object[]> getRiepilogoFasiAnnuale(long idCampagna) {
		List<Object[]> lista = new ArrayList<Object[]>();
		
		String queryStr = "SELECT t.ID_FASE, crs.DESCRIZIONE, SUM(t.VALORE_PESATO_FASE) as INCC FROM AU_C_NONCONF AS t"
					+ "	join CRS_SOTTOPROCESSO crs on t.ID_FASE = crs.ID_SOTTOPROCESSO"
					+ "	where t.ID_C_CAMPAGNA = " + idCampagna
					+ "	group by t.ID_C_CAMPAGNA, t.ID_FASE, crs.DESCRIZIONE";
		
		try {
			lista = em.createNativeQuery(queryStr).getResultList();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		return lista;		
	}
	@Override
	public List<Object[]> getRiepilogoGiudiziAnnuale(long idCampagna) {
		// TODO Stub di metodo generato automaticamente
		return null;
	}
	@Override
	public List<Object[]> getRiepilogoIstanzeAnnuale(long idCampagna) {
		// TODO Stub di metodo generato automaticamente
		return null;
	}
	@Override
	public List<Object[]> getRiepilogoRischiAnnuale(long idCampagna) {
		List<Object[]> lista = new ArrayList<Object[]>();
		
		String queryStr = "select r.ID_M_RISCHIO, 0 as ID_S_RISCHIO, m.DESCRIZIONE_RISCHIO ,0 as importo, 0 as SU_PS_PERC, 0 as NUM from AU_C_RISESPR as r"
						+ " join AU_S_RISCHIO as s on r.ID_M_RISCHIO = s.ID_M_RISCHIO"
						+ " join AU_M_RISCHIO as m on r.ID_M_RISCHIO = m.ID_M_RISCHIO"
						+ " where r.ID_C_CAMPAGNA = " + idCampagna
						+ " and RAGGRUPPAMENTO_RISCHIO not in (3,4)"
						+ " group by r.ID_M_RISCHIO, m.DESCRIZIONE_RISCHIO";
		
		try {
			lista = em.createNativeQuery(queryStr).getResultList();


		} catch (Throwable e) {
			e.printStackTrace();

		}		
		return lista;
	}
	@Override
	public List<Object[]> getRisEsprByIdMNonConfAnnuale(Long idMRischio,
			long idCampagna) {
		
		List<Object[]> lista = new ArrayList<Object[]>();
		
		String queryStr = "select DESCRIZIONE, NUM, SU_TOT, IMPORTO from AU_C_RISESPR"
							+ " where ID_C_CAMPAGNA = " + idCampagna
							+ " and ID_M_RISCHIO = " + idMRischio;	
 		
		try {
			lista = em.createNativeQuery(queryStr).getResultList();


		} catch (Throwable e) {
			e.printStackTrace();

		}		
		return lista;
	}
	@Override
	public List<Object[]> getRisultatiByTempoAnnuale(long idCampagna) {
		// TODO Stub di metodo generato automaticamente
		return null;
	}
	@Override
	public List<Object[]> getTipoDifesaIncompletaAnnuale(long idCampagna,
			String tipoDifesa) {
		// TODO Stub di metodo generato automaticamente
		return null;
	}
	@Override
	public List<Object[]> getVarCompByIdMNonConfAnnuale(Long idMNonConf) {
		// TODO Stub di metodo generato automaticamente
		return null;
	}
	
	// --------------------------------------------------------------------------------------------
	// NUOVI REPORT ANGELO ALLEGATO
	@Override
	public String getAllegatoReportAccessoPDFAnnuale(long idCampagna) {
	
		
		String queryStr = "SELECT NOME FROM AU_CAMPAGNA WHERE ID_CAMPAGNA = " + idCampagna;
		
		try {
			List<String> resultList = em.createNativeQuery(queryStr).getResultList();

			if(!resultList.isEmpty() && resultList.size() > 0){
				return resultList.get(0).toString();
			}

		} catch (Throwable e) {
			e.printStackTrace();

		}
		
		return null;
	}
	
	@Override
	public List<AtpoPratiche> getReportAllegatiPDFAnnuale(String sede,
			long idCampagna) {
/*		String queryStr = "select prt.* from ATPO_PRATICHE_SISCO sisco, ATPO_PRATICHE prt, ATPO_ASS_PRATICA_CAMPAGNA ass, AU_SESSIONI sess " 	
			+"where sisco.ID_PRATICHE_SISCO = prt.ID_PRATICHE_SISCO "
			+"and sisco.ID_PRATICHE_SISCO = ass.ID_PRATICA_SISCO "
			//+"and sess.ID_SESSIONE= "+idSessione
			+"and sess.ID_CAMPAGNA = ass.ID_CAMPAGNA "
			+"and sess.ID_CAMPAGNA =  "  + idCampagna
			//+"and prt.sede = '"+sede+"'"
			+" and (prt.Esito like 'SFAVOREVOLE%' or  "
			+"	prt.Esito like 'FAVOREVOLE%' or  "
			+"	prt.Esito like 'PARZIALMENTE%' ) ";*/
		String queryStr = "select prt.* " +
				"FROM 	AU_S_PRATICA pra	" +
				", AU_S_SESSIONE ausess	" +
				", AU_SESSIONI sess	" +
				", ATPO_PRATICHE prt" +
				" where " +
				"	pra.ID_S_SESSIONE = ausess.ID_S_SESSIONE" +
				" AND ausess.ID_SESSIONE = sess.ID_SESSIONE" +
				" AND pra.ESAME_PRATICA = 'C'" +
				" AND ausess.STATO_ESAME_SESSIONE = 'C'" +
				" AND pra.ID_PRATICA = prt.ID_PRATICHE" +
				" AND sess.ID_CAMPAGNA =  " + idCampagna +
				" AND (prt.Esito like 'SFAVOREVOLE%' or  	prt.Esito like 'FAVOREVOLE%' or  	prt.Esito like 'PARZIALMENTE%' ) ";
		try {
			
			List<AtpoPratiche> list = (List<AtpoPratiche>)em.createNativeQuery(queryStr,AtpoPratiche.class).getResultList();
			return list;


		} catch (Throwable e) {
			e.printStackTrace();

		}
		
		return null;
	}
	@Override
	public String calcolaIndicatoriCampagna(long idCampanga) {
		try{
			Query query = em.createQuery("exec CalcolaIndicatoriCampagna(2)");
			
						
			int a = 0;
			a= 1;
			
		}catch(Exception ex){
			System.out.println(ex);
		}
		return null;
	}
	
	@Override
	public String getCampagnaAnno(long idCampanga) {
		String anno = "";
		List<Object[]> lista = new ArrayList<Object[]>();
		String queryStr = "SELECT cast(DATEPART(year, [DATA_INIZIO]) as varchar) AS Anno  FROM AU_CAMPAGNA where ID_CAMPAGNA  = " + idCampanga;	
 		
		try {
			lista = em.createNativeQuery(queryStr).getResultList();
			
			for (Object row : lista) {
				anno = (String)row;
			}
			
		} catch (Throwable e) {
			e.printStackTrace();

		}		
		return anno;
	}
	@Override
	public List<String> getSediByCampagna(long idCampanga) {
		
		List<String> listaSedi = new ArrayList<String>();
		
		String query = " select " 
						+ " 	SEDE " 
						+ " from " 
						+ " 	AU_SESSIONI assi, " 
						+ " 	AU_S_SESSIONE ass , " 
						+ " 	AU_CAMPAGNA ac " 
						+ " WHERE " 
						+ " 	assi.ID_SESSIONE = ass.ID_SESSIONE " 
						+ " 	AND assi.ID_CAMPAGNA = ac.ID_CAMPAGNA " 
						+ " 	AND ac.ID_CAMPAGNA = " + idCampanga
						+ " 	AND ass.STATO_ESAME_SESSIONE = 'C'";
		List<Object[]> lista = new ArrayList<Object[]>();
		
		try {
			lista = em.createNativeQuery(query).getResultList();
			
			for (Object  row : lista) {
				 String sede = (String)row;
				 listaSedi.add(sede);
			}
			
		} catch (Throwable e) {
			e.printStackTrace();
		}	
		
		return listaSedi;
	}
}
