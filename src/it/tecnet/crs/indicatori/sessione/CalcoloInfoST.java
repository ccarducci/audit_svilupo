package it.tecnet.crs.indicatori.sessione;

import it.tecnet.crs.jpa.model.AuSPratica;
import it.tecnet.crs.jpa.model.AuSSessione;
import it.tecnet.crs.jpa.model.AuSTempi;
import it.tecnet.crs.jpa.model.AuSTesito;
import it.tecnet.crs.jpa.model.AuSTfascicolo;
import it.tecnet.crs.jpa.model.AuTdocmanc;
import it.tecnet.crs.jpa.model.AuTplTipologiche;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CalcoloInfoST {
	
	private AuSSessione sessione;
	//private List<AuTplTipologiche> tipologica;
	
	@SuppressWarnings("unused")
	private List<AuSPratica> listAuSPratica;
	
	public CalcoloInfoST(AuSSessione sessione, List<AuTplTipologiche> tipologica,List<AuSPratica> listAuSPratica) {
		this.sessione = sessione;
		//this.tipologica = tipologica;
		this.listAuSPratica = listAuSPratica;
	}

	public List<AuTdocmanc> getSTDocManc(List<RiepilogoTipologica> riepilogoTipologica, Integer numeroPraticheEsaminate){
		List<AuTdocmanc> listAuSDocmanc = new ArrayList<AuTdocmanc>();
		/*
		 * 1 - Quantità = Numero pratiche esaminate per pratiche con Esame pratica = "C" quantificate per Tipo Dettaglio da S-PraticaDF  (T-Tipologiche - Tipo “V003”)
		 * 2 - % = Quantità / N. pratiche esaminate su S-Sessione 
		 */
		if (riepilogoTipologica != null && riepilogoTipologica.size() == 0 )return listAuSDocmanc;
		for (RiepilogoTipologica item : riepilogoTipologica) {
			
			AuTdocmanc itemToAdd = new AuTdocmanc();
			itemToAdd.setIdSSessione(sessione.getIdSSessione());
			itemToAdd.setCodifica(item.getCodifica());
			itemToAdd.setQuantita(item.getNum());
			if( item.getNum() > 0 && numeroPraticheEsaminate.doubleValue() > 0)
				itemToAdd.setPercentuale(item.getNum() 
									/ numeroPraticheEsaminate.doubleValue());
			itemToAdd.setTipo("V003");
			listAuSDocmanc.add(itemToAdd);
			
		}
		return listAuSDocmanc;
	}
	
	public List<AuSTfascicolo> getAuSTfascicolo(List<RiepilogoTipologica> riepilogoTipologica, Integer numeroPraticheEsaminate){
		List<AuSTfascicolo> listAuSTfascicolo = new ArrayList<AuSTfascicolo>();
		if (riepilogoTipologica != null && riepilogoTipologica.size() == 0 )return listAuSTfascicolo;
		for (RiepilogoTipologica item : riepilogoTipologica) {
			
			AuSTfascicolo itemToAdd = new AuSTfascicolo();
			itemToAdd.setIdSSessione(sessione.getIdSSessione());
			itemToAdd.setCodifica(item.getCodifica());
			itemToAdd.setTipo("V007");
			itemToAdd.setQuantita(item.getNum());
			if( item.getNum() > 0 && numeroPraticheEsaminate.doubleValue() > 0)
				itemToAdd.setPerc( new Double(item.getNum()).doubleValue() / numeroPraticheEsaminate.doubleValue() );
			listAuSTfascicolo.add(itemToAdd);
			
		}
		return listAuSTfascicolo;
	}
	
	public List<AuSTempi> getSTempi(List<RiepilogoTipologica> riepilogoTipologica, Integer numeroPraticheEsaminate){
		List<AuSTempi> listAuSTempi = new ArrayList<AuSTempi>();
		if (riepilogoTipologica != null && riepilogoTipologica.size() == 0 )return listAuSTempi;
		for (RiepilogoTipologica item : riepilogoTipologica) {
			
			AuSTempi itemToAdd = new AuSTempi();
			itemToAdd.setIdSSessione(sessione.getIdSSessione());
			itemToAdd.setCodifica(item.getCodifica());
			itemToAdd.setTipo("V008");
			itemToAdd.setQuantita(item.getNum());
			itemToAdd.setOrdinamento(item.getOrd());
			if( item.getNum() > 0 && numeroPraticheEsaminate.intValue() > 0)
				itemToAdd.setMediaGg(new Double(item.getNum()).doubleValue() / numeroPraticheEsaminate.doubleValue() );
			
			if (item.getNc() != null) itemToAdd.setNc(item.getNc());
			listAuSTempi.add(itemToAdd);
			
		}
		return listAuSTempi;
	}
	
	public List<AuSTesito> getSTEsito(List<RiepilogoTipologica> riepilogoTipologica, Integer numeroPraticheEsaminate){
		List<AuSTesito> listAuSTesito = new ArrayList<AuSTesito>();
		
		for (RiepilogoTipologica item : riepilogoTipologica) {
			AuSTesito itemToAdd = new AuSTesito();
		
			itemToAdd.setIdSSessione(sessione.getIdSSessione());
			itemToAdd.setTipoDifesa(item.getCodifica());
			itemToAdd.setCodChiusuraCorretto(item.getCodifica2());
			itemToAdd.setQuantita(item.getNum()); // Quantità
			Integer num = item.getNum();
			itemToAdd.setPercQuantita(num.doubleValue() / numeroPraticheEsaminate.doubleValue()); // % Quantita
			itemToAdd.setNumPrestazioni(item.getNumPrestazioni()); // Numero prestazioni
			itemToAdd.setImportoPrestazione(item.getImportoPrestazione()); // item.getImportoPrestazione();
			itemToAdd.setSpeseLegali(item.getSpeseLegali()); // Spese legali
			itemToAdd.setDataInserimento(new Date());
			itemToAdd.setSpeseLegaliCtu(item.getSpeseCtu());
			listAuSTesito.add(itemToAdd);
		}
		
		return listAuSTesito;
	}
	
	/*
	private List<String> getTipoloficaByTipo(String tipo){
		List<String> retMap = new ArrayList<String>();
		if(tipo == null || tipo.trim().length() == 0 ) return retMap;
		for (AuTplTipologiche itemTipologica : tipologica) {
			if ( itemTipologica.getTipo().trim().toUpperCase().equals(tipo.trim().toUpperCase()))
					retMap.add(itemTipologica.getCodifica());
		}
		return retMap;
	}
	*/
}
