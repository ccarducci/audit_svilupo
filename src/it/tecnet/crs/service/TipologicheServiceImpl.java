package it.tecnet.crs.service;

import it.tecnet.crs.jpa.dao.TipologicheDao;
import it.tecnet.crs.jpa.dao.UtenzeDao;
import it.tecnet.crs.jpa.model.AuAudit;
import it.tecnet.crs.jpa.model.AuCampagna;
import it.tecnet.crs.jpa.model.AuSede;
import it.tecnet.crs.jpa.model.AuTplIsnc;
import it.tecnet.crs.jpa.model.AuTplTipologiche;
import it.tecnet.crs.jpa.model.CrsBatchCaricamentoPratiche;
import it.tecnet.crs.jpa.model.CrsRuolo;
import it.tecnet.crs.jpa.model.CrsUtenteAdv;
import it.tecnet.crs.util.ModelToDto;

import java.util.ArrayList;
import java.util.List;


public class TipologicheServiceImpl implements TipologicheService {
	
	private TipologicheDao tipologicheDao;

	

	public TipologicheDao getTipologicheDao() {
		return tipologicheDao;
	}

	public void setTipologicheDao(TipologicheDao tipologicheDao) {
		this.tipologicheDao = tipologicheDao;
	}

	@Override
	public List<CrsRuolo> getValidRolesList() {
		return tipologicheDao.getValidRolesList();
	}

	@Override
	public List<String> getRuoliAssociatiUtente(long id) {
		return tipologicheDao.getRuoliAssociatiUtente(id);
	}

	@Override
	public CrsUtenteAdv getUtenteByUsername(String username) {
		return tipologicheDao.getUtenteByUsername(username);
	}
	@Override
	public List<CrsUtenteAdv> getListUtenti(Integer pageNumber,Integer pageSize, int columnNameToOrder, String orderType,String search) {
		List<Object[]> auditList= tipologicheDao.getListUtenti(pageNumber,pageSize, columnNameToOrder, orderType,search);
		if(auditList==null) return null;
		List<CrsUtenteAdv> auAuditList= new ArrayList<CrsUtenteAdv>();
		for(Object o : auditList){			
			Object[] obj=(Object[])o;

			auAuditList.add(ModelToDto.modelToUsersDto(obj));
		}
		return auAuditList;
	}

	public Integer countUsers(String textSearch){
		return tipologicheDao.countAudit(textSearch);
	}
	@Override
	public long manageProfile(String nome, String cognome, String username,
			String email) {
		CrsUtenteAdv profile=tipologicheDao.getUtenteByUsername(username);
		long result=0;
		if(profile==null){
			CrsUtenteAdv utente=new CrsUtenteAdv();
			utente.setNome(nome);
			utente.setCognome(cognome);
			utente.setUsername(username);
			utente.setEmail(email);
			utente.setAttivo("A");
			utente.setDataUltimoAccesso(null);
			CrsUtenteAdv nuovoUtente=tipologicheDao.salvaRestituisci(utente);
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
		return tipologicheDao.salva(obj);
	}

	@Override
	public <T> T trova(Class<T> clss, Object primaryKey) {
		return tipologicheDao.trova(clss, primaryKey);
	}

	@Override
	public Long getDirigenteByUserId(long userId) {
		
		return tipologicheDao.getDirigenteByUserId(userId);
	}

	@Override
	public void deleteUtente(long idUtente) {
		tipologicheDao.deleteUtente(idUtente);
		
	}

	@Override
	public void saveRuoliUtente(long idUtente, List<String> ruoli) {
		tipologicheDao.saveRuoliUtente(idUtente,ruoli);
		
	}

	@Override
	public List getRuoliUtente(long idUtente) {
		return tipologicheDao.getRuoliUtente(idUtente);
	}

	@Override
	public Integer countTipologiche(String textSearch) {
		return tipologicheDao.countTipologiche(textSearch);
	}

	@Override
	public List<AuTplTipologiche> getListTipologiche(Integer pageNumber,
			Integer pageSize, int columnNameToOrder, String orderType,
			String search) {
		List<Object[]> auditList= tipologicheDao.getListTipologiche(pageNumber,pageSize, columnNameToOrder, orderType,search);
		if(auditList==null) return null;
		List<AuTplTipologiche> auAuditList= new ArrayList<AuTplTipologiche>();
		for(Object o : auditList){			
			Object[] obj=(Object[])o;

			auAuditList.add(ModelToDto.modelToTipologicheDto(obj));
		}
		return auAuditList;
	}

	@Override
	public void deleteTipologica(long idTplTipologica) {
		tipologicheDao.deleteTipologica(idTplTipologica);
		
	}

	@Override
	public Integer countCaricametoPratiche(String getsSearch) {
		return tipologicheDao.countCaricametoPratiche(getsSearch);
	}

	@Override
	public List<CrsBatchCaricamentoPratiche> getListCaricamentoPratiche(Integer pageNumber,
			Integer pageSize, int columnNameToOrder, String orderType,
			String search) {
		List<Object[]> auditList= tipologicheDao.getListCaricamentoPratiche(pageNumber,pageSize, columnNameToOrder, orderType,search);
		if(auditList==null) return null;
		List<CrsBatchCaricamentoPratiche> auAuditList= new ArrayList<CrsBatchCaricamentoPratiche>();
		for(Object o : auditList){			
			Object[] obj=(Object[])o;

			auAuditList.add(ModelToDto.modelTocaricamentoPraticheDto(obj));
		}
		return auAuditList;
	}

	@Override
	public List<AuCampagna> getCampagneList() {
		return tipologicheDao.getCampagneList();
	}

	@Override
	public List<AuSede> getSediList() {
		return tipologicheDao.getSediList();
	}
	@Override
	public List<AuAudit> getAuditList() {
		return tipologicheDao.getAuditList();
	}

	@Override
	public void deleteCaricamentoPratiche(long idBcp) {
		tipologicheDao.deleteCaricamentoPratiche(idBcp);
		
	}

	
	
	
	@Override
	public Integer countIsnc(String getsSearch) {
		return tipologicheDao.countIsnc(getsSearch);
	}

	@Override
	public List<AuTplIsnc> getListIsnc(Integer pageNumber,
			Integer pageSize, int columnNameToOrder, String orderType,
			String search) {
		List<Object[]> auditList= tipologicheDao.getListIsnc(pageNumber,pageSize, columnNameToOrder, orderType,search);
		if(auditList==null) return null;
		List<AuTplIsnc> auAuditList= new ArrayList<AuTplIsnc>();
		for(Object o : auditList){			
			Object[] obj=(Object[])o;

			auAuditList.add(ModelToDto.modelToIsncDto(obj));
		}
		return auAuditList;
	}

	@Override
	public void deleteIsnc(long idTplIsnc) {
		tipologicheDao.deleteIsnc(idTplIsnc);
		
	}
	
	
	

}
