package it.tecnet.crs.jpa.dao;

import java.util.List;

import it.tecnet.crs.jpa.model.AuAudit;
import it.tecnet.crs.jpa.model.AuCampagna;
import it.tecnet.crs.jpa.model.AuSede;
import it.tecnet.crs.jpa.model.CrsRuolo;
import it.tecnet.crs.jpa.model.CrsUtenteAdv;

public interface TipologicheDao {
	
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

	public Integer countTipologiche(String textSearch);

	public List<Object[]> getListTipologiche(Integer pageNumber,
			Integer pageSize, int columnNameToOrder, String orderType,
			String search);

	public void deleteTipologica(long idTplTipologica);

	public Integer countCaricametoPratiche(String getsSearch);

	public List<Object[]> getListCaricamentoPratiche(Integer pageNumber,
			Integer pageSize, int columnNameToOrder, String orderType,
			String search);

	public List<AuCampagna> getCampagneList();

	public List<AuSede> getSediList();

	public void deleteCaricamentoPratiche(long idBcp);

	
	public Integer countIsnc(String getsSearch);

	public List<Object[]> getListIsnc(Integer pageNumber,
			Integer pageSize, int columnNameToOrder, String orderType,
			String search);

	public List<AuAudit> getAuditList();

	public void deleteIsnc(long idTplIsnc);
	
	


}
