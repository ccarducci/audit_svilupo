package it.tecnet.crs.ATPO.auditors.service;

import java.util.Date;
import java.util.List;

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
import it.tecnet.crs.ATPO.auditors.web.dto.PraticheAtpoDto;
import it.tecnet.crs.web.dto.AuTplTipologicheDto;
import it.tecnet.crs.web.dto.RischiPraticheDto;

public interface AtpoFasiService {

	//FASI
	public AtpoFaseAcquisizioneIstanza getFaseAcquisizioneIstanza(long idFaseDati) ;

	public AtpoFaseGestioneIstruttoria getFaseGestioneIstruttoria(long idFaseDati);

	public AtpoFaseAutotutelaResistenzaGiudizio getFaseAutotutelaResistenzaGiudizio(long idFaseDati);
	
	public AtpoFasePeritale getFasePeritale(long idFaseDati);
	
	public AtpoFasePostPeritale getFasePostPeritale(long idFaseDati);
	
	public AtpoFaseEsecuzioneProvvedimenti getEsecuzioneProvvedimenti(long idFaseDati);
	
	
	
	public AtpoRiepilogoFascicolo getFascicolo(long idFaseDati);

	public List<AtpoDettaglioFascicoloDto> getTabDocMancante(long idFascicolo,
			Integer pageNumber, Integer pageSize, Integer columnNameToOrder,
			String orderType, String textSearch);
	
	public int countTabDocMancante(long idFascicolo,
			Integer pageNumber, Integer pageSize, Integer columnNameToOrder,
			String orderType, String textSearch);
	

	public AtpoDettaglioFascicolo checkDocMancante(long idRiepilogoFascicolo,
			String codifica);

	/*
	 * NEW TIPOLOGICA
	 */
	List<AuTplTipologicheAtpoDto> getTipologicaAtpo(String tipo);
	
	public AuTplTipologicheAtpoDto getDescrTipologicaByCodifica(String tipo, String codifica);

	//TOPOLOGICHE
	public List<AtpoTipologicheDto> getVoceTotalitario();

	public List<AtpoTipologicheDto> getSiNo();

	public List<AtpoTipologicheDto> getTipologiaRicorso();

	public List<AtpoTipologicheDto> getTerminiPrimaUdienza();

	public List<AtpoTipologicheDto> getParereAutotutela();

	public List<AtpoTipologicheDto> getValoriGI();

	public List<AtpoTipologicheDto> getCorrispondenzaIstanzaAtp();

	public List<AtpoTipologicheDto> getVerificaCorrettezza();
	
	public List<AtpoTipologicheDto> getInfoOperazioniPeritali();
	
	public List<AtpoTipologicheDto> getSiNoNonRilevabile();
	
	public List<AtpoTipologicheDto> getAtpoParereBozzaCTU();
	
	public List<AtpoTipologicheDto> getOptionsCodChiusura();
	
	public List<AtpoTipologicheDto> getCorrispDecrOMGeCtuDef();
	
	public List<AtpoTipologicheDto> getCodPagamentoSpeseLegaliINPS();
	
	public List<AtpoTipologicheDto> getOptionsRecDatiPratica();
	
	public List<AtpoTipologicheDto> getOpzCondPagSpeseLegali();
	
	public List<AtpoTipologicheDto> getOpzSoggRichPagamento();
	
	public List<AtpoTipologicheDto> getFascicoliElettroniciEcartacei();
	
	public List<AtpoTipologicheDto> getDettDocMancate();
	
	public AtpoPraticheSISCO getPraticheSisco(String fascicolo, String codSede);
	
	//OPERAIOZNI COMUNI
	public <T> T salva(T obj) ;
	
	public <T> T cerca(Class<T> obj , Object pk);

	public void remove(Object o);

	public Date getDateSessione(Long idSessione);
}
