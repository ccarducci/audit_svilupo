package it.tecnet.crs.componenti.service;

import it.tecnet.crs.jpa.model.CrsAssProcessoClasse;
import it.tecnet.crs.jpa.model.CrsAssSottoprocessoClasse;
import it.tecnet.crs.mod.web.dto.DocMediaAttCompDto;
import it.tecnet.crs.componenti.jpa.model.CrsComponenteTecnico;
import it.tecnet.crs.componenti.jpa.model.CrsDescrizioneTipo;
import it.tecnet.crs.componenti.jpa.model.CrsDocumentiMedia;
import it.tecnet.crs.componenti.jpa.model.CrsDomini;
import it.tecnet.crs.componenti.jpa.model.CrsTplCompTecnico;
import it.tecnet.crs.componenti.jpa.model.CrsTplDocMedia;
import it.tecnet.crs.componenti.jpa.model.CrsDominiValori;
import it.tecnet.crs.componenti.jpa.model.CrsTplEnteEmittente;
import it.tecnet.crs.componenti.jpa.model.CrsTplTipoLegge;
import it.tecnet.crs.componenti.web.bean.CircolariInps;
import it.tecnet.crs.componenti.web.bean.DatiTipoNormativa;
import it.tecnet.crs.componenti.web.bean.DominiValoriPaginator;
import it.tecnet.crs.componenti.web.bean.EnteEmittente;
import it.tecnet.crs.componenti.web.bean.LeggiDecreti;
import it.tecnet.crs.componenti.web.bean.MessaggiInps;
import it.tecnet.crs.componenti.web.bean.NormativaTablePaginator;
import it.tecnet.crs.componenti.web.bean.NoteDecreti;
import it.tecnet.crs.componenti.web.bean.TipoLegge;
import it.tecnet.crs.componenti.web.bean.TipoNormativa;
import it.tecnet.crs.componenti.web.dto.CrsCompTecnicoDto;
import it.tecnet.crs.componenti.web.dto.CrsDocumentiAllegatiDto;

import java.util.List;



public interface GestioneComponentiService {
	
	
	
	/*
	 * 		NORMATIVA
	 * */
	public void saveNormativa(DatiTipoNormativa normativa);
	
	public void deleteNormativa(long idNormativa, long idTipo);
	
	public List<CrsDescrizioneTipo> getListaNormativaLabel(long idTipo);
	
//	public List<DatiTipoNormativa> getListaNormative(NormativaTablePaginator filter);
//	
//	public Integer countAllNormative(NormativaTablePaginator filter);

	public List<TipoNormativa> getComboTipoNormativa(long idClasse);
	
	public DatiTipoNormativa getNormativaById(Long idDatiTipo);

	public CrsAssProcessoClasse getCrsAssProcByIdDatiTipo(long idNormativa, long idTipo);

	public CrsAssSottoprocessoClasse getCrsAssSottProcByIdDatiTipo(long idNormativa, long idTipo);

	public List<CrsDocumentiMedia> getListDocMedia(Integer pageNumber,Integer pageSize, int columnNameToOrder, String orderType,String search);

	public Integer countListDocMedia(String getsSearch);
	
	public List<CrsDocumentiMedia> getListDocMediaFiltro(Integer pageNumber,Integer pageSize, int columnNameToOrder, String orderType,String search);

	public Integer countListDocMediaFiltro(String getsSearch);
	
	public Boolean checkCodEsistente(String codice);
	
	public List<CrsDomini> getListDomini(Integer pageNumber,Integer pageSize, int columnNameToOrder, String orderType,String search);
	
	public Integer countListDomini(String getsSearch);
	
	public List<CrsDocumentiAllegatiDto> getAllegatiDominio(long idDominio,Integer pageNumber,Integer pageSize, int columnNameToOrder, String orderType,String search);
	
	public Integer countListAllegatiDominio(long idDominio,String getsSearch);
	
	public List<CrsCompTecnicoDto> getListCompTecnico(Integer pageNumber,Integer pageSize, int columnNameToOrder, String orderType,String search);

	public Integer countListCompTecnico(String search);
	
	public Boolean checkCodEsistenteCompTecnico(String codice);
	
	public List<CrsCompTecnicoDto> getListCompTecnicoStorico(Integer pageNumber,Integer pageSize, int columnNameToOrder, String orderType,String search);

	public Integer countListCompTecnicoStorico(String search);
	
	public List<CrsDocumentiAllegatiDto> getAllegatiCt(long idCt,Integer pageNumber,Integer pageSize, int columnNameToOrder, String orderType,String search);

	public Integer countListAllegatiCt(long idCT, String search);
	
	public List<CrsDocumentiMedia> getNonAllegatiCompTec(long idCt,Integer pageNumber,Integer pageSize, int columnNameToOrder, String orderType,String search);
	public List<CrsTplDocMedia> getTplDocMedia();
	public Integer countListNonAllegatiCompTec(long idCt, String getsSearch);



	


	
	public List<CircolariInps> getCircolariInpsTable(NormativaTablePaginator filter);
	public Integer countAllCircolariInpsTable(NormativaTablePaginator filter);
	
	public List<NoteDecreti> getNoteDecretiTable(NormativaTablePaginator filter);
	public Integer countAllNoteDecretiTable(NormativaTablePaginator filter);
	public Boolean checkCodEsistenteDominio(String codice);
	public List<CrsDomini> getListDominiFiltro(Integer pageNumber,Integer pageSize, int columnNameToOrder, String orderType,String search);
	public Integer countListDominiFiltro(String getsSearch);
	public List<CrsDocumentiMedia> getNonAllegatiDominio(long idDominio,Integer pageNumber,Integer pageSize, int columnNameToOrder, String orderType,String search);
	public Integer countListNonAllegatiDominio(long idDominio, String search);
	public List<MessaggiInps> getMessaggiInpsTable(NormativaTablePaginator filter);
	public Integer countAllMessaggiInpsTable(NormativaTablePaginator filter);
	
	public List<LeggiDecreti> getLeggiDecretiTable(NormativaTablePaginator filter);
	public Integer countAllLeggiDecretiTable(NormativaTablePaginator filter);
	
	

	public List<CrsDocumentiAllegatiDto> getAllegatiCircolariInpsTable(NormativaTablePaginator filter);
	public Integer countAllAllegatiCircolariInpsTable(NormativaTablePaginator filter);
	public List<CrsDocumentiAllegatiDto> getAllegatiNoteDecretiTable(NormativaTablePaginator filter);
	public Integer countAllAllegatiNoteDecretiTable(NormativaTablePaginator filter);
	public List<CrsDocumentiAllegatiDto> getAllegatiMessaggiInpsTable(NormativaTablePaginator filter);
	public Integer countAllAllegatiMessaggiInpsTable(NormativaTablePaginator filter);
	public List<CrsDocumentiAllegatiDto> getAllegatiLeggiDecretiTable(NormativaTablePaginator filter);
	public Integer countAllAllegatiLeggiDecretiTable(NormativaTablePaginator filter);
	
	public List<CrsDocumentiMedia> getNonAllegatiCircolariInps(NormativaTablePaginator filter);
	public Integer countListNonAllegatiCircolariInps(NormativaTablePaginator filter);
	public List<CrsDocumentiMedia> getNonAllegatiNoteDecreti(NormativaTablePaginator filter);
	public Integer countListNonAllegatiNoteDecreti(NormativaTablePaginator filter);
	public List<CrsDocumentiMedia> getNonAllegatiMessaggiInps(NormativaTablePaginator filter);
	public Integer countListNonAllegatiMessaggiInps(NormativaTablePaginator filter);
	public List<CrsDocumentiMedia> getNonAllegatiLeggiDecreti(NormativaTablePaginator filter);
	public Integer countListNonAllegatiLeggiDecreti(NormativaTablePaginator filter);
	
	
	public List<EnteEmittente> getComboEnteEmittente();
	public List<TipoLegge> getComboTipoLegge();

	public List<CrsDominiValori> getDominiValoriTable(DominiValoriPaginator filter);
	public Integer countAllDominiValoriTable(DominiValoriPaginator filter);
	public Boolean checkCodEsistenteDominiValori(String codice);

	
	//op comuni
	public <T> T salva(T obj) ;
	
	public <T> T cerca(Class<T> obj , Object pk);

	public void remove(Object o);

	public  CrsTplEnteEmittente cercaEmittenteByCodice(String enteEmittente);

	public CrsTplTipoLegge cercaLeggeByCodice(String tipoLegge);

	public List<CrsTplCompTecnico> getTplCompTec();


	






	

	
	


	


	

	
	
	
	
}
