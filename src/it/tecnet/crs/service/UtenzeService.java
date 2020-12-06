package it.tecnet.crs.service;

import java.util.List;

import it.tecnet.crs.audit.web.dto.AuAuditDto;
import it.tecnet.crs.jpa.model.CrsRuolo;
import it.tecnet.crs.jpa.model.CrsUtenteAdv;


public interface UtenzeService {
	
	public List<CrsUtenteAdv> getListUtenti(Integer pageNumber,Integer pageSize, int columnNameToOrder, String orderType,String search);

	public Integer countUsers(String textSearch);
	public CrsUtenteAdv getUtenteByUsername(String username);
	
	public List<CrsRuolo> getValidRolesList();
	
	public List<String> getRuoliAssociatiUtente(long id);
	
	public <T> T salva(T obj);
	
	public <T> T trova(Class<T> clss, Object primaryKey);
	
	public long manageProfile(String nome,String cognome,String username,String email);
	
	public Long getDirigenteByUserId(long userId);

	public void deleteUtente(long idUtente);
	public void saveRuoliUtente(long idUtente,List<String> ruoli);

	public List getRuoliUtente(long idutente);
}
