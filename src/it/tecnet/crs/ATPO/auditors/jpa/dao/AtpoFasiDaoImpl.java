package it.tecnet.crs.ATPO.auditors.jpa.dao;

import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoRiepilogoFascicolo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

public class AtpoFasiDaoImpl implements AtpoFasiDao {

	protected static Logger log = Logger.getLogger(AtpoFasiDaoImpl.class);

	@PersistenceContext
	private EntityManager em;


	// F A S I __________________________________________________________


	public Object[] getFasePeritale(long idFaseDati) {
		List<Object[]>l=new ArrayList<Object[]>();

		String queryStr = "select gi.id_peritale, gi.data_com_i_op_per_CTU, gi.reg_info_op_peritali, "+
		"gi.assegn_CTU_a_med_INPS, gi.ctp_INPS_op_peritali, gi.data_arr_bozza, "+
		"gi.data_prot_bozza, gi.CTU_bozza_img_atti, gi.parere_bozza_CTU, "+
		"gi.oss_bozza, gi.data_com_dep_CTU_def_canc, gi.data_prot_CTU_def, "+
		"gi.CTU_def_img_atti,gi.int_temp_com_DEP_CTU_to_CTU_def, gi.data_term_DISS_SISCO, "+
		"gi.data_term_DISS_da_decreto, gi.parere_diss_accett, gi.oss_par_def, "+
		"gi.id_fase_dati, gi.presenza_medico_inps_doc "+
		"from ATPO_FASE_PERITALE gi , ATPO_FASE_DATI fd "+
		"where gi.id_fase_dati = fd.id_fase_dati "+
		"and fd.id_fase_dati = "+idFaseDati;	


		try {
			Query querySel = em.createNativeQuery(queryStr);
			l = (List<Object[]>) querySel.getResultList();
			if(l!=null && l.size()!=0 && l.get(0) !=null){
				return l.get(0);
			}

		} catch (Throwable e) {
			e.printStackTrace();
			log.error(e);
		}
		return null;
	}

	public Object[] getFaseGestioneIstruttoria(long idFaseDati) {
		
		List<Object[]>l=new ArrayList<Object[]>();
		String queryStr = "select gi.id_gestione_istruttoria, gi.eccezioni_non_rilevabili, gi.litispendenza, "+
		"gi.decadenza, gi.corrisp_ATP_dom_amm_inv, gi.verifica_dic_es_pag_spese, "+
		"gi.indeterminatezza_ogg_dom, gi.carenza_interesse_ad_agire, gi.data_costit_giudizio, "+
		"gi.cost_giud_telematica, gi.data_prima_udienza, gi.intervallo_costGiud_udienza, "+
		"gi.altre_ecc_processuali, gi.id_fase_dati  "+
		"from ATPO_FASE_GESTIONE_ISTRUTTORIA gi , ATPO_FASE_DATI fd "+
		"where gi.id_fase_dati = fd.id_fase_dati "+
		"and fd.id_fase_dati = "+idFaseDati;	


		try {
			Query querySel = em.createNativeQuery(queryStr);
			l = (List<Object[]>) querySel.getResultList();
			if(l!=null && l.size()!=0 && l.get(0) !=null){
				return l.get(0);
			}
		} catch (Throwable e) {
			e.printStackTrace();
			log.error(e);
		}
		return null;
	}

	public Object[] getFaseAutotutelaResistenzaGiudizio(long idFaseDati) {
		
		List<Object[]>l=new ArrayList<Object[]>();
		String queryStr = "select arg.id_autotutela, arg.parere, arg.termini_prima_udienza, arg.id_fase_dati "+
		"from ATPO_FASE_AUTOTUTELA_RESISTENZA_GIUDIZIO arg, ATPO_FASE_DATI fd "+
		"where arg.id_fase_dati = fd.id_fase_dati "+
		"and fd.id_fase_dati = "+idFaseDati;


		try {
			Query querySel = em.createNativeQuery(queryStr);
			l = (List<Object[]>)querySel.getResultList();
			if(l!=null && l.size()!=0 && l.get(0) !=null){
				return l.get(0);
			}

		} catch (Throwable e) {
			e.printStackTrace();
			log.error(e);
		}
		return null;
	}

	public  Object[] getFaseAcquisizioneIstanza(long idFaseDati) {
		List<Object[]>l=new ArrayList<Object[]>();

		String queryStr = "select fai.id_fase_acquisizione_istanza, fai.data_notifica, fai.data_protocollo, fai.voce_titolario, "+
		"fai.intervallo_notifica_protocollo, fai.protocollo_con_img, fai.data_acquisizione_SISCO, "+
		"fai.intervallo_notifica_SISCO, fai.id_fase_dati "+
		"from ATPO_FASE_ACQUISIZIONE_ISTANZA fai, ATPO_FASE_DATI fd "+
		"where fai.id_fase_dati = fd.id_fase_dati "+
		"and fd.id_fase_dati = "+idFaseDati;	


		try {
			Query querySel = em.createNativeQuery(queryStr);
			l = (List<Object[]>) querySel.getResultList();
			if(l!=null && l.size()!=0 && l.get(0) !=null){
				return l.get(0);
			}

		} catch (Throwable e) {
			e.printStackTrace();
			log.error(e);
		}
		return null;
	}

	@Override
	public  Object[] getFasePostPeritale(long idFaseDati) {
		List<Object[]>l=new ArrayList<Object[]>();

		String queryStr = "select pp.ID_POST_PERITALE, pp.DATA_DEPOSITO_DEC_OMOLOGA, pp.DATA_NOTIFICA_DEC_OMOLOGA,pp.DATA_PROT_DEC_OMOLOGA_NOTIF, pp.INT_TEMP_NOTIF_OMG_PROT_OMG, "+
						  " pp.COD_CHIUSURA_CORRETTO, pp.COD_CHIUSURA_INSERITO, pp.SPESE_PAGATE, pp.SPESE_DECR_OMOLOGA, pp.CORRISP_DECR_OMG_E_CTU_DEF, pp.COD_PAGAMENTO_SPESE_LEGALI, "+
						  "pp.COD_PAGAMENTO_SPESE_LEGALI_CORRETTO,pp.REG_DATI_PRATICA, pp.DATA_TRASMISS_DECR_LPS, pp.OMOLOGA_ALLEGATA, pp.INT_TEMP_NOTIF_DECR_OMG_A_DECR_LPS, "+
						  "pp.DATA_DEPOSITO_DISSENSO, pp.INT_TEMP_DEPOSITO_DISS_TERMINE_DISS, pp.COM_DEP_DISS_UFF_LEGALE, pp.DATA_DEP_RIC_PRIMO_G, pp.DATA_DEF_PRATICA, pp.TIPO_DISSENSO, "+
						  " pp.ID_FASE_DATI, pp.FASE_PRONTA, pp.COM_DEP_DISS_UL "+
						  "from ATPO_FASE_POST_PERITALE pp , ATPO_FASE_DATI fd "+
						  "where pp.id_fase_dati = fd.id_fase_dati "+
						  "and fd.id_fase_dati = "+idFaseDati;	


		try {
			Query querySel = em.createNativeQuery(queryStr);
			l = (List<Object[]>) querySel.getResultList();
			if(l!=null && l.size()!=0 && l.get(0) !=null){
				return l.get(0);
			}
		}catch (Throwable e) {
			e.printStackTrace();
			log.error(e);
		}
		return null;
	}

	
	public Object[] getEsecuzioneProvvedimenti(long idFaseDati) {
		List<Object[]>l=new ArrayList<Object[]>();

		String queryStr = "select ep.ID_ESECUZIONE_PROVVEDIMENTI,ep.PRES_DECR_OMG_NEL_FASC, ep.DATA_DREC_LIQ_CTU, "+
						  "ep.DATA_PRESA_IN_CARICO_DECR_OMG_LPS,ep.INT_T_DEP_DECR_OMG_DATA_REC_LEQUIDAZIONE, "+
						  "ep.DATA_LIQ_PREST_LPS,ep.IMPORTO_MENSILE_RATA,ep.DATA_REC_DATI_LIQ, ep.GG_DA_NOTIF_DECR_OMG_A_LIQ_PREST, "+
						  "ep.GG_DA_TRASM_DECR_LPS_A_DECR_OMG_DA_LPS, ep.INTERESSI_LEGALI_PAGATI, "+
						  "ep.INTERESSI_LEGALI_DOVUTI, ep.DATA_DEC_CALCOLO_INT_LEGALI, ep.DATA_DEC_PREST_INSERITA, "+
						  "ep.DATA_CORR_DEC_PRESTAZIONE,ep.PRESTAZIONE_CORRISP, "+
						  "ep.IMPORTO_RATA_DOVUTA, ep.CONDANNA_A_PAGAMENTO_CTU_ATPO, ep.DATA_FATTURA, ep.DATA_LIQ_CTU_ATPO, "+
						  "ep.INT_TEMP_DA_FATT_ELETTR_A_PAG_CTU_ATPO, ep.ANTICIPATE_SPESE_CTU, "+
						  "ep.IMPORTO_SPESE_CTU_PAGATE, ep.IMPORTO_SPESE_CTU_DOVUTE,ep.DATA_LETT_INV_PAG_SPESE_LEGAL, "+
						  "ep.DATA_LETTERA_RECUPERO_SPESE_CTU,ep.CONDANNA_PAG_SPESE_LEGALI, "+
						  "ep.SOGG_RICH_PAGAMENTO,ep.DATA_ARRIVO_NOTULA,ep.DATA_PAG_SPESE_LEGALI_AVV_CPARTE, "+
						  "ep.INT_T_DEP_DECR_OMG_A_PAG_SPESE_LEGALI_CPARTE, ep.PRES_DECR_SENT_MANC_PAG_PREST, "+
						  "ep.COSTO_GIUDIZIO_MANC_PAG_PREST, ep.CONDANNA_PAG_CTU_1G, ep.VER_PAG_CTU_EFF, "+
						  " ep.NO_PRECETTO, ep.SPESE_LEGALI_FLAG_PREC, ep.DATA_SPESE_LEGALI_PREC, ep.DATA_COM_PRE_SL, "+
						  " ep.COSTO_PRE_SL, ep.SPESE_CTU_FLAG_PREC, ep.DATA_SPESE_CTU_PREC, ep.DATA_COM_PRE_SCTU, "+
						  " ep.COSTO_PRE_SCTU, ep.PRESTAZIONE_FLAG_PREC, ep.DATA_PRESTAZIONE, ep.DATA_COM_PRE_PREST, "+
						  " ep.COSTO_PRE_PREST, " +
						  " ep.NO_PIGNORAMENTO, ep.SPESE_LEGALI_FLAG_PIGN, ep.DATA_SPESE_LEGALI_PIGN, "+
						  " ep.COSTO_PIGN_SL, ep.SPESE_CTU_FLAG_PIGN, ep.DATA_SPESE_CTU_PIGN, ep.COSTO_PIGN_SCTU, "+
						  " ep.PRESTAZIONE_FLAG_PIGN, ep.DATA_PIGNORAMENTO_PRESTAZIONE, ep.COSTO_PIGN_PREST, ep.DATA_LIMITE_CALCOLO_IMPATTO "+
						  " from ATPO_FASE_ESECUZIONE_PROVVEDIMENTI ep , ATPO_FASE_DATI fd "+
						  " where ep.id_fase_dati = fd.id_fase_dati "+
						  " and fd.id_fase_dati = "+idFaseDati;

		try {
			Query querySel = em.createNativeQuery(queryStr);
			l = (List<Object[]>) querySel.getResultList();
			if(l!=null && l.size()!=0 && l.get(0) !=null){
				return l.get(0);
			}
		}catch (Throwable e) {
			e.printStackTrace();
			log.error(e);
		}
		return null;
	}


	

	@Override
	public Object[] getFascicolo(long idFaseDati) {
		List<Object[]>l=new ArrayList<Object[]>();
		String queryStr = "select f.ID_RIEPILOGO_FASCICOLO, f.FASCICOLO_ELETTRONICO, f.DETT_DOC_MANCANTE "+
						  "from ATPO_FASE_RIEPILOGO_FASCICOLO f , ATPO_FASE_DATI fd "+
						  "where f.id_fase_dati = fd.id_fase_dati "+
						  "and fd.id_fase_dati = "+idFaseDati;	
		try {
			Query querySel = em.createNativeQuery(queryStr);
			l = (List<Object[]>) querySel.getResultList();
			if(l!=null && l.size()!=0 && l.get(0) !=null){
				return l.get(0);
			}
		}catch (Throwable e) {
			e.printStackTrace();
			log.error(e);
		}
		return null;
	}
	
	@Override
	public List<Object[]> getTabDocMancante(long idFascicolo,Integer pageNumber, Integer pageSize, Integer columnNameToOrder,
			String orderType, String textSearch) {
		List<Object[]> table=null;
		String queryStr = "SELECT df.ID_DETT_FASC, df.ID_RIEPILOGO_FASCICOLO, df.CODIFICA, t.DESCRIZIONE  from ATPO_DETTAGLIO_FASCICOLO df, AU_TPL_TIPOLOGICHE t "+
						   " where df.ID_RIEPILOGO_FASCICOLO="+idFascicolo +" AND df.codifica= t.CODIFICA";

		if (!StringUtils.isEmpty(textSearch)) {

			queryStr += " and (" + "upper(t.descrizione) like UPPER('%" + textSearch + "%') "  + ")";
		}
		if (columnNameToOrder != null) {
			
			queryStr +=" ORDER BY "+(columnNameToOrder + 1)+" "+orderType +" OFFSET "+pageNumber+" ROWS ";
			queryStr +=" FETCH NEXT "+pageSize+" ROWS ONLY ";
		}

		Query querySel = em.createNativeQuery(queryStr);
		
		try{
			table = querySel.getResultList();
			return table;
		}catch(Exception e){
			log.error("Errore durante l'esecuzione del meotodo getTabDocMancante() della classe AtpoFasiDaoImpl.java ");
		}
		

		return null;
	}
	@Override
	public Integer countTabDocMancante(long idFascicolo, String textSearch) {
		Integer count = null;
		String query = "SELECT count(df.ID_DETT_FASC) from ATPO_DETTAGLIO_FASCICOLO df, AU_TPL_TIPOLOGICHE t "+
		   " where df.ID_RIEPILOGO_FASCICOLO="+idFascicolo +" AND df.codifica= t.CODIFICA";
		if (!StringUtils.isEmpty(textSearch)) {
		   query+= " and (" + "upper(t.descrizione) like UPPER('%"+ textSearch + "%') " + ")";
		}

		try {
			Query querySel = em.createNativeQuery(query);
			count = (Integer) querySel.getSingleResult();
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Errore durante l'esecuzione del meotodo  countTabDocMancante() della classe AtpoFasiDaoImpl.java ");

		}
		
		return null;
	}
	
	@Override
	public Object[] checkDocMancante(long idRiepilogoFascicolo, String codifica) {
		List<Object[]>l=new ArrayList<Object[]>();
		String queryStr = "SELECT ID_DETT_FASC ,ID_RIEPILOGO_FASCICOLO ,CODIFICA "+
						  "FROM ATPO_DETTAGLIO_FASCICOLO "+
						  " WHERE ID_RIEPILOGO_FASCICOLO="+idRiepilogoFascicolo +" AND CODIFICA='"+codifica+"'";
		try {
			Query querySel = em.createNativeQuery(queryStr);
			l = (List<Object[]>) querySel.getResultList();
			if(l!=null && l.size()!=0 && l.get(0) !=null){
				return l.get(0);
			}
		}catch (Throwable e) {
			e.printStackTrace();
			log.error(e);
		}
		return null;
	}

	
	

	/*
	 * 	NEW TIPOLOGICHE
	 */
	@Override
	public List<Object[]> getTipologicaAtpo(String tipo) {
		List<Object[]> list = new ArrayList<Object[]>();

		String queryStr = "select t.ID_TPL_TIPOLOGICHE, t.CODIFICA, t.TIPO, t.DESCRIZIONE  from AU_TPL_TIPOLOGICHE t WHERE TIPO='"+tipo+"'";	

		try {
			Query querySel = em.createNativeQuery(queryStr);
			list = querySel.getResultList();

		} catch (Throwable e) {
			e.printStackTrace();
			log.error(e);
		}
		return list;
	}
	
	@Override
	public Object[] getDescrTipologicaByCodifica(String tipo, String codifica) {
		List<Object[]> list = new ArrayList<Object[]>();

		String queryStr = "select t.ID_TPL_TIPOLOGICHE, t.CODIFICA, t.TIPO, t.DESCRIZIONE  from AU_TPL_TIPOLOGICHE t WHERE t.CODIFICA='"+codifica+"' and t.TIPO ='"+tipo+"'" ;	

		try {
			Query querySel = em.createNativeQuery(queryStr);
			list = querySel.getResultList();
			if(list !=null && list.size() !=0 && list.get(0) != null){
				return list.get(0);
			}

		} catch (Throwable e) {
			e.printStackTrace();
			log.error(e);
		}
		return null;
	}
	
	@Override
	public Object[] getPraticheSisco(String fascicolo, String codSede) {
		List<Object[]> list = new ArrayList<Object[]>();

		String queryStr = "select t.ID_PRATICHE_SISCO, t.T_DISSENSO  from ATPO_PRATICHE_SISCO t "+
					      "  WHERE t.FASCICOLO='"+fascicolo+"' and COD_SEDE='"+codSede+"'";

		try {
			Query querySel = em.createNativeQuery(queryStr);
			list = querySel.getResultList();
			if(list !=null){
				return list.get(0);
			}

		} catch (Throwable e) {
			e.printStackTrace();
			log.error(e);
		}
		return null;
	}





	
	// O P   C O M U N I____________________________________________

	@Transactional
	public <T> T save(T entity){
		return em.merge(entity);
	}

	@Transactional
	public <T> T cerca(Class<T> obj , Object pk) {

		try {
			return (T) em.find(obj,pk);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	



	// T O P O L O G I C H E ____________________________________________________


	public List<Object[]> getVoceTotalitario() {
		List<Object[]> list = new ArrayList<Object[]>();

		String queryStr = "select * from ATPO_VOCE_TOTALITARIO";	

		try {
			Query querySel = em.createNativeQuery(queryStr);
			list = querySel.getResultList();

		} catch (Throwable e) {
			e.printStackTrace();
			log.error(e);
		}
		return list;
	}

	public List<Object[]> getTipologiaRicorso() {
		List<Object[]> list = new ArrayList<Object[]>();

		String queryStr = "select * from ATPO_TIPOLOGIA_RICORSO";	

		try {
			Query querySel = em.createNativeQuery(queryStr);
			list = querySel.getResultList();

		} catch (Throwable e) {
			e.printStackTrace();
			log.error(e);
		}
		return list;
	}

	public List<Object[]> getTerminiPrimaUdienza() {
		List<Object[]> list = new ArrayList<Object[]>();

		String queryStr = "select * from ATPO_TERMINI_PRIMA_UDIENZA";	

		try {
			Query querySel = em.createNativeQuery(queryStr);
			list = querySel.getResultList();

		} catch (Throwable e) {
			e.printStackTrace();
			log.error(e);
		}
		return list;
	}

	public List<Object[]> getParereAutotutela() {
		List<Object[]> list = new ArrayList<Object[]>();

		String queryStr = "select * from ATPO_PARERE_AUTOTUTELA";	

		try {
			Query querySel = em.createNativeQuery(queryStr);
			list = querySel.getResultList();

		} catch (Throwable e) {
			e.printStackTrace();
			log.error(e);
		}
		return list;
	}

	public List<Object[]> getValoriGI() {
		List<Object[]> list = new ArrayList<Object[]>();

		String queryStr = "select * from ATPO_VALORI_GI";	

		try {
			Query querySel = em.createNativeQuery(queryStr);
			list = querySel.getResultList();

		} catch (Throwable e) {
			e.printStackTrace();
			log.error(e);
		}
		return list;
	}

	public List<Object[]> getCorrispondenzaIstanzaAtp() {
		List<Object[]> list = new ArrayList<Object[]>();

		String queryStr = "select * from ATPO_CORRISPONDENZA_ISTANZA_ATP";	

		try {
			Query querySel = em.createNativeQuery(queryStr);
			list = querySel.getResultList();

		} catch (Throwable e) {
			e.printStackTrace();
			log.error(e);
		}
		return list;
	}

	public List<Object[]> getVerificaCorrettezza() {
		List<Object[]> list = new ArrayList<Object[]>();

		String queryStr = "select * from ATPO_VERIFICA_CORRETTEZZA";	

		try {
			Query querySel = em.createNativeQuery(queryStr);
			list = querySel.getResultList();

		} catch (Throwable e) {
			e.printStackTrace();
			log.error(e);
		}
		return list;
	}

	public List<Object[]> getSiNo() {
		List<Object[]> list = new ArrayList<Object[]>();

		String queryStr = "select * from ATPO_SI_NO";	

		try {
			Query querySel = em.createNativeQuery(queryStr);
			list = querySel.getResultList();

		} catch (Throwable e) {
			e.printStackTrace();
			log.error(e);
		}
		return list;
	}

	@Override
	public List<Object[]> getInfoOperazioniPeritali() {
		List<Object[]> list = new ArrayList<Object[]>();
		String queryStr = "select * from ATPO_REG_INFO_OP_PERITALI";	

		try {
			Query querySel = em.createNativeQuery(queryStr);
			list = querySel.getResultList();

		} catch (Throwable e) {
			e.printStackTrace();
			log.error(e);
		}
		return list;
	}

	public List<Object[]> getRegInfoOpPeritale() {
		List<Object[]> list = new ArrayList<Object[]>();

		String queryStr = "select * from ATPO_REG_INFO_OP_PERITALI";	

		try {
			Query querySel = em.createNativeQuery(queryStr);
			list = querySel.getResultList();

		} catch (Throwable e) {
			e.printStackTrace();
			log.error(e);
		}
		return list;
	}

	public List<Object[]> getPreCtpInpsOpPeritali() {
		List<Object[]> list = new ArrayList<Object[]>();

		String queryStr = "select * from ATPO_PRE_CTP_INPS_OP_PERITALI";	

		try {
			Query querySel = em.createNativeQuery(queryStr);
			list = querySel.getResultList();

		} catch (Throwable e) {
			e.printStackTrace();
			log.error(e);
		}
		return list;
	}

	public List<Object[]> getParereBozzaCTU() {
		List<Object[]> list = new ArrayList<Object[]>();

		String queryStr = "select * from ATPO_PARERE_BOZZA_CTU";	

		try {
			Query querySel = em.createNativeQuery(queryStr);
			list = querySel.getResultList();

		} catch (Throwable e) {
			e.printStackTrace();
			log.error(e);
		}
		return list;
	}

	@Override
	public List<Object[]> getSiNoNonRilevabile() {
		List<Object[]> list = new ArrayList<Object[]>();

		String queryStr = "select * from ATPO_PRE_CTP_INPS_OP_PERITALI";	

		try {
			Query querySel = em.createNativeQuery(queryStr);
			list = querySel.getResultList();

		} catch (Throwable e) {
			e.printStackTrace();
			log.error(e);
		}
		return list;
	}


	@Override
	public List<Object[]> getAtpoParereBozzaCTU() {
		List<Object[]> list = new ArrayList<Object[]>();

		String queryStr = "select * from ATPO_PARERE_BOZZA_CTU";	

		try {
			Query querySel = em.createNativeQuery(queryStr);
			list = querySel.getResultList();

		} catch (Throwable e) {
			e.printStackTrace();
			log.error(e);
		}
		return list;
	}

	@Override
	public List<Object[]> getOptionsCodChiusura() {
		List<Object[]> list = new ArrayList<Object[]>();

		String queryStr = "select * from ATPO_COD_CHIUSURA_POST_PERITALE";	

		try {
			Query querySel = em.createNativeQuery(queryStr);
			list = querySel.getResultList();

		} catch (Throwable e) {
			e.printStackTrace();
			log.error(e);
		}
		return list;
	}

	@Override
	public List<Object[]> getCorrispDecrOMGeCtuDef() {
		List<Object[]> list = new ArrayList<Object[]>();

		String queryStr = "select * from ATPO_CORRISP_DECR_OMG_E_CTU_DEF_POSTPERITALE";	

		try {
			Query querySel = em.createNativeQuery(queryStr);
			list = querySel.getResultList();

		} catch (Throwable e) {
			e.printStackTrace();
			log.error(e);
		}
		return list;
	}
	
	@Override
	public List<Object[]> getCodPagamentoSpeseLegaliINPS() {
		List<Object[]> list = new ArrayList<Object[]>();

		String queryStr = "select * from ATPO_COD_PAG_SPESE_LEGALI_INPS_POST_PERITALE";	

		try {
			Query querySel = em.createNativeQuery(queryStr);
			list = querySel.getResultList();

		} catch (Throwable e) {
			e.printStackTrace();
			log.error(e);
		}
		return list;
	}
	
	@Override
	public List<Object[]> getOptionsRecDatiPratica() {
		List<Object[]> list = new ArrayList<Object[]>();

		String queryStr = "select * from ATPO_REC_DATI_PRATICA_POST_PERITALE";	

		try {
			Query querySel = em.createNativeQuery(queryStr);
			list = querySel.getResultList();

		} catch (Throwable e) {
			e.printStackTrace();
			log.error(e);
		}
		return list;
	}


	@Override
	public List<Object[]> getOpzCondPagSpeseLegali() {
		List<Object[]> list = new ArrayList<Object[]>();

		String queryStr = "select * from ATPO_COND_PAG_SPESE_L_ES_PROVV";	

		try {
			Query querySel = em.createNativeQuery(queryStr);
			list = querySel.getResultList();

		} catch (Throwable e) {
			e.printStackTrace();
			log.error(e);
		}
		return list;
	}

	@Override
	public List<Object[]> getOpzSoggRichPagamento() {
		List<Object[]> list = new ArrayList<Object[]>();

		String queryStr = "select * from ATPO_SOGG_RICH_PAG_ES_PROVV";	

		try {
			Query querySel = em.createNativeQuery(queryStr);
			list = querySel.getResultList();

		} catch (Throwable e) {
			e.printStackTrace();
			log.error(e);
		}
		return list;
	}

	@Override
	public List<Object[]> getFascicoliElettroniciEcartacei() {
		List<Object[]> list = new ArrayList<Object[]>();

		String queryStr = "select * from ATPO_FASCICOLO_ES_PROV";	

		try {
			Query querySel = em.createNativeQuery(queryStr);
			list = querySel.getResultList();

		} catch (Throwable e) {
			e.printStackTrace();
			log.error(e);
		}
		return list;
	}

	@Override
	public List<Object[]> getDettDocMancate() {
		List<Object[]> list = new ArrayList<Object[]>();

		String queryStr = "select * from ATPO_DETT_DOC_MANCANTE_ES_PROV";	

		try {
			Query querySel = em.createNativeQuery(queryStr);
			list = querySel.getResultList();

		} catch (Throwable e) {
			e.printStackTrace();
			log.error(e);
		}
		return list;
	}

	@Transactional
	public void remove(Object o) {
		try{
			em.remove(o);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}

	@Override
	public Date getDateSessione(Long idSessione) {
		List<Object[]> list = new ArrayList<Object[]>();

		String queryStr = "SELECT DATA_INIZIO, 'DATA_INIZIO' FROM AU_SESSIONI WHERE ID_SESSIONE = " + idSessione;	

		try {
			Query querySel = em.createNativeQuery(queryStr);
			list = querySel.getResultList();
			return (Date)list.get(0)[0];
		} catch (Throwable e) {
			e.printStackTrace();
			log.error(e);
		}
		return null;
	}





	







}
