package it.tecnet.crs.jpa.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import it.tecnet.crs.jpa.model.AuMediaFase;
import it.tecnet.crs.jpa.model.AuNonConformitaVerbale;
import it.tecnet.crs.jpa.model.AuNonConformita;
import it.tecnet.crs.web.dto.NonConformitaVerbaleDto;


public interface AuditNonConformitaDao {


	/*
	 * 		NON CONFORMITA'
	 * */
	public List<AuNonConformita> getListaNonConformita(Long idAudit, String fase);
	public List<AuNonConformita> getListaNonConformita(Long idAudit, Long idFase) ;
	
//	public List<Object> mediaNonConformitaD(long idSessione,Integer pageNumber, Integer pageSize, String columnNameToOrder,
//			String orderType, String textSearch) ;
	
	public List<Object> mediaNonConformita(long idSessione, String tipo,
			Integer pageNumber, Integer pageSize, String columnNameToOrder,
			String orderType, String textSearch) ;
	
	//count non conformita
	public Integer countMediaNonConformitaD(long idSessione);
	public Integer countMediaNonConformitaN(long idSessione);
	

	/*
	 * 		NON CONFORMITA' VERBALE
	 * */
	public List<AuNonConformitaVerbale> getListaNonConformitaVerbale(Long idVerbale, String fase);
	
	public List<AuNonConformitaVerbale> getListaNonConformitaVerbaleIdNC(Long idVerbale,Long idNC);

	public List<AuNonConformitaVerbale> getListaNonConformitaVerbale(long idSessione);
	
	public Integer countAllListaNonConformitaVerbale(Long idVerbale,String textSearch,String fase);
	
//	public List<AuNonConformitaVerbale> getListaNonConformitaVerbaleIdNonConformita(Long idNonConformita, Integer pageNumber, Integer pageSize, String columnNameToOrder, String orderType, String textSearch,String fase);

	
	
	public List<AuMediaFase> getIndicatoriDatiGenerali(long idSessione);
	
	
	@Transactional
	public void salvaNonConformitaVerbale(AuNonConformitaVerbale verbaleNonConformita);

	@Transactional
	public void salvaNonConformitaVerbaleDto(NonConformitaVerbaleDto verbaleNonConformitaDto);



	public void aggiornaMedia(AuMediaFase mf);
	
	public void aggiornaPunteggioQuestionario(AuMediaFase mf);





	/*
	 * 		REGOLE CAMPAGNA
	 * */
	

	

}
