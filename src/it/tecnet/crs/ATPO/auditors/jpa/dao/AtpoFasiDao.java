package it.tecnet.crs.ATPO.auditors.jpa.dao;

import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoRiepilogoFascicolo;

import java.util.Date;
import java.util.List;

public interface AtpoFasiDao {

	
	//fasi
	public Object[] getFaseAcquisizioneIstanza(long idFaseDati) ;
	
	public Object[] getFaseGestioneIstruttoria(long idFaseDati);
	
	public Object[] getFaseAutotutelaResistenzaGiudizio(long idFaseDati);
	
	public Object[] getFasePeritale(long idFaseDati);
	
	public Object[] getFasePostPeritale(long idFaseDati);
	
	public Object[] getEsecuzioneProvvedimenti(long idFaseDati);

	
	
	public Object[] getFascicolo(long idFaseDati);
	public Object[] checkDocMancante(long idRiepilogoFascicolo, String codifica);
	
	//new tipologiche
	List<Object[]> getTipologicaAtpo(String tipo);
	public Object[] getDescrTipologicaByCodifica(String tipo, String codifica);
	
	
	//topologiche da cancellare
	public List<Object[]> getVoceTotalitario();
	
	public List<Object[]> getTipologiaRicorso();
	
	public List<Object[]> getTerminiPrimaUdienza();
	
	public List<Object[]> getParereAutotutela();
	
	public List<Object[]> getValoriGI();
	
	public List<Object[]> getCorrispondenzaIstanzaAtp();
	
	public List<Object[]> getVerificaCorrettezza();
	
	public List<Object[]> getSiNo();
	
	public List<Object[]> getSiNoNonRilevabile();
	
	public List<Object[]> getInfoOperazioniPeritali();
	
	public List<Object[]> getAtpoParereBozzaCTU();
	
	public List<Object[]> getOptionsCodChiusura();
	
	public List<Object[]> getCorrispDecrOMGeCtuDef();
	
	public List<Object[]> getCodPagamentoSpeseLegaliINPS();
	
	public List<Object[]> getOptionsRecDatiPratica();
	
	public List<Object[]> getOpzCondPagSpeseLegali();
	
	public List<Object[]> getOpzSoggRichPagamento();
	
	public List<Object[]> getFascicoliElettroniciEcartacei();
	
	public List<Object[]> getDettDocMancate();
	
	public Object[] getPraticheSisco(String fascicolo, String codSede);
	
	//op comuni
	public <T> T save(T entity);
	
	public <T> T cerca(Class<T> obj, Object pk);

	public List<Object[]> getTabDocMancante(long idFascicolo, Integer pageNumber,
			Integer pageSize, Integer columnNameToOrder, String orderType,
			String textSearch);

	public Integer countTabDocMancante(long idFascicolo, String textSearch);

	public void remove(Object o);

	public Date getDateSessione(Long idSessione);
	
}
