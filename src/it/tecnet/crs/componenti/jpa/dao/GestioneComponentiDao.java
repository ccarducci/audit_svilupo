package it.tecnet.crs.componenti.jpa.dao;

import it.tecnet.crs.componenti.jpa.model.CrsDatiTipo;
import it.tecnet.crs.componenti.jpa.model.CrsDescrizioneTipo;
import it.tecnet.crs.componenti.jpa.model.CrsDominiValori;
import it.tecnet.crs.componenti.jpa.model.CrsTipo;
import it.tecnet.crs.componenti.jpa.model.CrsTplCompTecnico;
import it.tecnet.crs.componenti.jpa.model.CrsTplDocMedia;
import it.tecnet.crs.componenti.web.bean.DominiValoriPaginator;
import it.tecnet.crs.componenti.web.bean.EnteEmittente;
import it.tecnet.crs.componenti.web.bean.NormativaTablePaginator;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;



public interface GestioneComponentiDao {


	/*
	 * 		NORMATIVA
	 * */


	public List<CrsDescrizioneTipo> getListaNormativaLabel(long idTipo);

	@Transactional
	public void saveNormativa(CrsDatiTipo datiTipo);

	//	public void deleteNormativa(long idDatiTipo);

	//	public List<Object[]> getListaNormative(NormativaTablePaginator filter);
	//	
	//	public Integer countAllNormative(NormativaTablePaginator filter);

	public List<CrsTipo> getComboTipoNormativa(long idClasse);

	public CrsDatiTipo getNormativaById(Long idDatiTipo);

	public List<Object[]> getCrsAssProcByIdDatiTipo(long idNormativa, long idTipo);

	public List<Object[]> getCrsAssSottProcByIdDatiTipo(long idNormativa, long idTipo);

	public List<Object[]> getListDocMedia(Integer pageNumber, Integer pageSize,
			int columnNameToOrder, String orderType, String search);

	public Integer countListDocMedia(String search);

	public List<Object[]> getListDocMediaFiltro(Integer pageNumber, Integer pageSize,int columnNameToOrder, String orderType, String search);

	public Integer countListDocMediaFiltro(String getsSearch);

	public List<CrsTplDocMedia> getTplDocMedia();

	public Object[] checkCodiceEsistente(String codice);





	public List<Object[]> getCircolariInpsTable(NormativaTablePaginator filter);
	public Integer countAllCircolariInpsTable(NormativaTablePaginator filter);

	public List<Object[]> getNoteDecretiTable(NormativaTablePaginator filter);
	public Integer countAllNoteDecretiTable(NormativaTablePaginator filter);

	public Integer countDomini(String getsSearch);

	public List<Object[]> getListDomini(Integer pageNumber, Integer pageSize,
			int columnNameToOrder, String orderType, String search);

	public Object[] checkCodiceEsistenteDominio(String codice);


	public List<Object[]> getListDominiFiltro(Integer pageNumber,
			Integer pageSize, int columnNameToOrder, String orderType,
			String search);

	public Integer countDominiFiltro(String getsSearch);

	public List<Object[]> getAllegatiDominio(long idDominio,Integer pageNumber,
			Integer pageSize, int columnNameToOrder, String orderType,
			String search);

	public Integer countListAllegatiDominio(long idDominio,String getsSearch);

	public List<Object[]> getNonAllegatiDominio(long idDominio,
			Integer pageNumber, Integer pageSize, int columnNameToOrder,
			String orderType, String search);

	public Integer countListNonAllegatiDominio(long idDominio, String search);

	public Integer countListCompTecnico(String search);
	
	
	// COMPONENTE TECNICO
	public List<Object[]> getListCompTecnico(Integer pageNumber,
			Integer pageSize, int columnNameToOrder, String orderType,
			String search);

	public Object[] checkCodEsistenteCompTecnico(String codice);

	public Integer countListCompTecnicoStorico(String search);

	public List<Object[]> getListCompTecnicoStorico(Integer pageNumber,
			Integer pageSize, int columnNameToOrder, String orderType,
			String search);

	public List<Object[]> getAllegatiCt(long idCt, Integer pageNumber,
			Integer pageSize, int columnNameToOrder, String orderType,
			String search);

	public Integer countListAllegatiCt(long idCT, String search);
	
	public List<Object[]> getNonAllegatiCompTec(long idCt, Integer pageNumber,
			Integer pageSize, int columnNameToOrder, String orderType,
			String search);

	public Integer countListNonAllegatiCompTec(long idCt, String search);

	




	//	public List<Object[]> getMessaggiInpsTable(NormativaTablePaginator filter);
	//	public Integer countAllMessaggiInpsTable(NormativaTablePaginator filter);
	//	
	//	public List<Object[]> getLeggiDecretiTable(NormativaTablePaginator filter);
	//	public Integer countAllLeggiDecretiTable(NormativaTablePaginator filter);
	public List<Object[]> getMessaggiInpsTable(NormativaTablePaginator filter);
	public Integer countAllMessaggiInpsTable(NormativaTablePaginator filter);

	public List<Object[]> getLeggiDecretiTable(NormativaTablePaginator filter);
	public Integer countAllLeggiDecretiTable(NormativaTablePaginator filter);

	public void deleteCircolariInps(long idCircolariInps);
	public void deleteNoteDecreti(long idNoteDecreti);
	public void deleteMessaggiInps(long idMessaggiInps);
	public void deleteLeggiDecreti(long idLeggiDecreti);
	
	
	public List<Object[]> getAllegatiCircolariInpsTable(NormativaTablePaginator filter);
	public Integer countAllAllegatiCircolariInpsTable(NormativaTablePaginator filter);
	public List<Object[]> getAllegatiNoteDecretiTable(NormativaTablePaginator filter);
	public Integer countAllAllegatiNoteDecretiTable(NormativaTablePaginator filter);
	public List<Object[]> getAllegatiMessaggiInpsTable(NormativaTablePaginator filter);
	public Integer countAllAllegatiMessaggiInpsTable(NormativaTablePaginator filter);
	public List<Object[]> getAllegatiLeggiDecretiTable(NormativaTablePaginator filter);
	public Integer countAllAllegatiLeggiDecretiTable(NormativaTablePaginator filter);
	
	public List<Object[]> getNonAllegatiCircolariInps(NormativaTablePaginator filter);
	public Integer countListNonAllegatiCircolariInps(NormativaTablePaginator filter);
	public List<Object[]> getNonAllegatiNoteDecreti(NormativaTablePaginator filter);
	public Integer countListNonAllegatiNoteDecreti(NormativaTablePaginator filter);
	public List<Object[]> getNonAllegatiMessaggiInps(NormativaTablePaginator filter);
	public Integer countListNonAllegatiMessaggiInps(NormativaTablePaginator filter);
	public List<Object[]> getNonAllegatiLeggiDecreti(NormativaTablePaginator filter);
	public Integer countListNonAllegatiLeggiDecreti(NormativaTablePaginator filter);
	
	public List<Object[]> getComboEnteEmittente();
	public List<Object[]> getComboTipoLegge();
	
	public List<Object[]> getDominiValoriTable(DominiValoriPaginator filter);
	public Integer countAllDominiValoriTable(DominiValoriPaginator filter);
	public Object[] checkCodiceEsistenteDominiValori(String codice);

	//op comuni
	public <T> T save(T entity);

	public <T> T cerca(Class<T> obj, Object pk);

	public void remove(Object o);

	public Object[] cercaEmittenteByCodice(String enteEmittente);

	public Object[] cercaLeggeByCodice(String tipoLegge);

	public List<CrsTplCompTecnico> getTplCompTec();













}
