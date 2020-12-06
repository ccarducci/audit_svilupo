package it.tecnet.crs.jpa.dao;

import java.util.List;
import it.tecnet.crs.jpa.model.CrsRuolo;
import it.tecnet.crs.jpa.model.CrsUtenteAdv;

public interface UtenzeDao {
	
	public CrsUtenteAdv getUtenteByUsername(String username);
	
	public List<CrsRuolo> getValidRolesList();
	
	public List<String> getRuoliAssociatiUtente(long id);
		
	public boolean checkIfProfileIsAttivo(String username);
		
	public <T> T salva(T obj);
	
	public <T> T salvaRestituisci(T obj);
	
	public <T> T trova(Class<T> clss, Object primaryKey);
	
	public Long getDirigenteByUserId(long userId);

	public Integer countAudit(String textSearch);

	public List<Object[]> getListUtenti(Integer pageNumber, Integer pageSize,
			int columnNameToOrder, String orderType, String search);

	public void deleteUtente(long idUtente);

	public void saveRuoliUtente(long idUtente, List<String> ruoli);

	public List getRuoliUtente(long idUtente);
	


}
