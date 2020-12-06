package it.tecnet.crs.service;

import java.util.List;

import it.tecnet.crs.audit.web.dto.AuAuditDto;
import it.tecnet.crs.jpa.model.AuAudit;
import it.tecnet.crs.jpa.model.AuCampagna;
import it.tecnet.crs.jpa.model.AuSede;
import it.tecnet.crs.jpa.model.AuTplIsnc;
import it.tecnet.crs.jpa.model.AuTplTipologiche;
import it.tecnet.crs.jpa.model.CrsBatchCaricamentoPratiche;
import it.tecnet.crs.jpa.model.CrsRuolo;
import it.tecnet.crs.jpa.model.CrsUtenteAdv;


public interface TipologicheService {
	
	public List<AuTplTipologiche> getListTipologiche(Integer pageNumber,Integer pageSize, int columnNameToOrder, String orderType,String search);
	public Integer countTipologiche(String textSearch);

	
	
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
	public void deleteTipologica(long idTplTipologica);
	public List<CrsBatchCaricamentoPratiche> getListCaricamentoPratiche(Integer pageNumber,Integer pageSize, int columnNameToOrder, String orderType,String search);
	
	public Integer countCaricametoPratiche(String getsSearch);
	public List<AuCampagna> getCampagneList();
	public List<AuSede> getSediList();
	public List<AuAudit> getAuditList();
	public void deleteCaricamentoPratiche(long idBcp);
	
	public List<AuTplIsnc> getListIsnc(Integer pageNumber,Integer pageSize, int columnNameToOrder, String orderType,String search);
	public Integer countIsnc(String getsSearch);
	public void deleteIsnc(long idTplIsnc);
	
	
}
