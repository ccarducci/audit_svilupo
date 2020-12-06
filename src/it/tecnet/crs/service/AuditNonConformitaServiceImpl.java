package it.tecnet.crs.service;


import it.tecnet.crs.jpa.dao.AuditNonConformitaDao;
import it.tecnet.crs.jpa.model.AuMediaFase;
import it.tecnet.crs.jpa.model.AuNonConformita;
import it.tecnet.crs.jpa.model.AuNonConformitaVerbale;
import it.tecnet.crs.util.ModelToDto;
import it.tecnet.crs.web.dto.NonConformitaDto;
import it.tecnet.crs.web.dto.NonConformitaVerbaleDto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;



public class AuditNonConformitaServiceImpl implements AuditNonConformitaService {


	private AuditNonConformitaDao auditNonConformitaDao;

	public AuditNonConformitaDao getAuditNonConformitaDao() {
		return auditNonConformitaDao;
	}

	public void setAuditNonConformitaDao(AuditNonConformitaDao auditNonConformitaDao) {
		this.auditNonConformitaDao = auditNonConformitaDao;
	}


	/*
	 * 		NON CONFORMITA'
	 * */
	public List<AuNonConformita> getListaNonConformita(Long idAudit, String fase){
		List<AuNonConformita> listaNonConformita = auditNonConformitaDao.getListaNonConformita(idAudit,fase);
		return listaNonConformita;
	}
	
	public List<AuNonConformita> getListaNonConformita(Long idAudit, Long idFase) {
		List<AuNonConformita> listaNonConformita = auditNonConformitaDao.getListaNonConformita(idAudit,idFase);
		return listaNonConformita;
	}
	/*
	 * popola la tabella faseNotifica in modifica-accessi.jsp
	 */
	public List<NonConformitaDto> getMediaNonConformita(long idSessione, String tipo, Integer pageNumber, Integer pageSize, String columnNameToOrder,String orderType, String textSearch) {
		List<Object> listaNonConformita = auditNonConformitaDao.mediaNonConformita(idSessione,tipo,pageNumber, pageSize, columnNameToOrder, orderType, textSearch);
		List<NonConformitaDto> nonConformitaList= new ArrayList<NonConformitaDto>();
		for(Object o : listaNonConformita){			
			Object[] obj=(Object[])o;
			
			nonConformitaList.add(ModelToDto.modelToNonConformitaDto(obj));
		}
		
		return nonConformitaList;
	}
	/*
	 * popola la tabella faseDefinizione in modifica-accessi.jsp
	 */
//	public List<NonConformitaDto> getMediaNonConformitaD(long idSessione,Integer pageNumber, Integer pageSize, String columnNameToOrder,String orderType, String textSearch) {
//		List<Object> listaNonConformitaD = auditNonConformitaDao.mediaNonConformitaD(idSessione,pageNumber, pageSize, columnNameToOrder, orderType, textSearch);
//		List<NonConformitaDto> nonConformitaList= new ArrayList<NonConformitaDto>();
//		for(Object o : listaNonConformitaD){			
//			Object[] obj=(Object[])o;
//			
//			nonConformitaList.add(ModelToDto.modelToNonConformitaDto(obj));
//		}
//		
//		return nonConformitaList;
//		
//	}
	
	public Integer countRecordMediaDefinizione(long idSessione) {
		Integer c= auditNonConformitaDao.countMediaNonConformitaD(idSessione);
		return c;
	}
	
	public Integer countRecordMediaNotifica(long idSessione) {
		Integer c= auditNonConformitaDao.countMediaNonConformitaN(idSessione);
		return c;
	}
	/*
	 * 		NON CONFORMITA' VERBALE
	 * */

	public List<AuNonConformitaVerbale> getListaNonConformitaVerbale(Long idVerbale,String fase){
		List<AuNonConformitaVerbale> listaNonConfVerbali = auditNonConformitaDao.getListaNonConformitaVerbale(idVerbale, fase);
		return listaNonConfVerbali;
	}

	public List<AuNonConformitaVerbale> getListaNonConformitaVerbaleIdNC(Long idVerbale,Long idNC){
		List<AuNonConformitaVerbale> listaNonConfVerbali = auditNonConformitaDao.getListaNonConformitaVerbaleIdNC(idVerbale, idNC);
		return listaNonConfVerbali;
	}

	public List<AuNonConformitaVerbale> getListaNonConformitaVerbale(long idSessione){
		List<AuNonConformitaVerbale> listaNonConfVerbali = auditNonConformitaDao.getListaNonConformitaVerbale(idSessione);
		return listaNonConfVerbali;
	}

	public Integer countAllListaNonConformitaVerbale(Long idVerbale,String textSearch,String fase){
		return auditNonConformitaDao.countAllListaNonConformitaVerbale(idVerbale, textSearch, fase);
	}

	



	@Transactional
	public void salvaNonConformitaVerbale(AuNonConformitaVerbale verbaleNonConformita){
		auditNonConformitaDao.salvaNonConformitaVerbale(verbaleNonConformita);
	}

	@Transactional
	public void salvaNonConformitaVerbaleDto(NonConformitaVerbaleDto verbaleNonConformitaDto){
		auditNonConformitaDao.salvaNonConformitaVerbaleDto(verbaleNonConformitaDto);

	}

	@Override
	public List<AuMediaFase> getIndicatoriDatiGenerali(long idSessione) {
		return auditNonConformitaDao.getIndicatoriDatiGenerali(idSessione);
		
	}

	@Override
	public void aggiornaMedia(AuMediaFase mf) {
		auditNonConformitaDao.aggiornaMedia(mf);
		
	}

	@Override
	public void aggiornaPunteggioQuestionario(AuMediaFase mf) {
		auditNonConformitaDao.aggiornaPunteggioQuestionario(mf);
		
	}

	



}
