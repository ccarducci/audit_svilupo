package it.tecnet.crs.audit.jpa.dao;

import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoFaseAcquisizioneIstanza;
import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoFaseAutotutelaResistenzaGiudizio;
import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoFaseDati;
import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoFaseEsecuzioneProvvedimenti;
import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoFaseGestioneIstruttoria;
import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoFasePeritale;
import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoFasePostPeritale;
import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoPratiche;
import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoPraticheSISCO;
import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoRiepilogoFascicolo;
import it.tecnet.crs.ATPO.auditors.web.dto.AtpoAuMContestoDto;
import it.tecnet.crs.audit.model.AuAssVerbaleCampagna;
import it.tecnet.crs.audit.model.AuSediCritiche;
import it.tecnet.crs.jpa.model.AuAssCampPrtAtpo;
import it.tecnet.crs.jpa.model.AuCampagna;
import it.tecnet.crs.jpa.model.AuCampione;
import it.tecnet.crs.jpa.model.AuRegoleCampagna;
import it.tecnet.crs.jpa.model.AuRisultatiCampagna;
import it.tecnet.crs.jpa.model.AuRisultatiCampione;
import it.tecnet.crs.jpa.model.AuSPratica;
import it.tecnet.crs.jpa.model.AuSSessione;
import it.tecnet.crs.jpa.model.AuSede;
import it.tecnet.crs.jpa.model.AuSessioni;
import it.tecnet.crs.util.PraticaUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

public class AuCampagnaDaoImpl implements AuCampagnaDao {

	@PersistenceContext()
	private EntityManager em;

	protected static Logger log = Logger.getLogger(AuCampagnaDaoImpl.class);
	@Override
	public AtpoAuMContestoDto saveDatiContesto(
			AtpoAuMContestoDto atpoAuMContestoDto) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Object[] getDatiContesto(long idSessione, long idCampagna) {
		List<Object[]> list = new ArrayList<Object[]>();

		String query = "SELECT mc.ID_M_CONTESTO, mc.FUNZIONARI, mc.MEDICI_INPS, mc.APERTE, mc.GG_MEDI_APERTURA, "+
		"mc.GG_MEDI_CHIUSURA, mc.ABBANDONO_DOM, mc.ABBANDONO_REV, "+
		"mc.ACQ_ERR_DOM, mc.ACQ_ERR_REV,mc.CESS_MAT_CONTENDERE_DOM, mc.CESS_MAT_CONTENDERE_REV, "+
		"mc.DEF_AUT_ATP_DOM, mc.DEF_AUT_ATP_REV,  mc.DISSENSO_DOM, mc.DISSENSO_REV, "+
		"mc.ESTINTA_DOM ,mc.ESTINTA_REV,mc.FAV_DOM, mc.FAV_REV, mc.INAMMISS_DOM, mc.INAMMISS_REV, "+
		"mc.INCOMP_DOM, mc.INCOMP_REV,mc.PARZ_FAV_DOM,mc.PARZ_FAV_REV,mc.SFAV_DOM,mc.SFAV_REV, "+
		"mc.TRASF_ALTRO_SETT_DOM,mc.TRASF_ALTRO_SETT_REV, "+
		"mc.DISSENSO_INPS as SENTENZEPRIMOG, mc.CTU, mc.VISITE_PERITALI, mc.PERC_CVP_SU_NR_CTU, "+
		"mc.PERC_VP_SU_NR_MEDICI, mc.PERC_PARERE_CONCORDE, "+
		"c.DATA_INIZIO_OSSERVAZIONE, c.DATA_FINE_OSSERVAZIONE "+
		"FROM AU_M_CONTESTO mc, AU_CAMPAGNA c "+
		"WHERE mc.ID_SESSIONE="+idSessione +" and c.ID_CAMPAGNA="+idCampagna;

		try {

			Query querySel = em.createNativeQuery(query);
			list =querySel.getResultList();

			if(list != null && list.size() != 0){
				return list.get(0);
			}

		} catch (Throwable e) {
			e.printStackTrace();

		}
		return null;
	}


//	public List<AuReportSediLabel> getListaReportSediLabel(long idCampagna){
//		List<AuReportSediLabel> list = new ArrayList<AuReportSediLabel>();
//		String queryStr = "select r.* from AU_REPORT_SEDI_LABEL r "+
//		"where r.id_campagna="+idCampagna;	
//
//		try {
//			Query querySel = em.createNativeQuery(queryStr,AuReportSediLabel.class);
//			list = querySel.getResultList();
//
//		} catch (Throwable e) {
//			e.printStackTrace();
//			log.error(e);
//		}
//		return list;
//	}
	
	public List<AuSediCritiche> getListaSediCritiche( Integer pageNumber,
			Integer pageSize, Integer columnNameToOrder, String orderType,String textSearch){

		List<AuSediCritiche> list = new ArrayList<AuSediCritiche>();
		String queryStr = "select * from AU_SEDI_CRITICHE ";		

		
		if (!StringUtils.isEmpty(textSearch)) {
			queryStr += " where ((" + "upper(Sede) like UPPER('%"+ textSearch + "%')"+ ")";
			queryStr += " or (" + "upper(Anno) like UPPER('%"+ textSearch + "%')"+ "))"; 
		}

		queryStr +=" ORDER BY "+(columnNameToOrder)+" "+orderType +" OFFSET "+pageNumber+" ROWS ";
		queryStr +=" FETCH NEXT "+pageSize+" ROWS ONLY ";


		try {
			Query querySel = em.createNativeQuery(queryStr,AuSediCritiche.class);
			list = querySel.getResultList();

		} catch (Throwable e) {
			e.printStackTrace();
			log.error(e);
		}
		return list;
	}
	
	public Integer countSediCritiche(Integer pageNumber,
			Integer pageSize, Integer columnNameToOrder, String orderType,String textSearch){

		Integer count=null;
		String queryStr = "select count(*) from AU_SEDI_CRITICHE  ";		

		if (!StringUtils.isEmpty(textSearch)) {
			queryStr += " where ((" + "upper(Sede) like UPPER('%"+ textSearch + "%')"+ ")";
			queryStr += " or (" + "upper(Anno) like UPPER('%"+ textSearch + "%')"+ "))"; 
		}

		/*queryStr +=" ORDER BY "+(columnNameToOrder + 1)+" OFFSET "+pageNumber+" ROWS ";
		queryStr +=" FETCH NEXT "+pageSize+" ROWS ONLY ";*/


		try {
			Query querySel = em.createNativeQuery(queryStr);
			count = (Integer) querySel.getSingleResult();

		} catch (Throwable e) {
			e.printStackTrace();
			log.error(e);
		}
		return count;
	}


	public List getListaReportSedi(long idCampagna, Integer pageNumber,
			Integer pageSize, Integer columnNameToOrder, String orderType,String textSearch){

		List list = new ArrayList();
		String queryStr = "select s.id_sede, s.NOME_SEDE,r.indicatore1, r.indicatore2, r.indicatore3, r.indicatore4, r.indicatore5, r.indicatore6, r.indicatore7, r.indicatore8, r.indicatore9, r.indicatore10"+
		" from AU_REPORT_SEDI r, AU_SEDE s "+
		"where r.id_sede=s.ID_SEDE and r.id_campagna="+idCampagna;		

		if (!StringUtils.isEmpty(textSearch)) {
			queryStr += " and (" + "upper(s.NOME_SEDE) like UPPER('%"+ textSearch + "%')"+ ")";
		}

		queryStr +=" ORDER BY "+(columnNameToOrder + 1)+" "+orderType +" OFFSET "+pageNumber+" ROWS ";
		queryStr +=" FETCH NEXT "+pageSize+" ROWS ONLY ";


		try {
			Query querySel = em.createNativeQuery(queryStr);
			list = querySel.getResultList();

		} catch (Throwable e) {
			e.printStackTrace();
			log.error(e);
		}
		return list;
	}

	public Integer countReportSedi(long idCampagna, Integer pageNumber,
			Integer pageSize, Integer columnNameToOrder, String orderType,String textSearch){

		Integer count=null;
		String queryStr = "select count(*) from AU_REPORT_SEDI r, AU_SEDE s "+
		"where r.id_sede=s.ID_SEDE and r.id_campagna="+idCampagna;		

		if (!StringUtils.isEmpty(textSearch)) {
			queryStr += " and (" + "upper(s.NOME_SEDE) like UPPER('%"+ textSearch + "%')"+ ")";
		}

		/*queryStr +=" ORDER BY "+(columnNameToOrder + 1)+" OFFSET "+pageNumber+" ROWS ";
		queryStr +=" FETCH NEXT "+pageSize+" ROWS ONLY ";*/


		try {
			Query querySel = em.createNativeQuery(queryStr);
			count = (Integer) querySel.getSingleResult();

		} catch (Throwable e) {
			e.printStackTrace();
			log.error(e);
		}
		return count;
	}



	public List<AuSessioni> getListaSessioniCampagna(long idCampagna, Integer pageNumber,
			Integer pageSize, Integer columnNameToOrder, String orderType,String textSearch){

		List<AuSessioni> campagnaList = new ArrayList<AuSessioni>();
		String queryStr =" select sess.* from AU_SESSIONI sess where sess.ID_CAMPAGNA="+idCampagna;	

		if (!StringUtils.isEmpty(textSearch)) {
			queryStr += " and (" + "upper(sess.SEDE) like UPPER('%"+ textSearch + "%')"+ ")";
		}

		queryStr +=" ORDER BY "+(columnNameToOrder + 1)+" "+orderType +" OFFSET "+pageNumber+" ROWS ";
		queryStr +=" FETCH NEXT "+pageSize+" ROWS ONLY ";


		try {
			Query querySel = em.createNativeQuery(queryStr,AuSessioni.class);
			campagnaList = querySel.getResultList();

		} catch (Throwable e) {
			e.printStackTrace();
			log.error(e);
		}
		return campagnaList;
	}

	public Integer  countListaSessioniCampagna(long idCampagna,Integer pageNumber, Integer pageSize, Integer columnNameToOrder,
			String orderType, String textSearch) {

		Integer count = null;
		String queryStr = "select count(*) from AU_SESSIONI sess where sess.ID_CAMPAGNA="+idCampagna;	

		if (!StringUtils.isEmpty(textSearch)) {
			queryStr += " and (" + "upper(sess.SEDE) like UPPER('%"+ textSearch + "%')"+ ")";
		}

		//queryStr +=" ORDER BY "+(columnNameToOrder + 1)+" OFFSET "+pageNumber+" ROWS ";
		//	queryStr +=" FETCH NEXT "+pageSize+" ROWS ONLY ";


		try {
			Query querySel = em.createNativeQuery(queryStr);
			count = (Integer) querySel.getSingleResult();
		} catch (Throwable e) {
			e.printStackTrace();
			log.error(e);
		}
		return count;
	}

	public List<Object[]> getListaSessioni(Integer pageNumber,
			Integer pageSize, Integer columnNameToOrder, String orderType,String textSearch,long idUtente){

		List<Object[]> campagnaList = new ArrayList<Object[]>();
		String queryStr = "SELECT sess.ID_SESSIONE, cmp.NOME, sess.SEDE, sess.DATA_INIZIO, sess.DATA_FINE, sess.STATO "+
		" from AU_SESSIONI sess,AU_CAMPAGNA cmp,AU_ASS_UTENTE_AUDIT ass,AU_AUDIT au"+ 
		" WHERE ass.ID_UTENTE= "+idUtente+
		" and sess.ID_CAMPAGNA=cmp.ID_CAMPAGNA"+
		" and cmp.ID_AUDIT= ass.ID_AUDIT"+
		" and au.ID_AUDIT=ass.ID_AUDIT";

		if (!StringUtils.isEmpty(textSearch)) {
			queryStr += " and ((" + "upper(sess.SEDE) like UPPER('%"+ textSearch + "%')"+ ")";
			queryStr += " or (" + "upper(cmp.NOME) like UPPER('%"+ textSearch + "%')"+ "))"; 
		}

		queryStr +=" ORDER BY "+(columnNameToOrder + 1)+" "+orderType +" OFFSET "+pageNumber+" ROWS ";
		queryStr +=" FETCH NEXT "+pageSize+" ROWS ONLY ";


		try {
			Query querySel = em.createNativeQuery(queryStr);
			campagnaList = querySel.getResultList();

		} catch (Throwable e) {
			e.printStackTrace();
			log.error(e);
		}
		return campagnaList;
	}

	public Integer  countListaSessioni(Integer pageNumber, Integer pageSize, Integer columnNameToOrder,
			String orderType, String textSearch, long idUtente) {

		Integer count = null;
		String queryStr = "SELECT count(sess.ID_SESSIONE)"+
		" from AU_SESSIONI sess,AU_CAMPAGNA cmp,AU_ASS_UTENTE_AUDIT ass,AU_AUDIT au"+ 
		" WHERE ass.ID_UTENTE= "+idUtente+
		" and sess.ID_CAMPAGNA=cmp.ID_CAMPAGNA"+
		" and cmp.ID_AUDIT= ass.ID_AUDIT"+
		" and au.ID_AUDIT=ass.ID_AUDIT";	

		if (!StringUtils.isEmpty(textSearch)) {
			queryStr += " and ((" + "upper(sess.SEDE) like UPPER('%"+ textSearch + "%')"+ ")";
			queryStr += " or (" + "upper(cmp.NOME) like UPPER('%"+ textSearch + "%')"+ "))";  
		}

		try {
			Query querySel = em.createNativeQuery(queryStr);
			count = (Integer) querySel.getSingleResult();
		} catch (Throwable e) {
			e.printStackTrace();
			log.error(e);
		}
		return count;
	}

	public List getListaCampagna(Integer pageNumber,
			Integer pageSize, Integer columnNameToOrder, String orderType,String textSearch,long idUtente){

		List campagnaList = new ArrayList();
		String queryStr = "SELECT cmp.id_campagna, cmp.nome, au.nome, cmp.data_inizio, cmp.data_fine, cmp.data_inizio_osservazione, cmp.data_fine_osservazione, cmp.stato, 'vigilanza' as TIPO_ACCESSO "+
		" FROM AU_CAMPAGNA cmp,AU_ASS_UTENTE_AUDIT ass,AU_AUDIT au "+
		" WHERE ass.ID_UTENTE= "+idUtente+
		" and cmp.ID_AUDIT= ass.ID_AUDIT"+
		" and au.ID_AUDIT=ass.ID_AUDIT";

		if (!StringUtils.isEmpty(textSearch)) {
			queryStr += " and (" + "upper(cmp.nome) like UPPER('%"+ textSearch + "%')"+ ")";
		}
		//quando pageNumber =-1 la query non popola una tabella ma un menu a tendina
		if(pageNumber != -1){
			queryStr +=" ORDER BY "+(columnNameToOrder + 1)+" "+orderType +" OFFSET "+pageNumber+" ROWS ";
			queryStr +=" FETCH NEXT "+pageSize+" ROWS ONLY ";
		}



		try {
			Query querySel = em.createNativeQuery(queryStr);
			campagnaList = querySel.getResultList();

		} catch (Throwable e) {
			e.printStackTrace();
			log.error(e);
		}
		return campagnaList;
	}

	public Integer countListaCampagna(Integer pageNumber,
			Integer pageSize, Integer columnNameToOrder, String orderType,String textSearch, long idUtente){
		Integer count = null;
		String queryStr = "SELECT COUNT (ID_CAMPAGNA)"+
		" FROM AU_CAMPAGNA cmp,AU_ASS_UTENTE_AUDIT ass,AU_AUDIT au"+
		" WHERE ass.ID_UTENTE= "+idUtente+
		" and cmp.ID_AUDIT= ass.ID_AUDIT"+
		" and au.ID_AUDIT=ass.ID_AUDIT";		

		if (!StringUtils.isEmpty(textSearch)) {
			queryStr += " and (" + "upper(cmp.nome) like UPPER('%"+ textSearch + "%')"+ ")";
		}

		//queryStr +=" ORDER BY "+(columnNameToOrder + 1)+" OFFSET "+pageNumber+" ROWS ";
		//queryStr +=" FETCH NEXT "+pageSize+" ROWS ONLY ";

		try {
			Query querySel = em.createNativeQuery(queryStr);
			count = (Integer) querySel.getSingleResult();
		} catch (Throwable e) {
			e.printStackTrace();
			log.error(e);
		}
		return count;
	}

	public List<AuRegoleCampagna> getListaRegoleCampagna(long idCampagna,Integer pageNumber,
			Integer pageSize, Integer columnNameToOrder, String orderType,String textSearch){

		List<AuRegoleCampagna> rcList = new ArrayList<AuRegoleCampagna>();
		String queryStr = "select rc.ID_REGOLA_CAMPAGNA, rc.DESCRIZIONE , rc.valore1, rc.valore2, rc.valore3, rc.valore4, rc.valore5, rc.STATO from AU_REGOLE_CAMPAGNA rc, AU_ASS_REGOLE_CAMPAGNA ass, AU_CAMPAGNA camp  "+ 
		"where camp.ID_CAMPAGNA = "+idCampagna+
		"and camp.ID_CAMPAGNA = ass.ID_CAMPAGNA "+
		"and rc.ID_REGOLA_CAMPAGNA = ass.ID_REGOLA_CAMPAGNA";

		if (!StringUtils.isEmpty(textSearch)) {
			queryStr += " and (" + "upper(rc.descrizione) like UPPER('%"+ textSearch + "%')"+ ")";
		}

		queryStr +=" ORDER BY "+(columnNameToOrder + 1)+" "+orderType +" OFFSET "+pageNumber+" ROWS ";
		queryStr +=" FETCH NEXT "+pageSize+" ROWS ONLY ";

		try {
			Query querySel = em.createNativeQuery(queryStr,AuRegoleCampagna.class);
			rcList = querySel.getResultList();

		} catch (Throwable e) {
			e.printStackTrace();
			log.error(e);
		}
		return rcList;
	}

	public Integer countListaRegoleCampagna(long idCampagna,Integer pageNumber,
			Integer pageSize, Integer columnNameToOrder, String orderType,String textSearch){
		Integer count = null;

		String queryStr = "SELECT COUNT (ID_ASS_REGOLA_CAMPAGNA) FROM AU_ASS_REGOLE_CAMPAGNA ass,AU_CAMPAGNA cmp,AU_REGOLE_CAMPAGNA reg"+
		" WHERE cmp.ID_CAMPAGNA= "+idCampagna+
		" and ass.ID_CAMPAGNA=cmp.ID_CAMPAGNA"+
		" and ass.ID_REGOLA_CAMPAGNA=reg.ID_REGOLA_CAMPAGNA";

		if (!StringUtils.isEmpty(textSearch)) {
			queryStr += " and (" + "upper(reg.descrizione) like UPPER('%"+ textSearch + "%')"+ ")";
		}


		try {
			Query querySel = em.createNativeQuery(queryStr);
			count = (Integer) querySel.getSingleResult();
		} catch (Throwable e) {
			e.printStackTrace();
			log.error(e);
		}
		return count;
	}

	public List<Object[]> getListaVerbaliCampagna(long idCampagna,Integer pageNumber,
			Integer pageSize, Integer columnNameToOrder, String orderType,String textSearch){

		List<Object[]> prtList = new ArrayList<Object[]>();
		String queryStr = "select sede.NOME_SEDE, sisco.FASCICOLO, sisco.DATA_NOTIFICA, sisco.DATA_APERTURA, cogisan.RG, cogisan.PARTE, cogisan.VISITA_PERITALE , sisco.ESITO  "
			+" from ATPO_PRATICHE_SISCO sisco, ATPO_PRATICHE_COGISAN cogisan, ATPO_ASS_PRATICA_CAMPAGNA ass, "
			+" AU_CAMPAGNA cmp,  AU_SEDE sede "
			+" where cmp.ID_CAMPAGNA="+idCampagna
			+" and ass.ID_CAMPAGNA = cmp.ID_CAMPAGNA "
			+" and ass.ID_PRATICA_SISCO = sisco.ID_PRATICHE_SISCO "
			+" and sisco.COD_SEDE= sede.CODICE_SEDE "
			+" and sisco.FASCICOLO = cogisan.FASCICOLO	 ";

		if (!StringUtils.isEmpty(textSearch)) {
			queryStr += " and (" + "upper(sisco.FASCICOLO) like UPPER('%"+ textSearch + "%')"+ ")";
		}

		queryStr +=" ORDER BY "+(columnNameToOrder+1)+" "+orderType +" OFFSET "+pageNumber+" ROWS ";
		queryStr +=" FETCH NEXT "+pageSize+" ROWS ONLY ";

		try {
			Query querySel = em.createNativeQuery(queryStr);
			prtList = querySel.getResultList();

		} catch (Throwable e) {
			e.printStackTrace();
			log.error(e);
		}
		return prtList;
	}
	public List<Object[]> getListaVerbaliCampagna(long idCampagna){

		List<Object[]> prtList = new ArrayList<Object[]>();
		String queryStr = "select sede.NOME_SEDE, sisco.FASCICOLO, sisco.DATA_NOTIFICA, " +
				"sisco.DATA_APERTURA, cogisan.RG, cogisan.PARTE, cogisan.VISITA_PERITALE , pratiche.Esito , pratiche.PresenzaVisitePeritali, pratiche.Funzionario , pratiche.MedicoINPS , pratiche.TempoAperturaPratica , pratiche.TempoChiusuraPratica ,pratiche.PresenzaCTU "
			+" from ATPO_PRATICHE_SISCO sisco,ATPO_PRATICHE pratiche, ATPO_PRATICHE_COGISAN cogisan, ATPO_ASS_PRATICA_CAMPAGNA ass, "
			+" AU_CAMPAGNA cmp,  AU_SEDE sede "
			+" where cmp.ID_CAMPAGNA="+idCampagna
			+" and ass.ID_CAMPAGNA = cmp.ID_CAMPAGNA "
			+" and ass.ID_PRATICA_SISCO = sisco.ID_PRATICHE_SISCO "
			+" and pratiche.ID_PRATICHE_SISCO = sisco.ID_PRATICHE_SISCO "
			+" and sisco.COD_SEDE= sede.CODICE_SEDE "
			+" and sisco.FASCICOLO = cogisan.FASCICOLO	 ";

		
		try {
			Query querySel = em.createNativeQuery(queryStr);
			prtList = querySel.getResultList();

		} catch (Throwable e) {
			e.printStackTrace();
			log.error(e);
		}
		return prtList;
	}
	public List<Object[]> getListaVerbaliCampagnaSede(long idCampagna,String sede){

		List<Object[]> prtList = new ArrayList<Object[]>();
//		String queryStr = "select sede.NOME_SEDE, sisco.FASCICOLO, sisco.DATA_NOTIFICA, " +
//				"sisco.DATA_APERTURA, cogisan.RG, cogisan.PARTE, cogisan.VISITA_PERITALE , pratiche.Esito , pratiche.PresenzaVisitePeritali, pratiche.Funzionario , pratiche.MedicoINPS , pratiche.TempoAperturaPratica , pratiche.TempoChiusuraPratica ,pratiche.PresenzaCTU "
//			+" from ATPO_PRATICHE_SISCO sisco,ATPO_PRATICHE pratiche, ATPO_PRATICHE_COGISAN cogisan, ATPO_ASS_PRATICA_CAMPAGNA ass, "
//			+" AU_CAMPAGNA cmp,  AU_SEDE sede "
//			+" where cmp.ID_CAMPAGNA="+idCampagna
//			+" and ass.ID_CAMPAGNA = cmp.ID_CAMPAGNA "
//			+" and ass.ID_PRATICA_SISCO = sisco.ID_PRATICHE_SISCO "
//			+" and pratiche.ID_PRATICHE_SISCO = sisco.ID_PRATICHE_SISCO "
//			+" and sisco.COD_SEDE= sede.CODICE_SEDE "
//			+" and sisco.FASCICOLO = cogisan.FASCICOLO	 ";
		String queryStr = "select 		 pratiche.Sede, sisco.FASCICOLO, sisco.DATA_NOTIFICA, sisco.DATA_APERTURA, pratiche.Intestatario, pratiche.Intestatario, pratiche.PresenzaVisitePeritali , pratiche.Esito ,		pratiche.PresenzaVisitePeritali, pratiche.Funzionario , pratiche.MedicoINPS , pratiche.TempoAperturaPratica , pratiche.TempoChiusuraPratica ,pratiche.PresenzaCTU		from		ATPO_PRATICHE pratiche,		ATPO_ASS_PRATICA_CAMPAGNA ass,		ATPO_PRATICHE_SISCO sisco		where 		ass.ID_PRATICA_SISCO = pratiche.ID_PRATICHE_SISCO  and		sisco.ID_PRATICHE_SISCO = pratiche.ID_PRATICHE_SISCO  and		ass.ID_CAMPAGNA="+idCampagna+"		and  pratiche.Sede='"+sede+"'";
		
		try {
			Query querySel = em.createNativeQuery(queryStr);
			prtList = querySel.getResultList();

		} catch (Throwable e) {
			e.printStackTrace();
			log.error(e);
		}
		return prtList;
	}
	public Integer countListaVerbaliCampagna(long idCampagna,Integer pageNumber,
			Integer pageSize, Integer columnNameToOrder, String orderType,String textSearch){
		Integer count = null;

		String queryStr = "select count(sisco.FASCICOLO ) "
			+" from ATPO_PRATICHE_SISCO sisco, ATPO_PRATICHE_COGISAN cogisan, ATPO_ASS_PRATICA_CAMPAGNA ass, "
			+" AU_CAMPAGNA cmp,  AU_SEDE sede "
			+" where cmp.ID_CAMPAGNA="+idCampagna
			+" and ass.ID_CAMPAGNA = cmp.ID_CAMPAGNA "
			+" and ass.ID_PRATICA_SISCO = sisco.ID_PRATICHE_SISCO "
			+" and sisco.COD_SEDE= sede.CODICE_SEDE "
			+" and sisco.FASCICOLO = cogisan.FASCICOLO";	

		if (!StringUtils.isEmpty(textSearch)) {
			queryStr += " and (" + "upper(sisco.FASCICOLO) like UPPER('%"+ textSearch + "%')"+ ")";
		}



		try {
			Query querySel = em.createNativeQuery(queryStr);
			count = (Integer) querySel.getSingleResult();
		} catch (Throwable e) {
			e.printStackTrace();
			log.error(e);
		}
		return count;
	}

	@Transactional
	public AuCampagna salvaCampagna(AuCampagna a) {
		//a.setIdAudit(null);
		try {
			return em.merge(a);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Transactional
	public <T> void salva(T obj) {
		//a.setIdAudit(null);

		try {
			em.merge(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Transactional
	public <T> T cerca(Class<T> obj , Object pk) {
		//a.setIdAudit(null);
		try {
			return (T) em.find(obj,pk);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Transactional
	public void associaVerbaliCampagna(AuCampagna camp){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String queryStr ="select ID_VERBALE from AU_VERBALE where DATA_CHIUSURA_VERBALE " +
		"between '"+sdf.format(camp.getDataInizioOsservazione())+"' and '"+sdf.format(camp.getDataFineOsservazione())+"'";

		try {
			Query querySel = em.createNativeQuery(queryStr,Long.class);
			List<Long> idList = querySel.getResultList();

			queryStr="delete from AU_ASS_VERBALE_CAMPAGNA where ID_CAMPAGNA="+camp.getIdCampagna();
			querySel = em.createNativeQuery(queryStr);
			querySel.executeUpdate();

			for(Long id: idList){
				//				queryStr=" insert into AU_ASS_VERBALE_CAMPAGNA values ("+camp.getIdCampagna()+", "+id+" )";
				//				querySel = em.createNativeQuery(queryStr);
				//				querySel.executeUpdate();
				AuAssVerbaleCampagna avc = new AuAssVerbaleCampagna();
				avc.setIdCampagna(camp.getIdCampagna());
				avc.setIdVerbale(id);
				em.persist(avc);
			}

		} catch (Throwable e) {
			e.printStackTrace();
			log.error(e);
		}
	}


	@Transactional
	public void eliminaCampagna(long idCampagna) {
		Validate.notNull(idCampagna, "Id " + idCampagna + " non valorizzato.");

		AuCampagna campagna = em.find(AuCampagna.class, idCampagna);

		Validate.notNull(campagna, "La campagna con id " + campagna + " non esiste.");

		em.remove(campagna);
	}


	@Transactional
	public void eliminaSessione(long idSessione) {

		//cancello campione associato alla sessione
		String queryStr = "DELETE FROM AU_CAMPIONE WHERE ID_SESSIONE="+idSessione;	

		try{
			AuSessioni a=em.find(AuSessioni.class, idSessione);
			em.remove(a);
		}catch(Exception e){
			e.printStackTrace();
		}

	}


	public AuCampagna getCampagnaDaModificare(long idCampagna) {
		return em.find(AuCampagna.class, idCampagna);

	}

	

	public List<AuRegoleCampagna> getListaRegoleCampagna(long idCampagna){
		List<AuRegoleCampagna> rcList = new ArrayList<AuRegoleCampagna>();
		String queryStr = "select rc.* from AU_REGOLE_CAMPAGNA rc, AU_ASS_REGOLE_CAMPAGNA ass, AU_CAMPAGNA camp  "+ 
		"where camp.ID_CAMPAGNA = "+idCampagna+
		"and camp.ID_CAMPAGNA = ass.ID_CAMPAGNA "+
		"and rc.ID_REGOLA_CAMPAGNA = ass.ID_REGOLA_CAMPAGNA";

		try {
			Query querySel = em.createNativeQuery(queryStr,AuRegoleCampagna.class);
			rcList = querySel.getResultList();

		} catch (Throwable e) {
			e.printStackTrace();
			log.error(e);
		}
		return rcList;
	}

	public List<AuSessioni> getListaSessioniCampagna(long idCampagna){
		List<AuSessioni> ssList = new ArrayList<AuSessioni>();
		String queryStr = "select * from AU_SESSIONI sess where sess.ID_CAMPAGNA="+idCampagna;

		try {
			Query querySel = em.createNativeQuery(queryStr,AuSessioni.class);
			ssList = querySel.getResultList();

		} catch (Throwable e) {
			e.printStackTrace();
			log.error(e);
		}
		return ssList;
	}

	public AuRisultatiCampagna getRisultatoRegolaCampagna(long idRegolaCampagna, long idCampagna){
		List<AuRisultatiCampagna> rclist=null;
		String queryStr = "select * from AU_RISULTATI_CAMPAGNA where ID_REGOLA_CAMP="+idRegolaCampagna+" and ID_CAMPAGNA="+idCampagna;

		try {
			Query querySel = em.createNativeQuery(queryStr,AuRisultatiCampagna.class);
			rclist =  querySel.getResultList();

		} catch (Throwable e) {
			e.printStackTrace();
			log.error(e);
		}
		if(rclist.size()==0){
			return null;
		}else{
			return rclist.get(0);
		}
	}

	public AuRisultatiCampione getRisultatoRegolaCampione(long idRegolaCampagna, long idCampione){
		List<AuRisultatiCampione> rclist=null;
		String queryStr = "select * from AU_RISULTATI_CAMPIONE where ID_REGOLA_CAMP="+idRegolaCampagna+" and ID_CAMPIONE="+idCampione;

		try {
			Query querySel = em.createNativeQuery(queryStr,AuRisultatiCampione.class);
			rclist =  querySel.getResultList();

		} catch (Throwable e) {
			e.printStackTrace();
			log.error(e);
		}
		if(rclist.size()==0){
			return null;
		}else{
			return rclist.get(0);
		}
	}



	

	//controllo che il campione abbia una sessione associata

	public AuCampione getCampioneBySessione(long idSessione){
		List<AuCampione> rclist=null;
		String queryStr = "select * from AU_CAMPIONE cmp, AU_SESSIONI ss "+
		"where ss.ID_SESSIONE= "+idSessione+
		" and cmp.ID_SESSIONE=ss.ID_SESSIONE ";

		try {
			Query querySel = em.createNativeQuery(queryStr,AuCampione.class);
			rclist =  querySel.getResultList();

		} catch (Throwable e) {
			e.printStackTrace();
			log.error(e);
		}
		if(rclist==null || rclist.size()==0){
			return null;
		}else{
			return rclist.get(0);
		}
	}


	public List<Object[]> getRisultatiRegolaCampagnaCampione(long idRegola){
		List<Object[]> rclist=null;
		String queryStr = "select 'CAMPAGNA' as TIPO , rc.DESCRIZIONE, cmp.RES1, cmp.RES2, cmp.RES3, cmp.RES4 from AU_RISULTATI_CAMPAGNA cmp , AU_REGOLE_CAMPAGNA rc "+
		" where rc.ID_REGOLA_CAMPAGNA="+idRegola+
		" and cmp.ID_REGOLA_CAMP = rc.ID_REGOLA_CAMPAGNA "+
		" union "+
		" select 'CAMPIONE' as TIPO , rc.DESCRIZIONE, cmp.RES_1, cmp.RES_2, cmp.RES_3, cmp.RES_4 from AU_RISULTATI_CAMPIONE cmp , AU_REGOLE_CAMPAGNA rc "+
		" where rc.ID_REGOLA_CAMPAGNA="+idRegola+
		" and cmp.ID_REGOLA_CAMP = rc.ID_REGOLA_CAMPAGNA ";

		try {
			Query querySel = em.createNativeQuery(queryStr);
			rclist =  querySel.getResultList();

		} catch (Throwable e) {
			e.printStackTrace();
			log.error(e);
		}

		return rclist;

	}

	@Override
	public List<Object[]> getListaVerbaliCampioneSess(long idCampione,
			Integer pageNumber, Integer pageSize, Integer columnNameToOrder,
			String orderType, String textSearch) {

		List<Object[]> verbaliCampione = null;
		String queryStr = "select sisco.ID_PRATICHE_SISCO, sede.NOME_SEDE, sisco.FASCICOLO, sisco.DATA_NOTIFICA, sisco.DATA_APERTURA, cogisan.RG, cogisan.PARTE, cogisan.VISITA_PERITALE ,sisco.ESITO"+
		" from ATPO_PRATICHE_SISCO sisco, ATPO_PRATICHE_COGISAN cogisan, "+
		" AU_ASS_CAMP_PRT_ATPO ass, AU_CAMPIONE campione,  AU_SEDE sede "+
		" where campione.ID_CAMPIONE= "+idCampione+
		" and ass.ID_CAMPIONE= campione.ID_CAMPIONE "+
		" and ass.ID_PRATICHE_SISCO= sisco.ID_PRATICHE_SISCO "+
		" and sisco.FASCICOLO=cogisan.FASCICOLO "+
		" and sisco.COD_SEDE = cogisan.COD_SEDE " +
		" and sisco.COD_SEDE= sede.CODICE_SEDE";

		if (!StringUtils.isEmpty(textSearch)) {
			queryStr += " and (" + "upper(sisco.FASCICOLO) like UPPER('%"+ textSearch + "%')"+ ")";
		}

		queryStr +=" ORDER BY "+(columnNameToOrder + 1)+" "+orderType +" OFFSET "+pageNumber+" ROWS ";
		queryStr +=" FETCH NEXT "+pageSize+" ROWS ONLY ";


		try {
			Query querySel = em.createNativeQuery(queryStr );
			verbaliCampione = querySel.getResultList();

		} catch (Throwable e) {
			e.printStackTrace();
			log.error(e);
		}
		return verbaliCampione;	

	}


	public Integer countListaVerbaliCampioneSess(long idCampione, Integer pageNumber,
			Integer pageSize, Integer columnNameToOrder, String orderType,String textSearch) {

		Integer count=null;
		String queryStr = "select count(sisco.FASCICOLO) "+
		" from ATPO_PRATICHE_SISCO sisco, ATPO_PRATICHE_COGISAN cogisan, "+
		" AU_ASS_CAMP_PRT_ATPO ass, AU_CAMPIONE campione,  AU_SEDE sede "+
		" where campione.ID_CAMPIONE= "+idCampione+
		" and ass.ID_CAMPIONE= campione.ID_CAMPIONE "+
		" and ass.ID_PRATICHE_SISCO= sisco.ID_PRATICHE_SISCO "+
		" and sisco.FASCICOLO=cogisan.FASCICOLO "+
		" and sisco.COD_SEDE= sede.CODICE_SEDE" +
		" and sisco.COD_SEDE = cogisan.COD_SEDE";

		if (!StringUtils.isEmpty(textSearch)) {
			queryStr += " and (" + "upper(sisco.FASCICOLO) like UPPER('%"+textSearch+"%')"+ ")";
		}

		try {
			Query querySel = em.createNativeQuery(queryStr);
			count = (Integer) querySel.getSingleResult();

		} catch (Throwable e) {
			e.printStackTrace();
			log.error(e);
		}
		return count;
	}

	@Override
	public List<Object[]> getListaVerbaliCampagnaSess(long idCampione,
			Integer pageNumber, Integer pageSize, Integer columnNameToOrder,
			String orderType, String textSearch) {
		List<Object[]> praticheSessione = null;
		
		String queryStr = "select sisco.ID_PRATICHE_SISCO, sede.NOME_SEDE, sisco.FASCICOLO, sisco.DATA_NOTIFICA, sisco.DATA_APERTURA, "+
			" cogisan.RG, cogisan.PARTE, cogisan.VISITA_PERITALE , sisco.ESITO "+
			" from ATPO_PRATICHE_SISCO sisco, ATPO_PRATICHE_COGISAN cogisan, "+
			" AU_CAMPIONE campione,  AU_SEDE sede , AU_CAMPAGNA cmp, AU_SESSIONI sess, "+
			" ATPO_ASS_PRATICA_CAMPAGNA atpo_ass "+
			" where campione.ID_CAMPIONE="+idCampione+
			" and campione.ID_SESSIONE=sess.ID_SESSIONE "+
			" and sess.ID_CAMPAGNA = cmp.ID_CAMPAGNA "+
			" and cmp.ID_CAMPAGNA=atpo_ass.ID_CAMPAGNA "+
			" and sisco.ID_PRATICHE_SISCO = atpo_ass.ID_PRATICA_SISCO "+
			" and sisco.FASCICOLO=cogisan.FASCICOLO "+
			" and sisco.COD_SEDE = cogisan.COD_SEDE " +
			" and sisco.COD_SEDE= sede.CODICE_SEDE and sede.NOME_SEDE=sess.SEDE "+
			" and sisco.ID_PRATICHE_SISCO not in (select ID_PRATICHE_SISCO from AU_ASS_CAMP_PRT_ATPO where ID_CAMPIONE="+idCampione+") ";

		if (!StringUtils.isEmpty(textSearch)) {
			queryStr += " and ((" + "upper(sisco.FASCICOLO) like UPPER('%"+ textSearch + "%')"+ ")";
			queryStr += " or (" + "upper(cogisan.PARTE) like UPPER('%"+ textSearch + "%')"+ "))";
		}

		queryStr +=" ORDER BY "+(columnNameToOrder + 1)+" "+orderType +" OFFSET "+pageNumber+" ROWS ";
		queryStr +=" FETCH NEXT "+pageSize+" ROWS ONLY ";


		try {
			Query querySel = em.createNativeQuery(queryStr );
			praticheSessione = querySel.getResultList();

		} catch (Throwable e) {
			e.printStackTrace();
			log.error(e);
		}
		return praticheSessione;	
	}

	@Override
	public Integer countListaVerbaliCampagnaSess(long idCampione, Integer pageNumber,
			Integer pageSize, Integer columnNameToOrder, String orderType,
			String textSearch) {
		Integer count = null;

		String queryStr = "select count(sisco.ID_PRATICHE_SISCO) "+
		" from ATPO_PRATICHE_SISCO sisco, ATPO_PRATICHE_COGISAN cogisan, "+
		" AU_CAMPIONE campione,  AU_SEDE sede , AU_CAMPAGNA cmp, AU_SESSIONI sess, "+
		" ATPO_ASS_PRATICA_CAMPAGNA atpo_ass "+
		" where campione.ID_CAMPIONE="+idCampione+
		" and campione.ID_SESSIONE=sess.ID_SESSIONE "+
		" and sess.ID_CAMPAGNA = cmp.ID_CAMPAGNA "+
		" and cmp.ID_CAMPAGNA=atpo_ass.ID_CAMPAGNA "+
		" and sisco.ID_PRATICHE_SISCO = atpo_ass.ID_PRATICA_SISCO "+
		" and sisco.FASCICOLO=cogisan.FASCICOLO "+
		" and sisco.COD_SEDE = cogisan.COD_SEDE " +
		" and sisco.COD_SEDE= sede.CODICE_SEDE and sede.NOME_SEDE=sess.SEDE "+
		" and sisco.ID_PRATICHE_SISCO not in (select ID_PRATICHE_SISCO from AU_ASS_CAMP_PRT_ATPO where ID_CAMPIONE="+idCampione+") ";


		if (!StringUtils.isEmpty(textSearch)) {
			queryStr += " and (" + "upper(sisco.FASCICOLO) like UPPER('%"+ textSearch + "%')"+ ")";
		}

		try {
			Query querySel = em.createNativeQuery(queryStr);
			count = (Integer) querySel.getSingleResult();
		} catch (Throwable e) {
			e.printStackTrace();
			log.error(e);
		}
		return count;
	}

	//prende gli id regole campione
	public List<Long> getIdRegoleCampione(long idSessione){
		List<Long> listaId= new ArrayList<Long>();
		String queryStr = "select a.ID_REGOLA_CAMPAGNA from AU_REGOLE_CAMPAGNA a, AU_ASS_REGOLE_CAMPAGNA ass, AU_SESSIONI ss, AU_CAMPAGNA gna "+
		" where ss.ID_SESSIONE=" + idSessione+
		" and ss.ID_CAMPAGNA = gna.ID_CAMPAGNA "+
		" and a.ID_REGOLA_CAMPAGNA = ass.ID_ASS_REGOLA_CAMPAGNA "+
		" and ass.ID_CAMPAGNA = gna.ID_CAMPAGNA";			

		try {
			Query querySel = em.createNativeQuery(queryStr);
			listaId =(List<Long>) querySel.getResultList();

		} catch (Throwable e) {
			e.printStackTrace();
			log.error(e);
		}

		return listaId;
	}

	@Override
	public List<AuSede> getSedi() {
		List<AuSede> sedi = null;
		String queryStr = "SELECT S.* FROM AU_SEDE S";

		try {
			Query querySel = em.createNativeQuery(queryStr , AuSede.class);
			sedi = querySel.getResultList();

		} catch (Throwable e) {
			e.printStackTrace();
			log.error(e);
		}
		return sedi;
	}

	@Override
	public int countSessioniAssociateCampagna(long idCampagna) {
		String qryString="SELECT COUNT (ID_SESSIONE) FROM AU_SESSIONI WHERE ID_CAMPAGNA= "+idCampagna;
		Query query=em.createNativeQuery(qryString,Integer.class);
		int result;
		try{
			result=(Integer)query.getSingleResult();
		}catch (Exception e) {
			result=0;
		}
		return result;
	}

	@Override
	public long getIdDirigenteFromDelegato(long idDelegato) {
		String qryString ="SELECT ID_DIRIGENTE FROM CRS_ASS_DELEGATO_DIRIGENTE WHERE ID_DELEGATO= "+idDelegato;
		Query query=em.createNativeQuery(qryString);
		long result;
		try{
			result=(Long)query.getSingleResult();
		}catch (Exception e) {
			result=0;
		}
		return result;
	}

	@Override
	public long getIdAuditByIdCampagna(long idCampagna) {
		String qryString="SELECT ID_AUDIT FROM AU_CAMPAGNA WHERE ID_CAMPAGNA= "+idCampagna;
		Query query= em.createNativeQuery(qryString);
		long result;
		try{
			result=(Long)query.getSingleResult();
		}catch (Exception e) {
			result=0;
		}
		return result;
	}

	@Transactional
	@Override
	public void aggiornaDataFineAccessiCampagna(AuCampagna campagna) {
		try {
			String dataStr =new SimpleDateFormat("yyyy-MM-dd").format(campagna.getDataFine()).toString(); 
			int countUpdated = em.createNativeQuery("Update AU_SESSIONI set DATA_FINE = '" + dataStr + "' where id_campagna = " +  campagna.getIdCampagna()).executeUpdate();
			log.debug("Aggiornati rows " + countUpdated +  " : update data fine con " + dataStr);
		} catch (Exception e) {
			log.error(e);
		}
	}

	@Transactional
	public void deletePraticaCampione(long idCampione, long idPratica, long idSPratica) {

		// ELIMINO L'ASSOCIAZIONE --------------------------------------------
		String queryStr = "select  a.* "+
		" from AU_ASS_CAMP_PRT_ATPO a"+
		" where a.ID_PRATICHE_SISCO ="+idPratica +"AND a.ID_CAMPIONE="+idCampione;

		Query querySel = em.createNativeQuery(queryStr, AuAssCampPrtAtpo.class );
		AuAssCampPrtAtpo a = (AuAssCampPrtAtpo) querySel.getSingleResult();

		// RECUPERO IL CAMPIONE -----------------------------------------------
		//AuCampione campione = (AuCampione)em.find(AuCampione.class, idCampione);

		// ELIMINO LA S_PRATICA ------------------------------------------------	
		String querySPraticaStr = "DELETE from AU_S_PRATICA  where ID_PRATICA = " + idPratica;
		Query query=em.createNativeQuery(querySPraticaStr);
		
		//ELIMINO LA AU_S_PRATICA_RIS--------------------------------------------
		String querySPraticaRis = "DELETE from AU_S_PRATICA_RIS  where ID_S_PRATICA = " + idSPratica;
		Query praticaRis=em.createNativeQuery(querySPraticaRis);
		
		//ELIMINO LA AU_S_PRATICA_VARCOMP--------------------------------------------
		String querySPraticaVarComp = "DELETE from AU_S_PRATICA_VARCOMP where ID_S_PRATICA = " + idSPratica;
		Query varComp=em.createNativeQuery(querySPraticaVarComp);
		
		//ELIMINO FASI
		AtpoPraticheSISCO sisco = em.find(AtpoPraticheSISCO.class, idPratica);
		String idquery = "select ID_FASE_DATI FROM ATPO_FASE_DATI where FASCICOLO='"+sisco.getFascicolo()+"' and COD_SEDE='"+sisco.getCodSede()+"'";
		Query iddati=em.createNativeQuery(idquery);
		long id_fase_dati=(Long)iddati.getSingleResult();
		
		String queryFaseAI = "DELETE from ATPO_FASE_ACQUISIZIONE_ISTANZA where ID_FASE_DATI = " + id_fase_dati;
		Query qfai=em.createNativeQuery(queryFaseAI);
		
		String queryFaseARG = "DELETE from ATPO_FASE_AUTOTUTELA_RESISTENZA_GIUDIZIO where ID_FASE_DATI = " + id_fase_dati;
		Query qarg=em.createNativeQuery(queryFaseARG);
		
		String queryFaseGI = "DELETE from ATPO_FASE_GESTIONE_ISTRUTTORIA where ID_FASE_DATI = " + id_fase_dati;
		Query qgi=em.createNativeQuery(queryFaseGI);
		
		String queryFasePeritale = "DELETE from ATPO_FASE_PERITALE where ID_FASE_DATI = " + id_fase_dati;
		Query qp=em.createNativeQuery(queryFasePeritale);
		
		String queryFasePPeritale = "DELETE from ATPO_FASE_POST_PERITALE where ID_FASE_DATI = " + id_fase_dati;
		Query qpp=em.createNativeQuery(queryFasePPeritale);
		
		String queryFaseRF = "DELETE from ATPO_FASE_RIEPILOGO_FASCICOLO where ID_FASE_DATI = " + id_fase_dati;
		Query qrf=em.createNativeQuery(queryFaseRF);
		
		String queryFaseD = "DELETE from ATPO_FASE_DATI where ID_FASE_DATI = " + id_fase_dati;
		Query qd=em.createNativeQuery(queryFaseD);
		
		try {	
			em.remove(a);
			varComp.executeUpdate();
			praticaRis.executeUpdate();
		 	query.executeUpdate();	
		 	
		 	qfai.executeUpdate();
		 	qarg.executeUpdate();
		 	qgi.executeUpdate();
		 	qp.executeUpdate();
		 	qpp.executeUpdate();
		 	qrf.executeUpdate();
		 	qd.executeUpdate();
		 	
		}catch(Exception e){
			e.printStackTrace();
		 	log.error(e);
		}
	}

	@Transactional
	@Override
	public void aggiungiPraticaAlCampione(long idCampione, long idPratica) {
		// INSERISCO L'ASSOCIAZIONE ------------------------
		AuAssCampPrtAtpo auAssCampPrt= new AuAssCampPrtAtpo();
		auAssCampPrt.setIdCampione(idCampione);
		auAssCampPrt.setIdPraticheSisco(idPratica);
		em.persist(auAssCampPrt);
	
		// INSERISCO S_SESSIONE SE NON ESISTE E POI INSERISCO S_PRATICA --
		AuCampione auCampione = em.find(AuCampione.class, idCampione);
		long idSSessione = getSSessione(auCampione.getIdSessione());
		
		AuSSessione auSSessione = null;
		AuSPratica auSPratica = new AuSPratica();
		auSPratica.setIdPratica(idPratica);
		auSPratica.setEsamePratica(PraticaUtil.ESAME_PRATICA_APERTO); 
		//auSPratica.setTipoDifesa("U04");
		
		if (idSSessione == 0){
			
			auSSessione = new AuSSessione();
			auSSessione.setIdSessione(auCampione.getIdSessione());
			auSSessione.setStatoEsameSessione("A");
			em.persist(auSSessione);
						
			auSPratica.setIdSSessione(auSSessione.getIdSSessione());
			
		}else{
			auSPratica.setIdSSessione(idSSessione);
			
		}
		em.persist(auSPratica);
		
		AtpoPraticheSISCO sisco = em.find(AtpoPraticheSISCO.class, idPratica);
		
		//AtpoPratiche pratica = em.find(AtpoPratiche.class, idPratica);
		Query q = em.createNamedQuery(AtpoPratiche.QUERY_PRATICA_BY_SISCO);
		q.setParameter(AtpoPratiche.QUERY_PARAM_IDSISCO,sisco.getIdPraticheSisco());
		AtpoPratiche pratica = (AtpoPratiche) q.getSingleResult();
//		System.out.println("PRATICA="+pratica.getSede());
		
		//INSERISCO VERBALE_MN e VERBALE MANUALE DEFINIZIONE
		AtpoFaseDati fd= new AtpoFaseDati();
		
		fd.setFascicolo(sisco.getFascicolo());
		fd.setCodSede(sisco.getCodSede());
		
		if(sisco.getEsito().trim().startsWith("FAVOREVOLE", 0)){
			fd.setGiudizio("1");
		}else if(sisco.getEsito().trim().startsWith("PARZIALMENTE", 0)){
			fd.setGiudizio("2");
		}else if(sisco.getEsito().trim().startsWith("SFAVOREVOLE", 0)){
			fd.setGiudizio("3");
		}else if(sisco.getEsito().trim().startsWith("DISSENSO", 0)){
			fd.setGiudizio("4");
		}
		
		fd.setFasePronta("N");
		em.persist(fd);
		
		AtpoFaseAcquisizioneIstanza fai = new AtpoFaseAcquisizioneIstanza();
		
		fai.setIdFaseDati(fd.getIdfaseDati());
		fai.setFasePronta("N");
		
		//String dp=sisco.getPrRic().trim();
		String dp=sisco.getPrRic();
		if(dp !=null && !dp.trim().equals("") ){
			dp=dp.trim();
			String [] d =dp.split("\\.");
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		    String dateInString = d[2];
		    Date dataProt = null;
			try {
				dataProt = formatter.parse(dateInString);
				fai.setDataProtocollo(getRealDate(dataProt));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
//		else{
//			if(pratica!= null){
//				fai.setDataProtocollo(pratica.getDataProtocollo());
//			}
//		}
		fai.setDataProtocollo(getRealDate(sisco.getDataApertura()));
		
		if(pratica!= null){
			fai.setDataNotifica(getRealDate(pratica.getDataNotificaAtto()));
			fai.setDataAcquisizioneSISCO(getRealDate(pratica.getDataAcquisizioneSISCO()));
//			fai.setDataProtocollo(pratica.getDataProtocollo());	
		}
		
		em.persist(fai);
		
		AtpoFaseAutotutelaResistenzaGiudizio farg = new AtpoFaseAutotutelaResistenzaGiudizio();
		
		farg.setIdFaseDati(fd.getIdfaseDati());
		farg.setFasePronta("N");
		em.persist(farg);
		
		AtpoFaseEsecuzioneProvvedimenti fep = new AtpoFaseEsecuzioneProvvedimenti();
		
		
		

		
		
		fep.setIdFaseDati(fd.getIdfaseDati());
		fep.setFasePronta("N");
		//fep.setPresDecrOmgFasc("n");
		//fep.setCondannaPagCtuAtpo("n");
		//fep.setAntSpeseCtu("n");
		//precetto
		fep.setNoPrecetto("f");
		fep.setSpeseLegaliFlagPrec("f");
		fep.setSpeseCtuFlagPrec("f");
		fep.setPrestazioneFlagPrec("f");
		//pignoramento
		fep.setSpeseLegaliFlagPign("f");
		fep.setNoPignoramento("f");
		fep.setSpeseCtuFlagPign("f");
		fep.setPrestazioneFlagPign("f");
		
		
		if(pratica!= null){
			String z=null;
			if(pratica.getImportoSpeseCTUPagate()!= null){
				fep.setImpSpeseCtuPagate(pratica.getImportoSpeseCTUPagate().doubleValue());
			}
//			fep.setPresDecrOmgFasc(pratica.getPresenzaDecretoOmologa());
			
			z = pratica.getPresenzaDecretoOmologa();
			if(z!= null && z.trim().equalsIgnoreCase("NO")){
				fep.setPresDecrOmgFasc("n");
			}
			else{
				fep.setPresDecrOmgFasc("s");
			}
			
			fep.setDataLiqPrestLps(getRealDate(pratica.getDataLiquidazionePrestazione()));
//			fep.setCondannaPagCtuAtpo(pratica.getCondannaPagamentoSpeseLegali());
			z = pratica.getCondannaPagamentoSpeseLegali();
			if(z!= null && z.trim().equalsIgnoreCase("NO")){
				fep.setCondannaPagCtuAtpo("n");
			}
			else{
				fep.setCondannaPagCtuAtpo("s");
			}
			
			
			z = pratica.getPrecetto();
			if(z!= null && z.trim().equalsIgnoreCase("NO")){
				fep.setNoPrecetto("f");
			}
			else{
				fep.setNoPrecetto("v");
			}
			z = pratica.getPrecettoSpeseLegali();
			if(z!= null && z.trim().equalsIgnoreCase("NO")){
				fep.setSpeseLegaliFlagPrec("v");
			}
			z = pratica.getPignoramentoSpeseLegali();
			if(z!= null && z.trim().equalsIgnoreCase("NO")){
				fep.setSpeseLegaliFlagPign("v");
			}
			
			
			z = pratica.getPignoramento();
			if(z!= null && z.trim().equalsIgnoreCase("NO")){
				fep.setNoPignoramento("f");
			}
			else{
				fep.setNoPignoramento("v");
			}
			
			fep.setDataPresaInCaricoDecrOmgLps(getRealDate(pratica.getDataPresaInCaricoEsecuzione()));
			fep.setDataLiqCtuAtpo(getRealDate(pratica.getDataLiquidazioneCTU()));
			fep.setDataPagSpseLegaliAvvCparte(getRealDate(pratica.getDataPagamentoSpeseLegali()));

			
			
			
		}
		
		em.persist(fep);
		AtpoFaseGestioneIstruttoria fgi = new AtpoFaseGestioneIstruttoria();
		if(pratica!= null){
			long l = pratica.getCostituzioneInGiudizioTelematica();
			if(l == 0){
				fgi.setCostGiudTelematica("n");
			}
			else{
				fgi.setCostGiudTelematica("s");
			}
			
			String z = pratica.getConPresenzaAttoCostituzione();
			if(z!= null && z.trim().equalsIgnoreCase("NO")){
				fgi.setEccezioniNonRilevabili("n");
			}
			else{
				fgi.setEccezioniNonRilevabili("s");
			}
			fgi.setDataCostitGiudizio(getRealDate(pratica.getDataCostituzioneInGiudizio()));
			fgi.setDataPrimaUdienza(getRealDate(pratica.getDataPrimaUdienza()));
			
		}
		
		fgi.setIdFaseDati(fd.getIdfaseDati());
		fgi.setFasePronta("N");
//		fgi.setDataCostitGiudizio(sisco.getDataCostituzione());
//		fgi.setDataPrimaUdienza(sisco.getDataUdienza());
		em.persist(fgi);
		
		AtpoFasePeritale fp = new AtpoFasePeritale();
		if(pratica!= null){
			String z = pratica.getOsservazioniSuBozza();
			if(z == null || z.trim().equalsIgnoreCase("")){
				fp.setOssBozza("n");
			}
			else{
				fp.setOssBozza("s");
			}
			
			z = pratica.getPresenzaCTU();
			if(z == null || z.trim().equalsIgnoreCase("NO")){
				fp.setPresMedicoInpsDoc("n");
			}
			else{
				fp.setPresMedicoInpsDoc("s");
			}
			
			
			z = pratica.getOsservazioniSuParereDefinitivo();
			if(z == null || z.trim().equalsIgnoreCase("")){
				fp.setOssParDef("n");
			}
			else{
				fp.setOssParDef("s");
			}
			
			z = pratica.getPresenzaCTPINPSOperazioniPeritali();
			if(z == null || z.trim().equalsIgnoreCase("NO")){
				fp.setCtpINPSopPeritali("n");
			}
			else{
				fp.setCtpINPSopPeritali("s");
			}
			
			fp.setDataComOpPerCTU(getRealDate(pratica.getDataRegistrazioneVisitaPeritaleSISCO()));
			z = pratica.getAssegnazioneCTUMedicoINPS();
			if(z == null || z.trim().equalsIgnoreCase("NO")){
				fp.setAssCTUMedicoInps("n");
			}
			else{
				fp.setAssCTUMedicoInps("s");
			}
//			fp.setCtpINPSopPeritali(pratica.getPresenzaCTPINPSOperazioniPeritali());
			fp.setDataArrBozza(getRealDate(pratica.getDataArrivoBozza()));
			fp.setDataProtBozza(getRealDate(pratica.getDataProtocolloBozza()));
			
			z = pratica.getParereSuBozzaCTU();
			if(z == null || z.trim().equalsIgnoreCase("NO")){
				fp.setParereBozzaCtu("1");
			}
			else{
				fp.setParereBozzaCtu("2");
			}
			z = pratica.getOsservazioniSuBozza();
			if(z == null || z.trim().equalsIgnoreCase("")){
				fp.setOssBozza("n");
			}
			else{
				fp.setOssBozza("s");
			}
			fp.setDataProtCTUDef(getRealDate(pratica.getDataProtocolloCTUDefinitiva()));
			fp.setDataTermDissSisco(getRealDate(pratica.getDataTermineDissenso()));
			z = pratica.getOsservazioniSuParereDefinitivo();
			if(z == null || z.trim().equalsIgnoreCase("NO")){
				fp.setOssParDef("n");
			}
			else{
				fp.setOssParDef("s");
			}
		}
		fp.setIdFaseDati(fd.getIdfaseDati());
		fp.setFasePronta("N");
		String pb=sisco.getPrBzz();
		if(pb!=null && !pb.trim().equals("") ){
			pb=pb.trim();
			String [] d =pb.split("\\.");
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		    String dateInString = d[2];
		    Date dataProtBozza = null;
			try {
				dataProtBozza = formatter.parse(dateInString);
				fp.setDataProtBozza(getRealDate(dataProtBozza));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		String cd=sisco.getPrDef();
		if(cd!=null && !cd.trim().equals("")){
			cd= cd.trim();
			String [] st =cd.split("\\.");
			SimpleDateFormat form = new SimpleDateFormat("dd/MM/yyyy");
		    String dateString = st[2];
		    Date dataProtCtuDef = null;
		    try {
		    	dataProtCtuDef = form.parse(dateString);
				fp.setDataProtCTUDef(getRealDate(dataProtCtuDef));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		em.persist(fp);
		
		AtpoFasePostPeritale fpp = new AtpoFasePostPeritale();
		
		if(pratica!= null){
			long z = pratica.getCodiceEsitoCausa();
			if(z==11){
				fpp.setCodPagamentoSpeseLegali("1");
			}
			if(z==12){
				fpp.setCodPagamentoSpeseLegali("2");
			}
			if(z==9){
				fpp.setCodPagamentoSpeseLegali("3");
			}
			if(z==3){
				fpp.setCodPagamentoSpeseLegali("4");
			}
			if(z==7){
				fpp.setCodPagamentoSpeseLegali("5");
			}
			if(z==1){
				fpp.setCodPagamentoSpeseLegali("6");
			}
			fpp.setDataProtDecOmologaNotif(getRealDate(pratica.getDataProtocolloDecretoOmologa()));
			if(pratica.getImportoSpeseLegali() != null){
				fpp.setSpesePagate(pratica.getImportoSpeseLegali().doubleValue());
			}
			fpp.setDataDepositoDecOmologa(getRealDate(pratica.getDataDepositoDecretoOmologa()));
			fpp.setDataTrasmissDecrLPS(getRealDate(pratica.getDataComunicazioneUffAmmPerEsecuzione()));
			
			
		}
		
		
		fpp.setIdfaseDati(fd.getIdfaseDati());
		fpp.setFasePronta("N");
		fpp.setCodChiusuraInserito(fd.getGiudizio());
		em.persist(fpp);
		
		
		
		// inserisco riepilogo fascicolo
		AtpoRiepilogoFascicolo  a= new AtpoRiepilogoFascicolo();
		a.setIdFaseDati(fd.getIdfaseDati());
//		String fascicoloE= a.getFascicoloElettronico().trim();
//		
//		//a.setFascicoloCartaceo(fascCartaceo);
//		a.setFascicoloElettronico(fascicoloE);
		a.setFasePronta("N");
		em.persist(a);
		
		
		
		
		

	}
	private Date getRealDate(Date in){
		if(in != null){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(in);
			int year = calendar.get(Calendar.YEAR);
			int month = calendar.get(Calendar.MONTH);
			int day = calendar.get(Calendar.DAY_OF_MONTH);
			if(year==1900 && day==1 && month==1){
				in=null;
			}
		}
		return in;
	}
	private long getSSessione(long idSessione){
		long idSSessione = 0;	
		String qryString="SELECT SS.ID_S_SESSIONE FROM AU_SESSIONI S , AU_S_SESSIONE SS"; 
		qryString += " WHERE S.ID_SESSIONE =  SS.ID_SESSIONE";
		qryString += " AND S.ID_SESSIONE = " + idSessione;

		Query query= em.createNativeQuery(qryString);

		try{
			idSSessione=(Long)query.getSingleResult();
		}catch (NoResultException e) {
			idSSessione=0;
		}
		return idSSessione;
	}

	@Override
	public AuSPratica getAuSPraticaById(long idPratica) {
		List<AuSPratica> sPratica = null;
		AuSPratica result;
		String queryStr = "select s.* from au_s_pratica s where ID_PRATICA="+idPratica;
		
		try {
			Query querySel = em.createNativeQuery(queryStr , AuSPratica.class);
			sPratica = querySel.getResultList();
			if(sPratica !=null && sPratica.size() !=0){
				result= sPratica.get(0);
				return result;
			}
		} catch (Throwable e) {
			e.printStackTrace();
			log.error(e);
			return null;
		}
		return null;
	}


	

}



