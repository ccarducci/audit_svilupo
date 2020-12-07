package it.tecnet.crs.audit.service;

import it.tecnet.crs.ATPO.auditors.web.dto.AtpoAuMContestoDto;
import it.tecnet.crs.ATPO.util.ObjectToDto;
import it.tecnet.crs.audit.jpa.dao.AuCampagnaDao;
import it.tecnet.crs.audit.model.AuSediCritiche;
import it.tecnet.crs.audit.web.dto.CampagnaDto;
import it.tecnet.crs.audit.web.dto.PraticheCampagnaDto;
import it.tecnet.crs.audit.web.dto.ReportSediDto;
import it.tecnet.crs.audit.web.dto.RisultatoRegolaCampagnaCampioneDto;
import it.tecnet.crs.audit.web.dto.SessioniDto;
import it.tecnet.crs.jpa.model.AuAudit;
import it.tecnet.crs.jpa.model.AuCampagna;
import it.tecnet.crs.jpa.model.AuCampione;
import it.tecnet.crs.jpa.model.AuRegoleCampagna;
import it.tecnet.crs.jpa.model.AuRisultatiCampagna;
import it.tecnet.crs.jpa.model.AuRisultatiCampione;
import it.tecnet.crs.jpa.model.AuSPratica;
import it.tecnet.crs.jpa.model.AuSede;
import it.tecnet.crs.jpa.model.AuSessioni;
import it.tecnet.crs.util.ModelToDto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AuCampagnaServiceImpl implements AuCampagnaService {

	private AuCampagnaDao campagnaDao;

	public List<PraticheCampagnaDto> getListaVerbaliCampagna(long idCampagna,Integer pageNumber,
			Integer pageSize, Integer columnNameToOrder, String orderType,String textSearch){
		List<PraticheCampagnaDto> listDto= new ArrayList<PraticheCampagnaDto>();
		List<Object[]> list=campagnaDao.getListaVerbaliCampagna(idCampagna, pageNumber, pageSize, columnNameToOrder, orderType, textSearch);
		if(list!=null){
			for(Object[] obj:list){
				PraticheCampagnaDto dto = new PraticheCampagnaDto();
				dto.setNomeSede((String)obj[0]);
				dto.setFascicolo((String)obj[1]);
				dto.setNotifica((Date)obj[2]);
				dto.setApertura((Date)obj[3]);
				dto.setRg((String)obj[4]);
				dto.setParte((String)obj[5]);
				dto.setVisitaPeritale((String)obj[6]);
				if(obj.length>6){
					dto.setEsito((String)obj[7]);
				}
				listDto.add(dto);
			}
		}
		return listDto;
	}
	public List<PraticheCampagnaDto> getListaVerbaliCampagna(long idCampagna){
		List<PraticheCampagnaDto> listDto= new ArrayList<PraticheCampagnaDto>();
		List<Object[]> list=campagnaDao.getListaVerbaliCampagna(idCampagna);
		if(list!=null){
			for(Object[] obj:list){
				PraticheCampagnaDto dto = new PraticheCampagnaDto();
				dto.setNomeSede((String)obj[0]);
				dto.setFascicolo((String)obj[1]);
				dto.setNotifica((Date)obj[2]);
				dto.setApertura((Date)obj[3]);
				dto.setRg((String)obj[4]);
				dto.setParte((String)obj[5]);
				dto.setVisitaPeritale((String)obj[6]);
				dto.setEsito((String)obj[7]);
				dto.setPresenzaVisitePeritali((String)obj[8]);
				
				dto.setFunzionario((String)obj[9]);
				dto.setMedicoINPS((String)obj[10]);
				dto.setTempoAperturaPratica(((Long)obj[11]).longValue()+"");
				dto.setTempoChiusuraPratica(((Long)obj[12]).longValue()+"");
				dto.setPresenzaCTU((String)obj[13]);
				
				
				
				listDto.add(dto);
			}
		}
		return listDto;
	}
	public List<PraticheCampagnaDto> getListaVerbaliCampagnaSede(long idCampagna,String sede){
		List<PraticheCampagnaDto> listDto= new ArrayList<PraticheCampagnaDto>();
		List<Object[]> list=campagnaDao.getListaVerbaliCampagnaSede(idCampagna,sede);
		if(list!=null){
			for(Object[] obj:list){
				PraticheCampagnaDto dto = new PraticheCampagnaDto();
				dto.setNomeSede((String)obj[0]);
				dto.setFascicolo((String)obj[1]);
				dto.setNotifica((Date)obj[2]);
				dto.setApertura((Date)obj[3]);
				dto.setRg((String)obj[4]);
				dto.setParte((String)obj[5]);
				dto.setVisitaPeritale((String)obj[6]);
				dto.setEsito((String)obj[7]);
				dto.setPresenzaVisitePeritali((String)obj[8]);
				
				dto.setFunzionario((String)obj[9]);
				dto.setMedicoINPS((String)obj[10]);
				dto.setTempoAperturaPratica(((Long)obj[11]).longValue()+"");
				dto.setTempoChiusuraPratica(((Long)obj[12]).longValue()+"");
				dto.setPresenzaCTU((String)obj[13]);
				
				
				
				listDto.add(dto);
			}
		}
		return listDto;
	}
	
	@Override
	public AtpoAuMContestoDto getDatiContesto(long idSessione, long idCampagna) {
		Object[] o=campagnaDao.getDatiContesto(idSessione,idCampagna);
		try{
			if(o!=null){
				AtpoAuMContestoDto a= ObjectToDto.objectToDatiContestoDto(o);
				return a;
			}

		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}	

	public Integer countListaVerbaliCampagna(long idCampagna,Integer pageNumber,
			Integer pageSize, Integer columnNameToOrder, String orderType,String textSearch) {

		return campagnaDao.countListaVerbaliCampagna(idCampagna, pageNumber, pageSize, columnNameToOrder, orderType, textSearch);
	}

	public List<AuRegoleCampagna> getListaRegoleCampagna(long idCampagna,Integer pageNumber,
			Integer pageSize, Integer columnNameToOrder, String orderType,String textSearch){
		return campagnaDao.getListaRegoleCampagna(idCampagna, pageNumber, pageSize, columnNameToOrder, orderType, textSearch);
	}

	public Integer countListaRegoleCampagna(long idCampagna,Integer pageNumber,
			Integer pageSize, Integer columnNameToOrder, String orderType,String textSearch){
		return campagnaDao.countListaRegoleCampagna(idCampagna, pageNumber, pageSize, columnNameToOrder, orderType, textSearch);
	}

	public List<CampagnaDto> getListaCampagna(Integer pageNumber,
			Integer pageSize, Integer columnNameToOrder, String orderType,String textSearch, long idUtente){
		List<CampagnaDto> dtoLs= new ArrayList<CampagnaDto>();
		List<Object[]> ls=(List<Object[]>)campagnaDao.getListaCampagna(pageNumber, pageSize, columnNameToOrder, orderType, textSearch, idUtente);
		for(int i=0;i<ls.size();i++){
			dtoLs.add(ModelToDto.modelToCampagnaDto(ls.get(i)));
		}
		return dtoLs;
	}

	public Integer countListaCampagna(Integer pageNumber,
			Integer pageSize, Integer columnNameToOrder, String orderType,String textSearch, long idUtente){

		return campagnaDao.countListaCampagna(pageNumber, pageSize, columnNameToOrder, orderType, textSearch, idUtente);
	}

	public AuCampagna salvaCampagna(AuCampagna a){
		return campagnaDao.salvaCampagna(a);
	}

	public void eliminaCampagna(long idCampagna){
		campagnaDao.eliminaCampagna(idCampagna);
	}

	public AuCampagna getCampagnaDaModificare(long idCampagna){
		return campagnaDao.getCampagnaDaModificare(idCampagna);
	}

	public CampagnaDto getCampagnaDto(long idCampagna){
		AuCampagna campagna= campagnaDao.getCampagnaDaModificare(idCampagna);
		AuAudit audit= campagnaDao.cerca(AuAudit.class,campagna.getIdAudit());
		CampagnaDto dto= new CampagnaDto();
		dto.setDataFine(campagna.getDataFine());
		dto.setDataFineOsservazione(campagna.getDataFineOsservazione());
		dto.setDataInizio(campagna.getDataInizio());
		dto.setDataInizioOsservazione(campagna.getDataInizioOsservazione());
		dto.setIdAudit(campagna.getIdAudit());
		dto.setIdCampagna(idCampagna);
		dto.setNome(campagna.getNome());
		dto.setNomeAudit(audit.getNome());
		dto.setStato(campagna.getStato());
		if(dto.getStato()==null)dto.setStato("A");
		return dto;

	}

	public AuCampagnaDao getCampagnaDao() {
		return campagnaDao;
	}

	public void setCampagnaDao(AuCampagnaDao campagnaDao) {
		this.campagnaDao = campagnaDao;
	}

	public void associaVerbaliCampagna(AuCampagna camp){
		campagnaDao.associaVerbaliCampagna(camp);
	}

	

	public List<AuRegoleCampagna> getListaRegoleCampagna(long idCampagna){
		return campagnaDao.getListaRegoleCampagna(idCampagna);
	}

	public <T> void salva(T obj){
		campagnaDao.salva(obj);
	}

	public <T> T cerca(Class<T> obj , Object pk) {
		return campagnaDao.cerca(obj,pk);
	}

	public AuRisultatiCampagna getRisultatoRegolaCampagna(long idRegolaCampagna, long idCampagna){
		return campagnaDao.getRisultatoRegolaCampagna(idRegolaCampagna, idCampagna);
	}

	

	public AuRisultatiCampione getRisultatoRegolaCampione(long idRegolaCampagna, long idCampione){
		return campagnaDao.getRisultatoRegolaCampione(idRegolaCampagna, idCampione);
	}

	public List<AuSessioni> getListaSessioniCampagna(long idCampagna){
		return campagnaDao.getListaSessioniCampagna(idCampagna);
	}



	public AuCampione getCampioneBySessione(long idSessione){
		return campagnaDao.getCampioneBySessione(idSessione);
	}
	
	public List<AuSediCritiche> getListaSediCritiche(Integer pageNumber,
			Integer pageSize, Integer columnNameToOrder, String orderType,String textSearch){

		
		return campagnaDao.getListaSediCritiche(pageNumber, pageSize, columnNameToOrder, orderType, textSearch);

	}
	
	public Integer countSediCritiche(Integer pageNumber,
			Integer pageSize, Integer columnNameToOrder, String orderType,String textSearch){

		return campagnaDao.countSediCritiche(pageNumber, pageSize, columnNameToOrder, orderType, textSearch);
	}


	public List<ReportSediDto> getListaReportSedi(long idCampagna, Integer pageNumber,
			Integer pageSize, Integer columnNameToOrder, String orderType,String textSearch){

		List<ReportSediDto> dtoLs= new ArrayList<ReportSediDto>();
		List<Object[]> ls=(List<Object[]>)campagnaDao.getListaReportSedi(idCampagna, pageNumber, pageSize, columnNameToOrder, orderType, textSearch);
		for(int i=0;i<ls.size();i++){
			dtoLs.add(ModelToDto.modelToReportSediDto(ls.get(i)));
		}
		return dtoLs;

	}
	
	
	public Integer countReportSedi(long idCampagna, Integer pageNumber,
			Integer pageSize, Integer columnNameToOrder, String orderType,String textSearch){

		return campagnaDao.countReportSedi(idCampagna, pageNumber, pageSize, columnNameToOrder, orderType, textSearch);
	}
		

//	public List<AuReportSediLabel> getListaReportSediLabel(long idCampagna){
//		return campagnaDao.getListaReportSediLabel(idCampagna);
//	}
	
	

	public List<AuSessioni> getListaSessioniCampagna(long idCampagna, Integer pageNumber,
			Integer pageSize, Integer columnNameToOrder, String orderType,String textSearch){

		return campagnaDao.getListaSessioniCampagna(idCampagna, pageNumber, pageSize, columnNameToOrder, orderType, textSearch);

	}

	public Integer countListaSessioniCampagna(long idCampagna, Integer pageNumber,
			Integer pageSize, Integer columnNameToOrder, String orderType,String textSearch){

		return campagnaDao.countListaSessioniCampagna(idCampagna, pageNumber, pageSize, columnNameToOrder, orderType, textSearch);
	}

	public List<SessioniDto> getListaSessioni(Integer pageNumber,
			Integer pageSize, Integer columnNameToOrder, String orderType,String textSearch, long idUtente){

		List<SessioniDto> dtoLs= new ArrayList<SessioniDto>();
		List<Object[]> ls=(List<Object[]>) campagnaDao.getListaSessioni(pageNumber, pageSize, columnNameToOrder, orderType, textSearch, idUtente);
		for(int i=0;i<ls.size();i++){
			dtoLs.add(ModelToDto.modelToSessioniDto(ls.get(i)));
		}
		return dtoLs;

		//return campagnaDao.getListaSessioni(pageNumber, pageSize, columnNameToOrder, orderType, textSearch);
	}

	public Integer  countListaSessioni(Integer pageNumber, Integer pageSize, Integer columnNameToOrder,
			String orderType, String textSearch, long idUtente){
		return campagnaDao.countListaSessioni(pageNumber, pageSize, columnNameToOrder, orderType, textSearch, idUtente);
	}

	

	

	public List<RisultatoRegolaCampagnaCampioneDto> getRisultatiRegolaCampagnaCampione(long idRegola){

		List<RisultatoRegolaCampagnaCampioneDto> dtoLs= new ArrayList<RisultatoRegolaCampagnaCampioneDto>();
		List<Object[]> ls=(List<Object[]>)campagnaDao.getRisultatiRegolaCampagnaCampione(idRegola);
		for(int i=0;i<ls.size();i++){
			dtoLs.add(ModelToDto.modelToRisultatoRegolaCampagnaCampioneDto(ls.get(i)));
		}
		return dtoLs;

	}

	@Override
	public void eliminaSessione(long idSessione) {
		campagnaDao.eliminaSessione(idSessione);
	}

	@Override
	public List<PraticheCampagnaDto> getListaVerbaliCampioneSess(long idCampione,
			Integer pageNumber, Integer pageSize, Integer columnNameToOrder,
			String orderType, String textSearch) {
		List<PraticheCampagnaDto> listDto= new ArrayList<PraticheCampagnaDto>();
		List<Object[]> l=(List<Object[]>)campagnaDao.getListaVerbaliCampioneSess(idCampione, pageNumber, pageSize, columnNameToOrder, orderType, textSearch);

		if(l!=null){
			for(Object[] obj:l){
				PraticheCampagnaDto dto = new PraticheCampagnaDto();
				dto.setId((Long)obj[0]);
				dto.setNomeSede((String)obj[1]);
				dto.setFascicolo((String)obj[2]);
				dto.setNotifica((Date)obj[3]);
				dto.setApertura((Date)obj[4]);
				dto.setRg((String)obj[5]);
				dto.setParte((String)obj[6]);
				dto.setVisitaPeritale((String)obj[7]);
				if(obj.length>7){
					dto.setEsito((String)obj[8]);
				}
				listDto.add(dto);
			}
		}


		return listDto;
	}

	@Override
	public Integer countListaVerbaliCampioneSess(long idCampione, Integer pageNumber,
			Integer pageSize, Integer columnNameToOrder, String orderType,String textSearch) {

		Integer count=0;
		try{
			count= campagnaDao.countListaVerbaliCampioneSess(idCampione, pageNumber, pageSize, columnNameToOrder, orderType, textSearch);
		}catch(Exception e){
			e.printStackTrace();
		}

		return count;
	}

	@Override
	public List<PraticheCampagnaDto> getListaVerbaliCampagnaSess(long idCampione,
			Integer pageNumber, Integer pageSize, Integer columnNameToOrder,
			String orderType, String textSearch) {
		List<PraticheCampagnaDto> listDto= new ArrayList<PraticheCampagnaDto>();
		List<Object[]> l=campagnaDao.getListaVerbaliCampagnaSess(idCampione, pageNumber, pageSize, columnNameToOrder, orderType, textSearch);

		if(l!=null){
			for(Object[] obj:l){
				PraticheCampagnaDto dto = new PraticheCampagnaDto();
				dto.setId((Long)obj[0]);
				dto.setNomeSede((String)obj[1]);
				dto.setFascicolo((String)obj[2]);
				dto.setNotifica((Date)obj[3]);
				dto.setApertura((Date)obj[4]);
				dto.setRg((String)obj[5]);
				dto.setParte((String)obj[6]);
				dto.setVisitaPeritale((String)obj[7]);
				if(obj.length>7)
					dto.setEsito((String)obj[8]);
				listDto.add(dto);
			}
		}


		return listDto;
	}

	@Override
	public int countListaVerbaliCampagnaSess(long idSessione, Integer pageNumber,
			Integer pageSize, Integer columnNameToOrder, String orderType,
			String textSearch) {
		Integer count=0;
		try{
			count= campagnaDao.countListaVerbaliCampagnaSess(idSessione, pageNumber, pageSize, columnNameToOrder, orderType, textSearch);
		}catch(Exception e){
			e.printStackTrace();
		}

		return count;
	}

	@Override
	public void deletePraticaCampione(long idCampione, long idPratica, long idSPratica) {
		campagnaDao.deletePraticaCampione( idCampione,  idPratica,  idSPratica);

	}

	@Override
	public List<Long> getIdRegoleCampione(long idSessione) {

		return campagnaDao.getIdRegoleCampione(idSessione);
	}

	@Override
	public List<AuSede> getSedi() {

		return  campagnaDao.getSedi();
	}

//	public void calcoloRegolaCampione(Long idCanmpagna){
//		CalcoloRegole cr = new CalcoloRegole();
//		//List<AuVerbale> vlist= campagnaService.getListaVerbaliCampione(1L);
//		List<AuRegoleCampagna> rclist= getListaRegoleCampagna(idCanmpagna);
//		List<AuSessioni> sessList = getListaSessioniCampagna(idCanmpagna);
//
//		for(AuRegoleCampagna rc:rclist){
//			for(AuSessioni ses: sessList){
//				AuCampione campione= getCampioneBySessione(ses.getIdSessione());
//				if(campione != null){
//					List<AuVerbale> vlist= getListaVerbaliCampione(campione.getIdCampione());
//					if(rc.getIdRegolaCamp()==CalcoloRegole.PERCENTUALE_NOTIFICA_VERBALE){
//						AuRisultatiCampione ris= getRisultatoRegolaCampione(rc.getIdRegolaCamp(),campione.getIdCampione() );
//						if(ris==null){
//							ris= new AuRisultatiCampione();
//							ris.setIdCampione(campione.getIdCampione());
//						}
//						ris=cr.percentualeNotificaVerbale(vlist, rc, campione, ris);
//						salva(ris);
//					}else{
//						AuRisultatiCampione ris= getRisultatoRegolaCampione(rc.getIdRegolaCamp(),campione.getIdCampione() );
//						if(ris==null){
//							ris= new AuRisultatiCampione();
//							ris.setIdCampione(campione.getIdCampione());
//						}
//						ris.setRes1("test");
//						salva(ris);
//					}
//				}
//			}
//		}
//	}



	@Override
	public int countSessioniAssociateCampagna(long idCampagna) {
		return campagnaDao.countSessioniAssociateCampagna(idCampagna);
	}

	@Override
	public long getIdDirigenteFromDelegato(long idDelegato) {
		return campagnaDao.getIdDirigenteFromDelegato(idDelegato);
	}

	@Override
	public long getIdAuditByIdCampagna(long idCampagna) {
		return campagnaDao.getIdAuditByIdCampagna(idCampagna);
	}

	@Override
	public void aggiornaDataFineAccessiCampagna(AuCampagna campagna) {
		campagnaDao.aggiornaDataFineAccessiCampagna(campagna);
	}

	@Override
	public void aggiungiPraticaAlCampione(long idCampione, long idPratica) {
		campagnaDao.aggiungiPraticaAlCampione(idCampione, idPratica);
	}

	@Override
	public AuSPratica getAuSPraticaById(long idPratica) {
		return campagnaDao.getAuSPraticaById(idPratica);
	}
	

}
