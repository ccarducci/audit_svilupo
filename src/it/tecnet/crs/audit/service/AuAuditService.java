package it.tecnet.crs.audit.service;

import it.tecnet.crs.audit.web.dto.AuAuditDomandaDto;
import it.tecnet.crs.audit.web.dto.AuAuditDto;
import it.tecnet.crs.audit.web.dto.AuAuditorsDto;
import it.tecnet.crs.audit.web.dto.AuditDelegatoDto;
import it.tecnet.crs.audit.web.dto.AuditFasiDto;
import it.tecnet.crs.jpa.model.AuAssUtenteAudit;
import it.tecnet.crs.jpa.model.AuAssUtenteSessione;
import it.tecnet.crs.jpa.model.AuAudit;
import it.tecnet.crs.jpa.model.AuMDomanda;
import it.tecnet.crs.jpa.model.AuMRisposta;
import it.tecnet.crs.jpa.model.AuQuestionario;
import it.tecnet.crs.mod.web.dto.ProcessoDto;
import it.tecnet.crs.web.dto.NonConformitaDto;

import java.util.List;

public interface AuAuditService {
	
	
	/*
	 * AUDIT - GENERALE
	 */
	public List<AuAuditDto> getListAuditGenerale(long idUtente,Integer pageNumber,Integer pageSize, int columnNameToOrder, String orderType,String search);

	public Integer countAudit(long idUtente,String textSearch);
	
	public void salvaAudit(AuAudit a);

	public long getIdDirigenteFromDelegato(long idDelegato);
	
	public AuAudit getAuditDaModificare(long idAudit);
	
	public void salvaModifiche(AuAudit a);

	//AUDTI -GENERALE-MODIFICA: TABELLA TAB FASI MODIFICA GENERALE
	public List<AuditFasiDto> getFasiModificaGenerale(long idAudit,Integer pageNumber,Integer pageSize, int columnNameToOrder, String orderType,String search);

	//count delle righe tabella 
	public Integer countFasiAudit(long idAudit,String textSearch);
	
	//popola la tabella in aside aggiungi fasi
	public List<AuditFasiDto>  getFasiInAsideTable(long idProcesso,long idAudit, Integer pageNumber,Integer pageSize, int columnNameToOrder, String orderType,String search);

	
	//popola il menu a tendina nell aside delle fasi
	public List<ProcessoDto> getProcessi();

	//conta le fasi nell'aside fasi
	public Integer countFasiInAsideTable(long idProcesso, long idAudit,
			String textSearch);

	//aggiunge fase all audit
	public void addFaseToAudit(long idFaseToSave, long idAudit);

	//cancella audit
	public void deleteAudit(long idAudit);

	//elimina fase assegnata all'audit
	public void eliminaFase(long idAudit, long idSottoprocesso);

	
	//get lista non conformita associata alla fase
	public List<NonConformitaDto> getListaNonConformita(Long idAudit,long idFase, Integer pageNumber,
			Integer pageSize, int sSortCol, String orderType,String search);

	public Integer countNonConformita(long idAudit, long idFase, String getsSearch );

	//questionario
	public AuQuestionario cercaQuestionario(long idAudit);
	
	public List<AuAuditDomandaDto> getDomande(long idAudit, int pageNumber,int pageSize, int columnNameToOrder, String orderType,String search);
	
	public <T> Object salva(T obj);
	
	//public int checkIfAuditHasCampagne(long idAudit);
	public int isAuditReferenced(long idAudit);
	
	public int checkIfAuditHasQuestionari(long idAudit);
	
	public void deleteQuestionario(long idAudit);
	
	
	
	public List<AuditDelegatoDto> getListaDelegati(long idDirigente, long idAudit,Integer pageNumber,Integer pageSize, int columnNameToOrder, String orderType,String search);
	
	public int countListaDelegati(long idDirigente, long idAudit, String textSearch);
	@Deprecated
	public void dissociaDelegato(long idAudit, long idUtente);
	
	public List<AuAuditorsDto> getListaAuditors(long idDirigente, long idAccesso,Integer pageNumber,Integer pageSize, int columnNameToOrder, String orderType,String search);
	
	public int countListaAuditors(long idDirigente, long idAccesso, String textSearch);
	@Deprecated
	public void dissociaAuditors(long idAccesso, long idUtente);

	public int countDomande(long idAudit, int getiDisplayStart,
			int getiDisplayLength, int getiSortCol_0, String getsSortDir_0,
			String getsSearch);

	public long getNumDomandeStessoRischio(long idRischio, long idQuestionario);

	//nuovo domanda
	public Double sommaPesiDomandeStessoRischio(long idRischio,
			long idQuestionario);

	//modifica domanda
	public Double sommaPesiDomandeStessoRischio(long idRischio,
			long idQuestionario, long idDomanda);

	public List<AuMRisposta> getRisposteDomanda(long idDomanda, int pageNumber,int pageSize, int columnNameToOrder, String orderType,String search);

	public int countRisposte(long idDomanda, int pageNumber,int pageSize, int columnNameToOrder, String orderType,String search);

	public AuMDomanda checkRisposteDomanda(AuMDomanda i);

	

	public AuAssUtenteAudit getAssociazioneDelegato(long idAudit, long idUtente);
	
	public AuAssUtenteSessione getAssociazioneAuditors(long idSessione, long idUtente);

}
