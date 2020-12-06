package it.tecnet.crs.util;

import it.tecnet.crs.audit.web.dto.CalcoloIndicatoriRiepilogoPraticheNonConfFasi;
import it.tecnet.crs.indicatori.sessione.CalcoloIndicatoriSessione;
import it.tecnet.crs.jpa.model.AuInccDes;
import it.tecnet.crs.jpa.model.AuSPraCalIndLog;
import it.tecnet.crs.jpa.model.AuSPraticaRis;
import it.tecnet.crs.jpa.model.AuSRischio;
import it.tecnet.crs.jpa.model.AuSTvalori;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.apache.log4j.Logger;


public class SessioneUtil {

	protected static Logger log = Logger
	.getLogger(CalcoloIndicatoriSessione.class);
	
	public static final String ESAME_SESSIONE_APERTO = "A";
	public static final String ESAME_SESSIONE_ELABORAZIONE = "E";
	public static final String ESAME_SESSIONE_CHIUSO = "C";
	
	
	public static Double getTVAloreParam(String codice, String param,List<AuSTvalori> valori){
		for (AuSTvalori item : valori) {
			if(item.getCodice() != null && item.getCodice().trim().equals(codice.trim())
					&& item.getValore() != null && item.getVariabile().trim().equals(param) ) return item.getValore();
		}
		return 0d;	
	}
	
	public static HashMap<Long, Long> getNumPerStessoRischio(List<AuSPraticaRis> listAuPraticaRis) {
		HashMap<Long, Long> ret = new HashMap<Long, Long>();
		for (AuSPraticaRis item : listAuPraticaRis) {
			Long idMRischio = item.getIdMRischio();
			Long num = 1L;
			if (ret.containsKey(idMRischio)) {
				num = ret.get(idMRischio);
				num++;
			}
			ret.put(idMRischio, num);
		}
		return ret;
	}

	public static Double getTotalePesiValoreAssoluto(
			HashMap<Long, AuSRischio> listaRischio) {
		Double ret = 0D;
		if (listaRischio == null) return ret;
		for (Entry<Long, AuSRischio> itemRischio : listaRischio.entrySet()) {
			if (itemRischio.getValue() != null 
					&& itemRischio.getValue().getPesoRischio() != null)
				ret += itemRischio.getValue().getPesoRischio();
		}
		return ret;
	}
	
	public static String getInccDescrittivo( double incc, List<AuInccDes> inccDescrittiviLista) {
		String descrittivo = "";
		try{
			if ( inccDescrittiviLista != null ){
				for (AuInccDes auInnccDes : inccDescrittiviLista) {
					// if(incc<= auInnccDes.getValoreIncc())level++;
					if(incc >= auInnccDes.getValoreIncc())return auInnccDes.getDescrizione();
				}
				descrittivo = inccDescrittiviLista.get(0).getDescrizione();
			}
		}catch(Exception ex){
			log.error(ex.getMessage());
		}
		return descrittivo;
	}
	
	public static void insertLog(List<AuSPraCalIndLog> list,String messaggio,long idSSessione, String tipoCalcolo, String tipoErrore ){
		AuSPraCalIndLog item = new AuSPraCalIndLog();
		if (tipoCalcolo == null ) tipoCalcolo = "INFO";
		if (tipoErrore == null ) tipoErrore = "INFO";
		item.setIdSSessione(idSSessione);
		item.setTipoCalcolo(tipoCalcolo);
		item.setTipoErrore(tipoErrore);
		item.setDataInserimento(new Date());
		item.setMessaggio(messaggio);
		list.add(item);
		log.info(item);
	}
	
	public static Long getIdFaseByIdMNonconf(long idNonConf,List<CalcoloIndicatoriRiepilogoPraticheNonConfFasi> listaRiepilogoNonConfFasi){
		for (CalcoloIndicatoriRiepilogoPraticheNonConfFasi row : listaRiepilogoNonConfFasi) {
			if (idNonConf == row.getIdMNonConf().longValue())return row.getIdFase();
		}
		return 0l;
	}
	
	public static Double getSumByFase(long idFase,List<CalcoloIndicatoriRiepilogoPraticheNonConfFasi> listaRiepilogoNonConfFasi){
		Double numTot = 0d;
		for (CalcoloIndicatoriRiepilogoPraticheNonConfFasi row : listaRiepilogoNonConfFasi) {
			if (idFase == row.getIdFase().longValue())numTot = numTot.doubleValue() + row.getValorePesato().doubleValue();
		}
		return numTot;
	}
}
