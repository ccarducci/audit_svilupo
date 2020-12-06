package it.tecnet.crs.ATPO.auditors.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import it.tecnet.crs.ATPO.auditors.jpa.dao.AtpoFasiDao;

import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoDettaglioFascicolo;
import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoFaseAcquisizioneIstanza;
import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoFaseAutotutelaResistenzaGiudizio;
import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoFaseEsecuzioneProvvedimenti;
import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoFaseGestioneIstruttoria;
import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoFasePeritale;
import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoFasePostPeritale;
import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoPraticheSISCO;

import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoRiepilogoFascicolo;
import it.tecnet.crs.ATPO.auditors.web.dto.AtpoDettaglioFascicoloDto;
import it.tecnet.crs.ATPO.auditors.web.dto.AtpoTipologicheDto;
import it.tecnet.crs.ATPO.auditors.web.dto.AuTplTipologicheAtpoDto;
import it.tecnet.crs.ATPO.util.ObjectToDto;
import it.tecnet.crs.util.ModelToDto;
import it.tecnet.crs.web.dto.AuTplTipologicheDto;

public class AtpoFasiServiceImpl implements AtpoFasiService {

	private AtpoFasiDao atpoFasiDao;
	
	//FASI
	
	public AtpoFaseAcquisizioneIstanza getFaseAcquisizioneIstanza(long idFaseDati) {
		 
		Object[] o = atpoFasiDao.getFaseAcquisizioneIstanza(idFaseDati);
		try{
			if(o!=null){
				AtpoFaseAcquisizioneIstanza a= ObjectToDto.objectToAtpoAcquisizioneIstanza(o);
				return a;
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public AtpoFaseGestioneIstruttoria getFaseGestioneIstruttoria(long idFaseDati){
		
		Object[] o=atpoFasiDao.getFaseGestioneIstruttoria(idFaseDati);
		try{
			if(o!=null){
				AtpoFaseGestioneIstruttoria a= ObjectToDto.objectToAtpoFaseGestioneIstruttoria(o);
				return a;
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null ;
	}
	
	public AtpoFaseAutotutelaResistenzaGiudizio getFaseAutotutelaResistenzaGiudizio(long idFaseDati){
		Object[] o=atpoFasiDao.getFaseAutotutelaResistenzaGiudizio(idFaseDati);
		try{
			if(o!=null){
				AtpoFaseAutotutelaResistenzaGiudizio a= ObjectToDto.objectToAtpoAutotutelaResGiud(o);
				return a;
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}	
	
	@Override
	public AtpoFasePeritale getFasePeritale(long idFaseDati) {
		Object[] o= atpoFasiDao.getFasePeritale(idFaseDati);
		try{
			if(o!=null){
				AtpoFasePeritale a= ObjectToDto.objectToAtpoFasePeritale(o);
				return a;
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null ;
	}
	
	@Override
	public AtpoFasePostPeritale getFasePostPeritale(long idFaseDati) {
		
		Object[] o= atpoFasiDao.getFasePostPeritale(idFaseDati);
		try{
			if(o!=null){
				AtpoFasePostPeritale a= ObjectToDto.objectToAtpoFasePostPeritaleA(o);
				return a;
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null ;
	}
	

	@Override
	public AtpoFaseEsecuzioneProvvedimenti getEsecuzioneProvvedimenti(long idFaseDati) {
		
		Object[] o= atpoFasiDao.getEsecuzioneProvvedimenti(idFaseDati);
		try{
			if(o!=null){
				AtpoFaseEsecuzioneProvvedimenti a= ObjectToDto.objectToAtpoFaseEsecuzioneProvvedimenti(o);
				return a;
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null ;
	}
	

	@Override
	public AtpoRiepilogoFascicolo getFascicolo(long idFaseDati) {
		// TODO Auto-generated method stub
	
		Object[] o= atpoFasiDao.getFascicolo(idFaseDati);
		try{
			if(o!=null){
				AtpoRiepilogoFascicolo a= ObjectToDto.objectToAtpoRiepFascicolo(o);
				return a;
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public AtpoPraticheSISCO getPraticheSisco(String fascicolo, String codSede) {
		Object[] o= atpoFasiDao.getPraticheSisco(fascicolo, codSede);
		try{
			if(o!=null){
				AtpoPraticheSISCO a= ObjectToDto.objectToAtpoPSisco(o);
				return a;
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null ;
	}

	@Override
	public List<AtpoDettaglioFascicoloDto> getTabDocMancante(long idFascicolo,Integer pageNumber, Integer pageSize, Integer columnNameToOrder,
			String orderType, String textSearch) {
		List<Object[]> o= atpoFasiDao.getTabDocMancante(idFascicolo, pageNumber,  pageSize,  columnNameToOrder, orderType,  textSearch);
		List<AtpoDettaglioFascicoloDto> list= new ArrayList<AtpoDettaglioFascicoloDto>();
		try{
			if(o!=null){
				for(int i=0; i< o.size(); i++){
					AtpoDettaglioFascicoloDto a= ObjectToDto.objectToDettMancDto(o.get(i));
					list.add(a);
				}
				
				return list;
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null ;
	}
	
	@Override
	public int countTabDocMancante(long idFascicolo, Integer pageNumber,
			Integer pageSize, Integer columnNameToOrder, String orderType,
			String textSearch) {
		Integer c= atpoFasiDao.countTabDocMancante(idFascicolo, textSearch);
		return c;
	
	}
	
	@Override
	public AtpoDettaglioFascicolo checkDocMancante(long idRiepilogoFascicolo,
			String codifica) {
		Object[] o= atpoFasiDao.checkDocMancante(idRiepilogoFascicolo, codifica);
		AtpoDettaglioFascicolo a=null;
		try{
			if(o!=null){
				
				a= ObjectToDto.objectToDettManc(o);
				
				return a;
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return a ;
	}
	
	/*
	 * 	NEW TIPOLOGICHE
	 */
	
	@Override
	public List<AuTplTipologicheAtpoDto> getTipologicaAtpo(String tipo) {
		List<Object[]> list =atpoFasiDao.getTipologicaAtpo(tipo);
		List<AuTplTipologicheAtpoDto> dto= new ArrayList<AuTplTipologicheAtpoDto>();
		for(Object[] obj:list){
			
			dto.add(new AuTplTipologicheAtpoDto((Long)obj[0],(String)obj[1],(String)obj[2],(String)obj[3]));
		}
		return dto;
	}
	
	@Override
	public AuTplTipologicheAtpoDto getDescrTipologicaByCodifica(String tipo, String codifica) {
		Object[] o =atpoFasiDao.getDescrTipologicaByCodifica(tipo,codifica);
		AuTplTipologicheAtpoDto t=null;
		if(o!=null){
			t=new AuTplTipologicheAtpoDto((Long)o[0],(String)o[1],(String)o[2],(String)o[3]);
		}
		
		return t;
	}




	//T O P O L O G I C H E DA CANCELLARE _____________________________________________________________
	
	public List<AtpoTipologicheDto> getVoceTotalitario(){
		List<Object[]> list =atpoFasiDao.getVoceTotalitario();
		List<AtpoTipologicheDto> dto= new ArrayList<AtpoTipologicheDto>();
		for(Object[] obj:list){
			dto.add(new AtpoTipologicheDto((Long)obj[0],(String)obj[1]));
		}
		return dto;
	}
	
	public List<AtpoTipologicheDto> getSiNo() {
		List<Object[]> list =atpoFasiDao.getSiNo();
		List<AtpoTipologicheDto> dto= new ArrayList<AtpoTipologicheDto>();
		for(Object[] obj:list){
			dto.add(new AtpoTipologicheDto((Long)obj[0],(String)obj[1]));
		}
		return dto;
	}
	

	

	@Override
	public List<AtpoTipologicheDto> getCorrispondenzaIstanzaAtp() {
		List<Object[]> list =atpoFasiDao.getCorrispondenzaIstanzaAtp();
		List<AtpoTipologicheDto> dto= new ArrayList<AtpoTipologicheDto>();
		for(Object[] obj:list){
			dto.add(new AtpoTipologicheDto((Long)obj[0],(String)obj[1]));
		}
		return dto;
	}

	@Override
	public List<AtpoTipologicheDto> getParereAutotutela() {
		List<Object[]> list =atpoFasiDao.getParereAutotutela();
		List<AtpoTipologicheDto> dto= new ArrayList<AtpoTipologicheDto>();
		for(Object[] obj:list){
			dto.add(new AtpoTipologicheDto((Long)obj[0],(String)obj[1]));
		}
		return dto;
	}

	@Override
	public List<AtpoTipologicheDto> getTerminiPrimaUdienza() {
		List<Object[]> list =atpoFasiDao.getTerminiPrimaUdienza();
		List<AtpoTipologicheDto> dto= new ArrayList<AtpoTipologicheDto>();
		for(Object[] obj:list){
			dto.add(new AtpoTipologicheDto((Long)obj[0],(String)obj[1]));
		}
		return dto;
	}

	@Override
	public List<AtpoTipologicheDto> getTipologiaRicorso() {
		List<Object[]> list =atpoFasiDao.getTipologiaRicorso();
		List<AtpoTipologicheDto> dto= new ArrayList<AtpoTipologicheDto>();
		for(Object[] obj:list){
			dto.add(new AtpoTipologicheDto((Long)obj[0],(String)obj[1]));
		}
		return dto;
	}

	@Override
	public List<AtpoTipologicheDto> getValoriGI() {
		List<Object[]> list =atpoFasiDao.getValoriGI();
		List<AtpoTipologicheDto> dto= new ArrayList<AtpoTipologicheDto>();
		for(Object[] obj:list){
			dto.add(new AtpoTipologicheDto((Long)obj[0],(String)obj[1]));
		}
		return dto;
	}

	@Override
	public List<AtpoTipologicheDto> getVerificaCorrettezza() {
		List<Object[]> list =atpoFasiDao.getVerificaCorrettezza();
		List<AtpoTipologicheDto> dto= new ArrayList<AtpoTipologicheDto>();
		for(Object[] obj:list){
			dto.add(new AtpoTipologicheDto((Long)obj[0],(String)obj[1]));
		}
		return dto;
	}
	
	@Override
	public List<AtpoTipologicheDto> getInfoOperazioniPeritali() {
		List<Object[]> list =atpoFasiDao.getInfoOperazioniPeritali();
		List<AtpoTipologicheDto> dto= new ArrayList<AtpoTipologicheDto>();
		for(Object[] obj:list){
			dto.add(new AtpoTipologicheDto((Long)obj[0],(String)obj[1]));
		}
		return dto;
	}


	@Override
	public List<AtpoTipologicheDto> getSiNoNonRilevabile() {
		List<Object[]> list =atpoFasiDao.getSiNoNonRilevabile();
		List<AtpoTipologicheDto> dto= new ArrayList<AtpoTipologicheDto>();
		for(Object[] obj:list){
			dto.add(new AtpoTipologicheDto((Long)obj[0],(String)obj[1]));
		}
		return dto;
	}

	@Override
	public List<AtpoTipologicheDto> getAtpoParereBozzaCTU() {
		List<Object[]> list =atpoFasiDao.getAtpoParereBozzaCTU();
		List<AtpoTipologicheDto> dto= new ArrayList<AtpoTipologicheDto>();
		for(Object[] obj:list){
			dto.add(new AtpoTipologicheDto((Long)obj[0],(String)obj[1]));
		}
		return dto;
	}
	
	@Override
	public List<AtpoTipologicheDto> getOptionsCodChiusura() {
		List<Object[]> list =atpoFasiDao.getOptionsCodChiusura();
		List<AtpoTipologicheDto> dto= new ArrayList<AtpoTipologicheDto>();
		for(Object[] obj:list){
			dto.add(new AtpoTipologicheDto((Long)obj[0],(String)obj[1]));
		}
		return dto;
	}
	
	@Override
	public List<AtpoTipologicheDto> getCorrispDecrOMGeCtuDef() {
		List<Object[]> list =atpoFasiDao.getCorrispDecrOMGeCtuDef();
		List<AtpoTipologicheDto> dto= new ArrayList<AtpoTipologicheDto>();
		for(Object[] obj:list){
			dto.add(new AtpoTipologicheDto((Long)obj[0],(String)obj[1]));
		}
		return dto;
	}
	
	@Override
	public List<AtpoTipologicheDto> getCodPagamentoSpeseLegaliINPS() {
		List<Object[]> list =atpoFasiDao.getCodPagamentoSpeseLegaliINPS();
		List<AtpoTipologicheDto> dto= new ArrayList<AtpoTipologicheDto>();
		for(Object[] obj:list){
			dto.add(new AtpoTipologicheDto((Long)obj[0],(String)obj[1]));
		}
		return dto;
	}
	
	@Override
	public List<AtpoTipologicheDto> getOptionsRecDatiPratica() {
		List<Object[]> list =atpoFasiDao.getOptionsRecDatiPratica();
		List<AtpoTipologicheDto> dto= new ArrayList<AtpoTipologicheDto>();
		for(Object[] obj:list){
			dto.add(new AtpoTipologicheDto((Long)obj[0],(String)obj[1]));
		}
		return dto;
	}

	@Override
	public List<AtpoTipologicheDto> getOpzCondPagSpeseLegali() {
		List<Object[]> list =atpoFasiDao.getOpzCondPagSpeseLegali();
		List<AtpoTipologicheDto> dto= new ArrayList<AtpoTipologicheDto>();
		for(Object[] obj:list){
			dto.add(new AtpoTipologicheDto((Long)obj[0],(String)obj[1]));
		}
		return dto;
	}

	@Override
	public List<AtpoTipologicheDto> getOpzSoggRichPagamento() {
		List<Object[]> list =atpoFasiDao.getOpzSoggRichPagamento();
		List<AtpoTipologicheDto> dto= new ArrayList<AtpoTipologicheDto>();
		for(Object[] obj:list){
			dto.add(new AtpoTipologicheDto((Long)obj[0],(String)obj[1]));
		}
		return dto;
	}

	@Override
	public List<AtpoTipologicheDto> getFascicoliElettroniciEcartacei() {
		List<Object[]> list =atpoFasiDao.getFascicoliElettroniciEcartacei();
		List<AtpoTipologicheDto> dto= new ArrayList<AtpoTipologicheDto>();
		for(Object[] obj:list){
			dto.add(new AtpoTipologicheDto((Long)obj[0],(String)obj[1]));
		}
		return dto;
	}


	@Override
	public List<AtpoTipologicheDto> getDettDocMancate() {
		List<Object[]> list =atpoFasiDao.getDettDocMancate();
		List<AtpoTipologicheDto> dto= new ArrayList<AtpoTipologicheDto>();
		for(Object[] obj:list){
			dto.add(new AtpoTipologicheDto((Long)obj[0],(String)obj[1]));
		}
		return dto;
	}
	
	
	//OP C O M U N I______________________________________________
	@Override
	public <T> T cerca(Class<T> obj, Object pk) {
	
		return atpoFasiDao.cerca(obj , pk);
	}

	@Override
	public <T> T salva(T obj) {
		return atpoFasiDao.save(obj);
		
	}

	public AtpoFasiDao getAtpoFasiDao() {
		return atpoFasiDao;
	}


	public void setAtpoFasiDao(AtpoFasiDao atpoFasiDao) {
		this.atpoFasiDao = atpoFasiDao;
	}

	@Override
	public void remove(Object o) {
		atpoFasiDao.remove(o);
		
	}

	@Override
	public Date getDateSessione(Long idSessione) {
		return atpoFasiDao.getDateSessione(idSessione);
	}


	
	

	
	

	


}
