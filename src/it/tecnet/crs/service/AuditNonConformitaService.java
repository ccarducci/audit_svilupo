package it.tecnet.crs.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;



import it.tecnet.crs.jpa.model.AuMediaFase;
import it.tecnet.crs.jpa.model.AuNonConformita;
import it.tecnet.crs.jpa.model.AuNonConformitaVerbale;
import it.tecnet.crs.web.dto.NonConformitaVerbaleDto;
import it.tecnet.crs.web.dto.NonConformitaDto;


public interface AuditNonConformitaService {
	
	/*
	 * 		NON CONFORMITA'
	 * */
	public List<AuNonConformita> getListaNonConformita(Long idAudit, String fase);
	public List<AuNonConformita> getListaNonConformita(Long idAudit, Long idFase) ;
	
	//tab fase notifica e fase definizione in modifica-accessi
//	@Transactional
//	public List<NonConformitaDto> getMediaNonConformitaN(long idSessione,Integer pageNumber, Integer pageSize, String columnNameToOrder,String orderType, String textSearch);
	@Transactional
	public List<NonConformitaDto> getMediaNonConformita(long idSessione, String tipo,
			Integer pageNumber, Integer pageSize, String columnNameToOrder,
			String orderType, String textSearch);
	//count media fase notifica
	public Integer countRecordMediaNotifica(long idSessione);
	//count media fase definizione
	public Integer countRecordMediaDefinizione(long idSessione);
	

	/*
	 * 		NON CONFORMITA' VERBALE
	 * */
	//non viene usato questo metodo.
	public List<AuNonConformitaVerbale> getListaNonConformitaVerbale(Long idVerbale,String fase);

	public List<AuNonConformitaVerbale> getListaNonConformitaVerbaleIdNC(Long idVerbale,Long idNC);

	public List<AuNonConformitaVerbale> getListaNonConformitaVerbale(long idSessione);
	


	public Integer countAllListaNonConformitaVerbale(Long idVerbale,String textSearch,String fase);	

	@Transactional
	public void salvaNonConformitaVerbale(AuNonConformitaVerbale verbaleNonConformita);
	
	@Transactional
	public void salvaNonConformitaVerbaleDto(NonConformitaVerbaleDto verbaleNonConformitaDto);

	public List<AuMediaFase> getIndicatoriDatiGenerali(long idSessione);

	@Transactional
	public void aggiornaMedia(AuMediaFase mf);
	
	@Transactional
	public void aggiornaPunteggioQuestionario(AuMediaFase mf);

	
	

	
}
