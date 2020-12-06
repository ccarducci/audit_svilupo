package it.tecnet.crs.jpa.dao;

import it.tecnet.crs.jpa.model.AuAudit;
import it.tecnet.crs.jpa.model.AuCampagna;
import it.tecnet.crs.jpa.model.AuMNonConf;
import it.tecnet.crs.jpa.model.AuMRischio;
import it.tecnet.crs.jpa.model.AuMRisepr;
import it.tecnet.crs.jpa.model.AuMVarcomp;
import it.tecnet.crs.jpa.model.AuSPraCalIndLog;
import it.tecnet.crs.jpa.model.AuSPratica;
import it.tecnet.crs.jpa.model.AuSessioni;
import it.tecnet.crs.web.dto.NonConformitaDto;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;


public interface AuditDao {

	/*
	 * 		AUDIT
	 * */
	public Long getIdAuditBySessione(Long idSessione);


	
	
	public List<Object []> getListaSessioniUtente(long idUtente,Integer pageNumber, Integer pageSize, String columnNameToOrder, String orderType, String textSearch);
	public Integer countAllSessioniUtente(long idUtente,String textSearch);
	public String getNotaSessione(long idSessione);
	public void salvaNota(AuSessioni s);
	public List<AuSessioni> getListaSessioniUtenteAsDirigente(long idUtente,Integer pageNumber, Integer pageSize, String columnNameToOrder, String orderType, String textSearch);
	public Integer countAllSessioniUtenteAsDirigente(long idUtente,String textSearch);

	/*
	 * 		VERBALE
	 * */
	

	public List<NonConformitaDto> mediaNonConformita(long idSessione);
	/*
	 * 		VERBALE MANUALE NOTIFICA
	 * */

	
	public List<Object[]> getTabRischiPrat(long idAudit, Integer pageNumber,Integer pageSize, int columnNameToOrder, String orderType,
			String search);
	
	public int countTabRischiPrat(long idAudit, Integer pageNumber,
			Integer pageSize, int columnNameToOrder, String orderType,
			String search);
	

	public List<Object[]> getListaNonConformitaPratiche(long idAudit,Integer pageNumber, Integer pageSize, int columnNameToOrder,
			String orderType, String search);
	

	public List<Object[]> getListaNonConformitaAccessi(long idAudit,long idSessione, String filtro,Integer pageNumber, Integer pageSize,
			int columnNameToOrder, String orderType, String search);
	


	public List<Object[]> getTabRischiAccess(long idSSessione, Integer pageNumber, Integer pageSize, int columnNameToOrder, String orderType, String search);


	
	public <T> void salva(T obj);

	public <T> T cerca(Class<T> obj , Object pk);

	/*
	 * TIPOLOGICHE
	 */
	public List<Object[]> getCorrettoErrato();

	public List<Object[]> getModalitaNotifica();

	public List<Object[]> getChiNotifica();

	public List<Object[]> getSiNo();

	public List<Object[]> getEsitoReg();

	public List<Object[]> getErroreEsito();

	public List<Object[]> getComDiscRdl();

	public List<Object[]> getCredPrescr();
	
	public AuSPratica getSPraticaByIDVerbale(long idVerbale);

	public Integer countAllNonConformitaPratiche(long idAudit, String search);

	public List<Object[]> getTipologica(String tipo);

	public long getIdCambagnaByIdSSessione(long idSSessione);
	
	public AuCampagna getCampagnaById(long idCampagna);
	
	public AuAudit getAuditById(long idAudit);

	public Object[] getDettagliNonConformita(long idNonConfM, long idNonConfS);

	public int countTabNonConfAccess(long idAudit, long idSSessione,String filtro,
			String search);

	public int countPraticheNC(long idNonConformita, long idSessione);

	public List<AuMRisepr> getEspressioRischioByAudit(long idAudit);

	public List<AuMRischio> getRishioByAudit(long idAudit);

	

	@Transactional
	public void deleteSPtaricaVarcompByIdSPratica(long idSPratica);
	
	@Transactional
	public void deleteSPtaricaRisByIdSPratica(long idSPratica);

	List<AuMNonConf> getMNonConfByAudit(long idAudit);
	
	@Transactional
	public void salvaSPraticaCalIndLog(AuSPraCalIndLog auSPraticaCalIndLog);

	public List<AuMVarcomp> getAuMVarcomp();

	public int countTabRischiAccess(long idSessione, String search);
	public Object[] getDettagliRischi(long idRischioM, long idRischioS);




	public List<Object[]> getDescrizioneFaseAssociate(long idAudit);




	public List<Object[]> getVarCompNonConfAccessi(long idNonConfM, long idNonConfS,
			int pageNumber, int pageSize, int columnNameToOrder,
			String orderType, String search);




	public List<Object[]> getStatoEsprRischioTable(long idMRischio,
			long idSsessione, int pageNumber, int pageSize,
			int columnNameToOrder, String orderType, String search);




	public int countVarCompNonConfAccessi(long idNonConfM, long idNonConfS,
			String textSearch);




	public int countStatoEsprRischioTable(long idMRischio, long idSsessione,
			String getsSearch);




	public List<Object[]> getListFase(String nameGrafico);

}

	
