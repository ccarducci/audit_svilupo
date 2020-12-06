package it.tecnet.crs.ATPO.auditors.jpa.dao;

import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoFaseDati;

import java.util.List;



public interface AuditorsAtpoUpdPraticheDao {

	Object getDatiPratica(long idPratica, String fascicolo);

	
	AtpoFaseDati getFaseDati(String fascicolo, String codSede);

	
	/*
	 * tab rischi in pratiche
	 */
	List<Object[]> getTabRischiPratAtpo(long idAudit, Integer pageNumber,
			Integer pageSize, int columnNameToOrder, String orderType,
			String search, long idPratica);

	int countTabRischiPratAtpo(long idAudit, String search, long idPratica);
	/*
	 * tab non conformita in pratiche
	 */
	List<Object[]> getNonConfPratAtpo(long idAudit, long idPratica,String filtro,
			Integer pageNumber, Integer pageSize, int columnNameToOrder,
			 String orderType, String search);
	
	int countNonConfPratAtpo(long idAudit, long idPratica, String filtro, String getsSearch);

	/*
	 * OP COMUNI
	 */
	public <T> T salva(T obj);
	public <T> T cerca(Class<T> obj , Object pk);


	List<Object[]> getTipologicaAtpo(String tipo);


	Object[] getDescrTipologicaByCodifica(String codifica);


	
	public Long getAuSPraticaByIdPratica(Long idPratica);


	List<Object[]> getDescrizioneFaseAssociate(long idAudit);

	
}
