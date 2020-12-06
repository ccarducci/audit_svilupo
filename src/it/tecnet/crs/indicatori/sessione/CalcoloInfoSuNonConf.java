package it.tecnet.crs.indicatori.sessione;

import it.tecnet.crs.jpa.model.AuMNonConf;
import it.tecnet.crs.jpa.model.AuMVarcomp;
import it.tecnet.crs.jpa.model.AuSNonconf;
import it.tecnet.crs.jpa.model.AuSSessione;
import it.tecnet.crs.jpa.model.AuSTvalori;
import it.tecnet.crs.jpa.model.AuSVarcomp;
import it.tecnet.crs.util.SessioneUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class CalcoloInfoSuNonConf {

	private List<AuSVarcomp> auSVarcomp;
	private List<AuMNonConf> listAuMNonConf;
	private List<AuSNonconf> listAuSNonconf;
	private AuSSessione sSessione;
	private List<AuMVarcomp> listAuMVarcomp;
	private List<AuSTvalori> listAuTValori;
	
	public CalcoloInfoSuNonConf(List<AuSVarcomp> auSVarcomp,
			List<AuMNonConf> listAuMNonConf, List<AuSNonconf> listAuSNonconf, AuSSessione sSessione, 
			List<AuMVarcomp> listAuMVarcomp, List<AuSTvalori> listAuTValori) {
		this.auSVarcomp = auSVarcomp;
		this.listAuMNonConf = listAuMNonConf;
		this.listAuSNonconf = listAuSNonconf;
		this.sSessione = sSessione;
		this.listAuMVarcomp = listAuMVarcomp;
		this.listAuTValori = listAuTValori;
	}

	public List<AuSNonconf> calcoloInfoSuNonConf(){
		HashMap<Long,AuSNonconf> auSNonconfMap = new HashMap<Long,AuSNonconf>();
		HashMap<Long,Double> percPessataPerIdMNonConfMap = new HashMap<Long,Double>();
		
		double totPesoNonConf = 0D;
		double min = SessioneUtil.getTVAloreParam("AAA" ,"Min" , listAuTValori);
		double max = SessioneUtil.getTVAloreParam("AAA" ,"Max" , listAuTValori);
		double minINCC = 0D;
		double maxINCC = 0D;
		boolean isFirst = true;
		
		for (AuSVarcomp item : auSVarcomp) {
			/*
			 * num = numero pratiche esaminate della sessione con la specifica non conformità da S-VarComp
			 */
			long idMNonconf = item.getIdMNonconf();
			AuSNonconf itemToAdd = null; 
			if(auSNonconfMap.containsKey(idMNonconf)){
				itemToAdd = auSNonconfMap.get(idMNonconf);
				Integer num = itemToAdd.getNum();
				num = num + item.getNum();
				itemToAdd.setNum(num);
				itemToAdd.setIdMNonconf(idMNonconf);
			}else{
				itemToAdd = new AuSNonconf();
				itemToAdd.setNum(item.getNum());
				itemToAdd.setIdMNonconf(idMNonconf);
			}
		
			itemToAdd.setIdMNonconf(idMNonconf);
			itemToAdd.setIdSSessione(sSessione.getIdSSessione());
			
			auSNonconfMap.put(idMNonconf, itemToAdd);
			
			
			// -----------------------------------------------------------
			// CALCOLO SOMMA PESATA PER NON CONFORMITA -------------------
			Double percPessata = 0D;
			if(percPessataPerIdMNonConfMap.containsKey(idMNonconf)){
				percPessata = percPessataPerIdMNonConfMap.get(idMNonconf);
				if (item.getPercPesata() != null) percPessata += item.getPercPesata().doubleValue();
			}else{
				if (item.getPercPesata() != null) percPessata += item.getPercPesata().doubleValue();
			}

			percPessataPerIdMNonConfMap.put(idMNonconf, percPessata);
			// -----------------------------------------------------------

		}
		
		for (Entry<Long, AuSNonconf> item : auSNonconfMap.entrySet()) {
			AuSNonconf itemToAdd = item.getValue();
			
			/*
			 * Peso non conf =
			 * se num > 0 allora Peso non conf = Peso non conf da M-NonConf 
			 * altrimenti
			 * Peso non conf = 0
			 */
			if (itemToAdd.getNum().intValue() > 0 ){
				//AuMVarcomp auMVarcomp = getAuMNonconfByIdMVarComp(itemToAdd.getIdMNonconf());
				Double pesoVc = getPesoNonConf(itemToAdd.getIdMNonconf());
				if (pesoVc != null ) itemToAdd.setPesoNonconf(pesoVc.doubleValue());
			}else{
				itemToAdd.setPesoNonconf(0D);
			}
			
			/*
			 * Valore INCC 
			 * SE (PESO NON CONF = 0 allora VALORE INCC = "" 
			 * altrimenti
			 * SE (somma delle % pesata relative alla stessa non conformità da S-VarComp )  < 0 allora VALORE INCC = 0
			 * altrimenti 
			 * VALORE INCC = somma delle % pesata relative alla stessa non conformità da S-VarComp)  
			 */
			if(itemToAdd.getPesoNonconf().doubleValue()  > 0 ){
				Double  percPessata = percPessataPerIdMNonConfMap.get(itemToAdd.getIdMNonconf());
				if (percPessata != null && percPessata.doubleValue() > 0)
					itemToAdd.setValoreIncc(percPessata);
				else
					itemToAdd.setValoreIncc(0D);
			}else{
				itemToAdd.setValoreIncc(0D);
			}
			
			/*
			 * Valore/Pratica non soggetta =
			 * Se peso non conf = 0 allora Valore/Pratica non soggetta = "Non ci sono pratiche soggette"
			 * altrimenti 
			 * Valore/Pratica non soggetta = valore pesato
			 */
			if (itemToAdd.getPesoNonconf().doubleValue() > 0)
				itemToAdd.setValorePraticaNonsoggetta("valore pesato");
			else
				itemToAdd.setValorePraticaNonsoggetta("Non ci sono pratiche soggette");
			
		
		    // Minimo
			if(itemToAdd.getValoreIncc().doubleValue() < min)
				itemToAdd.setMinimo(0);
			else
				itemToAdd.setMinimo(1);
			
			// Massimo
			if(itemToAdd.getValoreIncc().doubleValue() > max)
				itemToAdd.setMassimo(0);
			else
				itemToAdd.setMassimo(1);
			
			// CALCOLO PER MIN MAX -- usata nel ciclo dopo
			if (isFirst){
				isFirst = false;
				maxINCC =  itemToAdd.getValoreIncc().doubleValue();
				minINCC =  itemToAdd.getValoreIncc().doubleValue();
			}else{
				if (maxINCC < itemToAdd.getValoreIncc().doubleValue())maxINCC =  itemToAdd.getValoreIncc().doubleValue();
				if (minINCC > itemToAdd.getValoreIncc().doubleValue())minINCC =  itemToAdd.getValoreIncc().doubleValue();
			}
			
			totPesoNonConf = totPesoNonConf + itemToAdd.getPesoNonconf();
			
			
			listAuSNonconf.add(itemToAdd);
		}

		for (AuSNonconf item : listAuSNonconf) {
			// valore pesato = Valore (INCC) * Peso non conf / somma Peso non conf su S-Nonconf
			if ( item.getValoreIncc().doubleValue() > 0 && item.getPesoNonconf().doubleValue()  > 0 && totPesoNonConf > 0 ){
				item.setValorePesato((item.getValoreIncc().doubleValue() * item.getPesoNonconf().doubleValue() )/totPesoNonConf);
				item.setValorePesatoFase(item.getValoreIncc().doubleValue() * item.getPesoNonconf().doubleValue());
			}else{
				item.setValorePesato(0D);
				item.setValorePesatoFase(0D);
			}

			// Attivita con valore minimo
			if ( item.getValoreIncc().doubleValue() == minINCC)
				item.setValoreMinAttivita(getDescrizioneFromIdNonConf(item.getIdMNonconf()));
			
			// Attivita con valore massimo
			if ( item.getValoreIncc().doubleValue() == maxINCC)
				item.setValoreMaxAttivita(getDescrizioneFromIdNonConf(item.getIdMNonconf()));
			
		}
		
		return listAuSNonconf;
	}
	
	private Double getPesoNonConf(Long id){
		if (id==null) return null;
		for (AuMNonConf auMNonConfItem : listAuMNonConf) {
			if (auMNonConfItem.getIdMNonConf() == id.longValue())return auMNonConfItem.getPesoNonConf();
		}
		return null;
	}
	
	@SuppressWarnings("unused")
	private AuMVarcomp getAuMNonconfByIdMVarComp(Long idMNonConf) {
		AuMVarcomp ret = null;
		for (AuMVarcomp element : listAuMVarcomp) {
			if (element.getIdMNonConf() == idMNonConf.longValue()) return element;
		}
		return ret;
	}
	
	private String getDescrizioneFromIdNonConf(long idNonConf){
		for (AuMNonConf item : listAuMNonConf) {
			if ( item.getIdMNonConf() == idNonConf)return item.getDescrizione();
		}
		return "";
	}
}
