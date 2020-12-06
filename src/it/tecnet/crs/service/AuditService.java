package it.tecnet.crs.service;

import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoFaseAcquisizioneIstanza;
import it.tecnet.crs.ATPO.auditors.web.dto.AtpoTipologicheDto;
import it.tecnet.crs.jpa.model.AuSPraCalIndLog;
import it.tecnet.crs.jpa.model.AuSPratica;
import it.tecnet.crs.jpa.model.AuSessioni;
import it.tecnet.crs.web.dto.AuMVarCompDto;
import it.tecnet.crs.web.dto.AuTplTipologicheDto;
import it.tecnet.crs.web.dto.NonConformitaAccessiDto;
import it.tecnet.crs.web.dto.NonConformitaPraticheDto;
import it.tecnet.crs.web.dto.PraticaConRischioDto;
import it.tecnet.crs.web.dto.RischiAccessiDto;
import it.tecnet.crs.web.dto.RischiPraticheDto;
import it.tecnet.crs.web.dto.SessioneDto;
import it.tecnet.crs.web.dto.VerbaleDto;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

public interface AuditService {

	/*
	 * AUDIT
	 */
	public Long getIdAuditBySessione(Long idSessione);

	/*
	 * SESSIONI
	 */

	public List<SessioneDto> getListaSessioniUtente(long idUtente,
			Integer pageNumber, Integer pageSize, String columnNameToOrder,
			String orderType, String textSearch);

	public Integer countAllSessioniUtente(long idUtente, String textSearch);

	public String getNotaSessione(long idSessione);

	public List<SessioneDto> getListaSessioniUtenteAsDirigente(long idUtente,
			Integer pageNumber, Integer pageSize, String columnNameToOrder,
			String orderType, String textSearch);

	public Integer countAllSessioniUtenteAsDirigente(long idUtente,
			String textSearch);

	
	/*
	 * VERBALE
	 */

	public List<VerbaleDto> getListaPraticheSessione(long idSessione,
			Integer pageNumber, Integer pageSize, String columnNameToOrder,
			String orderType, String textSearch);

	public Integer countAllPraticheSessione(long idSessione, String textSearch);

	

	

	

	/*
	 * PRATICHE CON RISCHIO
	 */

	public int countAllPraticheConRischio(int sessionId, String textSearch,
			String columnNameToOrder, String orderType, Integer pageNumber,
			Integer pageSize);

	@Transactional
	public List<PraticaConRischioDto> getListaPraticheRischio(int sessionId,
			int getiDisplayStart, int getiDisplayLength, String colunmName,
			String getsSortDir_0, String getsSearch);

	

	public List<RischiPraticheDto> getTabRischiPrat(long idAudit,
			Integer pageNumber, Integer pageSize, int columnNameToOrder,
			String orderType, String search);

	public int countTabRischiPrat(long idAudit, Integer pageNumber,
			Integer pageSize, int columnNameToOrder, String orderType,
			String search);

	public List<NonConformitaPraticheDto> getTabNonConfPrat(long idAudit,
			Integer pageNumber, Integer pageSize, int columnNameToOrder,
			String orderType, String search);

	public int countTabConfPrat(long idAudit, Integer pageNumber,
			Integer pageSize, int columnNameToOrder, String orderType,
			String search);
	
	public List<NonConformitaAccessiDto> getTabNonConfAccess(long idAudit,
			long idSSessione,String filtro,Integer pageNumber, Integer pageSize, int columnNameToOrder,
			String orderType, String search);
	
	public int countTabNonConfAccess(long idAudit, long idSSessione,String filtro, Integer pageNumber,
			Integer pageSize, int columnNameToOrder, String orderType,
			String search);

	public NonConformitaAccessiDto getDettagliNonConformita(long idNonConfM, long idNonConfS);

	public List<RischiAccessiDto> getTabRischiAccess(long idSessione,Integer pageNumber, Integer pageSize, int columnNameToOrder, String orderType, String search);

	public int countTabRischiAccess(long idSessione,Integer pageNumber, Integer pageSize, int columnNameToOrder, String orderType, String search);

	public RischiAccessiDto getDettagliRischi(long idRischioM, long idRischioS);

	
	public <T> void salva(T obj);

	public <T> T cerca(Class<T> obj, Object pk);

	/*
	 * TIPOLOGICHE
	 */

	public List<AtpoTipologicheDto> getCorrettoErrato();

	public List<AtpoTipologicheDto> getModalitaNotifica();

	public List<AtpoTipologicheDto> getChiNotifica();

	public List<AtpoTipologicheDto> getSiNo();

	public List<AtpoTipologicheDto> getEsitoReg();

	public List<AtpoTipologicheDto> getErroreEsito();

	public List<AtpoTipologicheDto> getComDiscRdl();

	public List<AtpoTipologicheDto> getCredPrescr();

	public List<AuTplTipologicheDto> getTipologica(String tipo);

	public AuSPratica getSPraticaByIDVerbale(long idVerbale);

	@Transactional
	public List<AuSPraCalIndLog> calcolaIndicatoriPratica(long idVerbale) throws Exception;

	public List<String> getDescrizioneFaseAssociate(long idAudit);

	public List<AuMVarCompDto> getVarCompNonConfAccessi(long idNonConfM,
			long idNonConfS, int getiDisplayStart, int getiDisplayLength,
			int getiSortCol_0, String getsSortDir_0, String getsSearch);

	public List<RischiAccessiDto> getStatoEsprRischioTable(long idMRischio,
			long idSsessione, int getiDisplayStart, int getiDisplayLength,
			int getiSortCol_0, String getsSortDir_0, String getsSearch);

	public int countVarCompNonConfAccessi(long idNonConfM, long idNonConfS,
			 String textSearch);

	public int countStatoEsprRischioTable(long idMRischio, long idSsessione,
			String getsSearch);

	public List<Object> getListFase(String nameGrafico);



	
	
	
	
}
