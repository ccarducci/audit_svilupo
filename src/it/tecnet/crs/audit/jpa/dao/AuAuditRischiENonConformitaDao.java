package it.tecnet.crs.audit.jpa.dao;

import it.tecnet.crs.jpa.model.AuMNonConf;
import it.tecnet.crs.jpa.model.AuMRischio;
import it.tecnet.crs.jpa.model.AuMRisepr;
import it.tecnet.crs.jpa.model.AuMVarcomp;
import it.tecnet.crs.mod.jpa.model.CrsSottoprocesso;

import java.util.List;

public interface AuAuditRischiENonConformitaDao {

	
	/*
	 * TABELLA RISCHI___________________________________________________________________________
	 */
	List<AuMRischio> getRischi(long idAudit, Integer pageNumber,
			Integer pageSize, int columnNameToOrder, String orderType,
			String search);
	
	int countListaRischi(long idAudit, int pageNumber, int pageSize,
			int columnNameToOrder, String orderType, String search);
	
	void delete(AuMRischio i);
	
	List<AuMRischio> checkCodiceRischio();
	
	AuMRischio checkRischioInAuDomandaAuRisepr(AuMRischio i);
	/*
	 * TABELLA ESPRESSIONE RISCHIO______________________________________________________________
	 */
	List<Object[]> getEsprRischi(long idAudit, Integer pageNumber,
			Integer pageSize, int columnNameToOrder, String orderType,
			String search);
	int countListaEsprRischi(long idAudit, int pageNumber, int pageSize,
			int columnNameToOrder, String orderType, String search);
	
	AuMRischio getRischioOptionDropModifica(long idEsprRischio);
	
	List<AuMRisepr> checkCodiceEsprRischio();
	
	List<AuMRischio> getRischiPerDropDown(long idAudit);
	/*
	 * TABELLA NON CONFORMITA___________________________________________________________________
	 */
	List<Object[]> getNonConf(long idAudit, Integer pageNumber,
			Integer pageSize, int columnNameToOrder, String orderType,
			String search);
	
	int countListaNonConf(long idAudit, int pageNumber, int pageSize,
			int columnNameToOrder, String orderType, String search);
	
	List<CrsSottoprocesso> getFasi(long idAudit);
	
	AuMNonConf checkNcInVarComportamentale(AuMNonConf i);
	
	/*
	 * TABELLA VARIANTE COMPORTAMENTALE_________________________________________________________
	 */
	List<Object[]> getVarComp(long idAudit, Integer pageNumber,
			Integer pageSize, int columnNameToOrder, String orderType,
			String search);
	int countListaVarComp(long idAudit, Integer pageNumber, Integer pageSize,
			int columnNameToOrder, String orderType, String search);
	
	List<AuMNonConf> getNC(long idAudit);
	
	List<AuMVarcomp> checkCodice();
	/*
	 *  FUNZIONI COMUNI_________________________________________________________________________________________
	 */
	public <T> T save(T entity);
	
	public <T> T cerca(Class<T> obj, Object pk);

	public void remove(Object obj);

	List<AuMNonConf> checkCodiceNC();





	

	
	

	

	

	

	

	

	

	

	
	

	
	

	

	

	

}
