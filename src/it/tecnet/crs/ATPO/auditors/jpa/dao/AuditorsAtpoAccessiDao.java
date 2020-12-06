package it.tecnet.crs.ATPO.auditors.jpa.dao;

import java.util.List;

import it.tecnet.crs.jpa.model.AuAudit;

public interface AuditorsAtpoAccessiDao {

	//TABELLA PRATICHE ATPO
	List<Object[]> getPraticheATPO(int idSessione, int filtroEsito, int filtroStato, Integer pageNumber,
			Integer pageSize, Integer columnNameToOrder, String orderType,String textSearch);

	Integer countListaPraticheATPO(int idSessione, int filtroEsito, int filtroStato, int getiDisplayStart, int getiDisplayLength,
			int getiSortCol_0, String getsSortDir_0, String getsSearch);

	
	
	
	public <T> T cerca(Class<T> obj , Object pk);

	AuAudit getAuditByIdCampagna(long idCampagna);

	Object[] getSSessioneDatiGenerali(long idSessione);

	Object[] getDatiContesto(long idSessione, long idCampagna);

	Integer getNumeroPraticheEsaminate(long idSessione);

	List<Object[]> getTipologiaPraticheCampione(long idSessione);

	List<Object[]> getNonConformitaAccessiAtpo(Long idAudit);

	List<Object[]> getSNonConfByIdMNonConf(long idMNonConf, long idSSessione);

	List<Object[]> getVarComp(long idMNonConf, long idSSessione,
			int pageNumber, int pageSize, int columnNameToOrder,
			String orderType, String search);

	int countVarComp(long idMNonConf, long idSSessione, String search);

	List<Object[]> getTabRischiPM(Long idAudit);

	List<Object[]> searchEsprRischiTablePM(long idRischio, long idSSessione,
			int pageNumber, int pageSize, int columnNameToOrder,
			String orderType, String search);

	int countEsprRischiTablePM(long idRischio, long idSSessione, String search);

	public <T> T salva(T entity);

	List<Object[]> getRisultatiByTempo(long idSSessione, int pageNumber,
			int pageSize, int columnNameToOrder, String orderType, String search);

	int countListTempo(long idSSessione, String search);

	
	
}
