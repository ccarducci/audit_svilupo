package it.tecnet.crs.audit.service;

import it.tecnet.crs.audit.web.dto.AuAuditMRisesprDto;
import it.tecnet.crs.audit.web.dto.AuAuditNonConfDto;
import it.tecnet.crs.audit.web.dto.AuAuditVarCompDto;
import it.tecnet.crs.jpa.model.AuMDomanda;
import it.tecnet.crs.jpa.model.AuMNonConf;
import it.tecnet.crs.jpa.model.AuMRischio;
import it.tecnet.crs.jpa.model.AuMRisepr;
import it.tecnet.crs.jpa.model.AuMVarcomp;
import it.tecnet.crs.mod.jpa.model.CrsSottoprocesso;

import java.util.List;

public interface AuAuditRischiENonConformitaService {

	
	List<AuMRischio> getRischi(long idAudit,Integer pageNumber,Integer pageSize, int columnNameToOrder, String orderType,String search);

	List<AuAuditMRisesprDto> getEsprRischi(long idAudit,Integer pageNumber,Integer pageSize, int columnNameToOrder, String orderType,String search);
	
	List<AuAuditNonConfDto> getNonConf(long idAudit,Integer pageNumber,Integer pageSize, int columnNameToOrder, String orderType,String search);
	
	List<AuAuditVarCompDto> getVarComp(long idAudit,Integer pageNumber,Integer pageSize, int columnNameToOrder, String orderType,String search);
	
	int countListaRischi(long idAudit, int getiDisplayStart,
			int getiDisplayLength, int getiSortCol_0, String getsSortDir_0,
			String getsSearch);
	
	int countListaEsprRischi(long idAudit, int getiDisplayStart,
			int getiDisplayLength, int getiSortCol_0, String getsSortDir_0,
			String getsSearch);
	
	int countListaNonConf(long idAudit, int getiDisplayStart,
			int getiDisplayLength, int getiSortCol_0, String getsSortDir_0,
			String getsSearch);
	int countListaVarComp(long idAudit, int getiDisplayStart,
			int getiDisplayLength, int getiSortCol_0, String getsSortDir_0,
			String getsSearch);

	//questi metodi servono a controllare che l'utente non abbia inserito dei codici
	//già presenti in Db.Il controllo del codice viene fatto lato client
	List<AuMRischio> checkCodiceRischio();
	List<AuMRisepr> checkCodiceEsprRischio();
	
	AuMRischio checkRischioInAuDomandaAuRisepr(AuMRischio i);
	
	AuMNonConf checkNcInVarComportamentale(AuMNonConf i);
	
	AuMRischio getRischioOptionDropModifica(long idEsprRischio);
	
	List<CrsSottoprocesso> getFasi(long idAudit);
	
	void deleteRischio(AuMRischio i);
	
	public <T> T salva(T obj) ;
	
	public <T> T cerca(Class<T> obj , Object pk);
	
	public void remove(Object obj);
	


	List<AuMRischio> getRischiPerDropDown(long idAudit);

	//aside variante comportamentlae
	List<AuMNonConf> getNC(long idAudit);

	List<AuMVarcomp> checkCodice();

	List<AuMNonConf> checkCodiceNC();

	
	

	

	
	

	

	

	

	

	

	



	

	

	
	

	

	

	

	

	

}
