package it.tecnet.crs.componenti.service;

import it.tecnet.crs.ATPO.auditors.web.dto.TipologiaPraticheCampioneDto;
import it.tecnet.crs.componenti.jpa.dao.GestioneComponentiDao;
import it.tecnet.crs.jpa.model.CrsAssProcessoClasse;
import it.tecnet.crs.componenti.jpa.model.CrsComponenteTecnico;
import it.tecnet.crs.componenti.jpa.model.CrsDatiTipo;
import it.tecnet.crs.componenti.jpa.model.CrsDescrizioneTipo;
import it.tecnet.crs.componenti.jpa.model.CrsDocumentiMedia;
import it.tecnet.crs.componenti.jpa.model.CrsDomini;
import it.tecnet.crs.componenti.jpa.model.CrsDominiValori;
import it.tecnet.crs.componenti.jpa.model.CrsTipo;
import it.tecnet.crs.componenti.jpa.model.CrsTplCompTecnico;
import it.tecnet.crs.componenti.jpa.model.CrsTplDocMedia;
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
import it.tecnet.crs.jpa.model.CrsAssSottoprocessoClasse;
import it.tecnet.crs.util.BeanToModel;
import it.tecnet.crs.util.ModelToBean;
import it.tecnet.crs.util.TipoNormativaEnum;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;


public class GestioneComponentiServiceImpl implements GestioneComponentiService {
	
	private GestioneComponentiDao gestioneComponentiDao;
	

	public void setGestioneComponentiDao(GestioneComponentiDao gestioneComponentiDao) {
		this.gestioneComponentiDao = gestioneComponentiDao;
	}

	public GestioneComponentiDao getGestioneComponentiDao() {
		return gestioneComponentiDao;
	}

/**
 ***************************************************
 *************	SEZIONE NORMATIVA	****************
 ***************************************************	
 **/
	
	public List<CrsDescrizioneTipo> getListaNormativaLabel(long idTipo){
		return gestioneComponentiDao.getListaNormativaLabel(idTipo);
	}
	
	
//	public List<DatiTipoNormativa> getListaNormative(NormativaTablePaginator filter){
//		
//		List<Object[]> lista = null;
//		List<DatiTipoNormativa> listaReturn = new ArrayList<DatiTipoNormativa>();
//		
//		lista=gestioneComponentiDao.getListaNormative(filter);
//		
//		for(Object[] obj : lista){	
//			DatiTipoNormativa objectBean = ModelToBean.modelToDatiTipoNormativaBean(obj);
//			listaReturn.add(objectBean);
//		}
//		
//		return listaReturn;	
//	}
//	
//	public Integer countAllNormative(NormativaTablePaginator filter){
//		return gestioneComponentiDao.countAllNormative(filter);
//	}
	
	
	public List<TipoNormativa> getComboTipoNormativa(long idClasse){
		List<CrsTipo> lista = null;
		List<TipoNormativa> listaBean=new ArrayList<TipoNormativa>();
		
		lista = gestioneComponentiDao.getComboTipoNormativa(idClasse);
		
		for(CrsTipo crsTipo : lista){	
			TipoNormativa tipoNormativa = ModelToBean.modelToBean(crsTipo);
			listaBean.add(tipoNormativa);
		}
		
		return listaBean;	
	}
	
	
	
	@Transactional
	public void saveNormativa(DatiTipoNormativa normativa){
		
		CrsDatiTipo normativaJpa = BeanToModel.beanToModel(normativa);
		gestioneComponentiDao.saveNormativa(normativaJpa);
	}
	
	public void deleteNormativa(long idNormativa, long idTipo){
		
		// si elimina l'entità in base al tipo normativa
		if(new Long(idTipo).intValue() == TipoNormativaEnum.CIRCOLARI_INPS.getCodice()){
			
			gestioneComponentiDao.deleteCircolariInps(idNormativa);
			
		}
		if(new Long(idTipo).intValue() == TipoNormativaEnum.NOTE_DESCRETI.getCodice()){
			
			gestioneComponentiDao.deleteNoteDecreti(idNormativa);
			
		}
		if(new Long(idTipo).intValue() == TipoNormativaEnum.MESSAGGI_INPS.getCodice()){
			
			gestioneComponentiDao.deleteMessaggiInps(idNormativa);
			
		}
		if(new Long(idTipo).intValue() == TipoNormativaEnum.LEGGI_DECRETI.getCodice()){
	
			gestioneComponentiDao.deleteLeggiDecreti(idNormativa);
	
		}
		
	}
	
	
	@Override
	public DatiTipoNormativa getNormativaById(Long idDatiTipo) {
		
		CrsDatiTipo modelJpa = new CrsDatiTipo();
		DatiTipoNormativa bean = new DatiTipoNormativa();
		
		modelJpa = gestioneComponentiDao.getNormativaById(idDatiTipo);
		
		bean = ModelToBean.modelToBean(modelJpa);
		
		return bean;
	}

	@Override
	public CrsAssProcessoClasse getCrsAssProcByIdDatiTipo(long idNormativa, long idTipo) {
		List<CrsAssProcessoClasse> l=new ArrayList<CrsAssProcessoClasse>();

		List<Object[]> obj = gestioneComponentiDao.getCrsAssProcByIdDatiTipo(idNormativa, idTipo) ;

		if(obj !=null){
			for(int i=0; i<obj.size();i++){
				CrsAssProcessoClasse t= new CrsAssProcessoClasse();
				t.setIdAssProcessoClasse((Long)obj.get(i)[0]);
				t.setIdProcesso((Long)obj.get(i)[1]);
				t.setIdNormativa((Long)obj.get(i)[2]);
				t.setIdTipo((Long)obj.get(i)[3]);
				l.add(t);
				//basta sapere che almeno un record è associato alla normativa
				return l.get(0);
			}
		}
		return null;
	}

	@Override
	public CrsAssSottoprocessoClasse getCrsAssSottProcByIdDatiTipo(long idNormativa, long idTipo) {
		List<CrsAssSottoprocessoClasse> l=new ArrayList<CrsAssSottoprocessoClasse>();

		List<Object[]> obj = gestioneComponentiDao.getCrsAssSottProcByIdDatiTipo(idNormativa, idTipo) ;

		if(obj !=null){
			for(int i=0; i<obj.size();i++){
				CrsAssSottoprocessoClasse t= new CrsAssSottoprocessoClasse();
				t.setIdAssSottoProcessoClasse((Long)obj.get(i)[0]);
				t.setIdSottoprocesso((Long)obj.get(i)[1]);
				t.setIdNormativa((Long)obj.get(i)[2]);
				t.setIdTipo((Long)obj.get(i)[3]);
				l.add(t);
				//basta sapere che almeno un record è associato alla normativa
				return l.get(0);
			}
		}
		return null;
	}

	@Override
	public List<CrsDocumentiMedia> getListDocMedia(Integer pageNumber,
			Integer pageSize, int columnNameToOrder, String orderType,
			String search) {
		
		List<Object[]> obj = gestioneComponentiDao.getListDocMedia(pageNumber, pageSize,  columnNameToOrder,  orderType, search) ;
		List<CrsDocumentiMedia> l= new ArrayList<CrsDocumentiMedia>();
		if(obj !=null){
			for(int i=0; i<obj.size();i++){
				CrsDocumentiMedia t= new CrsDocumentiMedia();
				t.setId((Long)obj.get(i)[0]);
				t.setCodice((String)obj.get(i)[1]); 
				t.setDescrizione((String)obj.get(i)[2]);
				t.setDataInizio((Date)obj.get(i)[3]);
				t.setDataFine((Date)obj.get(i)[4]);
				l.add(t);
			}
		}
		return l;
	}

	@Override
	public Integer countListDocMedia(String search) {
		// TODO Auto-generated method stub
		return gestioneComponentiDao.countListDocMedia(search);
	}

	
	@Override
	public List<CrsDocumentiMedia> getListDocMediaFiltro(Integer pageNumber,
			Integer pageSize, int columnNameToOrder, String orderType,
			String search) {
		
		List<Object[]> obj = gestioneComponentiDao.getListDocMediaFiltro(pageNumber, pageSize,  columnNameToOrder,  orderType, search) ;
		List<CrsDocumentiMedia> l= new ArrayList<CrsDocumentiMedia>();
		if(obj !=null){
			for(int i=0; i<obj.size();i++){
				CrsDocumentiMedia t= new CrsDocumentiMedia();
				t.setId((Long)obj.get(i)[0]);
				t.setCodice((String)obj.get(i)[1]); 
				t.setDescrizione((String)obj.get(i)[2]);
				t.setDataInizio((Date)obj.get(i)[3]);
				t.setDataFine((Date)obj.get(i)[4]);
				l.add(t);
			}
		}
		return l;
	}
	
	@Override
	public Integer countListDocMediaFiltro(String getsSearch) {
		// TODO Auto-generated method stub
		return gestioneComponentiDao.countListDocMediaFiltro(getsSearch);
	}
	

	@Override
	public List<CrsTplDocMedia> getTplDocMedia() {
		List<CrsTplDocMedia> list= new ArrayList<CrsTplDocMedia>();
		try{
			list=gestioneComponentiDao.getTplDocMedia();
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	

	@Override
	public Boolean checkCodEsistente(String codice) {
		Object[]o = gestioneComponentiDao.checkCodiceEsistente(codice);
		if(o != null){
			//codice esistente
			return true;
		}
		//codice non esistente
		return false;
	}


	
	
	public List<CircolariInps> getCircolariInpsTable(NormativaTablePaginator filter){
		
		List<Object[]> lista = null;
		List<CircolariInps> listaReturn = new ArrayList<CircolariInps>();
		
		lista=gestioneComponentiDao.getCircolariInpsTable(filter);
		
		for(Object[] obj : lista){	
			CircolariInps objectBean = ModelToBean.modelToCircolariInpsBean(obj);
			listaReturn.add(objectBean);
		}
		
		return listaReturn;	
	}
	
	public Integer countAllCircolariInpsTable(NormativaTablePaginator filter){
		return gestioneComponentiDao.countAllCircolariInpsTable(filter);
	}
	
	
	public List<NoteDecreti> getNoteDecretiTable(NormativaTablePaginator filter){
		
		List<Object[]> lista = null;
		List<NoteDecreti> listaReturn = new ArrayList<NoteDecreti>();
		
		lista=gestioneComponentiDao.getNoteDecretiTable(filter);
		
		for(Object[] obj : lista){	
			NoteDecreti objectBean = ModelToBean.modelToNoteDecretiBean(obj);
			listaReturn.add(objectBean);
		}
		
		return listaReturn;	
	}
	
	public Integer countAllNoteDecretiTable(NormativaTablePaginator filter){
		return gestioneComponentiDao.countAllNoteDecretiTable(filter);
	}

	@Override
	public List<CrsDomini> getListDomini(Integer pageNumber, Integer pageSize,
			int columnNameToOrder, String orderType, String search) {
		List<Object[]> obj = gestioneComponentiDao.getListDomini(pageNumber, pageSize,  columnNameToOrder,  orderType, search) ;
		List<CrsDomini> l= new ArrayList<CrsDomini>();
		if(obj !=null){
			for(int i=0; i<obj.size();i++){
				CrsDomini t= new CrsDomini();
				t.setId((Long)obj.get(i)[0]);
				t.setDescrizione((String)obj.get(i)[1]);
				t.setCodice((String)obj.get(i)[2]);
				t.setDataInizio((Date)obj.get(i)[3]);
				t.setDataFine((Date)obj.get(i)[4]);
				
				l.add(t);
			}
		}
		return l;
	}

	@Override
	public Integer countListDomini(String getsSearch) {
		// TODO Auto-generated method stub
		return gestioneComponentiDao.countDomini(getsSearch);
	}
	
	@Override
	public Boolean checkCodEsistenteDominio(String codice) {
		Object[]o = gestioneComponentiDao.checkCodiceEsistenteDominio(codice);
		if(o != null){
			//codice esistente
			return true;
		}
		//codice non esistente
		return false;
	}
	
	
	@Override
	public List<CrsDomini> getListDominiFiltro(Integer pageNumber,Integer pageSize, int columnNameToOrder, String orderType,
			String search) {
		List<Object[]> obj = gestioneComponentiDao.getListDominiFiltro(pageNumber, pageSize,  columnNameToOrder,  orderType, search) ;
		List<CrsDomini> l= new ArrayList<CrsDomini>();
		if(obj !=null){
			for(int i=0; i<obj.size();i++){
				CrsDomini t= new CrsDomini();
				t.setId((Long)obj.get(i)[0]);
				t.setDescrizione((String)obj.get(i)[1]);
				t.setCodice((String)obj.get(i)[2]);
				t.setDataInizio((Date)obj.get(i)[3]);
				t.setDataFine((Date)obj.get(i)[4]);
				
				l.add(t);
			}
		}
		return l;
	}
	
	@Override
	public Integer countListDominiFiltro(String getsSearch) {
		// TODO Auto-generated method stub
		return gestioneComponentiDao.countDominiFiltro(getsSearch);
	}

	@Override
	public List<CrsDocumentiAllegatiDto> getAllegatiDominio(long idDominio, Integer pageNumber,
			Integer pageSize, int columnNameToOrder, String orderType,
			String search) {
		List<Object[]> obj = gestioneComponentiDao.getAllegatiDominio(idDominio, pageNumber, pageSize,  columnNameToOrder,  orderType, search) ;
		List<CrsDocumentiAllegatiDto> l= new ArrayList<CrsDocumentiAllegatiDto>();
		if(obj !=null){
			for(int i=0; i<obj.size();i++){
				CrsDocumentiAllegatiDto t= new CrsDocumentiAllegatiDto();
				t.setIdAssociazione((Long)obj.get(i)[0]);
				t.setIdDoc((Long)obj.get(i)[1]);
				t.setNomeFile((String)obj.get(i)[2]);
				t.setDescrizione((String)obj.get(i)[3]);
				t.setDataInizio((Date)obj.get(i)[4]);
				
				l.add(t);
			}
		}
		return l;
	}
	

	@Override
	public Integer countListAllegatiDominio(long idDominio,String getsSearch) {
		// TODO Auto-generated method stub
		return gestioneComponentiDao.countListAllegatiDominio(idDominio,getsSearch);
	}



	@Override
	public List<CrsDocumentiMedia> getNonAllegatiDominio(long idDominio,
			Integer pageNumber, Integer pageSize, int columnNameToOrder,
			String orderType, String search) {
		List<Object[]> obj = gestioneComponentiDao.getNonAllegatiDominio(idDominio, pageNumber, pageSize,  columnNameToOrder,  orderType, search) ;
		List<CrsDocumentiMedia> l= new ArrayList<CrsDocumentiMedia>();
		if(obj !=null){
			for(int i=0; i<obj.size();i++){
				CrsDocumentiMedia t= new CrsDocumentiMedia();
				t.setId((Long)obj.get(i)[0]);
				t.setNomeFile((String)obj.get(i)[1]);
				t.setDescrizione((String)obj.get(i)[2]);
				t.setDataInizio((Date)obj.get(i)[3]);
				
				l.add(t);
			}
		}
		return l;
	}

	@Override
	public Integer countListNonAllegatiDominio(long idDominio, String search) {
		// TODO Auto-generated method stub
		return gestioneComponentiDao.countListNonAllegatiDominio(idDominio,search);
	}
	
	@Override
	public List<CrsCompTecnicoDto> getListCompTecnico(Integer pageNumber,Integer pageSize, int columnNameToOrder, String orderType,
			String search) {
		List<CrsCompTecnicoDto> l= new ArrayList<CrsCompTecnicoDto>();
		try{
			List<Object[]> obj = gestioneComponentiDao.getListCompTecnico(pageNumber, pageSize,  columnNameToOrder,  orderType, search) ;
			
			if(obj !=null){
				for(int i=0; i<obj.size();i++){
					CrsCompTecnicoDto t= new CrsCompTecnicoDto();
					t.setId((Long)obj.get(i)[0]);
					t.setDescrizione((String)obj.get(i)[1]);
					t.setCodice((String)obj.get(i)[2]);
					if(obj.get(i)[3] !=null){
						t.setAutore((String)obj.get(i)[3]);
					}
					if(obj.get(i)[4] !=null){
						t.setDataPubblicazione((Date)obj.get(i)[4]);
					}
					if(obj.get(i)[5] !=null){
						t.setVersione((String)obj.get(i)[5]);
					}
					
					t.setIdTplCompTecnico((Long)obj.get(i)[6]);
					CrsTplCompTecnico tpl= this.cerca(CrsTplCompTecnico.class, t.getIdTplCompTecnico());
					t.setTipoCompTecnico(tpl.getDescrizione());
					l.add(t);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return l;
	}

	@Override
	public Boolean checkCodEsistenteCompTecnico(String codice) {
		Object[]o = gestioneComponentiDao.checkCodEsistenteCompTecnico(codice);
		if(o != null){
			//codice esistente
			return true;
		}
		//codice non esistente
		return false;
	}
	
	@Override
	public Integer countListCompTecnico(String search) {
		// TODO Auto-generated method stub
		return gestioneComponentiDao.countListCompTecnico(search);
	}


	@Override
	public List<CrsDocumentiAllegatiDto> getAllegatiCt(long idCt,
			Integer pageNumber, Integer pageSize, int columnNameToOrder,
			String orderType, String search) {
		List<Object[]> obj = gestioneComponentiDao.getAllegatiCt(idCt, pageNumber, pageSize,  columnNameToOrder,  orderType, search) ;
		List<CrsDocumentiAllegatiDto> l= new ArrayList<CrsDocumentiAllegatiDto>();
		if(obj !=null){
			for(int i=0; i<obj.size();i++){
				CrsDocumentiAllegatiDto t= new CrsDocumentiAllegatiDto();
				t.setIdAssociazione((Long)obj.get(i)[0]);
				t.setIdDoc((Long)obj.get(i)[1]);
				t.setNomeFile((String)obj.get(i)[2]);
				t.setDescrizione((String)obj.get(i)[3]);
				t.setDataInizio((Date)obj.get(i)[4]);
				
				l.add(t);
			}
		}
		return l;
	}

	@Override
	public Integer countListAllegatiCt(long idCT, String search) {
		// TODO Auto-generated method stub
		return gestioneComponentiDao.countListAllegatiCt(idCT,search);
	}

	
	

	@Override
	public List<CrsCompTecnicoDto> getListCompTecnicoStorico(
			Integer pageNumber, Integer pageSize, int columnNameToOrder,
			String orderType, String search) {
		List<Object[]> obj = gestioneComponentiDao.getListCompTecnicoStorico(pageNumber, pageSize,  columnNameToOrder,  orderType, search) ;
		List<CrsCompTecnicoDto> l= new ArrayList<CrsCompTecnicoDto>();
		if(obj !=null){
			for(int i=0; i<obj.size();i++){
				CrsCompTecnicoDto t= new CrsCompTecnicoDto();
				t.setId((Long)obj.get(i)[0]);
				t.setDescrizione((String)obj.get(i)[1]);
				t.setCodice((String)obj.get(i)[2]);
				if(obj.get(i)[3] !=null){
					t.setAutore((String)obj.get(i)[3]);
				}
				if(obj.get(i)[4] !=null){
					t.setDataPubblicazione((Date)obj.get(i)[4]);
				}
				if(obj.get(i)[5] !=null){
					t.setVersione((String)obj.get(i)[5]);
				}
				
				t.setIdTplCompTecnico((Long)obj.get(i)[6]);
				CrsTplCompTecnico tpl= this.cerca(CrsTplCompTecnico.class, t.getIdTplCompTecnico());
				t.setTipoCompTecnico(tpl.getDescrizione());
				l.add(t);
			}
		}
		return l;
	
	}
	

	@Override
	public List<CrsDocumentiMedia> getNonAllegatiCompTec(long idCt,
			Integer pageNumber, Integer pageSize, int columnNameToOrder,
			String orderType, String search) {
		List<Object[]> obj = gestioneComponentiDao.getNonAllegatiCompTec(idCt, pageNumber, pageSize,  columnNameToOrder,  orderType, search) ;
		List<CrsDocumentiMedia> l= new ArrayList<CrsDocumentiMedia>();
		if(obj !=null){
			for(int i=0; i<obj.size();i++){
				CrsDocumentiMedia t= new CrsDocumentiMedia();
				t.setId((Long)obj.get(i)[0]);
				t.setNomeFile((String)obj.get(i)[1]);
				t.setDescrizione((String)obj.get(i)[2]);
				t.setDataInizio((Date)obj.get(i)[3]);
				
				l.add(t);
			}
		}
		return l;
	}
	
	@Override
	public Integer countListNonAllegatiCompTec(long idCt, String search) {
		// TODO Auto-generated method stub
		return gestioneComponentiDao.countListNonAllegatiCompTec(idCt,search);
	}
	
	@Override
	public Integer countListCompTecnicoStorico(String search) {
		// TODO Auto-generated method stub
		return gestioneComponentiDao.countListCompTecnicoStorico(search);
	}
	
	//op comuni

	@Override
	public <T> T cerca(Class<T> obj, Object pk) {
		// TODO Auto-generated method stub
		return gestioneComponentiDao.cerca(obj , pk);
	}

	@Override
	public void remove(Object o) {
		 gestioneComponentiDao.remove(o);
		
	}
	@Override
	public <T> T salva(T obj) {
		// TODO Auto-generated method stub
		return gestioneComponentiDao.save(obj);
	}

	
	
	public List<MessaggiInps> getMessaggiInpsTable(NormativaTablePaginator filter){
		
		List<Object[]> lista = null;
		List<MessaggiInps> listaReturn = new ArrayList<MessaggiInps>();
		
		lista=gestioneComponentiDao.getMessaggiInpsTable(filter);
		
		for(Object[] obj : lista){	
			MessaggiInps objectBean = ModelToBean.modelToMessaggiInpsBean(obj);
			listaReturn.add(objectBean);
		}
		
		return listaReturn;	
	}
	
	public Integer countAllMessaggiInpsTable(NormativaTablePaginator filter){
		return gestioneComponentiDao.countAllMessaggiInpsTable(filter);
	}
	
	public List<LeggiDecreti> getLeggiDecretiTable(NormativaTablePaginator filter){
		
		List<Object[]> lista = null;
		List<LeggiDecreti> listaReturn = new ArrayList<LeggiDecreti>();
		
		lista=gestioneComponentiDao.getLeggiDecretiTable(filter);
		
		for(Object[] obj : lista){	
			LeggiDecreti objectBean = ModelToBean.modelToLeggiDecretiBean(obj);
			listaReturn.add(objectBean);
		}
		
		return listaReturn;	
	}
	
	public Integer countAllLeggiDecretiTable(NormativaTablePaginator filter){
		return gestioneComponentiDao.countAllLeggiDecretiTable(filter);
	}




	public List<CrsDocumentiAllegatiDto> getAllegatiCircolariInpsTable(NormativaTablePaginator filter) {
		
		List<Object[]> obj = gestioneComponentiDao.getAllegatiCircolariInpsTable(filter);
		List<CrsDocumentiAllegatiDto> l= new ArrayList<CrsDocumentiAllegatiDto>();
		
		if(obj !=null){
			for(int i=0; i<obj.size();i++){
				CrsDocumentiAllegatiDto t= new CrsDocumentiAllegatiDto();
				t.setIdAssociazione((Long)obj.get(i)[0]);
				t.setIdDoc((Long)obj.get(i)[1]);
				t.setNomeFile((String)obj.get(i)[2]);
				t.setDescrizione((String)obj.get(i)[3]);
				t.setDataInizio((Date)obj.get(i)[4]);
				
				l.add(t);
			}
		}
		return l;
	}

	public Integer countAllAllegatiCircolariInpsTable(NormativaTablePaginator filter){
		return gestioneComponentiDao.countAllAllegatiCircolariInpsTable(filter);
	}
	
	
	public List<CrsDocumentiAllegatiDto> getAllegatiNoteDecretiTable(NormativaTablePaginator filter) {
		
		List<Object[]> obj = gestioneComponentiDao.getAllegatiNoteDecretiTable(filter);
		List<CrsDocumentiAllegatiDto> l= new ArrayList<CrsDocumentiAllegatiDto>();
		
		if(obj !=null){
			for(int i=0; i<obj.size();i++){
				CrsDocumentiAllegatiDto t= new CrsDocumentiAllegatiDto();
				t.setIdAssociazione((Long)obj.get(i)[0]);
				t.setIdDoc((Long)obj.get(i)[1]);
				t.setNomeFile((String)obj.get(i)[2]);
				t.setDescrizione((String)obj.get(i)[3]);
				t.setDataInizio((Date)obj.get(i)[4]);
				
				l.add(t);
			}
		}
		return l;
	}

	public Integer countAllAllegatiNoteDecretiTable(NormativaTablePaginator filter){
		return gestioneComponentiDao.countAllAllegatiNoteDecretiTable(filter);
	}

	
	public List<CrsDocumentiAllegatiDto> getAllegatiMessaggiInpsTable(NormativaTablePaginator filter) {
		
		List<Object[]> obj = gestioneComponentiDao.getAllegatiMessaggiInpsTable(filter);
		List<CrsDocumentiAllegatiDto> l= new ArrayList<CrsDocumentiAllegatiDto>();
		
		if(obj !=null){
			for(int i=0; i<obj.size();i++){
				CrsDocumentiAllegatiDto t= new CrsDocumentiAllegatiDto();
				t.setIdAssociazione((Long)obj.get(i)[0]);
				t.setIdDoc((Long)obj.get(i)[1]);
				t.setNomeFile((String)obj.get(i)[2]);
				t.setDescrizione((String)obj.get(i)[3]);
				t.setDataInizio((Date)obj.get(i)[4]);
				
				l.add(t);
			}
		}
		return l;
	}

	public Integer countAllAllegatiMessaggiInpsTable(NormativaTablePaginator filter){
		return gestioneComponentiDao.countAllAllegatiMessaggiInpsTable(filter);
	}
	
	
	public List<CrsDocumentiAllegatiDto> getAllegatiLeggiDecretiTable(NormativaTablePaginator filter) {
		
		List<Object[]> obj = gestioneComponentiDao.getAllegatiLeggiDecretiTable(filter);
		List<CrsDocumentiAllegatiDto> l= new ArrayList<CrsDocumentiAllegatiDto>();
		
		if(obj !=null){
			for(int i=0; i<obj.size();i++){
				CrsDocumentiAllegatiDto t= new CrsDocumentiAllegatiDto();
				t.setIdAssociazione((Long)obj.get(i)[0]);
				t.setIdDoc((Long)obj.get(i)[1]);
				t.setNomeFile((String)obj.get(i)[2]);
				t.setDescrizione((String)obj.get(i)[3]);
				t.setDataInizio((Date)obj.get(i)[4]);
				
				l.add(t);
			}
		}
		return l;
	}

	public Integer countAllAllegatiLeggiDecretiTable(NormativaTablePaginator filter){
		return gestioneComponentiDao.countAllAllegatiLeggiDecretiTable(filter);
	}
	
	
	@Override
	public List<CrsDocumentiMedia> getNonAllegatiCircolariInps(NormativaTablePaginator filter) {
		
		List<Object[]> obj = gestioneComponentiDao.getNonAllegatiCircolariInps(filter) ;
		List<CrsDocumentiMedia> l= new ArrayList<CrsDocumentiMedia>();
		
		if(obj !=null){
			for(int i=0; i<obj.size();i++){
				CrsDocumentiMedia t= new CrsDocumentiMedia();
				t.setId((Long)obj.get(i)[0]);
				t.setNomeFile((String)obj.get(i)[1]);
				t.setDescrizione((String)obj.get(i)[2]);
				t.setDataInizio((Date)obj.get(i)[3]);
				
				l.add(t);
			}
		}
		return l;
	}

	@Override
	public Integer countListNonAllegatiCircolariInps(NormativaTablePaginator filter) {
		
		return gestioneComponentiDao.countListNonAllegatiCircolariInps(filter);
	}
	
	@Override
	public List<CrsDocumentiMedia> getNonAllegatiNoteDecreti(NormativaTablePaginator filter) {
		
		List<Object[]> obj = gestioneComponentiDao.getNonAllegatiNoteDecreti(filter) ;
		List<CrsDocumentiMedia> l= new ArrayList<CrsDocumentiMedia>();
		
		if(obj !=null){
			for(int i=0; i<obj.size();i++){
				CrsDocumentiMedia t= new CrsDocumentiMedia();
				t.setId((Long)obj.get(i)[0]);
				t.setNomeFile((String)obj.get(i)[1]);
				t.setDescrizione((String)obj.get(i)[2]);
				t.setDataInizio((Date)obj.get(i)[3]);
				
				l.add(t);
			}
		}
		return l;
	}

	@Override
	public Integer countListNonAllegatiNoteDecreti(NormativaTablePaginator filter) {
		
		return gestioneComponentiDao.countListNonAllegatiNoteDecreti(filter);
	}
	
	@Override
	public List<CrsDocumentiMedia> getNonAllegatiMessaggiInps(NormativaTablePaginator filter) {
		
		List<Object[]> obj = gestioneComponentiDao.getNonAllegatiMessaggiInps(filter) ;
		List<CrsDocumentiMedia> l= new ArrayList<CrsDocumentiMedia>();
		
		if(obj !=null){
			for(int i=0; i<obj.size();i++){
				CrsDocumentiMedia t= new CrsDocumentiMedia();
				t.setId((Long)obj.get(i)[0]);
				t.setNomeFile((String)obj.get(i)[1]);
				t.setDescrizione((String)obj.get(i)[2]);
				t.setDataInizio((Date)obj.get(i)[3]);
				
				l.add(t);
			}
		}
		return l;
	}

	@Override
	public Integer countListNonAllegatiMessaggiInps(NormativaTablePaginator filter) {
		
		return gestioneComponentiDao.countListNonAllegatiMessaggiInps(filter);
	}
	
	@Override
	public List<CrsDocumentiMedia> getNonAllegatiLeggiDecreti(NormativaTablePaginator filter) {
		
		List<Object[]> obj = gestioneComponentiDao.getNonAllegatiLeggiDecreti(filter) ;
		List<CrsDocumentiMedia> l= new ArrayList<CrsDocumentiMedia>();
		
		if(obj !=null){
			for(int i=0; i<obj.size();i++){
				CrsDocumentiMedia t= new CrsDocumentiMedia();
				t.setId((Long)obj.get(i)[0]);
				t.setNomeFile((String)obj.get(i)[1]);
				t.setDescrizione((String)obj.get(i)[2]);
				t.setDataInizio((Date)obj.get(i)[3]);
				
				l.add(t);
			}
		}
		return l;
	}

	@Override
	public Integer countListNonAllegatiLeggiDecreti(NormativaTablePaginator filter) {
		
		return gestioneComponentiDao.countListNonAllegatiLeggiDecreti(filter);
	}

	
	
	public List<EnteEmittente> getComboEnteEmittente(){
		
		List<Object[]> lista = null;
		List<EnteEmittente> listaReturn = new ArrayList<EnteEmittente>();
		
		lista = gestioneComponentiDao.getComboEnteEmittente();
		
		if(lista !=null){
			for(int i=0; i<lista.size();i++){
				EnteEmittente ente = new EnteEmittente();
				ente.setIdEnteEmittente((Long)lista.get(i)[0]);
				ente.setCodice((String)lista.get(i)[1]);
				ente.setDescrizione((String)lista.get(i)[2]);
				
				listaReturn.add(ente);
			}
		}
		
		return listaReturn;	
	}
	
	
	public List<TipoLegge> getComboTipoLegge(){
		
		List<Object[]> lista = null;
		List<TipoLegge> listaReturn = new ArrayList<TipoLegge>();
		
		lista = gestioneComponentiDao.getComboTipoLegge();
		
		if(lista !=null){
			for(int i=0; i<lista.size();i++){
				TipoLegge legge = new TipoLegge();
				legge.setIdTipoLegge((Long)lista.get(i)[0]);
				legge.setCodice((String)lista.get(i)[1]);
				legge.setDescrizione((String)lista.get(i)[2]);
				
				listaReturn.add(legge);
			}
		}
		
		return listaReturn;	
	}
	
	
	public List<CrsDominiValori> getDominiValoriTable(DominiValoriPaginator filter){
		
		List<Object[]> lista = null;
		List<CrsDominiValori> listaReturn = new ArrayList<CrsDominiValori>();
		
		lista = gestioneComponentiDao.getDominiValoriTable(filter);
		
		if(lista !=null){
			for(int i=0; i<lista.size();i++){
				CrsDominiValori t = new CrsDominiValori();
				t.setIdDominiValori((Long)lista.get(i)[0]);
				t.setCodiceValore((String)lista.get(i)[1]);
				t.setCodiceDominio((String)lista.get(i)[2]);
				t.setDescrizione((String)lista.get(i)[3]);
				t.setDataInizio((Date)lista.get(i)[4]);
				t.setDataFine((Date)lista.get(i)[5]);
				
				listaReturn.add(t);
			}
		}
		
		return listaReturn;	
	}
	
	public Integer countAllDominiValoriTable(DominiValoriPaginator filter){
		return gestioneComponentiDao.countAllDominiValoriTable(filter);
	}
	
	@Override
	public Boolean checkCodEsistenteDominiValori(String codice) {
		Object[]o = gestioneComponentiDao.checkCodiceEsistenteDominiValori(codice);
		if(o != null){
			//codice esistente
			return true;
		}
		//codice non esistente
		return false;
	}

	@Override
	public CrsTplEnteEmittente cercaEmittenteByCodice(String enteEmittente) {
		Object[]o = gestioneComponentiDao.cercaEmittenteByCodice(enteEmittente);
		if(o!=null){
			CrsTplEnteEmittente em=new CrsTplEnteEmittente();
			em.setId((Long)o[0]);
			em.setCodice((String)o[1]);
			em.setDescrizione((String)o[2]);
			return em;
		}
		return null;
	}

	@Override
	public CrsTplTipoLegge cercaLeggeByCodice(String tipoLegge) {
		Object[]o = gestioneComponentiDao.cercaLeggeByCodice(tipoLegge);
		if(o!=null){
			CrsTplTipoLegge l=new CrsTplTipoLegge();
			l.setId((Long)o[0]);
			l.setCodice((String)o[1]);
			l.setDescrizione((String)o[2]);
			return l;
		}
		return null;
	}

	@Override
	public List<CrsTplCompTecnico> getTplCompTec() {
		List<CrsTplCompTecnico> list= new ArrayList<CrsTplCompTecnico>();
		try{
			list=gestioneComponentiDao.getTplCompTec();
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
}
