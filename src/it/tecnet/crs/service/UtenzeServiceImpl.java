package it.tecnet.crs.service;

import java.util.ArrayList;
import java.util.List;

import it.tecnet.crs.audit.web.dto.AuAuditDto;
import it.tecnet.crs.jpa.dao.UtenzeDao;
import it.tecnet.crs.jpa.model.CrsRuolo;
import it.tecnet.crs.jpa.model.CrsUtenteAdv;
import it.tecnet.crs.util.ModelToDto;


public class UtenzeServiceImpl implements UtenzeService {
	
	private UtenzeDao utenzeDao;

	public UtenzeDao getUtenzeDao() {
		return utenzeDao;
	}

	public void setUtenzeDao(UtenzeDao utenzeDao) {
		this.utenzeDao = utenzeDao;
	}

	@Override
	public List<CrsRuolo> getValidRolesList() {
		return utenzeDao.getValidRolesList();
	}

	@Override
	public List<String> getRuoliAssociatiUtente(long id) {
		return utenzeDao.getRuoliAssociatiUtente(id);
	}

	@Override
	public CrsUtenteAdv getUtenteByUsername(String username) {
		return utenzeDao.getUtenteByUsername(username);
	}
	@Override
	public List<CrsUtenteAdv> getListUtenti(Integer pageNumber,Integer pageSize, int columnNameToOrder, String orderType,String search) {
		List<Object[]> auditList= utenzeDao.getListUtenti(pageNumber,pageSize, columnNameToOrder, orderType,search);
		if(auditList==null) return null;
		List<CrsUtenteAdv> auAuditList= new ArrayList<CrsUtenteAdv>();
		for(Object o : auditList){			
			Object[] obj=(Object[])o;

			auAuditList.add(ModelToDto.modelToUsersDto(obj));
		}
		return auAuditList;
	}

	public Integer countUsers(String textSearch){
		return utenzeDao.countAudit(textSearch);
	}
	@Override
	public long manageProfile(String nome, String cognome, String username,
			String email) {
		CrsUtenteAdv profile=utenzeDao.getUtenteByUsername(username);
		long result=0;
		if(profile==null){
			CrsUtenteAdv utente=new CrsUtenteAdv();
			utente.setNome(nome);
			utente.setCognome(cognome);
			utente.setUsername(username);
			utente.setEmail(email);
			utente.setAttivo("A");
			utente.setDataUltimoAccesso(null);
			CrsUtenteAdv nuovoUtente=utenzeDao.salvaRestituisci(utente);
			result=nuovoUtente.getIdUtente();
		}else{
			String stato=profile.getAttivo();
			if(stato.equalsIgnoreCase("A")){
				result=profile.getIdUtente();
			}else{
				result=-1;
			}
		}
		return result;
	}
	
	public <T> T salva(T obj) {
		return utenzeDao.salva(obj);
	}

	@Override
	public <T> T trova(Class<T> clss, Object primaryKey) {
		return utenzeDao.trova(clss, primaryKey);
	}

	@Override
	public Long getDirigenteByUserId(long userId) {
		
		return utenzeDao.getDirigenteByUserId(userId);
	}

	@Override
	public void deleteUtente(long idUtente) {
		utenzeDao.deleteUtente(idUtente);
		
	}

	@Override
	public void saveRuoliUtente(long idUtente, List<String> ruoli) {
		utenzeDao.saveRuoliUtente(idUtente,ruoli);
		
	}

	@Override
	public List getRuoliUtente(long idUtente) {
		return utenzeDao.getRuoliUtente(idUtente);
	}	

}
