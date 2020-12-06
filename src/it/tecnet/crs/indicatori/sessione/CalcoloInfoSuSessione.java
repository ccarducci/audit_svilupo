package it.tecnet.crs.indicatori.sessione;

import it.tecnet.crs.jpa.model.AuInccDes;
import it.tecnet.crs.jpa.model.AuSNonconf;
import it.tecnet.crs.jpa.model.AuSSessione;
import it.tecnet.crs.util.SessioneUtil;

import java.util.ArrayList;
import java.util.List;

public class CalcoloInfoSuSessione {

	@SuppressWarnings("unused")
	private Double totalePesiValoreAssoluto;
	private List<AuSNonconf> listAuSNonconf;
	private List<AuInccDes> listAuInccDes;
	private AuSSessione sessione;
	private List<String> listAtpoFasePeritale;
	private int numDissensiAmm = 0;
	private int numDissensiSan = 0;
	
	public CalcoloInfoSuSessione(Double totalePesiValoreAssoluto,
			List<AuSNonconf> listAuSNonconf, List<AuInccDes> listAuInccDes,
			AuSSessione sessione, List<String> listAtpoFasePeritale) {
		this.totalePesiValoreAssoluto = totalePesiValoreAssoluto;
		this.listAuSNonconf = listAuSNonconf;
		this.listAuInccDes = listAuInccDes;
		this.sessione = sessione;
		this.listAtpoFasePeritale = listAtpoFasePeritale;
	}

	public void calcoloInfoSuSessione(){
		@SuppressWarnings("unused")
		Double inccPesatoPerNonConf = getValorePesatoNonConf();
		
		double Minimo = 0d;
		double Massimo = 0d;
		double Media = 0d;
		//float DevStandard = 0f;
		double incc_valore_pesato = 0d;
		double inccTotale = 0D;
		String inccDescrittivo;
		String statoPratiche;
		
		boolean isFirst = true;
		boolean praticheSoggette = true;
		
		for (AuSNonconf auSNonConf : listAuSNonconf) {
			// Minimo = il valore minimo tra i valori INCC da S-NonConf
			// Massimo = il valore massimo tra i valori INCC da S-NonConf
			if (isFirst){
				isFirst = false;
				Minimo = auSNonConf.getValoreIncc();
				Massimo = auSNonConf.getValoreIncc();
			}else{
				
				if (Minimo > auSNonConf.getMinimo())Minimo = auSNonConf.getValoreIncc();
				if (Massimo < auSNonConf.getMassimo())Massimo = auSNonConf.getValoreIncc();
			}
			
			// INCC = somma Valore pesato da tabella S-NonCconf
			if(auSNonConf.getValorePesato() !=null){
				incc_valore_pesato += auSNonConf.getValorePesato();
				//System.out.println(auSNonConf.getIdSNonconf() + " - " +  auSNonConf.getValorePesato());
			}
			
			if (auSNonConf.getValoreIncc() != null)inccTotale +=  auSNonConf.getValoreIncc();
			
			if ( auSNonConf.getPesoNonconf().doubleValue() > 0 )praticheSoggette = false;
		}
		
		// Media = il valore medio tra i valori INCC da S-NonConf
		if (inccTotale > 0 && listAuSNonconf.size() > 0 )Media = inccTotale / listAuSNonconf.size();
		
		// INCC Descrittivo = si ricava sulla base del INCC dalla tabella M-INCC (per <=)
		inccDescrittivo = getInccDescrittivo( incc_valore_pesato );
		
		// Stato pratiche
		if ( praticheSoggette ) {
			statoPratiche = "non ci sono pratiche soggette";
		}else {
			statoPratiche = "";
		}
		
		// Dev. standard = tra i valori INCC da S-NonConf
		double varianzaStandard = 0d;
		if ( Media > 0) {
			varianzaStandard = getVarianza(listAuSNonconf, Media);
		}else {
			varianzaStandard = 0d; 
		}
		
		getNumDissensi();
		
		sessione.setNumDissensiAmm(numDissensiAmm);
		sessione.setNumDissensiSan(numDissensiSan);
		
		sessione.setMinimo(Minimo);
		sessione.setMassimo(Massimo);
		sessione.setMedia(Media);
		sessione.setDevStandard(varianzaStandard);
		sessione.setIncc(incc_valore_pesato);
		sessione.setInccDescrizione(inccDescrittivo);
		sessione.setStatoPratiche(statoPratiche);
		
	}

	private void getNumDissensi() {
		if (listAtpoFasePeritale == null) return;
		for (String item : listAtpoFasePeritale) {
			
			if (item != null){
				if ("1".equals(item))
					numDissensiAmm++;
				
				if ("2".equals(item))
					numDissensiSan++;
			}
		}
		
	}

	private String getInccDescrittivo(Double valueIncc){
		return SessioneUtil.getInccDescrittivo(valueIncc, listAuInccDes);
	}
	
	private Double getValorePesatoNonConf(){
		Double ret = 0D;
		for (AuSNonconf item : listAuSNonconf) {
			if(item.getPesoNonconf() != null)ret += item.getPesoNonconf();
		}
		return ret; 
	}
	
	private double getVarianza(List<AuSNonconf> valori, double media) {
		Double varianzaStandard = 0d;
		List<Double> listaVarianza = new ArrayList<Double>();
		for (AuSNonconf item : valori) {
			if ( item.getValoreIncc().doubleValue() > 0) {
				if ( item.getValoreIncc().doubleValue() >= media) {
					Double varianza = item.getValoreIncc().doubleValue() - media;
					Double quadratoVarianza = Math.pow(varianza.doubleValue(), 2);
					listaVarianza.add(quadratoVarianza);
				}
				else {
					Double varianza = media - item.getValoreIncc().doubleValue();
					Double quadratoVarianza = Math.pow(varianza.doubleValue(), 2);
					listaVarianza.add(quadratoVarianza);
				}
			}else {
				listaVarianza.add(0D);
			}
		}
		for (Double varianza : listaVarianza) {
			varianzaStandard +=varianza;
		}
		
		return varianzaStandard;
	}
}
