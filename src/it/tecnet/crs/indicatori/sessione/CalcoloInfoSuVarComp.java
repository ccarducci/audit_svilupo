package it.tecnet.crs.indicatori.sessione;

import it.tecnet.crs.jpa.model.AuMVarcomp;
import it.tecnet.crs.jpa.model.AuSPraticaVarcomp;
import it.tecnet.crs.jpa.model.AuSVarcomp;
import it.tecnet.crs.jpa.model.AuTplIsnc;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class CalcoloInfoSuVarComp {

	private List<AuMVarcomp> auMVarComp;
	private List<AuSVarcomp> auSVarcomp;
	private List<AuSPraticaVarcomp> auSPraticaClosedVarcomp;
	private List<AuTplIsnc> listAuTplIsnc;
	
	public CalcoloInfoSuVarComp(List<AuMVarcomp> auMVarComp,
			List<AuSPraticaVarcomp> auSPraticaClosedVarcomp,
			List<AuSVarcomp> auSVarcomp, List<AuTplIsnc> listAuTplIsnc) {
		super();
		this.auMVarComp = auMVarComp;
		this.auSVarcomp = auSVarcomp;
		this.auSPraticaClosedVarcomp = auSPraticaClosedVarcomp;
		this.listAuTplIsnc = listAuTplIsnc;
	}

	public List<AuSVarcomp> calcoloInfoSuVarComp() {
		HashMap<String, Integer> varCompPerNumMap = new HashMap<String, Integer>();
		HashMap<Long, Double> sommaPerNonConf = new HashMap<Long, Double>();

		/*
		 * Ciclo per determinare la numerosita per variabile comportamentale e
		 * per nonconformita
		 */
		for (AuSPraticaVarcomp item : auSPraticaClosedVarcomp) {

			// CALCOLO SOMMA PER VARIABILE COMPORTAMENTALE
			Integer itemVarCompToAdd = null;
			if (varCompPerNumMap.containsKey(item.getVarComp())) {
				itemVarCompToAdd = varCompPerNumMap.get(item.getVarComp());
				itemVarCompToAdd++;
			} else {
				itemVarCompToAdd = 1;
			}
			varCompPerNumMap.put(item.getVarComp(), itemVarCompToAdd);

			// CALCOLO SOMMA PER NON CONF
			AuMVarcomp auMVarcomp = getAuSVarcompByVc(item.getVarComp());
			if (auMVarcomp != null) {
				Double itemNonConfToAdd = null;
				if (sommaPerNonConf.containsKey(auMVarcomp.getIdMNonConf())) {
					itemNonConfToAdd = sommaPerNonConf.get(auMVarcomp
							.getIdMNonConf());
					itemNonConfToAdd++;
				} else {
					itemNonConfToAdd = 1D;
				}
				
				
				sommaPerNonConf.put(auMVarcomp.getIdMNonConf(),
						itemNonConfToAdd);
			}
		}

		for (Entry<String, Integer> itemSet : varCompPerNumMap.entrySet()) {
			String varComp = itemSet.getKey();
			Integer numVarComp = itemSet.getValue();
			AuMVarcomp auMVarcomp = getAuSVarcompByVc(varComp);
			if (auMVarcomp != null) {
				
				Double sommaPerNonConformita = sommaPerNonConf.get(auMVarcomp
						.getIdMNonConf());
				AuTplIsnc auTplIsnc = getAuTplIsncByIndiceMVarcomp(auMVarcomp.getPesoVc());
				// Double pesoVc = auMVarcomp.getPesoVc();
				Double soglia = Double.parseDouble(auTplIsnc.getSoglia());
			
			
				AuSVarcomp auSVarcompItemToAdd = new AuSVarcomp();
				auSVarcompItemToAdd.setIdMNonconf(auMVarcomp.getIdMNonConf());
				auSVarcompItemToAdd.setNum(numVarComp);

				/*
				 * num = numero pratiche esaminate quantificate per specifica
				 * Variante Comportamentale da S-PraticaVarComp e S-Pratica con
				 * Esame pratica = "C"
				 */
				auSVarcompItemToAdd.setNum(numVarComp);

				/*
				 * % su PS = num / somma num con stessa Non Conformità da
				 * S-PraticaVarComp
				 */
				Double percSuPs = null;
				if (numVarComp != null && sommaPerNonConformita != null) {
					percSuPs = numVarComp
							/ sommaPerNonConformita.doubleValue();
					auSVarcompItemToAdd.setPercSuPs(percSuPs);
				}else{
					auSVarcompItemToAdd.setPercSuPs(0d);
				}

				/*
				 * % pesata = num * Indice VC da M-VarComp
				 */
				if (percSuPs != null && soglia != null) {
					Double percPesata = percSuPs.doubleValue()
							* soglia.doubleValue();
					auSVarcompItemToAdd.setPercPesata(percPesata);
				}else{
					auSVarcompItemToAdd.setPercPesata(0d);
				}

				auSVarcompItemToAdd.setIdMVarcomp(auMVarcomp.getIdMComp());
				auSVarcomp.add(auSVarcompItemToAdd);
			}
		}

		return auSVarcomp;
	}

	private AuMVarcomp getAuSVarcompByVc(String varComp) {
		AuMVarcomp ret = null;
		for (AuMVarcomp element : auMVarComp) {
			if (element.getCodicevc().trim().toUpperCase().equals(
					varComp.trim().toUpperCase()))
				return element;
		}
		return ret;
	}
	
	private AuTplIsnc getAuTplIsncByIndiceMVarcomp(Double indiceVc){
		for (AuTplIsnc item : listAuTplIsnc) {
			if(item.getIdTplIsnc() == indiceVc.longValue() ) return item;
		}
		return null;
	}
}
