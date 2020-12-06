package it.tecnet.crs.audit.jpa.dao;
import it.tecnet.crs.jpa.model.AuAssUtenteAudit;
import it.tecnet.crs.jpa.model.AuAssUtenteSessione;
import it.tecnet.crs.jpa.model.AuAudit;
import it.tecnet.crs.jpa.model.AuMDomanda;
import it.tecnet.crs.jpa.model.AuMRisposta;
import it.tecnet.crs.jpa.model.AuQuestionario;
import it.tecnet.crs.mod.web.dto.ProcessoDto;
import it.tecnet.crs.web.dto.NonConformitaDto;

import java.util.List;

public interface AuAuditDao {

	public List<Object[]> getListAuditGenerale(long idUtente,Integer pageNumber,Integer pageSize, int columnNameToOrder, String orderType,String search);
	
	public Integer countAudit(long idUtente,String textSearch);
	
	public long getIdDirigenteFromDelegato(long idDelegato);
	
	public void salvaAudit(AuAudit a);
	
	public <T> Object salva(T obj);

	public AuAudit getAuditDaModificare(long idAudit);
	
	public void salvaModifiche(AuAudit a);

	//popola la tabella delle fasi
	public List<Object[]> getFasiModificaGenerale(long idAudit,Integer pageNumber,Integer pageSize, int columnNameToOrder, String orderType,String search);

	//conta il numero di righe della tabella fasi
	public Integer countFasiAudit(long idAudit,String textSearch);
	
	
	//public List<AuditFasiDto> getFasiDaAggiungereAllAudit(long idAudit, long idProcesso);
	//QUERY TABELLA ASIDE DA MENU A TENDINA
	public List<Object[]> getProcessi();
	
	//popola la tabella in aside fasi 
	public List<Object[]>  getFasiInAsideTable(long idProcesso,long idAudit, Integer pageNumber,Integer pageSize, int columnNameToOrder, String orderType,String search);


	public int countFasiInAsideTable(long idProcesso, long idAudit,
			String textSearch);

	public void addFaseToAudit(long idFaseToSave, long idAudit);

	public void eliminaAudit(long idAudit);

	public void eliminaFase(long idAudit, long idSottoprocesso2);


	public List<Object[]> getListaNonConformita(Long idAudit,long idFase, Integer pageNumber,
			Integer pageSize, int columnNameToOrder, String orderType,String search);

	public int countNonConformita(long idAudit, long idFase, String getsSearch );
	
	//public int countCampagneAssociateAudit(long idAudit);
	
	public int countQuestionariAssociatiAudit(long idAudit);
	
	public void deleteQuestionario(long idAudit);
	

	
	public List<Object[]> getListaDelegati(long idDirigente, long idAudit,Integer pageNumber,Integer pageSize, int columnNameToOrder, String orderType,String search);
	
	public int countListaDelegati(long idDirigente, long idAudit, String textSearch);
	@Deprecated	
	public void dissociaDelegato(long idAudit, long idUtente);
	
	public List<Object[]> getListaAuditors(long idDirigente, long idAccesso,Integer pageNumber,Integer pageSize, int columnNameToOrder, String orderType,String search);
	
	public int countListaAuditors(long idDirigente, long idAccesso, String textSearch);
	@Deprecated	
	public void dissociaAuditors(long idAccesso, long idUtente);

	public List<Object[]> getDomande(long idAudit, int pageNumber,int pageSize, int columnNameToOrder, String orderType, String search);

	public int countDomande(long idAudit, int pageNumber, int pageSize,
			int columnNameToOrder, String orderType, String search);

	public long getNumDomandeStessoRischio(long idRischio, long idQuestionario);

	//nuova domanda
	public Double sommaPesiDomandeStessoRischio(long idRischio,long idQuestionario);
	//modifica domanda
	public Double sommaPesiDomandeStessoRischio(long idRischio,long idQuestionario, long idDomanda);

	public List<AuMRisposta> getRisposteDomanda(long idDomanda, int pageNumber,int pageSize, int columnNameToOrder, String orderType, String search);

	public int countRisposte(long idDomanda, int pageNumber, int pageSize,
			int columnNameToOrder, String orderType, String search);

	public AuMDomanda checkRisposteDomanda(AuMDomanda i);

	public AuQuestionario cercaQuestionario(long idAudit);

	public AuAssUtenteAudit getAssociazioneDelegato(long idAudit, long idUtente);
	
	public AuAssUtenteSessione getAssociazioneAuditors(long idSessione, long idUtente);

	int isAuditReferenced(long idAudit);
	
}
	