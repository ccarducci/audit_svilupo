package it.tecnet.crs.audit.service;

import it.tecnet.crs.jpa.model.AuInccDes;
import it.tecnet.crs.jpa.model.AuSPraCalIndLog;
import it.tecnet.crs.web.dto.StatoSessionePratica;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

public interface AuCalcolaIndicatoriService {
	public <T> Object salva(T obj);
	
	@Transactional
	public List<AuSPraCalIndLog> calcolaIndicatoriPratica(long idPratica) throws Exception;
	
	public StatoSessionePratica canCalculateIndicatori(long idPratica);
	
	public boolean isSSsessionOpen(Long idSSsessione);
	
	@Transactional
	public boolean cleanCalcoloIndiciPraticaLog(long idPratica);
	
	public int setEsamePratica(long idPratica,String stato, String userId, String userIdToUpdate);
	
	public int setPraticaInLavorazione(long idPratica, String userId);
	
	public int setPraticaInCalcolata(long idVerbale, String userId);
	
	public void cleanCalcoloIndiciSessione(long idSSessione);
	
	public List<AuSPraCalIndLog> calcolaIndicatoriSessione(long idSSessione) throws Exception;

	public void riapriSessione(long idSSessione);

	public List<AuInccDes> getAuInccDes();

	public Double getPraticheEsamintate(long idSSessione);
	
}
